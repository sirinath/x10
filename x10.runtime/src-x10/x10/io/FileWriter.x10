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

import x10.compiler.NativeRep;
import x10.compiler.Native;

public class FileWriter extends OutputStreamWriter {
    @NativeRep("java", "x10.core.io.FileOutputStream", null, "x10.core.io.FileOutputStream._RTT")
    @NativeRep("c++", "x10aux::ref<x10::io::FileWriter__FileOutputStream>", "x10::io::FileWriter__FileOutputStream", null)
    protected final static class FileOutputStream extends OutputStream {
        @Native("java", "new java.lang.Object() { x10.core.io.FileOutputStream eval(String s) { try { return new x10.core.io.FileOutputStream(s); } catch (java.io.FileNotFoundException e) { throw x10.runtime.impl.java.X10Throwable.getCorrespondingX10Exception(e); } } }.eval(#1)")
        public native def this(String); // throws IOException;
    }

    // TODO: This is questionable.
    //       What does it mean to send a File to another node?
    val file: File;
    
    // @Native("java", "new java.io.BufferedOutputStream(new java.io.FileOutputStream(#1))")
    private static def make(path: String):OutputStream{ //throws IOException {
        return new FileOutputStream(path);       
    }

    public def this(file: File) { //throws IOException {
        super(make(file.getPath()));
        this.file = file;
    }
}
