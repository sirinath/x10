/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

package x10.ast;

import polyglot.types.SemanticException;
import polyglot.util.Position;
import polyglot.visit.ContextVisitor;
import x10.errors.Errors;
import x10.types.X10LocalInstance;
import x10.types.X10TypeSystem_c;
import x10.visit.X10TypeChecker;

public class X10AmbReceiver_c extends AmbReceiver_c {

    public X10AmbReceiver_c(Position pos, Prefix prefix, Id name) {
        super(pos, prefix, name);
    }

    @Override
    public Node disambiguate(ContextVisitor ar) {
        try {
            return super.disambiguate(ar);
        } catch (SemanticException e) {
            Errors.issue(ar.job(), e, this);
            X10TypeSystem_c xts = (X10TypeSystem_c) ar.typeSystem();
            X10LocalInstance li = xts.createFakeLocal(name.id(), e);
            return ar.nodeFactory().Local(position(), name).localInstance(li).type(li.type());
        }
    }
}
