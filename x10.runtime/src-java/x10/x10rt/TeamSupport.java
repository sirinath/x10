/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */
package x10.x10rt;

import x10.core.IndexedMemoryChunk;
import x10.lang.FinishState;
import x10.lang.Place;

public class TeamSupport {
    
    /*
     * Must be manually kept in synch with x10rt_red_type in x10rt_types.h
     */
    private static final int RED_TYPE_BYTE = 1;
    private static final int RED_TYPE_SHORT = 2;
    private static final int RED_TYPE_INT = 4;
    private static final int RED_TYPE_LONG = 6;
    private static final int RED_TYPE_DOUBLE = 8;
    private static final int RED_TYPE_FLOAT = 9;
    
    private static int getTypeCode(IndexedMemoryChunk<?> chunk) {
        Object chunkRaw = chunk.getBackingArray();
        int typeCode = 0;
        if (chunkRaw instanceof byte[]) {
            typeCode = RED_TYPE_BYTE;
        } else if (chunkRaw instanceof short[]) {
            typeCode = RED_TYPE_SHORT;
        } else if (chunkRaw instanceof int[]) {
            typeCode = RED_TYPE_INT;
        } else if (chunkRaw instanceof long[]) {
            typeCode = RED_TYPE_LONG;
        } else if (chunkRaw instanceof double[]) {
            typeCode = RED_TYPE_DOUBLE;
        } else if (chunkRaw instanceof float[]) {
            typeCode = RED_TYPE_FLOAT;
        } else {
            throw new java.lang.UnsupportedOperationException("Unsupported type of src array "+chunk.T.typeName()+" in nativeAllReduce");
        }
        return typeCode;
    }
    
    private static void aboutToDie(String methodName) {
        System.err.println("About to die in " + methodName);
        System.exit(1);
    }

	public static void nativeMake(IndexedMemoryChunk<Place> places, int count, IndexedMemoryChunk<x10.core.Int> result) {
	    Place[] np = (Place[])places.getBackingArray();
	    int[] int_places = new int[np.length];
	    for (int i=0; i<places.length; i++) {
	        int_places[i] = np[i].id;
	    }
	    int[] nr = result.getIntArray();

	    FinishState fs = ActivityManagement.activityCreationBookkeeping();

	    try {
	        nativeMakeImpl(int_places, count, nr, fs);
	    } catch (UnsatisfiedLinkError e) {
	        aboutToDie("nativeMake");
	    }
	}
	
	
	public static int nativeSize(int id) {
	    if (!X10RT.forceSinglePlace){
		    int size = 0;
		    try {
		        size = nativeSizeImpl(id);
		    } catch (UnsatisfiedLinkError e) {
		        aboutToDie("nativeSize");
		    }
		    return size;
	    }
	    return 1;
	}
	
	public static void nativeBarrier(int id, int role) {
	    if (!X10RT.forceSinglePlace){
	    	FinishState fs = ActivityManagement.activityCreationBookkeeping();
		    try {
		    	nativeBarrierImpl(id, role, fs);
		    } catch (UnsatisfiedLinkError e) {
		        aboutToDie("nativeBarrier");
		    }
	    }
	}
	
    public static void nativeScatter(int id, int role, int root, IndexedMemoryChunk<?> src, int src_off, 
	                                 IndexedMemoryChunk<?> dst, int dst_off, int count) {
        aboutToDie("nativeScatter");
    }
	
    public static void nativeBcast(int id, int role, int root, IndexedMemoryChunk<?> src, int src_off, 
                                   IndexedMemoryChunk<?> dst, int dst_off, int count) {
        aboutToDie("nativeBcast");
    }
    
    public static void nativeAllToAll(int id, int role, IndexedMemoryChunk<?> src, int src_off, 
                                      IndexedMemoryChunk<?> dst, int dst_off, int count) {
        aboutToDie("nativeAllToAll");
    }
    
    public static void nativeAllReduce(int id, int role, IndexedMemoryChunk<?> src, int src_off, 
                                       IndexedMemoryChunk<?> dst, int dst_off, int count, int op) {
        Object srcRaw = src.getBackingArray();
        Object dstRaw = dst.getBackingArray();
        
        int typeCode = getTypeCode(src);
        assert getTypeCode(dst) == typeCode : "Incompatible src and dst arrays";
        
        FinishState fs = ActivityManagement.activityCreationBookkeeping();

        try {
            nativeAllReduceImpl(id, role, srcRaw, src_off, dstRaw, dst_off, count, op, typeCode, fs);
        } catch (UnsatisfiedLinkError e) {
            aboutToDie("nativeAllReduce");
        }
    }

    public static void nativeIndexOfMax(int id, int role, IndexedMemoryChunk<?> src,
                                        IndexedMemoryChunk<?> dst) {
        aboutToDie("nativeIndexOfMax");
    }

    public static void nativeIndexOfMin(int id, int role, IndexedMemoryChunk<?> src,
                                        IndexedMemoryChunk<?> dst) {
        aboutToDie("nativeIndexOfMin");
    }

    public static void nativeSplit(int id, int role, int color, int new_role, IndexedMemoryChunk<x10.core.Int> result) {
        aboutToDie("nativeSplit");
    }
    
    public static void nativeDel(int id, int role) {
        FinishState fs = ActivityManagement.activityCreationBookkeeping();
        
        try {
            nativeDelImpl(id, role, fs);
        } catch (UnsatisfiedLinkError e) {
            aboutToDie("nativeDel");
        }
    }
    
	private static native void nativeMakeImpl(int[] places, int count, int[] result, FinishState fs);
	
	private static native int nativeSizeImpl(int id);
	
	private static native void nativeBarrierImpl(int id, int role, FinishState fs);
	
	private static native void nativeAllReduceImpl(int id, int role, Object srcRaw, int src_off, 
	                                               Object dstRaw, int dst_off,
	                                               int count, int op, int typecode, FinishState fs);

	private static native void nativeDelImpl(int id, int role, FinishState fs);
	
	static native void initialize();
}
