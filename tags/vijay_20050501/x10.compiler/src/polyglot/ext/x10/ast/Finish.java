/*
 * Created by vj on Dec 20, 2004
 *
 * 
 */
package polyglot.ext.x10.ast;

import polyglot.ast.Block;
import polyglot.ast.Stmt;

/**
 * @author vj Dec 20, 2004
 * 
 */
public interface Finish extends Stmt {
	Stmt body();

}
