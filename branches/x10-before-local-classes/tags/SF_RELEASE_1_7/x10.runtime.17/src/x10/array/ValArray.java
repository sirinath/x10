/*
 *
 * (C) Copyright IBM Corporation 2006-2008
 *
 *  This file is part of X10 Language.
 *
 */
/*
 * Created on Oct 28, 2004
 */
package x10.array;

import x10.types.Type;


/**
 * Double arrays.
 *
 * @author Christoph von Praun
 * @author Igor Peshansky
 */
public abstract class ValArray<T> extends BaseAnyArray<T> {

	public ValArray(Type<T> T, Dist d) {
		super(T, d, false);
	}
}

