 /**  
 Check that circular dependencies between classes (through fields) are handled correctly
 during TypeElaboration.
 
 *@author vj
 *
 */

import harness.x10Test;

 public class CircularLV(int k)  extends x10Test { 
  
	 public CircularLV(int k) { this.k = k}
	
	 public boolean  run() { 
		 CLV1(:i==j) h = (CLV1(:i==j)) new CLV1(4,4);
		 return true;
	 }
   
  public static void main(String[] args) {
	  new CircularLV(4).execute();
	}
 }