
//
// This is the grammar specification from the Final Draft of the generic spec.
// It has been modified by Philippe Charles and Vijay Saraswat for use with 
// X10. 
// (1) Removed TypeParameters from class/interface/method declarations
// (2) Removed TypeParameters from types.
// (3) Removed Annotations -- cause conflicts with @ used in places.
// (4) Removed EnumDeclarations.
// 12/28/2004// 12/25/2004
// This is the basic X10 grammar specification without support for generic types.
//Intended for the Feb 2005 X10 release.
package x10.parser;

interface X10Parsersym {
    public final static int
      TK_IntegerLiteral = 45,
      TK_LongLiteral = 46,
      TK_FloatingPointLiteral = 47,
      TK_DoubleLiteral = 48,
      TK_CharacterLiteral = 49,
      TK_StringLiteral = 50,
      TK_MINUS_MINUS = 40,
      TK_OR = 69,
      TK_MINUS = 36,
      TK_MINUS_EQUAL = 105,
      TK_NOT = 51,
      TK_NOT_EQUAL = 74,
      TK_REMAINDER = 83,
      TK_REMAINDER_EQUAL = 106,
      TK_AND = 73,
      TK_AND_AND = 65,
      TK_AND_EQUAL = 107,
      TK_LPAREN = 1,
      TK_RPAREN = 5,
      TK_MULTIPLY = 81,
      TK_MULTIPLY_EQUAL = 108,
      TK_COMMA = 70,
      TK_DOT = 64,
      TK_DIVIDE = 84,
      TK_DIVIDE_EQUAL = 109,
      TK_COLON = 2,
      TK_SEMICOLON = 38,
      TK_QUESTION = 66,
      TK_AT = 98,
      TK_LBRACKET = 35,
      TK_RBRACKET = 82,
      TK_XOR = 71,
      TK_XOR_EQUAL = 110,
      TK_LBRACE = 67,
      TK_OR_OR = 68,
      TK_OR_EQUAL = 111,
      TK_RBRACE = 85,
      TK_TWIDDLE = 52,
      TK_PLUS = 37,
      TK_PLUS_PLUS = 41,
      TK_PLUS_EQUAL = 112,
      TK_LESS = 72,
      TK_LEFT_SHIFT = 79,
      TK_LEFT_SHIFT_EQUAL = 113,
      TK_LESS_EQUAL = 76,
      TK_EQUAL = 99,
      TK_EQUAL_EQUAL = 75,
      TK_GREATER = 57,
      TK_ELLIPSIS = 102,
      TK_RANGE = 126,
      TK_ARROW = 6,
      TK_abstract = 61,
      TK_assert = 88,
      TK_boolean = 9,
      TK_break = 89,
      TK_byte = 10,
      TK_case = 103,
      TK_catch = 119,
      TK_char = 11,
      TK_class = 78,
      TK_const = 127,
      TK_continue = 90,
      TK_default = 104,
      TK_do = 91,
      TK_double = 12,
      TK_enum = 128,
      TK_else = 114,
      TK_extends = 120,
      TK_false = 53,
      TK_final = 62,
      TK_finally = 121,
      TK_float = 13,
      TK_for = 92,
      TK_goto = 129,
      TK_if = 93,
      TK_implements = 124,
      TK_import = 122,
      TK_instanceof = 77,
      TK_int = 14,
      TK_interface = 80,
      TK_long = 15,
      TK_native = 116,
      TK_new = 42,
      TK_null = 54,
      TK_package = 125,
      TK_private = 59,
      TK_protected = 60,
      TK_public = 55,
      TK_return = 94,
      TK_short = 16,
      TK_static = 58,
      TK_strictfp = 63,
      TK_super = 43,
      TK_switch = 95,
      TK_synchronized = 86,
      TK_this = 44,
      TK_throw = 96,
      TK_throws = 123,
      TK_transient = 100,
      TK_true = 56,
      TK_try = 97,
      TK_void = 39,
      TK_volatile = 101,
      TK_while = 87,
      TK_activity = 30,
      TK_async = 17,
      TK_ateach = 18,
      TK_atomic = 8,
      TK_await = 19,
      TK_boxed = 130,
      TK_clocked = 20,
      TK_current = 31,
      TK_extern = 117,
      TK_finish = 21,
      TK_foreach = 22,
      TK_fun = 131,
      TK_future = 23,
      TK_here = 24,
      TK_local = 32,
      TK_method = 33,
      TK_next = 25,
      TK_now = 26,
      TK_nullable = 27,
      TK_or = 7,
      TK_place = 34,
      TK_reference = 4,
      TK_unsafe = 115,
      TK_value = 3,
      TK_when = 28,
      TK_IDENTIFIER = 29,
      TK_Comment = 132,
      TK_GREATER_EQUAL = 133,
      TK_RIGHT_SHIFT = 134,
      TK_UNSIGNED_RIGHT_SHIFT = 135,
      TK_RIGHT_SHIFT_EQUAL = 136,
      TK_UNSIGNED_RIGHT_SHIFT_EQUAL = 137,
      TK_EOF_TOKEN = 118,
      TK_ActualTypeArgument = 138,
      TK_$error = 139;

      public final static String orderedTerminalSymbols[] = {
                 "",
                 "LPAREN",
                 "COLON",
                 "value",
                 "reference",
                 "RPAREN",
                 "ARROW",
                 "or",
                 "atomic",
                 "boolean",
                 "byte",
                 "char",
                 "double",
                 "float",
                 "int",
                 "long",
                 "short",
                 "async",
                 "ateach",
                 "await",
                 "clocked",
                 "finish",
                 "foreach",
                 "future",
                 "here",
                 "next",
                 "now",
                 "nullable",
                 "when",
                 "IDENTIFIER",
                 "activity",
                 "current",
                 "local",
                 "method",
                 "place",
                 "LBRACKET",
                 "MINUS",
                 "PLUS",
                 "SEMICOLON",
                 "void",
                 "MINUS_MINUS",
                 "PLUS_PLUS",
                 "new",
                 "super",
                 "this",
                 "IntegerLiteral",
                 "LongLiteral",
                 "FloatingPointLiteral",
                 "DoubleLiteral",
                 "CharacterLiteral",
                 "StringLiteral",
                 "NOT",
                 "TWIDDLE",
                 "false",
                 "null",
                 "public",
                 "true",
                 "GREATER",
                 "static",
                 "private",
                 "protected",
                 "abstract",
                 "final",
                 "strictfp",
                 "DOT",
                 "AND_AND",
                 "QUESTION",
                 "LBRACE",
                 "OR_OR",
                 "OR",
                 "COMMA",
                 "XOR",
                 "LESS",
                 "AND",
                 "NOT_EQUAL",
                 "EQUAL_EQUAL",
                 "LESS_EQUAL",
                 "instanceof",
                 "class",
                 "LEFT_SHIFT",
                 "interface",
                 "MULTIPLY",
                 "RBRACKET",
                 "REMAINDER",
                 "DIVIDE",
                 "RBRACE",
                 "synchronized",
                 "while",
                 "assert",
                 "break",
                 "continue",
                 "do",
                 "for",
                 "if",
                 "return",
                 "switch",
                 "throw",
                 "try",
                 "AT",
                 "EQUAL",
                 "transient",
                 "volatile",
                 "ELLIPSIS",
                 "case",
                 "default",
                 "MINUS_EQUAL",
                 "REMAINDER_EQUAL",
                 "AND_EQUAL",
                 "MULTIPLY_EQUAL",
                 "DIVIDE_EQUAL",
                 "XOR_EQUAL",
                 "OR_EQUAL",
                 "PLUS_EQUAL",
                 "LEFT_SHIFT_EQUAL",
                 "else",
                 "unsafe",
                 "native",
                 "extern",
                 "EOF_TOKEN",
                 "catch",
                 "extends",
                 "finally",
                 "import",
                 "throws",
                 "implements",
                 "package",
                 "RANGE",
                 "const",
                 "enum",
                 "goto",
                 "boxed",
                 "fun",
                 "Comment",
                 "GREATER_EQUAL",
                 "RIGHT_SHIFT",
                 "UNSIGNED_RIGHT_SHIFT",
                 "RIGHT_SHIFT_EQUAL",
                 "UNSIGNED_RIGHT_SHIFT_EQUAL",
                 "ActualTypeArgument",
                 "$error"
             };

    public final static boolean isValidForParser = true;
}
