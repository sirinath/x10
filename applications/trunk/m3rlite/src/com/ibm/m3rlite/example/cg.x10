package com.ibm.m3rlite.example;
import com.ibm.m3rlite.Job;
import x10.util.Pair;
import x10.util.Triple;
import x10.util.ArrayList;
import com.ibm.m3rlite.Engine;

import x10.util.Random;

import x10.lang.Math;



class DVector 
{
	val N : Long;
	val v : Rail[Double];

	public def this(n : Long)
	{
		N = n;
		v = new Rail[Double](n);
	}

	public def dot(t : DVector) : Double
	{
		var d : Double = 0.0;
		var i : Long;

		for(i=0;i<N;i++){
			d += v(i) * t.v(i);
		}
		return d;
	}

	public def norm() : Double
	{
		var d : Double = 0.0;
		var i : Long;

		for(i=0;i<N;i++){
			d += v(i) * v(i);
		}
		return d;
	}

	public def add(t : DVector)
	{
		var i : Long;

		for(i=0;i<N;i++){
			v(i) += t.v(i);
		}
	}

	public def madd(t : DVector, s : Double)
	{
		var i : Long;

		for(i=0;i<N;i++){
			v(i) += s*t.v(i);
		}
	}

	public def mult_and_add(s : Double, t : DVector)
	{
		var i : Long;

		for(i=0;i<N;i++){
			v(i) = s*v(i) + t.v(i);
		}
	}

	public def sub(t0 : DVector,t1 : DVector)
	{
		var i : Long;

		for(i=0;i<N;i++){
			v(i) = t0.v(i) - t1.v(i);
		}
	}

	public def copy(t : DVector)
	{
		var i : Long;

		for(i=0;i<N;i++){
			v(i) = t.v(i);
		}
	}

	public def clear()
	{
		var i : Long;

		for(i=0;i<N;i++){
			v(i) += 0.0;
		}
	}

	public def makeRandomVector()
	{
		var i: Long;
		var t : Double;
		val rnd = new Random(300);

		for(i=0;i<N;i++){
			t = rnd.nextDouble();
			v(i) = t;
		}
	}

}


class DBlockMatrix
{
	val row : Rail[DVector];
	val N : Long;
	val M : Long;
	val ipn : Long;
	val ipm : Long;
	val pn : Long;
	val pm : Long;
	val sn : Long;
	val sm : Long;
	val en : Long;
	val em : Long;

	public def this(n : Long, m : Long)
	{
		N = n;
		M = m;
		ipn = 0;
		ipm = 0;
		pn = 1;
		pm = 1;
		sn = 0;
		sm = 0;
		en = N - 1;
		em = M - 1;

		row = new Rail[DVector](m, (i:Long)=>new DVector(n));
	}

	public def this(n : Long, m : Long, ni : Long, mi : Long, np : Long, mp : Long)
	{
		N = n;
		M = m;

		ipn = ni;
		ipm = mi;
		pn = np;
		pm = mp;

		sn = ni*n / np;
		en = (ni + 1)*n / np;
		sm = mi*m / mp;
		em = (mi + 1)*m / mp;

		row = new Rail[DVector]((mi+1)*m/mp-mi*m/mp, (i:Long)=>new DVector((ni+1)*n/np-ni*n/np));
	}

	public def clear()
	{
		var i : Long;
		for(i=0;i<em-sm;i++){
			row(i).clear();
		}
	}

	public def MV(v : DVector) : DVector
	{
		val res = new DVector(em-sm);
		var i : Long;
		for(i=0;i<em-sm;i++){
			res.v(i) = row(i).dot(v);
		}
		return res;
	}

	public def makeRandomPositiveSymmetricSparseMatrix()
	{
		var i: Long;
		var j: Long;
		var t : Double;
		val rnd = new Random(100);

		for(i=0;i<N;i++){
			for(j=i;j<N;j++){
				t = rnd.nextDouble();
				if(t > 0.5){
					row(i).v(j) = 0.0;
					row(j).v(i) = 0.0;
				}
				else{
					row(i).v(j) = t*2.0;
					row(j).v(i) = t*2.0;
				}
			}
		}
	}

}


//CRS format sparse matrix
class DBlockSparseMatrix
{
	var value : Rail[Double];
	var col : Rail[Long];
	val ptr : Rail[Long];
	val N : Long;
	val M : Long;
	val ipn : Long;
	val ipm : Long;
	val pn : Long;
	val pm : Long;
	val sn : Long;
	val sm : Long;
	val en : Long;
	val em : Long;

	public def this(n : Long, m : Long)
	{
		N = n;
		M = m;
		ipn = 0;
		ipm = 0;
		pn = 1;
		pm = 1;
		sn = 0;
		sm = 0;
		en = N;
		em = M;

		ptr = new Rail[Long](M+1);
	}

	public def this(n : Long, m : Long, ni : Long, mi : Long, np : Long, mp : Long)
	{
		N = n;
		M = m;

		ipn = ni;
		ipm = mi;
		pn = np;
		pm = mp;

		sn = ni*n / np;
		en = (ni + 1)*n / np;
		sm = mi*m / mp;
		em = (mi + 1)*m / mp;

		ptr = new Rail[Long]((mi + 1)*m / mp - mi*m / mp+1);
	}

	public def set(src : DBlockMatrix)
	{
		val n : Long = en - sn;
		val m : Long = em - sm;
		val t_value = new Rail[Double](n*m);
		val t_col = new Rail[Long](n*m);
		var nv : Long = 0;
		var i : Long;
		var j : Long;

		for(i=0;i<m;i++){
			ptr(i) = nv;
			for(j=0;j<n;j++){
				if(src.row(i).v(j) != 0.0){
					t_value(nv) = src.row(i).v(j);
					t_col(nv) = j;
					nv++;
				}
			}
			if(ptr(i) == nv){
				t_value(nv) = 0.0;
				t_col(nv) = 0;
				nv++;
			}
		}
		ptr(m) = nv;

		value = new Rail[Double](nv);
		col = new Rail[Long](nv);

		Rail.copy(t_value,0,value,0,nv);
		Rail.copy(t_col,0,col,0,nv);
	}

	public def makeSubBlock(src : DBlockSparseMatrix)
	{
		val n : Long = en - sn;
		val m : Long = em - sm;
		val t_value = new Rail[Double](n*m);
		val t_col = new Rail[Long](n*m);
		var nv : Long = 0;
		var i : Long;
		var j : Long;

		for(i=sm;i<em;i++){
			ptr(i-sm) = nv;
			for(j=src.ptr(i);j<src.ptr(i+1);j++){
				if(src.col(j) >= sn && src.col(j) < en){
					t_value(nv) = src.value(j);
					t_col(nv) = src.col(j) - sn;
					nv++;
				}
			}
			if(ptr(i-sm) == nv){
				t_value(nv) = 0.0;
				t_col(nv) = 0;
				nv++;
			}
		}
		ptr(m) = nv;

		value = new Rail[Double](nv);
		col = new Rail[Long](nv);

		Rail.copy(t_value,0,value,0,nv);
		Rail.copy(t_col,0,col,0,nv);
	}

	public def makeSubBlock(src : GlobalRef[DBlockSparseMatrix])
	{
		val n : Long = en - sn;
		val m : Long = em - sm;
		val t_value = new Rail[Double](n*m);
		val t_col = new Rail[Long](n*m);
		var nv : Long = 0;
		var i : Long;
		var j : Long;

		for(i=sm;i<em;i++){
			val ti : Long = i;
			val sj : Long = at(src) src().ptr(ti);
			val ej : Long = at(src) src().ptr(ti+1);
			ptr(i-sm) = nv;
			for(j=sj;j<ej;j++){
				val tj : Long = j;
				val tc : Long = at(src) src().col(tj);
				if(tc >= sn && tc < en){
					t_value(nv) = at(src) src().value(tj);
					t_col(nv) = at(src) src().col(tj);
					t_col(nv) -= sn;
					nv++;
				}
			}
			if(ptr(i-sm) == nv){
				t_value(nv) = 0.0;
				t_col(nv) = 0;
				nv++;
			}
		}
		ptr(m) = nv;

		value = new Rail[Double](nv);
		col = new Rail[Long](nv);

		Rail.copy(t_value,0,value,0,nv);
		Rail.copy(t_col,0,col,0,nv);

	}

	public def MV(v : DVector) : DVector
	{
		val m : Long = em-sm;
		val res = new DVector(m);
		var i : Long;
		var j : Long;

		for(i=0;i<m;i++){
			res.v(i) = 0.0;
			for(j=ptr(i);j<ptr(i+1);j++){
				res.v(i) += value(j) * v.v(col(j));
			}
		}
		return res;
	}
}



class BlockDist 
{
	var ipx : Long;
	var ipy : Long;
	var npx : Long;
	var npy : Long;

	public def this()
	{
	}

	public def set(nx : Long, ny : Long, ip : Long, np : Long)
	{
		npx = np;
		npy = 1;

		while(npy < npx){
			if(npx % 2 == 0){
				npx /= 2;
				npy *= 2;
			}
			else if(npx % 3 == 0){
				npx /= 3;
				npy *= 3;
			}
			else if(npx % 5 == 0){
				npx /= 5;
				npy *= 5;
			}
			else{
				var t : Long = npx;
				npx = npy;
				npy = t;
				break;
			}
		}

		ipx = ip % npx;
		ipy = ip / npx;
	}
}





class SparseMV
	implements Job[DBlockSparseMatrix,DVector,Triple[Long,Long,Long],DVector,Long,DVector]
{
	val mat : DBlockSparseMatrix;
	val inVec : DVector;
	val outVec : DVector;
	val dist : PlaceLocalHandle[BlockDist];
	val matRef : GlobalRef[DBlockSparseMatrix];
	val inVecRef : GlobalRef[DVector];
	val outVecRef : GlobalRef[DVector];
	var N : Long;
	var M : Long;
	var iStop:Long = 0;


	public def this(n:Long, m:Long)
	{
		N = n;
		M = m;

		mat = new DBlockSparseMatrix(n,m);
		inVec = new DVector(n);
		outVec = new DVector(m);

		matRef = GlobalRef[DBlockSparseMatrix](mat);
		inVecRef = GlobalRef[DVector](inVec);
		outVecRef = GlobalRef[DVector](outVec);

		dist = PlaceLocalHandle.make[BlockDist](Place.places(), ()=>new BlockDist());
	}

	public def initDist()
	{
		finish for(p in Place.places()) at(p) async {
			dist().set(N,M,p.id(),Place.numPlaces());
		}
	}
	public def reset()
	{
		iStop = 0;
	}

	public def stop():Boolean=iStop++>0;


	//(K1 = local matrix, V1 = local vector)
//	public def source()= new Iterable[Pair[DBlockMatrix,DVector]]() {

	public def source() {
		var x : Long;
		var y : Long;

		val m : DBlockSparseMatrix = new DBlockSparseMatrix(N,M,dist().ipx,dist().ipy,dist().npx,dist().npy);

		val sx : Long = m.sn;
		val sy : Long = m.sm;
		val ex : Long = m.en;
		val ey : Long = m.em;
		val v : DVector = new DVector(ex-sx);

		//copy global matrix to local matrix
		m.makeSubBlock(matRef);

		//copy global vector to local vector
		for(x=sx;x<ex;x++){
			val tx = x;
			v.v(x-sx) = at(inVecRef) inVecRef().v(tx);
		}

		return new Iterable[Pair[DBlockSparseMatrix,DVector]](){
			public def iterator() = new Iterator[Pair[DBlockSparseMatrix,DVector]]() {
				var i:Long = 0;
				public def hasNext() = i++ < 1;
				public def next() = Pair[DBlockSparseMatrix,DVector](m,v);
			};
		};
	}

	public def partition(k:Triple[Long,Long,Long])= k.third;

	public def sink(s:Iterable[Pair[Long, DVector]]): void {

		if (s !=null){
			for (x in s){
				var i : Long;

				for(i=0;i<x.second.N;i++){
					val t : Double = x.second.v(i);
					val pos : Long = i + x.first;
					at(outVecRef) outVecRef().v(pos) = t;
				}
			}
		}
	}

	// (K2 = (starting row, number of rows, place id for reduce number), V2 = mv result)
	public def mapper(k:DBlockSparseMatrix, v:DVector, s:(Triple[Long,Long,Long],DVector)=>void):void {
		var i : Long;
		var j : Long;
		var res : DVector = k.MV(v);

		s(Triple[Long,Long,Long](k.sm,k.em-k.sm,k.ipm * k.pn),res);
	}

	// (K3 = starting row number, V3 = mv result)
	public def reducer(a:Triple[Long,Long,Long], b:Iterable[DVector], sink:ArrayList[Pair[Long, DVector]]): void {
		var i : Long;

		if (b !=null){
			var res : DVector = new DVector(a.second);

			for (x in b){
				res.add(x);
			}

			sink.add(Pair(a.first as Long, res));
		}
	}

}


public class cg
{

	public static def test0(args:Rail[String]){
		val N : Long = args.size > 0 ? Long.parseLong(args(0)) : 1000;
		var i : Long;
		var j : Long;

		val h = new SparseMV(N,N);
		val src = new DBlockMatrix(N,N);

		val vIn = new DVector(N);
		val vx = new DVector(N);
		val vr = new DVector(N);
		val vp = new DVector(N);
		var rr : Double;
		var rrp : Double;
		var pap : Double;
		var cr : Double;
		var bk : Double;
		var iter : Long;
		val Niter : Long = 500;
		val enorm : Double = 1.0e-16;

		Console.OUT.println("N = " + N);

		h.initDist();

		src.makeRandomPositiveSymmetricSparseMatrix();
		h.mat.set(src);
		vIn.makeRandomVector();
		//vIn.clear();
		//vIn.v(0) = 1.0;

		//init
		val snorm : Double = 1.0 / vIn.norm();
		vx.copy(vIn);

		h.inVec.copy(vx);
		new Engine(h).run();
		h.reset();
		vr.sub(vIn,h.outVec);

		vp.copy(vr);
		rr = vr.norm();
		rrp = rr;

		//CG iteration
		for(iter=0;iter<Niter;iter++){
			h.inVec.copy(vp);
			new Engine(h).run();
			h.reset();
			pap = h.outVec.dot(vp);

			cr = rrp/pap;

			vx.madd(vp,cr);
			vr.madd(h.outVec,-cr);

			rr = vr.norm();
			bk = rr/rrp;

			vp.mult_and_add(bk,vr);
			rrp = rr;

			Console.OUT.println("["+iter+"] rr = "+rr);

			if(rr*snorm < enorm){
				break;
			}
		}

		Console.OUT.println("CG done");
	}
	public static def main(args:Rail[String]) {
		test0(args);
	}
}

	