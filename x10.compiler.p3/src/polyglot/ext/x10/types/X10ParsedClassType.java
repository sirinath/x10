/*
 *
 * (C) Copyright IBM Corporation 2006-2008
 *
 *  This file is part of X10 Language.
 *
 */
/*
 * Created on Nov 30, 2004
 */
package polyglot.ext.x10.types;

import java.util.List;

import polyglot.types.Flags;
import polyglot.types.ParsedClassType;
import polyglot.types.Type;
import x10.constraint.XConstraint;

/**
 * @author vj
 *
 *
 */
public interface X10ParsedClassType extends ParsedClassType, X10ClassType, X10NamedType, X10ThisVar {

	 XConstraint getXClause();
}
