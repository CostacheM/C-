package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java_cup.runtime.Symbol;

public class main {
	static HashMap<Integer, String> tokenClass = new HashMap<Integer, String> (); 
	
	public static void initHash()
	{
		tokenClass.put(sym.RETURN, "Keyword");
		tokenClass.put(sym.IF, "Keyword");
		tokenClass.put(sym.INT, "Keyword");
		tokenClass.put(sym.VOID, "Keyword");
		tokenClass.put(sym.ELSE, "Keyword");
		tokenClass.put(sym.WHILE, "Keyword");
		tokenClass.put(sym.LPAREN, "delimiter");
		tokenClass.put(sym.RPAREN, "delimiter");
		tokenClass.put(sym.LSQBKT, "delimiter");
		tokenClass.put(sym.RSQBKT, "delimiter");
		tokenClass.put(sym.LBRKT, "delimiter");
		tokenClass.put(sym.RBRKT, "delimiter");
		tokenClass.put(sym.SUB, "operator");
		tokenClass.put(sym.ADD, "operator");
		tokenClass.put(sym.MULT, "operator");
		tokenClass.put(sym.DIV, "operator");
		tokenClass.put(sym.LT, "operator");
		tokenClass.put(sym.GT, "operator");
		tokenClass.put(sym.LTE, "operator");
		tokenClass.put(sym.GTE, "operator");
		tokenClass.put(sym.DEQL, "operator");
		tokenClass.put(sym.DIFF, "operator");
		tokenClass.put(sym.EQL, "operator");
		tokenClass.put(sym.SEMI, "operator");
		tokenClass.put(sym.COMMA, "operator");
		tokenClass.put(sym.ID, "Identifier");
		tokenClass.put(sym.NUM, "Integer Literal");
	}

	public static void main (String[] args) {
		
		main.initHash();
		
		FileReader inputFile;
		try {
			inputFile = new FileReader("F:\\proiecte java\\C-\\src\\main\\C-example.in");
			BufferedReader br = new BufferedReader(inputFile);
			Lexer l = new Lexer (br);
			
			try {
				Symbol sCrt;
				do 
				{
					sCrt = l.next_token();
										
					if (sCrt.sym != sym.EOF)
					{
						System.out.println("Symbol value: "+ l.yytext() + " Class: " + main.tokenClass.get(sCrt.sym) + " line: " + sCrt.left + " column: " + sCrt.right);
					}
				}while(sCrt.sym != sym.EOF);
				System.out.println("EOF");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}

