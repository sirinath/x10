/*
 * (c) Copyright IBM Corporation 2007
 *
 * This file is part of X10 Runtime System.
 * Author : Ganesh Bikshandi
 */

/* $Id: Test_async.cc,v 1.2 2007-05-17 09:49:52 ganeshvb Exp $ */

#include <iostream>

#include <x10/x10lib.h>
#include <x10/array.h>

func_t handlers[128];

using namespace std;
using namespace x10lib;


struct helloWorld
{
void operator() (async_arg_t* arg0, int n)
  {
    assert (*arg0 = 333);
  }
};

int 
main (int argc, char* argv[])
{

  x10lib::Init(handlers, 0);

  if (here() == 0)
    for (place_t target = 0; target < numPlaces(); target++)
       asyncSpawnInline <1, helloWorld> (target, 333);

  x10lib::Gfence (); 

  cout << "Test_async PASSED" << endl;

  x10lib::Finalize();

  return 0;
}
