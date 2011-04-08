/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */
/********************************************************************
 * WARNING!  THIS JAVA FILE IS AUTO-GENERATED FROM x10/parser/x10.g *
 ********************************************************************/

package x10.parser;

public interface X10Parsersym {
    public final static int
      TK_IntegerLiteral = 11,
      TK_LongLiteral = 12,
      TK_FloatingPointLiteral = 13,
      TK_DoubleLiteral = 14,
      TK_CharacterLiteral = 15,
      TK_StringLiteral = 16,
      TK_MINUS_MINUS = 31,
      TK_OR = 37,
      TK_MINUS = 29,
      TK_MINUS_EQUAL = 90,
      TK_NOT = 34,
      TK_NOT_EQUAL = 82,
      TK_REMAINDER = 35,
      TK_REMAINDER_EQUAL = 91,
      TK_AND = 38,
      TK_AND_AND = 88,
      TK_AND_EQUAL = 92,
      TK_LPAREN = 1,
      TK_RPAREN = 3,
      TK_MULTIPLY = 28,
      TK_MULTIPLY_EQUAL = 93,
      TK_COMMA = 8,
      TK_DOT = 32,
      TK_DIVIDE = 36,
      TK_DIVIDE_EQUAL = 94,
      TK_COLON = 49,
      TK_SEMICOLON = 7,
      TK_QUESTION = 103,
      TK_AT = 5,
      TK_LBRACKET = 2,
      TK_RBRACKET = 44,
      TK_XOR = 39,
      TK_XOR_EQUAL = 95,
      TK_LBRACE = 42,
      TK_OR_OR = 96,
      TK_OR_EQUAL = 97,
      TK_RBRACE = 45,
      TK_TWIDDLE = 40,
      TK_PLUS = 30,
      TK_PLUS_PLUS = 33,
      TK_PLUS_EQUAL = 98,
      TK_LESS = 68,
      TK_LEFT_SHIFT = 69,
      TK_LEFT_SHIFT_EQUAL = 99,
      TK_RIGHT_SHIFT = 70,
      TK_RIGHT_SHIFT_EQUAL = 100,
      TK_UNSIGNED_RIGHT_SHIFT = 71,
      TK_UNSIGNED_RIGHT_SHIFT_EQUAL = 101,
      TK_LESS_EQUAL = 72,
      TK_EQUAL = 43,
      TK_EQUAL_EQUAL = 64,
      TK_GREATER = 73,
      TK_GREATER_EQUAL = 74,
      TK_ELLIPSIS = 133,
      TK_RANGE = 75,
      TK_ARROW = 76,
      TK_DARROW = 109,
      TK_SUBTYPE = 60,
      TK_SUPERTYPE = 80,
      TK_STARSTAR = 83,
      TK_NTWIDDLE = 89,
      TK_LARROW = 77,
      TK_FUNNEL = 78,
      TK_LFUNNEL = 79,
      TK_abstract = 50,
      TK_as = 104,
      TK_assert = 119,
      TK_async = 110,
      TK_at = 51,
      TK_athome = 52,
      TK_ateach = 111,
      TK_atomic = 47,
      TK_break = 120,
      TK_case = 84,
      TK_catch = 112,
      TK_class = 63,
      TK_clocked = 46,
      TK_continue = 121,
      TK_def = 113,
      TK_default = 85,
      TK_do = 114,
      TK_else = 122,
      TK_extends = 115,
      TK_false = 17,
      TK_final = 53,
      TK_finally = 116,
      TK_finish = 48,
      TK_for = 117,
      TK_goto = 134,
      TK_haszero = 81,
      TK_here = 18,
      TK_if = 123,
      TK_implements = 124,
      TK_import = 86,
      TK_in = 105,
      TK_instanceof = 87,
      TK_interface = 106,
      TK_native = 54,
      TK_new = 9,
      TK_null = 19,
      TK_offer = 125,
      TK_offers = 126,
      TK_operator = 127,
      TK_package = 118,
      TK_private = 55,
      TK_property = 107,
      TK_protected = 56,
      TK_public = 57,
      TK_return = 128,
      TK_self = 20,
      TK_static = 58,
      TK_struct = 65,
      TK_super = 10,
      TK_switch = 129,
      TK_this = 6,
      TK_throw = 130,
      TK_transient = 59,
      TK_true = 21,
      TK_try = 131,
      TK_type = 66,
      TK_val = 61,
      TK_var = 62,
      TK_void = 41,
      TK_when = 132,
      TK_while = 108,
      TK_EOF_TOKEN = 102,
      TK_IDENTIFIER = 4,
      TK_SlComment = 135,
      TK_MlComment = 136,
      TK_DocComment = 137,
      TK_ByteLiteral = 22,
      TK_ShortLiteral = 23,
      TK_UnsignedIntegerLiteral = 24,
      TK_UnsignedLongLiteral = 25,
      TK_UnsignedByteLiteral = 26,
      TK_UnsignedShortLiteral = 27,
      TK_PseudoDoubleLiteral = 138,
      TK_ErrorId = 67,
      TK_ERROR_TOKEN = 139;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "LPAREN",
                 "LBRACKET",
                 "RPAREN",
                 "IDENTIFIER",
                 "AT",
                 "this",
                 "SEMICOLON",
                 "COMMA",
                 "new",
                 "super",
                 "IntegerLiteral",
                 "LongLiteral",
                 "FloatingPointLiteral",
                 "DoubleLiteral",
                 "CharacterLiteral",
                 "StringLiteral",
                 "false",
                 "here",
                 "null",
                 "self",
                 "true",
                 "ByteLiteral",
                 "ShortLiteral",
                 "UnsignedIntegerLiteral",
                 "UnsignedLongLiteral",
                 "UnsignedByteLiteral",
                 "UnsignedShortLiteral",
                 "MULTIPLY",
                 "MINUS",
                 "PLUS",
                 "MINUS_MINUS",
                 "DOT",
                 "PLUS_PLUS",
                 "NOT",
                 "REMAINDER",
                 "DIVIDE",
                 "OR",
                 "AND",
                 "XOR",
                 "TWIDDLE",
                 "void",
                 "LBRACE",
                 "EQUAL",
                 "RBRACKET",
                 "RBRACE",
                 "clocked",
                 "atomic",
                 "finish",
                 "COLON",
                 "abstract",
                 "at",
                 "athome",
                 "final",
                 "native",
                 "private",
                 "protected",
                 "public",
                 "static",
                 "transient",
                 "SUBTYPE",
                 "val",
                 "var",
                 "class",
                 "EQUAL_EQUAL",
                 "struct",
                 "type",
                 "ErrorId",
                 "LESS",
                 "LEFT_SHIFT",
                 "RIGHT_SHIFT",
                 "UNSIGNED_RIGHT_SHIFT",
                 "LESS_EQUAL",
                 "GREATER",
                 "GREATER_EQUAL",
                 "RANGE",
                 "ARROW",
                 "LARROW",
                 "FUNNEL",
                 "LFUNNEL",
                 "SUPERTYPE",
                 "haszero",
                 "NOT_EQUAL",
                 "STARSTAR",
                 "case",
                 "default",
                 "import",
                 "instanceof",
                 "AND_AND",
                 "NTWIDDLE",
                 "MINUS_EQUAL",
                 "REMAINDER_EQUAL",
                 "AND_EQUAL",
                 "MULTIPLY_EQUAL",
                 "DIVIDE_EQUAL",
                 "XOR_EQUAL",
                 "OR_OR",
                 "OR_EQUAL",
                 "PLUS_EQUAL",
                 "LEFT_SHIFT_EQUAL",
                 "RIGHT_SHIFT_EQUAL",
                 "UNSIGNED_RIGHT_SHIFT_EQUAL",
                 "EOF_TOKEN",
                 "QUESTION",
                 "as",
                 "in",
                 "interface",
                 "property",
                 "while",
                 "DARROW",
                 "async",
                 "ateach",
                 "catch",
                 "def",
                 "do",
                 "extends",
                 "finally",
                 "for",
                 "package",
                 "assert",
                 "break",
                 "continue",
                 "else",
                 "if",
                 "implements",
                 "offer",
                 "offers",
                 "operator",
                 "return",
                 "switch",
                 "throw",
                 "try",
                 "when",
                 "ELLIPSIS",
                 "goto",
                 "SlComment",
                 "MlComment",
                 "DocComment",
                 "PseudoDoubleLiteral",
                 "ERROR_TOKEN"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}
