#ifndef X10REF_H
#define X10REF_H

#include <cstdlib>
#include <cassert>

#include <x10aux/config.h>
#include <x10aux/RTT.h>

namespace x10 { namespace lang { class Object; } }

namespace x10aux {

    struct remote_ref {
        static bool is_remote (void *ref) { return ((size_t)ref) & 1; }
        static remote_ref *strip (void *ref) { return (remote_ref*)(((size_t)ref) & ~1); }
        static void *mask (remote_ref *ref) { return (void*)(((size_t)ref) | 1); }

        x10_int loc;
        x10_ulong addr;

        // take a (possibly masked) pointer and provide a remote_ref struct for serialisation
        static remote_ref make (void *ptr);

        // take a remote_ref struct (presumably from the wire) and create a local representation
        static void *take (remote_ref r);
    };

    #ifndef NO_IOSTREAM
    inline std::ostream &operator<<(std::ostream &o, const remote_ref &rr) {
        return o << "rr("<<rr.addr<<"@"<<rr.loc<<")";
    }
    #endif

    void throwNPE() X10_PRAGMA_NORETURN;

    void throwBPE() X10_PRAGMA_NORETURN;

    class __ref {
        protected:
        #ifndef REF_STRIP_TYPE
        GPUSAFE __ref(void* = NULL) { }
        #else
        GPUSAFE __ref(void* val = NULL) : _val(val) { }
        public: // [IP] temporary
        void* _val;
        #endif
    };

    #ifndef REF_STRIP_TYPE
    #  define REF_INIT(v) __ref(), _val(v)
    #else
    #  define REF_INIT(v) __ref(v)
    #endif

    template<class T> class ref : public __ref {
        public:
        static const x10aux::RuntimeType* getRTT() { return T::getRTT(); }
        
        GPUSAFE ~ref() { }

        // Copy between refs of the same type
        GPUSAFE ref(const ref<T>& _ref) : REF_INIT(_ref._val) {
            _R_("Copying reference " << &_ref << "(" << _ref._val
                                     << ") of type " << TYPENAME(T)
                                     << " to " << this);
        }

        // Copy between refs of the same type
        // FIXME: something is wrong with the return value;
        // r1 = r2 = r3 doesn't work in xlC
        GPUSAFE const ref<T>& operator=(const ref<T>& _ref) {
            _val = _ref._val;
            _R_("Assigning reference " << &_ref << "(" << _ref._val
                                       << ") of type " << TYPENAME(T)
                                       << " to " << this);
            return *this;
        }

        // This is the big one -- turns a pointer into a ref
        // currently an implicit conversion
        GPUSAFE ref(T* const val = NULL) : REF_INIT(val) {
        }

        // Allow explicit casting between ref<S> and ref<T> dynamic_cast is
        // needed not to check for bad casts but to allow casting in the
        // context of virtual multiple inheritance.  Bad casts should never
        // happen, as the only places this operation is used are: class_cast
        // (which is guarded by a check) and upcasts from x10 code all operator
        // T are implicit conversions, this is no exception
/*
        template<class S> operator ref<S>() const {
            // (T*) cast needed when REF_STRIP_TYPE defined, otherwise harmless
            ref<S> _ref(dynamic_cast<S*>((T*)_val));
            _R_("Casting reference " << this << "(" << _val
                                     << ") of type " << TYPENAME(T)
                                     << " to type " << TYPENAME(S)
                                     << " into " << &_ref);
            return _ref;
        }
*/


        // Allow the construction of a ref<T> from a ref<S>
        template<class S> GPUSAFE ref(const ref<S>& _ref)
            // (S*) cast needed when REF_STRIP_TYPE defined, otherwise harmless
          : REF_INIT(dynamic_cast<T*>((S*)_ref._val)) {
            _R_("Casting reference " << &_ref << "(" << _ref._val
                                     << ") of type " << TYPENAME(S)
                                     << " to type " << TYPENAME(T)
                                     << " into " << this << "("<<_val<<")");
            // assert that the above dynamic_cast was successful
            assert(isNull() == _ref.isNull() && "Invalid c++ cast");
        }

        // Allow the assignment of a ref<S> to a ref<T>
        template<class S> GPUSAFE const ref<T> &operator=(const ref<S>& _ref) {
            // (S*) cast needed when REF_STRIP_TYPE defined, otherwise harmless
            _val = dynamic_cast<T*>((S*)_ref._val);
            _R_("Casting reference " << &_ref << "(" << _ref._val
                                     << ") of type " << TYPENAME(S)
                                     << " to type " << TYPENAME(T)
                                     << " into " << this << "("<<_val<<")");
            // assert that the above dynamic_cast was successful
            assert(isNull() == _ref.isNull() && "Invalid c++ cast");
            return *this;
        }

        inline void assertNonNull() const {
            #ifndef NO_EXCEPTIONS
            if (isNull()) throwNPE();
            #endif
        }

        inline void assertLocal() const {
            #ifndef NO_EXCEPTIONS
            if (remote_ref::is_remote(_val)) throwBPE();
            #endif
        }

        T& GPUSAFE operator*() const {
            _R_("Accessing object (*) via reference " << this << "(" << _val
                                      << ") of type " << TYPENAME(T));
            #ifndef NO_NULL_CHECKS
            assertNonNull();
            #endif
            #ifndef NO_PLACE_CHECKS
            assertLocal();
            #endif
            return *(T*)_val;
        }

        T* GPUSAFE get() const { 
            return (T*)_val;
        }

        T* GPUSAFE operator->() const { 
            _R_("Accessing object (*) via reference " << this << "(" << _val
                                      << ") of type " << TYPENAME(T));
            #ifndef NO_NULL_CHECKS
            assertNonNull();
            #endif
            #ifndef NO_PLACE_CHECKS
            assertLocal();
            #endif
            return (T*)_val;
        }

        bool GPUSAFE isNull() const {
            _R_("Nullcheck reference " << this << "(" << _val
                                      << ") of type " << TYPENAME(T));
            return _val == NULL;
        }

        // trivial operations that compare the contents of the two refs
        bool operator==(const ref<T>& _ref) const { return _val == _ref._val; }
        bool operator!=(const ref<T>& _ref) const { return _val != _ref._val; }


#ifndef REF_STRIP_TYPE
        T* _val;
#endif

    };

#ifndef NO_IOSTREAM
    template<class T> std::ostream& operator<<(std::ostream& o, ref<T> s) {
        if (s.isNull()) {
            o << "null";
        } else {
            o << *s;
        }
        return o;
    }
#endif

    // will be initialised to null
    typedef ref<x10::lang::Object> NullType;
    static NullType null;

    template<class F, class T> bool operator!=(F f, T t) { return !(f == t); }
    // comparison of a primitive with a ref
    template<class T> bool operator==(x10_boolean b, const ref<T>& _ref) { return false; }
    template<class T> bool operator==(x10_byte b, const ref<T>& _ref) { return false; }
    template<class T> bool operator==(x10_char c, const ref<T>& _ref) { return false; }
    template<class T> bool operator==(x10_short s, const ref<T>& _ref) { return false; }
    template<class T> bool operator==(x10_int i, const ref<T>& _ref) { return false; }
    template<class T> bool operator==(x10_long l, const ref<T>& _ref) { return false; }
    template<class T> bool operator==(x10_float f, const ref<T>& _ref) { return false; }
    template<class T> bool operator==(x10_double d, const ref<T>& _ref) { return false; }
    template<class T> bool operator==(x10_ubyte b, const ref<T>& _ref) { return false; }
    template<class T> bool operator==(x10_ushort s, const ref<T>& _ref) { return false; }
    template<class T> bool operator==(x10_uint i, const ref<T>& _ref) { return false; }
    template<class T> bool operator==(x10_ulong l, const ref<T>& _ref) { return false; }
    // comparison of a ref with a primitive
    template<class T> bool operator==(const ref<T>& _ref, x10_boolean b) { return false; }
    template<class T> bool operator==(const ref<T>& _ref, x10_byte b) { return false; }
    template<class T> bool operator==(const ref<T>& _ref, x10_char c) { return false; }
    template<class T> bool operator==(const ref<T>& _ref, x10_short s) { return false; }
    template<class T> bool operator==(const ref<T>& _ref, x10_int i) { return false; }
    template<class T> bool operator==(const ref<T>& _ref, x10_long l) { return false; }
    template<class T> bool operator==(const ref<T>& _ref, x10_float f) { return false; }
    template<class T> bool operator==(const ref<T>& _ref, x10_double d) { return false; }
    template<class T> bool operator==(const ref<T>& _ref, x10_ubyte b) { return false; }
    template<class T> bool operator==(const ref<T>& _ref, x10_ushort s) { return false; }
    template<class T> bool operator==(const ref<T>& _ref, x10_uint i) { return false; }
    template<class T> bool operator==(const ref<T>& _ref, x10_ulong l) { return false; }

    x10_int location (void *ptr);

    template<class T> x10_int location (x10aux::ref<T> ptr) {
        return location(ptr.get());
    }

} //namespace x10


#endif

// vim:tabstop=4:shiftwidth=4:expandtab
