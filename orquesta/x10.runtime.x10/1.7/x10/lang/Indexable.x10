package x10.lang;
import static TypeDefs.*;
/**

   The compiler accepts the syntax f(p) for f a variable of type S if
   S implements Indexable[T](R) and it can establish that p is in the
   region R.

   TODO: It would be really neat if we had some way of specifying that 
   you could take an object o of type Indexable[T](r) and
   o(i1,..., ik) is considered the same as o(new point(i1,..., ik)), provided
   that r is of rank k.
 */

public interface Indexable[T](base: region) extends point(base)=>T {}










