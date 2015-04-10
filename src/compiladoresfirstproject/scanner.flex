package compiladoresfirstproject;

import java_cup.runtime.*;

%%
%class myLexer

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

public void echo(int pToken) {
                System.out.println("Token: " + pToken + " Lexema: " + yytext());
}

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

\' { /* ignora apostrofes. */ }
<YYINITIAL> {ESPACIO_EN_BLANCO}  {/*no hace nada, aumenta una columna,continua lectura*/yychar++; }
<YYINITIAL> {NEW_LINE}     {yychar=0; yyline=0}

<YYINITIAL>"mover"          {echo(sym.mover); return new Symbol(sym.mover,          yyline, yychar, yytext());}
<YYINITIAL>"lindos"         {echo(sym.lindos); return new Symbol(sym.lindos,         yyline, yychar, yytext());}
<YYINITIAL>"<"              {echo(sym.menor); return new Symbol(sym.menor,          yyline, yychar, yytext());}
<YYINITIAL>"haga"           {echo(sym.haga); return new Symbol(sym.haga,           yyline, yychar, yytext());}
<YYINITIAL>"adios"          {echo(sym.adios); return new Symbol(sym.adios,          yyline, yychar, yytext());}
<YYINITIAL>"+"              {echo(sym.suma); return new Symbol(sym.suma,           yyline, yychar, yytext());}
<YYINITIAL>"-"              {echo(sym.resta); return new Symbol(sym.resta,          yyline, yychar, yytext());}
<YYINITIAL>"<="             {echo(sym.menoreq); return new Symbol(sym.menoreq,        yyline, yychar, yytext());}
<YYINITIAL>"true"           {echo(sym.True); return new Symbol(sym.True,           yyline, yychar, yytext());}
<YYINITIAL>"*"              {echo(sym.multi); return new Symbol(sym.multi,          yyline, yychar, yytext());}
<YYINITIAL>"/"              {echo(sym.divi); return new Symbol(sym.divi,           yyline, yychar, yytext());}
<YYINITIAL>"mientras"       {echo(sym.mientras); return new Symbol(sym.mientras,       yyline, yychar, yytext());}
<YYINITIAL>"sino"           {echo(sym.sino); return new Symbol(sym.sino,           yyline, yychar, yytext());}
<YYINITIAL>">="             {echo(sym.mayoreq); return new Symbol(sym.mayoreq,        yyline, yychar, yytext());}
<YYINITIAL>"false"          {echo(sym.False); return new Symbol(sym.False,          yyline, yychar, yytext());}
<YYINITIAL>"izquierda"      {echo(sym.izquierda); return new Symbol(sym.izquierda,      yyline, yychar, yytext());}		
<YYINITIAL>"ojos"           {echo(sym.ojos); return new Symbol(sym.ojos,           yyline, yychar, yytext());}
<YYINITIAL>"arriba"         {echo(sym.arriba); return new Symbol(sym.arriba,         yyline, yychar, yytext());}
<YYINITIAL>"=="             {echo(sym.eqeq); return new Symbol(sym.eqeq,           yyline, yychar, yytext());}
<YYINITIAL>">"              {echo(sym.mayor); return new Symbol(sym.mayor,          yyline, yychar, yytext());}
<YYINITIAL>"hola"           {echo(sym.hola); return new Symbol(sym.hola,           yyline, yychar, yytext());}
<YYINITIAL>"!="             {echo(sym.diferente); return new Symbol(sym.diferente,      yyline, yychar, yytext());}
<YYINITIAL>"boca"           {echo(sym.boca); return new Symbol(sym.boca,           yyline, yychar, yytext());}
<YYINITIAL>"entonces"       {echo(sym.entonces); return new Symbol(sym.entonces,       yyline, yychar, yytext());}
<YYINITIAL>"decir"          {echo(sym.decir); return new Symbol(sym.decir,          yyline, yychar, yytext());}
<YYINITIAL>"abajo"          {echo(sym.abajo); return new Symbol(sym.abajo,          yyline, yychar, yytext());}
<YYINITIAL>"derecha"        {echo(sym.derecha); return new Symbol(sym.derecha,        yyline, yychar, yytext());}
<YYINITIAL>"si"             {echo(sym.si); return new Symbol(sym.si,             yyline, yychar, yytext());}

<YYINITIAL>{DIGITO}+ {ESPACIO_EN_BLANCO}* {echo(sym.num); return new Symbol(sym.num, yyline, yychar, yytext()); }

EOF    {System.out.println("Fin de archivo");}

/*<YYINITIAL>{FRASE}        {echo(); return new Symbol(sym.FRASE,          yyline, yychar, yytext());}*/

. {System.out.println("Caracter desconocido en la fila: " + yyline + ", columna: " + yychar);}