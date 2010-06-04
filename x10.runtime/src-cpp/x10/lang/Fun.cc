#include <x10aux/config.h>
#include <x10aux/alloc.h>
#include <x10aux/RTT.h>

#include <x10/lang/Fun_0_0.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Fun_0_3.h>
#include <x10/lang/Fun_0_4.h>
#include <x10/lang/Fun_0_5.h>
#include <x10/lang/Fun_0_6.h>
#include <x10/lang/Fun_0_7.h>
#include <x10/lang/Fun_0_8.h>
#include <x10/lang/Fun_0_9.h>
#include <x10/lang/VoidFun_0_0.h>
#include <x10/lang/VoidFun_0_1.h>
#include <x10/lang/VoidFun_0_2.h>
#include <x10/lang/VoidFun_0_3.h>
#include <x10/lang/VoidFun_0_4.h>
#include <x10/lang/VoidFun_0_5.h>
#include <x10/lang/VoidFun_0_6.h>
#include <x10/lang/VoidFun_0_7.h>
#include <x10/lang/VoidFun_0_8.h>
#include <x10/lang/VoidFun_0_9.h>

using namespace x10::lang;
using namespace x10aux;

x10aux::RuntimeType Fun_0_0<void>::rtt;
x10aux::RuntimeType Fun_0_1<void,void>::rtt;
x10aux::RuntimeType Fun_0_2<void,void,void>::rtt;
x10aux::RuntimeType Fun_0_3<void,void,void,void>::rtt;
x10aux::RuntimeType Fun_0_4<void,void,void,void,void>::rtt;
x10aux::RuntimeType Fun_0_5<void,void,void,void,void,void>::rtt;
x10aux::RuntimeType Fun_0_6<void,void,void,void,void,void,void>::rtt;
x10aux::RuntimeType Fun_0_7<void,void,void,void,void,void,void,void>::rtt;
x10aux::RuntimeType Fun_0_8<void,void,void,void,void,void,void,void,void>::rtt;
x10aux::RuntimeType Fun_0_9<void,void,void,void,void,void,void,void,void,void>::rtt;

x10aux::RuntimeType VoidFun_0_0::rtt;
x10aux::RuntimeType VoidFun_0_1<void>::rtt;
x10aux::RuntimeType VoidFun_0_2<void,void>::rtt;
x10aux::RuntimeType VoidFun_0_3<void,void,void>::rtt;
x10aux::RuntimeType VoidFun_0_4<void,void,void,void>::rtt;
x10aux::RuntimeType VoidFun_0_5<void,void,void,void,void>::rtt;
x10aux::RuntimeType VoidFun_0_6<void,void,void,void,void,void>::rtt;
x10aux::RuntimeType VoidFun_0_7<void,void,void,void,void,void,void>::rtt;
x10aux::RuntimeType VoidFun_0_8<void,void,void,void,void,void,void,void>::rtt;
x10aux::RuntimeType VoidFun_0_9<void,void,void,void,void,void,void,void,void>::rtt;

namespace x10 {
    namespace lang {

        void
        VoidFun_0_0::_initRTT() {
            rtt.canonical = &(VoidFun_0_0::rtt);
            rtt.init(&rtt, "x10::lang::VoidFun_0_0", 0, NULL, 0, NULL, NULL);
        }

        void
        _initRTTHelper_Fun_0_0(RuntimeType *location, const RuntimeType *rtt0) {
            const RuntimeType* params[1] = { rtt0 };
            RuntimeType::Variance variances[1] = { RuntimeType::covariant };
            const RuntimeType* canonical = getRTT<Fun_0_0<void> >();
            const char *name = alloc_printf("x10.lang.Fun_0_0[%s]",rtt0->name());
            location->init(canonical, name, 0, NULL, 1, params, variances);
        }

        void
        _initRTTHelper_Fun_0_1(RuntimeType *location, const RuntimeType *rtt0, const RuntimeType *rtt1) {
            const RuntimeType* params[2] = { rtt0, rtt1 };
            RuntimeType::Variance variances[2] = { RuntimeType::covariant, RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<Fun_0_1<void, void> >();
            const char *name =  alloc_printf("x10.lang.Fun_0_1[%s,%s]", rtt0->name(), rtt1->name());
            location->init(canonical, name, 0, NULL, 2, params, variances);
        }    

        void
        _initRTTHelper_Fun_0_2(RuntimeType *location,
                               const RuntimeType *rtt0,
                               const RuntimeType *rtt1,
                               const RuntimeType *rtt2) {
            const RuntimeType* params[3] = { rtt0, rtt1, rtt2 };
            RuntimeType::Variance variances[] = { RuntimeType::covariant, RuntimeType::contravariant, RuntimeType::contravariant };
            const char *name =  alloc_printf("x10.lang.Fun_0_2[%s,%s,%s]",
                                             rtt0->name(), rtt1->name(), rtt2->name());
            const RuntimeType* canonical = getRTT<Fun_0_2<void, void, void> >();
            location->init(canonical, name, 0, NULL, 3, params, variances);
        }    

        void
        _initRTTHelper_Fun_0_3(RuntimeType *location,
                               const RuntimeType *rtt0,
                               const RuntimeType *rtt1,
                               const RuntimeType *rtt2,
                               const RuntimeType *rtt3) {
            const RuntimeType* params[4] = { rtt0, rtt1, rtt2, rtt3 };
            RuntimeType::Variance variances[4] = { RuntimeType::covariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant };
            const char *name =  alloc_printf("x10.lang.Fun_0_3[%s,%s,%s,%s]",
                                             rtt0->name(), rtt1->name(), rtt2->name(), rtt3->name());
            const RuntimeType* canonical = getRTT<Fun_0_3<void, void, void, void> >();
            location->init(canonical, name, 0, NULL, 4, params, variances);
        }    

        void
        _initRTTHelper_Fun_0_4(RuntimeType *location,
                               const RuntimeType *rtt0,
                               const RuntimeType *rtt1,
                               const RuntimeType *rtt2,
                               const RuntimeType *rtt3,
                               const RuntimeType *rtt4) {
            const RuntimeType* params[5] = { rtt0, rtt1, rtt2, rtt3, rtt4 };
            RuntimeType::Variance variances[5] = { RuntimeType::covariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<Fun_0_4<void, void, void, void, void> >();
            const char *name =  alloc_printf("x10.lang.Fun_0_4[%s,%s,%s,%s,%s]",
                                             rtt0->name(), rtt1->name(), rtt2->name(), rtt3->name(), rtt4->name());
            location->init(canonical, name, 0, NULL, 5, params, variances);
        }    

        void
        _initRTTHelper_Fun_0_5(RuntimeType *location,
                               const RuntimeType *rtt0,
                               const RuntimeType *rtt1,
                               const RuntimeType *rtt2,
                               const RuntimeType *rtt3,
                               const RuntimeType *rtt4,
                               const RuntimeType *rtt5) {
            const RuntimeType* params[6] = { rtt0, rtt1, rtt2, rtt3, rtt4, rtt5 };
            RuntimeType::Variance variances[6] = { RuntimeType::covariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant,
                                                  RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<Fun_0_5<void, void, void, void, void, void> >();
            const char *name =  alloc_printf("x10.lang.Fun_0_5[%s,%s,%s,%s,%s,%s]",
                                             rtt0->name(), rtt1->name(), rtt2->name(), rtt3->name(),
                                             rtt4->name(), rtt5->name());
            location->init(canonical, name, 0, NULL, 6, params, variances);
        }    

        void
        _initRTTHelper_Fun_0_6(RuntimeType *location,
                               const RuntimeType *rtt0,
                               const RuntimeType *rtt1,
                               const RuntimeType *rtt2,
                               const RuntimeType *rtt3,
                               const RuntimeType *rtt4,
                               const RuntimeType *rtt5,
                               const RuntimeType *rtt6) {
            const RuntimeType* params[7] = { rtt0, rtt1, rtt2, rtt3, rtt4, rtt5, rtt6 };
            RuntimeType::Variance variances[7] = { RuntimeType::covariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant,
                                                   RuntimeType::contravariant, RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<Fun_0_6<void, void, void, void, void, void, void> >();
            const char *name =  alloc_printf("x10.lang.Fun_0_6[%s,%s,%s,%s,%s,%s,%s]",
                                             rtt0->name(), rtt1->name(), rtt2->name(), rtt3->name(),
                                             rtt4->name(), rtt5->name(), rtt6->name());
            location->init(canonical, name, 0, NULL, 7, params, variances);
        }    

        void
        _initRTTHelper_Fun_0_7(RuntimeType *location,
                               const RuntimeType *rtt0,
                               const RuntimeType *rtt1,
                               const RuntimeType *rtt2,
                               const RuntimeType *rtt3,
                               const RuntimeType *rtt4,
                               const RuntimeType *rtt5,
                               const RuntimeType *rtt6,
                               const RuntimeType *rtt7) {
            const RuntimeType* params[8] = { rtt0, rtt1, rtt2, rtt3, rtt4, rtt5, rtt6, rtt7 };
            RuntimeType::Variance variances[8] = { RuntimeType::covariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant,
                                                   RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<Fun_0_7<void, void, void, void, void, void, void, void> >();
            const char *name =  alloc_printf("x10.lang.Fun_0_7[%s,%s,%s,%s,%s,%s,%s,%s]",
                                             rtt0->name(), rtt1->name(), rtt2->name(), rtt3->name(),
                                             rtt4->name(), rtt5->name(), rtt6->name(), rtt7->name());
            location->init(canonical, name, 0, NULL, 8, params, variances);
        }    

        void
        _initRTTHelper_Fun_0_8(RuntimeType *location,
                               const RuntimeType *rtt0,
                               const RuntimeType *rtt1,
                               const RuntimeType *rtt2,
                               const RuntimeType *rtt3,
                               const RuntimeType *rtt4,
                               const RuntimeType *rtt5,
                               const RuntimeType *rtt6,
                               const RuntimeType *rtt7,
                               const RuntimeType *rtt8) {
            const RuntimeType* params[9] = { rtt0, rtt1, rtt2, rtt3, rtt4, rtt5, rtt6, rtt7, rtt8 };
            RuntimeType::Variance variances[9] = { RuntimeType::covariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant,
                                                   RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<Fun_0_8<void, void, void, void, void, void, void, void, void> >();
            const char *name =  alloc_printf("x10.lang.Fun_0_8[%s,%s,%s,%s,%s,%s,%s,%s,%s]",
                                             rtt0->name(), rtt1->name(), rtt2->name(), rtt3->name(),
                                             rtt4->name(), rtt5->name(), rtt6->name(), rtt7->name(), rtt8->name());
            location->init(canonical, name, 0, NULL, 9, params, variances);
        }    

        void
        _initRTTHelper_Fun_0_9(RuntimeType *location,
                               const RuntimeType *rtt0,
                               const RuntimeType *rtt1,
                               const RuntimeType *rtt2,
                               const RuntimeType *rtt3,
                               const RuntimeType *rtt4,
                               const RuntimeType *rtt5,
                               const RuntimeType *rtt6,
                               const RuntimeType *rtt7,
                               const RuntimeType *rtt8,
                               const RuntimeType *rtt9) {
            const RuntimeType* params[10] = { rtt0, rtt1, rtt2, rtt3, rtt4, rtt5, rtt6, rtt7, rtt8, rtt9 };
            RuntimeType::Variance variances[10] = { RuntimeType::covariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant,
                                                   RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<Fun_0_9<void, void, void, void, void, void, void, void, void, void> >();
            const char *name =  alloc_printf("x10.lang.Fun_0_9[%s,%s,%s,%s,%s,%s,%s,%s,%s,%s]",
                                             rtt0->name(), rtt1->name(), rtt2->name(), rtt3->name(),
                                             rtt4->name(), rtt5->name(), rtt6->name(), rtt7->name(), rtt8->name(), rtt9->name());
            location->init(canonical, name, 0, NULL, 10, params, variances);
        }    

        void
        _initRTTHelper_VoidFun_0_1(RuntimeType *location, const RuntimeType *rtt1) {
            const RuntimeType* params[1] = { rtt1 };
            RuntimeType::Variance variances[] = { RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<VoidFun_0_1<void> >();
            const char *name =  alloc_printf("x10.lang.VoidFun_0_1[%s]", rtt1->name());
            location->init(canonical, name, 0, NULL, 1, params, variances);
        }    

        void
        _initRTTHelper_VoidFun_0_2(RuntimeType *location,
                                   const RuntimeType *rtt1,
                                   const RuntimeType *rtt2) {
            const RuntimeType* params[2] = { rtt1, rtt2 };
            RuntimeType::Variance variances[2] = { RuntimeType::contravariant, RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<VoidFun_0_2<void, void> >();
            const char *name =  alloc_printf("x10.lang.VoidFun_0_2[%s,%s]",
                                             rtt1->name(), rtt2->name());
            location->init(canonical, name, 0, NULL, 2, params, variances);
        }    

        void
        _initRTTHelper_VoidFun_0_3(RuntimeType *location,
                                   const RuntimeType *rtt1,
                                   const RuntimeType *rtt2,
                                   const RuntimeType *rtt3) {
            const RuntimeType* params[3] = { rtt1, rtt2, rtt3 };
            RuntimeType::Variance variances[3] = { RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<VoidFun_0_3<void, void, void> >();
            const char *name =  alloc_printf("x10.lang.VoidFun_0_3[%s,%s,%s]",
                                             rtt1->name(), rtt2->name(), rtt3->name());
            location->init(canonical, name, 0, NULL, 3, params, variances);
        }    

        void
        _initRTTHelper_VoidFun_0_4(RuntimeType *location,
                                   const RuntimeType *rtt1,
                                   const RuntimeType *rtt2,
                                   const RuntimeType *rtt3,
                                   const RuntimeType *rtt4) {
            const RuntimeType* params[4] = { rtt1, rtt2, rtt3, rtt4 };
            RuntimeType::Variance variances[4] = { RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<VoidFun_0_4<void, void, void, void> >();
            const char *name =  alloc_printf("x10.lang.VoidFun_0_4[%s,%s,%s,%s]",
                                             rtt1->name(), rtt2->name(), rtt3->name(), rtt4->name());
            location->init(canonical, name, 0, NULL, 4, params, variances);
        }    

        void
        _initRTTHelper_VoidFun_0_5(RuntimeType *location,
                                   const RuntimeType *rtt1,
                                   const RuntimeType *rtt2,
                                   const RuntimeType *rtt3,
                                   const RuntimeType *rtt4,
                                   const RuntimeType *rtt5) {
            const RuntimeType* params[5] = { rtt1, rtt2, rtt3, rtt4, rtt5 };
            RuntimeType::Variance variances[5] = { RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<VoidFun_0_5<void, void, void, void, void> >();
            const char *name =  alloc_printf("x10.lang.VoidFun_0_5[%s,%s,%s,%s,%s]",
                                             rtt1->name(), rtt2->name(), rtt3->name(),
                                             rtt4->name(), rtt5->name());
            location->init(canonical, name, 0, NULL, 5, params, variances);
        }    

        void
        _initRTTHelper_VoidFun_0_6(RuntimeType *location,
                                   const RuntimeType *rtt1,
                                   const RuntimeType *rtt2,
                                   const RuntimeType *rtt3,
                                   const RuntimeType *rtt4,
                                   const RuntimeType *rtt5,
                                   const RuntimeType *rtt6) {
            const RuntimeType* params[6] = { rtt1, rtt2, rtt3, rtt4, rtt5, rtt6 };
            RuntimeType::Variance variances[6] = { RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant,
                                                   RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<VoidFun_0_6<void, void, void, void, void, void> >();
            const char *name =  alloc_printf("x10.lang.VoidFun_0_6[%s,%s,%s,%s,%s,%s]",
                                             rtt1->name(), rtt2->name(), rtt3->name(),
                                             rtt4->name(), rtt5->name(), rtt6->name());
            location->init(canonical, name, 0, NULL, 6, params, variances);
        }    

        void
        _initRTTHelper_VoidFun_0_7(RuntimeType *location,
                                   const RuntimeType *rtt1,
                                   const RuntimeType *rtt2,
                                   const RuntimeType *rtt3,
                                   const RuntimeType *rtt4,
                                   const RuntimeType *rtt5,
                                   const RuntimeType *rtt6,
                                   const RuntimeType *rtt7) {
            const RuntimeType* params[7] = { rtt1, rtt2, rtt3, rtt4, rtt5, rtt6, rtt7 };
            RuntimeType::Variance variances[7] = { RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant,
                                                   RuntimeType::contravariant, RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<VoidFun_0_7<void, void, void, void, void, void, void> >();
            const char *name =  alloc_printf("x10.lang.VoidFun_0_7[%s,%s,%s,%s,%s,%s,%s]",
                                             rtt1->name(), rtt2->name(), rtt3->name(),
                                             rtt4->name(), rtt5->name(), rtt6->name(), rtt7->name());
            location->init(canonical, name, 0, NULL, 7, params, variances);
        }    

        void
        _initRTTHelper_VoidFun_0_8(RuntimeType *location,
                                   const RuntimeType *rtt1,
                                   const RuntimeType *rtt2,
                                   const RuntimeType *rtt3,
                                   const RuntimeType *rtt4,
                                   const RuntimeType *rtt5,
                                   const RuntimeType *rtt6,
                                   const RuntimeType *rtt7,
                                   const RuntimeType *rtt8) {
            const RuntimeType* params[8] = { rtt1, rtt2, rtt3, rtt4, rtt5, rtt6, rtt7, rtt8 };
            RuntimeType::Variance variances[8] = { RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant,
                                                   RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<VoidFun_0_8<void, void, void, void, void, void, void, void> >();
            const char *name =  alloc_printf("x10.lang.VoidFun_0_8[%s,%s,%s,%s,%s,%s,%s,%s]",
                                             rtt1->name(), rtt2->name(), rtt3->name(),
                                             rtt4->name(), rtt5->name(), rtt6->name(),
                                             rtt7->name(), rtt8->name());
            location->init(canonical, name, 0, NULL, 8, params, variances);
        }    

        void
        _initRTTHelper_VoidFun_0_9(RuntimeType *location,
                                   const RuntimeType *rtt1,
                                   const RuntimeType *rtt2,
                                   const RuntimeType *rtt3,
                                   const RuntimeType *rtt4,
                                   const RuntimeType *rtt5,
                                   const RuntimeType *rtt6,
                                   const RuntimeType *rtt7,
                                   const RuntimeType *rtt8,
                                   const RuntimeType *rtt9) {
            const RuntimeType* params[9] = { rtt1, rtt2, rtt3, rtt4, rtt5, rtt6, rtt7, rtt8, rtt9 };
            RuntimeType::Variance variances[9] = { RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant,
                                                   RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant, RuntimeType::contravariant };
            const RuntimeType* canonical = getRTT<VoidFun_0_9<void, void, void, void, void, void, void, void, void> >();
            const char *name =  alloc_printf("x10.lang.VoidFun_0_9[%s,%s,%s,%s,%s,%s,%s,%s,%s]",
                                             rtt1->name(), rtt2->name(), rtt3->name(),
                                             rtt4->name(), rtt5->name(), rtt6->name(),
                                             rtt7->name(), rtt8->name(), rtt9->name());
            location->init(canonical, name, 0, NULL, 9, params, variances);
        }    
    }
}
