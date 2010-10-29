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

package x10.parser;

public interface X10Parsersym {
    public final static int
      TK_IntegerLiteral = 20,
      TK_LongLiteral = 21,
      TK_FloatingPointLiteral = 22,
      TK_DoubleLiteral = 23,
      TK_CharacterLiteral = 24,
      TK_StringLiteral = 25,
      TK_MINUS_MINUS = 36,
      TK_OR = 71,
      TK_MINUS = 34,
      TK_MINUS_EQUAL = 81,
      TK_NOT = 39,
      TK_NOT_EQUAL = 72,
      TK_REMAINDER = 61,
      TK_REMAINDER_EQUAL = 82,
      TK_AND = 73,
      TK_AND_AND = 80,
      TK_AND_EQUAL = 83,
      TK_LPAREN = 1,
      TK_RPAREN = 14,
      TK_MULTIPLY = 60,
      TK_MULTIPLY_EQUAL = 84,
      TK_COMMA = 18,
      TK_DOT = 33,
      TK_DIVIDE = 62,
      TK_DIVIDE_EQUAL = 85,
      TK_COLON = 44,
      TK_SEMICOLON = 15,
      TK_QUESTION = 95,
      TK_AT = 13,
      TK_LBRACKET = 2,
      TK_RBRACKET = 40,
      TK_XOR = 74,
      TK_XOR_EQUAL = 86,
      TK_LBRACE = 41,
      TK_OR_OR = 87,
      TK_OR_EQUAL = 88,
      TK_RBRACE = 42,
      TK_TWIDDLE = 43,
      TK_PLUS = 35,
      TK_PLUS_PLUS = 37,
      TK_PLUS_EQUAL = 89,
      TK_LESS = 63,
      TK_LEFT_SHIFT = 64,
      TK_LEFT_SHIFT_EQUAL = 90,
      TK_RIGHT_SHIFT = 65,
      TK_RIGHT_SHIFT_EQUAL = 91,
      TK_UNSIGNED_RIGHT_SHIFT = 66,
      TK_UNSIGNED_RIGHT_SHIFT_EQUAL = 92,
      TK_LESS_EQUAL = 67,
      TK_EQUAL = 38,
      TK_EQUAL_EQUAL = 57,
      TK_GREATER = 68,
      TK_GREATER_EQUAL = 69,
      TK_ELLIPSIS = 124,
      TK_RANGE = 100,
      TK_ARROW = 32,
      TK_DARROW = 101,
      TK_SUBTYPE = 46,
      TK_SUPERTYPE = 75,
      TK_abstract = 48,
      TK_as = 96,
      TK_assert = 111,
      TK_async = 102,
      TK_at = 6,
      TK_ateach = 103,
      TK_atomic = 4,
      TK_break = 112,
      TK_case = 76,
      TK_catch = 104,
      TK_class = 47,
      TK_clocked = 3,
      TK_continue = 113,
      TK_def = 105,
      TK_default = 77,
      TK_do = 106,
      TK_else = 114,
      TK_extends = 107,
      TK_false = 26,
      TK_final = 49,
      TK_finally = 108,
      TK_finish = 45,
      TK_for = 109,
      TK_goto = 125,
      TK_here = 7,
      TK_if = 115,
      TK_implements = 116,
      TK_import = 78,
      TK_in = 70,
      TK_instanceof = 93,
      TK_interface = 97,
      TK_native = 50,
      TK_new = 17,
      TK_next = 8,
      TK_null = 27,
      TK_offer = 9,
      TK_offers = 117,
      TK_operator = 118,
      TK_package = 110,
      TK_private = 51,
      TK_property = 98,
      TK_protected = 52,
      TK_public = 53,
      TK_resume = 10,
      TK_return = 119,
      TK_self = 28,
      TK_static = 54,
      TK_struct = 59,
      TK_super = 19,
      TK_switch = 120,
      TK_this = 16,
      TK_throw = 121,
      TK_transient = 55,
      TK_true = 29,
      TK_try = 122,
      TK_type = 5,
      TK_val = 11,
      TK_var = 56,
      TK_when = 123,
      TK_while = 99,
      TK_hasZero = 79,
      TK_EOF_TOKEN = 94,
      TK_IDENTIFIER = 12,
      TK_SlComment = 126,
      TK_MlComment = 127,
      TK_DocComment = 128,
      TK_UnsignedIntegerLiteral = 30,
      TK_UnsignedLongLiteral = 31,
      TK_ErrorId = 58,
      TK_ERROR_TOKEN = 129;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "LPAREN",
                 "LBRACKET",
                 "clocked",
                 "atomic",
                 "type",
                 "at",
                 "here",
                 "next",
                 "offer",
                 "resume",
                 "val",
                 "IDENTIFIER",
                 "AT",
                 "RPAREN",
                 "SEMICOLON",
                 "this",
                 "new",
                 "COMMA",
                 "super",
                 "IntegerLiteral",
                 "LongLiteral",
                 "FloatingPointLiteral",
                 "DoubleLiteral",
                 "CharacterLiteral",
                 "StringLiteral",
                 "false",
                 "null",
                 "self",
                 "true",
                 "UnsignedIntegerLiteral",
                 "UnsignedLongLiteral",
                 "ARROW",
                 "DOT",
                 "MINUS",
                 "PLUS",
                 "MINUS_MINUS",
                 "PLUS_PLUS",
                 "EQUAL",
                 "NOT",
                 "RBRACKET",
                 "LBRACE",
                 "RBRACE",
                 "TWIDDLE",
                 "COLON",
                 "finish",
                 "SUBTYPE",
                 "class",
                 "abstract",
                 "final",
                 "native",
                 "private",
                 "protected",
                 "public",
                 "static",
                 "transient",
                 "var",
                 "EQUAL_EQUAL",
                 "ErrorId",
                 "struct",
                 "MULTIPLY",
                 "REMAINDER",
                 "DIVIDE",
                 "LESS",
                 "LEFT_SHIFT",
                 "RIGHT_SHIFT",
                 "UNSIGNED_RIGHT_SHIFT",
                 "LESS_EQUAL",
                 "GREATER",
                 "GREATER_EQUAL",
                 "in",
                 "OR",
                 "NOT_EQUAL",
                 "AND",
                 "XOR",
                 "SUPERTYPE",
                 "case",
                 "default",
                 "import",
                 "hasZero",
                 "AND_AND",
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
                 "instanceof",
                 "EOF_TOKEN",
                 "QUESTION",
                 "as",
                 "interface",
                 "property",
                 "while",
                 "RANGE",
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
                 "ERROR_TOKEN"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}
