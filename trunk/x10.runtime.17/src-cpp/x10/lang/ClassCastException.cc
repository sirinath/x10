#include <x10aux/config.h>

#include <x10/lang/ClassCastException.h>

using namespace x10::lang;
using namespace x10aux;

const serialization_id_t ClassCastException::_serialization_id =
    DeserializationDispatcher::addDeserializer(ClassCastException::_deserializer<Object>);

RTT_CC_DECLS1(ClassCastException, "x10.lang.ClassCastException", RuntimeException)

// vim:tabstop=4:shiftwidth=4:expandtab
