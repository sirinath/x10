
//
// Licensed Material 
// (C) Copyright IBM Corp, 2006
//

package x10.parser;

public class X10Lexerprs implements lpg.lpgjavaruntime.ParseTable, X10Lexersym {

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
            1,0,0,0,0,0,1,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            25,26,33,29,30,31,14,19,21,28,
            32,20,22,27,36,40,2,3,4,5,
            6,7,8,9,10,11,12,13,15,16,
            17,18,23,24,34,35,37,38,1,39
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
            1,3,3,1,1,1,1,5,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,2,2,2,2,2,
            2,2,2,2,2,2,2,2,2,3,
            2,2,3,1,3,2,2,3,3,2,
            1,2,2,2,3,3,2,3,2,2,
            0,1,2,2,2,0,2,1,2,1,
            2,2,2,3,2,3,3,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,2,3,1,1,1,1,1,1,
            1,1,1,1,1,2,1,2,2,1,
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
            1,1,1,6,2,1,1,1,1,1,
            1,1,6,2,2,2,2,2,2,2,
            2,2,2
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            17,17,17,17,17,17,17,17,17,17,
            17,17,17,17,17,17,17,17,17,17,
            17,17,17,17,17,17,17,17,17,17,
            17,17,17,17,17,17,17,17,17,17,
            17,17,17,17,17,17,17,17,17,17,
            17,17,17,17,21,21,22,23,23,23,
            23,24,24,24,24,24,24,24,25,25,
            25,25,26,26,27,27,19,19,7,7,
            30,30,32,32,32,12,12,12,10,10,
            10,10,10,4,4,4,4,4,5,5,
            5,5,5,5,5,5,5,5,5,5,
            5,5,5,5,5,5,5,5,5,5,
            5,5,5,5,6,6,6,6,6,6,
            6,6,6,6,6,6,6,6,6,6,
            6,6,6,6,6,6,6,6,6,6,
            1,1,1,1,1,1,1,1,1,1,
            11,11,11,11,11,11,11,11,3,3,
            3,3,3,3,3,3,3,3,3,3,
            2,2,35,35,35,8,8,9,9,31,
            31,14,14,29,29,28,28,18,18,18,
            36,36,36,36,36,36,36,36,36,36,
            36,36,36,36,36,36,36,36,36,36,
            36,36,36,36,36,36,36,36,36,15,
            15,15,15,15,15,15,15,15,15,15,
            15,15,15,15,15,15,15,15,15,15,
            15,15,15,15,15,15,15,37,37,37,
            37,37,37,37,37,37,37,37,37,37,
            37,37,37,37,37,37,37,37,37,37,
            37,37,37,37,37,38,38,38,38,38,
            38,38,38,38,38,38,38,38,38,38,
            38,38,38,38,38,38,38,38,38,38,
            38,38,38,13,13,13,13,40,40,33,
            33,33,33,33,33,33,33,34,34,34,
            34,34,34,34,34,34,20,20,20,20,
            20,20,20,20,20,16,16,16,16,16,
            16,16,16,17,408,533,989,607,532,532,
            532,444,610,199,534,634,198,198,198,98,
            992,357,362,58,65,356,5,6,7,1,
            70,437,425,70,70,70,451,56,402,70,
            1035,471,70,944,70,510,530,471,1165,530,
            530,530,308,77,464,416,77,77,77,200,
            1166,530,1024,514,1171,530,103,68,77,514,
            68,68,68,1078,522,1164,68,446,344,68,
            522,68,1168,530,379,196,77,205,75,77,
            998,75,75,75,926,79,686,477,477,477,
            1004,953,60,67,1089,524,509,1170,412,57,
            64,524,1174,365,1046,412,710,481,481,481,
            75,1067,79,75,1167,516,477,734,81,81,
            81,758,489,489,489,782,493,493,493,806,
            497,497,497,830,501,501,501,854,343,343,
            343,878,505,505,505,902,334,334,334,988,
            979,958,1100,528,1111,79,1155,59,66,528,
            977,335,1122,79,1133,79,1175,184,1144,79,
            728,960,920,1169,1172,776,824,872,996,1176,
            1180,1181,1182,545,545
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
            90,91,92,93,94,95,96,0,98,99,
            100,101,0,1,2,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,29,30,31,32,33,34,35,36,37,
            38,39,40,41,42,43,44,45,46,47,
            48,49,50,51,52,53,54,55,56,57,
            58,59,60,61,62,63,64,65,66,67,
            68,69,70,71,72,73,74,75,76,77,
            78,79,80,81,82,83,84,85,86,87,
            88,89,90,91,92,93,94,95,96,0,
            98,99,100,101,0,1,2,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            36,37,38,39,40,41,42,43,44,45,
            46,47,48,49,50,51,52,53,54,55,
            56,57,58,59,60,61,62,63,64,65,
            66,67,68,69,70,71,72,73,74,75,
            76,77,78,79,80,81,82,83,84,85,
            86,87,88,89,90,91,92,93,94,95,
            96,0,98,99,0,0,102,0,1,2,
            3,4,5,6,7,8,9,10,11,12,
            13,14,15,16,17,18,19,20,21,22,
            23,24,25,26,27,28,29,30,31,32,
            33,34,35,36,37,38,39,40,41,42,
            43,44,45,46,47,48,49,50,51,52,
            53,54,55,56,57,58,59,60,61,62,
            63,64,65,66,67,68,69,70,71,72,
            73,74,75,76,77,78,79,80,81,82,
            83,84,85,86,87,88,89,90,91,92,
            93,94,95,96,0,98,99,0,1,2,
            3,4,5,6,7,8,9,10,11,12,
            13,14,15,16,17,18,19,20,21,22,
            23,24,25,26,27,28,29,30,31,32,
            33,34,35,36,37,38,39,40,41,42,
            43,44,45,46,47,48,49,50,51,52,
            53,54,55,56,57,58,59,60,61,62,
            63,64,65,66,67,68,69,70,71,72,
            73,74,75,76,0,78,79,80,81,82,
            83,84,85,86,87,88,89,90,91,92,
            93,94,95,96,0,0,0,100,101,0,
            1,2,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,19,20,
            21,22,23,0,25,26,27,28,29,30,
            31,32,33,34,35,36,37,38,39,40,
            41,42,43,44,45,46,47,48,49,50,
            51,52,53,54,55,56,57,58,59,60,
            61,62,63,64,65,66,67,68,69,70,
            71,72,73,74,75,76,77,78,79,80,
            81,82,83,84,85,86,87,88,89,90,
            91,92,93,94,95,96,0,98,99,0,
            1,2,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,0,19,20,
            21,22,23,0,25,26,27,28,29,30,
            0,32,33,0,11,12,13,14,39,40,
            41,42,43,44,45,46,47,48,49,50,
            51,52,53,54,55,56,57,58,59,60,
            61,62,63,64,65,66,67,68,69,70,
            71,72,0,0,75,0,1,2,3,4,
            5,6,7,8,9,10,11,12,13,14,
            15,16,17,97,19,20,21,22,23,0,
            1,2,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,0,19,20,
            21,22,23,0,1,2,3,4,5,6,
            7,8,9,10,11,12,13,14,15,16,
            17,24,19,20,21,22,23,0,1,2,
            3,4,5,6,7,8,9,10,11,12,
            13,14,15,16,17,0,19,20,21,22,
            23,0,1,2,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,17,24,
            19,20,21,22,23,0,1,2,3,4,
            5,6,7,8,9,10,11,12,13,14,
            15,16,17,0,19,20,21,22,23,0,
            1,2,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,24,19,20,
            21,22,23,0,1,2,3,4,5,6,
            7,8,9,10,11,12,13,14,15,16,
            17,0,19,20,21,22,23,0,1,2,
            3,4,5,6,7,8,9,10,11,12,
            13,14,15,16,17,24,19,20,21,22,
            23,0,1,2,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,17,0,
            19,20,21,22,23,0,1,2,3,4,
            5,6,7,8,9,10,11,12,13,14,
            0,16,17,0,1,2,3,4,5,6,
            7,8,0,0,11,0,31,0,15,0,
            0,0,0,11,12,13,14,24,16,17,
            27,28,29,30,0,18,0,34,0,1,
            2,3,4,5,6,7,8,0,0,11,
            31,0,35,15,18,0,0,0,11,12,
            13,14,24,0,0,27,28,29,30,18,
            0,0,34,25,26,18,97,0,0,24,
            77,18,97,0,1,2,3,4,5,6,
            7,8,9,10,0,1,2,3,4,5,
            6,7,8,9,10,0,1,2,3,4,
            5,6,7,8,0,77,80,81,35,36,
            0,0,0,0,0,31,0,1,2,3,
            4,5,6,7,8,9,10,0,1,2,
            3,4,5,6,7,8,9,10,0,1,
            2,3,4,5,6,7,8,9,10,0,
            1,2,3,4,5,6,7,8,9,10,
            0,1,2,3,4,5,6,7,8,9,
            10,0,1,2,3,4,5,6,7,8,
            9,10,0,1,2,3,4,5,6,7,
            8,9,10,0,1,2,3,4,5,6,
            7,8,9,10,0,1,2,3,4,5,
            6,7,8,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,18,18,18,0,18,18,25,
            26,18,18,25,26,0,32,33,0,24,
            37,38,36,24,24,24,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,73,0,0,0,
            0,74,0,78,0,79,76,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,97,0,100,101,97,0,0,97,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            545,615,615,615,615,615,615,615,615,615,
            615,615,615,615,615,615,615,615,615,615,
            615,615,615,615,615,615,615,615,615,615,
            615,615,615,615,615,615,615,615,615,615,
            615,615,615,615,615,615,615,615,615,615,
            615,615,615,615,615,615,615,615,615,615,
            615,615,615,615,615,615,615,615,615,615,
            615,615,615,615,615,615,615,615,615,615,
            614,416,615,615,615,615,615,615,615,615,
            615,615,615,615,615,615,615,76,615,615,
            615,615,545,613,613,613,613,613,613,613,
            613,613,613,613,613,613,613,613,613,613,
            613,613,613,613,613,613,613,613,613,613,
            613,613,613,613,613,613,613,613,613,613,
            613,613,613,613,613,613,613,613,613,613,
            613,613,613,613,613,613,613,613,613,613,
            613,613,613,613,613,613,613,613,613,613,
            613,613,613,613,613,613,613,613,613,613,
            613,613,553,618,613,613,613,613,613,613,
            613,613,613,613,613,613,613,613,613,71,
            613,613,613,613,9,620,620,620,620,620,
            620,620,620,620,620,620,620,620,620,620,
            620,620,620,620,620,620,620,620,620,620,
            620,620,620,620,620,620,620,620,620,620,
            620,620,620,620,620,620,620,620,620,620,
            620,620,620,620,620,620,620,620,620,620,
            620,620,620,620,620,620,620,620,620,620,
            620,620,620,620,620,620,620,620,620,620,
            620,620,620,620,620,620,620,620,620,620,
            620,620,620,620,620,620,620,620,620,620,
            620,545,620,620,545,545,620,545,622,622,
            622,622,622,622,622,622,622,622,622,622,
            622,622,622,622,622,622,622,622,622,622,
            622,622,622,622,622,622,622,622,622,622,
            622,547,622,622,622,622,622,622,622,622,
            622,622,622,622,622,622,622,622,622,622,
            622,622,622,622,622,622,622,622,622,622,
            622,622,622,622,622,622,622,622,622,622,
            622,622,622,622,510,622,622,622,622,622,
            622,622,622,622,622,622,622,622,622,622,
            622,622,622,622,545,622,622,545,398,533,
            533,533,533,533,533,533,533,533,532,532,
            532,532,532,532,532,457,532,532,532,532,
            532,395,532,532,532,532,532,532,390,532,
            532,369,511,474,534,534,532,532,532,532,
            532,532,532,532,532,532,532,532,532,532,
            532,532,532,532,532,532,532,532,532,532,
            532,532,532,532,532,532,532,532,532,532,
            534,432,532,414,545,410,572,520,462,440,
            370,580,568,450,564,565,577,578,575,576,
            579,563,560,561,545,545,545,534,534,545,
            530,530,530,530,530,530,530,530,530,530,
            530,530,530,530,530,530,530,530,530,530,
            530,530,530,545,530,530,530,530,530,530,
            530,530,530,530,530,530,530,530,530,530,
            530,530,530,530,530,530,530,530,530,530,
            530,530,530,530,530,530,530,530,530,530,
            530,530,530,530,530,530,530,530,530,530,
            530,530,530,530,530,530,393,530,530,530,
            530,530,530,530,530,530,530,530,530,530,
            530,530,530,530,530,530,545,530,530,1,
            744,744,744,744,744,744,744,744,744,744,
            743,743,743,743,743,743,743,545,743,743,
            743,743,743,62,743,743,743,743,743,743,
            545,743,743,545,603,610,610,603,743,743,
            743,743,743,743,743,743,743,743,743,743,
            743,743,743,743,743,743,743,743,743,743,
            743,743,743,743,743,743,743,743,743,743,
            743,743,545,545,743,545,477,477,477,477,
            477,477,477,477,477,477,477,477,477,477,
            477,477,477,544,477,477,477,477,477,545,
            481,481,481,481,481,481,481,481,481,481,
            481,481,481,481,481,481,481,545,481,481,
            481,481,481,55,626,626,626,626,626,626,
            626,626,626,626,626,626,626,626,626,626,
            626,548,626,626,626,626,626,545,489,489,
            489,489,489,489,489,489,489,489,489,489,
            489,489,489,489,489,160,489,489,489,489,
            489,545,493,493,493,493,493,493,493,493,
            493,493,493,493,493,493,493,493,493,182,
            493,493,493,493,493,545,497,497,497,497,
            497,497,497,497,497,497,497,497,497,497,
            497,497,497,161,497,497,497,497,497,545,
            501,501,501,501,501,501,501,501,501,501,
            501,501,501,501,501,501,501,182,501,501,
            501,501,501,545,888,888,888,888,888,888,
            888,888,888,888,888,888,888,888,888,888,
            888,162,888,888,888,888,888,545,505,505,
            505,505,505,505,505,505,505,505,505,505,
            505,505,505,505,505,182,505,505,505,505,
            505,545,879,879,879,879,879,879,879,879,
            879,879,879,879,879,879,879,879,879,197,
            879,879,879,879,879,54,624,624,624,624,
            624,624,624,624,624,624,605,612,612,605,
            545,412,412,545,535,536,537,538,539,540,
            541,542,7,545,893,545,423,11,890,545,
            545,545,545,602,609,609,602,896,412,412,
            892,894,891,466,545,587,14,895,545,880,
            880,880,880,880,880,880,880,63,4,893,
            598,29,581,890,590,163,545,22,604,611,
            611,604,896,21,545,892,894,891,485,585,
            545,545,895,601,601,594,1,545,545,182,
            897,593,4,545,514,514,514,514,514,514,
            514,514,514,514,28,471,471,471,471,471,
            471,471,471,471,471,182,516,516,516,516,
            516,516,516,516,545,897,619,409,512,454,
            545,545,545,545,545,531,82,624,624,624,
            624,624,624,624,624,624,624,83,522,522,
            522,522,522,522,522,522,522,522,545,524,
            524,524,524,524,524,524,524,524,524,545,
            528,528,528,528,528,528,528,528,528,528,
            85,624,624,624,624,624,624,624,624,624,
            624,84,624,624,624,624,624,624,624,624,
            624,624,87,624,624,624,624,624,624,624,
            624,624,624,86,624,624,624,624,624,624,
            624,624,624,624,183,729,729,729,729,729,
            729,729,729,10,78,26,12,24,78,17,
            25,195,545,13,41,164,545,545,545,165,
            166,167,545,584,588,592,545,583,591,54,
            54,589,595,54,54,545,446,446,545,182,
            741,741,582,182,182,182,545,545,545,545,
            545,545,545,545,545,545,545,545,545,545,
            545,545,545,545,545,545,545,545,545,545,
            545,545,545,545,545,545,741,545,545,545,
            545,596,545,526,545,898,597,545,545,545,
            545,545,545,545,545,545,545,545,545,545,
            545,4,545,741,741,4,545,545,10
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
    public final int getErrorSymbol() { return 0; }
    public final int getScopeUbound() { return 0; }
    public final int getScopeSize() { return 0; }
    public final int getMaxNameLength() { return 0; }

    public final static int
           NUM_STATES        = 66,
           NT_OFFSET         = 102,
           LA_STATE_OFFSET   = 898,
           MAX_LA            = 1,
           NUM_RULES         = 353,
           NUM_NONTERMINALS  = 40,
           NUM_SYMBOLS       = 142,
           SEGMENT_SIZE      = 8192,
           START_STATE       = 354,
           IDENTIFIER_SYMBOL = 0,
           EOFT_SYMBOL       = 97,
           EOLT_SYMBOL       = 103,
           ACCEPT_ACTION     = 544,
           ERROR_ACTION      = 545;

    public final static boolean BACKTRACK = false;

    public final int getNumStates() { return NUM_STATES; }
    public final int getNtOffset() { return NT_OFFSET; }
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }
    public final int getMaxLa() { return MAX_LA; }
    public final int getNumRules() { return NUM_RULES; }
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }
    public final int getNumSymbols() { return NUM_SYMBOLS; }
    public final int getSegmentSize() { return SEGMENT_SIZE; }
    public final int getStartState() { return START_STATE; }
    public final int getStartSymbol() { return lhs[0]; }
    public final int getIdentifierSymbol() { return IDENTIFIER_SYMBOL; }
    public final int getEoftSymbol() { return EOFT_SYMBOL; }
    public final int getEoltSymbol() { return EOLT_SYMBOL; }
    public final int getAcceptAction() { return ACCEPT_ACTION; }
    public final int getErrorAction() { return ERROR_ACTION; }
    public final boolean isValidForParser() { return isValidForParser; }
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int originalState(int state) { return 0; }
    public final int asi(int state) { return 0; }
    public final int nasi(int state) { return 0; }
    public final int inSymbol(int state) { return 0; }

    public final int ntAction(int state, int sym) {
        return baseAction[state + sym];
    }

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
