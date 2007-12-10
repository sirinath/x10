/*
 * (c) Copyright IBM Corporation 2007
 *
 * $Id: types.h,v 1.24 2007-12-10 15:52:11 srkodali Exp $
 * This file is part of X10 Runtime System.
 */

/** X10Lib's Primitive Types. **/

#ifndef __X10_TYPES_H
#define __X10_TYPES_H

#include <sys/types.h>

/* x10lang types
 * previously part of x10lang.h
 */
#ifdef __cplusplus
typedef bool x10_boolean;
#endif
typedef int8_t x10_byte;
typedef uint16_t x10_char;
typedef int16_t x10_short;
typedef int32_t x10_int;
typedef int64_t x10_long;
typedef float x10_float;
typedef double x10_double;

/* x10lang array index */
#ifdef USE_LONG_ARRAYS
typedef x10_long x10_index_t;
#else
typedef x10_int x10_index_t;
#endif

/* async handler */
typedef long x10_async_arg_t;
typedef int x10_async_handler_t;

/* x10 place index */
typedef int x10_place_t;

#endif /* __X10_TYPES_H */
