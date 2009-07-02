import x10.io.Console;
import x10.util.Random;

/* This class represents a real-world problem in graphics engines --
 * determining which objects in a large sprawling world are close enough to the
 * camera to be considered for rendering.  The naive implementation produces a
 * lot of objects and is thus a good benchmark for garbage collection in X10.
 *
 * The class has been annotated with 'ref' and 'inlined' params to show how the
 * inline proposal might be deployed to make this implementation more efficient.
 *
 * @Author Dave Cunningham
 * @Author Vijay Saraswat
*/
class GCSpheres {

    static type Real = Float;


    static final value Vector3 {

        public def this (x:Real, y:Real, z:Real) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public def getX () = x; 
        public def getY () = y;
        public def getZ () = z;

        public def add (/*ref*/ other:Vector3)
            = new Vector3(this.x+other.x,
                          this.y+other.y,
                          this.z+other.z);

        public def neg () = new Vector3(-this.x, -this.y, -this.z);

        public def sub (/*ref*/ other:Vector3) = add(other.neg());

        public def length () = Math.sqrt(length2());

        public def length2 () = x*x + y*y + z*z;

        protected val x:Real, y:Real, z:Real;
    }


    static final value WorldObject {

        def this (x:Real, y:Real, z:Real, r:Real) {
            pos = new Vector3(x,y,z);
            renderingDistance = r;
        }

        public def intersects (/*ref*/ home:Vector3)
            = home.sub(pos).length2() < renderingDistance*renderingDistance;

        protected /*inlined*/ val pos:Vector3;
        protected val renderingDistance:Real;
    }


    public static def main (Rail[String]) {

        val reps = 75;

        // The following correspond to a modern out-door computer game:
        val num_objects = 50000;
        val world_size = 6000;
        val obj_max_size = 400;

        val ran = new Random(0);

        // the array can go on the heap
        // but the elements ought to be /*inlined*/ in the array
        val spheres =
            ValRail.make[/*inlined*/ WorldObject](num_objects, (i:Int) => {
                val x = ran.nextDouble()*world_size as Real;
                val y = ran.nextDouble()*world_size as Real;
                val z = ran.nextDouble()*world_size as Real;
                val r = ran.nextDouble()*obj_max_size as Real;
                return new WorldObject(x,y,z,r);
            });

        val time_start = System.nanoTime();

        var counter : Long = 0;

        // HOT LOOP BEGINS
        for ((frame):Point in [1..reps]) {

            val x = ran.nextDouble()*10000 as Real;
            val y = ran.nextDouble()*10000 as Real;
            val z = ran.nextDouble()*10000 as Real;

            /*inlined*/ val pos = new Vector3(x,y,z);

            for ((i):Point in [0..spheres.length-1]) {
                if (spheres(i).intersects(pos)) {
                    counter++;
                }
            }
        }
        // HOT LOOP ENDS

        val time_taken = System.nanoTime() - time_start;
        Console.OUT.println("Total time: "+time_taken/1E9);

        val expected = 237;
        if (counter != expected) {
            Console.ERR.println("number of intersections: "+counter
                                +" (expected "+expected+")");
            System.exit(1);
        }
    }

}
