/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import x10.lang.Object;
public class List(int(:self >=0) n) {
  
    this(:n>0) protected  Object  value;
    this(:n>0) protected  List(n-1) tail;
  
    public List(t.n+1)(Object o, List t) {
        n=t.n+1;
        tail = t;
        value = o;
    }
    public List(0) () {
        n=0;
       
    }
     public  List(l.n) a(List l) {
    	
        return (n==0) ? l : new List(value, tail.append(l));
    }
   
    public  List(n+l.n) append(List l) {
        final int nn =n;
        return n==0? ((List(0))this).a(l) : ((List(:nn>0)) this).a(l);
    }
    
    this(:n>0) public  Object nth( int(: self >= 1 && self <= n) k) {
        return k==1 ? (Object) value : tail.nth(k-1);
    }
  
    public List(k) gen(final int(: self >= 0) k) {
        return k==0 ? new List() : new List(k, gen(k-1));
    }
  
}