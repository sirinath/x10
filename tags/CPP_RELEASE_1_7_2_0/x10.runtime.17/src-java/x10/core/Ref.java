/*
 *
 * (C) Copyright IBM Corporation 2006-2008.
 *
 *  This file is part of X10 Language.
 *
 */

package x10.core;

import x10.runtime.impl.java.Thread;
import x10.types.RuntimeType;

// Base class of all X10 ref objects -- should be generated, but we need this class to get Box to compile.
public class Ref {
    public final int location;
    
    public Ref() {
       	location = Thread.currentThread().location();
    }
     
    /** Note: since this is final, it's important that the method name not conflict with any methods introduced by subclasses of Ref in X10 code. */
    public final int location() {
        return location;
    }
    
    public static class RTT extends RuntimeType<Ref> {
    	public static final RTT it = new RTT();

    	public RTT() {
            super(Ref.class);
        }

        @Override
        public boolean instanceof$(Object o) {
            return o instanceof Ref;
        }
    }

    public Ref box$() {
        return this;
    }
}
