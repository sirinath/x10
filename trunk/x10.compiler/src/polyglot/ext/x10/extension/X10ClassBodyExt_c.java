
/**
 * @author Chris Donawa 
 * Implementation of extern (previously known as native)
 *         calls
 */
package polyglot.ext.x10.extension;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import polyglot.ast.Call;
import polyglot.ast.Expr;
import polyglot.ast.MethodDecl;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.TypeNode;
import polyglot.ext.jl.ast.Block_c;
import polyglot.ext.jl.ast.Call_c;
import polyglot.ext.jl.ast.ClassBody_c;
import polyglot.ext.jl.ast.Formal_c;
import polyglot.ext.jl.ast.MethodDecl_c;
import polyglot.ext.jl.types.ClassType_c;
import polyglot.ext.jl.types.Type_c;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.types.MethodInstance;
import polyglot.types.PrimitiveType;
import polyglot.util.Position;

/**
 * 
 * @author donawa
 *
 * Check class bodies for 'extern' keyword (aka native)
 * and generate approriate wrappers and stubs to support
 * the simplified JNI-like interface to native code from X10
 */
public class X10ClassBodyExt_c extends X10Ext_c {

	private BufferedWriter wrapperFile;
	X10TypeSystem typeSystem;
	
	/* name of unsafe array method used to pass address to native calls */
	private final String getUnsafeAddressMethod = "getUnsafeAddress";
	
	String[] wrapperPrelogue = {
			"/*Automatically generated -- DO NOT EDIT THIS FILE */\n",
			"#include <jni.h>\n", "#ifdef __cplusplus\n", "extern \"C\" {\n",
			"#endif\n", "" };

	String[] wrapperEpilogue = { "\n", "#ifdef __cplusplus\n", "}\n",
			"#endif\n" };

	
	private void generateWrapperPrologue() {
		
		try {
			for (int i = 0; i < wrapperPrelogue.length; ++i) {
				wrapperFile.write(wrapperPrelogue[i]);
			}			
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Problems writing to " + wrapperFile);
		}
	}

	private void generateWrapperEpilogue() {
		try {
			for (int i = 0; i < wrapperEpilogue.length; ++i) {
				wrapperFile.write(wrapperEpilogue[i]);
			}
			wrapperFile.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Problems with " + wrapperFile );
		}
	}

/**
 * Create a text file with suffix _x10stub.c, one for each outermost class which
 * contains x10 extern methods
 * @param containingClassName
 */
	private void createWrapperFile(String containingClassName) {
		String fileName = containingClassName + "_x10stub.c";
		Date timeStamp = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat();
		
		try {
			wrapperFile = new BufferedWriter(new FileWriter(fileName));
			wrapperFile.write("/*\n * Filename:"+fileName +
					"\n * Generated: "+formatter.format(timeStamp)+" */\n");
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Problems writing to "+wrapperFile);
		}
	}

	private String typeToCString(Type_c theType) {
		if (theType.isPrimitive()) {
			//System.out.println(theType.toString() + " is primitive");
			if (theType.isArray())
				throw new Error("Array Type unexpected" + theType.toString());
				
			if (theType.isInt())
				return "signed int";
			if (theType.isChar())
				return "signed short";
			if (theType.isBoolean())
				return "unsigned char";
			if (theType.isByte())
				return "signed char";
			if (theType.isShort())
				return "signed short";
			if (theType.isLong())
				return "jlong"; // best to use jni.h's defn
			if (theType.isFloat())
				return "float";
			if (theType.isDouble())
				return "double";
			if (theType.isVoid())
				return "void";
			
			throw new Error("Unexpected type" + theType.toString());
		}
		else
			return "jlong";//xlated to long getUnsafeAddress();
	}
	
	private String typeToJavaSigString(Type_c theType){
		if (theType.isPrimitive()) {
			//System.out.println(theType.toString() + " is primitive");
			if (theType.isArray())
				System.out.println("Error!" + theType);
			if (theType.isInt())
				return "I";
			if (theType.isChar())
				return "J";
			if (theType.isBoolean())
				return "Z";
			if (theType.isByte())
				return "B";
			if (theType.isShort())
				return "S";
			if (theType.isLong())
				return "J";
			if (theType.isFloat())
				return "F";
			if (theType.isDouble())
				return "D";
			if (theType.isVoid())
				return "V";
			throw new Error("Unexpected type" + theType.toString());
		} else
			return "J";// should have been xlated to  long getUnsafeAddress();
	}
	
	
	
	private String generateJavaSignature(MethodDecl_c method){
		MethodInstance mi = method.methodInstance();
		String signature = "";// "("

		for (ListIterator i = method.formals().listIterator(); i.hasNext();) {
			Formal_c parameter = (Formal_c) i.next();
			signature += typeToJavaSigString((Type_c) parameter.declType());
		}
		if(false)signature += ")" + typeToJavaSigString((Type_c) mi.returnType());
		return signature;
	}
	
	/**
	 * replace '_' with '_1'
	 *         '<unicode>' with '_0<unicode>'
	 *         ';' with '_2
	 *         '[' with _3
	 * @param inName
	 * @return
	 */
	private String JNImangle(String inName){
		char [] charName = inName.toCharArray();
		StringBuffer buffer = new StringBuffer(inName.length());
		for(int i=0;i< inName.length();++i){
			char ch = inName.charAt(i);
			
			switch(ch){
				case '_':
					buffer.append("_1");
					continue;
				case ';':
					buffer.append("_2");
					continue;
				case '[':
					buffer.append("_3");
					continue;
				default:
					//FIXME: handle unicode conversion
					buffer.append(ch);
					break;
			}
		}
		//System.out.println("convert from "+inName+" to "+buffer.toString());
		return buffer.toString();
	}
	/**
	 * Apply same mangling algorithm as javah does so we can automatically 
	 * generate a name for the JNI code.
	 * @param method
	 * @param isOverloaded
	 * @return
	 */
	private String generateJNIName(MethodDecl_c method, boolean isOverloaded){
		String name = "Java_" + JNImangle(method.methodInstance().container().toString())+ 
		"_" + JNImangle(method.methodInstance().container().toString()) +
		"_1" + JNImangle(method.name());
		if(isOverloaded)
			name += "__" + generateJavaSignature(method);
		return name;
	}
	
	private String generateX10NativeName(MethodDecl_c method){
		return  method.methodInstance().container().toString()+ 
		"_" + method.name();
	
	}
	/**
	 * change the name of native Method and any non-primitive parameters
	 * to type long
	 * @param nativeMethod
	 * @param nf node factory
	 * @return new method declaration of java jni call
	 */
	private MethodDecl_c createNewNative(MethodDecl_c nativeMethod, NodeFactory nf) {
	
		MethodInstance mi = nativeMethod.methodInstance();
		
		String nativeName = generateX10NativeName(nativeMethod);
		MethodDecl_c newNative = (MethodDecl_c)nativeMethod.name(nativeName);
		ArrayList newFormals = new ArrayList();
		
		TypeNode longType = nf.CanonicalTypeNode(nativeMethod.position(),typeSystem.Long());
		boolean seenNonPrimitive=false;
		/* any objects that are not primitive will be unsafe arrays and 
		 * transformed to getUnsafeAddress calls, so just map to type long
		 */
		for (ListIterator i = nativeMethod.formals().listIterator(); i.hasNext();) {	
			Formal_c parameter = (Formal_c) i.next();	
			if(parameter.declType().isPrimitive())
				newFormals.add(parameter);
			else{
				seenNonPrimitive=true;
				newFormals.add(parameter.type(longType));
			}
		}
		if(seenNonPrimitive){
			newNative = (MethodDecl_c)newNative.formals(newFormals);
		}
		return newNative;
	}
	
	
	/**
	 * Create java wrapper which calls a JNI call which implementes
	 * interface with X10 extern
	 * @param nativeMethod
	 * @param nf node factory
	 * @return wrapper method
	 */
	private MethodDecl_c createNativeWrapper(MethodDecl_c nativeMethod, NodeFactory nf) {
		nativeMethod = (MethodDecl_c)nativeMethod.flags(nativeMethod.flags().clearNative());
		MethodInstance mi = nativeMethod.methodInstance();
		Position pos = nativeMethod.position();
		MethodDecl_c nativeWrapper = nativeMethod;
		
		ArrayList newArgs = new ArrayList();
		String jniName = generateX10NativeName(nativeMethod);
		
		TypeNode receiver = nf.CanonicalTypeNode(pos,mi.container());
		
		Call jniCall = nf.Call(pos,receiver,jniName,newArgs);
		jniCall = (Call_c)jniCall.targetImplicit(true);
		jniCall = (Call_c)jniCall.methodInstance(mi);
		
		ArrayList args = new ArrayList();
		for (ListIterator i = nativeMethod.formals().listIterator(); i.hasNext();) {
			Formal_c parameter = (Formal_c) i.next();
			if(parameter.declType().isPrimitive())
				args.add(nf.Local(pos,parameter.name()));
			else{
				ClassType_c ct = (ClassType_c)parameter.declType().toClass();
				if(null == ct) 
					throw new Error("Problems with unsafe array "+parameter.name());
				
				List methods = ct.methods();
				MethodInstance memberMI=null;
				for(ListIterator j = methods.listIterator();j.hasNext();){
					memberMI = (MethodInstance)j.next();
					if(memberMI.name().equals(getUnsafeAddressMethod)) break;
				}
				if(null == memberMI) 
					throw new Error("Could not find "+getUnsafeAddressMethod+" in class"+ ct.fullName());
				
				Call getAddr = nf.Call(pos,nf.Local(pos,parameter.name()),getUnsafeAddressMethod);
				getAddr = (Call_c)getAddr.methodInstance(memberMI);
				args.add(getAddr);
				//System.out.println("just added " + getAddr);
			
			}
		}
		
		jniCall = (Call_c)jniCall.arguments(args);
		
		ArrayList newStmts = new ArrayList();
		if (nativeMethod.methodInstance().returnType().isVoid())
			newStmts.add(nf.Eval(pos,(Expr)jniCall));
		else
			newStmts.add(nf.Return(pos,(Expr)jniCall));
		
		Block_c newBlock = (Block_c)nf.Block(pos,newStmts);
		
		nativeWrapper = (MethodDecl_c)nativeWrapper.body(newBlock);
		return nativeWrapper;
	}
	
	/**
	 * Create C stub that user will later compile into a dynamic library
	 * contains JNI signature C code which calls the expected X10 routine
	 * @param nativeMethod
	 * @param isOverloaded
	 */
	private void generateStub(MethodDecl_c nativeMethod, boolean isOverloaded){
		

		String newName = generateX10NativeName(nativeMethod);
		if(isOverloaded)
			newName += "__" + generateJavaSignature(nativeMethod);
	

		String jniCall, wrapperCall, wrapperDecl,returnTheValue = "";

		wrapperCall = newName + "(";
		wrapperDecl = "extern " + typeToCString((Type_c)nativeMethod.methodInstance().returnType()) + " ";
		wrapperDecl += wrapperCall;
		
		newName = generateJNIName(nativeMethod,isOverloaded);

		jniCall = "JNIEXPORT "
				+ typeToJNIString((Type_c) nativeMethod.methodInstance()
						.returnType()) + " JNICALL\n" + newName
				+ "(JNIEnv *env, jobject obj";

		if (!nativeMethod.methodInstance().returnType().isVoid())
			returnTheValue = "return ";
		String commaString = "";
		for (ListIterator i = nativeMethod.formals().listIterator(); i
				.hasNext();) {
			Formal_c parameter = (Formal_c) i.next();
			jniCall += ", " + typeToJNIString((Type_c) parameter.declType())
					+ " " + parameter.name();
			wrapperCall += commaString + " " + parameter.name();
			wrapperDecl += commaString
					+ typeToCString((Type_c) parameter.declType()) + " "
					+ parameter.name();
			commaString = ", ";
		}
		jniCall += ")";
		wrapperCall += ")";
		wrapperDecl +=");";
		try {
			wrapperFile.write("\n/* * * * * * * */\n"+wrapperDecl + "\n"+jniCall + "{\n   " + 
					returnTheValue + wrapperCall
					+ ";\n}\n");


		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Problems writing file");
		}
		
	}

	/**
	 * Primitive types get translated to their corresponding JNI type.
	 * Any non-primitive type should be an unsafe memory type,
	 * and get translated to a call to getUnsafeAddress, which
	 * returns an address, which we will put into a long
	 * @param theType
	 * @return JNI name for the type
	 */
	private String typeToJNIString(Type_c theType) {
		if (theType.isPrimitive()) {
			//System.out.println(theType.toString() + " is primitive");
			if (theType.isArray())
				System.out.println("Error!" + theType);
			if (theType.isInt())
				return "jint";
			if (theType.isBoolean())
				return "jboolean";
			if (theType.isByte())
				return "jbyte";
			if (theType.isShort())
				return "jshort";
			if (theType.isLong())
				return "jlong";
			if (theType.isFloat())
				return "jfloat";
			if(theType.isChar())
				return "jchar";
			if (theType.isDouble())
				return "jdouble";
			if (theType.isVoid())
				return "void";
			System.out.println("Error!");
		} else
			return "jlong";// should be a call to long getUnsafeAddress()
		
		return "<unknown>";
	}


	
	private static int containingClassDepth = 0; // use to create stub file for outermost class w/ natives
	
	/** 
	 * Identify native (aka extern) x10 methods and create a wrapper with
	 * the same name.  The wrapper make a JNI call to a routine which
	 * then calls the expected X10 native call.
	 * e.g. 
	 * <code>
	 * class C{
	 * static int extern foo(int x);
	 * }
	 * </code>
	 * would result in java code
	 * <code>
	 * class C {
	 * static int native C_foo(int x);
	 * static int foo(int x) { return C_foo(x);}
	 * }
	 * </code>
	 * 
	 * C_foo is a native C call, which would end up looking like
	 * <code> int Java_C_C_1foo(int x){ return C_foo(x);}</code>
	 * Stub files for each containing class are generated containing
	 * the C wrappers.  It is up to the user to compile these, along
	 * with the actual native implementation of C_foo(int), into
	 * a dynamic library, and ensure that the X10 program can find them
	 */
	public Node rewrite(X10TypeSystem ts, NodeFactory nf) {
		typeSystem = ts;
		PrimitiveType tt = ts.Long();
		boolean seenNativeMethodDecl = false;
		
		ClassBody_c cb = (ClassBody_c) node();
		List members = cb.members();
		HashMap methodHash=null;
		ArrayList listOfNewMethods = new ArrayList();	
		for (ListIterator i = members.listIterator(); i.hasNext();) {
			Object o = i.next();
			if (o instanceof MethodDecl) {
				MethodDecl_c md = (MethodDecl_c) o;
				MethodInstance mi = md.methodInstance();
			
				if (!mi.flags().isNative()){
					listOfNewMethods.add(o);
					continue;
				}

				if (!seenNativeMethodDecl) {
					
					// JNI signature changes depends on whether the method
					// is overloaded or not.  Determine that by scanning
					// through and hashing all native method names
					methodHash = new HashMap();
					for (ListIterator j = members.listIterator(); j.hasNext();) {
						Object theObj = j.next();
						if (!(theObj instanceof MethodDecl))
							continue;
						MethodDecl_c methodDecl = (MethodDecl_c) theObj;
						if (!methodDecl.methodInstance().flags().isNative())
							continue;
						Boolean boolObj = null;

						if (methodHash.containsKey(methodDecl.name())) {
							methodHash.put(methodDecl.name(), methodDecl); // more than one instance
						} else {
							methodHash.put(methodDecl.name(), null);
						}					
					}
					if (0 == containingClassDepth++) {
						createWrapperFile(mi.container().toString());
						generateWrapperPrologue();
					}
					seenNativeMethodDecl = true;
				}
				
				
				boolean isOverLoaded = (null != methodHash.get(md.name()));
				
				//System.out.println("method: "+md.name() +" overload:"+isOverLoaded);
				generateStub(md,isOverLoaded);
				listOfNewMethods.add(createNewNative(md,nf));	
				listOfNewMethods.add(createNativeWrapper(md,nf));
			}
			else
				listOfNewMethods.add(o);
		}
		
		if (seenNativeMethodDecl) {
			--containingClassDepth;
			if (0 == containingClassDepth)
				generateWrapperEpilogue();
		}

		cb = (ClassBody_c)cb.members(listOfNewMethods);
        return cb;
	}
}