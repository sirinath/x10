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

#ifndef X10AUX_THROW_H
#define X10AUX_THROW_H

#include <cstdlib>
#include <x10aux/config.h>
#include <x10aux/ref.h>

#include <x10/lang/Throwable.h>

namespace x10aux {

    template<class T> void throwException() X10_PRAGMA_NORETURN;

    void throwException(x10aux::ref<x10::lang::Throwable> e) X10_PRAGMA_NORETURN;

    inline void throwException(x10aux::ref<x10::lang::Throwable> e) {
        throw e->fillInStackTrace();
    }

    template<class T> void throwException() {
        throwException(T::_make());
    }

}

#endif
// vim:tabstop=4:shiftwidth=4:expandtab
