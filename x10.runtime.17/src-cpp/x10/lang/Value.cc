#include <sstream>

#include <x10aux/ref.h>
#include <x10aux/alloc.h>

#include <x10/lang/Value.h>
#include <x10/lang/String.h>

using namespace x10::lang;
using namespace x10aux;

const serialization_id_t Value::_serialization_id =
    DeserializationDispatcher::addDeserializer(Value::_deserializer<Object>);

x10aux::ref<Value> Value::_make() {
    return (new (x10aux::alloc<Value>()) Value())->_constructor();
}

x10aux::ref<x10::lang::String> x10::lang::Value::toString() {
    return String::Lit("Value without toString defined.");
}

RTT_CC_DECLS1(Value, "x10.lang.Value", Object)

// vim:tabstop=4:shiftwidth=4:expandtab
