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
/*****************************************************
 * WARNING!  THIS IS A GENERATED FILE.  DO NOT EDIT! *
 *****************************************************/

package x10.parser;

public interface X10Lexersym {
    public final static int
      Char_CtlCharNotWS = 102,
      Char_LF = 100,
      Char_CR = 101,
      Char_HT = 39,
      Char_FF = 40,
      Char_a = 19,
      Char_b = 16,
      Char_c = 20,
      Char_d = 12,
      Char_e = 17,
      Char_f = 11,
      Char_g = 41,
      Char_h = 42,
      Char_i = 43,
      Char_j = 44,
      Char_k = 45,
      Char_l = 26,
      Char_m = 46,
      Char_n = 31,
      Char_o = 47,
      Char_p = 48,
      Char_q = 49,
      Char_r = 32,
      Char_s = 50,
      Char_t = 33,
      Char_u = 25,
      Char_v = 51,
      Char_w = 52,
      Char_x = 34,
      Char_y = 53,
      Char_z = 54,
      Char__ = 55,
      Char_A = 21,
      Char_B = 22,
      Char_C = 23,
      Char_D = 13,
      Char_E = 18,
      Char_F = 14,
      Char_G = 56,
      Char_H = 57,
      Char_I = 58,
      Char_J = 59,
      Char_K = 60,
      Char_L = 27,
      Char_M = 61,
      Char_N = 62,
      Char_O = 63,
      Char_P = 64,
      Char_Q = 65,
      Char_R = 66,
      Char_S = 67,
      Char_T = 68,
      Char_U = 28,
      Char_V = 69,
      Char_W = 70,
      Char_X = 35,
      Char_Y = 71,
      Char_Z = 72,
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
      Char_AfterASCII = 73,
      Char_Space = 74,
      Char_DoubleQuote = 36,
      Char_SingleQuote = 24,
      Char_Percent = 83,
      Char_VerticalBar = 75,
      Char_Exclamation = 84,
      Char_AtSign = 85,
      Char_BackQuote = 98,
      Char_Tilde = 86,
      Char_Sharp = 99,
      Char_DollarSign = 76,
      Char_Ampersand = 77,
      Char_Caret = 87,
      Char_Colon = 78,
      Char_SemiColon = 88,
      Char_BackSlash = 79,
      Char_LeftBrace = 89,
      Char_RightBrace = 90,
      Char_LeftBracket = 91,
      Char_RightBracket = 92,
      Char_QuestionMark = 93,
      Char_Comma = 94,
      Char_Dot = 30,
      Char_LessThan = 80,
      Char_GreaterThan = 29,
      Char_Plus = 37,
      Char_Minus = 38,
      Char_Slash = 81,
      Char_Star = 82,
      Char_LeftParen = 95,
      Char_RightParen = 96,
      Char_Equal = 15,
      Char_EOF = 97;

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
                 "f",
                 "d",
                 "D",
                 "F",
                 "Equal",
                 "b",
                 "e",
                 "E",
                 "a",
                 "c",
                 "A",
                 "B",
                 "C",
                 "SingleQuote",
                 "u",
                 "l",
                 "L",
                 "U",
                 "GreaterThan",
                 "Dot",
                 "n",
                 "r",
                 "t",
                 "x",
                 "X",
                 "DoubleQuote",
                 "Plus",
                 "Minus",
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
                 "s",
                 "v",
                 "w",
                 "y",
                 "z",
                 "_",
                 "G",
                 "H",
                 "I",
                 "J",
                 "K",
                 "M",
                 "N",
                 "O",
                 "P",
                 "Q",
                 "R",
                 "S",
                 "T",
                 "V",
                 "W",
                 "Y",
                 "Z",
                 "AfterASCII",
                 "Space",
                 "VerticalBar",
                 "DollarSign",
                 "Ampersand",
                 "Colon",
                 "BackSlash",
                 "LessThan",
                 "Slash",
                 "Star",
                 "Percent",
                 "Exclamation",
                 "AtSign",
                 "Tilde",
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
                 "EOF",
                 "BackQuote",
                 "Sharp",
                 "LF",
                 "CR",
                 "CtlCharNotWS"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}
