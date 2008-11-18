#include <x10aux/short_utils.h>
#include <x10aux/string_utils.h>

#include <x10/lang/String.h>					\

using namespace x10::lang;
using namespace std;
using namespace x10aux;

const ref<String> x10aux::short_utils::toString(x10_short value, x10_int radix) {
    (void) value; (void) radix;
	assert(false); /* FIXME: STUBBED NATIVE */
	return NULL;
}

const ref<String> x10aux::short_utils::toHexString(x10_short value) {
    (void) value;
	assert(false); /* FIXME: STUBBED NATIVE */
	return NULL;
}

const ref<String> x10aux::short_utils::toOctalString(x10_short value) {
    (void) value;
	assert(false); /* FIXME: STUBBED NATIVE */
	return NULL;
}

const ref<String> x10aux::short_utils::toBinaryString(x10_short value) {
    (void) value;
	assert(false); /* FIXME: STUBBED NATIVE */
	return NULL;
}

const ref<String> x10aux::short_utils::toString(x10_short value) {
	return to_string(value);
}

x10_short x10aux::short_utils::parseShort(const ref<String>& s, x10_int radix) {
    (void) s;
	assert(false); /* FIXME: STUBBED NATIVE */
	return radix; /* Bogus, but use radix to avoid warning about unused parameter */
}

x10_short x10aux::short_utils::parseShort(const ref<String>& s) {
    (void) s;
	assert(false); /* FIXME: STUBBED NATIVE */
	return 0; 
}

x10_short x10aux::short_utils::reverseBytes(x10_short x) {
	assert(false); /* FIXME: STUBBED NATIVE */
	return x; /* Bogus, but use x to avoid warning about unused parameter */
}
