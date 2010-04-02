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

import harness.x10Test;
import x10.array.Array;
import x10.array.Region;

/**
 * Implementing a 5-point stencil operation using foreach loop
 * @author Tong  11/29/2006
 * @author vj 09/2008 In 1.7 this version is hte same as StencilForeach2D. 
 * Type inference infers all the relevant properties.
 */

public class StencilForeach2D_Dep extends x10Test {
    
    public def run(): boolean = {
        val R  = [-1..256, -1..256] as Region;
        val r  = [0..255, 0..255] as Region;
        val north = [0, 1] as Point;
        val south = [0, -1] as Point;
        val west  = [-1, 0] as Point;
        val east = [1, 0] as Point; 
        val A  =  Array.make[double](R->here, (Point)=>0.0D); 
        val h: double = 0.1;
        
        finish foreach (val p: Point(2) in r) 
            A(p)=(A(p+north)+A(p+south)+A(p+west)+A(p+east)-4*A(p))*h;
            
        return true;
    }
    
    public static def main(var args: Rail[String]): void = {
        new StencilForeach2D_Dep().execute();
    }

}
