/**
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Purpose: Checks variable name shadowing works correctly.
 * @author vcave
 **/
public struct ValueClass(p:int) implements X10InterfaceOne {
	
	public def this(p:int):ValueClass{self.p==p} = {
	    property(p);
	}
	public def typeName()="ValueClass";

	public  def interfaceMethod():void  = {}
}
