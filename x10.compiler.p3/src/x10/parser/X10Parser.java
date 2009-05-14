
//#line 18 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
//
// Licensed Material
// (C) Copyright IBM Corp, 2006
//

package x10.parser;

import lpg.runtime.*;

//#line 28 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.io.File;

import polyglot.ext.x10.ast.X10Binary_c;
import polyglot.ext.x10.ast.X10Unary_c;
import polyglot.types.QName;
import polyglot.types.Name;
import polyglot.ast.AmbExpr;
import polyglot.ast.AmbTypeNode;
import polyglot.ast.ArrayInit;
import polyglot.ast.Assign;
import polyglot.ast.Binary;
import polyglot.ast.Block;
import polyglot.ast.Call;
import polyglot.ast.CanonicalTypeNode;
import polyglot.ast.Case;
import polyglot.ast.Catch;
import polyglot.ast.ClassBody;
import polyglot.ast.ClassDecl;
import polyglot.ast.ClassMember;
import polyglot.ast.ConstructorCall;
import polyglot.ast.ConstructorDecl;
import polyglot.ast.Eval;
import polyglot.ast.Expr;
import polyglot.ast.Field;
import polyglot.ast.FloatLit;
import polyglot.ast.ForInit;
import polyglot.ast.ForUpdate;
import polyglot.ast.Formal;
import polyglot.ast.Id;
import polyglot.ast.Import;
import polyglot.ast.Initializer;
import polyglot.ast.IntLit;
import polyglot.ast.LocalDecl;
import polyglot.ast.MethodDecl;
import polyglot.ast.FieldDecl;
import polyglot.ast.New;
import polyglot.ast.NodeFactory;
import polyglot.ast.PackageNode;
import polyglot.ast.Receiver;
import polyglot.ast.SourceFile;
import polyglot.ast.Stmt;
import polyglot.ast.SwitchElement;
import polyglot.ast.TopLevelDecl;
import polyglot.ast.TypeNode;
import polyglot.ast.Unary;
import polyglot.ast.FlagsNode;
import polyglot.parse.ParsedName;
import polyglot.ext.x10.ast.AnnotationNode;
import polyglot.ext.x10.ast.Closure;
import polyglot.ext.x10.ast.ClosureCall;
import polyglot.ext.x10.ast.Here;
import polyglot.ext.x10.ast.DepParameterExpr;
import polyglot.ext.x10.ast.Tuple;
import polyglot.ext.x10.ast.When;
import polyglot.ext.x10.ast.X10Formal;
import polyglot.ext.x10.ast.X10Formal_c;
import polyglot.ext.x10.ast.X10Loop;
import polyglot.ext.x10.ast.X10Call;
import polyglot.ext.x10.ast.ConstantDistMaker;
import polyglot.ext.x10.ast.TypeDecl;
import polyglot.ext.x10.ast.TypeParamNode;
import polyglot.ext.x10.ast.TypePropertyNode;
import polyglot.ext.x10.ast.X10NodeFactory;
import polyglot.ext.x10.types.TypeProperty;
import polyglot.ext.x10.types.X10TypeSystem;
import polyglot.ext.x10.types.X10TypeSystem_c;
import polyglot.ext.x10.ast.PropertyDecl;
import polyglot.ext.x10.ast.RegionMaker;
import polyglot.ext.x10.extension.X10Ext;
import polyglot.frontend.FileSource;
import polyglot.frontend.Parser;
import polyglot.lex.BooleanLiteral;
import polyglot.lex.CharacterLiteral;
import polyglot.lex.DoubleLiteral;
import polyglot.lex.FloatLiteral;
import polyglot.lex.Identifier;
import polyglot.lex.IntegerLiteral;
import polyglot.lex.LongLiteral;
import polyglot.lex.NullLiteral;
import polyglot.lex.Operator;
import polyglot.lex.StringLiteral;
import polyglot.main.Report;
import polyglot.parse.VarDeclarator;
import polyglot.types.Flags;
import polyglot.ext.x10.types.X10Flags;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.ErrorInfo;
import polyglot.util.ErrorQueue;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import polyglot.util.TypedList;
import polyglot.util.CollectionUtil;

import lpg.runtime.BacktrackingParser;
import lpg.runtime.BadParseException;
import lpg.runtime.BadParseSymFileException;
import lpg.runtime.DiagnoseParser;
import lpg.runtime.LexStream;
import lpg.runtime.NotBacktrackParseTableException;
import lpg.runtime.NullExportedSymbolsException;
import lpg.runtime.NullTerminalSymbolsException;
import lpg.runtime.ParseTable;
import lpg.runtime.PrsStream;
import lpg.runtime.RuleAction;
import lpg.runtime.UndefinedEofSymbolException;
import lpg.runtime.UnimplementedTerminalsException;

public class X10Parser implements RuleAction, Parser, ParseErrorCodes
{
    private PrsStream prsStream = null;
    
    private boolean unimplementedSymbolsWarning = false;

    private static ParseTable prsTable = new X10Parserprs();
    public ParseTable getParseTable() { return prsTable; }

    private BacktrackingParser btParser = null;
    public BacktrackingParser getParser() { return btParser; }

    private void setResult(Object object) { btParser.setSym1(object); }
    public Object getRhsSym(int i) { return btParser.getSym(i); }

    public int getRhsTokenIndex(int i) { return btParser.getToken(i); }
    public IToken getRhsIToken(int i) { return prsStream.getIToken(getRhsTokenIndex(i)); }
    
    public int getRhsFirstTokenIndex(int i) { return btParser.getFirstToken(i); }
    public IToken getRhsFirstIToken(int i) { return prsStream.getIToken(getRhsFirstTokenIndex(i)); }

    public int getRhsLastTokenIndex(int i) { return btParser.getLastToken(i); }
    public IToken getRhsLastIToken(int i) { return prsStream.getIToken(getRhsLastTokenIndex(i)); }

    public int getLeftSpan() { return btParser.getFirstToken(); }
    public IToken getLeftIToken()  { return prsStream.getIToken(getLeftSpan()); }

    public int getRightSpan() { return btParser.getLastToken(); }
    public IToken getRightIToken() { return prsStream.getIToken(getRightSpan()); }

    public int getRhsErrorTokenIndex(int i)
    {
        int index = btParser.getToken(i);
        IToken err = prsStream.getIToken(index);
        return (err instanceof ErrorToken ? index : 0);
    }
    public ErrorToken getRhsErrorIToken(int i)
    {
        int index = btParser.getToken(i);
        IToken err = prsStream.getIToken(index);
        return (ErrorToken) (err instanceof ErrorToken ? err : null);
    }

    public void reset(ILexStream lexStream)
    {
        prsStream = new PrsStream(lexStream);
        btParser.reset(prsStream);

        try
        {
            prsStream.remapTerminalSymbols(orderedTerminalSymbols(), prsTable.getEoftSymbol());
        }
        catch(NullExportedSymbolsException e) {
        }
        catch(NullTerminalSymbolsException e) {
        }
        catch(UnimplementedTerminalsException e)
        {
            if (unimplementedSymbolsWarning) {
                java.util.ArrayList unimplemented_symbols = e.getSymbols();
                System.out.println("The Lexer will not scan the following token(s):");
                for (int i = 0; i < unimplemented_symbols.size(); i++)
                {
                    Integer id = (Integer) unimplemented_symbols.get(i);
                    System.out.println("    " + X10Parsersym.orderedTerminalSymbols[id.intValue()]);               
                }
                System.out.println();
            }
        }
        catch(UndefinedEofSymbolException e)
        {
            throw new Error(new UndefinedEofSymbolException
                                ("The Lexer does not implement the Eof symbol " +
                                 X10Parsersym.orderedTerminalSymbols[prsTable.getEoftSymbol()]));
        } 
    }
    
    public X10Parser()
    {
        try
        {
            btParser = new BacktrackingParser(prsStream, prsTable, (RuleAction) this);
        }
        catch (NotBacktrackParseTableException e)
        {
            throw new Error(new NotBacktrackParseTableException
                                ("Regenerate X10Parserprs.java with -BACKTRACK option"));
        }
        catch (BadParseSymFileException e)
        {
            throw new Error(new BadParseSymFileException("Bad Parser Symbol File -- X10Parsersym.java"));
        }
    }
    
    public X10Parser(ILexStream lexStream)
    {
        this();
        reset(lexStream);
    }
    
    public int numTokenKinds() { return X10Parsersym.numTokenKinds; }
    public String[] orderedTerminalSymbols() { return X10Parsersym.orderedTerminalSymbols; }
    public String getTokenKindName(int kind) { return X10Parsersym.orderedTerminalSymbols[kind]; }
    public int getEOFTokenKind() { return prsTable.getEoftSymbol(); }
    public IPrsStream getIPrsStream() { return prsStream; }

    /**
     * @deprecated replaced by {@link #getIPrsStream()}
     *
     */
    public PrsStream getPrsStream() { return prsStream; }

    /**
     * @deprecated replaced by {@link #getIPrsStream()}
     *
     */
    public PrsStream getParseStream() { return prsStream; }

    public polyglot.ast.Node parser()
    {
        return parser(null, 0);
    }
    
    public polyglot.ast.Node parser(Monitor monitor)
    {
        return parser(monitor, 0);
    }
    
    public polyglot.ast.Node parser(int error_repair_count)
    {
        return parser(null, error_repair_count);
    }

    public polyglot.ast.Node parser(Monitor monitor, int error_repair_count)
    {
        btParser.setMonitor(monitor);
        
        try
        {
            return (polyglot.ast.Node) btParser.fuzzyParse(error_repair_count);
        }
        catch (BadParseException e)
        {
            prsStream.reset(e.error_token); // point to error token

            DiagnoseParser diagnoseParser = new DiagnoseParser(prsStream, prsTable);
            diagnoseParser.diagnose(e.error_token);
        }

        return null;
    }

    //
    // Additional entry points, if any
    //
    

    //#line 314 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
    private ErrorQueue eq;
    private X10TypeSystem ts;
    private X10NodeFactory nf;
    private FileSource source;
    private boolean unrecoverableSyntaxError = false;

    public void initialize(TypeSystem t, NodeFactory n, FileSource source, ErrorQueue q)
    {
        this.ts = (X10TypeSystem) t;
        this.nf = (X10NodeFactory) n;
        this.source = source;
        this.eq = q;
    }
    
    public X10Parser(ILexStream lexStream, TypeSystem t, NodeFactory n, FileSource source, ErrorQueue q)
    {
        this(lexStream);
        initialize((X10TypeSystem) t,
                   (X10NodeFactory) n,
                   source,
                   q);
        prsStream.setMessageHandler(new MessageHandler(q));
    }

public static class MessageHandler implements IMessageHandler {
ErrorQueue eq;

public MessageHandler(ErrorQueue eq) {
    this.eq = eq;
}

public void handleMessage(int errorCode, int[] msgLocation,
                          int[] errorLocation, String filename,
                          String[] errorInfo) {

    File file = new File(filename);

    int l0 = msgLocation[2];
    int c0 = msgLocation[3];
    int l1 = msgLocation[4];
    int c1 = msgLocation[5];
    int o0 = msgLocation[0];
    int o1 = msgLocation[0] + msgLocation[1];

    Position pos = new Position(file.getPath(),
                file.getPath(), l0, c0, l1, c1+1, o0, o1);

    String msg = "";
    String info = "";

    for (String s : errorInfo) {
        info += s;
    }

    switch (errorCode) {
        case LEX_ERROR_CODE:
            msg = "Unexpected character ignored: " + info;
            break;
        case ERROR_CODE:
            msg = "Parse terminated at this token: " + info;
            break;
        case BEFORE_CODE:
            msg = "Token " + info + " expected before this input";
            break;
        case INSERTION_CODE:
            msg = "Token " + info + " expected after this input";
            break;
        case INVALID_CODE:
            msg = "Unexpected input discarded: " + info;
            break;
        case SUBSTITUTION_CODE:
            msg = "Token " + info + " expected instead of this input";
            break;
        case DELETION_CODE:
            msg = "Unexpected input ignored: " + info;
            break;
        case MERGE_CODE:
            msg = "Merging token(s) to recover: " + info;
            break;
        case MISPLACED_CODE:
            msg = "Misplaced constructs(s): " + info;
            break;
        case SCOPE_CODE:
            msg = "Token(s) inserted to complete scope: " + info;
            break;
        case EOF_CODE:
            msg = "Reached after this token: " + info;
            break;
        case INVALID_TOKEN_CODE:
            msg = "Invalid token: " + info;
            break;
        case ERROR_RULE_WARNING_CODE:
            msg = "Ignored token: " + info;
            break;
        case NO_MESSAGE_CODE:
            msg = "Syntax error";
            break;
    }

    eq.enqueue(ErrorInfo.SYNTAX_ERROR, msg, pos);
}
}

    public String getErrorLocation(int lefttok, int righttok)
    {
        return prsStream.getFileName() + ':' +
               prsStream.getLine(lefttok) + ":" + prsStream.getColumn(lefttok) + ":" +
               prsStream.getEndLine(righttok) + ":" + prsStream.getEndColumn(righttok) + ": ";
    }

    public Position getErrorPosition(int lefttok, int righttok)
    {
        return new Position(null, prsStream.getFileName(),
               prsStream.getLine(lefttok), prsStream.getColumn(lefttok),
               prsStream.getEndLine(righttok), prsStream.getEndColumn(righttok));
    }

    // RMF 11/7/2005 - N.B. This class has to be serializable, since it shows up inside Type objects,
    // which Polyglot serializes to save processing when loading class files generated from source
    // by Polyglot itself.
    public static class JPGPosition extends Position
    {
        private static final long serialVersionUID= -1593187800129872262L;
        private final transient IToken leftIToken,
                                       rightIToken;

        public JPGPosition(String path, String filename, IToken leftToken, IToken rightToken)
        {
            super(path, filename,
                  leftToken.getLine(), leftToken.getColumn(),
                  rightToken.getEndLine(), rightToken.getEndColumn(),
                  leftToken.getStartOffset(), rightToken.getEndOffset());
            this.leftIToken = null; // BRT -- was null, need to keep leftToken for later reference
            this.rightIToken = null;  // BRT -- was null, need to keep rightToken for later reference
        }

        public IToken getLeftIToken() { return leftIToken; }
        public IToken getRightIToken() { return rightIToken; }

        public String toText()
        {
            if (leftIToken == null) return "...";
            IPrsStream prsStream = leftIToken.getPrsStream();
            return new String(prsStream.getInputChars(), offset(), endOffset() - offset() + 1);
        }
    }
    
    public void syntaxError(String msg, Position pos) {
                unrecoverableSyntaxError = true;
                eq.enqueue(ErrorInfo.SYNTAX_ERROR, msg, pos);
            }   
    

    public polyglot.ast.Node parse() {
        try
        {
            SourceFile sf = (SourceFile) parser();

            if (sf != null)
            {
                if (! unrecoverableSyntaxError)
                    return sf.source(source);
                eq.enqueue(ErrorInfo.SYNTAX_ERROR, "Unable to parse " + source.name() + ".", new Position(null, file(), 1, 1, 1, 1));
            }   
        }
        catch (RuntimeException e) {
            // Let the Compiler catch and report it.
            throw e;
        }
        catch (Exception e) {
            // Used by cup to indicate a non-recoverable error.
            eq.enqueue(ErrorInfo.SYNTAX_ERROR, e.getMessage(), new Position(null, file(), 1, 1, 1, 1));
        }

        return null;
    }

    public String file()
    {
        return prsStream.getFileName();
    }

    public JPGPosition pos()
    {
        return new JPGPosition("",
                               prsStream.getFileName(),
                               prsStream.getIToken(getLeftSpan()),
                               prsStream.getIToken(getRightSpan()));
    }

    public JPGPosition pos(int i)
    {
        return new JPGPosition("",
                               prsStream.getFileName(),
                               prsStream.getIToken(i),
                               prsStream.getIToken(i));
    }

    public JPGPosition pos(int i, int j)
    {
        return new JPGPosition("",
                               prsStream.getFileName(),
                               prsStream.getIToken(i),
                               prsStream.getIToken(j));
    }

    /**
     * Return the source position of the declaration.
     */
    public JPGPosition pos (VarDeclarator n)
    {
      if (n == null) return null;
      return (JPGPosition) n.pos;
    }

    public JPGPosition pos(JPGPosition start, JPGPosition end) {
        return new JPGPosition(start.path(), start.file(), start.leftIToken, end.rightIToken);
    }

    private void checkTypeName(Id identifier) {
        String filename = file();
        String idname = identifier.id().toString();
        int dot = filename.lastIndexOf('.'),
            slash = filename.lastIndexOf('/', dot);
        if (slash == -1)
            slash = filename.lastIndexOf('\\', dot);
        String clean_filename = (slash >= 0 && dot >= 0 ? filename.substring(slash+1, dot) : "");
        if ((! clean_filename.equals(idname)) && clean_filename.equalsIgnoreCase(idname))
            eq.enqueue(ErrorInfo.SYNTAX_ERROR,
                       "This type name does not match the name of the containing file: " + filename.substring(slash+1),
                       identifier.position());
   }


    private polyglot.lex.Operator op(int i) {
        return new Operator(pos(i), prsStream.getName(i), prsStream.getKind(i));
    }

    private polyglot.lex.Identifier id(int i) {
        return new Identifier(pos(i), prsStream.getName(i), X10Parsersym.TK_IDENTIFIER);
    }
    private String comment(int i) {
        String s = prsStream.getName(i);
        if (s != null && s.startsWith("/**") && s.endsWith("*/")) {
            return s +"\n";
        }
        return null;
    }

    private List<Formal> toFormals(List<Formal> l) { return l; }

    private List<Expr> toActuals(List<Formal> l) {
        List<Expr> l2 = new ArrayList<Expr>();
        for (Formal f : l) {
            l2.add(nf.Local(f.position(), f.name()));
        }
        return l2;
    }

    private List<TypeParamNode> toTypeParams(List<TypeParamNode> l) { return l; }

    private List<TypeNode> toTypeArgs(List<TypeParamNode> l) {
        List<TypeNode> l2 = new ArrayList<TypeNode>();
        for (TypeParamNode f : l) {
            l2.add(nf.AmbTypeNode(f.position(), null, f.name()));
        }
        return l2;
    }

            
    private List extractAnnotations(List l) {
        List l2 = new LinkedList();
        for (Iterator i = l.iterator(); i.hasNext(); ) {
            Object o = i.next();
            if (o instanceof AnnotationNode) {
                l2.add((AnnotationNode) o);
            }
        }
        return l2;
    }

    private FlagsNode extractFlags(List l, Flags f) {
        FlagsNode fn = extractFlags(l);
        fn = fn.flags(fn.flags().set(f));
        return fn;
    }
    
    private FlagsNode extractFlags(List l1, List l2) {
        List l = new ArrayList();
        l.addAll(l1);
        l.addAll(l2);
        return extractFlags(l);
    }
    
    private FlagsNode extractFlags(List l) {
        Position pos = null;
        X10Flags xf = X10Flags.toX10Flags(Flags.NONE);
        for (Iterator i = l.iterator(); i.hasNext(); ) {
            Object o = i.next();
            if (o instanceof FlagsNode) {
                FlagsNode fn = (FlagsNode) o;
                pos = pos == null ? fn.position() : new Position(pos, fn.position());
                Flags f = fn.flags();
                if (f instanceof X10Flags) {
                    xf = xf.setX((X10Flags) f);
                }
                else {
                    xf = X10Flags.toX10Flags(xf.set(f));
                }
            }
        }
        return nf.FlagsNode(pos == null ? Position.COMPILER_GENERATED : pos, xf);
    }

    /* Roll our own integer parser.  We can't use Long.parseLong because
     * it doesn't handle numbers greater than 0x7fffffffffffffff correctly.
     */
    private long parseLong(String s, int radix)
    {
        long x = 0L;

        s = s.toLowerCase();

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);

            if (c < '0' || c > '9') {
                c = c - 'a' + 10;
            }
            else {
                c = c - '0';
            }

            x *= radix;
            x += c;
        }

        return x;
    }

    private long parseLong(String s)
    {
        int radix,
            start_index,
            end_index = (s.charAt(s.length() - 1) == 'l' || s.charAt(s.length() - 1) == 'L'
                                                   ? s.length() - 1
                                                   : s.length());
        if (s.charAt(0) == '0')
        {
           if (s.length() > 1 && (s.charAt(1) == 'x' || s.charAt(1) == 'X'))
           {
               radix = 16;
               start_index = 2;
           }
           else
           {
               radix = 8;
               start_index = 0;
           }
        }
        else
        {
            radix = 10;
            start_index = 0;
        }

        return parseLong(s.substring(start_index, end_index), radix);
    }

    private polyglot.lex.LongLiteral int_lit(int i, int radix)
    {
        long x = parseLong(prsStream.getName(i), radix);
        return new LongLiteral(pos(i),  x, X10Parsersym.TK_IntegerLiteral);
    }

    private polyglot.lex.LongLiteral int_lit(int i)
    {
        long x = parseLong(prsStream.getName(i));
        return new LongLiteral(pos(i),  x, X10Parsersym.TK_IntegerLiteral);
    }

    private polyglot.lex.LongLiteral long_lit(int i, int radix)
    {
        long x = parseLong(prsStream.getName(i), radix);
        return new LongLiteral(pos(i), x, X10Parsersym.TK_LongLiteral);
    }

    private polyglot.lex.LongLiteral long_lit(int i)
    {
        long x = parseLong(prsStream.getName(i));
        return new LongLiteral(pos(i), x, X10Parsersym.TK_LongLiteral);
    }

    private polyglot.lex.FloatLiteral float_lit(int i)
    {
        try {
            String s = prsStream.getName(i);
            int end_index = (s.charAt(s.length() - 1) == 'f' || s.charAt(s.length() - 1) == 'F'
                                                       ? s.length() - 1
                                                       : s.length());
            float x = Float.parseFloat(s.substring(0, end_index));
            return new FloatLiteral(pos(i), x, X10Parsersym.TK_FloatingPointLiteral);
        }
        catch (NumberFormatException e) {
            unrecoverableSyntaxError = true;
            eq.enqueue(ErrorInfo.LEXICAL_ERROR,
                       "Illegal float literal \"" + prsStream.getName(i) + "\"", pos(i));
            return null;
        }
    }

    private polyglot.lex.DoubleLiteral double_lit(int i)
    {
        try {
            String s = prsStream.getName(i);
            int end_index = (s.charAt(s.length() - 1) == 'd' || s.charAt(s.length() - 1) == 'D'
                                                       ? s.length() - 1
                                                       : s.length());
            double x = Double.parseDouble(s.substring(0, end_index));
            return new DoubleLiteral(pos(i), x, X10Parsersym.TK_DoubleLiteral);
        }
        catch (NumberFormatException e) {
            unrecoverableSyntaxError = true;
            eq.enqueue(ErrorInfo.LEXICAL_ERROR,
                       "Illegal float literal \"" + prsStream.getName(i) + "\"", pos(i));
            return null;
        }
    }

    private polyglot.lex.CharacterLiteral char_lit(int i)
    {
        char x;
        String s = prsStream.getName(i);
        if (s.charAt(1) == '\\') {
            switch(s.charAt(2)) {
                case 'u':
                    x = (char) parseLong(s.substring(3, s.length() - 1), 16);
                    break;
                case 'b':
                    x = '\b';
                    break;
                case 't':
                    x = '\t';
                    break;
                case 'n':
                    x = '\n';
                    break;
                case 'f':
                    x = '\f';
                    break;
                case 'r':
                    x = '\r';
                    break;
                case '\"':
                    x = '\"';
                    break;
                case '\'':
                    x = '\'';
                    break;
                case '\\':
                    x = '\\';
                    break;
                default:
                    x = (char) parseLong(s.substring(2, s.length() - 1), 8);
                    if (x > 255) {
                        unrecoverableSyntaxError = true;
                        eq.enqueue(ErrorInfo.LEXICAL_ERROR,
                                   "Illegal character literal " + s, pos(i));
                    }
            }
        }
        else {
            assert(s.length() == 3);
            x = s.charAt(1);
        }

        return new CharacterLiteral(pos(i), x, X10Parsersym.TK_CharacterLiteral);
    }

    private polyglot.lex.BooleanLiteral boolean_lit(int i)
    {
        return new BooleanLiteral(pos(i), prsStream.getKind(i) == X10Parsersym.TK_true, prsStream.getKind(i));
    }

    private polyglot.lex.StringLiteral string_lit(int i)
    {
        String s = prsStream.getName(i);
        char x[] = new char[s.length()];
        int j = 1,
            k = 0;
        while(j < s.length() - 1) {
            if (s.charAt(j) != '\\')
                x[k++] = s.charAt(j++);
            else {
                switch(s.charAt(j + 1)) {
                    case 'u':
                        x[k++] = (char) parseLong(s.substring(j + 2, j + 6), 16);
                        j += 6;
                        break;
                    case 'b':
                        x[k++] = '\b';
                        j += 2;
                        break;
                    case 't':
                        x[k++] = '\t';
                        j += 2;
                        break;
                    case 'n':
                        x[k++] = '\n';
                        j += 2;
                        break;
                    case 'f':
                        x[k++] = '\f';
                        j += 2;
                        break;
                    case 'r':
                        x[k++] = '\r';
                        j += 2;
                        break;
                    case '\"':
                        x[k++] = '\"';
                        j += 2;
                        break;
                    case '\'':
                        x[k++] = '\'';
                        j += 2;
                        break;
                    case '\\':
                        x[k++] = '\\';
                        j += 2;
                        break;
                    default:
                    {
                        int n = j + 1;
                        for (int l = 0; l < 3 && Character.isDigit(s.charAt(n)); l++)
                            n++;
                        char c = (char) parseLong(s.substring(j + 1, n), 8);
                        if (c > 255) {
                            unrecoverableSyntaxError = true;
                            eq.enqueue(ErrorInfo.LEXICAL_ERROR,
                                       "Illegal character (" + s.substring(j, n) + ") in string literal " + s, pos(i));
                        }
                        x[k++] = c;
                        j = n;
                    }
                }
            }
        }

        return new StringLiteral(pos(i), new String(x, 0, k), X10Parsersym.TK_StringLiteral);
    }

    private polyglot.lex.NullLiteral null_lit(int i)
    {
        return new NullLiteral(pos(i), X10Parsersym.TK_null);
    }


    public void ruleAction(int ruleNumber)
    {
        switch (ruleNumber)
        {

            //
            // Rule 1:  TypeName ::= TypeName . ErrorId
            //
            case 1: {
               //#line 8 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 6 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 8 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                    setResult(new ParsedName(nf,
                                      ts,
                                      pos(getLeftSpan(), getRightSpan()),
                                      TypeName,
                                      nf.Id(pos(getRightSpan()), "*")));
                          break;
            }
        
            //
            // Rule 2:  PackageName ::= PackageName . ErrorId
            //
            case 2: {
               //#line 18 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 16 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                ParsedName PackageName = (ParsedName) getRhsSym(1);
                //#line 18 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                    setResult(new ParsedName(nf,
                                      ts,
                                      pos(getLeftSpan(), getRightSpan()),
                                      PackageName,
                                      nf.Id(pos(getRightSpan()), "*")));
                          break;
            }
        
            //
            // Rule 3:  ExpressionName ::= AmbiguousName . ErrorId
            //
            case 3: {
               //#line 28 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 26 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                ParsedName AmbiguousName = (ParsedName) getRhsSym(1);
                //#line 28 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                    setResult(new ParsedName(nf,
                                      ts,
                                      pos(getLeftSpan(), getRightSpan()),
                                      AmbiguousName,
                                      nf.Id(pos(getRightSpan()), "*")));
                          break;
            }
        
            //
            // Rule 4:  MethodName ::= AmbiguousName . ErrorId
            //
            case 4: {
               //#line 38 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 36 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                ParsedName AmbiguousName = (ParsedName) getRhsSym(1);
                //#line 38 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                    setResult(new ParsedName(nf,
                                      ts,
                                      pos(getLeftSpan(), getRightSpan()),
                                      AmbiguousName,
                                      nf.Id(pos(getRightSpan()), "*")));
                          break;
            }
        
            //
            // Rule 5:  PackageOrTypeName ::= PackageOrTypeName . ErrorId
            //
            case 5: {
               //#line 48 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 46 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                ParsedName PackageOrTypeName = (ParsedName) getRhsSym(1);
                //#line 48 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                    setResult(new ParsedName(nf,
                                      ts,
                                      pos(getLeftSpan(), getRightSpan()),
                                      PackageOrTypeName,
                                      nf.Id(pos(getRightSpan()), "*")));
                          break;
            }
        
            //
            // Rule 6:  AmbiguousName ::= AmbiguousName . ErrorId
            //
            case 6: {
               //#line 58 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 56 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                ParsedName AmbiguousName = (ParsedName) getRhsSym(1);
                //#line 58 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                    setResult(new ParsedName(nf,
                                      ts,
                                      pos(getLeftSpan(), getRightSpan()),
                                      AmbiguousName,
                                      nf.Id(pos(getRightSpan()), "*")));
                         break;
            }
        
            //
            // Rule 7:  FieldAccess ::= Primary . ErrorId
            //
            case 7: {
               //#line 68 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 66 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 68 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Field(pos(), Primary,
                                      nf.Id(pos(getRightSpan()), "*")));
                      break;
            }
    
            //
            // Rule 8:  FieldAccess ::= super . ErrorId
            //
            case 8: {
               //#line 74 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 74 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Field(pos(getRightSpan()), nf.Super(pos(getLeftSpan())),
                                      nf.Id(pos(getRightSpan()), "*")));
                      break;
            }
    
            //
            // Rule 9:  FieldAccess ::= ClassName . super$sup . ErrorId
            //
            case 9: {
               //#line 80 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 78 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                ParsedName ClassName = (ParsedName) getRhsSym(1);
                //#line 78 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                IToken sup = (IToken) getRhsIToken(3);
                //#line 80 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Field(pos(getRightSpan()), nf.Super(pos(getRhsFirstTokenIndex(3)), ClassName.toType()),
                                      nf.Id(pos(getRightSpan()), "*")));
                      break;
            }
    
            //
            // Rule 10:  MethodInvocation ::= MethodPrimaryPrefix ( ArgumentListopt )
            //
            case 10: {
               //#line 87 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 85 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                Object MethodPrimaryPrefix = (Object) getRhsSym(1);
                //#line 85 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                List ArgumentListopt = (List) getRhsSym(3);
                //#line 87 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                Expr Primary = (Expr) ((Object[]) MethodPrimaryPrefix)[0];
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) ((Object[]) MethodPrimaryPrefix)[1];
                setResult(nf.Call(pos(), Primary, nf.Id(pos(), identifier.getIdentifier()), ArgumentListopt));
                      break;
            }
    
            //
            // Rule 11:  MethodInvocation ::= MethodSuperPrefix ( ArgumentListopt )
            //
            case 11: {
               //#line 94 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 92 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                polyglot.lex.Identifier MethodSuperPrefix = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 92 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                List ArgumentListopt = (List) getRhsSym(3);
                //#line 94 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                polyglot.lex.Identifier identifier = MethodSuperPrefix;
                setResult(nf.Call(pos(), nf.Super(pos(getLeftSpan())), nf.Id(pos(), identifier.getIdentifier()), ArgumentListopt));
                      break;
            }
    
            //
            // Rule 12:  MethodInvocation ::= MethodClassNameSuperPrefix ( ArgumentListopt )
            //
            case 12: {
               //#line 100 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 98 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                Object MethodClassNameSuperPrefix = (Object) getRhsSym(1);
                //#line 98 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                List ArgumentListopt = (List) getRhsSym(3);
                //#line 100 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                ParsedName ClassName = (ParsedName) ((Object[]) MethodClassNameSuperPrefix)[0];
                JPGPosition super_pos = (JPGPosition) ((Object[]) MethodClassNameSuperPrefix)[1];
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) ((Object[]) MethodClassNameSuperPrefix)[2];
                setResult(nf.Call(pos(), nf.Super(super_pos, ClassName.toType()), nf.Id(pos(), identifier.getIdentifier()), ArgumentListopt));
                      break;
            }
    
            //
            // Rule 13:  MethodPrimaryPrefix ::= Primary . ErrorId$ErrorId
            //
            case 13: {
               //#line 109 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 107 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 107 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                IToken ErrorId = (IToken) getRhsIToken(3);
                //#line 109 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                Object[] a = new Object[2];
                a[0] = Primary;
                a[1] = id(getRhsFirstTokenIndex(3));
                setResult(a);
                      break;
            }
    
            //
            // Rule 14:  MethodSuperPrefix ::= super . ErrorId$ErrorId
            //
            case 14: {
               //#line 117 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 115 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                IToken ErrorId = (IToken) getRhsIToken(3);
                //#line 117 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(id(getRhsFirstTokenIndex(3)));
                      break;
            }
    
            //
            // Rule 15:  MethodClassNameSuperPrefix ::= ClassName . super$sup . ErrorId$ErrorId
            //
            case 15: {
               //#line 122 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 120 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                ParsedName ClassName = (ParsedName) getRhsSym(1);
                //#line 120 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                IToken sup = (IToken) getRhsIToken(3);
                //#line 120 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/MissingId.gi"
                IToken ErrorId = (IToken) getRhsIToken(5);
                //#line 122 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                Object[] a = new Object[3];
                a[0] = ClassName;
                a[1] = pos(getRhsFirstTokenIndex(3));
                a[2] = id(getRhsFirstTokenIndex(5));
                setResult(a);
                      break;
            }
    
            //
            // Rule 16:  TypeDefDeclaration ::= TypeDefModifiersopt type Identifier TypeParametersopt FormalParametersopt WhereClauseopt = Type ;
            //
            case 16: {
               //#line 877 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 875 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeDefModifiersopt = (List) getRhsSym(1);
                //#line 875 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 875 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeParametersopt = (List) getRhsSym(4);
                //#line 875 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalParametersopt = (List) getRhsSym(5);
                //#line 875 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                DepParameterExpr WhereClauseopt = (DepParameterExpr) getRhsSym(6);
                //#line 875 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(8);
                //#line 877 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                FlagsNode f = extractFlags(TypeDefModifiersopt);
                List annotations = extractAnnotations(TypeDefModifiersopt);
                for (Formal v : (List<Formal>) FormalParametersopt) {
                    if (!v.flags().flags().isFinal()) throw new InternalCompilerError("Type definition parameters must be final.", v.position()); 
                }
                TypeDecl cd = nf.TypeDecl(pos(), f, Identifier, TypeParametersopt, FormalParametersopt, WhereClauseopt, Type);
                cd = (TypeDecl) ((X10Ext) cd.ext()).annotations(annotations);
                setResult(cd);
                      break;
            }
    
            //
            // Rule 17:  TypeDefDeclaration ::= TypeDefModifiersopt type Identifier TypeParametersopt FormalParametersopt WhereClauseopt ;
            //
            case 17: {
               //#line 889 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 887 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeDefModifiersopt = (List) getRhsSym(1);
                //#line 887 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 887 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeParametersopt = (List) getRhsSym(4);
                //#line 887 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalParametersopt = (List) getRhsSym(5);
                //#line 887 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                DepParameterExpr WhereClauseopt = (DepParameterExpr) getRhsSym(6);
                //#line 889 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                FlagsNode f = extractFlags(TypeDefModifiersopt);
                List annotations = extractAnnotations(TypeDefModifiersopt);
                for (Formal v : (List<Formal>) FormalParametersopt) {
                    if (!v.flags().flags().isFinal()) throw new InternalCompilerError("Type definition parameters must be final.", v.position()); 
                }
                TypeDecl cd = nf.TypeDecl(pos(), f, Identifier, TypeParametersopt, FormalParametersopt, WhereClauseopt, null);
                cd = (TypeDecl) ((X10Ext) cd.ext()).annotations(annotations);
                setResult(cd);
                      break;
            }
    
            //
            // Rule 18:  Properties ::= ( PropertyList )
            //
            case 18: {
               //#line 902 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 900 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List PropertyList = (List) getRhsSym(2);
                //#line 902 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
   setResult(PropertyList);
                 break;
            } 
            //
            // Rule 19:  PropertyList ::= Property
            //
            case 19: {
               //#line 907 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 905 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                PropertyDecl Property = (PropertyDecl) getRhsSym(1);
                //#line 907 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), PropertyDecl.class, false);
                l.add(Property);
                setResult(l);
                      break;
            }
    
            //
            // Rule 20:  PropertyList ::= PropertyList , Property
            //
            case 20: {
               //#line 914 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 912 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List PropertyList = (List) getRhsSym(1);
                //#line 912 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                PropertyDecl Property = (PropertyDecl) getRhsSym(3);
                //#line 914 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                PropertyList.add(Property);
                      break;
            }
    
            //
            // Rule 21:  Property ::= Annotationsopt Identifier : Type
            //
            case 21: {
               //#line 921 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 919 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Annotationsopt = (List) getRhsSym(1);
                //#line 919 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(2);
                //#line 919 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(4);
                //#line 921 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List annotations = extractAnnotations(Annotationsopt);
                PropertyDecl cd = nf.PropertyDecl(pos(), nf.FlagsNode(pos(), Flags.PUBLIC.Final()), Type, Identifier);
                cd = (PropertyDecl) ((X10Ext) cd.ext()).annotations(annotations);
                setResult(cd);
                      break;
            }
    
            //
            // Rule 22:  MethodDeclaration ::= MethodModifiersopt def Identifier TypeParametersopt FormalParameters WhereClauseopt ResultTypeopt Throwsopt MethodBody
            //
            case 22: {
               //#line 930 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 928 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List MethodModifiersopt = (List) getRhsSym(1);
                //#line 928 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 928 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeParametersopt = (List) getRhsSym(4);
                //#line 928 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalParameters = (List) getRhsSym(5);
                //#line 928 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                DepParameterExpr WhereClauseopt = (DepParameterExpr) getRhsSym(6);
                //#line 928 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ResultTypeopt = (TypeNode) getRhsSym(7);
                //#line 928 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Throwsopt = (List) getRhsSym(8);
                //#line 928 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block MethodBody = (Block) getRhsSym(9);
                //#line 930 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
       if (Identifier.id().toString().equals("this")) {
                   ConstructorDecl cd = nf.X10ConstructorDecl(pos(),
                                             extractFlags(MethodModifiersopt),
                                             nf.Id(pos(3), "this"),
                                             ResultTypeopt,
                                             TypeParametersopt,
                                             FormalParameters,
                                             WhereClauseopt,
                                             Throwsopt,
                                             MethodBody);
     cd = (ConstructorDecl) ((X10Ext) cd.ext()).annotations(extractAnnotations(MethodModifiersopt));
     setResult(cd);
          }
          else {
       MethodDecl md = nf.X10MethodDecl(pos(getRhsFirstTokenIndex(1), getRhsLastTokenIndex(9)),
          extractFlags(MethodModifiersopt),
          ResultTypeopt == null ? nf.UnknownTypeNode(pos()) : ResultTypeopt,
          Identifier,
          TypeParametersopt,
          FormalParameters,
          WhereClauseopt,
          Throwsopt,
          MethodBody);
      md = (MethodDecl) ((X10Ext) md.ext()).annotations(extractAnnotations(MethodModifiersopt));
      setResult(md);
      }
                      break;
            }
    
            //
            // Rule 23:  PropertyMethodDeclaration ::= MethodModifiersopt property Identifier TypeParametersopt FormalParameters WhereClauseopt ResultTypeopt Throwsopt MethodBody
            //
            case 23: {
               //#line 961 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 959 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List MethodModifiersopt = (List) getRhsSym(1);
                //#line 959 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 959 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeParametersopt = (List) getRhsSym(4);
                //#line 959 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalParameters = (List) getRhsSym(5);
                //#line 959 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                DepParameterExpr WhereClauseopt = (DepParameterExpr) getRhsSym(6);
                //#line 959 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ResultTypeopt = (TypeNode) getRhsSym(7);
                //#line 959 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Throwsopt = (List) getRhsSym(8);
                //#line 959 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block MethodBody = (Block) getRhsSym(9);
                //#line 961 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
       MethodDecl md = nf.X10MethodDecl(pos(getRhsFirstTokenIndex(1), getRhsLastTokenIndex(9)),
          extractFlags(MethodModifiersopt, X10Flags.PROPERTY),
          ResultTypeopt == null ? nf.UnknownTypeNode(pos()) : ResultTypeopt,
          Identifier,
          TypeParametersopt,
          FormalParameters,
          WhereClauseopt,
          Throwsopt,
          MethodBody);
      md = (MethodDecl) ((X10Ext) md.ext()).annotations(extractAnnotations(MethodModifiersopt));
      setResult(md);
                      break;
            }
    
            //
            // Rule 24:  PropertyMethodDeclaration ::= MethodModifiersopt property Identifier WhereClauseopt ResultTypeopt MethodBody
            //
            case 24: {
               //#line 976 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 974 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List MethodModifiersopt = (List) getRhsSym(1);
                //#line 974 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 974 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                DepParameterExpr WhereClauseopt = (DepParameterExpr) getRhsSym(4);
                //#line 974 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ResultTypeopt = (TypeNode) getRhsSym(5);
                //#line 974 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block MethodBody = (Block) getRhsSym(6);
                //#line 976 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
       MethodDecl md = nf.X10MethodDecl(pos(getRhsFirstTokenIndex(1), getRhsLastTokenIndex(6)),
          extractFlags(MethodModifiersopt, X10Flags.PROPERTY),
          ResultTypeopt == null ? nf.UnknownTypeNode(pos()) : ResultTypeopt,
          Identifier,
          Collections.EMPTY_LIST,
          Collections.EMPTY_LIST,
          WhereClauseopt,
          Collections.EMPTY_LIST,
          MethodBody);
      md = (MethodDecl) ((X10Ext) md.ext()).annotations(extractAnnotations(MethodModifiersopt));
      setResult(md);
                      break;
            }
    
            //
            // Rule 25:  ExplicitConstructorInvocation ::= this TypeArgumentsopt ( ArgumentListopt ) ;
            //
            case 25: {
               //#line 992 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 990 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(2);
                //#line 990 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(4);
                //#line 992 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.X10ThisCall(pos(), TypeArgumentsopt, ArgumentListopt));
                      break;
            }
    
            //
            // Rule 26:  ExplicitConstructorInvocation ::= super TypeArgumentsopt ( ArgumentListopt ) ;
            //
            case 26: {
               //#line 997 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 995 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(2);
                //#line 995 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(4);
                //#line 997 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.X10SuperCall(pos(), TypeArgumentsopt, ArgumentListopt));
                      break;
            }
    
            //
            // Rule 27:  ExplicitConstructorInvocation ::= Primary . this TypeArgumentsopt ( ArgumentListopt ) ;
            //
            case 27: {
               //#line 1002 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1000 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 1000 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(4);
                //#line 1000 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(6);
                //#line 1002 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.X10ThisCall(pos(), Primary, TypeArgumentsopt, ArgumentListopt));
                      break;
            }
    
            //
            // Rule 28:  ExplicitConstructorInvocation ::= Primary . super TypeArgumentsopt ( ArgumentListopt ) ;
            //
            case 28: {
               //#line 1007 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1005 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 1005 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(4);
                //#line 1005 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(6);
                //#line 1007 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.X10SuperCall(pos(), Primary, TypeArgumentsopt, ArgumentListopt));
                      break;
            }
    
            //
            // Rule 29:  NormalInterfaceDeclaration ::= InterfaceModifiersopt interface Identifier TypePropertiesopt Propertiesopt WhereClauseopt ExtendsInterfacesopt InterfaceBody
            //
            case 29: {
               //#line 1013 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1011 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List InterfaceModifiersopt = (List) getRhsSym(1);
                //#line 1011 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 1011 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypePropertiesopt = (List) getRhsSym(4);
                //#line 1011 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Propertiesopt = (List) getRhsSym(5);
                //#line 1011 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                DepParameterExpr WhereClauseopt = (DepParameterExpr) getRhsSym(6);
                //#line 1011 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ExtendsInterfacesopt = (List) getRhsSym(7);
                //#line 1011 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ClassBody InterfaceBody = (ClassBody) getRhsSym(8);
                //#line 1013 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
      checkTypeName(Identifier);
      List TypeParametersopt = Collections.EMPTY_LIST;
      List/*<PropertyDecl>*/ props = Propertiesopt;
      DepParameterExpr ci = WhereClauseopt;
      FlagsNode fn = extractFlags(InterfaceModifiersopt, Flags.INTERFACE);
      ClassDecl cd = nf.X10ClassDecl(pos(),
                   fn,
                   Identifier,
                   TypeParametersopt,
                   TypePropertiesopt,
                   props,
                   ci,
                   null,
                   ExtendsInterfacesopt,
                   InterfaceBody);
      cd = (ClassDecl) ((X10Ext) cd.ext()).annotations(extractAnnotations(InterfaceModifiersopt));
      setResult(cd);
                      break;
            }
    
            //
            // Rule 30:  ClassInstanceCreationExpression ::= new TypeName TypeArgumentsopt ( ArgumentListopt ) ClassBodyopt
            //
            case 30: {
               //#line 1035 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1033 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(2);
                //#line 1033 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(3);
                //#line 1033 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(5);
                //#line 1033 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ClassBody ClassBodyopt = (ClassBody) getRhsSym(7);
                //#line 1035 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                if (ClassBodyopt == null)
                     setResult(nf.X10New(pos(), TypeName.toType(), TypeArgumentsopt, ArgumentListopt));
                else setResult(nf.X10New(pos(), TypeName.toType(), TypeArgumentsopt, ArgumentListopt, ClassBodyopt));
                      break;
            }
    
            //
            // Rule 31:  ClassInstanceCreationExpression ::= Primary . new Identifier TypeArgumentsopt ( ArgumentListopt ) ClassBodyopt
            //
            case 31: {
               //#line 1042 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1040 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 1040 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(4);
                //#line 1040 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(5);
                //#line 1040 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(7);
                //#line 1040 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ClassBody ClassBodyopt = (ClassBody) getRhsSym(9);
                //#line 1042 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                ParsedName b = new X10ParsedName(nf, ts, pos(), Identifier);
                if (ClassBodyopt == null)
                     setResult(nf.X10New(pos(), Primary, b.toType(), TypeArgumentsopt, ArgumentListopt));
                else setResult(nf.X10New(pos(), Primary, b.toType(), TypeArgumentsopt, ArgumentListopt, ClassBodyopt));
                      break;
            }
    
            //
            // Rule 32:  ClassInstanceCreationExpression ::= AmbiguousName . new Identifier TypeArgumentsopt ( ArgumentListopt ) ClassBodyopt
            //
            case 32: {
               //#line 1050 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1048 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName AmbiguousName = (ParsedName) getRhsSym(1);
                //#line 1048 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(4);
                //#line 1048 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(5);
                //#line 1048 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(7);
                //#line 1048 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ClassBody ClassBodyopt = (ClassBody) getRhsSym(9);
                //#line 1050 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                ParsedName b = new X10ParsedName(nf, ts, pos(), Identifier);
                if (ClassBodyopt == null)
                     setResult(nf.X10New(pos(), AmbiguousName.toExpr(), b.toType(), TypeArgumentsopt, ArgumentListopt));
                else setResult(nf.X10New(pos(), AmbiguousName.toExpr(), b.toType(), TypeArgumentsopt, ArgumentListopt, ClassBodyopt));
                      break;
            }
    
            //
            // Rule 33:  AssignPropertyCall ::= property TypeArgumentsopt ( ArgumentListopt ) ;
            //
            case 33: {
               //#line 1059 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1057 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(2);
                //#line 1057 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(4);
                //#line 1059 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.AssignPropertyCall(pos(), TypeArgumentsopt, ArgumentListopt));
                      break;
            }
    
            //
            // Rule 36:  FunctionType ::= TypeArgumentsopt ( FormalParameterListopt ) WhereClauseopt Throwsopt => Type
            //
            case 36: {
               //#line 1071 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1069 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(1);
                //#line 1069 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalParameterListopt = (List) getRhsSym(3);
                //#line 1069 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                DepParameterExpr WhereClauseopt = (DepParameterExpr) getRhsSym(5);
                //#line 1069 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Throwsopt = (List) getRhsSym(6);
                //#line 1069 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(8);
                //#line 1071 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.FunctionTypeNode(pos(), TypeArgumentsopt, FormalParameterListopt, WhereClauseopt, Type, Throwsopt));
                      break;
            }
    
            //
            // Rule 41:  AnnotatedType ::= Type Annotations
            //
            case 41: {
               //#line 1080 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1078 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(1);
                //#line 1078 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Annotations = (List) getRhsSym(2);
                //#line 1080 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                TypeNode tn = Type;
                tn = (TypeNode) ((X10Ext) tn.ext()).annotations((List<AnnotationNode>) Annotations);
                setResult(tn);
                      break;
            }
    
            //
            // Rule 45:  ConstrainedType ::= ( Type )
            //
            case 45: {
               //#line 1091 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1089 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(2);
                //#line 1091 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Type);
                      break;
            }
    
            //
            // Rule 46:  PlaceType ::= any
            //
            case 46: {
               //#line 1097 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 1097 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(null);
                      break;
            }
    
            //
            // Rule 47:  PlaceType ::= current
            //
            case 47: {
               //#line 1102 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 1102 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(),
                                    nf.Field(pos(), nf.This(pos()), nf.Id(pos(), "loc")), Binary.EQ,
                                    nf.Field(pos(), nf.Self(pos()), nf.Id(pos(), "$current"))));
                      break;
            }
    
            //
            // Rule 48:  PlaceType ::= PlaceExpression
            //
            case 48: {
               //#line 1109 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1107 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr PlaceExpression = (Expr) getRhsSym(1);
                //#line 1109 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(),
                                    nf.Field(pos(), nf.This(pos()), nf.Id(pos(), "loc")), Binary.EQ,
                                    PlaceExpression));
                      break;
            }
    
            //
            // Rule 49:  NamedType ::= Primary . Identifier TypeArgumentsopt Argumentsopt DepParametersopt
            //
            case 49: {
               //#line 1117 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1115 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 1115 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 1115 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(4);
                //#line 1115 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Argumentsopt = (List) getRhsSym(5);
                //#line 1115 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                DepParameterExpr DepParametersopt = (DepParameterExpr) getRhsSym(6);
                //#line 1117 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
            TypeNode type = nf.X10AmbTypeNode(pos(), Primary, Identifier);
            // TODO: place constraint
            if (DepParametersopt != null || (TypeArgumentsopt != null && ! TypeArgumentsopt.isEmpty()) || (Argumentsopt != null && ! Argumentsopt.isEmpty())) {
                type = nf.AmbDepTypeNode(pos(), Primary, Identifier, TypeArgumentsopt, Argumentsopt, DepParametersopt);
            }
            setResult(type);
                      break;
            }
    
            //
            // Rule 50:  NamedType ::= TypeName TypeArgumentsopt Argumentsopt DepParametersopt
            //
            case 50: {
               //#line 1128 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1126 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 1126 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(2);
                //#line 1126 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Argumentsopt = (List) getRhsSym(3);
                //#line 1126 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                DepParameterExpr DepParametersopt = (DepParameterExpr) getRhsSym(4);
                //#line 1128 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
            TypeNode type;
            
            if (TypeName.name.id().toString().equals("void")) {
                type = nf.CanonicalTypeNode(pos(), ts.Void());
            } else
            if (ts.isPrimitiveTypeName(TypeName.name.id())) {
                try {
                    type = nf.CanonicalTypeNode(pos(), ts.primitiveForName(TypeName.name.id()));
                }
                catch (SemanticException e) {
                    throw new InternalCompilerError("Unable to create primitive type for '" + TypeName.name.id() + "'!");
                }
            } else {
                type = TypeName.toType();
            }
            // TODO: place constraint
            if (DepParametersopt != null || (TypeArgumentsopt != null && ! TypeArgumentsopt.isEmpty()) || (Argumentsopt != null && ! Argumentsopt.isEmpty())) {
                type = nf.AmbDepTypeNode(pos(), TypeName.prefix != null ? TypeName.prefix.toPrefix() : null, TypeName.name, TypeArgumentsopt, Argumentsopt, DepParametersopt);
            }
            setResult(type);
                      break;
            }
    
            //
            // Rule 51:  DepParameters ::= { ExistentialListopt Conjunction }
            //
            case 51: {
               //#line 1154 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1152 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ExistentialListopt = (List) getRhsSym(2);
                //#line 1152 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Conjunction = (Expr) getRhsSym(3);
                //#line 1154 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.DepParameterExpr(pos(), ExistentialListopt, Conjunction));
                      break;
            }
    
            //
            // Rule 52:  DepParameters ::= { ExistentialListopt Conjunction } !
            //
            case 52: {
               //#line 1159 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1157 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ExistentialListopt = (List) getRhsSym(2);
                //#line 1157 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Conjunction = (Expr) getRhsSym(3);
                //#line 1159 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.DepParameterExpr(pos(), ExistentialListopt, Conjunction));
                      break;
            }
    
            //
            // Rule 53:  DepParameters ::= { ExistentialListopt Conjunction } ! PlaceType
            //
            case 53: {
               //#line 1164 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1162 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ExistentialListopt = (List) getRhsSym(2);
                //#line 1162 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Conjunction = (Expr) getRhsSym(3);
                //#line 1162 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr PlaceType = (Expr) getRhsSym(6);
                //#line 1164 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                if (PlaceType != null)
                    setResult(nf.DepParameterExpr(pos(), ExistentialListopt, nf.Binary(pos(), Conjunction, Binary.COND_AND, PlaceType)));
            else
		setResult(nf.DepParameterExpr(pos(), ExistentialListopt, Conjunction));
                      break;
            }
    
            //
            // Rule 54:  TypeProperties ::= [ TypePropertyList ]
            //
            case 54: {
               //#line 1173 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1171 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypePropertyList = (List) getRhsSym(2);
                //#line 1173 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(TypePropertyList);
                      break;
            }
    
            //
            // Rule 55:  TypeParameters ::= [ TypeParameterList ]
            //
            case 55: {
               //#line 1179 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1177 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeParameterList = (List) getRhsSym(2);
                //#line 1179 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(TypeParameterList);
                      break;
            }
    
            //
            // Rule 56:  FormalParameters ::= ( FormalParameterListopt )
            //
            case 56: {
               //#line 1184 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1182 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalParameterListopt = (List) getRhsSym(2);
                //#line 1184 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(FormalParameterListopt);
                      break;
            }
    
            //
            // Rule 57:  Conjunction ::= Expression
            //
            case 57: {
               //#line 1190 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1188 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(1);
                //#line 1190 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Expression);
                      break;
            }
    
            //
            // Rule 58:  Conjunction ::= Conjunction , Expression
            //
            case 58: {
               //#line 1195 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1193 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Conjunction = (Expr) getRhsSym(1);
                //#line 1193 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1195 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), Conjunction, Binary.COND_AND, Expression));
                      break;
            }
    
            //
            // Rule 59:  SubtypeConstraint ::= Type$t1 <: Type$t2
            //
            case 59: {
               //#line 1201 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1199 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode t1 = (TypeNode) getRhsSym(1);
                //#line 1199 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode t2 = (TypeNode) getRhsSym(3);
                //#line 1201 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.SubtypeTest(pos(), t1, t2, false));
                      break;
            }
    
            //
            // Rule 60:  SubtypeConstraint ::= Type$t1 :> Type$t2
            //
            case 60: {
               //#line 1206 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1204 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode t1 = (TypeNode) getRhsSym(1);
                //#line 1204 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode t2 = (TypeNode) getRhsSym(3);
                //#line 1206 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.SubtypeTest(pos(), t2, t1, false));
                      break;
            }
    
            //
            // Rule 61:  WhereClause ::= DepParameters
            //
            case 61: {
               //#line 1212 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1210 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                DepParameterExpr DepParameters = (DepParameterExpr) getRhsSym(1);
                //#line 1212 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
            setResult(DepParameters);
                      break;
            }
      
            //
            // Rule 62:  ExistentialListopt ::= $Empty
            //
            case 62: {
               //#line 1218 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 1218 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
            setResult(new ArrayList());
                      break;
            }
      
            //
            // Rule 63:  ExistentialListopt ::= ExistentialList ;
            //
            case 63: {
               //#line 1223 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1221 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ExistentialList = (List) getRhsSym(1);
                //#line 1223 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
            setResult(ExistentialList);
                      break;
            }
    
            //
            // Rule 64:  ExistentialList ::= FormalParameter
            //
            case 64: {
               //#line 1229 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1227 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                X10Formal FormalParameter = (X10Formal) getRhsSym(1);
                //#line 1229 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Formal.class, false);
                l.add(FormalParameter.flags(nf.FlagsNode(pos(), Flags.FINAL)));
                setResult(l);
                      break;
            }
    
            //
            // Rule 65:  ExistentialList ::= ExistentialList ; FormalParameter
            //
            case 65: {
               //#line 1236 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1234 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ExistentialList = (List) getRhsSym(1);
                //#line 1234 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                X10Formal FormalParameter = (X10Formal) getRhsSym(3);
                //#line 1236 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                ExistentialList.add(FormalParameter.flags(nf.FlagsNode(pos(), Flags.FINAL)));
                      break;
            }
    
            //
            // Rule 68:  NormalClassDeclaration ::= ClassModifiersopt class Identifier TypePropertiesopt Propertiesopt WhereClauseopt Superopt Interfacesopt ClassBody
            //
            case 68: {
               //#line 1247 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1245 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ClassModifiersopt = (List) getRhsSym(1);
                //#line 1245 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 1245 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypePropertiesopt = (List) getRhsSym(4);
                //#line 1245 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Propertiesopt = (List) getRhsSym(5);
                //#line 1245 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                DepParameterExpr WhereClauseopt = (DepParameterExpr) getRhsSym(6);
                //#line 1245 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Superopt = (TypeNode) getRhsSym(7);
                //#line 1245 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Interfacesopt = (List) getRhsSym(8);
                //#line 1245 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ClassBody ClassBody = (ClassBody) getRhsSym(9);
                //#line 1247 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
      checkTypeName(Identifier);
                List TypeParametersopt = Collections.EMPTY_LIST;
      List/*<PropertyDecl>*/ props = Propertiesopt;
      DepParameterExpr ci = WhereClauseopt;
      FlagsNode f = extractFlags(ClassModifiersopt);
      List annotations = extractAnnotations(ClassModifiersopt);
      ClassDecl cd = nf.X10ClassDecl(pos(),
              f, Identifier, TypeParametersopt, TypePropertiesopt, props, ci, Superopt, Interfacesopt, ClassBody);
      cd = (ClassDecl) ((X10Ext) cd.ext()).annotations(annotations);
      setResult(cd);
                      break;
            }
    
            //
            // Rule 69:  ValueClassDeclaration ::= ClassModifiersopt value Identifier TypePropertiesopt Propertiesopt WhereClauseopt Superopt Interfacesopt ClassBody
            //
            case 69: {
               //#line 1262 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1260 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ClassModifiersopt = (List) getRhsSym(1);
                //#line 1260 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 1260 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypePropertiesopt = (List) getRhsSym(4);
                //#line 1260 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Propertiesopt = (List) getRhsSym(5);
                //#line 1260 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                DepParameterExpr WhereClauseopt = (DepParameterExpr) getRhsSym(6);
                //#line 1260 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Superopt = (TypeNode) getRhsSym(7);
                //#line 1260 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Interfacesopt = (List) getRhsSym(8);
                //#line 1260 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ClassBody ClassBody = (ClassBody) getRhsSym(9);
                //#line 1262 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
    checkTypeName(Identifier);
                List TypeParametersopt = Collections.EMPTY_LIST;
    List props = Propertiesopt;
    DepParameterExpr ci = WhereClauseopt;
    ClassDecl cd = (nf.X10ClassDecl(pos(getLeftSpan(), getRightSpan()),
    extractFlags(ClassModifiersopt, X10Flags.VALUE), Identifier,  TypeParametersopt,
    TypePropertiesopt, props, ci, Superopt, Interfacesopt, ClassBody));
    cd = (ClassDecl) ((X10Ext) cd.ext()).annotations(extractAnnotations(ClassModifiersopt));
    setResult(cd);
                      break;
            }
    
            //
            // Rule 70:  ConstructorDeclaration ::= ConstructorModifiersopt def this TypeParametersopt FormalParameters WhereClauseopt ResultTypeopt Throwsopt ConstructorBody
            //
            case 70: {
               //#line 1276 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1274 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ConstructorModifiersopt = (List) getRhsSym(1);
                //#line 1274 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeParametersopt = (List) getRhsSym(4);
                //#line 1274 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalParameters = (List) getRhsSym(5);
                //#line 1274 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                DepParameterExpr WhereClauseopt = (DepParameterExpr) getRhsSym(6);
                //#line 1274 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ResultTypeopt = (TypeNode) getRhsSym(7);
                //#line 1274 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Throwsopt = (List) getRhsSym(8);
                //#line 1274 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block ConstructorBody = (Block) getRhsSym(9);
                //#line 1276 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
     ConstructorDecl cd = nf.X10ConstructorDecl(pos(),
                                             extractFlags(ConstructorModifiersopt),
                                             nf.Id(pos(3), "this"),
                                             ResultTypeopt,
                                             TypeParametersopt,
                                             FormalParameters,
                                             WhereClauseopt,
                                             Throwsopt,
                                             ConstructorBody);
     cd = (ConstructorDecl) ((X10Ext) cd.ext()).annotations(extractAnnotations(ConstructorModifiersopt));
     setResult(cd);
                     break;
            }
   
            //
            // Rule 71:  Super ::= extends ClassType
            //
            case 71: {
               //#line 1292 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1290 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ClassType = (TypeNode) getRhsSym(2);
                //#line 1292 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(ClassType);
                      break;
            }
    
            //
            // Rule 72:  FieldKeyword ::= val
            //
            case 72: {
               //#line 1298 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 1298 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.FINAL)));
                      break;
            }
    
            //
            // Rule 73:  FieldKeyword ::= var
            //
            case 73: {
               //#line 1303 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 1303 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.NONE)));
                      break;
            }
    
            //
            // Rule 74:  FieldKeyword ::= const
            //
            case 74: {
               //#line 1308 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 1308 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.FINAL.Static())));
                      break;
            }
    
            //
            // Rule 75:  VarKeyword ::= val
            //
            case 75: {
               //#line 1316 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 1316 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.FINAL)));
                      break;
            }
    
            //
            // Rule 76:  VarKeyword ::= var
            //
            case 76: {
               //#line 1321 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 1321 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.NONE)));
                      break;
            }
    
            //
            // Rule 77:  FieldDeclaration ::= FieldModifiersopt FieldKeyword FieldDeclarators ;
            //
            case 77: {
               //#line 1328 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1326 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FieldModifiersopt = (List) getRhsSym(1);
                //#line 1326 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FieldKeyword = (List) getRhsSym(2);
                //#line 1326 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FieldDeclarators = (List) getRhsSym(3);
                //#line 1328 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                    FlagsNode fn = extractFlags(FieldModifiersopt, FieldKeyword);
    
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                    for (Iterator i = FieldDeclarators.iterator(); i.hasNext(); )
                    {
                        Object[] o = (Object[]) i.next();
                        Position pos = (Position) o[0];
                        Id name = (Id) o[1];
                        if (name == null) name = nf.Id(pos, Name.makeFresh());
                        List exploded = (List) o[2];
                        TypeNode type = (TypeNode) o[3];
                        if (type == null) type = nf.UnknownTypeNode(name.position());
                        Expr init = (Expr) o[4];
                        FieldDecl ld = nf.FieldDecl(pos, fn,
                                           type, name, init);
                        ld = (FieldDecl) ((X10Ext) ld.ext()).annotations(extractAnnotations(FieldModifiersopt));
                        l.add(ld);
                    }
                setResult(l);
                      break;
            }
    
            //
            // Rule 78:  FieldDeclaration ::= FieldModifiersopt FieldDeclarators ;
            //
            case 78: {
               //#line 1353 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1351 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FieldModifiersopt = (List) getRhsSym(1);
                //#line 1351 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FieldDeclarators = (List) getRhsSym(2);
                //#line 1353 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                    List FieldKeyword = Collections.singletonList(nf.FlagsNode(pos(), Flags.FINAL));
                    FlagsNode fn = extractFlags(FieldModifiersopt, FieldKeyword);
    
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                    for (Iterator i = FieldDeclarators.iterator(); i.hasNext(); )
                    {
                        Object[] o = (Object[]) i.next();
                        Position pos = (Position) o[0];
                        Id name = (Id) o[1];
                        if (name == null) name = nf.Id(pos, Name.makeFresh());
                        List exploded = (List) o[2];
                        TypeNode type = (TypeNode) o[3];
                        if (type == null) type = nf.UnknownTypeNode(name.position());
                        Expr init = (Expr) o[4];
                        FieldDecl ld = nf.FieldDecl(pos, fn,
                                           type, name, init);
                        ld = (FieldDecl) ((X10Ext) ld.ext()).annotations(extractAnnotations(FieldModifiersopt));
                        l.add(ld);
                    }
                setResult(l);
                      break;
            }
    
            //
            // Rule 109:  IfThenStatement ::= if ( Expression ) Statement
            //
            case 109: {
               //#line 1412 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1410 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1410 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(5);
                //#line 1412 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.If(pos(), Expression, Statement));
                      break;
            }
    
            //
            // Rule 110:  IfThenElseStatement ::= if ( Expression ) Statement$s1 else Statement$s2
            //
            case 110: {
               //#line 1418 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1416 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1416 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt s1 = (Stmt) getRhsSym(5);
                //#line 1416 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt s2 = (Stmt) getRhsSym(7);
                //#line 1418 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.If(pos(), Expression, s1, s2));
                      break;
            }
    
            //
            // Rule 111:  EmptyStatement ::= ;
            //
            case 111: {
               //#line 1424 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 1424 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Empty(pos()));
                      break;
            }
    
            //
            // Rule 112:  LabeledStatement ::= Identifier : LoopStatement
            //
            case 112: {
               //#line 1430 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1428 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 1428 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt LoopStatement = (Stmt) getRhsSym(3);
                //#line 1430 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Labeled(pos(), Identifier, LoopStatement));
                      break;
            }
    
            //
            // Rule 118:  ExpressionStatement ::= StatementExpression ;
            //
            case 118: {
               //#line 1442 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1440 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr StatementExpression = (Expr) getRhsSym(1);
                //#line 1442 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                boolean eval = true;
                if (StatementExpression instanceof X10Call) {
                    X10Call c = (X10Call) StatementExpression;
                    if (c.name().id().toString().equals("property") && c.target() == null) {
                        setResult(nf.AssignPropertyCall(c.position(),c.typeArguments(), c.arguments()));
                        eval = false;
                    }
                    if (c.name().id().toString().equals("super") && c.target() instanceof Expr) {
                        setResult(nf.X10SuperCall(c.position(), (Expr) c.target(), c.typeArguments(), c.arguments()));
                        eval = false;
                   }
                   if (c.name().id().toString().equals("this") && c.target() instanceof Expr) {
                        setResult(nf.X10ThisCall(c.position(), (Expr) c.target(), c.typeArguments(), c.arguments()));
                        eval = false;
                   }
                }
                    
                setResult(nf.Eval(pos(), StatementExpression));
                      break;
            }
    
            //
            // Rule 126:  AssertStatement ::= assert Expression ;
            //
            case 126: {
               //#line 1473 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1471 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(2);
                //#line 1473 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Assert(pos(), Expression));
                      break;
            }
    
            //
            // Rule 127:  AssertStatement ::= assert Expression$expr1 : Expression$expr2 ;
            //
            case 127: {
               //#line 1478 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1476 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr expr1 = (Expr) getRhsSym(2);
                //#line 1476 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr expr2 = (Expr) getRhsSym(4);
                //#line 1478 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Assert(pos(), expr1, expr2));
                      break;
            }
    
            //
            // Rule 128:  SwitchStatement ::= switch ( Expression ) SwitchBlock
            //
            case 128: {
               //#line 1484 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1482 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1482 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List SwitchBlock = (List) getRhsSym(5);
                //#line 1484 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Switch(pos(), Expression, SwitchBlock));
                      break;
            }
    
            //
            // Rule 129:  SwitchBlock ::= { SwitchBlockStatementGroupsopt SwitchLabelsopt }
            //
            case 129: {
               //#line 1490 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1488 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List SwitchBlockStatementGroupsopt = (List) getRhsSym(2);
                //#line 1488 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List SwitchLabelsopt = (List) getRhsSym(3);
                //#line 1490 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                SwitchBlockStatementGroupsopt.addAll(SwitchLabelsopt);
                setResult(SwitchBlockStatementGroupsopt);
                      break;
            }
    
            //
            // Rule 131:  SwitchBlockStatementGroups ::= SwitchBlockStatementGroups SwitchBlockStatementGroup
            //
            case 131: {
               //#line 1498 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1496 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List SwitchBlockStatementGroups = (List) getRhsSym(1);
                //#line 1496 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List SwitchBlockStatementGroup = (List) getRhsSym(2);
                //#line 1498 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                SwitchBlockStatementGroups.addAll(SwitchBlockStatementGroup);
                // setResult(SwitchBlockStatementGroups);
                      break;
            }
    
            //
            // Rule 132:  SwitchBlockStatementGroup ::= SwitchLabels BlockStatements
            //
            case 132: {
               //#line 1505 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1503 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List SwitchLabels = (List) getRhsSym(1);
                //#line 1503 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List BlockStatements = (List) getRhsSym(2);
                //#line 1505 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), SwitchElement.class, false);
                l.addAll(SwitchLabels);
                l.add(nf.SwitchBlock(pos(), BlockStatements));
                setResult(l);
                      break;
            }
    
            //
            // Rule 133:  SwitchLabels ::= SwitchLabel
            //
            case 133: {
               //#line 1514 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1512 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Case SwitchLabel = (Case) getRhsSym(1);
                //#line 1514 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Case.class, false);
                l.add(SwitchLabel);
                setResult(l);
                      break;
            }
    
            //
            // Rule 134:  SwitchLabels ::= SwitchLabels SwitchLabel
            //
            case 134: {
               //#line 1521 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1519 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List SwitchLabels = (List) getRhsSym(1);
                //#line 1519 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Case SwitchLabel = (Case) getRhsSym(2);
                //#line 1521 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                SwitchLabels.add(SwitchLabel);
                //setResult(SwitchLabels);
                      break;
            }
    
            //
            // Rule 135:  SwitchLabel ::= case ConstantExpression :
            //
            case 135: {
               //#line 1528 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1526 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr ConstantExpression = (Expr) getRhsSym(2);
                //#line 1528 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Case(pos(), ConstantExpression));
                      break;
            }
    
            //
            // Rule 136:  SwitchLabel ::= default :
            //
            case 136: {
               //#line 1533 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 1533 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Default(pos()));
                      break;
            }
    
            //
            // Rule 137:  WhileStatement ::= while ( Expression ) Statement
            //
            case 137: {
               //#line 1539 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1537 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1537 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(5);
                //#line 1539 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.While(pos(), Expression, Statement));
                      break;
            }
    
            //
            // Rule 138:  DoStatement ::= do Statement while ( Expression ) ;
            //
            case 138: {
               //#line 1545 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1543 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(2);
                //#line 1543 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(5);
                //#line 1545 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Do(pos(), Statement, Expression));
                      break;
            }
    
            //
            // Rule 141:  BasicForStatement ::= for ( ForInitopt ; Expressionopt ; ForUpdateopt ) Statement
            //
            case 141: {
               //#line 1554 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1552 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ForInitopt = (List) getRhsSym(3);
                //#line 1552 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expressionopt = (Expr) getRhsSym(5);
                //#line 1552 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ForUpdateopt = (List) getRhsSym(7);
                //#line 1552 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(9);
                //#line 1554 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.For(pos(), ForInitopt, Expressionopt, ForUpdateopt, Statement));
                      break;
            }
    
            //
            // Rule 143:  ForInit ::= LocalVariableDeclaration
            //
            case 143: {
               //#line 1561 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1559 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List LocalVariableDeclaration = (List) getRhsSym(1);
                //#line 1561 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ForInit.class, false);
                l.addAll(LocalVariableDeclaration);
                //setResult(l);
                      break;
            }
    
            //
            // Rule 145:  StatementExpressionList ::= StatementExpression
            //
            case 145: {
               //#line 1571 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1569 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr StatementExpression = (Expr) getRhsSym(1);
                //#line 1571 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Eval.class, false);
                l.add(nf.Eval(pos(), StatementExpression));
                setResult(l);
                      break;
            }
    
            //
            // Rule 146:  StatementExpressionList ::= StatementExpressionList , StatementExpression
            //
            case 146: {
               //#line 1578 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1576 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List StatementExpressionList = (List) getRhsSym(1);
                //#line 1576 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr StatementExpression = (Expr) getRhsSym(3);
                //#line 1578 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                StatementExpressionList.add(nf.Eval(pos(), StatementExpression));
                      break;
            }
    
            //
            // Rule 147:  BreakStatement ::= break Identifieropt ;
            //
            case 147: {
               //#line 1584 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1582 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifieropt = (Id) getRhsSym(2);
                //#line 1584 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Break(pos(), Identifieropt));
                      break;
            }
    
            //
            // Rule 148:  ContinueStatement ::= continue Identifieropt ;
            //
            case 148: {
               //#line 1590 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1588 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifieropt = (Id) getRhsSym(2);
                //#line 1590 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Continue(pos(), Identifieropt));
                      break;
            }
    
            //
            // Rule 149:  ReturnStatement ::= return Expressionopt ;
            //
            case 149: {
               //#line 1596 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1594 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expressionopt = (Expr) getRhsSym(2);
                //#line 1596 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Return(pos(), Expressionopt));
                      break;
            }
    
            //
            // Rule 150:  ThrowStatement ::= throw Expression ;
            //
            case 150: {
               //#line 1602 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1600 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(2);
                //#line 1602 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Throw(pos(), Expression));
                      break;
            }
    
            //
            // Rule 151:  TryStatement ::= try Block Catches
            //
            case 151: {
               //#line 1608 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1606 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block Block = (Block) getRhsSym(2);
                //#line 1606 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Catches = (List) getRhsSym(3);
                //#line 1608 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Try(pos(), Block, Catches));
                      break;
            }
    
            //
            // Rule 152:  TryStatement ::= try Block Catchesopt Finally
            //
            case 152: {
               //#line 1613 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1611 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block Block = (Block) getRhsSym(2);
                //#line 1611 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Catchesopt = (List) getRhsSym(3);
                //#line 1611 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block Finally = (Block) getRhsSym(4);
                //#line 1613 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Try(pos(), Block, Catchesopt, Finally));
                      break;
            }
    
            //
            // Rule 153:  Catches ::= CatchClause
            //
            case 153: {
               //#line 1619 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1617 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Catch CatchClause = (Catch) getRhsSym(1);
                //#line 1619 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Catch.class, false);
                l.add(CatchClause);
                setResult(l);
                      break;
            }
    
            //
            // Rule 154:  Catches ::= Catches CatchClause
            //
            case 154: {
               //#line 1626 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1624 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Catches = (List) getRhsSym(1);
                //#line 1624 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Catch CatchClause = (Catch) getRhsSym(2);
                //#line 1626 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                Catches.add(CatchClause);
                //setResult(Catches);
                      break;
            }
    
            //
            // Rule 155:  CatchClause ::= catch ( FormalParameter ) Block
            //
            case 155: {
               //#line 1633 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1631 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                X10Formal FormalParameter = (X10Formal) getRhsSym(3);
                //#line 1631 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block Block = (Block) getRhsSym(5);
                //#line 1633 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Catch(pos(), FormalParameter, Block));
                      break;
            }
    
            //
            // Rule 156:  Finally ::= finally Block
            //
            case 156: {
               //#line 1639 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1637 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block Block = (Block) getRhsSym(2);
                //#line 1639 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Block);
                      break;
            }
    
            //
            // Rule 157:  NowStatement ::= now ( Clock ) Statement
            //
            case 157: {
               //#line 1645 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1643 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Clock = (Expr) getRhsSym(3);
                //#line 1643 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(5);
                //#line 1645 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
              setResult(nf.Now(pos(), Clock, Statement));
                      break;
            }
    
            //
            // Rule 158:  ClockedClause ::= clocked ( ClockList )
            //
            case 158: {
               //#line 1651 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1649 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ClockList = (List) getRhsSym(3);
                //#line 1651 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(ClockList);
                      break;
            }
    
            //
            // Rule 159:  AsyncStatement ::= async PlaceExpressionSingleListopt ClockedClauseopt Statement
            //
            case 159: {
               //#line 1657 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1655 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr PlaceExpressionSingleListopt = (Expr) getRhsSym(2);
                //#line 1655 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ClockedClauseopt = (List) getRhsSym(3);
                //#line 1655 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(4);
                //#line 1657 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
              setResult(nf.Async(pos(), (PlaceExpressionSingleListopt == null
                                                                        ? nf.Here(pos(getLeftSpan()))
                                                                        : PlaceExpressionSingleListopt),
                                         ClockedClauseopt, Statement));
                      break;
            }
    
            //
            // Rule 160:  AtStatement ::= at PlaceExpressionSingleList Statement
            //
            case 160: {
               //#line 1666 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1664 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr PlaceExpressionSingleList = (Expr) getRhsSym(2);
                //#line 1664 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(3);
                //#line 1666 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
              setResult(nf.AtStmt(pos(), PlaceExpressionSingleList, Statement));
                      break;
            }
    
            //
            // Rule 161:  AtomicStatement ::= atomic Statement
            //
            case 161: {
               //#line 1672 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1670 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(2);
                //#line 1672 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
              setResult(nf.Atomic(pos(), nf.Here(pos(getLeftSpan())), Statement));
                      break;
            }
    
            //
            // Rule 162:  WhenStatement ::= when ( Expression ) Statement
            //
            case 162: {
               //#line 1679 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1677 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1677 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(5);
                //#line 1679 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.When(pos(), Expression, Statement));
                      break;
            }
    
            //
            // Rule 163:  WhenStatement ::= WhenStatement or$or ( Expression ) Statement
            //
            case 163: {
               //#line 1684 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1682 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                When WhenStatement = (When) getRhsSym(1);
                //#line 1682 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken or = (IToken) getRhsIToken(2);
                //#line 1682 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(4);
                //#line 1682 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(6);
                //#line 1684 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
              WhenStatement.addBranch(pos(getRhsFirstTokenIndex(2), getRightSpan()), Expression, Statement);
              setResult(WhenStatement);
                      break;
            }
    
            //
            // Rule 164:  ForEachStatement ::= foreach ( LoopIndex in Expression ) ClockedClauseopt Statement
            //
            case 164: {
               //#line 1691 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1689 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                X10Formal LoopIndex = (X10Formal) getRhsSym(3);
                //#line 1689 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(5);
                //#line 1689 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ClockedClauseopt = (List) getRhsSym(7);
                //#line 1689 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(8);
                //#line 1691 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                FlagsNode fn = LoopIndex.flags();
                Flags f = fn.flags();
                f = f.Final();
                fn = fn.flags(f);
                setResult(nf.ForEach(pos(),
                              LoopIndex.flags(fn),
                              Expression,
                              ClockedClauseopt,
                              Statement));
                      break;
            }
    
            //
            // Rule 165:  AtEachStatement ::= ateach ( LoopIndex in Expression ) ClockedClauseopt Statement
            //
            case 165: {
               //#line 1705 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1703 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                X10Formal LoopIndex = (X10Formal) getRhsSym(3);
                //#line 1703 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(5);
                //#line 1703 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ClockedClauseopt = (List) getRhsSym(7);
                //#line 1703 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(8);
                //#line 1705 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                FlagsNode fn = LoopIndex.flags();
                Flags f = fn.flags();
                f = f.Final();
                fn = fn.flags(f);
                setResult(nf.AtEach(pos(),
                             LoopIndex.flags(fn),
                             Expression,
                             ClockedClauseopt,
                             Statement));
                      break;
            }
    
            //
            // Rule 166:  EnhancedForStatement ::= for ( LoopIndex in Expression ) Statement
            //
            case 166: {
               //#line 1719 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1717 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                X10Formal LoopIndex = (X10Formal) getRhsSym(3);
                //#line 1717 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(5);
                //#line 1717 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(7);
                //#line 1719 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                FlagsNode fn = LoopIndex.flags();
                Flags f = fn.flags();
                f = f.Final();
                fn = fn.flags(f);
                setResult(nf.ForLoop(pos(),
                        LoopIndex.flags(fn),
                        Expression,
                        Statement));
                      break;
            }
    
            //
            // Rule 167:  FinishStatement ::= finish Statement
            //
            case 167: {
               //#line 1732 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1730 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(2);
                //#line 1732 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Finish(pos(),  Statement));
                      break;
            }
    
            //
            // Rule 168:  AnnotationStatement ::= Annotations Statement
            //
            case 168: {
               //#line 1739 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1737 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Annotations = (List) getRhsSym(1);
                //#line 1737 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(2);
                //#line 1739 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                if (Statement.ext() instanceof X10Ext && Annotations instanceof List) {
                    Statement = (Stmt) ((X10Ext) Statement.ext()).annotations((List) Annotations);
                }
                setResult(Statement);
                      break;
            }
    
            //
            // Rule 169:  PlaceExpressionSingleList ::= ( PlaceExpression )
            //
            case 169: {
               //#line 1748 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1746 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr PlaceExpression = (Expr) getRhsSym(2);
                //#line 1748 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
              setResult(PlaceExpression);
                      break;
            }
    
            //
            // Rule 171:  NextStatement ::= next ;
            //
            case 171: {
               //#line 1756 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 1756 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Next(pos()));
                      break;
            }
    
            //
            // Rule 172:  AwaitStatement ::= await Expression ;
            //
            case 172: {
               //#line 1762 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1760 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(2);
                //#line 1762 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Await(pos(), Expression));
                      break;
            }
    
            //
            // Rule 173:  ClockList ::= Clock
            //
            case 173: {
               //#line 1768 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1766 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Clock = (Expr) getRhsSym(1);
                //#line 1768 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Expr.class, false);
                l.add(Clock);
                setResult(l);
                      break;
            }
    
            //
            // Rule 174:  ClockList ::= ClockList , Clock
            //
            case 174: {
               //#line 1775 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1773 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ClockList = (List) getRhsSym(1);
                //#line 1773 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Clock = (Expr) getRhsSym(3);
                //#line 1775 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                ClockList.add(Clock);
                setResult(ClockList);
                      break;
            }
    
            //
            // Rule 175:  Clock ::= Expression
            //
            case 175: {
               //#line 1783 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1781 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(1);
                //#line 1783 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
    setResult(Expression);
                      break;
            }
    
            //
            // Rule 176:  CastExpression ::= CastExpression as Type
            //
            case 176: {
               //#line 1797 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1795 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr CastExpression = (Expr) getRhsSym(1);
                //#line 1795 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(3);
                //#line 1797 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.X10Cast(pos(), Type, CastExpression));
                      break;
            }
    
            //
            // Rule 177:  CastExpression ::= ConditionalExpression ! Expression
            //
            case 177: {
               //#line 1802 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1800 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr ConditionalExpression = (Expr) getRhsSym(1);
                //#line 1800 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1802 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.PlaceCast(pos(), Expression, ConditionalExpression));
                      break;
            }
    
            //
            // Rule 179:  TypePropertyList ::= TypeProperty
            //
            case 179: {
               //#line 1811 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1809 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypePropertyNode TypeProperty = (TypePropertyNode) getRhsSym(1);
                //#line 1811 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), TypePropertyNode.class, false);
                l.add(TypeProperty);
                setResult(l);
                      break;
            }
    
            //
            // Rule 180:  TypePropertyList ::= TypePropertyList , TypeProperty
            //
            case 180: {
               //#line 1818 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1816 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypePropertyList = (List) getRhsSym(1);
                //#line 1816 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypePropertyNode TypeProperty = (TypePropertyNode) getRhsSym(3);
                //#line 1818 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                TypePropertyList.add(TypeProperty);
                setResult(TypePropertyList);
                      break;
            }
    
            //
            // Rule 181:  TypeParameterList ::= TypeParameter
            //
            case 181: {
               //#line 1825 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1823 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeParamNode TypeParameter = (TypeParamNode) getRhsSym(1);
                //#line 1825 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), TypeParamNode.class, false);
                l.add(TypeParameter);
                setResult(l);
                      break;
            }
    
            //
            // Rule 182:  TypeParameterList ::= TypeParameterList , TypeParameter
            //
            case 182: {
               //#line 1832 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1830 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeParameterList = (List) getRhsSym(1);
                //#line 1830 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeParamNode TypeParameter = (TypeParamNode) getRhsSym(3);
                //#line 1832 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                TypeParameterList.add(TypeParameter);
                setResult(TypeParameterList);
                      break;
            }
    
            //
            // Rule 183:  TypeProperty ::= Identifier
            //
            case 183: {
               //#line 1839 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1837 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 1839 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.TypePropertyNode(pos(), Identifier, TypeProperty.Variance.INVARIANT));
                      break;
            }
    
            //
            // Rule 184:  TypeProperty ::= + Identifier
            //
            case 184: {
               //#line 1844 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1842 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(2);
                //#line 1844 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.TypePropertyNode(pos(), Identifier, TypeProperty.Variance.COVARIANT));
                      break;
            }
    
            //
            // Rule 185:  TypeProperty ::= - Identifier
            //
            case 185: {
               //#line 1849 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1847 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(2);
                //#line 1849 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.TypePropertyNode(pos(), Identifier, TypeProperty.Variance.CONTRAVARIANT));
                      break;
            }
    
            //
            // Rule 186:  TypeParameter ::= Identifier
            //
            case 186: {
               //#line 1855 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1853 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 1855 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.TypeParamNode(pos(), Identifier));
                      break;
            }
    
            //
            // Rule 187:  Primary ::= here
            //
            case 187: {
               //#line 1861 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 1861 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(((X10NodeFactory) nf).Here(pos()));
                      break;
            }
    
            //
            // Rule 189:  RegionExpressionList ::= RegionExpression
            //
            case 189: {
               //#line 1869 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1867 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr RegionExpression = (Expr) getRhsSym(1);
                //#line 1869 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Expr.class, false);
                l.add(RegionExpression);
                setResult(l);
                      break;
            }
    
            //
            // Rule 190:  RegionExpressionList ::= RegionExpressionList , RegionExpression
            //
            case 190: {
               //#line 1876 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1874 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List RegionExpressionList = (List) getRhsSym(1);
                //#line 1874 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr RegionExpression = (Expr) getRhsSym(3);
                //#line 1876 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                RegionExpressionList.add(RegionExpression);
                //setResult(RegionExpressionList);
                      break;
            }
    
            //
            // Rule 191:  Primary ::= [ ArgumentListopt ]
            //
            case 191: {
               //#line 1883 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1881 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(2);
                //#line 1883 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                Tuple tuple = nf.Tuple(pos(), ArgumentListopt);
                setResult(tuple);
                      break;
            }
    
            //
            // Rule 192:  AssignmentExpression ::= Expression$expr1 -> Expression$expr2
            //
            case 192: {
               //#line 1890 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1888 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr expr1 = (Expr) getRhsSym(1);
                //#line 1888 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr expr2 = (Expr) getRhsSym(3);
                //#line 1890 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                Expr call = nf.ConstantDistMaker(pos(), expr1, expr2);
                setResult(call);
                      break;
            }
    
            //
            // Rule 193:  ClosureExpression ::= TypeParametersopt FormalParameters WhereClauseopt ResultTypeopt Throwsopt => ClosureBody
            //
            case 193: {
               //#line 1897 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1895 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeParametersopt = (List) getRhsSym(1);
                //#line 1895 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalParameters = (List) getRhsSym(2);
                //#line 1895 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                DepParameterExpr WhereClauseopt = (DepParameterExpr) getRhsSym(3);
                //#line 1895 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ResultTypeopt = (TypeNode) getRhsSym(4);
                //#line 1895 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Throwsopt = (List) getRhsSym(5);
                //#line 1895 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block ClosureBody = (Block) getRhsSym(7);
                //#line 1897 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Closure(pos(), TypeParametersopt, FormalParameters, WhereClauseopt, 
          ResultTypeopt == null ? nf.UnknownTypeNode(pos()) : ResultTypeopt, Throwsopt, ClosureBody));
                      break;
            }
    
            //
            // Rule 194:  LastExpression ::= Expression
            //
            case 194: {
               //#line 1904 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1902 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(1);
                //#line 1904 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.X10Return(pos(), Expression, true));
                      break;
            }
    
            //
            // Rule 195:  ClosureBody ::= CastExpression
            //
            case 195: {
               //#line 1910 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1908 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr CastExpression = (Expr) getRhsSym(1);
                //#line 1910 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Block(pos(), nf.X10Return(pos(), CastExpression, true)));
                      break;
            }
    
            //
            // Rule 196:  ClosureBody ::= Annotationsopt { BlockStatementsopt LastExpression }
            //
            case 196: {
               //#line 1915 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1913 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Annotationsopt = (List) getRhsSym(1);
                //#line 1913 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List BlockStatementsopt = (List) getRhsSym(3);
                //#line 1913 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt LastExpression = (Stmt) getRhsSym(4);
                //#line 1915 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Stmt> l = new ArrayList<Stmt>();
                l.addAll(BlockStatementsopt);
                l.add(LastExpression);
                Block b = nf.Block(pos(), l);
                b = (Block) ((X10Ext) b.ext()).annotations(Annotationsopt);
                setResult(b);
                      break;
            }
    
            //
            // Rule 197:  ClosureBody ::= Annotationsopt Block
            //
            case 197: {
               //#line 1925 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1923 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Annotationsopt = (List) getRhsSym(1);
                //#line 1923 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block Block = (Block) getRhsSym(2);
                //#line 1925 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                Block b = Block;
                b = (Block) ((X10Ext) b.ext()).annotations(Annotationsopt);
                setResult(b);
                      break;
            }
    
            //
            // Rule 198:  AtExpression ::= at PlaceExpressionSingleList ClosureBody
            //
            case 198: {
               //#line 1934 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1932 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr PlaceExpressionSingleList = (Expr) getRhsSym(2);
                //#line 1932 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block ClosureBody = (Block) getRhsSym(3);
                //#line 1934 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.AtExpr(pos(), PlaceExpressionSingleList, nf.UnknownTypeNode(pos()), ClosureBody));
                      break;
            }
    
            //
            // Rule 199:  AsyncExpression ::= async ClosureBody
            //
            case 199: {
               //#line 1940 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1938 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block ClosureBody = (Block) getRhsSym(2);
                //#line 1940 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Call(pos(), nf.Future(pos(), nf.Here(pos(getLeftSpan())), nf.UnknownTypeNode(pos()), ClosureBody), nf.Id(pos(), "force")));
                      break;
            }
    
            //
            // Rule 200:  AsyncExpression ::= async PlaceExpressionSingleList ClosureBody
            //
            case 200: {
               //#line 1945 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1943 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr PlaceExpressionSingleList = (Expr) getRhsSym(2);
                //#line 1943 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block ClosureBody = (Block) getRhsSym(3);
                //#line 1945 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Call(pos(), nf.Future(pos(), PlaceExpressionSingleList, nf.UnknownTypeNode(pos()), ClosureBody), nf.Id(pos(), "force")));
                      break;
            }
    
            //
            // Rule 201:  AsyncExpression ::= async [ Type ] ClosureBody
            //
            case 201: {
               //#line 1950 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1948 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(3);
                //#line 1948 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block ClosureBody = (Block) getRhsSym(5);
                //#line 1950 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Call(pos(), nf.Future(pos(), nf.Here(pos(getLeftSpan())), Type, ClosureBody), nf.Id(pos(), "force")));
                      break;
            }
    
            //
            // Rule 202:  AsyncExpression ::= async [ Type ] PlaceExpressionSingleList ClosureBody
            //
            case 202: {
               //#line 1955 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1953 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(3);
                //#line 1953 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr PlaceExpressionSingleList = (Expr) getRhsSym(5);
                //#line 1953 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block ClosureBody = (Block) getRhsSym(6);
                //#line 1955 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Call(pos(), nf.Future(pos(), PlaceExpressionSingleList, Type, ClosureBody), nf.Id(pos(), "force")));
                      break;
            }
    
            //
            // Rule 203:  FutureExpression ::= future ClosureBody
            //
            case 203: {
               //#line 1961 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1959 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block ClosureBody = (Block) getRhsSym(2);
                //#line 1961 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Future(pos(), nf.Here(pos(getLeftSpan())), nf.UnknownTypeNode(pos()), ClosureBody));
                      break;
            }
    
            //
            // Rule 204:  FutureExpression ::= future PlaceExpressionSingleList ClosureBody
            //
            case 204: {
               //#line 1966 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1964 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr PlaceExpressionSingleList = (Expr) getRhsSym(2);
                //#line 1964 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block ClosureBody = (Block) getRhsSym(3);
                //#line 1966 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Future(pos(), PlaceExpressionSingleList, nf.UnknownTypeNode(pos()), ClosureBody));
                      break;
            }
    
            //
            // Rule 205:  FutureExpression ::= future [ Type ] ClosureBody
            //
            case 205: {
               //#line 1971 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1969 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(3);
                //#line 1969 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block ClosureBody = (Block) getRhsSym(5);
                //#line 1971 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Future(pos(), nf.Here(pos(getLeftSpan())), Type, ClosureBody));
                      break;
            }
    
            //
            // Rule 206:  FutureExpression ::= future [ Type ] PlaceExpressionSingleList ClosureBody
            //
            case 206: {
               //#line 1976 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 1974 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(3);
                //#line 1974 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr PlaceExpressionSingleList = (Expr) getRhsSym(5);
                //#line 1974 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block ClosureBody = (Block) getRhsSym(6);
                //#line 1976 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Future(pos(), PlaceExpressionSingleList, Type, ClosureBody));
                      break;
            }
    
            //
            // Rule 207:  DepParametersopt ::= $Empty
            //
            case 207:
                setResult(null);
                break;

            //
            // Rule 209:  PropertyListopt ::= $Empty
            //
            case 209: {
               //#line 1987 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 1987 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), PropertyDecl.class, false));
                      break;
            }
    
            //
            // Rule 211:  WhereClauseopt ::= $Empty
            //
            case 211:
                setResult(null);
                break;

            //
            // Rule 213:  ObjectKindopt ::= $Empty
            //
            case 213:
                setResult(null);
                break;

            //
            // Rule 215:  PlaceExpressionSingleListopt ::= $Empty
            //
            case 215:
                setResult(null);
                break;

            //
            // Rule 217:  ClassModifiersopt ::= $Empty
            //
            case 217: {
               //#line 2006 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2006 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
         setResult(Collections.singletonList(nf.FlagsNode(Position.COMPILER_GENERATED, X10Flags.toX10Flags(Flags.NONE))));
                      break;
            } 
            //
            // Rule 219:  TypeDefModifiersopt ::= $Empty
            //
            case 219: {
               //#line 2012 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2012 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
         setResult(Collections.singletonList(nf.FlagsNode(Position.COMPILER_GENERATED, X10Flags.toX10Flags(Flags.NONE))));
                      break;
            } 
            //
            // Rule 221:  Unsafeopt ::= $Empty
            //
            case 221:
                setResult(null);
                break;

            //
            // Rule 222:  Unsafeopt ::= unsafe
            //
            case 222: {
               //#line 2020 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2020 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                // any value distinct from null
                setResult(this);
                      break;
            }
    
            //
            // Rule 223:  ClockedClauseopt ::= $Empty
            //
            case 223: {
               //#line 2027 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2027 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), Expr.class, false));
                      break;
            }
    
            //
            // Rule 225:  identifier ::= IDENTIFIER$ident
            //
            case 225: {
               //#line 2038 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2036 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken ident = (IToken) getRhsIToken(1);
                //#line 2038 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                ident.setKind(X10Parsersym.TK_IDENTIFIER);
                setResult(id(getRhsFirstTokenIndex(1)));
                      break;
            }
    
            //
            // Rule 226:  TypeName ::= Identifier
            //
            case 226: {
               //#line 2045 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2043 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 2045 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new X10ParsedName(nf, ts, pos(), Identifier));
                      break;
            }
    
            //
            // Rule 227:  TypeName ::= TypeName . Identifier
            //
            case 227: {
               //#line 2050 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2048 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 2048 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 2050 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new X10ParsedName(nf,
                                  ts,
                                  pos(getLeftSpan(), getRightSpan()),
                                  TypeName,
                                  Identifier));
                      break;
            }
    
            //
            // Rule 229:  TypeArguments ::= [ TypeArgumentList ]
            //
            case 229: {
               //#line 2062 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2060 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentList = (List) getRhsSym(2);
                //#line 2062 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(TypeArgumentList);
                      break;
            }
    
            //
            // Rule 230:  TypeArgumentList ::= Type
            //
            case 230: {
               //#line 2069 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2067 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(1);
                //#line 2069 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new ArrayList();
                l.add(Type);
                setResult(l);
                      break;
            }
    
            //
            // Rule 231:  TypeArgumentList ::= TypeArgumentList , Type
            //
            case 231: {
               //#line 2076 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2074 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentList = (List) getRhsSym(1);
                //#line 2074 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(3);
                //#line 2076 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                TypeArgumentList.add(Type);
                      break;
            }
    
            //
            // Rule 232:  PackageName ::= Identifier
            //
            case 232: {
               //#line 2086 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2084 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 2086 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new X10ParsedName(nf, ts, pos(), Identifier));
                      break;
            }
    
            //
            // Rule 233:  PackageName ::= PackageName . Identifier
            //
            case 233: {
               //#line 2091 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2089 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName PackageName = (ParsedName) getRhsSym(1);
                //#line 2089 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 2091 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new X10ParsedName(nf,
                                  ts,
                                  pos(getLeftSpan(), getRightSpan()),
                                  PackageName,
                                  Identifier));
                      break;
            }
    
            //
            // Rule 234:  ExpressionName ::= Identifier
            //
            case 234: {
               //#line 2107 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2105 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 2107 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new X10ParsedName(nf, ts, pos(), Identifier));
                      break;
            }
    
            //
            // Rule 235:  ExpressionName ::= AmbiguousName . Identifier
            //
            case 235: {
               //#line 2112 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2110 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName AmbiguousName = (ParsedName) getRhsSym(1);
                //#line 2110 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 2112 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new X10ParsedName(nf,
                                  ts,
                                  pos(getLeftSpan(), getRightSpan()),
                                  AmbiguousName,
                                  Identifier));
                      break;
            }
    
            //
            // Rule 236:  MethodName ::= Identifier
            //
            case 236: {
               //#line 2122 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2120 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 2122 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new X10ParsedName(nf, ts, pos(), Identifier));
                      break;
            }
    
            //
            // Rule 237:  MethodName ::= AmbiguousName . Identifier
            //
            case 237: {
               //#line 2127 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2125 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName AmbiguousName = (ParsedName) getRhsSym(1);
                //#line 2125 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 2127 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new X10ParsedName(nf,
                                  ts,
                                  pos(getLeftSpan(), getRightSpan()),
                                  AmbiguousName,
                                  Identifier));
                      break;
            }
    
            //
            // Rule 238:  PackageOrTypeName ::= Identifier
            //
            case 238: {
               //#line 2137 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2135 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 2137 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new X10ParsedName(nf, ts, pos(), Identifier));
                      break;
            }
    
            //
            // Rule 239:  PackageOrTypeName ::= PackageOrTypeName . Identifier
            //
            case 239: {
               //#line 2142 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2140 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName PackageOrTypeName = (ParsedName) getRhsSym(1);
                //#line 2140 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 2142 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new X10ParsedName(nf,
                                  ts,
                                  pos(getLeftSpan(), getRightSpan()),
                                  PackageOrTypeName,
                                  Identifier));
                      break;
            }
    
            //
            // Rule 240:  AmbiguousName ::= Identifier
            //
            case 240: {
               //#line 2152 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2150 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 2152 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new X10ParsedName(nf, ts, pos(), Identifier));
                      break;
            }
    
            //
            // Rule 241:  AmbiguousName ::= AmbiguousName . Identifier
            //
            case 241: {
               //#line 2157 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2155 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName AmbiguousName = (ParsedName) getRhsSym(1);
                //#line 2155 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 2157 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new X10ParsedName(nf,
                                  ts,
                                  pos(getLeftSpan(), getRightSpan()),
                                  AmbiguousName,
                                  Identifier));
                     break;
            }
    
            //
            // Rule 242:  CompilationUnit ::= PackageDeclarationopt ImportDeclarationsopt TypeDeclarationsopt
            //
            case 242: {
               //#line 2169 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2167 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                PackageNode PackageDeclarationopt = (PackageNode) getRhsSym(1);
                //#line 2167 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ImportDeclarationsopt = (List) getRhsSym(2);
                //#line 2167 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeDeclarationsopt = (List) getRhsSym(3);
                //#line 2169 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                // Add import x10.lang.* by default.
                int token_pos = (ImportDeclarationsopt.size() == 0
                                     ? TypeDeclarationsopt.size() == 0
                                           ? prsStream.getSize() - 1
                                           : prsStream.getPrevious(getRhsFirstTokenIndex(3))
                                 : getRhsLastTokenIndex(2)
                            );
//                    Import x10LangImport = 
//                    nf.Import(pos(token_pos), Import.PACKAGE, QName.make("x10.lang"));
//                    ImportDeclarationsopt.add(x10LangImport);
                setResult(nf.SourceFile(pos(getLeftSpan(), getRightSpan()), PackageDeclarationopt, ImportDeclarationsopt, TypeDeclarationsopt));
                      break;
            }
    
            //
            // Rule 243:  ImportDeclarations ::= ImportDeclaration
            //
            case 243: {
               //#line 2185 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2183 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Import ImportDeclaration = (Import) getRhsSym(1);
                //#line 2185 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Import.class, false);
                l.add(ImportDeclaration);
                setResult(l);
                      break;
            }
    
            //
            // Rule 244:  ImportDeclarations ::= ImportDeclarations ImportDeclaration
            //
            case 244: {
               //#line 2192 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2190 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ImportDeclarations = (List) getRhsSym(1);
                //#line 2190 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Import ImportDeclaration = (Import) getRhsSym(2);
                //#line 2192 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                if (ImportDeclaration != null)
                    ImportDeclarations.add(ImportDeclaration);
                //setResult(l);
                      break;
            }
    
            //
            // Rule 245:  TypeDeclarations ::= TypeDeclaration
            //
            case 245: {
               //#line 2200 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2198 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TopLevelDecl TypeDeclaration = (TopLevelDecl) getRhsSym(1);
                //#line 2200 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), TopLevelDecl.class, false);
                if (TypeDeclaration != null)
                    l.add(TypeDeclaration);
                setResult(l);
                      break;
            }
    
            //
            // Rule 246:  TypeDeclarations ::= TypeDeclarations TypeDeclaration
            //
            case 246: {
               //#line 2208 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2206 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeDeclarations = (List) getRhsSym(1);
                //#line 2206 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TopLevelDecl TypeDeclaration = (TopLevelDecl) getRhsSym(2);
                //#line 2208 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                if (TypeDeclaration != null)
                    TypeDeclarations.add(TypeDeclaration);
                //setResult(l);
                      break;
            }
    
            //
            // Rule 247:  PackageDeclaration ::= Annotationsopt package PackageName ;
            //
            case 247: {
               //#line 2216 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2214 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Annotationsopt = (List) getRhsSym(1);
                //#line 2214 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName PackageName = (ParsedName) getRhsSym(3);
                //#line 2216 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                PackageNode pn = PackageName.toPackage();
                pn = (PackageNode) ((X10Ext) pn.ext()).annotations(Annotationsopt);
                setResult(pn);
                      break;
            }
    
            //
            // Rule 250:  SingleTypeImportDeclaration ::= import TypeName ;
            //
            case 250: {
               //#line 2230 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2228 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(2);
                //#line 2230 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Import(pos(getLeftSpan(), getRightSpan()), Import.CLASS, QName.make(TypeName.toString())));
                      break;
            }
    
            //
            // Rule 251:  TypeImportOnDemandDeclaration ::= import PackageOrTypeName . * ;
            //
            case 251: {
               //#line 2236 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2234 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName PackageOrTypeName = (ParsedName) getRhsSym(2);
                //#line 2236 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Import(pos(getLeftSpan(), getRightSpan()), Import.PACKAGE, QName.make(PackageOrTypeName.toString())));
                      break;
            }
    
            //
            // Rule 255:  TypeDeclaration ::= ;
            //
            case 255: {
               //#line 2251 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2251 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(null);
                      break;
            }
    
            //
            // Rule 256:  ClassModifiers ::= ClassModifier
            //
            case 256: {
               //#line 2259 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2257 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ClassModifier = (List) getRhsSym(1);
                //#line 2259 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new LinkedList();
                l.addAll(ClassModifier);
                setResult(l);
                      break;
            }
    
            //
            // Rule 257:  ClassModifiers ::= ClassModifiers ClassModifier
            //
            case 257: {
               //#line 2266 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2264 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ClassModifiers = (List) getRhsSym(1);
                //#line 2264 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ClassModifier = (List) getRhsSym(2);
                //#line 2266 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                ClassModifiers.addAll(ClassModifier);
                      break;
            }
    
            //
            // Rule 258:  ClassModifier ::= Annotation
            //
            case 258: {
               //#line 2272 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2270 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 2272 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(Annotation));
                      break;
            }
    
            //
            // Rule 259:  ClassModifier ::= public
            //
            case 259: {
               //#line 2277 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2277 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PUBLIC)));
                      break;
            }
    
            //
            // Rule 260:  ClassModifier ::= protected
            //
            case 260: {
               //#line 2282 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2282 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PROTECTED)));
                      break;
            }
    
            //
            // Rule 261:  ClassModifier ::= private
            //
            case 261: {
               //#line 2287 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2287 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PRIVATE)));
                      break;
            }
    
            //
            // Rule 262:  ClassModifier ::= abstract
            //
            case 262: {
               //#line 2292 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2292 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.ABSTRACT)));
                      break;
            }
    
            //
            // Rule 263:  ClassModifier ::= static
            //
            case 263: {
               //#line 2297 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2297 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.STATIC)));
                      break;
            }
    
            //
            // Rule 264:  ClassModifier ::= final
            //
            case 264: {
               //#line 2302 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2302 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.FINAL)));
                      break;
            }
    
            //
            // Rule 265:  ClassModifier ::= strictfp
            //
            case 265: {
               //#line 2307 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2307 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.STRICTFP)));
                      break;
            }
    
            //
            // Rule 266:  ClassModifier ::= safe
            //
            case 266: {
               //#line 2312 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2312 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), X10Flags.SAFE)));
                      break;
            }
    
            //
            // Rule 267:  ClassModifier ::= value
            //
            case 267: {
               //#line 2317 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2317 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), X10Flags.VALUE)));
                      break;
            }
    
            //
            // Rule 268:  TypeDefModifiers ::= TypeDefModifier
            //
            case 268: {
               //#line 2323 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2321 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeDefModifier = (List) getRhsSym(1);
                //#line 2323 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new LinkedList();
                l.addAll(TypeDefModifier);
                setResult(l);
                      break;
            }
    
            //
            // Rule 269:  TypeDefModifiers ::= TypeDefModifiers TypeDefModifier
            //
            case 269: {
               //#line 2330 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2328 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeDefModifiers = (List) getRhsSym(1);
                //#line 2328 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeDefModifier = (List) getRhsSym(2);
                //#line 2330 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                TypeDefModifiers.addAll(TypeDefModifier);
                      break;
            }
    
            //
            // Rule 270:  TypeDefModifier ::= Annotation
            //
            case 270: {
               //#line 2336 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2334 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 2336 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(Annotation));
                      break;
            }
    
            //
            // Rule 271:  TypeDefModifier ::= public
            //
            case 271: {
               //#line 2341 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2341 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PUBLIC)));
                      break;
            }
    
            //
            // Rule 272:  TypeDefModifier ::= protected
            //
            case 272: {
               //#line 2346 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2346 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PROTECTED)));
                      break;
            }
    
            //
            // Rule 273:  TypeDefModifier ::= private
            //
            case 273: {
               //#line 2351 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2351 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PRIVATE)));
                      break;
            }
    
            //
            // Rule 274:  TypeDefModifier ::= abstract
            //
            case 274: {
               //#line 2356 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2356 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.ABSTRACT)));
                      break;
            }
    
            //
            // Rule 275:  TypeDefModifier ::= static
            //
            case 275: {
               //#line 2361 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2361 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.STATIC)));
                      break;
            }
    
            //
            // Rule 276:  TypeDefModifier ::= final
            //
            case 276: {
               //#line 2366 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2366 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.FINAL)));
                      break;
            }
    
            //
            // Rule 277:  Interfaces ::= implements InterfaceTypeList
            //
            case 277: {
               //#line 2375 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2373 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List InterfaceTypeList = (List) getRhsSym(2);
                //#line 2375 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(InterfaceTypeList);
                      break;
            }
    
            //
            // Rule 278:  InterfaceTypeList ::= Type
            //
            case 278: {
               //#line 2381 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2379 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(1);
                //#line 2381 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), TypeNode.class, false);
                l.add(Type);
                setResult(l);
                      break;
            }
    
            //
            // Rule 279:  InterfaceTypeList ::= InterfaceTypeList , Type
            //
            case 279: {
               //#line 2388 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2386 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List InterfaceTypeList = (List) getRhsSym(1);
                //#line 2386 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(3);
                //#line 2388 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                InterfaceTypeList.add(Type);
                setResult(InterfaceTypeList);
                      break;
            }
    
            //
            // Rule 280:  ClassBody ::= { ClassBodyDeclarationsopt }
            //
            case 280: {
               //#line 2398 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2396 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ClassBodyDeclarationsopt = (List) getRhsSym(2);
                //#line 2398 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.ClassBody(pos(getLeftSpan(), getRightSpan()), ClassBodyDeclarationsopt));
                      break;
            }
    
            //
            // Rule 282:  ClassBodyDeclarations ::= ClassBodyDeclarations ClassBodyDeclaration
            //
            case 282: {
               //#line 2405 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2403 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ClassBodyDeclarations = (List) getRhsSym(1);
                //#line 2403 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ClassBodyDeclaration = (List) getRhsSym(2);
                //#line 2405 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                ClassBodyDeclarations.addAll(ClassBodyDeclaration);
                // setResult(a);
                      break;
            }
    
            //
            // Rule 284:  ClassBodyDeclaration ::= InstanceInitializer
            //
            case 284: {
               //#line 2413 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2411 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Initializer InstanceInitializer = (Initializer) getRhsSym(1);
                //#line 2413 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(InstanceInitializer);
                setResult(l);
                      break;
            }
    
            //
            // Rule 285:  ClassBodyDeclaration ::= StaticInitializer
            //
            case 285: {
               //#line 2420 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2418 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Initializer StaticInitializer = (Initializer) getRhsSym(1);
                //#line 2420 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(StaticInitializer);
                setResult(l);
                      break;
            }
    
            //
            // Rule 286:  ClassBodyDeclaration ::= ConstructorDeclaration
            //
            case 286: {
               //#line 2427 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2425 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ConstructorDecl ConstructorDeclaration = (ConstructorDecl) getRhsSym(1);
                //#line 2427 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(ConstructorDeclaration);
                setResult(l);
                      break;
            }
    
            //
            // Rule 288:  ClassMemberDeclaration ::= MethodDeclaration
            //
            case 288: {
               //#line 2436 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2434 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ClassMember MethodDeclaration = (ClassMember) getRhsSym(1);
                //#line 2436 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(MethodDeclaration);
                setResult(l);
                      break;
            }
    
            //
            // Rule 289:  ClassMemberDeclaration ::= PropertyMethodDeclaration
            //
            case 289: {
               //#line 2443 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2441 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ClassMember PropertyMethodDeclaration = (ClassMember) getRhsSym(1);
                //#line 2443 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(PropertyMethodDeclaration);
                setResult(l);
                      break;
            }
    
            //
            // Rule 290:  ClassMemberDeclaration ::= TypeDefDeclaration
            //
            case 290: {
               //#line 2450 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2448 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeDecl TypeDefDeclaration = (TypeDecl) getRhsSym(1);
                //#line 2450 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(TypeDefDeclaration);
                setResult(l);
                      break;
            }
    
            //
            // Rule 291:  ClassMemberDeclaration ::= ClassDeclaration
            //
            case 291: {
               //#line 2457 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2455 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ClassDecl ClassDeclaration = (ClassDecl) getRhsSym(1);
                //#line 2457 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(ClassDeclaration);
                setResult(l);
                      break;
            }
    
            //
            // Rule 292:  ClassMemberDeclaration ::= InterfaceDeclaration
            //
            case 292: {
               //#line 2464 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2462 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ClassDecl InterfaceDeclaration = (ClassDecl) getRhsSym(1);
                //#line 2464 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(InterfaceDeclaration);
                setResult(l);
                      break;
            }
    
            //
            // Rule 293:  ClassMemberDeclaration ::= ;
            //
            case 293: {
               //#line 2471 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2471 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                setResult(l);
                      break;
            }
    
            //
            // Rule 294:  FormalDeclarators ::= FormalDeclarator
            //
            case 294: {
               //#line 2478 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2476 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Object[] FormalDeclarator = (Object[]) getRhsSym(1);
                //#line 2478 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Object[].class, false);
                l.add(FormalDeclarator);
                setResult(l);
                      break;
            }
    
            //
            // Rule 295:  FormalDeclarators ::= FormalDeclarators , FormalDeclarator
            //
            case 295: {
               //#line 2485 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2483 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalDeclarators = (List) getRhsSym(1);
                //#line 2483 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Object[] FormalDeclarator = (Object[]) getRhsSym(3);
                //#line 2485 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                FormalDeclarators.add(FormalDeclarator);
                      break;
            }
    
            //
            // Rule 296:  FieldDeclarators ::= FieldDeclarator
            //
            case 296: {
               //#line 2492 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2490 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Object[] FieldDeclarator = (Object[]) getRhsSym(1);
                //#line 2492 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Object[].class, false);
                l.add(FieldDeclarator);
                setResult(l);
                      break;
            }
    
            //
            // Rule 297:  FieldDeclarators ::= FieldDeclarators , FieldDeclarator
            //
            case 297: {
               //#line 2499 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2497 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FieldDeclarators = (List) getRhsSym(1);
                //#line 2497 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Object[] FieldDeclarator = (Object[]) getRhsSym(3);
                //#line 2499 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                FieldDeclarators.add(FieldDeclarator);
                // setResult(FieldDeclarators);
                      break;
            }
    
            //
            // Rule 298:  VariableDeclaratorsWithType ::= VariableDeclaratorWithType
            //
            case 298: {
               //#line 2507 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2505 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Object[] VariableDeclaratorWithType = (Object[]) getRhsSym(1);
                //#line 2507 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Object[].class, false);
                l.add(VariableDeclaratorWithType);
                setResult(l);
                      break;
            }
    
            //
            // Rule 299:  VariableDeclaratorsWithType ::= VariableDeclaratorsWithType , VariableDeclaratorWithType
            //
            case 299: {
               //#line 2514 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2512 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableDeclaratorsWithType = (List) getRhsSym(1);
                //#line 2512 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Object[] VariableDeclaratorWithType = (Object[]) getRhsSym(3);
                //#line 2514 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                VariableDeclaratorsWithType.add(VariableDeclaratorWithType);
                // setResult(VariableDeclaratorsWithType);
                      break;
            }
    
            //
            // Rule 300:  VariableDeclarators ::= VariableDeclarator
            //
            case 300: {
               //#line 2521 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2519 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Object[] VariableDeclarator = (Object[]) getRhsSym(1);
                //#line 2521 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Object[].class, false);
                l.add(VariableDeclarator);
                setResult(l);
                      break;
            }
    
            //
            // Rule 301:  VariableDeclarators ::= VariableDeclarators , VariableDeclarator
            //
            case 301: {
               //#line 2528 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2526 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableDeclarators = (List) getRhsSym(1);
                //#line 2526 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Object[] VariableDeclarator = (Object[]) getRhsSym(3);
                //#line 2528 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                VariableDeclarators.add(VariableDeclarator);
                // setResult(VariableDeclarators);
                      break;
            }
    
            //
            // Rule 303:  FieldModifiers ::= FieldModifier
            //
            case 303: {
               //#line 2537 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2535 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FieldModifier = (List) getRhsSym(1);
                //#line 2537 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new LinkedList();
                l.addAll(FieldModifier);
                setResult(l);
                      break;
            }
    
            //
            // Rule 304:  FieldModifiers ::= FieldModifiers FieldModifier
            //
            case 304: {
               //#line 2544 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2542 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FieldModifiers = (List) getRhsSym(1);
                //#line 2542 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FieldModifier = (List) getRhsSym(2);
                //#line 2544 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                FieldModifiers.addAll(FieldModifier);
                      break;
            }
    
            //
            // Rule 305:  FieldModifier ::= Annotation
            //
            case 305: {
               //#line 2550 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2548 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 2550 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(Annotation));
                      break;
            }
    
            //
            // Rule 306:  FieldModifier ::= public
            //
            case 306: {
               //#line 2555 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2555 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PUBLIC)));
                      break;
            }
    
            //
            // Rule 307:  FieldModifier ::= protected
            //
            case 307: {
               //#line 2560 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2560 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PROTECTED)));
                      break;
            }
    
            //
            // Rule 308:  FieldModifier ::= private
            //
            case 308: {
               //#line 2565 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2565 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PRIVATE)));
                      break;
            }
    
            //
            // Rule 309:  FieldModifier ::= static
            //
            case 309: {
               //#line 2570 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2570 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.STATIC)));
                      break;
            }
    
            //
            // Rule 310:  FieldModifier ::= transient
            //
            case 310: {
               //#line 2575 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2575 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.TRANSIENT)));
                      break;
            }
    
            //
            // Rule 311:  FieldModifier ::= volatile
            //
            case 311: {
               //#line 2580 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2580 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.VOLATILE)));
                      break;
            }
    
            //
            // Rule 312:  ResultType ::= : Type
            //
            case 312: {
               //#line 2586 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2584 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(2);
                //#line 2586 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Type);
                      break;
            }
    
            //
            // Rule 313:  FormalParameters ::= ( FormalParameterList )
            //
            case 313: {
               //#line 2592 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2590 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalParameterList = (List) getRhsSym(2);
                //#line 2592 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(FormalParameterList);
                      break;
            }
    
            //
            // Rule 314:  FormalParameterList ::= FormalParameter
            //
            case 314: {
               //#line 2598 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2596 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                X10Formal FormalParameter = (X10Formal) getRhsSym(1);
                //#line 2598 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Formal.class, false);
                l.add(FormalParameter);
                setResult(l);
                      break;
            }
    
            //
            // Rule 315:  FormalParameterList ::= FormalParameterList , FormalParameter
            //
            case 315: {
               //#line 2605 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2603 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalParameterList = (List) getRhsSym(1);
                //#line 2603 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                X10Formal FormalParameter = (X10Formal) getRhsSym(3);
                //#line 2605 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                FormalParameterList.add(FormalParameter);
                      break;
            }
    
            //
            // Rule 316:  LoopIndexDeclarator ::= Identifier ResultTypeopt
            //
            case 316: {
               //#line 2611 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2609 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 2609 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ResultTypeopt = (TypeNode) getRhsSym(2);
                //#line 2611 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new Object[] { pos(), Identifier, Collections.EMPTY_LIST, null, ResultTypeopt, null });
                      break;
            }
    
            //
            // Rule 317:  LoopIndexDeclarator ::= ( IdentifierList ) ResultTypeopt
            //
            case 317: {
               //#line 2616 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2614 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List IdentifierList = (List) getRhsSym(2);
                //#line 2614 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ResultTypeopt = (TypeNode) getRhsSym(4);
                //#line 2616 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new Object[] { pos(), null, IdentifierList, null, ResultTypeopt, null });
                      break;
            }
    
            //
            // Rule 318:  LoopIndexDeclarator ::= Identifier ( IdentifierList ) ResultTypeopt
            //
            case 318: {
               //#line 2621 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2619 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 2619 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List IdentifierList = (List) getRhsSym(3);
                //#line 2619 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ResultTypeopt = (TypeNode) getRhsSym(5);
                //#line 2621 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new Object[] { pos(), Identifier, IdentifierList, null, ResultTypeopt, null });
                      break;
            }
    
            //
            // Rule 319:  LoopIndex ::= VariableModifiersopt LoopIndexDeclarator
            //
            case 319: {
               //#line 2627 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2625 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableModifiersopt = (List) getRhsSym(1);
                //#line 2625 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Object[] LoopIndexDeclarator = (Object[]) getRhsSym(2);
                //#line 2627 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
            Formal f;
                        	FlagsNode fn = extractFlags(VariableModifiersopt, Flags.FINAL);
            Object[] o = LoopIndexDeclarator;
            Position pos = (Position) o[0];
            Id name = (Id) o[1];
            boolean unnamed = name == null;
            if (name == null) name = nf.Id(pos, Name.makeFresh());
               List exploded = (List) o[2];
                        DepParameterExpr guard = (DepParameterExpr) o[3];
                        TypeNode type = (TypeNode) o[4];
                        if (type == null) type = nf.UnknownTypeNode(name != null ? name.position() : pos);
                        List explodedFormals = new ArrayList();
                        for (Iterator i = exploded.iterator(); i.hasNext(); ) {
                        	Id id = (Id) i.next();
                        	explodedFormals.add(nf.Formal(id.position(), fn, nf.UnknownTypeNode(id.position()), id));
                        }
            f = nf.X10Formal(pos(), fn, type, name, explodedFormals, unnamed);
            f = (Formal) ((X10Ext) f.ext()).annotations(extractAnnotations(VariableModifiersopt));
            setResult(f);
                      break;
            }
    
            //
            // Rule 320:  LoopIndex ::= VariableModifiersopt VarKeyword LoopIndexDeclarator
            //
            case 320: {
               //#line 2650 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2648 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableModifiersopt = (List) getRhsSym(1);
                //#line 2648 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VarKeyword = (List) getRhsSym(2);
                //#line 2648 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Object[] LoopIndexDeclarator = (Object[]) getRhsSym(3);
                //#line 2650 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
            Formal f;
                        	FlagsNode fn = extractFlags(VariableModifiersopt, VarKeyword);
            Object[] o = LoopIndexDeclarator;
            Position pos = (Position) o[0];
            Id name = (Id) o[1];
            boolean unnamed = name == null;
            if (name == null) name = nf.Id(pos, Name.makeFresh());
               List exploded = (List) o[2];
                        DepParameterExpr guard = (DepParameterExpr) o[3];
                        TypeNode type = (TypeNode) o[4];
                                                    if (type == null) type = nf.UnknownTypeNode(name != null ? name.position() : pos);
                                                    List explodedFormals = new ArrayList();
                        for (Iterator i = exploded.iterator(); i.hasNext(); ) {
                        	Id id = (Id) i.next();
                        	explodedFormals.add(nf.Formal(id.position(), fn, nf.UnknownTypeNode(id.position()), id));
                        }
            f = nf.X10Formal(pos(), fn, type, name, explodedFormals, unnamed);
            f = (Formal) ((X10Ext) f.ext()).annotations(extractAnnotations(VariableModifiersopt));
            setResult(f);
                      break;
            }
    
            //
            // Rule 321:  FormalParameter ::= VariableModifiersopt FormalDeclarator
            //
            case 321: {
               //#line 2674 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2672 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableModifiersopt = (List) getRhsSym(1);
                //#line 2672 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Object[] FormalDeclarator = (Object[]) getRhsSym(2);
                //#line 2674 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
            Formal f;
                        	FlagsNode fn = extractFlags(VariableModifiersopt, Flags.FINAL);
            Object[] o = FormalDeclarator;
            Position pos = (Position) o[0];
            Id name = (Id) o[1];
            boolean unnamed = name == null;
            if (name == null) name = nf.Id(pos, Name.makeFresh());
               List exploded = (List) o[2];
                        DepParameterExpr guard = (DepParameterExpr) o[3];
                        TypeNode type = (TypeNode) o[4];
                        if (type == null) type = nf.UnknownTypeNode(name != null ? name.position() : pos);
                        Expr init = (Expr) o[5];
                        List explodedFormals = new ArrayList();
                        for (Iterator i = exploded.iterator(); i.hasNext(); ) {
                        	Id id = (Id) i.next();
                        	explodedFormals.add(nf.Formal(id.position(), fn, nf.UnknownTypeNode(id.position()), id));
                        }
            f = nf.X10Formal(pos(), fn, type, name, explodedFormals, unnamed);
            f = (Formal) ((X10Ext) f.ext()).annotations(extractAnnotations(VariableModifiersopt));
            setResult(f);
                      break;
            }
    
            //
            // Rule 322:  FormalParameter ::= VariableModifiersopt VarKeyword FormalDeclarator
            //
            case 322: {
               //#line 2698 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2696 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableModifiersopt = (List) getRhsSym(1);
                //#line 2696 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VarKeyword = (List) getRhsSym(2);
                //#line 2696 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Object[] FormalDeclarator = (Object[]) getRhsSym(3);
                //#line 2698 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
            Formal f;
                        	FlagsNode fn = extractFlags(VariableModifiersopt, VarKeyword);
            Object[] o = FormalDeclarator;
            Position pos = (Position) o[0];
            Id name = (Id) o[1];
            boolean unnamed = name == null;
            if (name == null) name = nf.Id(pos, Name.makeFresh());
               List exploded = (List) o[2];
                        DepParameterExpr guard = (DepParameterExpr) o[3];
                        TypeNode type = (TypeNode) o[4];
                                                    if (type == null) type = nf.UnknownTypeNode(name != null ? name.position() : pos);
                        Expr init = (Expr) o[5];
                                                    List explodedFormals = new ArrayList();
                        for (Iterator i = exploded.iterator(); i.hasNext(); ) {
                        	Id id = (Id) i.next();
                        	explodedFormals.add(nf.Formal(id.position(), fn, nf.UnknownTypeNode(id.position()), id));
                        }
            f = nf.X10Formal(pos(), fn, type, name, explodedFormals, unnamed);
            f = (Formal) ((X10Ext) f.ext()).annotations(extractAnnotations(VariableModifiersopt));
            setResult(f);
                      break;
            }
    
            //
            // Rule 323:  FormalParameter ::= Type
            //
            case 323: {
               //#line 2722 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2720 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(1);
                //#line 2722 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
            Formal f;
            f = nf.X10Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), Type, nf.Id(pos(), Name.makeFresh()), Collections.EMPTY_LIST, true);
            setResult(f);
                      break;
            }
    
            //
            // Rule 324:  VariableModifiers ::= VariableModifier
            //
            case 324: {
               //#line 2730 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2728 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableModifier = (List) getRhsSym(1);
                //#line 2730 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new LinkedList();
                l.addAll(VariableModifier);
                setResult(l);
                      break;
            }
    
            //
            // Rule 325:  VariableModifiers ::= VariableModifiers VariableModifier
            //
            case 325: {
               //#line 2737 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2735 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableModifiers = (List) getRhsSym(1);
                //#line 2735 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableModifier = (List) getRhsSym(2);
                //#line 2737 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                VariableModifiers.addAll(VariableModifier);
                      break;
            }
    
            //
            // Rule 326:  VariableModifier ::= Annotation
            //
            case 326: {
               //#line 2743 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2741 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 2743 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(Annotation));
                      break;
            }
    
            //
            // Rule 327:  VariableModifier ::= shared
            //
            case 327: {
               //#line 2748 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2748 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), X10Flags.SHARED)));
                      break;
            }
    
            //
            // Rule 328:  MethodModifiers ::= MethodModifier
            //
            case 328: {
               //#line 2757 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2755 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List MethodModifier = (List) getRhsSym(1);
                //#line 2757 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new LinkedList();
                l.addAll(MethodModifier);
                setResult(l);
                      break;
            }
    
            //
            // Rule 329:  MethodModifiers ::= MethodModifiers MethodModifier
            //
            case 329: {
               //#line 2764 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2762 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List MethodModifiers = (List) getRhsSym(1);
                //#line 2762 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List MethodModifier = (List) getRhsSym(2);
                //#line 2764 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                MethodModifiers.addAll(MethodModifier);
                      break;
            }
    
            //
            // Rule 330:  MethodModifier ::= Annotation
            //
            case 330: {
               //#line 2770 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2768 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 2770 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(Annotation));
                      break;
            }
    
            //
            // Rule 331:  MethodModifier ::= public
            //
            case 331: {
               //#line 2775 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2775 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PUBLIC)));
                      break;
            }
    
            //
            // Rule 332:  MethodModifier ::= protected
            //
            case 332: {
               //#line 2780 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2780 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PROTECTED)));
                      break;
            }
    
            //
            // Rule 333:  MethodModifier ::= private
            //
            case 333: {
               //#line 2785 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2785 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PRIVATE)));
                      break;
            }
    
            //
            // Rule 334:  MethodModifier ::= abstract
            //
            case 334: {
               //#line 2790 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2790 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.ABSTRACT)));
                      break;
            }
    
            //
            // Rule 335:  MethodModifier ::= static
            //
            case 335: {
               //#line 2795 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2795 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.STATIC)));
                      break;
            }
    
            //
            // Rule 336:  MethodModifier ::= final
            //
            case 336: {
               //#line 2800 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2800 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.FINAL)));
                      break;
            }
    
            //
            // Rule 337:  MethodModifier ::= native
            //
            case 337: {
               //#line 2805 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2805 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.NATIVE)));
                      break;
            }
    
            //
            // Rule 338:  MethodModifier ::= strictfp
            //
            case 338: {
               //#line 2810 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2810 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.STRICTFP)));
                      break;
            }
    
            //
            // Rule 339:  MethodModifier ::= atomic
            //
            case 339: {
               //#line 2815 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2815 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), X10Flags.ATOMIC)));
                      break;
            }
    
            //
            // Rule 340:  MethodModifier ::= extern
            //
            case 340: {
               //#line 2820 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2820 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), X10Flags.EXTERN)));
                      break;
            }
    
            //
            // Rule 341:  MethodModifier ::= safe
            //
            case 341: {
               //#line 2825 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2825 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), X10Flags.SAFE)));
                      break;
            }
    
            //
            // Rule 342:  MethodModifier ::= sequential
            //
            case 342: {
               //#line 2830 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2830 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), X10Flags.SEQUENTIAL)));
                      break;
            }
    
            //
            // Rule 343:  MethodModifier ::= local
            //
            case 343: {
               //#line 2835 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2835 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), X10Flags.LOCAL)));
                      break;
            }
    
            //
            // Rule 344:  MethodModifier ::= nonblocking
            //
            case 344: {
               //#line 2840 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2840 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), X10Flags.NON_BLOCKING)));
                      break;
            }
    
            //
            // Rule 345:  MethodModifier ::= incomplete
            //
            case 345: {
               //#line 2845 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2845 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), X10Flags.INCOMPLETE)));
                      break;
            }
    
            //
            // Rule 346:  MethodModifier ::= property
            //
            case 346: {
               //#line 2850 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2850 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), X10Flags.PROPERTY)));
                      break;
            }
    
            //
            // Rule 347:  Throws ::= throws ExceptionTypeList
            //
            case 347: {
               //#line 2857 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2855 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ExceptionTypeList = (List) getRhsSym(2);
                //#line 2857 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(ExceptionTypeList);
                      break;
            }
    
            //
            // Rule 348:  ExceptionTypeList ::= ExceptionType
            //
            case 348: {
               //#line 2863 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2861 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ExceptionType = (TypeNode) getRhsSym(1);
                //#line 2863 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), TypeNode.class, false);
                l.add(ExceptionType);
                setResult(l);
                      break;
            }
    
            //
            // Rule 349:  ExceptionTypeList ::= ExceptionTypeList , ExceptionType
            //
            case 349: {
               //#line 2870 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2868 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ExceptionTypeList = (List) getRhsSym(1);
                //#line 2868 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ExceptionType = (TypeNode) getRhsSym(3);
                //#line 2870 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                ExceptionTypeList.add(ExceptionType);
                      break;
            }
    
            //
            // Rule 351:  MethodBody ::= = LastExpression ;
            //
            case 351: {
               //#line 2878 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2876 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt LastExpression = (Stmt) getRhsSym(2);
                //#line 2878 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Block(pos(), LastExpression));
                      break;
            }
    
            //
            // Rule 352:  MethodBody ::= = { BlockStatementsopt LastExpression }
            //
            case 352: {
               //#line 2883 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2881 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List BlockStatementsopt = (List) getRhsSym(3);
                //#line 2881 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt LastExpression = (Stmt) getRhsSym(4);
                //#line 2883 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new ArrayList();
                l.addAll(BlockStatementsopt);
                l.add(LastExpression);
                setResult(nf.Block(pos(), l));
                      break;
            }
    
            //
            // Rule 353:  MethodBody ::= = Block
            //
            case 353: {
               //#line 2891 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2889 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block Block = (Block) getRhsSym(2);
                //#line 2891 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Block);
                      break;
            }
    
            //
            // Rule 354:  MethodBody ::= Block
            //
            case 354: {
               //#line 2896 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2894 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block Block = (Block) getRhsSym(1);
                //#line 2896 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Block);
                      break;
            }
    
            //
            // Rule 355:  MethodBody ::= ;
            //
            case 355:
                setResult(null);
                break;

            //
            // Rule 356:  InstanceInitializer ::= Block
            //
            case 356: {
               //#line 2904 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2902 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block Block = (Block) getRhsSym(1);
                //#line 2904 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Initializer(pos(), nf.FlagsNode(pos(), Flags.NONE), Block));
                      break;
            }
    
            //
            // Rule 357:  StaticInitializer ::= static Block
            //
            case 357: {
               //#line 2910 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2908 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block Block = (Block) getRhsSym(2);
                //#line 2910 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Initializer(pos(), nf.FlagsNode(pos(getLeftSpan()), Flags.STATIC), Block));
                      break;
            }
    
            //
            // Rule 358:  SimpleTypeName ::= Identifier
            //
            case 358: {
               //#line 2916 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2914 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 2916 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new X10ParsedName(nf, ts, pos(), Identifier));
                      break;
            }
    
            //
            // Rule 359:  ConstructorModifiers ::= ConstructorModifier
            //
            case 359: {
               //#line 2922 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2920 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ConstructorModifier = (List) getRhsSym(1);
                //#line 2922 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new LinkedList();
                l.addAll(ConstructorModifier);
                setResult(l);
                      break;
            }
    
            //
            // Rule 360:  ConstructorModifiers ::= ConstructorModifiers ConstructorModifier
            //
            case 360: {
               //#line 2929 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2927 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ConstructorModifiers = (List) getRhsSym(1);
                //#line 2927 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ConstructorModifier = (List) getRhsSym(2);
                //#line 2929 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                ConstructorModifiers.addAll(ConstructorModifier);
                      break;
            }
    
            //
            // Rule 361:  ConstructorModifier ::= Annotation
            //
            case 361: {
               //#line 2935 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2933 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 2935 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(Annotation));
                      break;
            }
    
            //
            // Rule 362:  ConstructorModifier ::= public
            //
            case 362: {
               //#line 2940 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2940 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PUBLIC)));
                      break;
            }
    
            //
            // Rule 363:  ConstructorModifier ::= protected
            //
            case 363: {
               //#line 2945 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2945 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PROTECTED)));
                      break;
            }
    
            //
            // Rule 364:  ConstructorModifier ::= private
            //
            case 364: {
               //#line 2950 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2950 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PRIVATE)));
                      break;
            }
    
            //
            // Rule 365:  ConstructorModifier ::= native
            //
            case 365: {
               //#line 2955 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 2955 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.NATIVE)));
                      break;
            }
    
            //
            // Rule 366:  ConstructorBody ::= = ConstructorBlock
            //
            case 366: {
               //#line 2961 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2959 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block ConstructorBlock = (Block) getRhsSym(2);
                //#line 2961 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(ConstructorBlock);
                      break;
            }
    
            //
            // Rule 367:  ConstructorBody ::= ConstructorBlock
            //
            case 367: {
               //#line 2966 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2964 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Block ConstructorBlock = (Block) getRhsSym(1);
                //#line 2966 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(ConstructorBlock);
                      break;
            }
    
            //
            // Rule 368:  ConstructorBody ::= = ExplicitConstructorInvocation
            //
            case 368: {
               //#line 2971 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2969 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ConstructorCall ExplicitConstructorInvocation = (ConstructorCall) getRhsSym(2);
                //#line 2971 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l;
                l = new TypedList(new LinkedList(), Stmt.class, false);
                l.add(ExplicitConstructorInvocation);
                setResult(nf.Block(pos(), l));
                      break;
            }
    
            //
            // Rule 369:  ConstructorBody ::= = AssignPropertyCall
            //
            case 369: {
               //#line 2979 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2977 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt AssignPropertyCall = (Stmt) getRhsSym(2);
                //#line 2979 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l;
                l = new TypedList(new LinkedList(), Stmt.class, false);
                l.add(nf.SuperCall(pos(), Collections.EMPTY_LIST));
                l.add(AssignPropertyCall);
                setResult(nf.Block(pos(), l));
                      break;
            }
    
            //
            // Rule 370:  ConstructorBody ::= ;
            //
            case 370:
                setResult(null);
                break;

            //
            // Rule 371:  ConstructorBlock ::= { ExplicitConstructorInvocationopt BlockStatementsopt }
            //
            case 371: {
               //#line 2991 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 2989 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt ExplicitConstructorInvocationopt = (Stmt) getRhsSym(2);
                //#line 2989 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List BlockStatementsopt = (List) getRhsSym(3);
                //#line 2991 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l;
                l = new TypedList(new LinkedList(), Stmt.class, false);
                if (ExplicitConstructorInvocationopt == null)
                {
                    l.add(nf.SuperCall(pos(), Collections.EMPTY_LIST));
                }
                else
                {
                    l.add(ExplicitConstructorInvocationopt);
                }
                l.addAll(BlockStatementsopt);
                setResult(nf.Block(pos(), l));
                      break;
            }
    
            //
            // Rule 372:  Arguments ::= ( ArgumentListopt )
            //
            case 372: {
               //#line 3008 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3006 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(2);
                //#line 3008 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(ArgumentListopt);
                      break;
            }
    
            //
            // Rule 374:  InterfaceModifiers ::= InterfaceModifier
            //
            case 374: {
               //#line 3018 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3016 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List InterfaceModifier = (List) getRhsSym(1);
                //#line 3018 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new LinkedList();
                l.addAll(InterfaceModifier);
                setResult(l);
                      break;
            }
    
            //
            // Rule 375:  InterfaceModifiers ::= InterfaceModifiers InterfaceModifier
            //
            case 375: {
               //#line 3025 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3023 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List InterfaceModifiers = (List) getRhsSym(1);
                //#line 3023 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List InterfaceModifier = (List) getRhsSym(2);
                //#line 3025 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                InterfaceModifiers.addAll(InterfaceModifier);
                      break;
            }
    
            //
            // Rule 376:  InterfaceModifier ::= Annotation
            //
            case 376: {
               //#line 3031 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3029 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 3031 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(Annotation));
                      break;
            }
    
            //
            // Rule 377:  InterfaceModifier ::= public
            //
            case 377: {
               //#line 3036 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 3036 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PUBLIC)));
                      break;
            }
    
            //
            // Rule 378:  InterfaceModifier ::= protected
            //
            case 378: {
               //#line 3041 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 3041 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PROTECTED)));
                      break;
            }
    
            //
            // Rule 379:  InterfaceModifier ::= private
            //
            case 379: {
               //#line 3046 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 3046 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.PRIVATE)));
                      break;
            }
    
            //
            // Rule 380:  InterfaceModifier ::= abstract
            //
            case 380: {
               //#line 3051 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 3051 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.ABSTRACT)));
                      break;
            }
    
            //
            // Rule 381:  InterfaceModifier ::= static
            //
            case 381: {
               //#line 3056 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 3056 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.STATIC)));
                      break;
            }
    
            //
            // Rule 382:  InterfaceModifier ::= strictfp
            //
            case 382: {
               //#line 3061 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 3061 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.singletonList(nf.FlagsNode(pos(), Flags.STRICTFP)));
                      break;
            }
    
            //
            // Rule 383:  ExtendsInterfaces ::= extends Type
            //
            case 383: {
               //#line 3067 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3065 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(2);
                //#line 3067 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), TypeNode.class, false);
                l.add(Type);
                setResult(l);
                      break;
            }
    
            //
            // Rule 384:  ExtendsInterfaces ::= ExtendsInterfaces , Type
            //
            case 384: {
               //#line 3074 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3072 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ExtendsInterfaces = (List) getRhsSym(1);
                //#line 3072 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(3);
                //#line 3074 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                ExtendsInterfaces.add(Type);
                      break;
            }
    
            //
            // Rule 385:  InterfaceBody ::= { InterfaceMemberDeclarationsopt }
            //
            case 385: {
               //#line 3083 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3081 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List InterfaceMemberDeclarationsopt = (List) getRhsSym(2);
                //#line 3083 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.ClassBody(pos(), InterfaceMemberDeclarationsopt));
                      break;
            }
    
            //
            // Rule 387:  InterfaceMemberDeclarations ::= InterfaceMemberDeclarations InterfaceMemberDeclaration
            //
            case 387: {
               //#line 3090 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3088 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List InterfaceMemberDeclarations = (List) getRhsSym(1);
                //#line 3088 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List InterfaceMemberDeclaration = (List) getRhsSym(2);
                //#line 3090 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                InterfaceMemberDeclarations.addAll(InterfaceMemberDeclaration);
                // setResult(l);
                      break;
            }
    
            //
            // Rule 388:  InterfaceMemberDeclaration ::= MethodDeclaration
            //
            case 388: {
               //#line 3097 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3095 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ClassMember MethodDeclaration = (ClassMember) getRhsSym(1);
                //#line 3097 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(MethodDeclaration);
                setResult(l);
                      break;
            }
    
            //
            // Rule 389:  InterfaceMemberDeclaration ::= PropertyMethodDeclaration
            //
            case 389: {
               //#line 3104 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3102 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ClassMember PropertyMethodDeclaration = (ClassMember) getRhsSym(1);
                //#line 3104 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(PropertyMethodDeclaration);
                setResult(l);
                      break;
            }
    
            //
            // Rule 390:  InterfaceMemberDeclaration ::= FieldDeclaration
            //
            case 390: {
               //#line 3111 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3109 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FieldDeclaration = (List) getRhsSym(1);
                //#line 3111 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.addAll(FieldDeclaration);
                setResult(l);
                      break;
            }
    
            //
            // Rule 391:  InterfaceMemberDeclaration ::= ClassDeclaration
            //
            case 391: {
               //#line 3118 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3116 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ClassDecl ClassDeclaration = (ClassDecl) getRhsSym(1);
                //#line 3118 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(ClassDeclaration);
                setResult(l);
                      break;
            }
    
            //
            // Rule 392:  InterfaceMemberDeclaration ::= InterfaceDeclaration
            //
            case 392: {
               //#line 3125 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3123 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ClassDecl InterfaceDeclaration = (ClassDecl) getRhsSym(1);
                //#line 3125 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(InterfaceDeclaration);
                setResult(l);
                      break;
            }
    
            //
            // Rule 393:  InterfaceMemberDeclaration ::= TypeDefDeclaration
            //
            case 393: {
               //#line 3132 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3130 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeDecl TypeDefDeclaration = (TypeDecl) getRhsSym(1);
                //#line 3132 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(TypeDefDeclaration);
                setResult(l);
                      break;
            }
    
            //
            // Rule 394:  InterfaceMemberDeclaration ::= ;
            //
            case 394: {
               //#line 3139 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 3139 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.EMPTY_LIST);
                      break;
            }
    
            //
            // Rule 395:  Annotations ::= Annotation
            //
            case 395: {
               //#line 3145 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3143 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 3145 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), AnnotationNode.class, false);
                l.add(Annotation);
                setResult(l);
                      break;
            }
    
            //
            // Rule 396:  Annotations ::= Annotations Annotation
            //
            case 396: {
               //#line 3152 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3150 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Annotations = (List) getRhsSym(1);
                //#line 3150 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(2);
                //#line 3152 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                Annotations.add(Annotation);
                      break;
            }
    
            //
            // Rule 397:  Annotation ::= @ NamedType
            //
            case 397: {
               //#line 3158 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3156 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode NamedType = (TypeNode) getRhsSym(2);
                //#line 3158 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.AnnotationNode(pos(), NamedType));
                      break;
            }
    
            //
            // Rule 398:  SimpleName ::= Identifier
            //
            case 398: {
               //#line 3164 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3162 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 3164 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new X10ParsedName(nf, ts, pos(), Identifier));
                      break;
            }
    
            //
            // Rule 399:  Identifier ::= identifier
            //
            case 399: {
               //#line 3170 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3168 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 3170 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult( nf.Id(identifier.getPosition(), identifier.getIdentifier()));
                      break;
            }
    
            //
            // Rule 400:  ArrayInitializer ::= [ VariableInitializersopt ,opt$opt ]
            //
            case 400: {
               //#line 3178 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3176 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableInitializersopt = (List) getRhsSym(2);
                //#line 3176 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Object opt = (Object) getRhsSym(3);
                //#line 3178 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                if (VariableInitializersopt == null)
                     setResult(nf.ArrayInit(pos()));
                else setResult(nf.ArrayInit(pos(), VariableInitializersopt));
                      break;
            }
    
            //
            // Rule 401:  VariableInitializers ::= VariableInitializer
            //
            case 401: {
               //#line 3186 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3184 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr VariableInitializer = (Expr) getRhsSym(1);
                //#line 3186 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Expr.class, false);
                l.add(VariableInitializer);
                setResult(l);
                      break;
            }
    
            //
            // Rule 402:  VariableInitializers ::= VariableInitializers , VariableInitializer
            //
            case 402: {
               //#line 3193 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3191 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableInitializers = (List) getRhsSym(1);
                //#line 3191 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr VariableInitializer = (Expr) getRhsSym(3);
                //#line 3193 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                VariableInitializers.add(VariableInitializer);
                //setResult(VariableInitializers);
                      break;
            }
    
            //
            // Rule 403:  Block ::= { BlockStatementsopt }
            //
            case 403: {
               //#line 3211 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3209 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List BlockStatementsopt = (List) getRhsSym(2);
                //#line 3211 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Block(pos(), BlockStatementsopt));
                      break;
            }
    
            //
            // Rule 404:  BlockStatements ::= BlockStatement
            //
            case 404: {
               //#line 3217 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3215 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List BlockStatement = (List) getRhsSym(1);
                //#line 3217 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Stmt.class, false);
                l.addAll(BlockStatement);
                setResult(l);
                      break;
            }
    
            //
            // Rule 405:  BlockStatements ::= BlockStatements BlockStatement
            //
            case 405: {
               //#line 3224 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3222 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List BlockStatements = (List) getRhsSym(1);
                //#line 3222 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List BlockStatement = (List) getRhsSym(2);
                //#line 3224 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                BlockStatements.addAll(BlockStatement);
                //setResult(l);
                      break;
            }
    
            //
            // Rule 407:  BlockStatement ::= ClassDeclaration
            //
            case 407: {
               //#line 3232 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3230 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ClassDecl ClassDeclaration = (ClassDecl) getRhsSym(1);
                //#line 3232 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Stmt.class, false);
                l.add(nf.LocalClassDecl(pos(), ClassDeclaration));
                setResult(l);
                      break;
            }
    
            //
            // Rule 408:  BlockStatement ::= TypeDefDeclaration
            //
            case 408: {
               //#line 3239 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3237 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeDecl TypeDefDeclaration = (TypeDecl) getRhsSym(1);
                //#line 3239 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Stmt.class, false);
                l.add(nf.LocalTypeDef(pos(), TypeDefDeclaration));
                setResult(l);
                      break;
            }
    
            //
            // Rule 409:  BlockStatement ::= Statement
            //
            case 409: {
               //#line 3246 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3244 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(1);
                //#line 3246 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Stmt.class, false);
                l.add(Statement);
                setResult(l);
                      break;
            }
    
            //
            // Rule 410:  IdentifierList ::= Identifier
            //
            case 410: {
               //#line 3254 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3252 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 3254 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Id.class, false);
                l.add(Identifier);
                setResult(l);
                      break;
            }
    
            //
            // Rule 411:  IdentifierList ::= IdentifierList , Identifier
            //
            case 411: {
               //#line 3261 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3259 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List IdentifierList = (List) getRhsSym(1);
                //#line 3259 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 3261 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                IdentifierList.add(Identifier);
                      break;
            }
    
            //
            // Rule 412:  FormalDeclarator ::= Identifier : Type
            //
            case 412: {
               //#line 3267 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3265 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 3265 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(3);
                //#line 3267 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new Object[] { pos(), Identifier, Collections.EMPTY_LIST, null, Type, null });
                      break;
            }
    
            //
            // Rule 413:  FormalDeclarator ::= ( IdentifierList ) : Type
            //
            case 413: {
               //#line 3272 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3270 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List IdentifierList = (List) getRhsSym(2);
                //#line 3270 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(5);
                //#line 3272 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new Object[] { pos(), null, IdentifierList, null, Type, null });
                      break;
            }
    
            //
            // Rule 414:  FormalDeclarator ::= Identifier ( IdentifierList ) : Type
            //
            case 414: {
               //#line 3277 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3275 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 3275 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List IdentifierList = (List) getRhsSym(3);
                //#line 3275 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(6);
                //#line 3277 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new Object[] { pos(), Identifier, IdentifierList, null, Type, null });
                      break;
            }
    
            //
            // Rule 415:  FieldDeclarator ::= Identifier : Type
            //
            case 415: {
               //#line 3283 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3281 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 3281 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(3);
                //#line 3283 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new Object[] { pos(), Identifier, Collections.EMPTY_LIST, Type, null });
                      break;
            }
    
            //
            // Rule 416:  FieldDeclarator ::= Identifier ResultTypeopt = VariableInitializer
            //
            case 416: {
               //#line 3288 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3286 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 3286 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ResultTypeopt = (TypeNode) getRhsSym(2);
                //#line 3286 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr VariableInitializer = (Expr) getRhsSym(4);
                //#line 3288 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new Object[] { pos(), Identifier, Collections.EMPTY_LIST, ResultTypeopt, VariableInitializer });
                      break;
            }
    
            //
            // Rule 417:  VariableDeclarator ::= Identifier ResultTypeopt = VariableInitializer
            //
            case 417: {
               //#line 3294 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3292 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 3292 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ResultTypeopt = (TypeNode) getRhsSym(2);
                //#line 3292 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr VariableInitializer = (Expr) getRhsSym(4);
                //#line 3294 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new Object[] { pos(), Identifier, Collections.EMPTY_LIST, null, ResultTypeopt, VariableInitializer });
                      break;
            }
    
            //
            // Rule 418:  VariableDeclarator ::= ( IdentifierList ) ResultTypeopt = VariableInitializer
            //
            case 418: {
               //#line 3299 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3297 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List IdentifierList = (List) getRhsSym(2);
                //#line 3297 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ResultTypeopt = (TypeNode) getRhsSym(4);
                //#line 3297 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr VariableInitializer = (Expr) getRhsSym(6);
                //#line 3299 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new Object[] { pos(), null, IdentifierList, null, ResultTypeopt, VariableInitializer });
                      break;
            }
    
            //
            // Rule 419:  VariableDeclarator ::= Identifier ( IdentifierList ) ResultTypeopt = VariableInitializer
            //
            case 419: {
               //#line 3304 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3302 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 3302 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List IdentifierList = (List) getRhsSym(3);
                //#line 3302 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ResultTypeopt = (TypeNode) getRhsSym(5);
                //#line 3302 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr VariableInitializer = (Expr) getRhsSym(7);
                //#line 3304 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new Object[] { pos(), Identifier, IdentifierList, null, ResultTypeopt, VariableInitializer });
                      break;
            }
    
            //
            // Rule 420:  VariableDeclaratorWithType ::= Identifier ResultType = VariableInitializer
            //
            case 420: {
               //#line 3310 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3308 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 3308 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ResultType = (TypeNode) getRhsSym(2);
                //#line 3308 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr VariableInitializer = (Expr) getRhsSym(4);
                //#line 3310 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new Object[] { pos(), Identifier, Collections.EMPTY_LIST, null, ResultType, VariableInitializer });
                      break;
            }
    
            //
            // Rule 421:  VariableDeclaratorWithType ::= ( IdentifierList ) ResultType = VariableInitializer
            //
            case 421: {
               //#line 3315 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3313 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List IdentifierList = (List) getRhsSym(2);
                //#line 3313 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ResultType = (TypeNode) getRhsSym(4);
                //#line 3313 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr VariableInitializer = (Expr) getRhsSym(6);
                //#line 3315 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new Object[] { pos(), null, IdentifierList, null, ResultType, VariableInitializer });
                      break;
            }
    
            //
            // Rule 422:  VariableDeclaratorWithType ::= Identifier ( IdentifierList ) ResultType = VariableInitializer
            //
            case 422: {
               //#line 3320 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3318 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 3318 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List IdentifierList = (List) getRhsSym(3);
                //#line 3318 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode ResultType = (TypeNode) getRhsSym(5);
                //#line 3318 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr VariableInitializer = (Expr) getRhsSym(7);
                //#line 3320 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new Object[] { pos(), Identifier, IdentifierList, null, ResultType, VariableInitializer });
                      break;
            }
    
            //
            // Rule 424:  LocalVariableDeclaration ::= VariableModifiersopt VarKeyword VariableDeclarators
            //
            case 424: {
               //#line 3328 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3326 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableModifiersopt = (List) getRhsSym(1);
                //#line 3326 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VarKeyword = (List) getRhsSym(2);
                //#line 3326 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableDeclarators = (List) getRhsSym(3);
                //#line 3328 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                FlagsNode fn = extractFlags(VariableModifiersopt, VarKeyword);
    
                List l = new TypedList(new LinkedList(), LocalDecl.class, false);
                List s = new TypedList(new LinkedList(), Stmt.class, false);
                    for (Iterator i = VariableDeclarators.iterator(); i.hasNext(); )
                    {
                        Object[] o = (Object[]) i.next();
                        Position pos = (Position) o[0];
                        Id name = (Id) o[1];
                        if (name == null) name = nf.Id(pos, Name.makeFresh());
                        List exploded = (List) o[2];
                        DepParameterExpr guard = (DepParameterExpr) o[3];
                        TypeNode type = (TypeNode) o[4];
                        if (type == null) type = nf.UnknownTypeNode(name != null ? name.position() : pos);
                        Expr init = (Expr) o[5];
                        LocalDecl ld = nf.LocalDecl(pos, fn,
                                           type, name, init);
                        ld = (LocalDecl) ((X10Ext) ld.ext()).annotations(extractAnnotations(VariableModifiersopt));
                        int index = 0;
                        l.add(ld);
                        for (Iterator j = exploded.iterator(); j.hasNext(); ) {
                        	Id id = (Id) j.next();
                        	TypeNode tni = nf.UnknownTypeNode(id.position());
                        	l.add(nf.LocalDecl(id.position(), fn, tni, id, init != null ? nf.ClosureCall(init.position(), nf.Local(init.position(), name), Collections.EMPTY_LIST, Collections.<Expr>singletonList(nf.IntLit(init.position(), IntLit.INT, index))) : null));
                        	index++;
                        }
                    }
                l.addAll(s); 
                setResult(l);
                      break;
            }
    
            //
            // Rule 425:  LocalVariableDeclaration ::= VariableModifiersopt VariableDeclaratorsWithType
            //
            case 425: {
               //#line 3361 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3359 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableModifiersopt = (List) getRhsSym(1);
                //#line 3359 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableDeclaratorsWithType = (List) getRhsSym(2);
                //#line 3361 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                FlagsNode fn = extractFlags(VariableModifiersopt, Flags.FINAL);
    
                List l = new TypedList(new LinkedList(), LocalDecl.class, false);
                List s = new TypedList(new LinkedList(), Stmt.class, false);
                    for (Iterator i = VariableDeclaratorsWithType.iterator(); i.hasNext(); )
                    {
                        Object[] o = (Object[]) i.next();
                        Position pos = (Position) o[0];
                        Id name = (Id) o[1];
                        if (name == null) name = nf.Id(pos, Name.makeFresh());
                        List exploded = (List) o[2];
                        DepParameterExpr guard = (DepParameterExpr) o[3];
                        TypeNode type = (TypeNode) o[4];
                        if (type == null) type = nf.UnknownTypeNode(name != null ? name.position() : pos);
                        Expr init = (Expr) o[5];
                        LocalDecl ld = nf.LocalDecl(pos, fn,
                                           type, name, init);
                        ld = (LocalDecl) ((X10Ext) ld.ext()).annotations(extractAnnotations(VariableModifiersopt));
                        int index = 0;
                        l.add(ld);
                        for (Iterator j = exploded.iterator(); j.hasNext(); ) {
                        	Id id = (Id) j.next();
                        	// HACK: if the local is non-final, assume the type is point and the component is int
                        	TypeNode tni = nf.UnknownTypeNode(id.position());
                        	l.add(nf.LocalDecl(id.position(), fn, tni, id, init != null ? nf.ClosureCall(init.position(), nf.Local(init.position(), name), Collections.EMPTY_LIST, Collections.<Expr>singletonList(nf.IntLit(init.position(), IntLit.INT, index))) : null));
                        	index++;
                        }
                    }
                l.addAll(s); 
                setResult(l);
                      break;
            }
    
            //
            // Rule 426:  LocalVariableDeclaration ::= VariableModifiersopt VarKeyword FormalDeclarators
            //
            case 426: {
               //#line 3395 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3393 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VariableModifiersopt = (List) getRhsSym(1);
                //#line 3393 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List VarKeyword = (List) getRhsSym(2);
                //#line 3393 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalDeclarators = (List) getRhsSym(3);
                //#line 3395 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                FlagsNode fn = extractFlags(VariableModifiersopt, VarKeyword);
    
                List l = new TypedList(new LinkedList(), LocalDecl.class, false);
                List s = new TypedList(new LinkedList(), Stmt.class, false);
                    for (Iterator i = FormalDeclarators.iterator(); i.hasNext(); )
                    {
                        Object[] o = (Object[]) i.next();
                        Position pos = (Position) o[0];
                        Id name = (Id) o[1];
                        if (name == null) name = nf.Id(pos, Name.makeFresh());
                        List exploded = (List) o[2];
                        DepParameterExpr guard = (DepParameterExpr) o[3];
                        TypeNode type = (TypeNode) o[4];
                                                    if (type == null) type = nf.UnknownTypeNode(name != null ? name.position() : pos);
                        Expr init = (Expr) o[5];
                        LocalDecl ld = nf.LocalDecl(pos, fn,
                                           type, name, init);
                        ld = (LocalDecl) ((X10Ext) ld.ext()).annotations(extractAnnotations(VariableModifiersopt));
                        int index = 0;
                        l.add(ld);
                        for (Iterator j = exploded.iterator(); j.hasNext(); ) {
                        	Id id = (Id) j.next();
                        	// HACK: if the local is non-final, assume the type is point and the component is int
                        	TypeNode tni = nf.UnknownTypeNode(id.position());
                        	l.add(nf.LocalDecl(id.position(), fn, tni, id, init != null ? nf.ClosureCall(init.position(), nf.Local(init.position(), name), Collections.EMPTY_LIST, Collections.<Expr>singletonList(nf.IntLit(init.position(), IntLit.INT, index))) : null));
                        	index++;
                        }
                    }
                l.addAll(s); 
                setResult(l);
                      break;
            }
    
            //
            // Rule 428:  Primary ::= TypeName . class
            //
            case 428: {
               //#line 3436 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3434 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3436 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                if (TypeName instanceof ParsedName)
                {
                    ParsedName a = (ParsedName) TypeName;
                    setResult(nf.ClassLit(pos(), a.toType()));
                }
                else assert(false);
                      break;
            }
    
            //
            // Rule 429:  Primary ::= self
            //
            case 429: {
               //#line 3446 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 3446 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Self(pos()));
                      break;
            }
    
            //
            // Rule 430:  Primary ::= this
            //
            case 430: {
               //#line 3451 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 3451 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.This(pos()));
                      break;
            }
    
            //
            // Rule 431:  Primary ::= ClassName . this
            //
            case 431: {
               //#line 3456 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3454 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName ClassName = (ParsedName) getRhsSym(1);
                //#line 3456 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.This(pos(), ClassName.toType()));
                      break;
            }
    
            //
            // Rule 432:  Primary ::= ( Expression )
            //
            case 432: {
               //#line 3461 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3459 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(2);
                //#line 3461 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.ParExpr(pos(), Expression));
                      break;
            }
    
            //
            // Rule 438:  OperatorFunction ::= TypeName . +
            //
            case 438: {
               //#line 3472 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3470 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3472 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.UnknownTypeNode(pos());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.ADD, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 439:  OperatorFunction ::= TypeName . -
            //
            case 439: {
               //#line 3483 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3481 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3483 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.UnknownTypeNode(pos());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.SUB, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 440:  OperatorFunction ::= TypeName . *
            //
            case 440: {
               //#line 3494 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3492 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3494 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.UnknownTypeNode(pos());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.MUL, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 441:  OperatorFunction ::= TypeName . /
            //
            case 441: {
               //#line 3505 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3503 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3505 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.UnknownTypeNode(pos());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.DIV, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 442:  OperatorFunction ::= TypeName . %
            //
            case 442: {
               //#line 3516 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3514 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3516 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.UnknownTypeNode(pos());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.MOD, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 443:  OperatorFunction ::= TypeName . &
            //
            case 443: {
               //#line 3527 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3525 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3527 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.UnknownTypeNode(pos());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.BIT_AND, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 444:  OperatorFunction ::= TypeName . |
            //
            case 444: {
               //#line 3538 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3536 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3538 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.UnknownTypeNode(pos());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.BIT_OR, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 445:  OperatorFunction ::= TypeName . ^
            //
            case 445: {
               //#line 3549 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3547 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3549 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.UnknownTypeNode(pos());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.BIT_XOR, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 446:  OperatorFunction ::= TypeName . <<
            //
            case 446: {
               //#line 3560 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3558 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3560 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.UnknownTypeNode(pos());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.SHL, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 447:  OperatorFunction ::= TypeName . >>
            //
            case 447: {
               //#line 3571 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3569 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3571 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.UnknownTypeNode(pos());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.SHR, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 448:  OperatorFunction ::= TypeName . >>>
            //
            case 448: {
               //#line 3582 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3580 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3582 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.UnknownTypeNode(pos());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.USHR, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 449:  OperatorFunction ::= TypeName . <
            //
            case 449: {
               //#line 3593 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3591 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3593 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.CanonicalTypeNode(pos(), ts.Boolean());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.LT, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 450:  OperatorFunction ::= TypeName . <=
            //
            case 450: {
               //#line 3604 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3602 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3604 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.CanonicalTypeNode(pos(), ts.Boolean());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.LE, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 451:  OperatorFunction ::= TypeName . >=
            //
            case 451: {
               //#line 3615 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3613 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3615 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.CanonicalTypeNode(pos(), ts.Boolean());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.GE, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 452:  OperatorFunction ::= TypeName . >
            //
            case 452: {
               //#line 3626 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3624 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3626 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.CanonicalTypeNode(pos(), ts.Boolean());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.GT, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 453:  OperatorFunction ::= TypeName . ==
            //
            case 453: {
               //#line 3637 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3635 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3637 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.CanonicalTypeNode(pos(), ts.Boolean());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.EQ, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 454:  OperatorFunction ::= TypeName . !=
            //
            case 454: {
               //#line 3648 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3646 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName TypeName = (ParsedName) getRhsSym(1);
                //#line 3648 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<Formal> formals = new ArrayList<Formal>();
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "x")));
                formals.add(nf.Formal(pos(), nf.FlagsNode(pos(), Flags.FINAL), TypeName.toType(), nf.Id(pos(), "y")));
                TypeNode tn = nf.CanonicalTypeNode(pos(), ts.Boolean());
                setResult(nf.Closure(pos(), Collections.EMPTY_LIST, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.Binary(pos(), nf.Local(pos(), nf.Id(pos(), "x")),
                                                           Binary.NE, nf.Local(pos(), nf.Id(pos(), "y"))), true))));
                      break;
            }
    
            //
            // Rule 455:  Literal ::= IntegerLiteral$IntegerLiteral
            //
            case 455: {
               //#line 3661 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3659 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken IntegerLiteral = (IToken) getRhsIToken(1);
                //#line 3661 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                polyglot.lex.LongLiteral a = int_lit(getRhsFirstTokenIndex(1));
                setResult(nf.IntLit(pos(), IntLit.INT, a.getValue().longValue()));
                      break;
            }
    
            //
            // Rule 456:  Literal ::= LongLiteral$LongLiteral
            //
            case 456: {
               //#line 3667 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3665 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken LongLiteral = (IToken) getRhsIToken(1);
                //#line 3667 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                polyglot.lex.LongLiteral a = long_lit(getRhsFirstTokenIndex(1));
                setResult(nf.IntLit(pos(), IntLit.LONG, a.getValue().longValue()));
                      break;
            }
    
            //
            // Rule 457:  Literal ::= FloatingPointLiteral$FloatLiteral
            //
            case 457: {
               //#line 3673 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3671 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken FloatLiteral = (IToken) getRhsIToken(1);
                //#line 3673 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                polyglot.lex.FloatLiteral a = float_lit(getRhsFirstTokenIndex(1));
                setResult(nf.FloatLit(pos(), FloatLit.FLOAT, a.getValue().floatValue()));
                      break;
            }
    
            //
            // Rule 458:  Literal ::= DoubleLiteral$DoubleLiteral
            //
            case 458: {
               //#line 3679 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3677 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken DoubleLiteral = (IToken) getRhsIToken(1);
                //#line 3679 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                polyglot.lex.DoubleLiteral a = double_lit(getRhsFirstTokenIndex(1));
                setResult(nf.FloatLit(pos(), FloatLit.DOUBLE, a.getValue().doubleValue()));
                      break;
            }
    
            //
            // Rule 459:  Literal ::= BooleanLiteral
            //
            case 459: {
               //#line 3685 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3683 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                polyglot.lex.BooleanLiteral BooleanLiteral = (polyglot.lex.BooleanLiteral) getRhsSym(1);
                //#line 3685 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.BooleanLit(pos(), BooleanLiteral.getValue().booleanValue()));
                      break;
            }
    
            //
            // Rule 460:  Literal ::= CharacterLiteral$CharacterLiteral
            //
            case 460: {
               //#line 3690 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3688 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken CharacterLiteral = (IToken) getRhsIToken(1);
                //#line 3690 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                polyglot.lex.CharacterLiteral a = char_lit(getRhsFirstTokenIndex(1));
                setResult(nf.CharLit(pos(), a.getValue().charValue()));
                      break;
            }
    
            //
            // Rule 461:  Literal ::= StringLiteral$str
            //
            case 461: {
               //#line 3696 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3694 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken str = (IToken) getRhsIToken(1);
                //#line 3696 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                polyglot.lex.StringLiteral a = string_lit(getRhsFirstTokenIndex(1));
                setResult(nf.StringLit(pos(), a.getValue()));
                      break;
            }
    
            //
            // Rule 462:  Literal ::= null
            //
            case 462: {
               //#line 3702 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 3702 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.NullLit(pos()));
                      break;
            }
    
            //
            // Rule 463:  BooleanLiteral ::= true$trueLiteral
            //
            case 463: {
               //#line 3708 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3706 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken trueLiteral = (IToken) getRhsIToken(1);
                //#line 3708 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(boolean_lit(getRhsFirstTokenIndex(1)));
                      break;
            }
    
            //
            // Rule 464:  BooleanLiteral ::= false$falseLiteral
            //
            case 464: {
               //#line 3713 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3711 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken falseLiteral = (IToken) getRhsIToken(1);
                //#line 3713 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(boolean_lit(getRhsFirstTokenIndex(1)));
                      break;
            }
    
            //
            // Rule 465:  ArgumentList ::= Expression
            //
            case 465: {
               //#line 3722 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3720 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(1);
                //#line 3722 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List l = new TypedList(new LinkedList(), Expr.class, false);
                l.add(Expression);
                setResult(l);
                      break;
            }
    
            //
            // Rule 466:  ArgumentList ::= ArgumentList , Expression
            //
            case 466: {
               //#line 3729 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3727 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentList = (List) getRhsSym(1);
                //#line 3727 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 3729 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                ArgumentList.add(Expression);
                      break;
            }
    
            //
            // Rule 467:  FieldAccess ::= Primary . Identifier
            //
            case 467: {
               //#line 3735 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3733 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 3733 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 3735 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Field(pos(), Primary, Identifier));
                      break;
            }
    
            //
            // Rule 468:  FieldAccess ::= super . Identifier
            //
            case 468: {
               //#line 3740 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3738 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 3740 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Field(pos(getRightSpan()), nf.Super(pos(getLeftSpan())), Identifier));
                      break;
            }
    
            //
            // Rule 469:  FieldAccess ::= ClassName . super$sup . Identifier
            //
            case 469: {
               //#line 3745 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3743 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName ClassName = (ParsedName) getRhsSym(1);
                //#line 3743 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken sup = (IToken) getRhsIToken(3);
                //#line 3743 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(5);
                //#line 3745 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Field(pos(getRightSpan()), nf.Super(pos(getRhsFirstTokenIndex(3)), ClassName.toType()), Identifier));
                      break;
            }
    
            //
            // Rule 470:  FieldAccess ::= Primary . class$c
            //
            case 470: {
               //#line 3750 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3748 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 3748 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken c = (IToken) getRhsIToken(3);
                //#line 3750 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Field(pos(), Primary, nf.Id(pos(getRhsFirstTokenIndex(3)), "class")));
                      break;
            }
    
            //
            // Rule 471:  FieldAccess ::= super . class$c
            //
            case 471: {
               //#line 3755 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3753 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken c = (IToken) getRhsIToken(3);
                //#line 3755 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Field(pos(getRightSpan()), nf.Super(pos(getLeftSpan())), nf.Id(pos(getRhsFirstTokenIndex(3)), "class")));
                      break;
            }
    
            //
            // Rule 472:  FieldAccess ::= ClassName . super$sup . class$c
            //
            case 472: {
               //#line 3760 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3758 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName ClassName = (ParsedName) getRhsSym(1);
                //#line 3758 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken sup = (IToken) getRhsIToken(3);
                //#line 3758 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken c = (IToken) getRhsIToken(5);
                //#line 3760 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Field(pos(getRightSpan()), nf.Super(pos(getRhsFirstTokenIndex(3)), ClassName.toType()), nf.Id(pos(getRhsFirstTokenIndex(5)), "class")));
                      break;
            }
    
            //
            // Rule 473:  MethodInvocation ::= MethodName TypeArgumentsopt ( ArgumentListopt )
            //
            case 473: {
               //#line 3766 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3764 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName MethodName = (ParsedName) getRhsSym(1);
                //#line 3764 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(2);
                //#line 3764 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(4);
                //#line 3766 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.X10Call(pos(), MethodName.prefix == null
                                                             ? null
                                                             : MethodName.prefix.toReceiver(), MethodName.name, TypeArgumentsopt, ArgumentListopt));
                      break;
            }
    
            //
            // Rule 474:  MethodInvocation ::= Primary . Identifier TypeArgumentsopt ( ArgumentListopt )
            //
            case 474: {
               //#line 3773 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3771 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 3771 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 3771 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(4);
                //#line 3771 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(6);
                //#line 3773 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.X10Call(pos(), Primary, Identifier, TypeArgumentsopt, ArgumentListopt));
                      break;
            }
    
            //
            // Rule 475:  MethodInvocation ::= super . Identifier TypeArgumentsopt ( ArgumentListopt )
            //
            case 475: {
               //#line 3778 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3776 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 3776 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(4);
                //#line 3776 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(6);
                //#line 3778 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.X10Call(pos(), nf.Super(pos(getLeftSpan())), Identifier, TypeArgumentsopt, ArgumentListopt));
                      break;
            }
    
            //
            // Rule 476:  MethodInvocation ::= ClassName . super$sup . Identifier TypeArgumentsopt ( ArgumentListopt )
            //
            case 476: {
               //#line 3783 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3781 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName ClassName = (ParsedName) getRhsSym(1);
                //#line 3781 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken sup = (IToken) getRhsIToken(3);
                //#line 3781 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(5);
                //#line 3781 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(6);
                //#line 3781 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(8);
                //#line 3783 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.X10Call(pos(), nf.Super(pos(getRhsFirstTokenIndex(3)), ClassName.toType()), Identifier, TypeArgumentsopt, ArgumentListopt));
                      break;
            }
    
            //
            // Rule 477:  MethodInvocation ::= Primary TypeArgumentsopt ( ArgumentListopt )
            //
            case 477: {
               //#line 3788 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3786 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 3786 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeArgumentsopt = (List) getRhsSym(2);
                //#line 3786 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(4);
                //#line 3788 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                if (Primary instanceof Field) {
                    Field f = (Field) Primary;
                    setResult(nf.X10Call(pos(), f.target(), f.name(), TypeArgumentsopt, ArgumentListopt));
                }
                else if (Primary instanceof AmbExpr) {
                    AmbExpr f = (AmbExpr) Primary;
                    setResult(nf.X10Call(pos(), null, f.name(), TypeArgumentsopt, ArgumentListopt));
                }
                else if (Primary instanceof Here) {
                    Here f = (Here) Primary;
                    setResult(nf.X10Call(pos(), null, nf.Id(Primary.position(), Name.make("here")), TypeArgumentsopt, ArgumentListopt));
                }
                else {
                    setResult(nf.ClosureCall(pos(), Primary, TypeArgumentsopt, ArgumentListopt));
                }
                      break;
            }
    
            //
            // Rule 478:  MethodSelection ::= MethodName . TypeParametersopt ( FormalParameterListopt )
            //
            case 478: {
               //#line 3808 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3806 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName MethodName = (ParsedName) getRhsSym(1);
                //#line 3806 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeParametersopt = (List) getRhsSym(3);
                //#line 3806 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalParameterListopt = (List) getRhsSym(5);
                //#line 3808 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<TypeNode> typeArgs = toTypeArgs(TypeParametersopt);
                List<TypeParamNode> typeParams = toTypeParams(TypeParametersopt);
                List<Formal> formals = toFormals(FormalParameterListopt);
                List<Expr> actuals = toActuals(FormalParameterListopt);
                TypeNode tn = nf.UnknownTypeNode(pos());
                setResult(nf.Closure(pos(), typeParams, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(), nf.X10Call(pos(), MethodName.prefix == null
                                                             ? null
                                                             : MethodName.prefix.toReceiver(), MethodName.name, typeArgs, actuals), true))));
                      break;
            }
    
            //
            // Rule 479:  MethodSelection ::= Primary . Identifier . TypeParametersopt ( FormalParameterListopt )
            //
            case 479: {
               //#line 3821 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3819 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 3819 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 3819 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeParametersopt = (List) getRhsSym(5);
                //#line 3819 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalParameterListopt = (List) getRhsSym(7);
                //#line 3821 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<TypeNode> typeArgs = toTypeArgs(TypeParametersopt);
                List<TypeParamNode> typeParams = toTypeParams(TypeParametersopt);
                List<Formal> formals = toFormals(FormalParameterListopt);
                List<Expr> actuals = toActuals(FormalParameterListopt);
                TypeNode tn = nf.UnknownTypeNode(pos());
                setResult(nf.Closure(pos(), typeParams, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(),
                                               nf.X10Call(pos(), Primary, Identifier, typeArgs, actuals), true))));
                      break;
            }
    
            //
            // Rule 480:  MethodSelection ::= super . Identifier . TypeParametersopt ( FormalParameterListopt )
            //
            case 480: {
               //#line 3833 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3831 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(3);
                //#line 3831 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeParametersopt = (List) getRhsSym(5);
                //#line 3831 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalParameterListopt = (List) getRhsSym(7);
                //#line 3833 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<TypeNode> typeArgs = toTypeArgs(TypeParametersopt);
                List<TypeParamNode> typeParams = toTypeParams(TypeParametersopt);
                List<Formal> formals = toFormals(FormalParameterListopt);
                List<Expr> actuals = toActuals(FormalParameterListopt);
                TypeNode tn = nf.UnknownTypeNode(pos());
                setResult(nf.Closure(pos(), typeParams, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(),
                                               nf.X10Call(pos(), nf.Super(pos(getLeftSpan())), Identifier, typeArgs, actuals), true))));
                      break;
            }
    
            //
            // Rule 481:  MethodSelection ::= ClassName . super$sup . Identifier . TypeParametersopt ( FormalParameterListopt )
            //
            case 481: {
               //#line 3845 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3843 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName ClassName = (ParsedName) getRhsSym(1);
                //#line 3843 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                IToken sup = (IToken) getRhsIToken(3);
                //#line 3843 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(5);
                //#line 3843 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List TypeParametersopt = (List) getRhsSym(7);
                //#line 3843 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List FormalParameterListopt = (List) getRhsSym(9);
                //#line 3845 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                List<TypeNode> typeArgs = toTypeArgs(TypeParametersopt);
                List<TypeParamNode> typeParams = toTypeParams(TypeParametersopt);
                List<Formal> formals = toFormals(FormalParameterListopt);
                List<Expr> actuals = toActuals(FormalParameterListopt);
                TypeNode tn = nf.UnknownTypeNode(pos());
                setResult(nf.Closure(pos(), typeParams, formals, (DepParameterExpr) null, tn, Collections.EMPTY_LIST, nf.Block(pos(),
                                     nf.X10Return(pos(),
                                               nf.X10Call(pos(), nf.Super(pos(getRhsFirstTokenIndex(3)), ClassName.toType()), Identifier, typeArgs, actuals), true))));
                      break;
            }
    
            //
            // Rule 484:  PostfixExpression ::= ExpressionName
            //
            case 484: {
               //#line 3860 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3858 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName ExpressionName = (ParsedName) getRhsSym(1);
                //#line 3860 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(ExpressionName.toExpr());
                      break;
            }
    
            //
            // Rule 487:  PostIncrementExpression ::= PostfixExpression ++
            //
            case 487: {
               //#line 3868 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3866 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr PostfixExpression = (Expr) getRhsSym(1);
                //#line 3868 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Unary(pos(), PostfixExpression, Unary.POST_INC));
                      break;
            }
    
            //
            // Rule 488:  PostDecrementExpression ::= PostfixExpression --
            //
            case 488: {
               //#line 3874 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3872 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr PostfixExpression = (Expr) getRhsSym(1);
                //#line 3874 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Unary(pos(), PostfixExpression, Unary.POST_DEC));
                      break;
            }
    
            //
            // Rule 491:  UnaryExpression ::= + UnaryExpressionNotPlusMinus
            //
            case 491: {
               //#line 3882 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3880 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr UnaryExpressionNotPlusMinus = (Expr) getRhsSym(2);
                //#line 3882 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Unary(pos(), Unary.POS, UnaryExpressionNotPlusMinus));
                      break;
            }
    
            //
            // Rule 492:  UnaryExpression ::= - UnaryExpressionNotPlusMinus
            //
            case 492: {
               //#line 3887 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3885 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr UnaryExpressionNotPlusMinus = (Expr) getRhsSym(2);
                //#line 3887 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Unary(pos(), Unary.NEG, UnaryExpressionNotPlusMinus));
                      break;
            }
    
            //
            // Rule 494:  PreIncrementExpression ::= ++ UnaryExpressionNotPlusMinus
            //
            case 494: {
               //#line 3894 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3892 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr UnaryExpressionNotPlusMinus = (Expr) getRhsSym(2);
                //#line 3894 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Unary(pos(), Unary.PRE_INC, UnaryExpressionNotPlusMinus));
                      break;
            }
    
            //
            // Rule 495:  PreDecrementExpression ::= -- UnaryExpressionNotPlusMinus
            //
            case 495: {
               //#line 3900 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3898 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr UnaryExpressionNotPlusMinus = (Expr) getRhsSym(2);
                //#line 3900 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Unary(pos(), Unary.PRE_DEC, UnaryExpressionNotPlusMinus));
                      break;
            }
    
            //
            // Rule 497:  UnaryExpressionNotPlusMinus ::= ~ UnaryExpression
            //
            case 497: {
               //#line 3907 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3905 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr UnaryExpression = (Expr) getRhsSym(2);
                //#line 3907 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Unary(pos(), Unary.BIT_NOT, UnaryExpression));
                      break;
            }
    
            //
            // Rule 498:  UnaryExpressionNotPlusMinus ::= Annotations UnaryExpression
            //
            case 498: {
               //#line 3912 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3910 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List Annotations = (List) getRhsSym(1);
                //#line 3910 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr UnaryExpression = (Expr) getRhsSym(2);
                //#line 3912 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                Expr e = UnaryExpression;
                e = (Expr) ((X10Ext) e.ext()).annotations(Annotations);
                setResult(e);
                      break;
            }
    
            //
            // Rule 499:  UnaryExpressionNotPlusMinus ::= ! UnaryExpression
            //
            case 499: {
               //#line 3919 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3917 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr UnaryExpression = (Expr) getRhsSym(2);
                //#line 3919 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Unary(pos(), Unary.NOT, UnaryExpression));
                      break;
            }
    
            //
            // Rule 501:  MultiplicativeExpression ::= MultiplicativeExpression * UnaryExpression
            //
            case 501: {
               //#line 3926 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3924 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr MultiplicativeExpression = (Expr) getRhsSym(1);
                //#line 3924 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr UnaryExpression = (Expr) getRhsSym(3);
                //#line 3926 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), MultiplicativeExpression, Binary.MUL, UnaryExpression));
                      break;
            }
    
            //
            // Rule 502:  MultiplicativeExpression ::= MultiplicativeExpression / UnaryExpression
            //
            case 502: {
               //#line 3931 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3929 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr MultiplicativeExpression = (Expr) getRhsSym(1);
                //#line 3929 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr UnaryExpression = (Expr) getRhsSym(3);
                //#line 3931 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), MultiplicativeExpression, Binary.DIV, UnaryExpression));
                      break;
            }
    
            //
            // Rule 503:  MultiplicativeExpression ::= MultiplicativeExpression % UnaryExpression
            //
            case 503: {
               //#line 3936 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3934 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr MultiplicativeExpression = (Expr) getRhsSym(1);
                //#line 3934 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr UnaryExpression = (Expr) getRhsSym(3);
                //#line 3936 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), MultiplicativeExpression, Binary.MOD, UnaryExpression));
                      break;
            }
    
            //
            // Rule 505:  AdditiveExpression ::= AdditiveExpression + MultiplicativeExpression
            //
            case 505: {
               //#line 3943 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3941 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr AdditiveExpression = (Expr) getRhsSym(1);
                //#line 3941 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr MultiplicativeExpression = (Expr) getRhsSym(3);
                //#line 3943 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), AdditiveExpression, Binary.ADD, MultiplicativeExpression));
                      break;
            }
    
            //
            // Rule 506:  AdditiveExpression ::= AdditiveExpression - MultiplicativeExpression
            //
            case 506: {
               //#line 3948 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3946 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr AdditiveExpression = (Expr) getRhsSym(1);
                //#line 3946 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr MultiplicativeExpression = (Expr) getRhsSym(3);
                //#line 3948 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), AdditiveExpression, Binary.SUB, MultiplicativeExpression));
                      break;
            }
    
            //
            // Rule 508:  ShiftExpression ::= ShiftExpression << AdditiveExpression
            //
            case 508: {
               //#line 3955 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3953 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr ShiftExpression = (Expr) getRhsSym(1);
                //#line 3953 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr AdditiveExpression = (Expr) getRhsSym(3);
                //#line 3955 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), ShiftExpression, Binary.SHL, AdditiveExpression));
                      break;
            }
    
            //
            // Rule 509:  ShiftExpression ::= ShiftExpression >> AdditiveExpression
            //
            case 509: {
               //#line 3960 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3958 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr ShiftExpression = (Expr) getRhsSym(1);
                //#line 3958 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr AdditiveExpression = (Expr) getRhsSym(3);
                //#line 3960 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), ShiftExpression, Binary.SHR, AdditiveExpression));
                      break;
            }
    
            //
            // Rule 510:  ShiftExpression ::= ShiftExpression >>> AdditiveExpression
            //
            case 510: {
               //#line 3965 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3963 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr ShiftExpression = (Expr) getRhsSym(1);
                //#line 3963 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr AdditiveExpression = (Expr) getRhsSym(3);
                //#line 3965 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), ShiftExpression, Binary.USHR, AdditiveExpression));
                      break;
            }
    
            //
            // Rule 512:  RangeExpression ::= ShiftExpression$expr1 .. ShiftExpression$expr2
            //
            case 512: {
               //#line 3972 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3970 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr expr1 = (Expr) getRhsSym(1);
                //#line 3970 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr expr2 = (Expr) getRhsSym(3);
                //#line 3972 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                Expr regionCall = nf.RegionMaker(pos(), expr1, expr2);
                setResult(regionCall);
                      break;
            }
    
            //
            // Rule 515:  RelationalExpression ::= RelationalExpression < RangeExpression
            //
            case 515: {
               //#line 3981 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3979 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr RelationalExpression = (Expr) getRhsSym(1);
                //#line 3979 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr RangeExpression = (Expr) getRhsSym(3);
                //#line 3981 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), RelationalExpression, Binary.LT, RangeExpression));
                      break;
            }
    
            //
            // Rule 516:  RelationalExpression ::= RelationalExpression > RangeExpression
            //
            case 516: {
               //#line 3986 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3984 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr RelationalExpression = (Expr) getRhsSym(1);
                //#line 3984 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr RangeExpression = (Expr) getRhsSym(3);
                //#line 3986 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), RelationalExpression, Binary.GT, RangeExpression));
                      break;
            }
    
            //
            // Rule 517:  RelationalExpression ::= RelationalExpression <= RangeExpression
            //
            case 517: {
               //#line 3991 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3989 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr RelationalExpression = (Expr) getRhsSym(1);
                //#line 3989 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr RangeExpression = (Expr) getRhsSym(3);
                //#line 3991 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), RelationalExpression, Binary.LE, RangeExpression));
                      break;
            }
    
            //
            // Rule 518:  RelationalExpression ::= RelationalExpression >= RangeExpression
            //
            case 518: {
               //#line 3996 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3994 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr RelationalExpression = (Expr) getRhsSym(1);
                //#line 3994 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr RangeExpression = (Expr) getRhsSym(3);
                //#line 3996 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), RelationalExpression, Binary.GE, RangeExpression));
                      break;
            }
    
            //
            // Rule 519:  RelationalExpression ::= RelationalExpression instanceof Type
            //
            case 519: {
               //#line 4001 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 3999 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr RelationalExpression = (Expr) getRhsSym(1);
                //#line 3999 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(3);
                //#line 4001 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Instanceof(pos(), RelationalExpression, Type));
                      break;
            }
    
            //
            // Rule 520:  RelationalExpression ::= RelationalExpression in ShiftExpression
            //
            case 520: {
               //#line 4006 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 4004 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr RelationalExpression = (Expr) getRhsSym(1);
                //#line 4004 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr ShiftExpression = (Expr) getRhsSym(3);
                //#line 4006 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Contains(pos(), RelationalExpression, ShiftExpression));
                      break;
            }
    
            //
            // Rule 522:  EqualityExpression ::= EqualityExpression == RelationalExpression
            //
            case 522: {
               //#line 4013 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 4011 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr EqualityExpression = (Expr) getRhsSym(1);
                //#line 4011 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr RelationalExpression = (Expr) getRhsSym(3);
                //#line 4013 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), EqualityExpression, Binary.EQ, RelationalExpression));
                      break;
            }
    
            //
            // Rule 523:  EqualityExpression ::= EqualityExpression != RelationalExpression
            //
            case 523: {
               //#line 4018 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 4016 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr EqualityExpression = (Expr) getRhsSym(1);
                //#line 4016 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr RelationalExpression = (Expr) getRhsSym(3);
                //#line 4018 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), EqualityExpression, Binary.NE, RelationalExpression));
                      break;
            }
    
            //
            // Rule 524:  EqualityExpression ::= Type$t1 == Type$t2
            //
            case 524: {
               //#line 4023 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 4021 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode t1 = (TypeNode) getRhsSym(1);
                //#line 4021 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                TypeNode t2 = (TypeNode) getRhsSym(3);
                //#line 4023 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.SubtypeTest(pos(), t1, t2, true));
                      break;
            }
    
            //
            // Rule 526:  AndExpression ::= AndExpression & EqualityExpression
            //
            case 526: {
               //#line 4030 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 4028 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr AndExpression = (Expr) getRhsSym(1);
                //#line 4028 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr EqualityExpression = (Expr) getRhsSym(3);
                //#line 4030 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), AndExpression, Binary.BIT_AND, EqualityExpression));
                      break;
            }
    
            //
            // Rule 528:  ExclusiveOrExpression ::= ExclusiveOrExpression ^ AndExpression
            //
            case 528: {
               //#line 4037 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 4035 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr ExclusiveOrExpression = (Expr) getRhsSym(1);
                //#line 4035 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr AndExpression = (Expr) getRhsSym(3);
                //#line 4037 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), ExclusiveOrExpression, Binary.BIT_XOR, AndExpression));
                      break;
            }
    
            //
            // Rule 530:  InclusiveOrExpression ::= InclusiveOrExpression | ExclusiveOrExpression
            //
            case 530: {
               //#line 4044 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 4042 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr InclusiveOrExpression = (Expr) getRhsSym(1);
                //#line 4042 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr ExclusiveOrExpression = (Expr) getRhsSym(3);
                //#line 4044 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), InclusiveOrExpression, Binary.BIT_OR, ExclusiveOrExpression));
                      break;
            }
    
            //
            // Rule 532:  ConditionalAndExpression ::= ConditionalAndExpression && InclusiveOrExpression
            //
            case 532: {
               //#line 4051 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 4049 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr ConditionalAndExpression = (Expr) getRhsSym(1);
                //#line 4049 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr InclusiveOrExpression = (Expr) getRhsSym(3);
                //#line 4051 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), ConditionalAndExpression, Binary.COND_AND, InclusiveOrExpression));
                      break;
            }
    
            //
            // Rule 534:  ConditionalOrExpression ::= ConditionalOrExpression || ConditionalAndExpression
            //
            case 534: {
               //#line 4058 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 4056 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr ConditionalOrExpression = (Expr) getRhsSym(1);
                //#line 4056 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr ConditionalAndExpression = (Expr) getRhsSym(3);
                //#line 4058 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Binary(pos(), ConditionalOrExpression, Binary.COND_OR, ConditionalAndExpression));
                      break;
            }
    
            //
            // Rule 540:  ConditionalExpression ::= ConditionalOrExpression ? Expression : ConditionalExpression
            //
            case 540: {
               //#line 4070 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 4068 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr ConditionalOrExpression = (Expr) getRhsSym(1);
                //#line 4068 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 4068 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr ConditionalExpression = (Expr) getRhsSym(5);
                //#line 4070 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Conditional(pos(), ConditionalOrExpression, Expression, ConditionalExpression));
                      break;
            }
    
            //
            // Rule 543:  Assignment ::= LeftHandSide AssignmentOperator AssignmentExpression
            //
            case 543: {
               //#line 4079 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 4077 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr LeftHandSide = (Expr) getRhsSym(1);
                //#line 4077 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Assign.Operator AssignmentOperator = (Assign.Operator) getRhsSym(2);
                //#line 4077 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr AssignmentExpression = (Expr) getRhsSym(3);
                //#line 4079 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.Assign(pos(), LeftHandSide, AssignmentOperator, AssignmentExpression));
                      break;
            }
    
            //
            // Rule 544:  Assignment ::= ExpressionName$e1 ( ArgumentList ) AssignmentOperator AssignmentExpression
            //
            case 544: {
               //#line 4084 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 4082 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName e1 = (ParsedName) getRhsSym(1);
                //#line 4082 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentList = (List) getRhsSym(3);
                //#line 4082 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Assign.Operator AssignmentOperator = (Assign.Operator) getRhsSym(5);
                //#line 4082 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr AssignmentExpression = (Expr) getRhsSym(6);
                //#line 4084 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.SettableAssign(pos(), e1.toExpr(), ArgumentList, AssignmentOperator, AssignmentExpression));
                      break;
            }
    
            //
            // Rule 545:  Assignment ::= Primary$e1 ( ArgumentList ) AssignmentOperator AssignmentExpression
            //
            case 545: {
               //#line 4089 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 4087 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr e1 = (Expr) getRhsSym(1);
                //#line 4087 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                List ArgumentList = (List) getRhsSym(3);
                //#line 4087 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Assign.Operator AssignmentOperator = (Assign.Operator) getRhsSym(5);
                //#line 4087 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Expr AssignmentExpression = (Expr) getRhsSym(6);
                //#line 4089 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(nf.SettableAssign(pos(), e1, ArgumentList, AssignmentOperator, AssignmentExpression));
                      break;
            }
    
            //
            // Rule 546:  LeftHandSide ::= ExpressionName
            //
            case 546: {
               //#line 4095 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 4093 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                ParsedName ExpressionName = (ParsedName) getRhsSym(1);
                //#line 4095 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(ExpressionName.toExpr());
                      break;
            }
    
            //
            // Rule 548:  AssignmentOperator ::= =
            //
            case 548: {
               //#line 4102 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4102 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Assign.ASSIGN);
                      break;
            }
    
            //
            // Rule 549:  AssignmentOperator ::= *=
            //
            case 549: {
               //#line 4107 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4107 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Assign.MUL_ASSIGN);
                      break;
            }
    
            //
            // Rule 550:  AssignmentOperator ::= /=
            //
            case 550: {
               //#line 4112 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4112 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Assign.DIV_ASSIGN);
                      break;
            }
    
            //
            // Rule 551:  AssignmentOperator ::= %=
            //
            case 551: {
               //#line 4117 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4117 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Assign.MOD_ASSIGN);
                      break;
            }
    
            //
            // Rule 552:  AssignmentOperator ::= +=
            //
            case 552: {
               //#line 4122 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4122 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Assign.ADD_ASSIGN);
                      break;
            }
    
            //
            // Rule 553:  AssignmentOperator ::= -=
            //
            case 553: {
               //#line 4127 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4127 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Assign.SUB_ASSIGN);
                      break;
            }
    
            //
            // Rule 554:  AssignmentOperator ::= <<=
            //
            case 554: {
               //#line 4132 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4132 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Assign.SHL_ASSIGN);
                      break;
            }
    
            //
            // Rule 555:  AssignmentOperator ::= >>=
            //
            case 555: {
               //#line 4137 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4137 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Assign.SHR_ASSIGN);
                      break;
            }
    
            //
            // Rule 556:  AssignmentOperator ::= >>>=
            //
            case 556: {
               //#line 4142 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4142 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Assign.USHR_ASSIGN);
                      break;
            }
    
            //
            // Rule 557:  AssignmentOperator ::= &=
            //
            case 557: {
               //#line 4147 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4147 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Assign.BIT_AND_ASSIGN);
                      break;
            }
    
            //
            // Rule 558:  AssignmentOperator ::= ^=
            //
            case 558: {
               //#line 4152 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4152 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Assign.BIT_XOR_ASSIGN);
                      break;
            }
    
            //
            // Rule 559:  AssignmentOperator ::= |=
            //
            case 559: {
               //#line 4157 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4157 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Assign.BIT_OR_ASSIGN);
                      break;
            }
    
            //
            // Rule 562:  Catchesopt ::= $Empty
            //
            case 562: {
               //#line 4170 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4170 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), Catch.class, false));
                      break;
            }
    
            //
            // Rule 564:  Identifieropt ::= $Empty
            //
            case 564:
                setResult(null);
                break;

            //
            // Rule 565:  Identifieropt ::= Identifier
            //
            case 565: {
               //#line 4179 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                //#line 4177 "/Users/nystrom/work/x10/old/1.7-sexp/x10.compiler.p3/src/x10/parser/x10.g"
                Id Identifier = (Id) getRhsSym(1);
                //#line 4179 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Identifier);
                      break;
            }
    
            //
            // Rule 566:  ForUpdateopt ::= $Empty
            //
            case 566: {
               //#line 4185 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4185 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), ForUpdate.class, false));
                      break;
            }
    
            //
            // Rule 568:  Expressionopt ::= $Empty
            //
            case 568:
                setResult(null);
                break;

            //
            // Rule 570:  ForInitopt ::= $Empty
            //
            case 570: {
               //#line 4196 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4196 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), ForInit.class, false));
                      break;
            }
    
            //
            // Rule 572:  SwitchLabelsopt ::= $Empty
            //
            case 572: {
               //#line 4203 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4203 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), Case.class, false));
                      break;
            }
    
            //
            // Rule 574:  SwitchBlockStatementGroupsopt ::= $Empty
            //
            case 574: {
               //#line 4210 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4210 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), SwitchElement.class, false));
                      break;
            }
    
            //
            // Rule 576:  VariableModifiersopt ::= $Empty
            //
            case 576: {
               //#line 4217 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4217 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.EMPTY_LIST);
                      break;
            }
    
            //
            // Rule 578:  VariableInitializersopt ::= $Empty
            //
            case 578:
                setResult(null);
                break;

            //
            // Rule 580:  InterfaceMemberDeclarationsopt ::= $Empty
            //
            case 580: {
               //#line 4228 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4228 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), ClassMember.class, false));
                      break;
            }
    
            //
            // Rule 582:  ExtendsInterfacesopt ::= $Empty
            //
            case 582: {
               //#line 4235 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4235 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), TypeNode.class, false));
                      break;
            }
    
            //
            // Rule 584:  InterfaceModifiersopt ::= $Empty
            //
            case 584: {
               //#line 4242 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4242 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.EMPTY_LIST);
                      break;
            }
    
            //
            // Rule 586:  ClassBodyopt ::= $Empty
            //
            case 586:
                setResult(null);
                break;

            //
            // Rule 588:  Argumentsopt ::= $Empty
            //
            case 588: {
               //#line 4253 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4253 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), Expr.class, false));
                      break;
            }
    
            //
            // Rule 590:  ArgumentListopt ::= $Empty
            //
            case 590: {
               //#line 4260 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4260 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), Expr.class, false));
                      break;
            }
    
            //
            // Rule 592:  BlockStatementsopt ::= $Empty
            //
            case 592: {
               //#line 4267 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4267 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), Stmt.class, false));
                      break;
            }
    
            //
            // Rule 594:  ExplicitConstructorInvocationopt ::= $Empty
            //
            case 594:
                setResult(null);
                break;

            //
            // Rule 596:  ConstructorModifiersopt ::= $Empty
            //
            case 596: {
               //#line 4278 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4278 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.EMPTY_LIST);
                      break;
            }
    
            //
            // Rule 598:  FormalParameterListopt ::= $Empty
            //
            case 598: {
               //#line 4285 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4285 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), Formal.class, false));
                      break;
            }
    
            //
            // Rule 600:  Throwsopt ::= $Empty
            //
            case 600: {
               //#line 4292 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4292 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), TypeNode.class, false));
                      break;
            }
    
            //
            // Rule 602:  MethodModifiersopt ::= $Empty
            //
            case 602: {
               //#line 4299 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4299 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.EMPTY_LIST);
                      break;
            }
    
            //
            // Rule 604:  FieldModifiersopt ::= $Empty
            //
            case 604: {
               //#line 4306 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4306 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Collections.EMPTY_LIST);
                      break;
            }
    
            //
            // Rule 606:  ClassBodyDeclarationsopt ::= $Empty
            //
            case 606: {
               //#line 4313 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4313 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), ClassMember.class, false));
                      break;
            }
    
            //
            // Rule 608:  Interfacesopt ::= $Empty
            //
            case 608: {
               //#line 4320 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4320 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), TypeNode.class, false));
                      break;
            }
    
            //
            // Rule 610:  Superopt ::= $Empty
            //
            case 610:
                setResult(null);
                break;

            //
            // Rule 612:  TypeParametersopt ::= $Empty
            //
            case 612: {
               //#line 4331 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4331 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), TypeParamNode.class, false));
                      break;
            }
    
            //
            // Rule 614:  FormalParametersopt ::= $Empty
            //
            case 614: {
               //#line 4338 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4338 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), Formal.class, false));
                      break;
            }
    
            //
            // Rule 616:  Annotationsopt ::= $Empty
            //
            case 616: {
               //#line 4345 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4345 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), AnnotationNode.class, false));
                      break;
            }
    
            //
            // Rule 618:  TypeDeclarationsopt ::= $Empty
            //
            case 618: {
               //#line 4352 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4352 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), TopLevelDecl.class, false));
                      break;
            }
    
            //
            // Rule 620:  ImportDeclarationsopt ::= $Empty
            //
            case 620: {
               //#line 4359 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4359 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), Import.class, false));
                      break;
            }
    
            //
            // Rule 622:  PackageDeclarationopt ::= $Empty
            //
            case 622:
                setResult(null);
                break;

            //
            // Rule 624:  ResultTypeopt ::= $Empty
            //
            case 624:
                setResult(null);
                break;

            //
            // Rule 626:  TypeArgumentsopt ::= $Empty
            //
            case 626: {
               //#line 4374 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4374 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), TypeNode.class, false));
                      break;
            }
    
            //
            // Rule 628:  TypePropertiesopt ::= $Empty
            //
            case 628: {
               //#line 4381 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4381 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), TypePropertyNode.class, false));
                      break;
            }
    
            //
            // Rule 630:  Propertiesopt ::= $Empty
            //
            case 630: {
               //#line 4388 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4388 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(new TypedList(new LinkedList(), PropertyDecl.class, false));
                      break;
            }
    
            //
            // Rule 632:  ,opt ::= $Empty
            //
            case 632:
                setResult(null);
                break;

            //
            // Rule 634:  PrefixOp ::= +
            //
            case 634: {
               //#line 4399 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4399 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Unary.POS);
                      break;
            }
    
            //
            // Rule 635:  PrefixOp ::= -
            //
            case 635: {
               //#line 4404 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4404 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Unary.NEG);
                      break;
            }
    
            //
            // Rule 636:  PrefixOp ::= !
            //
            case 636: {
               //#line 4409 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4409 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Unary.NOT);
                      break;
            }
    
            //
            // Rule 637:  PrefixOp ::= ~
            //
            case 637: {
               //#line 4414 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4414 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Unary.BIT_NOT);
                      break;
            }
    
            //
            // Rule 638:  BinOp ::= +
            //
            case 638: {
               //#line 4420 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4420 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.ADD);
                      break;
            }
    
            //
            // Rule 639:  BinOp ::= -
            //
            case 639: {
               //#line 4425 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4425 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.SUB);
                      break;
            }
    
            //
            // Rule 640:  BinOp ::= *
            //
            case 640: {
               //#line 4430 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4430 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.MUL);
                      break;
            }
    
            //
            // Rule 641:  BinOp ::= /
            //
            case 641: {
               //#line 4435 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4435 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.DIV);
                      break;
            }
    
            //
            // Rule 642:  BinOp ::= %
            //
            case 642: {
               //#line 4440 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4440 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.MOD);
                      break;
            }
    
            //
            // Rule 643:  BinOp ::= &
            //
            case 643: {
               //#line 4445 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4445 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.BIT_AND);
                      break;
            }
    
            //
            // Rule 644:  BinOp ::= |
            //
            case 644: {
               //#line 4450 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4450 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.BIT_OR);
                      break;
            }
    
            //
            // Rule 645:  BinOp ::= ^
            //
            case 645: {
               //#line 4455 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4455 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.BIT_XOR);
                      break;
            }
    
            //
            // Rule 646:  BinOp ::= &&
            //
            case 646: {
               //#line 4460 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4460 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.COND_AND);
                      break;
            }
    
            //
            // Rule 647:  BinOp ::= ||
            //
            case 647: {
               //#line 4465 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4465 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.COND_OR);
                      break;
            }
    
            //
            // Rule 648:  BinOp ::= <<
            //
            case 648: {
               //#line 4470 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4470 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.SHL);
                      break;
            }
    
            //
            // Rule 649:  BinOp ::= >>
            //
            case 649: {
               //#line 4475 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4475 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.SHR);
                      break;
            }
    
            //
            // Rule 650:  BinOp ::= >>>
            //
            case 650: {
               //#line 4480 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4480 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.USHR);
                      break;
            }
    
            //
            // Rule 651:  BinOp ::= >=
            //
            case 651: {
               //#line 4485 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4485 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.GE);
                      break;
            }
    
            //
            // Rule 652:  BinOp ::= <=
            //
            case 652: {
               //#line 4490 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4490 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.LE);
                      break;
            }
    
            //
            // Rule 653:  BinOp ::= >
            //
            case 653: {
               //#line 4495 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4495 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.GT);
                      break;
            }
    
            //
            // Rule 654:  BinOp ::= <
            //
            case 654: {
               //#line 4500 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4500 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.LT);
                      break;
            }
    
            //
            // Rule 655:  BinOp ::= ==
            //
            case 655: {
               //#line 4508 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4508 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.EQ);
                      break;
            }
    
            //
            // Rule 656:  BinOp ::= !=
            //
            case 656: {
               //#line 4513 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                
                //#line 4513 "/Users/nystrom/work/x10/old/1.7-sexp/lpg.generator/templates/java/btParserTemplateF.gi"
                setResult(Binary.NE);
                      break;
            }
    
    
            default:
                break;
        }
        return;
    }
}

