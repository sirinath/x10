#ifndef X10_LANG_FUN_0_9_H
#define X10_LANG_FUN_0_9_H

#include <x10aux/config.h>
#include <x10aux/RTT.h>
#include <x10aux/fun_utils.h>

namespace x10 {
    namespace lang {
        extern const x10aux::RuntimeType* _initRTTHelper_Fun_0_9(const x10aux::RuntimeType **location,
                                                                 const x10aux::RuntimeType *rtt0,
                                                                 const x10aux::RuntimeType *rtt1,
                                                                 const x10aux::RuntimeType *rtt2,
                                                                 const x10aux::RuntimeType *rtt3,
                                                                 const x10aux::RuntimeType *rtt4,
                                                                 const x10aux::RuntimeType *rtt5,
                                                                 const x10aux::RuntimeType *rtt6,
                                                                 const x10aux::RuntimeType *rtt7,
                                                                 const x10aux::RuntimeType *rtt8,
                                                                 const x10aux::RuntimeType *rtt9);

        template<class P1, class P2, class P3, class P4, class P5, class P6, class P7, class P8, class P9, class R> class Fun_0_9 : public x10aux::AnyFun {
            public:
            static const x10aux::RuntimeType* rtt;
            static const x10aux::RuntimeType* getRTT() { return NULL == rtt ? _initRTT() : rtt; }
            static const x10aux::RuntimeType* _initRTT();

            template <class I> struct itable {
                itable(R(I::*apply)(P1,P2,P3,P4,P5,P6,P7,P8,P9)) : apply(apply) {}
                R (I::*apply)(P1,P2,P3,P4,P5,P6,P7,P8,P9);
            };
        };

        template<class P1, class P2, class P3, class P4, class P5, class P6, class P7, class P8, class P9, class R>
            const x10aux::RuntimeType* Fun_0_9<P1,P2,P3,P4,P5,P6,P7,P8,P9,R>::_initRTT() {
            return x10::lang::_initRTTHelper_Fun_0_9(&rtt, x10aux::getRTT<P1>(), x10aux::getRTT<P2>(), 
                                                     x10aux::getRTT<P3>(), x10aux::getRTT<P4>(), 
                                                     x10aux::getRTT<P5>(), x10aux::getRTT<P6>(),
                                                     x10aux::getRTT<P7>(), x10aux::getRTT<P8>(),
                                                     x10aux::getRTT<P9>(), x10aux::getRTT<R>());
        }
            
        template<class P1, class P2, class P3, class P4, class P5, class P6, class P7, class P8, class P9, class R>
            const x10aux::RuntimeType* Fun_0_9<P1,P2,P3,P4,P5,P6,P7,P8,P9,R>::rtt = NULL;
    }
}
#endif
// vim:tabstop=4:shiftwidth=4:expandtab
