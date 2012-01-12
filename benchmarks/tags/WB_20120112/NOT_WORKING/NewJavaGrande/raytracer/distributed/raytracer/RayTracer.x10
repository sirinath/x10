/**************************************************************************
*                                                                         *
*             Java Grande Forum Benchmark Suite - Version 2.0             *
*                                                                         *
*                            produced by                                  *
*                                                                         *
*                  Java Grande Benchmarking Project                       *
*                                                                         *
*                                at                                       *
*                                                                         *
*                Edinburgh Parallel Computing Centre                      *
*                                                                         *
*                email: epcc-javagrande@epcc.ed.ac.uk                     *
*                                                                         *
*                 Original version of this code by                        *
*            Florian Doyon (Florian.doyon@sophia.inria.fr)                *
*              and  Wilfried Klauser (wklauser@acm.org)                   *
*                                                                         *
*      This version copyright (c) The University of Edinburgh, 1999.      *
*                         All rights reserved.                            *
*                                                                         *
**************************************************************************/
package raytracer.distributed.raytracer;

public class RayTracer {

	var scene: Scene;

	/**
	 * Lights for the rendering scene
	 */
	var lights: Rail[Light];

	/**
	 * Objects (spheres) for the rendering scene
	 */
	var prim: Rail[Primitive];

	/**
	 * The view for the rendering scene
	 */
	var view: View;

	/**
	 * Alpha channel
	 */
	static val alpha: int = 255<<24;

	/**
	 * Null vector (for speedup, instead of <code>new Vec(0,0,0)</code>
	 */
	static val voidVec: Vec = new Vec();

	/**
	 * Height of the <code>Image</code> to be rendered
	 */
	var height: int;

	/**
	 * Width of the <code>Image</code> to be rendered
	 */
	var width: int;

	var datasizes: Array[int] = [ 20, 500 ]; //reducing data size

	var checksum: long = 0;

	var size: int;

	var numobjects: int;

	/**
	 * Create and initialize the scene for the rendering picture.
	 * @return The scene just created
	 */
	def createScene(): Scene = {
		val x: int = 0;
		val y: int = 0;

		var scene: Scene = new Scene(new View(new Vec(x, 20, -30),
					new Vec(x, y, 0),
					new Vec(0, 1, 0),
					1.0,
					35.0 * 3.14159265 / 180.0,
					1.0));

		/* create spheres */
		val nx: int = 4;
		val ny: int = 4;
		val nz: int = 4;
		for (var i: int = 0; i<nx; i++) {
			for (var j: int = 0; j<ny; j++) {
				for (var k: int = 0; k<nz; k++) {
					var xx: double = 20.0 / (nx - 1) * i - 10.0;
					var yy: double = 20.0 / (ny - 1) * j - 10.0;
					var zz: double = 20.0 / (nz - 1) * k - 10.0;
					var p: Primitive = new Sphere(new Vec(xx,yy,zz), 3,
							new Surface(15.0, 1.5 - 1.0, 1.5 - 1.0,
								new Vec(0,0,(i+j)/(nx+ny-2) as double)));
					scene.addObject(p);
				}
			}
		}

		/* Creates five lights for the scene */
		scene.addLight(new Light(100, 100, -50, 1.0));
		scene.addLight(new Light(-100, 100, -50, 1.0));
		scene.addLight(new Light(100, -100, -50, 1.0));
		scene.addLight(new Light(-100, -100, -50, 1.0));
		scene.addLight(new Light(200, 200, 0, 1.0));

		return scene;
	}

	public def setScene(var scene: Scene): void = {
		// Get the objects count
		var nLights: int = scene.getLights();
		var nObjects: int = scene.getObjects();

		lights = new Array[Light](nLights);
		prim = new Array[Primitive](nObjects);

		// Get the lights
		for (var l: int = 0; l < nLights; l++) {
			lights(l) = scene.getLight(l);
		}

		// Get the primitives
		for (var o: int = 0; o < nObjects; o++) {
			prim(o) = scene.getObject(o);
		}

		// Set the view
		view = scene.getView();
	}

	public def render(val interval: Interval): void = {

		//final int ub = (interval.width * (interval.yto-interval.yfrom))-1;
		val R: Region = 0..(interval.width * (interval.yto-interval.yfrom)-1);
		val DBlock: Dist = Dist.makeBlock(R);
		val U: Dist = Dist.makeUnique();
		val row = DistArray.make[int](DBlock);

		finish ateach ([pl]: Point in U) {
			val my_dist: Dist = DBlock | here;
			var checksum1: long = 0;
			var frustrumwidth: double = view.dist * Math.tan(view.angle);
			var viewVec: Vec = Vec.sub(view.att, view.from).normalized();
			var tmpVec: Vec = new Vec(viewVec).scale(Vec.dot(view.up, viewVec));
			var upVec: Vec = Vec.sub(view.up, tmpVec).normalized().scale(-frustrumwidth);
			var leftVec: Vec = Vec.cross(view.up, viewVec).normalized().scale(view.aspect * frustrumwidth);

			var r: Ray = new Ray(view.from, voidVec);

			for ([pixCounter]: Point in my_dist.region) {
				var y: int = pixCounter / interval.width;
				var x: int = pixCounter % interval.width;
				var ylen: double = (2.0 * y) / interval.width - 1.0;
				var xlen: double = (2.0 * x) / interval.width - 1.0;
				r = r.d (Vec.comb(xlen, leftVec, ylen, upVec).added(viewVec).normalized());
				var col: Vec = trace(0, 1.0, r, new Isect(), new Ray());

				// computes the color of the ray
				var red: int = (col.x * 255.0) as int;
				if (red > 255) red = 255;
				var green: int = (col.y * 255.0) as int;
				if (green > 255) green = 255;
				var blue: int = (col.z * 255.0) as int;
				if (blue > 255) blue = 255;

				checksum1 += red + green + blue;
				// RGB values for .ppm file
				// Sets the pixels
				//row[pixCounter/*++*/] =  alpha | (red << 16) | (green << 8) | (blue);
			}
			val checksumx: long = checksum1;
			finish async at (Place.FIRST_PLACE) {
				atomic { checksum += checksumx; }
			}
		}
	}

	def intersect(var r: Ray, var maxt: double, var inter: Isect): boolean = {
		var tp: Isect;
		var i: int;
		var nhits: int;

		nhits = 0;
		inter.t = 1e9;
		for (i = 0; i < prim.size; i++) {
			// uses global temporary Prim (tp) as temp.object for speedup
			tp = prim(i).intersect(r);
			if (tp != null && tp.t < inter.t) {
				inter.t = tp.t;
				inter.prim = tp.prim;
				inter.surf = tp.surf;
				inter.enter = tp.enter;
				nhits++;
			}
		}
		return nhits > 0 ? true : false;
	}

	/**
	 * Checks if there is a shadow
	 * @param r The ray
	 * @return Returns 1 if there is a shadow, 0 if there isn't
	 */
	def Shadow(var r: Ray, var tmax: double, var inter: Isect): int = {
		if (intersect(r, tmax, inter))
			return 0;
		return 1;
	}

	/**
	 * Return the Vector's reflection direction
	 * @return The specular direction
	 */
	def SpecularDirection(var I: Vec, var N: Vec): Vec = {
		return Vec.comb(1.0/Math.abs(Vec.dot(I, N)), I, 2.0, N).normalized();
	}

	/**
	 * Return the Vector's transmission direction
	 */
	def TransDir(var m1: Surface, var m2: Surface, var I: Vec, var N: Vec): Vec = {
		var n1: double = m1 == null ? 1.0 : m1.ior;
		var n2: double = m2 == null ? 1.0 : m2.ior;
		var eta: double = n1/n2;
		var c1: double = -Vec.dot(I, N);
		var cs2: double = 1.0 - eta * eta * (1.0 - c1 * c1);
		if (cs2 < 0.0) return null;
		return Vec.comb(eta, I, eta * c1 - Math.sqrt(cs2), N).normalized();
	}

	/**
	 * Returns the shaded color
	 * @return The color in Vec form (rgb)
	 */
	def shade(var level: int, var weight: double, var P: Vec, var N: Vec, var I: Vec, var hit: Isect, var tRay: Ray): Vec = {

		var surf: Surface = hit.surf;
		var bigr: Vec = new Vec();
		if (surf.shine > 1e-6) {
			bigr = SpecularDirection(I, N);
		}

		// Computes the effectof each light
		var col: Vec = new Vec();
		for (var l: int = 0; l < lights.size; l++) {
			var L: Vec = Vec.sub(lights(l).pos, P);
			if (Vec.dot(N, L) >= 0.0) {
				L = L.normalized();
				var t: double = L.length();

				tRay.p = P;
				tRay.d = L;

				// Checks if there is a shadow
				if (Shadow(tRay, t, hit) > 0) {
					var diff: double = Vec.dot(N, L) * surf.kd *
						lights(l).brightness;

					col = col.adds(diff, surf.color);
					if (surf.shine > 1e-6) {
						var spec: double = Vec.dot(bigr, L);
						if (spec > 1e-6) {
							spec = Math.pow(spec, surf.shine);
							col = col.added(new Vec(spec,spec,spec));
						}
					}
				}
			} // if
		} // for

		tRay.p = P;
		if (surf.ks * weight > 1e-3) {
			tRay.d = SpecularDirection(I, N);
			var tcol: Vec = trace(level + 1, surf.ks * weight, tRay, hit, tRay);
			col = col.adds(surf.ks, tcol);
		}
		if (surf.kt * weight > 1e-3) {
			if (hit.enter > 0)
				tRay.d = TransDir(null, surf, I, N) as Vec;
			else
				tRay.d = TransDir(surf, null, I, N) as Vec;
			var tcol: Vec = trace(level + 1, surf.kt * weight, tRay, hit, tRay);
			col = col.adds(surf.kt, tcol);
		}
		// garbaging...
		// tcol = null;
		// surf = null;

		return col;
	}

	/**
	 * Launches a ray
	 */
	def trace(var level: int, var weight: double, var r: Ray, var inter: Isect, var tRay: Ray): Vec = {
		// Checks the recursion level
		if (level > 6) {
			return new Vec();
		}

		var hit: boolean = intersect(r, 1e6, inter);
		if (hit) {
			var P: Vec = r.point(inter.t);
			var N: Vec = inter.prim.normal(P);
			if (Vec.dot(r.d, N) >= 0.0) {
				N = N.negate();
			}
			return shade(level, weight, P, N, r.d, inter, tRay);
		}
		// no intersection --> col = 0,0,0
		return voidVec;
	}
}
