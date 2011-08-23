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
      TK_IntegerLiteral = 23,
      TK_LongLiteral = 24,
      TK_ByteLiteral = 25,
      TK_ShortLiteral = 26,
      TK_UnsignedIntegerLiteral = 27,
      TK_UnsignedLongLiteral = 28,
      TK_UnsignedByteLiteral = 29,
      TK_UnsignedShortLiteral = 30,
      TK_FloatingPointLiteral = 31,
      TK_DoubleLiteral = 32,
      TK_CharacterLiteral = 33,
      TK_StringLiteral = 34,
      TK_MINUS_MINUS = 42,
      TK_OR = 14,
      TK_MINUS = 8,
      TK_MINUS_EQUAL = 95,
      TK_NOT = 10,
      TK_NOT_EQUAL = 67,
      TK_REMAINDER = 12,
      TK_REMAINDER_EQUAL = 96,
      TK_AND = 15,
      TK_AND_AND = 68,
      TK_AND_EQUAL = 97,
      TK_LPAREN = 1,
      TK_RPAREN = 3,
      TK_MULTIPLY = 11,
      TK_MULTIPLY_EQUAL = 98,
      TK_COMMA = 7,
      TK_DOT = 21,
      TK_DIVIDE = 13,
      TK_DIVIDE_EQUAL = 99,
      TK_COLON = 63,
      TK_SEMICOLON = 6,
      TK_QUESTION = 100,
      TK_AT = 4,
      TK_LBRACKET = 2,
      TK_RBRACKET = 44,
      TK_XOR = 16,
      TK_XOR_EQUAL = 101,
      TK_LBRACE = 45,
      TK_OR_OR = 64,
      TK_OR_EQUAL = 102,
      TK_RBRACE = 46,
      TK_TWIDDLE = 17,
      TK_PLUS = 9,
      TK_PLUS_PLUS = 43,
      TK_PLUS_EQUAL = 103,
      TK_LESS = 48,
      TK_LEFT_SHIFT = 49,
      TK_LEFT_SHIFT_EQUAL = 104,
      TK_RIGHT_SHIFT = 50,
      TK_RIGHT_SHIFT_EQUAL = 105,
      TK_UNSIGNED_RIGHT_SHIFT = 51,
      TK_UNSIGNED_RIGHT_SHIFT_EQUAL = 106,
      TK_LESS_EQUAL = 52,
      TK_EQUAL = 40,
      TK_EQUAL_EQUAL = 47,
      TK_GREATER = 53,
      TK_GREATER_EQUAL = 54,
      TK_ELLIPSIS = 145,
      TK_RANGE = 55,
      TK_ARROW = 56,
      TK_DARROW = 121,
      TK_SUBTYPE = 82,
      TK_SUPERTYPE = 93,
      TK_STARSTAR = 65,
      TK_NTWIDDLE = 69,
      TK_LARROW = 57,
      TK_FUNNEL = 58,
      TK_LFUNNEL = 59,
      TK_DIAMOND = 60,
      TK_BOWTIE = 61,
      TK_RANGE_EQUAL = 107,
      TK_ARROW_EQUAL = 108,
      TK_STARSTAR_EQUAL = 109,
      TK_TWIDDLE_EQUAL = 110,
      TK_LARROW_EQUAL = 111,
      TK_FUNNEL_EQUAL = 112,
      TK_LFUNNEL_EQUAL = 113,
      TK_DIAMOND_EQUAL = 114,
      TK_BOWTIE_EQUAL = 115,
      TK_abstract = 70,
      TK_as = 116,
      TK_assert = 132,
      TK_async = 122,
      TK_at = 81,
      TK_athome = 146,
      TK_ateach = 123,
      TK_atomic = 66,
      TK_break = 133,
      TK_case = 89,
      TK_catch = 124,
      TK_class = 85,
      TK_clocked = 62,
      TK_continue = 134,
      TK_def = 125,
      TK_default = 90,
      TK_do = 126,
      TK_else = 135,
      TK_extends = 127,
      TK_false = 35,
      TK_final = 71,
      TK_finally = 128,
      TK_finish = 72,
      TK_for = 129,
      TK_goto = 147,
      TK_haszero = 94,
      TK_here = 36,
      TK_if = 136,
      TK_implements = 137,
      TK_import = 91,
      TK_in = 130,
      TK_instanceof = 92,
      TK_interface = 118,
      TK_native = 73,
      TK_new = 20,
      TK_null = 37,
      TK_offer = 138,
      TK_offers = 139,
      TK_operator = 18,
      TK_package = 131,
      TK_private = 74,
      TK_property = 119,
      TK_protected = 75,
      TK_public = 76,
      TK_return = 140,
      TK_self = 38,
      TK_static = 77,
      TK_struct = 86,
      TK_super = 22,
      TK_switch = 141,
      TK_this = 19,
      TK_throw = 142,
      TK_transient = 78,
      TK_true = 39,
      TK_try = 143,
      TK_type = 87,
      TK_val = 83,
      TK_var = 84,
      TK_void = 41,
      TK_when = 144,
      TK_while = 120,
      TK_unitfor = 79,
      TK_atomicplus = 80,
      TK_EOF_TOKEN = 117,
      TK_IDENTIFIER = 5,
      TK_SlComment = 148,
      TK_MlComment = 149,
      TK_DocComment = 150,
      TK_PseudoDoubleLiteral = 151,
      TK_ErrorId = 88,
      TK_ERROR_TOKEN = 152;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "LPAREN",
                 "LBRACKET",
                 "RPAREN",
                 "AT",
                 "IDENTIFIER",
                 "SEMICOLON",
                 "COMMA",
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
                 "DOT",
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
                 "atomic",
                 "NOT_EQUAL",
                 "AND_AND",
                 "NTWIDDLE",
                 "abstract",
                 "final",
                 "finish",
                 "native",
                 "private",
                 "protected",
                 "public",
                 "static",
                 "transient",
                 "unitfor",
                 "atomicplus",
                 "at",
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
