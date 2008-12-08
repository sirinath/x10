#ifndef X10AUX_DOUBLE_UTILS_H
#define X10AUX_DOUBLE_UTILS_H

#include <x10aux/config.h>
#include <x10aux/math.h>
#include <x10aux/ref.h>

namespace x10 {
    namespace lang {
        class String;
    }
}

namespace x10aux {
    class double_utils {
    public:
        static const ref<x10::lang::String> toHexString(x10_double value);
        static const ref<x10::lang::String> toString(x10_double value);
        static x10_double parseDouble(const ref<x10::lang::String>& s);
        static x10_boolean isNaN(x10_double value);
        static x10_boolean isInfinite(x10_double value);
        static x10_long toLongBits(x10_double value);
        static x10_long toRawLongBits(x10_double value);
        static x10_double fromLongBits(x10_long value);
    };

    inline x10_double div(x10_double a, x10_double b) {
        x10_double d = (x10_double)(a / b); // Store in a variable to force rounding
        return d;
    }
    inline x10_double mod(x10_double a, x10_double b) {
        x10_double d = (x10_double)::fmod(a, b);
        return d;
    }
}

#endif
// vim:tabstop=4:shiftwidth=4:expandtab
