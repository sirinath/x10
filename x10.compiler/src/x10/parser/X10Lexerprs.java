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
/**************************************************************************
 * WARNING!  THIS JAVA FILE IS AUTO-GENERATED FROM x10/parser/X10Lexer.gi *
 **************************************************************************/

package x10.parser;

public class X10Lexerprs implements lpg.runtime.ParseTable, X10Lexersym {
    public final static int ERROR_SYMBOL = 0;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 0;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 0;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 0;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 82;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 102;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 1121;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 2;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 435;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 56;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 158;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 436;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 99;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 103;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 685;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 686;
    public final int getErrorAction() { return ERROR_ACTION; }

    public final static boolean BACKTRACK = false;
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int getStartSymbol() { return lhs(0); }
    public final boolean isValidForParser() { return X10Lexersym.isValidForParser; }


    public interface IsNullable {
        public final static byte isNullable[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,1,1,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,1,0,0,0,0,
            0,0,0,0,0,0,0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            40,41,47,43,44,45,21,46,27,29,
            31,42,55,24,25,26,30,32,35,48,
            50,2,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,19,20,
            22,23,28,33,34,36,37,38,39,49,
            51,52,53,56,1,54
        };
    };
    public final static byte prosthesesIndex[] = ProsthesesIndex.prosthesesIndex;
    public final int prosthesesIndex(int index) { return prosthesesIndex[index]; }

    public interface IsKeyword {
        public final static byte isKeyword[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            1,3,3,3,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,2,2,2,2,2,2,2,
            2,3,2,2,2,2,2,2,2,2,
            3,3,4,2,2,3,1,3,2,2,
            2,2,3,3,3,3,3,3,2,3,
            3,2,1,2,2,2,3,3,2,2,
            5,3,2,2,0,1,2,1,2,2,
            0,2,0,2,1,2,1,2,2,2,
            3,2,3,3,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,2,
            3,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,2,1,
            2,2,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,6,2,1,1,1,1,
            1,1,1,6,2,1,1,1,1,1,
            1,1,6,2,2,2,2,2,2,2,
            2,2,2,1,2,2,2,2,2,2,
            2,2,2,2,3
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            22,22,22,22,22,22,22,22,22,22,
            22,22,22,22,22,22,22,22,22,22,
            22,22,22,22,22,22,22,22,22,22,
            22,22,22,22,22,22,22,22,22,22,
            22,22,22,22,22,22,22,22,22,22,
            22,22,22,22,22,22,22,22,22,22,
            22,22,22,22,22,22,22,27,27,28,
            29,30,31,32,32,33,33,34,34,35,
            35,35,35,36,36,36,36,36,36,36,
            37,38,44,44,44,44,45,45,39,46,
            46,24,24,25,25,7,7,42,42,43,
            43,43,17,17,17,12,12,12,12,12,
            4,4,4,4,4,5,5,5,5,5,
            5,5,5,5,5,5,5,5,5,5,
            5,5,5,5,5,5,5,5,5,5,
            5,6,6,6,6,6,6,6,6,6,
            6,6,6,6,6,6,6,6,6,6,
            6,6,6,6,6,6,6,1,1,1,
            1,1,1,1,1,1,1,8,8,8,
            8,8,8,8,8,3,3,3,3,3,
            3,3,3,3,3,3,3,2,2,20,
            20,20,10,10,11,11,14,14,15,15,
            16,16,9,9,18,18,41,41,40,40,
            23,23,23,50,50,50,50,50,50,50,
            50,50,50,50,50,50,50,50,50,50,
            50,50,50,50,50,50,50,50,50,50,
            50,50,21,21,21,21,21,21,21,21,
            21,21,21,21,21,21,21,21,21,21,
            21,21,21,21,21,21,21,21,21,21,
            51,51,51,51,51,51,51,51,51,51,
            51,51,51,51,51,51,51,51,51,51,
            51,51,51,51,51,51,51,51,52,52,
            52,52,52,52,52,52,52,52,52,52,
            52,52,52,52,52,52,52,52,52,52,
            52,52,52,52,52,52,53,53,53,53,
            53,53,53,53,53,53,53,53,53,53,
            53,53,53,53,53,53,53,53,53,53,
            53,53,53,53,19,19,19,19,56,56,
            47,47,47,47,47,47,47,47,49,49,
            49,49,49,49,49,49,49,26,26,26,
            26,26,26,26,26,26,48,48,48,48,
            48,48,48,48,48,13,13,13,13,13,
            13,13,13,13,22,22,22,22,22,22,
            22,22,22,22,22,54,308,674,647,1520,
            673,673,673,477,812,571,571,571,675,410,
            104,229,1303,104,104,104,1358,106,438,564,
            304,1236,104,496,6,7,8,9,10,11,
            12,13,14,15,16,17,439,1111,106,545,
            1,94,538,1143,94,94,94,82,89,1129,
            424,100,94,557,587,636,1312,524,104,94,
            104,94,608,670,480,537,670,670,670,405,
            645,644,531,509,102,670,449,102,102,102,
            835,108,108,108,1154,529,102,1521,670,103,
            92,1194,649,92,92,92,740,1519,205,100,
            78,92,100,100,100,1186,80,87,92,1301,
            92,73,75,77,670,79,86,789,520,520,
            520,102,536,626,707,232,102,1302,231,231,
            231,858,589,589,589,881,593,593,593,904,
            600,600,600,1403,106,100,1209,1138,100,927,
            604,604,604,950,608,608,608,81,88,520,
            973,612,612,612,996,404,404,404,1019,616,
            616,616,1042,620,620,620,1065,395,395,395,
            1088,413,413,413,1289,456,1267,583,1347,651,
            1222,456,202,583,1321,651,1278,583,524,1369,
            658,1385,660,583,1533,1538,658,1177,660,1247,
            414,1414,106,76,74,396,513,211,1425,106,
            1436,106,755,1172,1529,1528,1530,1147,1532,1536,
            747,748,1542,1205,1214,1206,1447,1456,1465,1474,
            1483,1492,1501,1510,686,686
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,35,36,37,38,39,
            40,41,42,43,44,45,46,47,48,49,
            50,51,52,53,54,55,56,57,58,59,
            60,61,62,63,64,65,66,67,68,69,
            70,71,72,73,74,75,76,77,78,79,
            80,81,82,83,84,85,86,87,88,89,
            90,91,92,93,94,95,96,97,98,0,
            100,101,0,1,2,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,29,30,31,32,33,34,35,36,37,
            38,39,40,41,42,43,44,45,46,47,
            48,49,50,51,52,53,54,55,56,57,
            58,59,60,61,62,63,64,65,66,67,
            68,69,70,71,72,73,74,75,76,77,
            78,79,80,81,82,83,84,85,86,87,
            88,89,90,91,92,93,94,95,96,97,
            98,0,100,101,0,1,2,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            36,37,38,39,40,41,42,43,44,45,
            46,47,48,49,50,51,52,53,54,55,
            56,57,58,59,60,61,62,63,64,65,
            66,67,68,69,70,71,72,73,74,75,
            76,77,78,79,80,81,82,83,84,85,
            86,87,88,89,90,91,92,93,94,95,
            96,97,98,0,0,0,102,0,1,2,
            3,4,5,6,7,8,9,10,11,12,
            13,14,15,16,17,18,19,20,21,22,
            23,24,25,26,27,28,29,30,31,32,
            33,34,35,36,37,38,39,40,41,42,
            43,0,45,46,47,48,49,50,51,52,
            53,54,55,56,57,58,59,60,61,62,
            63,64,65,66,67,68,69,70,71,72,
            73,74,75,76,77,78,79,80,81,82,
            83,84,85,86,87,88,89,90,91,92,
            93,94,95,96,97,0,0,100,101,0,
            1,2,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,19,20,
            21,22,23,24,25,26,27,28,29,30,
            31,32,33,34,35,36,37,38,39,40,
            41,42,43,44,45,46,47,48,49,50,
            51,52,53,54,55,56,57,58,59,60,
            61,62,63,64,65,66,67,68,69,70,
            71,72,73,74,75,76,77,78,79,80,
            81,82,83,84,85,86,87,88,89,90,
            91,92,93,94,95,96,97,98,0,1,
            2,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,27,28,29,30,31,
            32,33,34,35,36,37,38,39,40,41,
            42,43,44,45,46,47,48,49,50,51,
            52,53,54,55,56,57,58,59,60,61,
            62,63,64,65,66,67,68,69,70,71,
            72,73,74,75,76,77,78,79,80,81,
            82,83,84,85,86,87,88,89,90,91,
            92,93,94,95,96,97,98,0,1,2,
            3,4,5,6,7,8,9,10,11,12,
            13,14,15,16,17,18,19,20,21,22,
            23,24,25,26,27,28,29,30,31,32,
            33,34,35,36,37,38,0,40,41,42,
            43,44,45,46,47,48,49,50,51,52,
            53,54,55,56,57,58,59,60,61,62,
            63,64,65,66,67,68,69,70,71,72,
            73,74,75,76,77,78,79,80,81,82,
            83,84,85,86,87,88,89,90,91,92,
            93,94,95,96,97,98,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,0,
            24,25,26,27,28,29,30,31,0,0,
            34,35,36,0,0,99,0,0,42,43,
            11,12,13,14,0,49,50,51,52,53,
            54,55,56,57,58,59,60,61,62,63,
            64,65,66,67,68,69,70,71,72,73,
            74,75,76,77,37,39,32,81,0,1,
            2,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,0,1,2,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,0,1,2,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,0,1,2,
            3,4,5,6,7,8,9,10,11,12,
            13,14,15,16,17,18,19,20,21,22,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,0,1,2,3,4,5,6,
            7,8,9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,0,
            1,2,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,19,20,
            21,22,0,1,2,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,0,1,2,3,4,
            5,6,7,8,9,10,11,12,13,14,
            15,16,17,18,19,20,21,22,0,1,
            2,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,0,1,2,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,0,1,2,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,0,1,2,
            3,4,5,6,7,8,9,10,11,12,
            13,14,15,16,17,18,19,20,21,22,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,0,16,17,0,1,
            2,3,4,5,6,7,8,0,0,11,
            0,0,0,15,0,0,0,37,11,12,
            13,14,24,0,1,2,3,4,5,6,
            7,8,34,35,36,23,38,39,40,23,
            0,0,44,0,32,33,0,1,2,3,
            4,5,6,7,8,0,0,11,0,0,
            0,15,46,0,23,0,11,12,13,14,
            24,16,17,32,0,0,0,0,0,99,
            34,35,36,0,38,39,40,24,25,0,
            44,0,1,2,3,4,5,6,7,8,
            84,23,11,0,0,0,15,24,25,26,
            27,28,29,30,31,24,0,1,2,3,
            4,5,6,7,8,34,35,36,23,38,
            39,40,0,0,0,44,0,1,2,3,
            4,5,6,7,8,9,10,0,1,2,
            3,4,5,6,7,8,9,10,0,1,
            2,3,4,5,6,7,8,9,10,33,
            0,0,0,99,99,0,0,41,0,0,
            33,0,99,0,0,80,0,0,41,0,
            0,0,0,23,23,37,24,25,26,27,
            28,29,30,31,0,24,25,26,27,28,
            29,30,31,23,42,43,0,1,2,3,
            4,5,6,7,8,9,10,0,1,2,
            3,4,5,6,7,8,9,10,0,1,
            2,3,4,5,6,7,8,9,10,0,
            79,0,82,37,0,1,2,3,4,5,
            6,7,8,9,10,0,0,0,0,0,
            0,99,0,1,2,3,4,5,6,7,
            8,9,10,0,1,2,3,4,5,6,
            7,8,9,10,0,1,2,3,4,5,
            6,7,8,9,10,0,1,2,3,4,
            5,6,7,8,9,10,0,1,2,3,
            4,5,6,7,8,0,1,2,3,4,
            5,6,7,8,0,1,2,3,4,5,
            6,7,8,0,1,2,3,4,5,6,
            7,8,0,1,2,3,4,5,6,7,
            8,0,1,2,3,4,5,6,7,8,
            0,1,2,3,4,5,6,7,8,0,
            1,2,3,4,5,6,7,8,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,23,26,27,28,29,30,31,
            23,23,23,33,23,0,24,25,23,32,
            33,24,25,32,23,45,47,48,0,0,
            41,0,45,0,46,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,78,0,0,
            0,0,0,83,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,100,
            101,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            686,780,780,780,780,780,780,780,780,780,
            780,780,780,780,780,780,780,780,780,780,
            780,780,780,780,780,780,780,780,780,780,
            780,780,780,780,780,780,780,780,780,780,
            780,780,780,780,780,780,529,780,780,780,
            780,780,780,780,780,780,780,780,780,780,
            780,780,780,780,780,780,780,780,780,780,
            780,780,780,780,780,780,780,780,780,780,
            780,780,780,780,779,780,780,780,780,780,
            780,780,780,780,780,780,780,780,780,103,
            780,780,686,778,778,778,778,778,778,778,
            778,778,778,778,778,778,778,778,778,778,
            778,778,778,778,778,778,778,778,778,778,
            778,778,778,778,778,778,778,778,778,778,
            778,778,778,778,778,778,778,778,783,778,
            778,778,778,778,778,778,778,778,778,778,
            778,778,778,778,778,778,778,778,778,778,
            778,778,778,778,778,778,778,778,778,778,
            778,778,778,778,778,778,777,778,778,778,
            778,778,778,778,778,778,778,778,778,778,
            778,101,778,778,98,786,786,786,786,786,
            786,786,786,786,786,786,786,786,786,786,
            786,786,786,786,786,786,786,786,786,786,
            786,786,786,786,786,786,786,786,786,786,
            786,786,786,786,786,786,786,786,786,786,
            786,786,786,786,786,786,786,786,786,786,
            786,786,786,786,786,786,786,786,786,786,
            786,786,786,786,786,786,786,786,786,786,
            786,786,786,786,786,786,786,786,786,786,
            786,786,786,786,786,786,786,786,786,786,
            786,786,786,95,686,686,786,686,452,674,
            674,674,674,674,674,674,674,674,673,673,
            673,673,673,673,673,673,673,673,673,673,
            663,673,673,673,673,673,673,673,673,483,
            665,673,673,673,624,491,502,632,666,673,
            673,686,527,664,675,675,673,673,673,673,
            673,673,673,673,673,673,673,673,673,673,
            673,673,673,673,673,673,673,673,673,673,
            673,673,673,673,673,675,567,717,673,549,
            662,667,586,461,729,634,714,726,727,724,
            725,728,712,709,710,686,686,675,675,686,
            790,790,790,790,790,790,790,790,790,790,
            790,790,790,790,790,790,790,790,790,790,
            790,790,790,790,790,790,790,790,790,790,
            790,790,790,790,790,790,790,689,790,790,
            790,790,790,647,790,790,790,790,790,790,
            790,790,790,790,790,790,790,790,790,790,
            790,790,790,790,790,790,790,790,790,790,
            790,790,790,790,790,790,790,790,790,790,
            790,790,790,790,790,790,790,790,790,790,
            790,790,790,790,790,790,790,790,686,788,
            788,788,788,788,788,788,788,788,788,788,
            788,788,788,788,788,788,788,788,788,788,
            788,788,788,788,788,788,788,788,788,788,
            788,788,788,788,788,788,788,788,688,788,
            788,788,630,788,788,788,788,788,788,788,
            788,788,788,788,788,788,788,788,788,788,
            788,788,788,788,788,788,788,788,788,788,
            788,788,788,788,788,788,788,788,788,788,
            788,788,788,788,788,788,788,788,788,788,
            788,788,788,788,788,788,788,686,670,670,
            670,670,670,670,670,670,670,670,670,670,
            670,670,670,670,670,670,670,670,670,670,
            670,670,670,670,670,670,670,670,670,670,
            670,670,670,670,670,670,686,670,670,670,
            670,489,670,670,670,670,670,670,670,670,
            670,670,670,670,670,670,670,670,670,670,
            670,670,670,670,670,670,670,670,670,670,
            670,670,670,670,670,670,670,670,670,670,
            670,670,670,670,670,670,670,670,670,670,
            670,670,670,670,670,670,1,918,918,918,
            918,918,918,918,918,918,918,917,917,917,
            917,917,917,917,917,917,917,917,917,686,
            917,917,917,917,917,917,917,917,686,84,
            917,917,917,686,686,685,686,425,917,917,
            766,773,773,766,27,917,917,917,917,917,
            917,917,917,917,917,917,917,917,917,917,
            917,917,917,917,917,917,917,917,917,917,
            917,917,917,917,752,690,1115,917,686,520,
            520,520,520,520,520,520,520,520,520,520,
            520,520,520,520,520,520,520,520,520,520,
            520,686,571,571,571,571,571,571,571,571,
            571,571,571,571,571,571,571,571,571,571,
            571,571,571,571,68,794,794,794,794,794,
            794,794,794,794,794,794,794,794,794,794,
            794,794,794,794,794,794,794,686,589,589,
            589,589,589,589,589,589,589,589,589,589,
            589,589,589,589,589,589,589,589,589,589,
            686,593,593,593,593,593,593,593,593,593,
            593,593,593,593,593,593,593,593,593,593,
            593,593,593,686,600,600,600,600,600,600,
            600,600,600,600,600,600,600,600,600,600,
            600,600,600,600,600,600,686,604,604,604,
            604,604,604,604,604,604,604,604,604,604,
            604,604,604,604,604,604,604,604,604,686,
            608,608,608,608,608,608,608,608,608,608,
            608,608,608,608,608,608,608,608,608,608,
            608,608,686,612,612,612,612,612,612,612,
            612,612,612,612,612,612,612,612,612,612,
            612,612,612,612,612,686,1090,1090,1090,1090,
            1090,1090,1090,1090,1090,1090,1090,1090,1090,1090,
            1090,1090,1090,1090,1090,1090,1090,1090,686,616,
            616,616,616,616,616,616,616,616,616,616,
            616,616,616,616,616,616,616,616,616,616,
            616,686,620,620,620,620,620,620,620,620,
            620,620,620,620,620,620,620,620,620,620,
            620,620,620,620,686,1081,1081,1081,1081,1081,
            1081,1081,1081,1081,1081,1081,1081,1081,1081,1081,
            1081,1081,1081,1081,1081,1081,1081,686,1099,1099,
            1099,1099,1099,1099,1099,1099,1099,1099,1099,1099,
            1099,1099,1099,1099,1099,1099,1099,1099,1099,1099,
            67,792,792,792,792,792,792,792,792,792,
            792,768,775,775,768,686,636,636,686,676,
            677,678,679,680,681,682,683,85,686,1104,
            686,686,35,1101,686,686,22,628,767,774,
            774,767,444,209,873,874,875,876,877,878,
            879,880,1103,1105,1102,734,1106,1107,1108,742,
            686,25,1109,686,668,1120,686,1082,1082,1082,
            1082,1082,1082,1082,1082,14,686,1104,686,686,
            686,1101,460,71,732,686,765,772,772,765,
            575,626,626,1113,230,228,686,686,30,5,
            1103,1105,1102,105,1106,1107,1108,764,764,686,
            1109,686,676,677,678,679,680,681,682,683,
            785,746,1104,686,686,37,1101,67,67,67,
            67,67,67,67,67,579,210,873,874,875,
            876,877,878,879,880,1103,1105,1102,735,1106,
            1107,1108,686,686,686,1109,90,583,583,583,
            583,583,583,583,583,583,583,686,583,583,
            583,583,583,583,583,583,583,583,36,456,
            456,456,456,456,456,456,456,456,456,639,
            33,32,105,1,18,686,686,641,686,686,
            639,5,5,686,686,1117,686,686,641,686,
            29,686,686,743,744,671,67,67,67,67,
            67,67,67,67,686,537,537,645,531,644,
            645,531,644,745,557,557,110,651,651,651,
            651,651,651,651,651,651,651,109,792,792,
            792,792,792,792,792,792,792,792,686,658,
            658,658,658,658,658,658,658,658,658,686,
            750,686,751,1121,686,660,660,660,660,660,
            660,660,660,660,660,686,686,686,686,686,
            686,5,112,792,792,792,792,792,792,792,
            792,792,792,111,792,792,792,792,792,792,
            792,792,792,792,114,792,792,792,792,792,
            792,792,792,792,792,113,792,792,792,792,
            792,792,792,792,792,792,209,187,187,187,
            187,187,187,187,187,209,188,188,188,188,
            188,188,188,188,209,189,189,189,189,189,
            189,189,189,209,190,190,190,190,190,190,
            190,190,209,191,191,191,191,191,191,191,
            191,209,192,192,192,192,192,192,192,192,
            209,193,193,193,193,193,193,193,193,209,
            194,194,194,194,194,194,194,194,72,18,
            34,686,686,686,686,686,686,20,21,19,
            686,51,70,686,686,50,686,69,686,686,
            686,52,686,733,759,763,761,759,763,761,
            740,741,739,1118,748,686,762,762,747,1112,
            731,760,760,672,749,669,915,915,686,686,
            730,686,1119,686,1116,686,686,686,686,686,
            686,686,686,686,686,686,686,686,686,686,
            686,686,686,686,686,686,686,915,686,686,
            686,686,686,1114,686,686,686,686,686,686,
            686,686,686,686,686,686,686,686,686,915,
            915
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }
    public final int asb(int index) { return 0; }
    public final int asr(int index) { return 0; }
    public final int nasb(int index) { return 0; }
    public final int nasr(int index) { return 0; }
    public final int terminalIndex(int index) { return 0; }
    public final int nonterminalIndex(int index) { return 0; }
    public final int scopePrefix(int index) { return 0;}
    public final int scopeSuffix(int index) { return 0;}
    public final int scopeLhs(int index) { return 0;}
    public final int scopeLa(int index) { return 0;}
    public final int scopeStateSet(int index) { return 0;}
    public final int scopeRhs(int index) { return 0;}
    public final int scopeState(int index) { return 0;}
    public final int inSymb(int index) { return 0;}
    public final String name(int index) { return null; }
    public final int originalState(int state) { return 0; }
    public final int asi(int state) { return 0; }
    public final int nasi(int state) { return 0; }
    public final int inSymbol(int state) { return 0; }

    /**
     * assert(! goto_default);
     */
    public final int ntAction(int state, int sym) {
        return baseAction[state + sym];
    }

    /**
     * assert(! shift_default);
     */
    public final int tAction(int state, int sym) {
        int i = baseAction[state],
            k = i + sym;
        return termAction[termCheck[k] == sym ? k : i];
    }
    public final int lookAhead(int la_state, int sym) {
        int k = la_state + sym;
        return termAction[termCheck[k] == sym ? k : la_state];
    }
}
