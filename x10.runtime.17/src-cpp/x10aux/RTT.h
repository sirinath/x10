#ifndef X10AUX_RTT_H
#define X10AUX_RTT_H

#include <x10aux/config.h>
#include <x10aux/alloc.h>

#include <pthread.h>

/* Macro to use in class declaration for boilerplace RTT junk */
#define RTT_H_DECLS \
    static const x10aux::RuntimeType* rtt; \
    static const x10aux::RuntimeType* getRTT() { return NULL == rtt ? _initRTT() : rtt; } \
    static const x10aux::RuntimeType* _initRTT(); \
    virtual const x10aux::RuntimeType *_type() const { return getRTT(); }

#define RTT_CC_DECLS1(TYPE,NAME,P1) \
    const x10aux::RuntimeType* TYPE::rtt = NULL; \
    const x10aux::RuntimeType * TYPE::_initRTT() { \
        const x10aux::RuntimeType *cand = new (x10aux::alloc<x10aux::RuntimeType >()) x10aux::RuntimeType(NAME, 1, P1::getRTT()); \
        return x10aux::RuntimeType::installRTT(&rtt, cand); \
    }

namespace x10 {
    namespace lang {
        class Object;
    }
}

namespace x10aux {

    template<class T> class ref;

    class RuntimeType {
    private:
        static pthread_mutex_t installLock;
        static pthread_mutexattr_t installLockAttr;
        
    public:
        /*
         * RTT objects for all builtin primitive types.
         * These are created by the bootstrap method
         */
        static const RuntimeType* BooleanType;
        static const RuntimeType* ByteType;
        static const RuntimeType* CharType;
        static const RuntimeType* ShortType;
        static const RuntimeType* IntType;
        static const RuntimeType* FloatType;
        static const RuntimeType* LongType;
        static const RuntimeType* DoubleType;

        /**
         * RTT object for x10::lang::Object
         * Created by the bootstrap method because it
         * is needed as the parent object for the primitive RTT's
         */
        static const RuntimeType* ObjectType;
        
    public:
        const int parentsc;
        const RuntimeType **parents;
        const char* typeName;
        
        RuntimeType(const char* n, int pc, ...);

        const char *name() const { return typeName; }

        bool subtypeOf(const RuntimeType * const other) const;

        // use "const ref<t> &" here to break circular dependency
        bool instanceOf(const x10aux::ref<x10::lang::Object> &other) const;

        // use "const ref<t> &" here to break circular dependency
        bool concreteInstanceOf(const x10aux::ref<x10::lang::Object> &other) const;

        bool equals(const RuntimeType * const other) const {
            return other == this;
        }

        static const RuntimeType* installRTT(const RuntimeType **location, const RuntimeType *rtt);
        static void bootstrap();
    };


    // this is the function we use to get runtime types from types
    template<class T> const x10aux::RuntimeType* getRTT() {
        return T::getRTT();
    }
    // specializations of getRTT template for primitive types
    template<> const x10aux::RuntimeType *getRTT<x10_boolean>();
    template<> const x10aux::RuntimeType *getRTT<x10_byte>();
    template<> const x10aux::RuntimeType *getRTT<x10_short>();
    template<> const x10aux::RuntimeType *getRTT<x10_char>();
    template<> const x10aux::RuntimeType *getRTT<x10_int>();
    template<> const x10aux::RuntimeType *getRTT<x10_float>();
    template<> const x10aux::RuntimeType *getRTT<x10_long>();
    template<> const x10aux::RuntimeType *getRTT<x10_double>();
    
    // This is different to getRTT because it distinguishes between T and ref<T>
    template<class T> struct TypeName { static const char *_() {
        const RuntimeType *t = getRTT<T>();
        if (t == NULL) return "Uninitialized RTT";
        return t->name();
    } };

    template<class T> struct TypeName<ref<T> > { static const char *_() {
        const RuntimeType *t = getRTT<T>();
        if (t == NULL) return "Uninitialized RTT";
        static const char *with_star = alloc_printf("%s*",t->name());
        return with_star;
    } };

    template<class T> const char *typeName() {
        return TypeName<T>::_();
    }

    #define TYPENAME(T) x10aux::typeName<T>()
    class place;
    template<> inline const char *typeName<place>() { return "place"; }
    template<> inline const char *typeName<x10_remote_ref_t>() { return "x10_remote_ref_t"; }
    class InitDispatcher;
    template<> inline const char *typeName<InitDispatcher>() { return "InitDispatcher"; }
    template<> inline const char *typeName<void (*)()>() { return "void (*)()"; }
    template<> inline const char *typeName<const void*>() { return "const void *"; }
    template<> inline const char *typeName<char>() { return "char"; }
    template<> inline const char *typeName<const RuntimeType*>() { return "const RuntimeType *"; }

    template<class T> inline x10_boolean instanceof(const x10aux::ref<x10::lang::Object> &v) {
        return x10aux::getRTT<T>()->instanceOf(v);
    }

    template<class T> inline x10_boolean concrete_instanceof(const x10aux::ref<x10::lang::Object> &v) {
        return x10aux::getRTT<T>()->concreteInstanceOf(v);
    }

    template<class T1,class T2> inline x10_boolean subtypeof() {
        return x10aux::getRTT<T1>()->subtypeOf(x10aux::getRTT<T2>());
    }

    template<class T1,class T2> inline x10_boolean sametype() {
        return x10aux::getRTT<T1>()->equals(x10aux::getRTT<T2>());
    }

}

#endif
// vim:tabstop=4:shiftwidth=4:expandtab
