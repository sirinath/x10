#ifndef X10_LANG_VOIDFUN_0_1_H
#define X10_LANG_VOIDFUN_0_1_H

#include <x10aux/config.h>
#include <x10aux/RTT.h>
#include <x10/lang/Object.h>

namespace x10 {
    namespace lang {
        template<class P1> class VoidFun_0_1 : public virtual Object {
            public:
            class RTT : public x10aux::RuntimeType {
                public:
                virtual void init() { initParents(1,x10aux::getRTT<Object>()); }
                virtual const char *name() const {
                    static const char *name =
                        x10aux::alloc_printf("x10.lang.VoidFun_0_1[%s]",
                                             x10aux::getRTT<P1>()->name());
                    return name;
                }
            };
            static RTT * const rtt;
            virtual const x10aux::RuntimeType *_type() const {
                return x10aux::getRTT<VoidFun_0_1<P1> >();
            }

            virtual ~VoidFun_0_1() { }
            virtual void apply(P1 p1) = 0;
        };
        template<class P1> typename VoidFun_0_1<P1>::RTT * const VoidFun_0_1<P1>::rtt =
            new (x10aux::alloc<typename VoidFun_0_1<P1>::RTT>()) typename VoidFun_0_1<P1>::RTT();
    }
}
#endif
// vim:tabstop=4:shiftwidth=4:expandtab
