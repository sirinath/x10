/*
 * Created on Apr 12, 2007
 */
package polyglot.ext.x10.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import polyglot.ast.Call;
import polyglot.ast.Call_c;
import polyglot.ast.Expr;
import polyglot.ast.Expr_c;
import polyglot.ast.Formal;
import polyglot.ast.Id;
import polyglot.ast.Node;
import polyglot.ast.ProcedureCall;
import polyglot.ast.Receiver;
import polyglot.ast.Term;
import polyglot.ext.x10.types.ClosureInstance;
import polyglot.ext.x10.types.ClosureType;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.types.Flags;
import polyglot.types.MethodInstance;
import polyglot.types.ProcedureInstance;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.CodeWriter;
import polyglot.util.CollectionUtil;
import polyglot.util.Position;
import polyglot.util.TypedList;
import polyglot.visit.CFGBuilder;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;

public class ClosureCall_c extends Expr_c implements ClosureCall {
    protected Expr target;

    protected List arguments;

    protected ClosureInstance ci;

    public ClosureCall_c(Position pos, Expr target, List arguments) {
	super(pos);
	assert (target != null && arguments != null);
	this.target= target;
	this.arguments= TypedList.copyAndCheck(arguments, Expr.class, true);
    }

    @Override
    public Term entry() {
	if (!(target instanceof Closure)) {
	    return ((Term) target).entry();
	}
	return listEntry(arguments, this);
    }

    @Override
    public List acceptCFG(CFGBuilder v, List succs) {
	if (!(target instanceof Closure)) { // Don't visit a literal closure here
	    Term t= (Term) target;
	    v.visitCFG(t, listEntry(arguments, this));
	}
	v.visitCFGList(arguments, this);
	return succs;
    }

    public Expr target() {
	return target;
    }

    public ClosureCall target(Expr target) {
	ClosureCall_c n= (ClosureCall_c) copy();
	n.target= target;
	return n;
    }

    /** Get the method instance of the call. */
    public ClosureInstance closureInstance() {
	return this.ci;
    }

    /** Set the method instance of the call. */
    public ClosureCall closureInstance(ClosureInstance ci) {
	if (ci == this.ci)
	    return this;
	ClosureCall_c n= (ClosureCall_c) copy();
	n.ci= ci;
	return n;
    }

    public ProcedureInstance procedureInstance() {
	return this.ci;
    }

    /** Get the actual arguments of the call. */
    public List arguments() {
	return this.arguments;
    }

    /** Set the actual arguments of the call. */
    public ProcedureCall arguments(List arguments) {
	ClosureCall_c n= (ClosureCall_c) copy();
	n.arguments= TypedList.copyAndCheck(arguments, Expr.class, true);
	return n;
    }

    /** Reconstruct the call. */
    protected ClosureCall_c reconstruct(Expr target, List arguments) {
	if (target != this.target || !CollectionUtil.equals(arguments, this.arguments)) {
	    ClosureCall_c n= (ClosureCall_c) copy();
	    n.target= target;
	    n.arguments= TypedList.copyAndCheck(arguments, Expr.class, true);
	    return n;
	}
	return this;
    }

    /** Visit the children of the call. */
    public Node visitChildren(NodeVisitor v) {
	Expr target= (Expr) visitChild(this.target, v);
	List arguments= visitList(this.arguments, v);
	return reconstruct(target, arguments);
    }

    public Node buildTypes(TypeBuilder tb) throws SemanticException {
	ClosureCall_c n= (ClosureCall_c) super.buildTypes(tb);

	X10TypeSystem x10ts= (X10TypeSystem) tb.typeSystem();

	List l= new ArrayList(arguments.size());
	for(int i= 0; i < arguments.size(); i++) {
	    l.add(x10ts.unknownType(position()));
	}

	ClosureInstance ci= x10ts.closureInstance(position(), x10ts.Object(), null, x10ts.unknownType(position()), l,
		Collections.EMPTY_LIST);
	return n.closureInstance(ci);
    }

    @Override
    public Node typeCheck(TypeChecker tc) throws SemanticException {
	Type targetType= target.type();

	if (!(targetType instanceof ClosureType)) {
	    throw new SemanticException("The target of a closure call must be a closure, not " + targetType + ".", target.position());
	}
	// TODO handle deptypes, annotations (a la X10Call_c.adjustMI() and friends)
	ClosureType closureType= (ClosureType) targetType;
	List<Type> formalArgTypes= closureType.argumentTypes();
	if (arguments.size() != formalArgTypes.size())
	    throw new SemanticException("Closure call has wrong # of arguments (" + arguments.size() + ", should be " + formalArgTypes.size() + ").", position());
//	List actualArgTypes= new TypedList(new ArrayList(this.arguments.size()), Type.class, false);
	for(int i=0; i < arguments.size(); i++) {
	    Expr actualArg= (Expr) arguments.get(i);
	    final Type formalArgType= formalArgTypes.get(i);
	    final Type actualArgType= actualArg.type();

	    if (!actualArgType.isImplicitCastValid(formalArgType)) {
		throw new SemanticException("Closure call argument must be " + formalArgType + ", not " + actualArgType + ".", actualArg.position());
	    }
	}
	Type returnType= closureType.returnType();

	return this.type(returnType);
    }

    public String toString() {
	StringBuffer buff= new StringBuffer();
	buff.append(target)
	    .append("(");
	for(Iterator iter= arguments.iterator(); iter.hasNext(); ) {
	    Expr arg= (Expr) iter.next();
	    buff.append(arg);
	    if (iter.hasNext()) buff.append(", ");
	}
	buff.append(")");
	return buff.toString();
    }

	public void prettyPrint(CodeWriter w, PrettyPrinter tr) {
	    w.begin(0);
	    printSubExpr((Expr) target, w, tr);
	    w.write("(");
	    if (arguments.size() > 0) {
		w.allowBreak(2, 2, "", 0); // miser mode
		w.begin(0);
		for(Iterator i = arguments.iterator(); i.hasNext();) {
		    Expr e = (Expr) i.next();
		    print(e, w, tr);
		    if (i.hasNext()) {
			w.write(",");
			w.allowBreak(0, " ");
		    }
		}
		w.end();
	    }
	    w.write(")");
	    w.end();
	}
}
