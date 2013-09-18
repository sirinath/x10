package futuresched.benchs.pagerankdelta;

import x10.util.Random;
import x10.util.ArrayList;


public class Graph {

   public var nodes: ArrayList[Node];

   public def this(nodes: ArrayList[Node]) {
      this.nodes = nodes;
   }

   public def nodeCount(): Int {
      return nodes.size() as Int;
   }
   // A random connected undirected graph with n nodes and mb maximum branching factor.
   public static def random(n: Int, mb: Int): Graph {
      val list = new ArrayList[Node]();
      for (var i: Int = 0; i < n; i++)
         list.add(new Node(i));

      for (var i: Int = n - 1; i >= 1 ; i--) {
         val node1 = list.get(i);
         val node2 = list.get(i-1);
         node2.addOutNeighbor(node1);
         node1.addInNeighbor(node2);
      }

      val nRand = new Random();
      val bRand = new Random();

      for (var i: Int = 0; i < n; i++) {
         val node1 = list.get(i);
         val b = 1 + bRand.nextInt(mb);
         var d1: Int = node1.degree();
         while (d1 < b) {
            val node2Index = nRand.nextInt(n);
            val node2 = list.get(node2Index);
            if (node1 != node2 && !node1.contains(node2) && node2.degree() < mb) {
               node1.addOutNeighbor(node2);
               node2.addInNeighbor(node1);
               d1 = node1.degree();
            }
         }
      }

      return new FutGraph(list);
   }

   public def toString(): String {

      var s: String = "digraph {\n";
      val iter = nodes.iterator();
      while (iter.hasNext()) {
         val node = iter.next();
         //s += node.no + ": ";
         val iter2 = node.neighbors.iterator();
         while (iter2.hasNext()) {
            val node2 = iter2.next();
            s += "\t" + node.no + " -> " + node2.no + ";\n";
         }
         //s += "\n";
      }
      s += "}\n";
      return s;
   }

   public def toStringRanks(): String {

      var s: String = "Ranks:\n";
      val iter = nodes.iterator();
      while (iter.hasNext()) {
         val node = iter.next();
         //s += node.no + ": ";
         s += "\t" + node.no + ": " + node.rank + "\n";
      }
      return s;
   }


}





