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

package x10.matrix.builder;

import x10.matrix.Matrix;
import x10.matrix.ElemType;

public type MatrixBuilder(blder:MatrixBuilder)=MatrixBuilder{self==blder};

public interface MatrixBuilder {
    
    
    public def init(initFun:(Long,Long)=>ElemType):MatrixBuilder(this);
    
    public def initRandom(nonZeroDensity:Float, initFun:(Long,Long)=>ElemType):MatrixBuilder(this);
    
    //public def initRandomTri(up:Boolean):MatrixBuilder;
    //public def initRandomTri(halfNonZeroDensity:ElemType, up:Boolean):MatrixBuilder(this);
    //public def initRandomSym(halfNonZeroDensity:ElemType):MatrixBuilder(this);
    
    public def set(r:Long, c:Long, value:ElemType): void;
    public def reset(r:Long, c:Long):Boolean;
    
    
    //public def makeTranspose(mat:Matrix):void;
    
    public def toMatrix():Matrix;
    
    
    
}
