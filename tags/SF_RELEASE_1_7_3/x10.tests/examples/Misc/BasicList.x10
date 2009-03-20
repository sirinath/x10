/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */

public class BasicList(n: int{self >=0}) {
  
    protected var value: Object;
    protected var tail: BasicList;
  
    public def this(var o: Object, var t: BasicList): BasicList = {
        property(t.n+1 as int{self>=0});
        tail = t;
        value = o;
    }
    public def this(): BasicList = {
        property(n as int{self>=0});
    }
     public def a(l: BasicList): BasicList = {
        return (n==0) ? l : new BasicList(value, tail.append(l));
    }
   
    public def append(var l: BasicList): BasicList = {
        return n==0? (this as BasicList{n==0}).a(l) : (this as BasicList{n>0}).a(l);
    }
    
    public def nth(var k: int{self >= 1 && self <= n}): Object = {
        return k==1 ? value : tail.nth(k-1);
    }
  
    public def gen(val k: int{self >= 0}): BasicList = {
        return k==0 ? new BasicList() : new BasicList(k, gen(k-1));
    }
}
