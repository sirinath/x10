#ifndef X10_LANG_FUN_0_5_H
#define X10_LANG_FUN_0_5_H

#include <x10aux/config.h>
#include <x10aux/RTT.h>
#include <x10aux/fun_utils.h>

namespace x10 {
    namespace lang {

        void _initRTTHelper_Fun_0_5(x10aux::RuntimeType *location,
                                    const x10aux::RuntimeType *rtt0,
                                    const x10aux::RuntimeType *rtt1,
                                    const x10aux::RuntimeType *rtt2,
                                    const x10aux::RuntimeType *rtt3,
                                    const x10aux::RuntimeType *rtt4,
                                    const x10aux::RuntimeType *rtt5);

        template<class P1, class P2, class P3, class P4, class P5, class R> class Fun_0_5 : public x10aux::AnyFun {
            public:
            RTT_H_DECLS_INTERFACE

            struct itable {
                itable(R(*apply)(x10aux::ref<Fun_0_5<P1,P2,P3,P4,P5,R> >, P1,P2,P3,P4,P5)) : apply(apply) {}
                R (*apply)(x10aux::ref<Fun_0_5<P1,P2,P3,P4,P5,R> >, P1,P2,P3,P4,P5);
            };
        };

        template<class P1, class P2, class P3, class P4, class P5, class R>
            void Fun_0_5<P1,P2,P3,P4,P5,R>::_initRTT() {
            rtt.parentsc = -2;
            x10::lang::_initRTTHelper_Fun_0_5(&rtt, x10aux::getRTT<P1>(), x10aux::getRTT<P2>(), 
                                                    x10aux::getRTT<P3>(), x10aux::getRTT<P4>(), 
                                                    x10aux::getRTT<P5>(), x10aux::getRTT<R>());
        }
        
        template<class P1, class P2, class P3, class P4, class P5, class R>
            x10aux::RuntimeType Fun_0_5<P1,P2,P3,P4,P5,R>::rtt;
    }
}
#endif
// vim:tabstop=4:shiftwidth=4:expandtab
