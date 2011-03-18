package x10.compiler.ws;

import x10.compiler.Abort;
import x10.compiler.Header;
import x10.compiler.Inline;

public abstract class RegularFrame extends Frame {
    public val ff:FinishFrame;

    // constructor
    @Header public def this(up:Frame, ff:FinishFrame) {
        super(up);
        this.ff = ff;
    }

    // copy constructor
    public def this(Int, o:RegularFrame) {
        super(o.up.realloc());
        throwable = NULL[Throwable]();
        this.ff = o.ff.redirect;
    }

    // copy methods
    public abstract def remap():RegularFrame;

    @Inline public final def push(worker:Worker) {
        worker.deque.push(this);
    }

    @Inline public final def redo(worker:Worker):void {
        worker.migrate();
        Runtime.wsBlock(remap());
        throw Abort.ABORT;
    }

    public def wrapResume(worker:Worker) {
        if (!isNULL(throwable)) return;
        try {
            resume(worker);
        } catch (t:Abort) {
            throw t;
        } catch (t:Throwable) {
            throwable = t;
        }
    }
}
