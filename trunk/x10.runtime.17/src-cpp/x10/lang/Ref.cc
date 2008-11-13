#include <sstream>

#include <x10aux/ref.h>
#include <x10aux/alloc.h>

#include <x10/lang/Ref.h>
#include <x10/lang/String.h>

x10_int x10::lang::Ref::hashCode() {
    return (x10_int) this;
}

x10aux::ref<x10::lang::String> x10::lang::Ref::toString() {
    std::stringstream ss;
    ss << this->_type()->name() << "@" << std::hex << (std::size_t)this;
    return new (x10aux::alloc<x10::lang::String>()) x10::lang::String(ss.str());
}

const x10::lang::Ref::RTT * const x10::lang::Ref::RTT::it =
    new x10::lang::Ref::RTT();
