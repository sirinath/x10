/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * Check that x=y implies x.f=y.f.
 *
 * @author vj
 */
public class CheckDescendEntails extends x10Test {
	class Prop(int i, int j) {
		public Prop(int i, int j) {
			this.i=i; this.j=j;
		}
	}
  
    class Test(Prop a, Prop b) {
        public Test(Prop a, Prop b) { this.a = a; this.b=b;}
     }
  
	public boolean run() {
		Prop p = new Prop(1,2);
		
	  Test(:a == b) t = (Test(:a==b)) new Test(p,p);
	  Test(:a.i == b.i) u = t;
	   return true;
	}
	public static void main(String[] args) {
		new CheckDescendEntails().execute();
	}
}