/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 * This file was originally derived from the Polyglot extensible compiler framework.
 *
 *  (C) Copyright 2000-2007 Polyglot project group, Cornell University
 *  (C) Copyright IBM Corporation 2007-2015.
 */

package polyglot.frontend;

import polyglot.ast.Node;
import polyglot.main.Reporter;
import polyglot.util.InternalCompilerError;
import polyglot.visit.NodeVisitor;

/**
 * This goal does not fail when errors were produced.
 * @author igor
 */
public class ForgivingVisitorGoal extends VisitorGoal {
    private static final long serialVersionUID = -4239390029748390935L;

    public ForgivingVisitorGoal(String name, Job job, NodeVisitor v) {
        super(name, job, v);
    }
    public boolean runTask() {
        NodeVisitor v = visitor();
        Node ast = job().ast();
        if (ast == null) {
            throw new InternalCompilerError("Null AST for job " + job() + ": did the parser run?");
        }
        try {
            NodeVisitor v_ = v.begin();
            if (v_ != null) {
                Reporter reporter = scheduler.extensionInfo().getOptions().reporter;
                if (reporter.should_report(Reporter.frontend, 3))
                    reporter.report(3, "Running " + v_ + " on " + ast);
                ast = ast.visit(v_);
                v_.finish(ast);
                return true;
            }
            return false;
        }
        catch (RuntimeException e) {
            fail();
            throw e;
        }
        catch (Error e) {
            fail();
            throw e;
        }
        finally {
            job().ast(ast);
        }
    }
}