/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
package polyglot.ext.x10.types;

import polyglot.ast.Expr;
import polyglot.ast.Receiver;
import polyglot.types.FieldInstance;
import polyglot.types.Type;

/**
 * Represents information about a Property. A property has the same
 * attributes as a Field, except that it is always public, instance and final
 * and has no initializer.
 * @author vj
 *
 */
public interface X10FieldInstance extends FieldInstance, X10TypeObject {
	
	public static final String MAGIC_PROPERTY_NAME = "propertyNames$";
	public static final String MAGIC_CI_PROPERTY_NAME = "classInvariant$";
	/** Is this field a property? */
	
	boolean isPropertyInitialized();
	boolean isProperty();
	void setProperty();
	
	/** Returns true if the deptype has been processed for this field. */
	boolean depTypeSet();
	void setDepType(Type type);
	/**
	 * Set the self var on the depclause associated with this
	 * local instance if the variable is declared final.
	 * Return true iff the LI changed.
	 */
	boolean setSelfClauseIfFinal();
}
