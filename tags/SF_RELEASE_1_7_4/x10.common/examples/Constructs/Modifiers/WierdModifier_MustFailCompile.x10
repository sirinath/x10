/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Check that a wierd annotation is not recognized.
 * @author vj  9/2006
 */
public class WierdModifierMustFail extends x10Test {

    public what void m() {
    
    }
	public boolean run() {
		m();
		return true;
	}

	public static void main(String[] args) {
		new WierdModifierMustFail().execute();
	}

	
}

