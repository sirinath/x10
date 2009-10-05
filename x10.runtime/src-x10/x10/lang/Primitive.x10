/*
 *
 * (C) Copyright IBM Corporation 2006-2008.
 *
 *  This file is part of X10 Language.
 *
 */

package x10.lang;

public abstract struct Primitive {
   public final def equals(x:Object)=false;

   public final def equals[T](x:T) = this == x;
}
