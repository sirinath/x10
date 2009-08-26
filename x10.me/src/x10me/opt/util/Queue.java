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

package x10me.opt.util;

import java.util.Iterator;

import x10me.util.LinkedListRVM;

public final class Queue<T> implements Iterable<T> {
  private final LinkedListRVM<T> elements = new LinkedListRVM<T>();

  public Queue() { }

  public Queue(T e) {
    elements.add(e);
  }

  public T insert(T e) {
    elements.add(e);            // Insert at tail
    return e;
  }

  public T remove() {
    return elements.remove(0);  // Remove from head
  }

  public boolean isEmpty() {
    return elements.isEmpty();
  }

  public Iterator<T> iterator() {
    return elements.iterator();
  }
}
