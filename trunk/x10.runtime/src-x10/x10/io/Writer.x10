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

package x10.io;

import x10.compiler.Native;

/**
 * Usage:
 *
 * try {
 *   val in = new File(inputFileName);
 *   val out = new File(outputFileName);
 *   val p = out.printer();
 *   for (line in in.lines()) {
 *      line = line.chop();
 *      p.println(line);
 *   }
 * }
 * catch (IOException e) { }
 */    
public abstract class Writer {
    public abstract global def close(): Void throws IOException;
    public abstract global def flush(): Void throws IOException;
    public abstract global def write(x: Byte): Void throws IOException;

    public global def writeByte(x: Byte): Void throws IOException = Marshal.BYTE.write(this, x);
    public global def writeChar(x: Char): Void throws IOException =  Marshal.CHAR.write(this, x);
    public global def writeShort(x: Short): Void throws IOException = Marshal.SHORT.write(this, x);
    public global def writeInt(x: Int): Void throws IOException = Marshal.INT.write(this, x);
    public global def writeLong(x: Long): Void throws IOException = Marshal.LONG.write(this, x);
    public global def writeFloat(x: Float): Void throws IOException = Marshal.FLOAT.write(this, x);
    public global def writeDouble(x: Double): Void throws IOException = Marshal.DOUBLE.write(this, x);
    public global def writeBoolean(x: Boolean): Void throws IOException = Marshal.BOOLEAN.write(this, x);
    
    // made final to satisfy the restrictions on template functions in c++
    public final global def write[T](m: Marshal[T], x: T): Void throws IOException = m.write(this, x);

    public global def write(buf: ValRail[Byte]): Void throws IOException {
        write(buf, 0, buf.length);
    }

    public global def write(buf: Rail[Byte]!): Void throws IOException {
        write(buf, 0, buf.length);
    }

    public global def write(buf: ValRail[Byte], off: Int, len: Int): Void throws IOException {
        for (var i: Int = off; i < off+len; i++) {
            write(buf(i));
        }
    }

    public global def write(buf: Rail[Byte]!, off: Int, len: Int): Void throws IOException {
        for (var i: Int = off; i < off+len; i++) {
            write(buf(i));
        }
    }
    
    @Native("java", "new java.lang.Object() { java.io.OutputStream eval(final x10.io.Writer w) { return new java.io.OutputStream() { public void write(int x) throws java.io.IOException { w.write((byte) x); } }; } }.eval(#0)")
    private global def oos(): OutputStreamWriter.OutputStream {
        return oos();
    }
    
    // DO NOT CALL from X10 code -- only used in @Native annotations
    public global def getNativeOutputStream(): OutputStreamWriter.OutputStream = oos();
}
