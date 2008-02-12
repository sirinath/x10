#!/usr/bin/ksh

#
# (c) IBM Corporation 2008
#
# $Id: run.sh,v 1.1 2008-02-12 11:17:54 srkodali Exp $
#
# Interactive script for benchmarking bfs.java.random programs.
#

TOP=../../../..
prog_name=bfs.java.random
. ${TOP}/config/run.header

_CMD_="/home/dl/1.7.0/j2se/martin/promoted/solaris-sparcv9/bin/java"
_CMD_="${_CMD_} -server -Xbootclasspath/p:/home/dl/jsr166/build/lib/jsr166.jar"
_CMD_="${_CMD_} -cp ${TOP}/../xwsn.jar"
_CMD_="${_CMD_} -Xms2G -Xmx3G"
_CMD_="${_CMD_} graph.BFS"

seq=1
while [[ $seq -le $MAX_RUNS ]]
do
	printf "#\n# Run: %d\n#\n" $seq 2>&1| tee -a $OUT_FILE
	for size in 100000 1000000 5000000 10000000
	do
		printf "\n## Size: %d\n" $size 2>&1| tee -a $OUT_FILE
		if [ $num_proc -eq 32 ]
		then
			for nproc in 1 2 4 8 16 20 24 30 32
			do
				printf "\n### nproc: %d\n" $nproc 2>&1| tee -a $OUT_FILE
				CMD="${_CMD_} $nproc E $size 4 false false true"
				printf "${CMD}\n" 2>&1| tee -a $OUT_FILE
				${CMD} 2>&1| tee -a $OUT_FILE
				if [ $nproc -eq 30 ]
				then
					printf "\n#<<<< BEGIN BATCHING >>>>\n" 2>&1| tee -a $OUT_FILE
					for bsize in 1 10 20 30 40 50 60 70 80 90 100
					do
						printf "\n#### bsize: %d\n" $bsize 2>&1| tee -a $OUT_FILE
						CMD="${_CMD_} $nproc E $size 4 false false true $bsize"
						printf "${CMD}\n" 2>&1| tee -a $OUT_FILE
						${CMD} 2>&1| tee -a $OUT_FILE
					done
					printf "\n#<<<< END BATCHING >>>>\n" 2>&1| tee -a $OUT_FILE
				fi
			done
		else
			for nproc in 1 2 4 6 8
			do
				printf "\n### nproc: %d\n" $nproc 2>&1| tee -a $OUT_FILE
				CMD="${_CMD_} $nproc E $size 4 false false true"
				printf "${CMD}\n" 2>&1| tee -a $OUT_FILE
				${CMD} 2>&1| tee -a $OUT_FILE
				if [ $nproc -eq 8 ]
				then
					printf "\n#<<<< BEGIN BATCHING >>>>\n" 2>&1| tee -a $OUT_FILE
					for bsize in 1 10 20 30 40 50 60 70 80 90 100
					do
						printf "\n#### bsize: %d\n" $bsize 2>&1| tee -a $OUT_FILE
						CMD="${_CMD_} $nproc E $size 4 false false true $bsize"
						printf "${CMD}\n" 2>&1| tee -a $OUT_FILE
						${CMD} 2>&1| tee -a $OUT_FILE
					done
					printf "\n#<<<< END BATCHING >>>>\n" 2>&1| tee -a $OUT_FILE
				fi
			done
		fi
	done
	let 'seq = seq + 1'
done
. ${TOP}/config/run.footer
