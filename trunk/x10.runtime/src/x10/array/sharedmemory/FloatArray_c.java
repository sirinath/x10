/*
 * Created on Oct 20, 2004
 */
package x10.array.sharedmemory;

import java.util.Iterator;

import x10.array.FloatArray;
import x10.array.Helper;
import x10.array.Operator;
import x10.array.Distribution_c;
import x10.base.Allocator;
import x10.base.MemoryBlock;
import x10.base.UnsafeContainer;
import x10.lang.FloatReferenceArray;
import x10.lang.Indexable;
import x10.lang.Runtime;
import x10.lang.dist;
import x10.lang.place;
import x10.lang.point;
import x10.lang.region;
import x10.runtime.Configuration;



/**
 * @author Christian Grothoff, Christoph von Praun
 */
public class FloatArray_c extends FloatArray implements UnsafeContainer, Cloneable {

    private final boolean safe_;
    private final MemoryBlock arr_;
    public final boolean mutable_;
    
    public boolean valueEquals(Indexable other) {
        return arr_.valueEquals(((FloatArray_c)other).arr_);
    }
    
    /**
     * This constructor must not be used directly by an application programmer.
     * Arrays are constructed by the corresponding factory methods in 
     * x10.lang.Runtime.
     */

    protected FloatArray_c(dist d, Operator.Pointwise c, boolean safe) {
    	this(d, c, safe, true);
    }
    public FloatArray_c(dist d, Operator.Pointwise c, boolean safe, boolean mutable, boolean ignored) {
        this(d, c, safe, mutable);
    }
    protected FloatArray_c(dist d, Operator.Pointwise c, boolean safe, boolean mutable) {
        super(d);
        assert (d instanceof Distribution_c);
        this.mutable_ = mutable;
        this.safe_ = safe;
        int count =  d.region.size();
        if (!safe) {
            int rank = d.region.rank;
            int ranks[] = new int[rank];
            for (int i = 0; i < rank; ++i) 
                ranks[i] = d.region.rank(i).size();
            this.arr_ = Allocator.allocUnsafe(count, ranks, Allocator.SIZE_FLOAT);
        } else {
            this.arr_ = Allocator.allocSafe(count, Float.TYPE);
        }
        if (c != null)
            pointwise(this, c);
    }
    
    /** Create a new array per the given distribution, initialized to c.
     * 
     * @param d
     * @param c
     * @param safe
     */
    public FloatArray_c(dist d, float c) {
        this(d, c, true);
    }
    public FloatArray_c(dist d, float c, boolean safe) {
    	this(d, c, safe, true);
}
    public FloatArray_c(dist d, float c, boolean safe, boolean mutable) {
    	super(d);
    	this.mutable_ = mutable;
    	int count =  d.region.size();
        this.safe_ = safe;
        if (!safe) {
            int rank = d.region.rank;
            int ranks[] = new int[rank];
            for (int i = 0; i < rank; ++i) 
                ranks[i] = d.region.rank(i).size();
            this.arr_ = Allocator.allocUnsafe(count, ranks, Allocator.SIZE_FLOAT);
        } else {
            this.arr_ =Allocator.allocSafe(count, Float.TYPE);
        }
        scan(this, new Assign(c));
    	
    }
    public FloatArray_c(dist d, FloatArray.pointwiseOp f) {
        this(d, f, true);
    }
    public FloatArray_c(dist d, FloatArray.pointwiseOp f, boolean safe) {
    	this(d, f, safe, true);
    }
    
    public FloatArray_c(dist d, FloatArray.pointwiseOp f, boolean safe, boolean mutable) {
    	super(d);
    	this.mutable_ = mutable;
    	int count =  d.region.size();
        this.safe_ = safe;
        
        if (!safe) {
            int rank = d.region.rank;
            int ranks[] = new int[rank];
            for (int i = 0; i < rank; ++i) 
                ranks[i] = d.region.rank(i).size();
            this.arr_ = Allocator.allocUnsafe(count, ranks, Allocator.SIZE_FLOAT);
        } else {
            this.arr_ =Allocator.allocSafe(count, Float.TYPE);
        }
        if (f != null)
            scan(this, f);
    }
    
    private FloatArray_c(dist d, float[] a, boolean safe, boolean mutable) {
    	super(d);
        int count =  d.region.size();
    	this.safe_ = safe;
        if (!safe) {
            int rank = d.region.rank;
            int ranks[] = new int[rank];
            for (int i = 0; i < rank; ++i) 
                ranks[i] = d.region.rank(i).size();
            this.arr_ = Allocator.allocUnsafe(count, ranks, Allocator.SIZE_FLOAT);
        } else {
            this.arr_ =Allocator.allocSafe(count, Float.TYPE);
        }
        this.mutable_ = mutable;
    }
    /** Return a safe IntArray_c initialized with the given local 1-d (Java) int array.
     * 
     * @param a
     * @return
     */
    public static FloatArray_c FloatArray_c(float[] a, boolean safe, boolean mutable) {
    	dist d = Runtime.factory.getDistributionFactory().local(a.length);
    	return new FloatArray_c(d, a, safe, mutable);
    }
    
    public void keepItLive() {}
    
    public long getUnsafeAddress() {
        return arr_.getUnsafeAddress();
    }
    
    public long getUnsafeDescriptor() {
        return arr_.getUnsafeDescriptor();
    }
    
    /* Overrides the superclass method - this implementation is more efficient */
    public void reduction(Operator.Reduction op) {
        int count = arr_.count();
        for (int i  = 0; i < count; ++i) 
            op.apply(arr_.getFloat(i));
    }
    
    /* Overrides the superclass method - this implementation is more efficient */
    protected void assign(FloatArray rhs) {
    	assert rhs instanceof FloatArray_c;
    	
    	FloatArray_c rhs_t = (FloatArray_c) rhs;
    	if (rhs.distribution.equals(distribution)) {
    	    int count = arr_.count();
    		for (int i  = 0; i < count; ++i) 
    			arr_.setFloat(rhs_t.arr_.getFloat(i), i);
    	} else 
    		// fall back to generic implementation
    		super.assign(rhs);
    }

	protected FloatArray newInstance(dist d) {
		assert d instanceof Distribution_c;
		
		return new FloatArray_c(d, (Operator.Pointwise) null, safe_);	
	}
	
	protected FloatArray newInstance(dist d, Operator.Pointwise c) {
		assert d instanceof Distribution_c;
		
		return new FloatArray_c(d, c, safe_);	
	}
	

	public FloatReferenceArray lift(FloatArray.binaryOp op, x10.lang.floatArray arg) {
	    assert arg.distribution.equals(distribution); 
	    FloatArray arg1 = (FloatArray)arg;
	    FloatArray result = newInstance(distribution);
	    place here = x10.lang.Runtime.runtime.currentPlace();
	    try {
	        for (Iterator it = distribution.region.iterator(); it.hasNext();) {
	            point p = (point) it.next();
	            place pl = distribution.get(p);
	            x10.lang.Runtime.runtime.setCurrentPlace(pl);
	            result.set(op.apply(this.get(p), arg1.get(p)),p);
	        }
	    } finally {
	        x10.lang.Runtime.runtime.setCurrentPlace(here);
	    }  
	    return result;
	}
    
	public FloatReferenceArray lift(FloatArray.unaryOp op) {
	    FloatArray result = newInstance(distribution);
	    place here = x10.lang.Runtime.runtime.currentPlace();
	    try {
	        for (Iterator it = distribution.region.iterator(); it.hasNext();) {
	            point p = (point) it.next();
	            place pl = distribution.get(p);
	            x10.lang.Runtime.runtime.setCurrentPlace(pl);
	            result.set(op.apply(this.get(p)),p);
	        }
	    } finally {
	        x10.lang.Runtime.runtime.setCurrentPlace(here);
	    }  
	    return result;
	}
    
	public float reduce(FloatArray.binaryOp op, float unit) {
	    float result = unit;
	    place here = x10.lang.Runtime.runtime.currentPlace();
	    try {
	        for (Iterator it = distribution.region.iterator(); it.hasNext();) {
	            point p = (point) it.next();
	            place pl = distribution.get(p);
	            x10.lang.Runtime.runtime.setCurrentPlace(pl);
	            result = op.apply(this.get(p), result);
	        }
	    } finally {
	        x10.lang.Runtime.runtime.setCurrentPlace(here);
	    }  
	    return result;
	}

	public FloatReferenceArray scan(binaryOp op, float unit) {
	    float temp = unit;
	    FloatArray result = newInstance(distribution);
	    place here = x10.lang.Runtime.runtime.currentPlace();
	    try {
	        for (Iterator it = distribution.region.iterator(); it.hasNext();) {
	            point p = (point) it.next();
	            place pl = distribution.get(p);
	            x10.lang.Runtime.runtime.setCurrentPlace(pl);
	            temp = op.apply(this.get(p), temp);
	            result.set(temp, p);
	        }
	    } finally {
	        x10.lang.Runtime.runtime.setCurrentPlace(here);
	    }  
	    return result;
	}   
	
    /* (non-Javadoc)
     * @see x10.lang.FloatArray#set(int, int[])
     */
	 public float set(float v, point pos) {return set(v,pos,true,true);}
    public float set(float v, point pos,boolean chkPl,boolean chkAOB) {
        if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
            Runtime.hereCheckPlace(distribution.get(pos));        
        return arr_.setFloat(v, (int) distribution.region.ordinal(pos));
    }
    
    public float setOrdinal(float v, int rawIndex) {    	
    	return arr_.setFloat(v,rawIndex);
    }
    
    public float set(float v, int d0) {return set(v,d0,true,true);}
    public float set(float v, int d0,boolean chkPl,boolean chkAOB) {
        if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
            Runtime.hereCheckPlace(distribution.get(d0));        
        d0 = Helper.ordinal(distribution,d0,chkAOB);
    	return arr_.setFloat(v,d0);
    }
     
    
    public float set(float v, int d0,int d1) {return set(v,d0,d1,true,true);}
    public float set(float v, int d0, int d1,boolean chkPl,boolean chkAOB) {
        if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
            Runtime.hereCheckPlace(distribution.get(d0, d1));        
        int	theIndex = Helper.ordinal(distribution,d0,d1,chkAOB);
    	return arr_.setFloat(v,theIndex);
    }
    
    public float set(float v, int d0,int d1,int d2) {return set(v,d0,d1,d2,true,true);}
    public float set(float v, int d0, int d1, int d2,boolean chkPl,boolean chkAOB) {
        if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
            Runtime.hereCheckPlace(distribution.get(d0, d1, d2));        
        int	theIndex = Helper.ordinal(distribution,d0,d1,d2,chkAOB);
    	return arr_.setFloat(v,theIndex);
    }
    
    public float set(float v, int d0,int d1,int d2,int d3) {return set(v,d0,d1,d2,d3,true,true);}
    
    public float set(float v, int d0, int d1, int d2, int d3,boolean chkPl,boolean chkAOB) {
        if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
            Runtime.hereCheckPlace(distribution.get(d0, d1, d2, d3));        
        int	theIndex = Helper.ordinal(distribution,d0,d1,d2,d3,chkAOB);
    	return arr_.setFloat(v,theIndex);   	
    }

    /* (non-Javadoc)
     * @see x10.lang.FloatArray#get(int[])
     */
    public float get(point pos) {return get(pos,true,true);}
    	   
    public float get(point pos,boolean chkPl,boolean chkAOB) {
        if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
            Runtime.hereCheckPlace(distribution.get(pos));        
        return arr_.getFloat((int) distribution.region.ordinal(pos));
    }    
    
    public float getOrdinal(int rawIndex) {    	
    	return arr_.getFloat(rawIndex);
    }
    
    public float get(int d0) {return get(d0,true,true);}
    
    public float get(int d0,boolean chkPl,boolean chkAOB) {
        if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
            Runtime.hereCheckPlace(distribution.get(d0));        
        d0 = Helper.ordinal(distribution,d0,chkAOB);
    	return arr_.getFloat(d0);
    }
    
    public float get(int d0,int d1) {return get(d0,d1,true,true);}
    public float get(int d0, int d1,boolean chkPl,boolean chkAOB) {
        if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
            Runtime.hereCheckPlace(distribution.get(d0, d1));        
        int theIndex = Helper.ordinal(distribution,d0,d1,chkAOB);
            
    	//int placeId = Runtime.here().id;
    	//System.out.println("convert "+theIndex+"->"+distribution.getVirtualIndexAdjustment(theIndex));
    	
    	return arr_.getFloat(theIndex);
    }
    
    public float get(int d0,int d1,int d2) {return get(d0,d1,d2,true,true);}
    
    public float get(int d0, int d1, int d2,boolean chkPl,boolean chkAOB) {
        if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
            Runtime.hereCheckPlace(distribution.get(d0, d1, d2));        
        int theIndex = Helper.ordinal(distribution,d0,d1,d2,chkAOB);
    	return arr_.getFloat(theIndex);  	
    } 
    
    public float get(int d0,int d1,int d2,int d3) {return get(d0,d1,d2,d3,true,true);}
     public float get(int d0, int d1, int d2, int d3,boolean chkPl,boolean chkAOB) {
        if (chkPl && Configuration.BAD_PLACE_RUNTIME_CHECK && mutable_)
            Runtime.hereCheckPlace(distribution.get(d0, d1, d2, d3));        
        int theIndex = Helper.ordinal(distribution,d0, d1, d2, d3,chkAOB);	
    	return arr_.getFloat(theIndex);    	
    }
    
     
     public float get(int[] pos) {return get(pos,true,true);}
        
    public float get(int[] pos,boolean chkPl,boolean chkAOB) {
        final point p = Runtime.factory.getPointFactory().point(pos);
    	return get(p);
    }
    
    public x10.lang.FloatReferenceArray overlay(x10.lang.floatArray d) {
        dist dist = distribution.overlay(d.distribution);
        FloatArray_c ret = new FloatArray_c(dist, 0, safe_);
        place here = x10.lang.Runtime.runtime.currentPlace();
        try {
            for (Iterator it = dist.iterator(); it.hasNext(); ) {
                point p = (point) it.next();
                place pl = dist.get(p);
                x10.lang.Runtime.runtime.setCurrentPlace(pl);
                float val = (d.distribution.region.contains(p)) ? d.get(p) : get(p);
                ret.set(val, p);
            } 
        } finally {
            x10.lang.Runtime.runtime.setCurrentPlace(here);
        }  
        return ret;
    }
    
    public void update(x10.lang.floatArray d) {
        assert (region.contains(d.region));
        place here = x10.lang.Runtime.runtime.currentPlace();
        try {
            for (Iterator it = d.iterator(); it.hasNext(); ) {
                point p = (point) it.next();
                place pl = distribution.get(p);
                x10.lang.Runtime.runtime.setCurrentPlace(pl);
                set(d.get(p), p);
            }
        } finally {
            x10.lang.Runtime.runtime.setCurrentPlace(here);
        }  
    }
    
    public FloatReferenceArray union(x10.lang.floatArray d) {
        dist dist = distribution.union(d.distribution);
        FloatArray_c ret = new FloatArray_c(dist, 0, safe_);
        place here = x10.lang.Runtime.runtime.currentPlace();
        try {
            for (Iterator it = dist.iterator(); it.hasNext(); ) {
                point p = (point) it.next();
                place pl = dist.get(p);
                x10.lang.Runtime.runtime.setCurrentPlace(pl);
                float val = (distribution.region.contains(p)) ? get(p) : d.get(p);
                ret.set(val, p);
            }
        } finally {
            x10.lang.Runtime.runtime.setCurrentPlace(here);
        }  
        return ret;
    }
    
    public FloatReferenceArray restriction(dist d) {
        return restriction(d.region);
    }
    
    public FloatReferenceArray restriction(region r) {
        dist dist = distribution.restriction(r);
        FloatArray_c ret = new FloatArray_c(dist, 0, safe_);
        place here = x10.lang.Runtime.runtime.currentPlace();
        try {
            for (Iterator it = dist.iterator(); it.hasNext(); ) {
                point p = (point) it.next();
                place pl = dist.get(p);
                x10.lang.Runtime.runtime.setCurrentPlace(pl);
                ret.set(get(p), p);
            }
        } finally {
            x10.lang.Runtime.runtime.setCurrentPlace(here);
        }  
        return ret;
    }
    
    public x10.lang.floatArray toValueArray() {
    	if (! mutable_) return this;
    	throw new Error("TODO: <T>ReferenceArray --> <T>ValueArray");   
    }
    public boolean isValue() {
        return ! this.mutable_;
    }

    
}
