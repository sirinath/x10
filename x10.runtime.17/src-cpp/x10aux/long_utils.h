#ifndef X10AUX_LONG_UTILS_H
#define X10AUX_LONG_UTILS_H

#include <x10aux/config.h>
#include <x10aux/ref.h>

namespace x10 {
    namespace lang {
        class String;
    }
}

namespace x10aux {
	class long_utils {
	public:
		static const ref<x10::lang::String> toString(x10_long value, x10_int radix);
		static const ref<x10::lang::String> toHexString(x10_long value);
		static const ref<x10::lang::String> toOctalString(x10_long value);
		static const ref<x10::lang::String> toBinaryString(x10_long value);
		static const ref<x10::lang::String> toString(x10_long value);
		static x10_long parseLong(const ref<x10::lang::String>& s, x10_int radix);
		static x10_long parseLong(const ref<x10::lang::String>& s);
		static x10_long highestOneBit(x10_long value);
		static x10_long lowestOneBit(x10_long value);
		static x10_int numberOfLeadingZeros(x10_long value);
		static x10_int numberOfTrailingZeros(x10_long value);
		static x10_int bitCount(x10_long value);
		static x10_long rotateLeft(x10_long value);
		static x10_long rotateRight(x10_long value);
		static x10_long reverse(x10_long value);
		static x10_int signum(x10_long value);
		static x10_long reverseBytes(x10_long value);
	};
}

#endif
