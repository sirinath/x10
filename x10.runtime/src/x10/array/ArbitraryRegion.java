/*
 * Created on Jan 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package x10.array;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;

import x10.lang.PointOutOfRegionError;
import x10.lang.point;
import x10.lang.region;

/**
 * @author Christoph von Praun
 */
public class ArbitraryRegion extends region {

    private final SortedSet points_; 
    
    private ArbitraryRegion(int rank) {
        super(rank);
        points_ = new TreeSet();
    }
    
    private static class PointComparator_ implements java.util.Comparator {
        public int compare(java.lang.Object o1, java.lang.Object o2) {
            point_c p1 = (point_c) o1;
            point_c p2 = (point_c) o2;
            return p1.compareTo(p2);
        }
        public boolean equals(Object o) {
            return false;
        }
    };
    
    public ArbitraryRegion(region[] dims) {
        super(dims.length);
        int sz = dims[0].size();
        int sz_total = sz;
        for (int i = 1; i < dims.length; ++ i) {
            assert dims[i].size() == sz;
            assert dims[i].rank == 1;;
        }
 
        points_ = new TreeSet(new PointComparator_());
        HashSet x = new HashSet();
        permutations_(x, new int[]{}, dims);
        points_.addAll(x);
        System.out.println( "x=== " + x.size() + "   points===" + points_.size());
    }
    
    /* create all points in the region that is spawned by the dimensions in var */
    private void permutations_(Set hs, int[] fix, region[] var) {
        if (var.length == 0) {
            point new_point = point.factory.point(ArbitraryRegion.this, fix);
            hs.add(new_point);
        } else {
            region[] var_new = new region[var.length - 1];
            System.arraycopy(var, 1, var_new, 0, var.length - 1);
            region cur_reg = var[0];
            int[] fix_new = new int[fix.length + 1];
            System.arraycopy(fix, 0, fix_new, 0, fix.length);
            for (Iterator it = cur_reg.iterator(); it.hasNext(); ) {
                point tmp = (point) it.next();
                int tmp_i = tmp.valueAt(0);
                fix_new[fix.length] = tmp_i;
                permutations_(hs, fix_new, var_new);
            }
        }
    }
    
    public ArbitraryRegion(int rank, Set points) {
        super(rank);
        assert (points != null);
        
        for (Iterator it = points.iterator(); it.hasNext(); ) {
            java.lang.Object o = it.next();
            assert o instanceof point;
            point p = (point) o;
            assert p.rank == rank;
        }
        
        points_ = new TreeSet();
        points_.addAll(points);
    }
    
    
    /* modifies this region - this method should be only called during the initialization 
     * or creation of an arbitrary region - hence it is private. 
     */
    private void add_(point p) {
        assert p.rank == rank;
        points_.add(p);
    }
    
    /* (non-Javadoc)
     * @see x10.lang.region#size()
     */
    public int size() {
        return points_.size();
    }

    /* returns a one-dimenasional region (formerly called range)
     */
    public region rank(int index) {
        ArbitraryRegion ret = new ArbitraryRegion(1);
        for (Iterator it = iterator(); it.hasNext(); ) {
            point p = (point) it.next();
            point p_onedim = point.factory.point(ArbitraryRegion.this, new int[] {p.valueAt(index)});
            ret.add_(p_onedim);
        }
        return ret;
    }

    /* 
     * TODO cvp->vj
     */
    public boolean isConvex() {
        throw new Error("TODO");
    }

    /* 
     * method only menaingful for regions of one dimension (aka ranges)
     */
    public int low() throws EmptyRegionError {
        assert rank == 1;
        if (points_.isEmpty())
            throw new EmptyRegionError();
        
        point p = (point) points_.first(); 
        return p.valueAt(0);
    }

    /* 
     * method only menaingful for regions of one dimension (aka ranges)
     */
    public int high() throws EmptyRegionError {
        assert rank == 1;
        if (points_.isEmpty())
            throw new EmptyRegionError();
        
        point p = (point) points_.last(); 
        return p.valueAt(0);
    }

    /* (non-Javadoc)
     * @see x10.lang.region#union(x10.lang.region)
     */
    public region union(region r) {
        return union(this, r);
    }
    
    protected static region union(region r1, region r2) {
        assert r1.rank == r2.rank;
        
        ArbitraryRegion ret = new ArbitraryRegion(r1.rank);
        for (Iterator it = r1.iterator(); it.hasNext(); ) {
            point p = (point) it.next();
            ret.add_(p);
        }
        for (Iterator it = r2.iterator(); it.hasNext(); ) {
            point p = (point) it.next();
            ret.add_(p);
        }
        return ret;
    }

    /* 
     */
    public region intersection(region r) {
        return intersection(this, r);
    }
    
    protected static region intersection(region r1, region r2) {
        assert r1.rank == r2.rank;
        
        ArbitraryRegion ret = new ArbitraryRegion(r1.rank);
        for (Iterator it = r1.iterator(); it.hasNext(); ) {
            point p = (point) it.next();
            if (r2.contains(p))
                ret.add_(p);
        }
        return ret;
    }

    /* (non-Javadoc)
     * @see x10.lang.region#difference(x10.lang.region)
     */
    public region difference(region r) {
        return difference(this, r);
    }
    
    protected static region difference(region r1, region r2) {
        ArbitraryRegion ret = new ArbitraryRegion(r1.rank);
        for (Iterator it = r1.iterator(); it.hasNext(); ) {
            point p = (point) it.next();
            if (! r2.contains(p))
                ret.add_(p);
        }
        return ret;
    }

    /* 
     * TODO cvp->vj
     */
    public region convexHull() {
        throw new Error("TODO");
    }

    /* (non-Javadoc)
     * @see x10.lang.region#contains(x10.lang.point)
     */
    public boolean contains(point p) {
        assert p != null;
        return points_.contains(p);
    }

    /* (non-Javadoc)
     * @see x10.lang.region#contains(int[])
     */
    public boolean contains(int[] p) {
        assert p != null;
        boolean ret;
        if (p.length == rank) {
            point pp = point.factory.point(ArbitraryRegion.this, p);
        	ret = contains(pp);
        } else
            ret = false;
        return ret;
    }

    /* (non-Javadoc)
     * @see x10.lang.region#disjoint(x10.lang.region)
     */
    public boolean disjoint(region r) {
        return intersection(r).size() == 0;
    }

    /* (non-Javadoc)
     * @see x10.lang.region#ordinal(x10.lang.point)
     */
    public int ordinal(point p) throws EmptyRegionError, PointOutOfRegionError {
        int ret = 0;
        if (size() == 0)
            throw new EmptyRegionError();
        if (!contains(p))
            throw new PointOutOfRegionError();
        else {
            for (Iterator it = iterator(); it.hasNext(); ) {
    		    point q = (point) it.next();
    		    if (q.equals(p))
    		        break;
    		    else
    		        ret++;
            }
        }
        return ret;
    }

    /* (non-Javadoc)
     * @see x10.lang.region#coord(int)
     */
    public point coord(int ord) throws PointOutOfRegionError {
        point ret;
        if (ord >= size())
            throw new PointOutOfRegionError();
        else {
            int ctr = 0;
            Iterator it = iterator();
            while (ctr++ < ord) it.next();
            ret = (point) it.next();
        }
        return ret;
    }

    /* (non-Javadoc)
     * @see x10.lang.region#iterator()
     */
    public Iterator iterator() {
        return points_.iterator();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (Iterator it = iterator(); it.hasNext(); ) {
		    sb.append(it.next().toString());
			if (it.hasNext())
				sb.append(",");
		}
		sb.append("}");
		return sb.toString();
    }





}
