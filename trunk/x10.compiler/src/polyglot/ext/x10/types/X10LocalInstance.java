/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Language.
 *
 */
/**
 * 
 */
package polyglot.ext.x10.types;

import polyglot.types.LocalInstance;

/**
 * @author vj
 *
 */
public interface X10LocalInstance extends LocalInstance {
	void setPositionInArgList(int i);
	int positionInArgList();
	/**
	 * Set the self var on the depclause associated with this
	 * local instance if the variable is declared final.
	 *
	 */
	void setSelfClauseIfFinal();
}
