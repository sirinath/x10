/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2013.
 */
package x10.regionarray;

/**
 * ResilientDistArray which supports snapshot and restore
 * Currently limited methods necessary for ResilientHeatTransfer are implemented
 * @author kawatiya
 */
public class ResilientDistArray[T](region:Region) implements (Point(region.rank))=>T {
    public property rank():Long = region.rank;
    public var dist:Dist; // mutable (but should not be set from outside)
    private var da:DistArray[T](rank);
    private def this(dist:Dist, da:DistArray[T](dist.rank)) {
        property(dist.region);
        this.dist = dist; this.da = da;
    }
    public static def make[T](dist:Dist, init:(Point(dist.rank))=>T) = new ResilientDistArray[T](dist, DistArray.make[T](dist, init));
    public static def make[T](dist:Dist){T haszero} = new ResilientDistArray[T](dist, DistArray.make[T](dist));
    public final operator this(pt:Point(rank)) = da(pt);
    public final operator this(i0:long, i1:long){rank==2} = da(i0, i1);
    public final operator this(pt:Point(rank))=(v:T):T { da(pt) = v; return v; }
    public final operator this(i0:long, i1:long)=(v:T){rank==2}:T { da(i0, i1) = v; return v; }
    public final def map[S,U](dst:ResilientDistArray[S](rank), src:ResilientDistArray[U](rank), filter:Region(rank), op:(T,U)=>S):ResilientDistArray[S](rank) {
        da.map(dst.da, src.da, filter, op); return dst;
    }
    public final def reduce(op:(T,T)=>T, unit:T):T =  da.reduce(op, unit);
    
    /*
     * Snapshot mechanism
     */
    public def remake(dist:Dist, init:(Point(dist.rank))=>T){dist.region==region} {
        this.dist = dist;
        da = DistArray.make[T](dist, init);
    }
    public def remake(dist:Dist){dist.region==region,T haszero} {
        this.dist = dist;
        da = DistArray.make[T](dist);
    }
    
    // /*
    //  * Simple implementation of snapshot/restore (just for reference)
    //  */
    // private val ss = at (Place.FIRST_PLACE) GlobalRef[Cell[Array[T](region)]](new Cell[Array[T](region)](null));
    // public def snapshot() {
    //     at (ss) {
    //         val a = new Array[T](region, (p:Point)=>at (dist(p)) da(p));
    //         ss()() = a; // replace only when "a" is created successfully
    //     }
    // }
    // public def restore(dist:Dist){dist.region==region} {
    //     this.dist = dist;
    //     da = DistArray.make[T](dist, (p:Point)=>at (ss) ss()()(p));
    // }
    
    /*
     * Note that snapshot/restore should be executed for the master copy of DistArray
     */
    private val snapshots = new Rail[ResilientStore[Place,Rail[T]]](2, (Long)=>ResilientStore.make[Place,Rail[T]]());
    private var commit_count:Long = 0L;
    
    public def snapshot() {
        snapshot_try(); // may fail with DeadPlaceException
        snapshot_commit();
    }
    public def snapshot_try() {
        val idx = (commit_count+1) % 2;
        val snapshot = snapshots(idx);
        finish for (pl in dist.places()) {
            at (pl) async {
                val raw = da.raw();
                snapshot.save(pl, raw); // the data should be copied inside the call
            }
        }
    }
    public def snapshot_commit() {
        val idx = commit_count % 2;
        commit_count++;
        val old_snapshot = snapshots(idx);
        try { old_snapshot.deleteAll(); } catch (e:Exception) { /* ignore errors */ }
    }
    
    public def restore(newDist:Dist){newDist.region==region} {
        val idx = commit_count % 2;
        val snapshot = snapshots(idx);
        val init = (pt:Point)=>{ //TODO: This code is very slow
            val raw = snapshot.load(dist(pt));
            // val offset = dist.offset(pt); // This does not work at another place
            val offset = dist.get(dist(pt)).indexOf(pt); //TODO: This may not be general
            return raw(offset);
        };
        da = DistArray.make[T](newDist, init);
        dist = newDist;
    }
    
    /**
     * Test program, should print "0 1 2 3 4 5 6 7 8 9" without Exception
     */
    public static def main(ars:Rail[String]) {
        val livePlaces = new x10.util.ArrayList[Place]();
        for (pl in Place.places()) livePlaces.add(pl);
        if (livePlaces.size() < 2) throw new Exception("numPlaces should be >=2");
        val R = Region.make(0..9);
        val D = Dist.makeBlock(R, 0, new SparsePlaceGroup(livePlaces.toRail()));
        val A = ResilientDistArray.make[Long](D, ([x]:Point(1))=>x);
        A.snapshot();
        livePlaces.remove(here.next());
        val newD = Dist.makeBlock(R, 0, new SparsePlaceGroup(livePlaces.toRail()));
        A.restore(newD);
        for (pt:Point(1) in A.region) Console.OUT.print(at(A.dist(pt))A(pt) + " ");
        Console.OUT.println();
    }
    
    /**
     * ResilientStore interface used by snapshot/restore
     */
    static abstract class ResilientStore[K,V] {
        static val mode = getMode();
        static def getMode() {
            val env = System.getenv("X10_RESILIENT_DIST_ARRAY_MODE");
            val m = (env!=null) ? Int.parseInt(env) : 0N;
            Console.OUT.println("X10_RESILIENT_DIST_ARRAY_MODE=" + m);
            return m;
        }
        public static def make[K,V]():ResilientStore[K,V] {
            switch (mode) {
            case 0N: return new ResilientStorePlace0[K,V]();
            case 1N: return new ResilientStoreDistributed[K,V]();
            default: throw new Exception("unknown mode");
            }
        }
        public abstract def save(key:K, value:V):void;
        public abstract def load(key:K):V;
        public abstract def delete(key:K):void;
        public abstract def deleteAll():void;
    }
    /**
     * Place0 implementation of ResilientStore
     */
    static class ResilientStorePlace0[K,V] extends ResilientStore[K,V] {
        val hm = at (Place.FIRST_PLACE) GlobalRef(new x10.util.HashMap[K,V]());
        public def save(key:K, value:V) {
            at (hm) hm().put(key,value); // value is deep-copied by "at"
        }
        public def load(key:K) {
            return at (hm) hm().getOrThrow(key); // value is deep-copied by "at"
        }
        public def delete(key:K) {
            at (hm) hm().remove(key);
        }
        public def deleteAll() {
            at (hm) hm().clear();
        }
    }
    /**
     * Distributed (local+backup) implementation of ResilientStore
     */
    static class ResilientStoreDistributed[K,V] extends ResilientStore[K,V] {
        val hm = PlaceLocalHandle.make[x10.util.HashMap[K,V]](PlaceGroup.WORLD, ()=>new x10.util.HashMap[K,V]());
        private def DEBUG(key:K, msg:String) { Console.OUT.println(here + ": key=" + key + ": " + msg); }
        public def save(key:K, value:V) {
            /* Store the copy of value locally */
            at (here) hm().put(key, value); // value is deep-copied by "at"
            DEBUG(key, "backuped locally");
            /* Backup the value in another place */
            var backupPlace:Long = key.hashCode() % Place.MAX_PLACES;
            var trial:Long;
            for (trial = 0L; trial < Place.MAX_PLACES; trial++) {
                if (backupPlace != here.id && !Place.isDead(backupPlace)) break; // found appropriate place
                backupPlace = (backupPlace+1) % Place.MAX_PLACES;
            }
            if (trial == Place.MAX_PLACES) {
                /* no backup place available */
                DEBUG(key, "no backup place available");
            } else {
                at (Place(backupPlace)) hm().put(key, value);
                DEBUG(key, "backuped at place " + backupPlace);
            }
        }
        public def load(key:K) {
            /* First, try to load locally */
            try {
                val value = at (here) hm().getOrThrow(key); // value is deep-copied by "at"
                DEBUG(key, "restored locally");
                return value;
            } catch (e:x10.util.NoSuchElementException) { /* falls through */ }
            /* Try to load from another place */
            var backupPlace:Long = key.hashCode() % Place.MAX_PLACES;
            var trial:Long;
            for (trial = 0L; trial < Place.MAX_PLACES; trial++) {
                if (backupPlace != here.id && !Place.isDead(backupPlace)) {
                    DEBUG(key, "checking backup place " + backupPlace);
                    try {
                        val value = at (Place(backupPlace)) hm().getOrThrow(key);
                        DEBUG(key, "restored from backup place " + backupPlace);
                        return value;
                    } catch (e:x10.util.NoSuchElementException) { /* falls through */ }
                }
                backupPlace = (backupPlace+1) % Place.MAX_PLACES;
            }
            DEBUG(key, "no backup found, ERROR");
            throw new Exception("No data for key " + key);
        }
        public def delete(key:K) {
            finish for (pl in Place.places()) {
                if (pl.isDead()) continue;
                at (pl) async hm().remove(key);
            }
        }
        public def deleteAll() {
            finish for (pl in Place.places()) {
                if (pl.isDead()) continue;
                at (pl) async hm().clear();
            }
        }
    }
}
static public type ResilientDistArray[T](r:Long) = ResilientDistArray[T]{self.rank==r};
