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

package ssca2;

/**
 * This struct allows treating a pair of values as a single value, for example when returning from a method.
 */
public struct Triplet[T,U,V] {
    public val first:T;
    public val second:U;
    public val third:V;

    public def this(first:T, second:U, third:V):Triplet[T,U,V] {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public global safe def toString():String {
        return "(" + first + ", " + second + ", " + third + ")";
    }

}
