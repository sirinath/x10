package x10.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Compiler  {
	
	public static void main(String[] args){
		compile("hello");
		compile("hello world");
	}
	
	public static void compile(String expression) {
	      //lexer splits input into tokens
	      ANTLRInputStream input = new ANTLRInputStream(expression);
	      CommonTokenStream tokens = new CommonTokenStream( new X10ParserLexer(input));
	    
	      //parser generates abstract syntax tree
	      X10ParserParser parser = new X10ParserParser(tokens);
	      ParseTree tree = parser.compilationUnit(); 
	      
	      System.out.println(tree);
	      
	}
}