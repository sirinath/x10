/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * The supertype cannot be a proto type. This is actually going to be a parser error.
 * @author vj
 */
public class ProtoImplements_MustFailCompile extends x10Test {

	interface A{}
	class B implements proto A{
		var x:B;
	}
    public def run()=true;

    public static def main(Rail[String])  {
	new ProtoImplements_MustFailCompile().execute();
    }
}
