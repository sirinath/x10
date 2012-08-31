/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

#include <x10aux/config.h>
#include <x10aux/bootstrap.h>

#include <pthread.h>

#include <x10/lang/Place.h>
#include <x10/lang/Runtime.h>
#include <x10/io/Console.h>
#include <x10/lang/Thread.h>

using namespace x10aux;

volatile x10_int x10aux::exitCode = 0;

x10::lang::VoidFun_0_0::itable<BootStrapClosure> BootStrapClosure::_itable(&BootStrapClosure::equals, &BootStrapClosure::hashCode,
                                                                           &BootStrapClosure::__apply,
                                                                           &BootStrapClosure::toString, &BootStrapClosure::typeName);

x10aux::itable_entry BootStrapClosure::_itables[2] = {
    x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &_itable),
    x10aux::itable_entry(NULL, NULL)
};

void x10aux::initialize_xrx() {
    x10::lang::Runtime::FMGL(staticMonitor__do_init)();
//    x10::lang::Runtime::FMGL(env__do_init)();
    x10::lang::Runtime::FMGL(STRICT_FINISH__do_init)();
    x10::lang::Runtime::FMGL(NTHREADS__do_init)();
    x10::lang::Runtime::FMGL(MAX_THREADS__do_init)();
    x10::lang::Runtime::FMGL(STATIC_THREADS__do_init)();
    x10::lang::Runtime::FMGL(WARN_ON_THREAD_CREATION__do_init)();
    x10::lang::Runtime::FMGL(BUSY_WAITING__do_init)();
//    x10::lang::Place::FMGL(places__do_init)();
//    x10::lang::Place::FMGL(FIRST_PLACE__do_init)();
}

struct x10_main_args {
    int ac;
    char **av;
    ApplicationMainFunction mainFunc;    
};

static void* real_x10_main_inner(void* args);

int x10aux::real_x10_main(int ac, char **av, ApplicationMainFunction mainFunc) {

#if defined(__bgp__)    
    x10_main_args args;
    args.ac = ac;
    args.av = av;
    args.mainFunc = mainFunc;
    real_x10_main_inner(&args);
#else
    x10_main_args* args = x10aux::system_alloc<x10_main_args>();
    args->ac = ac;
    args->av = av;
    args->mainFunc = mainFunc;

    pthread_t* xthread = x10aux::system_alloc<pthread_t>();
    pthread_attr_t* xthread_attr = x10aux::system_alloc<pthread_attr_t>();

    (void)pthread_attr_init(xthread_attr);
    x10::lang::Thread::initAttributes(xthread_attr);
    
    int err = pthread_create(xthread, xthread_attr,
                             &real_x10_main_inner, (void *)args);
    if (err) {
        ::fprintf(stderr,"Could not create first worker thread: %s\n", ::strerror(err));
        ::abort();
    }

    pthread_join(*xthread, NULL);

#endif
    
    return x10aux::exitCode;
}    

static void* real_x10_main_inner(void* _main_args) {
    x10_main_args* main_args = (x10_main_args*)_main_args;

    setlinebuf(stdout);

    x10aux::num_local_cores = sysconf(_SC_NPROCESSORS_ONLN);

    x10aux::network_init(main_args->ac, main_args->av);

#ifdef X10_USE_BDWGC
    GC_INIT();
#endif

#ifndef NO_EXCEPTIONS
    try {
#endif
        x10aux::place_local::initialize();

        // Initialize a few key fields of XRX that must be set before any X10 code can execute
        x10aux::initialize_xrx();

        // Initialise enough state to make this 'main' thread look like a normal x10 thread
        // (e.g. make Thread::CurrentThread work properly).
        x10::lang::Runtime__Worker::_make((x10_int)0);

        // Get the args into an X10 Array[String]
        x10::array::Array<x10::lang::String*>* args = x10aux::convert_args(main_args->ac, main_args->av);

        // Construct closure to invoke the user's "public static def main(Array[String]) : void"
        // if at place 0 otherwise wait for asyncs.
        x10::lang::VoidFun_0_0* main_closure =
            reinterpret_cast<x10::lang::VoidFun_0_0*>(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10aux::BootStrapClosure))) x10aux::BootStrapClosure(main_args->mainFunc, args));

        // Bootup the serialization/deserialization code
        x10aux::DeserializationDispatcher::registerHandlers();

        // Actually start up the runtime and execute the program.
        // When this function returns, the program will have exited.
        x10::lang::Runtime::start(main_closure);

#ifndef NO_EXCEPTIONS
    } catch(int exitCode) {

        x10aux::exitCode = exitCode;

    } catch(x10::lang::CheckedThrowable* e) {
        fprintf(stderr, "Uncaught exception at place %ld: %s\n", (long)x10aux::here,
                x10aux::string_utils::cstr(e->toString()));

        e->printStackTrace();

        x10aux::exitCode = 1;

    } catch(...) {

        fprintf(stderr, "Caught unrecognised exception at place %ld\n", (long)x10aux::here);
        x10aux::exitCode = 1;

    }
#endif

    // We're done.  Shutdown the place.
    x10aux::shutdown();

    if (x10aux::trace_rxtx)
        fprintf(stderr, "Place: %ld   rx: %lld/%lld   tx: %lld/%lld\n",
                (long)x10aux::here,
                (long long)x10aux::deserialized_bytes, (long long)x10aux::asyncs_received,
                (long long)x10aux::serialized_bytes, (long long)x10aux::asyncs_sent);

    return NULL;
}

// vim:tabstop=4:shiftwidth=4:expandtab
