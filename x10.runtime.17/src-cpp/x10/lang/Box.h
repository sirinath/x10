#ifndef X10_LANG_BOX_H
#define X10_LANG_BOX_H

#include <sstream>


#include <x10aux/config.h>
#include <x10aux/RTT.h>


#include <x10/lang/Ref.h>
#include <x10/lang/String.h>


namespace x10 {

    namespace lang {

        template<class T> class Box : public Ref {

            public:

            class RTT : public x10aux::RuntimeType {
                public:
                static RTT *it;

                virtual void init() { initParents(1,x10aux::getRTT<Ref>()); }

                virtual std::string name() const {
                    std::stringstream ss;
                    ss<<"x10.lang.Box["<<x10aux::getRTT<T>()->name()<<"]";
                    return ss.str();
                }
                 
            };

            virtual const x10aux::RuntimeType *_type() const {
                return x10aux::getRTT<Box<T> >();
            }

            static x10aux::ref<Box<T> > _make(T contents_) {
                return (new (x10aux::alloc<Box<T> >())Box<T>())->_constructor(contents_);
            }

            x10aux::ref<Box<T> > _constructor(T contents_) {
                contents = contents_;
                return this;
            }

            virtual T get() {
                return contents;
            }

            virtual x10aux::ref<String> toString() {
                 return String::Lit("")+contents;
            }

            protected:

            T contents;

        };

        template<class T> typename Box<T>::RTT *Box<T>::RTT::it =
            new (x10aux::alloc<typename Box<T>::RTT>()) typename Box<T>::RTT();

    }
}


#endif
// vim:tabstop=4:shiftwidth=4:expandtab
