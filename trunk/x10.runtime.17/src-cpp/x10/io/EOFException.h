#ifndef X10_IO_EOFEXCEPTION_H
#define X10_IO_EOFEXCEPTION_H

#include <x10aux/config.h>
#include <x10aux/RTT.h>

#include <x10/io/IOException.h>

namespace x10 {

    namespace io {

        class EOFException : public IOException {
        public:
            class RTT : public x10aux::RuntimeType { 
                public:
                static RTT* const it; 
            
                virtual void init() {
                    initParents(1,x10aux::getRTT<IOException>());
                }
                
                virtual std::string name() const {
                    return "x10.io.EOFException";
                }

            };

            virtual const x10aux::RuntimeType *_type() const {
                return x10aux::getRTT<EOFException>();
            }

            EOFException() : IOException() { }
            EOFException(x10aux::ref<x10::lang::String> message) : IOException(message) {   }
            EOFException(x10aux::ref<x10::lang::String> message,
                        x10aux::ref<x10::lang::Throwable> cause)
              : IOException(message,cause) { }
            EOFException(x10aux::ref<x10::lang::Throwable> cause) : IOException(cause) { }

            EOFException(x10aux::SERIALIZATION_MARKER m) : IOException(m) { }

            static const x10aux::serialization_id_t _serialization_id;

            virtual void _serialize_id(x10aux::serialization_buffer &buf, x10aux::addr_map &m) {
                buf.write(_serialization_id,m);
            }

            virtual void _serialize_body(x10aux::serialization_buffer& buf, x10aux::addr_map& m) {
                IOException::_serialize_body(buf,m);
            }

            void _deserialize_body(x10aux::serialization_buffer& buf) {
                IOException::_deserialize_body(buf);
            }

            template<class T>
            static x10aux::ref<T> _deserializer(x10aux::serialization_buffer &buf) {
                x10aux::ref<EOFException> this_ =
                    X10NEW(EOFException)(x10aux::SERIALIZATION_MARKER());
                this_->_deserialize_body(buf);
                return this_;
            }


        };

    }
}


#endif
// vim:tabstop=4:shiftwidth=4:expandtab
