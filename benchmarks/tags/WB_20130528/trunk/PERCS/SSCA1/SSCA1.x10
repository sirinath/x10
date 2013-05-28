import x10.compiler.Inline;
import x10.compiler.Native;
import x10.compiler.NativeRep;
import x10.compiler.Pragma;
import x10.util.RailBuilder;

@NativeRep("java", "java.util.Random", null, null)
final class Random {
    @Native("c++", "srandom(#seed)")
    private static def srandom(seed:Int):void {}

    @Native("c++", "random()")
    @Native("java", "#this.nextLong()")
    native def rand():Long;

    @Native("java", "new java.util.Random(#seed)")
    def this(seed:Int) {
        srandom(seed);
    }

    @Native("java", "((byte) (#this.nextLong() & 3L))")
    def next():Byte = (rand() & 3L) as Byte; // 0..alphabetSize-1
}

final class Parameters {
    val openGapPenalty = 2;
    val extendGapPenalty = 1;
    val matchScore = 4y;
    val mismatchScore = -3y;
    val alphabetSize = 4;
    val scoringMatrix = new Rail[Byte](alphabetSize*alphabetSize);
    
    val shortLength:Int;
    val longLength:Int;
    val seed:Int;
    val stride:Int;

    val overlap:Int;
    val segmentCount:Int;
    val baseSegmentLength:Int;
    val shortfall:Int;

    def this(shortLength:Int, longLength:Int, seed:Int, stride:Int) {
        this.shortLength = shortLength;
        this.longLength = longLength;
        this.seed = seed;
        this.stride = stride;

        for (var i:Int=0; i<alphabetSize;i++) {
            for (var j:Int=0; j<alphabetSize;j++) {
                scoringMatrix(i*alphabetSize+j) = (i==j) ? matchScore : mismatchScore;
            }
        }

        val maxScoreOnMatch = matchScore * shortLength;
        val maxAlignedLength = (openGapPenalty<=extendGapPenalty) ?
                shortLength - 1 + maxScoreOnMatch/openGapPenalty :
                    shortLength + (maxScoreOnMatch-openGapPenalty)/extendGapPenalty;
        overlap = maxAlignedLength - 1;
        val mostWeCanUse = (2*overlap>=longLength) ? 1 : (longLength-overlap)/overlap;
        segmentCount = (mostWeCanUse>Place.MAX_PLACES) ? Place.MAX_PLACES : mostWeCanUse;
        baseSegmentLength = overlap + (longLength-overlap)/segmentCount;
        shortfall = (longLength-overlap) % segmentCount;

        Console.OUT.println("places: " + segmentCount + "/" + Place.MAX_PLACES + " short: " + shortLength + " long: " + longLength + " seed: " + seed + " stride: " + stride + " overlap: " + overlap);
    }
}

struct Result(
    score:Int,
    shortFirst:Int,
    shortLast:Int,
    longFirst:Int,
    longLast:Int,
    short_:Rail[Byte],
    long_:Rail[Byte]) {

    def print(first:Int, length:Int) {
        Console.OUT.println(new String(short_, first, length));
        Console.OUT.println(new String(long_, first, length));
        Console.OUT.println();
    }

    def print() {
        val line = 72;
        val quo = (short_.size as Int)/line;
        val mod = (short_.size as Int)-quo*line;
        for (var i:Int=0; i<quo; i++) print(i*line, line);
        if (mod > 0) print(quo*line, mod);
    }
}

final class SSCA1 {
    static STOP = 1y, LEFT = 2y, DIAGONAL = 3y, UP = 4y;
    static GAP = '-'.ord() as Byte, A = 'A'.ord() as Byte;

    val params:Parameters;
    val short_:Rail[Byte];
    val long_:Rail[Byte];
    val tracebackMoves:Rail[Rail[Byte]];
    val first:Int;
    val last:Int;
    val localSize:Int;
    val placeId = Runtime.hereInt();

    var winningScore:Int = 0;
    var shortLast:Int = -1;
    var longLast:Int = -1;
    
    var bestPlace:Int = -1;
    var bestScore:int = -1;

    def this(params:Parameters, verbose:Boolean) {
        this.params = params;
        val baseOffset = placeId * (params.baseSegmentLength-params.overlap);
        first = (placeId<params.shortfall) ? baseOffset+placeId: baseOffset+params.shortfall;
        val size = (placeId<params.shortfall ? params.baseSegmentLength+1 : params.baseSegmentLength);
        localSize = size;
        last = first + size;
        val r = new Random(params.seed);
        short_ = new Rail[Byte](params.shortLength);
        long_ = new Rail[Byte](size);
        for (var i:Int=0; i<params.shortLength; i++) {
            short_(i) = r.next();
        }
        for (var i:Int=0; i<last; i++) {
            val v = r.next();
            if (i>=first) long_(i-first) = v;
        }
        tracebackMoves = new Rail[Rail[Byte]](params.shortLength+1, (Long)=>new Rail[Byte](size+1));
        if (verbose) Console.OUT.println("place=" + placeId + " [" + first + ","+ last +"[");
    }

    def run(plh:PlaceLocalHandle[SSCA1], parentId:Int, verbose:Boolean) {
        val scoringMatrix = params.scoringMatrix;
        val alphabetSize = params.alphabetSize;
        val extendGapPenalty = params.extendGapPenalty;
        val openGapPenalty = params.openGapPenalty;
        val shortSize = params.shortLength;

        val short_ = this.short_;
        val long_ = this.long_;
        val tracebackMoves = this.tracebackMoves;
        val localSize = this.localSize;

        for (var i:Int=0; i<=shortSize; i++) tracebackMoves(i)(0) = STOP;
        for (var j:Int=0; j<=localSize; j++) tracebackMoves(0)(j) = STOP;

        val bestScoreUpTo_I_J = new Rail[Int](localSize + 1);

        var winningScore:Int = -1;
        var shortLast:Int = -1;
        var longLast:Int = -1;

        for (var i:Int=1; i<=shortSize; i++) {
            var previousBestScore:Int = 0;
            val ti = tracebackMoves(i);
            val ti1 = tracebackMoves(i-1);
            val s = short_(i-1)*alphabetSize;
            for (var j:Int=1; j<=localSize; j++) {
                val scoreOfMatchAtLast = scoringMatrix(s + long_(j-1));
                val scoreUsingLatestIJ = previousBestScore + scoreOfMatchAtLast;
                val bestIfGapInsertedInI = bestScoreUpTo_I_J(j) - (ti1(j)==UP ? extendGapPenalty : openGapPenalty);
                val bestIfGapInsertedInJ = bestScoreUpTo_I_J(j-1) - (ti(j-1)==LEFT ? extendGapPenalty : openGapPenalty);     
                val direction = maxOrZero(scoreUsingLatestIJ, bestIfGapInsertedInI, bestIfGapInsertedInJ);
                ti(j) = direction;
                var winner:Int = 0;
                switch (direction) {
                case DIAGONAL: winner = scoreUsingLatestIJ; break;
                case UP:       winner = bestIfGapInsertedInI; break;
                case LEFT:     winner = bestIfGapInsertedInJ;
                }
                previousBestScore = bestScoreUpTo_I_J(j);
                bestScoreUpTo_I_J(j) = winner;
                if (winner > winningScore) {
                    winningScore = winner;
                    shortLast = i;
                    longLast = j;
                }
            }
        }
        this.winningScore = winningScore;
        this.shortLast = shortLast;
        this.longLast = longLast;
        if (verbose) Console.OUT.println("place=" + placeId + " score=" + winningScore + " short=" + shortLast + " long=" + (first+longLast));
        reduce(plh, parentId, placeId, winningScore);
    }

    @Inline static def reduce(plh:PlaceLocalHandle[SSCA1], parentId:Int, placeId:Int, winningScore:Int) {
        at (Place(parentId)) async {
            /* atomic */
            if (plh().bestScore < winningScore) {
                plh().bestScore = winningScore;
                plh().bestPlace = placeId;
            } else if (plh().bestScore == winningScore && plh().bestPlace > placeId) {
                plh().bestPlace = placeId;
            }
        }
    }

    @Inline static def maxOrZero(a:Int, b:Int, c:Int) {
        if (a >= b) {
            if (a >= c) return a > 0 ? DIAGONAL : STOP; else return c > 0 ? LEFT : STOP;
        } else {
            if (b >= c) return b > 0 ? UP : STOP; else return c > 0 ? LEFT : STOP;
        }
    }

    def trace(verify:Boolean) {
        var nextMove:Byte;
        var i:Int = shortLast;
        var j:Int = longLast;
        val shortBuilder = new RailBuilder[Byte]();
        val longBuilder = new RailBuilder[Byte]();
        while ((nextMove = tracebackMoves(i)(j)) != STOP) {
            switch (nextMove) {
            case UP:
                shortBuilder.add(short_(--i) + A);
                longBuilder.add(GAP);
                break;
            case DIAGONAL:
                shortBuilder.add(short_(--i) + A);
                longBuilder.add(long_(--j) + A);
                break;
            case LEFT:
                shortBuilder.add(GAP);
                longBuilder.add(long_(--j) + A);
            }
        }
        val s = shortBuilder.result();
        val l = longBuilder.result();
        if (verify) check(s, l);
        return Result(winningScore, i, shortLast, first+j, first+longLast, s, l);
    }

    def check(s:Rail[Byte], l:Rail[Byte]) {
        var opened:Boolean = false;
        var score:Int = 0;
        for (var i:Int=0; i<s.size; i++) {
            if (s(i) != GAP && l(i) != GAP) {
                score += params.scoringMatrix((s(i)-A)*params.alphabetSize + (l(i)-A));
                opened = false;
            } else {
                score -= opened ? params.extendGapPenalty : params.openGapPenalty;
                opened = true;
            }
        }
        if (score == winningScore) {
            Console.OUT.println("VERIFIED");
        } else {
            Console.OUT.println("FAILED VERIFICATION");
        }
    }

    def loop(plh:PlaceLocalHandle[SSCA1], verbose:Boolean) {
        val id = placeId;
        val m = Math.min(id+params.stride, params.segmentCount);
        @Pragma(Pragma.FINISH_HERE) finish for (var i:Int=id; i<m; i++) {
            at (Place(i)) async plh().run(plh, id, verbose);
        }
        reduce(plh, 0, bestPlace, bestScore);
    }

    def score(plh:PlaceLocalHandle[SSCA1], verbose:Boolean) {
        @Pragma(Pragma.FINISH_HERE) finish for (var i:Int=0; i<params.segmentCount; i+=params.stride) {
            at (Place(i)) async plh().loop(plh, verbose);
        }
        return bestPlace;
    }

    static def sub(str:String, start:Int, end:Int) = str.substring(start, Math.min(end, str.length()));

    static def printTime(title:String, time:Long) {
        Console.OUT.println(title + sub("" + (time/1e9), 0, 6) + "s");
    }

    public static def sqrt(var p:Int) {
        var r:Int = p;
        while (p > 1) { p = p>>2; r = r>>1; }
        return r;
    }

    public static def main(args:Rail[String]){here==Place.FIRST_PLACE} {
        if (args.size < 2) {
            Console.ERR.println("Usage: ssca1 shortLength longLength [seed] [iterations] [stride] [verbose] [verify]");
            System.setExitCode(1);
            return;
        }
        val shortLength = Int.parseInt(args(0));
        val longLength = Int.parseInt(args(1));
        val seed = args.size>2 ? Int.parseInt(args(2)) : 1;
        val iterations = args.size>3 ? Int.parseInt(args(3)) : 6;
        val s = args.size>4 ? Int.parseInt(args(4)) : 0;
        val stride = s==0 ? sqrt(Place.MAX_PLACES) : s;
        val verbose = args.size>5 ? Boolean.parseBoolean(args(5)) : false;
        val verify = args.size>6 ? Boolean.parseBoolean(args(6)) : false;
        val params = new Parameters(shortLength, longLength, seed, stride);

        var t:Long = System.nanoTime();
        val plh = PlaceLocalHandle.makeFlat[SSCA1](PlaceGroup.make(params.segmentCount), ()=>new SSCA1(params, verbose));
        printTime("Init:  ", System.nanoTime()-t);

        var start_time:Long = 0;
        Console.OUT.println("Warmup");

        for (var k:Int=0; k<iterations; k++) {
            val v = verbose && (k==0);
            if (k == 1) {
                Console.OUT.println("Start Timer");
                start_time = System.nanoTime();
            }
            t = System.nanoTime();
            val p = plh().score(plh, v);
            printTime("Score: ", System.nanoTime()-t);
            t = System.nanoTime();
            val r = at (Place(p)) plh().trace(verify);
            printTime("Trace: ", System.nanoTime()-t);
            Console.OUT.println("place=" + p + " score=" + r.score + " short=[" + r.shortFirst + "," + r.shortLast + "[ long=[" + r.longFirst + "," + r.longLast +"[ length=" + r.short_.size);
            if (k == 0) r.print();
        }
        val stop_time = System.nanoTime();
        Console.OUT.println("Stop Timer");

        Console.OUT.println("Total time: " + (stop_time-start_time)/1E9);
    }
}
