/*
 *
 * (C) Copyright IBM Corporation 2006-2008.
 *
 *  This file is part of X10 Language.
 *
 */

package x10.lang;

import x10.compiler.Native;
import x10.compiler.NativeRep;

@NativeRep("java", "x10.runtime.clock.ClockUseException")
public class ClockUseException extends RuntimeException {
    public def this(): ClockUseException = {}
    public def this(message: String): ClockUseException = { super(message);}
}
