package x10.io;

public /*value*/ class FilterWriter extends Writer {
    val w: Writer;
    
    protected def inner(): Writer = w;

    public def this(w: Writer) { this.w = w; }

    public def close(): Void throws IOException = w.close();
    public def flush(): Void throws IOException = w.flush();

    public def write(b: Byte): Void throws IOException = w.write(b);
}
