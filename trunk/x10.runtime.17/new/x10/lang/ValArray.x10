package x10.lang;

import x10.compiler.ArithmeticOps;
import x10.compiler.Native;
import x10.compiler.NativeRep;

// note: use dist.region.rank, not rank -- only the properties in the
// header are in scope in the header.

@NativeRep("java", "x10.array.Array<#1>")
public abstract value class ValArray[T](dist: Dist) implements
    Indexable[Point(dist.region.rank),T],
    ArithmeticOps[ValArray[T]]
{
    // region via dist
    property region: Region = dist.region;
    property rank: int = dist.rank;
    property rect: boolean = dist.rect;
    property zeroBased: boolean = dist.zeroBased;
    
    // dist
    property rail: boolean = dist.rail;
    property unique: boolean = dist.unique;
    property constant: boolean = dist.constant;
    property onePlace: Place = dist.onePlace;

 @Native("java", "x10.array.ArrayFactory.makeValFromRail(#2, #4)")
    native public static def $convert[T](r: Rail[T]): ValArray[T]{rank==1,rail};
 
    @Native("java", "x10.array.ArrayFactory.<#2>makeValFromValRail(#3, #4)")
    native public static def $convert[T](r: ValRail[T]): ValArray[T]{rank==1,rail};
    
    @Native("java", "x10.array.ArrayFactory.<#2>makeValFromValRail(#3, #4)")
    native public static def make[T](r: ValRail[T]): ValArray[T]{rank==1};
    @Native("java", "x10.array.ArrayFactory.makeValFromRail(#2, #4)")
    native public static def make[T](r: Rail[T]): ValArray[T]{rank==1,rail};
    
    @Native("java", "x10.array.ArrayFactory.<#2>makeValArray(#3, #4, #5)")
    native public static def make[T](d: Dist, init: (Point)=>T)
       : ValArray[T](d);
    
    @Native("java", "x10.array.ArrayFactory.<#2>makeValArray(#3,x10.array.RegionFactory.makeRect(0,(#4-1)),#5)")
    native public static def make[T](n: nat, init:(point)=>T): ValArray[T]{rank==1,rect};
    
     @Native("java", "x10.array.ArrayFactory.<#2> makeValArray(#3, #4,#5)")
    native public static def makeFromRegion[T](r: Region, init: (Point)=>T)
	   : ValArray[T]{region==r,onePlace==here};

    @Native("java", "x10.array.ArrayFactory.<#2>makeValArray(#3, #4,#5,#6)")
    native public static def makeFromRegion[T](r: Region, 
       init: (Point)=>T, value: boolean): ValArray[T]{region==r,onePlace==here};
    
    @Native("java", "(#0).restriction(#1)")
    public native def restriction(r: Region): ValArray[T]{region==r};
                     
    @Native("java", "(#0).get(#1)")
    public native def get(pt: Point(this.rank)): T;
    @Native("java", " (#0).get(#1)")
    public native def get(i0: int){rank==1}: T;
    @Native("java", "(#0).get(#1, #2)")
    public native def get(i0: int, i1: int){rank==2}: T;
    @Native("java", " (#0).get(#1, #2, #3)")
    public native def get(i0: int, i1: int, i2: int){rank==3}: T;   
                 
    @Native("java", "(#0).get(#1)")
    public native def apply(pt: Point(this.rank)): T;
    @Native("java", " (#0).get(#1)")
    public native def apply(i0: int){rank==1}: T;
    @Native("java", "(#0).get(#1, #2)")
    public native def apply(i0: int, i1: int){rank==2}: T;
    @Native("java", " (#0).get(#1, #2, #3)")
    public native def apply(i0: int, i1: int, i2: int){rank==3}: T;
                     
    @Native("java", "(#0)")
    native public def $plus(): ValArray[T](this.dist);
    @Native("java", "(#0).neg()")
    native public def $minus(): ValArray[T](this.dist);

    @Native("java", "(#0).add(#1)")
    native public def $plus(that: ValArray[T](this.dist)): ValArray[T](this.dist);
    
    @Native("java", "(#0).sub(#1)")
    native public def $minus(that: ValArray[T](this.dist)): ValArray[T](this.dist);
    
    @Native("java", "(#0).mul(#1)")
    native public def $times(that: ValArray[T](this.dist)): ValArray[T](this.dist);
    
    @Native("java", "(#0).div(#1)")
    native public def $over(that: ValArray[T](this.dist)): ValArray[T](this.dist);

    // ----------------- restriction
    @Native("java", "(#0).restriction(#1)")
    native public def $bar(r: Region): ValArray[T]{region==r};
    
    @Native("java", "(#0).restriction(#1)")
    native public def $bar(d: Dist(this.rank)): ValArray[T](d.region);
    
    @Native("java", "(#0).restriction(#1)")
    native public def $bar(p: Place): ValArray[T]{onePlace==here};
    
    // ----------------- union
    @Native("java", "(#0).union(#1)")
    native public def $or(a:ValArray[T](this.rank)):ValArray[T](this.rank);
    
    // ----------------- overlay
    @Native("java", "(#0).overlay(#1)")
    native public def overlay(a:ValArray[T](this.rank)):ValArray[T](this.rank);
    
    // ----------------- update
    @Native("java", "(#0).update(#1)")
    native public def update(a:ValArray[T](this.rank)):ValArray[T](this.rank);
    
  
    // ------------------- scan
   @Native("java","(#0).scan(#1,#2)")
    native public def scan(res:ValArray[T](this.dist), 
              op:(T)=>T):Void;
   
    @Native("java","(#0).scan(#1,#2)")
    native public def scan(res:ValArray[T](this.dist), 
              op:(Point(this.rank), T)=>T):Void;
    @Native("java", "(#0).scan(#1,#2)")
    native public def scan(op:(T,T)=>T, unit:T): ValArray[T](this.dist);
    
    // ------------------- pointwise
    @Native("java", "(#0).pointwise(#1,#2")
    native public 
      def pointwise(res: ValArray[T](this.dist), 
                    f:(Point(this.rank), T, T)=>T):Void;
    
    // ------------------- reduce, reduction
    @Native("java", "(#0).reduce(#1,#2,#3)")
    native public def reduce(f:(T,T)=>T, unit:T, r:Region(this.rank)):T;
    @Native("java", "(#0).reduce(#1,#2)")
    native public def reduce(f:(T,T)=>T, unit:T):T;
    @Native("java", "(#0).reduce(#1)")
    native public def reduce(f:(T,T)=>T):T;
    
    @Native("java", "(#0).<#1>reduction(#3)")
    native public def reduction[U](op:(T)=>U):U;
    
     // ------------------- lift
    @Native("java", "(#0).lift(#1)")
    native public def lift(op:(T)=>T):ValArray[T](dist);
    
     @Native("java", "(#0).lift(#1,#2)")
    native public def lift(op:(T,T)=>T, other:ValArray[T](this.dist))
      :Array[T](this.dist);
    
    @Native("java", "(#0).sum()")
    native public def sum():T;
    
    @Native("java", "(#0).sum(#1)")
    native public def sum(r:Region):T;
    
    @Native("java", "(#0).max()")
    native public def max():T;
    
    @Native("java", "(#0).sum(#1)")
    native public def max(r:Region):T;
    
    
    
    //
    // private/protected
    //

    protected def this(dist: Dist) = {
        property(dist);
    }
}
