/* The Computer Language Benchmarks Game
 * http://shootout.alioth.debian.org/
 * 
 * based on Java version by  James McIlree, Matthieu Bentot and The Anh Tran
 */

import x10.io.IOException;
import x10.io.File;
import x10.io.FileReader;
import x10.io.EOFException;
import x10.util.ArrayBuilder;
import x10.util.Map;
import x10.util.HashMap;
import x10.util.ArrayList;
import x10.util.StringBuilder;
import x10.util.concurrent.Future;

public class knucleotide {
	
	static def writeCount(futures:ArrayList[Future[HashMap[ByteString, ByteString]]] , nucleotideFragment:String) {
		key:ByteString  = new ByteString(nucleotideFragment.length());
		key.calculateHash(nucleotideFragment.bytes(), 0);
		var count:int = 0;
		for (future:Future[HashMap[ByteString, ByteString]]  in futures) {
			val temp = future().get(key);
			if (temp != null) count += temp().count;
		}

		return count + "\t" + nucleotideFragment.toUpperCase() + '\n';
	}
	static def  sumTwoMaps(map1:HashMap[ByteString, ByteString] , map2:HashMap[ByteString, ByteString] ) {
		for (entry:Map.Entry[ByteString, ByteString] in map2.entries()) {
			val sum = map1.get(entry.getKey());
			if (sum != null)
				sum().count += entry.getValue().count;
			else
				map1.put(entry.getKey(), entry.getValue());
				
		}
		return map1;
	}
	
	
	static def createFragmentMap(sequence:Array[Byte], offset:int, fragmentLength:int) {
		val map =  new HashMap[ByteString, ByteString]();
		val lastIndex = sequence.size - fragmentLength + 1;
		var key:ByteString = new ByteString(fragmentLength);
		for (var index:int = offset; index<lastIndex; index+=fragmentLength) {
			key.calculateHash(sequence, index);
			val fragment = map.get(key);
			if (fragment != null) { // hmm.  is this possible?   do I have to say fragment() != null?  
				fragment().count++;// weirdness to a java programmer.  make sure this is
				// explained in a user's guide
			} else {
				map.put(key,key);
				key = new ByteString(fragmentLength);
			}
		}
		return map;
	}
	
	static def writeFrequencies(totalCount: float, frequencies:HashMap[ByteString,ByteString]) {
		val sb = new StringBuilder();
		while(0<frequencies.size()){
			var temp:ByteString = new ByteString(0);
			var count:int = 0;
			for (k in frequencies.entries()){
				if(k.getValue().count>count){
					count = k.getValue().count;
					temp =frequencies.get(k.getKey())() as ByteString;
				}
			}
			sb.add(temp.toString().toUpperCase() + "  " + (temp.count as float) * 100.0f / totalCount);
			sb.add('\n');
			frequencies.remove(temp);
		}
		return sb.add('\n').toString();
	}
	
	public static def main(args: Array[String](1){rail})  {
		val start = System.nanoTime();
		val f = new File("/Users/emontal/Desktop/inputs/input" + args(0) + ".txt");
		assert f.exists();
		val input = new FileReader(f);
		
		// yuck.
		try {
			while (true) {
				val line = input.readLine();
				// if (line.startsWith(">THREE")) break;
				if (line.indexOf(">THREE") == 0) break;
			}
		} catch (e:EOFException) {
			// do nothing
		}
		
		val writer = new ArrayBuilder[Byte]();
		
		try {
			while (true) {
				val bytes = input.readLine().bytes();
				for (i in bytes) {
					writer.add(bytes(i));
				}
			}
		} catch (e:EOFException) {
			// do nothing
		}
		
		val sequence = writer.result();
		val fragmentLengths = [1, 2, 3, 4, 6, 12, 18];
		val futures = new ArrayList[Future[HashMap[ByteString,ByteString]]]();  
		for (i in fragmentLengths) {
			for (var j:int =0; j<fragmentLengths(i);j++) { 
				val o = j;
				val fut = Future.make[HashMap[ByteString, ByteString]](()=>createFragmentMap(sequence, o, fragmentLengths(i)));
				futures.add(fut);
			}
		}
		
		val sb = new StringBuilder();
		sb.add(writeFrequencies(sequence.size, futures.get(0)()));
		sb.add(writeFrequencies(sequence.size - 1, sumTwoMaps(futures.get(1)(), futures.get(2)())));
		
		val nucleotideFragments =  ["ggt", "ggta", "ggtatt", "ggtattttaatt", "ggtattttaatttatagt"];
		for (var i:int = 0; i<nucleotideFragments.size;i++) {
			sb.add(writeCount(futures, nucleotideFragments(i).toString()));
		}

		Console.OUT.println(sb.toString());
		val end = System.nanoTime();
		Console.OUT.println("Program runtime: " + (end - start));
	}
	
	static class ByteString implements Comparable[ByteString] {
		val bytes:Array[Byte];
		var hash:int;
		var count:int = 1;
		def this(size:int) {
			this.bytes = new Array[Byte](size);
			for (var i:int = 0; i < size; i++){
				val b:byte = 0;
			}
		}
		
		def calculateHash(k:Array[Byte], offset:int) {
			var temp:int = 0; ;
			for (var i:Int = 0; i < bytes.size; i++) {
				bytes(i) = k(offset+i);
				temp = temp * 31 + bytes(i);
			}
			hash = temp;
		}
		
		public def equals(other:Any):Boolean {
			return bytes.toString().equals((other as ByteString).bytes.toString());
		}
		
		public def hashCode() {
			return hash;
		}
		
		public def compareTo(other:ByteString) {
			return other.count - count;
		}
		
		public def toString() {
			var chars:String = "";
			for (var i:int = 0; i < bytes.size;i++)
				chars += bytes(i) as char;
			return chars;
			
		}
	}
	
	
}

