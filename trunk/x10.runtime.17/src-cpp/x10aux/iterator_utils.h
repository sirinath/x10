#ifndef X10AUX_ITERATOR_UTILS_H
#define X10AUX_ITERATOR_UTILS_H

#include <x10aux/config.h>

#include <x10/lang/Iterator.h>

namespace x10aux {
    template<class T, class F> class IteratorAdapter : public x10::lang::Iterator<T> {
        x10::lang::Iterator<F>* _from;
    public:
        IteratorAdapter(ref<x10::lang::Iterator<F> > from) : _from(from) { }

        x10::lang::Iterator<F>* operator->() { return _from; }

        x10_boolean hasNext() { return _from->hasNext(); }

        T next() { return (T)(_from->next()); }

        x10_boolean equals(ref<x10::lang::Object> o) { return _from->equals(o); }

        x10_int hashCode() { return _from->hashCode(); }

        ref<x10::lang::String> toString() { return _from->toString(); }
    };

    template<class T, class F> struct _convert_iterator {
        static ref<x10::lang::Iterator<T> > _(ref<x10::lang::Iterator<F> > _from) {
            return (ref<x10::lang::Iterator<T> >)
                (new (alloc<IteratorAdapter<T, F> >())
                    IteratorAdapter<T, F>(_from.operator->()));
        }
    };

    // Specialize for same type (to catch errors)
    template<class T> struct _convert_iterator<T,T> { };

    template<class T, class F>
    ref<x10::lang::Iterator<T> > convert_iterator(ref<x10::lang::Iterator<F> > from) {
        return _convert_iterator<T,F>::_(from);
    }
}

#endif
// vim:tabstop=4:shiftwidth=4:expandtab

