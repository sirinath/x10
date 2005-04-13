/**
 * @author kemal 4/2005
 * This is to clarify and test default initialization of 
 * non-nullable array elements, fields, variables
 */
class Node {
	int data;
	Node next;
	public Node() {
		data=19;
	}
}
class BoxedNode {
	public Node val;
}
class BoxedNodeArray {
	public Node[(0:9)->here] val;
}
 
public class InitNonNullable {


	public boolean run() {
		nullable BoxedNode bn=new BoxedNode(); 
		chk(bn!=null);
		Node tmp1=(Node)(bn.val);
		// bn.val must have default initialization
		chk(bn.val.data==19);
		// bn.val.next should not be null either
		// -- manual not clear here
		Node tmp2=(Node)(bn.val.next);
		chk(bn.val.next.data==19);
		Node tmp3=(Node)(bn.val.next.next);
		chk(bn.val.next.next.data==19);
		Node tmp4=(Node)(bn.val.next.next.next);
		chk(bn.val.next.next.next.data==19);

		nullable BoxedNodeArray bna=new BoxedNodeArray();
		chk(bna!=null);
		Node[.] tmp5= (Node[(0:9)->here])(bna.val);
		Node tmp6= (Node)(bna.val[9]);
		chk(bna.val[9].data==19);
		Node tmp7= (Node)(bna.val[9].next);
		chk(bna.val[9].next.data==19);
		Node[.] A=new Node[(0:9)->here];
		Node tmp8= (Node)(A[2]);
		chk(A[2].data==19);
		Node tmp9= (Node)(A[2].next);
		chk(A[2].next.data==19);
		return true;
	}
	static void chk(boolean b) {
		if(!b) throw new Error();
	}
	public static void main(String args[]) {
		boolean b= (new InitNonNullable()).run();
		System.out.println("++++++ "+(b?"Test succeeded.":"Test failed."));
		System.exit(b?0:1);
	}
}
