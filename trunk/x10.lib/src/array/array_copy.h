/*
 * (c) Copyright IBM Corporation 2007
 *
 * $Id: array_copy.h,v 1.3 2007-10-19 16:04:29 ganeshvb Exp $
 * This file is part of X10 Runtime System.
 */

#ifndef __X10_ARRAY_COPY_H
#define __X10_ARRAY_COPY_H

#include <iostream>
#include <x10/err.h>
#include <x10/types.h>
#include <x10/switch.h>
#include <x10/closure.h>

/* C++ Lang Interface */
#ifdef __cplusplus
extern "C" void* arrayCopySwitch (int handler, void * args);
namespace x10lib {
  
  extern int __x10_num_places;
  extern int __x10_my_place;

  /**
   * arg0 = src address.
   * args = pointer to closure (a derived class of asyncArrayCOpyClosure).
   * len = number of bytes to be copied.
   * target = destination node where the dest. array is residing.
   * c = clock (optional).
   */
  
  /**
   * DESCRIPTION :  This methods invokes the arrayCopySwitch method in the destinaion
   * The arrayCopySwitch method needs to be overloaded by the programmer. It's signature
   * is void* arrayCopySwitch (void* args). 
   **/
  
  /**
   * The input buffers (src and args) can be re-used
   * or deleted safely upon return from this funcion
   */
  
  x10_err_t
  asyncArrayCopy (void* src, 
		  x10_closure_t args,
		  size_t len, 
		  int target, 
		  x10_switch_t c=NULL);
  
  /**
   * Same as above, but does not guarantee the buffer 
   * re-use or safe deletion after its return. 
   * It is the user's responsibility to ensure safe
   * buffer re-use & de-allocation.
   */
  
  x10_err_t
  asyncArrayCopyRaw (void* src, 
		     x10_closure_t args,
		     size_t len, 
		     int target, 
		     x10_switch_t c=NULL);
  
 } /* closing brace for namespace x10lib */
#endif


#ifdef __cplusplus
extern "C" {
#endif

  /* The C bindings for the above functions */

  x10_err_t x10_async_array_copy (void* src, x10_closure_t args, size_t len, int target, x10_switch_t c);

  x10_err_t x10_async_array_copy_raw (void* src, x10_closure_t args, size_t len, int target,x10_switch_t c);

#ifdef __cplusplus
}
#endif 

#endif
  

// Local Variables:
// mode: C++
// End:
