/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */

//Generated automatically by
//m4 NullableArray4.m4 > NullableArray4.x10
//Do not edit
import harness.x10Test;

/**
 * Tests:
 *   - the inter-play of array, nullable, java types, sub-typing;
 *   - nullable array reference; nullable array elements;
 *   - proper runtime checking for Java-style array subtyping;
 *
 * @author Bin Xin
 * @author kemal
 * @date 2005/08
 */
public class NullableArray4 extends x10Test {
	class Sup { }
	class Sub extends Sup { }

	public boolean run() {
		boolean gotException = false;

		Sub[.] subarr00 = new Sub[[0:3]];
		nullable<Sub>[.] subarr01 = new nullable<Sub>[[0:3]];
		nullable<Sub[.]> subarr10 = null;
		subarr10 = subarr00;
		nullable <nullable<Sub>[.]> subarr11 = null;
		subarr11 = subarr01;

		Sup[.] suparr00 = subarr00;
		nullable<Sup>[.] suparr01 = subarr00;
		suparr01 = subarr01;
		suparr01 = suparr00;

		nullable<Sup[.]> suparr10 = subarr00;

		//suparr10 = subarr01; //illegal
		suparr10 = subarr10;
		suparr10 = suparr00;
		//suparr10 = suparr01; //illegal

		nullable<nullable<Sup>[.]> suparr11 = subarr00;
		suparr11 = subarr01;

		suparr11 = subarr10;

		suparr11 = subarr11;
		suparr11 = suparr00;
		suparr11 = suparr01;
		suparr11 = suparr10;

		suparr00 = subarr00;
		gotException = false;
		try {
			suparr00[0] = new Sup();
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (!gotException) throw new Error();
		suparr01 = subarr00;
		gotException = false;
		try {
			suparr01[0] = new Sup();
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (!gotException) throw new Error();
		suparr01 = subarr00;
		gotException = false;
		try {
			suparr01[0] = null;
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (!gotException) throw new Error();
		suparr01 = suparr00;
		gotException = false;
		try {
			suparr01[0] = null;
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (!gotException) throw new Error();
		suparr10 = subarr00;
		gotException = false;
		try {
			suparr10[0] = new Sup();
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (!gotException) throw new Error();
		suparr10 = subarr10;
		gotException = false;
		try {
			suparr10[0] = new Sup();
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (!gotException) throw new Error();
		suparr11 = subarr00;
		gotException = false;
		try {
			suparr11[0] = new Sup();
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (!gotException) throw new Error();
		suparr11 = subarr00;
		gotException = false;
		try {
			suparr11[0] = null;
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (!gotException) throw new Error();
		suparr11 = subarr01;
		gotException = false;
		try {
			suparr11[0] = new Sup();
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (!gotException) throw new Error();
		suparr11 = subarr01;
		gotException = false;
		try {
			suparr11[0] = null;
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (gotException) throw new Error();
		suparr11 = subarr10;
		gotException = false;
		try {
			suparr11[0] = new Sup();
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (!gotException) throw new Error();
		suparr11 = subarr10;
		gotException = false;
		try {
			suparr11[0] = null;
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (!gotException) throw new Error();
		suparr11 = subarr11;
		gotException = false;
		try {
			suparr11[0] = new Sup();
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (!gotException) throw new Error();
		suparr11 = subarr11;
		gotException = false;
		try {
			suparr11[0] = null;
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (gotException) throw new Error();
		suparr11 = suparr00;
		gotException = false;
		try {
			suparr11[0] = null;
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (!gotException) throw new Error();
		suparr11 = suparr01;
		gotException = false;
		try {
			suparr11[0] = null;
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (gotException) throw new Error();
		suparr11 = suparr10;
		gotException = false;
		try {
			suparr11[0] = null;
		} catch (ClassCastException e) {
			gotException = true;
		}
		if (!gotException) throw new Error();

		//what about java classes?

		String[] sa00 = new String[3];
		nullable<String>[] sa01 = new nullable<String>[3];
		nullable<String[]> sa10 = null;
		nullable<nullable<String>[]> sa11 = null;

		sa01 = sa00;
		sa10 = sa00;
		// sa10 = sa01; //illegal
		sa11 = sa00;
		sa11 = sa10;
		sa11 = sa01;

		return true;
	}

	public static void main(String[] args) {
		new NullableArray4().execute();
	}
}

