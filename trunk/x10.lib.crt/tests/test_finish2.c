#include <assert.h>
#include <math.h>

#include "x10.h"

typedef struct 
{
  x10_async_closure_t base;
  int magic_number;
} my_closure_t;

int done = 0;

int val = 0;

void __x10_callback_asyncswitch (x10_async_closure_t* closure, 
				 x10_finish_record_t* frecord, 
				 x10_clock_t* clocks, 
				 int num_clocks)
{
  
  my_closure_t* my_closure = (my_closure_t*) closure;
  
  if (x10_here() == 0) 
    {
      val += my_closure->magic_number;
      x10_finish_child(frecord, NULL, 0);
      return;
    }
  
  switch (my_closure->base.handler) {
    
  case 1: {
    
    int i;
    
    for (i = 0; i < 10; i++) {    
      
      x10_comm_handle_t req = x10_async_spawn((x10_here() + 1) % x10_nplaces(), closure, 
					      sizeof(my_closure_t), x10_get_cur_frecord(), NULL, 0);   
      
      x10_async_spawn_wait(req);    
    }
    
    x10_finish_child(frecord, NULL, 0);
    
    break;
  }
  }
}

int __xlc_upc_main(){}

int main()
{
  x10_init();

  printf ("hello world: %d %d \n", x10_here(), x10_nplaces());

  if (x10_here() == 0) {
    
    my_closure_t closure;
    closure.base.handler = 1;
    closure.magic_number = 1;
    
    x10_finish_record_t frecord;
    
    X10_FINISH_BEGIN_GLOBAL
    
    int i;
    
    for (i = 0; i < 10; i++) 
      {	
	x10_comm_handle_t req = x10_async_spawn(1, (x10_async_closure_t*) &closure, 
					        sizeof(closure), x10_get_cur_frecord(), NULL, 0);  
	x10_async_spawn_wait(req);
      }
    
    X10_FINISH_END


    //    printf ("val : %d\n", val);
    
    assert (val == pow(10, (x10_nplaces())));
    
  } else {
    
    x10_wait();
  }

  x10_finalize();

  return 0;

}
