#include <x10/x10lib.h>
#include <stdio.h>

int cntr;

void* headerHandler (lapi_handle_t* hndl, void* uhdr, int* ulen,
		     ulong* mlen, compl_hndlr_t** comp, void** uinfo)
{
  cntr++;
  *comp = NULL;
  return NULL;
}

int main (int argc, char** argv)
{
  x10_init(NULL, 0);
  cntr = 0;
  LAPI_Addr_set (x10_get_handle(), (void*) headerHandler, 10);
  if (x10_here() == 0) {
     switch_t s; 
    x10_switch_init (&s, 0);
    for (int i = 0; i < x10_num_places(); i++) {
      if (i == x10_here())
	  {
	    cntr++;
	    continue;
	  }
      x10_switch_add_val (&s, -1);
      LAPI_Amsend (x10_get_handle(),
		   i,
		   (void*) 10,
		   NULL,
		   0,
		   NULL,
		   0,
		   NULL,
		   NULL,
		   &s);
    }

    x10_switch_next (&s);
  }
  
  x10_gfence();
  assert (cntr == 1);    

  printf ("Test_switch_c PASSED\n"); 
  
  x10_finalize();
  return 0;
}
