package x10.compiler.ws;

public final class RootFinish extends FinishFrame {
    public def this() {
        super(new RootFrame());
        asyncs = 1;
        //move the assign to make
        //redirect = this; 
        this.redirect = NULL[FinishFrame]();
    }

    public def init() {
        redirect = this;
        return this;
    }

    public def remap():FinishFrame = this;
}