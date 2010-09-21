/* *********************************************************************** */
/* *********************************************************************** */

#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <stdio.h>
#include <assert.h>
#include <fcntl.h>
#include <stdarg.h>
#include <alloca.h>
#include <arpa/inet.h>
#include <time.h>

#include "Launcher.h"
#include "TCP.h"

/* *********************************************************************** */
/*     utility methods, called outside of the launcher					   */
/* *********************************************************************** */
const char* CTRL_MSG_TYPE_STRINGS[] = {"HELLO", "GOODBYE", "PORT_REQUEST", "PORT_RESPONSE"};

int Launcher::setPort(uint32_t place, char* port)
{
	if (port == NULL)
		return -1;

	if (_singleton == NULL)
	{
		// send this out to our local launcher
		if (_parentLauncherControlLink <= 0)
		{
			if (getenv(X10LAUNCHER_PARENT) != NULL)
			{
				#ifdef DEBUG
					fprintf(stderr, "Runtime %u connecting to launcher at \"%s\"\n", place, getenv(X10LAUNCHER_PARENT));
				#endif

				_parentLauncherControlLink = TCP::connect((const char *) getenv(X10LAUNCHER_PARENT), 10);
			}
			if (_parentLauncherControlLink <= 0)
				return -1; // connection failed for some reason
		}

		struct ctrl_msg m;
		m.type = HELLO;
		m.from = place;
		m.to = place;
		m.datalen = strlen(port);
		int r = TCP::write(_parentLauncherControlLink, &m, sizeof(struct ctrl_msg));
		if (r <= 0)
			return -1;
		TCP::write(_parentLauncherControlLink, port, m.datalen);
		#ifdef DEBUG
			fprintf(stderr, "Runtime %u: sent port data \"%s\" to launcher\n", place, port);
		#endif
	}
	else
	{
		_singleton->_runtimePort = (char*)malloc(strlen(port)+1);
		strcpy(_singleton->_runtimePort, port);
	}
	return 1;
}

int Launcher::lookupPlace(uint32_t myPlace, uint32_t destPlace, char* response, int responseLen)
{
	// TODO - this isn't reentrant.  Need to fix that.
	struct ctrl_msg m;
	m.type = PORT_REQUEST;
	m.from = myPlace;
	m.to = destPlace;
	m.datalen = 0;

	if (_singleton == NULL)
	{
		// inside this if, we're running within the runtime process, and talking to a launcher
		// send this out to our local launcher
		// parent link was established earlier, at HELLO
		if (_parentLauncherControlLink <= 0)
			DIE("Runtime %u: bad link to launcher", myPlace);

		#ifdef DEBUG
			fprintf(stderr, "Runtime %u: sending %s:%u to launcher %u\n", myPlace, CTRL_MSG_TYPE_STRINGS[m.type], destPlace, myPlace);
		#endif
		int ret = TCP::write(_parentLauncherControlLink, &m, sizeof(struct ctrl_msg));
		if (ret <= 0)
			DIE("Runtime %u: Unable to write port request", myPlace);

		// this should block, until the data is available
		#ifdef DEBUG
			fprintf(stderr, "Runtime %u: waiting for %s:%u message from launcher %u\n", myPlace, CTRL_MSG_TYPE_STRINGS[PORT_RESPONSE], destPlace, myPlace);
		#endif
		ret = TCP::read(_parentLauncherControlLink, &m, sizeof(struct ctrl_msg));
		if (ret <= 0 || m.type != PORT_RESPONSE || m.datalen <= 0)
			DIE("Runtime %u: Invalid port request reply (len=%d, type=%s, datalen=%d)", myPlace, ret, CTRL_MSG_TYPE_STRINGS[m.type], m.datalen);

		if (responseLen < m.datalen+1)
			DIE("Runtime %u: The buffer is too small for the place lookup (data=%d bytes, buffer=%d bytes)", myPlace, m.datalen, responseLen);

		#ifdef DEBUG
			fprintf(stderr, "Runtime %d: waiting for %s data from launcher %u\n", myPlace, CTRL_MSG_TYPE_STRINGS[PORT_RESPONSE], myPlace);
		#endif
		ret = TCP::read(_parentLauncherControlLink, response, m.datalen);
		if (ret <= 0)
			DIE("Runtime %u: Unable to read port response data", myPlace);
		response[m.datalen] = '\0';

		#ifdef DEBUG
			fprintf(stderr, "Runtime %u: determined place %u is at \"%s\"\n", myPlace, destPlace, response);
		#endif

		return m.datalen;
	}

	// if we're here, then this is executing in the launcher process, not in a runtime.
	// we can't handle this request.  Forward it to some launcher that can.
	return _singleton->forwardMessage(&m, NULL);
}

/* *********************************************************************** */
/*     start all children. If there are no children, nothing is done       */
/* *********************************************************************** */

void Launcher::startChildren()
{
	#ifdef DEBUG
		fprintf(stderr, "Launcher %u: starting %d child%s\n", _myproc, _numchildren, _numchildren==1?"":"ren");
	#endif

	/* -------------------------------------------- */
	/*    create and initialize listener FD         */
	/* -------------------------------------------- */

	unsigned listenPort = 0;
	_listenSocket = TCP::listen(&listenPort, 10);
	if (_listenSocket < 0)
		DIE("Launcher %u: cannot create listener port", _myproc);
	#ifdef DEBUG
		fprintf(stderr, "Launcher %u: opened listen socket at port %d\n", _myproc, listenPort);
	#endif

	// allocate space to hold all the links to the child launchers and the runtime
	// Child launchers (if any) are first in the list, followed by the local runtime in the last slot.
	_pidlst = (int *) malloc(sizeof(int) * (_numchildren+1));
	_childCoutLinks = (int *) malloc(sizeof(int) * (_numchildren+1));
	_childCerrorLinks = (int *) malloc(sizeof(int) * (_numchildren+1));
	_childControlLinks = (int *) malloc(sizeof(int) * (_numchildren+1));

	if (!_pidlst || !_childControlLinks || !_childCoutLinks || !_childCerrorLinks)
		DIE("%u: failed in alloca()", _myproc);

	for (uint32_t id=0; id <= _numchildren; id++)
	{
		int pid, outpipe[2], errpipe[2];
		if (pipe(outpipe) < 0) DIE("Launcher %u: failed in outpipe()", _myproc);
		if (pipe(errpipe) < 0) DIE("Launcher %u: failed in errpipe()", _myproc);
		if ((pid = fork()) < 0) DIE("Launcher %u: failed in fork()", _myproc);
		else if (pid > 0)
		{
			// parent process
			_pidlst[id] = pid;
			_childControlLinks[id] = -1; // these get set later, when the child connects
			_childCoutLinks[id] = outpipe[0];
			close(outpipe[1]);
			_childCerrorLinks[id] = errpipe[0];
			close(errpipe[1]);
			fcntl(_childCoutLinks[id], F_SETFL, O_NONBLOCK | O_NDELAY);
			fcntl(_childCerrorLinks[id], F_SETFL, O_NONBLOCK | O_NDELAY);
		}
		else
		{
			// child process
			// redirect stderr and stdout over to our pipes
/*			int z1 = dup2(outpipe[1], fileno(stdout));
			close(outpipe[0]);
			int z2 = dup2(errpipe[1], fileno(stderr));
			close(errpipe[0]);
			if (z1 != 1 || z2 != 2)
				DIE("Launcher %d: DUP failed", _myproc);
*/
			char masterPort[1024];
			TCP::getname(_listenSocket, masterPort, sizeof(masterPort));

			if (id == _numchildren && _myproc != 0xFFFFFFFF)
			{ // start up the local x10 runtime
				unsetenv(X10LAUNCHER_HOSTFILE);
				unsetenv(X10LAUNCHER_SSH);
				setenv(X10LAUNCHER_PARENT, masterPort, 1);
				setenv(X10LAUNCHER_RUNTIME, "1", 1);
				#ifdef DEBUG
					fprintf(stderr, "Runtime %u forked.  Running exec.\n", _myproc);
				#endif
				if (execvp(_argv[0], _argv))
					// can't get here, if the exec succeeded
					DIE("Launcher %u: runtime exec failed", _myproc);
			}
			else
			{
				/* SSH children: call startSSH client */
				if (_hostlist && strncmp(_hostlist[_firstchildproc+id], "localhost", 9) != 0)
					startSSHclient(_firstchildproc+id, masterPort, _hostlist[_firstchildproc+id]);
				else
				{ // if the child is on the localhost, just exec it.  No need for ssh.
					setenv(X10LAUNCHER_PARENT, masterPort, 1);
					char idString[24];
					sprintf(idString, "%d", _firstchildproc+id);
					setenv(X10LAUNCHER_MYID, idString, 1);
					#ifdef DEBUG
						fprintf(stderr, "Launcher %u forked launcher %s on localhost.  Running exec.\n", _myproc, idString);
					#endif
					if (execvp(_argv[0], _argv))
						DIE("Launcher %u: local child launcher exec failed", _myproc);
				}
			}
		}
	}

	/* process manager invokes main loop */
	handleRequestsLoop();
}


/* *********************************************************************** */
/* this is the infinite loop that the process manager is executing.        */
/* *********************************************************************** */

void Launcher::handleRequestsLoop()
{
	#ifdef DEBUG
		fprintf(stderr, "Launcher %u: main loop start\n", _myproc);
	#endif
	bool running = true;

	while (running)
	{
		struct timeval timeout = { 0, 100000 };
		fd_set infds, efds;
		int fd_max = makeFDSets(&infds, NULL, &efds);
		if (select(fd_max+1, &infds, NULL, &efds, &timeout) < 0)
			break; // select error.  This can happen when we're in the middle of shutdown

		/* listener socket (new connections) */
		if (_listenSocket >= 0)
		{
			if (FD_ISSET(_listenSocket, &efds))
			{
				#ifdef DEBUG
					fprintf(stderr, "Launcher %u: listensocket ERROR detected\n", _myproc);
				#endif
				close(_listenSocket);
				_listenSocket = -1;
			}
			else if (FD_ISSET(_listenSocket, &infds))
				handleNewChildConnection();
		}
		/* parent control socket */
		if (_parentLauncherControlLink >= 0)
		{
			if (FD_ISSET(_parentLauncherControlLink, &efds))
				running = handleDeadParent();
			else if (FD_ISSET(_parentLauncherControlLink, &infds))
				if (handleControlMessage(_parentLauncherControlLink) < 0)
					running = handleDeadParent();
		}
		/* runtime and children output, stdout and stderr */
		for (uint32_t i = 0; i <= _numchildren; i++)
		{
			if (_childControlLinks[i] >= 0)
			{
				if (FD_ISSET(_childControlLinks[i], &efds))
					running = handleDeadChild(i);
				else if (FD_ISSET(_childControlLinks[i], &infds))
					if (handleControlMessage(_childControlLinks[i]) < 0)
						running = handleDeadChild(i);
			}

			if (_childCoutLinks[i] >= 0)
			{
				if (FD_ISSET(_childCoutLinks[i], &efds))
					running = handleDeadChild(i);
				else if (FD_ISSET(_childCoutLinks[i], &infds))
					running = handleChildCout(i);
			}

			if (_childCerrorLinks[i] >= 0)
			{
				if (FD_ISSET(_childCerrorLinks[i], &efds))
					running = handleDeadChild(i);
				else if (FD_ISSET(_childCerrorLinks[i], &infds))
					running = handleChildCerror(i);
			}
		}
	}

	/* --------------------------------------------- */
	/* end of main loop. kill & wait every process   */
	/* --------------------------------------------- */
	//cleanup:

	#ifdef DEBUG
		fprintf(stderr, "Launcher %u: killing sub-processes\n", _myproc);
	#endif

	int status = 0;
	for (uint32_t i = 0; i <= _numchildren; i++)
	{
		#ifdef DEBUG
			fprintf(stderr, "Launcher %u: killing pid=%d\n", _myproc, _pidlst[i]);
		#endif
		int status1;
		kill(_pidlst[i], SIGTERM);
		waitpid(_pidlst[i], &status1, WNOHANG);
		if (status1 != 0)
			status = status1;
	}
	// shut down any connections if they still exist
	handleDeadParent();

	// free up allocated memory (not really needed, since we're about to exit)
	free(_hostlist);
	free(_runtimePort);
	#ifdef DEBUG
		fprintf(stderr, "Launcher %u: cleanup complete.  Goodbye!\n", _myproc);
	#endif
	exit(status);
}

/* *********************************************************************** */
/*     prepare FD sets to listen to in main loop                           */
/* *********************************************************************** */

#define MAX(a,b) (((a)<(b))?(b):(a))
int Launcher::makeFDSets(fd_set * infds, fd_set * outfds, fd_set * efds)
{
	int fd_max = 0;
	FD_ZERO(infds);
	FD_ZERO(efds);

	/* listener socket */
	if (_listenSocket >= 0)
	{
		FD_SET (_listenSocket, infds);
		FD_SET (_listenSocket, efds);
		fd_max = MAX(fd_max, _listenSocket);
	}

	/* control socket to parent */
	if (_parentLauncherControlLink >= 0)
	{
		FD_SET (_parentLauncherControlLink, infds);
		FD_SET (_parentLauncherControlLink, efds);
		fd_max = MAX(fd_max, _parentLauncherControlLink);
	}

	/* control sockets to children */
	for (uint32_t i = 0; i <= _numchildren; i++)
	{
		if (_childControlLinks[i] >= 0)
		{
			FD_SET(_childControlLinks[i], infds);
			FD_SET(_childControlLinks[i], efds);
			fd_max = MAX(fd_max, _childControlLinks[i]);
		}
		if (_childCoutLinks[i] >= 0)
		{
			FD_SET(_childCoutLinks[i], infds);
			FD_SET(_childCoutLinks[i], efds);
			fd_max = MAX(fd_max, _childCoutLinks[i]);
		}
		if (_childCerrorLinks[i] >= 0)
		{
			FD_SET(_childCerrorLinks[i], infds);
			FD_SET(_childCerrorLinks[i], efds);
			fd_max = MAX(fd_max, _childCerrorLinks[i]);
		}
	}

	return fd_max;
}

/* *********************************************************************** */
/* open control connection to parent (if any) and announce ourselves       */
/* *********************************************************************** */

void Launcher::connectToParentLauncher(void)
{
	if (_myproc == 0)
		return; // don't connect - nothing useful for us at the primordial launcher.

	/* case 1: we have inherited the listener FD */
	if (_listenSocket >= 0)
	{
		char masterport[1024];
		TCP::getname(_listenSocket, masterport, sizeof(masterport));
		#ifdef DEBUG
			fprintf(stderr, "Launcher %u: connecting to parent via inherited port: %s\n", _myproc, masterport);
		#endif
		_parentLauncherControlLink = TCP::connect(masterport, 10);
	}

	/* case 2: the SOCK_PARENT env. var is set */
	else if (getenv(X10LAUNCHER_PARENT) != NULL)
	{
		#ifdef DEBUG
			fprintf(stderr, "Launcher %u: connecting to parent via: %s\n", _myproc, getenv(X10LAUNCHER_PARENT));
		#endif
		_parentLauncherControlLink = TCP::connect((const char *) getenv(X10LAUNCHER_PARENT), 10);
	}

	/* case 3: launcher=-1 has no parent. We don't connect */
	else
	{
		#ifdef DEBUG
			fprintf(stderr, "Launcher %u: has no parent.\n", _myproc);
		#endif
		_parentLauncherControlLink = -1;
		return;
	}

	/* we are hopefully connected. So we announce ourselves */
	if (_parentLauncherControlLink < 0)
		DIE("Launcher %u: failed to connect to parent", _myproc);

	struct ctrl_msg helloMsg;
	helloMsg.type = HELLO;
	helloMsg.from = _myproc;
	helloMsg.to = -1;
	helloMsg.datalen = 0;
	TCP::write(_parentLauncherControlLink, &helloMsg, sizeof(struct ctrl_msg));
}

/* *********************************************************************** */
/*   a new child is announcing itself on the listener socket               */
/* *********************************************************************** */

void Launcher::handleNewChildConnection(void)
{
	#ifdef DEBUG
		fprintf(stderr, "Launcher %u: new connection detected\n", _myproc);
	#endif
	/* accept the new connection and read off the rank */
	int fd = TCP::accept(_listenSocket);
	if (fd < 0)
	{
		close(_listenSocket);
		_listenSocket = -1;
	}

	struct ctrl_msg m;
	int size = TCP::read(fd, &m, sizeof(struct ctrl_msg));

	/* find rank in child list */
	if (size == sizeof(struct ctrl_msg) && m.type == HELLO)
	{
		if (m.from == _myproc)
		{
			_childControlLinks[_numchildren] = fd;
			if (m.datalen > 0)
			{
				_runtimePort = (char*)malloc(m.datalen+1);
				_runtimePort[m.datalen] = '\0';
				size = TCP::read(_childControlLinks[_numchildren], _runtimePort, m.datalen);
				if (size < m.datalen)
					DIE("Launcher %u: could not read local runtime data", _myproc);
			}
			#ifdef DEBUG
				fprintf(stderr, "Launcher %u: new ctrl conn %d from local runtime, with runtime port=\"%s\"\n", _myproc, fd, _runtimePort);
			#endif
			return;
		}

		for (uint32_t i = 0; i < _numchildren; i++) // the runtime is not in this loop
		{
			if (m.from == _firstchildproc + i)
			{
				_childControlLinks[i] = fd;
				#ifdef DEBUG
					fprintf(stderr, "Launcher %u: new ctrl conn from child=%d\n", _myproc, m.from);
				#endif
				if (m.datalen > 0)
				{
					char* data = (char*)alloca(m.datalen+1);
					data[m.datalen] = '\0';
					TCP::read(fd, data, m.datalen);
					DIE("Launcher %u: Control message from child launcher came in with datalen of \"%s\"\n", _myproc, data);
				}
				return;
			}
		}
	}

	// something bad came in.  Maybe some other program trying to connect to our port.  Disconnect it.
	close(fd);
	#ifdef DEBUG
		fprintf(stderr, "Launcher %u: Invalid %d byte message came into listen socket.  Closing it down.\n", _myproc, size);
	#endif
}

/* *********************************************************************** */
/*    A child connection has closed                                        */
/* *********************************************************************** */
// returns false if everything should die
bool Launcher::handleDeadChild(uint32_t childNo)
{
	// this is usually called multiple times, for stdin, stderr, etc
	if (_childControlLinks[childNo] >= 0)
	{
		close(_childControlLinks[childNo]);
		_childControlLinks[childNo] = -1;

		#ifdef DEBUG
		if (childNo == _numchildren && _myproc != 0xFFFFFFFF)
			fprintf(stderr, "Launcher %u: local runtime control disconnected\n", _myproc);
		else
			fprintf(stderr, "Launcher %u: child=%d control disconnected\n", _myproc, _firstchildproc+childNo);
		#endif
	}
	if (_childCerrorLinks[childNo] >= 0)
	{
		close(_childCerrorLinks[childNo]);
		_childCerrorLinks[childNo] = -1;

		#ifdef DEBUG
		if (childNo == _numchildren && _myproc != 0xFFFFFFFF)
			fprintf(stderr, "Launcher %u: local runtime cerror disconnected\n", _myproc);
		else
			fprintf(stderr, "Launcher %u: child=%d cerror disconnected\n", _myproc, _firstchildproc+childNo);
		#endif
	}
	if (_childCoutLinks[childNo] >= 0)
	{
		close(_childCoutLinks[childNo]);
		_childCoutLinks[childNo] = -1;

		#ifdef DEBUG
		if (childNo == _numchildren && _myproc != 0xFFFFFFFF)
			fprintf(stderr, "Launcher %u: local runtime cout disconnected\n", _myproc);
		else
			fprintf(stderr, "Launcher %u: child=%d cout disconnected\n", _myproc, _firstchildproc+childNo);
		#endif
	}

	// check to see if *all* child links are down
	for (uint32_t i=0; i<=_numchildren; i++)
	{
		if (_childControlLinks[i] >= 0) return false;
		if (_childCoutLinks[i] >= 0) return false;
		if (_childCerrorLinks[i] >= 0) return false;
	}
	return true;
}

/* *********************************************************************** */
/*    Parent connection has closed                                         */
/* *********************************************************************** */

bool Launcher::handleDeadParent()
{
	#ifdef DEBUG
		fprintf(stderr, "Launcher %u: parent disconnected\n", _myproc);
	#endif
	if (_parentLauncherControlLink != -1)
		close(_parentLauncherControlLink);
	_parentLauncherControlLink = -1;
	for (uint32_t i = 0; i <= _numchildren; i++)
		handleDeadChild(i);
	return false;
}

/* *********************************************************************** */
/* *********************************************************************** */

int Launcher::handleControlMessage(int fd)
{
	struct ctrl_msg m;
	assert (fd >= 0);
	int ret = TCP::read(fd, &m, sizeof(struct ctrl_msg));
	if (ret < (int)sizeof(struct ctrl_msg))
		return -1;

	#ifdef DEBUG
		fprintf(stderr, "Launcher %u: Incoming %d byte %s message from %u for %u, with datalen=%d\n",
				_myproc, ret, CTRL_MSG_TYPE_STRINGS[m.type], m.from, m.to, m.datalen);
	#endif

	char* data = NULL;
	if (m.datalen > 0)
	{
		data = (char*)alloca(m.datalen);
		if (data == NULL)
			DIE("Launcher %u: cannot allocate %d bytes for a control message", _myproc, m.datalen);
	}

	if (TCP::read(fd, data, m.datalen) < 0)
		DIE("Launcher %u: cannot read %d bytes of control message data", _myproc, m.datalen);

	if (m.to == _myproc)
	{
		switch(m.type)
		{
			case PORT_REQUEST:
			{
				m.to = m.from;
				m.from = _myproc;
				m.type = PORT_RESPONSE;
				m.datalen = strlen(_runtimePort);
				#ifdef DEBUG
					fprintf(stderr, "Launcher %u preparing %s message for %u.\n", _myproc, CTRL_MSG_TYPE_STRINGS[PORT_RESPONSE], m.to);
				#endif
				TCP::write(fd, &m, sizeof(struct ctrl_msg));
				TCP::write(fd, _runtimePort, m.datalen);
			}
			break;
			case PORT_RESPONSE:
			{
				// forward to connected runtime
				#ifdef DEBUG
					fprintf(stderr, "Launcher %u forwarding %s message to local runtime.\n", _myproc, CTRL_MSG_TYPE_STRINGS[PORT_RESPONSE]);
				#endif
				TCP::write(_childControlLinks[_numchildren], &m, sizeof(struct ctrl_msg));
				TCP::write(_childControlLinks[_numchildren], data, m.datalen);
			}
			break;
			case HELLO:
				DIE("Unexpected HELLO message");
			break;
			case GOODBYE:
				DIE("Unexpected GOODBYE message");
			break;
		}
	}
	else
		ret = forwardMessage(&m, data);
	return ret;
}


bool Launcher::handleChildCout(int childNo)
{
	#ifdef DEBUG
		//fprintf(stderr, "Launcher %u: cout from %s detected\n", _myproc, childNo==_numchildren?"runtime":"child launcher");
	#endif
	char buf[1024];
	int n = read(_childCoutLinks[childNo], buf, sizeof(buf));
	if (n <= 0)
		return handleDeadChild(childNo);
	else
	{
		write(fileno(stdout), buf, n);
		fflush(stdout);
	}
	return true;
}

bool Launcher::handleChildCerror(int childNo)
{
	#ifdef DEBUG
		//fprintf(stderr, "Launcher %u: cerror from %s detected\n", _myproc, childNo==_numchildren?"runtime":"child launcher");
	#endif
	char buf[1024];
	int n = read(_childCerrorLinks[childNo], buf, sizeof(buf));
	if (n <= 0)
		return handleDeadChild(childNo);
	else
	{
		write(fileno(stderr), buf, n);
		fflush(stderr);
	}
	return true;
}

int Launcher::forwardMessage(struct ctrl_msg* message, char* data)
{
	// this request needs to be forwarded either to the parent launcher, or a child launcher
	// figure out where to send it, by determining if we are on the chain between place 0 and the dest
	uint32_t child=message->to, parent;

	int destFD = -1;
	if (child > _singleton->_myproc)
	{
		do
		{
			parent = (child-1)/2;
			if (parent == _singleton->_myproc)
			{
				if (child == _singleton->_firstchildproc)
					destFD = _childControlLinks[0];
				else
					destFD = _childControlLinks[1];
				#ifdef DEBUG
					fprintf(stderr, "Launcher %u: forwarding %s message to child launcher %u.\n", _myproc, CTRL_MSG_TYPE_STRINGS[message->type], child);
				#endif
				break;
			}
			else
				child = parent;
		}
		while (parent > _singleton->_myproc);
	}

	if (destFD == -1)
	{
		destFD = _parentLauncherControlLink;
		#ifdef DEBUG
			fprintf(stderr, "Launcher %u: forwarding %s message to parent launcher.\n", _myproc, CTRL_MSG_TYPE_STRINGS[message->type]);
		#endif
	}

	int ret = TCP::write(destFD, message, sizeof(struct ctrl_msg));
	if (ret < (int)sizeof(struct ctrl_msg))
		DIE("Failed to forward message");
	if (message->datalen > 0)
		ret = TCP::write(destFD, data, message->datalen);

	return ret;
}

/* *********************************************************************** */
/*            signal handler for PM to handle dying processes              */
/* *********************************************************************** */

void Launcher::cb_sighandler_cld(int signo)
{
	int status;
	wait(&status);

/*
	int status, pid=wait(&status);

	for (int i=0; i<_singleton->_numchildren+1; i++)
	if (_singleton->_pidlst[i] == pid)
	{
		_singleton->_actlst[i] = false;
		#ifdef DEBUG
			fprintf(stderr, "Launcher %d: SIGCHLD, killing proc=%d\n", _singleton->_myproc, _singleton->_childranks[i]));
		#endif

		if (i == _singleton->_numchildren)
			for (int j=0; j<_singleton->_numchildren+1; j++)
				_singleton->_actlst[j] = false;
	}

	for (int j=0; j<_singleton->_numchildren+1; j++)
		_singleton->_actlst[j] = false;
*/
}


/* *********************************************************************** */
/*          start a new child                                              */
/* *********************************************************************** */

void Launcher::startSSHclient(uint32_t id, char* masterPort, char* remotehost)
{
	char * cmd = (char *) _realpath;
	char ** argv = (char **) alloca (sizeof(char *) * (_argc+8));
	int z = 0;
	argv[z] = _ssh_command;
	argv[++z] = remotehost;
//	argv[++z] = (char *) "/usr/bin/env";
	argv[++z] = (char*) alloca(256);
	sprintf(argv[z], X10LAUNCHER_HOSTFILE"=%s", _hostfname);
	argv[++z] = (char*) alloca(256);
	sprintf(argv[z], X10LAUNCHER_SSH"=%s", _ssh_command);
	argv[++z] = (char*) alloca(1024);
	sprintf(argv[z], X10LAUNCHER_PARENT"=%s", masterPort);
	argv[++z] = (char*) alloca(100);
	sprintf(argv[z], X10LAUNCHER_MYID"=%d", id);
	argv[++z] = (char*) alloca(100);
	sprintf(argv[z], X10LAUNCHER_NPROCS"=%d", _nplaces);
	argv[++z] = cmd;
	for (int i = 1; i < _argc; i++)
		argv[z + i] = _argv[i];
	argv[z + _argc] = NULL;

	#ifdef DEBUG
		fprintf(stderr, "Launcher %u exec-ing SSH process to start up launcher %u on %s.\n", _myproc, id, remotehost);
		//for (int i=0; i<z+_argc; i++)
		//	fprintf (stderr, " %s ", argv[i]);
		//fprintf (stderr, "\n");
	#endif

	z = execvp(argv[0], argv);

	if (z)
		DIE("Launcher %u: ssh exec failed", _myproc);
	abort();
}
