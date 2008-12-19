// (C) Copyright IBM Corporation 2006-2008.
// This file is part of X10 Language.

package x10.array;

import x10.io.Printer;
import x10.array.mat.*;


/**
 * A PolyMat is a set of linear inequalisty constraints represented as
 * a constraint matrix. Each row is represented as a PolyRow
 * object. The constraint matrix represents a set of points defined as
 * the intersection of the halfspaces represented by each PolyRow in
 * the PolyMat, or equivalently, as the set of points satisfying the
 * conjunction of the linear inequalities represented by each PolyRow
 * object.
 *
 * @author bdlucas
 */

public value class PolyMat(rank: int) extends Mat[PolyRow] {

    static type PolyMat(rank:nat) = PolyMat{self.rank==rank};
    static type PolyMatBuilder(rank:nat) = PolyMatBuilder{self.rank==rank};

    //
    // value
    //

    private val isSimplified: boolean;
    private val r: ValRail[PolyRow];


    /**
     * Low-level constructors. For greater convenience use PolyMatBuilder.
     */


    /*
    public def this(rank:nat, r: ValRail[PolyRow], isSimplified:boolean): PolyMat(rank) {
        super(r.length, rank+1);
        property(rank);
        this.r = r;
        this.isSimplified = isSimplified;
    }
    */

    private def this(rows: nat, cols: nat, init:(nat)=>PolyRow, isSimplified:boolean) {
        super(rows, cols);
        property(cols-1);
        this.isSimplified = isSimplified;
        r = Rail.makeVal[PolyRow](rows, init);
    }

    public def this(rows: nat, cols: nat, init: (i:nat,j:nat)=>int, isSimplified:boolean)
        = this(rows, cols, (i:nat)=>new PolyRow(cols, (j:nat)=>init(i,j)), isSimplified);

    /*
    public def this(rows:nat, cols:nat, init:ValRail[ValRail[int]], isSimplified:boolean)
        = this(rows, cols, (i:nat,j:nat)=>init(i)(j), isSimplified);
    */

    public def apply(i:nat) = r(i);

    public def iterator() = r.iterator();


    /**
     * Eliminate redundant parallel halfspaces. Since halfspaces
     * with equal coefficients are sorted in increasing order of
     * the constant (which is the least significant part of the
     * key) taking the last of a set of parallel halfspaces
     * captures the strongest halfspace.
     */

    def simplifyParallel(): PolyMat {

        if (rows==0)
            return this;

        val pmb = new PolyMatBuilder(rank);
        var last: Box[PolyRow] = null as Box[PolyRow];
        for (next:PolyRow in this) {
            if (last!=null && !next.isParallel(last to PolyRow))
                pmb.add(last to PolyRow);
            last = next to Box[PolyRow];
        }
        pmb.add(last to PolyRow);

        return pmb.toSortedPolyMat(false);
    }


    /**
     * Determine whether a halfspace is redundant by reductio ad
     * absurdum: assume the negation of H, and if the result is the
     * empty set then H is implied by the other halfspaces.
     *
     * This is guaranteed to remove redundant halfspaces, but it may
     * be expensive.
     */

    def simplifyAll(): PolyMat {

        if (isSimplified)
            return this;

        val pmb = new PolyMatBuilder(rank);
        var removed: Rail[boolean] = Rail.makeVar[boolean](rows, (nat)=>false); // XTENLANG-39 workaround

        for (var i: int = 0; i<rows; i++) {
            val r = this(i);
            val trial = new PolyMatBuilder(rank);
            for (var j: int = 0; j<rows; j++)
                if (!removed(j))
                    trial.add(i==j? r.complement() : this(j));
            if (!trial.toSortedPolyMat(false).isEmpty())
                pmb.add(r);
            else
                removed(i) = true;
        }

        return pmb.toSortedPolyMat(true);
    }


    /**
     * Apply Fourier-Motzkin Elimination to eliminate variable k:
     *
     * Copy each halfspace whose kth coefficient is already 0
     *
     * For each pair of halfspaces such that the kth coefficient is of
     * opposite sign, construct a new halfspace by adding the two
     * original halfspaces with appropriate positive multipliers to
     * obtain a halfspace with a kth coefficent of 0.
     *
     * The result is a set of halfspaces that describe the polyhedron
     * that is the projection of the polyhedron described by the
     * original halfspaces onto a rank-1 dimensional subspace obtained
     * by eliminating axis k
     */

    def eliminate(k: int, simplifyDegenerate: boolean): PolyMat {
        val pmb = new PolyMatBuilder(rank);
        for (ir:PolyRow in this) {
            val ia = ir(k);
            if (ia==0) {
                pmb.add(ir);
            } else {
                for (jr:PolyRow in this) {
                    val ja = jr(k);
                    val as = Rail.makeVar[int](rank+1);
                    if (ia>0 && ja<0) {
                        for (var l: int = 0; l<=rank; l++)
                            as(l) = ia*jr(l) - ja*ir(l);
                    } else if (ia<0 && ja>0) {
                        for (var l: int = 0; l<=rank; l++)
                            as(l) = ja*ir(l) - ia*jr(l);
                    }
                    val lim = simplifyDegenerate? rank : rank+1;
                    var degenerate: boolean = true;
                    for (var l: int = 0; l<lim; l++)
                        if (as(l)!=0)
                            degenerate = false;
                    if (!degenerate) {
                        var r: PolyRow = new PolyRow(as);
                        pmb.add(r);
                    }
                }
            }
        }
        return pmb.toSortedPolyMat(false).simplifyParallel();
    }


    /**
     * Support for constructing rectangular regions: determining
     * whether or not a cl is rectangular, and computing min/max along
     * each axis if it is.
     *
     * XXX cache these for efficiency during region construction
     * XXX assume halfspaces have been sorted and simplified - check/enforce
     * XXX rectMin/Max only work if isRect is true - check/enforce
     * XXX cache rectMin/rectMax/isZeroBased for performance
     */

    def isRect(): boolean {
        for (r:PolyRow in this) {
            if (!r.isRect())
                return false;
        }
        return true;
    }

    def rectMin(axis: int): int {

        for (r:PolyRow in this) {
            val a = r(axis);
            if (a < 0)
                return -r(rank()) / a;
        }

        var msg: String = "axis " + axis + " has no minimum";
        throw new UnboundedRegionException(msg);
    }
    
    def rectMax(axis: int): int {

        for (r:PolyRow in this) {
            val a = r(axis);
            if (a > 0)
                return -r(rank()) / a;
        }

        val msg = "axis " + axis + " has no maximum";
        throw new UnboundedRegionException(msg);
    }

    def rectMin(): ValRail[int]
        = Rail.makeVal[int](rank, (i:nat)=>rectMin(i));

    def rectMax(): ValRail[int]
        = Rail.makeVal[int](rank, (i:nat)=>rectMax(i));

    def isZeroBased(): boolean {
        if (!isRect())
            return false;
        try {
            for (var i: int = 0; i<rank; i++)
                if (rectMin(i)!=0)
                    return false;
        } catch (e: UnboundedRegionException) {
            return false;
        }
        return true;
    }

    def isBounded(): boolean {
        try {
            for (var i: int = 0; i<rank; i++) {
                rectMin(i);
                rectMax(i);
            }
        } catch (e: UnboundedRegionException) {
            return false;
        }
        return true;
    }


    /**
     * A set of halfspaces is empty iff, after eliminating all
     * variables with FME, we are left with a contradiction, i.e. a
     * halfspace k<=0 where k>0.
     */

    def isEmpty(): boolean {

        // eliminate all variables
        var pm: PolyMat = this;
        for (var i: int = 0; i<rank; i++)
            pm = pm.eliminate(i, false);
    
        // look for contradictions
        for (r:PolyRow in pm)
            if (r(rank)>0)
                return true;
        return false;
    }


    /**
     * Matrix multiplication.
     */

    public def $times(that: ValMat): PolyMat {
        return new PolyMat(this.rows, that.cols, (i:nat,j:nat) => {
            var sum:int = 0;
            for (var k:int=0; k<this.cols; k++)
                sum += this(i)(k)*that(k)(j);
            return sum;
        }, true);
    }


    /**
     * Matrix times vector.
     */

    public def $times(p:Point):Point {
        return Point.make(p.rank, (i:nat)=> {
            var sum:int = this(i)(p.rank);
            for (var j:int=0; j<p.rank; j++)
                sum += p(j)*this(i)(j);
            return sum;
        });
    }

    /**
     * Concatenate matrices
     */

    public def $or(that: PolyMat) {
        return new PolyMat(this.rows+that.rows, this.cols, (i:nat,j:nat) =>
             i<this.rows? this(i)(j) : that(i-this.rows)(j), true
        );
    }


    /**
     * Identity matrix
     */

    public static def identity(rank:int) = new PolyMat(rank+1, rank+1, (i:nat,j:nat)=>(i==j?1:0), true);


    /**
     *
     */

    public def printInfo(ps: Printer, label: String): void {
        ps.printf("%s\n", label);
        for (r:PolyRow in this) {
            ps.printf("    ");
            r.printInfo(ps);
        }

    }

    public def toString(): String {

        var s: String = "(";
        var first: boolean = true;

        for (r:PolyRow in this) {
            if (!first) s += " && ";
            s += r.toString();
            first = false;
        }

        s += ")";
        return s;
    }

}
