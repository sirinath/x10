/*
 * Created by vj on Jan 11, 2005
 *
 * 
 */
package polyglot.ext.x10.tests;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import x10.lang.Runtime;
import x10.runtime.Activity;
import x10.runtime.Configuration;
import x10.runtime.DefaultRuntime_c;
import x10.runtime.Place;

/**
 * JUnit testing harness for the X10 compiler.
 * 
 * <h2>Overview</h2>
 * The harness helps invoke the compiler on an X10 testcase and
 * then tries to execute the resulting code in the JVM.
 * The X10 code can either consist of a 'run' method (which
 * is supposed to return 'false' on errors, or throw an exception).
 * Naturally, if the compiler gives an exception, this is also
 * an error.
 *
 * <h2>Adding test cases</h2>
 * The file TestCompiler.java contains the test cases.
 * It is generated automatically by the makeTestCompiler script,
 * from the current contents of the test directory, e.g.
 * examples/

 * <h2>Running the tests</h2>
 * Running this testcase is easy, add it as just any JUnit
 * test to eclipse. Set the current directory to the test
 * directory, e.g. examples/
 * 
 * If you are not using eclipse, compile the code using
 * "ant clobber all" then run "junit"  at the 
 * test directory, e.g. examples/
 * 
 * <h2>Internals</h2>
 * The harness works by using a class loader to load the
 * dumped .class files and some reflection to invoke the
 * testcase.  Some setup work is done to initialize the X10
 * Runtime.
 * 
 * @author Christian Grothoff <christian@grothoff.org>
 * @author vj -- Refactored to pull out the core code.
 * @author kemal -- supporting new test hierarchy
 */

/**
 * @author vj Jan 11, 2005
 * 
 */
public class TestX10_Compiler extends TestCase {

    static {
        Configuration.parseCommandLine(null);
        Runtime.init();
    }
    
	/**
	 * 
	 */
	public TestX10_Compiler() {
		super();
	}
	/**
     * Constructor for TestX10_CompilerPrettyPrinterVisitor.
     * @param name
     */
	public TestX10_Compiler(String name ) {
		super( name );
	}

	    
	public static void Main(Class k) {
		junit.textui.TestRunner.run(k);
	}
	
	public static TestSuite suite(Class k ) {
		return new TestSuite(k);
	}
	
	protected final Activity a = new Activity() { public void run() {} }; // dummy
	
	/**
	 * Junit may use additional threads to run the testcases
	 * (other than the main one used to initialize the
	 * Runtime class).  Hence we need a litte hack to register
	 * the thread used to run the testcase as a 'local' thread
	 * with the Runtime.
	 */
	public void setUp() {
        DefaultRuntime_c r = (DefaultRuntime_c) Runtime.runtime;
		Place[] pls = Place.places();
		Thread t = Thread.currentThread();
		r.registerThread(t, pls[0]);
		r.registerActivityStart(t, a, null);
	}
	
	
	protected void compile(String dir, String file) {
		String[] poargs = new String[] { "-sourcepath",dir,"-d",dir,"-ext", "x10", dir + file.substring(1) };
		polyglot.main.Main.main( poargs ); // run compiler!
	}
	
	protected void run(String file, String main,String dir) {
		
		try {
			compile(dir, file);
			String cwd=System.getProperty("user.dir")+
                              dir.substring(1);
			ClassLoader loader = new URLClassLoader
                          (new URL[] {new URL("file://"+cwd+"/")}); 
			Class c = loader.loadClass(main);
			Object inst = c.newInstance();
			Method m = c.getMethod("run", new Class[0]);
			Boolean ret = (Boolean) m.invoke(inst, null);
			assertTrue(ret.booleanValue());
			System.out.println("Test "+main+" completed.");
		} catch (IOException io) {
			fail(io.toString());
		} catch (InstantiationException ie) {
			fail(ie.toString());
		} catch (NoSuchMethodException nmse) {
			fail(nmse.toString());
		} catch (InvocationTargetException ite) {
			fail(ite.getCause().getMessage());
		} catch (ClassNotFoundException cnfe) { fail(cnfe.toString());
		} catch (IllegalArgumentException iae) {
			fail(iae.toString());
		} catch (ClassFormatError cfe) {
			fail(cfe.toString());
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
                        fail(iae.toString());
		}
	}
	
// runMain is no longer supported
/*
	protected void runMain(String file, String main) {
		runMain(file, main, null);
	}
	
	protected void runMain(String file, String main, String dir, String[] args) {
		compile(file);
		try {
			ClassLoader loader 
			= new URLClassLoader(new URL[] { new URL("file://" + System.getProperty("user.dir") + "/") }); 
			Class c
			= loader.loadClass(main);
			Method m
			= c.getMethod("main", new Class[] { String[].class });
			m.invoke(null, new Object[] {args});        
		} catch (IOException io) {
			fail(io.toString());
		} catch (NoSuchMethodException nmse) {
			fail(nmse.toString());
		} catch (InvocationTargetException ite) {
			fail(ite.toString());
		} catch (ClassNotFoundException cnfe) {
			fail(cnfe.toString());
		} catch (IllegalArgumentException iae) {
			fail(iae.toString());
		} catch (ClassFormatError cfe) {
			fail(cfe.toString());
		} catch (IllegalAccessException iae) {
			fail(iae.toString());
		}
	}
*/
	
}

