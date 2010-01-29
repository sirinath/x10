#include <x10aux/config.h>
#include <x10aux/RTT.h>
#include <x10aux/alloc.h>
#include <x10aux/atomic_ops.h>

#include <x10/lang/Reference.h>
#include <x10/lang/Lock__ReentrantLock.h>

#include <cstdarg>

using namespace x10aux;
using namespace x10::lang;

const char *RuntimeType::name() const {
    if (NULL == fullTypeName) {
        assert(paramsc > 0); // if paramssc == 0, then fullTypeName is set to baseName in initRTT();
        std::ostringstream ss;
        ss << baseName;
        ss << "[";
        for (int i=0; i<paramsc; i++) {
            if (i>0) ss << ", ";
            ss << params[i]->name();
        }
        ss << "]";
        const_cast<RuntimeType*>(this)->fullTypeName = ss.str().c_str();
    }
    
    return fullTypeName;
}

bool RuntimeType::subtypeOf(const RuntimeType * const other) const {
    // Checks to try to catch partially initialized RTT objects before we use them.
    assert(isInitialized);
    assert(other->isInitialized);

    if (equals(other)) return true; // trivial case

    // the NullType should be considered a subtype of any type that extends x10.lang.Object.
    if (equals(getRTT<x10::lang::NullType>())) {
        if (other->subtypeOf(getRTT<x10::lang::Object>())) return true;
    }
    
    if (paramsc > 0 && canonical->equals(other->canonical)) {
        // Different instances of the same generic type (since canonical is equal).
        // this->subtypeOf(other) will be true exactly when the type parameters
        // of have the subtyping relationship specified by the variances.
        assert(other->paramsc == paramsc);
        for (int i=0; i<paramsc; i++) {
            assert(other->variances[i] == variances[i]);
            assert(params[i] != NULL);
            assert(other->params[i] != NULL);
            switch(variances[i]) {
            case covariant:
                if (!params[i]->subtypeOf(other->params[i])) return false;
                break;
            case contravariant:
                if (!other->params[i]->subtypeOf(params[i])) return false;
                break;
            case invariant:
                if (!params[i]->equals(other->params[i])) return false;
                break;
            }
        }
        return true;
    }

    for (int i = 0; i < parentsc; ++i) {
        if (parents[i]->subtypeOf(other)) return true;
    }
    return false;
}

bool RuntimeType::instanceOf (const ref<Reference> &other) const {
    if (other.isNull())
        return false;
    return other->_type()->subtypeOf(this);
}

bool RuntimeType::concreteInstanceOf (const ref<Reference> &other) const {
    if (other.isNull())
        return false;
    return other->_type()->equals(this);
}

void RuntimeType::init(const RuntimeType *canonical_, const char* baseName_,
                       int parentsc_, const RuntimeType** parents_,
                       int paramsc_, const RuntimeType** params_,
                       Variance* variances_) {
    // Ensure that at most one thread is doing any RTT initialization at a time.
    // This is overkill, since many RTT's have nothing to do with each other and
    // RTT initialization is idempotent. However, we want to make sure that if
    // a thread asks for an RTT, it gets a fully initialized RTT before it starts
    // operating on it and we don't get a race with multiple threads partially
    // initializing an RTT.
    while (NULL == initRTTLock) {
        ref<x10::lang::Lock__ReentrantLock> tmpLock = x10::lang::Lock__ReentrantLock::_make();
        x10aux::atomic_ops::store_load_barrier();
        atomic_ops::compareAndSet_ptr((volatile void**)(&initRTTLock), NULL, tmpLock.operator->());
    }
    const_cast<x10::lang::Lock__ReentrantLock *>(initRTTLock)->lock();

    if (canonical != NULL) {
        if (isInitialized) return; // another thread finished the job while this thread was blocked on initRTTLock.
        // We should only get here if there is a cyclic intialization in progress.
        // We don't have a 100% foolproof way to be sure that is what is happening, so
        // just hope that is what is happening and return.
        const_cast<x10::lang::Lock__ReentrantLock *>(initRTTLock)->unlock();
        return;
    }
    
    canonical = canonical_;
    baseName = baseName_;
    parentsc = parentsc_;
    paramsc = paramsc_;
    containsPtrs = true; // TODO: Eventually the compiler should analyze structs and where possible set containsPtrs for their RTT's to false.
    if (parentsc > 0) {
        parents = alloc<const RuntimeType*>(parentsc * sizeof(const RuntimeType*));
        for (int i=0; i<parentsc; i++) {
            parents[i] = parents_[i];
        }
    } else {
        parents = NULL;
    }
    if (paramsc > 0) {
        params = alloc<const RuntimeType*>(paramsc * sizeof(const RuntimeType*));
        variances = alloc<Variance>(paramsc * sizeof(Variance));
        for (int i=0; i<paramsc; i++) {
            params[i] = params_[i];
            variances[i] = variances_[i];
        }
    } else {
        params = NULL;
        variances = NULL;
        fullTypeName = baseName;
    }

    x10aux::atomic_ops::store_load_barrier();
    isInitialized = true; // must come after the store_load_barrier
    const_cast<x10::lang::Lock__ReentrantLock *>(initRTTLock)->unlock();
}
    
void RuntimeType::initBooleanType() {
    BooleanType.init(&BooleanType, "x10.lang.Boolean", 0, NULL, 0, NULL, NULL);
    BooleanType.containsPtrs = false;
}
void RuntimeType::initByteType() {
    ByteType.init(&ByteType, "x10.lang.Byte", 0, NULL, 0, NULL, NULL);
    ByteType.containsPtrs = false;
}
void RuntimeType::initCharType() {
    CharType.init(&CharType, "x10.lang.Char", 0, NULL, 0, NULL, NULL);
    CharType.containsPtrs = false;
}
void RuntimeType::initShortType() {
    ShortType.init(&ShortType, "x10.lang.Short", 0, NULL, 0, NULL, NULL);
    ShortType.containsPtrs = false;
}
void RuntimeType::initIntType() {
    IntType.init(&IntType, "x10.lang.Int", 0, NULL, 0, NULL, NULL);
    IntType.containsPtrs = false;
}
void RuntimeType::initFloatType() {
    FloatType.init(&FloatType, "x10.lang.Float", 0, NULL, 0, NULL, NULL);
    FloatType.containsPtrs = false;
}
void RuntimeType::initLongType() {
    LongType.init(&LongType, "x10.lang.Long", 0, NULL, 0, NULL, NULL);
    LongType.containsPtrs = false;
}
void RuntimeType::initDoubleType() {
    DoubleType.init(&DoubleType, "x10.lang.Double", 0, NULL, 0, NULL, NULL);
    DoubleType.containsPtrs = false;
}
void RuntimeType::initUByteType() {
    UByteType.init(&UByteType, "x10.lang.UByte", 0, NULL, 0, NULL, NULL);
    UByteType.containsPtrs = false;
}
void RuntimeType::initUShortType() {
    UShortType.init(&UShortType, "x10.lang.UShort", 0, NULL, 0, NULL, NULL);
    UShortType.containsPtrs = false;
}
void RuntimeType::initUIntType() {
    UIntType.init(&UIntType, "x10.lang.UInt", 0, NULL, 0, NULL, NULL);
    UIntType.containsPtrs = false;
}
void RuntimeType::initULongType() {
    ULongType.init(&ULongType, "x10.lang.ULong", 0, NULL, 0, NULL, NULL);
    ULongType.containsPtrs = false;
}

RuntimeType RuntimeType::BooleanType;
RuntimeType RuntimeType::ByteType;
RuntimeType RuntimeType::CharType;
RuntimeType RuntimeType::ShortType;
RuntimeType RuntimeType::IntType;
RuntimeType RuntimeType::FloatType;
RuntimeType RuntimeType::LongType;
RuntimeType RuntimeType::DoubleType;
RuntimeType RuntimeType::UByteType;
RuntimeType RuntimeType::UShortType;
RuntimeType RuntimeType::UIntType;
RuntimeType RuntimeType::ULongType;

volatile x10::lang::Lock__ReentrantLock* RuntimeType::initRTTLock;

// vim:tabstop=4:shiftwidth=4:expandtab
