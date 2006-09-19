/*
 * Created on Oct 28, 2004
 */
package x10.array;

import java.util.Iterator;
import x10.lang.dist;
import x10.lang.place;
import x10.lang.point;
import x10.lang.CharReferenceArray;

/**
 * @author Christoph von Praun
 * 
 * Char Arrays are currently not implemented.
 */
public abstract class CharArray extends CharReferenceArray {
    public CharArray(dist d) {
        super(d);
    }
    
    public static class Assign extends Operator.Unary {
        private final char c_;

        public Assign(char c) {
            c_ = c;
        }

        public char apply(char i) {
            return c_;
        }
    }

    protected void assign(CharArray rhs) {
        assert rhs instanceof CharArray;
        place here = x10.lang.Runtime.runtime.currentPlace();
        CharArray rhs_t =  rhs;
        try {
            for (Iterator it = rhs_t.distribution.region.iterator(); it.hasNext();) {               
                point pos = (point) it.next();
                place pl = distribution.get(pos);
                x10.lang.Runtime.runtime.setCurrentPlace(pl);
                set(rhs_t.get(pos), pos);
            }
        } finally {
            x10.lang.Runtime.runtime.setCurrentPlace(here);
        }
    }

	/*
	 * Generic implementation - an array with fixed, known number of dimensions
	 * can of course do without the Iterator.
	 */
	public void pointwise(CharArray res, Operator.Pointwise op, CharArray arg) {
	    assert res.distribution.equals(distribution);
        assert arg.distribution.equals(distribution);
        
        place here = x10.lang.Runtime.runtime.currentPlace();
		CharArray arg_t =  arg;
		CharArray res_t = res;
		try {
		    for (Iterator it = distribution.region.iterator(); it.hasNext(); ) {
		        point p = (point) it.next();
		        place pl = distribution.get(p);
		        x10.lang.Runtime.runtime.setCurrentPlace(pl);
		        char arg1 = get(p);
		        char arg2 = arg_t.get(p);
		        char val = op.apply(p, arg1, arg2);
		        res_t.set(val, p);
		    }
		} finally {
		    x10.lang.Runtime.runtime.setCurrentPlace(here);
		}
	}
	
	public void pointwise(CharArray res, Operator.Pointwise op) {
	    assert res == null || res.distribution.equals(distribution);
        
	    place here = x10.lang.Runtime.runtime.currentPlace();
        try {
	        for (Iterator it = distribution.region.iterator(); it.hasNext(); ) {
	            point p = (point) it.next();
	            place pl = distribution.get(p);
	            x10.lang.Runtime.runtime.setCurrentPlace(pl);
	            char arg1 = get(p);
	            char val = op.apply(p, arg1);
	            if (res != null)
	                res.set(val, p);
	        }
	    } finally {
	        x10.lang.Runtime.runtime.setCurrentPlace(here);
	    }
	}
	
	public void reduction(Operator.Reduction op) {
	    place here = x10.lang.Runtime.runtime.currentPlace();
        try {
	        for (Iterator it = distribution.region.iterator(); it.hasNext(); ) {
	            point p = (point) it.next();
	            place pl = distribution.get(p);
                x10.lang.Runtime.runtime.setCurrentPlace(pl);
                char arg1 = get(p);
	            op.apply(arg1);
	        } 
	    } finally {
	        x10.lang.Runtime.runtime.setCurrentPlace(here);
	    }
	}
	
	public void scan(CharArray res, Operator.Unary op) {
	    assert res.distribution.equals(distribution);
	    
	    place here = x10.lang.Runtime.runtime.currentPlace();
	    try {
	        for (Iterator it = distribution.region.iterator(); it.hasNext(); ) {
	            point p = (point) it.next();
	            place pl = distribution.get(p);
	            x10.lang.Runtime.runtime.setCurrentPlace(pl);
	            char arg1 = get(p);
	            res.set(op.apply(arg1), p);
	        }
	    } finally {
	        x10.lang.Runtime.runtime.setCurrentPlace(here);
	    }
	}
	
	public void scan( CharArray res, pointwiseOp op ) {
	    assert res == null || res instanceof CharArray;
	    assert res.distribution.equals(distribution);
	    
	    place here = x10.lang.Runtime.runtime.currentPlace();
	    CharArray res_t = (res == null) ? null : (CharArray) res;
	    try {
	        for (Iterator it = distribution.region.iterator(); it.hasNext();) {
	            point p = (point) it.next();
	            place pl = distribution.get(p);
	            x10.lang.Runtime.runtime.setCurrentPlace(pl);
	            char val = op.apply(p);
	            if (res_t != null)
	                res_t.set(val, p);
	        }
	    } finally {
	        x10.lang.Runtime.runtime.setCurrentPlace(here);
	    }
    }
    
	
	/**
     * Generic flat access.
     */
    public abstract char set(char v, point pos);

    public abstract char set(char v, int d0);

    public abstract char set(char v, int d0, int d1);

    public abstract char set(char v, int d0, int d1, int d2);

    public abstract char set(char v, int d0, int d1, int d2, int d3);
    public abstract char set(char v, point pos,boolean cPl,boolean chkAOB);

    public abstract char set(char v, int d0,boolean cPl,boolean chkAOB);

    public abstract char set(char v, int d0, int d1,boolean cPl,boolean chkAOB);

    public abstract char set(char v, int d0, int d1, int d2,boolean cPl,boolean chkAOB);

    public abstract char set(char v, int d0, int d1, int d2, int d3,boolean cPl,boolean chkAOB);

    /**
     * Generic flat access.
     */
    public abstract char get(point pos);

    public abstract char get(int d0);

    public abstract char get(int d0, int d1);

    public abstract char get(int d0, int d1, int d2);

    public abstract char get(int d0, int d1, int d2, int d3);
    public abstract char get(int[] p);
    public abstract char get(point pos,boolean cPl,boolean chkAOB);

    public abstract char get(int d0,boolean cPl,boolean chkAOB);

    public abstract char get(int d0, int d1,boolean cPl,boolean chkAOB);

    public abstract char get(int d0, int d1, int d2,boolean cPl,boolean chkAOB);

    public abstract char get(int d0, int d1, int d2, int d3,boolean cPl,boolean chkAOB);
    public abstract char get(int[] p,boolean cPl,boolean chkAOB);
    
    public Object toJava() {        
        final int[] dims_tmp = new int[distribution.rank];       
        for (int i = 0; i < distribution.rank; ++i) {
            dims_tmp[i] = distribution.region.rank(i).high() + 1;
        }
        
        final Object ret = java.lang.reflect.Array.newInstance(Character.TYPE, dims_tmp);
        pointwise(null, new Operator.Pointwise() {
            public char apply(point p, char arg) {
                Object handle = ret;
                int i = 0;
                for (; i < dims_tmp.length - 1; ++i) {
                    handle = java.lang.reflect.Array.get(handle, p.get(i));
                }
                java.lang.reflect.Array.setChar(handle, p.get(i), arg);
                return arg;
            }
        });
        return ret;
    }
    
    /* for debugging */
    public static void printArray(String prefix, char[][] a) {
        System.out.print(prefix + "{");
        for (int i = 0; i < a.length; ++i) {
            System.out.print("{");
            for (int j = 0; j < a[i].length; ++ j) {
                System.out.print(a[i][j]);
                if (j < a[i].length - 1)
                    System.out.print(", ");
            }
            System.out.print("}");
            if (i < a.length - 1)
                System.out.print(", ");
        }
        System.out.println("}");
    }

}
