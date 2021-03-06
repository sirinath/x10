/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2015.
 */

#ifndef X10_LANG_VOIDFUN_0_5_H
#define X10_LANG_VOIDFUN_0_5_H

#include <x10aux/config.h>
#include <x10aux/RTT.h>

#include <x10/lang/Any.h>

namespace x10 {
    namespace lang {

        void _initRTTHelper_VoidFun_0_5(::x10aux::RuntimeType *location,
                                        const ::x10aux::RuntimeType *rtt1,
                                        const ::x10aux::RuntimeType *rtt2,
                                        const ::x10aux::RuntimeType *rtt3,
                                        const ::x10aux::RuntimeType *rtt4,
                                        const ::x10aux::RuntimeType *rtt5);

        template<class P1, class P2, class P3, class P4, class P5> class VoidFun_0_5 {
            public:
            static ::x10aux::RuntimeVoidFunType rtt;
            static const ::x10aux::RuntimeType* getRTT() { if (!rtt.isInitialized) _initRTT(); return &rtt; }
            static void _initRTT();

            template <class Iface> struct itable {
                itable(x10_boolean (Iface::*equals)(::x10::lang::Any*),
                       x10_int (Iface::*hashCode)(),
                       void(Iface::*__apply)(P1,P2,P3,P4,P5),
                       ::x10::lang::String* (Iface::*toString)(),
                       ::x10::lang::String* (Iface::*typeName)()
                    ) : equals(equals), hashCode(hashCode), __apply(__apply), toString(toString), typeName(typeName) {}
                x10_boolean (Iface::*equals)(::x10::lang::Any*);
                x10_int (Iface::*hashCode)();
                void (Iface::*__apply)(P1,P2,P3,P4,P5);
                ::x10::lang::String* (Iface::*toString)();
                ::x10::lang::String* (Iface::*typeName)();
            };

            static void __apply(VoidFun_0_5<P1,P2,P3,P4,P5>* fun, P1 arg1, P2 arg2, P3 arg3, P4 arg4, P5 arg5) {
                ::x10::lang::Reference* recv = reinterpret_cast< ::x10::lang::Reference*>(fun);
                return (recv->*(::x10aux::findITable<VoidFun_0_5<P1,P2,P3,P4,P5> >(recv->_getITables())->__apply))(arg1, arg2, arg3, arg4, arg5);
            }
            static x10_boolean equals(VoidFun_0_5<P1,P2,P3,P4,P5>* fun, ::x10::lang::Any* arg1) {
                ::x10::lang::Reference* recv = reinterpret_cast< ::x10::lang::Reference*>(fun);
                return (recv->*(::x10aux::findITable<VoidFun_0_5<P1,P2,P3,P4,P5> >(recv->_getITables())->equals))(arg1);
            }
            static x10_int hashCode(VoidFun_0_5<P1,P2,P3,P4,P5>* fun) {
                ::x10::lang::Reference* recv = reinterpret_cast< ::x10::lang::Reference*>(fun);
                return (recv->*(::x10aux::findITable<VoidFun_0_5<P1,P2,P3,P4,P5> >(recv->_getITables())->hashCode))();
            }
            static ::x10::lang::String* toString(VoidFun_0_5<P1,P2,P3,P4,P5>* fun) {
                ::x10::lang::Reference* recv = reinterpret_cast< ::x10::lang::Reference*>(fun);
                return (recv->*(::x10aux::findITable<VoidFun_0_5<P1,P2,P3,P4,P5> >(recv->_getITables())->toString))();
            }
            static ::x10::lang::String* typeName(VoidFun_0_5<P1,P2,P3,P4,P5>* fun) {
                ::x10::lang::Reference* recv = reinterpret_cast< ::x10::lang::Reference*>(fun);
                return (recv->*(::x10aux::findITable<VoidFun_0_5<P1,P2,P3,P4,P5> >(recv->_getITables())->typeName))();
            }
        };

        template<class P1, class P2, class P3, class P4, class P5>
            void VoidFun_0_5<P1,P2,P3,P4,P5>::_initRTT() {
            if (rtt.initStageOne(::x10aux::getRTT<VoidFun_0_5<void,void,void,void,void> >())) return;
            ::x10::lang::_initRTTHelper_VoidFun_0_5(&rtt, ::x10aux::getRTT<P1>(), ::x10aux::getRTT<P2>(), 
                                                        ::x10aux::getRTT<P3>(), ::x10aux::getRTT<P4>(), 
                                                        ::x10aux::getRTT<P5>());
        }

        template<class P1, class P2, class P3, class P4, class P5>
            ::x10aux::RuntimeVoidFunType VoidFun_0_5<P1,P2,P3,P4,P5>::rtt;

        template<> class VoidFun_0_5<void,void,void,void,void> {
        public:
            static ::x10aux::RuntimeType rtt;
            static const ::x10aux::RuntimeType* getRTT() { return &rtt; }
        };
    }
}
#endif
// vim:tabstop=4:shiftwidth=4:expandtab
