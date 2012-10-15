import x10.util.Ordered;
import x10.util.Random;
/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to you under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */
/* PROGRAMMING NOTES: X10 arrays are indexed by a Region that consists
 * of a collection of Points.  Points have integer-valued coordinates,
 * and the first coordinate is the 0-th coordinate.  If R is a Region,
 * then R.min(k) is the smallest value its k-th coordinate takes on.
 * R.max(k) is the largest value.  A C-like array is 1 dimensional:
 * its Region R consists of 1-dimensional Points.  Such Points have
 * only 1 coordinate, namely, the 0-th coordinate.  The value of
 * that coordinate runs from R.min(0) to R.max(0).
 *
 * We choose the pivot to be the middle element of the slice that
 * e are sorting now.  R. Sedgwick suggested that choosing instead
 * the median of the first, middle, last elements should help. This
 * seems to improve performance on our laptop just a bit, about 3%.
 * See QSortInts3.x10 for a version that uses this idea.
 * 
 * One more note: we've made the INSERTION_LIMIT a constant: actually
 * you probably want to supply it, if not at run-time, at least as a
 * function of the "environment", so you can tune the sort.
 */

/**
 * Straightforward quicksort implementation using shared-memory, single
 * Place parallelism.  For some history and pointers to other refinements,
 * the Wikipedia article 
 *    <a  href="http://en.wikipedia.org/wiki/Quicksort">quicksort</a>
 * is a fast, reasonable start.
 */
public class QSortInts2 {
   /**
    * top-level call: sorts the input array in ascending order
    * in place using the quicksort algorithm.  
    * @param data the array of Ints to be sorted.
    */
   public static def sort(data: Array[Int](1)) {
      val r = data.region; // == the interval used to index data()
      finish sort(data, r.min(0), r.max(0)); 
   }
   private static val INSERTION_LIMIT  = 16;
   /**
    * Spawns threads only as needed to divide up the work
    * of sorting a slice of the data array from left to right.
    * @param data the whole Array to be sorted
    * @param left the first index in a subarray to be sorted by
    *    this call
    * @param right the last index in a subarray to be sorted by
    *    this call
    */
   private static def sort(data:Array[Int](1), var left:Int, var right:Int) {
      //Console.OUT.println("sort("+left+", "+right+")");
      while(true) {
         var i: Int = left;
         var j: Int = right;
         if (right - left <= INSERTION_LIMIT) {
            insertionSort(data, left, right);
            return;
         }
         val pivot = data(left + (right-left)/2);
 	      while (i <= j) {
	         while (data(i) < pivot) i++;
	         while (data(j) > pivot) j--;
	         if (i <= j) {
	            val tmp = data(i);
	            data(i++) = data(j);
	            data(j--) = tmp;
	         }
	      }
	      if (left < j) { 
	         if (i < right) {  // two slices to sort: use async for one
	            val iVal = i, rightVal = right;
	            async sort(data, iVal, rightVal);
	            right = j;     // sort from current left to j
	         }
	         else right = j;
	      }
	      else if (i < right) { // just sort from i to current right
	         left = i; 
	      }
	      else return;
      }
   }  
   /**
    * This procedure sorts a slice of a one-dimensional array in ascending order.
    * It uses "insertion sort":  after the k-th iteration, the initial
    * k elements are in sorted order.  The next iteration works back through
    * that initial slice until it finds the first place to insert the k+1-st
    * element.
    * @param data the array of Ints to be sorted
    * @param left the first index in the slice
    * @param right the final index in the slice
    */
   private static def insertionSort(data: Array[Int](1), left:Int, right:Int) {
      for(var i:Int = left+1; i<=right; i++) { 
         val value = data(i);  // next value to insert
   		var j:Int = i - 1;
   		while(true) {         // shift value's successors to the right
   			if (data(j) > value) {
   			   data(j + 1) = data(j);
   				j = j - 1;
   				if (j < left) break;
   			}
   			else break;
   		}
   		data(j + 1) = value;  // and then insert it
      }
   }
   
   /*
    * code from this point on is just here to allow you to exercise the sort.
    */
   private static def showData(data:Array[Int](1), leader: String) {
      Console.OUT.println(leader);
      val end = data.size - 1;
      if (end < 120) {
	      for (var i: Int = 0; i<end; i++) { 
	         Console.OUT.print(""+data(i)+(i%10==9?"\r\n":", ")); 
	      }
	   }
      else {
         val breakpoint = 58;
         for (var i: Int = 0; i<=58; i++) {
            Console.OUT.print(""+data(i)+(i%10==9?"\r\n":", ")); 
         }
         Console.OUT.println(""+data(59)+"...");
         val start = data.size - 60;
         for(var i: Int = start; i<end; i++) {    
            Console.OUT.print(""+data(i)+((i-start)%10==9?"\r\n":", ")); 
         }
      }
      Console.OUT.println(""+data(data.size-1));
      Console.OUT.println("\r\n=====================\r\n");
   }
   
   public static def main(args:Array[String](1)) {
      val N = args.size>0 ? Int.parse(args(0)) : 100;
      val X = new Random();
      val rand = (Int) => X.nextInt(4*N); 
      val data: Array[Int](1) = new Array[Int](N, rand);
      showData(data, "UNSORTED:\r\n");
      sort(data);
      showData(data, "SORTED:\r\n");
   }
}