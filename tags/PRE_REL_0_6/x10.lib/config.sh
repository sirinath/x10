#! /usr/bin/ksh

#
# (c) Copyright IBM Corporation 2007
#
# $Id: config.sh,v 1.8 2007-10-24 17:24:04 ipeshansky Exp $
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
X10XWSDIR=${INCDIR}/x10/xws
if [ ! -d ${X10XWSDIR} ]
then
	echo "${MKDIR} ${X10XWSDIR}"
	${MKDIR} ${X10XWSDIR}
fi

LIBDIR=lib
if [ ! -d ${LIBDIR} ]
then
	echo "${MKDIR} ${LIBDIR}"
	${MKDIR} ${LIBDIR}
fi

# create header file links.
TOPDIR=`pwd`
SRCDIR=`cd src && pwd`
ARRAYDIR=${SRCDIR}/array
SCHEDDIR=${SRCDIR}/sched
LN=ln
FIND=find
echo "cd ${X10IDIR}"
cd "${X10IDIR}"
for i in "${SRCDIR}" "${ARRAYDIR}"
do
	for f in "$i"/*.h "$i"/*.tcc
	do
		[ -f "$f" ] && ${LN} -s -f "$f" .
	done
done
cd ${TOPDIR}
echo "cd ${X10XWSDIR}"
cd ${X10XWSDIR}
for i in ${SCHEDDIR}
do
	for f in "$i"/*.h "$i"/*.tcc
	do
		[ -f "$f" ] && ${LN} -s -f "$f" .
	done
done
echo "cd ${TOPDIR}"
cd ${TOPDIR}
