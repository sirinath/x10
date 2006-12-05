package polyglot.ext.x10.types;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import polyglot.main.Report;
import polyglot.types.Flags;

/**
 * Representation of X10 flags. 
 * @author pcharles
 * @author vj
 *
 */
public class X10Flags extends Flags {
        public static final Set X10_FLAGS = new TreeSet();
        public static final Flags VALUE        = createFlag("value", null);
        public static final Flags REFERENCE    = createFlag("reference", null);
        public static final Flags ATOMIC       = createFlag("atomic", null);
        public static final Flags PURE         = createFlag("pure", null);
        public static final Flags MUTABLE      = createFlag("mutable", null);
        public static final Flags SAFE         = createFlag("safe", null);
        public static final Flags LOCAL        = createFlag("local", null);
        public static final Flags NON_BLOCKING = createFlag("nonblocking", null);
        public static final Flags SEQUENTIAL   = createFlag("sequential", null);
       
        /**
         * Return a new Flags object with a new name.  Should be called only once
         * per name.
         *
         * @param name the name of the new flag
         * @param after the flags after which this flag should be printed;
         *        Flags.NONE to print before all other flags, null
         *        if we should print at the end.
         */
        public static Flags createFlag(String name, Flags after) {
           addToOrder(name, after);
           X10_FLAGS.add(name);
            return new X10Flags(name);
        }
        /** 
         * Launder Flags as X10Flags. Called so that when translate is called,
         * X10Flags.translate is invoked. This hack is necessary because Polyglot
         * has polyglot.types.Flag baked in all over the place.
         * @param f
         * @return
         */
        public static X10Flags toX10Flags(Flags f) {
        	if (f instanceof X10Flags) return (X10Flags) f;
        	X10Flags result = new X10Flags();
        	return  result.setX(f);
        	
        }
        public static boolean isX10Flag(Flags f) {
        	return f instanceof X10Flags;
        }
        private X10Flags() {
        	super();
        }
        protected X10Flags(String name) {
            super(name);
        }
        /**
         * Create new flags with the flags in <code>other</code> also set.
         */
        public X10Flags setX(Flags other) {
            X10Flags f = new X10Flags();
            f.flags.addAll(this.flags);
            f.flags.addAll(other.flags());
            return f;
        }

        /**
         * Create new flags with the flags in <code>other</code> cleared.
         */
        public X10Flags clearX(Flags other) {
            X10Flags f = new X10Flags();
            f.flags.addAll(this.flags);
            f.flags.removeAll(other.flags());
            return f;
        }

        /**
         * Create new flags with only flags in <code>other</code> set.
         */
        public X10Flags retainX(Flags other) {
            X10Flags f = new X10Flags();
            f.flags.addAll(this.flags);
            f.flags.retainAll(other.flags());
            return f;
        }

        /**
             * Return a copy of this <code>this</code> with the <code>value</code>
             * flag set.
         * @param flags TODO
             */
            public X10Flags Value() {
        	return setX(VALUE);
            }
        /**
             * Return a copy of this <code>this</code> with the <code>value</code>
             * flag clear.
         * @param flags TODO
             */
            public  X10Flags clearValue() {
        	return clearX(VALUE);
            }
        /**
             * Return true if <code>this</code> has the <code>value</code> flag set.
         * @param flags TODO
             */
            public  boolean isValue() {
        	return contains(VALUE);
            }
            public  static boolean isValue(Flags flags) {
            	return flags.contains(VALUE);
                }
        /**
             * Return a copy of this <code>this</code> with the <code>reference</code>
             * flag set.
         * @param flags TODO
             */
            public  X10Flags Reference() {
        	return setX(REFERENCE);
            }
        /**
             * Return a copy of this <code>this</code> with the <code>reference</code>
             * flag clear.
         * @param flags TODO
             */
            public  X10Flags clearReference() {
        	return clearX(REFERENCE);
            }
        /**
             * Return true if <code>this</code> has the <code>reference</code> flag set.
         * @param flags TODO
             */
            public  boolean isReference() {
        	return contains(REFERENCE);
            }
        /**
             * Return a copy of this <code>this</code> with the <code>atomic</code>
             * flag set.
         * @param flags TODO
             */
            public  X10Flags Atomic() {
        	return setX(ATOMIC);
            }
        /**
             * Return a copy of this <code>this</code> with the <code>atomic</code>
             * flag clear.
         * @param flags TODO
             */
            public  X10Flags clearAtomic() {
        	return clearX(ATOMIC);
            }
        /**
             * Return true if <code>this</code> has the <code>atomic</code> flag set.
         * @param flags TODO
             */
            public  boolean isAtomic() {
        	return contains(ATOMIC);
            }
        /**
             * Return a copy of this <code>this</code> with the <code>pure</code>
             * flag set.
         * @param flags TODO
             */
            public  X10Flags Pure() {
        	return setX(PURE);
            }
        /**
             * Return a copy of this <code>this</code> with the <code>pure</code>
             * flag clear.
         * @param flags TODO
             */
            public  X10Flags clearPure() {
        	return clearX(PURE);
            }
        /**
             * Return true if <code>this</code> has the <code>pure</code> flag set.
         * @param flags TODO
             */
            public  boolean isPure() {
        	return contains(PURE);
            }
            
            /**
             * Return a copy of this <code>this</code> with the <code>pure</code>
             * flag set.
         * @param flags TODO
             */
            public  X10Flags Safe() {
        	return setX(SAFE);
            }
        /**
             * Return a copy of this <code>this</code> with the <code>pure</code>
             * flag clear.
         * @param flags TODO
             */
            public  X10Flags clearSafe() {
        	return clearX(SAFE);
            }
        /**
             * Return true if <code>this</code> has the <code>pure</code> flag set.
         * @param flags TODO
             */
            public boolean isSafe() {
        	return contains(SAFE);
            }
            /**
             * Return a copy of this <code>this</code> with the <code>pure</code>
             * flag set.
         * @param flags TODO
             */
            public  X10Flags Local() {
        	return setX(LOCAL);
            }
        /**
             * Return a copy of this <code>this</code> with the <code>pure</code>
             * flag clear.
         * @param flags TODO
             */
            public  X10Flags clearLocal() {
        	return clearX(LOCAL);
            }
        /**
             * Return true if <code>this</code> has the <code>pure</code> flag set.
         * @param flags TODO
             */
            public boolean isLocal() {
        	return contains(LOCAL) || contains(SAFE);
            }
            /**
             * Return a copy of this <code>this</code> with the <code>pure</code>
             * flag set.
         * @param flags TODO
             */
            public  X10Flags NonBlocking() {
        	return setX(NON_BLOCKING);
            }
        /**
             * Return a copy of this <code>this</code> with the <code>pure</code>
             * flag clear.
         * @param flags TODO
             */
            public  X10Flags clearNonBlocking() {
        	return clearX(NON_BLOCKING);
            }
        /**
             * Return true if <code>this</code> has the <code>pure</code> flag set.
         * @param flags TODO
             */
            public boolean isNonBlocking() {
        	return contains(NON_BLOCKING) || contains(SAFE);
            }
            /**
             * Return a copy of this <code>this</code> with the <code>pure</code>
             * flag set.
         * @param flags TODO
             */
            public  X10Flags Sequential() {
        	return setX(SEQUENTIAL);
            }
        /**
             * Return a copy of this <code>this</code> with the <code>pure</code>
             * flag clear.
         * @param flags TODO
             */
            public  X10Flags clearSequential() {
        	return clearX(SEQUENTIAL);
            }
        /**
             * Return true if <code>this</code> has the <code>pure</code> flag set.
         * @param flags TODO
             */
            public  boolean isSequential() {
        	return contains(SEQUENTIAL) || contains(SAFE);
            }
            /**
             * Return "" if no flags set, or toString() + " " if some flags are set.
             */
            public String translate() {
                StringBuffer sb = new StringBuffer();

                for (Iterator i = this.flags.iterator(); i.hasNext(); ) {
                	String s = (String) i.next();
                	if (X10_FLAGS.contains(s)) continue;

                    sb.append(s);
                    sb.append(" ");
                }

                return sb.toString();
            }
            public boolean hasAllAnnotationsOf(X10Flags f) {
            	boolean result = 
            		((! f.isSequential()) || isSequential() || isSafe())
            		&& ((! f.isLocal()) || isLocal() || isSafe())
            		&& ((! f.isNonBlocking()) || isNonBlocking() || isSafe())
            		&& ((! f.isSafe()) || isSafe());
            	//Report.report(1, "X10Flags: " + this + ".hasAllAnnotationsOf(" + f + ")? " + result);
            	return result;
            	
            }
            public String toString() {
            	  StringBuffer sb = new StringBuffer();

                  for (Iterator i = this.flags.iterator(); i.hasNext(); ) {
                  	String s = (String) i.next();
                 

                      sb.append(s);
                      sb.append(" ");
                  }

                  return sb.toString();
                
            }


}
