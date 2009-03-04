/**
 * 
 */
package x10c.util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import polyglot.ast.SourceFile;
import polyglot.ext.x10cpp.visit.X10CPPTranslator.DelegateTargetFactory;
import polyglot.frontend.Job;
import polyglot.util.SimpleCodeWriter;

/**
 * This class represents a collection of output streams. Each stream has an associated
 * type, specified by the extension string.
 * Operations are provided to create a new stream of a given type (see getNewStream), 
 * and to commit all streams. Committing causes the contents of all streams of a given 
 * type to be written out to the file associated with that type, in the order in which 
 * the streams of that type were created.
 * 
 * @author nvk
 * @author vj -- Moved out to its own separate class
 * @author igor -- completely rewritten
 */
public class WriterStreams {
    private Map<String, SimpleCodeWriter> codeWriters;
    private Vector<ClassifiedStream> streams;
    private DelegateTargetFactory targetFactory;
    private Job job;
    private String pkg;
    private String className;

    public WriterStreams(String className, String pkg,
                         DelegateTargetFactory tf,
                         Job job) throws IOException
    {
        streams = new Vector<ClassifiedStream>();
        codeWriters = new TreeMap<String,SimpleCodeWriter>();
        targetFactory = tf;
        this.job = job;
        this.pkg = pkg;
        this.className = className;
    }

    /**
     * Write out all the streams associated with this object to their correspodnding files.
     * Note that all streams of the same type are written to the same file, their contents 
     * concatenated in the order in which the streams were created, as indicated by the
     * prepend argument to {@link #getNewStream(String, boolean)}.
     * @throws IOException
     */
    public void commitStreams() throws IOException {
        Set<String> nonEmpty = new HashSet<String>();
        for (ClassifiedStream s : streams) {
            nonEmpty.add(s.ext);
        }
        Set<String> extensions = new TreeSet<String>();
        for (ClassifiedStream s : streams) {
            extensions.add(s.ext);
        }
        for (String ext : extensions) {
//            if (!nonEmpty.contains(ext))
//                continue;
            final File file = targetFactory.integratedOutputFile(pkg, className, null, ext);
            codeWriters.put(ext,
                            new SimpleCodeWriter(targetFactory.outputWriter(file),
                                                 job.compiler().outputWidth()));
        }
        for (ClassifiedStream s : streams) {
            s.flush();
            codeWriters.get(s.ext).write(s.contents());
        }
        for (String ext : extensions) {
//            if (!nonEmpty.contains(ext))
//                continue;
            SimpleCodeWriter w = codeWriters.get(ext);
            w.flush();
            w.close();
        }
    }

    /**
     * Create and return a new stream of type ext.
     * @param ext
     * @return a new stream of type ext
     */
    ClassifiedStream getNewStream(String ext) { return getNewStream(ext, true); }

    /**
     * Create and return a new stream of type ext, inserting it either at the beginning
     * or at the end of the stream list.
     * @param ext
     * @param prepend Whether to prepend the new stream to all streams of its class (true)
     *                or append it (false)
     * @return a new stream of type ext
     */
    ClassifiedStream getNewStream(String ext, boolean prepend) {
        ClassifiedStream cs = new ClassifiedStream(ext, job.compiler().outputWidth());
        if (prepend) {
            streams.add(0, cs);
        } else {
            streams.add(cs);
        }
        return cs;
    }

    /**
     * Create and return a new stream of type ext, inserting it either before or after
     * a given stream s.
     * @param ext
     * @param s
     * @param prepend Whether to prepend the new stream (true) or append it (false)
     * @return a new stream of type ext
     */
    ClassifiedStream getNewStream(String ext, ClassifiedStream s, boolean prepend) {
        ClassifiedStream cs = new ClassifiedStream(ext, job.compiler().outputWidth());
        int i = streams.indexOf(s);
        if (prepend) {
            streams.add(i, cs);
        } else {
            streams.add(i+1, cs);
        }
        return cs;
    }
}
// vim:tabstop=4:shiftwidth=4:expandtab
