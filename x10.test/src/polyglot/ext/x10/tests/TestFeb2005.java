package polyglot.ext.x10.tests;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import junit.framework.TestCase;
import x10.lang.Activity;
import x10.lang.Runtime;
import x10.runtime.DefaultRuntime_c;
import x10.runtime.Place;
import x10.runtime.ThreadRegistry;

/**
 * JUnit testing harness for the X10 compiler.
 * 
 * <h2>Overview</h2>
 * The harness helps invoke the compiler on an X10 testcase and
 * then tries to execute the resulting code in the JVM.
 * The X10 code can either consist of a 'run' method (which
 * is supposed to return 'false' on errors) or of a 
 * 'main' method (which is supposed to throw an exception on
 * errors).
 * Naturally, if the compiler gives an exception, this is also
 * an error.
 * 
 * <h2>Adding tests</h2>
 * Adding testcases is typically a one-liner: add a testXXX
 * method which calls run or runMain, passing the path to the
 * X10 source and the name of the main class as arguments.
 * For the main case, you can also pass the arguments to the
 * main method of the X10 program (if any).  So most of the
 * work is to write the x10 code that does something interesting.
 * 
 * If you are addding new tests that are currently known to fail,
 * please <em>commit</em> them with a capital "T" in the "test" of the
 * method name.  This deactivates the test, allowing others to
 * quickly check that they did not break anything when running
 * the tests in regression.  
 * 
 * <h2>Running the tests</h2>
 * Running this testcase is easy, add it as just any JUnit
 * test to eclipse.  You might want to make sure to have the
 * current working directory set to the root of the X10
 * checkout (x10/), otherwise the data/ files (xcds) and
 * the X10 source inputs will not be found by the compiler.
 * 
 * If you are not using eclipse, compile the code using
 * "ant x10-ext" and then run "bin/junit" (both from the main
 * x10 directory).  [ Note that this currently does not work,
 * it fails in the same way as x10c does, Vj said he'd look
 * into this, so I won't. ]
 * 
 * <h2>Internals</h2>
 * The harness works by using a class loader to load the
 * dumped .class files and some reflection to invoke the
 * testcase.  Some setup work is done to initialize the X10
 * Runtime.
 * 
 * @author Christian Grothoff <christian@grothoff.org>
 * @author Vivek Sarkar <vsarkar@us.ibm.com>
 * TestFeb2005.java is a simple variant of TestCompiler.java
 */
public class TestFeb2005 extends TestCase {

    
    public void testArray1() {
        runMain("examples/testcases/feb2005/parserworking/Array1.x10", "Array1");
    }

    public void testArray2() {
	runMain("examples/testcases/feb2005/parserworking/Array2.x10", "Array2");
    }

    public void testAsyncTest() {
	runMain("examples/testcases/feb2005/parserworking/AsyncTest.x10", "AsyncTest");
    }

    public void testAsyncTest1() {
	runMain("examples/testcases/feb2005/parserworking/AsyncTest1.x10", "AsyncTest1");
    }

    public void testAteach() {
	runMain("examples/testcases/feb2005/parserworking/Ateach.x10", "Ateach");
    }

    public void testAtomic1() {
	runMain("examples/testcases/feb2005/parserworking/Atomic1.x10", "Atomic1");
    }

    public void testAtomicTest() {
	runMain("examples/testcases/feb2005/parserworking/AtomicTest.x10", "AtomicTest");
    }

    public void testAwaitTest() {
	runMain("examples/testcases/feb2005/parserworking/AwaitTest.x10", "AwaitTest");
    }

    public void testBoxing0() {
	runMain("examples/testcases/feb2005/parserworking/Boxing0.x10", "Boxing0");
    }

    public void testClockTest() {
	runMain("examples/testcases/feb2005/parserworking/ClockTest.x10", "ClockTest");
    }

    public void testClockTest1() {
	runMain("examples/testcases/feb2005/parserworking/ClockTest1.x10", "ClockTest1");
    }

    public void testClockTest2() {
	runMain("examples/testcases/feb2005/parserworking/ClockTest2.x10", "ClockTest2");
    }

    public void testConditionalAtomicTest() {
	runMain("examples/testcases/feb2005/parserworking/ConditionalAtomicTest.x10", "ConditionalAtomicTest");
    }

    public void testConditionalAtomicTest2() {
	runMain("examples/testcases/feb2005/parserworking/ConditionalAtomicTest2.x10", "ConditionalAtomicTest2");
    }

    public void testDistributionTest() {
	runMain("examples/testcases/feb2005/parserworking/DistributionTest.x10", "DistributionTest");
    }

    public void testFinishTest1() {
	runMain("examples/testcases/feb2005/parserworking/FinishTest1.x10", "FinishTest1");
    }

    public void testForeach() {
	runMain("examples/testcases/feb2005/parserworking/Foreach.x10", "Foreach");
    }

    public void testFuture0() {
	runMain("examples/testcases/feb2005/parserworking/Future0.x10", "Future0");
    }

    public void testFuture1() {
	runMain("examples/testcases/feb2005/parserworking/Future1.x10", "Future1");
    }

    public void testFuture1Boxed() {
	runMain("examples/testcases/feb2005/parserworking/Future1Boxed.x10", "Future1Boxed");
    }

    public void testFuture2Boxed() {
	runMain("examples/testcases/feb2005/parserworking/Future2Boxed.x10", "Future2Boxed");
    }

    public void testFuture3() {
	runMain("examples/testcases/feb2005/parserworking/Future3.x10", "Future3");
    }

    public void testFuture3Boxed() {
	runMain("examples/testcases/feb2005/parserworking/Future3Boxed.x10", "Future3Boxed");
    }

    public void testFuture4() {
	runMain("examples/testcases/feb2005/parserworking/Future4.x10", "Future4");
    }

    public void testFuture4Boxed() {
	runMain("examples/testcases/feb2005/parserworking/Future4Boxed.x10", "Future4Boxed");
    }

    public void testFutureNullable0() {
	runMain("examples/testcases/feb2005/parserworking/FutureNullable0.x10", "FutureNullable0");
    }

    public void testFutureNullable1Boxed() {
	runMain("examples/testcases/feb2005/parserworking/FutureNullable1Boxed.x10", "FutureNullable1Boxed");
    }

    public void testFutureTest2() {
	runMain("examples/testcases/feb2005/parserworking/FutureTest2.x10", "FutureTest2");
    }

    public void testJacobi() {
	runMain("examples/testcases/feb2005/parserworking/Jacobi.x10", "Jacobi");
    }

    public void testMiscTest1() {
	runMain("examples/testcases/feb2005/parserworking/MiscTest1.x10", "MiscTest1");
    }

    public void testNopTest() {
	runMain("examples/testcases/feb2005/parserworking/NopTest.x10", "NopTest");
    }

    public void testNullable0Ref() {
	runMain("examples/testcases/feb2005/parserworking/Nullable0Ref.x10", "Nullable0Ref");
    }

    public void testNullable1() {
	runMain("examples/testcases/feb2005/parserworking/Nullable1.x10", "Nullable1");
    }

    public void testNullable2() {
	runMain("examples/testcases/feb2005/parserworking/Nullable2.x10", "Nullable2");
    }

    public void testNullable5() {
	runMain("examples/testcases/feb2005/parserworking/Nullable5.x10", "Nullable5");
    }

    public void testNullableFuture0() {
	runMain("examples/testcases/feb2005/parserworking/NullableFuture0.x10", "NullableFuture0");
    }

    public void testNullableFuture1() {
	runMain("examples/testcases/feb2005/parserworking/NullableFuture1.x10", "NullableFuture1");
    }

    public void testNullableFuture2() {
	runMain("examples/testcases/feb2005/parserworking/NullableFuture2.x10", "NullableFuture2");
    }

    public void testNullableObject() {
	runMain("examples/testcases/feb2005/parserworking/NullableObject.x10", "NullableObject");
    }

    public void testRegionTest() {
	runMain("examples/testcases/feb2005/parserworking/RegionTest.x10", "RegionTest");
    }

    public void testRegionTest1() {
	runMain("examples/testcases/feb2005/parserworking/RegionTest1.x10", "RegionTest1");
    }

    public void testRegionTest2() {
	runMain("examples/testcases/feb2005/parserworking/RegionTest2.x10", "RegionTest2");
    }

    public void testTestBase() {
	runMain("examples/testcases/feb2005/parserworking/TestBase.x10", "TestBase");
    }

    public void testValueClass() {
	runMain("examples/testcases/feb2005/parserworking/ValueClass.x10", "ValueClass");
    }
  

    // *************** you should never have to edit anything
    // below this line **************************************
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestFeb2005.class);
    }

    /**
     * Constructor for TestX10PrettyPrinterVisitor.
     * @param name
     */
    public TestFeb2005(String name) {
        super(name);
    }  

    private final Activity a
        = new Activity() { public void run() {} }; // dummy
  
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
        if (r instanceof ThreadRegistry) {
            Thread t = Thread.currentThread();
            ThreadRegistry tr = (ThreadRegistry) r;
            tr.registerThread(t, pls[0]);
            tr.registerActivityStart(t, a, null);
        }
    }

    private void runMain(String file, String main) {
	runMain(file, main, null);
    }

    private void runMain(String file,
                         String main,
                         String[] args) {
	String[] poargs 
	    = new String[] { "-ext", "x10",
			     file };
	System.out.println("TestFeb2005: runMain() called on " + file + "...");
	polyglot.main.Main.main(poargs); // run compiler!
	/** Don't try to execute the X10 code as yet ...
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
    **/
    System.out.println("... done");
    }
}