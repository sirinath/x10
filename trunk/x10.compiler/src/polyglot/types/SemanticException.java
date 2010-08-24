/*
 * This file is part of the Polyglot extensible compiler framework.
 *
 * Copyright (c) 2000-2006 Polyglot project group, Cornell University
 * 
 */

package polyglot.types;

import polyglot.main.Report;
import polyglot.util.Position;

/**
 * Thrown during any number of phases of the compiler during which a semantic
 * error may be detected.
 */
public class SemanticException extends Exception {
    protected Position position;
    
    public SemanticException() {
        super();
    }

    public SemanticException(Throwable cause) {
        super(cause);
    }

    public SemanticException(Position position) {
	super();
	this.position = position;
    }

    public SemanticException(String m) {
        super(m);
    }

    public SemanticException(String m, Throwable cause) {
        super(m, cause);
    }

    public SemanticException(String m, Position position) {
	super(m);
	this.position = position;
    }

    public Position position() {
	return position;
    }
    
    private static boolean init = false;
    public static boolean fillInStackTrace = true;
    
    public synchronized Throwable fillInStackTrace() {
        if (! fillInStackTrace) {
            // fast path: init==true, fillInStackTrace==false
            return this;
        }
        if (! init) {
            fillInStackTrace = Report.should_report("trace", 1);
            init = true;
            if (! fillInStackTrace) {
                return this;
            }
        }
        return super.fillInStackTrace();
    }
}
