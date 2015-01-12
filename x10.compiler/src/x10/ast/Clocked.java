/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2015.
 */

package x10.ast;

import java.util.List;

import polyglot.ast.CompoundStmt;
import polyglot.ast.Expr;

/**
 * The node constructed for [ateach,foreach,async] clocked (C) [stmt].
 * @author Christian Grothoff
 */
public interface Clocked extends CompoundStmt {

    /** Get the clock. */
    List<Expr> clocks();

    Clocked clocks(List<Expr> clocks);
}
