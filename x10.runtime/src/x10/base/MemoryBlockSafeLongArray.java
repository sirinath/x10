/*
 * Created on Nov 14, 2004
 */
package x10.base;

/**
 * @author Christoph von Praun
 */
public class MemoryBlockSafeLongArray extends MemoryBlockSafe {
    
    private long[] arr_;
    
    MemoryBlockSafeLongArray(int count) {
        arr_ = new long[count];
    }
    
    MemoryBlockSafeLongArray(long[] a) {
        arr_ = a;
    }
    
    public int count() { 
        return arr_.length;
     }

     public long size() {
         return Allocator.SIZE_DOUBLE;
     }
     
     public void setLong(long val, int d0) {
        arr_[d0] = val;
    }
    
    public long getLong(int d0) {
        return arr_[d0];
    }
}
