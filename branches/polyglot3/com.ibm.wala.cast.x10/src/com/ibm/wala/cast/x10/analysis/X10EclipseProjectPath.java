package com.ibm.wala.cast.x10.analysis;

import java.io.IOException;
import java.util.jar.JarFile;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;

import com.ibm.wala.ide.util.EclipseProjectPath;

/**
 * Sub-class of EclipseProjectPath that prevents the X10 runtime jar from
 * being handled by the Primordial loader. That jar file is handled instead
 * by the X10PrimordialClassLoader.
 * @author rfuhrer
 */
public class X10EclipseProjectPath extends EclipseProjectPath {

    public X10EclipseProjectPath(IJavaProject project) throws IOException, CoreException {
	super(project, AnalysisScopeType.SOURCE_FOR_PROJ);
    }

    @Override
    protected boolean isPrimordialJarFile(JarFile j) {
        return !j.getName().contains("x10.runtime");
    }
}
