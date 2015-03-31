package compiladoresfirstproject;

import java_cup.runtime.*;
import javax.swing.*;
import java.util.*;

%%
%class Scanner

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

LETRA=[a-zA-Z]
DIGITO=[0-9]
ALPHA_NUMERIC={LETRA}|{DIGITO}

NEW_LINE=(\n|\r)
ESPACIO_EN_BLANCO=([\ |\t|\f])
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

st = [^\\\n\"]+ | [\\][\\] | "\\\"" | "\\\'" |"\\t"| "\\n" | "\\r" |"\\b" |"\n"
 
FRASE=("_"|{ALPHA_NUMERIC})("_"|{ALPHA_NUMERIC})*

%%
[\n] { yychar=0;}

[ \t\r\n\f] { /* ignore white space. */ }

\' { /* ignore apostrofos. */ }
<YYINITIAL> {ESPACIO_EN_BLANCO}  {/*no hace nada, aumenta una columna,continua lectura*/yychar++; }
<YYINITIAL> {NEW_LINE}     {yychar=0; yyline=0}

<YYINITIAL>"=="     {return new Symbol(sym.IGUALIGUAL, yyline, yychar, yytext());}
<YYINITIAL>"!="     {return new Symbol(sym.DIFERENTE, yyline, yychar, yytext());}
<YYINITIAL>">="     {return new Symbol(sym.MAYORIGUAL,yyline, yychar, yytext());}
<YYINITIAL>"<="     {return new Symbol(sym.MENORIGUAL,yyline, yychar, yytext());}
<YYINITIAL>">"      {return new Symbol(sym.MAYOR,     yyline, yychar, yytext());}
<YYINITIAL>"<"      {return new Symbol(sym.MENOR,     yyline, yychar, yytext());}

<YYINITIAL>"{"      {return new Symbol(sym.LLAVEIZQ,  yyline, yychar, yytext());}
<YYINITIAL>"}"      {return new Symbol(sym.LLAVEDER,  yyline, yychar, yytext());}
<YYINITIAL>"int"    {return new Symbol(sym.INT,       yyline, yychar, yytext());}

<YYINITIAL>"."      {return new Symbol(sym.PUNTO,     yyline, yychar, yytext());}
<YYINITIAL>"else"   {return new Symbol(sym.ELSE,      yyline, yychar, yytext());}
<YYINITIAL>"if"     {return new Symbol(sym.IF,        yyline, yychar, yytext());}

<YYINITIAL>"("      {return new Symbol(sym.OPEN,      yyline, yychar, yytext());}
<YYINITIAL>")"      {return new Symbol(sym.CLOSE,     yyline, yychar, yytext());}		
<YYINITIAL>";"      {return new Symbol(sym.PUNTOCOMA, yyline, yychar, yytext());}
<YYINITIAL>":"      {return new Symbol(sym.DOSPUNTOS, yyline, yychar, yytext());}
<YYINITIAL>","      {return new Symbol(sym.COMA,      yyline, yychar, yytext());}
<YYINITIAL>"/"      {return new Symbol(sym.DIAGONAL,  yyline, yychar, yytext());}
<YYINITIAL>"="      {return new Symbol(sym.EQUALS,    yyline, yychar, yytext());}

<YYINITIAL>{FRASE}  {return new Symbol(sym.FRASE,     yyline, yychar, yytext());}

. {System.out.println("Caracter desconocido en la fila " + yyline + ", columna " + yychar);}