/*
 *
 * (C) Copyright IBM Corporation 2006
 *
 *  This file is part of X10 Test.
 *
 */
import harness.x10Test;

/**
 * A distributed binary tree.
 * @author Satish Chandra 4/6/2006
 * @author vj
 *
 *       ____P0
 *      |     |
 *      |     |
 *    _P2  __P0
 *   |  | |   |
 *   |  | |   |
 *  P3 P2 P1 P0
 *   *  *  *  *
 *
 * Right child is always on the same place as its parent;
 * left child is at a different place at the top few levels of the tree,
 * but at the same place as its parent at the lower levels
 */
public class Tree extends x10Test {
	nullable<Tree> left; // depending on localleft
	nullable<Tree> right; // always current
	nullable<Tree> next; // mixed for lists
	int size;
	boolean localLeft; // if true then left child is at current

	private Tree() { }

	public Tree(boolean ll, nullable<Tree> l, nullable<Tree> r, int s) {
		left = l; right = r; next = null; localLeft = ll; size = s;
	}

	/**
	 * Thread all the nodes together in a post-ordered list,
	 * returning the first node in the list.
	 */
	public Tree postOrder() {
		Tree result = this;
		if (right != null) {
			result = right.postOrder();
			right.next = this;
		}
		if (left != null) {
			final Tree tt = result;
			result = localLeft? left.postOrder(tt)
				: future(left) { left.postOrder(tt) }.force();
		}
		return result;
	}

	Tree postOrder(Tree rest) {
		next = rest;
		return postOrder();
	}

	// Create a binary tree on span places
	public static nullable<Tree> build(final int count, final int span) {
		if (count == 0) return null;
		boolean ll = (span/2 == 0);
		return new Tree(ll, (ll? build(count/2, span/2) :
					future(place.places(here.id+span/2)) { build(count/2, span/2) }.force()),
				build(count/2, span/2), count);
	}

	public void print(final String prefix, final String suffix) {
		System.out.print(prefix + "{" + size);
		if (left != null) {
			System.out.println("");
			if (localLeft)
				left.print(prefix+" ", ",");
			else finish async(left) { left.print(prefix+" ", ","); };
		}
		if (right != null) {
			if (left == null)	System.out.println("");
			right.print(prefix+" ", "}"+suffix);
		} else
			System.out.println((left != null ? prefix : "")+"}"+suffix);
	}

	public int sum() {
		return size+((right == null) ? 0 : right.sum())
			+((left == null) ? 0 : (localLeft ? left.sum() :
						future(left) { left.sum() }.force()));
	}

	public boolean run() {
		return build(10, 5).sum() == 36;
	}

	// Test should be run with number of places >= 5.
	public static void main(String[] args) {
		new Tree().execute();
	}
}

