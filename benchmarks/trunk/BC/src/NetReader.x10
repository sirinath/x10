import x10.io.File;
import x10.io.Reader;
import x10.io.ReaderIterator;

/* A class to read the NET format by Pajek */
public  struct NetReader {

  /* 
   * The function that reads the binary format by Guojing -- this is an 
   * unweighted graph. Start index is not used here.
   */
  public static def readGuojingFile (fileName:String,
                                     startIndex:Int) {
    val inputFile:File = new File(fileName);
    val inputFileReader:Reader = inputFile.openRead();

    /* Find out the number of vertices --- this is needed to initialize */
    val N = inputFileReader.readInt();
    if (0 > N) throw new Exception ("Incorrect File Format");

    /* Declare the adjacency graph */
    val adjacencyGraph = new AdjacencyGraph(N);

    /* Now, read the graph in */
    for ([i] in 0..(N-1)) {
      val numNeighbors = inputFileReader.readInt();

      for ([j] in 0..(numNeighbors-1)) {
        adjacencyGraph.addEdge (i, inputFileReader.readInt(), 1 as Long);
      }
    }

    return adjacencyGraph;
  }

  private static def getNextLine(inputFileIterator:ReaderIterator[String]){
    var line:String = "EOF";
    while (inputFileIterator.hasNext()) {
      line = inputFileIterator.next().toLowerCase().trim();
      if (0>line.indexOf("#") && 
          0>line.indexOf("source") &&
          0>line.indexOf("id") &&
          0>line.indexOf("target")) break;
      else {} /* We found a comment line, move on to the next */
    }

    return line;
  }

  /* 
   * Removes all the empty strings --- this is needed because split() does 
   * not allow us to split on a regex. Consequently, all multiple spaces are
   * parsed as a bunch of empty strings.
   */
  private static def tokenize(line:String, splitter:String) {
    var numNonEmptyTokens:Int = 0;
    val tokens:Rail[String] = line.split(splitter);
    
    /* First count the number of non-empty tokens */
    for ([i] in 0..(tokens.length()-1)) {
      if (0<(tokens(i).length())) ++numNonEmptyTokens;
    }

    /* Now create the Rail */
    val nonEmptyTokens:Rail[String] = Rail.make[String] (numNonEmptyTokens);
    var nonEmptyTokenIndex:Int = 0;
    for ([i] in 0..(tokens.length()-1)) {
      if (0<(tokens(i).length())) {
        nonEmptyTokens(nonEmptyTokenIndex++) = tokens(i);
      }
    }

    return nonEmptyTokens;
  }

  /* The function that reads in a .NWB file */
  public static def readNwbFile (fileName:String,
                                 startIndex:Int) : 
                                AdjacencyGraph {

    val inputFile:File = new File(fileName);
    val inputFileReader:Reader = inputFile.openRead();
    val inputFileIterator:ReaderIterator[String] = inputFileReader.lines();

    /* Find out the number of vertices --- this is needed to initialize */
    val firstLine:String = getNextLine(inputFileIterator);
    val N:Int = (0 > firstLine.lastIndexOf ("*nodes")) ? 
                      -1: /* error --- first line has to be "*Vertices" */
                      Int.parse(tokenize(firstLine, " ")(1));

    if (0 > N) throw new Exception ("Incorrect File Format");

    val adjacencyGraph = new AdjacencyGraph(N);

    /* Iterate over the lines and construct the graph */
    var foundDirectedEdges:Boolean = false;
    var foundUnDirectedEdges:Boolean = false;
    for (var thisLine:String=getNextLine(inputFileIterator);
         !thisLine.equals("EOF");
         thisLine=getNextLine(inputFileIterator)) {

      if (0 <= thisLine.indexOf("*directededges")) {
        foundUnDirectedEdges = false;
        foundDirectedEdges = true;
        continue; 
      } else if (0 <= thisLine.indexOf("*undirectededges")) {
        foundUnDirectedEdges = true;
        foundDirectedEdges = false;
        continue; 
      }

      /* Process the edges or edgelist remaining --- assume Long weights */
      if (foundDirectedEdges) {
        val tokens:Rail[String] = tokenize(thisLine," ");

        if (2 > tokens.length()) 
          throw new Exception ("Format not \"src dest weight\"");

        val source= Int.parse (tokens(0)) - startIndex;
        val destination = Int.parse (tokens(1)) - startIndex;
        val weight:Long = (2==tokens.length()) ? 1 as Long: 
                                                  Long.parse (tokens(2));

        adjacencyGraph.addEdge (source, destination, weight);
        adjacencyGraph.incrementInDegree (destination);
      } else if (foundDirectedEdges) {
        val tokens:Rail[String] = tokenize(thisLine," ");

        if (2 > tokens.length()) 
          throw new Exception ("Format not \"src dest weight\"");

        val source= Int.parse (tokens(0)) - startIndex;
        val destination = Int.parse (tokens(1)) - startIndex;
        val weight:Long = (2==tokens.length()) ? 1 as Long: 
                                                  Long.parse (tokens(2));

        adjacencyGraph.addEdge (source, destination, weight);
        adjacencyGraph.addEdge (destination, source, weight);
        adjacencyGraph.incrementInDegree (source);
        adjacencyGraph.incrementInDegree (destination);
      } else {
        /* Do nothing */
      }
    }
    return adjacencyGraph;
  }

  /* The function that reads in .NET format */
  public static def readNetFile (fileName:String,
                                 startIndex:Int) : 
                                AdjacencyGraph {

    val inputFile:File = new File(fileName);
    val inputFileReader:Reader = inputFile.openRead();
    val inputFileIterator:ReaderIterator[String] = inputFileReader.lines();

    /* Find out the number of vertices --- this is needed to initialize */
    val firstLine:String = inputFileIterator.next().toLowerCase();
    val N:Int = (0 > firstLine.lastIndexOf ("*vertices")) ? 
                      -1: /* error --- first line has to be "*Vertices" */
                      Int.parse(tokenize(firstLine," ")(1));

    if (0 > N) throw new Exception ("Incorrect File Format");

    val adjacencyGraph = new AdjacencyGraph(N);

    /* Iterate over the lines and construct the graph */
    var foundEdges:Boolean = false;
    while (inputFileIterator.hasNext()) {
      val thisLine:String = inputFileIterator.next().toLowerCase().trim();

      if (0 <= thisLine.lastIndexOf("*edgeslist") || 
          0 <= thisLine.lastIndexOf("*arcslist")) {
        throw new Exception ("We do not support EdgesList or ArcsList");
      } else if (0 <= thisLine.lastIndexOf("*edges") || 
                 0 <= thisLine.lastIndexOf("*arcs")) {
        foundEdges = true;
        continue; 
      }

      /* Process the edges or edgelist remaining */
      if (foundEdges) {
        val tokens:Rail[String] = tokenize(thisLine," ");

        if (2 < tokens.length())
          throw new Exception ("Format not \"src dest weight\"");

        val source= Int.parse (tokens(0)) - startIndex;
        val destination = Int.parse (tokens(1)) - startIndex;
        val weight:Long = (2==tokens.length()) ? 1 as Long: 
                                                  Long.parse (tokens(2));

        adjacencyGraph.addEdge (source, destination, weight);
        adjacencyGraph.incrementInDegree (destination);
      }
    }
    return adjacencyGraph;
  }
}
