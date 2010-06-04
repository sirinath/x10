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

public interface X10KWLexersym {
    public final static int
      Char_a = 3,
      Char_b = 18,
      Char_c = 7,
      Char_d = 15,
      Char_e = 1,
      Char_f = 12,
      Char_g = 21,
      Char_h = 13,
      Char_i = 9,
      Char_j = 27,
      Char_k = 19,
      Char_l = 6,
      Char_m = 20,
      Char_n = 8,
      Char_o = 5,
      Char_p = 14,
      Char_q = 24,
      Char_r = 4,
      Char_s = 10,
      Char_t = 2,
      Char_u = 11,
      Char_v = 22,
      Char_w = 16,
      Char_x = 23,
      Char_y = 17,
      Char_z = 25,
      Char_EOF = 26;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "e",
                 "t",
                 "a",
                 "r",
                 "o",
                 "l",
                 "c",
                 "n",
                 "i",
                 "s",
                 "u",
                 "f",
                 "h",
                 "p",
                 "d",
                 "w",
                 "y",
                 "b",
                 "k",
                 "m",
                 "g",
                 "v",
                 "x",
                 "q",
                 "z",
                 "EOF",
                 "j"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}
