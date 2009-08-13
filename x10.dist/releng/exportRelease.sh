#!/bin/bash

# Dave Grove

svn_command=export

while [ $# != 0 ]; do

  case $1 in
    -version)
	export X10_VERSION=$2
	shift
    ;;

    -x10-tag)
	export X10_TAG=$2
	shift
    ;;

    -polyglot-tag)
	export POLYGLOT_TAG=$2
	shift
    ;;

    -branch)
        svn_command=co
    ;;

   esac
   shift
done

if [[ -z "$X10_VERSION" ]]; then
    echo "usage: $0 must give X10 version as -version <version>"
    exit 1
fi

if [[ -z "$X10_TAG" ]]; then
    echo "usage: $0 must give X10 tag as -x10-tag <svn tag>"
    exit 1
fi

if [[ -z "$POLYGLOT_TAG" ]]; then
    echo "usage: $0 must give polyglot tag as -polyglot-tag <svn tag>"
    exit 1
fi

date

workdir=$HOME/scratch/distribution
distdir=$workdir/x10-$X10_VERSION

echo
echo cleaning $workdir
rm -rf $workdir
mkdir -p $workdir || exit 1
mkdir -p $workdir/x10-$X10_VERSION



(
echo
echo getting polyglot
cd $workdir/x10-$X10_VERSION
svn $svn_command http://polyglot-compiler.googlecode.com/svn/tags/$POLYGLOT_TAG/polyglot
)

(
cd $workdir/x10-$X10_VERSION

echo
echo getting distrib
for i in \
	x10.common \
	x10.compiler.p3 \
	x10.constraints \
	x10.cppbackend.17 \
	x10.dist \
	x10.runtime \
	x10.tests
do
    svn $svn_command https://x10.svn.sourceforge.net/svnroot/x10/tags/$X10_TAG/$i
done
)

echo "The distribution is now exported to the directory $workdir"
