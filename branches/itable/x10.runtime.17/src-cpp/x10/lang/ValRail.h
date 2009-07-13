#ifndef X10_LANG_VALRAIL_H
#define X10_LANG_VALRAIL_H


#include <x10aux/config.h>
#include <x10aux/alloc.h>
#include <x10aux/RTT.h>


#include <x10/lang/Ref.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Iterable.h>

#include <x10aux/rail_utils.h>
#include <x10aux/basic_functions.h>

namespace x10 {

    namespace lang {

        extern const x10aux::RuntimeType* _initRTTHelper_ValRail(const x10aux::RuntimeType **location, const x10aux::RuntimeType *element,
                                                                 const x10aux::RuntimeType *p1, const x10aux::RuntimeType *p2);
        extern const x10aux::RuntimeType* _initRTTHelper_ValRailIterator(const x10aux::RuntimeType **location, const x10aux::RuntimeType *element,
                                                                         const x10aux::RuntimeType *p1);

        template<class T> class ValRail : public Value {

            public:
            static const x10aux::RuntimeType* rtt;
            static const x10aux::RuntimeType* getRTT() { return NULL == rtt ? _initRTT() : rtt; }
            static const x10aux::RuntimeType* _initRTT();
            virtual const x10aux::RuntimeType *_type() const { return getRTT(); }

            static x10aux::itable_entry _itables[3];
            virtual x10aux::itable_entry* _getITables() { return _itables; }
    
            static x10aux::ref<x10::lang::Iterator<T> >_itable_thunk_iterable(x10aux::ref<Iterable<T> > this_) {
                x10aux::ref<x10::lang::ValRail<T> > tmp = this_;
                return tmp->iterator();
            }

            static T _itable_thunk_fun(x10aux::ref<Fun_0_1<x10_int, T> > this_, x10_int arg0) {
                x10aux::ref<x10::lang::ValRail<T> > tmp = this_;
                return tmp->apply(arg0); // TODO: handline apply here?
            }
            
            
            private:

            ValRail(const ValRail<T>& arr); // disabled

            public:

            // 32 bit array indexes
            const x10_int FMGL(length);

            // The ValRail's data.
            // As a locality optimization, we are going to allocate all of the storage for the
            // Rail object and its data array contiguously (ie, in a single allocate call),
            // but to avoid making assumptions about the C++ object model, we will always
            // access it via this pointer instead of using the data[1] "struct hack."
            // This may cost us an extra load instruction (but no extra cache misses).
            // By declaring the pointer const, we should enable the C++ compiler to be reasonably
            // effective at hoisting this extra load out of loop nests.
            T* const _data;

            ValRail() : FMGL(length)(0),  _data(NULL) { }
            ValRail(x10_int length_, T* storage) : FMGL(length)(length_),  _data(storage) {}

            GPUSAFE T apply(x10_int index) {
                return operator[](index);
            }   

            GPUSAFE T& operator[](x10_int index) {
                x10aux::checkRailBounds(index, FMGL(length));
                return _data[index];
            }
      
            T* raw() { return _data; }

            class RailIterator : public Ref, public virtual x10::lang::Iterator<T> {

                protected:

                x10_int i;
                x10aux::ref<ValRail<T> > rail;

                public:
                static const x10aux::RuntimeType* rtt;
                static const x10aux::RuntimeType* getRTT() { return NULL == rtt ? _initRTT() : rtt; }
                static const x10aux::RuntimeType* _initRTT();
                virtual const x10aux::RuntimeType *_type() const { return getRTT(); }

                RailIterator (const x10aux::ref<ValRail> &rail_)
                    : i(0), rail(rail_) { }

                virtual x10_boolean hasNext() {
                    return i < rail->FMGL(length);
                }

                virtual T next() {
                    return (*rail)[i++];
                }

                virtual x10_int hashCode() { return 0; }

                virtual x10aux::ref<String> toString() {
                    return new (x10aux::alloc<String>()) String();
                }

                virtual x10_boolean equals(x10aux::ref<Ref> other) {
                    if (!x10aux::concrete_instanceof<RailIterator>(other)) return false;
                    x10aux::ref<RailIterator> other_i = other;
                    if (other_i->rail != rail) return false;
                    if (other_i->i != i) return false;
                    return true;
                }

                virtual x10_boolean equals(x10aux::ref<Value> other) {
                    return this->Ref::equals(other);
                }

            };

            virtual x10aux::ref<x10::lang::Iterator<T> > iterator() {
                return new (x10aux::alloc<RailIterator>()) RailIterator(this);
            }

            virtual x10_boolean _struct_equals(x10aux::ref<Object> other);

            virtual x10_int hashCode() { return 0; }

            virtual x10aux::ref<x10::lang::String> toString() { return x10aux::railToString<T,ValRail<T> >(this); }

            static x10aux::ref<ValRail<T> > make(x10_int length);

            static x10aux::ref<ValRail<T> > make(x10_int length,
                                                 x10aux::ref<Fun_0_1<x10_int,T> > init );

            static x10aux::ref<ValRail<T> > make(x10aux::ref<Rail<T> > other);

            static const x10aux::serialization_id_t _serialization_id;

            static void _serialize(x10aux::ref<ValRail<T> > this_,
                                   x10aux::serialization_buffer &buf,
                                   x10aux::addr_map &m);
            void _serialize_id(x10aux::serialization_buffer &buf, x10aux::addr_map &m) {
                buf.write(_serialization_id, m);
            }
            void _serialize_body(x10aux::serialization_buffer &buf, x10aux::addr_map &m);
            template<class S> static x10aux::ref<S> _deserialize(x10aux::serialization_buffer &buf);
        };

        template<class T> const x10aux::serialization_id_t ValRail<T>::_serialization_id =
            x10aux::DeserializationDispatcher
                ::addDeserializer(ValRail<T>::template _deserialize<Object>);

        template<class T> const x10aux::RuntimeType* ValRail<T>::_initRTT() {
            return x10::lang::_initRTTHelper_ValRail(&rtt, x10aux::getRTT<T>(), x10aux::getRTT<Fun_0_1<x10_int,T> >(),
                                                     x10aux::getRTT<Iterable<T> >());
        }

        template<class T> const x10aux::RuntimeType* ValRail<T>::RailIterator::_initRTT() {
            return x10::lang::_initRTTHelper_ValRailIterator(&rtt, x10aux::getRTT<T>(), x10aux::getRTT<x10::lang::Iterator<T> >());
        }

        template<class T> const x10aux::RuntimeType* ValRail<T>::rtt = NULL;

        template <class T> x10aux::itable_entry ValRail<T>::_itables[3] = {
            x10aux::itable_entry(&Iterable<T>::rtt,
                                 new typename x10::lang::Iterable<T>::itable(&ValRail<T>::_itable_thunk_iterable)),
            x10aux::itable_entry(&Fun_0_1<x10_int, T>::rtt,
                                 new typename x10::lang::Fun_0_1<x10_int, T>::itable(&ValRail<T>::_itable_thunk_fun)),
            x10aux::itable_entry(NULL, NULL)
        };

        template<class T> const x10aux::RuntimeType* ValRail<T>::RailIterator::rtt = NULL;

        template <class T> x10_boolean ValRail<T>::_struct_equals(x10aux::ref<Object> other) {
            if (other.get() == this) return true; // short-circuit trivial equality
            if (!this->Value::_struct_equals(other)) return false;
            x10aux::ref<ValRail> other_rail = other;
            // different sizes so false
            if (other_rail->FMGL(length) != this->FMGL(length)) return false;
            if (x10aux::getRTT<T>()->subtypeOf(x10aux::getRTT<Value>())) {
                // Value type; structurally compare elements
                for (x10_int i = 0; i < this->FMGL(length); ++i)
                    if (!x10aux::struct_equals((*other_rail)[i], this->raw()[i]))
                        return false;
            } else {
                // Ref type; simple reference equality
                for (x10_int i = 0; i < this->FMGL(length); ++i)
                    if (!x10aux::struct_equals((*other_rail)[i],this->raw()[i]))
                        return false;
            }
            return true;
        }

        template <class T> x10aux::ref<ValRail<T> > ValRail<T>::make(x10_int length) {
            x10aux::ref<ValRail<T> > rail = x10aux::alloc_rail<T,ValRail<T> >(length);
            for (x10_int i=0 ; i<length ; ++i) {
                // Initialise to zero, which should work for
                // numeric types and x10aux:;ref<T> which I think
                // covers everything.
                (*rail)[i] = 0;
            }
            return rail;
        }

        template <class T> x10aux::ref<ValRail<T> > ValRail<T>::make(x10_int length,
                                                                     x10aux::ref<Fun_0_1<x10_int,T> > init ) {
            x10aux::ref<ValRail<T> > rail = x10aux::alloc_rail<T,ValRail<T> >(length);
            typename Fun_0_1<x10_int,T>::itable *it = x10aux::findITable<Fun_0_1<x10_int,T> >(init);
            for (x10_int i=0 ; i<length ; ++i) {
                (*rail)[i] = it->apply(init, i);
            }
            return rail;
        }

        template <class T> x10aux::ref<ValRail<T> > ValRail<T>::make(x10aux::ref<Rail<T> > other) {
            x10_int length = other->FMGL(length);
            x10aux::ref<ValRail<T> > rail = x10aux::alloc_rail<T,ValRail<T> >(length);
            for (x10_int i=0 ; i<length ; ++i) {
                (*rail)[i] = (*other)[i];
            }
            return rail;
        }

        template <class T> void ValRail<T>::_serialize(x10aux::ref<ValRail<T> > this_,
                                                       x10aux::serialization_buffer &buf,
                                                       x10aux::addr_map &m) {
            if (this_ == x10aux::null) {
                ValRail<T> v;
                v._serialize_body(buf, m);
            } else {
                this_->_serialize_body(buf, m);
            }
        }

        template <class T> void ValRail<T>::_serialize_body(x10aux::serialization_buffer &buf, x10aux::addr_map &m) {
            buf.write(this->FMGL(length),m);
            for (x10_int i=0 ; i<this->FMGL(length) ; ++i) {
                buf.write(this->raw()[i], m); // avoid bounds check
            }
        }

        template <class T> template<class S> x10aux::ref<S> ValRail<T>::_deserialize(x10aux::serialization_buffer &buf) {
            x10_int length = buf.read<x10_int>();
            x10aux::ref<ValRail> this_ = x10aux::alloc_rail<T,ValRail<T> >(length);
            for (x10_int i=0 ; i<length ; ++i) {
                this_->raw()[i] = buf.read<T>(); // avoid bounds check
            }
            return this_;
        }
    }
}


#endif
// vim:tabstop=4:shiftwidth=4:expandtab
