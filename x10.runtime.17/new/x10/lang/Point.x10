// (C) Copyright IBM Corporation 2006-2008.
// This file is part of X10 Language.

package x10.lang;

import x10.compiler.ArithmeticOps;
import x10.compiler.ComparisonOps;

public value class Point(rank: nat) implements Indexable[nat,int] {

    public def apply(i: nat): int = coords(i);
    public def coords(): ValRail[int] = coords;

    //
    // factories
    //

    public static def make(cs: ValRail[int]): Point(cs.length) = new Point(cs);

    public static def make(cs: Rail[int]): Point(cs.length) {
        // (i:nat)=>cs(i) is workaround for XTENLANG-32
        val a: ValRail[int](cs.length) = Rail.makeVal[int](cs.length, (i:nat)=>cs(i));
        return make(a);
    }

    public static def make(i0:int) = make([i0]);
    public static def make(i0:int, i1:int) = make([i0,i1]);
    public static def make(i0:int, i1:int, i2:int) = make([i0,i1,i2]);


    //
    // arithmetic ops
    //

    public def $plus(): Point(rank) {
        return this;
    }

    public def $minus(): Point(rank) {
        val cs = Rail.makeVar[int](rank, (i:nat)=>-this.coords(i));
        return Point.make(cs);
    }

    public def $plus(that: Point(rank)): Point(rank) {
        val init = (i:nat) => this.coords(i) + that.coords(i);
        val cs = Rail.makeVar[int](rank, init);
        return Point.make(cs);
    }

    public def $minus(that: Point(rank)): Point(rank) {
        val init = (i:nat) => this.coords(i) - that.coords(i);
        val cs = Rail.makeVar[int](rank, init);
        return Point.make(cs);
    }

    public def $times(that: Point(rank)): Point(rank) {
        val init = (i:nat) => this.coords(i) * that.coords(i);
        val cs = Rail.makeVar[int](rank, init);
        return Point.make(cs);
    }

    public def $over(that: Point(rank)): Point(rank) {
        val init = (i:nat) => this.coords(i) / that.coords(i);
        val cs = Rail.makeVar[int](rank, init);
        return Point.make(cs);
    }

    public def $plus(c: int): Point(rank) {
        val init = (i:nat) => this.coords(i) + c;
        val cs = Rail.makeVar[int](rank, init);
        return Point.make(cs);
    }

    public def $minus(c: int): Point(rank) {
        val init = (i:nat) => this.coords(i) - c;
        val cs = Rail.makeVar[int](rank, init);
        return Point.make(cs);
    }

    public def $times(c: int): Point(rank) {
        val init = (i:nat) => this.coords(i) * c;
        val cs = Rail.makeVar[int](rank, init);
        return Point.make(cs);
    }

    public def $over(c: int): Point(rank) {
        val init = (i:nat) => this.coords(i) / c;
        val cs = Rail.makeVar[int](rank, init);
        return Point.make(cs);
    }

    public def inv$plus(c: int): Point(rank) {
        val init = (i:nat) => c + this.coords(i);
        val cs = Rail.makeVar[int](rank, init);
        return Point.make(cs);
    }

    public def inv$minus(c: int): Point(rank) {
        val init = (i:nat) => c - this.coords(i);
        val cs = Rail.makeVar[int](rank, init);
        return Point.make(cs);
    }

    public def inv$times(c: int): Point(rank) {
        val init = (i:nat) => c * this.coords(i);
        val cs = Rail.makeVar[int](rank, init);
        return Point.make(cs);
    }

    public def inv$over(c: int): Point(rank) {
        val init = (i:nat) => c / this.coords(i);
        val cs = Rail.makeVar[int](rank, init);
        return Point.make(cs);
    }


    //
    // comparison ops
    //

    public def $eq(that: Point(rank)): boolean {
        for (var i: int = 0; i<rank; i++)
            if (!(this.coords(i)==that.coords(i)))
                return false;
        return true;
    }

    public def $lt(that: Point(rank)): boolean {
        for (var i: int = 0; i<rank; i++)
            if (!(this.coords(i)<that.coords(i)))
                return false;
        return true;
    }

    public def $gt(that: Point(rank)): boolean {
        for (var i: int = 0; i<rank; i++)
            if (!(this.coords(i)>that.coords(i)))
                return false;
        return true;
    }

    public def $le(that: Point(rank)): boolean {
        for (var i: int = 0; i<rank; i++)
            if (!(this.coords(i)<=that.coords(i)))
                return false;
        return true;
    }

    public def $ge(that: Point(rank)): boolean {
        for (var i: int = 0; i<rank; i++)
            if (!(this.coords(i)>=that.coords(i)))
                return false;
        return true;
    }

    public def $ne(that: Point(rank)): boolean {
        for (var i: int = 0; i<rank; i++)
            if (!(this.coords(i)!=that.coords(i)))
                return false;
        return true;
    }

    public static def $convert(r: Rail[int]): Point(r.length) = make(r);
    public static def $convert(r: ValRail[int]): Point(r.length) = make(r);

    public def toString() {
        var s:String = "(";
        if (coords.length>0)
            s = s + coords(0); // XTENLANG-45
        for (var i:int=1; i<coords.length; i++)
            s = s + "," + coords(i); // XTENLANG-45
        s += ")";
        return s;
    }

    //
    //
    //

    private val coords: ValRail[int];

    private def this(cs: ValRail[int]): Point(cs.length) {
        property(cs.length);
        this.coords = cs;
    }

}
