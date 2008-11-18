#ifndef X10AUX_FLOAT_UTILS_H
#define X10AUX_FLOAT_UTILS_H

#include <x10aux/config.h>
#include <x10aux/ref.h>

namespace x10 {
    namespace lang {
        class String;
    }
}

namespace x10aux {
	class float_utils {
	public:
		static const ref<x10::lang::String> toHexString(x10_float value);
		static const ref<x10::lang::String> toString(x10_float value);
		static x10_float parseFloat(const ref<x10::lang::String>& s);
		static x10_boolean isNaN(x10_float value);
		static x10_boolean isInfinite(x10_float value);
		static x10_int toIntBits(x10_float value);
		static x10_int toRawIntBits(x10_float value);
		static x10_float fromIntBits(x10_int value);
	};
}

#endif
