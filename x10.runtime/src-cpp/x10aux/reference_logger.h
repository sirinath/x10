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

#ifndef X10AUX_REFERENCE_LOGGER_H
#define X10AUX_REFERENCE_LOGGER_H

#include <x10aux/config.h>
#include <x10aux/ref.h>

namespace x10 {
    namespace lang {
        class Lock__ReentrantLock;
    }
}


namespace x10aux {

#if defined(X10_USE_BDWGC) || defined(X10_DEBUG_REFERENCE_LOGGER)
    /* A utility class to keep track of references that have been
     * shipped to remote Places,and therefore must be treated as
     * roots for local GCs
     */
    class ReferenceLogger {
    public:
        static ReferenceLogger* it;
    private:
        class Bucket {
        public:
            void *_reference;
            Bucket *_next;
        };
        x10aux::ref<x10::lang::Lock__ReentrantLock> _lock;
        Bucket **_buckets;

    public:
        ReferenceLogger();
        void log_(void *x);
        static void log(void *x) { it->log_(x); }
        template<class T> friend const char *x10aux::typeName();
    };

    template<> inline const char *typeName<ReferenceLogger>() { return "ReferenceLogger"; }
    template<> inline const char *typeName<ReferenceLogger::Bucket*>() { return "ReferenceLogger::Bucket *"; }
    template<> inline const char *typeName<ReferenceLogger::Bucket>() { return "ReferenceLogger::Bucket"; }
#endif

}

#endif
// vim:tabstop=4:shiftwidth=4:expandtab
