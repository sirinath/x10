/**
 * Define a grammar called Hello
 */
grammar Hello;
@lexer::header {
  package x10.parser;
}
 
@parser::header {
  package x10.parser;
} 

r  : 'hello' ID ;         // match keyword hello followed by an identifier

ID : [a-z]+ ;             // match lower-case identifiers

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

