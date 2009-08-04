#ifndef X10_LANG_FUN_0_6_H
#define X10_LANG_FUN_0_6_H

#include <x10aux/config.h>
#include <x10aux/RTT.h>
#include <x10aux/fun_utils.h>

namespace x10 {
    namespace lang {

        void _initRTTHelper_Fun_0_6(x10aux::RuntimeType *location,
                                    const x10aux::RuntimeType *rtt0,
                                    const x10aux::RuntimeType *rtt1,
                                    const x10aux::RuntimeType *rtt2,
                                    const x10aux::RuntimeType *rtt3,
                                    const x10aux::RuntimeType *rtt4,
                                    const x10aux::RuntimeType *rtt5,
                                    const x10aux::RuntimeType *rtt6);

        template<class P1, class P2, class P3, class P4, class P5, class P6, class R> class Fun_0_6 : public x10aux::AnyFun {
            public:
            RTT_H_DECLS_INTERFACE

            template <class I> struct itable {
                itable(R(I::*apply)(P1,P2,P3,P4,P5,P6)) : apply(apply) {}
                R (I::*apply)(P1,P2,P3,P4,P5,P6);
            };
        };

        template<class P1, class P2, class P3, class P4, class P5, class P6, class R>
            void Fun_0_6<P1,P2,P3,P4,P5,P6,R>::_initRTT() {
            rtt.typeName = "CYCLIC RTT INIT\n";
            x10::lang::_initRTTHelper_Fun_0_6(&rtt, x10aux::getRTT<P1>(), x10aux::getRTT<P2>(), 
                                                    x10aux::getRTT<P3>(), x10aux::getRTT<P4>(), 
                                                    x10aux::getRTT<P5>(), x10aux::getRTT<P6>(),
                                                    x10aux::getRTT<R>());
        }
        
        template<class P1, class P2, class P3, class P4, class P5, class P6, class R>
            x10aux::RuntimeType Fun_0_6<P1,P2,P3,P4,P5,P6,R>::rtt;
    }
}
#endif
// vim:tabstop=4:shiftwidth=4:expandtab
