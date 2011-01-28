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

package x10cpp.postcompiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import polyglot.main.Options;
import polyglot.util.ErrorInfo;
import polyglot.util.ErrorQueue;
import polyglot.util.InternalCompilerError;
import polyglot.util.QuotedStringTokenizer;
import polyglot.util.CollectionUtil; import x10.util.CollectionFactory;
import x10.Configuration;
import x10.X10CompilerOptions;
import x10cpp.X10CPPCompilerOptions;

public class CXXCommandBuilder {
    
    private static String UNKNOWN = "unknown";
    
    public static final String XLC_EXTRA_FLAGS = System.getenv("XLC_EXTRA_FLAGS");

    protected static final boolean ENABLE_PROFLIB = System.getenv("X10_ENABLE_PROFLIB") != null;

    protected final X10CPPCompilerOptions options;
    
    protected final PostCompileProperties x10rt;
    
    protected CXXCommandBuilder(Options options, PostCompileProperties x10rt, ErrorQueue eq) {
        this.options = (X10CPPCompilerOptions) options;
        this.x10rt = x10rt;
    }

    private String cxxCompiler = UNKNOWN;
    protected String defaultPostCompiler() {
        if (cxxCompiler.equals(UNKNOWN)) {
            String pc = x10rt.props.getProperty("CXX");
            // Sanity check that x10rt and all the precompiled libraries were built with the same C++ compiler
            for (PrecompiledLibrary pcl: options.x10libs) {
                String pc2 = pcl.props.getProperty("CXX");
                if (pc2 != null) {
                    if (pc != null && !pc2.equals(pc)) {
                        throw new InternalCompilerError("Conflicting postcompilers. Both "+pc+" and "+pc2+" requested");
                    }
                    pc = pc2;
                } 
            }
            cxxCompiler = pc == null ? "g++" : pc;
        }
	    return cxxCompiler;
    }
    
    private String platform = UNKNOWN;
    protected String getPlatform() {
        if (platform.equals(UNKNOWN)) {
            String p1 = x10rt.props.getProperty("PLATFORM");
            // Sanity check that x10rt and all the precompiled libraries were built for the same platform
            for (PrecompiledLibrary pcl: options.x10libs) {
                String p2 = pcl.props.getProperty("PLATFORM");
                if (p2 != null) {
                    if (p1 != null && !p2.equals(p1)) {
                        throw new InternalCompilerError("Conflicting platforms. Both "+p1+" and "+p2+" specified");
                    }
                    p1 = p2;
                } 
            }
            if (p1 == null) {
                throw new InternalCompilerError("No platform specified by given property files");
            }
            platform = p1;
        }
        
        return platform;        
    }

    
    protected final boolean usingXLC() {
        return defaultPostCompiler().contains("xlC");
    }

    /** Add the arguments that go before the output files */
    protected void addPreArgs(ArrayList<String> cxxCmd) {
        cxxCmd.add("-g");

        // x10rt and other misc header files
        cxxCmd.add("-I"+options.distPath()+"/include");
        
        // header files for all prebuilt-libraries
        for (PrecompiledLibrary pcl:options.x10libs) {
            cxxCmd.add("-I"+pcl.absolutePathToRoot+"/include");
        }

        // headers generated from user input
        cxxCmd.add("-I"+options.output_directory);
        cxxCmd.add("-I.");

        if (options.x10_config.OPTIMIZE) {
            cxxCmd.add(usingXLC() ? "-O3" : "-O2");
            cxxCmd.add(usingXLC() ? "-qinline" : "-finline-functions");
            cxxCmd.add("-DNO_TRACING");
            if (usingXLC()) {
                cxxCmd.add("-qhot");
                cxxCmd.add("-qtune=auto");
                cxxCmd.add("-qarch=auto");
            }
        }

        if (usingXLC()) {
            cxxCmd.add("-qsuppress=1540-0809"    // Do not warn about empty sources
                               + ":1540-1101"    // Do not warn about non-void functions with no return
                               + ":1500-029");   // Do not warn about being unable to inline when optimizing

            if (XLC_EXTRA_FLAGS != null) {
                cxxCmd.add(XLC_EXTRA_FLAGS);
            }
        } else {
            cxxCmd.add("-Wno-long-long");        // Do not warn about using long long
            cxxCmd.add("-Wno-unused-parameter"); // Do not warn about unused parameters
            // DISABLED due to XTENLANG-2215; finally block rewriting has (dynamically unreachable) path in which there is no return
            // cxxCmd.add("-Wreturn-type");         // Do warn about non-void functions with no return
        }

        if (options.x10_config.NO_CHECKS) {
            cxxCmd.add("-DNO_CHECKS");
        }

        cxxCmd.addAll(x10rt.cxxFlags);
        for (PrecompiledLibrary pcl:options.x10libs) {
            cxxCmd.addAll(pcl.cxxFlags);
        }
    }

    /** Add the arguments that go after the output files */
    protected void addPostArgs(ArrayList<String> cxxCmd) {
        
        for (PrecompiledLibrary pcl:options.x10libs) {
            cxxCmd.add("-L"+pcl.absolutePathToRoot+"/lib");
            cxxCmd.addAll(pcl.ldFlags);
            cxxCmd.addAll(pcl.libs);
        }
            
        // x10rt
        cxxCmd.add("-L"+options.distPath()+"/lib");
        cxxCmd.addAll(x10rt.ldFlags);
        cxxCmd.addAll(x10rt.libs);

        cxxCmd.add("-ldl");
        cxxCmd.add("-lm");
        cxxCmd.add("-lpthread");

        if (ENABLE_PROFLIB) {
            cxxCmd.add("-lprofiler");
        }
    }

    protected void addExecutablePath(ArrayList<String> cxxCmd) {
        File exe = null;
        if (options.executable_path != null) {
            exe = new File(options.executable_path);
        } else if (options.x10_config.MAIN_CLASS != null) {
            exe = new File(options.output_directory, options.x10_config.MAIN_CLASS);
        } else {
            return;
        }
        cxxCmd.add("-o");
        cxxCmd.add(exe.getAbsolutePath().replace(File.separatorChar,'/'));
    }

    /** Construct the C++ compilation command */
    public final String[] buildCXXCommandLine(Collection<String> outputFiles) {
        String post_compiler = options.post_compiler;
        if (post_compiler.contains("javac")) {
            post_compiler = defaultPostCompiler();
        }

        QuotedStringTokenizer st = new QuotedStringTokenizer(post_compiler);
        int pc_size = st.countTokens();
        ArrayList<String> cxxCmd = new ArrayList<String>();
        String token = "";
        for (int i = 0; i < pc_size; i++) {
            token = st.nextToken();
            // A # as the first token signifies that the default postcompiler for this platform be used
            if (i==0 && token.equals("#")) {
            	cxxCmd.add(defaultPostCompiler());
            	continue;
            }

        	// consume all tokens up until the next # (or %) whereupon we will insert (or not)
        	// default CXXFLAGS parameters and generated compilation units
            if (token.equals("#") || token.equals("%")) {
                break;
            }
            cxxCmd.add(token);
        }

        boolean skipArgs = token.equals("%");
        if (!skipArgs) {
            addPreArgs(cxxCmd);
            addExecutablePath(cxxCmd);
        }

        // TODO: do we really need this excludes logic here?
        //       seems like putting the source file in the manifest 
        //       would have been enough to squash the job from generating
        //       the file in the first place
        Set<String> exclude = CollectionFactory.newHashSet();
        for (PrecompiledLibrary pco:options.x10libs) {
            exclude.addAll(pco.generatedFiles);
        }

        Iterator<String> iter = outputFiles.iterator();
        for (; iter.hasNext(); ) {
            String file = (String) iter.next();
            file = file.replace(File.separatorChar,'/');
            if (exclude.contains(file))
                continue;
            if (file.endsWith(".cu")) continue;
            cxxCmd.add(file);
        }

        while (st.hasMoreTokens()) {
            token = st.nextToken();
            // The second '#' delimits the libraries that have to go at the end
            if (token.equals("#") || token.equals("%")) {
                break;
            }
            cxxCmd.add(token);
        }

        boolean skipLibs = token.equals("%");
        if (!skipLibs) {
            addPostArgs(cxxCmd);
        }

        while (st.hasMoreTokens()) {
            cxxCmd.add(st.nextToken());
        }

        return cxxCmd.toArray(new String[cxxCmd.size()]);
    }

    private static Properties loadPropertyFile(String filename) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(filename));
        } catch(IOException e) {
            throw new InternalCompilerError("Unable to load property file "+filename+" "+ e.getMessage(), e);
        }
        return properties;
    }
    
    /**
     * Construct a CXXCommandBuilder for the given platform and options.
     * 
     * @param platform
     * @param options
     * @param eq
     * @return
     */
    public static CXXCommandBuilder getCXXCommandBuilder(X10CompilerOptions options, ErrorQueue eq) {
        // TODO: get options.distPath external to this method
        String dp = System.getProperty("x10.dist");
        options.setDistPath(dp);
        
        // TODO: get properties file external to this method and pass it as an argument
        String platform = System.getenv("X10_PLATFORM")==null?"unknown":System.getenv("X10_PLATFORM");
        String rtimpl = System.getenv("X10RT_IMPL");
        if (rtimpl == null) {
            // assume pgas (default to old behavior)
            if (platform.startsWith("aix_")) {
                rtimpl = "pgas_lapi";
            } else {
                rtimpl = "sockets";
            }
        }
        // allow the user to give an explicit path, otherwise look in etc
        if (!rtimpl.endsWith(".properties")) {
            rtimpl = dp + "/etc/x10rt_"+rtimpl+".properties";
        }
        Properties x10rt = loadPropertyFile(rtimpl);
        PostCompileProperties x10rt_props = new PostCompileProperties(x10rt);  
        
        CXXCommandBuilder ccb;
        
        if (platform.startsWith("win32_")) {
            ccb = new Cygwin_CXXCommandBuilder(options, x10rt_props, eq);
        } else if (platform.startsWith("linux_")) {
            ccb = new Linux_CXXCommandBuilder(options, x10rt_props, eq);
        } else if (platform.startsWith("aix_")) {
            ccb =  new AIX_CXXCommandBuilder(options, x10rt_props, eq);
        } else if (platform.startsWith("sunos_")) {
            ccb =  new SunOS_CXXCommandBuilder(options, x10rt_props, eq);
        } else if (platform.startsWith("macosx_")) {
            ccb = new MacOSX_CXXCommandBuilder(options, x10rt_props, eq);
        } else if (platform.startsWith("freebsd_")) {
            ccb =  new FreeBSD_CXXCommandBuilder(options, x10rt_props, eq);
        } else {   
            eq.enqueue(ErrorInfo.WARNING,
                       "Unknown platform '"+platform+"'; using the default post-compiler (g++)");
            ccb = new CXXCommandBuilder(options, x10rt_props, eq);
        }
        
        return ccb;
    }

}
