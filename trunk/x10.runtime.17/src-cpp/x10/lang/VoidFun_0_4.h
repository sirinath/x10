#ifndef X10_LANG_VOIDFUN_0_4_H
#define X10_LANG_VOIDFUN_0_4_H

#include <x10aux/config.h>
#include <x10aux/RTT.h>
#include <x10aux/fun_utils.h>

namespace x10 {
    namespace lang {

        void _initRTTHelper_VoidFun_0_4(x10aux::RuntimeType *location,
                                        const x10aux::RuntimeType *rtt1,
                                        const x10aux::RuntimeType *rtt2,
                                        const x10aux::RuntimeType *rtt3,
                                        const x10aux::RuntimeType *rtt4);

        template<class P1, class P2, class P3, class P4> class VoidFun_0_4 : public x10aux::AnyFun {
            public:
            RTT_H_DECLS_INTERFACE

            template <class I> struct itable {
                itable(void(I::*apply)(P1,P2,P3,P4)) : apply(apply) {}
                void (I::*apply)(P1,P2,P3,P4);
            };
        };

        template<class P1, class P2, class P3, class P4>
            void VoidFun_0_4<P1,P2,P3,P4>::_initRTT() {
            rtt.typeName = "CYCLIC RTT INIT\n";
            x10::lang::_initRTTHelper_VoidFun_0_4(&rtt, x10aux::getRTT<P1>(), x10aux::getRTT<P2>(), 
                                                        x10aux::getRTT<P3>(), x10aux::getRTT<P4>());
        }

        template<class P1, class P2, class P3, class P4>
            x10aux::RuntimeType VoidFun_0_4<P1,P2,P3,P4>::rtt;
    }
}
#endif
// vim:tabstop=4:shiftwidth=4:expandtab
