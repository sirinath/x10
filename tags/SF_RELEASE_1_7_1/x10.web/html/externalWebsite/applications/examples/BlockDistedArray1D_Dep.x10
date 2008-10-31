//import x10.lang.Object;
//import harness.x10Test;

/**
* Building arrays distributed accross places using the union-of-distribution approach.
* The performance of this kind of arrays is very poor at this moment.
* @author Tong 11/29/2006
* Modified by T.W. 11/29/2007: comment out the import statements;
*                              replace dist.factory.unique() with dist.UNIQUE;
*                              add rect property. 
*/
public class BlockDistedArray1D_Dep extends x10Test {
	public final static int SIZE=5; 
	public final static int N_PLACES=place.MAX_PLACES; 
	public final static dist ALLPLACES=dist.UNIQUE;
        public boolean run() {
        	dist(:rank==1&&rect) D;
        	D=[0:SIZE-1]->place.factory.place(0);
        	for(int i=1;i<N_PLACES;i++) D=(dist(:rank==1&&rect))(D||([i*SIZE:(i+1)*SIZE-1]->place.factory.place(i)));
        	final int [:rank==1&&rect] intArray=new int [D] (point [i]) {return i;}; 
         	final double [:rank==1&&rect] dblArray=new double [D] (point [i]) {return i*0.1;};
        	finish ateach(point p[i]:ALLPLACES)
    			for (point [j]: intArray|here) 
    				dblArray[j]+=intArray[j]; 
    				
	    return true;
	}
	
	public static void main(String[] args) {
		new BlockDistedArray1D_Dep().execute();
	}

}

