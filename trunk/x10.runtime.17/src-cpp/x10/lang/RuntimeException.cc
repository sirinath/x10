#include <x10aux/config.h>

#include <x10/lang/RuntimeException.h>

using namespace x10::lang;
using namespace x10aux;

const serialization_id_t RuntimeException::_serialization_id =
    DeserializationDispatcher::addDeserializer(RuntimeException::_deserializer<Object>);

RTT_CC_DECLS1(RuntimeException, "x10.lang.RuntimeException", Exception)
// vim:tabstop=4:shiftwidth=4:expandtab
