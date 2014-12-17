lexer grammar X10Lexer;

IDENTIFIER: [a-z]+; // XXX TODO

IntLiteral:
      IntegerLiteral [nN]
    ;
LongLiteral:
      IntegerLiteral [lL]?
    ;
ByteLiteral:
      IntegerLiteral [yY]
    ;
ShortLiteral:
      IntegerLiteral [sS]
    ;
UnsignedIntLiteral:
      IntegerLiteral (([uU][nN]) | [nN]([uU]))
    ;
UnsignedLongLiteral:
      IntegerLiteral (([uU][lL]?) | [lL?]([uU]))
    ;
UnsignedByteLiteral:
      IntegerLiteral (([uU][yY]) | [yY]([uU]))
    ;
UnsignedShortLiteral:
      IntegerLiteral (([uU][sS]) | [sS]([uU]))
    ;
FloatingPointLiteral:
      Digits '.' Digits? ExponentPart? FloatingTypeSuffix
    | '.' Digits ExponentPart? FloatingTypeSuffix
    | Digits ExponentPart FloatingTypeSuffix
    | Digits ExponentPart? FloatingTypeSuffix
    ;
ExponentPart:
      ('e'|'E') ('+'|'-')? Digits
    ;
FloatingTypeSuffix:
      'f' |  'F'
    ;
DoubleLiteral:
      Digits '.' Digits? ExponentPart? DoubleTypeSuffix?
    | '.' Digits ExponentPart? DoubleTypeSuffix?
    | Digits ExponentPart DoubleTypeSuffix?
    | Digits ExponentPart? DoubleTypeSuffix
    ;
DoubleTypeSuffix:
      'd' | 'D'
    ;
Digits:
      [0-9]+
    ;
IntegerLiteral: Digits; // XXX TODO

CharacterLiteral: [a-z]; // XXX TODO -- the usual
StringLiteral: [a-z]+;   // XXX TODO -- the usual

MINUS_MINUS: '--';
OR: '|';
MINUS: '-';
MINUS_EQUAL: '-=';
NOT: '!';
NOT_EQUAL: '!=';
REMAINDER: '%';
REMAINDER_EQUAL: '%=';
AND: '&';
AND_AND: '&&';
AND_EQUAL: '&=';
LPAREN: '(';
RPAREN: ')';
MULTIPLY: '*';
MULTIPLY_EQUAL: '*=';
COMMA: ',';
DOT: '.';
DIVIDE: '/';
DIVIDE_EQUAL: '/=';
COLON: ':';
SEMICOLON: ';';
QUESTION: '?';
AT: '@';
LBRACKET: '[';
RBRACKET: ']';
XOR: '^';
XOR_EQUAL: '^=';
LBRACE: '{';
OR_OR: '||';
OR_EQUAL: '|=';
RBRACE: '}';
TWIDDLE: '~';
PLUS: '+';
PLUS_PLUS: '++';
PLUS_EQUAL: '+=';
LESS: '<';
LEFT_SHIFT: '<<';
LEFT_SHIFT_EQUAL: '<<=';
RIGHT_SHIFT: '>>';
RIGHT_SHIFT_EQUAL: '>>=';
UNSIGNED_RIGHT_SHIFT: '>>>';
UNSIGNED_RIGHT_SHIFT_EQUAL: '>>>=';
LESS_EQUAL: '<=';
EQUAL: '=';
EQUAL_EQUAL: '==';
GREATER: '>';
GREATER_EQUAL: '>=';
ELLIPSIS: '...';

RANGE: '..';
ARROW: '->';
DARROW: '=>';
SUBTYPE: '<:';
SUPERTYPE: ':>';
STARSTAR: '**';
NTWIDDLE: '!~';
LARROW: '<-';
FUNNEL: '-<';
LFUNNEL: '>-';
DIAMOND: '<>';
BOWTIE: '><';
RANGE_EQUAL: '..=';
ARROW_EQUAL: '->=';
STARSTAR_EQUAL: '**=';
TWIDDLE_EQUAL: '~=';
LARROW_EQUAL: '<-=';
FUNNEL_EQUAL: '-<=';
LFUNNEL_EQUAL: '>-=';
DIAMOND_EQUAL: '<>=';
BOWTIE_EQUAL: '><=';
