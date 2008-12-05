#ifndef X10_UTIL_GROWABLE_RAIL_H
#define X10_UTIL_GROWABLE_RAIL_H

#include <sstream>

#include <x10aux/config.h>
#include <x10aux/alloc.h>
#include <x10aux/RTT.h>
#include <x10aux/rail_utils.h>
#include <x10aux/ref.h>

#include <x10/lang/Ref.h>
#include <x10/lang/Rail.h>

namespace x10 {

    namespace util {

        template<class T> class GrowableRail : public x10::lang::Ref {
        public:

            class RTT : public x10aux::RuntimeType {
                public:
                static RTT * it;

                virtual void init() { initParents(1,x10aux::getRTT<Ref>()); }

                virtual std::string name() const {
                    std::stringstream ss;
                    ss << "GrowableRail[" << x10aux::getRTT<T>()->name() << "]";
                    return ss.str();
                }
                 
            };

            virtual const x10aux::RuntimeType *_type() const {
                return x10aux::getRTT<GrowableRail<T> >();
            }

        private:
            x10aux::ref<x10::lang::Rail<T> > _array;
            x10_int _len;
            
        public:
            static x10aux::ref<GrowableRail<T> > _make() {
                return (new (x10aux::alloc<GrowableRail<T> >()) GrowableRail<T>())->_constructor();
            }
            static x10aux::ref<GrowableRail<T> > _make(x10_int sz) {
                return (new (x10aux::alloc<GrowableRail<T> >())GrowableRail<T>())->_constructor(sz);
            }
            x10aux::ref<GrowableRail> _constructor() {
                return this->_constructor(1);
            }
            x10aux::ref<GrowableRail> _constructor(x10_int size) {
                this->Ref::_constructor();
                _array = x10::lang::Rail<T>::make(size);
                _len = 0;
                return this;
            }

            T set(T v, x10_int i) {
                grow(i+1);
                return (*_array)[i] = v;
            }

            void add(T v) {
                grow(_len+1);
                (*_array)[_len] = v;
                _len++;
            }

            T apply(x10_int i) {
                assert(i>=0);
                assert(i<_len);

                return (*_array)[i];
            }

            void removeLast() {
                assert(_len > 0);
                
                (*_array)[_len-1] = (T)0;
                _len--;
                shrink(_len+1);
            }

            x10_int length() { return _len; }

            x10aux::ref<x10::lang::Rail<T> > toRail() {
                x10aux::ref<x10::lang::Rail<T> > ans = x10::lang::Rail<T>::make(_len);
                for (int i=0; i<_len; i++) {
                    (*ans)[i] = (*_array)[i];
                }
                return ans;
            }

            x10aux::ref<x10::lang::ValRail<T> > toValRail() {
                x10aux::ref<x10::lang::ValRail<T> > ans = x10::lang::ValRail<T>::make(_len);
                for (int i=0; i<_len; i++) {
                    (*ans)[i] = (*_array)[i];
                }
                return ans;
            }

        private:
            void grow(x10_int newSize) {
                x10_int oldStorage = size();

                if (newSize <= oldStorage) {
                    return;
                }
                if (newSize < oldStorage*2) {
                    newSize = oldStorage*2;
                }
                if (newSize < _len) {
                    newSize = _len;
                }
                if (newSize < 8) {
                    newSize = 8;
                }

                x10aux::ref<x10::lang::Rail<T> > tmp = x10::lang::Rail<T>::make(newSize);
                for (int i=0; i<_len; i++) {
                    (*tmp)[i] = (*_array)[i];
                }

                _array = tmp;
            }


            void shrink(x10_int newSize) {
                if (newSize > size()/2 || newSize < 8) {
                    return;
                }

                if (newSize < _len) {
                    newSize = _len;
                }
                if (newSize < 8) {
                    newSize = 8;
                }

                x10aux::ref<x10::lang::Rail<T> > tmp = x10::lang::Rail<T>::make(newSize);
                for (int i=0; i<_len; i++) {
                    (*tmp)[i] = (*_array)[i];
                }

                _array = tmp;
            }
                
            x10_int size() { return _array->FMGL(length); }
        };

        template<class T> typename GrowableRail<T>::RTT *GrowableRail<T>::RTT::it =
            new (x10aux::alloc<typename GrowableRail<T>::RTT>()) typename GrowableRail<T>::RTT();


    }
}


#endif /* X10_LANG_GROWABLE_RAIL */

// vim: shiftwidth=4:tabstop=4:expandtab
