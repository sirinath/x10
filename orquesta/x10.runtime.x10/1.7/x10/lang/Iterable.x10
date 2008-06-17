package x10.lang;
/**
   The compiler accepts the syntax 'for (x in s)' if x is a formal parameter of type T,
   and if s implements Iterable[T].
   
 */

public interface Iterable[T] {
        public def iterator(): Iterator[T];
}
