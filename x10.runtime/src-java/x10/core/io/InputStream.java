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

package x10.core.io;

import x10.core.Ref;
import x10.rtt.RuntimeType;
import x10.rtt.Type;
import x10.runtime.impl.java.X10Throwable;

public class InputStream extends Ref {
  
    private final java.io.InputStream stream;

    public InputStream(java.io.InputStream stream) {
        this.stream = stream;
    }
    
    public void close() {
        try {
            stream.close();
        } catch (java.io.IOException e) {
            throw X10Throwable.getCorrespondingX10Exception(e);
        }
    }
    
    public int read() {
        try {
            return stream.read();
        } catch (java.io.IOException e) {
            throw X10Throwable.getCorrespondingX10Exception(e);
        }
    }
    
    public void read(byte[] b, int off, int len) {
        try {
            stream.read(b, off, len);
        } catch (java.io.IOException e) {
            throw X10Throwable.getCorrespondingX10Exception(e);
        }
    }
    
    public int available() {
        try {
            return stream.available();
        } catch (java.io.IOException e) {
            throw X10Throwable.getCorrespondingX10Exception(e);
        }
    }
    
    public void skip(int n) {
        try {
            stream.skip(n);
        } catch (java.io.IOException e) {
            throw X10Throwable.getCorrespondingX10Exception(e);
        }
    }
    
    public void mark(int readlimit) {
        stream.mark(readlimit);
    }
    
    public void reset() {
        try {
            stream.reset();
        } catch (java.io.IOException e) {
            throw X10Throwable.getCorrespondingX10Exception(e);
        }
    }
    
    public boolean markSupported() {
        return stream.markSupported();
    }
    
    //
    // Runtime type information
    //
    public static final RuntimeType<InputStream> _RTT = new RuntimeType<InputStream>(
        InputStream.class,
        new Type[] { x10.rtt.Types.OBJECT }
    ) {
        @Override
        public String typeName() {
            return "x10.io.InputStreamReader.InputStream";
        }
    };
    public RuntimeType<?> getRTT() { return _RTT; }
    public Type<?> getParam(int i) { return null; }

}
