/**
 * 
 */
package x10.types.checker;

import polyglot.ast.Field;
import polyglot.ast.NamedVariable;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.frontend.Job;
import polyglot.types.SemanticException;
import polyglot.types.TypeSystem;
import polyglot.visit.ContextVisitor;
import x10.errors.Errors;
import x10.types.X10TypeEnv_c;

/**
 * @author vj
 *
 */
public class VarChecker extends ContextVisitor {
		public VarChecker(Job job, TypeSystem ts, polyglot.ast.NodeFactory nf) {
			super(job, ts, nf);
		}
		public SemanticException error = null;
		@Override
		public Node override(Node n) {
			if (n instanceof NamedVariable) {
				NamedVariable e = (NamedVariable) n;
				if (! e.flags().isFinal())
				    error = new Errors.VarMustBeFinalInTypeDef(e.name().toString(), e.position()); 
				
				if (n instanceof Field) {
					Field l = (Field) n;
					if (! new X10TypeEnv_c(context).isAccessible(l.fieldInstance())) {
						 error = new Errors.VarMustBeAccessibleInTypeDef(l.fieldInstance(), e.position()); 
					}
				}
				return n;
			}

			return null;
		}
	}