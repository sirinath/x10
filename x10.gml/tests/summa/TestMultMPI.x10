/*
 *  This file is part of the X10 Applications project.
 *
 *  (C) Copyright IBM Corporation 2011.
 */

import x10.compiler.Ifdef;
import x10.compiler.Ifndef;

import x10.matrix.Matrix;
import x10.matrix.DenseMatrix;
import x10.matrix.ElemType;

import x10.matrix.block.Grid;

import x10.matrix.dist.DistDenseMatrix;

import x10.matrix.dist.summa.mpi.SummaMPI; 
import x10.matrix.dist.summa.SummaDense;

// SKIP_NATIVE_X10: GML with MPI not included in Jenkins build
// SKIP_MANAGED_X10: GML with MPI not included in Jenkins build

/**
   This class contains test cases for dense matrix multiplication.
 */
public class TestMultMPI{
    public static def main(args:Array[String](1)) {
                val testcase = new SummaMultTest(args);
                testcase.run();
        }
}

class SummaMultTest {
    static def ET(a:Double)= a as ElemType;
    static def ET(a:Float)= a as ElemType;
    public val M:Long;
    public val N:Long;
    public val K:Long;
    
    public val pA:Grid;
    public val pB:Grid;
    public val pC:Grid;
    
    public def this(args:Array[String](1)) {
        M = args.size > 0 ? Long.parse(args(0)):21;
        N = args.size > 1 ? Long.parse(args(1)):23;
        K = args.size > 2 ? Long.parse(args(2)):25;     
        
        val numP = Place.numPlaces();//Place.numPlaces();
        Console.OUT.printf("\nTest SUMMA dist dense matrix over %d places\n", numP);
        pA = Grid.make(M, K);
        pB = Grid.make(K, N);
        pC = Grid.make(M, N);
    }
    
    public def run(): void {
        var ret:Boolean = true;
        // Set the matrix function
        @Ifdef("MPI_COMMU") {           
            ret &= (testMPI());
            ret &= (testMultTransMPI());
        }
        
        ret &= (testDenseMult());
        ret &= (testDenseMultTrans());
        
        if (!ret)
            Console.OUT.println("--------SUMMA distributed dense matrix multiply test failed!--------");
    }
    
    //This method only works for native C++ and MPI transport
    public def testMPI():Boolean {
        val numP = Place.numPlaces();//Place.numPlaces();
        Console.OUT.printf("\nTest C-SUMMA dist dense matrix MPI over %d places\n", numP);
        val da = DistDenseMatrix.make(pA);
        da.initRandom();
        val db = DistDenseMatrix.make(pB);
        db.initRandom();
        
        val dc = DistDenseMatrix.make(pC);
        
        SummaMPI.mult(1, ET(0.0), da, db, dc);
        
        val ma = da.toDense();
        val mb = db.toDense();
        val mc = DenseMatrix.make(ma.M, mb.N);
        
        mc.mult(ma, mb);
        
        val ret = dc.equals(mc as Matrix(dc.M, dc.N));
        if (!ret)
            Console.OUT.println("-----SUMMA C-MPI distributed dense matrix multplication test failed!-----");
        return ret;
    }
    
    //This method only works for native C++ and MPI transport
    public def testMultTransMPI():Boolean {
        val numP = Place.numPlaces();//Place.numPlaces();
        Console.OUT.printf("\nTest SUMMA C-MPI dist dense matrix multTrans MPI over %d places\n", numP);
        val da = DistDenseMatrix.make(M, K);
        da.initRandom();
        
        val db = DistDenseMatrix.make(N, K);
        db.initRandom();
        
        val dc = DistDenseMatrix.make(M, N);
        
        SummaMPI.multTrans(1, ET(0.0), da, db, dc);
        
        val ma = da.toDense();
        val mb = db.toDense();
        val mc = DenseMatrix.make(ma.M, mb.M);
        
        mc.mult(ma, mb);
        
        val ret = dc.equals(mc as Matrix(dc.M, dc.N));
        if (!ret)
            Console.OUT.println("-----SUMMA C-MPI distributed dense matrix multTrans test failed!-----");
        return ret;
    }   
    
    public def testDenseMult():Boolean {
        val numP = Place.numPlaces();//Place.numPlaces();
        Console.OUT.printf("\nTest SUMMA dist dense matrix over %d places\n", numP);
        val da = DistDenseMatrix.make(pA);
        da.initRandom();
        
        val db = DistDenseMatrix.make(pB);
        db.initRandom();
        
        val dc = DistDenseMatrix.make(pC);
        
        SummaDense.mult(1, ET(0.0), da, db, dc);
        
        val ma = da.toDense();
        val mb = db.toDense();
        val mc = DenseMatrix.make(ma.M, mb.N);
        
        mc.mult(ma, mb);
        
        val ret = dc.equals(mc as Matrix(dc.M, dc.N));
        if (!ret)
            Console.OUT.println("-----SUMMA x10 distributed dense matrix multplication test failed!-----");
        return ret;
    }
    
    public def testDenseMultTrans():Boolean {
        val numP = Place.numPlaces();//Place.numPlaces();
        Console.OUT.printf("\nTest SUMMA dist dense matrix multTrans over %d places\n", numP);
        val da = DistDenseMatrix.make(M, K);
        da.initRandom();
        val db = DistDenseMatrix.make(N, K);
        db.initRandom();
        
        val dc = DistDenseMatrix.make(M, N);
        
        SummaDense.multTrans(1, ET(0.0), da, db, dc);
        
        val ma = da.toDense();
        val mb = db.toDense();
        val mc = DenseMatrix.make(ma.M, mb.M);
        
        mc.mult(ma, mb);
        
        val ret = dc.equals(mc as Matrix(dc.M, dc.N));
        if (!ret)
            Console.OUT.println("-----SUMMA x10 distributed dense matrix multTrans test failed!-----");
        return ret;
    }
}
