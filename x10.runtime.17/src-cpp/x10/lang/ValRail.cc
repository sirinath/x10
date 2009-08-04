#include <x10aux/config.h>
#include <x10aux/alloc.h>
#include <x10aux/RTT.h>

#include <x10/lang/Ref.h>
#include <x10/lang/Value.h>

using namespace x10aux;

namespace x10 {
    namespace lang {

        void
        _initRTTHelper_ValRail(RuntimeType *location, const RuntimeType *element,
                               const RuntimeType *p1, const RuntimeType *p2) {
            const RuntimeType *parents[3] = { Value::getRTT(), p1, p2 };
            const RuntimeType *params[1] = { element };
            RuntimeType::Variance variances[1] = { RuntimeType::covariant };
            const char *name = alloc_printf("x10.lang.ValRail[+%s]", element->name());
            location->init(name, 3, parents, 1, params, variances);
        }
    }
}

