package x10.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import x10.parserGen.X10ParserLexer;
import x10.parserGen.X10ParserParser;

public class Compiler  {
	
	public static void main(String[] args){
		compile("class A {  }");
		//compile("hello world");
	}
	
	public static ParseTree compile(String expression) {
	      //lexer splits input into tokens
	      ANTLRInputStream input = new ANTLRInputStream(expression);
	      CommonTokenStream tokens = new CommonTokenStream( new X10ParserLexer(input));
	    
	      //parser generates abstract syntax tree
	      X10ParserParser parser = new X10ParserParser(tokens);
	      return parser.compilationUnit(); 
	      
	}
}