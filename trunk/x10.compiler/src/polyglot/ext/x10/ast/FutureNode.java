/*
 * Created on Nov 26, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package polyglot.ext.x10.ast;

import polyglot.ast.TypeNode;

/** The node constructed for the X10 type constructor future X.
 * @author vj
 *
 */
public interface FutureNode extends X10TypeNode, ContainsType {
	TypeNode base();

}
