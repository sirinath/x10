#include <x10aux/config.h>

#include <x10/lang/Throwable.h>
#include <x10aux/string_utils.h>


using namespace x10::lang;
using namespace x10aux;

Throwable::Throwable() : Value() {
    this->FMGL(cause) = NULL;
    this->FMGL(message) = to_string("");
}


Throwable::Throwable(const ref<String> &message) : Value() {
    this->FMGL(cause) = NULL;
    this->FMGL(message) = message;
}


Throwable::Throwable(const ref<Throwable> &cause) : Value() {
    this->FMGL(cause) = cause;
    this->FMGL(message) = to_string("");
}


Throwable::Throwable(const ref<String> &message,
                     const ref<Throwable> &cause) : Value() {
    this->FMGL(cause) = cause;
    this->FMGL(message) = message;
}


ref<String> Throwable::getMessage() {
    return FMGL(message);
}

ref<Throwable> Throwable::getCause() {
    return FMGL(cause);
}

ref<String> Throwable::toString() {
    std::stringstream ss;
    ss << this->_type()->name() << ": " << *this->getMessage();
    return to_string(ss.str().c_str());
}


#if 0
TODO: perhaps another day
void Throwable::_serialize_fields(serialization_buffer& buf, addr_map& m) {
    Value::_serialize_fields(buf, m);
    buf.write(this->cause); /* non-value */
    _S_("Written reference cause");
    if (!m.ensure_unique(this->message)) assert (false);
    this->message->_serialize(buf, m);
    _S_("Serialized message");
}

void Throwable::_deserialize_fields(serialization_buffer& buf) {
    this->cause = buf.read<ref<Box < ref<Throwable> > > >(); /* non-value */
    this->message = _deserialize_value_ref<String >(buf);
}
#endif


DEFINE_RTT(Throwable);
