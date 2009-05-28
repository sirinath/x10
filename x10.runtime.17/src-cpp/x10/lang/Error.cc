#include <x10aux/config.h>

#include <x10/lang/Error.h>

using namespace x10::lang;
using namespace x10aux;

const serialization_id_t Error::_serialization_id =
    DeserializationDispatcher::addDeserializer(Error::_deserializer<Object>);

RTT_CC_DECLS1(Error, "x10.lang.Error", Throwable)

// vim:tabstop=4:shiftwidth=4:expandtab
