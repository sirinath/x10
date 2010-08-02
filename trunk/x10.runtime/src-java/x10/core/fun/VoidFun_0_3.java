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
import x10.rtt.RuntimeType.Variance;

public interface VoidFun_0_3<T1,T2,T3> extends VoidFun {
    void apply(T1 o1, T2 o2, T3 o3);
    
    public static final RuntimeType<VoidFun_0_3<?,?,?>> _RTT = new RuntimeType<VoidFun_0_3<?,?,?>>(
        VoidFun_0_3.class,
        Variance.CONTRAVARIANT,
        Variance.CONTRAVARIANT,
        Variance.CONTRAVARIANT
    );
}
