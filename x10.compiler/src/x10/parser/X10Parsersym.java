
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
      TK_IntegerLiteral = 40,
      TK_LongLiteral = 41,
      TK_FloatingPointLiteral = 42,
      TK_DoubleLiteral = 43,
      TK_CharacterLiteral = 44,
      TK_StringLiteral = 45,
      TK_MINUS_MINUS = 30,
      TK_OR = 61,
      TK_MINUS = 18,
      TK_MINUS_EQUAL = 104,
      TK_NOT = 46,
      TK_NOT_EQUAL = 65,
      TK_REMAINDER = 74,
      TK_REMAINDER_EQUAL = 105,
      TK_AND = 63,
      TK_AND_AND = 56,
      TK_AND_EQUAL = 106,
      TK_LPAREN = 1,
      TK_RPAREN = 6,
      TK_MULTIPLY = 73,
      TK_MULTIPLY_EQUAL = 107,
      TK_COMMA = 57,
      TK_DOT = 55,
      TK_DIVIDE = 75,
      TK_DIVIDE_EQUAL = 108,
      TK_COLON = 2,
      TK_SEMICOLON = 17,
      TK_QUESTION = 58,
      TK_AT = 96,
      TK_LBRACKET = 8,
      TK_RBRACKET = 72,
      TK_XOR = 62,
      TK_XOR_EQUAL = 109,
      TK_LBRACE = 59,
      TK_OR_OR = 60,
      TK_OR_EQUAL = 110,
      TK_RBRACE = 78,
      TK_TWIDDLE = 47,
      TK_PLUS = 19,
      TK_PLUS_PLUS = 31,
      TK_PLUS_EQUAL = 111,
      TK_LESS = 64,
      TK_LEFT_SHIFT = 70,
      TK_LEFT_SHIFT_EQUAL = 112,
      TK_LESS_EQUAL = 67,
      TK_EQUAL = 97,
      TK_EQUAL_EQUAL = 66,
      TK_GREATER = 37,
      TK_ELLIPSIS = 115,
      TK_RANGE = 128,
      TK_ARROW = 5,
      TK_abstract = 48,
      TK_assert = 80,
      TK_boolean = 20,
      TK_break = 81,
      TK_byte = 21,
      TK_case = 102,
      TK_catch = 119,
      TK_char = 22,
      TK_class = 69,
      TK_const = 98,
      TK_continue = 82,
      TK_default = 103,
      TK_do = 83,
      TK_double = 23,
      TK_enum = 129,
      TK_else = 113,
      TK_extends = 120,
      TK_false = 49,
      TK_final = 50,
      TK_finally = 121,
      TK_float = 24,
      TK_for = 84,
      TK_goto = 130,
      TK_if = 85,
      TK_implements = 124,
      TK_import = 122,
      TK_instanceof = 68,
      TK_int = 25,
      TK_interface = 71,
      TK_long = 26,
      TK_native = 116,
      TK_new = 32,
      TK_null = 51,
      TK_package = 125,
      TK_private = 38,
      TK_protected = 39,
      TK_public = 33,
      TK_return = 86,
      TK_short = 27,
      TK_static = 34,
      TK_strictfp = 54,
      TK_super = 35,
      TK_switch = 87,
      TK_synchronized = 76,
      TK_this = 36,
      TK_throw = 88,
      TK_throws = 123,
      TK_transient = 99,
      TK_true = 52,
      TK_try = 89,
      TK_void = 29,
      TK_volatile = 100,
      TK_while = 79,
      TK_activitylocal = 126,
      TK_async = 90,
      TK_ateach = 91,
      TK_atomic = 77,
      TK_await = 92,
      TK_boxed = 131,
      TK_clocked = 93,
      TK_current = 14,
      TK_extern = 117,
      TK_finish = 94,
      TK_foreach = 95,
      TK_fun = 132,
      TK_future = 28,
      TK_here = 53,
      TK_local = 15,
      TK_method = 16,
      TK_next = 9,
      TK_now = 10,
      TK_nullable = 11,
      TK_or = 7,
      TK_placelocal = 127,
      TK_reference = 4,
      TK_unsafe = 114,
      TK_value = 3,
      TK_when = 12,
      TK_IDENTIFIER = 13,
      TK_Comment = 133,
      TK_GREATER_EQUAL = 134,
      TK_RIGHT_SHIFT = 135,
      TK_UNSIGNED_RIGHT_SHIFT = 136,
      TK_RIGHT_SHIFT_EQUAL = 137,
      TK_UNSIGNED_RIGHT_SHIFT_EQUAL = 138,
      TK_EOF_TOKEN = 118,
      TK_ActualTypeArgument = 139,
      TK_mutable = 101,
      TK_$error = 140;

      public final static String orderedTerminalSymbols[] = {
                 "",
                 "LPAREN",
                 "COLON",
                 "value",
                 "reference",
                 "ARROW",
                 "RPAREN",
                 "or",
                 "LBRACKET",
                 "next",
                 "now",
                 "nullable",
                 "when",
                 "IDENTIFIER",
                 "current",
                 "local",
                 "method",
                 "SEMICOLON",
                 "MINUS",
                 "PLUS",
                 "boolean",
                 "byte",
                 "char",
                 "double",
                 "float",
                 "int",
                 "long",
                 "short",
                 "future",
                 "void",
                 "MINUS_MINUS",
                 "PLUS_PLUS",
                 "new",
                 "public",
                 "static",
                 "super",
                 "this",
                 "GREATER",
                 "private",
                 "protected",
                 "IntegerLiteral",
                 "LongLiteral",
                 "FloatingPointLiteral",
                 "DoubleLiteral",
                 "CharacterLiteral",
                 "StringLiteral",
                 "NOT",
                 "TWIDDLE",
                 "abstract",
                 "false",
                 "final",
                 "null",
                 "true",
                 "here",
                 "strictfp",
                 "DOT",
                 "AND_AND",
                 "COMMA",
                 "QUESTION",
                 "LBRACE",
                 "OR_OR",
                 "OR",
                 "XOR",
                 "AND",
                 "LESS",
                 "NOT_EQUAL",
                 "EQUAL_EQUAL",
                 "LESS_EQUAL",
                 "instanceof",
                 "class",
                 "LEFT_SHIFT",
                 "interface",
                 "RBRACKET",
                 "MULTIPLY",
                 "REMAINDER",
                 "DIVIDE",
                 "synchronized",
                 "atomic",
                 "RBRACE",
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
                 "async",
                 "ateach",
                 "await",
                 "clocked",
                 "finish",
                 "foreach",
                 "AT",
                 "EQUAL",
                 "const",
                 "transient",
                 "volatile",
                 "mutable",
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
                 "ELLIPSIS",
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
                 "activitylocal",
                 "placelocal",
                 "RANGE",
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
