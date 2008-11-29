#include <x10aux/config.h>
#include <x10aux/assert.h>

#include <x10/lang/String.h>

using namespace x10aux;
using namespace x10::lang;

void x10aux::x10__assertion_failed(const ref<x10::lang::String>& message) {
        fprintf(stderr,"Assertion failed: \"%s\"\n",message->c_str());
        abort();
}
