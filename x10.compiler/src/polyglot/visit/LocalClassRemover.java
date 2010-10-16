package polyglot.visit;

import java.util.*;


import polyglot.frontend.Job;
import polyglot.types.*;
import polyglot.util.*;
import x10.ast.Block;
import x10.ast.ClassBody;
import x10.ast.ClassDecl;
import x10.ast.ClassMember;
import x10.ast.CodeNode;
import x10.ast.ConstructorCall;
import x10.ast.ConstructorDecl;
import x10.ast.Expr;
import x10.ast.Field;
import x10.ast.Formal;
import x10.ast.Id;
import x10.ast.Local;
import x10.ast.LocalClassDecl;
import x10.ast.New;
import x10.ast.Node;
import x10.ast.NodeFactory;
import x10.ast.ProcedureCall;
import x10.ast.Receiver;
import x10.ast.Special;
import x10.ast.Stmt;
import x10.ast.TypeNode;
import x10.types.ClassDef;
import x10.types.ClassType;
import x10.types.ConstructorDef;
import x10.types.ConstructorInstance;
import x10.types.Context;
import x10.types.FieldDef;
import x10.types.FieldInstance;
import x10.types.Flags;
import x10.types.LocalDef;
import x10.types.Name;
import x10.types.Ref;
import x10.types.SemanticException;
import x10.types.Type;
import x10.types.Types;

// TODO:
//Convert closures to anon
//Add frame classes around anon and local
//now all classes access only final locals
//Convert local and anon to member
//Dup inner member to static
//Remove inner member

public class LocalClassRemover extends ContextVisitor {
    protected class ConstructorCallRewriter extends NodeVisitor {
	protected final List<FieldDef> newFields;
	protected final ClassDef theLocalClass;
	protected ClassDef curr;

	public ConstructorCallRewriter(List<FieldDef> fields, ClassDef ct) {
	    this.newFields = fields;
	    this.theLocalClass = ct;
	}

	public NodeVisitor enter(Node n) {
	    if (n instanceof ClassDecl) {
		ConstructorCallRewriter v = (ConstructorCallRewriter) copy();
		v.curr = ((ClassDecl) n).classDef();
		return v;
	    }
	    return this;
	}

	public Node leave(Node old, Node n, NodeVisitor v) {
	    if (n instanceof New) {
		New neu = (New) n;
		ConstructorInstance ci = neu.constructorInstance();
		ConstructorDef nci = ci.def();
		ClassType container = Types.get(nci.container()).toClass();
		if (container.def() == theLocalClass) {
		    neu = (New) neu.arguments(addArgs(neu, nci, newFields, curr, theLocalClass));
		    neu = neu.constructorInstance(ci.formalTypes(addArgTypes(ci, nci, newFields, curr, theLocalClass)));
		    if (! theLocalClass.flags().isStatic()) {
			Expr q;
			ClassDef outer = Types.get(theLocalClass.outer());
			if (outer == context.currentClassDef())
			    q = nf.This(neu.position()).type(outer.asType());
			else
			    q = nf.This(neu.position(), nf.CanonicalTypeNode(neu.position(), outer.asType())).type(outer.asType());
			neu = neu.qualifier(q);
		    }
		}
		return neu;
	    }
	    if (n instanceof ConstructorCall) {
		ConstructorCall neu = (ConstructorCall) n;
		ConstructorInstance ci = neu.constructorInstance();       
		ConstructorDef nci = ci.def();
		ClassType container = Types.get(nci.container()).toClass();
		if (container.def() == theLocalClass) {
		    neu = (ConstructorCall) neu.arguments(addArgs(neu, nci, newFields, curr, theLocalClass));
		    // This is wrong: we cannot refer to this in a super() call qualifier.
		    // For now, let this pass and assume InnerClassRemover will fix it.
		    if (! theLocalClass.flags().isStatic()) {
			Expr q;
			ClassDef outer = Types.get(theLocalClass.outer());
			if (outer == context.currentClassDef())
			    q = nf.This(neu.position()).type(outer.asType());
			else
			    q = nf.This(neu.position(), nf.CanonicalTypeNode(neu.position(), outer.asType())).type(outer.asType());
			neu = neu.qualifier(q);
		    }
		}
		return neu;
	    }

	    return n;
	}
    }

    protected final InnerClassRemover icrv;

    public LocalClassRemover(InnerClassRemover icrv) {
	super(icrv.job(), icrv.typeSystem(), icrv.nodeFactory());
	this.icrv = icrv;
    }

    Map<Pair<LocalDef, ClassDef>, FieldDef> fieldForLocal = new HashMap<Pair<LocalDef, ClassDef>, FieldDef>();
    Map<ClassDef, List<ClassMember>> orphans = new HashMap<ClassDef, List<ClassMember>>();
    Map<ClassDef,List<FieldDef>> newFields = new HashMap<ClassDef, List<FieldDef>>();

    public Node override(Node parent, Node n) {
	// Find local classes in a block and remove them.
	// Rewrite local classes to instance member classes.
	// Add a field to the local class for each captured local variable.
	// Add a formal to each constructor for each introduced field.
	// Add a field initializer to each constructor for each introduced field.
	// Rewrite constructor calls to pass in the locals.

	// TODO: handle SwitchBlock correctly

	if (n instanceof Block) {
	    final Block b = (Block) n;
	    List<Stmt> ss = new ArrayList<Stmt>(b.statements());
	    for (int i = 0; i < ss.size(); i++) {
		Stmt s = ss.get(i);
		if (s instanceof LocalClassDecl) {
		    LocalClassDecl lcd = (LocalClassDecl) s;                    
		    ClassDecl cd = lcd.decl();

		    // Box locals
		    cd = (ClassDecl) n.visitChild(cd, localBoxer());

		    Flags oldFlags = cd.flags().flags();
		    Flags flags = context.inStaticContext() ? oldFlags.Private().Static() : oldFlags.Private();
		    Id name = nf.Id(cd.name().position(), UniqueID.newID(cd.name().toString()));
		    cd = cd.name(name);
		    cd = cd.flags(cd.flags().flags(flags));
		    cd.classDef().flags(flags);
		    cd.classDef().kind(ClassDef.MEMBER);
		    cd.classDef().name(cd.name().id());
		    cd = renameConstructors(cd, nf);

		    cd = rewriteLocalClass(cd, (List<FieldDef>) hashGet(newFields, cd.classDef(), Collections.<FieldDef>emptyList()));

		    // Nested local classes will be visited later
		    //cd = (ClassDecl) n.visitChild(cd, this);
		    ss.remove(i); // Remove the local class declaration
		    hashAdd(orphans, context.currentClassDef(), cd);

		    if (cd != lcd.decl()) {
			// Rewrite the constructor calls in the remaining statements
			for (int j = i; j < ss.size(); j++) {
			    Stmt sj = ss.get(j);
			    sj = (Stmt) rewriteConstructorCalls(sj, cd.classDef(), (List<FieldDef>)hashGet(newFields, cd.classDef(), Collections.<FieldDef>emptyList()));
			    ss.set(j, sj);
			}
		    }

		    i--;
		}
		else {
		    s = (Stmt) n.visitChild(s, this);
		    ss.set(i, s);
		}
	    }

	    return b.statements(ss);
	}

	return null;
    }

    protected NodeVisitor localBoxer() {
        return new LocalBoxer().context(context().freeze());
    }

    protected class LocalBoxer extends ContextVisitor {
        public LocalBoxer() {
            super(LocalClassRemover.this.job, LocalClassRemover.this.ts, LocalClassRemover.this.nf);
        }

        private boolean inConstructorCall;

        public NodeVisitor enterCall(Node parent, Node n) throws SemanticException {
            LocalBoxer v = (LocalBoxer) super.enterCall(parent, n);
            if (n instanceof ConstructorCall) {
                if (! inConstructorCall) {
                    v = (LocalBoxer) v.copy();
                    v.inConstructorCall = true;
                    return v;
                }
            }
            if (n instanceof ClassBody || n instanceof CodeNode) {
                if (v.inConstructorCall) {
                    v = (LocalBoxer) v.copy();
                    v.inConstructorCall = false;
                    return v;
                }
            }
            return v;
        }

        protected Node leaveCall(Node old, Node n, NodeVisitor v) {
            Context context = this.context();
            Position pos = n.position();
            if (n instanceof Local && ! inConstructorCall) {
                Local l = (Local) n;
                if (!isLocal(context, l.name().id())) {
                    FieldDef fi = boxLocal(l.localInstance().def());
                    if (fi != null) {
                        Field f = nf.Field(pos, makeMissingFieldTarget(fi.asInstance(), pos), nf.Id(pos, fi.name()));
                        f = f.fieldInstance(fi.asInstance());
                        f = (Field) f.type(fi.asInstance().type());
                        return f;
                    }
                }
            }
            return n;
        }

        // Create a field instance for a local.
        private FieldDef boxLocal(LocalDef li) {
            ClassDef curr = currLocalClass();

            if (curr == null)
                // not defined in a local class
                return null;

            Pair<LocalDef, ClassDef> key = new Pair<LocalDef, ClassDef>(li, curr);
            FieldDef fi = fieldForLocal.get(key);
            if (fi != null)
                return fi;

            Position pos = li.position();

            fi = ts.fieldDef(pos, Types.ref(computeConstructedType(curr)), li.flags().Private(), li.type(), li.name());
            fi.setNotConstant();

            curr.addField(fi);

            List<FieldDef> l = hashGet(newFields, curr, new ArrayList<FieldDef>());
            l.add(fi);

            localOfField.put(fi, li);
            fieldForLocal.put(key, fi);
            return fi;
        }

        /** Get the currently enclosing local class, or null. */
        private ClassDef currLocalClass() {
            ClassDef curr = context.currentClassDef();
            while (curr != null) {
                if (curr.isLocal() || curr.isAnonymous())
                    return curr;
                if (curr.isTopLevel())
                    break;
                curr = Types.get(curr.outer());
            }
            return null;
        }
    }

    /**
     * Rename all of the constructors to match the class name.
     */
    private ClassDecl renameConstructors(ClassDecl cd, NodeFactory nf) {
        ClassBody b = cd.body();
        List<ClassMember> newMembers = new ArrayList<ClassMember>();
        List<ClassMember> members = b.members();
        for (ClassMember m : members) {
            if (m instanceof ConstructorDecl) {
        	ConstructorDecl td = (ConstructorDecl) m;
        	td = td.name(nf.Id(td.name().position(), cd.name().id()));
        	newMembers.add(td.name(cd.name()));
            } else {
        	newMembers.add(m);
            }
	}
        return cd.body(b.members(newMembers));
    }

    protected boolean isLocal(Context c, Name name) {
	return c.isLocal(name);
    }

    protected Node leaveCall(Node old, Node n, NodeVisitor v) {

	Position pos = n.position();

	// Convert anonymous classes into member classes
	if (n instanceof New) {
	    New neu = (New) n;

	    ClassBody body = neu.body();

	    if (body == null)
		return neu;

	    // Box locals
	    body = (ClassBody) neu.visitChild(body, localBoxer());

	    // Check if extending a class or an interface.
	    TypeNode superClass = neu.objectType();
	    List<TypeNode> interfaces = Collections.<TypeNode>emptyList();

	    Type supertype = neu.objectType().type();
	    if (supertype instanceof ClassType) {
		ClassType s = (ClassType) supertype;
		if (s.flags().isInterface()) {
		    superClass = defaultSuperType(pos);
		    interfaces = Collections.singletonList(neu.objectType());
		}
	    }

	    Flags oldFlags = neu.anonType().flags();
	    Flags flags = context.inStaticContext() ? oldFlags.Private().Static() : oldFlags.Private();
	    Id name = nf.Id(pos, UniqueID.newID("Anonymous"));
	    ClassDecl cd = nf.ClassDecl(pos, nf.FlagsNode(pos, flags), name, superClass, interfaces, body);

	    ClassDef type = neu.anonType();
	    type.kind(ClassDef.MEMBER);
	    type.name(cd.name().id());
	    type.outer(Types.ref(context.currentClassDef()));
	    type.setPackage(Types.ref(context.package_()));
	    type.flags(flags);

	    cd = cd.classDef(type);

	    ConstructorDecl td = addConstructor(cd, neu);

	    // Add the CI to the class.
	    type.addConstructor(td.constructorDef());

	    {
		// Append the constructor to the body.
		ClassBody b = cd.body();
		List<ClassMember> members = new ArrayList<ClassMember>();
		members.addAll(b.members());
		members.add(td);
		b = b.members(members);
		cd = cd.body(b);
	    }

	    neu = neu.constructorInstance(computeConstructorInstance(td.constructorDef()));
	    neu = neu.anonType(null);

	    if (! flags.isStatic()) {
		neu = neu.qualifier(nf.This(pos).type(context.currentClass()));
	    }

	    cd = rewriteLocalClass(cd, (List<FieldDef>) hashGet(newFields, cd.classDef(), Collections.<FieldDef>emptyList()));
	    hashAdd(orphans, context.currentClassDef(), cd);
	    neu = neu.objectType(nf.CanonicalTypeNode(pos, computeConstructedType(type))).body(null);
	    neu = (New) rewriteConstructorCalls(neu, cd.classDef(), (List<FieldDef>) hashGet(newFields, cd.classDef(), Collections.<FieldDef>emptyList()));
	    return neu;
	}

	// Add any orphaned declarations created below to the class body
	if (n instanceof ClassDecl) {
	    ClassDecl cd = (ClassDecl) n;
	    List<ClassMember> o = orphans.get(cd.classDef());
	    if (o == null)
		return cd;
	    ClassBody b = cd.body();
	    o = b.visitList(o, this);
	    List<ClassMember> members = new ArrayList<ClassMember>();
	    members.addAll(b.members());
	    members.addAll(o);
	    b = b.members(members);
	    return cd.body(b);
	}

	return n;
    }

    protected ConstructorInstance computeConstructorInstance(ConstructorDef cd) {
        return cd.asInstance();
    }

    protected ClassType computeConstructedType(ClassDef type) {
        return type.asType();
    }

    /**
     * The type to be extended when translating an anonymous class that
     * implements an interface.
     */
    protected TypeNode defaultSuperType(Position pos) {
	return nf.CanonicalTypeNode(pos, ts.Object());
    }

    protected ClassDecl rewriteLocalClass(ClassDecl cd, List<FieldDef> newFields) {
	cd = cd.body((ClassBody) rewriteConstructorCalls(cd.body(), cd.classDef(), newFields));
	return icrv.addFieldsToClass(cd, newFields, ts, nf, false);
    }

    protected
    Node rewriteConstructorCalls(Node s, final ClassDef ct, final List<FieldDef> fields) {
	Node r = s.visit(new ConstructorCallRewriter(fields, ct));
	return r;
    }

    // Create a new constructor for an anonymous class.
    ConstructorDecl addConstructor(ClassDecl cd, New neu) {
	// Build the list of formal parameters and list of arguments for the super call.
	List<Formal> formals = new ArrayList<Formal>();
	List<Expr> args = new ArrayList<Expr>();
	List<Ref<? extends Type>> argTypes = new ArrayList<Ref<? extends Type>>();
	int i = 1;

	for (Expr e : neu.arguments()) {
	    Position pos = e.position();
	    Id name = nf.Id(pos, "a" + i);
	    i++;
	    Formal f = nf.Formal(pos, nf.FlagsNode(pos, Flags.FINAL), nf.CanonicalTypeNode(pos, e.type()), name);
	    Local l = nf.Local(pos, name);

	    LocalDef li = ts.localDef(pos, f.flags().flags(), f.type().typeRef(), name.id());
	    li.setNotConstant();
	    f = f.localDef(li);
	    l = l.localInstance(li.asInstance());
	    l = (Local) l.type(li.asInstance().type());

	    formals.add(f);
	    args.add(l);
	    argTypes.add(li.type());
	}

	Position pos = cd.position();

	// Create the super call.
	ConstructorCall cc = nf.SuperCall(pos, args);
	cc = cc.constructorInstance(neu.constructorInstance());
	cc = cc.qualifier(adjustQualifier(neu.qualifier()));

	List<Stmt> statements = new ArrayList<Stmt>();
	statements.add(cc);

	// Create the constructor declaration node and the CI.
	ConstructorDecl td = nf.ConstructorDecl(pos, nf.FlagsNode(pos, Flags.PRIVATE), cd.name(), formals,  nf.Block(pos, statements));
	ConstructorDef ci = ts.constructorDef(pos, Types.ref(cd.classDef().asType()), Flags.PRIVATE, argTypes);
	td = td.constructorDef(ci);

	return td;
    }

    private Expr adjustQualifier(Expr e) {
	if (e instanceof Special) {
	    Special s = (Special) e;
	    if (s.kind() == Special.THIS && s.qualifier() == null) {
		return s.qualifier(nf.CanonicalTypeNode(s.position(), s.type()));
	    }
	}
	return e;
    }

    // Add types to the argument list until it matches the declaration.
    protected List<Type> addArgTypes(ConstructorInstance ci, ConstructorDef nci, List<FieldDef> fields, ClassDef curr, ClassDef theLocalClass) {
        if (nci == null || fields == null || fields.isEmpty() || ci.formalTypes().size() == nci.formalTypes().size())
            return ci.formalTypes();
        List<Type> args = new ArrayList<Type>();
        for (FieldDef fi : fields) {
            if (curr != null && theLocalClass != null && ts.isEnclosed(curr, theLocalClass)) {
                // If in the local class being rewritten, use the type of the boxed local (i.e., field) instead of the local.
                args.add(fi.asInstance().type());
            } else {
                LocalDef li = localOfField.get(fi);
                assert (li != null);
                args.add(li.asInstance().type());
            }
        }
        args.addAll(ci.formalTypes());
        return args;
    }

    // Add local variables to the argument list until it matches the declaration.
    protected List<Expr> addArgs(ProcedureCall n, ConstructorDef nci, List<FieldDef> fields, ClassDef curr, ClassDef theLocalClass) {
	if (nci == null || fields == null || fields.isEmpty() || n.arguments().size() == nci.formalTypes().size())
	    return n.arguments();
	List<Expr> args = new ArrayList<Expr>();
	for (FieldDef fi : fields) {
	    if (curr != null && theLocalClass != null && ts.isEnclosed(curr, theLocalClass)) {
		// If in the local class being rewritten, use the boxed local (i.e., field) instead of the local.
		// This could generate a bad constructor call since the field will refer to 'this' before the superclass
		// is initialized, but we'll patch this up later.
		Position pos = fi.position();
		Field f = nf.Field(pos, makeMissingFieldTarget(fi.asInstance(), pos), nf.Id(pos, fi.name()));
		f = f.fieldInstance(fi.asInstance());
		f = (Field) f.type(fi.asInstance().type());
		args.add(f);
	    }
	    else {
		LocalDef li = localOfField.get(fi);
		if (li != null) {
		    Local l = nf.Local(li.position(), nf.Id(li.position(), li.name()));
		    l = l.localInstance(li.asInstance());
		    l = (Local) l.type(li.asInstance().type());
		    args.add(l);
		}
		else {
		    throw new InternalCompilerError("field " + fi + " created with rev map to null", n.position());
		}
	    }
	}

	args.addAll(n.arguments());
	assert args.size() == nci.formalTypes().size();
	return args;
    }

    Map<FieldDef, LocalDef> localOfField = new HashMap<FieldDef, LocalDef>();

    protected Receiver makeMissingFieldTarget(FieldInstance fi, Position pos) {
	Receiver r;

	Context c = context();

	if (fi.flags().isStatic()) {
	    r = nf.CanonicalTypeNode(pos, fi.container());
	}
	else {
	    // The field is non-static, so we must prepend with
	    // "this", but we need to determine if the "this"
	    // should be qualified.  Get the enclosing class which
	    // brought the field into scope.  This is different
	    // from fi.container().  fi.container() returns a super
	    // type of the class we want.
	    ClassType scope = (ClassType) fi.container();

	    if (! ts.typeEquals(scope, c.currentClass(), context)) {
		r = nf.This(pos.startOf(), nf.CanonicalTypeNode(pos, scope)).type(scope);
	    } else {
		r = nf.This(pos.startOf()).type(scope);
	    }
	}

	return r;
    }

    public static <K,V> V hashGet(Map<K,V> map, K k, V v) {
	V x = map.get(k);
	if (x != null)
	    return x;
	map.put(k, v);
	return v;
    }

    public static <K,V> void hashAdd(Map<K, List<V>> map, K k, V v) {
	List<V> l = map.get(k);
	if (l == null) {
	    l = new ArrayList<V>();
	    map.put(k, l);
	}
	l.add(v);
    }
}
