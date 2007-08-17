/*
 * (c) Copyright IBM Corporation 2007
 *
 * $Id: async.cc,v 1.12 2007-08-17 13:18:38 ganeshvb Exp $
 * This file is part of X10 Runtime System.
 */

#include <x10/types.h>
#include <x10/async.h>
#include <x10/xmacros.h>
#include <x10/xassert.h>
#include <stdarg.h>

using namespace x10lib;

static x10_async_arg_t __x10_async_args[X10_MAX_ASYNC_ARGS];

static void *
asyncSpawnHandler(lapi_handle_t hndl, void *uhdr,
		uint *uhdr_len, ulong* msg_len,
		compl_hndlr_t **comp_h, void **user_info)
{
        X10_DEBUG (1, "Entry");
	lapi_return_info_t *tmp = (lapi_return_info_t *)msg_len;
        assert (tmp->udata_one_pkt_ptr);
	asyncSwitch(*((x10_async_handler_t *)uhdr),
			(x10_async_arg_t *)tmp->udata_one_pkt_ptr,
			1);
	*comp_h = NULL;
        X10_DEBUG (1, "Exit");
	return NULL;
}

x10_err_t
asyncRegister()
{
  X10_DEBUG (1, "Entry");
  LRC(LAPI_Addr_set(__x10_hndl, (void *)asyncSpawnHandler, 2));
  X10_DEBUG (1, "Exit");
  return X10_OK;
}

namespace x10lib {
x10_err_t
asyncSpawnInline(x10_place_t tgt, x10_async_handler_t hndlr,
			int n, ...)
{
        X10_DEBUG (1, "Entry");
	va_list list;
	extern lapi_handle_t __x10_hndl;

	va_start(list, n);
	lapi_cntr_t origin_cntr;

	for (int i = 0; i < n; i++)
		__x10_async_args[i] = va_arg(list, x10_async_arg_t);
	va_end(list);

	int tmp;
	x10_async_handler_t buf = hndlr;
	LRC(LAPI_Setcntr(__x10_hndl, &origin_cntr, 0));
	LRC(LAPI_Amsend(__x10_hndl, tgt, (void *)2, &buf,
			sizeof(buf), __x10_async_args, n * sizeof(x10_async_arg_t),
			NULL, &origin_cntr, NULL));
	LRC(LAPI_Waitcntr(__x10_hndl, &origin_cntr, 1, &tmp));
        X10_DEBUG (1, "Exit");
	return X10_OK;
}

} /* closing brace for namespace x10lib */

extern "C"
x10_err_t
x10_async_spawn_inline (x10_place_t tgt, x10_async_handler_t hndlr,
					int n, ...)
{
        X10_DEBUG (1, "Entry");
	x10_err_t err = x10lib::asyncSpawnInline(tgt, hndlr, n);
        X10_DEBUG (1, "Exit");
        return err;
}
