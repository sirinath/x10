#ifndef X10_LANG_RAIL_H
#define X10_LANG_RAIL_H


#include <x10aux/config.h>
#include <x10aux/alloc.h>
#include <x10aux/RTT.h>
#include <x10aux/rail_utils.h>

#include <x10/lang/Ref.h>
#include <x10/lang/Iterable.h>
#include <x10/lang/Settable.h>
#include <x10/lang/RailIterator.h>
#include <x10/lang/ValRail.h>

namespace x10 {

    namespace lang {

        void _initRTTHelper_Rail(x10aux::RuntimeType *location, const x10aux::RuntimeType *element,
                                 const x10aux::RuntimeType *p1, const x10aux::RuntimeType *p2);

        template<class P1, class R> class Fun_0_1;

        template<class T> class Rail : public Ref {
            public:
            RTT_H_DECLS

            static typename Iterable<T>::template itable<Rail<T> > _itable_iterable;
            static typename Settable<x10_int, T>::template itable<Rail<T> > _itable_settable;
            static x10aux::itable_entry _itables[3];
            virtual x10aux::itable_entry* _getITables() { return _itables; }

            private:

            Rail(const Rail<T>& arr); // disabled

            public:
            // 32 bit array indexes
            const x10_int FMGL(length);

            // The Rail's data.
            // As a locality optimization, we are going to allocate all of the storage for the
            // Rail object and its data array contiguously (ie, in a single allocate call),
            // but to avoid making assumptions about the C++ object model, we will always
            // access it via this pointer instead of using the data[1] "struct hack."
            // This may cost us an extra load instruction (but no extra cache misses).
            // By declaring the pointer const, we should enable the C++ compiler to be reasonably
            // effective at hoisting this extra load out of loop nests.
            T* const _data;
            
            Rail(x10_int length_, T* storage) : FMGL(length)(length_),  _data(storage) { }

            GPUSAFE virtual T set(T v, x10_int index) { 
                return (*this)[index] = v; 
            } 

            GPUSAFE T apply(x10_int index) {
                return operator[](index);
            }   

            GPUSAFE T& operator[](x10_int index) {
                x10aux::checkRailBounds(index, FMGL(length));
                return _data[index];
            }
      
            T* raw() { return _data; }

            virtual x10aux::ref<Iterator<T> > iterator() {
                x10aux::ref<RailIterator<T> > tmp = new (x10aux::alloc<RailIterator<T> >()) RailIterator<T> (this->FMGL(length), this->raw());
                return tmp;
            }   

            static x10aux::ref<Rail<T> > make(x10_int length);
            static x10aux::ref<Rail<T> > make(x10_int length,
                                              x10aux::ref<Fun_0_1<x10_int,T> > init);
            static x10aux::ref<Rail<T> > make(x10aux::ref<ValRail<T> > other);

            virtual x10aux::ref<String> toString() { return x10aux::railToString<T,Rail<T> >(this); }
        };

        template<class T> x10aux::RuntimeType Rail<T>::rtt;

        template<class T> void Rail<T>::_initRTT() {
            rtt.typeName = "CYCLIC RTT INIT\n";
            x10::lang::_initRTTHelper_Rail(&rtt, x10aux::getRTT<T>(),
                                           x10aux::getRTT<Settable<x10_int,T> >(),
                                           x10aux::getRTT<Iterable<T> >());
        }

        template <class T> typename Iterable<T>::template itable<Rail<T> > Rail<T>::_itable_iterable(&Rail<T>::iterator);

        template <class T> typename Settable<x10_int, T>::template itable<Rail<T> > Rail<T>::_itable_settable(&Rail<T>::set);
        
        template <class T> x10aux::itable_entry x10::lang::Rail<T>::_itables[3] = {
            x10aux::itable_entry(&x10::lang::Iterable<T>::rtt, &x10::lang::Rail<T>::_itable_iterable),
            x10aux::itable_entry(&x10::lang::Settable<x10_int, T>::rtt, &x10::lang::Rail<T>::_itable_iterable),
            x10aux::itable_entry(NULL,  (void*)x10aux::getRTT<Rail<T> >())
        };

        template <class T> x10aux::ref<Rail<T> > Rail<T>::make(x10_int length) {
            x10aux::ref<Rail<T> > rail = x10aux::alloc_rail<T,Rail<T> >(length);
            for (x10_int i=0 ; i<length ; ++i) {
                // Initialise to zero, which should work for
                // numeric types and x10aux::ref<T> which I think
                // covers everything.
                (*rail)[i] = 0;
            }
            return rail;
        }

        template <class T> x10aux::ref<Rail<T> > Rail<T>::make(x10_int length,
                                                               x10aux::ref<Fun_0_1<x10_int,T> > init ) {
            x10aux::ref<Rail<T> > rail = x10aux::alloc_rail<T,Rail<T> >(length);
            x10aux::ref<x10::lang::Object> initAsObj = init;
            typename Fun_0_1<x10_int,T>::template itable<x10::lang::Object> *it = x10aux::findITable<Fun_0_1<x10_int,T> >(initAsObj->_getITables());
            for (x10_int i=0 ; i<length ; ++i) {
                (*rail)[i] = (initAsObj.get()->*(it->apply))(i);
            }
            return rail;
        }

        template <class T> x10aux::ref<Rail<T> > Rail<T>::make(x10aux::ref<ValRail<T> > other) {
            x10_int length = other->FMGL(length);
            x10aux::ref<Rail<T> > rail = x10aux::alloc_rail<T,Rail<T> >(length);
            for (x10_int i=0 ; i<length ; ++i) {
                (*rail)[i] = (*other)[i];
            }
            return rail;
        }
    }
}


#endif
// vim:tabstop=4:shiftwidth=4:expandtab
