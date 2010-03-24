/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

package x10.lang;

/**
 * A mutable cell containing a value.
 * Can be used to put a struct on the heap, or to capture a mutable location in a closure.
 *
 * @param T the type of the stored value
 */
public final class Cell[T#] {

    /**
     * The value stored in this cell.
     */
    public var value:T#;

    /**
     * Construct a cell with the given value.
     *
     * @param x the given value
     */
    public def this(x:T#) { value = x; }

    /**
     * Return a string representation of the Cell.
     * When invoked by an activity at the same place as the Cell object, toString() contains
     * the string representation of the current value stored in the Cell.  When invoked by an
     * activity at a different place, an UnsupportedOperationException is raised.
     *
     * @return the String object being constructed by the StringBuilder
     */
    public global safe def toString() {
        if (!at(here)) throw new UnsupportedOperationException();
        return "Cell(" + (this as Cell[T#]!).value.toString() + ")";
    }


    /**
     * Return the value stored in the Cell.
     * Will work even if the Cell reference is remote.
     *
     * @return the current value stored in the Cell.
     */
    public global def apply() = at (this) value;

    /**
     * Set the value stored in the Cell to the new value.
     * Will work even if the Cell reference is remote.
     *
     * @param x the new value
     */
    public global def apply(x:T#) { at (this) value = x; }

    /**
     * Set the value stored in the Cell to the new value.
     * Will work even if the Cell reference is remote.
     *
     * @param x the new value
     * @return the new value stored in the Cell.
     */
    public global def set(x:T#) { at (this) value = x; return x; }


    /**
     * Create a new Cell with the given value stored in it.
     *
     * @param T the value type of the Cell
     * @param x the given value
     * @return a new Cell with the given value stored in it.
     */
    public static def make[S#](x:S#)= new Cell[S#](x);


    /**
     * Return the value stored in the given Cell.
     * Will work even if the Cell reference is remote.
     *
     * @param T the value type of the Cell
     * @param x the given Cell
     * @return the value stored in the given Cell.
     */
    public static operator[S#](x:Cell[S#]) = x();

    /**
     * Create a new Cell with the given value stored in it.
     *
     * @param T the value type of the Cell
     * @param x the given value
     * @return a new Cell with the given value stored in it.
     */
    public static operator[S#](x:S#) = make[S#](x);
}

// vim:tabstop=4:shiftwidth=4:expandtab
