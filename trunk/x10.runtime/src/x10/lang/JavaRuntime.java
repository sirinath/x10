package x10.lang;

import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * "Native" functions mapped through to Java.  In the long run the
 * idea is that this becomes the "X10 native interface" (akin to JNI,
 * but of course better).  For the prototype, this interface contains
 * the few native functions that we just feel like we have to provide.
 *
 * In principle, this class can be subclassed, but since it is
 * unlikely that we will have multiple implementations of any of these
 * functions other than the default, we currently do without.
 *
 * I'm not sure that this class should live in this package -- maybe
 * the code can live here (JavaRuntime_c), but the current situation
 * where x10.lang.Runtime directly refers to a class in x10.runtime
 * is definitively bad. 
 *
 * @author Christian Grothoff, Christoph von Praun
 */
public class JavaRuntime {

    private final Logger logger_ 
	= Logger.getAnonymousLogger();

    public long currentTimeMillis() {
	return System.currentTimeMillis();
    }

    public void log(Level l,
		    String message,
		    Object param1) {
	logger_.log(l, message, param1);
    }

    public void log(Level l,
		    String message,
		    Object[] params) {
	logger_.log(l, message, params);
    }

    public void log(Level l,
		    String message,
		    Throwable thrown) {
	logger_.log(l, message, thrown);
    }
    
    public void error(String message, Throwable thrown) {
    	Error tmp = new Error("Unexpected runtime error, see log for details.");
    	if (thrown != null)
    		thrown.printStackTrace();
    	else 
    		tmp.printStackTrace();
    	logger_.log(Level.SEVERE, message, (Object) null);
    	throw tmp;
    }

} // end of Native

