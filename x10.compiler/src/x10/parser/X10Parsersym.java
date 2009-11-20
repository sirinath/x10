
//#line 18 "/Users/rmfuhrer/eclipse/workspaces/x10-safety/x10.compiler/src/x10/parser/x10.g"
//
// Licensed Material
// (C) Copyright IBM Corp, 2006
//

package x10.parser;

public interface X10Parsersym {
    public final static int
      TK_IntegerLiteral = 82,
      TK_LongLiteral = 83,
      TK_FloatingPointLiteral = 84,
      TK_DoubleLiteral = 85,
      TK_CharacterLiteral = 86,
      TK_StringLiteral = 87,
      TK_MINUS_MINUS = 91,
      TK_OR = 115,
      TK_MINUS = 95,
      TK_MINUS_EQUAL = 129,
      TK_NOT = 94,
      TK_NOT_EQUAL = 116,
      TK_REMAINDER = 120,
      TK_REMAINDER_EQUAL = 130,
      TK_AND = 117,
      TK_AND_AND = 121,
      TK_AND_EQUAL = 131,
      TK_LPAREN = 1,
      TK_RPAREN = 77,
      TK_MULTIPLY = 118,
      TK_MULTIPLY_EQUAL = 132,
      TK_COMMA = 90,
      TK_DOT = 96,
      TK_DIVIDE = 122,
      TK_DIVIDE_EQUAL = 133,
      TK_COLON = 103,
      TK_SEMICOLON = 80,
      TK_QUESTION = 127,
      TK_AT = 6,
      TK_LBRACKET = 78,
      TK_RBRACKET = 110,
      TK_XOR = 119,
      TK_XOR_EQUAL = 134,
      TK_LBRACE = 98,
      TK_OR_OR = 126,
      TK_OR_EQUAL = 135,
      TK_RBRACE = 101,
      TK_TWIDDLE = 100,
      TK_PLUS = 97,
      TK_PLUS_PLUS = 92,
      TK_PLUS_EQUAL = 136,
      TK_LESS = 111,
      TK_LEFT_SHIFT = 123,
      TK_LEFT_SHIFT_EQUAL = 137,
      TK_RIGHT_SHIFT = 124,
      TK_RIGHT_SHIFT_EQUAL = 138,
      TK_UNSIGNED_RIGHT_SHIFT = 125,
      TK_UNSIGNED_RIGHT_SHIFT_EQUAL = 139,
      TK_LESS_EQUAL = 112,
      TK_EQUAL = 99,
      TK_EQUAL_EQUAL = 104,
      TK_GREATER = 113,
      TK_GREATER_EQUAL = 114,
      TK_ELLIPSIS = 142,
      TK_RANGE = 141,
      TK_ARROW = 93,
      TK_DARROW = 128,
      TK_SUBTYPE = 108,
      TK_SUPERTYPE = 109,
      TK_abstract = 7,
      TK_as = 29,
      TK_assert = 47,
      TK_async = 48,
      TK_at = 49,
      TK_ateach = 39,
      TK_atomic = 19,
      TK_await = 50,
      TK_break = 51,
      TK_case = 27,
      TK_catch = 32,
      TK_class = 13,
      TK_clocked = 33,
      TK_const = 52,
      TK_continue = 53,
      TK_def = 15,
      TK_default = 28,
      TK_do = 40,
      TK_else = 41,
      TK_extends = 34,
      TK_extern = 20,
      TK_false = 54,
      TK_final = 8,
      TK_finally = 35,
      TK_finish = 55,
      TK_for = 42,
      TK_foreach = 43,
      TK_future = 105,
      TK_in = 106,
      TK_goto = 56,
      TK_global = 17,
      TK_has = 57,
      TK_here = 58,
      TK_if = 59,
      TK_implements = 44,
      TK_import = 36,
      TK_incomplete = 21,
      TK_instanceof = 30,
      TK_interface = 11,
      TK_local = 60,
      TK_native = 16,
      TK_new = 61,
      TK_next = 62,
      TK_nonblocking = 22,
      TK_now = 63,
      TK_null = 64,
      TK_or = 45,
      TK_operator = 102,
      TK_package = 37,
      TK_private = 2,
      TK_property = 18,
      TK_protected = 3,
      TK_proto = 23,
      TK_public = 4,
      TK_return = 65,
      TK_rooted = 66,
      TK_safe = 12,
      TK_self = 67,
      TK_sequential = 24,
      TK_shared = 31,
      TK_static = 5,
      TK_strictfp = 9,
      TK_struct = 14,
      TK_super = 81,
      TK_switch = 68,
      TK_synchronized = 143,
      TK_this = 79,
      TK_throw = 69,
      TK_throws = 46,
      TK_transient = 25,
      TK_true = 70,
      TK_try = 71,
      TK_type = 10,
      TK_unsafe = 72,
      TK_val = 73,
      TK_var = 74,
      TK_volatile = 26,
      TK_when = 75,
      TK_while = 38,
      TK_EOF_TOKEN = 140,
      TK_IDENTIFIER = 76,
      TK_SlComment = 144,
      TK_MlComment = 145,
      TK_DocComment = 146,
      TK_UnsignedIntegerLiteral = 88,
      TK_UnsignedLongLiteral = 89,
      TK_ErrorId = 107,
      TK_TypeModifier = 147,
      TK_ERROR_TOKEN = 148;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "LPAREN",
                 "private",
                 "protected",
                 "public",
                 "static",
                 "AT",
                 "abstract",
                 "final",
                 "strictfp",
                 "type",
                 "interface",
                 "safe",
                 "class",
                 "struct",
                 "def",
                 "native",
                 "global",
                 "property",
                 "atomic",
                 "extern",
                 "incomplete",
                 "nonblocking",
                 "proto",
                 "sequential",
                 "transient",
                 "volatile",
                 "case",
                 "default",
                 "as",
                 "instanceof",
                 "shared",
                 "catch",
                 "clocked",
                 "extends",
                 "finally",
                 "import",
                 "package",
                 "while",
                 "ateach",
                 "do",
                 "else",
                 "for",
                 "foreach",
                 "implements",
                 "or",
                 "throws",
                 "assert",
                 "async",
                 "at",
                 "await",
                 "break",
                 "const",
                 "continue",
                 "false",
                 "finish",
                 "goto",
                 "has",
                 "here",
                 "if",
                 "local",
                 "new",
                 "next",
                 "now",
                 "null",
                 "return",
                 "rooted",
                 "self",
                 "switch",
                 "throw",
                 "true",
                 "try",
                 "unsafe",
                 "val",
                 "var",
                 "when",
                 "IDENTIFIER",
                 "RPAREN",
                 "LBRACKET",
                 "this",
                 "SEMICOLON",
                 "super",
                 "IntegerLiteral",
                 "LongLiteral",
                 "FloatingPointLiteral",
                 "DoubleLiteral",
                 "CharacterLiteral",
                 "StringLiteral",
                 "UnsignedIntegerLiteral",
                 "UnsignedLongLiteral",
                 "COMMA",
                 "MINUS_MINUS",
                 "PLUS_PLUS",
                 "ARROW",
                 "NOT",
                 "MINUS",
                 "DOT",
                 "PLUS",
                 "LBRACE",
                 "EQUAL",
                 "TWIDDLE",
                 "RBRACE",
                 "operator",
                 "COLON",
                 "EQUAL_EQUAL",
                 "future",
                 "in",
                 "ErrorId",
                 "SUBTYPE",
                 "SUPERTYPE",
                 "RBRACKET",
                 "LESS",
                 "LESS_EQUAL",
                 "GREATER",
                 "GREATER_EQUAL",
                 "OR",
                 "NOT_EQUAL",
                 "AND",
                 "MULTIPLY",
                 "XOR",
                 "REMAINDER",
                 "AND_AND",
                 "DIVIDE",
                 "LEFT_SHIFT",
                 "RIGHT_SHIFT",
                 "UNSIGNED_RIGHT_SHIFT",
                 "OR_OR",
                 "QUESTION",
                 "DARROW",
                 "MINUS_EQUAL",
                 "REMAINDER_EQUAL",
                 "AND_EQUAL",
                 "MULTIPLY_EQUAL",
                 "DIVIDE_EQUAL",
                 "XOR_EQUAL",
                 "OR_EQUAL",
                 "PLUS_EQUAL",
                 "LEFT_SHIFT_EQUAL",
                 "RIGHT_SHIFT_EQUAL",
                 "UNSIGNED_RIGHT_SHIFT_EQUAL",
                 "EOF_TOKEN",
                 "RANGE",
                 "ELLIPSIS",
                 "synchronized",
                 "SlComment",
                 "MlComment",
                 "DocComment",
                 "TypeModifier",
                 "ERROR_TOKEN"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}
