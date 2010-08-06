package x10.finish.table;
/**
 * 
 * class represents a finish block in x10
 */
public class CallTableScopeKey extends CallTableKey {
	
	private static final long serialVersionUID = 1L;
	/**
	 * true if this object represents a "finish" contruct in the program
	 * otherwise it represents an "at"
	 */
	public boolean is_finish;
	//default pattern
	public int pattern=0;
	/**
	 * to calculate the arity of a method call from this block
	 */
	public int blk;
	
	public CallTableScopeKey(String s,String n, int line, int column, 
		int b, boolean f) {
		// finish = f;
		super(s,n,line,column);
		blk = b;
		is_finish = f;
	}
	
	/**
	 * although "tmp" is not necessary to included as part of a signature
	 */
	public String genSignature(){
	    String tmp;
	    if(is_finish == true){
	    	    tmp = ".finish.";
	    }
	    else{
	    	    tmp = ".at.";
	    }
	    return (scope + tmp + line + "." + column);
	}
	
	public String toString() {
	    return genSignature()+"."+pattern;
	}

	public boolean equals(Object o) {
	    if (o instanceof CallTableScopeKey) {
		return this.toString().equals(((CallTableScopeKey) o).toString());
	    }
	    return false;
	}

	public int hashCode() {
	    return this.toString().hashCode();
	}
}
