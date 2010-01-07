/*
 *
 * (C) Copyright IBM Corporation 2006-2008.
 *
 *  This file is part of X10 Language.
 *
 */

package x10.lang;

import x10.util.HashMap;

/**
 * @author tardieu
 */
class ClockPhases extends HashMap[Clock,Int] {
    static def make(clocks:ValRail[Clock], phases:ValRail[Int]) {
        val clockPhases = new ClockPhases();
        for(var i:Int = 0; i < clocks.length; i++) clockPhases.put(clocks(i), phases(i));
        return clockPhases;
    }

    def register(clocks:ValRail[Clock]) {
        return ValRail.make[Int](clocks.length, (i:Int)=>(clocks(i)).register());
    }

    def next() {
        for(clock:Clock in keySet()) clock.resumeUnsafe();
        for(clock:Clock in keySet()) clock.nextUnsafe();
    }

    def drop() {
       for(clock:Clock in keySet()) clock.dropUnsafe();
    }
}

// vim:shiftwidth=4:tabstop=4:expandtab
