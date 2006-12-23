
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
            0,0,0,1,0,0,0,0,0,0,
            0,0,0,0,1,0,0,0,0,0,
            0,0,0,0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            27,28,35,31,32,33,13,30,18,20,
            34,10,11,19,23,24,29,38,42,2,
            3,4,5,6,7,8,9,12,14,15,
            16,17,21,22,25,26,36,37,39,40,
            41,43,1
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
            1,3,3,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,2,2,2,2,2,
            2,2,2,2,2,2,2,2,2,3,
            2,2,3,1,3,2,2,3,3,2,
            1,2,2,2,3,3,2,5,3,2,
            2,0,1,2,1,2,2,0,2,1,
            2,1,2,2,2,3,2,3,3,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,2,3,1,1,1,1,
            1,1,1,1,1,1,1,2,1,2,
            2,1,1,1,1,1,1,1,1,1,
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
            1,1,1,1,1,6,2,1,1,1,
            1,1,1,1,6,2,2,2,2,2,
            2,2,2,2,2,9,2,2,3,2
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            20,20,20,20,20,20,20,20,20,20,
            20,20,20,20,20,20,20,20,20,20,
            20,20,20,20,20,20,20,20,20,20,
            20,20,20,20,20,20,20,20,20,20,
            20,20,20,20,20,20,20,20,20,20,
            20,20,20,20,24,24,25,26,26,26,
            26,27,27,27,27,27,27,27,12,33,
            33,33,33,34,34,13,16,16,22,22,
            7,7,30,30,32,32,32,14,14,14,
            8,8,8,8,8,4,4,4,4,4,
            5,5,5,5,5,5,5,5,5,5,
            5,5,5,5,5,5,5,5,5,5,
            5,5,5,5,5,5,6,6,6,6,
            6,6,6,6,6,6,6,6,6,6,
            6,6,6,6,6,6,6,6,6,6,
            6,6,1,1,1,1,1,1,1,1,
            1,1,11,11,11,11,11,11,11,11,
            3,3,3,3,3,3,3,3,3,3,
            3,3,2,2,37,37,37,9,9,10,
            10,31,31,17,17,29,29,28,28,21,
            21,21,38,38,38,38,38,38,38,38,
            38,38,38,38,38,38,38,38,38,38,
            38,38,38,38,38,38,38,38,38,38,
            38,18,18,18,18,18,18,18,18,18,
            18,18,18,18,18,18,18,18,18,18,
            18,18,18,18,18,18,18,18,18,39,
            39,39,39,39,39,39,39,39,39,39,
            39,39,39,39,39,39,39,39,39,39,
            39,39,39,39,39,39,39,40,40,40,
            40,40,40,40,40,40,40,40,40,40,
            40,40,40,40,40,40,40,40,40,40,
            40,40,40,40,40,15,15,15,15,41,
            41,35,35,35,35,35,35,35,35,36,
            36,36,36,36,36,36,36,36,23,23,
            23,23,23,23,23,23,23,19,19,19,
            19,19,19,19,19,20,20,42,42,42,
            42,410,558,1024,202,557,557,557,447,559,
            100,360,950,8,9,358,380,438,879,438,
            1028,378,462,420,1199,384,5,6,7,516,
            1,71,413,441,71,71,71,390,71,715,
            477,477,477,363,533,71,509,547,71,346,
            547,547,547,308,79,56,1193,79,79,79,
            1174,359,103,69,422,547,69,69,69,547,
            69,530,79,739,83,83,83,69,205,77,
            69,1192,77,77,77,469,547,931,81,79,
            57,64,79,1078,497,465,60,67,508,1196,
            497,512,611,201,508,640,200,200,200,691,
            433,433,433,77,58,65,77,763,485,485,
            485,787,489,489,489,811,493,493,493,835,
            500,500,500,859,345,345,345,1089,81,433,
            883,504,504,504,907,336,336,336,1005,526,
            991,528,959,1100,531,526,988,528,1119,535,
            531,59,66,1037,198,535,1130,81,1141,81,
            1183,1152,81,949,337,1163,81,982,635,960,
            1056,186,1202,1194,1197,946,1210,711,985,1200,
            927,962,968,1198,1025,1034,1207,1112,1195,1113,
            759,807,979,1027,1050,1213,1215,1226,570,570
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
            36,37,38,39,40,41,42,43,0,0,
            46,47,48,49,50,51,52,53,54,55,
            56,57,58,59,60,61,62,63,64,65,
            66,67,68,69,70,71,72,73,74,75,
            76,77,78,79,80,81,82,83,84,85,
            86,87,88,89,90,91,92,93,94,95,
            96,97,98,0,100,101,102,0,1,2,
            3,4,5,6,7,8,9,10,11,12,
            13,14,15,16,17,18,19,20,21,22,
            23,24,25,26,27,28,29,30,31,32,
            33,34,35,36,37,38,39,40,41,42,
            43,0,0,46,47,48,49,50,51,52,
            53,54,55,56,57,58,59,60,61,62,
            63,64,65,66,67,68,69,70,71,72,
            73,74,75,76,77,78,79,80,81,82,
            83,84,85,86,87,88,89,90,91,92,
            93,94,95,96,97,98,0,100,101,0,
            1,2,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,19,20,
            21,22,23,24,25,26,27,28,29,30,
            31,32,33,34,35,36,37,38,39,40,
            41,42,43,44,45,46,47,48,49,50,
            51,52,53,54,55,56,57,58,59,60,
            61,62,63,64,65,66,67,68,69,70,
            71,72,73,74,75,76,77,78,79,80,
            0,82,83,84,85,86,87,88,89,90,
            91,92,93,94,95,96,97,98,0,1,
            2,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,0,25,26,27,28,29,30,31,
            32,33,34,35,36,37,38,39,40,41,
            42,43,20,0,46,47,48,49,50,51,
            52,53,54,55,56,57,58,59,60,61,
            62,63,64,65,66,67,68,69,70,71,
            72,73,74,75,76,77,78,79,80,81,
            82,83,84,85,86,87,88,89,90,91,
            92,93,94,95,96,97,98,0,100,101,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            0,21,22,23,0,25,26,27,28,0,
            0,0,32,33,34,0,0,37,38,39,
            11,12,13,14,20,0,46,47,48,49,
            50,51,52,53,54,55,56,57,58,59,
            60,61,62,63,64,65,66,67,68,69,
            70,71,72,73,74,75,76,77,0,79,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            0,21,22,23,0,1,2,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,24,21,22,23,0,1,
            2,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,0,21,
            22,23,0,1,2,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,24,21,22,23,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,0,21,22,23,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            24,21,22,23,0,1,2,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,0,21,22,23,0,1,
            2,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,0,21,
            22,23,0,1,2,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,0,21,22,23,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,0,21,22,23,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,0,16,17,0,0,
            1,2,3,4,5,6,7,8,0,0,
            11,0,36,0,15,20,36,0,20,11,
            12,13,14,24,29,26,27,99,0,20,
            19,0,33,34,0,18,0,0,43,40,
            0,1,2,3,4,5,6,7,8,9,
            10,20,24,0,0,1,2,3,4,5,
            6,7,8,9,10,25,32,30,31,29,
            30,31,35,0,0,35,0,0,80,99,
            81,44,45,0,44,45,0,1,2,3,
            4,5,6,7,8,41,42,11,25,0,
            24,15,29,30,31,0,0,0,35,78,
            24,37,26,27,0,32,0,44,45,33,
            34,44,45,24,0,20,40,0,1,2,
            3,4,5,6,7,8,9,10,0,1,
            2,3,4,5,6,7,8,9,10,0,
            1,2,3,4,5,6,7,8,9,10,
            0,0,0,36,0,0,0,81,0,1,
            2,3,4,5,6,7,8,9,10,0,
            1,2,3,4,5,6,7,8,9,10,
            0,1,2,3,4,5,6,7,8,9,
            10,0,1,2,3,4,5,6,7,8,
            9,10,0,1,2,3,4,5,6,7,
            8,9,10,0,1,2,3,4,5,6,
            7,8,0,1,2,3,4,5,6,7,
            8,0,0,0,0,0,0,0,0,0,
            0,0,11,12,13,14,0,16,17,0,
            99,99,0,20,0,20,20,25,0,25,
            28,20,28,25,27,0,28,0,29,20,
            38,39,26,0,0,42,24,41,24,0,
            0,0,43,0,0,0,0,0,0,24,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,83,82,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,99,0,99,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            570,641,641,641,641,641,641,641,641,641,
            641,641,641,641,641,641,641,641,641,641,
            641,641,641,641,641,641,641,641,641,640,
            641,641,641,641,641,641,641,641,641,641,
            641,641,641,422,641,641,641,641,641,641,
            641,641,641,641,641,641,641,641,641,641,
            641,641,641,641,641,641,641,641,641,641,
            641,641,641,641,641,641,641,641,641,641,
            641,641,641,641,641,641,641,641,641,641,
            641,641,641,641,641,641,641,641,641,78,
            641,641,570,639,639,639,639,639,639,639,
            639,639,639,639,639,639,639,639,639,639,
            639,639,639,639,639,639,639,639,639,639,
            639,638,639,639,639,639,639,639,639,639,
            639,639,639,639,639,644,639,639,639,639,
            639,639,639,639,639,639,639,639,639,639,
            639,639,639,639,639,639,639,639,639,639,
            639,639,639,639,639,639,639,639,639,639,
            639,639,639,639,639,639,639,639,639,639,
            639,639,639,639,639,639,639,639,639,639,
            639,72,639,639,75,647,647,647,647,647,
            647,647,647,647,647,647,647,647,647,647,
            647,647,647,647,647,647,647,647,647,647,
            647,647,647,647,647,647,647,647,647,647,
            647,647,647,647,647,647,647,647,570,570,
            647,647,647,647,647,647,647,647,647,647,
            647,647,647,647,647,647,647,647,647,647,
            647,647,647,647,647,647,647,647,647,647,
            647,647,647,647,647,647,647,647,647,647,
            647,647,647,647,647,647,647,647,647,647,
            647,647,647,570,647,647,647,570,649,649,
            649,649,649,649,649,649,649,649,649,649,
            649,649,649,649,649,649,649,649,649,649,
            649,649,649,649,649,649,649,649,649,649,
            649,649,649,649,649,649,649,572,649,649,
            649,570,570,649,649,649,649,649,649,649,
            649,649,649,649,649,649,649,649,649,649,
            649,649,649,649,649,649,649,649,649,649,
            649,649,649,649,649,649,649,649,523,649,
            649,649,649,649,649,649,649,649,649,649,
            649,649,649,649,649,649,570,649,649,570,
            416,558,558,558,558,558,558,558,558,558,
            557,557,557,557,557,557,557,557,557,540,
            557,557,557,406,557,557,557,557,545,559,
            559,557,557,557,559,453,557,557,557,370,
            544,543,542,559,559,557,557,557,557,557,
            557,557,557,557,557,557,557,557,557,557,
            557,557,557,557,557,557,557,557,557,557,
            557,557,557,557,557,557,557,537,557,533,
            570,459,597,538,404,605,593,539,589,590,
            602,603,600,601,604,588,585,586,570,547,
            547,547,547,547,547,547,547,547,547,547,
            547,547,547,547,547,547,547,547,547,547,
            547,547,29,547,547,547,547,547,547,547,
            547,547,547,547,547,547,547,547,547,547,
            547,547,610,570,547,547,547,547,547,547,
            547,547,547,547,547,547,547,547,547,547,
            547,547,547,547,547,547,547,547,547,547,
            547,547,547,547,547,547,547,547,547,372,
            547,547,547,547,547,547,547,547,547,547,
            547,547,547,547,547,547,547,570,547,547,
            1,771,771,771,771,771,771,771,771,771,
            771,770,770,770,770,770,770,770,770,770,
            570,770,770,770,22,770,770,770,770,62,
            570,570,770,770,770,570,570,770,770,770,
            628,635,635,628,619,570,770,770,770,770,
            770,770,770,770,770,770,770,770,770,770,
            770,770,770,770,770,770,770,770,770,770,
            770,770,770,770,770,770,770,770,570,770,
            570,433,433,433,433,433,433,433,433,433,
            433,433,433,433,433,433,433,433,433,433,
            570,433,433,433,570,477,477,477,477,477,
            477,477,477,477,477,477,477,477,477,477,
            477,477,477,477,573,477,477,477,55,653,
            653,653,653,653,653,653,653,653,653,653,
            653,653,653,653,653,653,653,653,162,653,
            653,653,570,485,485,485,485,485,485,485,
            485,485,485,485,485,485,485,485,485,485,
            485,485,184,485,485,485,570,489,489,489,
            489,489,489,489,489,489,489,489,489,489,
            489,489,489,489,489,489,163,489,489,489,
            570,493,493,493,493,493,493,493,493,493,
            493,493,493,493,493,493,493,493,493,493,
            184,493,493,493,570,500,500,500,500,500,
            500,500,500,500,500,500,500,500,500,500,
            500,500,500,500,570,500,500,500,570,915,
            915,915,915,915,915,915,915,915,915,915,
            915,915,915,915,915,915,915,915,570,915,
            915,915,570,504,504,504,504,504,504,504,
            504,504,504,504,504,504,504,504,504,504,
            504,504,570,504,504,504,570,906,906,906,
            906,906,906,906,906,906,906,906,906,906,
            906,906,906,906,906,906,570,906,906,906,
            54,651,651,651,651,651,651,651,651,651,
            651,630,637,637,630,14,508,508,25,570,
            560,561,562,563,564,565,566,567,63,21,
            920,570,623,570,917,615,510,570,616,629,
            636,636,629,923,646,919,918,569,164,618,
            552,24,921,399,570,553,570,10,364,922,
            85,528,528,528,528,528,528,528,528,528,
            528,617,184,570,570,526,526,526,526,526,
            526,526,526,526,526,357,551,768,768,357,
            357,357,768,570,570,357,165,570,622,4,
            924,768,768,570,357,357,570,907,907,907,
            907,907,907,907,907,518,513,920,548,166,
            184,917,549,930,930,17,570,570,930,621,
            923,555,919,918,570,556,570,930,930,921,
            481,929,929,184,570,608,922,28,497,497,
            497,497,497,497,497,497,497,497,84,651,
            651,651,651,651,651,651,651,651,651,570,
            531,531,531,531,531,531,531,531,531,531,
            570,199,197,550,570,570,570,924,570,535,
            535,535,535,535,535,535,535,535,535,87,
            651,651,651,651,651,651,651,651,651,651,
            86,651,651,651,651,651,651,651,651,651,
            651,89,651,651,651,651,651,651,651,651,
            651,651,88,651,651,651,651,651,651,651,
            651,651,651,184,530,530,530,530,530,530,
            530,530,185,756,756,756,756,756,756,756,
            756,7,80,12,80,26,11,570,4,570,
            570,13,627,634,634,627,570,508,508,41,
            1,10,167,613,168,609,612,54,570,54,
            54,614,54,626,554,169,626,570,646,620,
            469,469,926,570,570,607,184,606,184,570,
            570,570,364,570,570,570,570,570,570,184,
            570,570,570,570,570,570,570,570,570,570,
            570,570,570,570,570,570,570,570,570,570,
            570,570,570,570,570,570,925,546,570,570,
            570,570,570,570,570,570,570,570,570,570,
            570,4,570,4
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
           NUM_STATES        = 76,
           NT_OFFSET         = 102,
           LA_STATE_OFFSET   = 930,
           MAX_LA            = 1,
           NUM_RULES         = 360,
           NUM_NONTERMINALS  = 43,
           NUM_SYMBOLS       = 145,
           SEGMENT_SIZE      = 8192,
           START_STATE       = 361,
           IDENTIFIER_SYMBOL = 0,
           EOFT_SYMBOL       = 99,
           EOLT_SYMBOL       = 103,
           ACCEPT_ACTION     = 569,
           ERROR_ACTION      = 570;

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
