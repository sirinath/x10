
package raytracer.primitives;

import x10.compiler.Native;

import raytracer.*;

public class CUDARectangle extends Primitive {

    p1:Vector3; //position of one of the vertexes, where x/y are minimal
    w:Float, h:Float; // dimensions in the z plane
    mat:Material;

    // order of points:
    // p1----p3
    // |      |
    // p2-----*
    public def this (p1:Vector3, w:Float, h:Float, mat:Material) {
        this.p1 = p1;
        this.w = w;
        this.h = h;
        this.mat = mat;
    }

    public def getAABB() {
        return AABB(p1, p1+Vector3(w,h,0));
    }

    static val dir = Vector2(1,2.6f);
    static val scaledDir = 0.1f * dir.normalised();

    @Native("c++", "::fabsf(#x)")
    private static def absf(x:Float) : Float = Math.abs(x) as Float;
    private static def waveFunc (x:Float) {
        val x2 = absf(x);
        val x3 = x2 % 2.0f;
        return x3>1 ? (1.5f-x3)*(x3-2.36602540378444f)*(x3+0.36602540378444f-1) : (x3-0.5f)*(x3-1.36602540378444f)*(x3+0.36602540378444f);
    }


    public static def to_col(x:Vector3) = x*0.5f + Vector3(0.5f,0.5f,0.5f);


    public def intersectRay (s:RayState) {
        val facing = s.d.z;
        if (facing > 0) return; // quad is facing away from the camera
        val t_ = (p1.z - s.o.z); // solve the plane equation
        val t = t_ / facing; // solve the plane equation
        if (t < 0) return;
        if (t >= s.t) return;
        val ipos = s.o + t*s.d; // intersection point, on the plane
        val ipos2 = ipos - p1;
        val a = ipos2.x;
        if (a<0 || a>w) return;
        val b = ipos2.y;
        if (b<0 || b>h) return;

        // use a and b to get tex coords
        s.texCoord = Vector2(a/w,b/h);
        s.t = t;
        s.mat = mat;

        s.normal = s.engine.heightFieldNormal(s.texCoord);
        s.colour = to_col(s.normal);

    }
}

// vim: shiftwidth=4:tabstop=4:expandtab

