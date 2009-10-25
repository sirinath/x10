/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file was derived from code developed by the
 *  Jikes RVM project (http://jikesrvm.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  See the COPYRIGHT.txt file distributed with this work for information
 *  regarding copyright ownership.
 */

package x10me.util;

import x10me.util.ImmutableEntryHashMapRVM.Bucket;

/**
 * A hash map with entirely immutable buckets. It doesn't correctly support
 * remove, and its values cannot be mutated by a put with the same key.
 */
public final class ImmutableEntryIdentityHashMapRVM<K, V> extends AbstractHashMapRVM<K,V> {

  @Override
  AbstractBucket<K,V> createNewBucket(K key, V value, AbstractBucket<K, V> next) {
    return new Bucket<K,V>(key, value, next);
  }

  public ImmutableEntryIdentityHashMapRVM() {
    super(DEFAULT_SIZE);
  }

  public ImmutableEntryIdentityHashMapRVM(int size) {
    super(size);
  }

  @Override
  protected boolean same(K k1, K k2) {
    return k1 == k2;
  }

  @Override
  protected int hashTheKey(K key) {
    return System.identityHashCode(key);
  }
}
