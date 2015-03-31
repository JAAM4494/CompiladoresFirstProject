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

<YYINITIAL>"mover"     {return new Symbol(sym.mover, yyline, yychar, yytext());}
<YYINITIAL>"lindos"     {return new Symbol(sym.lindos, yyline, yychar, yytext());}
<YYINITIAL>"<"     {return new Symbol(sym.MENOR,yyline, yychar, yytext());}
<YYINITIAL>"haga"     {return new Symbol(sym.haga,yyline, yychar, yytext());}
<YYINITIAL>"adios"      {return new Symbol(sym.adios,     yyline, yychar, yytext());}
<YYINITIAL>"+"      {return new Symbol(sym.SUMA,     yyline, yychar, yytext());}
<YYINITIAL>"<="      {return new Symbol(sym.MENOR_IGUAL,  yyline, yychar, yytext());}
<YYINITIAL>"true"      {return new Symbol(sym.True,  yyline, yychar, yytext());}
<YYINITIAL>"*"    {return new Symbol(sym.MULTI,       yyline, yychar, yytext());}
<YYINITIAL>"mientras"      {return new Symbol(sym.mientras,     yyline, yychar, yytext());}
<YYINITIAL>"sino"   {return new Symbol(sym.sino,      yyline, yychar, yytext());}
<YYINITIAL>">="     {return new Symbol(sym.MAYOR_IGUAL,        yyline, yychar, yytext());}
<YYINITIAL>"false"      {return new Symbol(sym.False,      yyline, yychar, yytext());}
<YYINITIAL>"izquierda"      {return new Symbol(sym.izquierda,     yyline, yychar, yytext());}		
<YYINITIAL>"ojos"      {return new Symbol(sym.ojos, yyline, yychar, yytext());}
<YYINITIAL>"arriba"      {return new Symbol(sym.arriba, yyline, yychar, yytext());}
<YYINITIAL>"=="      {return new Symbol(sym.IGUAL_IGUAL,      yyline, yychar, yytext());}
<YYINITIAL>">"      {return new Symbol(sym.MAYOR,  yyline, yychar, yytext());}
<YYINITIAL>"hola"      {return new Symbol(sym.hola,    yyline, yychar, yytext());}
<YYINITIAL>"!="      {return new Symbol(sym.DIFERENTE,    yyline, yychar, yytext());}
<YYINITIAL>"boca"      {return new Symbol(sym.boca,    yyline, yychar, yytext());}
<YYINITIAL>"entonces"      {return new Symbol(sym.entonces,    yyline, yychar, yytext());}
<YYINITIAL>"/"      {return new Symbol(sym.DIV,    yyline, yychar, yytext());}
<YYINITIAL>"decir"      {return new Symbol(sym.decir,    yyline, yychar, yytext());}
<YYINITIAL>"abajo"      {return new Symbol(sym.abajo,    yyline, yychar, yytext());}
<YYINITIAL>"derecha"      {return new Symbol(sym.derecha,    yyline, yychar, yytext());}
<YYINITIAL>"si"      {return new Symbol(sym.si,    yyline, yychar, yytext());}
<YYINITIAL>"-"      {return new Symbol(sym.RESTA,    yyline, yychar, yytext());}

/*<YYINITIAL>{FRASE}  {return new Symbol(sym.FRASE,     yyline, yychar, yytext());}*/

. {System.out.println("Caracter desconocido en la fila " + yyline + ", columna " + yychar);}