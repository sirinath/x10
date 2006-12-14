/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
package polyglot.ext.x10.types;

import polyglot.types.FieldInstance;
import polyglot.types.Type;

/**
 * Represents information about a Property. A property has the same
 * attributes as a Field, except that it is always public, instance and final
 * and has no initializer.
 * @author vj
 *
 */
public interface X10FieldInstance extends FieldInstance {
	
	public static final String MAGIC_PROPERTY_NAME = "propertyNames$";
	/** Is this field a property? */
	
	boolean isProperty();
	void setProperty();
	
	/** Returns true if the deptype has been processed for this field. */
	boolean depTypeSet();
	void setDepType(Type type);

}
