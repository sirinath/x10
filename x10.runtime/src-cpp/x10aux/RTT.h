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

#ifndef X10AUX_RTT_H
#define X10AUX_RTT_H

#include <x10aux/config.h>
#include <assert.h>
#include <pthread.h>
#include <x10aux/lock.h>

/* Macro to use in class declaration for boilerplate RTT junk */
#define RTT_H_DECLS_CLASS \
    static x10aux::RuntimeType rtt;                                     \
    static const x10aux::RuntimeType* getRTT() { if (!rtt.isInitialized) _initRTT(); return &rtt; } \
    static void _initRTT(); \
    virtual const x10aux::RuntimeType *_type() const { return getRTT(); }

#define RTT_H_DECLS_STRUCT \
    static x10aux::RuntimeType rtt;                                     \
    static const x10aux::RuntimeType* getRTT() { if (!rtt.isInitialized) _initRTT(); return &rtt; } \
    static void _initRTT(); \

#define RTT_H_DECLS_INTERFACE \
    static x10aux::RuntimeType rtt; \
    static const x10aux::RuntimeType* getRTT() { if (!rtt.isInitialized) _initRTT(); return &rtt; } \
    static void _initRTT();

#define RTT_CC_DECLS1(TYPE,NAME,P1)                             \
    x10aux::RuntimeType TYPE::rtt;                              \
    void TYPE::_initRTT() {                                     \
        if (rtt.initStageOne(&rtt)) return;                     \
        const x10aux::RuntimeType* parents[1] = {P1::getRTT()}; \
        rtt.initStageTwo(NAME, 1, parents, 0, NULL, NULL);      \
    }

namespace x10 {
    namespace lang {
        class NullType;
        class Reference;
        class String;
    }
}

namespace x10aux {

    template<class T> class ref;

    class RuntimeType {
    private:
        static volatile x10aux::reentrant_lock *initRTTLock;
        
    public:
        /*
         * RTT objects for all builtin primitive types.
         */
        static RuntimeType BooleanType;
        static RuntimeType ByteType;
        static RuntimeType CharType;
        static RuntimeType ShortType;
        static RuntimeType IntType;
        static RuntimeType FloatType;
        static RuntimeType LongType;
        static RuntimeType DoubleType;
        static RuntimeType UByteType;
        static RuntimeType UShortType;
        static RuntimeType UIntType;
        static RuntimeType ULongType;

        enum Variance { covariant, contravariant, invariant };
        
    public:
        const RuntimeType *canonical;
        int parentsc;
        int paramsc;
        bool containsPtrs;
        bool isInitialized;
        const RuntimeType **parents;
        const RuntimeType **params;
        Variance *variances;
        const char* fullTypeName;
        const char* baseName;

        // Initialization protocol.
        // (a) Call initStageOne before attempting to acquire the RTT
        //     if any of the types parents or parameters.
        // (b) if initStageOne returns true, then the type is either
        //       (a) being recursively initialized by the same thread
        //       (b) has already been initialied by another thread
        //     In either case, return from initRTT because by the time
        //     we unwind all the way back to the "user" code, the recursive
        //     RTT cycle will have been fully initialized.
        // (c) call getRTT on any input RTTs
        // (d) call initStageTwo

        
        bool initStageOne(const RuntimeType* canonical_);
        
        void initStageTwo(const char* baseName_,
                          int parsentsc_, const RuntimeType** parents_,
                          int paramsc_, const RuntimeType** params_, Variance* variances_);

        virtual const char *name() const;

        bool subtypeOf(const RuntimeType * const other) const;

        // use "const ref<t> &" here to break circular dependency
        bool instanceOf(const x10aux::ref<x10::lang::Reference> &other) const;

        // use "const ref<t> &" here to break circular dependency
        bool concreteInstanceOf(const x10aux::ref<x10::lang::Reference> &other) const;

        bool equals(const RuntimeType * const other) const {
            return other == this;
        }

        // Can the X10 type this RTT instance represents contain pointers to other chunks of heap-allocated memory?
        // Used in conjunction with BDWGC to optimize allocation of non-pointer containing rails.
        // It is always safe to return true; returning false is used in places like rail allocation to select an
        // alternate allocation routine for memory that will not contain pointers (and therefore does not have to
        // be scanned for pointers during the GC). Returning false incorrectly can cause memory corruption by hiding
        // pointers from the GC's scanning logic. 
        bool containsPointers() const { return containsPtrs; }
        
        static void initBooleanType();
        static void initByteType();
        static void initCharType();
        static void initShortType();
        static void initIntType();
        static void initFloatType();
        static void initLongType();
        static void initDoubleType();
        static void initUByteType();
        static void initUShortType();
        static void initUIntType();
        static void initULongType();
    };

    class RuntimeFunType : public RuntimeType {
    public:
        virtual const char *name() const;
    };
    class RuntimeVoidFunType : public RuntimeType {
    public:
        virtual const char *name() const;
    };


    // this is the function we use to get runtime types from types
    template<class T> const x10aux::RuntimeType* getRTT() {
        return T::getRTT();
    }
    // specializations of getRTT template for primitive types
    template<> inline const x10aux::RuntimeType *getRTT<x10_boolean>() {
        if (!x10aux::RuntimeType::BooleanType.isInitialized) {
            x10aux::RuntimeType::initBooleanType();
        }
        return &x10aux::RuntimeType::BooleanType;
    }
    template<> inline const x10aux::RuntimeType *getRTT<x10_byte>() {
        if (!x10aux::RuntimeType::ByteType.isInitialized) {
            x10aux::RuntimeType::initByteType();
        }
        return &x10aux::RuntimeType::ByteType;
    }
    template<> inline const x10aux::RuntimeType *getRTT<x10_char>() {
        if (!x10aux::RuntimeType::CharType.isInitialized) {
            x10aux::RuntimeType::initCharType();
        }
        return &x10aux::RuntimeType::CharType;
    }
    template<> inline const x10aux::RuntimeType *getRTT<x10_short>() {
        if (!x10aux::RuntimeType::ShortType.isInitialized) {
            x10aux::RuntimeType::initShortType();
        }
        return &x10aux::RuntimeType::ShortType;
    }
    template<> inline const x10aux::RuntimeType *getRTT<x10_int>() {
        if (!x10aux::RuntimeType::IntType.isInitialized) {
            x10aux::RuntimeType::initIntType();
        }
        return &x10aux::RuntimeType::IntType;
    }
    template<> inline const x10aux::RuntimeType *getRTT<x10_float>() {
        if (!x10aux::RuntimeType::FloatType.isInitialized) {
            x10aux::RuntimeType::initFloatType();
        }
        return &x10aux::RuntimeType::FloatType;
    }
    template<> inline const x10aux::RuntimeType *getRTT<x10_long>() {
        if (!x10aux::RuntimeType::LongType.isInitialized) {
            x10aux::RuntimeType::initLongType();
        }
        return &x10aux::RuntimeType::LongType;
    }
    template<> inline const x10aux::RuntimeType *getRTT<x10_double>() {
        if (!x10aux::RuntimeType::DoubleType.isInitialized) {
            x10aux::RuntimeType::initDoubleType();
        }
        return &x10aux::RuntimeType::DoubleType;
    }
    template<> inline const x10aux::RuntimeType *getRTT<x10_ubyte>() {
        if (!x10aux::RuntimeType::UByteType.isInitialized) {
            x10aux::RuntimeType::initUByteType();
        }
        return &x10aux::RuntimeType::UByteType;
    }
    template<> inline const x10aux::RuntimeType *getRTT<x10_ushort>() {
        if (!x10aux::RuntimeType::UShortType.isInitialized) {
            x10aux::RuntimeType::initUShortType();
        }
        return &x10aux::RuntimeType::UShortType;
    }
    template<> inline const x10aux::RuntimeType *getRTT<x10_uint>() {
        if (!x10aux::RuntimeType::UIntType.isInitialized) {
            x10aux::RuntimeType::initUIntType();
        }
        return &x10aux::RuntimeType::UIntType;
    }
    template<> inline const x10aux::RuntimeType *getRTT<x10_ulong>() {
        if (!x10aux::RuntimeType::ULongType.isInitialized) {
            x10aux::RuntimeType::initULongType();
        }
        return &x10aux::RuntimeType::ULongType;
    }

    // This is different to getRTT because it distinguishes between T and ref<T>
    template<class T> struct TypeName { static const char *_() {
        const RuntimeType *t = getRTT<T>();
        if (NULL == t || !t->isInitialized) return "uninitialized RTT";
        assert(NULL != t);
        return t->name();
    } };

    template<class T> const char *typeName() {
        return TypeName<T>::_();
    }

    #define TYPENAME(T) x10aux::typeName<T>()
    class InitClosure;
    template<> inline const char *typeName<InitClosure>() { return "InitClosure"; }
    class InitDispatcher;
    template<> inline const char *typeName<InitDispatcher>() { return "InitDispatcher"; }
    class StaticInitClosure;
    template<> inline const char *typeName<StaticInitClosure>() { return "StaticInitClosure"; }
    class BootStrapClosure;
    template<> inline const char *typeName<BootStrapClosure>() { return "BootStrapClosure"; }
    class remote_ref;
    template<> inline const char *typeName<remote_ref>() { return "remote_ref"; }
    template<> inline const char *typeName<reentrant_lock>() { return "reentrant_lock"; }
    template<> inline const char *typeName<void (*)()>() { return "void (*)()"; }
    template<> inline const char *typeName<void*>() { return "void *"; }
    template<> inline const char *typeName<const void*>() { return "const void *"; }
    template<> inline const char *typeName<volatile void*>() { return "volatile void *"; }
    template<> inline const char *typeName<char>() { return "char"; }
    template<> inline const char *typeName<const RuntimeType*>() { return "const RuntimeType *"; }
    template<> inline const char *typeName<RuntimeType::Variance>() { return "Variance"; }
    template<> inline const char *typeName<x10::lang::Reference>() { return "interface"; }
    template<> inline const char *typeName<x10::lang::NullType>() { return "Null"; }
#ifndef NO_IOSTREAM
    template<> inline const char *typeName<std::stringstream>() { return "std::stringstream"; }
#endif
    
    template<class T, class S> struct Instanceof { static x10_boolean _(S v) {
        // NOTE: This is only correct because X10 doesn't allow subtyping of structs.
        //       If a future version of X10 allows struct subtyping, then we would
        //       have to have template specializations for all of the C primitive types
        //       and call a method on non-C primitive structs (which would then have to have
        //       C++-level vtables or pointers to RuntimeType*) to get the runtime RTT object for v.
        return x10aux::getRTT<S>()->subtypeOf(x10aux::getRTT<T>());
    } };
    template<class T> struct Instanceof<T, T> { static x10_boolean _(T v) {
        return true;
    } };
    template<class T, class S> struct Instanceof<T, x10aux::ref<S> > {
        static x10_boolean _(x10aux::ref<S> v) {
            return x10aux::getRTT<T>()->instanceOf(v);
        }
    };
    // Have to specialize again to disambiguate
    template<class T> struct Instanceof<x10aux::ref<T>, x10aux::ref<T> > {
        static x10_boolean _(x10aux::ref<T> v) {
            return x10aux::getRTT<x10aux::ref<T> >()->instanceOf(v);
        }
    };

    template<class T, class S> inline x10_boolean instanceof(S v) {
        return x10aux::Instanceof<T, S>::_(v);
    }

    template<class T, class S> struct ConcreteInstanceof { static x10_boolean _(S v) {
        return false;
    } };
    template<class T> struct ConcreteInstanceof<T, T> { static x10_boolean _(T v) {
        return true;
    } };
    template<class T, class S> struct ConcreteInstanceof<T, x10aux::ref<S> > {
        static x10_boolean _(x10aux::ref<S> v) {
            return x10aux::getRTT<T>()->concreteInstanceOf(v);
        }
    };
    // Have to specialize again to disambiguate
    template<class T> struct ConcreteInstanceof<x10aux::ref<T>, x10aux::ref<T> > {
        static x10_boolean _(x10aux::ref<T> v) {
            return x10aux::getRTT<x10aux::ref<T> >()->concreteInstanceOf(v);
        }
    };

    template<class T, class S> inline x10_boolean concrete_instanceof(S v) {
        return x10aux::ConcreteInstanceof<T, S>::_(v);
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
