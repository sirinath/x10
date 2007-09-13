#include <x10/misc.h>
#include <x10/xmacros.h>

using namespace x10lib;

extern "C" void* arrayCopySwitch (x10_async_handler_t, void * args);

/* extern "C"
void*
arrayCopySwitch (x10_async_handler_t h, void* args)
{
  cout << "arrayCopySwitch should be overriddern \n";
  exit(-1);
}*/

struct asyncArrayCopyHeader
{
  x10_async_handler_t handler;
  char args[MAX_UHDR_SZ];
};

static void* asyncArrayCopyHandler (lapi_handle_t hndl, void* uhdr, uint* uhdr_len, 
			     ulong* msg_len,  compl_hndlr_t **comp_h, void **user_info)
{
  asyncArrayCopyHeader header = *((asyncArrayCopyHeader*) uhdr);	
  lapi_return_info_t *ret_info =
    (lapi_return_info_t *)msg_len;
  if (ret_info->udata_one_pkt_ptr) {
    memcpy (arrayCopySwitch (header.handler, (void*) header.args), ret_info->udata_one_pkt_ptr, *msg_len);
    ret_info->ctl_flags = LAPI_BURY_MSG;
    *comp_h = NULL;
    return NULL;
  } else {	  
    *comp_h = NULL;
    return arrayCopySwitch (header.handler, (void*) header.args);
  }
  
  return NULL; 
}

namespace x10lib {

  //TODO: take care of clock operations
  x10_err_t
  asyncArrayCopy (void* src, size_t srcOffset,
		  x10_async_handler_t handler,
		  void* args, size_t arg_size, 
		  int target, size_t len, Clock* c)
  {
    int tmp;
    lapi_cntr_t origin_cntr;
    
    LRC(LAPI_Setcntr(__x10_hndl, &origin_cntr, 0));
    
    LAPI_Addr_set (__x10_hndl, (void*) asyncArrayCopyHandler, ASYNC_ARRAY_COPY_HANDLER);
    
    int max_uhdr_sz;
    (void) LAPI_Qenv(__x10_hndl, MAX_UHDR_SZ, &max_uhdr_sz);
    
    assert (arg_size >= 0 && arg_size < max_uhdr_sz);
    
    asyncArrayCopyHeader header;
    header.handler = handler;
    memcpy (header.args, args,arg_size);
    
    LRC (LAPI_Amsend (__x10_hndl, 
		      target,
		      (void*) ASYNC_ARRAY_COPY_HANDLER, 
		      (void*) &header,
		      sizeof (header),
		      (void*) ((char*) src + srcOffset),
		      len,
		      NULL,
		      &origin_cntr,
		      NULL));
    
    LRC (LAPI_Waitcntr (__x10_hndl, &origin_cntr, 1, &tmp));
    
    return X10_OK;
  }  
}

x10_err_t 
x10lib::Broadcast (void* buffer, size_t nbytes, x10_place_t root)
{  
  lapi_long_t remoteAddresses[__x10_num_places];
  
  LRC (LAPI_Address_init64 (__x10_hndl, (lapi_long_t) buffer, remoteAddresses));
  
  if (root == __x10_my_place)
    {
      for (x10_place_t p = 0; p < __x10_num_places; p++) 
	{
	  if (p == __x10_my_place) continue;
	  LAPI_Put (__x10_hndl,
		    p,
		    nbytes,
		    (void*) remoteAddresses[p],
		    buffer,
		    NULL,
		    NULL,
		    NULL);
	  
	}
    }

  //replace this with a counter
  LAPI_Gfence (__x10_hndl);

  return X10_OK;
}
