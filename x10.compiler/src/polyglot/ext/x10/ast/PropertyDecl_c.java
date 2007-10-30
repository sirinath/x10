/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
package polyglot.ext.x10.ast;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import polyglot.ast.ArrayInit;
import polyglot.ast.Block;
import polyglot.ast.ClassBody;
import polyglot.ast.ClassMember;
import polyglot.ast.Expr;
import polyglot.ast.FieldDecl;
import polyglot.ast.Id;
import polyglot.ast.MethodDecl;
import polyglot.ast.Node;
import polyglot.ast.Stmt;
import polyglot.ast.TypeNode;
import polyglot.ast.FieldDecl_c;
import polyglot.ast.MethodDecl_c;
import polyglot.ext.x10.types.X10FieldInstance;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.ext.x10.types.X10TypeSystem_c;
import polyglot.main.Report;
import polyglot.types.FieldInstance;
import polyglot.types.Flags;
import polyglot.types.InitializerInstance;
import polyglot.types.ParsedClassType;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.util.Position;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;

public class PropertyDecl_c extends X10FieldDecl_c  implements PropertyDecl {
	MethodDecl getter;
	MethodDecl abstractGetter;
	
    public PropertyDecl_c(Position pos, Flags flags, TypeNode type,
            Id name, X10NodeFactory nf) {
        this(pos, flags, type, name, null, nf);
    }
    public PropertyDecl_c(Position pos, Flags flags, TypeNode type,
            Id name, Expr init, X10NodeFactory nf) {
        super(pos, flags, type, name, init);
    }
   /**
    * Return body, augmented with properties and their getters. Used for classes. May be called during
    * initial AST construction phase.
    * 
    * @param properties -- properties declared with the class or interface.
    * @param body   -- the body of the class or interface
    * @return body, with properties and getters added.
    */
    public static ClassBody addProperties(List<PropertyDecl> properties, ClassBody body,
    		X10NodeFactory nf) {
    	
    	if (properties != null && ! properties.isEmpty()) {
    		int n = properties.size();
    		for (int i=0; i < n; i++) {
    		
    			PropertyDecl  p =  properties.get(i);
    			MethodDecl getter = ((PropertyDecl_c) p).getter(nf);
    			body = body.addMember(getter);
    			body = body.addMember(p);
    		}
    		body = body.addMember(makePropertyNamesField(properties, nf));
    	}
    	return body;
    }
    
    /**
     * Return body, augmented with getters. Used for interfaces. May be called during
     * initial AST construction phase.
     * 
     * @param properties -- properties declared with the class or interface.
     * @param body   -- the body of the class or interface
     * @return body, with properties and getters added.
     */
    public static ClassBody addGetters(List<PropertyDecl> properties, ClassBody body, X10NodeFactory nf) {
        if (properties != null && ! properties.isEmpty()) {
        	int n = properties.size();
    		for (int i=0; i < n; i++) {
                PropertyDecl  p = properties.get(i);
                body = body.addMember(((PropertyDecl_c) p).abstractGetter(nf));
                body = body.addMember(p);
            }
            body = body.addMember(makePropertyNamesField(properties, nf));
        }
        return body;
    }
    
   
    /** Construct the synthetic field:
    public static final String propertyNames$ = " ... ";
    The string contains the names of the fields separated by " ".
    This is an attempt to circumvent the Java restriction that inner classes
    cannot contain static members whose initializers are not compile time constants.
    Arrays of strings are not compile time constants.
    */    
   public static ClassMember makePropertyNamesField(List<PropertyDecl> properties, X10NodeFactory nf) {
    
       final Position pos = Position.COMPILER_GENERATED;
       TypeSystem ts =  nf.extensionInfo().typeSystem();
       
       TypeNode tn  = nf.CanonicalTypeNode(pos, ts.String());
       // get the initial value.
       StringBuffer s = new StringBuffer();
     
       for (Iterator e = properties.iterator(); e.hasNext(); ) {
           PropertyDecl p = (PropertyDecl) e.next();
           s = s.append(p.name()).append(" ");
       }
       
       FieldDecl f = nf.PropertyDecl(pos, 
    		   Flags.PUBLIC.Static().Final(), tn, 
    		   nf.Id(pos, X10FieldInstance.MAGIC_PROPERTY_NAME),
               nf.StringLit(pos, s.toString()).type(ts.String()))
               .type(nf.CanonicalTypeNode(pos, ts.String()));
      
       return f;
   }
   
   /**
    * Return the synthetic getter metod for this property.
    * @return -- the getter method for this property.
    */
    public MethodDecl getter() {
	    return getter;
    }
   
    protected MethodDecl getter(X10NodeFactory nf) {
        X10TypeSystem ts = (X10TypeSystem) nf.extensionInfo().typeSystem();
        Position pos = Position.COMPILER_GENERATED;
        Flags flags = Flags.PUBLIC.Final();
        List formals = Collections.EMPTY_LIST;
        List throwTypes = Collections.EMPTY_LIST;
        Expr e = nf.Field(pos, nf.This(pos), name);
        //Report.report(1, "PropertyDecl_c: GOLDEN e=|" + e + " " + e.getClass());
        
        Stmt s = nf.Return(pos, e);
        Block body = nf.Block(pos, s);
        getter = nf.MethodDecl(pos, flags, type, name, formals, throwTypes, body);

        return getter;
    }
    
    /**
     * For Interfaces with properties, an abstract method signature for each property 
     * is generated in the interface body.
     * Any class implementing the interface has to have the same property 
     * <RAJ> 
     */
    public MethodDecl abstractGetter() {
    	return abstractGetter;
    }

    /**
     * For Interfaces with properties, an abstract method signature for each property 
     * is generated in the interface body.
     * Any class implementing the interface has to have the same property 
     * <RAJ> 
     */
    protected MethodDecl abstractGetter(X10NodeFactory nf) {
      abstractGetter = nf.MethodDecl(Position.COMPILER_GENERATED, Flags.PUBLIC.Abstract(), type, name, 
                              Collections.EMPTY_LIST, Collections.EMPTY_LIST, null);
      return abstractGetter;
    }
    
    public Node buildTypes(TypeBuilder tb) throws SemanticException {
    	PropertyDecl_c n = (PropertyDecl_c) super.buildTypes(tb);
    	
    	// An error occurred.
    	if (n.fi == null)
    		return this;
    	
        // Property fields of interfaces are NOT static.
        Flags f = n.flags;
        f = f.clearStatic();
        
        if (n.ii != null) {
        	n.ii.setFlags(Flags.NONE);
        }
        
        n.fi.setFlags(f);
        return n.flags(f);
    }
   
}
