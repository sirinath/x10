#include <x10aux/reference_logger.h>

#include <x10/lang/Lock.h>

using namespace x10::lang;
using namespace x10aux;

#if defined(X10_USE_BDWGC) || defined(X10_DEBUG_REFERENCE_LOGGER)

#define NUM_BUCKETS 4096
#define ADDR_SHIFT 7

#define GATHER_STATS 1

ReferenceLogger *x10aux::ReferenceLogger::it = new (x10aux::alloc<ReferenceLogger>()) ReferenceLogger();

ReferenceLogger::ReferenceLogger() {
    _lock = Lock::_make();
    _buckets = x10aux::alloc<Bucket*>(NUM_BUCKETS*sizeof(Bucket*));
    memset(_buckets, 0, NUM_BUCKETS*sizeof(Bucket*));
}

void ReferenceLogger::log_(void *x) {

    // Critical section guarded by lock:
    // Lookup x in hashmap.
    //   If found; done.
    //   If not found, allocate a new bucket to contain x and add it.
    _lock->lock();

    // Hash function: throw out low bits and mod by NUM_BUCKETS.
    int bucket = (((size_t)(x)) >> ADDR_SHIFT) % NUM_BUCKETS;
    Bucket *cur = _buckets[bucket];
    while (cur != NULL) {
        if (cur->_reference == x) {
            _lock->unlock();
            return;
        }
        cur = cur->_next;
    }
    Bucket *newBucket = x10aux::alloc<Bucket>();
    newBucket->_reference = x;
    newBucket->_next = _buckets[bucket];
    _buckets[bucket] = newBucket;

    _lock->unlock();
}
#endif /* X10_USE_BDWGC */
