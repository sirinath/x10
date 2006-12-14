/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
package polyglot.ext.x10.types.constr;

import java.io.Serializable;

import polyglot.ast.Variable;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.ext.x10.types.X10TypeSystem_c;
import polyglot.types.Type;

public interface C_Term extends Serializable {
	//public static final  X10TypeSystem typeSystem = X10TypeSystem_c.getTypeSystem();
	//public static final TypeTranslator translator = typeSystem.typeTranslator();
	Type type();
	
	/**
	 * Return the result of substituting value for var in this.
	 * @param value -- the value to be substituted
	 * @param var  -- the variable which is being substituted for
	 * @return  the term with the substitution applied
	 */
	
	C_Term substitute(C_Term value, C_Term var);
	boolean rootVarIsSelf();
	boolean rootVarIsThis();
	
	/**
	 * Is this an existentially quantified variable in the constraint?
	 * @return true if it is, false if it isnt.
	 */
	boolean isEQV();
	/**
	 * If true, bind this variable when processing this=term, for any term. 
	 * In case term also prefers being bound, choose any one.
	 * @return
	 */
	boolean prefersBeingBound();
	
	/**
	 * Is this (= x.f1...fn) a prefix of term, i.e. is term of the form x.f1...fn.fn+1...fk?
	 * @return
	 */
	boolean prefixes(C_Term term);

}
