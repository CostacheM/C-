package jflex;

import java_cup.runtime.Symbol;
%%

%unicode
%class Lexer
%cup
%implements sym 

%line
%column
 
%{
private Symbol symbol(int sym) {
    return new Symbol(sym, yyline+1, yycolumn+1);
}
  
private Symbol symbol(int sym, Object val) {
   return new Symbol(sym, yyline+1, yycolumn+1, val);
}

private void error(String message) {
   System.out.println("Error at line "+ (yyline+1) + ", column " + (yycolumn+ 1)+ " : "+message);
}
%} 

LineEnd = [\r\n]|\r\n
Character = [^\r\n]
WhiteSpace = {LineEnd} | [ \t\f]

CStyleComment = "/*" ~"*/" 
Comment = {CStyleComment}

 
Identifier = [a-zA-Z][_a-zA-Z0-9]*

NumericConstant = [0-9]+
 
%%
<YYINITIAL> {
   
 
   /* Arithmetic Operations */
   "-" { return symbol(SUB);}
   "+" { return symbol(ADD); }
   "*" { return symbol(MULT); }
   "/" { return symbol(DIV); }
 
   "<" { return symbol(LT); }
   ">" { return symbol(GT); }
   "<=" { return symbol(LTE); } 
   ">=" { return symbol(GTE); }    
   "==" { return symbol(DEQL); }
   "!=" { return symbol(DIFF); }
   "=" { return symbol(EQL); }
   ";" { return symbol(SEMI); }
   "," { return symbol(COMMA); }
   
   
   /* Keywords */
   "if"      { return symbol(IF); }
   "int"     { return symbol(INT); }
   "void"    { return symbol(VOID); }
   "else"    { return symbol(ELSE); }
   "while"   { return symbol(WHILE); }
   "return"  { return symbol(RETURN); }
 
   "(" { return symbol(LPAREN); }
   ")" { return symbol(RPAREN); }
   "[" { return symbol(LSQBKT); }
   "]" { return symbol(RSQBKT); }
   "{" { return symbol(LBRKT); }
   "}" { return symbol(RBRKT); }
   
   {Comment} {}
   {Identifier} { return symbol(ID, yytext());}
   {NumericConstant} { return symbol(NUM, new Integer(Integer.parseInt(yytext()))); }
   {WhiteSpace} { /* Ignore */ }
 
 }
 
.|\n { System.out.println("ERROR");error(yytext());}