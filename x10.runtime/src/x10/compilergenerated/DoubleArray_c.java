/*
 * Created on Oct 20, 2004
 */
package x10.compilergenerated;

import x10.array.Array;
import x10.array.Array_c;
import x10.array.Distribution;
import x10.array.DoubleArray;


/**
 * @author Christian Grothoff, Christoph von Praun
 */
public class DoubleArray_c extends Array_c implements DoubleArray {

    private final double[] arr_;
    
    /**
     *  This constructor must not be used directly by an application programmer.
     * Arrays are constructed by the corresponding factory methods in 
     * x10.lang.Runtime.
     */
    public DoubleArray_c(Distribution d) {
        super(d);
        this.arr_ = new double[d.size()];
    }
    
    /* (non-Javadoc)
     * @see x10.lang.IntArray#set(int, int[])
     */
    public void set(double v, int[] pos) {
        arr_[this.d.ordinal(pos)] = v;
    }

    /* (non-Javadoc)
     * @see x10.lang.IntArray#get(int[])
     */
    public double get(int[] pos) {
        return arr_[this.d.ordinal(pos)];
    }

    /* (non-Javadoc)
     * @see x10.lang.Array#padd(x10.lang.Array)
     */
    public Array padd(Array arg) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see x10.lang.Array#psub(x10.lang.Array)
     */
    public Array psub(Array arg) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see x10.lang.Array#pmult(x10.lang.Array)
     */
    public Array pmult(Array arg) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see x10.lang.Array#mult(x10.lang.Array)
     */
    public Array mult(Array arg) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see x10.lang.Array#circshift(int[])
     */
    public Array circshift(int[] arg) {
        // TODO Auto-generated method stub
        return null;
    }

}
