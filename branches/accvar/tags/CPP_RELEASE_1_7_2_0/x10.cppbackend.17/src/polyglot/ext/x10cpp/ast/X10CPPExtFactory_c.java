/*
 * Created on Feb 26, 2008
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package polyglot.ext.x10cpp.ast;

import polyglot.ast.Ext;
import polyglot.ext.x10.ast.X10ExtFactory_c;
import polyglot.ext.x10cpp.extension.X10ClassBodyExt_c;

public class X10CPPExtFactory_c extends X10ExtFactory_c {
	protected Ext extClassBodyImpl() {
		return new X10ClassBodyExt_c();
	}
}
