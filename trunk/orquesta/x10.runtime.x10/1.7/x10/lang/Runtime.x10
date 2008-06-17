package x10.lang;

/**
    A class with static methods that offer runtime support for X10 programs.
    @author vj 06/12/08
 */
public class Runtime {

    /*package protected*/
    static val runtime = new x10.runtime.Runtime();

    public abstract def maxPlaces(): Nat;
    /**
       Make a clock.
     */
    public abstract def makeClock(): Clock;

    /**
       Make a NativeRail[T] of length l, initializing the value at
       index i with init(i).
     */
    public abstract def [T] makeNativeRail(l: Nat, init: Nat(l-1)=>T): NativeRail[T](l);

    /**
       Make a NativeRail[T] of length l, initialized the value at
       index i with init.
     */
    public def [T] makeNativeRail(l: Nat, init: T) = makeNativeRail(l, (Nat(l-1))=> init);

    /**
       Create and return a NativeRail[T] of length l, after
       initialized the value at index i with init(i).
     */
    public abstract def [T] makeNativeValRail(l: Nat, init: Nat(l-1)=>T): NativeValRail[T](l);


    /**
       Create and return a NativeValRail[T] of length l, after
       initialized the value at index i with init.
     */
    public def [T] makeNativeValRail(l: Nat, init: T):NativeValRail[T](l);

    public abstract def makeUnitRegion():Region{rank==0,rect,!rail, !colMajor};

    public abstract def makeEmptyRegion(r :nat):Region{rank==r,rect,rail==(r==1), !colMajor};

    public abstract def makeRectRegion(r :nat):Region{rank==r,rect,rail==(r==1), !colMajor};

    //********************** Place *****************************
    /**
       Return the place at which the current activity is executing.
     */
    public abstract def here(): Place; 

    /**
       Return the ValRail of all places, with the i'th entry
       containing the place whose id is i.
     */
    public abstract def places():ValRail[Place];


}
