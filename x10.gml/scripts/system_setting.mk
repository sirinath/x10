# Platform-specific settings for building GML library and application codes

X10CXX ?= x10c++
X10C ?= x10c
CXX ?= g++
JAR ?= jar
MAKE ?= make

# JNI include path, for managed GML
ifdef JAVA_HOME
  jarch=$(shell uname -p)
  ifeq ($(jarch),unknown)
    jarch=$(shell uname -m)
  endif

  ifeq ($(shell uname -s),AIX)
    JNI_INCLUDES = -I"$(JAVA_HOME)"/include -I"$(JAVA_HOME)"/include/aix
    JNI_LIBS = -L"$(JAVA_HOME)"/jre/lib/$(jarch)/j9vm
  else
  ifeq ($(shell uname -s),Linux)
    JNI_INCLUDES = -I"$(JAVA_HOME)"/include -I"$(JAVA_HOME)"/include/linux
    ifeq ($(jarch),x86_64)
      jarch=amd64
    endif
    JNI_LIBS = -L"$(JAVA_HOME)"/jre/lib/$(jarch)/j9vm -L"$(JAVA_HOME)"/jre/lib/$(jarch)/server -L"$(JAVA_HOME)"/jre/lib/$(jarch)/client
  else
  ifeq ($(firstword $(subst _, ,$(shell uname -s))),CYGWIN)
    # Intentionally not setting JNI_INCLUDES and JNI_LIBS
    # We don't want to build the jni-bindings for x10rt on cygwin
  else
  ifeq ($(shell uname -s),Darwin)
    JNI_INCLUDES = -I"$(JAVA_HOME)"/include -I"$(JAVA_HOME)"/include/darwin
    ifeq ($(jarch),x86_64)
      jarch=amd64
    endif
    JNI_LIBS = -L"$(JAVA_HOME)"/jre/lib/$(jarch)/server -L"$(JAVA_HOME)"/jre/lib/$(jarch)/client
  else
  ifeq ($(shell uname -s),SunOS)
    JNI_INCLUDES = -I"$(JAVA_HOME)"/include -I"$(JAVA_HOME)"/include/solaris
    ifeq ($(jarch),x86_64)
      jarch=amd64
    endif
    JNI_LIBS = -L"$(JAVA_HOME)"/jre/lib/$(jarch)/server -L"$(JAVA_HOME)"/jre/lib/$(jarch)/client
  else
  ifeq ($(shell uname -s),FreeBSD)
    JNI_INCLUDES = -I"$(JAVA_HOME)"/include -I"$(JAVA_HOME)"/include/freebsd
    ifeq ($(jarch),x86_64)
      jarch=amd64
    endif
    JNI_LIBS = -L"$(JAVA_HOME)"/jre/lib/$(jarch)/server -L"$(JAVA_HOME)"/jre/lib/$(jarch)/client
  endif
  endif
  endif
  endif
  endif
  endif
endif

# comment the following lines to disable BLAS and LAPACK wrappers
ADD_BLAS	= yes
ADD_LAPACK	= yes

# BLAS and LAPACK compiler options
ifdef ADD_BLAS
    X10CXX_PREARGS += -cxx-prearg -DENABLE_BLAS
	add_jblas	= chk_jblas
    ifdef ADD_LAPACK
        X10CXX_PREARGS += -cxx-prearg -DENABLE_LAPACK
	add_jlapack	= chk_jlapack
    endif
endif

# BLAS and LAPACK linker options
ifdef BGQ
    # Blue Gene/Q compiler settings
    CXX = /bgsys/drivers/ppcfloor/gnu-linux/bin/powerpc64-bgq-linux-g++
    # IBM ESSL on Blue Gene/Q
    BLASLIB = ESSL
    ifdef ADD_BLAS
        IBMCMP_ROOT = /opt/ibmcmp
        XLSMP_LIB_PATH = $(IBMCMP_ROOT)/xlsmp/bg/3.1/bglib64
        XLMASS_LIB_PATH = $(IBMCMP_ROOT)/xlmass/bg/7.3/bglib64
        XLF_LIB_PATH = $(IBMCMP_ROOT)/xlf/bg/14.1/bglib64
        ESSL_LIB_PATH = /opt/ibmmath/essl/5.1/lib64
        ESSL_LIB = esslsmpbg
        # need mass lib on BG/Q
        X10CXX_POSTARGS += -cxx-postarg -Wl,--allow-multiple-definition -cxx-postarg -L$(XLMASS_LIB_PATH) -cxx-postarg -lmassv -cxx-postarg -lmass
        #X10CXXFLAGS += -cxx-postarg -L/opt/ibmcmp/vac/bg/9.0/lib
    endif
endif

# choose BLAS implementation
BLASLIB ?= 

ifeq ($(BLASLIB),ESSL)
    # IBM ESSL
    X10CXX_PREARGS += -cxx-prearg -D__essl__
    ifdef ADD_BLAS
        IBMCMP_ROOT = /opt/ibmcmp
        XLSMP_LIB_PATH ?= $(IBMCMP_ROOT)/xlsmp/3.1/lib64
        XLF_LIB_PATH ?= $(IBMCMP_ROOT)/xlf/14.1/lib64
        ESSL_LIB_PATH ?= /usr/lib64
        ESSL_LIB ?= esslsmp6464
        ifdef ADD_LAPACK
            X10CXX_POSTARGS += -cxx-postarg -llapack
        endif
        X10CXX_POSTARGS += -cxx-postarg -L$(ESSL_LIB_PATH) -cxx-postarg -l$(ESSL_LIB) -cxx-postarg -L$(XLF_LIB_PATH) -cxx-postarg -lxlf90_r -cxx-postarg -L$(XLSMP_LIB_PATH) -cxx-postarg -lxlsmp -cxx-postarg -lxlopt -cxx-postarg -lxlfmath -cxx-postarg -lxl

    endif
else
ifeq ($(BLASLIB),GotoBLAS2)
    # GotoBLAS2
    ifdef ADD_BLAS
        X10CXX_POSTARGS += -cxx-postarg -L$(HOME)/GotoBLAS2 -cxx-postarg -lgoto2
        ifdef ADD_LAPACK
            X10CXX_POSTARGS += -cxx-postarg -llapack
        endif
    endif
else
ifeq ($(BLASLIB),MKL)
    # Intel Math Kernel Library (LP64, 64-bit, libgomp)
    ifdef ADD_BLAS
        X10CXX_POSTARGS += -cxx-postarg -L$(MKLROOT)/lib/intel64 -cxx-postarg -lmkl_intel_lp64 -cxx-postarg -lmkl_core -cxx-postarg -lmkl_gnu_thread -cxx-postarg -ldl
        X10CXX_PREARGS += -cxx-prearg -fopenmp -cxx-prearg -m64 -cxx-prearg -I$(MKLROOT)/include
    endif
else
    # assume NetLib reference BLAS/LAPACK
    ifdef ADD_BLAS
        X10CXX_POSTARGS += -cxx-postarg -L/usr/lib64 -cxx-postarg -lblas
        ifdef ADD_LAPACK
            X10CXX_POSTARGS += -cxx-postarg -llapack
        endif
    endif
endif
endif
endif
