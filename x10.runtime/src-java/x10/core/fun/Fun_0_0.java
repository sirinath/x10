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

package x10.core.fun;

import x10.rtt.RuntimeType;
import x10.rtt.Type;


public interface Fun_0_0<U> {
    U apply();
    Type<?> rtt_x10$lang$Fun_0_0_U();

    public static class RTT extends RuntimeType<Fun_0_0<?>>{
        Type<?> U;

        public RTT(Type<?> U) {
            super(Fun_0_0.class);
            this.U = U;
        }

        @Override
        public boolean instanceof$(Object o) {
            if (o instanceof Fun_0_0<?>) {
                Fun_0_0<?> v = (Fun_0_0<?>) o;
                return v.rtt_x10$lang$Fun_0_0_U().isSubtype(U); // covariant
            }
            return false;
        }

        @Override
        public boolean isSubtype(Type<?> o) {
            if (! super.isSubtype(o))
                return false;
            if (o instanceof Fun_0_0.RTT) {
                Fun_0_0.RTT t = (RTT) o;
                return U.isSubtype(t.U);
            }
            return false;
        }
    }
}
