/*
 * This file is part of the Polyglot extensible compiler framework.
 *
 * Copyright (c) 2000-2006 Polyglot project group, Cornell University
 * 
 */

package x10.ast;

import x10.types.Qualifier;
import x10.types.Ref;

/**
 * A <code>QualifierNode</code> represents any node that can be used as a type
 * qualifier (<code>polyglot.types.Qualifier</code>).  It can resolve to either
 * an enclosing type or can be a package.
 */
public interface QualifierNode extends Prefix
{
    /** The qualifier type object. */
    Ref<? extends Qualifier> qualifierRef();
}
