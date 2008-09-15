/*
 *
 * (C) Copyright IBM Corporation 2008
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Test assigments and coercions.
 *
 * @author nystrom 9/2008
 */
public class Assign2 extends x10Test {
	public def run(): boolean = {
                val a = Rail.makeVar[String](5, (nat)=>"hi");
                var i:Settable[nat,String] = a;
                i.set(1, "bye");

                return true;
	}

	public static def main(var args: Rail[String]): void = {
		new Assign2().execute();
	}
}

