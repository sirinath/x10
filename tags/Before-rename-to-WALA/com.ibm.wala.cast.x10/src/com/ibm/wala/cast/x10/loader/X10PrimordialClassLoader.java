package com.ibm.domo.ast.x10.loader;

import com.ibm.wala.classLoader.ArrayClassLoader;
import com.ibm.wala.classLoader.ClassLoaderImpl;
import com.ibm.wala.classLoader.IClassLoader;
import com.ibm.wala.classLoader.Language;
import com.ibm.wala.ipa.callgraph.impl.SetOfClasses;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.util.Atom;

public class X10PrimordialClassLoader extends ClassLoaderImpl {
    public static Atom X10PrimordialName= Atom.findOrCreateAsciiAtom("X10Primordial");

    public static ClassLoaderReference X10Primordial= new ClassLoaderReference(X10PrimordialName, X10Language.X10);

    public X10PrimordialClassLoader(ClassLoaderReference loader, ArrayClassLoader arrayClassLoader, IClassLoader parent, SetOfClasses exclusions, IClassHierarchy cha) {
	super(loader, arrayClassLoader, parent, exclusions, cha);
    }

    @Override
    public Language getLanguage() {
        return X10Language.X10Lang;
    }
}
