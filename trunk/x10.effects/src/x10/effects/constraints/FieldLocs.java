package x10.effects.constraints;

import x10.constraint.XName;

/**
 * A field represents the mutable location o.f where o is an object
 * and f is a mutable field of that object.
 * @author vj
 *
 */
public interface FieldLocs extends Locs {
	ObjLocs obj();
	XName field();

}
