/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

package x10.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import polyglot.ast.Binary;
import polyglot.ast.Id;
import polyglot.ast.NodeFactory;
import polyglot.ast.TypeNode;
import polyglot.frontend.ExtensionInfo;
import polyglot.frontend.Globals;
import polyglot.frontend.Goal;
import polyglot.frontend.Source;
import polyglot.types.ClassDef;
import polyglot.types.ClassType;
import polyglot.types.CodeDef;
import polyglot.types.CodeInstance;
import polyglot.types.ConstructorDef;
import polyglot.types.ConstructorInstance;
import polyglot.types.Context;
import polyglot.types.DerefTransform;
import polyglot.types.FieldDef;
import polyglot.types.FieldInstance;
import polyglot.types.Flags;
import polyglot.types.InitializerDef;
import polyglot.types.LazyRef;
import polyglot.types.LocalDef;
import polyglot.types.LocalInstance;
import polyglot.types.Matcher;
import polyglot.types.MethodDef;
import polyglot.types.Name;
import polyglot.types.Named;
import polyglot.types.NoClassException;
import polyglot.types.NoMemberException;
import polyglot.types.NullType;
import polyglot.types.ObjectType;
import polyglot.types.ParsedClassType;
import polyglot.types.JavaPrimitiveType;

import polyglot.types.ProcedureDef;
import polyglot.types.ProcedureInstance;
import polyglot.types.QName;
import polyglot.types.Ref;
import polyglot.types.SemanticException;
import polyglot.types.ContainerType;
import polyglot.types.TopLevelResolver;
import polyglot.types.Type;
import polyglot.types.TypeObject;
import polyglot.types.TypeSystem;
import polyglot.types.TypeSystem_c;
import polyglot.types.Types;
import polyglot.types.UnknownType;
import polyglot.types.VarDef;
import polyglot.types.TypeSystem_c.MostSpecificComparator;
import polyglot.util.CollectionUtil;
import polyglot.util.ErrorInfo;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import polyglot.util.Predicate2;
import polyglot.util.TransformingList;
import polyglot.visit.ContextVisitor;
import polyglot.visit.TypeBuilder;
import x10.constraint.XEQV;
import x10.constraint.XFailure;
import x10.constraint.XField;
import x10.constraint.XFormula;
import x10.constraint.XLit;
import x10.constraint.XLocal;
import x10.constraint.XName;
import x10.constraint.XNameWrapper;
import x10.constraint.XTerm;
import x10.constraint.XTerms;
import x10.constraint.XVar;
import x10.parser.X10ParsedName;
import x10.types.XTypeTranslator.XTypeLit_c;
import x10.types.constraints.CConstraint;
import x10.types.constraints.SubtypeConstraint;
import x10.types.constraints.TypeConstraint;
import x10.types.matcher.X10ConstructorMatcher;
import x10.types.matcher.X10FieldMatcher;
import x10.types.matcher.X10MemberTypeMatcher;
import x10.types.matcher.X10MethodMatcher;
import x10.types.matcher.X10TypeMatcher;
import x10.util.ClosureSynthesizer;
import x10.visit.X10TypeBuilder;
import x10.errors.Errors;

/**
 * A TypeSystem implementation for X10.
 *
 * @author Christian Grothoff
 * @author Christoph von Praun
 * @author vj
 */
public class X10TypeSystem_c extends TypeSystem_c {
	public static final int EXPAND_MACROS_DEPTH=25;

    public X10TypeSystem_c() {
        super();
    }

    public ClassDef classDefOf(Type t) {
        t = Types.baseType(t);
        if (t instanceof ClassType)
            return ((ClassType) t).def();
        return null;
    }

    @Override
    public Collection<Type> uncheckedExceptions() {
    	List<Type> l = new ArrayList<Type>(1);
    	l.add(Throwable());
    	return l;
        }

    @Override
    public X10InitializerDef initializerDef(Position pos, Ref<? extends ClassType> container, Flags flags) {
        String fullNameWithThis = "<init>#this";
        XName thisName = new XNameWrapper<Object>(new Object(), fullNameWithThis);
        XVar thisVar = XTerms.makeLocal(thisName);

        return initializerDef(pos, container, flags, thisVar);
    }

    public X10InitializerDef initializerDef(Position pos, Ref<? extends ClassType> container, Flags flags, XVar thisVar) {
        assert_(container);
        return new X10InitializerDef_c(this, pos, container, flags, thisVar);
    }

    public List<MethodInstance> methods(ContainerType t, Name name, List<Type> typeParams, List<Type> argTypes, XVar thisVar, Context context) {
        List<MethodInstance> l = new ArrayList<MethodInstance>();
        for (MethodInstance mi : t.methodsNamed(name)) {
            List<XVar> ys = new ArrayList<XVar>(2);
            List<XVar> xs = new ArrayList<XVar>(2);

            MethodInstance_c.buildSubst(mi, ys, xs, thisVar);
            final XVar[] y = ys.toArray(new XVar[ys.size()]);
            final XVar[] x = xs.toArray(new XVar[ys.size()]);

            mi = new X10TypeEnv_c(context).fixThis((MethodInstance) mi, y, x);

            if (mi.typeParameters().size() != typeParams.size()) {
                continue;
            }

//            TypeConstraint env = new TypeConstraint();
//            for (int j = 0; j < mi.typeParameters().size(); j++) {
//                Type p1 = mi.typeParameters().get(j);
//                Type p2 = typeParams.get(j);
//                env.addTerm(new SubtypeConstraint(p1, p2, true));
//            }
//
//            if (CollectionUtil.allElementwise(argTypes, mi.formalTypes(),
//                    new TypeEqualsInEnvironment((X10Context)context, env)))
//            {
//                l.add(mi);
//            }

            TypeParamSubst tps = new TypeParamSubst(this, typeParams, mi.x10Def().typeParameters());
            if (CollectionUtil.allElementwise(argTypes, tps.reinstantiate(mi.formalTypes()), new TypeEquals(context))) {
                l.add(mi);
            }
        }

        return l;
    }

    public static class TypeEqualsInEnvironment implements Predicate2<Type> {
        Context context;
        TypeConstraint env;
        public TypeEqualsInEnvironment(Context context, TypeConstraint env) {
            this.context = context;
            this.env = env;
        }
        public boolean isTrue(Type o, Type p) {
            TypeConstraint newEnv = new TypeConstraint();
            newEnv.addTerm(new SubtypeConstraint(o, p, true));
            // FIXME: Vijay, why doesn't this work?
            return env.entails(newEnv, context);
        }
    }

    public static class BaseTypeEquals implements Predicate2<Type> {
        Context context;
        public BaseTypeEquals(Context context) {
            this.context = context;
        }
        public boolean isTrue(Type o, Type p) {
            TypeSystem ts = context.typeSystem();
            return ts.typeEquals(Types.baseType(o), Types.baseType(p), context);
        }
    }

    /**
     * Assert that <code>ct</code> implements all abstract methods required;
     * that is, if it is a concrete class, then it must implement all interfaces
     * and abstract methods that it or it's superclasses declare, and if it is
     * an abstract class then any methods that it overrides are overridden
     * correctly.
     */
    public void checkClassConformance(ClassType ct, Context context) throws SemanticException {
        env(context).checkClassConformance(ct);
    }

    public void checkOverride(ClassType ct, MethodInstance mi0, MethodInstance mj0, Context context) throws SemanticException {
        env(context).checkOverride(ct, mi0, mj0);
    }


    public MethodInstance findImplementingMethod(ClassType ct, MethodInstance mi, boolean includeAbstract, Context context) {

        XVar thisVar = ((X10ClassDef) ct.def()).thisVar(); // XTerms.makeLocal(XTerms.makeFreshName("this"));

        List<XVar> ys = new ArrayList<XVar>(2);
        List<XVar> xs = new ArrayList<XVar>(2);
        MethodInstance_c.buildSubst(mi, ys, xs, thisVar);
        MethodInstance_c.buildSubst(ct, ys, xs, thisVar);
        final XVar[] y = ys.toArray(new XVar[ys.size()]);
        final XVar[] x = xs.toArray(new XVar[ys.size()]);

        mi = new X10TypeEnv_c(context).fixThis( mi, y, x);

        ContainerType curr = ct;
        while (curr != null) {
            List<MethodInstance> possible = methods(curr, mi.name(), mi.typeParameters(), mi.formalTypes(), thisVar, context);
            for (MethodInstance mj : possible) {
                if ((includeAbstract || !mj.flags().isAbstract()) 
                        && ((isAccessible(mi, context) && isAccessible(mj, context)) 
                                || isAccessible(mi, context))) {
                    // The method mj may be a suitable implementation of mi.
                    // mj is not abstract, and either mj's container
                    // can access mi (thus mj can really override mi), or
                    // mi and mj are both accessible from ct (e.g.,
                    // mi is declared in an interface that ct implements,
                    // and mj is defined in a superclass of ct).
                    return mj;
                }
            }
            if (curr.typeEquals(mi.container(), context)) {
                // we've reached the definition of the abstract
                // method. We don't want to look higher in the
                // hierarchy; this is not an optimization, but is
                // required for correctness.
                break;
            }

            if (curr instanceof ObjectType) {
                ObjectType ot = (ObjectType) curr;
                if (ot.superClass() instanceof ContainerType) {
                    curr = (ContainerType) ot.superClass();
                }
                else {
                    curr = null;
                }
            }
            else {
                curr = null;
            }
        }
        return null;
    }

    public Type AnnotatedType(Position pos, Type baseType, List<Type> annotations) {
    	baseType = baseType.annotations(annotations);
        return baseType;
    }

    public boolean equalsStruct(Type l, Type r) {
        // if (l instanceof ParameterType && r instanceof ParameterType) {
        // return TypeParamSubst.isSameParameter((ParameterType) l,
        // (ParameterType) r);
        // }
        return equals((TypeObject) l, (TypeObject) r);
    }

    /** Return true if the constraint is consistent. */
    public boolean consistent(CConstraint c) {
        return env(null).consistent(c);
    }

    /** Return true if the constraint is consistent. */
    public boolean consistent(TypeConstraint c, Context context) {
        return env(context).consistent(c);
    }

    /** Return true if constraints in the type are all consistent. */
    public boolean consistent(Type t, Context context) {
        return env(context).consistent(t);
    }

    enum Bound {
        UPPER, LOWER, EQUAL
    }

    @Override
    public Set<FieldInstance> findFields(Type container, TypeSystem_c.FieldMatcher matcher) {
        assert_(container);

        Set<FieldInstance> candidates = new HashSet<FieldInstance>();

        for (Type t : env(matcher.context()).upperBounds(container, true)) {
            Set<FieldInstance> fs = super.findFields(t, matcher);
            candidates.addAll(fs);
        }

        return candidates;
    }

    public TypeDefMatcher TypeDefMatcher(Type container, Name name, List<Type> typeArgs, List<Type> argTypes, Context context) {
        return new TypeDefMatcher(container, name, typeArgs, argTypes, context);
    }

    @Override
    public Type findMemberType(Type container, Name name, Context context) throws SemanticException {
        // FIXME: check for ambiguities
        for (Type t : env(context).upperBounds(container, true)) {
            try {
                return super.findMemberType(t, name, context);
            }
            catch (SemanticException e) {
            }
            try {
                return this.findTypeDef(t, this.TypeDefMatcher(t, name, Collections.<Type>emptyList(), Collections.<Type>emptyList(), context), context);
            }
            catch (SemanticException e) {
            }
        }

        throw new NoClassException(name.toString(), container);
    }



    public List<LocalDef> dummyLocalDefs(List<Ref<? extends Type>> types) {
        List<LocalDef> list = new ArrayList<LocalDef>();
        for (int i = 0; i < types.size(); i++) {
            LocalDef ld = localDef(Position.COMPILER_GENERATED, Flags.FINAL, types.get(i), Name.make("a" + (i + 1)));
            ld.setNotConstant();
            list.add(ld);
        }
        return list;
    }

    @Override
    public List<QName> defaultOnDemandImports() {
        List<QName> l = new ArrayList<QName>(1);
        l.add(QName.make("x10.lang"));
        l.add(QName.make("x10.lang", TypeSystem.DUMMY_PACKAGE_CLASS_NAME.toString()));
        l.add(QName.make("x10.array"));
        return l;
    }

    public List<MacroType> findAcceptableTypeDefs(Type container, TypeDefMatcher matcher, Context context) throws SemanticException {
        assert_(container);
        return env(context).findAcceptableTypeDefs(container, matcher);
    }

    @Override
    protected <S extends ProcedureDef, T extends ProcedureInstance<S>> Comparator<T> mostSpecificComparator(Type ct, Matcher<T> matcher, Context context) {
    	return new X10MostSpecificComparator<S,T>(ct, matcher, context);
    }
    protected static class X10MostSpecificComparator<S extends ProcedureDef, T extends ProcedureInstance<S>> extends MostSpecificComparator<S, T> {
        private Matcher<T> matcher;
        Type container;

        protected X10MostSpecificComparator(Type container, Matcher<T> matcher, Context context) {
            super(context);
            this.matcher = matcher;
            this.container=container;
        }

        public int compare(T p1, T p2) {
    	    if (p1.moreSpecific(container, p2, context))
    		return -1;
    	    if (p2.moreSpecific(container, p1, context))
    		return 1;
    	    return 0;
    	}

        public Type container() {
        	return container;
        }

    }

    private boolean contains(Collection<Type> c, Type x) {
    	Context cxt = emptyContext();
    	for (Type t : c) {
    		if (typeEquals(t, x, cxt)) {
    			return true;
    		}
    	}
    	return false;
    }

    public static class FilteringMatcher<T, U extends T> extends BaseMatcher<U> {
        private Matcher<T> matcher;
        private Class<U> filter;
        public FilteringMatcher(Matcher<T> matcher, Class<U> filter) {
            this.matcher = matcher;
            this.filter = filter;
        }
        @SuppressWarnings("unchecked") // Casting to a generic type argument
        public U instantiate(U matched) throws SemanticException {
            if (filter.isInstance(matched)) {
                T result = matcher.instantiate(matched);
                if (filter.isInstance(result)) {
                    return (U) result;
                }
            }
            return null;
        }
        public java.lang.Object key() { return matcher.key(); }
        public Name name() { return matcher.name(); }
        public java.lang.String signature() { return matcher.signature(); }
    }
    public MacroType findTypeDef(Type container, TypeDefMatcher matcher, Context context) throws SemanticException {
        List<MacroType> acceptable = findAcceptableTypeDefs(container, matcher, context);

        if (acceptable.size() == 0) {
            throw new SemanticException("No valid type definition found for " + matcher.signature() + " in " + container + ".");
        }

        Collection<MacroType> maximal = findMostSpecificProcedures(acceptable,
                new FilteringMatcher<Named, MacroType>(matcher, MacroType.class),
                context);

        if (maximal.size() > 1) { // remove references that resolve to the same type.
        	Collection<Type> reduced = Collections.<Type>emptyList();
        	Collection<MacroType> max2 = Collections.<MacroType>emptyList();
        	for (MacroType mt : maximal) {
        		Type expanded = Types.baseType(mt);
        		if (! reduced.contains(expanded)) {
        			reduced.add(expanded);
        			max2.add(mt);
        		}
        	}
        	 maximal = max2;
        }

        if (maximal.size() > 1) {


            StringBuffer sb = new StringBuffer();
            for (Iterator<MacroType> i = maximal.iterator(); i.hasNext();) {
                MacroType ma = (MacroType) i.next();
                sb.append(ma.returnType());
                sb.append(" ");
                sb.append(ma.container());
                sb.append(".");
                sb.append(ma.signature());
                if (i.hasNext()) {
                    if (maximal.size() == 2) {
                        sb.append(" and ");
                    }
                    else {
                        sb.append(", ");
                    }
                }
            }

            throw new SemanticException("Reference to " + matcher.name() + " is ambiguous, multiple type defintions match: " + sb.toString());
        }

        MacroType mi = maximal.iterator().next();

        return mi;
    }

    public List<MacroType> findTypeDefs(Type container, Name name, ClassDef currClass) throws SemanticException {
        assert_(container);

        // Named n = classContextResolver(container, currClass).find(name);
        //
        // if (n instanceof MacroType) {
        // return (MacroType) n;
        // }

        throw new NoClassException(name.toString(), container);
    }

    @Override
    public Matcher<Named> TypeMatcher(Name name) {
        return new X10TypeMatcher(name);
    }

    @Override
    public Matcher<Named> MemberTypeMatcher(Type container, Name name, Context context) {
        return new X10MemberTypeMatcher(container, name, context);
    }

    public X10ClassDef unknownClassDef() {
        if (unknownClassDef == null) {
            unknownClassDef = createFakeClass(QName.make("<unknown class>"), new SemanticException("Unknown class")).def();
        }
        return (X10ClassDef) unknownClassDef;
    }

    UnknownType unknownType;

    @Override
    public X10ClassType load(String name) {
        QName qualName = QName.make(name);
        try {
            return (X10ClassType) typeForName(qualName);
        }
        catch (SemanticException e) {
            extensionInfo().compiler().errorQueue().enqueue(
                                                    ErrorInfo.INTERNAL_ERROR,
                                                    "Cannot load X10 runtime class \"" + name
                                                            + "\".  Is the X10 runtime library in your classpath or sourcepath?");
            Goal goal = extensionInfo().scheduler().currentGoal();
            if (goal != null)
                goal.fail();
            return createFakeClass(qualName, e);
        }
    }

    protected X10ClassType typeForNameSilent(QName fullName) {
        try {
            if (fullName == null) {
                return (X10ClassType) unknownClassDef().asType();
            }
            return (X10ClassType) typeForName(fullName);
        }
        catch (SemanticException e) {
            return createFakeClass(fullName, e);
        }
    }

    public boolean isUnknown(Type t) {
        return Types.baseType(t) instanceof UnknownType;
    }

    // Temporary hack:
    //   use cache to break cycles checking for unknown type
    //   WARNING: this code is NOT reentrant
    //   FIXME: resolve cycles and remove this cache
    private Map<Type, Boolean> unknownTypeMap = new HashMap<Type, Boolean>();
    public boolean hasUnknown(Type t) {
        unknownTypeMap = new HashMap<Type, Boolean>();
        return hasUnknownType(t);
    }

    private boolean hasUnknownType(Type t) {
        Boolean unknown = unknownTypeMap.get(t);
        if (null == unknown) {
            unknownTypeMap.put(t, false); // break circular check for unknown type (this value may get reset to true below)
        } else {
            return unknown.booleanValue();
        }

        if (isUnknown(t)) {
            unknownTypeMap.put(t, true);
            return true;
        }
        if (t instanceof X10ClassType) {
            X10ClassType ct = (X10ClassType) t;
            if (ct.typeArguments() != null) {
            for (Type a : ct.typeArguments()) {
                if (hasUnknownType(a)) {
                    unknownTypeMap.put(t, true);
                    return true;
                }
            }
            }
            if (ct.x10Def().isFunction()) {
                // Look at the superclass and interfaces (if any)
                if (hasUnknownType(ct.superClass())) {
                    unknownTypeMap.put(t, true);
                    return true;
                }
                for (Type i : ct.interfaces()) {
                    if (hasUnknownType(i)) {
                        unknownTypeMap.put(t, true);
                        return true;
                    }
                }
            }
        }
        /*if (t instanceof AnnotatedType) {
            if (hasUnknownType(X10TypeMixin.baseType(t))) {
                unknownTypeMap.put(t, true);
                return true;
            }
            AnnotatedType at = (AnnotatedType) t;
            List<Type> ann = at.annotations();
            for (Type a : ann) {
                if (hasUnknownType(a)) {
                    unknownTypeMap.put(t, true);
                    return true;
                }
            }
        }*/
        if (t instanceof ConstrainedType) {
            if (hasUnknownType(Types.baseType(t))) {
                unknownTypeMap.put(t, true);
                return true;
            }
            ConstrainedType ct = (ConstrainedType) t;
            for (XTerm x : Types.xclause(ct).constraints()) {
                if (hasUnknown(x)) {
                    unknownTypeMap.put(t, true);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasUnknown(XTerm x) {
        if (x instanceof XFormula) {
            for (XTerm a : ((XFormula) x).arguments()) {
                if (hasUnknown(a))
                    return true;
            }
        } else if (x instanceof XField) {
            XField f = (XField) x;
            if (hasUnknown(f.receiver()))
                return true;
            return hasUnknown(f.field());
        } else if (x instanceof XTypeLit_c) {
            return hasUnknownType(((XTypeLit_c) x).type());
        } else if (x instanceof XEQV) {
            return false;
        } else if (x instanceof XLocal) {
            return hasUnknown(((XLocal) x).name());
        }
        return false;
    }

    private boolean hasUnknown(XName n) {
        if (n instanceof XNameWrapper<?>) {
            Object v = ((XNameWrapper<?>) n).val();
            if (v instanceof LocalDef) {
                LocalDef ld = (LocalDef) v;
                return hasUnknownType(Types.get(ld.type()));
            } else if (v instanceof FieldDef) {
                FieldDef fd = (FieldDef) v;
                return hasUnknownType(Types.get(fd.type())) || hasUnknownType(Types.get(fd.container()));
            }
        }
        return false;
    }

    public X10ClassType createFakeClass(QName fullName, SemanticException error) {
        X10ClassDef cd = (X10ClassDef) createClassDef();
        cd.name(fullName.name());
        cd.position(Position.COMPILER_GENERATED);
        cd.kind(ClassDef.TOP_LEVEL);
        cd.flags(Flags.PUBLIC);
        cd.superType(null);

        try {
            cd.setPackage(Types.ref(packageForName(fullName.qualifier())));
        }
        catch (SemanticException e) {
        }

        return ((X10ParsedClassType) cd.asType()).error(error);
    }

    public X10FieldInstance createFakeField(QName containerName, Flags flags, Name name, SemanticException error) {
        return createFakeField(typeForNameSilent(containerName), flags, name, error);
    }
    public X10FieldInstance createFakeField(Name name, SemanticException error) {
        return createFakeField(unknownClassDef().asType(), Flags.PUBLIC.Static(), name, error);
    }
    public X10FieldInstance createFakeField(ClassType container, Flags flags, Name name, SemanticException error) {
        Position pos = Position.compilerGenerated(container == null ? null : container.position());
        Type type = unknownType(pos);
        ThisDef thisDef = thisDef(pos, Types.ref(container));
        List<Ref<? extends Type>> excTypes = Collections.emptyList();
        X10FieldDef fd = (X10FieldDef) fieldDef(pos, Types.ref(container), flags,
                                                Types.ref(type), name, thisDef);
        return ((X10FieldInstance) fd.asInstance()).error(error);
    }

    public MethodInstance createFakeMethod(QName containerName, Flags flags, Name name, List<Type> typeArgs, List<Type> argTypes, SemanticException error) {
        return createFakeMethod(typeForNameSilent(containerName), flags, name, typeArgs, argTypes, error);
    }
    public MethodInstance createFakeMethod(Name name, List<Type> typeArgs, List<Type> argTypes, SemanticException error) {
        return createFakeMethod(unknownClassDef().asType(), Flags.PUBLIC.Static(), name, typeArgs, argTypes, error);
    }
    public MethodInstance createFakeMethod(ClassType container, Flags flags, Name name, List<Type> typeArgs, List<Type> argTypes, SemanticException error) {
        Position pos = Position.compilerGenerated(container == null ? null : container.position());
        Type returnType = unknownType(pos);
        List<Ref<? extends Type>> args = new ArrayList<Ref<? extends Type>>();
        List<LocalDef> formalNames = new ArrayList<LocalDef>();
        int i = 0;
        for (Type t : argTypes) {
            args.add(Types.ref(t));
            formalNames.add(localDef(pos, Flags.FINAL, Types.ref(t), Name.make("p"+(++i))));
        }
        ThisDef thisDef = thisDef(pos, Types.ref(container));
        X10MethodDef md = (X10MethodDef) methodDef(pos, Types.ref(container), flags,
                                                   Types.ref(returnType), name, Collections.<ParameterType>emptyList(),
                                                   args, thisDef, formalNames, null, null,  null, null);
        List<ParameterType> typeParams = new ArrayList<ParameterType>();
        i = 0;
        for (Type r : typeArgs) {
            typeParams.add(new ParameterType(this, pos, Name.make("T"+(++i)), Types.ref(md)));
        }
        md.setTypeParameters(typeParams);
        return md.asInstance().error(error);
    }

    public X10ConstructorInstance createFakeConstructor(QName containerName, Flags flags, List<Type> typeArgs, List<Type> argTypes, SemanticException error) {
        return createFakeConstructor(typeForNameSilent(containerName).typeArguments(typeArgs), flags, argTypes, error);
    }
    public X10ConstructorInstance createFakeConstructor(ClassType container, Flags flags, List<Type> argTypes, SemanticException error) {
        Position pos = Position.compilerGenerated(container == null ? null : container.position());
        List<Ref<? extends Type>> args = new ArrayList<Ref<? extends Type>>();
        List<LocalDef> formalNames = new ArrayList<LocalDef>();
        int i = 0;
        for (Type t : argTypes) {
            args.add(Types.ref(t));
            formalNames.add(localDef(pos, Flags.FINAL, Types.ref(t), Name.make("p"+(++i))));
        }
        ThisDef thisDef = thisDef(pos, Types.ref(container));
        X10ConstructorDef cd = (X10ConstructorDef) constructorDef(pos, Types.ref(container), flags,
                Types.ref(container), args,
                thisDef, formalNames, null, null,  null);
//        List<Ref<? extends Type>> typeParams = new ArrayList<Ref<? extends Type>>();
//        i = 0;
//        for (Type r : typeArgs) {
//            typeParams.add(Types.ref(new ParameterType_c(this, pos, Name.make("T"+(++i)), Types.ref(cd))));
//        }
//        cd.setTypeParameters(typeParams);
        return ((X10ConstructorInstance) cd.asInstance()).error(error);
    }

    public X10LocalInstance createFakeLocal(Name name, SemanticException error) {
        Position pos = Position.COMPILER_GENERATED;
        Type type = unknownType(pos);
        List<Ref<? extends Type>> excTypes = Collections.emptyList();
        X10LocalDef ld = (X10LocalDef) localDef(pos, Flags.FINAL, Types.ref(type), name);
        return ((X10LocalInstance) ld.asInstance()).error(error);
    }


  /*  private X10ParsedClassType boxType_;

    public Type Box() {
        if (boxType_ == null)
            boxType_ = (X10ParsedClassType) load("x10.lang.Box");
        return boxType_;
    }

    public Type boxOf(Ref<? extends Type> base) {
        return boxOf(Position.COMPILER_GENERATED, base);
    }
*/
    public List<Type> superTypes(ObjectType t) {
        Type sup = t.superClass();
        if (sup == null)
            return t.interfaces();
        List<Type> ts = new ArrayList<Type>();
        ts.add(sup);
        ts.addAll(t.interfaces());
        return ts;
    }

    public List<FunctionType> getFunctionSupertypes(Type t, Context context) {
        if (t == null)
            return Collections.<FunctionType>emptyList();

        List<FunctionType> l = new ArrayList<FunctionType>();

        for (Type bound : env(context).upperBounds(t, false)) {
            if (bound instanceof FunctionType)
                l.add((FunctionType) bound);

            if (bound instanceof ObjectType) {
                ObjectType ot = (ObjectType) bound;
                for (Type ti : superTypes(ot)) {
                    List<FunctionType> supFunctions = getFunctionSupertypes(ti, context);
                    l.addAll(supFunctions);
                }
            }
        }

        return l;
    }

    public boolean isFunctionType(Type t) {
    	t = Types.baseType(t);
    	if (! (t instanceof X10ClassType)) {
    		return false;
    	}
    	X10ClassType xt = (X10ClassType) t;
    	return declaredFunctionType(xt) || ((X10ClassDef) xt.def()).isFunction();
    }
    public boolean declaredFunctionType(X10ClassType t) {
    	for (Type i : t.interfaces()) {
    		if (i instanceof FunctionType)
    			return true;
    	}
    	return false;
    }
    public boolean isExactlyFunctionType(Type t) {
    	t = Types.baseType(t);
    	if (! (t instanceof X10ClassType)) {
    		return false;
    	}
    	X10ClassType xt = (X10ClassType) t;
    	return (xt instanceof FunctionType) || ((X10ClassDef) xt.def()).isFunction();
    }

   /* public boolean isBox(Type t) {
        return hasSameClassDef(t, this.Box());
    }*/

    public boolean isInterfaceType(Type t) {
        t = Types.baseType(t);
        if (t instanceof ClassType)
            if (((ClassType) t).flags().isInterface())
                return true;
        return false;
    }

    static enum Kind {
        NEITHER, EITHER, OBJECT, STRUCT, INTERFACE
    }

    public Kind kind(Type t, Context c) {
        return env(c).kind(t);
    }

    public boolean isParameterType(Type t) {
        t = Types.baseType(t);
        return t instanceof ParameterType;
    }

    public boolean isObjectOrInterfaceType(Type t, Context c) {
        Kind kind = kind(t, c);
        return kind == Kind.OBJECT || kind == Kind.INTERFACE;
    }

    public boolean isObjectType(Type t, Context c) {
        return kind(t, c) == Kind.OBJECT;
    }


    public boolean isStructType(Type t) {
        return kind(t, null) == Kind.STRUCT;
    }

    @Override
    public Type arrayOf(Position pos, Ref<? extends Type> type) {
        // Should be called only by the Java class file loader.
        Type r = Rail();
        return Types.instantiate(r, type);
    }

    public X10ClassDef createClassDef(Source fromSource) {
        return new X10ClassDef_c(this, fromSource);
    }

    public X10ParsedClassType createClassType(Position pos, Ref<? extends ClassDef> def) {
        return new X10ParsedClassType_c(this, pos, def);
    }

    public X10ConstructorInstance createConstructorInstance(Position pos, Ref<? extends ConstructorDef> def) {
        return new X10ConstructorInstance_c(this, pos, (Ref<? extends X10ConstructorDef>) def);
    }

    public MethodInstance createMethodInstance(Position pos, Ref<? extends MethodDef> def) {
        return new MethodInstance_c(this, pos, (Ref<? extends X10MethodDef>) def);
    }

    public X10FieldInstance createFieldInstance(Position pos, Ref<? extends FieldDef> def) {
        return new X10FieldInstance_c(this, pos, (Ref<? extends X10FieldDef>) def);
    }

    public X10LocalInstance createLocalInstance(Position pos, Ref<? extends LocalDef> def) {
        return new X10LocalInstance_c(this, pos, (Ref<? extends X10LocalDef>) def);
    }

    public ClosureInstance createClosureInstance(Position pos, Ref<? extends ClosureDef> def) {
        return new ClosureInstance_c(this, pos, def);
    }

    public ThisInstance createThisInstance(Position pos, Ref<? extends ThisDef> def) {
        return new ThisInstance_c(this, pos, def);
    }

    public ThisDef thisDef(Position pos, Ref<? extends ClassType> type) {
        assert_(type);
        return new ThisDef_c(this, pos, type);
    }

    public List<X10ClassType> allImplementedInterfaces(X10ClassType c) {
    	return allImplementedInterfaces(c, true);
    }

	public List<X10ClassType> allImplementedInterfaces(X10ClassType c, boolean checkSuperClasses) {
		List<X10ClassType> ans =  new ArrayList<X10ClassType>();
		allImplementedInterfaces(c, checkSuperClasses, ans);
		return ans;
	}

	private void allImplementedInterfaces(X10ClassType c, boolean checkSuperClasses, List<X10ClassType> l) {
		Context context = createContext();
		if (c.typeEquals(Object(), context)) {
			return;
		}

		for (Type old : l) {
			if (c.typeEquals(old, context)) {
				return; /* Already been here */
			}
		}

		if (c.flags().isInterface()) {
			l.add(c);
		}

		if (checkSuperClasses && c.superClass() != null) {
			allImplementedInterfaces((X10ClassType)Types.baseType(c.superClass()),
					checkSuperClasses, l);
		}

		for (Type parent : c.interfaces()) {
			allImplementedInterfaces((X10ClassType)Types.baseType(parent),
					checkSuperClasses, l);
		}
	}



    public final Context createContext() {
        return emptyContext();
    }

    public Context emptyContext() {
        return new X10Context_c(this);
    }

    

    @Override
    public Flags legalInitializerFlags() {
        return Static();
    }

    /** All flags allowed for a method. */
    @Override
    public Flags legalMethodFlags() {
        Flags x = legalAccessFlags().Abstract().Static().Final().Native();
        x = x.Clocked().Property().Pure().Atomic();
        return x;

    }

    @Override
    public Flags legalAbstractMethodFlags() {
        Flags x = legalAccessFlags().clearPrivate().Abstract();
        x = x.Clocked().Property().Pure().Atomic();
        return x;
    }

    /** All flags allowed for a top-level class. */
    @Override
    public Flags legalTopLevelClassFlags() {
        return legalAccessFlags().clearPrivate().Abstract().Final().Interface().Clocked().Struct();
    }

    protected final Flags X10_TOP_LEVEL_CLASS_FLAGS =  legalTopLevelClassFlags();

    /** All flags allowed for an interface. */
    @Override
    public Flags legalInterfaceFlags() {
        return legalAccessFlags().Abstract().Interface().Static().Clocked();
    }

    protected final Flags X10_INTERFACE_FLAGS = legalInterfaceFlags();

    /** All flags allowed for a member class. */
    @Override
    public Flags legalMemberClassFlags() {
        return legalAccessFlags().Static().Abstract().Final().Interface().Clocked().Struct();
    }

    protected final Flags X10_MEMBER_CLASS_FLAGS =  legalMemberClassFlags();

    /** All flags allowed for a local class. */
    @Override
    public Flags legalLocalClassFlags() {
        return Abstract().Final().Interface().Struct();
    }

    protected final Flags X10_LOCAL_CLASS_FLAGS =  legalLocalClassFlags();

    @Override
    public Flags legalLocalFlags() {
        return Final().Clocked();
    }

    protected final Flags X10_LOCAL_VARIABLE_FLAGS =  legalLocalFlags();

    @Override
    public Flags legalFieldFlags() {
        return legalAccessFlags().Static().Final().Transient().Property().Clocked();
    }

    protected final Flags X10_FIELD_VARIABLE_FLAGS = legalFieldFlags();

    @Override
    public X10MethodDef methodDef(Position pos, Ref<? extends ContainerType> container, Flags flags,
    		Ref<? extends Type> returnType, Name name,
            List<Ref<? extends Type>> argTypes) {
    	return methodDef(pos, container, flags, returnType, name, argTypes, null);
    }

    public X10MethodDef methodDef(Position pos, Ref<? extends ContainerType> container,
            Flags flags, Ref<? extends Type> returnType, Name name,
            List<Ref<? extends Type>> argTypes,  Ref<? extends Type> offerType)
    {
        ThisDef thisDef = ((X10ClassType) Types.get(container)).x10Def().thisDef();
        assert (!name.toString().contains(AtDef.DUMMY_AT_ASYNC));
        // set up null thisVar for method def's, so the outer contexts are searched for thisVar.
        return methodDef(pos, container, flags, returnType, name, Collections.<ParameterType>emptyList(), argTypes,
                thisDef, dummyLocalDefs(argTypes), null, null, offerType, null);
    }

    public X10MethodDef methodDef(Position pos, Ref<? extends ContainerType> container,
    		Flags flags, Ref<? extends Type> returnType, Name name,
            List<ParameterType> typeParams, List<Ref<? extends Type>> argTypes,
            ThisDef thisDef, List<LocalDef> formalNames,
            Ref<CConstraint> guard,
            Ref<TypeConstraint> typeGuard,
            Ref<? extends Type> offerType,
            Ref<XTerm> body)
    {
        assert_(container);
        assert_(returnType);
        assert_(typeParams);
        assert_(argTypes);
        return new X10MethodDef_c(this, pos, container, flags, returnType, name, typeParams, argTypes, thisDef, formalNames, guard, typeGuard, offerType, body);
    }

    /**
     * Return a nullable type based on a given type. TODO: rename this to
     * nullableType() -- the name is misleading.
     */
    public Type boxOf(Position pos, Ref<? extends Type> type) {
       return type.get();
      //  X10ParsedClassType box = (X10ParsedClassType) Box();
      //  return X10TypeMixin.instantiate(box, type);
    }

    X10ParsedClassType futureType_;

    public Type futureOf(Position pos, Ref<? extends Type> base) {
        if (futureType_ == null)
            futureType_ = (X10ParsedClassType) load("x10.lang.Future");
        return Types.instantiate(futureType_, base);
    }

    // TODO: [IP] this should be a special CodeInstance instead
    public AsyncDef asyncCodeInstance(Position pos, ThisDef thisDef,
            List<ParameterType> typeParameters,
            Ref<? extends CodeInstance<?>> methodContainer,
            Ref<? extends ClassType> typeContainer, boolean isStatic) {
        // Need to create a new one on each call. Portions of this asyncDef, such as thisVar may be destructively modified later.
        return new AsyncDef_c(this, pos, thisDef, typeParameters, methodContainer, typeContainer, isStatic);
    }

    // TODO: [IP] this should be a special CodeInstance instead
    public AtDef atCodeInstance(Position pos, ThisDef thisDef,
            List<ParameterType> typeParameters,
            Ref<? extends CodeInstance<?>> methodContainer,
            Ref<? extends ClassType> typeContainer, boolean isStatic) {
        // Need to create a new one on each call. Portions of this atDef, such as thisVar may be destructively modified later.
        return new AtDef_c(this, pos, thisDef, typeParameters, methodContainer, typeContainer, isStatic);
    }
    
    public ClosureDef closureDef(Position p, Ref<? extends ClassType> typeContainer, Ref<? extends CodeInstance<?>> methodContainer,
            Ref<? extends Type> returnType, List<Ref<? extends Type>> argTypes, ThisDef thisDef,
            List<LocalDef> formalNames, Ref<CConstraint> guard,
            Ref<? extends Type> offerType) {
        return new ClosureDef_c(this, p, typeContainer, methodContainer, returnType,
        		argTypes, thisDef, formalNames, guard,  offerType);
    }

    public FunctionType closureType(Position p, Ref<? extends Type> returnType,
    		// List<Ref<? extends Type>> typeParams,
    		List<Ref<? extends Type>> argTypes,
            List<LocalDef> formalNames, Ref<CConstraint> guard
          //  Ref<TypeConstraint> typeGuard,
            ) {
        Type rt = Types.get(returnType);
        X10ClassDef def = ClosureSynthesizer.closureBaseInterfaceDef(this, 0 /*typeParams.size()*/, argTypes.size(), rt.isVoid(), formalNames, guard);
        FunctionType ct = (FunctionType) def.asType();
        List<Type> typeArgs = new ArrayList<Type>();
        for (Ref<? extends Type> ref : argTypes) {
            typeArgs.add(Types.get(ref));
        }
        if (!rt.isVoid())
            typeArgs.add(rt);
        return (FunctionType) ct.typeArguments(typeArgs);
    }


    // User-defined structs and do they have zero (haszero)
    // This is not just a cache: we use this map to prevent infinite recursion such as in the case of:
    // struct U(u:U) {}
    public HashMap<X10ClassDef_c, Boolean> structHaszero = new HashMap<X10ClassDef_c, Boolean>();

    /******************** Primitive types as Objects ******************/

    private static final String WRAPPER_PACKAGE = "x10.compilergenerated";

   

    // The only primitive left.
    Type VOID_ = new VoidType(this);

    public Type Void() {
        return VOID_;
    }

    public boolean isVoid(Type t) {
        return t != null &&
                Types.baseType( // in case someone writes:  void{i==1}
                        expandMacros(t)).equals((Object) Void());
    } // do not use typeEquals

    protected X10ClassType Boolean_;

    public X10ClassType Boolean() {
        if (Boolean_ == null)
            Boolean_ = load("x10.lang.Boolean");
        return Boolean_;
    }

    protected X10ClassType Byte_;

    public X10ClassType Byte() {
        if (Byte_ == null)
            Byte_ = load("x10.lang.Byte");
        return Byte_;
    }

    protected X10ClassType Short_;

    public X10ClassType Short() {
        if (Short_ == null)
            Short_ = load("x10.lang.Short");
        return Short_;
    }

    protected X10ClassType Char_;

    public X10ClassType Char() {
        if (Char_ == null)
            Char_ = load("x10.lang.Char");
        return Char_;
    }

    protected X10ClassType Int_;

    public X10ClassType Int() {
        if (Int_ == null)
            Int_ = load("x10.lang.Int");
        return Int_;
    }

    protected X10ClassType Long_;

    public X10ClassType Long() {
        if (Long_ == null)
            Long_ = load("x10.lang.Long");
        return Long_;
    }

    protected X10ClassType Float_;

    public X10ClassType Float() {
        if (Float_ == null)
            Float_ = load("x10.lang.Float");
        return Float_;
    }

    protected X10ClassType Double_;

    public X10ClassType Double() {
        if (Double_ == null)
            Double_ = load("x10.lang.Double");
        return Double_;
    }

    // Unsigned integers
    protected X10ClassType UByte_;

    public X10ClassType UByte() {
        if (UByte_ == null)
            UByte_ = load("x10.lang.UByte");
        return UByte_;
    }

    protected X10ClassType UShort_;

    public X10ClassType UShort() {
        if (UShort_ == null)
            UShort_ = load("x10.lang.UShort");
        return UShort_;
    }

    protected X10ClassType UInt_;

    public X10ClassType UInt() {
        if (UInt_ == null)
            UInt_ = load("x10.lang.UInt");
        return UInt_;
    }

    protected X10ClassType ULong_;

    public X10ClassType ULong() {
        if (ULong_ == null)
            ULong_ = load("x10.lang.ULong");
        return ULong_;
    }

    // Atomic
    protected X10ClassType AtomicBoolean_;

    public X10ClassType AtomicBoolean() {
        if (AtomicBoolean_ == null)
            AtomicBoolean_ = load("x10.util.concurrent.AtomicBoolean");
        return AtomicBoolean_;
    }

    protected X10ClassType AtomicInteger_;

    public X10ClassType AtomicInteger() {
        if (AtomicInteger_ == null)
            AtomicInteger_ = load("x10.util.concurrent.AtomicInteger");
        return AtomicInteger_;
    }

    protected X10ClassType nativeRail_;

    public X10ClassType Rail() {
        if (nativeRail_ == null)
            nativeRail_ = load("x10.lang.Rail");
        return nativeRail_;
    }

    // protected X10ClassType XOBJECT_;
    // public X10ClassType X10Object() {
    // if (XOBJECT_ == null)
    // XOBJECT_ = load("x10.lang.Object");
    // return XOBJECT_;
    // }

    protected X10ClassType GLOBAL_REF_;
    public X10ClassType GlobalRef() {
        if (GLOBAL_REF_ == null)
            GLOBAL_REF_ = load("x10.lang.GlobalRef");
        return GLOBAL_REF_;
    }

    public X10ClassType Object() {
        if (OBJECT_ == null)
            OBJECT_ = load("x10.lang.Object");
        return (X10ClassType) OBJECT_;
    }

    public Type Class() {
        if (CLASS_ != null)
            return CLASS_;
        return CLASS_ = load("x10.lang.Class");
    }

    X10ClassType ANY_ = null;
    public X10ClassType Any() {
        if (ANY_ != null)
            return ANY_;
    	return ANY_ = load("x10.lang.Any"); // x10.util.Any.makeDef(this).asType();
    }

    public LazyRef<Type> lazyAny() {
		final LazyRef<Type> ANY = Types.lazyRef(null);
		ANY.setResolver(new Runnable() {
			public void run() {
				ANY.update(Any());
			}
		});
		return ANY;
    }
    public X10ClassType String() {
        if (STRING_ != null)
            return (X10ClassType) STRING_;
        X10ClassType t = load("x10.lang.String");
        STRING_ = t;
        return t;
    }

    public X10ClassType Throwable() {
        if (THROWABLE_ != null)
            return (X10ClassType) THROWABLE_;
        X10ClassType t = load("x10.lang.Throwable");
        THROWABLE_ = t;
        return t;
    }

    public X10ClassType Error() {
        return load("x10.lang.Error");
    }

    public X10ClassType Exception() {
        return load("x10.lang.Exception");
    }

    public X10ClassType RuntimeException() {
        return load("x10.lang.RuntimeException");
    }

    public X10ClassType NullPointerException() {
        return load("x10.lang.NullPointerException");
    }

    public X10ClassType ClassCastException() {
        return load("x10.lang.ClassCastException");
    }

    public X10ClassType OutOfBoundsException() {
        return load("x10.lang.ArrayIndexOutOfBoundsException");
    }

    public X10ClassType ArrayStoreException() {
        return load("x10.lang.ArrayStoreException");
    }

    public X10ClassType ArithmeticException() {
        return load("x10.lang.ArithmeticException");
    }

    protected X10ClassType comparableType_;

    public X10ClassType Comparable() {
        if (comparableType_ == null)
            comparableType_ = load("x10.lang.Comparable"); // java file
        return comparableType_;
    }

    protected X10ClassType iterableType_;

    public X10ClassType Iterable() {
        if (iterableType_ == null)
            iterableType_ = load("x10.lang.Iterable"); // java file
        return iterableType_;
    }

    protected X10ClassType customSerializationType_;

    public X10ClassType CustomSerialization() {
        if (customSerializationType_ == null)
            customSerializationType_ = load("x10.io.CustomSerialization"); // java file
        return customSerializationType_;
    }

    protected X10ClassType serialDataType_;

    public X10ClassType SerialData() {
        if (serialDataType_ == null)
            serialDataType_ = load("x10.io.SerialData"); // java file
        return serialDataType_;
    }

    protected X10ClassType reducibleType_;

    public X10ClassType Reducible() {
        if (reducibleType_ == null)
            reducibleType_ = load("x10.lang.Reducible"); // java file
        return reducibleType_;
    }

    protected X10ClassType nativeRepType_;
    public X10ClassType NativeRep() {
    	if (nativeRepType_ == null)
    		nativeRepType_ = load("x10.compiler.NativeRep");
    	return nativeRepType_;
    }
    protected X10ClassType nativeType_;
    public X10ClassType NativeType() {
    	if (nativeType_ == null)
    		nativeType_ = load("x10.compiler.Native");
    	return nativeType_;
    }
    public X10ClassType Iterable(Type index) {
        return Types.instantiate(Iterable(), index);
    }

    public X10ClassType Iterator(Type index) {
        return Types.instantiate(Iterator(), index);
    }


    protected X10ClassType iteratorType_;

    public X10ClassType Iterator() {
        if (iteratorType_ == null)
            iteratorType_ = load("x10.lang.Iterator"); // java file
        return iteratorType_;
    }

    protected X10ClassType containsType_;

    public X10ClassType Contains() {
        if (containsType_ == null)
            containsType_ = load("x10.lang.Contains"); // java file
        return containsType_;
    }

    protected X10ClassType settableType_;

    public X10ClassType Settable() {
        if (settableType_ == null)
            settableType_ = load("x10.lang.Settable"); // java file
        return settableType_;
    }

    protected X10ClassType containsAllType_;

    public X10ClassType ContainsAll() {
        if (containsAllType_ == null)
            containsAllType_ = load("x10.lang.ContainsAll"); // java file
        return containsAllType_;
    }

    protected X10ClassType placeType_;

    public X10ClassType Place() {
        if (placeType_ == null)
            placeType_ = load("x10.lang.Place"); // java file
        return placeType_;
    }

    protected X10ClassType regionType_;

    public X10ClassType Region() {
        if (regionType_ == null)
            regionType_ = load("x10.array.Region"); // java file
        return regionType_;
    }

    protected X10ClassType pointType_;

    public X10ClassType Point() {
        if (pointType_ == null)
            pointType_ = load("x10.array.Point");
        return pointType_;
    }

    protected X10ClassType distributionType_;

    public X10ClassType Dist() {
        if (distributionType_ == null)
            distributionType_ = load("x10.array.Dist"); // java file
        return distributionType_;
    }

    protected X10ClassType clockType_;

    public X10ClassType Clock() {
        if (clockType_ == null)
            clockType_ = load("x10.lang.Clock"); // java file
        return clockType_;
    }

    protected X10ClassType finishStateType_;

    public X10ClassType FinishState() {
        if (finishStateType_ == null)
            finishStateType_ = load("x10.lang.FinishState"); // java file
        return finishStateType_;
    }

    protected X10ClassType runtimeType_;

    public X10ClassType Runtime() {
        if (runtimeType_ == null)
            runtimeType_ = load("x10.lang.Runtime"); // java file
        return runtimeType_;
    }

    protected X10ClassType arrayType_ = null;

    public X10ClassType Array() {
        if (arrayType_ == null)
            arrayType_ = load("x10.array.Array");
        return arrayType_;
    }

    protected X10ClassType remoteArrayType_ = null;

    public X10ClassType RemoteArray() {
        if (remoteArrayType_ == null)
            remoteArrayType_ = load("x10.array.RemoteArray");
        return remoteArrayType_;
    }

    protected X10ClassType distArrayType_ = null;

    public X10ClassType DistArray() {
        if (distArrayType_ == null)
            distArrayType_ = load("x10.array.DistArray");
        return distArrayType_;
    }

    protected X10ClassType mortalType_ = null;

    public X10ClassType Mortal() {
        if (mortalType_ == null)
            mortalType_ = load("x10.lang.Runtime.Mortal");
        return mortalType_;
    }


    // RMF 11/1/2005 - Not having the "static" qualifier on interfaces causes
    // problems,
    // e.g. for New_c.disambiguate(AmbiguityRemover), which assumes that
    // instantiating
    // non-static types requires a "this" qualifier expression.
    // [IP] FIXME: why does the above matter when we supply the bits?
    public Flags flagsForBits(int bits) {
        Flags sf = super.flagsForBits(bits);
        if (sf.isInterface())
            return sf.Static();
        return sf;
    }

    public X10FieldDef fieldDef(Position pos, Ref<? extends ContainerType> container, Flags flags, Ref<? extends Type> type, Name name) {
        assert_(container);
        assert_(type);

        ThisDef thisDef = ((X10ClassType) Types.get(container)).x10Def().thisDef();

        return fieldDef(pos, container, flags, type, name, thisDef);
    }

    public X10FieldDef fieldDef(Position pos, Ref<? extends ContainerType> container, Flags flags, Ref<? extends Type> type, Name name, ThisDef thisDef) {
        assert_(container);
        assert_(type);
        return new X10FieldDef_c(this, pos, container, flags, type, name, thisDef);
    }

    public boolean isUByte(Type t) {
        return isSubtype(t, UByte(), emptyContext());
    }

    public boolean isUShort(Type t) {
        return isSubtype(t, UShort(), emptyContext());
    }

    public boolean isUInt(Type t) {
        return isSubtype(t, UInt(), emptyContext());
    }

    public boolean isULong(Type t) {
        return isSubtype(t, ULong(), emptyContext());
    }

    public boolean isNumeric(Type t) {
        if (isChar(t))
            return false;
        return super.isNumeric(t) || isUByte(t) || isUShort(t) || isUInt(t) || isULong(t);
    }

    public boolean isUnsignedNumeric(Type t) {
        return super.isUnsignedNumeric(t) || isUByte(t) || isUShort(t) || isUInt(t) || isULong(t);
    }

    public boolean isIntOrLess(Type t) {
        if (isChar(t))
            return false;
        return super.isIntOrLess(t) || isUByte(t) || isUShort(t);
    }

    public boolean isLongOrLess(Type t) {
        if (isChar(t))
            return false;
        return super.isLongOrLess(t) || isUByte(t) || isUShort(t) || isUInt(t) || isULong(t);
    }

    public Type expandMacros(Type t) {
    	return expandMacros(t, 0);
    }
    private Type expandMacros(Type t, int depth) {
    	if (depth > EXPAND_MACROS_DEPTH) {
            Errors.issue(t.typeSystem().extensionInfo(),new SemanticException("Reached max macro expansion depth with " + t),t.position());
    		return unknownType(Position.COMPILER_GENERATED); // bottom
    	}
        /*if (t instanceof AnnotatedType)
            return expandMacros(((AnnotatedType) t).baseType(), depth+1);
            */
        if (t instanceof MacroType)
            return expandMacros(((MacroType) t).definedType(), depth+1);
        if (t instanceof ConstrainedType) {
            ConstrainedType ct = (ConstrainedType) t;
            Type base = ct.baseType().get();
            Type ebase = expandMacros(base, depth+1);
            if (base == ebase)
                return t;
            CConstraint c = ct.constraint().get();
            return Types.xclause(ebase, c);
        }
        return t;
    }

    public boolean entails(CConstraint c1, CConstraint c2, Context context, Type selfType) {
        if (c1 != null || c2 != null) {
            boolean result = true;

            if (c1 != null && c2 != null) {
                try {
                    result = c1.entails(c2, context.constraintProjection(c1, c2));
                }
                catch (XFailure e) {
                    result = false;
                }
            }
            else if (c2 != null) {
                result = c2.valid();
            }

            return result;
        }

        return true;
    }

    @Override
    public boolean numericConversionValid(Type t, java.lang.Object value, Context context) {
        return env(context).numericConversionValid(t, value);
    }

    public boolean numericConversionValid(Type t, Type fromType, java.lang.Object value, Context context) {
        return env(context).numericConversionValid(t, fromType, value);
    }

    protected boolean typeRefListEquals(List<Ref<? extends Type>> l1, List<Ref<? extends Type>> l2, Context context) {
        return CollectionUtil.<Type> allElementwise(new TransformingList<Ref<? extends Type>, Type>(l1, new DerefTransform<Type>()),
                                                    new TransformingList<Ref<? extends Type>, Type>(l2, new DerefTransform<Type>()),
                                                    new TypeSystem_c.TypeEquals(context));
    }

    protected boolean typeListEquals(List<Type> l1, List<Type> l2, Context context) {
        return CollectionUtil.<Type> allElementwise(l1, l2, new TypeSystem_c.TypeEquals(context));
    }

    protected boolean listEquals(List<XVar> l1, List<XVar> l2) {
        return CollectionUtil.<XVar> allEqual(l1, l2);
    }

    protected boolean isX10BaseSubtype(Type me, Type sup, Context context) {
        Type xme = Types.baseType(me);
        Type xsup = Types.baseType(sup);
        return isSubtype(xme, xsup, context);
    }

    public boolean isRail(Type t) {
        return hasSameClassDef(t, Rail());
    }

    public boolean isRailOf(Type t, Type p) {
        if (!isRail(t)) return false;
        List<Type> ta = ((X10ClassType)Types.baseType(t)).typeArguments();
        assert (ta.size() == 1);
        return ta.get(0).typeEquals(p, createContext());
    }

    public boolean isArray(Type t) {
        return hasSameClassDef(t, Array());
    }

    public boolean isArrayOf(Type t, Type p) {
        if (!isArray(t)) return false;
        List<Type> ta = ((X10ClassType)Types.baseType(t)).typeArguments();
        assert (ta.size() == 1);
        return ta.get(0).typeEquals(p, createContext());
    }

    public boolean isRemoteArray(Type t) {
        return hasSameClassDef(t, RemoteArray());
    }

    public boolean isRemoteArrayOf(Type t, Type p) {
        if (!isRemoteArray(t)) return false;
        List<Type> ta = ((X10ClassType)Types.baseType(t)).typeArguments();
        assert (ta.size() == 1);
        Type array_type = ta.get(0);
        List<Type> ta2 = ((X10ClassType)Types.baseType(array_type)).typeArguments();
        assert (ta2.size() == 1);
        return ta2.get(0).typeEquals(p, createContext());
    }

    public boolean hasSameClassDef(Type t1, Type t2) {
        Type b1 = Types.baseType(t1);
        Type b2 = Types.baseType(t2);
        if (b1 instanceof ClassType && b2 instanceof ClassType) {
            X10ClassType ct1 = (X10ClassType) b1;
            X10ClassType ct2 = (X10ClassType) b2;
            return ct1.def().equals(ct2.def());
        }
        return false;
    }

    public X10ClassType Rail(Type arg) {
        return Types.instantiate(Rail(), arg);
    }

    public X10ClassType Array(Type arg) {
        return Types.instantiate(Array(), arg);
    }

    public X10ClassType Settable(Type domain, Type range) {
        return Types.instantiate(Settable(), domain, range);
    }

    public boolean isSettable(Type me) {
        return hasSameClassDef(me, Settable());
    }

    public boolean isX10Array(Type me) {
        if (hasSameClassDef(me, Array())) {
            return true;
        } else if (me.isClass()) {
            Type parent = me.toClass().superClass();
            return parent != null && isX10Array(parent);
        } else {
            return false;
        }
    }

    public boolean isX10DistArray(Type me) {
        if (hasSameClassDef(me, DistArray())) {
            return true;
        } else if (me.isClass()) {
            Type parent = me.toClass().superClass();
            return parent != null && isX10DistArray(parent);
        } else {
            return false;
        }
    }

    public boolean isTypeConstrained(Type me) {
        return me instanceof ConstrainedType;
    }

    public boolean isAny(Type me) {
        return typeEquals(me, Any(), emptyContext());
    }

    public boolean isStruct(Type me) {
        return Types.isX10Struct(me);
            //typeEquals(me, Struct(), emptyContext());
    }

    public boolean isClock(Type me) {
        return isSubtype(me, Clock(), emptyContext());
    }

    public boolean isPoint(Type me) {
        return isSubtype(me, Point(), emptyContext());
    }

    public boolean isPlace(Type me) {
        return isSubtype(me, Place(), emptyContext());
    }

    public boolean isRegion(Type me) {
        return isSubtype(me, Region(), emptyContext());
    }

    public boolean isDistribution(Type me) {
        return isSubtype(me, Dist(), emptyContext());
    }

    public boolean isDistributedArray(Type me) {
        return isX10DistArray(me);
    }

    public boolean isComparable(Type me) {
        return isSubtype(me, Comparable(), emptyContext());
    }

    public boolean isIterable(Type me) {
        return isSubtype(me, Iterable(), emptyContext());
    }

    public boolean isIterator(Type me) {
        return isSubtype(me, Iterator(), emptyContext());
    }
    public boolean isReducible(Type me) {
        return isSubtype(me, Reducible(), emptyContext());
    }

    public boolean isContains(Type me) {
        return isSubtype(me, Contains(), emptyContext());
    }

    public boolean isContainsAll(Type me) {
        return isSubtype(me, ContainsAll(), emptyContext());
    }

    protected XTypeTranslator xtt = new XTypeTranslator(this);

    public XTypeTranslator xtypeTranslator() {
        return xtt;
    }

    @Override
    public void initialize(TopLevelResolver loadedResolver, ExtensionInfo extInfo) throws SemanticException {
        super.initialize(loadedResolver, extInfo);
    }

    public boolean equivClause(Type me, Type other, Context context) {
        return entailsClause(me, other, context) && entailsClause(other, me, context);
    }

    public boolean entailsClause(CConstraint c1, CConstraint c2, Context context, Type selfType) {
        return entails(c1, c2, context, selfType);
    }

    public boolean entailsClause(Type me, Type other, Context context) {
        try {
            CConstraint c1 = Types.realX(me);
            CConstraint c2 = Types.xclause(other);
            return entailsClause(c1, c2, context, null);
        }
        catch (InternalCompilerError e) {
            if (e.getCause() instanceof XFailure) {
                return false;
            }
            throw e;
        }
    }
/*
    protected XLit hereConstraintLit; // Maybe this should be declared as C_Lit
                                      // instead of a concrete impl class?

    public XLit here() {
        if (hereConstraintLit == null)
            hereConstraintLit = xtypeTranslator().transHere();
        return hereConstraintLit;
    }
*/
    protected XLit FALSE;

    public XLit FALSE() {
        if (FALSE == null)
            FALSE = xtypeTranslator().trans(false);
        return FALSE;
    }

    protected XLit TRUE;

    public XLit TRUE() {
        if (TRUE == null)
            TRUE = xtypeTranslator().trans(true);
        return TRUE;
    }

    protected XLit NEG_ONE;

    public XLit NEG_ONE() {
        if (NEG_ONE == null)
            NEG_ONE = xtypeTranslator().trans(-1);
        return NEG_ONE;
    }

    protected XLit ZERO;

    public XLit ZERO() {
        if (ZERO == null)
            ZERO = xtypeTranslator().trans(0);
        return ZERO;
    }

    protected XLit ONE;

    public XLit ONE() {
        if (ONE == null)
            ONE = xtypeTranslator().trans(1);
        return ONE;
    }

    protected XLit TWO;

    public XLit TWO() {
        if (TWO == null)
            TWO = xtypeTranslator().trans(2);
        return TWO;
    }

    protected XLit THREE;

    public XLit THREE() {
        if (THREE == null)
            THREE = xtypeTranslator().trans(3);
        return THREE;
    }

    protected XLit NULL;

    public XLit NULL() {
        if (NULL == null)
            NULL = xtypeTranslator().transNull();
        return NULL;
    }



    /** All flags allowed for a constructor. */
    @Override
    public Flags legalConstructorFlags() {
        return legalAccessFlags().Native(); // allow native
    }

    protected final Flags X10_METHOD_FLAGS = legalMethodFlags();

    @Override
    public void checkMethodFlags(Flags f) throws SemanticException {
        // Report.report(1, "X10TypeSystem_c:method_flags are |" +
        // X10_METHOD_FLAGS + "|");
        if (!f.clear(X10_METHOD_FLAGS).equals(Flags.NONE)) {
            throw new SemanticException("Cannot declare method with flags " + f.clear(X10_METHOD_FLAGS) + ".");
        }

        if (f.isAbstract() && !f.clear(ABSTRACT_METHOD_FLAGS).equals(Flags.NONE)) {
            throw new SemanticException("Cannot declare abstract method with flags " + f.clear(ABSTRACT_METHOD_FLAGS) + ".");
        }

        checkAccessFlags(f);
    }

    @Override
    public void checkTopLevelClassFlags(Flags f) throws SemanticException {
        if (!f.clear(X10_TOP_LEVEL_CLASS_FLAGS).equals(Flags.NONE)) {
            throw new SemanticException("Cannot declare a top-level class with flag(s) " + f.clear(X10_TOP_LEVEL_CLASS_FLAGS) + ".");
        }

        if (f.isInterface() && !f.clear(X10_INTERFACE_FLAGS).equals(Flags.NONE)) {
            throw new SemanticException("Cannot declare interface with flags " + f.clear(X10_INTERFACE_FLAGS) + ".");
        }

        checkAccessFlags(f);
    }

    @Override
    public void checkMemberClassFlags(Flags f) throws SemanticException {
        if (!f.clear(X10_MEMBER_CLASS_FLAGS).equals(Flags.NONE)) {
            throw new SemanticException("Cannot declare a member class with flag(s) " + f.clear(X10_MEMBER_CLASS_FLAGS) + ".");
        }

        checkAccessFlags(f);
    }

    @Override
    public void checkLocalClassFlags(Flags f) throws SemanticException {
        if (f.isInterface()) {
            throw new SemanticException("Cannot declare a local interface.");
        }

        if (!f.clear(X10_LOCAL_CLASS_FLAGS).equals(Flags.NONE)) {
            throw new SemanticException("Cannot declare a local class with flag(s) " + f.clear(X10_LOCAL_CLASS_FLAGS) + ".");
        }

        checkAccessFlags(f);
    }

    public boolean isSigned(Type t) {
        return isByte(t) || isShort(t) || isInt(t) || isLong(t);
    }

    public boolean isUnsigned(Type t) {
        return isUByte(t) || isUShort(t) || isUInt(t) || isULong(t);
    }

    public Type promote2(Type t1, Type t2) throws SemanticException {
        if (isDouble(t1) || isDouble(t2))
            return Double();

        if (isFloat(t1) || isFloat(t2))
            return Float();

        if (isLong(t1) || isLong(t2))
            return Long();

        if (isULong(t1) || isULong(t2))
            return Long();

        if (isInt(t1) || isInt(t2))
            return Int();

        if (isUInt(t1) || isUInt(t2))
            return Int();

        if (isShort(t1) || isShort(t2))
            return Int();

        if (isChar(t1) || isChar(t2))
            return Int();

        if (isByte(t1) || isByte(t2))
            return Int();

        if (isUShort(t1) || isUShort(t2))
            return Int();

        if (isUByte(t1) || isUByte(t2))
            return Int();

        throw new SemanticException("Cannot promote non-numeric type " + t1);
    }

    public Type promote2(Type t) throws SemanticException {
        if (isUByte(t) || isUShort(t) || isUInt(t))
            return UInt();

        if (isULong(t))
            return ULong();

        if (isByte(t) || isShort(t) || isInt(t))
            return Int();

        if (isLong(t))
            return Long();

        if (isFloat(t))
            return Float();

        if (isDouble(t))
            return Double();

        throw new SemanticException("Cannot promote non-numeric type " + t);
    }

    @Override
    public Type promote(Type t) throws SemanticException {
        Type pt = promote2(t);
        return Types.baseType(pt);
    }

    @Override
    public Type promote(Type t1, Type t2) throws SemanticException {
        Type pt = promote2(t1, t2);
        return Types.baseType(pt);
    }

    @Override
    public Type leastCommonAncestor(Type type1, Type type2, Context context) throws SemanticException {
        assert_(type1);
        assert_(type2);
        return env(context).leastCommonAncestor(type1, type2);
    }

    public boolean typeBaseEquals(Type type1, Type type2, Context context) {
        assert_(type1);
        assert_(type2);
        if (type1 == type2)
            return true;
        if (type1 == null || type2 == null)
            return false;
        return typeEquals(Types.baseType(type1), Types.baseType(type2), context);
    }

    public boolean typeDeepBaseEquals(Type type1, Type type2, Context context) {
        assert_(type1);
        assert_(type2);
        if (type1 == type2)
            return true;
        if (type1 == null || type2 == null)
            return false;
        return typeEquals(Types.stripConstraints(type1), Types.stripConstraints(type2), context);
    }

    public X10LocalDef localDef(Position pos, Flags flags, Ref<? extends Type> type, Name name) {
        assert_(type);
        return new X10LocalDef_c(this, pos, flags, type, name);
    }


    public boolean equalTypeParameters(List<Type> a, List<Type> b, Context context) {
        if (a == null || a.isEmpty())
            return b == null || b.isEmpty();
        if (b == null || b.isEmpty())
            return false;
        int i = a.size(), j = b.size();
        if (i != j)
            return false;
        boolean result = true;
        for (int k = 0; result && k < i; k++) {
            result = typeEquals(a.get(k), b.get(k), context);
        }
        return result;
    }

    public X10ConstructorDef defaultConstructor(Position pos, Ref<? extends ClassType> container) {
    	assert_(container);

    	// access for the default constructor is determined by the
    	// access of the containing class. See the JLS, 2nd Ed., 8.8.7.
    	Flags access = Flags.NONE;
    	Flags flags = container.get().flags();
    	if (flags.isPrivate()) {
    	    access = access.Private();
    	}
    	if (flags.isProtected()) {
    	    access = access.Protected();
    	}
    	if (flags.isPublic()) {
    	    access = access.Public();
    	}
    	return constructorDef(pos, container,
    	                      access, Collections.<Ref<? extends Type>>emptyList()
    	                      );
        }

    @Override
    public X10ConstructorDef constructorDef(Position pos, Ref<? extends ClassType> container, Flags flags, List<Ref<? extends Type>> argTypes) {
    	return constructorDef(pos, container, flags, argTypes,  null);
    }
    public X10ConstructorDef constructorDef(Position pos, Ref<? extends ClassType> container, Flags flags, List<Ref<? extends Type>> argTypes,
           Ref<? extends Type> offerType)
    {
        assert_(container);
        assert_(argTypes);

        ThisDef thisDef = ((X10ClassType) Types.get(container)).x10Def().thisDef();

        return constructorDef(pos, container, flags, Types.ref(Types.get(container)), argTypes, thisDef, dummyLocalDefs(argTypes),
        		null, null,  offerType);
    }

    public X10ConstructorDef constructorDef(Position pos, Ref<? extends ClassType> container, Flags flags, Ref<? extends ClassType> returnType,
            List<Ref<? extends Type>> argTypes, ThisDef thisDef, List<LocalDef> formalNames, Ref<CConstraint> guard, Ref<TypeConstraint> typeGuard,
            Ref<? extends Type> offerType)
    {
        assert_(container);
        assert_(argTypes);

        X10ClassType t = (X10ClassType) Types.get(returnType);
		assert t != null : "Cannot set return type of constructor to " + t;
		if (t==null)
			throw new InternalCompilerError("Cannot set return type of constructor to " + t);
		//t = (X10ClassType) t.setFlags(X10Flags.ROOTED);
		((Ref<X10ClassType>)returnType).update(t);
		//returnType = new Ref_c<X10ClassType>(t);
        return new X10ConstructorDef_c(this, pos, container, flags, returnType, argTypes, thisDef, formalNames, guard, typeGuard,  offerType);
    }

    public void addAnnotation(X10Def o, Type annoType, boolean replace) {
        List<Ref<? extends Type>> newATs = new ArrayList<Ref<? extends Type>>();

        if (replace) {
            for (Ref<? extends Type> at : o.defAnnotations()) {
                if (!at.get().isSubtype(Types.baseType(annoType), emptyContext())) {
                    newATs.add(at);
                }
            }
        }
        else {
            newATs.addAll(o.defAnnotations());
        }

        newATs.add(Types.ref(annoType));

        o.setDefAnnotations(newATs);
    }


    public boolean clausesConsistent(CConstraint c1, CConstraint c2, Context context) {
        X10TypeEnv env = env(context);
        return env.clausesConsistent(c1, c2);
    }

    public Type performBinaryOperation(Type t, Type l, Type r, Binary.Operator op) {
        CConstraint cl = Types.realX(l);
        CConstraint cr = Types.realX(r);
        TypeSystem xts = (TypeSystem) t.typeSystem();
        CConstraint c = xts.xtypeTranslator().binaryOp(op, cl, cr);
        return Types.xclause(Types.baseType(t), c);
    }

    public Type performUnaryOperation(Type t, Type a, polyglot.ast.Unary.Operator op) {
        CConstraint ca = Types.realX(a);
        TypeSystem xts = (TypeSystem) t.typeSystem();
        CConstraint c = xts.xtypeTranslator().unaryOp(op, ca);
        if (c == null)
            return t;
        return Types.xclause(Types.baseType(t), c);
    }

    /**
     * Returns true iff an object of type <type> may be thrown.
     **/
    public boolean isThrowable(Type type) {
        assert_(type);
        return isSubtype(type, Throwable(), emptyContext());
    }

    /**
     * Returns a true iff the type or a supertype is in the list returned by
     * uncheckedExceptions().
     */
    public boolean isUncheckedException(Type type) {
        assert_(type);

        if (type.isThrowable()) {
            for (Type t : uncheckedExceptions()) {
                if (isSubtype(type, t, emptyContext())) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean descendsFrom(ClassDef child, ClassDef ancestor) {
        ClassDef a = classDefOf(Any());

        if (ancestor == a)
            return true;

        if (child == a)
            return false;

        return super.descendsFrom(child, ancestor);
    }

    public X10TypeEnv env(Context context) {
        return new X10TypeEnv_c(context == null ? emptyContext() : context);
    }

 /* @Override
   public  List<Type> abstractSuperInterfaces(Type t) {
	   List<Type> result = super.abstractSuperInterfaces(t);
	   // A work-around for the current transient state of the system in which
	   // Object is an interface.
	   if (isStructType(t)) {
		   result.remove(Object());
	   }
	   return result;
   }
*/
  
}
