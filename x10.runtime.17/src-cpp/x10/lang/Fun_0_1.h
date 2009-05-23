#ifndef X10_LANG_FUN_0_1_H
#define X10_LANG_FUN_0_1_H

#include <x10aux/config.h>
#include <x10aux/RTT.h>
#include <x10/lang/Object.h>

namespace x10 {
    namespace lang {
        template<class P1, class R> class Fun_0_1 : public virtual Object {
            public:
            class RTT : public x10aux::RuntimeType {
                public:
                virtual void init() { initParents(1,x10aux::getRTT<Object>()); }
                virtual const char *name() const {
                    static const char *name =
                        x10aux::alloc_printf("x10.lang.Fun_0_1[%s,%s]",
                                             x10aux::getRTT<P1>()->name(),
                                             x10aux::getRTT<R>()->name());
                    return name;
                }
            };
            static RTT * const rtt;
            virtual const x10aux::RuntimeType *_type() const {
                return x10aux::getRTT<Fun_0_1<P1,R> >();
            }

            virtual ~Fun_0_1() { }
            virtual R apply(P1 p1) = 0;
        };
        template<class P1, class R> typename Fun_0_1<P1,R>::RTT * const Fun_0_1<P1,R>::rtt =
            new (x10aux::alloc<typename Fun_0_1<P1,R>::RTT>())
                typename Fun_0_1<P1,R>::RTT();
    }
}
#endif
// vim:tabstop=4:shiftwidth=4:expandtab
