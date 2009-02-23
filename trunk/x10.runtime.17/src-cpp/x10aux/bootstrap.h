#ifndef X10AUX_BOOTSTRAP_H
#define X10AUX_BOOTSTRAP_H

#include <x10aux/config.h>
#include <x10aux/pgas.h>
#include <x10aux/alloc.h>
#include <x10aux/string_utils.h>
#include <x10aux/init_dispatcher.h>

#include <x10/lang/VoidFun_0_0.h>
#include <x10/lang/String.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Iterator.h>

#include <x10/runtime/Thread.h>

namespace x10 { namespace lang { template<class T> class Rail; } }

namespace x10aux {

    extern x10_int exitCode;

    typedef void (*ApplicationMainFunction)(ref<x10::lang::Rail<ref<x10::lang::String> > >);

    class BootStrapClosure : public x10::lang::Value,
                             public virtual x10::lang::VoidFun_0_0
    {
        protected:

        ApplicationMainFunction main;
        ref<x10::lang::Rail<ref<x10::lang::String> > > args;
        public:

        // closure body
        void apply () {
            main(args);
        }

        BootStrapClosure(ApplicationMainFunction main_,
                         ref<x10::lang::Rail<ref<x10::lang::String> > > args_)
          : main(main_), args(args_)
        { }


        const x10aux::RuntimeType *_type() const {return x10aux::getRTT<x10::lang::VoidFun_0_0>();}

        ref<x10::lang::String> toString() {
            return x10::lang::String::Lit("x10aux::BootStrapClosure ("__FILELINE__")");
        }

    };

    template<class Runtime, class T> int template_main(int ac, char **av) {
    
        x10aux::ref<x10::lang::Rail<x10aux::ref<x10::lang::String> > > args =
            x10aux::convert_args(ac, av);

#ifndef NO_EXCEPTIONS
        try {
#endif
            setlinebuf(stdout);
			
            x10aux::barrier();

            // Initialise enough state to make this 'main' thread look like a normal x10 thread
            // (e.g. make Thread::CurrentThread work properly).
            x10::runtime::Thread::_make(x10aux::null, x10::lang::String::Lit("thread-main"));

            // Initialise the static fields of x10 classes.
            x10aux::InitDispatcher::runInitializers();

            // Construct closure to invoke the user's "public static def main(Rail[String]) : Void"
            // if at place 0 otherwise wait for asyncs.
            x10aux::ref<x10::lang::VoidFun_0_0> main_closure =
                new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10aux::BootStrapClosure)))
                    x10aux::BootStrapClosure(T::main,args);

            Runtime::start(main_closure); // use XRX
            //main_closure->apply(); // bypass XRX
            //sleep(3);

#ifndef NO_EXCEPTIONS
        } catch(int exitCode) {

            x10aux::exitCode = exitCode;

        } catch(x10aux::__ref& e) {

            // Assume that only throwables can be thrown
            // and things are never thrown by interface (always cast to a value/object class)
            x10aux::ref<x10::lang::Throwable> &e_ =
                static_cast<x10aux::ref<x10::lang::Throwable>&>(e);

            fprintf(stderr, "Uncaught exception at place %d of type: %s\n",
                                (int)x10_here(), e_->_type()->name());
            fprintf(stderr, "%s\n", e_->toString()->c_str());

            x10aux::ref<x10::lang::ValRail<x10aux::ref<x10::lang::String> > > trace =
                e_->getStackTrace();

            x10aux::ref<x10::lang::Iterator<x10aux::ref<x10::lang::String> > > it =
                trace->iterator();

            while (it->hasNext()) {
                fprintf(stderr, "        at %s\n", it->next()->c_str());
            }

            x10aux::exitCode = 1;

        } catch(...) {

            fprintf(stderr, "Caught unrecognised exception at place %d\n", (int)x10_here());
            x10aux::exitCode = 1;

        }
#endif

        //fprintf(stderr, "Done with main in place %d", (int)x10_here());

        x10aux::free_args(args);

        x10aux::shutdown();

        if (getenv("X10_RXTX")!=NULL)
            fprintf(stderr, "Place: %d   rx: %lld   tx: %lld\n",
                (int)x10_here(),
                (long long)x10aux::deserialized_bytes,
                (long long)x10aux::serialized_bytes);

        return x10aux::exitCode;
    }


}

#endif

// vim:tabstop=4:shiftwidth=4:expandtab
