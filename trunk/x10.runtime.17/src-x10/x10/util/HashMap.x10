/*
 *
 * (C) Copyright IBM Corporation 2006-2008.
 *
 *  This file is part of X10 Language.
 *
 */

package x10.util;

public class HashMap[-K,V] implements Map[K,V] {

    static class HashEntry[-Key,Value] implements Map.Entry[Key,Value] {
        public def getKey() = key;
        public def getValue() = value;
        public def setValue(v: Value) { value = v; }
        
        val key: Key;
        var value: Value;
        var removed: Boolean; 
        val hash: Int;
        
        def this(key: Key, value: Value, h: Int) {
            this.key = key;
            this.value = value;
            this.hash = h;
            this.removed = false;
        }
    }
    
    /** The actual table, must be of size 2**n */
    var table: Rail[HashEntry[K,V]];
    
    /** Number of (non-null) entries in the table. */
    var size: Int;
    
    /** table.length - 1 */
    var mask: Int;
    
    var shouldRehash: Boolean;

    const MAX_PROBES = 3;
    const MIN_SIZE = 4;
    
    public def this() {
        init(MIN_SIZE);
    }
    
    public def this(var sz: int) {
        var pow2: int = MIN_SIZE;
        while (pow2 < sz)
            pow2 <<= 1;
        init(pow2);
    }
    
    protected def init(sz: int): void {
        // check that sz is a power of 2
        assert (sz & -sz) == sz;
        assert sz >= MIN_SIZE;
    
        table = Rail.makeVar[HashEntry[K,V]](sz);
        mask = table.length - 1;
        size = 0;
        shouldRehash = false;
    }
    
    public def clear(): void {
        init(MIN_SIZE);
    }
    
    protected def hash(k: K): Int {
        return k == null ? 0 : (k.hashCode() * 17);
    }
    
    public safe def apply(k: K): Box[V] = get(k);
    
    public safe def get(k: K): Box[V] {
        val e = getEntry(k);
        if (e == null) return null;
        return e.value as Box[V];
    }    
    
    protected def getEntry(k: K): HashEntry[K,V] {
        if (size == 0)
            return null;
            
        if (shouldRehash)
            rehash();
            
        val h = hash(k);

        var i: int = h;

        while (true) {        
            val j = i & mask;
            i++;
            
            val e = table(j);
            if (e == null) {
                if (i - h > MAX_PROBES)
                    shouldRehash = true;
                return null;
            }
            if (e != null) {
                if (e.hash == h && (k == null ? e.key == null : k.equals(e.key))) {
                    if (i - h > MAX_PROBES)
                        shouldRehash = true;
                    return e;
                }
            }
        }
    }
    
    public safe def put(k: K, v: V): Box[V] {
        if (size == table.length || shouldRehash)
            rehash();
        
        val h = hash(k);
        var i: int = h;

        while (true) {
            val j = i & mask;
            i++;
            
            val e = table(j);
            if (e == null) {
                if (i - h > MAX_PROBES)
                    shouldRehash = true;
                table(j) = new HashEntry[K,V](k, v, h);
                size++;
                return null;
            }
            else if (e.hash == h && (k == null ? e.key == null : k.equals(e.key))) {
                if (i - h > MAX_PROBES)
                    shouldRehash = true;
                val old = e.value;
                e.value = v;
                if (e.removed)
                    return null;
                return (old as V) as Box[V];
            }
        }
    }
    
    public def rehash(): void {
        val t = table;
        val oldSize = size;
        table = Rail.makeVar[HashEntry[K,V]](t.length*2);
        mask = table.length - 1;
        size = 0;
        shouldRehash = false;

        for (var i: int = 0; i < t.length; i++) {
            if (t(i) != null && ! t(i).removed) {
                put(t(i).key, t(i).value);
            }
        }
        
        assert size == oldSize;
    }
    
	public def containsKey(k: K): boolean {
	    val e = getEntry(k);
	    return e != null && ! e.removed;
	}
	
	public def remove(k: K): Box[V] {
	    val e = getEntry(k);
	    if (e != null && ! e.removed) {
	        size--;
	        e.removed = true;
	        return e.value as Box[V];
	    }
	    return null;
    }
    
	public def keySet(): Set[K] {
	    return new KeySet[K,V](this);
	}
	
	public incomplete def entries(): Set[Map.Entry[K,V]];
	
    protected def entriesIterator(): Iterator[HashEntry[K,V]] {
	    return new EntriesIterator[K,V](this);
	}
	
	protected static class EntriesIterator[-Key,Value] implements Iterator[HashEntry[Key,Value]] {
	    val map: HashMap[Key,Value];
	    var i: Int;
	    
	    def this(map: HashMap[Key,Value]) { this.map = map; this.i = 0; advance(); }

        def advance(): void {
            while (i < map.table.length) {
               if (map.table(i) != null && ! map.table(i).removed)
                   return;
               i++;
            }
        }
	    
	    public def hasNext(): Boolean {
	        if (i < map.table.length) {
                assert map.table(i) != null && ! map.table(i).removed : "map entry " + i + " is not null or removed";
	            return true;
	        }
	        return false;
	    }
	    
	    public def next(): HashEntry[Key,Value] {
	        val j = i;
	        assert map.table(j) != null && ! map.table(j).removed : "map entry " + j + " is not null or removed";
	        i++;
	        advance();
	        return map.table(j);
	    }
	}
	
	public def size() = size;
	
    protected static class KeySet[-Key,Value] extends AbstractCollection[Key] implements Set[Key] {
	    val map: HashMap[Key,Value];
	    
	    def this(map: HashMap[Key,Value]) { this.map = map; }
	    
	    public def iterator(): Iterator[Key] {
	        return new MapIterator[HashEntry[Key,Value],Key](map.entriesIterator(), (e: HashEntry[Key,Value]) => e.key);
	    }
	    
	    public def contains(k: Key) {
	        return map.containsKey(k);
	    }
	    
	    public def add(k: Key): Boolean { throw new UnsupportedOperationException(); }
	    public def remove(k: Key): Boolean { throw new UnsupportedOperationException(); }
	    public def clone(): KeySet[Key,Value] { throw new UnsupportedOperationException(); }
	    public def size(): Int = map.size();
	}
}
