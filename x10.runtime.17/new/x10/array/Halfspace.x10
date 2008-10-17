// (C) Copyright IBM Corporation 2006-2008.
// This file is part of X10 Language.

package x10.array;

import x10.io.PrintStream;
import x10.io.ByteArrayOutputStream;

/**
 * This class represents a single polyhedral halfspace of the form
 *
 *     a0*x0 + a1*x1 + ... + constant <= 0
 *
 * The as are stored in the first rank elements of the as[] array; the
 * constant is stored in as[rank()] (using homogeneous coordinates).
 *
 * XXX shouldn't be public - temp hack to support Iterator_Halfspace
 *
 */

class Halfspace(rank:nat) implements Comparable[Halfspace] {

    static type PolyRegion(rank:nat) = PolyRegion{self.rank==rank};
    static type PolyRegionList(rank:nat) = PolyRegionList{self.rank==rank};
    static type Halfspace(rank:nat) = Halfspace{self.rank==rank};
    static type HalfspaceList(rank:nat) = HalfspaceList{self.rank==rank};

    val as: Rail[int];

    def this(as: Rail[int]): Halfspace(as.length-1) {
        property(as.length-1);
        this.as = as;
    }

    /**
     * natural sort order for halfspaces: from lo to hi on each
     * axis, from most major to least major axis, with constant as
     * least siginficant part of key
     */

    public def compareTo(that: Halfspace): int {
        for (var i: int = 0; i<as.length; i++) {
            if (as(i) < that.as(i))
                return -1;
            else if (as(i) > that.as(i))
                return 1;
        }
        return 0;
    }


    /**
     * two halfspaces are parallel if all coefficients are the
     * same; constants may differ
     *
     * XXX only right if first coefficients are the same; needs to
     * allow for multiplication by positive constant
     */

    def isParallel(that: Halfspace): boolean {
        for (var i: int = 0; i<as.length-1; i++)
            if (as(i)!=that.as(i))
                return false;
        return true;
    }


    /**
     * halfspace is rectangular if only one coefficent is
     * non-zero
     */

    def isRect(): boolean {
        var nz: boolean = false;
        for (var i: int = 0; i<as.length-1; i++) {
            if (as(i)!=0) {
                if (nz) return false;
                nz = true;
            }
        }
        return true;
    }


    /**
     * determine whether point satisfies halfspace
     */

    def contains(p: Point): boolean {
        var sum: int = as(rank);
        for (var i: int = 0; i<rank; i++)
            sum += as(i)*p(i);
        return sum <= 0;
    }

    /**
     * given
     *    a0*x0 + ... +ar   <=  0
     * complement is
     *    a0*x0 + ... +ar   >   0
     *   -a0*x0 - ... -ar   <   0
     *   -a0*x0 - ... -ar   <= -1
     *   -a0*x0 - ... -ar+1 <=  0
     */

    def complement(): Halfspace {
        var as: Rail[int] = Rail.makeVar[int](rank+1);
        for (var i: int = 0; i<rank; i++)
            as(i) = -this.as(i);
        as(rank) = -this.as(rank)+1;
        return new Halfspace(as);
    }


    /**
     * print a halfspace in both matrix and equation form
     */

    public def printInfo(ps: PrintStream): void {
        ps.printf("[");
        for (var i: int = 0; i<as.length; i++) {
            ps.printf("%4d", as(i));
            if (i==as.length-2) ps.printf(" |");
        }
        ps.printf(" ]   ");
        printEqn(ps, " ");
        ps.printf("\n");
    }

    /**
     * print a halfspace in equation form
     */

    private def printEqn(ps: PrintStream, spc: String): void {
        var sgn: int = 0;
        var first: boolean = true;
        for (var i: int = 0; i<as.length-1; i++) {
            if (sgn==0) {
                if (as(i)<0)
                    sgn = -1;
                else if (as(i)>0)
                    sgn = 1;
            }
            var c: int = sgn*as(i);
            if (c==1) {
                if (first)
                    ps.printf("x%d", i);
                else
                    ps.printf("+x%d", i);
            } else if (c==-1)
                ps.printf("-x%d", i);
            else if (c!=0)
                ps.printf("%+d*x%d ", c, i);
            if (c!=0)
                first = false;
        }
        if (first)
            ps.printf("0");
        if (sgn>0)
            ps.printf("%s<=%s%d", spc, spc, -as(as.length-1));
        else
            ps.printf("%s>=%s%d", spc, spc, as(as.length-1));
    }

    public def toString(): String {
        var os: ByteArrayOutputStream = new ByteArrayOutputStream();
        var ps: PrintStream = new PrintStream(os);
        //printInfo(ps);
        printEqn(ps, "");
        return os.toString();
    }
}
