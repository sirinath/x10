/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2014.
 */

package apgas.examples;

import static apgas.Constructs.*;

import java.io.Serializable;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import apgas.Configuration;
import apgas.GlobalRuntime;
import apgas.Place;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.TransactionalMap;
import com.hazelcast.transaction.TransactionException;
import com.hazelcast.transaction.TransactionalTask;
import com.hazelcast.transaction.TransactionalTaskContext;

final class Bag implements Serializable {
  private static final long serialVersionUID = 2200935927036145803L;

  final byte[] hash;
  final int[] depth;
  final int[] lower;
  final int[] upper;
  int size;

  Bag(int n) {
    hash = new byte[n * 20 + 4]; // slack for in-place SHA1 computation
    depth = new int[n];
    lower = new int[n];
    upper = new int[n];
  }

  Bag split() {
    int s = 0;
    for (int i = 0; i < size; ++i) {
      if ((upper[i] - lower[i]) >= 2) {
        ++s;
      }
    }
    if (s == 0) {
      return null;
    }
    final Bag bag = new Bag(s);
    for (int i = 0; i < size; ++i) {
      final int p = upper[i] - lower[i];
      if (p >= 2) {
        System.arraycopy(hash, i * 20, bag.hash, bag.size * 20, 20);
        bag.depth[bag.size] = depth[i];
        bag.upper[bag.size] = upper[i];
        upper[i] -= p / 2;
        bag.lower[bag.size++] = upper[i];
      }
    }
    return bag;
  }

  void merge(Bag bag) {
    System.arraycopy(bag.hash, 0, hash, size * 20, bag.size * 20);
    System.arraycopy(bag.depth, 0, depth, size, bag.size);
    System.arraycopy(bag.lower, 0, lower, size, bag.size);
    System.arraycopy(bag.upper, 0, upper, size, bag.size);
    size += bag.size;
  }
}

final class Checkpoint implements Serializable {
  private static final long serialVersionUID = -7591718010712195252L;

  final Bag bag;
  final long count;

  Checkpoint(long c) {
    bag = null;
    count = c;
  }

  Checkpoint(Bag b, long c) {
    if (b.size == b.depth.length) {
      bag = b;
    } else {
      bag = new Bag(b.size);
      System.arraycopy(b.hash, 0, bag.hash, 0, b.size * 20);
      System.arraycopy(b.depth, 0, bag.depth, 0, b.size);
      System.arraycopy(b.lower, 0, bag.lower, 0, b.size);
      System.arraycopy(b.upper, 0, bag.upper, 0, b.size);
      bag.size = b.size;
    }
    count = c;
  }
}

final class Worker {
  static final Worker uts = new Worker();

  static MessageDigest encoder() {
    try {
      return MessageDigest.getInstance("SHA-1");
    } catch (final NoSuchAlgorithmException e) {
    }
    return null;
  }

  final HazelcastInstance hz = Hazelcast.getHazelcastInstanceByName("apgas");
  final IMap<Place, Checkpoint> map = hz.getMap("map");
  final Place home = here();

  final Random random = new Random();
  final MessageDigest md = encoder();
  final double den = Math.log(4.0 / (1.0 + 4.0)); // branching factor: 4.0
  Bag bag = new Bag(4096);
  long count;

  final ConcurrentLinkedQueue<Place> thieves = new ConcurrentLinkedQueue<Place>();
  AtomicBoolean lifeline = new AtomicBoolean(home.id != places().size() - 1);
  int state = -2; // -2: inactive, -1: running, p: stealing from p

  int digest() throws DigestException {
    if (bag.size >= bag.depth.length) {
      grow();
    }
    final int offset = bag.size * 20;
    md.digest(bag.hash, offset, 20);
    ++count;
    final int v = ((0x7f & bag.hash[offset + 16]) << 24)
        | ((0xff & bag.hash[offset + 17]) << 16)
        | ((0xff & bag.hash[offset + 18]) << 8)
        | (0xff & bag.hash[offset + 19]);
    return (int) (Math.log(1.0 - v / 2147483648.0) / den);
  }

  void init(int seed, int depth) throws DigestException {
    bag.hash[16] = (byte) (seed >> 24);
    bag.hash[17] = (byte) (seed >> 16);
    bag.hash[18] = (byte) (seed >> 8);
    bag.hash[19] = (byte) seed;
    md.update(bag.hash, 0, 20);
    final int v = digest();
    if (v > 0) {
      bag.depth[0] = depth;
      bag.upper[0] = v;
      bag.size = 1;
    }
    map.set(home, new Checkpoint(bag, count));
  }

  void expand() throws DigestException {
    final int top = bag.size - 1;
    final int d = bag.depth[top];
    final int l = bag.lower[top];
    final int u = bag.upper[top] - 1;
    if (d > 1) {
      if (u == l) {
        --bag.size;
      } else {
        bag.upper[top] = u;
      }
      final int offset = top * 20;
      bag.hash[offset + 20] = (byte) (u >> 24);
      bag.hash[offset + 21] = (byte) (u >> 16);
      bag.hash[offset + 22] = (byte) (u >> 8);
      bag.hash[offset + 23] = (byte) u;
      md.update(bag.hash, offset, 24);
      final int v = digest();
      if (v > 0) {
        bag.depth[bag.size] = d - 1;
        bag.lower[bag.size] = 0;
        bag.upper[bag.size++] = v;
      }
    } else {
      --bag.size;
      count += 1 + u - l;
    }
  }

  void grow() {
    final Bag b = new Bag(bag.depth.length * 2);
    System.arraycopy(bag.hash, 0, b.hash, 0, bag.size * 20);
    System.arraycopy(bag.depth, 0, b.depth, 0, bag.size);
    System.arraycopy(bag.lower, 0, b.lower, 0, bag.size);
    System.arraycopy(bag.upper, 0, b.upper, 0, bag.size);
    b.size = bag.size;
    bag = b;
  }

  void run() throws DigestException {
    System.err.println(here() + " starting");
    synchronized (this) {
      state = -1;
    }
    while (bag.size > 0) {
      while (bag.size > 0) {
        for (int n = 500; (n > 0) && (bag.size > 0); --n) {
          expand();
        }
        distribute();
      }
      map.set(home, new Checkpoint(count));
      steal();
    }
    synchronized (this) {
      state = -2;
    }
    lifelinesteal();
    System.err.println(here() + " stopping");
    distribute();
  }

  void lifelinesteal() {
    asyncat(place((here().id + places().size() - 1) % places().size()), () -> {
      uts.lifeline.set(true);
    });
  }

  void steal() {
    if (places().size() == 1) {
      return;
    }
    final Place from = home;
    int p = random.nextInt(places().size() - 1);
    if (p >= from.id) {
      p++;
    }
    synchronized (this) {
      state = p;
    }
    uncountedasyncat(place(p), () -> {
      uts.request(from);
    });
    synchronized (this) {
      while (state >= 0) {
        try {
          wait();
        } catch (final InterruptedException e) {
        }
      }
    }
  }

  void request(Place p) {
    synchronized (this) {
      if (state == -1) {
        thieves.add(p);
        return;
      }
    }
    uncountedasyncat(p, () -> {
      uts.deal(null);
    });
  }

  void merge(Bag b) {
    while (bag.size + b.size > bag.depth.length) {
      grow();
    }
    bag.merge(b);
  }

  void lifelinedeal(Bag b) throws DigestException {
    merge(b);
    run();
  }

  void deal(Bag b) {
    if (b != null) {
      merge(b);
    }
    synchronized (this) {
      state = -1;
      notifyAll();
    }
  }

  void transfer(Place p, Bag b) {
    try {
      hz.executeTransaction(new TransactionalTask<Object>() {
        @Override
        public Object execute(TransactionalTaskContext context)
            throws TransactionException {
          final TransactionalMap<Place, Checkpoint> map = context.getMap("map");
          map.set(home, new Checkpoint(bag, count));
          final Checkpoint c = map.getForUpdate(p);
          final long n = c == null ? 0 : c.count;
          map.set(p, new Checkpoint(b, n));
          return null;
        }
      });
    } catch (final Throwable t) {
      System.err.println("Exception in transaction!");
      t.printStackTrace();
    }
  }

  void distribute() {
    Place p;
    if (lifeline.get()) {
      final Bag b = bag.split();
      if (b != null) {
        p = place((here().id + 1) % places().size());
        lifeline.set(false);
        transfer(p, b);
        asyncat(p, () -> {
          uts.lifelinedeal(b);
        });
      }
    }
    while ((p = thieves.poll()) != null) {
      final Bag b = bag.split();
      if (b != null) {
        transfer(p, b);
      }
      uncountedasyncat(p, () -> {
        uts.deal(b);
      });
    }
  }
}

final class UTS {

  static String sub(String str, int start, int end) {
    return str.substring(start, Math.min(end, str.length()));
  }

  public static void main(String[] args) {
    if (System.getProperty(Configuration.APGAS_PLACES) == null) {
      System.setProperty(Configuration.APGAS_PLACES, "4");
    }
    System.setProperty(Configuration.APGAS_RESILIENT, "true");
    GlobalRuntime.getRuntime(); // force init

    int d = 13;
    try {
      d = Integer.parseInt(args[0]);
    } catch (final Exception e) {
    }
    final int depth = d;

    System.out.println("Starting...");
    long time = System.nanoTime();
    finish(() -> {
      Worker.uts.init(19, depth);
      Worker.uts.run();
    });
    time = System.nanoTime() - time;
    System.out.println("Finished.");

    long count = 0;
    for (final Checkpoint c : Worker.uts.map.values()) {
      count += c.count;
    }
    System.out.println("Performance: " + count + "/"
        + sub("" + time / 1e9, 0, 6) + " = "
        + sub("" + (count / (time / 1e3)), 0, 6) + "M nodes/s");
  }
}
