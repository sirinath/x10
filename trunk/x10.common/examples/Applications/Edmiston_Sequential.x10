/** 
 * Sequential version of Edmiston's algorithm for Sequence Alignment 
 * This code is an X10 port of the Edmiston_Sequential.c program written by 
 * Sirisha Muppavarapu (sirisham@cs.unm.edu), U. New Mexico.
 *
 * @author Vivek Sarkar (vsarkar@us.ibm.com)
 * @author Kemal Ebcioglu (kemal@us.ibm.com)
 * 
 */

import java.util.Random;

public class Edmiston_Sequential {
    const int gapPen = 2;
    const int match = 0;
    const int misMatch= -1;
    const int EXPECTED_RESULT= 549;

    /**
     * Edmiston's algorithm
     */
    public boolean run() {

        final int N = 10;
        final int M = 10;
        final char[.] c1=new char[(0:N)->here]
          (point[i]) {return (i==0)?'-':randomChar(i);};
        final char[.] c2= new char[(0:M)->here]
          (point[i]) {return (i==0)?'-':randomChar(N+i);};
        final distribution D=[0:N,0:M]->here;
        final distribution Dinner=D|[1:N,1:M];
        final distribution Dboundary=D-Dinner;
        //  e is initialized to:
        //  0     1*gapPen     2*gapPen     3*gapPen ...
        //  1*gapPen ...
        //  2*gapPen ...
        //  3*gapPen ...
        //  ...
        final int[.] e=new int[D](point [i,j]){return Dboundary.contains([i,j])?gapPen*(i+j):0;};
        for(point [i,j]:Dinner) 
          e[i,j]=
            min3(e[i-1,j]+gapPen,
                 e[i,j-1]+gapPen,
                 e[i-1,j-1]+(c1[i]==c2[j]?match:misMatch));
        pr(c1,c2,e,"Edit distance matrix:");
        return e.sum()==EXPECTED_RESULT;
    }

    /**
     * returns the minimum of x y and z.
     */
    static int min3(int x, int y, int z) {
        int t=(x<y)?x:y;
        return (t<z)?t:z;
    }

    /** 
     * Function to generate the i'th random character
     */
    char randomChar(int i) {
        // Randomly select one of 'A', 'C', 'G', 'T' 
        //
        // find i'th random pair of booleans
        // TODO: need parallel version of this
        boolean bit1=false;
        boolean bit2=false;
        final Random  rand=new Random(1L);
        for(point [k]: 1:i) {
            bit1 = rand.nextBoolean();
            bit2 = rand.nextBoolean();
        }
        return bit1 ? (bit2 ? 'A' : 'C') : (bit2 ? 'G' : 'T');
    }

    /* 
     * Print the Edit Distance Matrix 
     */
    static void pr(char[.] c1, char[.] c2, int[.] e, String s)
    {
        final int N=c1.region.high();
        final int M=c2.region.high();
        final int K=4; // padding amount

        System.out.println(s);

        System.out.print(" "+pad(' ',K));
        for(point [j]:c2) System.out.print(" "+pad(c2[j],K));
        System.out.println();

        for(point [i]:0:N){
            System.out.print(" "+pad(c1[i],K));
            for(point [j]:0:M) System.out.print(" "+pad(e[i,j],K));
            System.out.println();
        }
    }

    /**
     * right justify an integer in a field of n blanks
     */
    static String pad(int x, int n) {
        String s=""+x;
        while (s.length()<n) s=" "+s;
        return s;
    }
    /**
     * right justify a character in a field of n blanks
     */
    static String pad(char x, int n) {
        String s=""+x;
        while (s.length()<n) s=" "+s;
        return s;
    }

   /**
    * main method
    */
    public static void main(String[] args) {
        final boxedBoolean b=new boxedBoolean();
        try {
                finish b.val=(new Edmiston_Sequential()).run();
        } catch (Throwable e) {
                e.printStackTrace();
                b.val=false;
        }
        System.out.println("++++++ "+(b.val?"Test succeeded.":"Test failed."));
        x10.lang.Runtime.setExitCode(b.val?0:1);
    }
    static class boxedBoolean {
        boolean val=false;
    }
}
