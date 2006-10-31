package polyglot.ext.x10.types.constr;

import java.util.HashMap;

/**
 * All nodes that occur in the graph maintained by a constraint
 * must implement Promise. 
 * 
 * @author vj
 *
 */
public interface Promise {
	
	/**
	 * vars must be a sequence C_Var, C_Field, ... C_Field, satisfying the property
	 * that the receiver of each element (other than the first) is the preceding element.
	 * this must be the promise corresponding to the index'th element in this list. 
	 * Return the node in the graph of constraint c obtained
	 * by following the path specified by vars[index],.., vars[path.length-1] from this
	 * node. Create new nodes if necessary so that this path is defined
	 * in the graph of c. Return the node at the end of the path. The returned node
	 * must not be a forwarded node.
	 * @param path
	 * @param index
	 * @param c
	 * @return
	 */
	Promise intern(C_Var[] vars, int index);
	/**
	 * vars and this must be as for intern. Return the node in the graph of constraint
	 * c obtained by following the path specified by vars[index],....vars[path.length-1] 
	 * from this node. Return null if the path does not exist; do not create new nodes.
	 * @param vars
	 * @param index
	 * @return
	 */
	Promise lookup(C_Var[] vars, int index);
	Promise lookup(String s);
	Promise lookup();
	
	/**
	 * An eq link entering this has just been established. Now the 
	 * children of the source of the link have to be transferred to this.
	 * If this does not have an s-child, then install the child as its s child.
	 * If it does, then recursively bind the s-child to this's s child.
	 * 
	 * @param s -- the name of the field
	 * @param child -- the s child of the source of the eq link.
	 */
	void addIn(String s, Promise child) throws Failure;
	
	/**
	 * Bind this promise to the given target. All the children of this, if any, have to be
	 * added into the target. target should satisfy the condition that it is not forwarded.
	 * Return true if the execution of this operation caused a change to the constraint graph;
	 * false otherwise.
	 * @param target
	 */
	boolean bind(Promise target) throws Failure;
	
	/** Has this promise been forwarded?
	 * 
	 * @return true if it has been forwarded (its value !=null)
	 */
	boolean forwarded();
	
	/** Does this node have children?
	 * A node cannot both have children and be forwaded.
	 */
	boolean hasChildren();
	
	/**
	 * Is there a path from here to p? 
	 * @param p
	 * @return
	 */
	boolean canReach(/*@nonnull*/ Promise p);
	
	/**
	 * Traverse the subtree under this promise, and add t1 -> t2 into result for any term t1 
	 * which has an outgoing edge to a term tw.
	 * @param result
	 */
	void dump(HashMap/*<C_Term,C_Term>*/ result);
	void dump(HashMap/*<C_Term, C_Term>*/ reuslt, C_Term newSelf, C_Term newThis);
	
	/**
	 * Return the term that labels this promise.
	 * 
	 * @return
	 */
	C_Term term();
	
}
