#ifndef X10_LANG_VOIDFUN_0_7_H
#define X10_LANG_VOIDFUN_0_7_H

#include <x10aux/config.h>
#include <x10aux/RTT.h>
#include <x10/lang/Object.h>

namespace x10 {
    namespace lang {
        template<class P1, class P2, class P3, class P4, class P5, class P6, class P7> class VoidFun_0_7 : public virtual Object {
            public:
            class RTT : public x10aux::RuntimeType {
                public:
                static RTT * const it;
                virtual void init() { initParents(1,x10aux::getRTT<Object>()); }
                virtual const char *name() const {
                    static const char *name =
                        x10aux::alloc_printf("x10.lang.VoidFun_0_7[%s,%s,%s,%s,%s,%s,%s]",
                                             x10aux::getRTT<P1>()->name(),
                                             x10aux::getRTT<P2>()->name(),
                                             x10aux::getRTT<P3>()->name(),
                                             x10aux::getRTT<P4>()->name(),
                                             x10aux::getRTT<P5>()->name(),
                                             x10aux::getRTT<P6>()->name(),
                                             x10aux::getRTT<P7>()->name());
                    return name;
                }
            };
            virtual const x10aux::RuntimeType *_type() const {
                return x10aux::getRTT<VoidFun_0_7<P1,P2,P3,P4,P5,P6,P7> >();
            }

            virtual ~VoidFun_0_7() { }
            virtual void apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7) = 0;
        };
        template<class P1, class P2, class P3, class P4, class P5, class P6, class P7>
            typename VoidFun_0_7<P1,P2,P3,P4,P5,P6,P7>::RTT * const VoidFun_0_7<P1,P2,P3,P4,P5,P6,P7>::RTT::it =
                new (x10aux::alloc<typename VoidFun_0_7<P1,P2,P3,P4,P5,P6,P7>::RTT>())
                    typename VoidFun_0_7<P1,P2,P3,P4,P5,P6,P7>::RTT();
    }
}
#endif
// vim:tabstop=4:shiftwidth=4:expandtab
