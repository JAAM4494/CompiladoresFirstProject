package proyecto1;

import java_cup.runtime.*;
import javax.swing.*;
import java.util.*;
import proyecto1.*;
%%

%class Yylex
%{String literal;
  String temp_include;

%}
%{
int columna=1;
%}
%public
%cup
%line
%full
%unicode
%ignorecase
%char

%{
  public String sourceFilename;
    StringBuffer string = new StringBuffer();
    int ultimoEstado = 0;
 public void init(){};
%}

%eofval{
{return new Symbol(sym.EOF, null); }
%eofval}

ALPHA=[A-Za-z]
DIGITO=[0-9]
ALPHA_NUMERIC={ALPHA}|{DIGITO}

NEW_LINE=(\n|\r)
WHITE_SPACE=([\ |\t|\f])
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

st = [^\\\n\"]+ | [\\][\\] | "\\\"" | "\\\'" |"\\t"| "\\n" | "\\r" |"\\b" |"\n"
 
FRASE=("_"|{ALPHA_NUMERIC})("_"|{ALPHA_NUMERIC})*

%%
[\n] { yychar=0;}

[ \t\r\n\f] { /* ignore white space. */ }

\' { /* ignore apostrofos. */ }
<YYINITIAL> {WHITE_SPACE}  {/*no hace nada, aumenta una columna*/yychar++; }
<YYINITIAL> {NEW_LINE}     {yychar=0; yyline=0}

<YYINITIAL>"<>"     {return new Symbol(sym.DIFERENTE, yyline, yychar, yytext());}
<YYINITIAL>">="     {return new Symbol(sym.MAYORIGUAL,yyline, yychar, yytext());}
<YYINITIAL>"<="     {return new Symbol(sym.MENORIGUAL,yyline, yychar, yytext());}
<YYINITIAL>"-="     {return new Symbol(sym.MENOSNUM,  yyline, yychar, yytext());}
<YYINITIAL>"--"     {return new Symbol(sym.MENOSUNO,  yyline, yychar, yytext());}
<YYINITIAL>"+="     {return new Symbol(sym.MASNUM,    yyline, yychar, yytext());}
<YYINITIAL>"++"     {return new Symbol(sym.MASUNO,    yyline, yychar, yytext());}
<YYINITIAL>">"      {return new Symbol(sym.MAYOR,     yyline, yychar, yytext());}
<YYINITIAL>"<"      {return new Symbol(sym.MENOR,     yyline, yychar, yytext());}

<YYINITIAL>"{"      {return new Symbol(sym.LLAVEIZQ,  yyline, yychar, yytext());}
<YYINITIAL>"}"      {return new Symbol(sym.LLAVEDER,  yyline, yychar, yytext());}
<YYINITIAL>"for"    {return new Symbol(sym.FOR,       yyline, yychar, yytext());}
<YYINITIAL>"int"    {return new Symbol(sym.INT,       yyline, yychar, yytext());}
<YYINITIAL>"void"   {return new Symbol(sym.VOID,      yyline, yychar, yytext());}

<YYINITIAL>"#"      {return new Symbol(sym.NUMERAL,   yyline, yychar, yytext());}
<YYINITIAL>"."      {return new Symbol(sym.PUNTO,     yyline, yychar, yytext());}
<YYINITIAL>"%"      {return new Symbol(sym.PORCIENTO, yyline, yychar, yytext());}
<YYINITIAL>"\""     {return new Symbol(sym.COMILLAS,  yyline, yychar, yytext());}
<YYINITIAL>"&"      {return new Symbol(sym.AMPERSAND, yyline, yychar, yytext());}
<YYINITIAL>"getch"  {return new Symbol(sym.GETCH,     yyline, yychar, yytext());}
<YYINITIAL>"scanf"  {return new Symbol(sym.SCANF,     yyline, yychar, yytext());}
<YYINITIAL>"printf" {return new Symbol(sym.PRINTF,    yyline, yychar, yytext());}
<YYINITIAL>"else"   {return new Symbol(sym.ELSE,      yyline, yychar, yytext());}
<YYINITIAL>"if"     {return new Symbol(sym.IF,        yyline, yychar, yytext());}
<YYINITIAL>"h"      {return new Symbol(sym.H,         yyline, yychar, yytext());}
<YYINITIAL>"stdio"  {return new Symbol(sym.STDIO,     yyline, yychar, yytext());}
<YYINITIAL>"conio"  {return new Symbol(sym.CONIO,     yyline, yychar, yytext());}

<YYINITIAL>"("      {return new Symbol(sym.OPEN,      yyline, yychar, yytext());}
<YYINITIAL>")"      {return new Symbol(sym.CLOSE,     yyline, yychar, yytext());}		
<YYINITIAL>";"      {return new Symbol(sym.PUNTOCOMA, yyline, yychar, yytext());}
<YYINITIAL>":"      {return new Symbol(sym.DOSPUNTOS, yyline, yychar, yytext());}
<YYINITIAL>","      {return new Symbol(sym.COMA,      yyline, yychar, yytext());}
<YYINITIAL>"/"      {return new Symbol(sym.DIAGONAL,  yyline, yychar, yytext());}
<YYINITIAL>"="      {return new Symbol(sym.EQUALS,    yyline, yychar, yytext());}

<YYINITIAL>{FRASE}  {return new Symbol(sym.FRASE,     yyline, yychar, yytext());}

.		        {
              System.out.println("error lexico en la fila "+yyline +" y en la columna " + yychar);
interfaz.rotular("error lexico :"+  yytext()+" en la fila "+yyline +" y en la columna " + yychar);

	          	}