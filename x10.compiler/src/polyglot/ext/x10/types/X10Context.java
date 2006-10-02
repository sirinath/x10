package polyglot.ext.x10.types;

import polyglot.types.ClassType;
import polyglot.types.Context;
import polyglot.types.ParsedClassType;
import polyglot.types.SemanticException;

public interface X10Context extends Context {
	
	// Use addVariable to add a PropertyInstance to the context.
	
	/**
	 * Looks up a property in the current scope.
	 * @param name
	 * @return
	 * @throws SemanticException
	 */
	PropertyInstance findProperty(String name) throws SemanticException;
	
	/**
     * Finds the type which added a property to the scope.
     * This is usually a subclass of <code>findProperty(name).container()</code>.
     */
    ClassType findPropertyScope(String name) throws SemanticException;
    
    /** Enter the scope of a deptype. */
    X10Context pushDepType(X10NamedType t);
    
    /** Return the current deptype, null if there is none. */
    X10NamedType currentDepType();
    boolean isDepType();

    /** Return whether innermost scope is a deptype scope. */
    boolean inDepType();
    
    /**
     * Enter the scope of an atomic block. The body of such a block must be local,
     * sequential and nonblocking.
     * @return a new context
     */
    X10Context pushAtomicBlock(); 
    
    void setSafeCode();
    void setNonBlockingCode();
    void setSequentialCode();
    void setLocalCode();
    boolean inSafeCode();
    boolean inLocalCode();
    boolean inSequentialCode();
    boolean inNonBlockingCode();
}
