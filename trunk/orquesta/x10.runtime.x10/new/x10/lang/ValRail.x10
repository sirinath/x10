package x10.lang;

import x10.compiler.Native;
import x10.compiler.NativeRep;

@NativeRep("java", "#1[]")
public value class ValRail[+T](length: nat)
    implements Indexable[nat,T]
{
    // need to declare a constructor to shut up the initialization checker
    private native def this(n: nat): ValRail[T]{length==n};
    
    @Native("java", "x10.runtime.Runtime.makeValRail(#0, #1)")
    incomplete public static def make[T](length: nat, init: (nat) => T, value: boolean): ValRail[T]{self.length==length};

    @Native("java", "(#0[#1])")
    public native def get(i: nat): T;

    @Native("java", "(#0[#1])")
    public native def apply(i: nat): T;
}
