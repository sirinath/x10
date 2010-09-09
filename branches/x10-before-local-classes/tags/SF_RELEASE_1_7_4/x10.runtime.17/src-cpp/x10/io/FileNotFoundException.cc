#include <x10aux/config.h>

#include <x10/io/FileNotFoundException.h>

using namespace x10::lang;
using namespace x10::io;
using namespace x10aux;

const serialization_id_t FileNotFoundException::_serialization_id =
    DeserializationDispatcher::addDeserializer(FileNotFoundException::_deserializer<Object>);

RTT_CC_DECLS1(FileNotFoundException, "x10.io.FileNotFoundException", IOException)

// vim:tabstop=4:shiftwidth=4:expandtab
