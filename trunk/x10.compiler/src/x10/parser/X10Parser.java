
//#line 18 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
//
// Licensed Material
// (C) Copyright IBM Corp, 2006
//

//
// This is the grammar specification from the Final Draft of the generic spec.
// It has been modified by Philippe Charles and Vijay Saraswat for use with 
// X10. 
// (1) Removed TypeParameters from class/interface/method declarations
// (2) Removed TypeParameters from types.
// (3) Removed Annotations -- cause conflicts with @ used in places.
// (4) Removed EnumDeclarations.
// 12/28/2004

package x10.parser;

import lpg.runtime.*;

//#line 28 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
import polyglot.ast.Eval;
import polyglot.ast.Expr;
import polyglot.ast.FloatLit;
import polyglot.ast.ForInit;
import polyglot.ast.ForUpdate;
import polyglot.ast.Formal;
import polyglot.ast.Id;
import polyglot.ast.Import;
import polyglot.ast.IntLit;
import polyglot.ast.LocalDecl;
import polyglot.ast.ConstructorDecl;
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
import polyglot.parse.Name;
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
import polyglot.ext.x10.ast.ConstantDistMaker;
import polyglot.ext.x10.ast.X10NodeFactory;
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
import polyglot.ext.x10.ast.X10TypeNode;
import polyglot.types.SemanticException;
import polyglot.types.Type;
import polyglot.types.TypeSystem;
import polyglot.util.ErrorInfo;
import polyglot.util.ErrorQueue;
import polyglot.util.InternalCompilerError;
import polyglot.util.Position;
import polyglot.util.TypedList;

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

public class X10Parser extends PrsStream implements RuleAction, Parser
{
    private static ParseTable prs = new X10Parserprs();
    private BacktrackingParser btParser;

    public BacktrackingParser getParser() { return btParser; }
    private void setResult(Object object) { btParser.setSym1(object); }
    public Object getRhsSym(int i) { return btParser.getSym(i); }

    public int getRhsTokenIndex(int i) { return btParser.getToken(i); }
    public IToken getRhsIToken(int i) { return super.getIToken(getRhsTokenIndex(i)); }
    
    public int getRhsFirstTokenIndex(int i) { return btParser.getFirstToken(i); }
    public IToken getRhsFirstIToken(int i) { return super.getIToken(getRhsFirstTokenIndex(i)); }

    public int getRhsLastTokenIndex(int i) { return btParser.getLastToken(i); }
    public IToken getRhsLastIToken(int i) { return super.getIToken(getRhsLastTokenIndex(i)); }

    public int getLeftSpan() { return btParser.getFirstToken(); }
    public IToken getLeftIToken()  { return super.getIToken(getLeftSpan()); }

    public int getRightSpan() { return btParser.getLastToken(); }
    public IToken getRightIToken() { return super.getIToken(getRightSpan()); }

    public int getRhsErrorTokenIndex(int i)
    {
        int index = btParser.getToken(i);
        IToken err = super.getIToken(index);
        return (err instanceof ErrorToken ? index : 0);
    }
    public ErrorToken getRhsErrorIToken(int i)
    {
        int index = btParser.getToken(i);
        IToken err = super.getIToken(index);
        return (ErrorToken) (err instanceof ErrorToken ? err : null);
    }

    public X10Parser(ILexStream lexStream)
    {
        super(lexStream);

        try
        {
            super.remapTerminalSymbols(orderedTerminalSymbols(), X10Parserprs.EOFT_SYMBOL);
        }
        catch(NullExportedSymbolsException e) {
        }
        catch(NullTerminalSymbolsException e) {
        }
        catch(UnimplementedTerminalsException e)
        {
            java.util.ArrayList unimplemented_symbols = e.getSymbols();
            System.out.println("The Lexer will not scan the following token(s):");
            for (int i = 0; i < unimplemented_symbols.size(); i++)
            {
                Integer id = (Integer) unimplemented_symbols.get(i);
                System.out.println("    " + X10Parsersym.orderedTerminalSymbols[id.intValue()]);               
            }
            System.out.println();                        
        }
        catch(UndefinedEofSymbolException e)
        {
            throw new Error(new UndefinedEofSymbolException
                                ("The Lexer does not implement the Eof symbol " +
                                 X10Parsersym.orderedTerminalSymbols[X10Parserprs.EOFT_SYMBOL]));
        } 
    }

    public int numTokenKinds() { return X10Parsersym.orderedTerminalSymbols.length; }
    public String[] orderedTerminalSymbols() { return X10Parsersym.orderedTerminalSymbols; }
    public String getTokenKindName(int kind) { return X10Parsersym.orderedTerminalSymbols[kind]; }
    public int getEOFTokenKind() { return X10Parserprs.EOFT_SYMBOL; }
    public PrsStream getParseStream() { return (PrsStream) this; }

    /**
     * When constructing a SAFARI parser, a handler for error messages
     * can be passed to the parser.
     */
    /*
    private IMessageHandler handler = null;
    public void setMessageHandler(IMessageHandler handler)
    {
        this.handler = handler;
    }
    
    //
    // Redirect syntax error message to proper recipient.
    //
    public void reportError(int error_code, String location_info, int left_token, int right_token, String token_text)
    {
        if (this.handler == null)
            super.reportError(error_code,
                              location_info,
                              left_token,
                              right_token,
                              token_text);
        else 
        {
            int start_offset = super.getStartOffset(left_token),
                end_offset = (right_token > left_token 
                                          ? super.getEndOffset(right_token)
                                          : super.getEndOffset(left_token));

            String msg = ((error_code == DELETION_CODE ||
                           error_code == MISPLACED_CODE ||
                           token_text.equals(""))
                                       ? ""
                                       : (token_text + " ")) +
                         errorMsgText[error_code];

            handler.handleMessage(start_offset,
                                  end_offset - start_offset + 1,
                                  msg);
        }
    }

    //
    // Report error message for given error_token.
    //
    public final void reportErrorTokenMessage(int error_token, String msg)
    {
        if (this.handler == null)
        {
            int firsttok = super.getFirstRealToken(error_token),
                lasttok = super.getLastRealToken(error_token);
            String location = super.getFileName() + ':' +
                              (firsttok > lasttok
                                        ? (super.getEndLine(lasttok) + ":" + super.getEndColumn(lasttok))
                                        : (super.getLine(error_token) + ":" +
                                           super.getColumn(error_token) + ":" +
                                           super.getEndLine(error_token) + ":" +
                                           super.getEndColumn(error_token)))
                              + ": ";
            super.reportError((firsttok > lasttok ? ParseErrorCodes.INSERTION_CODE : ParseErrorCodes.SUBSTITUTION_CODE), location, msg);
        }
        else 
        {
            handler.handleMessage(super.getStartOffset(error_token),
                                  super.getTokenLength(error_token),
                                  msg);
        }
    }
    */

    public polyglot.ast.Node parser()
    {
        return parser(null, Integer.MAX_VALUE);
    }
    
    public polyglot.ast.Node parser(Monitor monitor)
    {
        return parser(monitor, Integer.MAX_VALUE);
    }
    
    public polyglot.ast.Node parser(int error_repair_count)
    {
        return parser(null, error_repair_count);
    }

    public polyglot.ast.Node parser(Monitor monitor, int error_repair_count)
    {
        try
        {
            btParser = new BacktrackingParser(monitor, (TokenStream) this, prs, (RuleAction) this);
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

        try
        {
            return (polyglot.ast.Node) btParser.fuzzyParse(error_repair_count);
        }
        catch (BadParseException e)
        {
            reset(e.error_token); // point to error token
            DiagnoseParser diagnoseParser = new DiagnoseParser(this, prs);
            diagnoseParser.diagnose(e.error_token);
        }

        return null;
    }

    //
    // Additional entry points, if any
    //
    

    //#line 289 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
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

    public X10Parser(LexStream lexStream, TypeSystem t, NodeFactory n, FileSource source, ErrorQueue q)
    {
        this(lexStream);
        initialize((X10TypeSystem) t,
                   (X10NodeFactory) n,
                   source,
                   q);
    }

    public String getErrorLocation(int lefttok, int righttok)
    {
        return getFileName() + ':' +
               getLine(lefttok) + ":" + getColumn(lefttok) + ":" +
               getEndLine(righttok) + ":" + getEndColumn(righttok) + ": ";
    }

    public Position getErrorPosition(int lefttok, int righttok)
    {
        return new Position(null, getFileName(),
               getLine(lefttok), getColumn(lefttok),
               getEndLine(righttok), getEndColumn(righttok));
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
            this.leftIToken = leftToken;
            this.rightIToken = rightToken;
        }

        public IToken getLeftIToken() { return leftIToken; }
        public IToken getRightIToken() { return rightIToken; }

        public String toText()
        {
            IPrsStream prsStream = leftIToken.getPrsStream();
            return new String(prsStream.getInputChars(), offset(), endOffset() - offset() + 1);
        }
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
        return super.getFileName();
    }

    public JPGPosition pos()
    {
        return new JPGPosition("",
                               super.getFileName(),
                               super.getIToken(getLeftSpan()),
                               super.getIToken(getRightSpan()));
    }

    public JPGPosition pos(int i)
    {
        return new JPGPosition("",
                               super.getFileName(),
                               super.getIToken(i),
                               super.getIToken(i));
    }

    public JPGPosition pos(int i, int j)
    {
        return new JPGPosition("",
                               super.getFileName(),
                               super.getIToken(i),
                               super.getIToken(j));
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

    private void checkTypeName(polyglot.lex.Identifier identifier) {
        String filename = file(),
               idname = identifier.getIdentifier();
        int dot = filename.lastIndexOf('.'),
            slash = filename.lastIndexOf('/', dot);
        if (slash == -1)
            slash = filename.lastIndexOf('\\', dot);
        String clean_filename = (slash >= 0 && dot >= 0 ? filename.substring(slash+1, dot) : "");
        if ((! clean_filename.equals(idname)) && clean_filename.equalsIgnoreCase(idname))
            eq.enqueue(ErrorInfo.SYNTAX_ERROR,
                       "This type name does not match the name of the containing file: " + filename.substring(slash+1),
                       identifier.getPosition());
   }


    private polyglot.lex.Operator op(int i) {
        return new Operator(pos(i), super.getName(i), super.getKind(i));
    }

    private polyglot.lex.Identifier id(int i) {
        return new Identifier(pos(i), super.getName(i), X10Parsersym.TK_IDENTIFIER);
    }
    private String comment(int i) {
        String s = super.getName(i);
        if (s != null && s.startsWith("/**") && s.endsWith("*/")) {
            return s +"\n";
        }
        return null;
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

    private X10Flags extractFlags(List l) {
        X10Flags f = X10Flags.toX10Flags(Flags.NONE);
        for (Iterator i = l.iterator(); i.hasNext(); ) {
            Object o = i.next();
            if (o instanceof X10Flags) {
                f = f.setX((X10Flags) o);
            }
            else if (o instanceof Flags) {
                f = X10Flags.toX10Flags(f.set((Flags) o));
            }
        }
        return f;
    }

    /**
     * Pretend to have parsed
     * <code>
     * new Operator.Pointwise() { public <T> apply(Formal, <T> _) MethodBody }
     * </code>
     * instead of (Formal) MethodBody. Note that Formal may have
     * exploded vars.
     * @author vj
    */
    private Expr makeInitializer(Position pos, TypeNode resultType,
                                 X10Formal f, Block body) {
      Flags flags = Flags.PUBLIC;
      TypeNode appResultType = resultType;
      if (!(resultType instanceof CanonicalTypeNode)) {
        appResultType = nf.TypeNodeFromQualifiedName(pos, "x10.compilergenerated.Parameter1");
      }
      // resultType is a.
      List l1 = new TypedList(new LinkedList(), X10Formal.class, false);
      l1.add(f);
      l1.add(nf.Formal(pos, Flags.FINAL, appResultType, nf.Id(pos, "_")));
      MethodDecl decl = nf.MethodDecl(pos, flags, appResultType,
                                    nf.Id(pos, "apply"), l1,
                                      new LinkedList(), body);
      //  new ClassOrInterfaceType ( ArgumentListopt ) ClassBodyopt
      TypeNode tOperatorPointwise = nf.TypeNodeFromQualifiedName(pos, "x10.array.Operator.Pointwise");
      List classDecl = new TypedList(new LinkedList(), MethodDecl.class, false);
      classDecl.add( decl );

      New initializer = nf.New(pos,
                               tOperatorPointwise,
                               new LinkedList(),
                               nf.ClassBody( pos, classDecl ) );
      return initializer;
    }

    /** Pretend to have parsed new <T>Array.pointwiseOp
     * { public <T> apply(Formal) MethodBody }
     * instead of (Formal) MethodBody. Note that Formal may have
     * exploded vars.
     * @author vj
    */
    private New XXmakeInitializer( Position pos, TypeNode resultType,
                                 X10Formal f, Block body ) {
      Flags flags = Flags.PUBLIC;
      // resulttype is a.
      List l1 = new TypedList(new LinkedList(), X10Formal.class, false);
      l1.add(f);
      MethodDecl decl = nf.MethodDecl(pos, flags, resultType,
                                      nf.Id(pos, "apply"), l1,
                                      new LinkedList(), body);
      //  new ClassOrInterfaceType ( ArgumentListopt ) ClassBodyopt
      // [IP] FIXME: this will break if the result is not a canonical type
      Expr tArray = nf.ExprFromQualifiedName(pos, resultType.toString() + "Array");
      TypeNode tArrayPointwiseOp = nf.TypeNodeFromQualifiedName(pos, resultType.toString() + "Array.pointwiseOp");

      List classDecl = new TypedList(new LinkedList(), MethodDecl.class, false);
      classDecl.add( decl );
      New initializer = nf.New(pos, tArray,
                             tArrayPointwiseOp,
                             new LinkedList(),
                             nf.ClassBody( pos, classDecl ) );
      return initializer;
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
        long x = parseLong(super.getName(i), radix);
        return new LongLiteral(pos(i),  x, X10Parsersym.TK_IntegerLiteral);
    }

    private polyglot.lex.LongLiteral int_lit(int i)
    {
        long x = parseLong(super.getName(i));
        return new LongLiteral(pos(i),  x, X10Parsersym.TK_IntegerLiteral);
    }

    private polyglot.lex.LongLiteral long_lit(int i, int radix)
    {
        long x = parseLong(super.getName(i), radix);
        return new LongLiteral(pos(i), x, X10Parsersym.TK_LongLiteral);
    }

    private polyglot.lex.LongLiteral long_lit(int i)
    {
        long x = parseLong(super.getName(i));
        return new LongLiteral(pos(i), x, X10Parsersym.TK_LongLiteral);
    }

    private polyglot.lex.FloatLiteral float_lit(int i)
    {
        try {
            String s = super.getName(i);
            int end_index = (s.charAt(s.length() - 1) == 'f' || s.charAt(s.length() - 1) == 'F'
                                                       ? s.length() - 1
                                                       : s.length());
            float x = Float.parseFloat(s.substring(0, end_index));
            return new FloatLiteral(pos(i), x, X10Parsersym.TK_FloatingPointLiteral);
        }
        catch (NumberFormatException e) {
            unrecoverableSyntaxError = true;
            eq.enqueue(ErrorInfo.LEXICAL_ERROR,
                       "Illegal float literal \"" + super.getName(i) + "\"", pos(i));
            return null;
        }
    }

    private polyglot.lex.DoubleLiteral double_lit(int i)
    {
        try {
            String s = super.getName(i);
            int end_index = (s.charAt(s.length() - 1) == 'd' || s.charAt(s.length() - 1) == 'D'
                                                       ? s.length() - 1
                                                       : s.length());
            double x = Double.parseDouble(s.substring(0, end_index));
            return new DoubleLiteral(pos(i), x, X10Parsersym.TK_DoubleLiteral);
        }
        catch (NumberFormatException e) {
            unrecoverableSyntaxError = true;
            eq.enqueue(ErrorInfo.LEXICAL_ERROR,
                       "Illegal float literal \"" + super.getName(i) + "\"", pos(i));
            return null;
        }
    }

    private polyglot.lex.CharacterLiteral char_lit(int i)
    {
        char x;
        String s = super.getName(i);
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
        return new BooleanLiteral(pos(i), super.getKind(i) == X10Parsersym.TK_true, super.getKind(i));
    }

    private polyglot.lex.StringLiteral string_lit(int i)
    {
        String s = super.getName(i);
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
                //#line 6 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                Name TypeName = (Name) getRhsSym(1);
                //#line 8 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                    setResult(new Name(nf,
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
                //#line 16 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                Name PackageName = (Name) getRhsSym(1);
                //#line 18 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                    setResult(new Name(nf,
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
                //#line 26 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                Name AmbiguousName = (Name) getRhsSym(1);
                //#line 28 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                    setResult(new Name(nf,
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
                //#line 36 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                Name AmbiguousName = (Name) getRhsSym(1);
                //#line 38 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                    setResult(new Name(nf,
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
                //#line 46 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                Name PackageOrTypeName = (Name) getRhsSym(1);
                //#line 48 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                    setResult(new Name(nf,
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
                //#line 56 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                Name AmbiguousName = (Name) getRhsSym(1);
                //#line 58 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                    setResult(new Name(nf,
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
                //#line 66 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 68 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Field(pos(), Primary,
                                      nf.Id(pos(getRightSpan()), "*")));
                break;
            }
     
            //
            // Rule 8:  FieldAccess ::= super . ErrorId
            //
            case 8: {
                
                //#line 74 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Field(pos(getRightSpan()), nf.Super(pos(getLeftSpan())),
                                      nf.Id(pos(getRightSpan()), "*")));
                break;
            }
     
            //
            // Rule 9:  FieldAccess ::= ClassName . super$sup . ErrorId
            //
            case 9: {
                //#line 78 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                Name ClassName = (Name) getRhsSym(1);
                //#line 78 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                IToken sup = (IToken) getRhsIToken(3);
                //#line 80 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Field(pos(getRightSpan()), nf.Super(pos(getRhsFirstTokenIndex(3)), ClassName.toType()),
                                      nf.Id(pos(getRightSpan()), "*")));
                break;
            }
     
            //
            // Rule 10:  MethodInvocation ::= MethodPrimaryPrefix ( ArgumentListopt )
            //
            case 10: {
                //#line 85 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                Object MethodPrimaryPrefix = (Object) getRhsSym(1);
                //#line 85 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                List ArgumentListopt = (List) getRhsSym(3);
                //#line 87 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                Expr Primary = (Expr) ((Object[]) MethodPrimaryPrefix)[0];
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) ((Object[]) MethodPrimaryPrefix)[1];
                setResult(nf.Call(pos(), Primary, nf.Id(pos(), identifier.getIdentifier()), ArgumentListopt));
                break;
            }
     
            //
            // Rule 11:  MethodInvocation ::= MethodSuperPrefix ( ArgumentListopt )
            //
            case 11: {
                //#line 92 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                polyglot.lex.Identifier MethodSuperPrefix = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 92 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                List ArgumentListopt = (List) getRhsSym(3);
                //#line 94 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                polyglot.lex.Identifier identifier = MethodSuperPrefix;
                setResult(nf.Call(pos(), nf.Super(pos(getLeftSpan())), nf.Id(pos(), identifier.getIdentifier()), ArgumentListopt));
                break;
            }
     
            //
            // Rule 12:  MethodInvocation ::= MethodClassNameSuperPrefix ( ArgumentListopt )
            //
            case 12: {
                //#line 98 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                Object MethodClassNameSuperPrefix = (Object) getRhsSym(1);
                //#line 98 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                List ArgumentListopt = (List) getRhsSym(3);
                //#line 100 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                Name ClassName = (Name) ((Object[]) MethodClassNameSuperPrefix)[0];
                JPGPosition super_pos = (JPGPosition) ((Object[]) MethodClassNameSuperPrefix)[1];
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) ((Object[]) MethodClassNameSuperPrefix)[2];
                setResult(nf.Call(pos(), nf.Super(super_pos, ClassName.toType()), nf.Id(pos(), identifier.getIdentifier()), ArgumentListopt));
                break;
            }
     
            //
            // Rule 13:  MethodPrimaryPrefix ::= Primary . ErrorId$ErrorId
            //
            case 13: {
                //#line 107 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 107 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                IToken ErrorId = (IToken) getRhsIToken(3);
                //#line 109 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
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
                //#line 115 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                IToken ErrorId = (IToken) getRhsIToken(3);
                //#line 117 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(id(getRhsFirstTokenIndex(3)));
                break;
            }
     
            //
            // Rule 15:  MethodClassNameSuperPrefix ::= ClassName . super$sup . ErrorId$ErrorId
            //
            case 15: {
                //#line 120 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                Name ClassName = (Name) getRhsSym(1);
                //#line 120 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                IToken sup = (IToken) getRhsIToken(3);
                //#line 120 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/MissingId.gi"
                IToken ErrorId = (IToken) getRhsIToken(5);
                //#line 122 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                Object[] a = new Object[3];
                a[0] = ClassName;
                a[1] = pos(getRhsFirstTokenIndex(3));
                a[2] = id(getRhsFirstTokenIndex(5));
                setResult(a);
                break;
            }
     
            //
            // Rule 16:  identifier ::= IDENTIFIER$ident
            //
            case 16: {
                //#line 93 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                IToken ident = (IToken) getRhsIToken(1);
                //#line 95 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                ident.setKind(X10Parsersym.TK_IDENTIFIER);
                setResult(id(getRhsFirstTokenIndex(1)));
                break;
            }
     
            //
            // Rule 19:  IntegralType ::= byte
            //
            case 19: {
                
                //#line 120 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.CanonicalTypeNode(pos(), ts.Byte()));
                break;
            }
     
            //
            // Rule 20:  IntegralType ::= char
            //
            case 20: {
                
                //#line 125 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.CanonicalTypeNode(pos(), ts.Char()));
                break;
            }
     
            //
            // Rule 21:  IntegralType ::= short
            //
            case 21: {
                
                //#line 130 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.CanonicalTypeNode(pos(), ts.Short()));
                break;
            }
     
            //
            // Rule 22:  IntegralType ::= int
            //
            case 22: {
                
                //#line 135 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.CanonicalTypeNode(pos(), ts.Int()));
                break;
            }
     
            //
            // Rule 23:  IntegralType ::= long
            //
            case 23: {
                
                //#line 140 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.CanonicalTypeNode(pos(), ts.Long()));
                break;
            }
     
            //
            // Rule 24:  FloatingPointType ::= float
            //
            case 24: {
                
                //#line 146 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.CanonicalTypeNode(pos(), ts.Float()));
                break;
            }
     
            //
            // Rule 25:  FloatingPointType ::= double
            //
            case 25: {
                
                //#line 151 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.CanonicalTypeNode(pos(), ts.Double()));
                break;
            }
     
            //
            // Rule 28:  TypeName ::= identifier
            //
            case 28: {
                //#line 174 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 176 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf, ts, pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 29:  TypeName ::= TypeName . identifier
            //
            case 29: {
                //#line 179 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Name TypeName = (Name) getRhsSym(1);
                //#line 179 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 181 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf,
                                  ts,
                                  pos(getLeftSpan(), getRightSpan()),
                                  TypeName,
                                  nf.Id(pos(getRightSpan()), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 31:  ArrayType ::= Type [ Annotationsopt ]
            //
            case 31: {
                //#line 193 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                TypeNode Type = (TypeNode) getRhsSym(1);
                //#line 193 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Object Annotationsopt = (Object) getRhsSym(3);
                //#line 195 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.array(Type, pos(), 1));
                break;
            }
     
            //
            // Rule 32:  PackageName ::= identifier
            //
            case 32: {
                //#line 239 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 241 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf, ts, pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 33:  PackageName ::= PackageName . identifier
            //
            case 33: {
                //#line 244 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Name PackageName = (Name) getRhsSym(1);
                //#line 244 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 246 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf,
                                  ts,
                                  pos(getLeftSpan(), getRightSpan()),
                                  PackageName,
                                  nf.Id(pos(getRightSpan()), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 34:  ExpressionName ::= identifier
            //
            case 34: {
                //#line 260 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 262 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf, ts, pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 35:  ExpressionName ::= AmbiguousName . identifier
            //
            case 35: {
                //#line 265 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Name AmbiguousName = (Name) getRhsSym(1);
                //#line 265 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 267 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf,
                                  ts,
                                  pos(getLeftSpan(), getRightSpan()),
                                  AmbiguousName,
                                  nf.Id(pos(getRightSpan()), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 36:  MethodName ::= identifier
            //
            case 36: {
                //#line 275 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 277 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf, ts, pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 37:  MethodName ::= AmbiguousName . identifier
            //
            case 37: {
                //#line 280 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Name AmbiguousName = (Name) getRhsSym(1);
                //#line 280 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 282 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf,
                                  ts,
                                  pos(getLeftSpan(), getRightSpan()),
                                  AmbiguousName,
                                  nf.Id(pos(getRightSpan()), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 38:  PackageOrTypeName ::= identifier
            //
            case 38: {
                //#line 290 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 292 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf, ts, pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 39:  PackageOrTypeName ::= PackageOrTypeName . identifier
            //
            case 39: {
                //#line 295 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Name PackageOrTypeName = (Name) getRhsSym(1);
                //#line 295 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 297 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf,
                                  ts,
                                  pos(getLeftSpan(), getRightSpan()),
                                  PackageOrTypeName,
                                  nf.Id(pos(getRightSpan()), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 40:  AmbiguousName ::= identifier
            //
            case 40: {
                //#line 305 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 307 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf, ts, pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 41:  AmbiguousName ::= AmbiguousName . identifier
            //
            case 41: {
                //#line 310 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Name AmbiguousName = (Name) getRhsSym(1);
                //#line 310 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 312 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf,
                                  ts,
                                  pos(getLeftSpan(), getRightSpan()),
                                  AmbiguousName,
                                  nf.Id(pos(getRightSpan()), identifier.getIdentifier())));
               break;
            }
     
            //
            // Rule 42:  CompilationUnit ::= PackageDeclarationopt ImportDeclarationsopt TypeDeclarationsopt
            //
            case 42: {
                //#line 322 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                PackageNode PackageDeclarationopt = (PackageNode) getRhsSym(1);
                //#line 322 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ImportDeclarationsopt = (List) getRhsSym(2);
                //#line 322 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List TypeDeclarationsopt = (List) getRhsSym(3);
                //#line 324 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                // Add import x10.lang.* by default.
                int token_pos = (ImportDeclarationsopt.size() == 0
                                     ? TypeDeclarationsopt.size() == 0
                                           ? super.getSize() - 1
                                           : getPrevious(getRhsFirstTokenIndex(3))
                                 : getRhsLastTokenIndex(2)
                            );
                Import x10LangImport = 
                nf.Import(pos(token_pos), Import.PACKAGE, "x10.lang");
                ImportDeclarationsopt.add(x10LangImport);
                setResult(nf.SourceFile(pos(getLeftSpan(), getRightSpan()), PackageDeclarationopt, ImportDeclarationsopt, TypeDeclarationsopt));
                break;
            }
     
            //
            // Rule 43:  ImportDeclarations ::= ImportDeclaration
            //
            case 43: {
                //#line 338 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Import ImportDeclaration = (Import) getRhsSym(1);
                //#line 340 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), Import.class, false);
                l.add(ImportDeclaration);
                setResult(l);
                break;
            }
     
            //
            // Rule 44:  ImportDeclarations ::= ImportDeclarations ImportDeclaration
            //
            case 44: {
                //#line 345 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ImportDeclarations = (List) getRhsSym(1);
                //#line 345 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Import ImportDeclaration = (Import) getRhsSym(2);
                //#line 347 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                if (ImportDeclaration != null)
                    ImportDeclarations.add(ImportDeclaration);
                //setResult(l);
                break;
            }
     
            //
            // Rule 45:  TypeDeclarations ::= TypeDeclaration
            //
            case 45: {
                //#line 353 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                ClassDecl TypeDeclaration = (ClassDecl) getRhsSym(1);
                //#line 355 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), TopLevelDecl.class, false);
                if (TypeDeclaration != null)
                    l.add(TypeDeclaration);
                setResult(l);
                break;
            }
     
            //
            // Rule 46:  TypeDeclarations ::= TypeDeclarations TypeDeclaration
            //
            case 46: {
                //#line 361 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List TypeDeclarations = (List) getRhsSym(1);
                //#line 361 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                ClassDecl TypeDeclaration = (ClassDecl) getRhsSym(2);
                //#line 363 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                if (TypeDeclaration != null)
                    TypeDeclarations.add(TypeDeclaration);
                //setResult(l);
                break;
            }
     
            //
            // Rule 49:  SingleTypeImportDeclaration ::= import TypeName ;
            //
            case 49: {
                //#line 376 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Name TypeName = (Name) getRhsSym(2);
                //#line 378 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Import(pos(getLeftSpan(), getRightSpan()), Import.CLASS, TypeName.toString()));
                break;
            }
     
            //
            // Rule 50:  TypeImportOnDemandDeclaration ::= import PackageOrTypeName . * ;
            //
            case 50: {
                //#line 382 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Name PackageOrTypeName = (Name) getRhsSym(2);
                //#line 384 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Import(pos(getLeftSpan(), getRightSpan()), Import.PACKAGE, PackageOrTypeName.toString()));
                break;
            }
     
            //
            // Rule 53:  TypeDeclaration ::= ;
            //
            case 53: {
                
                //#line 398 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(null);
                break;
            }
     
            //
            // Rule 55:  ClassModifiers ::= ClassModifier
            //
            case 55: {
                //#line 409 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ClassModifier = (List) getRhsSym(1);
                //#line 411 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new LinkedList();
                l.addAll(ClassModifier);
                setResult(l);
                break;
            }
     
            //
            // Rule 56:  ClassModifiers ::= ClassModifiers ClassModifier
            //
            case 56: {
                //#line 416 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ClassModifiers = (List) getRhsSym(1);
                //#line 416 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ClassModifier = (List) getRhsSym(2);
                //#line 418 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                ClassModifiers.addAll(ClassModifier);
                break;
            }
     
            //
            // Rule 57:  ClassModifier ::= Annotation
            //
            case 57: {
                //#line 422 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 424 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Annotation));
                break;
            }
     
            //
            // Rule 58:  ClassModifier ::= public
            //
            case 58: {
                
                //#line 429 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PUBLIC));
                break;
            }
     
            //
            // Rule 59:  ClassModifier ::= protected
            //
            case 59: {
                
                //#line 434 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PROTECTED));
                break;
            }
     
            //
            // Rule 60:  ClassModifier ::= private
            //
            case 60: {
                
                //#line 439 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PRIVATE));
                break;
            }
     
            //
            // Rule 61:  ClassModifier ::= abstract
            //
            case 61: {
                
                //#line 444 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.ABSTRACT));
                break;
            }
     
            //
            // Rule 62:  ClassModifier ::= static
            //
            case 62: {
                
                //#line 449 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.STATIC));
                break;
            }
     
            //
            // Rule 63:  ClassModifier ::= final
            //
            case 63: {
                
                //#line 454 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.FINAL));
                break;
            }
     
            //
            // Rule 64:  ClassModifier ::= strictfp
            //
            case 64: {
                
                //#line 459 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.STRICTFP));
                break;
            }
     
            //
            // Rule 65:  Super ::= extends ClassType
            //
            case 65: {
                //#line 471 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                TypeNode ClassType = (TypeNode) getRhsSym(2);
                //#line 473 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ClassType);
                break;
            }
     
            //
            // Rule 66:  Interfaces ::= implements InterfaceTypeList
            //
            case 66: {
                //#line 482 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List InterfaceTypeList = (List) getRhsSym(2);
                //#line 484 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(InterfaceTypeList);
                break;
            }
     
            //
            // Rule 67:  InterfaceTypeList ::= InterfaceType
            //
            case 67: {
                //#line 488 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                TypeNode InterfaceType = (TypeNode) getRhsSym(1);
                //#line 490 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), TypeNode.class, false);
                l.add(InterfaceType);
                setResult(l);
                break;
            }
     
            //
            // Rule 68:  InterfaceTypeList ::= InterfaceTypeList , InterfaceType
            //
            case 68: {
                //#line 495 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List InterfaceTypeList = (List) getRhsSym(1);
                //#line 495 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                TypeNode InterfaceType = (TypeNode) getRhsSym(3);
                //#line 497 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                InterfaceTypeList.add(InterfaceType);
                setResult(InterfaceTypeList);
                break;
            }
     
            //
            // Rule 69:  ClassBody ::= { ClassBodyDeclarationsopt }
            //
            case 69: {
                //#line 507 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ClassBodyDeclarationsopt = (List) getRhsSym(2);
                //#line 509 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.ClassBody(pos(getLeftSpan(), getRightSpan()), ClassBodyDeclarationsopt));
                break;
            }
     
            //
            // Rule 71:  ClassBodyDeclarations ::= ClassBodyDeclarations ClassBodyDeclaration
            //
            case 71: {
                //#line 514 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ClassBodyDeclarations = (List) getRhsSym(1);
                //#line 514 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ClassBodyDeclaration = (List) getRhsSym(2);
                //#line 516 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                ClassBodyDeclarations.addAll(ClassBodyDeclaration);
                // setResult(a);
                break;
            }
     
            //
            // Rule 73:  ClassBodyDeclaration ::= InstanceInitializer
            //
            case 73: {
                //#line 522 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Block InstanceInitializer = (Block) getRhsSym(1);
                //#line 524 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(nf.Initializer(pos(), Flags.NONE, InstanceInitializer));
                setResult(l);
                break;
            }
     
            //
            // Rule 74:  ClassBodyDeclaration ::= StaticInitializer
            //
            case 74: {
                //#line 529 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Block StaticInitializer = (Block) getRhsSym(1);
                //#line 531 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(nf.Initializer(pos(), Flags.STATIC, StaticInitializer));
                setResult(l);
                break;
            }
     
            //
            // Rule 75:  ClassBodyDeclaration ::= ConstructorDeclaration
            //
            case 75: {
                //#line 536 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                ConstructorDecl ConstructorDeclaration = (ConstructorDecl) getRhsSym(1);
                //#line 538 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(ConstructorDeclaration);
                setResult(l);
                break;
            }
     
            //
            // Rule 77:  ClassMemberDeclaration ::= MethodDeclaration
            //
            case 77: {
                //#line 545 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                MethodDecl MethodDeclaration = (MethodDecl) getRhsSym(1);
                //#line 547 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(MethodDeclaration);
                setResult(l);
                break;
            }
     
            //
            // Rule 78:  ClassMemberDeclaration ::= ClassDeclaration
            //
            case 78: {
                //#line 552 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                ClassDecl ClassDeclaration = (ClassDecl) getRhsSym(1);
                //#line 554 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(ClassDeclaration);
                setResult(l);
                break;
            }
     
            //
            // Rule 79:  ClassMemberDeclaration ::= InterfaceDeclaration
            //
            case 79: {
                //#line 559 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                ClassDecl InterfaceDeclaration = (ClassDecl) getRhsSym(1);
                //#line 561 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(InterfaceDeclaration);
                setResult(l);
                break;
            }
     
            //
            // Rule 80:  ClassMemberDeclaration ::= ;
            //
            case 80: {
                
                //#line 568 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                setResult(l);
                break;
            }
     
            //
            // Rule 81:  VariableDeclarators ::= VariableDeclarator
            //
            case 81: {
                //#line 576 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                VarDeclarator VariableDeclarator = (VarDeclarator) getRhsSym(1);
                //#line 578 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), X10VarDeclarator.class, false);
                l.add(VariableDeclarator);
                setResult(l);
                break;
            }
     
            //
            // Rule 82:  VariableDeclarators ::= VariableDeclarators , VariableDeclarator
            //
            case 82: {
                //#line 583 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List VariableDeclarators = (List) getRhsSym(1);
                //#line 583 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                VarDeclarator VariableDeclarator = (VarDeclarator) getRhsSym(3);
                //#line 585 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                VariableDeclarators.add(VariableDeclarator);
                // setResult(VariableDeclarators);
                break;
            }
     
            //
            // Rule 84:  VariableDeclarator ::= VariableDeclaratorId = VariableInitializer
            //
            case 84: {
                //#line 591 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                X10VarDeclarator VariableDeclaratorId = (X10VarDeclarator) getRhsSym(1);
                //#line 591 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr VariableInitializer = (Expr) getRhsSym(3);
                //#line 593 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                VariableDeclaratorId.init = VariableInitializer;
                VariableDeclaratorId.position(pos(((JPGPosition) VariableDeclaratorId.pos), ((JPGPosition) VariableInitializer.position())));
                // setResult(VariableDeclaratorId);
                break;
            }
     
            //
            // Rule 85:  TraditionalVariableDeclaratorId ::= identifier
            //
            case 85: {
                //#line 599 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 601 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new X10VarDeclarator(pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 86:  TraditionalVariableDeclaratorId ::= TraditionalVariableDeclaratorId [ ]
            //
            case 86: {
                //#line 604 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                X10VarDeclarator TraditionalVariableDeclaratorId = (X10VarDeclarator) getRhsSym(1);
                //#line 606 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                TraditionalVariableDeclaratorId.dims++;
                TraditionalVariableDeclaratorId.position(pos());
                // setResult(a);
                break;
            }
     
            //
            // Rule 88:  VariableDeclaratorId ::= identifier [ IdentifierList ]
            //
            case 88: {
                //#line 613 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 613 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List IdentifierList = (List) getRhsSym(3);
                //#line 615 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new X10VarDeclarator(pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier()), IdentifierList));
                break;
            }
     
            //
            // Rule 89:  VariableDeclaratorId ::= [ IdentifierList ]
            //
            case 89: {
                //#line 618 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List IdentifierList = (List) getRhsSym(2);
                //#line 620 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new X10VarDeclarator(pos(), IdentifierList));
                break;
            }
     
            //
            // Rule 92:  FieldModifiers ::= FieldModifier
            //
            case 92: {
                //#line 627 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List FieldModifier = (List) getRhsSym(1);
                //#line 629 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new LinkedList();
                l.addAll(FieldModifier);
                setResult(l);
                break;
            }
     
            //
            // Rule 93:  FieldModifiers ::= FieldModifiers FieldModifier
            //
            case 93: {
                //#line 634 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List FieldModifiers = (List) getRhsSym(1);
                //#line 634 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List FieldModifier = (List) getRhsSym(2);
                //#line 636 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                FieldModifiers.addAll(FieldModifier);
                break;
            }
     
            //
            // Rule 94:  FieldModifier ::= Annotation
            //
            case 94: {
                //#line 640 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 642 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Annotation));
                break;
            }
     
            //
            // Rule 95:  FieldModifier ::= public
            //
            case 95: {
                
                //#line 647 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PUBLIC));
                break;
            }
     
            //
            // Rule 96:  FieldModifier ::= protected
            //
            case 96: {
                
                //#line 652 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PROTECTED));
                break;
            }
     
            //
            // Rule 97:  FieldModifier ::= private
            //
            case 97: {
                
                //#line 657 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PRIVATE));
                break;
            }
     
            //
            // Rule 98:  FieldModifier ::= static
            //
            case 98: {
                
                //#line 662 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.STATIC));
                break;
            }
     
            //
            // Rule 99:  FieldModifier ::= final
            //
            case 99: {
                
                //#line 667 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.FINAL));
                break;
            }
     
            //
            // Rule 100:  FieldModifier ::= transient
            //
            case 100: {
                
                //#line 672 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.TRANSIENT));
                break;
            }
     
            //
            // Rule 102:  ResultType ::= void
            //
            case 102: {
                
                //#line 689 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.CanonicalTypeNode(pos(), ts.Void()));
                break;
            }
     
            //
            // Rule 103:  FormalParameterList ::= LastFormalParameter
            //
            case 103: {
                //#line 709 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Formal LastFormalParameter = (Formal) getRhsSym(1);
                //#line 711 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), Formal.class, false);
                l.add(LastFormalParameter);
                setResult(l);
                break;
            }
     
            //
            // Rule 104:  FormalParameterList ::= FormalParameters , LastFormalParameter
            //
            case 104: {
                //#line 716 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List FormalParameters = (List) getRhsSym(1);
                //#line 716 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Formal LastFormalParameter = (Formal) getRhsSym(3);
                //#line 718 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                FormalParameters.add(LastFormalParameter);
                // setResult(FormalParameters);
                break;
            }
     
            //
            // Rule 105:  FormalParameters ::= FormalParameter
            //
            case 105: {
                //#line 723 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                X10Formal FormalParameter = (X10Formal) getRhsSym(1);
                //#line 725 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), Formal.class, false);
                l.add(FormalParameter);
                setResult(l);
                break;
            }
     
            //
            // Rule 106:  FormalParameters ::= FormalParameters , FormalParameter
            //
            case 106: {
                //#line 730 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List FormalParameters = (List) getRhsSym(1);
                //#line 730 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                X10Formal FormalParameter = (X10Formal) getRhsSym(3);
                //#line 732 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                FormalParameters.add(FormalParameter);
                // setResult(FormalParameters);
                break;
            }
     
            //
            // Rule 107:  FormalParameter ::= VariableModifiersopt Type VariableDeclaratorId
            //
            case 107: {
                //#line 737 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List VariableModifiersopt = (List) getRhsSym(1);
                //#line 737 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                TypeNode Type = (TypeNode) getRhsSym(2);
                //#line 737 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                X10VarDeclarator VariableDeclaratorId = (X10VarDeclarator) getRhsSym(3);
                //#line 739 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
            Formal f;
        if (VariableDeclaratorId != null)
                f = nf.X10Formal(pos(), extractFlags(VariableModifiersopt), nf.array(Type, pos(getRhsFirstTokenIndex(2), getRhsLastTokenIndex(2)), VariableDeclaratorId.dims), VariableDeclaratorId.name, VariableDeclaratorId.names());
            else
                f = nf.Formal(pos(), extractFlags(VariableModifiersopt), nf.array(Type, pos(getRhsFirstTokenIndex(2), getRhsLastTokenIndex(2)), 1), nf.Id(pos(), ""));
            f = (Formal) ((X10Ext) f.ext()).annotations(extractAnnotations(VariableModifiersopt));
            setResult(f);
                break;
            }
     
            //
            // Rule 108:  VariableModifiers ::= VariableModifier
            //
            case 108: {
                //#line 749 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List VariableModifier = (List) getRhsSym(1);
                //#line 751 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new LinkedList();
                l.addAll(VariableModifier);
                setResult(l);
                break;
            }
     
            //
            // Rule 109:  VariableModifiers ::= VariableModifiers VariableModifier
            //
            case 109: {
                //#line 756 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List VariableModifiers = (List) getRhsSym(1);
                //#line 756 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List VariableModifier = (List) getRhsSym(2);
                //#line 758 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                VariableModifiers.addAll(VariableModifier);
                break;
            }
     
            //
            // Rule 110:  VariableModifier ::= final
            //
            case 110: {
                
                //#line 764 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.FINAL));
                break;
            }
     
            //
            // Rule 111:  VariableModifier ::= Annotation
            //
            case 111: {
                //#line 767 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 769 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Annotation));
                break;
            }
     
            //
            // Rule 112:  LastFormalParameter ::= VariableModifiersopt Type ...opt$opt VariableDeclaratorId
            //
            case 112: {
                //#line 773 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List VariableModifiersopt = (List) getRhsSym(1);
                //#line 773 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                TypeNode Type = (TypeNode) getRhsSym(2);
                //#line 773 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Object opt = (Object) getRhsSym(3);
                //#line 773 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                X10VarDeclarator VariableDeclaratorId = (X10VarDeclarator) getRhsSym(4);
                //#line 775 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                assert(opt == null);
                Formal f = nf.X10Formal(pos(), extractFlags(VariableModifiersopt), nf.array(Type, pos(getRhsFirstTokenIndex(2), getRhsLastTokenIndex(2)), VariableDeclaratorId.dims), VariableDeclaratorId.name, VariableDeclaratorId.names());
                f = (Formal) ((X10Ext) f.ext()).annotations(extractAnnotations(VariableModifiersopt));
                setResult(f);
                break;
            }
     
            //
            // Rule 113:  MethodModifiers ::= MethodModifier
            //
            case 113: {
                //#line 788 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List MethodModifier = (List) getRhsSym(1);
                //#line 790 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new LinkedList();
                l.addAll(MethodModifier);
                setResult(l);
                break;
            }
     
            //
            // Rule 114:  MethodModifiers ::= MethodModifiers MethodModifier
            //
            case 114: {
                //#line 795 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List MethodModifiers = (List) getRhsSym(1);
                //#line 795 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List MethodModifier = (List) getRhsSym(2);
                //#line 797 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                MethodModifiers.addAll(MethodModifier);
                break;
            }
     
            //
            // Rule 115:  MethodModifier ::= Annotation
            //
            case 115: {
                //#line 801 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 803 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Annotation));
                break;
            }
     
            //
            // Rule 116:  MethodModifier ::= public
            //
            case 116: {
                
                //#line 808 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PUBLIC));
                break;
            }
     
            //
            // Rule 117:  MethodModifier ::= protected
            //
            case 117: {
                
                //#line 813 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PROTECTED));
                break;
            }
     
            //
            // Rule 118:  MethodModifier ::= private
            //
            case 118: {
                
                //#line 818 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PRIVATE));
                break;
            }
     
            //
            // Rule 119:  MethodModifier ::= abstract
            //
            case 119: {
                
                //#line 823 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.ABSTRACT));
                break;
            }
     
            //
            // Rule 120:  MethodModifier ::= static
            //
            case 120: {
                
                //#line 828 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.STATIC));
                break;
            }
     
            //
            // Rule 121:  MethodModifier ::= final
            //
            case 121: {
                
                //#line 833 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.FINAL));
                break;
            }
     
            //
            // Rule 122:  MethodModifier ::= native
            //
            case 122: {
                
                //#line 843 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.NATIVE));
                break;
            }
     
            //
            // Rule 123:  MethodModifier ::= strictfp
            //
            case 123: {
                
                //#line 848 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.STRICTFP));
                break;
            }
     
            //
            // Rule 124:  Throws ::= throws ExceptionTypeList
            //
            case 124: {
                //#line 852 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ExceptionTypeList = (List) getRhsSym(2);
                //#line 854 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ExceptionTypeList);
                break;
            }
     
            //
            // Rule 125:  ExceptionTypeList ::= ExceptionType
            //
            case 125: {
                //#line 858 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                TypeNode ExceptionType = (TypeNode) getRhsSym(1);
                //#line 860 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), TypeNode.class, false);
                l.add(ExceptionType);
                setResult(l);
                break;
            }
     
            //
            // Rule 126:  ExceptionTypeList ::= ExceptionTypeList , ExceptionType
            //
            case 126: {
                //#line 865 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ExceptionTypeList = (List) getRhsSym(1);
                //#line 865 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                TypeNode ExceptionType = (TypeNode) getRhsSym(3);
                //#line 867 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                ExceptionTypeList.add(ExceptionType);
                // setResult(ExceptionTypeList);
                break;
            }
     
            //
            // Rule 129:  MethodBody ::= ;
            //
            case 129:
                setResult(null);
                break;
 
            //
            // Rule 131:  StaticInitializer ::= static Block
            //
            case 131: {
                //#line 887 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Block Block = (Block) getRhsSym(2);
                //#line 889 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Block);
                break;
            }
     
            //
            // Rule 132:  SimpleTypeName ::= identifier
            //
            case 132: {
                //#line 904 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 906 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf, ts, pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 133:  ConstructorModifiers ::= ConstructorModifier
            //
            case 133: {
                //#line 910 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ConstructorModifier = (List) getRhsSym(1);
                //#line 912 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new LinkedList();
                l.addAll(ConstructorModifier);
                setResult(l);
                break;
            }
     
            //
            // Rule 134:  ConstructorModifiers ::= ConstructorModifiers ConstructorModifier
            //
            case 134: {
                //#line 917 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ConstructorModifiers = (List) getRhsSym(1);
                //#line 917 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ConstructorModifier = (List) getRhsSym(2);
                //#line 919 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                ConstructorModifiers.addAll(ConstructorModifier);
                break;
            }
     
            //
            // Rule 135:  ConstructorModifier ::= Annotation
            //
            case 135: {
                //#line 923 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 925 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Annotation));
                break;
            }
     
            //
            // Rule 136:  ConstructorModifier ::= public
            //
            case 136: {
                
                //#line 930 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PUBLIC));
                break;
            }
     
            //
            // Rule 137:  ConstructorModifier ::= protected
            //
            case 137: {
                
                //#line 935 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PROTECTED));
                break;
            }
     
            //
            // Rule 138:  ConstructorModifier ::= private
            //
            case 138: {
                
                //#line 940 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PRIVATE));
                break;
            }
     
            //
            // Rule 139:  ConstructorBody ::= { ExplicitConstructorInvocationopt BlockStatementsopt }
            //
            case 139: {
                //#line 944 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Stmt ExplicitConstructorInvocationopt = (Stmt) getRhsSym(2);
                //#line 944 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List BlockStatementsopt = (List) getRhsSym(3);
                //#line 946 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
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
            // Rule 140:  Arguments ::= ( ArgumentListopt )
            //
            case 140: {
                //#line 979 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ArgumentListopt = (List) getRhsSym(2);
                //#line 981 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ArgumentListopt);
                break;
            }
     
            //
            // Rule 142:  InterfaceModifiers ::= InterfaceModifier
            //
            case 142: {
                //#line 996 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List InterfaceModifier = (List) getRhsSym(1);
                //#line 998 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new LinkedList();
                l.addAll(InterfaceModifier);
                setResult(l);
                break;
            }
     
            //
            // Rule 143:  InterfaceModifiers ::= InterfaceModifiers InterfaceModifier
            //
            case 143: {
                //#line 1003 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List InterfaceModifiers = (List) getRhsSym(1);
                //#line 1003 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List InterfaceModifier = (List) getRhsSym(2);
                //#line 1005 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                InterfaceModifiers.addAll(InterfaceModifier);
                break;
            }
     
            //
            // Rule 144:  InterfaceModifier ::= Annotation
            //
            case 144: {
                //#line 1009 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 1011 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Annotation));
                break;
            }
     
            //
            // Rule 145:  InterfaceModifier ::= public
            //
            case 145: {
                
                //#line 1016 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PUBLIC));
                break;
            }
     
            //
            // Rule 146:  InterfaceModifier ::= protected
            //
            case 146: {
                
                //#line 1021 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PROTECTED));
                break;
            }
     
            //
            // Rule 147:  InterfaceModifier ::= private
            //
            case 147: {
                
                //#line 1026 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PRIVATE));
                break;
            }
     
            //
            // Rule 148:  InterfaceModifier ::= abstract
            //
            case 148: {
                
                //#line 1031 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.ABSTRACT));
                break;
            }
     
            //
            // Rule 149:  InterfaceModifier ::= static
            //
            case 149: {
                
                //#line 1036 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.STATIC));
                break;
            }
     
            //
            // Rule 150:  InterfaceModifier ::= strictfp
            //
            case 150: {
                
                //#line 1041 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.STRICTFP));
                break;
            }
     
            //
            // Rule 151:  ExtendsInterfaces ::= extends InterfaceType
            //
            case 151: {
                //#line 1045 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                TypeNode InterfaceType = (TypeNode) getRhsSym(2);
                //#line 1047 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), TypeNode.class, false);
                l.add(InterfaceType);
                setResult(l);
                break;
            }
     
            //
            // Rule 152:  ExtendsInterfaces ::= ExtendsInterfaces , InterfaceType
            //
            case 152: {
                //#line 1052 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ExtendsInterfaces = (List) getRhsSym(1);
                //#line 1052 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                TypeNode InterfaceType = (TypeNode) getRhsSym(3);
                //#line 1054 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                ExtendsInterfaces.add(InterfaceType);
                // setResult(ExtendsInterfaces);
                break;
            }
     
            //
            // Rule 153:  InterfaceBody ::= { InterfaceMemberDeclarationsopt }
            //
            case 153: {
                //#line 1064 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List InterfaceMemberDeclarationsopt = (List) getRhsSym(2);
                //#line 1066 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.ClassBody(pos(), InterfaceMemberDeclarationsopt));
                break;
            }
     
            //
            // Rule 155:  InterfaceMemberDeclarations ::= InterfaceMemberDeclarations InterfaceMemberDeclaration
            //
            case 155: {
                //#line 1071 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List InterfaceMemberDeclarations = (List) getRhsSym(1);
                //#line 1071 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List InterfaceMemberDeclaration = (List) getRhsSym(2);
                //#line 1073 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                InterfaceMemberDeclarations.addAll(InterfaceMemberDeclaration);
                // setResult(l);
                break;
            }
     
            //
            // Rule 157:  InterfaceMemberDeclaration ::= AbstractMethodDeclaration
            //
            case 157: {
                //#line 1079 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                MethodDecl AbstractMethodDeclaration = (MethodDecl) getRhsSym(1);
                //#line 1081 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(AbstractMethodDeclaration);
                setResult(l);
                break;
            }
     
            //
            // Rule 158:  InterfaceMemberDeclaration ::= ClassDeclaration
            //
            case 158: {
                //#line 1086 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                ClassDecl ClassDeclaration = (ClassDecl) getRhsSym(1);
                //#line 1088 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(ClassDeclaration);
                setResult(l);
                break;
            }
     
            //
            // Rule 159:  InterfaceMemberDeclaration ::= InterfaceDeclaration
            //
            case 159: {
                //#line 1093 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                ClassDecl InterfaceDeclaration = (ClassDecl) getRhsSym(1);
                //#line 1095 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                l.add(InterfaceDeclaration);
                setResult(l);
                break;
            }
     
            //
            // Rule 160:  InterfaceMemberDeclaration ::= ;
            //
            case 160: {
                
                //#line 1102 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.EMPTY_LIST);
                break;
            }
     
            //
            // Rule 161:  ConstantDeclaration ::= ConstantModifiersopt Type VariableDeclarators
            //
            case 161: {
                //#line 1106 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ConstantModifiersopt = (List) getRhsSym(1);
                //#line 1106 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                TypeNode Type = (TypeNode) getRhsSym(2);
                //#line 1106 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List VariableDeclarators = (List) getRhsSym(3);
                //#line 1108 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                for (Iterator i = VariableDeclarators.iterator(); i.hasNext();)
                {
                    X10VarDeclarator d = (X10VarDeclarator) i.next();
                    if (d.hasExplodedVars())
                      // TODO: Report this exception correctly.
                      throw new Error("Field Declarations may not have exploded variables." + pos());
                    FieldDecl fd = nf.FieldDecl(pos(getRhsFirstTokenIndex(2), getRightSpan()),
                                       extractFlags(ConstantModifiersopt),
                                       nf.array(Type, pos(getRhsFirstTokenIndex(2), getRhsLastTokenIndex(2)), d.dims),
                                       d.name,
                                       d.init);
                    fd = (FieldDecl) ((X10Ext) fd.ext()).annotations(extractAnnotations(ConstantModifiersopt));
                    l.add(fd);
                }
                setResult(l);
                break;
            }
     
            //
            // Rule 162:  ConstantModifiers ::= ConstantModifier
            //
            case 162: {
                //#line 1127 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ConstantModifier = (List) getRhsSym(1);
                //#line 1129 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new LinkedList();
                l.addAll(ConstantModifier);
                setResult(l);
                break;
            }
     
            //
            // Rule 163:  ConstantModifiers ::= ConstantModifiers ConstantModifier
            //
            case 163: {
                //#line 1134 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ConstantModifiers = (List) getRhsSym(1);
                //#line 1134 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ConstantModifier = (List) getRhsSym(2);
                //#line 1136 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                ConstantModifiers.addAll(ConstantModifier);
                break;
            }
     
            //
            // Rule 164:  ConstantModifier ::= Annotation
            //
            case 164: {
                //#line 1140 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 1142 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Annotation));
                break;
            }
     
            //
            // Rule 165:  ConstantModifier ::= public
            //
            case 165: {
                
                //#line 1147 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PUBLIC));
                break;
            }
     
            //
            // Rule 166:  ConstantModifier ::= static
            //
            case 166: {
                
                //#line 1152 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.STATIC));
                break;
            }
     
            //
            // Rule 167:  ConstantModifier ::= final
            //
            case 167: {
                
                //#line 1157 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.FINAL));
                break;
            }
     
            //
            // Rule 168:  AbstractMethodModifiers ::= AbstractMethodModifier
            //
            case 168: {
                //#line 1163 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List AbstractMethodModifier = (List) getRhsSym(1);
                //#line 1165 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new LinkedList();
                l.addAll(AbstractMethodModifier);
                setResult(l);
                break;
            }
     
            //
            // Rule 169:  AbstractMethodModifiers ::= AbstractMethodModifiers AbstractMethodModifier
            //
            case 169: {
                //#line 1170 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List AbstractMethodModifiers = (List) getRhsSym(1);
                //#line 1170 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List AbstractMethodModifier = (List) getRhsSym(2);
                //#line 1172 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                AbstractMethodModifiers.addAll(AbstractMethodModifier);
                break;
            }
     
            //
            // Rule 170:  AbstractMethodModifier ::= Annotation
            //
            case 170: {
                //#line 1176 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 1178 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Annotation));
                break;
            }
     
            //
            // Rule 171:  AbstractMethodModifier ::= public
            //
            case 171: {
                
                //#line 1183 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PUBLIC));
                break;
            }
     
            //
            // Rule 172:  AbstractMethodModifier ::= abstract
            //
            case 172: {
                
                //#line 1188 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.ABSTRACT));
                break;
            }
     
            //
            // Rule 173:  Annotations ::= Annotation
            //
            case 173: {
                //#line 1221 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(1);
                //#line 1223 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), AnnotationNode.class, false);
                l.add(Annotation);
                setResult(l);
                break;
            }
     
            //
            // Rule 174:  Annotations ::= Annotations Annotation
            //
            case 174: {
                //#line 1228 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List Annotations = (List) getRhsSym(1);
                //#line 1228 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                AnnotationNode Annotation = (AnnotationNode) getRhsSym(2);
                //#line 1230 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                Annotations.add(Annotation);
                break;
            }
     
            //
            // Rule 175:  Annotation ::= @ InterfaceType
            //
            case 175: {
                //#line 1234 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                TypeNode InterfaceType = (TypeNode) getRhsSym(2);
                //#line 1236 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.AnnotationNode(pos(), InterfaceType));
                break;
            }
     
            //
            // Rule 176:  SimpleName ::= identifier
            //
            case 176: {
                //#line 1251 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 1253 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf, ts, pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 177:  ArrayInitializer ::= { VariableInitializersopt ,opt$opt }
            //
            case 177: {
                //#line 1280 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List VariableInitializersopt = (List) getRhsSym(2);
                //#line 1280 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Object opt = (Object) getRhsSym(3);
                //#line 1282 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                if (VariableInitializersopt == null)
                     setResult(nf.ArrayInit(pos()));
                else setResult(nf.ArrayInit(pos(), VariableInitializersopt));
                break;
            }
     
            //
            // Rule 178:  VariableInitializers ::= VariableInitializer
            //
            case 178: {
                //#line 1288 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr VariableInitializer = (Expr) getRhsSym(1);
                //#line 1290 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), Expr.class, false);
                l.add(VariableInitializer);
                setResult(l);
                break;
            }
     
            //
            // Rule 179:  VariableInitializers ::= VariableInitializers , VariableInitializer
            //
            case 179: {
                //#line 1295 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List VariableInitializers = (List) getRhsSym(1);
                //#line 1295 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr VariableInitializer = (Expr) getRhsSym(3);
                //#line 1297 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                VariableInitializers.add(VariableInitializer);
                //setResult(VariableInitializers);
                break;
            }
     
            //
            // Rule 180:  Block ::= { BlockStatementsopt }
            //
            case 180: {
                //#line 1316 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List BlockStatementsopt = (List) getRhsSym(2);
                //#line 1318 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Block(pos(), BlockStatementsopt));
                break;
            }
     
            //
            // Rule 181:  BlockStatements ::= BlockStatement
            //
            case 181: {
                //#line 1322 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List BlockStatement = (List) getRhsSym(1);
                //#line 1324 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), Stmt.class, false);
                l.addAll(BlockStatement);
                setResult(l);
                break;
            }
     
            //
            // Rule 182:  BlockStatements ::= BlockStatements BlockStatement
            //
            case 182: {
                //#line 1329 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List BlockStatements = (List) getRhsSym(1);
                //#line 1329 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List BlockStatement = (List) getRhsSym(2);
                //#line 1331 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                BlockStatements.addAll(BlockStatement);
                //setResult(l);
                break;
            }
     
            //
            // Rule 184:  BlockStatement ::= ClassDeclaration
            //
            case 184: {
                //#line 1337 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                ClassDecl ClassDeclaration = (ClassDecl) getRhsSym(1);
                //#line 1339 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), Stmt.class, false);
                l.add(nf.LocalClassDecl(pos(), ClassDeclaration));
                setResult(l);
                break;
            }
     
            //
            // Rule 185:  BlockStatement ::= Statement
            //
            case 185: {
                //#line 1344 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Stmt Statement = (Stmt) getRhsSym(1);
                //#line 1346 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), Stmt.class, false);
                l.add(Statement);
                setResult(l);
                break;
            }
     
            //
            // Rule 187:  LocalVariableDeclaration ::= VariableModifiersopt Type VariableDeclarators
            //
            case 187: {
                //#line 1354 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List VariableModifiersopt = (List) getRhsSym(1);
                //#line 1354 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                TypeNode Type = (TypeNode) getRhsSym(2);
                //#line 1354 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List VariableDeclarators = (List) getRhsSym(3);
                //#line 1356 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), LocalDecl.class, false);
                List s = new TypedList(new LinkedList(), Stmt.class, false);
                if (VariableDeclarators != null) {
                    for (Iterator i = VariableDeclarators.iterator(); i.hasNext(); )
                    {
                        X10VarDeclarator d = (X10VarDeclarator) i.next();
                        d.setFlag(extractFlags(VariableModifiersopt)); 
                        // use d.flags below and not flags, setFlag may change it.
                        LocalDecl ld = nf.LocalDecl(d.pos, d.flags,
                                           nf.array(Type, pos(d), d.dims), d.name, d.init);
                        ld = (LocalDecl) ((X10Ext) ld.ext()).annotations(extractAnnotations(VariableModifiersopt));
                        l.add(ld);
                        // [IP] TODO: Add X10Local with exploded variables
                        if (d.hasExplodedVars())
                           s.addAll(X10Formal_c.explode(nf, ts, d.name, pos(d), d.flags, d.names()));
                    }
                }
                l.addAll(s); 
                setResult(l);
                break;
            }
     
            //
            // Rule 211:  IfThenStatement ::= if ( Expression ) Statement
            //
            case 211: {
                //#line 1420 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1420 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Stmt Statement = (Stmt) getRhsSym(5);
                //#line 1422 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.If(pos(), Expression, Statement));
                break;
            }
     
            //
            // Rule 212:  IfThenElseStatement ::= if ( Expression ) StatementNoShortIf else Statement
            //
            case 212: {
                //#line 1426 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1426 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Stmt StatementNoShortIf = (Stmt) getRhsSym(5);
                //#line 1426 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Stmt Statement = (Stmt) getRhsSym(7);
                //#line 1428 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.If(pos(), Expression, StatementNoShortIf, Statement));
                break;
            }
     
            //
            // Rule 213:  IfThenElseStatementNoShortIf ::= if ( Expression ) StatementNoShortIf$true_stmt else StatementNoShortIf$false_stmt
            //
            case 213: {
                //#line 1432 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1432 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Stmt true_stmt = (Stmt) getRhsSym(5);
                //#line 1432 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Stmt false_stmt = (Stmt) getRhsSym(7);
                //#line 1434 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.If(pos(), Expression, true_stmt, false_stmt));
                break;
            }
     
            //
            // Rule 214:  EmptyStatement ::= ;
            //
            case 214: {
                
                //#line 1440 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Empty(pos()));
                break;
            }
     
            //
            // Rule 215:  LabeledStatement ::= identifier : Statement
            //
            case 215: {
                //#line 1444 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 1444 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Stmt Statement = (Stmt) getRhsSym(3);
                //#line 1446 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Labeled(pos(), nf.Id(pos(getLeftSpan()), identifier.getIdentifier()), Statement));
                break;
            }
     
            //
            // Rule 216:  LabeledStatementNoShortIf ::= identifier : StatementNoShortIf
            //
            case 216: {
                //#line 1450 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 1450 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Stmt StatementNoShortIf = (Stmt) getRhsSym(3);
                //#line 1452 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Labeled(pos(), nf.Id(pos(getLeftSpan()), identifier.getIdentifier()), StatementNoShortIf));
                break;
            }
     
            //
            // Rule 217:  ExpressionStatement ::= StatementExpression ;
            //
            case 217: {
                //#line 1455 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr StatementExpression = (Expr) getRhsSym(1);
                //#line 1457 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Eval(pos(), StatementExpression));
                break;
            }
     
            //
            // Rule 225:  AssertStatement ::= assert Expression ;
            //
            case 225: {
                //#line 1478 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expression = (Expr) getRhsSym(2);
                //#line 1480 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Assert(pos(), Expression));
                break;
            }
     
            //
            // Rule 226:  AssertStatement ::= assert Expression$expr1 : Expression$expr2 ;
            //
            case 226: {
                //#line 1483 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr expr1 = (Expr) getRhsSym(2);
                //#line 1483 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr expr2 = (Expr) getRhsSym(4);
                //#line 1485 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Assert(pos(), expr1, expr2));
                break;
            }
     
            //
            // Rule 227:  SwitchStatement ::= switch ( Expression ) SwitchBlock
            //
            case 227: {
                //#line 1489 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1489 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List SwitchBlock = (List) getRhsSym(5);
                //#line 1491 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Switch(pos(), Expression, SwitchBlock));
                break;
            }
     
            //
            // Rule 228:  SwitchBlock ::= { SwitchBlockStatementGroupsopt SwitchLabelsopt }
            //
            case 228: {
                //#line 1495 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List SwitchBlockStatementGroupsopt = (List) getRhsSym(2);
                //#line 1495 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List SwitchLabelsopt = (List) getRhsSym(3);
                //#line 1497 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                SwitchBlockStatementGroupsopt.addAll(SwitchLabelsopt);
                setResult(SwitchBlockStatementGroupsopt);
                break;
            }
     
            //
            // Rule 230:  SwitchBlockStatementGroups ::= SwitchBlockStatementGroups SwitchBlockStatementGroup
            //
            case 230: {
                //#line 1503 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List SwitchBlockStatementGroups = (List) getRhsSym(1);
                //#line 1503 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List SwitchBlockStatementGroup = (List) getRhsSym(2);
                //#line 1505 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                SwitchBlockStatementGroups.addAll(SwitchBlockStatementGroup);
                // setResult(SwitchBlockStatementGroups);
                break;
            }
     
            //
            // Rule 231:  SwitchBlockStatementGroup ::= SwitchLabels BlockStatements
            //
            case 231: {
                //#line 1510 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List SwitchLabels = (List) getRhsSym(1);
                //#line 1510 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List BlockStatements = (List) getRhsSym(2);
                //#line 1512 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), SwitchElement.class, false);
                l.addAll(SwitchLabels);
                l.add(nf.SwitchBlock(pos(), BlockStatements));
                setResult(l);
                break;
            }
     
            //
            // Rule 232:  SwitchLabels ::= SwitchLabel
            //
            case 232: {
                //#line 1519 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Case SwitchLabel = (Case) getRhsSym(1);
                //#line 1521 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), Case.class, false);
                l.add(SwitchLabel);
                setResult(l);
                break;
            }
     
            //
            // Rule 233:  SwitchLabels ::= SwitchLabels SwitchLabel
            //
            case 233: {
                //#line 1526 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List SwitchLabels = (List) getRhsSym(1);
                //#line 1526 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Case SwitchLabel = (Case) getRhsSym(2);
                //#line 1528 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                SwitchLabels.add(SwitchLabel);
                //setResult(SwitchLabels);
                break;
            }
     
            //
            // Rule 234:  SwitchLabel ::= case ConstantExpression :
            //
            case 234: {
                //#line 1533 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr ConstantExpression = (Expr) getRhsSym(2);
                //#line 1535 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Case(pos(), ConstantExpression));
                break;
            }
     
            //
            // Rule 235:  SwitchLabel ::= default :
            //
            case 235: {
                
                //#line 1542 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Default(pos()));
                break;
            }
     
            //
            // Rule 236:  WhileStatement ::= while ( Expression ) Statement
            //
            case 236: {
                //#line 1549 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1549 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Stmt Statement = (Stmt) getRhsSym(5);
                //#line 1551 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.While(pos(), Expression, Statement));
                break;
            }
     
            //
            // Rule 237:  WhileStatementNoShortIf ::= while ( Expression ) StatementNoShortIf
            //
            case 237: {
                //#line 1555 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1555 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Stmt StatementNoShortIf = (Stmt) getRhsSym(5);
                //#line 1557 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.While(pos(), Expression, StatementNoShortIf));
                break;
            }
     
            //
            // Rule 238:  DoStatement ::= do Statement while ( Expression ) ;
            //
            case 238: {
                //#line 1561 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Stmt Statement = (Stmt) getRhsSym(2);
                //#line 1561 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expression = (Expr) getRhsSym(5);
                //#line 1563 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Do(pos(), Statement, Expression));
                break;
            }
     
            //
            // Rule 241:  BasicForStatement ::= for ( ForInitopt ; Expressionopt ; ForUpdateopt ) Statement
            //
            case 241: {
                //#line 1570 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ForInitopt = (List) getRhsSym(3);
                //#line 1570 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expressionopt = (Expr) getRhsSym(5);
                //#line 1570 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ForUpdateopt = (List) getRhsSym(7);
                //#line 1570 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Stmt Statement = (Stmt) getRhsSym(9);
                //#line 1572 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.For(pos(), ForInitopt, Expressionopt, ForUpdateopt, Statement));
                break;
            }
     
            //
            // Rule 242:  ForStatementNoShortIf ::= for ( ForInitopt ; Expressionopt ; ForUpdateopt ) StatementNoShortIf
            //
            case 242: {
                //#line 1576 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ForInitopt = (List) getRhsSym(3);
                //#line 1576 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expressionopt = (Expr) getRhsSym(5);
                //#line 1576 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ForUpdateopt = (List) getRhsSym(7);
                //#line 1576 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Stmt StatementNoShortIf = (Stmt) getRhsSym(9);
                //#line 1578 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.For(pos(), ForInitopt, Expressionopt, ForUpdateopt, StatementNoShortIf));
                break;
            }
     
            //
            // Rule 244:  ForInit ::= LocalVariableDeclaration
            //
            case 244: {
                //#line 1583 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List LocalVariableDeclaration = (List) getRhsSym(1);
                //#line 1585 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), ForInit.class, false);
                l.addAll(LocalVariableDeclaration);
                //setResult(l);
                break;
            }
     
            //
            // Rule 246:  StatementExpressionList ::= StatementExpression
            //
            case 246: {
                //#line 1593 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr StatementExpression = (Expr) getRhsSym(1);
                //#line 1595 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), Eval.class, false);
                l.add(nf.Eval(pos(), StatementExpression));
                setResult(l);
                break;
            }
     
            //
            // Rule 247:  StatementExpressionList ::= StatementExpressionList , StatementExpression
            //
            case 247: {
                //#line 1600 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List StatementExpressionList = (List) getRhsSym(1);
                //#line 1600 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr StatementExpression = (Expr) getRhsSym(3);
                //#line 1602 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                StatementExpressionList.add(nf.Eval(pos(), StatementExpression));
                //setResult(StatementExpressionList);
                break;
            }
     
            //
            // Rule 248:  BreakStatement ::= break identifieropt ;
            //
            case 248: {
                //#line 1610 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Name identifieropt = (Name) getRhsSym(2);
                //#line 1612 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                if (identifieropt == null)
                     setResult(nf.Break(pos()));
                else setResult(nf.Break(pos(), identifieropt.toString()));
                break;
            }
     
            //
            // Rule 249:  ContinueStatement ::= continue identifieropt ;
            //
            case 249: {
                //#line 1618 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Name identifieropt = (Name) getRhsSym(2);
                //#line 1620 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                if (identifieropt == null)
                     setResult(nf.Continue(pos()));
                else setResult(nf.Continue(pos(), identifieropt.toString()));
                break;
            }
     
            //
            // Rule 250:  ReturnStatement ::= return Expressionopt ;
            //
            case 250: {
                //#line 1626 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expressionopt = (Expr) getRhsSym(2);
                //#line 1628 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Return(pos(), Expressionopt));
                break;
            }
     
            //
            // Rule 251:  ThrowStatement ::= throw Expression ;
            //
            case 251: {
                //#line 1632 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expression = (Expr) getRhsSym(2);
                //#line 1634 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Throw(pos(), Expression));
                break;
            }
     
            //
            // Rule 252:  TryStatement ::= try Block Catches
            //
            case 252: {
                //#line 1644 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Block Block = (Block) getRhsSym(2);
                //#line 1644 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List Catches = (List) getRhsSym(3);
                //#line 1646 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Try(pos(), Block, Catches));
                break;
            }
     
            //
            // Rule 253:  TryStatement ::= try Block Catchesopt Finally
            //
            case 253: {
                //#line 1649 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Block Block = (Block) getRhsSym(2);
                //#line 1649 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List Catchesopt = (List) getRhsSym(3);
                //#line 1649 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Block Finally = (Block) getRhsSym(4);
                //#line 1651 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Try(pos(), Block, Catchesopt, Finally));
                break;
            }
     
            //
            // Rule 254:  Catches ::= CatchClause
            //
            case 254: {
                //#line 1655 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Catch CatchClause = (Catch) getRhsSym(1);
                //#line 1657 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), Catch.class, false);
                l.add(CatchClause);
                setResult(l);
                break;
            }
     
            //
            // Rule 255:  Catches ::= Catches CatchClause
            //
            case 255: {
                //#line 1662 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List Catches = (List) getRhsSym(1);
                //#line 1662 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Catch CatchClause = (Catch) getRhsSym(2);
                //#line 1664 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                Catches.add(CatchClause);
                //setResult(Catches);
                break;
            }
     
            //
            // Rule 256:  CatchClause ::= catch ( FormalParameter ) Block
            //
            case 256: {
                //#line 1669 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                X10Formal FormalParameter = (X10Formal) getRhsSym(3);
                //#line 1669 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Block Block = (Block) getRhsSym(5);
                //#line 1671 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Catch(pos(), FormalParameter, Block));
                break;
            }
     
            //
            // Rule 257:  Finally ::= finally Block
            //
            case 257: {
                //#line 1675 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Block Block = (Block) getRhsSym(2);
                //#line 1677 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Block);
                break;
            }
     
            //
            // Rule 261:  PrimaryNoNewArray ::= Type . class
            //
            case 261: {
                //#line 1695 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                TypeNode Type = (TypeNode) getRhsSym(1);
                //#line 1697 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                if (Type instanceof Name)
                {
                    Name a = (Name) Type;
                    setResult(nf.ClassLit(pos(), a.toType()));
                }
                else if (Type instanceof TypeNode)
                {
                    setResult(nf.ClassLit(pos(), Type));
                }
                else if (Type instanceof CanonicalTypeNode)
                {
                    CanonicalTypeNode a = (CanonicalTypeNode) Type;
                    setResult(nf.ClassLit(pos(), a));
                }
                else assert(false);
                break;
            }
     
            //
            // Rule 262:  PrimaryNoNewArray ::= void . class
            //
            case 262: {
                
                //#line 1716 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.ClassLit(pos(),
                                     nf.CanonicalTypeNode(pos(getLeftSpan()), ts.Void())));
                break;
            }
     
            //
            // Rule 263:  PrimaryNoNewArray ::= this
            //
            case 263: {
                
                //#line 1722 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.This(pos()));
                break;
            }
     
            //
            // Rule 264:  PrimaryNoNewArray ::= ClassName . this
            //
            case 264: {
                //#line 1725 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Name ClassName = (Name) getRhsSym(1);
                //#line 1727 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.This(pos(), ClassName.toType()));
                break;
            }
     
            //
            // Rule 265:  PrimaryNoNewArray ::= ( Expression )
            //
            case 265: {
                //#line 1730 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expression = (Expr) getRhsSym(2);
                //#line 1732 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.ParExpr(pos(), Expression));
                break;
            }
     
            //
            // Rule 270:  Literal ::= IntegerLiteral$IntegerLiteral
            //
            case 270: {
                //#line 1740 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                IToken IntegerLiteral = (IToken) getRhsIToken(1);
                //#line 1742 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                polyglot.lex.LongLiteral a = int_lit(getRhsFirstTokenIndex(1));
                setResult(nf.IntLit(pos(), IntLit.INT, a.getValue().longValue()));
                break;
            }
     
            //
            // Rule 271:  Literal ::= LongLiteral$LongLiteral
            //
            case 271: {
                //#line 1746 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                IToken LongLiteral = (IToken) getRhsIToken(1);
                //#line 1748 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                polyglot.lex.LongLiteral a = long_lit(getRhsFirstTokenIndex(1));
                setResult(nf.IntLit(pos(), IntLit.LONG, a.getValue().longValue()));
                break;
            }
     
            //
            // Rule 272:  Literal ::= FloatingPointLiteral$FloatLiteral
            //
            case 272: {
                //#line 1752 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                IToken FloatLiteral = (IToken) getRhsIToken(1);
                //#line 1754 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                polyglot.lex.FloatLiteral a = float_lit(getRhsFirstTokenIndex(1));
                setResult(nf.FloatLit(pos(), FloatLit.FLOAT, a.getValue().floatValue()));
                break;
            }
     
            //
            // Rule 273:  Literal ::= DoubleLiteral$DoubleLiteral
            //
            case 273: {
                //#line 1758 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                IToken DoubleLiteral = (IToken) getRhsIToken(1);
                //#line 1760 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                polyglot.lex.DoubleLiteral a = double_lit(getRhsFirstTokenIndex(1));
                setResult(nf.FloatLit(pos(), FloatLit.DOUBLE, a.getValue().doubleValue()));
                break;
            }
     
            //
            // Rule 274:  Literal ::= BooleanLiteral
            //
            case 274: {
                //#line 1764 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.BooleanLiteral BooleanLiteral = (polyglot.lex.BooleanLiteral) getRhsSym(1);
                //#line 1766 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.BooleanLit(pos(), BooleanLiteral.getValue().booleanValue()));
                break;
            }
     
            //
            // Rule 275:  Literal ::= CharacterLiteral$CharacterLiteral
            //
            case 275: {
                //#line 1769 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                IToken CharacterLiteral = (IToken) getRhsIToken(1);
                //#line 1771 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                polyglot.lex.CharacterLiteral a = char_lit(getRhsFirstTokenIndex(1));
                setResult(nf.CharLit(pos(), a.getValue().charValue()));
                break;
            }
     
            //
            // Rule 276:  Literal ::= StringLiteral$str
            //
            case 276: {
                //#line 1775 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                IToken str = (IToken) getRhsIToken(1);
                //#line 1777 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                polyglot.lex.StringLiteral a = string_lit(getRhsFirstTokenIndex(1));
                setResult(nf.StringLit(pos(), a.getValue()));
                break;
            }
     
            //
            // Rule 277:  Literal ::= null
            //
            case 277: {
                
                //#line 1783 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.NullLit(pos()));
                break;
            }
     
            //
            // Rule 278:  BooleanLiteral ::= true$trueLiteral
            //
            case 278: {
                //#line 1787 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                IToken trueLiteral = (IToken) getRhsIToken(1);
                //#line 1789 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(boolean_lit(getRhsFirstTokenIndex(1)));
                break;
            }
     
            //
            // Rule 279:  BooleanLiteral ::= false$falseLiteral
            //
            case 279: {
                //#line 1792 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                IToken falseLiteral = (IToken) getRhsIToken(1);
                //#line 1794 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(boolean_lit(getRhsFirstTokenIndex(1)));
                break;
            }
     
            //
            // Rule 280:  ArgumentList ::= Expression
            //
            case 280: {
                //#line 1807 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expression = (Expr) getRhsSym(1);
                //#line 1809 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), Expr.class, false);
                l.add(Expression);
                setResult(l);
                break;
            }
     
            //
            // Rule 281:  ArgumentList ::= ArgumentList , Expression
            //
            case 281: {
                //#line 1814 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ArgumentList = (List) getRhsSym(1);
                //#line 1814 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1816 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                ArgumentList.add(Expression);
                //setResult(ArgumentList);
                break;
            }
     
            //
            // Rule 282:  DimExprs ::= DimExpr
            //
            case 282: {
                //#line 1850 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr DimExpr = (Expr) getRhsSym(1);
                //#line 1852 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), Expr.class, false);
                l.add(DimExpr);
                setResult(l);
                break;
            }
     
            //
            // Rule 283:  DimExprs ::= DimExprs DimExpr
            //
            case 283: {
                //#line 1857 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List DimExprs = (List) getRhsSym(1);
                //#line 1857 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr DimExpr = (Expr) getRhsSym(2);
                //#line 1859 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                DimExprs.add(DimExpr);
                //setResult(DimExprs);
                break;
            }
     
            //
            // Rule 284:  DimExpr ::= [ Expression ]
            //
            case 284: {
                //#line 1864 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expression = (Expr) getRhsSym(2);
                //#line 1866 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Expression.position(pos()));
                break;
            }
     
            //
            // Rule 285:  Dims ::= [ ]
            //
            case 285: {
                
                //#line 1872 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Integer(1));
                break;
            }
     
            //
            // Rule 286:  Dims ::= Dims [ ]
            //
            case 286: {
                //#line 1875 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Integer Dims = (Integer) getRhsSym(1);
                //#line 1877 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Integer(Dims.intValue() + 1));
                break;
            }
     
            //
            // Rule 287:  FieldAccess ::= Primary . identifier
            //
            case 287: {
                //#line 1881 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 1881 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 1883 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Field(pos(), Primary, nf.Id(pos(getRightSpan()), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 288:  FieldAccess ::= super . identifier
            //
            case 288: {
                //#line 1886 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 1888 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Field(pos(getRightSpan()), nf.Super(pos(getLeftSpan())), nf.Id(pos(getRightSpan()), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 289:  FieldAccess ::= ClassName . super$sup . identifier
            //
            case 289: {
                //#line 1891 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Name ClassName = (Name) getRhsSym(1);
                //#line 1891 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                IToken sup = (IToken) getRhsIToken(3);
                //#line 1891 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(5);
                //#line 1893 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Field(pos(getRightSpan()), nf.Super(pos(getRhsFirstTokenIndex(3)), ClassName.toType()), nf.Id(pos(getRightSpan()), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 290:  MethodInvocation ::= MethodName ( ArgumentListopt )
            //
            case 290: {
                //#line 1897 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Name MethodName = (Name) getRhsSym(1);
                //#line 1897 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                List ArgumentListopt = (List) getRhsSym(3);
                //#line 1899 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Call(pos(), MethodName.prefix == null
                                                             ? null
                                                             : MethodName.prefix.toReceiver(), MethodName.name, ArgumentListopt));
                break;
            }
     
            //
            // Rule 292:  PostfixExpression ::= ExpressionName
            //
            case 292: {
                //#line 1922 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Name ExpressionName = (Name) getRhsSym(1);
                //#line 1924 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ExpressionName.toExpr());
                break;
            }
     
            //
            // Rule 295:  PostIncrementExpression ::= PostfixExpression ++
            //
            case 295: {
                //#line 1930 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr PostfixExpression = (Expr) getRhsSym(1);
                //#line 1932 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Unary(pos(), PostfixExpression, Unary.POST_INC));
                break;
            }
     
            //
            // Rule 296:  PostDecrementExpression ::= PostfixExpression --
            //
            case 296: {
                //#line 1936 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr PostfixExpression = (Expr) getRhsSym(1);
                //#line 1938 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Unary(pos(), PostfixExpression, Unary.POST_DEC));
                break;
            }
     
            //
            // Rule 299:  UnaryExpression ::= + UnaryExpression
            //
            case 299: {
                //#line 1944 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr UnaryExpression = (Expr) getRhsSym(2);
                //#line 1946 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Unary(pos(), Unary.POS, UnaryExpression));
                break;
            }
     
            //
            // Rule 300:  UnaryExpression ::= - UnaryExpression
            //
            case 300: {
                //#line 1949 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr UnaryExpression = (Expr) getRhsSym(2);
                //#line 1951 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Unary(pos(), Unary.NEG, UnaryExpression));
                break;
            }
     
            //
            // Rule 302:  PreIncrementExpression ::= ++ UnaryExpression
            //
            case 302: {
                //#line 1956 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr UnaryExpression = (Expr) getRhsSym(2);
                //#line 1958 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Unary(pos(), Unary.PRE_INC, UnaryExpression));
                break;
            }
     
            //
            // Rule 303:  PreDecrementExpression ::= -- UnaryExpression
            //
            case 303: {
                //#line 1962 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr UnaryExpression = (Expr) getRhsSym(2);
                //#line 1964 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Unary(pos(), Unary.PRE_DEC, UnaryExpression));
                break;
            }
     
            //
            // Rule 305:  UnaryExpressionNotPlusMinus ::= ~ UnaryExpression
            //
            case 305: {
                //#line 1969 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr UnaryExpression = (Expr) getRhsSym(2);
                //#line 1971 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Unary(pos(), Unary.BIT_NOT, UnaryExpression));
                break;
            }
     
            //
            // Rule 306:  UnaryExpressionNotPlusMinus ::= ! UnaryExpression
            //
            case 306: {
                //#line 1974 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr UnaryExpression = (Expr) getRhsSym(2);
                //#line 1976 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Unary(pos(), Unary.NOT, UnaryExpression));
                break;
            }
     
            //
            // Rule 309:  MultiplicativeExpression ::= MultiplicativeExpression * UnaryExpression
            //
            case 309: {
                //#line 1988 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr MultiplicativeExpression = (Expr) getRhsSym(1);
                //#line 1988 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr UnaryExpression = (Expr) getRhsSym(3);
                //#line 1990 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), MultiplicativeExpression, Binary.MUL, UnaryExpression));
                break;
            }
     
            //
            // Rule 310:  MultiplicativeExpression ::= MultiplicativeExpression / UnaryExpression
            //
            case 310: {
                //#line 1993 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr MultiplicativeExpression = (Expr) getRhsSym(1);
                //#line 1993 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr UnaryExpression = (Expr) getRhsSym(3);
                //#line 1995 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), MultiplicativeExpression, Binary.DIV, UnaryExpression));
                break;
            }
     
            //
            // Rule 311:  MultiplicativeExpression ::= MultiplicativeExpression % UnaryExpression
            //
            case 311: {
                //#line 1998 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr MultiplicativeExpression = (Expr) getRhsSym(1);
                //#line 1998 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr UnaryExpression = (Expr) getRhsSym(3);
                //#line 2000 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), MultiplicativeExpression, Binary.MOD, UnaryExpression));
                break;
            }
     
            //
            // Rule 313:  AdditiveExpression ::= AdditiveExpression + MultiplicativeExpression
            //
            case 313: {
                //#line 2005 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr AdditiveExpression = (Expr) getRhsSym(1);
                //#line 2005 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr MultiplicativeExpression = (Expr) getRhsSym(3);
                //#line 2007 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), AdditiveExpression, Binary.ADD, MultiplicativeExpression));
                break;
            }
     
            //
            // Rule 314:  AdditiveExpression ::= AdditiveExpression - MultiplicativeExpression
            //
            case 314: {
                //#line 2010 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr AdditiveExpression = (Expr) getRhsSym(1);
                //#line 2010 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr MultiplicativeExpression = (Expr) getRhsSym(3);
                //#line 2012 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), AdditiveExpression, Binary.SUB, MultiplicativeExpression));
                break;
            }
     
            //
            // Rule 316:  ShiftExpression ::= ShiftExpression << AdditiveExpression
            //
            case 316: {
                //#line 2017 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr ShiftExpression = (Expr) getRhsSym(1);
                //#line 2017 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr AdditiveExpression = (Expr) getRhsSym(3);
                //#line 2019 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ShiftExpression, Binary.SHL, AdditiveExpression));
                break;
            }
     
            //
            // Rule 317:  ShiftExpression ::= ShiftExpression > > AdditiveExpression
            //
            case 317: {
                //#line 2022 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr ShiftExpression = (Expr) getRhsSym(1);
                //#line 2022 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr AdditiveExpression = (Expr) getRhsSym(4);
                //#line 2024 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                // TODO: make sure that there is no space after the ">" signs
                setResult(nf.Binary(pos(), ShiftExpression, Binary.SHR, AdditiveExpression));
                break;
            }
     
            //
            // Rule 318:  ShiftExpression ::= ShiftExpression > > > AdditiveExpression
            //
            case 318: {
                //#line 2028 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr ShiftExpression = (Expr) getRhsSym(1);
                //#line 2028 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr AdditiveExpression = (Expr) getRhsSym(5);
                //#line 2030 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                // TODO: make sure that there is no space after the ">" signs
                setResult(nf.Binary(pos(), ShiftExpression, Binary.USHR, AdditiveExpression));
                break;
            }
     
            //
            // Rule 320:  RelationalExpression ::= RelationalExpression < ShiftExpression
            //
            case 320: {
                //#line 2036 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr RelationalExpression = (Expr) getRhsSym(1);
                //#line 2036 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr ShiftExpression = (Expr) getRhsSym(3);
                //#line 2038 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), RelationalExpression, Binary.LT, ShiftExpression));
                break;
            }
     
            //
            // Rule 321:  RelationalExpression ::= RelationalExpression > ShiftExpression
            //
            case 321: {
                //#line 2041 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr RelationalExpression = (Expr) getRhsSym(1);
                //#line 2041 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr ShiftExpression = (Expr) getRhsSym(3);
                //#line 2043 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), RelationalExpression, Binary.GT, ShiftExpression));
                break;
            }
     
            //
            // Rule 322:  RelationalExpression ::= RelationalExpression <= ShiftExpression
            //
            case 322: {
                //#line 2046 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr RelationalExpression = (Expr) getRhsSym(1);
                //#line 2046 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr ShiftExpression = (Expr) getRhsSym(3);
                //#line 2048 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), RelationalExpression, Binary.LE, ShiftExpression));
                break;
            }
     
            //
            // Rule 323:  RelationalExpression ::= RelationalExpression > = ShiftExpression
            //
            case 323: {
                //#line 2051 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr RelationalExpression = (Expr) getRhsSym(1);
                //#line 2051 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr ShiftExpression = (Expr) getRhsSym(4);
                //#line 2053 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                // TODO: make sure that there is no space after the ">" signs
                setResult(nf.Binary(pos(), RelationalExpression, Binary.GE, ShiftExpression));
                break;
            }
     
            //
            // Rule 325:  EqualityExpression ::= EqualityExpression == RelationalExpression
            //
            case 325: {
                //#line 2067 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr EqualityExpression = (Expr) getRhsSym(1);
                //#line 2067 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr RelationalExpression = (Expr) getRhsSym(3);
                //#line 2069 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), EqualityExpression, Binary.EQ, RelationalExpression));
                break;
            }
     
            //
            // Rule 326:  EqualityExpression ::= EqualityExpression != RelationalExpression
            //
            case 326: {
                //#line 2072 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr EqualityExpression = (Expr) getRhsSym(1);
                //#line 2072 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr RelationalExpression = (Expr) getRhsSym(3);
                //#line 2074 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), EqualityExpression, Binary.NE, RelationalExpression));
                break;
            }
     
            //
            // Rule 328:  AndExpression ::= AndExpression & EqualityExpression
            //
            case 328: {
                //#line 2079 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr AndExpression = (Expr) getRhsSym(1);
                //#line 2079 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr EqualityExpression = (Expr) getRhsSym(3);
                //#line 2081 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), AndExpression, Binary.BIT_AND, EqualityExpression));
                break;
            }
     
            //
            // Rule 330:  ExclusiveOrExpression ::= ExclusiveOrExpression ^ AndExpression
            //
            case 330: {
                //#line 2086 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr ExclusiveOrExpression = (Expr) getRhsSym(1);
                //#line 2086 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr AndExpression = (Expr) getRhsSym(3);
                //#line 2088 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ExclusiveOrExpression, Binary.BIT_XOR, AndExpression));
                break;
            }
     
            //
            // Rule 332:  InclusiveOrExpression ::= InclusiveOrExpression | ExclusiveOrExpression
            //
            case 332: {
                //#line 2093 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr InclusiveOrExpression = (Expr) getRhsSym(1);
                //#line 2093 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr ExclusiveOrExpression = (Expr) getRhsSym(3);
                //#line 2095 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), InclusiveOrExpression, Binary.BIT_OR, ExclusiveOrExpression));
                break;
            }
     
            //
            // Rule 334:  ConditionalAndExpression ::= ConditionalAndExpression && InclusiveOrExpression
            //
            case 334: {
                //#line 2100 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr ConditionalAndExpression = (Expr) getRhsSym(1);
                //#line 2100 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr InclusiveOrExpression = (Expr) getRhsSym(3);
                //#line 2102 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConditionalAndExpression, Binary.COND_AND, InclusiveOrExpression));
                break;
            }
     
            //
            // Rule 336:  ConditionalOrExpression ::= ConditionalOrExpression || ConditionalAndExpression
            //
            case 336: {
                //#line 2107 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr ConditionalOrExpression = (Expr) getRhsSym(1);
                //#line 2107 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr ConditionalAndExpression = (Expr) getRhsSym(3);
                //#line 2109 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConditionalOrExpression, Binary.COND_OR, ConditionalAndExpression));
                break;
            }
     
            //
            // Rule 338:  ConditionalExpression ::= ConditionalOrExpression ? Expression : ConditionalExpression
            //
            case 338: {
                //#line 2114 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr ConditionalOrExpression = (Expr) getRhsSym(1);
                //#line 2114 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 2114 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr ConditionalExpression = (Expr) getRhsSym(5);
                //#line 2116 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Conditional(pos(), ConditionalOrExpression, Expression, ConditionalExpression));
                break;
            }
     
            //
            // Rule 341:  Assignment ::= LeftHandSide AssignmentOperator AssignmentExpression
            //
            case 341: {
                //#line 2123 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr LeftHandSide = (Expr) getRhsSym(1);
                //#line 2123 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Assign.Operator AssignmentOperator = (Assign.Operator) getRhsSym(2);
                //#line 2123 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Expr AssignmentExpression = (Expr) getRhsSym(3);
                //#line 2125 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Assign(pos(), LeftHandSide, AssignmentOperator, AssignmentExpression));
                break;
            }
     
            //
            // Rule 342:  LeftHandSide ::= ExpressionName
            //
            case 342: {
                //#line 2129 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                Name ExpressionName = (Name) getRhsSym(1);
                //#line 2131 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ExpressionName.toExpr());
                break;
            }
     
            //
            // Rule 345:  AssignmentOperator ::= =
            //
            case 345: {
                
                //#line 2139 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Assign.ASSIGN);
                break;
            }
     
            //
            // Rule 346:  AssignmentOperator ::= *=
            //
            case 346: {
                
                //#line 2144 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Assign.MUL_ASSIGN);
                break;
            }
     
            //
            // Rule 347:  AssignmentOperator ::= /=
            //
            case 347: {
                
                //#line 2149 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Assign.DIV_ASSIGN);
                break;
            }
     
            //
            // Rule 348:  AssignmentOperator ::= %=
            //
            case 348: {
                
                //#line 2154 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Assign.MOD_ASSIGN);
                break;
            }
     
            //
            // Rule 349:  AssignmentOperator ::= +=
            //
            case 349: {
                
                //#line 2159 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Assign.ADD_ASSIGN);
                break;
            }
     
            //
            // Rule 350:  AssignmentOperator ::= -=
            //
            case 350: {
                
                //#line 2164 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Assign.SUB_ASSIGN);
                break;
            }
     
            //
            // Rule 351:  AssignmentOperator ::= <<=
            //
            case 351: {
                
                //#line 2169 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Assign.SHL_ASSIGN);
                break;
            }
     
            //
            // Rule 352:  AssignmentOperator ::= > > =
            //
            case 352: {
                
                //#line 2174 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                // TODO: make sure that there is no space after the ">" signs
                setResult(Assign.SHR_ASSIGN);
                break;
            }
     
            //
            // Rule 353:  AssignmentOperator ::= > > > =
            //
            case 353: {
                
                //#line 2180 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                // TODO: make sure that there is no space after the ">" signs
                setResult(Assign.USHR_ASSIGN);
                break;
            }
     
            //
            // Rule 354:  AssignmentOperator ::= &=
            //
            case 354: {
                
                //#line 2186 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Assign.BIT_AND_ASSIGN);
                break;
            }
     
            //
            // Rule 355:  AssignmentOperator ::= ^=
            //
            case 355: {
                
                //#line 2191 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Assign.BIT_XOR_ASSIGN);
                break;
            }
     
            //
            // Rule 356:  AssignmentOperator ::= |=
            //
            case 356: {
                
                //#line 2196 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Assign.BIT_OR_ASSIGN);
                break;
            }
     
            //
            // Rule 359:  Dimsopt ::= $Empty
            //
            case 359: {
                
                //#line 2209 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Integer(0));
                break;
            }
     
            //
            // Rule 361:  Catchesopt ::= $Empty
            //
            case 361: {
                
                //#line 2216 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), Catch.class, false));
                break;
            }
     
            //
            // Rule 363:  identifieropt ::= $Empty
            //
            case 363:
                setResult(null);
                break;
 
            //
            // Rule 364:  identifieropt ::= identifier
            //
            case 364: {
                //#line 2223 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/GJavaParserForX10.gi"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 2225 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf, ts, pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 365:  ForUpdateopt ::= $Empty
            //
            case 365: {
                
                //#line 2231 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), ForUpdate.class, false));
                break;
            }
     
            //
            // Rule 367:  Expressionopt ::= $Empty
            //
            case 367:
                setResult(null);
                break;
 
            //
            // Rule 369:  ForInitopt ::= $Empty
            //
            case 369: {
                
                //#line 2242 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), ForInit.class, false));
                break;
            }
     
            //
            // Rule 371:  SwitchLabelsopt ::= $Empty
            //
            case 371: {
                
                //#line 2249 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), Case.class, false));
                break;
            }
     
            //
            // Rule 373:  SwitchBlockStatementGroupsopt ::= $Empty
            //
            case 373: {
                
                //#line 2256 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), SwitchElement.class, false));
                break;
            }
     
            //
            // Rule 375:  VariableModifiersopt ::= $Empty
            //
            case 375: {
                
                //#line 2263 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.EMPTY_LIST);
                break;
            }
     
            //
            // Rule 377:  VariableInitializersopt ::= $Empty
            //
            case 377:
                setResult(null);
                break;
 
            //
            // Rule 379:  AbstractMethodModifiersopt ::= $Empty
            //
            case 379: {
                
                //#line 2293 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.EMPTY_LIST);
                break;
            }
     
            //
            // Rule 381:  ConstantModifiersopt ::= $Empty
            //
            case 381: {
                
                //#line 2300 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.EMPTY_LIST);
                break;
            }
     
            //
            // Rule 383:  InterfaceMemberDeclarationsopt ::= $Empty
            //
            case 383: {
                
                //#line 2307 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), ClassMember.class, false));
                break;
            }
     
            //
            // Rule 385:  ExtendsInterfacesopt ::= $Empty
            //
            case 385: {
                
                //#line 2314 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), TypeNode.class, false));
                break;
            }
     
            //
            // Rule 387:  InterfaceModifiersopt ::= $Empty
            //
            case 387: {
                
                //#line 2321 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.EMPTY_LIST);
                break;
            }
     
            //
            // Rule 389:  ClassBodyopt ::= $Empty
            //
            case 389:
                setResult(null);
                break;
 
            //
            // Rule 391:  Argumentsopt ::= $Empty
            //
            case 391: {
                
                //#line 2332 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), Expr.class, false));
                break;
            }
     
            //
            // Rule 393:  ,opt ::= $Empty
            //
            case 393:
                setResult(null);
                break;
 
            //
            // Rule 395:  ArgumentListopt ::= $Empty
            //
            case 395: {
                
                //#line 2353 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), Expr.class, false));
                break;
            }
     
            //
            // Rule 397:  BlockStatementsopt ::= $Empty
            //
            case 397: {
                
                //#line 2360 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), Stmt.class, false));
                break;
            }
     
            //
            // Rule 399:  ExplicitConstructorInvocationopt ::= $Empty
            //
            case 399:
                setResult(null);
                break;
 
            //
            // Rule 401:  ConstructorModifiersopt ::= $Empty
            //
            case 401: {
                
                //#line 2371 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.EMPTY_LIST);
                break;
            }
     
            //
            // Rule 403:  ...opt ::= $Empty
            //
            case 403:
                setResult(null);
                break;
 
            //
            // Rule 405:  FormalParameterListopt ::= $Empty
            //
            case 405: {
                
                //#line 2382 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), Formal.class, false));
                break;
            }
     
            //
            // Rule 407:  Throwsopt ::= $Empty
            //
            case 407: {
                
                //#line 2389 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), TypeNode.class, false));
                break;
            }
     
            //
            // Rule 409:  MethodModifiersopt ::= $Empty
            //
            case 409: {
                
                //#line 2396 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.EMPTY_LIST);
                break;
            }
     
            //
            // Rule 411:  FieldModifiersopt ::= $Empty
            //
            case 411: {
                
                //#line 2403 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.EMPTY_LIST);
                break;
            }
     
            //
            // Rule 413:  ClassBodyDeclarationsopt ::= $Empty
            //
            case 413: {
                
                //#line 2410 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), ClassMember.class, false));
                break;
            }
     
            //
            // Rule 415:  Interfacesopt ::= $Empty
            //
            case 415: {
                
                //#line 2417 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), TypeNode.class, false));
                break;
            }
     
            //
            // Rule 417:  Superopt ::= $Empty
            //
            case 417: {
                
                //#line 2424 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
               setResult(nf.TypeNodeFromQualifiedName(pos(), "x10.lang.Object"));
                break;
            }
     
            //
            // Rule 419:  ClassModifiersopt ::= $Empty
            //
            case 419: {
                
                //#line 2435 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.EMPTY_LIST);
                break;
            }
     
            //
            // Rule 421:  Annotationsopt ::= $Empty
            //
            case 421: {
                
                //#line 2442 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), AnnotationNode.class, false));
                break;
            }
     
            //
            // Rule 423:  TypeDeclarationsopt ::= $Empty
            //
            case 423: {
                
                //#line 2449 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), TopLevelDecl.class, false));
                break;
            }
     
            //
            // Rule 425:  ImportDeclarationsopt ::= $Empty
            //
            case 425: {
                
                //#line 2456 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), Import.class, false));
                break;
            }
     
            //
            // Rule 427:  PackageDeclarationopt ::= $Empty
            //
            case 427:
                setResult(null);
                break;
 
            //
            // Rule 429:  ClassType ::= TypeName DepParametersopt PlaceTypeSpecifieropt
            //
            case 429: {
                //#line 787 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Name TypeName = (Name) getRhsSym(1);
                //#line 787 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                DepParameterExpr DepParametersopt = (DepParameterExpr) getRhsSym(2);
                //#line 787 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object PlaceTypeSpecifieropt = (Object) getRhsSym(3);
                //#line 789 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                     setResult(DepParametersopt == null
                               ? TypeName.toType()
                               : ((X10TypeNode) TypeName.toType()).dep(null, DepParametersopt));
                break;
            }
     
            //
            // Rule 430:  InterfaceType ::= TypeName DepParametersopt PlaceTypeSpecifieropt
            //
            case 430: {
                //#line 796 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Name TypeName = (Name) getRhsSym(1);
                //#line 796 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                DepParameterExpr DepParametersopt = (DepParameterExpr) getRhsSym(2);
                //#line 796 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object PlaceTypeSpecifieropt = (Object) getRhsSym(3);
                //#line 798 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                 setResult(DepParametersopt == null
                               ? TypeName.toType()
                               : ((X10TypeNode) TypeName.toType()).dep(null, DepParametersopt));
                break;
            }
     
            //
            // Rule 431:  PackageDeclaration ::= package PackageName ;
            //
            case 431: {
                //#line 804 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Name PackageName = (Name) getRhsSym(2);
                //#line 806 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(PackageName.toPackage());
                break;
            }
     
            //
            // Rule 432:  NormalClassDeclaration ::= X10ClassModifiersopt class identifier PropertyListopt Superopt Interfacesopt ClassBody
            //
            case 432: {
                //#line 810 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List X10ClassModifiersopt = (List) getRhsSym(1);
                //#line 810 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 810 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object[] PropertyListopt = (Object[]) getRhsSym(4);
                //#line 810 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode Superopt = (TypeNode) getRhsSym(5);
                //#line 810 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List Interfacesopt = (List) getRhsSym(6);
                //#line 810 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                ClassBody ClassBody = (ClassBody) getRhsSym(7);
                //#line 812 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
      checkTypeName(identifier);
      List/*<PropertyDecl>*/ props = PropertyListopt == null ? null
                  : (List) PropertyListopt[0];
      Expr ci = PropertyListopt == null ? null : (Expr) PropertyListopt[1];
      Flags f = extractFlags(X10ClassModifiersopt);
      List annotations = extractAnnotations(X10ClassModifiersopt);
      ClassDecl cd = X10Flags.isValue(f)
         ? nf.ValueClassDecl(pos(),
              f, nf.Id(identifier.getPosition(), identifier.getIdentifier()), props, ci, Superopt, Interfacesopt, ClassBody)
         : nf.ClassDecl(pos(),
              f, nf.Id(identifier.getPosition(), identifier.getIdentifier()), props, ci, Superopt, Interfacesopt, ClassBody);
      cd = (ClassDecl) ((X10Ext) cd.ext()).annotations(annotations);
      setResult(cd);
                break;
            }
     
            //
            // Rule 433:  X10ClassModifiers ::= X10ClassModifier
            //
            case 433: {
                //#line 828 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List X10ClassModifier = (List) getRhsSym(1);
                //#line 830 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
       List l = new LinkedList();
       l.addAll(X10ClassModifier);
       setResult(l);
                break;
            }
     
            //
            // Rule 434:  X10ClassModifiers ::= X10ClassModifiers X10ClassModifier
            //
            case 434: {
                //#line 835 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List X10ClassModifiers = (List) getRhsSym(1);
                //#line 835 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List X10ClassModifier = (List) getRhsSym(2);
                //#line 837 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
       X10ClassModifiers.addAll(X10ClassModifier);
                break;
            }
     
            //
            // Rule 435:  X10ClassModifier ::= ClassModifier
            //
            case 435: {
                //#line 841 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ClassModifier = (List) getRhsSym(1);
                //#line 843 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                // Done by extractFlags
                // X10Flags.toX10Flags(ClassModifier));
                setResult(ClassModifier);
                break;
            }
     
            //
            // Rule 436:  X10ClassModifier ::= safe
            //
            case 436: {
                
                //#line 850 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(X10Flags.SAFE));
                break;
            }
     
            //
            // Rule 437:  PropertyList ::= ( Properties WhereClauseopt )
            //
            case 437: {
                //#line 854 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List Properties = (List) getRhsSym(2);
                //#line 854 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr WhereClauseopt = (Expr) getRhsSym(3);
                //#line 856 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
   Object[] result = new Object[2];
   result[0] = Properties;
   result[1] = WhereClauseopt;
   setResult(result);
           break;
            }  
            //
            // Rule 438:  PropertyList ::= ( WhereClause )
            //
            case 438: {
                //#line 861 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr WhereClause = (Expr) getRhsSym(2);
                //#line 863 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
   Object[] result = new Object[2];
   result[0] = null;
   result[1] = WhereClause;
   setResult(result);
           break;
            }  
            //
            // Rule 439:  Properties ::= Property
            //
            case 439: {
                //#line 870 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                PropertyDecl Property = (PropertyDecl) getRhsSym(1);
                //#line 872 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), PropertyDecl.class, false);
                l.add(Property);
                setResult(l);
                break;
            }
     
            //
            // Rule 440:  Properties ::= Properties , Property
            //
            case 440: {
                //#line 877 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List Properties = (List) getRhsSym(1);
                //#line 877 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                PropertyDecl Property = (PropertyDecl) getRhsSym(3);
                //#line 879 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                Properties.add(Property);
                // setResult(FormalParameters);
                break;
            }
     
            //
            // Rule 441:  Property ::= Type identifier
            //
            case 441: {
                //#line 885 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(1);
                //#line 885 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(2);
                //#line 887 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
    
                setResult(nf.PropertyDecl(pos(), Flags.PUBLIC.Final(), Type,
                nf.Id(identifier.getPosition(), identifier.getIdentifier())));
              
                break;
            }
     
            //
            // Rule 442:  MethodDeclaration ::= ThisClauseopt MethodModifiersopt ResultType MethodDeclarator Throwsopt MethodBody
            //
            case 442: {
                //#line 900 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                DepParameterExpr ThisClauseopt = (DepParameterExpr) getRhsSym(1);
                //#line 900 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List MethodModifiersopt = (List) getRhsSym(2);
                //#line 900 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode ResultType = (TypeNode) getRhsSym(3);
                //#line 900 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object[] MethodDeclarator = (Object[]) getRhsSym(4);
                //#line 900 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List Throwsopt = (List) getRhsSym(5);
                //#line 900 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Block MethodBody = (Block) getRhsSym(6);
                //#line 902 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
      Name c = (MethodDeclarator != null) ? (Name) MethodDeclarator[0] : null;
      List d = (MethodDeclarator != null) ? (List) MethodDeclarator[1] : null;
      Integer e = (MethodDeclarator != null) ? (Integer) MethodDeclarator[2] : null;
      Expr where = (MethodDeclarator != null) ? (Expr) MethodDeclarator[3] : null;
      if (ResultType.type() == ts.Void() && e != null && e.intValue() > 0)
         {
           // TODO: error!!!
           System.err.println("Fix me - encountered method returning void but with non-zero rank?");
         }

       MethodDecl md = nf.MethodDecl(pos(getRhsFirstTokenIndex(1), getRhsLastTokenIndex(6)),
          ThisClauseopt,
          extractFlags(MethodModifiersopt),
          nf.array((TypeNode) ResultType, pos(getRhsFirstTokenIndex(3), getRhsLastTokenIndex(3)), e != null ? e.intValue() : 1),
          c != null ? c.name : nf.Id(pos(), ""),
          d,
          where,
          Throwsopt,
          MethodBody);
      md = (MethodDecl) ((X10Ext) md.ext()).annotations(extractAnnotations(MethodModifiersopt));
      setResult(md);
                break;
            }
     
            //
            // Rule 443:  ExplicitConstructorInvocation ::= this ( ArgumentListopt ) ;
            //
            case 443: {
                //#line 926 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(3);
                //#line 928 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.ThisCall(pos(), ArgumentListopt));
                break;
            }
     
            //
            // Rule 444:  ExplicitConstructorInvocation ::= super ( ArgumentListopt ) ;
            //
            case 444: {
                //#line 931 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(3);
                //#line 933 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.SuperCall(pos(), ArgumentListopt));
                break;
            }
     
            //
            // Rule 445:  ExplicitConstructorInvocation ::= Primary . this ( ArgumentListopt ) ;
            //
            case 445: {
                //#line 936 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 936 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(5);
                //#line 938 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.ThisCall(pos(), Primary, ArgumentListopt));
                break;
            }
     
            //
            // Rule 446:  ExplicitConstructorInvocation ::= Primary . super ( ArgumentListopt ) ;
            //
            case 446: {
                //#line 941 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 941 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(5);
                //#line 943 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.SuperCall(pos(), Primary, ArgumentListopt));
                break;
            }
     
            //
            // Rule 447:  NormalInterfaceDeclaration ::= InterfaceModifiersopt interface identifier PropertyListopt ExtendsInterfacesopt InterfaceBody
            //
            case 447: {
                //#line 947 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List InterfaceModifiersopt = (List) getRhsSym(1);
                //#line 947 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 947 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object[] PropertyListopt = (Object[]) getRhsSym(4);
                //#line 947 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ExtendsInterfacesopt = (List) getRhsSym(5);
                //#line 947 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                ClassBody InterfaceBody = (ClassBody) getRhsSym(6);
                //#line 949 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
      checkTypeName(identifier);
      List/*<PropertyDecl>*/ props = PropertyListopt == null ? null 
                  : (List) PropertyListopt[0];
      Expr ci = PropertyListopt == null ? null : (Expr) PropertyListopt[1];
      ClassDecl cd = nf.ClassDecl(pos(),
                   extractFlags(InterfaceModifiersopt).Interface(),
                   nf.Id(identifier.getPosition(), identifier.getIdentifier()),
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
            // Rule 448:  AbstractMethodDeclaration ::= ThisClauseopt AbstractMethodModifiersopt ResultType MethodDeclarator Throwsopt ;
            //
            case 448: {
                //#line 966 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                DepParameterExpr ThisClauseopt = (DepParameterExpr) getRhsSym(1);
                //#line 966 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List AbstractMethodModifiersopt = (List) getRhsSym(2);
                //#line 966 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode ResultType = (TypeNode) getRhsSym(3);
                //#line 966 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object[] MethodDeclarator = (Object[]) getRhsSym(4);
                //#line 966 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List Throwsopt = (List) getRhsSym(5);
                //#line 968 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
     Name c = (Name) MethodDeclarator[0];
     List d = (List) MethodDeclarator[1];
     Integer e = (Integer) MethodDeclarator[2];
     Expr where = (Expr) MethodDeclarator[3];
     
     if (ResultType.type() == ts.Void() && e.intValue() > 0)
        {
          // TODO: error!!!
          assert(false);
        }

     MethodDecl md = nf.MethodDecl(pos(getRhsFirstTokenIndex(3), getRhsLastTokenIndex(4)),
                ThisClauseopt,
                extractFlags(AbstractMethodModifiersopt),
                nf.array((TypeNode) ResultType, pos(getRhsFirstTokenIndex(3), getRhsLastTokenIndex(3)), e.intValue()),
                nf.Id(c.pos, c.toString()),
                d,
                where,
                Throwsopt,
                null);
      md = (MethodDecl) ((X10Ext) md.ext()).annotations(extractAnnotations(AbstractMethodModifiersopt));
      setResult(md);
                break;
            }
     
            //
            // Rule 449:  ClassInstanceCreationExpression ::= new ClassOrInterfaceType ( ArgumentListopt ) ClassBodyopt
            //
            case 449: {
                //#line 993 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode ClassOrInterfaceType = (TypeNode) getRhsSym(2);
                //#line 993 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(4);
                //#line 993 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                ClassBody ClassBodyopt = (ClassBody) getRhsSym(6);
                //#line 995 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                if (ClassBodyopt == null)
                     setResult(nf.New(pos(), ClassOrInterfaceType, ArgumentListopt));
                else setResult(nf.New(pos(), ClassOrInterfaceType, ArgumentListopt, ClassBodyopt));
                break;
            }
     
            //
            // Rule 450:  ClassInstanceCreationExpression ::= Primary . new identifier ( ArgumentListopt ) ClassBodyopt
            //
            case 450: {
                //#line 1000 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 1000 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(4);
                //#line 1000 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(6);
                //#line 1000 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                ClassBody ClassBodyopt = (ClassBody) getRhsSym(8);
                //#line 1002 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                Name b = new Name(nf, ts, pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier()));
                if (ClassBodyopt == null)
                     setResult(nf.New(pos(), Primary, b.toType(), ArgumentListopt));
                else setResult(nf.New(pos(), Primary, b.toType(), ArgumentListopt, ClassBodyopt));
                break;
            }
     
            //
            // Rule 451:  ClassInstanceCreationExpression ::= AmbiguousName . new identifier ( ArgumentListopt ) ClassBodyopt
            //
            case 451: {
                //#line 1008 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Name AmbiguousName = (Name) getRhsSym(1);
                //#line 1008 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(4);
                //#line 1008 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(6);
                //#line 1008 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                ClassBody ClassBodyopt = (ClassBody) getRhsSym(8);
                //#line 1010 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                Name b = new Name(nf, ts, pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier()));
                if (ClassBodyopt == null)
                     setResult(nf.New(pos(), AmbiguousName.toExpr(), b.toType(), ArgumentListopt));
                else setResult(nf.New(pos(), AmbiguousName.toExpr(), b.toType(), ArgumentListopt, ClassBodyopt));
                break;
            }
     
            //
            // Rule 452:  MethodInvocation ::= Primary . identifier ( ArgumentListopt )
            //
            case 452: {
                //#line 1017 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Primary = (Expr) getRhsSym(1);
                //#line 1017 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 1017 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(5);
                //#line 1019 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Call(pos(), Primary, nf.Id(identifier.getPosition(), identifier.getIdentifier()), ArgumentListopt));
                break;
            }
     
            //
            // Rule 453:  MethodInvocation ::= super . identifier ( ArgumentListopt )
            //
            case 453: {
                //#line 1022 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 1022 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(5);
                //#line 1024 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Call(pos(), nf.Super(pos(getLeftSpan())), nf.Id(identifier.getPosition(), identifier.getIdentifier()), ArgumentListopt));
                break;
            }
     
            //
            // Rule 454:  MethodInvocation ::= ClassName . super$sup . identifier ( ArgumentListopt )
            //
            case 454: {
                //#line 1027 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Name ClassName = (Name) getRhsSym(1);
                //#line 1027 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                IToken sup = (IToken) getRhsIToken(3);
                //#line 1027 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(5);
                //#line 1027 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ArgumentListopt = (List) getRhsSym(7);
                //#line 1029 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Call(pos(), nf.Super(pos(getRhsFirstTokenIndex(3)), ClassName.toType()), nf.Id(identifier.getPosition(), identifier.getIdentifier()), ArgumentListopt));
                break;
            }
     
            //
            // Rule 455:  AssignPropertyCall ::= property ( ArgumentList )
            //
            case 455: {
                //#line 1034 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ArgumentList = (List) getRhsSym(3);
                //#line 1036 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.AssignPropertyCall(pos(),  ArgumentList));
                break;
            }
     
            //
            // Rule 459:  AnnotatedType ::= Type Annotations
            //
            case 459: {
                //#line 1049 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(1);
                //#line 1049 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List Annotations = (List) getRhsSym(2);
                //#line 1051 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                TypeNode tn = Type;
                tn = (TypeNode) ((X10Ext) tn.ext()).annotations((List<AnnotationNode>) Annotations);
                setResult(tn);
                break;
            }
     
            //
            // Rule 460:  SpecialType ::= nullable < Type > DepParametersopt
            //
            case 460: {
                //#line 1057 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(3);
                //#line 1057 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                DepParameterExpr DepParametersopt = (DepParameterExpr) getRhsSym(5);
                //#line 1059 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                X10TypeNode t = nf.Nullable(pos(), Type);
                setResult(DepParametersopt == null ? t 
                : t.dep(null, DepParametersopt));
      
                break;
            }
     
            //
            // Rule 461:  SpecialType ::= future < Type >
            //
            case 461: {
                //#line 1065 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(3);
                //#line 1067 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.FutureNode(pos(), Type));
                break;
            }
     
            //
            // Rule 464:  PrimitiveType ::= NumericType DepParametersopt
            //
            case 464: {
                //#line 1080 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode NumericType = (TypeNode) getRhsSym(1);
                //#line 1080 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                DepParameterExpr DepParametersopt = (DepParameterExpr) getRhsSym(2);
                //#line 1082 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
              //  System.out.println("Parser: parsed PrimitiveType |" + NumericType + "| |" + DepParametersopt +"|");
                setResult(DepParametersopt == null
                               ? NumericType
                               : ((X10TypeNode) NumericType).dep(null, DepParametersopt));
                break;
            }
     
            //
            // Rule 465:  PrimitiveType ::= boolean DepParametersopt
            //
            case 465: {
                //#line 1088 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                DepParameterExpr DepParametersopt = (DepParameterExpr) getRhsSym(2);
                //#line 1090 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                X10TypeNode res = (X10TypeNode) nf.CanonicalTypeNode(pos(), ts.Boolean());
                setResult(DepParametersopt==null 
                           ? res 
                           : res.dep(null, DepParametersopt));
               break;
            }
     
            //
            // Rule 470:  ClassOrInterfaceType ::= TypeName DepParametersopt PlaceTypeSpecifieropt
            //
            case 470: {
                //#line 1102 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Name TypeName = (Name) getRhsSym(1);
                //#line 1102 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                DepParameterExpr DepParametersopt = (DepParameterExpr) getRhsSym(2);
                //#line 1102 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object PlaceTypeSpecifieropt = (Object) getRhsSym(3);
                //#line 1104 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
            X10TypeNode type;
            
            if (ts.isPrimitiveTypeName(TypeName.name.id())) {
                try {
                    type= (X10TypeNode) nf.CanonicalTypeNode(pos(), ts.primitiveForName(TypeName.name.id()));
                } catch (SemanticException e) {
                    throw new InternalCompilerError("Unable to create primitive type for '" + TypeName.name.id() + "'!");
                }
            } else
                type= (X10TypeNode) TypeName.toType();
            //  System.out.println("Parser: parsed ClassOrInterfaceType |" + TypeName + "| |" + DepParametersopt +"|");
                setResult(DepParametersopt == null
                               ? type
                               : type.dep(null, DepParametersopt));
                break;
            }
     
            //
            // Rule 471:  DepParameters ::= ( DepParameterExpr )
            //
            case 471: {
                //#line 1121 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                DepParameterExpr DepParameterExpr = (DepParameterExpr) getRhsSym(2);
                //#line 1123 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(DepParameterExpr);
                break;
            }
     
            //
            // Rule 472:  DepParameterExpr ::= ArgumentList WhereClauseopt
            //
            case 472: {
                //#line 1127 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ArgumentList = (List) getRhsSym(1);
                //#line 1127 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr WhereClauseopt = (Expr) getRhsSym(2);
                //#line 1129 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.DepParameterExpr(pos(), ArgumentList, WhereClauseopt));
                break;
            }
     
            //
            // Rule 473:  DepParameterExpr ::= WhereClause
            //
            case 473: {
                //#line 1132 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr WhereClause = (Expr) getRhsSym(1);
                //#line 1134 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.DepParameterExpr(pos(), Collections.EMPTY_LIST, WhereClause));
                break;
            }
     
            //
            // Rule 474:  WhereClause ::= : ConstExpression
            //
            case 474: {
                //#line 1138 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstExpression = (Expr) getRhsSym(2);
                //#line 1140 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ConstExpression);
                break;
            }
     
            //
            // Rule 475:  ConstPrimary ::= Literal
            //
            case 475: {
                //#line 1145 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.ast.Lit Literal = (polyglot.ast.Lit) getRhsSym(1);
                //#line 1147 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Literal);
                break;
            }
     
            //
            // Rule 476:  ConstPrimary ::= Type . class
            //
            case 476: {
                //#line 1150 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(1);
                //#line 1152 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                if (Type instanceof Name)
                {
                    Name a = (Name) Type;
                    setResult(nf.ClassLit(pos(), a.toType()));
                }
                else if (Type instanceof TypeNode)
                {
                    setResult(nf.ClassLit(pos(), Type));
                }
                else if (Type instanceof CanonicalTypeNode)
                {
                    CanonicalTypeNode a = (CanonicalTypeNode) Type;
                    setResult(nf.ClassLit(pos(), a));
                }
                else assert(false);
                break;
            }
     
            //
            // Rule 477:  ConstPrimary ::= void . class
            //
            case 477: {
                
                //#line 1171 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.ClassLit(pos(),
                                     nf.CanonicalTypeNode(pos(getLeftSpan()), ts.Void())));
                break;
            }
     
            //
            // Rule 478:  ConstPrimary ::= this
            //
            case 478: {
                
                //#line 1177 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.This(pos()));
                break;
            }
     
            //
            // Rule 479:  ConstPrimary ::= here
            //
            case 479: {
                
                //#line 1182 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Here(pos()));
                break;
            }
     
            //
            // Rule 480:  ConstPrimary ::= ClassName . this
            //
            case 480: {
                //#line 1185 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Name ClassName = (Name) getRhsSym(1);
                //#line 1187 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.This(pos(), ClassName.toType()));
                break;
            }
     
            //
            // Rule 481:  ConstPrimary ::= ( ConstExpression )
            //
            case 481: {
                //#line 1190 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstExpression = (Expr) getRhsSym(2);
                //#line 1192 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ConstExpression);
                break;
            }
     
            //
            // Rule 483:  ConstPrimary ::= self
            //
            case 483: {
                
                //#line 1198 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Self(pos()));
                break;
            }
     
            //
            // Rule 484:  ConstPostfixExpression ::= ConstPrimary
            //
            case 484: {
                //#line 1204 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstPrimary = (Expr) getRhsSym(1);
                //#line 1206 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ConstPrimary);
                        break;
            }
            
            //
            // Rule 485:  ConstPostfixExpression ::= ExpressionName
            //
            case 485: {
                //#line 1209 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Name ExpressionName = (Name) getRhsSym(1);
                //#line 1211 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ExpressionName.toExpr());
                break;
            }
     
            //
            // Rule 486:  ConstUnaryExpression ::= ConstPostfixExpression
            //
            case 486: {
                //#line 1214 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstPostfixExpression = (Expr) getRhsSym(1);
                //#line 1216 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ConstPostfixExpression);
                        break;
            }
            
            //
            // Rule 487:  ConstUnaryExpression ::= + ConstUnaryExpression
            //
            case 487: {
                //#line 1219 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstUnaryExpression = (Expr) getRhsSym(2);
                //#line 1221 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Unary(pos(), Unary.POS, ConstUnaryExpression));
                break;
            }
     
            //
            // Rule 488:  ConstUnaryExpression ::= - ConstUnaryExpression
            //
            case 488: {
                //#line 1224 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstUnaryExpression = (Expr) getRhsSym(2);
                //#line 1226 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Unary(pos(), Unary.NEG, ConstUnaryExpression));
                break;
            }
     
            //
            // Rule 489:  ConstUnaryExpression ::= ! ConstUnaryExpression
            //
            case 489: {
                //#line 1229 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstUnaryExpression = (Expr) getRhsSym(2);
                //#line 1231 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Unary(pos(), Unary.NOT, ConstUnaryExpression));
                break;
            }
     
            //
            // Rule 490:  ConstMultiplicativeExpression ::= ConstUnaryExpression
            //
            case 490: {
                //#line 1235 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstUnaryExpression = (Expr) getRhsSym(1);
                //#line 1237 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ConstUnaryExpression);
                break;
            }
     
            //
            // Rule 491:  ConstMultiplicativeExpression ::= ConstMultiplicativeExpression * ConstUnaryExpression
            //
            case 491: {
                //#line 1240 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstMultiplicativeExpression = (Expr) getRhsSym(1);
                //#line 1240 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstUnaryExpression = (Expr) getRhsSym(3);
                //#line 1242 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConstMultiplicativeExpression, Binary.MUL, ConstUnaryExpression));
                break;
            }
     
            //
            // Rule 492:  ConstMultiplicativeExpression ::= ConstMultiplicativeExpression / ConstUnaryExpression
            //
            case 492: {
                //#line 1245 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstMultiplicativeExpression = (Expr) getRhsSym(1);
                //#line 1245 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstUnaryExpression = (Expr) getRhsSym(3);
                //#line 1247 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConstMultiplicativeExpression, Binary.DIV, ConstUnaryExpression));
                break;
            }
     
            //
            // Rule 493:  ConstMultiplicativeExpression ::= ConstMultiplicativeExpression % ConstUnaryExpression
            //
            case 493: {
                //#line 1250 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstMultiplicativeExpression = (Expr) getRhsSym(1);
                //#line 1250 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstUnaryExpression = (Expr) getRhsSym(3);
                //#line 1252 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConstMultiplicativeExpression, Binary.MOD, ConstUnaryExpression));
                break;
            }
     
            //
            // Rule 494:  ConstAdditiveExpression ::= ConstMultiplicativeExpression
            //
            case 494: {
                //#line 1256 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstMultiplicativeExpression = (Expr) getRhsSym(1);
                //#line 1258 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ConstMultiplicativeExpression);
                break;
            }
     
            //
            // Rule 495:  ConstAdditiveExpression ::= ConstAdditiveExpression + ConstMultiplicativeExpression
            //
            case 495: {
                //#line 1261 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstAdditiveExpression = (Expr) getRhsSym(1);
                //#line 1261 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstMultiplicativeExpression = (Expr) getRhsSym(3);
                //#line 1263 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConstAdditiveExpression, Binary.ADD, ConstMultiplicativeExpression));
                break;
            }
     
            //
            // Rule 496:  ConstAdditiveExpression ::= ConstAdditiveExpression - ConstMultiplicativeExpression
            //
            case 496: {
                //#line 1266 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstAdditiveExpression = (Expr) getRhsSym(1);
                //#line 1266 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstMultiplicativeExpression = (Expr) getRhsSym(3);
                //#line 1268 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConstAdditiveExpression, Binary.SUB, ConstMultiplicativeExpression));
                break;
            }
     
            //
            // Rule 497:  ConstRelationalExpression ::= ConstAdditiveExpression
            //
            case 497: {
                //#line 1273 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstAdditiveExpression = (Expr) getRhsSym(1);
                //#line 1275 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ConstAdditiveExpression);
                break;
            }
     
            //
            // Rule 498:  ConstRelationalExpression ::= ConstRelationalExpression < ConstAdditiveExpression
            //
            case 498: {
                //#line 1278 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstRelationalExpression = (Expr) getRhsSym(1);
                //#line 1278 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstAdditiveExpression = (Expr) getRhsSym(3);
                //#line 1280 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConstRelationalExpression, Binary.LT, ConstAdditiveExpression));
                break;
            }
     
            //
            // Rule 499:  ConstRelationalExpression ::= ConstRelationalExpression > ConstAdditiveExpression
            //
            case 499: {
                //#line 1283 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstRelationalExpression = (Expr) getRhsSym(1);
                //#line 1283 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstAdditiveExpression = (Expr) getRhsSym(3);
                //#line 1285 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConstRelationalExpression, Binary.GT, ConstAdditiveExpression));
                break;
            }
     
            //
            // Rule 500:  ConstRelationalExpression ::= ConstRelationalExpression <= ConstAdditiveExpression
            //
            case 500: {
                //#line 1288 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstRelationalExpression = (Expr) getRhsSym(1);
                //#line 1288 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstAdditiveExpression = (Expr) getRhsSym(3);
                //#line 1290 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConstRelationalExpression, Binary.LE, ConstAdditiveExpression));
                break;
            }
     
            //
            // Rule 501:  ConstRelationalExpression ::= ConstRelationalExpression > = ConstAdditiveExpression
            //
            case 501: {
                //#line 1293 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstRelationalExpression = (Expr) getRhsSym(1);
                //#line 1293 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstAdditiveExpression = (Expr) getRhsSym(4);
                //#line 1295 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConstRelationalExpression, Binary.GE, ConstAdditiveExpression));
                break;
            }
     
            //
            // Rule 502:  ConstEqualityExpression ::= ConstRelationalExpression
            //
            case 502: {
                //#line 1299 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstRelationalExpression = (Expr) getRhsSym(1);
                //#line 1301 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ConstRelationalExpression);
                break;
            }
     
            //
            // Rule 503:  ConstEqualityExpression ::= ConstEqualityExpression == ConstRelationalExpression
            //
            case 503: {
                //#line 1304 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstEqualityExpression = (Expr) getRhsSym(1);
                //#line 1304 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstRelationalExpression = (Expr) getRhsSym(3);
                //#line 1306 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConstEqualityExpression, Binary.EQ, ConstRelationalExpression));
                break;
            }
     
            //
            // Rule 504:  ConstEqualityExpression ::= ConstEqualityExpression != ConstRelationalExpression
            //
            case 504: {
                //#line 1309 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstEqualityExpression = (Expr) getRhsSym(1);
                //#line 1309 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstRelationalExpression = (Expr) getRhsSym(3);
                //#line 1311 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConstEqualityExpression, Binary.NE, ConstRelationalExpression));
                break;
            }
     
            //
            // Rule 505:  ConstAndExpression ::= ConstEqualityExpression
            //
            case 505: {
                //#line 1315 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstEqualityExpression = (Expr) getRhsSym(1);
                //#line 1317 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ConstEqualityExpression);
                break;
            }
     
            //
            // Rule 506:  ConstAndExpression ::= ConstAndExpression && ConstEqualityExpression
            //
            case 506: {
                //#line 1320 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstAndExpression = (Expr) getRhsSym(1);
                //#line 1320 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstEqualityExpression = (Expr) getRhsSym(3);
                //#line 1322 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConstAndExpression, Binary.COND_AND, ConstEqualityExpression));
                break;
            }
     
            //
            // Rule 507:  ConstExclusiveOrExpression ::= ConstAndExpression
            //
            case 507: {
                //#line 1326 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstAndExpression = (Expr) getRhsSym(1);
                //#line 1328 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ConstAndExpression);
                break;
            }
     
            //
            // Rule 508:  ConstExclusiveOrExpression ::= ConstExclusiveOrExpression ^ ConstAndExpression
            //
            case 508: {
                //#line 1331 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstExclusiveOrExpression = (Expr) getRhsSym(1);
                //#line 1331 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstAndExpression = (Expr) getRhsSym(3);
                //#line 1333 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConstExclusiveOrExpression, Binary.BIT_XOR, ConstAndExpression));
                break;
            }
     
            //
            // Rule 509:  ConstInclusiveOrExpression ::= ConstExclusiveOrExpression
            //
            case 509: {
                //#line 1337 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstExclusiveOrExpression = (Expr) getRhsSym(1);
                //#line 1339 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ConstExclusiveOrExpression);
                break;
            }
     
            //
            // Rule 510:  ConstInclusiveOrExpression ::= ConstInclusiveOrExpression || ConstExclusiveOrExpression
            //
            case 510: {
                //#line 1342 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstInclusiveOrExpression = (Expr) getRhsSym(1);
                //#line 1342 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstExclusiveOrExpression = (Expr) getRhsSym(3);
                //#line 1344 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Binary(pos(), ConstInclusiveOrExpression, Binary.COND_OR, ConstExclusiveOrExpression));
                break;
            }
     
            //
            // Rule 511:  ConstExpression ::= ConstInclusiveOrExpression
            //
            case 511: {
                //#line 1348 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstInclusiveOrExpression = (Expr) getRhsSym(1);
                //#line 1350 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ConstInclusiveOrExpression);
                break;
            }
     
            //
            // Rule 512:  ConstExpression ::= ConstInclusiveOrExpression ? ConstExpression$first : ConstExpression
            //
            case 512: {
                //#line 1353 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstInclusiveOrExpression = (Expr) getRhsSym(1);
                //#line 1353 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr first = (Expr) getRhsSym(3);
                //#line 1353 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstExpression = (Expr) getRhsSym(5);
                //#line 1355 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Conditional(pos(), ConstInclusiveOrExpression, first, ConstExpression));
                break;
            }
     
            //
            // Rule 513:  ConstFieldAccess ::= ConstPrimary . identifier
            //
            case 513: {
                //#line 1360 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr ConstPrimary = (Expr) getRhsSym(1);
                //#line 1360 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 1362 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Field(pos(), ConstPrimary, nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 514:  ConstFieldAccess ::= super . identifier
            //
            case 514: {
                //#line 1365 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 1367 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Field(pos(getRightSpan()), nf.Super(pos(getLeftSpan())), nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 515:  ConstFieldAccess ::= ClassName . super$sup . identifier
            //
            case 515: {
                //#line 1370 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Name ClassName = (Name) getRhsSym(1);
                //#line 1370 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                IToken sup = (IToken) getRhsIToken(3);
                //#line 1370 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(5);
                //#line 1372 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Field(pos(getRightSpan()), nf.Super(pos(getRhsFirstTokenIndex(3)), ClassName.toType()), nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 517:  X10ArrayType ::= Type [ . ]
            //
            case 517: {
                //#line 1388 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(1);
                //#line 1390 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.X10ArrayTypeNode(pos(), Type, false, null));
                break;
            }
     
            //
            // Rule 518:  X10ArrayType ::= Type value [ . ]
            //
            case 518: {
                //#line 1393 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(1);
                //#line 1395 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.X10ArrayTypeNode(pos(), Type, true, null));
                break;
            }
     
            //
            // Rule 519:  X10ArrayType ::= Type [ DepParameterExpr ]
            //
            case 519: {
                //#line 1398 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(1);
                //#line 1398 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                DepParameterExpr DepParameterExpr = (DepParameterExpr) getRhsSym(3);
                //#line 1400 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.X10ArrayTypeNode(pos(), Type, false, DepParameterExpr));
                break;
            }
     
            //
            // Rule 520:  X10ArrayType ::= Type value [ DepParameterExpr ]
            //
            case 520: {
                //#line 1403 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(1);
                //#line 1403 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                DepParameterExpr DepParameterExpr = (DepParameterExpr) getRhsSym(4);
                //#line 1405 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.X10ArrayTypeNode(pos(), Type, true, DepParameterExpr));
                break;
            }
     
            //
            // Rule 521:  ObjectKind ::= value
            //
            case 521:
                throw new Error("No action specified for rule " + 521);
 
            //
            // Rule 522:  ObjectKind ::= reference
            //
            case 522:
                throw new Error("No action specified for rule " + 522);
 
            //
            // Rule 523:  MethodModifier ::= atomic
            //
            case 523: {
                
                //#line 1419 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(X10Flags.ATOMIC));
                break;
            }
     
            //
            // Rule 524:  MethodModifier ::= extern
            //
            case 524: {
                
                //#line 1424 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.NATIVE));
                break;
            }
     
            //
            // Rule 525:  MethodModifier ::= safe
            //
            case 525: {
                
                //#line 1429 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(X10Flags.SAFE));
                break;
            }
     
            //
            // Rule 526:  MethodModifier ::= sequential
            //
            case 526: {
                
                //#line 1434 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(X10Flags.SEQUENTIAL));
                break;
            }
     
            //
            // Rule 527:  MethodModifier ::= local
            //
            case 527: {
                
                //#line 1439 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(X10Flags.LOCAL));
                break;
            }
     
            //
            // Rule 528:  MethodModifier ::= nonblocking
            //
            case 528: {
                
                //#line 1444 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(X10Flags.NON_BLOCKING));
                break;
            }
     
            //
            // Rule 530:  ValueClassDeclaration ::= X10ClassModifiersopt value identifier PropertyListopt Superopt Interfacesopt ClassBody
            //
            case 530: {
                //#line 1450 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List X10ClassModifiersopt = (List) getRhsSym(1);
                //#line 1450 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 1450 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object[] PropertyListopt = (Object[]) getRhsSym(4);
                //#line 1450 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode Superopt = (TypeNode) getRhsSym(5);
                //#line 1450 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List Interfacesopt = (List) getRhsSym(6);
                //#line 1450 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                ClassBody ClassBody = (ClassBody) getRhsSym(7);
                //#line 1452 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
    checkTypeName(identifier);
    List/*<PropertyDecl>*/ props = PropertyListopt==null ? null : (List) PropertyListopt[0];
    Expr ci = PropertyListopt==null ? null : (Expr) PropertyListopt[1];
    ClassDecl cd = (nf.ValueClassDecl(pos(getLeftSpan(), getRightSpan()),
    extractFlags(X10ClassModifiersopt), nf.Id(identifier.getPosition(), identifier.getIdentifier()), 
    props, ci, Superopt, Interfacesopt, ClassBody));
    cd = (ClassDecl) ((X10Ext) cd.ext()).annotations(extractAnnotations(X10ClassModifiersopt));
    setResult(cd);
                break;
            }
     
            //
            // Rule 531:  ValueClassDeclaration ::= X10ClassModifiersopt value class identifier PropertyListopt Superopt Interfacesopt ClassBody
            //
            case 531: {
                //#line 1462 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List X10ClassModifiersopt = (List) getRhsSym(1);
                //#line 1462 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(4);
                //#line 1462 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object[] PropertyListopt = (Object[]) getRhsSym(5);
                //#line 1462 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode Superopt = (TypeNode) getRhsSym(6);
                //#line 1462 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List Interfacesopt = (List) getRhsSym(7);
                //#line 1462 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                ClassBody ClassBody = (ClassBody) getRhsSym(8);
                //#line 1464 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                checkTypeName(identifier);
    List/*<PropertyDecl>*/ props = PropertyListopt==null ? null : (List) PropertyListopt[0];
    Expr ci = PropertyListopt==null ? null : (Expr) PropertyListopt[1];
    ClassDecl cd = (nf.ValueClassDecl(pos(getLeftSpan(), getRightSpan()),
                                extractFlags(X10ClassModifiersopt), nf.Id(identifier.getPosition(), identifier.getIdentifier()), 
                                props, ci, Superopt, Interfacesopt, ClassBody));
    cd = (ClassDecl) ((X10Ext) cd.ext()).annotations(extractAnnotations(X10ClassModifiersopt));
    setResult(cd);
                break;
            }
     
            //
            // Rule 532:  ConstructorDeclaration ::= ConstructorModifiersopt ConstructorDeclarator Throwsopt ConstructorBody
            //
            case 532: {
                //#line 1475 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ConstructorModifiersopt = (List) getRhsSym(1);
                //#line 1475 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object[] ConstructorDeclarator = (Object[]) getRhsSym(2);
                //#line 1475 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List Throwsopt = (List) getRhsSym(3);
                //#line 1475 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Block ConstructorBody = (Block) getRhsSym(4);
                //#line 1477 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
     Name a = (Name) ConstructorDeclarator[1];
     DepParameterExpr c = (DepParameterExpr) ConstructorDeclarator[2];
     List b = (List) ConstructorDeclarator[3];
     Expr e = (Expr) ConstructorDeclarator[4];
     List n = (List) ConstructorDeclarator[5];
     
       X10TypeNode resultType = (X10TypeNode) a.toType();        
       if (c != null) 
     resultType = resultType.dep(c);
     if (resultType.ext() instanceof X10Ext && n != null) {
         resultType = (X10TypeNode) ((X10Ext) resultType.ext()).annotations(n);
     }
     ConstructorDecl cd = (nf.ConstructorDecl(pos(), extractFlags(ConstructorModifiersopt), nf.Id(a.pos, a.toString()), resultType, b, e, Throwsopt, ConstructorBody));
     cd = (ConstructorDecl) ((X10Ext) cd.ext()).annotations(extractAnnotations(ConstructorModifiersopt));
     setResult(cd);
               break;
            }
    
            //
            // Rule 533:  ConstructorDeclarator ::= SimpleTypeName DepParametersopt Annotationsopt ( FormalParameterListopt WhereClauseopt )
            //
            case 533: {
                //#line 1495 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Name SimpleTypeName = (Name) getRhsSym(1);
                //#line 1495 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                DepParameterExpr DepParametersopt = (DepParameterExpr) getRhsSym(2);
                //#line 1495 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object Annotationsopt = (Object) getRhsSym(3);
                //#line 1495 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List FormalParameterListopt = (List) getRhsSym(5);
                //#line 1495 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr WhereClauseopt = (Expr) getRhsSym(6);
                //#line 1497 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
             Object[] a = new Object[6];
             a[1] = SimpleTypeName;
             a[2] = DepParametersopt;
             a[3] = FormalParameterListopt;
             a[4] = WhereClauseopt;
             a[5] = Annotationsopt;
             setResult(a);
               break;
            }
    
            //
            // Rule 534:  ThisClause ::= this DepParameters
            //
            case 534: {
                //#line 1506 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                DepParameterExpr DepParameters = (DepParameterExpr) getRhsSym(2);
                //#line 1508 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(DepParameters);
                break;
            }
     
            //
            // Rule 535:  Super ::= extends DataType
            //
            case 535: {
                //#line 1512 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode DataType = (TypeNode) getRhsSym(2);
                //#line 1514 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(DataType);
                break;
            }
     
            //
            // Rule 536:  MethodDeclarator ::= identifier ( FormalParameterListopt WhereClauseopt )
            //
            case 536: {
                //#line 1518 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 1518 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List FormalParameterListopt = (List) getRhsSym(3);
                //#line 1518 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr WhereClauseopt = (Expr) getRhsSym(4);
                //#line 1520 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
            //   System.out.println("Parsing methoddeclarator...");
                Object[] a = new Object[5];
               a[0] = new Name(nf, ts, pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier()));
                a[1] = FormalParameterListopt;
               a[2] = new Integer(0);
               a[3] = WhereClauseopt;
             
                setResult(a);
                break;
            }
     
            //
            // Rule 537:  MethodDeclarator ::= MethodDeclarator [ ]
            //
            case 537: {
                //#line 1530 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object[] MethodDeclarator = (Object[]) getRhsSym(1);
                //#line 1532 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                MethodDeclarator[2] = new Integer(((Integer) MethodDeclarator[2]).intValue() + 1);
                // setResult(MethodDeclarator);
               break;
            }
     
            //
            // Rule 538:  FieldDeclaration ::= ThisClauseopt FieldModifiersopt Type VariableDeclarators ;
            //
            case 538: {
                //#line 1538 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                DepParameterExpr ThisClauseopt = (DepParameterExpr) getRhsSym(1);
                //#line 1538 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List FieldModifiersopt = (List) getRhsSym(2);
                //#line 1538 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(3);
                //#line 1538 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List VariableDeclarators = (List) getRhsSym(4);
                //#line 1540 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), ClassMember.class, false);
                if (VariableDeclarators != null && VariableDeclarators.size() > 0) {
                    boolean gt1 = (VariableDeclarators.size() > 1);
                    for (Iterator i = VariableDeclarators.iterator(); i.hasNext();)
                    {
                        X10VarDeclarator d = (X10VarDeclarator) i.next();
                        if (d.hasExplodedVars())
                          // TODO: Report this exception correctly.
                          throw new Error("Field Declarations may not have exploded variables." + pos());
                        Position p = gt1 ? d.position() :
                        		pos(getRhsFirstTokenIndex(1), getRhsLastTokenIndex(4));
                        d.setFlag(extractFlags(FieldModifiersopt));
                        FieldDecl fd = nf.FieldDecl(p,
                                           ThisClauseopt,
                                           d.flags,
                                           nf.array(Type, Type.position(), d.dims),
                                           d.name,
                                           d.init);
                        fd = (FieldDecl) ((X10Ext) fd.ext()).annotations(extractAnnotations(FieldModifiersopt));
                        l.add(fd);
                    }
                }
                setResult(l);
                break;
            }
     
            //
            // Rule 539:  ArrayCreationExpression ::= new ArrayBaseType Unsafeopt Dims ArrayInitializer
            //
            case 539: {
                //#line 1579 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode ArrayBaseType = (TypeNode) getRhsSym(2);
                //#line 1579 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object Unsafeopt = (Object) getRhsSym(3);
                //#line 1579 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Integer Dims = (Integer) getRhsSym(4);
                //#line 1579 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                ArrayInit ArrayInitializer = (ArrayInit) getRhsSym(5);
                //#line 1581 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                // setResult(nf.ArrayConstructor(pos(), a, false, null, d));
                setResult(nf.NewArray(pos(), ArrayBaseType, Dims.intValue(), ArrayInitializer));
                break;
            }
     
            //
            // Rule 540:  ArrayCreationExpression ::= new ArrayBaseType Unsafeopt DimExpr Dims
            //
            case 540: {
                //#line 1585 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode ArrayBaseType = (TypeNode) getRhsSym(2);
                //#line 1585 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object Unsafeopt = (Object) getRhsSym(3);
                //#line 1585 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr DimExpr = (Expr) getRhsSym(4);
                //#line 1585 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Integer Dims = (Integer) getRhsSym(5);
                //#line 1587 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                // setResult(nf.ArrayConstructor(pos(), a, false, null, d));
                setResult(nf.NewArray(pos(), ArrayBaseType, Collections.singletonList(DimExpr), Dims.intValue()));
                break;
            }
     
            //
            // Rule 541:  ArrayCreationExpression ::= new ArrayBaseType Unsafeopt DimExpr DimExprs Dimsopt
            //
            case 541: {
                //#line 1591 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode ArrayBaseType = (TypeNode) getRhsSym(2);
                //#line 1591 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object Unsafeopt = (Object) getRhsSym(3);
                //#line 1591 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr DimExpr = (Expr) getRhsSym(4);
                //#line 1591 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List DimExprs = (List) getRhsSym(5);
                //#line 1591 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Integer Dimsopt = (Integer) getRhsSym(6);
                //#line 1593 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                // setResult(nf.ArrayConstructor(pos(), a, false, null, d));
                List l = new TypedList(new LinkedList(), Expr.class, false);
                l.add(DimExpr);
                l.addAll(DimExprs);
                setResult(nf.NewArray(pos(), ArrayBaseType, l, Dimsopt.intValue()));
                break;
            }
     
            //
            // Rule 542:  ArrayCreationExpression ::= new ArrayBaseType Valueopt Unsafeopt [ Expression ]
            //
            case 542: {
                //#line 1600 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode ArrayBaseType = (TypeNode) getRhsSym(2);
                //#line 1600 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object Valueopt = (Object) getRhsSym(3);
                //#line 1600 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object Unsafeopt = (Object) getRhsSym(4);
                //#line 1600 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(6);
                //#line 1602 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.ArrayConstructor(pos(), ArrayBaseType, Unsafeopt != null, Valueopt != null, Expression, null));
                break;
            }
     
            //
            // Rule 543:  ArrayCreationExpression ::= new ArrayBaseType Valueopt Unsafeopt [ Expression$distr ] Expression$initializer
            //
            case 543: {
                //#line 1605 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode ArrayBaseType = (TypeNode) getRhsSym(2);
                //#line 1605 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object Valueopt = (Object) getRhsSym(3);
                //#line 1605 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object Unsafeopt = (Object) getRhsSym(4);
                //#line 1605 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr distr = (Expr) getRhsSym(6);
                //#line 1605 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr initializer = (Expr) getRhsSym(8);
                //#line 1607 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.ArrayConstructor(pos(), ArrayBaseType, Unsafeopt != null, Valueopt != null, distr, initializer));
                break;
            }
     
            //
            // Rule 544:  ArrayCreationExpression ::= new ArrayBaseType Valueopt Unsafeopt [ Expression ] ($lparen FormalParameter ) MethodBody
            //
            case 544: {
                //#line 1610 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode ArrayBaseType = (TypeNode) getRhsSym(2);
                //#line 1610 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object Valueopt = (Object) getRhsSym(3);
                //#line 1610 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Object Unsafeopt = (Object) getRhsSym(4);
                //#line 1610 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(6);
                //#line 1610 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                IToken lparen = (IToken) getRhsIToken(8);
                //#line 1610 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                X10Formal FormalParameter = (X10Formal) getRhsSym(9);
                //#line 1610 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Block MethodBody = (Block) getRhsSym(11);
                //#line 1612 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                // Expr initializer = makeInitializer( pos(getRhsFirstTokenIndex(8), getRightSpan()), ArrayBaseType, FormalParameter, MethodBody );
                List formals = new TypedList(new ArrayList(1), Formal.class, false);
                formals.add(FormalParameter);
                Closure closure = nf.Closure(MethodBody.position(), formals, ArrayBaseType, new TypedList(new ArrayList(), Type.class, true), MethodBody);
                setResult(nf.ArrayConstructor(pos(), ArrayBaseType, Unsafeopt != null, Valueopt != null, Expression, closure));
                break;
            }
     
            //
            // Rule 545:  Valueopt ::= $Empty
            //
            case 545:
                setResult(null);
                break;
 
            //
            // Rule 546:  Valueopt ::= value
            //
            case 546: {
                
                //#line 1624 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                // any value distinct from null
                setResult(this);
                break;
            }
     
            //
            // Rule 551:  ArrayBaseType ::= ( Type )
            //
            case 551: {
                //#line 1633 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(2);
                //#line 1635 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Type);
                break;
            }
     
            //
            // Rule 552:  ArrayAccess ::= ExpressionName [ ArgumentList ]
            //
            case 552: {
                //#line 1639 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Name ExpressionName = (Name) getRhsSym(1);
                //#line 1639 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ArgumentList = (List) getRhsSym(3);
                //#line 1641 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                if (ArgumentList.size() == 1)
                     setResult(nf.X10ArrayAccess1(pos(), ExpressionName.toExpr(), (Expr) ArgumentList.get(0)));
                else setResult(nf.X10ArrayAccess(pos(), ExpressionName.toExpr(), ArgumentList));
                break;
            }
     
            //
            // Rule 553:  ArrayAccess ::= PrimaryNoNewArray [ ArgumentList ]
            //
            case 553: {
                //#line 1646 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr PrimaryNoNewArray = (Expr) getRhsSym(1);
                //#line 1646 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ArgumentList = (List) getRhsSym(3);
                //#line 1648 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                if (ArgumentList.size() == 1)
                     setResult(nf.X10ArrayAccess1(pos(), PrimaryNoNewArray, (Expr) ArgumentList.get(0)));
                else setResult(nf.X10ArrayAccess(pos(), PrimaryNoNewArray, ArgumentList));
                break;
            }
     
            //
            // Rule 573:  NowStatement ::= now ( Clock ) Statement
            //
            case 573: {
                //#line 1679 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Clock = (Expr) getRhsSym(3);
                //#line 1679 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(5);
                //#line 1681 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
              setResult(nf.Now(pos(), Clock, Statement));
                break;
            }
     
            //
            // Rule 574:  ClockedClause ::= clocked ( ClockList )
            //
            case 574: {
                //#line 1685 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ClockList = (List) getRhsSym(3);
                //#line 1687 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(ClockList);
                break;
            }
     
            //
            // Rule 575:  AsyncStatement ::= async PlaceExpressionSingleListopt ClockedClauseopt Statement
            //
            case 575: {
                //#line 1691 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr PlaceExpressionSingleListopt = (Expr) getRhsSym(2);
                //#line 1691 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ClockedClauseopt = (List) getRhsSym(3);
                //#line 1691 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(4);
                //#line 1693 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
              setResult(nf.Async(pos(), (PlaceExpressionSingleListopt == null
                                                                        ? nf.Here(pos(getLeftSpan()))
                                                                        : PlaceExpressionSingleListopt),
                                         ClockedClauseopt, Statement));
                break;
            }
     
            //
            // Rule 576:  AtomicStatement ::= atomic PlaceExpressionSingleListopt Statement
            //
            case 576: {
                //#line 1701 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr PlaceExpressionSingleListopt = (Expr) getRhsSym(2);
                //#line 1701 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(3);
                //#line 1703 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
              setResult(nf.Atomic(pos(), (PlaceExpressionSingleListopt == null
                                               ? nf.Here(pos(getLeftSpan()))
                                               : PlaceExpressionSingleListopt), Statement));
                break;
            }
     
            //
            // Rule 577:  WhenStatement ::= when ( Expression ) Statement
            //
            case 577: {
                //#line 1710 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1710 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(5);
                //#line 1712 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.When(pos(), Expression, Statement));
                break;
            }
     
            //
            // Rule 578:  WhenStatement ::= WhenStatement or$or ( Expression ) Statement
            //
            case 578: {
                //#line 1715 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                When WhenStatement = (When) getRhsSym(1);
                //#line 1715 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                IToken or = (IToken) getRhsIToken(2);
                //#line 1715 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(4);
                //#line 1715 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(6);
                //#line 1717 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
              WhenStatement.addBranch(pos(getRhsFirstTokenIndex(2), getRightSpan()), Expression, Statement);
              setResult(WhenStatement);
                break;
            }
     
            //
            // Rule 579:  ForEachStatement ::= foreach ( FormalParameter : Expression ) ClockedClauseopt Statement
            //
            case 579: {
                //#line 1722 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                X10Formal FormalParameter = (X10Formal) getRhsSym(3);
                //#line 1722 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(5);
                //#line 1722 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ClockedClauseopt = (List) getRhsSym(7);
                //#line 1722 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(8);
                //#line 1724 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.ForEach(pos(),
                              FormalParameter.flags(FormalParameter.flags().Final()),
                              Expression,
                              ClockedClauseopt,
                              Statement));
                break;
            }
     
            //
            // Rule 580:  AtEachStatement ::= ateach ( FormalParameter : Expression ) ClockedClauseopt Statement
            //
            case 580: {
                //#line 1732 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                X10Formal FormalParameter = (X10Formal) getRhsSym(3);
                //#line 1732 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(5);
                //#line 1732 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ClockedClauseopt = (List) getRhsSym(7);
                //#line 1732 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(8);
                //#line 1734 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.AtEach(pos(),
                             FormalParameter.flags(FormalParameter.flags().Final()),
                             Expression,
                             ClockedClauseopt,
                             Statement));
                break;
            }
     
            //
            // Rule 581:  EnhancedForStatement ::= for ( FormalParameter : Expression ) Statement
            //
            case 581: {
                //#line 1742 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                X10Formal FormalParameter = (X10Formal) getRhsSym(3);
                //#line 1742 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(5);
                //#line 1742 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(7);
                //#line 1744 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.ForLoop(pos(),
                        FormalParameter.flags(FormalParameter.flags().Final()),
                        Expression,
                        Statement));
                break;
            }
     
            //
            // Rule 582:  FinishStatement ::= finish Statement
            //
            case 582: {
                //#line 1751 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(2);
                //#line 1753 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Finish(pos(),  Statement));
                break;
            }
     
            //
            // Rule 583:  AnnotationStatement ::= Annotations Statement
            //
            case 583: {
                //#line 1758 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List Annotations = (List) getRhsSym(1);
                //#line 1758 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt Statement = (Stmt) getRhsSym(2);
                //#line 1760 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                if (Statement.ext() instanceof X10Ext && Annotations instanceof List) {
                    Statement = (Stmt) ((X10Ext) Statement.ext()).annotations((List) Annotations);
                }
                setResult(Statement);
                break;
            }
     
            //
            // Rule 584:  NowStatementNoShortIf ::= now ( Clock ) StatementNoShortIf
            //
            case 584: {
                //#line 1768 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Clock = (Expr) getRhsSym(3);
                //#line 1768 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt StatementNoShortIf = (Stmt) getRhsSym(5);
                //#line 1770 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Now(pos(), Clock, StatementNoShortIf));
                break;
            }
     
            //
            // Rule 585:  AsyncStatementNoShortIf ::= async PlaceExpressionSingleListopt ClockedClauseopt StatementNoShortIf
            //
            case 585: {
                //#line 1774 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr PlaceExpressionSingleListopt = (Expr) getRhsSym(2);
                //#line 1774 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ClockedClauseopt = (List) getRhsSym(3);
                //#line 1774 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt StatementNoShortIf = (Stmt) getRhsSym(4);
                //#line 1776 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Async(pos(), (PlaceExpressionSingleListopt == null
                                                ? nf.Here(pos(getLeftSpan()))
                                                : PlaceExpressionSingleListopt),
                                            ClockedClauseopt, StatementNoShortIf));
                break;
            }
     
            //
            // Rule 586:  AtomicStatementNoShortIf ::= atomic StatementNoShortIf
            //
            case 586: {
                //#line 1783 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt StatementNoShortIf = (Stmt) getRhsSym(2);
                //#line 1785 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Atomic(pos(), nf.Here(pos(getLeftSpan())), StatementNoShortIf));
                break;
            }
     
            //
            // Rule 587:  WhenStatementNoShortIf ::= when ( Expression ) StatementNoShortIf
            //
            case 587: {
                //#line 1789 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1789 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt StatementNoShortIf = (Stmt) getRhsSym(5);
                //#line 1791 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.When(pos(), Expression, StatementNoShortIf));
                break;
            }
     
            //
            // Rule 588:  WhenStatementNoShortIf ::= WhenStatement or$or ( Expression ) StatementNoShortIf
            //
            case 588: {
                //#line 1794 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                When WhenStatement = (When) getRhsSym(1);
                //#line 1794 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                IToken or = (IToken) getRhsIToken(2);
                //#line 1794 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(4);
                //#line 1794 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt StatementNoShortIf = (Stmt) getRhsSym(6);
                //#line 1796 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                WhenStatement.addBranch(pos(getRhsFirstTokenIndex(2), getRightSpan()), Expression, StatementNoShortIf);
                setResult(WhenStatement);
                break;
            }
     
            //
            // Rule 589:  ForEachStatementNoShortIf ::= foreach ( FormalParameter : Expression ) ClockedClauseopt StatementNoShortIf
            //
            case 589: {
                //#line 1801 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                X10Formal FormalParameter = (X10Formal) getRhsSym(3);
                //#line 1801 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(5);
                //#line 1801 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ClockedClauseopt = (List) getRhsSym(7);
                //#line 1801 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt StatementNoShortIf = (Stmt) getRhsSym(8);
                //#line 1803 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.ForEach(pos(),
                             FormalParameter.flags(FormalParameter.flags().Final()),
                             Expression,
                             ClockedClauseopt,
                             StatementNoShortIf));

                break;
            }
     
            //
            // Rule 590:  AtEachStatementNoShortIf ::= ateach ( FormalParameter : Expression ) ClockedClauseopt StatementNoShortIf
            //
            case 590: {
                //#line 1812 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                X10Formal FormalParameter = (X10Formal) getRhsSym(3);
                //#line 1812 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(5);
                //#line 1812 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ClockedClauseopt = (List) getRhsSym(7);
                //#line 1812 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt StatementNoShortIf = (Stmt) getRhsSym(8);
                //#line 1814 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.AtEach(pos(),
                            FormalParameter.flags(FormalParameter.flags().Final()),
                            Expression,
                            ClockedClauseopt,
                            StatementNoShortIf));
                break;
            }
     
            //
            // Rule 591:  EnhancedForStatementNoShortIf ::= for ( FormalParameter : Expression ) StatementNoShortIf
            //
            case 591: {
                //#line 1822 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                X10Formal FormalParameter = (X10Formal) getRhsSym(3);
                //#line 1822 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(5);
                //#line 1822 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt StatementNoShortIf = (Stmt) getRhsSym(7);
                //#line 1824 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                  setResult(nf.ForLoop(pos(),
                             FormalParameter.flags(FormalParameter.flags().Final()),
                             Expression,
                             StatementNoShortIf));
                break;
            }
     
            //
            // Rule 592:  FinishStatementNoShortIf ::= finish StatementNoShortIf
            //
            case 592: {
                //#line 1831 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt StatementNoShortIf = (Stmt) getRhsSym(2);
                //#line 1833 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Finish(pos(), StatementNoShortIf));
                break;
            }
     
            //
            // Rule 593:  AnnotationStatementNoShortIf ::= Annotations StatementNoShortIf
            //
            case 593: {
                //#line 1838 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List Annotations = (List) getRhsSym(1);
                //#line 1838 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Stmt StatementNoShortIf = (Stmt) getRhsSym(2);
                //#line 1840 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                if (StatementNoShortIf.ext() instanceof X10Ext && Annotations instanceof List) {
                    StatementNoShortIf = (Stmt) ((X10Ext) StatementNoShortIf.ext()).annotations((List) Annotations);
                }
                setResult(StatementNoShortIf);
                break;
            }
     
            //
            // Rule 594:  PlaceExpressionSingleList ::= ( PlaceExpression )
            //
            case 594: {
                //#line 1848 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr PlaceExpression = (Expr) getRhsSym(2);
                //#line 1850 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
              setResult(PlaceExpression);
                break;
            }
     
            //
            // Rule 596:  NextStatement ::= next ;
            //
            case 596: {
                
                //#line 1858 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Next(pos()));
                break;
            }
     
            //
            // Rule 597:  AwaitStatement ::= await Expression ;
            //
            case 597: {
                //#line 1862 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(2);
                //#line 1864 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Await(pos(), Expression));
                break;
            }
     
            //
            // Rule 598:  ClockList ::= Clock
            //
            case 598: {
                //#line 1868 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Clock = (Expr) getRhsSym(1);
                //#line 1870 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), Expr.class, false);
                l.add(Clock);
                setResult(l);
                break;
            }
     
            //
            // Rule 599:  ClockList ::= ClockList , Clock
            //
            case 599: {
                //#line 1875 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List ClockList = (List) getRhsSym(1);
                //#line 1875 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Clock = (Expr) getRhsSym(3);
                //#line 1877 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                ClockList.add(Clock);
                setResult(ClockList);
                break;
            }
     
            //
            // Rule 600:  Clock ::= Expression
            //
            case 600: {
                //#line 1883 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(1);
                //#line 1885 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
    setResult(Expression);
                break;
            }
     
            //
            // Rule 601:  CastExpression ::= ( PrimitiveType ) UnaryExpression
            //
            case 601: {
                //#line 1897 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode PrimitiveType = (TypeNode) getRhsSym(2);
                //#line 1897 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr UnaryExpression = (Expr) getRhsSym(4);
                //#line 1899 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Cast(pos(), PrimitiveType, UnaryExpression));
                break;
            }
     
            //
            // Rule 602:  CastExpression ::= ( SpecialType ) UnaryExpressionNotPlusMinus
            //
            case 602: {
                //#line 1902 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode SpecialType = (TypeNode) getRhsSym(2);
                //#line 1902 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr UnaryExpressionNotPlusMinus = (Expr) getRhsSym(4);
                //#line 1904 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Cast(pos(), SpecialType, UnaryExpressionNotPlusMinus));
                break;
            }
     
            //
            // Rule 603:  CastExpression ::= ( ReferenceType ) UnaryExpressionNotPlusMinus
            //
            case 603: {
                //#line 1907 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode ReferenceType = (TypeNode) getRhsSym(2);
                //#line 1907 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr UnaryExpressionNotPlusMinus = (Expr) getRhsSym(4);
                //#line 1909 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Cast(pos(), ReferenceType, UnaryExpressionNotPlusMinus));
                break;
            }
     
            //
            // Rule 604:  CastExpression ::= ( ! Expression ) UnaryExpressionNotPlusMinus
            //
            case 604: {
                //#line 1912 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 1912 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr UnaryExpressionNotPlusMinus = (Expr) getRhsSym(5);
                //#line 1914 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.PlaceCast(pos(), Expression, UnaryExpressionNotPlusMinus));
                break;
            }
     
            //
            // Rule 605:  CastExpression ::= ( AnnotatedType ) UnaryExpression
            //
            case 605: {
                //#line 1917 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode AnnotatedType = (TypeNode) getRhsSym(2);
                //#line 1917 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr UnaryExpression = (Expr) getRhsSym(4);
                //#line 1919 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Cast(pos(), AnnotatedType, UnaryExpression));
                break;
            }
     
            //
            // Rule 606:  CastExpression ::= ( Annotations ) UnaryExpressionNotPlusMinus
            //
            case 606: {
                //#line 1922 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List Annotations = (List) getRhsSym(2);
                //#line 1922 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr UnaryExpressionNotPlusMinus = (Expr) getRhsSym(4);
                //#line 1924 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                Expr e = UnaryExpressionNotPlusMinus;
                e = (Expr) ((X10Ext) e.ext()).annotations(Annotations);
                setResult(e);
                break;
            }
     
            //
            // Rule 607:  RelationalExpression ::= RelationalExpression instanceof Type
            //
            case 607: {
                //#line 1936 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr RelationalExpression = (Expr) getRhsSym(1);
                //#line 1936 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                TypeNode Type = (TypeNode) getRhsSym(3);
                //#line 1938 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Instanceof(pos(), RelationalExpression, Type));
                break;
            }
     
            //
            // Rule 608:  IdentifierList ::= identifier
            //
            case 608: {
                //#line 1944 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 1946 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), Name.class, false);
                l.add(new Name(nf, ts, pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                setResult(l);
                break;
            }
     
            //
            // Rule 609:  IdentifierList ::= IdentifierList , identifier
            //
            case 609: {
                //#line 1951 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List IdentifierList = (List) getRhsSym(1);
                //#line 1951 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(3);
                //#line 1953 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                IdentifierList.add(new Name(nf, ts, pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                setResult(IdentifierList);
                break;
            }
     
            //
            // Rule 610:  Primary ::= here
            //
            case 610: {
                
                //#line 1960 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(((X10NodeFactory) nf).Here(pos()));
//
// A "here" expression used to be treated as an ExpressionName instead
// of as a primary.
//
//                    setResult(new Name(nf, ts, pos(), "here"){
//                                public Expr toExpr() {
//                                  return ((X10NodeFactory) nf).Here(pos);
//                                }
//                             });
                break;
            }
     
            //
            // Rule 613:  RegionExpression ::= Expression$expr1 : Expression$expr2
            //
            case 613: {
                //#line 1976 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr expr1 = (Expr) getRhsSym(1);
                //#line 1976 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr expr2 = (Expr) getRhsSym(3);
                //#line 1978 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                /*Name x10 = new Name(nf, ts, pos(), "x10");
                Name x10Lang = new Name(nf, ts, pos(), x10, "lang");

                Name x10LangRegion = new Name(nf, ts, pos(), x10Lang, "region");
                Name x10LangRegionFactory = new Name(nf, ts, pos(), x10LangRegion, "factory");
                Name x10LangRegionFactoryRegion = new Name(nf, ts, pos(), x10LangRegionFactory, "region");
                List l = new TypedList(new LinkedList(), Expr.class, false);
                l.add(expr1);
                l.add(expr2);
                 Call regionCall = nf.Call( pos(), x10LangRegionFactoryRegion.prefix.toReceiver(), "region", l  );
                */
                Call regionCall = nf.RegionMaker(pos(), expr1, expr2);
                setResult(regionCall);
                break;
            }
     
            //
            // Rule 614:  RegionExpressionList ::= RegionExpression
            //
            case 614: {
                //#line 1994 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr RegionExpression = (Expr) getRhsSym(1);
                //#line 1996 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                List l = new TypedList(new LinkedList(), Expr.class, false);
                l.add(RegionExpression);
                setResult(l);
                break;
            }
     
            //
            // Rule 615:  RegionExpressionList ::= RegionExpressionList , RegionExpression
            //
            case 615: {
                //#line 2001 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List RegionExpressionList = (List) getRhsSym(1);
                //#line 2001 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr RegionExpression = (Expr) getRhsSym(3);
                //#line 2003 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                RegionExpressionList.add(RegionExpression);
                //setResult(RegionExpressionList);
                break;
            }
     
            //
            // Rule 616:  Primary ::= [ RegionExpressionList ]
            //
            case 616: {
                //#line 2008 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                List RegionExpressionList = (List) getRhsSym(2);
                //#line 2010 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                Receiver x10LangPointFactory = nf.ReceiverFromQualifiedName(pos(), "x10.lang.point.factory");
                Receiver x10LangRegionFactory = nf.ReceiverFromQualifiedName(pos(), "x10.lang.region.factory");
                Tuple tuple = nf.Tuple(pos(), x10LangPointFactory, x10LangRegionFactory, RegionExpressionList);
                setResult(tuple);
                break;
            }
     
            //
            // Rule 617:  AssignmentExpression ::= Expression$expr1 -> Expression$expr2
            //
            case 617: {
                //#line 2017 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr expr1 = (Expr) getRhsSym(1);
                //#line 2017 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr expr2 = (Expr) getRhsSym(3);
                //#line 2019 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                ConstantDistMaker call = nf.ConstantDistMaker(pos(), expr1, expr2);
                setResult(call);
                break;
            }
     
            //
            // Rule 618:  FutureExpression ::= future PlaceExpressionSingleListopt { Expression }
            //
            case 618: {
                //#line 2024 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr PlaceExpressionSingleListopt = (Expr) getRhsSym(2);
                //#line 2024 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(4);
                //#line 2026 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(nf.Future(pos(), (PlaceExpressionSingleListopt == null
                                                ? nf.Here(pos(getLeftSpan()))
                                                : PlaceExpressionSingleListopt), Expression));
                break;
            }
     
            //
            // Rule 619:  FieldModifier ::= mutable
            //
            case 619: {
                
                //#line 2034 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(X10Flags.MUTABLE));
                break;
            }
     
            //
            // Rule 620:  FieldModifier ::= const
            //
            case 620: {
                
                //#line 2039 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(Collections.singletonList(Flags.PUBLIC.set(Flags.STATIC).set(Flags.FINAL)));
                break;
            }
     
            //
            // Rule 621:  FunExpression ::= fun Type ( FormalParameterListopt ) { Expression }
            //
            case 621:
                throw new Error("No action specified for rule " + 621);
 
            //
            // Rule 622:  MethodInvocation ::= MethodName ( ArgumentListopt$args1 ) ( ArgumentListopt$args2 )
            //
            case 622:
                throw new Error("No action specified for rule " + 622); 
 
            //
            // Rule 623:  MethodInvocation ::= Primary . identifier ( ArgumentListopt$args1 ) ( ArgumentListopt$args2 )
            //
            case 623:
                throw new Error("No action specified for rule " + 623); 
 
            //
            // Rule 624:  MethodInvocation ::= super . identifier ( ArgumentListopt$args1 ) ( ArgumentListopt$args2 )
            //
            case 624:
                throw new Error("No action specified for rule " + 624); 
 
            //
            // Rule 625:  MethodInvocation ::= ClassName . super . identifier ( ArgumentListopt$args1 ) ( ArgumentListopt$args2 )
            //
            case 625:
                throw new Error("No action specified for rule " + 625); 
 
            //
            // Rule 626:  MethodInvocation ::= TypeName . identifier ( ArgumentListopt$args1 ) ( ArgumentListopt$args2 )
            //
            case 626:
                throw new Error("No action specified for rule " + 626); 
 
            //
            // Rule 627:  ClassInstanceCreationExpression ::= new ClassOrInterfaceType ( ArgumentListopt$args1 ) ( ArgumentListopt$args2 ) ClassBodyopt
            //
            case 627:
                throw new Error("No action specified for rule " + 627); 
 
            //
            // Rule 628:  ClassInstanceCreationExpression ::= Primary . new identifier ( ArgumentListopt$args1 ) ( ArgumentListopt$args2 ) ClassBodyopt
            //
            case 628:
                throw new Error("No action specified for rule " + 628); 
 
            //
            // Rule 629:  ClassInstanceCreationExpression ::= AmbiguousName . new identifier ( ArgumentListopt$args1 ) ( ArgumentListopt$args2 ) ClassBodyopt
            //
            case 629:
                throw new Error("No action specified for rule " + 629); 
 
            //
            // Rule 630:  MethodModifier ::= synchronized
            //
            case 630: {
                
                //#line 2070 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                unrecoverableSyntaxError = true;
                eq.enqueue(ErrorInfo.SYNTAX_ERROR, "\"synchronized\" is an invalid X10 Method Modifier",
                           getErrorPosition(getLeftSpan(), getRightSpan()));
                setResult(Collections.singletonList(Flags.SYNCHRONIZED));
                break;
            }
     
            //
            // Rule 631:  FieldModifier ::= volatile
            //
            case 631: {
                
                //#line 2079 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                unrecoverableSyntaxError = true;
                eq.enqueue(ErrorInfo.SYNTAX_ERROR, "\"volatile\" is an invalid X10 Field Modifier",
                           getErrorPosition(getLeftSpan(), getRightSpan()));
                setResult(Collections.singletonList(Flags.VOLATILE));
                break;
            }
     
            //
            // Rule 632:  SynchronizedStatement ::= synchronized ( Expression ) Block
            //
            case 632: {
                //#line 2086 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Expr Expression = (Expr) getRhsSym(3);
                //#line 2086 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                Block Block = (Block) getRhsSym(5);
                //#line 2088 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                unrecoverableSyntaxError = true;
                eq.enqueue(ErrorInfo.SYNTAX_ERROR, "Synchronized Statement is invalid in X10",
                           getErrorPosition(getLeftSpan(), getRightSpan()));
                setResult(nf.Synchronized(pos(), Expression, Block));
                break;
            }
     
            //
            // Rule 633:  ThisClauseopt ::= $Empty
            //
            case 633:
                setResult(null);
                break;
 
            //
            // Rule 635:  PlaceTypeSpecifieropt ::= $Empty
            //
            case 635:
                setResult(null);
                break;
 
            //
            // Rule 637:  DepParametersopt ::= $Empty
            //
            case 637:
                setResult(null);
                break;
 
            //
            // Rule 639:  PropertyListopt ::= $Empty
            //
            case 639:
                setResult(null);
                break;
 
            //
            // Rule 641:  WhereClauseopt ::= $Empty
            //
            case 641:
                setResult(null);
                break;
 
            //
            // Rule 643:  ObjectKindopt ::= $Empty
            //
            case 643:
                setResult(null);
                break;
 
            //
            // Rule 645:  ArrayInitializeropt ::= $Empty
            //
            case 645:
                setResult(null);
                break;
 
            //
            // Rule 647:  PlaceExpressionSingleListopt ::= $Empty
            //
            case 647:
                setResult(null);
                break;
 
            //
            // Rule 649:  X10ClassModifiersopt ::= $Empty
            //
            case 649: {
                
                //#line 2130 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
         setResult(Collections.singletonList(X10Flags.toX10Flags(Flags.NONE)));
                break;
            }  
            //
            // Rule 651:  Unsafeopt ::= $Empty
            //
            case 651:
                setResult(null);
                break;
 
            //
            // Rule 652:  Unsafeopt ::= unsafe
            //
            case 652: {
                
                //#line 2138 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                // any value distinct from null
                setResult(this);
                break;
            }
     
            //
            // Rule 653:  ParamIdopt ::= $Empty
            //
            case 653:
                setResult(null);
                break;
 
            //
            // Rule 654:  ParamIdopt ::= identifier
            //
            case 654: {
                //#line 2145 "C:/workspace-3.2.2/x10.compiler/src/x10/parser/x10.g"
                polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) getRhsSym(1);
                //#line 2147 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new Name(nf, ts, pos(), nf.Id(identifier.getPosition(), identifier.getIdentifier())));
                break;
            }
     
            //
            // Rule 655:  ClockedClauseopt ::= $Empty
            //
            case 655: {
                
                //#line 2153 "C:/workspace-3.2.2/org.eclipse.imp.lpg.metatooling/templates/btParserTemplate.gi"
                setResult(new TypedList(new LinkedList(), Expr.class, false));
                break;
            }
    
    
            default:
                break;
        }
        return;
    }
}

