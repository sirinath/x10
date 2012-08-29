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
      TK_IntegerLiteral = 22,
      TK_LongLiteral = 23,
      TK_ByteLiteral = 24,
      TK_ShortLiteral = 25,
      TK_UnsignedIntegerLiteral = 26,
      TK_UnsignedLongLiteral = 27,
      TK_UnsignedByteLiteral = 28,
      TK_UnsignedShortLiteral = 29,
      TK_FloatingPointLiteral = 30,
      TK_DoubleLiteral = 31,
      TK_CharacterLiteral = 32,
      TK_StringLiteral = 33,
      TK_MINUS_MINUS = 42,
      TK_OR = 14,
      TK_MINUS = 8,
      TK_MINUS_EQUAL = 94,
      TK_NOT = 10,
      TK_NOT_EQUAL = 66,
      TK_REMAINDER = 12,
      TK_REMAINDER_EQUAL = 95,
      TK_AND = 15,
      TK_AND_AND = 67,
      TK_AND_EQUAL = 96,
      TK_LPAREN = 1,
      TK_RPAREN = 3,
      TK_MULTIPLY = 11,
      TK_MULTIPLY_EQUAL = 97,
      TK_COMMA = 6,
      TK_DOT = 39,
      TK_DIVIDE = 13,
      TK_DIVIDE_EQUAL = 98,
      TK_COLON = 63,
      TK_SEMICOLON = 5,
      TK_QUESTION = 99,
      TK_AT = 4,
      TK_LBRACKET = 2,
      TK_RBRACKET = 44,
      TK_XOR = 16,
      TK_XOR_EQUAL = 100,
      TK_LBRACE = 45,
      TK_OR_OR = 64,
      TK_OR_EQUAL = 101,
      TK_RBRACE = 46,
      TK_TWIDDLE = 17,
      TK_PLUS = 9,
      TK_PLUS_PLUS = 43,
      TK_PLUS_EQUAL = 102,
      TK_LESS = 48,
      TK_LEFT_SHIFT = 49,
      TK_LEFT_SHIFT_EQUAL = 103,
      TK_RIGHT_SHIFT = 50,
      TK_RIGHT_SHIFT_EQUAL = 104,
      TK_UNSIGNED_RIGHT_SHIFT = 51,
      TK_UNSIGNED_RIGHT_SHIFT_EQUAL = 105,
      TK_LESS_EQUAL = 52,
      TK_EQUAL = 40,
      TK_EQUAL_EQUAL = 47,
      TK_GREATER = 53,
      TK_GREATER_EQUAL = 54,
      TK_ELLIPSIS = 145,
      TK_RANGE = 55,
      TK_ARROW = 56,
      TK_DARROW = 120,
      TK_SUBTYPE = 80,
      TK_SUPERTYPE = 91,
      TK_STARSTAR = 65,
      TK_NTWIDDLE = 68,
      TK_LARROW = 57,
      TK_FUNNEL = 58,
      TK_LFUNNEL = 59,
      TK_DIAMOND = 60,
      TK_BOWTIE = 61,
      TK_RANGE_EQUAL = 106,
      TK_ARROW_EQUAL = 107,
      TK_STARSTAR_EQUAL = 108,
      TK_TWIDDLE_EQUAL = 109,
      TK_LARROW_EQUAL = 110,
      TK_FUNNEL_EQUAL = 111,
      TK_LFUNNEL_EQUAL = 112,
      TK_DIAMOND_EQUAL = 113,
      TK_BOWTIE_EQUAL = 114,
      TK_abstract = 71,
      TK_as = 115,
      TK_assert = 131,
      TK_async = 121,
      TK_at = 72,
      TK_athome = 146,
      TK_ateach = 122,
      TK_atomic = 69,
      TK_break = 132,
      TK_case = 87,
      TK_catch = 123,
      TK_class = 83,
      TK_clocked = 62,
      TK_continue = 133,
      TK_def = 124,
      TK_default = 88,
      TK_do = 125,
      TK_else = 134,
      TK_extends = 126,
      TK_false = 34,
      TK_final = 73,
      TK_finally = 127,
      TK_finish = 70,
      TK_for = 128,
      TK_goto = 147,
      TK_haszero = 92,
      TK_here = 35,
      TK_if = 135,
      TK_implements = 136,
      TK_import = 89,
      TK_in = 129,
      TK_instanceof = 90,
      TK_interface = 117,
      TK_isref = 93,
      TK_native = 74,
      TK_new = 20,
      TK_null = 36,
      TK_offer = 137,
      TK_offers = 138,
      TK_operator = 18,
      TK_package = 130,
      TK_private = 75,
      TK_property = 118,
      TK_protected = 76,
      TK_public = 77,
      TK_return = 139,
      TK_self = 37,
      TK_static = 78,
      TK_struct = 84,
      TK_super = 21,
      TK_switch = 140,
      TK_this = 19,
      TK_throw = 141,
      TK_throws = 142,
      TK_transient = 79,
      TK_true = 38,
      TK_try = 143,
      TK_type = 85,
      TK_val = 81,
      TK_var = 82,
      TK_void = 41,
      TK_when = 144,
      TK_while = 119,
      TK_EOF_TOKEN = 116,
      TK_IDENTIFIER = 7,
      TK_SlComment = 148,
      TK_MlComment = 149,
      TK_DocComment = 150,
      TK_PseudoDoubleLiteral = 151,
      TK_ErrorId = 86,
      TK_ERROR_TOKEN = 152;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "LPAREN",
                 "LBRACKET",
                 "RPAREN",
                 "AT",
                 "SEMICOLON",
                 "COMMA",
                 "IDENTIFIER",
                 "MINUS",
                 "PLUS",
                 "NOT",
                 "MULTIPLY",
                 "REMAINDER",
                 "DIVIDE",
                 "OR",
                 "AND",
                 "XOR",
                 "TWIDDLE",
                 "operator",
                 "this",
                 "new",
                 "super",
                 "IntegerLiteral",
                 "LongLiteral",
                 "ByteLiteral",
                 "ShortLiteral",
                 "UnsignedIntegerLiteral",
                 "UnsignedLongLiteral",
                 "UnsignedByteLiteral",
                 "UnsignedShortLiteral",
                 "FloatingPointLiteral",
                 "DoubleLiteral",
                 "CharacterLiteral",
                 "StringLiteral",
                 "false",
                 "here",
                 "null",
                 "self",
                 "true",
                 "DOT",
                 "EQUAL",
                 "void",
                 "MINUS_MINUS",
                 "PLUS_PLUS",
                 "RBRACKET",
                 "LBRACE",
                 "RBRACE",
                 "EQUAL_EQUAL",
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
                 "DIAMOND",
                 "BOWTIE",
                 "clocked",
                 "COLON",
                 "OR_OR",
                 "STARSTAR",
                 "NOT_EQUAL",
                 "AND_AND",
                 "NTWIDDLE",
                 "atomic",
                 "finish",
                 "abstract",
                 "at",
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
                 "struct",
                 "type",
                 "ErrorId",
                 "case",
                 "default",
                 "import",
                 "instanceof",
                 "SUPERTYPE",
                 "haszero",
                 "isref",
                 "MINUS_EQUAL",
                 "REMAINDER_EQUAL",
                 "AND_EQUAL",
                 "MULTIPLY_EQUAL",
                 "DIVIDE_EQUAL",
                 "QUESTION",
                 "XOR_EQUAL",
                 "OR_EQUAL",
                 "PLUS_EQUAL",
                 "LEFT_SHIFT_EQUAL",
                 "RIGHT_SHIFT_EQUAL",
                 "UNSIGNED_RIGHT_SHIFT_EQUAL",
                 "RANGE_EQUAL",
                 "ARROW_EQUAL",
                 "STARSTAR_EQUAL",
                 "TWIDDLE_EQUAL",
                 "LARROW_EQUAL",
                 "FUNNEL_EQUAL",
                 "LFUNNEL_EQUAL",
                 "DIAMOND_EQUAL",
                 "BOWTIE_EQUAL",
                 "as",
                 "EOF_TOKEN",
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
                 "in",
                 "package",
                 "assert",
                 "break",
                 "continue",
                 "else",
                 "if",
                 "implements",
                 "offer",
                 "offers",
                 "return",
                 "switch",
                 "throw",
                 "throws",
                 "try",
                 "when",
                 "ELLIPSIS",
                 "athome",
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
