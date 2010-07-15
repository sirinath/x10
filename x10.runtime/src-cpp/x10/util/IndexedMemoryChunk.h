#ifndef __X10_UTIL_INDEXEDMEMORYCHUNK_H
#define __X10_UTIL_INDEXEDMEMORYCHUNK_H

#include <x10rt.h>

#include <x10/util/IndexedMemoryChunk.struct_h>

#endif // X10_UTIL_INDEXEDMEMORYCHUNK_H

namespace x10 { namespace util { 
template<class T> class IndexedMemoryChunk;
} } 

#ifndef X10_UTIL_INDEXEDMEMORYCHUNK_H_NODEPS
#define X10_UTIL_INDEXEDMEMORYCHUNK_H_NODEPS
#include <x10/lang/Any.h>
#include <x10/lang/String.h>
#ifndef X10_UTIL_INDEXEDMEMORYCHUNK_H_GENERICS
#define X10_UTIL_INDEXEDMEMORYCHUNK_H_GENERICS
#endif // X10_UTIL_INDEXEDMEMORYCHUNK_H_GENERICS
#ifndef X10_UTIL_INDEXEDMEMORYCHUNK_H_IMPLEMENTATION
#define X10_UTIL_INDEXEDMEMORYCHUNK_H_IMPLEMENTATION
#include <x10/util/IndexedMemoryChunk.h>


// ITable junk, both for IndexedMemoryChunk and IBox<IndexedMemoryChunk>
namespace x10 {
    namespace util { 
        template<class T> class IndexedMemoryChunk_ithunk0 : public x10::util::IndexedMemoryChunk<T> {
        public:
            static x10::lang::Any::itable<IndexedMemoryChunk_ithunk0<T> > itable;
        };

        template<class T> x10::lang::Any::itable<IndexedMemoryChunk_ithunk0<T> >
            IndexedMemoryChunk_ithunk0<T>::itable(&IndexedMemoryChunk<T>::at,
                                                  &IndexedMemoryChunk<T>::at,
                                                  &IndexedMemoryChunk<T>::equals,
                                                  &IndexedMemoryChunk<T>::hashCode,
                                                  &IndexedMemoryChunk<T>::home,
                                                  &IndexedMemoryChunk<T>::toString,
                                                  &IndexedMemoryChunk_ithunk0<T>::typeName);

        template<class T> class IndexedMemoryChunk_iboxithunk0 : public x10::lang::IBox<x10::util::IndexedMemoryChunk<T> > {
        public:
            static x10::lang::Any::itable<IndexedMemoryChunk_iboxithunk0<T> > itable;
            x10_boolean at(x10aux::ref<x10::lang::Object> arg0) {
                return this->value->at(arg0);
            }
            x10_boolean at(x10::lang::Place arg0) {
                return this->value->at(arg0);
            }
            x10_boolean equals(x10aux::ref<x10::lang::Any> arg0) {
                return this->value->equals(arg0);
            }
            x10_int hashCode() {
                return this->value->hashCode();
            }
            x10::lang::Place home() {
                return this->value->home();
            }
            x10aux::ref<x10::lang::String> toString() {
                return this->value->toString();
            }
            x10aux::ref<x10::lang::String> typeName() {
                return this->value->typeName();
            }
        };

        template<class T> x10::lang::Any::itable<IndexedMemoryChunk_iboxithunk0<T> >
            IndexedMemoryChunk_iboxithunk0<T>::itable(&IndexedMemoryChunk_iboxithunk0<T>::at,
                                                      &IndexedMemoryChunk_iboxithunk0<T>::at,
                                                      &IndexedMemoryChunk_iboxithunk0<T>::equals,
                                                      &IndexedMemoryChunk_iboxithunk0<T>::hashCode,
                                                      &IndexedMemoryChunk_iboxithunk0<T>::home,
                                                      &IndexedMemoryChunk_iboxithunk0<T>::toString,
                                                      &IndexedMemoryChunk_iboxithunk0<T>::typeName);
    }
} 


template<class T> void x10::util::IndexedMemoryChunk<T>::copyTo(x10_int srcIndex,
                                                                x10::lang::Place dstPlace,
                                                                x10::util::IndexedMemoryChunk<T> dst,
                                                                x10_int dstIndex,
                                                                x10_int numElems) {
    if (dstPlace->FMGL(id) == x10aux::here) {
        void* srcAddr = (void*)(&FMGL(data)[srcIndex]);
        void* dstAddr = (void*)(&dst->FMGL(data)[dstIndex]);
        size_t numBytes = numElems * sizeof(T);
        if (FMGL(data) == dst->FMGL(data)) {
            // potentially overlapping, use memmove
            memmove(dstAddr, srcAddr, numBytes);
        } else {
            memcpy(dstAddr, srcAddr, numBytes);
        }                
    } else {
        assert(false);
    }
}


template<class T> void x10::util::IndexedMemoryChunk<T>::_serialize(x10::util::IndexedMemoryChunk<T> this_,
                                                                    x10aux::serialization_buffer& buf) {
    buf.write((size_t)(this_->FMGL(data)));
}

template<class T> void x10::util::IndexedMemoryChunk<T>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    FMGL(data) = (T*)buf.read<size_t>();
}


template<class T> x10_boolean x10::util::IndexedMemoryChunk<T>::_struct_equals(x10aux::ref<x10::lang::Any> that) {
    if ((!(x10aux::instanceof<x10::util::IndexedMemoryChunk<T> >(that)))) {
        return false;
    }
    return _struct_equals(x10aux::class_cast<x10::util::IndexedMemoryChunk<T> >(that));
}

template<class T> x10aux::ref<x10::lang::String> x10::util::IndexedMemoryChunk<T>::toString() {
    char* tmp = x10aux::alloc_printf("x10.util.IndexedMemoryChunk<%s>(%p)", x10aux::getRTT<T>()->name(), FMGL(data));
    return x10::lang::String::Steal(tmp);
}

template<class T> x10aux::ref<x10::lang::String> x10::util::IndexedMemoryChunk<T>::typeName() {
    char* tmp = x10aux::alloc_printf("x10.util.IndexedMemoryChunk<%s>", x10aux::getRTT<T>()->name());
    return x10::lang::String::Steal(tmp);
}

template<class T> x10aux::RuntimeType x10::util::IndexedMemoryChunk<T>::rtt;

template<class T> x10aux::itable_entry x10::util::IndexedMemoryChunk<T>::_itables[2] = {x10aux::itable_entry(x10aux::getRTT<x10::lang::Any>(), &IndexedMemoryChunk_ithunk0<T>::itable),
                                                                                        x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::util::IndexedMemoryChunk<T> >())};

template<class T> x10aux::itable_entry x10::util::IndexedMemoryChunk<T>::_iboxitables[2] = {x10aux::itable_entry(x10aux::getRTT<x10::lang::Any>(), &IndexedMemoryChunk_iboxithunk0<T>::itable),
                                                                                            x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::util::IndexedMemoryChunk<T> >())};



template<class T> void x10::util::IndexedMemoryChunk<T>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::IndexedMemoryChunk<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Any>(), x10aux::getRTT<x10::lang::Any>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<T>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.IndexedMemoryChunk";
    rtt.initStageTwo(baseName, 2, parents, 1, params, variances);
}
#endif // X10_UTIL_INDEXEDMEMORYCHUNK_H_IMPLEMENTATION
#endif // __X10_UTIL_INDEXEDMEMORYCHUNK_H_NODEPS
