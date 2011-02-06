/*
 * This file is part of the Polyglot extensible compiler framework.
 *
 * Copyright (c) 2000-2006 Polyglot project group, Cornell University
 * 
 */

package polyglot.types;

import java.util.*;

import polyglot.main.Reporter;
import polyglot.util.*;
import x10.util.CollectionFactory;

/**
 * A <code>CachingResolver</code> memoizes another Resolver
 */
public class CachingResolver implements TopLevelResolver {
    protected TopLevelResolver inner;
    private Map<QName,Object> cache;
    private boolean cacheNotFound;
    protected Reporter reporter;

    /**
     * Create a caching resolver.
     * @param inner The resolver whose results this resolver caches.
     */

    public CachingResolver(TopLevelResolver inner, Reporter reporter) {
        this.inner = inner;
        this.cacheNotFound = true;
        this.cache = new LinkedHashMap<QName, Object>();
        this.reporter = reporter;
    }

    protected boolean shouldReport(int level) {
        return (reporter.should_report(Reporter.sysresolver, level) && this instanceof SystemResolver) ||
               reporter.should_report(TOPICS, level);
    }
    
    public boolean packageExists(QName name) {
        return inner.packageExists(name);
    }

    /*
    public Object copy() {
        try {
            CachingResolver r = (CachingResolver) super.clone();
	        // todo: the inner resolver is not deep cloned. so I removed this copy method. If it is needed, then Resolver should extend Copy and we should implement copy for all Resolvers.
            r.cache = CollectionFactory.newHashMap(this.cache);
            return r;
        }
        catch (CloneNotSupportedException e) {
            throw new InternalCompilerError("clone failed");
        }
    }
    */
    
    /**
     * The resolver whose results this resolver caches.
     */
    public TopLevelResolver inner() {
        return this.inner;
    }

    public String toString() {
        return "(cache " + inner.toString() + ")";
    }

    protected Collection<Named> cachedObjects() {
	ArrayList<Named> l = new ArrayList<Named>();
	for (Object o : cache.values()) {
	    if (o instanceof Named)
		l.add((Named) o);
	}
	return l;
    }
    
    /**
     * Find a type object by name.
     * @param name The name to search for.
     */
    public Named find(QName name) throws SemanticException {
        if (shouldReport(2))
            reporter.report(2, "CachingResolver: find: " + name);

        Object o = cache.get(name);

        if (o instanceof SemanticException) throw ((SemanticException) o);

        Named q = (Named) o;

        if (q == null) {
            if (shouldReport(3))
                reporter.report(3, "CachingResolver: not cached: " + name);

            try {
                q = inner.find(name);
            }
            catch (NoClassException e) {
                if (shouldReport(3)) {
                    reporter.report(3, "CachingResolver: " + e.getMessage());
                    reporter.report(3, "CachingResolver: installing " + name + "-> (not found) in resolver cache");
                }
                if (cacheNotFound) {
                    cache.put(name, e);
                }
                throw e;
            }

            addNamed(name, q);
            addNamed(QName.make(q.fullName()), q);

            if (shouldReport(3))
                reporter.report(3, "CachingResolver: loaded: " + name);
	}
        else {
            if (shouldReport(3))
                reporter.report(3, "CachingResolver: cached: " + name);
        }

	return q;
    }

    /**
     * Check if a type object is in the cache, returning null if not.
     * @param name The name to search for.
     */
    public Named check(QName name) {
        Object o = cache.get(name);
        if (o instanceof Throwable)
            return null;
        return (Named) o;
    }

    /**
     * Install a qualifier in the cache.
     * @param name The name of the qualifier to insert.
     * @param q The qualifier to insert.
     */
    public void install(QName name, Named q) {
        if (shouldReport(3))
            reporter.report(3, "CachingResolver: installing " + name + "->" + q + " in resolver cache");
        if (shouldReport(5))
            new Exception().printStackTrace();

        Object old = cache.get(name);
	if (old != null && old != q && old instanceof Type)
	    assert false : name + "->" + old + " " + old.getClass().getName() + " is already in the cache; cannot replace with " + q + " " + q.getClass().getName();
	
        cache.put(name, q);
    }

    /**
     * Install a qualifier in the cache.
     * @param name The name of the qualifier to insert.
     * @param q The qualifier to insert.
     */
    public void addNamed(QName name, Named q) throws SemanticException {
	install(name, q);
    }

    public void dump() {
        reporter.report(1, "Dumping " + this);
        for (Map.Entry<QName, Object> e : cache.entrySet()) {
            reporter.report(2, e.toString());
        }
    }

    private static final Collection<String> TOPICS =
                    CollectionUtil.list(Reporter.types,
                                        Reporter.resolver);
}
