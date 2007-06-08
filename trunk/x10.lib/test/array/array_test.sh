#!/usr/bin/ksh

#
# (c) Copyright IBM Corporation 2007
#
# $Id: array_test.sh,v 1.1 2007-06-08 13:51:42 srkodali Exp $
# Script for testing the X10Lib's array functionality.
#

arrayTest() {
	RC=0
	for i in Test_array Test_array_async Test_dist Test_point \
			Test_region Test_tiled_region Test_point2 \
			Test_region2
	do
		let "TOTAL_TESTS = TOTAL_TESTS + 1"
		CMD=${SRC_TEST}/array/${i}
		CMDFILE=${SRC_TEST}/array/data/${i}.cmd
		DATAFILE=${SRC_TEST}/array/data/${i}.dat
		echo "\nTesting ${CMD}...\n"
		echo "${KSH} ${CMDFILE} ${CMD} > ${RESFILE}\n"
		${KSH} ${CMDFILE} ${CMD} > ${RESFILE}
		status=$?
		if [ $status -ne 0 ]
		then
			echo "Error in executing ${CMD}...\n"
			let "FAILED_TESTS = FAILED_TESTS + 1"
			RC=1
			continue
		fi
		echo "${DIFF} ${DATAFILE} ${RESFILE}\n"
		${DIFF} ${DATAFILE} ${RESFILE}
		status=$?
		if [ $status -ne 0 ]
		then
			echo "Failed in test ${CMD}...\n"
			echo "<<Expected Output>>\n"
			cat ${DATAFILE}
			echo "<<Tested Output>>\n"
			cat ${RESFILE}
			let "FAILED_TESTS = FAILED_TESTS + 1"
			RC=1
			continue
		fi
		echo "...OK\n"
		let "OK_TESTS = OK_TESTS + 1"
	done
	return $RC
}
