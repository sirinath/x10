// (C) Copyright IBM Corporation 2006-2008.
// This file is part of X10 Language.

package x10.array;

import x10.util.ArrayList;

public class MatBuilder {
    
    protected val mat: ArrayList[Row];
    protected val cols: int;

    public def this(cols: nat) {
        this.cols = cols;
        mat = new ArrayList[Row]();
    }

    public def this(rows: nat, cols: nat) {
        this.cols = cols;
        mat = new ArrayList[Row](rows);
        need(rows);
    }

    public def add(row:Row) {
        mat.add(row);
    }

    public def add(a:(nat)=>int) {
        mat.add(new VarRow(cols, a));
    }

    public def set(v:int, i:int, j:int) {
        need(i+1);
        mat(i)(j) = v;
    }

    public def setDiagonal(i:nat, j:nat, n:nat, v:(nat)=>int) {
        need(i+n);
        for (var k:int=0; k<n; k++)
            mat(i+k)(j+k) = v(k);
    }

    public def setColumn(i:nat, j:nat, n:nat, v:(nat)=>int) {
        need(i+n);
        for (var k:int=0; k<n; k++)
            mat(i+k)(j) = v(k);
    }

    public def setRow(i:nat, j:nat, n:nat, v:(nat)=>int) {
        need(i+1);
        for (var k:int=0; k<n; k++)
            mat(i)(j+k) = v(k);
    }
    
    private def need(n:int) {
        while (mat.size()<n)
            mat.add(new VarRow(cols));
    }

    public def toXformMat() {
        return new XformMat(mat.size(), cols, (i:nat,j:nat)=>mat(i)(j));
    }
}


