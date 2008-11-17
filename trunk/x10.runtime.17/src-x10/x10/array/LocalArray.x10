// (C) Copyright IBM Corporation 2006-2008.
// This file is part of X10 Language.

package x10.array;

/**
 * This class represents an array with single raw chunk in one place.
 *
 * @author bdlucas
 */

final value class LocalArray[T] extends BaseArray[T] {

    private val raw: Rail[T];
    private val layout: RectLayout;

    final protected def raw(): Rail[T] {
        return raw;
    }

    final protected def layout(): RectLayout {
        return layout;
    }


    //
    // high-performance methods here to facilitate inlining
    //
    // NB: local array, so don't do place checking
    //

    final public def apply(i0: int): T {
        if (checkBounds) checkBounds(i0);
        return raw(layout.offset(i0));
    }

    final public def apply(i0: int, i1: int): T {
        if (checkBounds) checkBounds(i0, i1);
        return raw(layout.offset(i0,i1));
    }

    final public def apply(i0: int, i1: int, i2: int): T {
        if (checkBounds) checkBounds(i0, i1, i2);
        return raw(layout.offset(i0,i1,i2));
    }

    final public def apply(i0: int, i1: int, i2: int, i3: int): T {
        if (checkBounds) checkBounds(i0, i1, i2, i3);
        return raw(layout.offset(i0,i1,i2,i3));
    }


    //
    // high-performance methods here to facilitate inlining
    //
    // NB: local array, so don't do place checking
    //

    final public def set(v: T, i0: int): T {
        if (checkBounds) checkBounds(i0);
        raw(layout.offset(i0)) = v;
        return v;
    }

    final public def set(v: T, i0: int, i1: int): T {
        if (checkBounds) checkBounds(i0, i1);
        raw(layout.offset(i0,i1)) = v;
        return v;
    }

    final public def set(v: T, i0: int, i1: int, i2: int): T {
        if (checkBounds) checkBounds(i0, i1, i2);
        raw(layout.offset(i0,i1,i2)) = v;
        return v;
    }

    final public def set(v: T, i0: int, i1: int, i2: int, i3: int): T {
        if (checkBounds) checkBounds(i0, i1, i2, i3);
        raw(layout.offset(i0,i1,i2,i3)) = v;
        return v;
    }


    //
    //
    //

    def this(dist: Dist{constant}, init: (Point)=>T): LocalArray[T]{self.dist==dist} {

        super(dist);

        layout = layout(region);
        val n = layout.size();
        raw = Rail.makeVar[T](n);
        if (init!=null) {
            for (p:Point in region)
                raw(layout.offset(p)) = init(p);
        }
    }


    /*
     * restriction view
     */

    public def restriction(d: Dist): Array[T] {
        return new LocalArray[T](this, d as Dist{constant});
    }

    def this(a: BaseArray[T], d: Dist{constant}) {

        super(d);

        this.layout = (future(d.onePlace) a.layout()) .force();
        this.raw = (future(d.onePlace) a.raw()) .force();
    }

}
