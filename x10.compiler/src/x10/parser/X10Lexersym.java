/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2015.
 */
/**************************************************************************
 * WARNING!  THIS JAVA FILE IS AUTO-GENERATED FROM x10/parser/X10Lexer.gi *
 **************************************************************************/

package x10.parser;

public interface X10Lexersym {
    public final static int
      Char_CtlCharNotWS = 102,
      Char_LF = 100,
      Char_CR = 101,
      Char_HT = 48,
      Char_FF = 49,
      Char_a = 19,
      Char_b = 16,
      Char_c = 20,
      Char_d = 13,
      Char_e = 17,
      Char_f = 12,
      Char_g = 50,
      Char_h = 51,
      Char_i = 52,
      Char_j = 53,
      Char_k = 54,
      Char_l = 28,
      Char_m = 55,
      Char_n = 25,
      Char_o = 56,
      Char_p = 57,
      Char_q = 58,
      Char_r = 36,
      Char_s = 29,
      Char_t = 37,
      Char_u = 24,
      Char_v = 59,
      Char_w = 60,
      Char_x = 44,
      Char_y = 30,
      Char_z = 61,
      Char__ = 62,
      Char_A = 21,
      Char_B = 22,
      Char_C = 23,
      Char_D = 14,
      Char_E = 18,
      Char_F = 15,
      Char_G = 63,
      Char_H = 64,
      Char_I = 65,
      Char_J = 66,
      Char_K = 67,
      Char_L = 31,
      Char_M = 68,
      Char_N = 32,
      Char_O = 69,
      Char_P = 70,
      Char_Q = 71,
      Char_R = 72,
      Char_S = 33,
      Char_T = 73,
      Char_U = 26,
      Char_V = 74,
      Char_W = 75,
      Char_X = 45,
      Char_Y = 34,
      Char_Z = 76,
      Char_0 = 1,
      Char_1 = 2,
      Char_2 = 3,
      Char_3 = 4,
      Char_4 = 5,
      Char_5 = 6,
      Char_6 = 7,
      Char_7 = 8,
      Char_8 = 9,
      Char_9 = 10,
      Char_AfterASCII = 77,
      Char_Space = 78,
      Char_DoubleQuote = 39,
      Char_SingleQuote = 40,
      Char_Percent = 85,
      Char_VerticalBar = 79,
      Char_Exclamation = 86,
      Char_AtSign = 87,
      Char_BackQuote = 41,
      Char_Tilde = 80,
      Char_Sharp = 98,
      Char_DollarSign = 81,
      Char_Ampersand = 82,
      Char_Caret = 88,
      Char_Colon = 83,
      Char_SemiColon = 89,
      Char_BackSlash = 46,
      Char_LeftBrace = 90,
      Char_RightBrace = 91,
      Char_LeftBracket = 92,
      Char_RightBracket = 93,
      Char_QuestionMark = 94,
      Char_Comma = 95,
      Char_Dot = 38,
      Char_LessThan = 42,
      Char_GreaterThan = 27,
      Char_Plus = 43,
      Char_Minus = 35,
      Char_Slash = 84,
      Char_Star = 47,
      Char_LeftParen = 96,
      Char_RightParen = 97,
      Char_Equal = 11,
      Char_EOF = 99;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "0",
                 "1",
                 "2",
                 "3",
                 "4",
                 "5",
                 "6",
                 "7",
                 "8",
                 "9",
                 "Equal",
                 "f",
                 "d",
                 "D",
                 "F",
                 "b",
                 "e",
                 "E",
                 "a",
                 "c",
                 "A",
                 "B",
                 "C",
                 "u",
                 "n",
                 "U",
                 "GreaterThan",
                 "l",
                 "s",
                 "y",
                 "L",
                 "N",
                 "S",
                 "Y",
                 "Minus",
                 "r",
                 "t",
                 "Dot",
                 "DoubleQuote",
                 "SingleQuote",
                 "BackQuote",
                 "LessThan",
                 "Plus",
                 "x",
                 "X",
                 "BackSlash",
                 "Star",
                 "HT",
                 "FF",
                 "g",
                 "h",
                 "i",
                 "j",
                 "k",
                 "m",
                 "o",
                 "p",
                 "q",
                 "v",
                 "w",
                 "z",
                 "_",
                 "G",
                 "H",
                 "I",
                 "J",
                 "K",
                 "M",
                 "O",
                 "P",
                 "Q",
                 "R",
                 "T",
                 "V",
                 "W",
                 "Z",
                 "AfterASCII",
                 "Space",
                 "VerticalBar",
                 "Tilde",
                 "DollarSign",
                 "Ampersand",
                 "Colon",
                 "Slash",
                 "Percent",
                 "Exclamation",
                 "AtSign",
                 "Caret",
                 "SemiColon",
                 "LeftBrace",
                 "RightBrace",
                 "LeftBracket",
                 "RightBracket",
                 "QuestionMark",
                 "Comma",
                 "LeftParen",
                 "RightParen",
                 "Sharp",
                 "EOF",
                 "LF",
                 "CR",
                 "CtlCharNotWS"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}
