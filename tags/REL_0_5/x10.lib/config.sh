#! /usr/bin/ksh

#
# (c) Copyright IBM Corporation 2007
#
# $Id: config.sh,v 1.3 2007-06-27 01:43:49 ipeshansky Exp $
# This file is part of X10 Runtime System.
#

## Configure script for building X10Lib.

# create target include and lib dirs.
MKDIR=mkdir
INCDIR=include
if [ ! -d ${INCDIR} ]
then
	echo "${MKDIR} ${INCDIR}"
	${MKDIR} ${INCDIR}
fi

X10IDIR=${INCDIR}/x10
if [ ! -d ${X10IDIR} ]
then
	echo "${MKDIR} ${X10IDIR}"
	${MKDIR} ${X10IDIR}
fi

LIBDIR=lib
if [ ! -d ${LIBDIR} ]
then
	echo "${MKDIR} ${LIBDIR}"
	${MKDIR} ${LIBDIR}
fi

# create header file links.
TOPDIR=`pwd`
SRCDIR=../../src
ARRAYDIR=${SRCDIR}/array
SCHEDDIR=${SRCDIR}/sched
LN=ln
FIND=find
echo "cd ${X10IDIR}"
cd ${X10IDIR}
for i in ${SRCDIR} ${ARRAYDIR} ${SCHEDDIR}
do
	`${FIND} ${i} -name CVS -prune -o -name '*.h' -type f -exec ${LN} -s -f {} . ';'`
	`${FIND} ${i} -name CVS -prune -o -name '*.tcc' -type f -exec ${LN} -s -f {} . ';'`
done
echo "cd ${TOPDIR}"
cd ${TOPDIR}
