/*
 * This file is part of the Polyglot extensible compiler framework.
 *
 * Copyright (c) 2000-2006 Polyglot project group, Cornell University
 * 
 */

package polyglot.ast;

import x10.ast.X10Cast;
import x10.types.checker.Converter;

/**
 * A <code>Cast</code> is an immutable representation of a casting
 * operation.  It consists of an <code>Expr</code> being cast and a
 * <code>TypeNode</code> being cast to.
 */ 
public interface Cast extends Expr
{
    /**
     * The type to cast to.
     */
    TypeNode castType();

    /**
     * Set the type to cast to.
     */
    Cast castType(TypeNode castType);

    /**
     * The expression to cast.
     */
    Expr expr();

    /**
     * Set the expression to cast.
     */
    Cast expr(Expr expr);
    
    public Converter.ConversionType conversionType();
    public X10Cast conversionType(Converter.ConversionType convert);
    public X10Cast exprAndConversionType(Expr c, Converter.ConversionType checked);
}
