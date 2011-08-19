/*
 * (c) Copyright IBM Corporation 2008
 *
 * This file is part of XRX/C++ native layer implementation.
 */

#ifndef X10_UTIL_CONCURRENT_ATOMIC_ATOMICINTEGER_H
#define X10_UTIL_CONCURRENT_ATOMIC_ATOMICINTEGER_H

#include <x10rt17.h>
#include <x10/lang/Ref.h>

namespace x10 {
    namespace util {
        namespace concurrent {
            namespace atomic {

                /**
                 * Native implementation of AtomicInteger.
                 */
                class AtomicInteger : public x10::lang::Ref {
                public:
                    class RTT : public x10aux::RuntimeType {
                    public: 
                        static RTT* const it;
                        
                        virtual void init() {
                            initParents(1,x10aux::getRTT<x10::lang::Ref>());
                        }
                    
                        virtual const char *name() const {
                            return "x10.util.concurrent.atomic.AtomicInteger";
                        }
                    
                    };
                    virtual const x10aux::RuntimeType *_type() const {
                        return x10aux::getRTT<AtomicInteger>();
                    }

                    static x10aux::ref<AtomicInteger> _make() {
                        x10aux::ref<AtomicInteger> this_ = new (x10aux::alloc<AtomicInteger>()) AtomicInteger();
                        this_->_constructor(0);
                        return this_;
                    }

                    static x10aux::ref<AtomicInteger> _make(x10_int val) {
                        x10aux::ref<AtomicInteger> this_ = new (x10aux::alloc<AtomicInteger>()) AtomicInteger();
                        this_->_constructor(val);
                        return this_;
                    }

                protected:
                    x10aux::ref<AtomicInteger> _constructor(x10_int val) { _val = val; return this; }

                private:
                    volatile x10_int _val;

                public:
                    x10_int get() { return _val; }
                    
                    void set(x10_int newVal) { _val = newVal; }

                    x10_boolean compareAndSet(x10_int expect, x10_int update) {
                        return x10aux::atomic_ops::compareAndSet_32(&_val, expect, update) == expect;
                    }

                    x10_boolean weakCompareAndSet(x10_int expect, x10_int update) {
                        // TODO: for minor optimization on ppc we could add a weakCompareAndSet in atomic_ops and use that here
                        return x10aux::atomic_ops::compareAndSet_32(&_val, expect, update) == expect;
                    }

                    x10_int getAndIncrement() {
                        return getAndAdd(1);
                    }
                        
                    x10_int getAndDecrement() {
                        return getAndAdd(-1);
                    }

                    x10_int getAndAdd(x10_int delta) {
                        x10_int oldValue = _val;
                        while (x10aux::atomic_ops::compareAndSet_32(&_val, oldValue, oldValue+delta) != oldValue) {
                            oldValue = _val;
                        }
                        return oldValue;
                    }
	
                    x10_int incrementAndGet() {
                        return addAndGet(1);
                    }

                    x10_int decrementAndGet() {
                        return addAndGet(-1);
                    }
	
                    x10_int addAndGet(x10_int delta) {
                        x10_int oldValue = _val;
                        while (x10aux::atomic_ops::compareAndSet_32(&_val, oldValue, oldValue+delta) != oldValue) {
                            oldValue = _val;
                        }
                        return oldValue + delta;
                    }
	
                    x10aux::ref<x10::lang::String> toString() {
                        return x10aux::int_utils::toString(_val);
                    }

                    x10_int intValue() {
                        return _val;
                    }

                    x10_long longValue() {
                        return (x10_long)_val;
                    }

                    x10_float floatValue() {
                        return (x10_float)_val;
                    }
	
                    x10_double doubleValue() {
                        return (x10_double)_val;
                    }
                    
                };
            }
        }
    }
}
        
#endif /* X10_UTIL_CONCURRENT_ATOMIC_ATOMICINTEGER_H */

// vim:tabstop=4:shiftwidth=4:expandtab
