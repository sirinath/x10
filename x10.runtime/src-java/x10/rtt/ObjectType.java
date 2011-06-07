/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2011.
 */

package x10.rtt;


public class ObjectType extends RuntimeType<x10.core.RefI> {

    private static final long serialVersionUID = 1L;

    public ObjectType() {
        super(x10.core.RefI.class,
            new Type[] { Types.ANY }
        );
    }
    
    @Override
    public String typeName() {
        return "x10.lang.Object";
    }
    
    // for shortcut
    @Override
    public boolean isSubtype(x10.rtt.Type<?> o) {
        return o == Types.OBJECT || o == Types.ANY;
    };
    
}
