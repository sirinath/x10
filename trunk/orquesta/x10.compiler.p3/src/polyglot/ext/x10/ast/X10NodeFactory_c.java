/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
package polyglot.ext.x10.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import polyglot.ast.ArrayAccess;
import polyglot.ast.Assign;
import polyglot.ast.Binary;
import polyglot.ast.Block;
import polyglot.ast.BooleanLit;
import polyglot.ast.Call;
import polyglot.ast.CanonicalTypeNode;
import polyglot.ast.Cast;
import polyglot.ast.CharLit;
import polyglot.ast.ClassBody;
import polyglot.ast.ClassDecl;
import polyglot.ast.ClassMember;
import polyglot.ast.Conditional;
import polyglot.ast.ConstructorCall;
import polyglot.ast.ConstructorDecl;
import polyglot.ast.Disamb;
import polyglot.ast.Expr;
import polyglot.ast.ExtFactory;
import polyglot.ast.Field;
import polyglot.ast.FieldDecl;
import polyglot.ast.FlagsNode;
import polyglot.ast.FloatLit;
import polyglot.ast.For;
import polyglot.ast.ForInit;
import polyglot.ast.ForUpdate;
import polyglot.ast.For_c;
import polyglot.ast.Formal;
import polyglot.ast.Id;
import polyglot.ast.If;
import polyglot.ast.Import;
import polyglot.ast.Instanceof;
import polyglot.ast.IntLit;
import polyglot.ast.Local;
import polyglot.ast.LocalDecl;
import polyglot.ast.MethodDecl;
import polyglot.ast.New;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.NodeFactory_c;
import polyglot.ast.PackageNode;
import polyglot.ast.Prefix;
import polyglot.ast.QualifierNode;
import polyglot.ast.Receiver;
import polyglot.ast.Return;
import polyglot.ast.Special;
import polyglot.ast.Stmt;
import polyglot.ast.StringLit;
import polyglot.ast.SwitchElement;
import polyglot.ast.TopLevelDecl;
import polyglot.ast.TypeNode;
import polyglot.ast.Unary;
import polyglot.ast.While;
import polyglot.ext.x10.ExtensionInfo;
import polyglot.ext.x10.types.TypeProperty;
import polyglot.ext.x10.types.X10ConstructorDef;
import polyglot.ext.x10.types.X10Flags;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.types.FieldInstance;
import polyglot.types.Flags;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.CollectionUtil;
import polyglot.util.Position;
import polyglot.util.TypedList;

/**
 * NodeFactory for X10 extension.
 *
 * @author ??
 * @author vj
 * @author Christian Grothoff
 */
public class X10NodeFactory_c extends NodeFactory_c implements X10NodeFactory {

	protected ExtensionInfo extInfo;
	public X10NodeFactory_c(ExtensionInfo extInfo) {
		super(new X10ExtFactory_c(), new X10DelFactory_c());
		this.extInfo = extInfo;
	}

	protected X10NodeFactory_c(ExtFactory extFact) {
		super(extFact);
	}

	public final ExtensionInfo extensionInfo() { return extInfo; }

	public Disamb disamb() {
		return new X10Disamb_c();
	}
	
	public X10AmbTypeNode X10AmbTypeNode(Position pos, Prefix prefix, Id name) {
		X10AmbTypeNode_c n = new X10AmbTypeNode_c(pos, prefix, name);
		n = (X10AmbTypeNode_c) n.ext(extFactory().extAmbTypeNode());
		n = (X10AmbTypeNode_c) n.del(delFactory().delAmbTypeNode());
		return n;
	}

	public X10AmbQualifierNode X10AmbQualifierNode(Position pos, Prefix prefix, Id name) {
		X10AmbQualifierNode_c n = new X10AmbQualifierNode_c(pos, prefix, name);
		n = (X10AmbQualifierNode_c) n.ext(extFactory().extAmbQualifierNode());
		n = (X10AmbQualifierNode_c) n.del(delFactory().delAmbQualifierNode());
		return n;
	}

	public UnknownTypeNode UnknownTypeNode(Position pos) {
		UnknownTypeNode_c n = new UnknownTypeNode_c(pos);
		n = (UnknownTypeNode_c)n.ext(extFactory().extTypeNode());
		n = (UnknownTypeNode_c)n.del(delFactory().delTypeNode());
		return n;
	}
	
	public Return X10Return(Position pos, Expr expr, boolean implicit) {
		Return n = new X10Return_c(pos, expr, implicit);
		n = (Return)n.ext(extFactory().extReturn());
		n = (Return)n.del(delFactory().delReturn());
		return n;
	}

	public Return Return(Position pos, Expr expr) {
		return X10Return(pos, expr, false);
	}

	public TypePropertyNode TypePropertyNode(Position pos, Id name, TypeProperty.Variance variance) {
		TypePropertyNode_c n = new TypePropertyNode_c(pos, name, variance);
		n = (TypePropertyNode_c) n.ext(extFactory().extNode());
		n = (TypePropertyNode_c) n.del(delFactory().delNode());
		return n;
	}

	public TypeParamNode TypeParamNode(Position pos, Id name) {
		TypeParamNode_c n = new TypeParamNode_c(pos, name);
		n = (TypeParamNode_c) n.ext(extFactory().extNode());
		n = (TypeParamNode_c) n.del(delFactory().delNode());
		return n;
	}
	
	public Expr SubtypeTest(Position pos, TypeNode sub, TypeNode sup) {
		SubtypeTest n = new SubtypeTest_c(pos, sub, sup);
		n = (SubtypeTest) n.ext(extFactory().extExpr());
		n = (SubtypeTest) n.del(delFactory().delExpr());
		return n;
	}
	
	public Expr Contains(Position pos, Expr item, Expr collection) {
	    Contains n = new Contains_c(pos, item, collection);
	    n = (Contains) n.ext(extFactory().extExpr());
	    n = (Contains) n.del(delFactory().delExpr());
	    return n;
	}
	
	public X10MLSourceFile X10MLSourceFile(Position position, PackageNode packageName, List<Import> imports, List<TopLevelDecl> decls) {
		 X10MLSourceFile n = new X10MLSourceFile_c(position, packageName, CollectionUtil.nonNullList(imports), CollectionUtil.nonNullList(decls));
		 n = (X10MLSourceFile)n.ext(extFactory().extSourceFile());
		 n = (X10MLSourceFile)n.del(delFactory().delSourceFile());
		 return n;
	}
	
	public AmbDepTypeNode AmbDepTypeNode(Position pos, TypeNode base, List<TypeNode> typeArgs, List<Expr> args, DepParameterExpr dep) {
		AmbDepTypeNode n = new AmbDepTypeNode_c(pos, base, typeArgs, args, dep);
		n = (AmbDepTypeNode)n.ext(extFactory().extTypeNode());
		n = (AmbDepTypeNode)n.del(delFactory().delTypeNode());
		return n;
	}
	public AmbDepTypeNode AmbDepTypeNode(Position pos, TypeNode base, DepParameterExpr dep) {
		return AmbDepTypeNode(pos, base, Collections.EMPTY_LIST, Collections.EMPTY_LIST, dep);
	}

	public Instanceof Instanceof(Position pos, Expr expr, TypeNode type) {
		Instanceof n = new X10Instanceof_c(pos, expr, type);
		n = (Instanceof) n.ext(extFactory().extInstanceof());
		n = (Instanceof) n.del(delFactory().delInstanceof());
		return n;
	}

	public Instanceof DepInstanceof(Position pos, TypeNode compareType,
			DepParameterExpr d, Expr expr) {
		Instanceof n = new DepInstanceof_c(pos, compareType, d, expr);
		n = (Instanceof)n.ext(extFactory().extInstanceof());
		n = (Instanceof)n.del(delFactory().delInstanceof());
		return n;
	}

	private Block asBlock(Stmt statement) {
		if (statement == null || statement instanceof Block)
			return (Block)statement;
		List<Stmt> l = new ArrayList<Stmt>();
		l.add(statement);
		return Block(statement.position(), l);
	}

	// Wrap the body of the async in a Block so as to ease further code transforamtions.
	public Async Async(Position pos, Expr place, List<Expr> clocks, Stmt body) {
		Async a = new Async_c(pos, place, clocks, asBlock(body));
		X10ExtFactory_c ext_fac = (X10ExtFactory_c) extFactory();
		a = (Async) a.ext(ext_fac.extAsyncImpl());
		X10DelFactory_c del_fac = (X10DelFactory_c) delFactory();
		a = (Async) a.del(del_fac.delAsyncImpl());
		return a;
	}

	// Wrap the body of an atomic in a block to facilitate code transformation.
	public Atomic Atomic(Position pos, Expr place, Stmt body) {
		Atomic a = new Atomic_c(pos, place, asBlock(body));
		a = (Atomic) a.ext(extFactory().extExpr());
		a = (Atomic) a.del(delFactory().delExpr());
		return a;
	}

	public Future Future(Position pos, Expr place, TypeNode returnType, Block body) {
		Future f = new Future_c(pos, place, returnType, body);
		X10ExtFactory_c ext_fac = (X10ExtFactory_c) extFactory();
		f = (Future) f.ext(ext_fac.extFutureImpl());
		return (Future) f.del(delFactory().delStmt());
	}

	public Here Here(Position pos) {
		Here f = new Here_c(pos);
		f = (Here) f.ext(extFactory().extStmt());
		return (Here) f.del(delFactory().delStmt());
	}

	// Wrap the body of a When in a conditional to facilitate code transformations
	public When When(Position pos, Expr expr, Stmt statement) {
		When w = new When_c(pos, expr, asBlock(statement));
		w = (When) w.ext(extFactory().extStmt());
		return (When) w.del(delFactory().delStmt());
	}

	public Next Next(Position pos) {
		Next n = new Next_c(pos);
		n = (Next) n.ext(extFactory().extStmt());
		return (Next) n.del(delFactory().delStmt());
	}

	public Now Now(Position pos, Expr expr, Stmt stmt) {
		Now n = new Now_c(pos, expr, stmt);
		n = (Now) n.ext(extFactory().extStmt());
		return (Now) n.del(delFactory().delStmt());
	}

	public ClassBody ClassBody(Position pos, List<ClassMember> members) {
		ClassBody n = new X10ClassBody_c(pos,members);
		n = (ClassBody)n.ext(extFactory().extClassBody());
		n = (ClassBody)n.del(delFactory().delClassBody());
		return n;
	}
	
	@Override
	public ClassDecl ClassDecl(Position pos, FlagsNode flags, Id name, TypeNode superClass, List<TypeNode> interfaces, ClassBody body) {
		return X10ClassDecl(pos, flags, name, Collections.EMPTY_LIST, Collections.EMPTY_LIST, null, superClass, interfaces, body);
	}

	public X10ClassDecl X10ClassDecl(Position pos, FlagsNode flags, Id name, List<TypePropertyNode> typeProperties, List<PropertyDecl> properties,
			DepParameterExpr ci, TypeNode superClass, List<TypeNode> interfaces, ClassBody body) {
		return (X10ClassDecl) TypeDecl(false, pos, flags, name, typeProperties, properties, ci, superClass, interfaces, body);
	}

	public ValueClassDecl ValueClassDecl(Position pos, FlagsNode flags, Id name, List<TypePropertyNode> typeProperties, List<PropertyDecl> properties, DepParameterExpr ci,
			TypeNode superClass, List<TypeNode> interfaces, ClassBody body)
	{
		return (ValueClassDecl) TypeDecl(true, pos, flags, name, typeProperties, properties, ci, superClass, interfaces, body);
	}

	private ClassDecl TypeDecl(boolean isValue, Position pos, FlagsNode flags,
			Id name, List<TypePropertyNode> typeProperties, List<PropertyDecl> properties,  DepParameterExpr ci, TypeNode superClass,
			List<TypeNode> interfaces, ClassBody body)
	{
		X10TypeSystem ts = (X10TypeSystem) this.extensionInfo().typeSystem();
		superClass = ((superClass == null && ! flags.flags().isInterface()) ?
				CanonicalTypeNode(Position.COMPILER_GENERATED, ts.X10Object()) :
				superClass);
		return TypeDecl(isValue, pos, flags, name, typeProperties, properties, superClass, interfaces, body, ci);
	}

	private ClassDecl TypeDecl(boolean isValue, Position pos, FlagsNode flags, Id name, List<TypePropertyNode> typeProperties, List<PropertyDecl> properties, TypeNode superClass, List<TypeNode> interfaces, ClassBody body, DepParameterExpr tci) {
		ClassDecl n = X10ClassDecl_c.make(pos, flags, name, typeProperties, properties, tci, superClass, interfaces,
				body, this, isValue);
		n = (ClassDecl)n.ext(extFactory().extClassDecl());
		n = (ClassDecl)n.del(delFactory().delClassDecl());
		return n;
	}
	public X10ClassDecl X10ClassDecl(Position pos, FlagsNode flags, Id name, TypeNode superClass, List<TypeNode> interfaces, ClassBody body, DepParameterExpr tci) {
		return (X10ClassDecl) TypeDecl(false, pos, flags, name, Collections.EMPTY_LIST, Collections.EMPTY_LIST, superClass, interfaces, body, tci);
	}
	public ValueClassDecl ValueClassDecl(Position pos, FlagsNode flags, Id name, TypeNode superClass, List<TypeNode> interfaces, ClassBody body, DepParameterExpr tci) {
		return ValueClassDecl(pos, flags, name, Collections.EMPTY_LIST, Collections.EMPTY_LIST, tci, superClass, interfaces, body);
	}

	public Await Await(Position pos, Expr expr) {
		Await n = new Await_c(pos, expr);
		n = (Await) n.ext(extFactory().extStmt());
		return (Await) n.del(delFactory().delStmt());
	}

	public X10ArrayAccess1 X10ArrayAccess1(Position pos, Expr array,
										   Expr index)
	{
		X10ArrayAccess1 n = new X10ArrayAccess1_c(pos, array, index);
		n = (X10ArrayAccess1) n.ext(extFactory().extArrayAccess());
		return (X10ArrayAccess1) n.del(delFactory().delArrayAccess());
	}

	public X10ArrayAccess X10ArrayAccess(Position pos, Expr array,
										 List/*<Expr>*/ index)
	{
		// return Call(pos, array, "get", index);
		X10ArrayAccess n = new X10ArrayAccess_c(pos, array, index);
		n = (X10ArrayAccess) n.ext(extFactory().extArrayAccess());
		return (X10ArrayAccess) n.del(delFactory().delArrayAccess());
	}

	public ArrayConstructor ArrayConstructor(Position pos, TypeNode base,
											 boolean unsafe, boolean isValue,
											 Expr d, Expr i)
	{
		ArrayConstructor n = new ArrayConstructor_c(pos, base, unsafe, isValue,
													d, i);
		n = (ArrayConstructor) n.ext(extFactory().extExpr());
		return (ArrayConstructor) n.del(delFactory().delExpr());
	}

	public Point Point(Position pos, List<Expr> exprs) {
		Point n = new Point_c(pos, exprs);
		n = (Point) n.ext(extFactory().extExpr());
		return (Point) n.del(delFactory().delExpr());
	}

	public RemoteCall RemoteCall(Position pos, Receiver target, Id name,
								 List<Expr> arguments)
	{
		RemoteCall n = new RemoteCall_c(pos, target, name, arguments);
		n = (RemoteCall) n.ext(extFactory().extExpr());
		return (RemoteCall) n.del(delFactory().delExpr());
	}


	public Call X10Call(Position pos, Receiver target, Id name, List<TypeNode> typeArguments, List<Expr> args) {
		Call n = new X10Call_c(pos, target, name, typeArguments, args);
		n = (Call) n.ext(extFactory().extExpr());
		return (Call) n.del(delFactory().delExpr());
	}
	
	public Call Call(Position pos, Receiver target, Id name, List<Expr> args) {
		return X10Call(pos, target, name, Collections.EMPTY_LIST, args);
	}
	
	public ConstantDistMaker ConstantDistMaker(Position pos, Expr e1, Expr e2) {
		NodeFactory nf = this;
		TypeSystem ts = this.extensionInfo().typeSystem();
		Receiver x10LangDistributionFactory = ReceiverFromQualifiedName(pos, "x10.lang.dist.factory");
		List<Expr> l = new TypedList<Expr>(new LinkedList<Expr>(), Expr.class, false);
		l.add(e1);
		l.add(e2);
		ConstantDistMaker n = new ConstantDistMaker_c(pos,
				x10LangDistributionFactory,
				Id(pos, "constant"), l);
		n = (ConstantDistMaker) n.ext(extFactory().extExpr());
		return (ConstantDistMaker) n.del(delFactory().delExpr());
	}

	public X10New X10New(Position pos, Expr qualifier, TypeNode objectType, List<TypeNode> typeArguments, List<Expr> arguments, ClassBody body) {
		X10New n = new X10New_c(pos, qualifier, objectType, typeArguments, arguments, body);
		n = (polyglot.ext.x10.ast.X10New) n.ext(extFactory().extNew());
		n = (polyglot.ext.x10.ast.X10New) n.del(delFactory().delNew());
		return n;
	}
	
	public X10New X10New(Position pos, Expr qualifier, TypeNode objectType, List<TypeNode> typeArguments, List<Expr> arguments) {
		return X10New(pos, qualifier, objectType, typeArguments, arguments, null);
	}

	public X10New X10New(Position pos, TypeNode objectType, List<TypeNode> typeArguments, List<Expr> arguments, ClassBody body) {
		return X10New(pos, null, objectType, typeArguments, arguments, body);
	}

	public X10New X10New(Position pos, TypeNode objectType, List<TypeNode> typeArguments, List<Expr> arguments) {
		return X10New(pos, null, objectType, typeArguments, arguments, null);
	}

	public New New(Position pos, Expr qualifier, TypeNode objectType, List<Expr> arguments, ClassBody body) {
		return X10New(pos, qualifier, objectType, Collections.EMPTY_LIST, arguments, body);
	}

//	 Wrap the body in a block to facilitate code transformations
	public AtEach AtEach(Position pos, Formal formal, Expr domain,
						 List<Expr> clocks, Stmt body)
	{
		AtEach n = new AtEach_c(pos, formal, domain, clocks, asBlock(body));
		X10ExtFactory_c ext_fac = (X10ExtFactory_c) extFactory();
		n = (AtEach) n.ext(ext_fac.extAtEachImpl());
		X10DelFactory_c del_fac = (X10DelFactory_c) delFactory();
		n = (AtEach) n.del(del_fac.delAtEachImpl());
		return n;
	}

	public For For(Position pos, List<ForInit> inits, Expr cond, List<ForUpdate> iters, Stmt body) {
		For n = new For_c(pos, inits, cond, iters, asBlock(body));
		n = (For)n.ext(extFactory().extFor());
		n = (For)n.del(delFactory().delFor());
		return n;
	}

//	 Wrap the body in a block to facilitate code transformations
	public X10Loop ForLoop(Position pos, Formal formal, Expr domain, Stmt body)
	{
		X10Loop n = new ForLoop_c(pos, formal, domain, asBlock(body));
		X10ExtFactory_c ext_fac = (X10ExtFactory_c) extFactory();
		n = (X10Loop) n.ext(ext_fac.extForLoopImpl());
		X10DelFactory_c del_fac = (X10DelFactory_c) delFactory();
		n = (X10Loop) n.del(del_fac.delForLoopImpl());
		return n;
	}

//	 Wrap the body in a block to facilitate code transformations
	public ForEach ForEach(Position pos, Formal formal, Expr domain,
						   List<Expr> clocks, Stmt body)
	{
		ForEach n = new ForEach_c(pos, formal, domain, clocks, asBlock(body));
		X10ExtFactory_c ext_fac = (X10ExtFactory_c) extFactory();
		n = (ForEach) n.ext(ext_fac.extForEachImpl());
		X10DelFactory_c del_fac = (X10DelFactory_c) delFactory();
		n = (ForEach) n.del(del_fac.delForEachImpl());
		return n;
	}

	// Wrap the body in a block to facilitate code transformations
	public Finish Finish(Position pos, Stmt body) {
		Finish n = new Finish_c(pos, asBlock(body));
		X10ExtFactory_c ext_fac = (X10ExtFactory_c) extFactory();
		n = (Finish) n.ext(ext_fac.extFinishImpl());
		X10DelFactory_c del_fac = (X10DelFactory_c) delFactory();
		n = (Finish) n.del(del_fac.delFinishImpl());
		return n;
	}

	public DepParameterExpr DepParameterExpr(Position pos, Expr e) {
		DepParameterExpr n = new DepParameterExpr_c(pos, e);
		n = (DepParameterExpr) n.ext(extFactory().extStmt());
		return (DepParameterExpr) n.del(delFactory().delStmt());
	}
	public DepParameterExpr DepParameterExpr(Position pos, List<Formal> formals, Expr e) {
		DepParameterExpr n = new DepParameterExpr_c(pos, formals, e);
		n = (DepParameterExpr) n.ext(extFactory().extStmt());
		return (DepParameterExpr) n.del(delFactory().delStmt());
	}

	public X10ArrayTypeNode X10ArrayTypeNode(Position pos, TypeNode base, boolean isValueType, DepParameterExpr indexedSet)
	{
		X10ArrayTypeNode n = new X10ArrayTypeNode_c(pos, base, isValueType, indexedSet);
		n = (X10ArrayTypeNode) n.ext(extFactory().extTypeNode());
		return (X10ArrayTypeNode) n.del(delFactory().delTypeNode());
	}

	public Assign Assign(Position pos, Expr left, Assign.Operator op,
						 Expr right)
	{
		if (left instanceof X10ArrayAccess) {
			return X10ArrayAccessAssign(pos, (X10ArrayAccess) left, op, right);
		}
		else if (left instanceof X10ArrayAccess1) {
			return X10ArrayAccess1Assign(pos, (X10ArrayAccess1) left, op, right);
		}
		return super.Assign(pos, left, op, right);
	}

	public X10ArrayAccessAssign X10ArrayAccessAssign(Position pos,
													 X10ArrayAccess left,
													 Assign.Operator op,
													 Expr right)
	{
		X10ArrayAccessAssign n = new X10ArrayAccessAssign_c(pos, left, op,
															right);
		n = (X10ArrayAccessAssign) n.ext(extFactory().extArrayAccess());
		return (X10ArrayAccessAssign) n.del(delFactory().delArrayAccess());
	}

	public X10ArrayAccess1Assign X10ArrayAccess1Assign(Position pos,
													   X10ArrayAccess1 left,
													   Assign.Operator op,
													   Expr right)
	{
		X10ArrayAccess1Assign n = new X10ArrayAccess1Assign_c(pos, left, op,
															  right);
		n = (X10ArrayAccess1Assign) n.ext(extFactory().extArrayAccess());
		return (X10ArrayAccess1Assign) n.del(delFactory().delArrayAccess());
	}

	public Binary Binary(Position pos, Expr left, Binary.Operator op,
						 Expr right)
	{
		Binary n = new X10Binary_c(pos, left, op, right);
		n = (Binary) n.ext(extFactory().extBinary());
		n = (Binary) n.del(delFactory().delBinary());
		return n;
	}

	public Unary Unary(Position pos, Unary.Operator op, Expr expr) {
		boolean incOp = (op == Unary.POST_INC || op == Unary.PRE_INC ||
						 op == Unary.POST_DEC || op == Unary.PRE_DEC);
		if (incOp && expr instanceof X10ArrayAccess) {
			return X10ArrayAccessUnary(pos, op, (X10ArrayAccess) expr);
		} else if (incOp && expr instanceof X10ArrayAccess1) {
			return X10ArrayAccess1Unary(pos, op, (X10ArrayAccess1) expr);
		}
		Unary n = new X10Unary_c(pos, op, expr);
		n = (Unary) n.ext(extFactory().extUnary());
		n = (Unary) n.del(delFactory().delUnary());
		return n;
	}

	public X10ArrayAccessUnary X10ArrayAccessUnary(Position pos,
												   Unary.Operator op,
												   X10ArrayAccess expr)
	{
		X10ArrayAccessUnary n = new X10ArrayAccessUnary_c(pos, op, expr);
		n = (X10ArrayAccessUnary) n.ext(extFactory().extArrayAccess());
		return (X10ArrayAccessUnary) n.del(delFactory().delArrayAccess());
	}

	public X10ArrayAccess1Unary X10ArrayAccess1Unary(Position pos,
			Unary.Operator op,
			ArrayAccess expr)
	{
		X10ArrayAccess1Unary n = new X10ArrayAccess1Unary_c(pos, op, expr);
		n = (X10ArrayAccess1Unary) n.ext(extFactory().extArrayAccess());
		return (X10ArrayAccess1Unary) n.del(delFactory().delArrayAccess());
	}

	public X10ArrayAccess1Unary X10ArrayAccess1Unary(Position pos,
													 Unary.Operator op,
													 X10ArrayAccess1 expr)
	{
		X10ArrayAccess1Unary n = new X10ArrayAccess1Unary_c(pos, op, expr);
		n = (X10ArrayAccess1Unary) n.ext(extFactory().extArrayAccess());
		return (X10ArrayAccess1Unary) n.del(delFactory().delArrayAccess());
	}

	public Tuple Tuple(Position pos, Receiver p, Receiver r, List<Expr> a) {
		//Report.report(1, "X10NodeFactory_c making tuple " + p + " " + r + " " + a);
		Tuple n = new Tuple_c(pos, p, r, a);
		n = (Tuple) n.ext(extFactory().extCall());
		n = (Tuple) n.del(delFactory().delCall());
		return n;
	}

	/**
	 * Return a TypeNode representing a <code>dims</code>-dimensional
	 * array of <code>n</code>.
	 */
	public TypeNode array(TypeNode n, Position pos, int dims) {
		if (dims > 0) {
			if (n instanceof CanonicalTypeNode) {
				Type t = ((CanonicalTypeNode) n).type();
				return CanonicalTypeNode(pos,
						t.arrayOf(dims));
			}
			return ArrayTypeNode(pos, array(n, pos, dims - 1));
		}
		return n;
	}

	public X10Formal X10Formal(Position pos, FlagsNode flags, TypeNode type, Id name,
						 List<Formal> vars)
	{
		X10Formal n = new X10Formal_c(pos, flags, type, name, vars);
		n = (X10Formal) n.ext(extFactory().extFormal());
		n = (X10Formal) n.del(delFactory().delFormal());
		return n;
	}

	public Formal Formal(Position pos, FlagsNode flags, TypeNode type, Id name)
	{
		return X10Formal(pos, flags, type, name, null);
	}

	public ParExpr ParExpr(Position pos, Expr expr) {
		ParExpr n = new ParExpr_c(pos, expr);
		n = (ParExpr) n.ext(extFactory().extExpr());
		return (ParExpr) n.del(delFactory().delExpr());
	}

	public PlaceCast PlaceCast(Position pos, Expr place, Expr expr) {
		PlaceCast n = new PlaceCast_c(pos, place, expr);
		n = (PlaceCast) n.ext(extFactory().extExpr());
		return (PlaceCast) n.del(delFactory().delExpr());
	}

	public Field Field(Position pos, Receiver target, Id name) {
		Field n = new X10Field_c(pos, target, name);
		n = (Field) n.ext(extFactory().extField());
		n = (Field) n.del(delFactory().delField());
		return n;
	}

	public FieldDecl FieldDecl(Position pos, DepParameterExpr thisClause, FlagsNode flags, TypeNode type,
							   Id name, Expr init)
	{
		FieldDecl n = new X10FieldDecl_c(pos, thisClause, flags, type, name, init);
		n = (FieldDecl) n.ext(extFactory().extFieldDecl());
		n = (FieldDecl) n.del(delFactory().delFieldDecl());
		return n;
	}

	public Cast Cast(Position pos, TypeNode type, Expr expr) {
		Cast n = new X10Cast_c(pos, type, expr);
		n = (Cast)n.ext(extFactory().extCast());
		n = (Cast)n.del(delFactory().delCast());
		return n;
	}

	public Cast DepCast(Position pos, TypeNode compareType,
			DepParameterExpr d, Expr expr) {
		Cast n = new DepCast_c(pos, compareType, d, expr);
		n = (Cast)n.ext(extFactory().extCast());
		n = (Cast)n.del(delFactory().delCast());
		return n;
	}

	@Override
	public MethodDecl MethodDecl(Position pos, FlagsNode flags, TypeNode returnType,
			Id name,
			List<Formal> formals, List<TypeNode> throwTypes, Block body)
	{
		return X10MethodDecl(pos, flags, returnType, name, Collections.EMPTY_LIST, formals, null, throwTypes, body);
	}

	public MethodDecl X10MethodDecl(Position pos, FlagsNode flags, TypeNode returnType, Id name, List<TypeParamNode> typeParams, List<Formal> formals, DepParameterExpr whereClause, List<TypeNode> throwTypes, Block body) {
		setFormalIndices(formals);
		if (flags != null && flags.flags().contains(X10Flags.ATOMIC))
			return AtomicMethodDecl(pos, flags, returnType, name, typeParams, formals,
					whereClause, throwTypes, body);

		MethodDecl n = new X10MethodDecl_c(pos, flags, returnType, name, typeParams,
				formals, whereClause, throwTypes, body);
		n = (MethodDecl)n.ext(extFactory().extMethodDecl());
		n = (MethodDecl)n.del(delFactory().delMethodDecl());
		return n;
	}
	
	public MethodDecl AtomicMethodDecl(Position pos, FlagsNode flags, TypeNode returnType,
			Id name, List<TypeParamNode> typeParams,
			List<Formal> formals, DepParameterExpr whereClause, List<TypeNode> throwTypes, Block body)
	{
		flags = flags.flags(flags.flags().clear(X10Flags.ATOMIC).set(X10Flags.SAFE));
		List<Stmt> ss = new TypedList<Stmt>(new LinkedList<Stmt>(), Stmt.class, false);
		ss.add(Atomic(pos, Here(pos), body));
		body = Block(pos, ss);
		return AtomicX10MethodDecl(pos, flags, returnType, name, typeParams, formals, whereClause, throwTypes, body);
	}

	public MethodDecl AtomicX10MethodDecl(Position pos, FlagsNode flags, TypeNode returnType, Id name, List<TypeParamNode> typeParams, List<Formal> formals, DepParameterExpr whereClause, List<TypeNode> throwTypes, Block body) {
		setFormalIndices(formals);
		MethodDecl n = new AtomicMethodDecl_c(pos, flags, returnType, name, typeParams,
				formals, whereClause, throwTypes, body);
		n = (MethodDecl)n.ext(extFactory().extMethodDecl());
		n = (MethodDecl)n.del(delFactory().delMethodDecl());
		return n;
	}

	public LocalDecl LocalDecl(Position pos, FlagsNode flags, TypeNode type,
							   Id name, Expr init)
	{
		LocalDecl n = new X10LocalDecl_c(pos, flags, type, name, init);
		n = (LocalDecl)n.ext(extFactory().extLocalDecl());
		n = (LocalDecl)n.del(delFactory().delLocalDecl());
		return n;
	}
	
	@Override
	public ConstructorDecl ConstructorDecl(Position pos, FlagsNode flags, Id name,
			List<Formal> formals, List<TypeNode> throwTypes,
			Block body) {
		return X10ConstructorDecl(pos, flags, name, null, Collections.EMPTY_LIST, formals, null, throwTypes, body);
	}

	void setFormalIndices(List<Formal> formals) {
		if (formals!=null) {
			int i=0;
			for (Iterator<Formal> it = formals.iterator(); it.hasNext();) {
				X10Formal f = (X10Formal) it.next();
				f.setPositionInArgList(i++);
			}
		}
	}
	public ConstructorDecl X10ConstructorDecl(Position pos, FlagsNode flags,
			Id name, TypeNode returnType,
			List<TypeParamNode> typeParams, List<Formal> formals,
			DepParameterExpr whereClause, List<TypeNode> throwTypes, Block body)
	{
		ConstructorDecl n =
			new X10ConstructorDecl_c(pos, flags,
					name, returnType,
					typeParams, formals,
					whereClause, throwTypes, body);
		setFormalIndices(formals);

		n = (ConstructorDecl)n.ext(extFactory().extConstructorDecl());
		n = (ConstructorDecl)n.del(delFactory().delConstructorDecl());
		return n;
	}
	public PropertyDecl PropertyDecl(Position pos, FlagsNode flags, TypeNode type, Id name) {
		PropertyDecl n = new PropertyDecl_c(pos, flags, type, name, this);
		n = (PropertyDecl)n.ext(extFactory().extFieldDecl());
		n = (PropertyDecl)n.del(delFactory().delFieldDecl());
		return n;
	}
	public PropertyDecl PropertyDecl(Position pos, FlagsNode flags, TypeNode type, Id name, Expr init) {
		PropertyDecl n = new PropertyDecl_c(pos, flags, type, name, init, this);
		n = (PropertyDecl)n.ext(extFactory().extFieldDecl());
		n = (PropertyDecl)n.del(delFactory().delFieldDecl());
		return n;
	}
	public final Special Self(Position pos) {
		return Special(pos, X10Special.SELF, null);
	}
	public Special Special(Position pos, Special.Kind kind, TypeNode outer) {
		Special n = new X10Special_c(pos, kind, outer);
		n = (Special)n.ext(extFactory().extSpecial());
		n = (Special)n.del(delFactory().delSpecial());
		return n;
	}

	public Local Local(Position pos, Id name) {
		Local n = new X10Local_c(pos, name);
		n = (Local)n.ext(extFactory().extLocal());
		n = (Local)n.del(delFactory().delLocal());
		return n;
	}
	public BooleanLit BooleanLit(Position pos, boolean value) {
		BooleanLit n = new X10BooleanLit_c(pos, value);
		n = (BooleanLit)n.ext(extFactory().extBooleanLit());
		n = (BooleanLit)n.del(delFactory().delBooleanLit());
		return n;
	}
	public StmtSeq StmtSeq(Position pos, List<Stmt> statements) {
		StmtSeq n = new StmtSeq_c(pos, statements);
		n = (StmtSeq)n.ext(extFactory().extBlock());
		n = (StmtSeq)n.del(delFactory().delBlock());
		return n;
	}
	// Place the consequent and the alternative in blocks to ease
	// further rewrites of the AST.
	public If If(Position pos, Expr cond, Stmt consequent, Stmt alternative) {
		If n = new X10If_c(pos, cond, asBlock(consequent), asBlock(alternative));
		n = (If)n.ext(extFactory().extIf());
		n = (If)n.del(delFactory().delIf());
		return n;
	}
	public RegionMaker RegionMaker(Position pos, Expr e1, Expr e2) {
		NodeFactory nf = this;
		TypeSystem ts = this.extensionInfo().typeSystem();

		Receiver x10LangRegionFactory = nf.ReceiverFromQualifiedName(pos, "x10.lang.region.factory");
		List<Expr> l = new TypedList<Expr>(new LinkedList<Expr>(), Expr.class, false);
		l.add(e1);
		l.add(e2);
		RegionMaker n = new RegionMaker_c( pos, x10LangRegionFactory, Id(pos, "region"), l  );

		n = (RegionMaker) n.ext(extFactory().extExpr());
		return (RegionMaker) n.del(delFactory().delExpr());
	}
	public RectRegionMaker RectRegionMaker(Position pos, Receiver receiver, Id name, List<Expr> args) {

		RectRegionMaker n = new RectRegionMaker_c( pos, receiver, name, args );

		n = (RectRegionMaker) n.ext(extFactory().extExpr());
		return (RectRegionMaker) n.del(delFactory().delExpr());
	}
	public While While(Position pos, Expr cond, Stmt body) {
		While n = new X10While_c(pos, cond, body);
		n = (While)n.ext(extFactory().extWhile());
		n = (While)n.del(delFactory().delWhile());
		return n;
	}
	public IntLit IntLit(Position pos, IntLit.Kind kind, long value) {
		IntLit n = new X10IntLit_c(pos, kind, value);
		n = (IntLit)n.ext(extFactory().extIntLit());
		n = (IntLit)n.del(delFactory().delIntLit());
		return n;
	}

	public StringLit StringLit(Position pos, String value) {
		StringLit n = new X10StringLit_c(pos, value);
		n = (StringLit)n.ext(extFactory().extStringLit());
		n = (StringLit)n.del(delFactory().delStringLit());
		return n;
	}
	public FloatLit FloatLit(Position pos, FloatLit.Kind kind, double value) {
		FloatLit n = new X10FloatLit_c(pos, kind, value);
		n = (FloatLit)n.ext(extFactory().extFloatLit());
		n = (FloatLit)n.del(delFactory().delFloatLit());
		return n;
	}
	public CharLit CharLit(Position pos, char value) {
		CharLit n = new X10CharLit_c(pos, value);
		n = (CharLit)n.ext(extFactory().extCharLit());
		n = (CharLit)n.del(delFactory().delCharLit());
		return n;
	}

	public AssignPropertyCall AssignPropertyCall(Position pos, List<TypeNode> typeArgs, List<Expr> args) {
		AssignPropertyCall  n = new AssignPropertyCall_c(pos, typeArgs, args);
		n = (AssignPropertyCall) n.ext(extFactory().extExpr());
		n= (AssignPropertyCall) n.del(delFactory().delExpr());
		return n;
	}
	public AssignPropertyBody AssignPropertyBody(Position pos, List<Stmt> statements, 
			X10ConstructorDef ci, List<FieldInstance> fi) {
		AssignPropertyBody  n = new AssignPropertyBody_c(pos, statements, ci, fi);
		n = (AssignPropertyBody) n.ext(extFactory().extStmt());
		n= (AssignPropertyBody) n.del(delFactory().delStmt());
		return n;
	}
	public Conditional Conditional(Position pos, Expr cond, Expr consequent, Expr alternative) {
		Conditional n = new X10Conditional_c(pos, cond, consequent, alternative);
		n = (Conditional)n.ext(extFactory().extConditional());
		n = (Conditional)n.del(delFactory().delConditional());
		return n;
	}

	public ConstructorCall X10ThisCall(Position pos, Expr outer, List<TypeNode> typeArgs, List<Expr> args) {
		return X10ConstructorCall(pos, ConstructorCall.THIS, outer, typeArgs, args);
	}

	public ConstructorCall X10ThisCall(Position pos, List<TypeNode> typeArgs, List<Expr> args) {
		return X10ConstructorCall(pos, ConstructorCall.THIS, null, typeArgs, args);
	}

	public ConstructorCall X10SuperCall(Position pos, Expr outer, List<TypeNode> typeArgs, List<Expr> args) {
		return X10ConstructorCall(pos, ConstructorCall.THIS, outer, typeArgs, args);
	}

	public ConstructorCall X10SuperCall(Position pos, List<TypeNode> typeArgs, List<Expr> args) {
		return X10ConstructorCall(pos, ConstructorCall.THIS, null, typeArgs, args);
	}

	public ConstructorCall X10ConstructorCall(Position pos, ConstructorCall.Kind kind, Expr outer, List<TypeNode> typeArgs, List<Expr> args) {
		ConstructorCall n = new X10ConstructorCall_c(pos, kind, outer, CollectionUtil.nonNullList(typeArgs), CollectionUtil.nonNullList(args));
		n = (ConstructorCall)n.ext(extFactory().extConstructorCall());
		n = (ConstructorCall)n.del(delFactory().delConstructorCall());
		return n;
	}
	
	public ConstructorCall ConstructorCall(Position pos, ConstructorCall.Kind kind, Expr outer, List<Expr> args) {
		return X10ConstructorCall(pos, kind, outer, Collections.EMPTY_LIST, args);
	}

	public Closure Closure(Position pos, List<TypeParamNode> typeParams, List<Formal> formals, DepParameterExpr whereClause, TypeNode returnType, List<TypeNode> throwTypes, Block body) {
		Closure n = new Closure_c(pos, typeParams, formals, returnType, whereClause, throwTypes, body);
		X10ExtFactory_c ext_fac = (X10ExtFactory_c) extFactory();
		n = (Closure) n.ext(ext_fac.extClosureImpl());
		X10DelFactory_c del_fac = (X10DelFactory_c) delFactory();
		n = (Closure) n.del(del_fac.delClosureImpl());
		return n;
	}

	public ClosureCall ClosureCall(Position pos, Expr closure, List<TypeNode> typeArgs, List<Expr> args) {
		ClosureCall n = new ClosureCall_c(pos, closure, typeArgs, args);
		n = (ClosureCall) n.ext(extFactory().extExpr());
		n = (ClosureCall) n.del(delFactory().delExpr());
		return n;
	}

	public AnnotationNode AnnotationNode(Position pos, TypeNode tn) {
		AnnotationNode n = new AnnotationNode_c(pos, tn);
		n = (AnnotationNode) n.ext(extFactory().extNode());
		n = (AnnotationNode) n.del(delFactory().delNode());
		return n;
	}

	public TypeNode FunctionTypeNode(Position pos, List<TypeParamNode> typeParams, List<Formal> formals, DepParameterExpr where, TypeNode returnType,
			List<TypeNode> throwTypes) {
		FunctionTypeNode n = new FunctionTypeNode_c(pos, typeParams, formals, returnType, where, throwTypes);
		n = (FunctionTypeNode) n.ext(extFactory().extTypeNode());
		n = (FunctionTypeNode) n.del(delFactory().delTypeNode());
		return n;
	}

	public TypeDecl TypeDecl(Position pos, FlagsNode flags, Id name, List<TypeParamNode> typeParameters, List<Formal> formals, DepParameterExpr where,
			TypeNode type) {
		TypeDecl n = new TypeDecl_c(pos, flags, name, typeParameters, formals, where, type);
		n = (TypeDecl) n.ext(extFactory().extNode());
		n = (TypeDecl) n.del(delFactory().delNode());
		return n;
	}

}
