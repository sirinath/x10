#include "assert.h"

#include "x10.h"

typedef struct 
{
  x10_async_closure_t base;
  int magic_number;
} my_closure_t;

int done = 0;

int prev = -1;

void __x10_callback_asyncswitch (x10_async_closure_t* closure, 
				 x10_finish_record_t* frecord, 
				 x10_clock_t* clocks, 
				 int num_clocks)
{
  my_closure_t* my_closure = (my_closure_t*) closure;
  switch (my_closure->base.handler) {
  case 1:
    printf ("hello world (%d)\n", my_closure->magic_number);    
    assert (prev < my_closure->magic_number);
    prev = my_closure->magic_number;
    x10_finish_child(frecord, NULL, 0);
    break;
  }
}

void spawn_async (my_closure_t closure)
{
  x10_finish_record_t frecord;
  int tmp;
  
  x10_finish_begin_global(&frecord, NULL, NULL, 0, 0);
  
  x10_comm_handle_t req = x10_async_spawn(1, (x10_async_closure_t*) &closure, sizeof(closure), &frecord, NULL, 0);
  
  x10_async_spawn_wait(req);
  
  x10_finish_end(&frecord, &tmp);
  
}

int __xlc_upc_main(){}

int main()
{
  x10_init();

  printf ("hello world: %d %d \n", x10_here(), x10_nplaces());

  assert(x10_nplaces() == 2);

  if (x10_here() == 0) {
    
    my_closure_t closure;
    closure.base.handler = 1;
    int i;
    for (i = 0; i < 100; ++i) {
      closure.magic_number = i;      
      spawn_async(closure);
    }

  } else {
    
    x10_wait();
  }

  x10_finalize();

  return 0;
}
