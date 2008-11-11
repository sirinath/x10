package x10.util;

import x10.compiler.Native;

public class Timer {
        /** Milliseconds since the Epoch: midnight, Jan 1, 1970. */
        @Native("java", "java.lang.System.currentTimeMillis()")
        public static def milliTime(): Long;
        
        /** Current value of the system timer, in nanoseconds.  May be rounded if system timer does not have nanosecond precision. */
        @Native("java", "java.lang.System.nanoTime()")
        public static def nanoTime(): Long;
}
