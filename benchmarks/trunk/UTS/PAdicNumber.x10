/**
 * A representation of a number in K "digits" of base P.
 * Useful in hypercube based routing.
 */
public class PAdicNumber(P:Int, K:Int) {
	global val digits: ValRail[Int]/*(K)*/;
	public static def pow(w:Int, var n:Int) {
		var result:Int=1;
	while (n-- > 0) result *= w;
	return result;
	}
	def pow(n:Int) = pow(P, n);
	public def this(p:Int, k:Int, x:Int):PAdicNumber{self.P==p, self.K==k} {
		property(p,k);
		digits = ValRail.make
	     (k, (i:Int) => { val wi = pow(p,i); (x % (p*wi))/wi});
	}
	def this (p:Int, k:Int, ds:ValRail[Int]/*(k)*/) {
		property(p, k);
		digits = ds;
	}
	public safe global operator this < (that:PAdicNumber(P,K)):Boolean {
		var i:Int =0;
		for (; i < K && digits(i) < that.digits(i); ++i) ;
		return i==K-1;
	}
	public global safe def equals(o:Any):Boolean {
		if (! (o instanceof PAdicNumber)) 
			return false;
		val other = o as PAdicNumber;
		if (P != other.P || K != other.K) 
			return false;
		for (var i:Int=0; i < K; ++i)
			if (digits(i) != other.digits(i))
				return false;
		return true;
	}
	public global safe def hashCode():Int {
		return P+K+digits.hashCode();
	}
	
	/**
	 * Return the number distance d away along dimension dim (using modulo arithmetic). 
	 */
	global public def delta(d:Int, dim:Int)= 
		new PAdicNumber(P, K, ValRail.make(K, 
				(i:Int) => (i==dim ?  (digits(i)+d)% P : digits(i))));
	
	global public def boundedDelta(d:Int, dim:Int, bound:Int): PAdicNumber(P,K) {
		val o = new PAdicNumber(P, K, ValRail.make(K, 
				(i:Int) => (i==dim ?  (digits(i)+d)% P : digits(i))));

		val od = o.toDecimal();
		if (od < bound)
			return o; 
		var q:PAdicNumber(P,K)  = o.delta(d,dim);
		var qd:Int = q.toDecimal();
		while (qd >= bound) {
			q = q.delta(d,dim);
			qd = q.toDecimal();
			if (qd == od)
				break;
		}
		return q;
	}
		
	
	
	global public def toDecimal():Int {
		 var result:Int=digits(K-1);
	     for (var i:Int=K-1; i > 0; i--) {
		    result = result*P + digits(i-1);
	     }
	     return result;
	}
	global safe public def toString() {
		var result:String="";
		for (var i:Int=K-1; i >= 0; i--) {
			result += digits(i);
			if (i > 0)
				result += "^";
		}
		return result;
	}
	
  public static def main2(args: Rail[String]!) {
	  val n = args.length;
	  if (n < 2) {
		  Console.OUT.println("Usage: PAdicNumbers w:Int k:Int n1:Int ... nk:Int");
		  return;
	  }
	  val w = Int.parseInt(args(0));
	  val k = Int.parseInt(args(1));
	  Console.OUT.println("w="  + w + " k=" + k);
	  for (var i:Int=2; i < n; ++i) {
		  val x = Int.parseInt(args(i));
		  val p = new PAdicNumber(w, k, x);
		  Console.OUT.println(x + "==> " + p + "; " + p.toDecimal());
	  }
	  if (n == 2) {
		  val high = pow(w,k);
		  for (var x:Int=0; x < high; x++) {
			  val p = new PAdicNumber(w, k, x);
			  val px = p.toDecimal();
			  if (x != px)
				  Console.OUT.println("Error for " + x + " p=" + p + " p.toDecimal()=" + px);
		  }
	  }
	  Console.OUT.println("done.");
  } 
  
  public static def main(args: Rail[String]!) {
	  val n = args.length;
	  if (n < 2) {
		  Console.OUT.println("Usage: PAdicNumbers P:Int k:Int");
		  return;
	  }
	  val P = Int.parseInt(args(0));
	  val k = Int.parseInt(args(1));
	  val w = NetworkGenerator.findW(P, k);
	  Console.OUT.println("P=" + P + " w="  + w + " k=" + k);
	  
	  val m = NetworkGenerator.generateSparseEmbedding(P,k);
	  for ((r) in 0..P-1) {
		  Console.OUT.print("Place " + r + ":" + new PAdicNumber(w,k, r) + "=> " );
		  for ((i) in 0..k-1) {
			  Console.OUT.print(" " + m(r)(i) + ":" 
					  + (m(r)(i) == -1 ? "-1" : new PAdicNumber(w, k, m(r)(i)).toString()));
		  }
		  Console.OUT.println();
	  }
	  Console.OUT.println("done.");
  } 
}
