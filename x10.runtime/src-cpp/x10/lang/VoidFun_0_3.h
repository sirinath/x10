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

#ifndef X10_LANG_VOIDFUN_0_3_H
#define X10_LANG_VOIDFUN_0_3_H

#include <x10aux/config.h>
#include <x10aux/RTT.h>
#include <x10aux/fun_utils.h>

#include <x10/lang/Any.h>

namespace x10 {
    namespace lang {

        void _initRTTHelper_VoidFun_0_3(x10aux::RuntimeType *location,
                                        const x10aux::RuntimeType *rtt1,
                                        const x10aux::RuntimeType *rtt2,
                                        const x10aux::RuntimeType *rtt3);

        template<class P1, class P2, class P3> class VoidFun_0_3 : public x10aux::AnyFun {
            public:
            RTT_H_DECLS_INTERFACE

            template <class I> struct itable {
                itable(void(I::*apply)(P1,P2,P3),
                       x10_boolean (I::*_m0__at)(x10aux::ref<x10::lang::Object>),
                       x10_boolean (I::*_m1__at)(x10::lang::Place),
                       x10_boolean (I::*equals)(x10aux::ref<x10::lang::Any>),
                       x10_int (I::*hashCode)(),
                       x10::lang::Place (I::*home)(),
                       x10aux::ref<x10::lang::String> (I::*toString)(),
                       x10aux::ref<x10::lang::String> (I::*typeName)()
                    ) : apply(apply), _m0__at(_m0__at), _m1__at(_m1__at), equals(equals), hashCode(hashCode), home(home), toString(toString), typeName(typeName) {}
                void (I::*apply)(P1,P2,P3);
                x10_boolean (I::*_m0__at)(x10aux::ref<x10::lang::Object>);
                x10_boolean (I::*_m1__at)(x10::lang::Place);
                x10_boolean (I::*equals)(x10aux::ref<x10::lang::Any>);
                x10_int (I::*hashCode)();
                x10::lang::Place (I::*home)();
                x10aux::ref<x10::lang::String> (I::*toString)();
                x10aux::ref<x10::lang::String> (I::*typeName)();
            };
        };

        template<class P1, class P2, class P3> void VoidFun_0_3<P1,P2,P3>::_initRTT() {
            if (rtt.initStageOne(x10aux::getRTT<VoidFun_0_3<void,void,void> >())) return;
            x10::lang::_initRTTHelper_VoidFun_0_3(&rtt, x10aux::getRTT<P1>(), x10aux::getRTT<P2>(), 
                                                        x10aux::getRTT<P3>());
        }

        template<class P1, class P2, class P3> x10aux::RuntimeType VoidFun_0_3<P1,P2,P3>::rtt;

        template<> class VoidFun_0_3<void,void,void> {
        public:
            static x10aux::RuntimeType rtt;
            static const x10aux::RuntimeType* getRTT() { return &rtt; }
        };
    }
}
#endif
// vim:tabstop=4:shiftwidth=4:expandtab
