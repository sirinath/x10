#!/usr/bin/ksh

#
# (c) Copyright IBM Corporation 2007
#
# $Id: autorun.sh,v 1.8 2007-06-20 14:14:26 ganeshvb Exp $
# Script for automating the build and testing process.
#

SRC_TOP=`pwd`

# log files
LOGFILE=${SRC_TOP}/autorun.log

# script files containing build and testing functionality
BUILD=${SRC_TOP}/build.sh
MKDIST=${SRC_TOP}/mkdist.sh
REGRESS=${SRC_TOP}/regress.sh

# mailing list file and related vars
MAILING_LIST=${SRC_TOP}/misc/mailing.list
ADMIN_ADDR=""
CC_LIST=""

# get mailing address of the administrator
getAdminAddr() {
	ADMIN_ADDR=`sed -n '1,1p' ${MAILING_LIST}`
	return 0
}

# get mailing copy list
getCcList() {
	CC_LIST=""
	sed -n '2,$p' ${MAILING_LIST} | \
	while read entry
	do
		CC_LIST="$CC_LIST $entry"
	done
	CC_LIST="${CC_LIST#" "}"
	return 0
}

# mail results
mailResults() {
	RC=1
	getAdminAddr
	getCcList

	if [ $# -eq 1 ]
	then
		subject="!!! X10Lib AutoRun Results On `date` : $1 !!!"
	else
		subject="!!! X10Lib AutoRun Results On `date` !!!"
	fi
	if [ -n ${ADMIN_ADDR} ]
	then
		echo "\n> Mailing Results...\n" >> ${LOGFILE}
		if [ -n "${CC_LIST}" ]
		then
			echo "cat ${LOGFILE} | \\" >> ${LOGFILE}
			echo "mail -s \"${subject}\" \\" >> ${LOGFILE}
			echo "-c \"${CC_LIST}\" ${ADMIN_ADDR}" >> ${LOGFILE}
			echo "\n...done.\n" >> ${LOGFILE}
			cat ${LOGFILE} | \
			mail -s "${subject}" \
				-c "${CC_LIST}" ${ADMIN_ADDR}
		else
			echo "cat ${LOGFILE} | \\" >> ${LOGFILE}
			echo "mail -s \"${subject}\" \\"
			echo "${ADMIN_ADDR}" >> ${LOGFILE}
			echo "\n...done.\n" >> ${LOGFILE}
			cat ${LOGFILE} | \
			mail -s "${subject}" \
				${ADMIN_ADDR}
		fi
		RC=0
	fi
	return ${RC}
}

# start the autorun process by prepending header
echo "\n############################################################" > ${LOGFILE}
echo "#  X10Lib - The X10 Runtime System" >> ${LOGFILE}
echo "#  (c) Copyright IBM Corporation 2007" >> ${LOGFILE}
echo "#  Results Of Automatic Library Building & Testing Process" >> ${LOGFILE}
echo "#  Host: `hostname`" >> ${LOGFILE}
echo "#  Start Time: `date`" >> ${LOGFILE}
echo "############################################################\n" >> ${LOGFILE}

# append tail end message and exit
outResults() {
	if [ $# -ne 2 ]
	then
		echo "outResults exit_code mesg"
		return 1
	fi
	exit_code=$1
	mesg="$2"
	echo "\n############################################################" >> ${LOGFILE}
	echo "#  End Time: `date`" >> ${LOGFILE}
	if [ $exit_code -eq 0 ]
	then
		echo "#  Conclusion: SUCCESSFULL" >> ${LOGFILE}
	else
		echo "#  Conclusion: FAILED" >> ${LOGFILE}
	fi
	echo "############################################################\n" >> ${LOGFILE}
	mailResults "${mesg}"
	exit $exit_code
}

# do configure and build
echo "\n> Configuring and Building Library...\n" >> ${LOGFILE}
. ${BUILD}
buildLib 2>&1 >> ${LOGFILE}
status=$?
if [ $status -ne 0 ]
then
	echo "\n############################################################" >> ${LOGFILE}
	echo "#  Changes to Source Code since Last Build (24 Hours Ago)" >> ${LOGFILE}
	echo "############################################################\n" >> ${LOGFILE}
	cvs diff -D "24 hours ago" >> ${LOGFILE}
	MESG="Failed Configure & Build"
	outResults 1 "${MESG}"
else
	echo "\n...done.\n" >> ${LOGFILE}
fi

# run test harness
echo "\n> Starting Test Harness...\n" >> ${LOGFILE}
TOTAL_TESTS=0
FAILED_TESTS=0
OK_TESTS=0
. ${REGRESS}
regress 2>&1 >> ${LOGFILE}
#status=$?
exit_status=$?
echo "\n\n##### Total Tests : ${TOTAL_TESTS}" >> ${LOGFILE}
echo "##### Failed Tests : ${FAILED_TESTS}" >> ${LOGFILE}
echo "##### Passed Tests : ${OK_TESTS}\n\n" >> ${LOGFILE}
#if [ $status -ne 0 ]
#then
#	MESG="${TOTAL_TESTS} Total Tests With ${FAILED_TESTS} Failures"
#	outResults 1 "${MESG}"
#else
#	echo "\n...done.\n" >> ${LOGFILE}
#fi
echo "\n...done.\n" >> ${LOGFILE}

# make distribution
echo "\n> Making Library Distribution...\n" >> ${LOGFILE}
. ${MKDIST}
mkDist 2>&1 >> ${LOGFILE}
status=$?
if [ $status -ne 0 ]
then
	MESG="Failed Making Distribution"
	outResults 1 "${MESG}"
else
	echo "\n...done.\n" >> ${LOGFILE}
fi

# exit successfully
MESG="${TOTAL_TESTS} Total Tests With ${FAILED_TESTS} Failures"
outResults $exit_status "${MESG}"
