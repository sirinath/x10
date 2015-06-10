/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
package moldyn.distributed.moldyn;

public class Random {

	public var iseed: int;
	public var v1: double;
	public var v2: double;

	public def this(var iseed: int, var v1: double, var v2: double): Random {
		this.iseed = iseed;
		this.v1 = v1;
		this.v2 = v2;
	}

	public def update(): double {
		var rand: double;
		var scale: double = 4.656612875e-10;

		var is1: int;var is2: int;var iss2: int;
		var imult: int = 16807;
		var imod: int = 2147483647;

		if (iseed <= 0) { iseed = 1; }

		is2 = iseed % 32768;
		is1 = (iseed-is2)/32768;
		iss2 = is2 * imult;
		is2 = iss2 % 32768;
		is1 = (is1*imult+(iss2-is2)/32768) % (65536);

		iseed = (is1*32768+is2) % imod;

		rand = scale * iseed;

		return rand;
	}

	public def seed(): double {
		var s: double;var u1: double;var u2: double;var r: double;
		s = 1.0;
		do {
			u1 = update();
			u2 = update();

			v1 = 2.0 * u1 - 1.0;
			v2 = 2.0 * u2 - 1.0;
			s = v1*v1 + v2*v2;
		} while (s >= 1.0);

		r = Math.sqrt(-2.0*Math.log(s)/s);

		return r;
	}
}
