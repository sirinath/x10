/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

#ifndef X10_UTIL_CONCURRENT_ATOMIC_ATOMICBOOLEAN_H
#define X10_UTIL_CONCURRENT_ATOMIC_ATOMICBOOLEAN_H

#include <x10/lang/Object.h>
#include <x10aux/serialization.h>
#include <x10aux/atomic_ops.h>
#include <x10aux/boolean_utils.h>

namespace x10 {
    namespace util {
        namespace concurrent {
            namespace atomic {

                /**
                 * Native implementation of AtomicBoolean.
                 */
                class AtomicBoolean : public x10::lang::Object {
                public:
                    RTT_H_DECLS_CLASS;

                    static x10aux::ref<AtomicBoolean> _make();
                    static x10aux::ref<AtomicBoolean> _make(x10_boolean val);

                protected:
                    x10aux::ref<AtomicBoolean> _constructor(x10_boolean val) {
                        this->x10::lang::Object::_constructor();
                        _val = (val ? 1 :0);
                        return this;
                    }

                public:
                    static const x10aux::serialization_id_t _serialization_id;

                    virtual x10aux::serialization_id_t _get_serialization_id() { return _serialization_id; };

                    virtual void _serialize_body(x10aux::serialization_buffer &buf);

                    template<class T> static x10aux::ref<T> _deserializer(x10aux::deserialization_buffer &buf);

                    virtual void _deserialize_body(x10aux::deserialization_buffer& buf);

                private:
                    /*
                     * An x10_int that is constrained to a 0/1 and interpreted as an x10_boolean.
                     * We do this so that we know that compareAndSet_32 can work on the whole memory word.
                     */
                    volatile x10_int _val;

                public:
                    x10_boolean get() { return _val == 1; }
                    
                    void set(x10_boolean newVal) { _val = (newVal ? 1 : 0); }

                    x10_boolean compareAndSet(x10_boolean expect, x10_boolean update) {
                        x10_int expectI = expect ? 1 : 0;
                        x10_int updateI = update ? 1 : 0;
                        x10_int oldVal = x10aux::atomic_ops::compareAndSet_32(&_val, expectI, updateI) == expectI;
                        return oldVal == 1;
                    }
                    
                    x10_boolean weakCompareAndSet(x10_boolean expect, x10_boolean update) {
                        // TODO: for minor optimization on ppc we could add a weakCompareAndSet in atomic_ops and use that here
                        x10_int expectI = expect ? 1 : 0;
                        x10_int updateI = update ? 1 : 0;
                        x10_int oldVal = x10aux::atomic_ops::compareAndSet_32(&_val, expectI, updateI) == expectI;
                        return oldVal == 1;
                    }

                    x10_boolean getAndSet(x10_boolean update) {
                        x10_boolean oldVal = get();
                        set(update);
                        return oldVal;
                    }
                        
                    x10aux::ref<x10::lang::String> toString() {
                        return x10aux::boolean_utils::toString(_val);
                    }
                };

                template<class T> x10aux::ref<T> AtomicBoolean::_deserializer(x10aux::deserialization_buffer &buf) {
                    x10aux::ref<AtomicBoolean> this_ = new (x10aux::alloc_remote<AtomicBoolean>()) AtomicBoolean();
                    buf.record_reference(this_); // TODO: avoid; no global refs; final class
                    this_->_deserialize_body(buf);
                    return this_;
                }
            }
        }
    }
}
        
#endif /* X10_UTIL_CONCURRENT_ATOMIC_ATOMICBOOLEAN_H */

// vim:tabstop=4:shiftwidth=4:expandtab
