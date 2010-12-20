/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

#ifndef X10AUX_CONFIG_H
#define X10AUX_CONFIG_H

/*
 * The following performance macros are supported:
 *   NO_EXCEPTIONS     - remove all exception-related code
 *   NO_CHECKS         - same as NO_BOUNDS_CHECKS NO_NULL_CHECKS NO_PLACE_CHECKS NO_ASSERTIONS
 *   NO_BOUNDS_CHECKS  - remove all bounds-checking code
 *   NO_NULL_CHECKS    - remove all null-checking code
 *   NO_PLACE_CHECKS   - remove all place-checking code
 *   NO_ASSERTIONS     - remove all assertion checking code
 *   NO_IOSTREAM       - remove all iostream-related code
 *
 * The following #defines make be specified by the enclosing build
 *   X10_USE_BDWGC     - enable BDW conservative GC
 *
 * The following debugging macros are supported:
 *   TRACE_REF         - trace reference operations
 *   TRACE_CAST        - trace casts
 *   TRACE_ENV_VAR     - turn on support for the tracing variables listed below
 *   REF_STRIP_TYPE    - experimental option: erase the exact content type in references
 *
 * Note, tracing is not actually enabled unless the following environment variables are defined:
 *   X10_TRACE_ALLOC       - trace allocation operations
 *   X10_TRACE_INIT        - trace x10 class initialization
 *   X10_TRACE_X10RT       - trace x10rt invocations
 *   X10_TRACE_SER         - trace serialization operations
 *   X10_TRACE_STATIC_INIT - trace static initialization
 *   X10_TRACE_ALL         - all of the above
 */

#define GPUSAFE

#ifdef NO_CHECKS
#ifndef NO_BOUNDS_CHECKS
#define NO_BOUNDS_CHECKS
#endif//NO_BOUNDS_CHECKS
#ifndef NO_NULL_CHECKS
#define NO_NULL_CHECKS
#endif//NO_NULL_CHECKS
#ifndef NO_PLACE_CHECKS
#define NO_PLACE_CHECKS
#endif//NO_PLACE_CHECKS
#ifndef NO_ASSERTIONS
#define NO_ASSERTIONS
#endif//NO_ASSERTIONS
#ifndef NDEBUG
#define NDEBUG
#endif//NDEBUG
#ifndef NO_TRACING
#define NO_TRACING
#endif//NO_TRACING
#endif//NO_CHECKS

#ifndef NO_TRACING
#ifndef TRACE_ENV_VAR
#define TRACE_ENV_VAR
#endif//TRACE_ENV_VAR
#endif

#ifdef NO_BOUNDS_CHECKS
#define BOUNDS_CHECK_BOOL false
#else
#define BOUNDS_CHECK_BOOL true
#endif

#ifdef NO_PLACE_CHECKS
#define PLACE_CHECK_BOOL false
#else
#define PLACE_CHECK_BOOL true
#endif

#ifndef NO_IOSTREAM
#  include <iostream>
#  include <sstream>
   // workaround for buggy ctype header on some platforms
#  undef _U
#  undef _L
#  undef _N
#  undef _S
#  undef _P
#  undef _C
#  undef _X
#  undef _B
   // end workaround
#endif
#include <stdint.h>
#include <stdio.h>

#include <x10aux/pragmas.h>

struct x10_char {
    unsigned short v;
    x10_char() : v(0) { }
    x10_char(const char x) : v(x) { }
    x10_char(const int x) : v((unsigned short) x) { }
};
#ifndef NO_IOSTREAM
inline std::ostream &operator << (std::ostream &o, const x10_char &c) {
    return o<<c.v;
}
namespace x10aux {
    template<class T> std::string star_rating (void) {
        std::string str = "[";
        for (size_t i=0 ; i<sizeof(T) ; ++i) str += "*";
        return str+"]";
    }
}
#endif
inline bool operator==(const x10_char a, x10_char b) { return a.v == b.v; }
inline bool operator!=(const x10_char a, x10_char b) { return a.v != b.v; }
inline bool operator>(const x10_char a, x10_char b) { return a.v > b.v; }
inline bool operator>=(const x10_char a, x10_char b) { return a.v >= b.v; }
inline bool operator<(const x10_char a, x10_char b) { return a.v < b.v; }
inline bool operator<=(const x10_char a, x10_char b) { return a.v <= b.v; }

typedef bool     x10_boolean;
typedef int8_t   x10_byte;
typedef int16_t  x10_short;
typedef int32_t  x10_int;
typedef int64_t  x10_long;
typedef float    x10_float;
typedef double   x10_double;
typedef uint8_t  x10_ubyte;
typedef uint16_t x10_ushort;
typedef uint32_t x10_uint;
typedef uint64_t x10_ulong;


namespace x10aux {
    typedef x10_ulong x10_addr_t;

    extern bool init_config_bools_done;
    void init_config_bools (void);
    extern bool use_ansi_colors_;
    extern bool disable_dealloc_;
    extern bool trace_alloc_;
    extern bool trace_init_;
    extern bool trace_x10rt_;
    extern bool trace_ser_;
    extern bool trace_static_init_;

    extern inline bool use_ansi_colors()
    { if (!init_config_bools_done) init_config_bools() ; return use_ansi_colors_; }
    extern inline bool trace_alloc()
    { if (!init_config_bools_done) init_config_bools() ; return trace_alloc_; }
    extern inline bool trace_init()
    { if (!init_config_bools_done) init_config_bools() ; return trace_init_; }
    extern inline bool trace_x10rt()
    { if (!init_config_bools_done) init_config_bools() ; return trace_x10rt_; }
    extern inline bool trace_ser()
    { if (!init_config_bools_done) init_config_bools() ; return trace_ser_; }
    extern inline bool trace_static_init()
    { if (!init_config_bools_done) init_config_bools() ; return trace_static_init_; }

    extern x10_int here;
    extern bool x10rt_initialized;
}

#define ANSI_RESET       (::x10aux::use_ansi_colors()?"\x1b[0m" :"")

#define ANSI_BOLD        (::x10aux::use_ansi_colors()?"\x1b[1m" :"")
#define ANSI_NOBOLD      (::x10aux::use_ansi_colors()?"\x1b[22m":"")

#define ANSI_UNDERLINE   (::x10aux::use_ansi_colors()?"\x1b[4m" :"")
#define ANSI_NOUNDERLINE (::x10aux::use_ansi_colors()?"\x1b[24m":"")

#define ANSI_REVERSE     (::x10aux::use_ansi_colors()?"\x1b[6m" :"")
#define ANSI_NOREVERSE   (::x10aux::use_ansi_colors()?"\x1b[27m":"")

#define ANSI_BLACK       (::x10aux::use_ansi_colors()?"\x1b[30m":"")
#define ANSI_RED         (::x10aux::use_ansi_colors()?"\x1b[31m":"")
#define ANSI_GREEN       (::x10aux::use_ansi_colors()?"\x1b[32m":"")
#define ANSI_YELLOW      (::x10aux::use_ansi_colors()?"\x1b[33m":"")
#define ANSI_BLUE        (::x10aux::use_ansi_colors()?"\x1b[34m":"")
#define ANSI_MAGENTA     (::x10aux::use_ansi_colors()?"\x1b[35m":"")
#define ANSI_CYAN        (::x10aux::use_ansi_colors()?"\x1b[36m":"")
#define ANSI_WHITE       (::x10aux::use_ansi_colors()?"\x1b[37m":"")

#define _MAYBE_DEBUG_MSG(col,type,msg,doit) do { \
    if (doit) _DEBUG_MSG(col,type,msg); \
} while (0)

#define _DEBUG_MSG(col,type,msg) do { \
    std::stringstream ss; \
    if (x10aux::x10rt_initialized) \
        ss << ANSI_BOLD << x10aux::here << ": " << col << type << ": " << ANSI_RESET << msg; \
    else \
        ss << ANSI_BOLD << col << type << ": " << ANSI_RESET << msg; \
    fprintf(stderr,"%s\n",ss.str().c_str()); \
} while (0)

#define ANSI_ALLOC ANSI_WHITE
#define ANSI_CAST ANSI_RED
#define ANSI_INIT ANSI_MAGENTA
#define ANSI_REF ANSI_YELLOW
#define ANSI_SER ANSI_CYAN
#define ANSI_X10RT ANSI_BLUE

#if !defined(NO_IOSTREAM) && defined(TRACE_ENV_VAR)
#include <stdio.h>
#define _M_(x) _MAYBE_DEBUG_MSG(ANSI_ALLOC,"MM",x,::x10aux::trace_alloc())
#else
#define _M_(x)
#endif

#if !defined(NO_IOSTREAM) && defined(TRACE_CAST)
#include <stdio.h>
#define _CAST_(x) _DEBUG_MSG(ANSI_CAST,"CAST",x)
#else
#define _CAST_(x)
#endif

#if !defined(NO_IOSTREAM) && defined(TRACE_ENV_VAR)
#include <stdio.h>
#define _I_(x) _MAYBE_DEBUG_MSG(ANSI_INIT,"INIT",x,::x10aux::trace_init())
#else
#define _I_(x)
#endif

#if !defined(NO_IOSTREAM) && defined(TRACE_REF)
#include <stdio.h>
#define _R_(x) _DEBUG_MSG(ANSI_REF,"RR",x)
#else
#define _R_(x)
#endif

#if !defined(NO_IOSTREAM) && defined(TRACE_ENV_VAR)
#include <stdio.h>
#define _S_(x) _MAYBE_DEBUG_MSG(ANSI_SER,"SS",x,::x10aux::trace_ser())
#define _Sd_(x) x
#else
#define _S_(x)
#define _Sd_(x)
#endif

#if !defined(NO_IOSTREAM) && defined(TRACE_ENV_VAR)
#include <stdio.h>
#define _SI_(x) _MAYBE_DEBUG_MSG(ANSI_SER,"SI",x,::x10aux::trace_static_init())
#define _SId_(x) x
#else
#define _SI_(x)
#define _SId_(x)
#endif

#if !defined(NO_IOSTREAM) && defined(TRACE_ENV_VAR)
#include <stdio.h>
#define _X_(x) _MAYBE_DEBUG_MSG(ANSI_X10RT,"XX",x,::x10aux::trace_x10rt())
#else
#define _X_(x)
#endif


// We must use the same mangling rules as the compiler backend uses.
// The c++ target has to mangle fields because c++ does not allow fields
// and methods to have the same name.
#define FMGL(x) x10__##x

//needed if you want to concat from another macro
#ifndef __CONCAT
#define __CONCAT(__x,__y) __x##__y
#endif

//if you want to turn a token into a string
#define __TOKEN_STRING(X) #X

//if you want to do the above but the token is hidden behind a macro
#define __TOKEN_STRING_DEREF(X) __TOKEN_STRING(X)

//combine __FILE__ and __LINE__ without using sprintf or other junk
#define __FILELINE__ __FILE__ ":" __TOKEN_STRING_DEREF(__LINE__) 

#define UNIMPLEMENTED(m) do { \
        fprintf(stderr, "Aborting due to unimplemented function %s at %s\n",m,__FILELINE__); \
        abort();                                                        \
} while (0)


// Debug support
#include <x10aux/debug.h>

#endif
// vim:tabstop=4:shiftwidth=4:expandtab
