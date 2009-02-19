package polyglot.ext.x10cpp.visit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import polyglot.ast.Block_c;
import polyglot.ast.Call_c;
import polyglot.ast.CanonicalTypeNode;
import polyglot.ast.ClassDecl_c;
import polyglot.ast.ConstructorDecl_c;
import polyglot.ast.Do_c;
import polyglot.ast.Expr;
import polyglot.ast.FieldDecl_c;
import polyglot.ast.For_c;
import polyglot.ast.Formal;
import polyglot.ast.Formal_c;
import polyglot.ast.If_c;
import polyglot.ast.LocalDecl_c;
import polyglot.ast.MethodDecl_c;
import polyglot.ast.New_c;
import polyglot.ast.Node;
import polyglot.ast.Receiver;
import polyglot.ast.SwitchBlock_c;
import polyglot.ast.Switch_c;
import polyglot.ast.Stmt_c;
import polyglot.ast.Stmt;
import polyglot.ast.Try_c;
import polyglot.ast.TypeNode;
import polyglot.ast.While_c;
import polyglot.types.QName;
import polyglot.ext.x10.ast.Async_c;
import polyglot.ext.x10.ast.AtEach_c;
import polyglot.ext.x10.ast.Closure_c;
import polyglot.ext.x10.ast.DepParameterExpr;
import polyglot.ext.x10.ast.Finish_c;
import polyglot.ext.x10.ast.ForLoop_c;
import polyglot.ext.x10.ast.Next_c;
import polyglot.ext.x10.ast.X10CanonicalTypeNode;
import polyglot.ext.x10.ast.X10Cast_c;
import polyglot.ext.x10.ast.X10ConstructorDecl_c;
import polyglot.ext.x10.ast.X10MethodDecl_c;
import polyglot.ext.x10.ast.X10Special_c;
import polyglot.ext.x10.types.ParameterType;
import polyglot.ext.x10.types.X10ClassDef;
import polyglot.ext.x10.types.X10ClassType;
import polyglot.ext.x10.types.X10ConstructorDef;
import polyglot.ext.x10.types.X10Flags;
import polyglot.ext.x10.types.X10MethodDef;
import polyglot.ext.x10.types.X10MethodInstance;
import polyglot.ext.x10.types.X10TypeMixin;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.ext.x10.types.X10TypeSystem_c;
import polyglot.ext.x10.visit.StaticNestedClassRemover;
import polyglot.ext.x10cpp.types.X10CPPContext_c;
import polyglot.types.ClassDef;
import polyglot.types.ClassType;
import polyglot.types.Context;
import polyglot.types.FieldInstance;
import polyglot.types.Flags;
import polyglot.types.MethodInstance;
import polyglot.types.Name;
import polyglot.types.Ref;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.VarInstance;
import polyglot.util.CodeWriter;
import polyglot.util.ErrorInfo;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import polyglot.visit.Translator;
import static polyglot.ext.x10cpp.visit.SharedVarsMethods.*;
import static polyglot.ext.x10cpp.visit.ASTQuery.*;
import x10c.util.ClassifiedStream;
import x10c.util.StreamWrapper;

public class Emitter {

    private final Translator tr;
    private ASTQuery query;
    public Emitter(Translator tr) {
        this.tr = tr;
        query = new ASTQuery(tr);
    }
    private static final String[] CPP_KEYWORDS = { // Some are also X10 keywords
        "asm", "auto", "bool", "break", "case", "catch", "char", "class",
        "const", "const_cast", "continue", "default", "delete", "do", "double",
        "dynamic_cast", "else", "enum", "explicit", "export", "extern",
        "false", "float", "for", "friend", "goto", "if", "inline", "int",
        "long", "mutable", "namespace", "new", "operator", "private",
        "protected", "public", "register", "reinterpret_cast", "return",
        "restrict", // Yes, stupid xlC has a "restrict" keyword -- who knew?
        "short", "signed", "sizeof", "static", "static_cast", "struct",
        "switch", "template", "this", "throw", "true", "try", "typedef",
        "typeid", "typename", "union", "unsigned", "using", "virtual", "void",
        "volatile", "wchar_t", "while"
    };
    private static boolean isCPPKeyword(String name) {
        for (int i = 0; i < CPP_KEYWORDS.length; i++) {
            if (CPP_KEYWORDS[i].equals(name))
                return true;
        }
        return false;
    }
    private static String mangle_to_cpp(String str) {
        if (isCPPKeyword(str))
            str = "_kwd__" + str;
        return str.replace("$", "__");
    }
    public static String mangled_method_name(String str) {
        return mangle_to_cpp(str);
    }
    public static String mangled_non_method_name(String str) {
        return mangle_to_cpp(str);
    }
    public static String mangled_field_name(String str) {
        //return "__"+mangle_to_cpp(str);
        //return "x10__"+mangle_to_cpp(str);
        return "FMGL("+mangle_to_cpp(str)+")";
    }
    public static String mangled_parameter_type_name(String str) {
        return "FMGL("+mangle_to_cpp(str)+")";
    }


	/**
	 * Same as n.printBlock(child, w, tr); but without the enterScope call.
	 */
	void printBlock(Node n, Expr child, StreamWrapper w, Translator tr) {
		w.begin(0);
		child.del().translate(w, tr);
		w.end();
	}

	/**
	 * Translate the string representation of a fully-qualified type name.
	 * TODO: rewrite to use QName instead
	 */
	public static String translateFQN(String name) {
		return translateFQN(name, "::");
	}
	public static String translateFQN(String name, String delim) {
		return name.replace("$","__").replaceAll("\\.", delim);
	}
	public static String translate_mangled_FQN(String name) {
		return translate_mangled_FQN(name, "::");
	}
	public static String translate_mangled_FQN(String name, String delim) {
		String src = name;
		String dest = "";
		int fromIndex = 0;
		while (true) {
			boolean finished=false;
			int toIndex = src.indexOf('.', fromIndex);
			if (toIndex < 0){
				finished=true;
				toIndex=src.length();
			}
			String s = src.substring(fromIndex, toIndex);
			dest += mangled_non_method_name(s);
			if (finished) break;
			dest += ".";
			fromIndex = toIndex+1;
		}

		return translateFQN(dest, delim);
	}
	public static String translate_mangled_NSFQN(String name) { // namespace FQN
		String src = name;
		String dest = "";
		int fromIndex = 0;
		while (true) {
			boolean finished=false;
			int toIndex = src.indexOf('.', fromIndex);
			if (fromIndex != 0 && toIndex >= 0)
				dest += ".";
			if (toIndex < 0)
				break;
			String s = src.substring(fromIndex, toIndex);
			dest += mangled_non_method_name(s);
			fromIndex = toIndex+1;
		}

		return translateFQN(dest);
	}

	/**
	 * Print a type as a reference.
	 */
	void printType(Type type, CodeWriter w) {
		w.write(translateType(type, true));
	}

	/**
	 * Translate a type name.
	 */
	static String translateType(Type type) {
		return translateType(type, false);
	}


	/**
	 * Translate a type.
	 *
	 * @param type type to translate
	 * @param asRef whether to make a reference
	 * @return a string representation of the type
	 */
	static String translateType(Type type, boolean asRef) {
		assert (type != null);
		X10TypeSystem_c xts = (X10TypeSystem_c) type.typeSystem();
		type = xts.expandMacros(type);
		if (type.isVoid()) {
			return "void";
		}
		// TODO: handle closures
//		if (((X10TypeSystem) type.typeSystem()).isClosure(type))
//			return translateType(((X10Type) type).toClosure().base(), asRef);
		String name = null;
		if (type.isClass()) {
			X10ClassType ct = (X10ClassType) type.toClass();
			if (ct.isAnonymous())  // FIXME: [IP] Is this ever true?
				name = "__anonymous__"+getId();
			else {
                type = X10TypeMixin.baseType(type);
				X10ClassDef cd = ((X10ClassType) type).x10Def();
				String pat = null;
				if (!asRef)
					pat = getCppBoxRep(cd);
				else
					pat = getCppRep(cd);
				if (pat != null) { 
					List<Type> typeArguments = ct.typeArguments();
					Object[] o = new Object[typeArguments.size()+1];
					int i = 0;
					o[i++] = type;
					for (Type a : typeArguments) {
					    o[i++] = a;
					}
					// FIXME: [IP] Clean up this code!
					return dumpRegex("NativeRep", o, pat);
				}
				else {
					if (ct.def().isNested()) {
						Name mangled = StaticNestedClassRemover.mangleName(ct.def());
						QName pkg = ct.package_() != null ? ct.package_().fullName() : null;
						QName full = QName.make(pkg, mangled);
						name = full.toString();
					} else
						name = ct.fullName().toString();
				}
			}
			if (ct.typeArguments().size() != 0) {
				name += "<";
				int s = ct.typeArguments().size();
				for (Type t: ct.typeArguments()) {
					name += translateType(t, true); // type arguments are always translated as refs
					s--;
					if (s > 0)
						name +=", ";
				}
				if (name.endsWith(">"))
					name += " ";
				name += ">";
			}
		} else if (type instanceof ParameterType) {
			name = ((ParameterType)type).name().toString();
			return mangled_parameter_type_name(name); // parameter types shouldn't be refs
		} else if (type.isNull()) {
			return "x10aux::NullType"; // typedef to something sensible
		} else 
			assert false : type; // unhandled type.
		assert (name != null);
		name = translateFQN(name);
		if (!asRef)
			return name;
		return make_ref(name);
	}

	void printArgumentList(CodeWriter w, X10CPPContext_c c) {
		printArgumentList(w, c, false, true);
	}
	
	void printArgumentList(CodeWriter w, X10CPPContext_c c, boolean omitType) {
		printArgumentList(w, c, omitType, true);
	}
	
	void printArgumentList(CodeWriter w, X10CPPContext_c c, boolean omitType, boolean saved_this_mechanism) {
		for (int i = 0; i < c.variables.size(); i++) {
			if (i > 0) {
				w.write(",");
				w.allowBreak(2, " ");
			}
			VarInstance var = (VarInstance)c.variables.get(i);
			if (!omitType) {
				Type t = var.type();
				if (c.isSPMDVar(var)) {
					assert (t.isClass());
					X10ClassType ct = (X10ClassType) t;
					assert (ct.typeArguments().size() == 1);
					t = ct.typeArguments().get(0);
				}
				String type = translateType(t, true);
				w.write(type + " ");
			}
			String name = var.name().toString();
			if (saved_this_mechanism && name.equals(THIS))
				name = SAVED_THIS;
			else
				name = mangled_non_method_name(name) ;

			w.write(name);
		}
	}


	void printTemplateSignature(List<Type> list, CodeWriter h) {
		int size = list.size();
		if (size != 0) {
			h.write("template<class ");
			for (Type t: list) {
				assert (t instanceof ParameterType);
				h.write(translateType(t));
				size--;
				if (size > 0)
					h.write(", class ");
			}
			h.write(">");
			h.allowBreak(0, " ");
		}
	}
	
	static List<Type> toTypeList(List<Ref<? extends Type>> list) {
		ArrayList<Type> res = new ArrayList<Type>();
		for (Ref<? extends Type> r : list)
			res.add(r.get());
		return res;
	}
	
	void printTemplateInstantiation(X10MethodInstance mi, CodeWriter w) {
		if (mi.typeParameters().size() == 0)
			return;
		w.write("<");
		for (Iterator<Type> i = mi.typeParameters().iterator(); i.hasNext(); ) {
		    final Type at = i.next();
		    w.write(translateType(at, true));
		    if (i.hasNext()) {
		        w.write(",");
		        w.allowBreak(0, " ");
		    }
		}
		w.write(" >");
	}

	public static String voidTemplateInstantiation(int num) {
		StringBuffer b = new StringBuffer();
		b.append("<");
		for (int i = 0; i < num; i++) {
			if (i > 0)
				b.append(", ");
			b.append("void");
		}
		b.append(">");
		return b.toString();
	}

	static MethodInstance getOverridingMethod(X10TypeSystem xts, ClassType localClass, MethodInstance mi, ClassType original) {
		try {
			return xts.findMethod(localClass,xts.MethodMatcher(localClass,mi.name(),mi.formalTypes()),original.def());
		} catch (SemanticException e) {
			return null;
		}
	}

	Type findRootMethodReturnType(X10MethodDef n, Position pos, MethodInstance from) {
		assert from != null;
		// [IP] Optimizations
		X10Flags flags = X10Flags.toX10Flags(from.flags());
		if (flags.isStatic() || flags.isPrivate() || flags.isProperty() || from.returnType().isVoid())
			return from.returnType();

		// [DC] c++ doesn't allow covariant smartptr return types so
		// we are forced to use the same type everywhere and cast
		// on call.  This function gets the one type that we use for
		// all return types, from the root of the class hierarchy.

		// TODO: currently we cannot handle the following code:
		// A new approach is required.
		/*
		interface Cloneable {
			def clone() : Cloneable;
		}

		interface Cloneable2 {
			def clone() : Cloneable2;
		}

		class A implements Cloneable, Cloneable2 {
			public def clone() { return this; }
		}
		*/

		// [DC] TODO: There has to be a better way!
		X10TypeSystem xts = (X10TypeSystem) tr.typeSystem();
		X10ClassType original = (X10ClassType) n.container().get();

		X10ClassType classType = (X10ClassType) from.container();
		X10ClassType superClass = (X10ClassType) classType.superClass();
		List<Type> interfaces = classType.interfaces();
		Type returnType = null;

		if (superClass != null) {
			MethodInstance superMeth = getOverridingMethod(xts,superClass,from,original);
			if (superMeth != null) {
				//System.out.println(from+" overrides "+superMeth);
				returnType = findRootMethodReturnType(n, pos, superMeth);
			}
		}

		for (Type itf : interfaces) {
			X10ClassType itf_ = (X10ClassType) itf;
			// same thing again for interfaces
			MethodInstance superMeth = getOverridingMethod(xts,itf_,from,original);
			if (superMeth != null) {
				//System.out.println(from+" implements "+superMeth);
				Type newReturnType = findRootMethodReturnType(n, pos, superMeth);

				// check -- 
				if (returnType != null && !xts.typeDeepBaseEquals(returnType, newReturnType)) {
					String msg = "Two supertypes declare " + from + " with "
						+ "different return types: " + returnType + " != " + newReturnType;
					tr.job().compiler().errorQueue().enqueue(ErrorInfo.WARNING, msg, pos);
				}
				returnType = newReturnType;
			}
		}

		// if we couldn't find an overridden method, just use the local return type
		return returnType != null ? returnType : from.returnType();
	}

	void printHeader(MethodDecl_c n, CodeWriter h, Translator tr, boolean qualify) {
		printHeader(n, h, tr, n.name().id().toString(), n.returnType().type(), qualify);
	}
	void printHeader(MethodDecl_c n, CodeWriter h, Translator tr, String name, Type ret, boolean qualify) {
		X10Flags flags = X10Flags.toX10Flags(n.flags().flags());

		h.begin(0);

		if (qualify) {
			printTemplateSignature(((X10ClassType)n.methodDef().container().get()).typeArguments(), h);
		}

		X10MethodDef def = (X10MethodDef) n.methodDef();
		printTemplateSignature(toTypeList(def.typeParameters()), h);

		if (!qualify) {
			if (flags.isStatic())
				h.write(flags.retain(Flags.STATIC).translate());
			else if (def.typeParameters().size() != 0) {
			    if (!flags.isFinal()) {
			        // FIXME: [IP] for now just make non-virtual.
			        // In the future, will need to have some sort of dispatch object, e.g. the following:
			        // class Foo { def m[T](a: X): Y { ... } }; class Bar extends Foo { def m[T](a: X): Y { ... } }
			        // translates to
			        // template<class T> class Foo_m {
			        //    Y _(Foo* f, X a) {
			        //       if (typeid(f)==typeid(Foo)) { return f->Foo::m_impl<T>(a); }
			        //       else if (typeid(f)==typeid(Bar)) { return f->Bar::m_impl<T>(a); }
			        //    }
			        // };
			        // class Foo {
			        //    public: template<class T> Y m(X a) { Foo_m<T>::_(this, a); }
			        //    public: template<class T> Y m_impl(X a);
			        // };
			        // class Bar : public Foo {
			        //    public: template<class T> Y m_impl(X a);
			        // };
			        String msg = n.methodDef()+" is both generic and virtual";
			        tr.job().compiler().errorQueue().enqueue(ErrorInfo.WARNING, msg, n.position());
			    }
			}
			else if (!flags.isProperty() && !flags.isPrivate() /*&& !flags.isFinal()*/) // [IP] TODO: find out if this is ok
				h.write("virtual ");
		}
		printType(ret, h);
		h.allowBreak(2, 2, " ", 1);
		if (qualify)
			h.write(translateType(n.methodDef().asInstance().container()) + "::"); 
		//n.print(n.id(), h, tr);
		h.write(mangled_method_name(name)); 
		h.write("(");
		h.allowBreak(2, 2, "", 0);
		h.begin(0);
		for (Iterator i = n.formals().iterator(); i.hasNext(); ) {
			Formal f = (Formal) i.next();
			n.print(f, h, tr);
			if (i.hasNext()) {
				h.write(",");
				h.allowBreak(0, " ");
			}
		}
		h.end();
		h.write(")");
		h.end();
		if (!qualify) {
			assert (!flags.isNative());
			if (n.body() == null && !flags.isExtern() && !flags.isProperty())
				h.write(" = 0");
		}
	}

	void printHeader(Formal_c n, CodeWriter h, Translator tr, boolean qualify) {
//		Flags flags = n.flags().flags();
		h.begin(0);
//		if (flags.isFinal())
//		h.write("const ");
		printType(n.type().type(), h);
		h.write(" ");
		h.write(mangled_non_method_name(n.name().id().toString()));
		h.end();
	}

	private void printAllTemplateSignatures(ClassDef cd, CodeWriter h) {
		if (cd.isNested()) {
			assert (false) : ("Nested class alert!");
			printAllTemplateSignatures(cd.outer().get(), h);
		}
		printTemplateSignature(((X10ClassType)cd.asType()).typeArguments(), h);
	}

	void printRTT(X10ClassType ct, ClassifiedStream h) {
		String x10name = ct.fullName().toString();
		int num_parents = 1 + ct.interfaces().size();
		//
		h.write("class RTT : public x10aux::RuntimeType {"); h.newline(4); h.begin(0);
			h.write("public:"); h.newline();
			h.write("static RTT * const it;"); h.newline();
			h.write("virtual void init() {"); h.newline(4); h.begin(0);
				h.write("initParents("+num_parents);
				h.write(", x10aux::getRTT<" + (ct.superClass()==null ? "x10::lang::Ref" : translateType(ct.superClass())) + " >()");
				for (Type iface : ct.interfaces()) {
					h.write(", x10aux::getRTT<"+translateType(iface)+" >()");
				}
				h.write(");"); h.end(); h.newline();
			h.write("}"); h.newline();
			h.write("virtual std::string name() const { ");
				//TODO: type parameters
				if (ct.typeArguments().isEmpty()) {
					h.write("return \""+x10name+"\"; ");
				} else {
                    h.newline(4); h.begin(0);
					h.write("std::stringstream ss;"); h.newline();
					h.write("ss << \""+x10name+"[\"");
					String comma = "";
					for (Type param : ct.typeArguments()) {
						h.write(comma+" << x10aux::getRTT<"+translateType(param)+">()->name()");
						comma = " << \",\"";
					}
					h.write(" << \"]\";") ; h.newline();
					h.write("return ss.str();"); h.end(); h.newline();
				}
			h.write("}"); h.end(); h.newline();
		h.write("};"); h.newline();
		h.write("virtual const x10aux::RuntimeType *_type () const {"); h.newline(4); h.begin(0);
			h.write("return x10aux::getRTT<"+translateType(ct)+" >();"); h.end(); h.newline();
		h.write("}"); h.newline(); h.forceNewline();
	}
	void printRTTDefn(X10ClassType ct, CodeWriter h) {
		if (ct.typeArguments().isEmpty()) {
			h.write("DEFINE_RTT("+translateType(ct)+");");
		} else {
    		printTemplateSignature(ct.typeArguments(), h);
			h.write("typename "+translateType(ct)+"::RTT * const "+translateType(ct)+"::RTT::it = ");
			h.newline(4);
			h.write("new (x10aux::alloc<typename "+translateType(ct)+"::RTT>()) "+
					"typename "+translateType(ct)+"::RTT();");
		}
		h.newline();
	}

	void printInheritance(ClassDecl_c n, CodeWriter h, Translator tr) {
		String extends_ = n.superClass()==null ? null : translateType(n.superClass().type());
		ArrayList<String> implements_ = new ArrayList<String>();
		for (TypeNode tn : n.interfaces()) {
			implements_.add(translateType(tn.type()));
		}

		// [DC] FIXME: the following is a hack, probably this should happen in
		// the front end but I doubt that will happen any time soon.  It ought
		// to use type objects instead of strings, too.  But I couldn't work
		// out how to do that.

		// it seems extends_==null implies that we are dealing with an
		// interface, since otherwise extends_ is Ref, Value, or some
		// user-defined type.

		if (extends_ == null && implements_.isEmpty()) {
			//Interfaces must always extend something in c++
			implements_.add("x10::lang::Object");
		} else if (extends_ != null && implements_.contains("x10::lang::Object")) {
			//Cosmetic: No point implementing Object if we're already extending something
			implements_.remove("x10::lang::Object");
		}

		String prefix = ":";
		if (extends_ != null) {
			h.write(" "+prefix+" public "+extends_);
			prefix = ",";
		}

		h.allowBreak(2);
		h.begin(0);
		for (String iface : implements_) {
			h.write(" "+prefix+" public virtual "+iface);
			prefix = ",";
		}
		h.end();
	}

	void printHeader(ClassDecl_c n, CodeWriter h, Translator tr, boolean qualify) {
		h.begin(0);
		// Handle generics
		// If it involves Parameter Types then generate C++
		// templates.

		printAllTemplateSignatures(n.classDef(), h);

		h.write("class ");
		assert (!n.classDef().isLocal());
		if (n.classDef().isNested() && !n.classDef().isLocal()) { // FIXME: handle local classes
			assert (false) : ("Nested class alert!");
			h.write(translateType(n.classDef().outer().get().asType()) + "::");
		}
		h.write(mangled_non_method_name(n.name().id().toString())); 

		printInheritance(n, h, tr);

		h.unifiedBreak(0);
		h.end();
	}

	void printHeader(ConstructorDecl_c n, CodeWriter h, Translator tr,
                     boolean define, String name, boolean semicolon) {
        Flags flags = n.flags().flags();

		X10ClassType container = (X10ClassType) n.constructorDef().container().get();
		if (define){
			printTemplateSignature((container).typeArguments(), h);
		}

		X10ConstructorDef def = (X10ConstructorDef) n.constructorDef();
		printTemplateSignature(toTypeList(def.typeParameters()), h);

		h.begin(0);
		String typeName = translateType(container.def().asType());
        // not a virtual method, this function is called only when the static type is precise
        h.write(make_ref(typeName) + " ");
		if (define)
			h.write(typeName + "::");
		h.write(name + "(");
		h.allowBreak(2, 2, "", 0);
		h.begin(0);
		for (Iterator i = n.formals().iterator(); i.hasNext(); ) {
			Formal f = (Formal) i.next();
			n.print(f, h, tr);
			if (i.hasNext()) {
				h.write(",");
				h.allowBreak(0, " ");
			}
		}
		h.end();
		h.write(")");
		h.end();
        // TODO: caller should emit this
		if (semicolon) {
			h.write(";");
			h.newline();
		}
	}

	void printHeader(FieldDecl_c n, CodeWriter h, Translator tr, boolean qualify) {
		Flags flags = n.flags().flags();
		h.begin(0);
		if (!qualify) {
			if (flags.isStatic())
				h.write(flags.retain(Flags.STATIC).translate());
		}

		if (query.hasAnnotation(n, "x10.lang.shared")) {
			h.write("volatile ");
		}

		printType(n.type().type(), h);
		h.allowBreak(2, 2, " ", 1);
		if (qualify)
			h.write(translateType(n.fieldDef().asInstance().container()) + "::");
		h.write(mangled_field_name(n.name().id().toString())); 
		h.end();
	}

	void printHeader(LocalDecl_c n, CodeWriter h, Translator tr, boolean qualify) {
		//Flags flags = n.flags().flags();
		h.begin(0);
		// Let us not generate constants - We will have problem in
		// initializing away from the place where it is declared.
		//if (flags.isFinal())
		//	h.write("const ");
		if (tr.printType()) {
			assert (n != null);
			assert (n.type() != null);
			assert (n.type().type() != null);
			printType(n.type().type(), h);
			h.write(" ");
		}
		h.write(mangled_non_method_name(n.name().id().toString())); 
		h.end();
	}
	
	void enterClosure(X10CPPContext_c.Closures a, Closure_c n) {
		assert (n != null);
//		assert (!a.arrayInitializers.contains(n));
		// if present, need to add again, because captured variables may change
		a.arrayInitializers.add(n);
		a.arrayInitializerParameters.add(null); // actual parameters will be defined later
		a.nesting++;
	}
	void exitClosure(X10CPPContext_c.Closures a) {
		a.nesting--;
	}

    void printExplicitTarget(Call_c n, Receiver target, X10CPPContext_c context, CodeWriter w) {
		if (target instanceof X10Special_c &&
				((X10Special_c)target).kind().equals(X10Special_c.THIS) &&
				(context.inlining || context.insideClosure))
		{
			w.write(SAVED_THIS);
			if (context.insideClosure)
				context.saveEnvVariableInfo(THIS);
		}
		else if (target instanceof Expr) {
			boolean assoc = !(target instanceof New_c);
			n.printSubExpr((Expr) target, assoc, w, tr);
		}
		else if (target != null) {
			n.print(target, w, tr);
		}

		return;
	}
	
	void printDeclarationList(CodeWriter w, X10CPPContext_c c, ArrayList vars) {
		printDeclarationList(w, c, vars, true);
	}
	
	void printDeclarationList(CodeWriter w, X10CPPContext_c c, ArrayList vars, boolean saved_this_mechanism) {
		for (int i = 0; i < vars.size(); i++) {
			VarInstance var = (VarInstance)vars.get(i);
			Type t = var.type();
			if (c.isSPMDVar(var)) {
				assert (t.isClass());
				X10ClassType ct = (X10ClassType) t;
				assert (ct.typeArguments().size() == 1);
				t = ct.typeArguments().get(0);
			}
			String type = translateType(t, true);
			List names = c.getRenameMapping(var);
			if (names == null) {
				String name = var.name().toString();
				if (saved_this_mechanism && name.equals(THIS)) {
					if (c.inlining || c.insideClosure) // FIXME: Krishna, why did you add this test?
						name = SAVED_THIS;
				}
				else {
					if (c.isGlobalVar(var) && c.getDuplicateId(var) != null)
						name += c.getDuplicateId(var);
					name = mangled_non_method_name(name);
				}
				w.write(type + " " + name + "; ");
				w.newline();
			} else {
				for (Iterator n = names.iterator(); n.hasNext(); ) {
					String name = (String) n.next();
					w.write(type + " " + name + "; ");
					w.newline();
				}
			}
		}
	}

	
	void generateSerializationMethods(ClassType type, StreamWrapper sw) {
		// FIXME: Has a lot of string constants. Refactor them
		// into final variables.
		// -Krishna.
		X10ClassType ct = (X10ClassType) type.toClass();
		X10TypeSystem ts = (X10TypeSystem) type.typeSystem();
		X10CPPContext_c context = (X10CPPContext_c) tr.context();
        ClassifiedStream w = sw.body();
		ClassifiedStream h = sw.header();
		h.forceNewline();

		h.write("// Serialization"); h.newline();
		String klass = translateType(type);

		if (!type.flags().isAbstract()) {
            // _serialization_id
            h.write("public: static const x10aux::serialization_id_t "+SERIALIZATION_ID_FIELD+";");
            h.newline();
            printTemplateSignature(ct.typeArguments(), w);
            w.write("const x10aux::serialization_id_t "+klass+"::"+SERIALIZATION_ID_FIELD+" = ");
            w.newline(4);
            w.write("x10aux::DeserializationDispatcher::addDeserializer(");
            if (context.inTemplate()) {
                w.write(klass+"::template "+DESERIALIZER_METHOD+"<Object>);");
            } else {
                w.write(klass+"::"+DESERIALIZER_METHOD+"<Object>);");
            }
            w.newline(); w.forceNewline();
        }

		// _serialize()
		if (!type.flags().isAbstract()) {
            if (type.flags().isFinal()) {
                h.write("public: ");
                h.write("static void "+SERIALIZE_METHOD+"("); h.begin(0);
                h.write(make_ref(klass)+" this_,"); h.newline();
                h.write(SERIALIZATION_BUFFER+"& buf,"); h.newline();
                h.write("x10aux::addr_map& m) "); h.end(); h.newline();
                h.write("{ this_->_serialize_body(buf, m); }"); h.newline();
                h.newline(0); h.forceNewline();
            }
        }

		// _serialize_id()
		if (!type.flags().isAbstract()) {
            h.write("public: ");
            if (!type.flags().isFinal())
                h.write("virtual ");
            h.write("void "+SERIALIZE_ID_METHOD+"("+SERIALIZATION_BUFFER+"& buf, x10aux::addr_map& m) {");
            h.newline(4); h.begin(0);
            h.write("buf.write(this->"+SERIALIZATION_ID_FIELD+",m);"); h.newline();
            h.end() ; h.newline();
            h.write("}"); h.newline(); h.forceNewline();
        }
    

		// _serialize_body()
		h.write("public: ");
		if (!type.flags().isFinal())
			h.write("virtual ");
		h.write("void "+SERIALIZE_BODY_METHOD+"("+SERIALIZATION_BUFFER+"& buf, x10aux::addr_map& m);");
        h.newline(0); h.forceNewline();

		printTemplateSignature(ct.typeArguments(), w);
		w.write("void "+klass+"::"+SERIALIZE_BODY_METHOD+
                    "("+SERIALIZATION_BUFFER+"& buf, x10aux::addr_map& m) {");
		w.newline(4); w.begin(0);
		Type parent = type.superClass();
		if (parent != null && ts.isValueType(parent)) {
			w.write(translateType(parent)+"::"+SERIALIZE_BODY_METHOD+"(buf, m);");
			w.newline();
		}
		for (int i = 0; i < type.fields().size(); i++) {
			if (i != 0)
				w.newline();
			FieldInstance f = (FieldInstance) type.fields().get(i);
			if (f.flags().isStatic() || query.isSyntheticField(f.name().toString()))
				continue;
			String fieldName = mangled_field_name(f.name().toString());
            w.write("buf.write(this->"+fieldName+",m);"); w.newline();
		}
		w.end(); w.newline();
		w.write("}");
		w.newline(); w.forceNewline();

		if (!type.flags().isAbstract()) {
            // _deserialize()
            h.write("public: template<class __T> static ");
            h.write(make_ref("__T")+" "+DESERIALIZER_METHOD+"("+SERIALIZATION_BUFFER+"& buf) {");
            h.newline(4) ; h.begin(0);
            h.write(make_ref(klass)+" this_ = "+
                        "new (x10aux::alloc<"+klass+" >()) "+klass+"();"); h.newline();
            h.write("this_->"+DESERIALIZE_BODY_METHOD+"(buf);"); h.newline();
            h.write("return this_;");
            h.end(); h.newline();
            h.write("}"); h.newline(); h.forceNewline();
        }

		if (!type.flags().isAbstract()) {
            if (type.flags().isFinal()) {
                // _deserialize()
                h.write("public: template<class __T> static ");
                h.write(make_ref("__T")+" "+DESERIALIZE_METHOD+"("+SERIALIZATION_BUFFER+"& buf) {");
                h.newline(4) ; h.begin(0);
                h.write("return "+DESERIALIZER_METHOD+"<__T>(buf);");
                h.end(); h.newline();
                h.write("}"); h.newline(); h.forceNewline();
            }
        }

		// _deserialize_body()
		h.write("public: ");
		h.write("void "+DESERIALIZE_BODY_METHOD+"("+SERIALIZATION_BUFFER+"& buf);"); h.newline(0);
		printTemplateSignature(ct.typeArguments(), w);
		w.write("void "+klass+"::"+DESERIALIZE_BODY_METHOD+"("+SERIALIZATION_BUFFER+"& buf) {");
		w.newline(4); w.begin(0);
		if (parent != null && ts.isValueType(parent)) {
			w.write(translateType(parent)+"::"+DESERIALIZE_BODY_METHOD+"(buf);");
			w.newline();
		}
		for (int i = 0; i < type.fields().size(); i++) {
			if (i != 0)
				w.newline();
			FieldInstance f = (FieldInstance) type.fields().get(i);
			if (f.flags().isStatic() || query.isSyntheticField(f.name().toString()))
				continue;
			String fieldName = mangled_field_name(f.name().toString());
            w.write(fieldName+" = buf.read<"+translateType(f.type(),true)+" >();");
		}
		w.end(); w.newline();
		w.write("}");
		w.newline();
		w.forceNewline();
	}

	public void emitUniqueNS(QName name, ArrayList<String> history, CodeWriter w) {
		if (name == null) return;
		if  (!history.contains(name.toString())) {
			openNamespaces(w, name);
			closeNamespaces(w, name);
			w.newline();
			w.write("using namespace "+translateFQN(name.toString())+";");
			history.add(name.toString());
		}
		return;
	}

    public static void openNamespaces(CodeWriter h, QName name) {
        if (name == null) return;
        openNamespaces(h, name.qualifier());
        h.write("namespace "+name.name()+" { ");
    }
                
    public static void closeNamespaces(CodeWriter h, QName name) {
        if (name == null) return;
        h.write("} ");
        closeNamespaces(h, name.qualifier());
    }
     
	public String makeUnsignedType(Type t) {
		// FIXME: HACK!
		if (t.isInt())
			return "uint32_t";
		if (t.isLong())
			return "uint64_t";
		return "unsigned "+translateType(t);
	}

	private static String dumpRegex(String id, Object[] components, String regex) {
	    String retVal = "";
	    for (int i = 0; i < components.length; i++) {
	        assert ! (components[i] instanceof Object[]);
	    }
	    int len = regex.length();
	    int pos = 0;
	    int start = 0;
	    while (pos < len) {
	    	if (regex.charAt(pos) == '\n') {
	    		retVal +=regex.substring(start, pos);
			retVal += "\n";
	    		start = pos+1;
	    	}
	    	else
	    	if (regex.charAt(pos) == '#') {
	    		retVal += regex.substring(start, pos); //translateFQN(regex.substring(start, pos));
	    		Integer idx = new Integer(regex.substring(pos+1,pos+2));
	    		pos++;
	    		start = pos+1;
	    		if (idx.intValue() >= components.length){
	    			throw new InternalCompilerError("Template '"+id+"' '"+regex+"' uses #"+idx+" (max is "+(components.length-1)+")");
                }
                Object o = components[idx.intValue()];
                if (o instanceof Type) {
                    retVal += translateType((Type)o, true);
                } else if (o != null) {
                    retVal += o.toString();
                }
	    	}
	    	pos++;
	    }
	    retVal += regex.substring(start); //translateFQN(regex.substring(start));
	    return retVal;
	}
	public void dumpRegex(String id, Object[] components, Translator tr, String regex, CodeWriter w) {
		for (int i = 0; i < components.length; i++) {
			assert ! (components[i] instanceof Object[]);
		}
		int len = regex.length();
		int pos = 0;
		int start = 0;
		while (pos < len) {
			if (regex.charAt(pos) == '\n') {
				w.write(regex.substring(start, pos));
				w.newline(0);
				start = pos+1;
			}
			else
			if (regex.charAt(pos) == '#') {
				w.write(regex.substring(start, pos) /*translateFQN(regex.substring(start, pos))*/);
				Integer idx = new Integer(regex.substring(pos+1,pos+2));
				pos++;
				start = pos+1;
				if (idx.intValue() >= components.length) {
					throw new InternalCompilerError("Template '"+id+"' '"+regex+"' uses #"+idx+" (max is "+(components.length-1)+")");
				}
				prettyPrint(components[idx.intValue()], tr, w);
			}
			pos++;
		}
		w.write(regex.substring(start) /*translateFQN(regex.substring(start))*/);
	}
    private void prettyPrint(Object o, Translator tr, CodeWriter w) {
        if (o instanceof Node) {
            Node n = (Node) o;
            X10CPPContext_c context = (X10CPPContext_c) tr.context();
            ((X10CPPTranslator) tr).setContext(n.del().enterScope(context));
            n.del().translate(w, tr);
            ((X10CPPTranslator) tr).setContext(context);
        } else if (o instanceof Type) {
            w.write(translateType((Type)o, true));
        } else if (o != null) {
            w.write(o.toString());
        }
    }
}
// vim:tabstop=4:shiftwidth=4:expandtab
