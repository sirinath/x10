/*
 * (c) Copyright IBM Corporation 2007
 *
 * $Id: clock.cc,v 1.2 2007-12-09 12:20:09 srkodali Exp $
 * This file is part of X10 Runtime System.
 */

/** Implementation file for (DUMMY) Clock interface. **/

#include <x10/clock.h>
#include <x10/xmacros.h>
#include <lapi.h>

namespace x10lib {

/* Perform next operation on the specified clock. */
x10_err_t x10lib::ClockNext(Clock *c, int n)
{
	// refine???
	LRC(LAPI_Gfence(__x10_hndl));
	return X10_OK;
}

} /* closing brace for namespace x10lib */
