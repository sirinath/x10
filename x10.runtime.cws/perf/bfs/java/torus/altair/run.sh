#!/usr/bin/ksh

#
# (c) IBM Corporation 2008
#
# $Id: run.sh,v 1.1 2008-02-24 08:28:57 srkodali Exp $
#
# Interactive script for benchmarking bfs.java.torus programs.
#

TOP=../../../..
prog_name=bfs.java.torus
. ${TOP}/config/run.header

_CMD_="/home/dl/1.7.0/j2se/martin/promoted/solaris-amd64/bin/java"
_CMD_="${_CMD_} -server -Xbootclasspath/p:/home/dl/jsr166/build/lib/jsr166.jar"
_CMD_="${_CMD_} -cp ${TOP}/../xwsn.jar"
_CMD_="${_CMD_} -Xmx3G"
_CMD_="${_CMD_} graph.AdaptiveBFS"

seq=1
while [[ $seq -le $MAX_RUNS ]]
do
	printf "#\n# Run: %d\n#\n" $seq 2>&1| tee -a $OUT_FILE
	for size in 500 1000 2000 3000
	do
		printf "\n## Size: %d\n" $size 2>&1| tee -a $OUT_FILE
		if [ $num_proc -eq 32 ]
		then
			for nproc in 1 2 4 8 16 20 24 30 32
			do
				printf "\n### nproc: %d\n" $nproc 2>&1| tee -a $OUT_FILE
				CMD="${_CMD_} $nproc T $size 4 false false true 1 4"
				printf "${CMD}\n" 2>&1| tee -a $OUT_FILE
				${CMD} 2>&1| tee -a $OUT_FILE
			done
		else
			for nproc in 1 2 4 6 8
			do
				printf "\n### nproc: %d\n" $nproc 2>&1| tee -a $OUT_FILE
				CMD="${_CMD_} $nproc T $size 4 false false true 1 4"
				printf "${CMD}\n" 2>&1| tee -a $OUT_FILE
				${CMD} 2>&1| tee -a $OUT_FILE
			done
		fi
	done
	let 'seq = seq + 1'
done
. ${TOP}/config/run.footer
