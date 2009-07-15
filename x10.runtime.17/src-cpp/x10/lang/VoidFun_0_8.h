#ifndef X10_LANG_VOIDFUN_0_8_H
#define X10_LANG_VOIDFUN_0_8_H

#include <x10aux/config.h>
#include <x10aux/RTT.h>
#include <x10/lang/Object.h>

namespace x10 {
    namespace lang {

        void _initRTTHelper_VoidFun_0_8(x10aux::RuntimeType *location,
                                        const x10aux::RuntimeType *rtt1,
                                        const x10aux::RuntimeType *rtt2,
                                        const x10aux::RuntimeType *rtt3,
                                        const x10aux::RuntimeType *rtt4,
                                        const x10aux::RuntimeType *rtt5,
                                        const x10aux::RuntimeType *rtt6,
                                        const x10aux::RuntimeType *rtt7,
                                        const x10aux::RuntimeType *rtt8);

        template<class P1, class P2, class P3, class P4, class P5, class P6, class P7, class P8> class VoidFun_0_8 : public virtual Object {
            public:
            RTT_H_DECLS

            virtual ~VoidFun_0_8() { }
            virtual void apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8) = 0;
        };

        template<class P1, class P2, class P3, class P4, class P5, class P6, class P7, class P8>
            void VoidFun_0_8<P1,P2,P3,P4,P5,P6,P7,P8>::_initRTT() {
            rtt.parentsc = -2;
            x10::lang::_initRTTHelper_VoidFun_0_8(&rtt, x10aux::getRTT<P1>(), x10aux::getRTT<P2>(), 
                                                        x10aux::getRTT<P3>(), x10aux::getRTT<P4>(), 
                                                        x10aux::getRTT<P5>(), x10aux::getRTT<P6>(),
                                                        x10aux::getRTT<P7>(), x10aux::getRTT<P8>());
        }

        template<class P1, class P2, class P3, class P4, class P5, class P6, class P7, class P8>
            x10aux::RuntimeType VoidFun_0_8<P1,P2,P3,P4,P5,P6,P7,P8>::rtt;
    }
}
#endif
// vim:tabstop=4:shiftwidth=4:expandtab
