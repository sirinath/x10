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

package x10.util;

import x10.util.HashMap;
import x10.util.GrowableRail;

/**
 * @author Dave Cunningham
*/
public final class OptionsParser {

    public static final class Err extends Exception {
        global private val msg:String;
        public def this (m:String) { this.msg = m; }
        global safe public def toString() = "Commandline error: "+msg;
    }

    private val map : HashMap[String,String]!;
    private val set : HashMap[String,Boolean]!;
    private val filteredArgs : GrowableRail[String]!;

    public def this (args:Rail[String]!, flags:ValRail[Option], specs:ValRail[Option]) throws Err {
        val map = new HashMap[String,String]();
        val set = new HashMap[String,Boolean]();
        val filteredArgs = new GrowableRail[String]();
        var offset:Int = 0;
        var ended:Boolean = false;
        for (var i:Int=0 ; i<args.length ; ++i) {
            val s = args(i);
            var recognised: Boolean = false;
            if (s.equals("--")) {
                ended = true;
                continue;
            }
            if (!ended) {
                if (flags!=null) for (flag in flags) {
                    if (recognised) break;
                    if (s.equals(flag.short_) || s.equals(flag.long_)) {
                        if (flag.short_!=null) set.put(flag.short_, true);
                        if (flag.long_!=null) set.put(flag.long_, true);
                        recognised = true;
                    }
                }
                if (specs!=null) for (spec in specs) {
                    if (recognised) break;
                    if (s.equals(spec.short_) || s.equals(spec.long_)) {
                        recognised = true;
                        ++i;
                        if (i>=args.length) throw new Err("Expected another arg after: \""+s+"\"");
                        val s2 = args(i);
                        if (spec.short_!=null) map.put(spec.short_, s2);
                        if (spec.long_!=null) map.put(spec.long_, s2);
                    }
                }
            }
            if (!recognised) filteredArgs.add(s);
        }
        this.map = map;
        this.set = set;
        this.filteredArgs = filteredArgs;
    }

    public def filteredArgs() = filteredArgs.toRail();

    public def apply (key:String) = set.containsKey(key) || map.containsKey(key);

    public def apply (key:String, d:String) = map.getOrElse(key, d);
    /* Uncomment on resolution of XTENLANG-1413
    public def apply (key:String, d:UByte) throws Err {
        if (!map.containsKey(key)) return d;
        val v = map.getOrElse(key, "???");
        try {
            return UByte.parseUByte(v);
        } catch (e:NumberFormatException) {
            throw new Err("Expected UByte, got: \""+v+"\"");
        }
    }
    */
    public def apply (key:String, d:Byte) throws Err {
        if (!map.containsKey(key)) return d;
        val v = map.getOrElse(key, "???");
        try {
            return Byte.parseByte(v);
        } catch (e:NumberFormatException) {
            throw new Err("Expected Byte, got: \""+v+"\"");
        }
    }
    /* Uncomment on resolution of XTENLANG-1413
    public def apply (key:String, d:UShort) throws Err {
        if (!map.containsKey(key)) return d;
        val v = map.getOrElse(key, "???");
        try {
            return UShort.parseUShort(v);
        } catch (e:NumberFormatException) {
            throw new Err("Expected UShort, got: \""+v+"\"");
        }
    }
    */
    public def apply (key:String, d:Short) throws Err {
        if (!map.containsKey(key)) return d;
        val v = map.getOrElse(key, "???");
        try {
            return Short.parseShort(v);
        } catch (e:NumberFormatException) {
            throw new Err("Expected Short, got: \""+v+"\"");
        }
    }
    /* Uncomment on resolution of XTENLANG-1413
    public def apply (key:String, d:UInt) throws Err {
        if (!map.containsKey(key)) return d;
        val v = map.getOrElse(key, "???");
        try {
            return UInt.parseUInt(v);
        } catch (e:NumberFormatException) {
            throw new Err("Expected UInt, got: \""+v+"\"");
        }
    }
    */
    public def apply (key:String, d:Int) throws Err {
        if (!map.containsKey(key)) return d;
        val v = map.getOrElse(key, "???");
        try {
            return Int.parseInt(v);
        } catch (e:NumberFormatException) {
            throw new Err("Expected Long, got: \""+v+"\"");
        }
    }
    /* Uncomment on resolution of XTENLANG-1413
    public def apply (key:String, d:ULong) throws Err {
        if (!map.containsKey(key)) return d;
        val v = map.getOrElse(key, "???");
        try {
            return ULong.parseULong(v);
        } catch (e:NumberFormatException) {
            throw new Err("Expected ULong, got: \""+v+"\"");
        }
    }
    */
    public def apply (key:String, d:Long) throws Err {
        if (!map.containsKey(key)) return d;
        val v = map.getOrElse(key, "???");
        try {
            return Long.parseLong(v);
        } catch (e:NumberFormatException) {
            throw new Err("Expected Int, got: \""+v+"\"");
        }
    }


    public def apply (key:String, d:Double) throws Err {
        if (!map.containsKey(key)) return d;
        val v = map.getOrElse(key, "???");
        try {
            return Double.parseDouble(v);
        } catch (e:NumberFormatException) {
            throw new Err("Expected Double, got: \""+v+"\"");
        }
    }
    public def apply (key:String, d:Float) throws Err {
        if (!map.containsKey(key)) return d;
        val v = map.getOrElse(key, "???");
        try {
            return Float.parseFloat(v);
        } catch (e:NumberFormatException) {
            throw new Err("Expected Float, got: \""+v+"\"");
        }
    }
}

// vim: shiftwidth=4:tabstop=4:expandtab
