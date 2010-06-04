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

/**
 * @author bdlucas
 */

#include <limits.h>

namespace UTSRand {

    //
    // For now use util.Random instead of SHA. To substitute SHA
    // redefine descriptor, next(), and number().
    //
    // Instead of actually using util.Random, we replicate its
    // function here to avoid allocating a Random object.
    //

    typedef long long descriptor;

    descriptor next(descriptor r, int i) {
        descriptor seed = r+i;
        seed = (seed ^ 0x5DEECE66DLL) & ((1LL << 48) - 1);
        for (int k=0; k<11; k++)
            seed = (seed * 0x5DEECE66DLL + 0xBLL) & ((1LL << 48) - 1);
        int l0 = (int) (seed >> (48 - 32));
        seed = (seed * 0x5DEECE66DLL + 0xBLL) & ((1LL << 48) - 1);
        int l1 = (int) (seed >> (48 - 32));
        return (((descriptor)l0) << 32) + l1;
    }


    double scale = ((double)LONG_LONG_MAX) - ((double)LONG_LONG_MIN);

    double number(descriptor r) {return (r/scale) - (LONG_LONG_MIN/scale);}
}
