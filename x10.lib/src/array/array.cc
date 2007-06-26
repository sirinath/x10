/*
 * (c) Copyright IBM Corporation 2007
 *
 * $Id: array.cc,v 1.2 2007-06-26 16:05:57 ganeshvb Exp $
 * This file is part of X10 Runtime System.
 */

/** helper functions (internal) for array classe */

#include <iostream>
#include <x10/alloc.h>
using namespace std;

namespace x10lib{
  extern Allocator* GlobalSMAlloc;
}

void 
arrayInit ()
{
  x10lib::GlobalSMAlloc = new x10lib::Allocator (1UL<<10);
}
