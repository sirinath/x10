// (C) Copyright IBM Corporation 2006-2008.
// This file is part of X10 Language.

package x10.array;

public class PolyXform(E:PolyMat, T:XformMat) extends Xform {

    public def this(E:PolyMat, T:XformMat) {
        property(E, T);
    }

    public def $times(that:Xform):Xform {
        if (that instanceof PolyXform) {
            val p = that to PolyXform;
            return new PolyXform(this.E||p.E, this.T*p.T);
        } else {
            throw new UnsupportedOperationException(this.className() + ".xform(" + that.className() + ")");
        }
    }
}
