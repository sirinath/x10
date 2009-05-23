#ifndef X10_LANG_VALUE_H
#define X10_LANG_VALUE_H

#include <x10aux/config.h>
#include <x10aux/ref.h>
#include <x10aux/serialization.h>
#include <x10aux/RTT.h>

#include <x10/lang/Object.h>
namespace x10 { namespace lang { class String; } }

namespace x10 {

    namespace lang {

        class Value : public virtual Object {
        public:

            class RTT : public x10aux::RuntimeType { 
                public:
            
                virtual void init() { initParents(1,x10aux::getRTT<Object>()); }
                
                virtual const char *name() const {
                    return "x10.lang.Value";
                }

            };
            static RTT* const rtt; 

            virtual const x10aux::RuntimeType *_type() const {
                return x10aux::getRTT<Value>();
            }

            static x10aux::ref<Value> _make()
            { return (new (x10aux::alloc<Value>()) Value())->_constructor(); }

            x10aux::ref<Value> _constructor() { return this; }


            static const x10aux::serialization_id_t _serialization_id;


            virtual void _serialize_id(x10aux::serialization_buffer &buf, x10aux::addr_map &m) {
                buf.write(_serialization_id,m);
            };

            virtual void _serialize_body(x10aux::serialization_buffer &, x10aux::addr_map &) {
                // there are no fields
            };


            template<class T>
            static x10aux::ref<T> _deserializer(x10aux::serialization_buffer &) {
                x10aux::ref<Value> this_ = new (x10aux::alloc<Value>())Value();
                return this_;
            }

            void _deserialize_body(x10aux::serialization_buffer &) {
                // there are no fields
            }



            virtual x10_int hashCode() {
                // All instances of Value are equal, so their hashcodes can be too.
                return 0;
            }

            virtual x10aux::ref<String> toString();

            virtual x10_boolean equals(x10aux::ref<Value> other) {
                if (other == x10aux::ref<Value>(this)) return true;
                if (!x10aux::instanceof<Value>(other)) return false;
                return this->_struct_equals(other);
            }

            virtual x10_boolean equals(x10aux::ref<Ref> other) {
                return false;
            }

            virtual x10_boolean _struct_equals(x10aux::ref<Object> other) {
                if (!_type()->concreteInstanceOf(other))
                    return false;
                // now compare fields but there aren't any
                return true;
            }

        };

    }
}


#endif
// vim:tabstop=4:shiftwidth=4:expandtab
