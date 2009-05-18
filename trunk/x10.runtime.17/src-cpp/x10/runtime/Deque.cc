/*
 * (c) Copyright IBM Corporation 2009
 *
 * This file is part of XRX/C++ native layer implementation.
 */

#include <x10aux/config.h>
#include <x10aux/atomic_ops.h>

#include <x10/runtime/Deque.h>

#include <errno.h>
#ifdef XRX_DEBUG
#include <iostream>
#endif /* XRX_DEBUG */

using namespace x10::lang;
using namespace x10::runtime;
using namespace x10aux;

ref<Deque> Deque::_constructor() {
    queue = x10aux::alloc<Slots>(sizeof(Slots) + (INITIAL_QUEUE_CAPACITY * sizeof(void*)));
    memset(queue->data, 0, (INITIAL_QUEUE_CAPACITY * sizeof(void*)));
    queue->capacity = INITIAL_QUEUE_CAPACITY;
    sp = 0;
    base = 0;
    return this;
}

void Deque::growQueue() {
    Slots *oldQ = queue;
    int oldSize = oldQ->capacity;
    int newSize = oldSize << 1;
    if (newSize > MAXIMUM_QUEUE_CAPACITY) {
        assert(false); /* throw new RuntimeException("Queue capacity exceeded"); */
    }
    Slots *newQ = x10aux::alloc<Slots>(sizeof(Slots) + (newSize * sizeof(void*)));
    memset(newQ->data, 0, (newSize * sizeof(void*)));
    newQ->capacity = newSize;
    queue = newQ;
    
    int b = base;
    int bf = b + oldSize;
    int oldMask = oldSize - 1;
    int newMask = newSize - 1;
    do {
        int oldIndex = b & oldMask;
        Object *t = (Object*)(oldQ->data[oldIndex]);
        if (t != NULL && !casSlotNull(oldQ, oldIndex, t)) {
            t = NULL;
        }
        setSlot(newQ, b & newMask, t);
    } while (++b != bf);
}

void Deque::pushTask(x10aux::ref<x10::lang::Object> t) {
    Slots *q = queue;
    int mask = q->capacity - 1;
    int s = sp;
    setSlot(q, s & mask, t.get());
    storeSp(++s);
    if ((s -= base) == 1) {
        ;
    } else if (s >= mask) {
        growQueue();
    }
}

ref<Object> Deque::deqTask() {
    Object *t;
    Slots *q;
    int i;
    int b;
    if (sp != (b = base) &&
        (q = queue) != NULL && // must read q after b
        (t = ((Object*)q->data[i = (q->capacity - 1) & b])) != NULL &&
        casSlotNull(q, i, t)) {
        base = b + 1;
        return t;
    }
    return null;
}

ref<Object> Deque::popTask() {
    int s = sp;
    while (s != base) {
        Slots *q = queue;
        int mask = q->capacity - 1;
        int i = (s - 1) & mask;
        Object *t = (Object*)(q->data[i]);
        if (t == NULL || !casSlotNull(q, i, t))
            break;
        storeSp(s - 1);
        return t;
    }
    return NULL;
}
                
            
DEFINE_RTT(Deque);

// vim:tabstop=4:shiftwidth=4:expandtab
