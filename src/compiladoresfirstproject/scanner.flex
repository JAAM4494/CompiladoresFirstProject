package compiladoresfirstproject;

import java_cup.runtime.*;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

%%
%class myLexer

%{String literal;
  String temp_include;
%}

%{
int columna=1;
%}

%{
boolean banderaNewLine = false;
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

Intermedio generadorIntermedio = new Intermedio();

Vector TokensOut = new Vector();
Vector TokensIntermedio = new Vector();

public void echo(int pToken)  {
      try {
          String TokenName = returnTokenName(pToken);
          System.out.println("Token: " + TokenName + " Lexema: " + yytext());
          VentanaPrincipal.mostrarSalida("Token: " + TokenName + " Lexema: " + yytext());
          TokensOut.addElement("Token: " + TokenName + " Lexema: " + yytext());
          generadorIntermedio.createInterStack(TokensIntermedio, TokenName, yytext());
      } catch (IllegalArgumentException | IllegalAccessException ex) {
          Logger.getLogger(myLexer.class.getName()).log(Level.SEVERE, null, ex);
      }
}

private static void writeOut(Vector pVector) {
        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            fichero = new FileWriter("C:/Users/JAAM/Documents/NetBeansProjects/CompiladoresFirstProject/src/compiladoresfirstproject/OutputAnalisisLexico.txt");
            pw = new PrintWriter(fichero);
            pw.println("***********  RESUMEN ANÁLISIS LÉXICO  ***********");
            
            for (Object pVector1 : pVector) {
                pw.println(pVector1);
                //System.out.println("Linea " + i);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // asegurarnos que se cierra el fichero.
            if (null != fichero) {
                try {
                    fichero.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}

private static String returnTokenName(int pIntToken) throws IllegalArgumentException, IllegalAccessException {
        String out = null;
        sym Bridge = null;
        Field fields[] = sym.class.getDeclaredFields();
        
        for (int i = 0; i < fields.length-1; i++) {
            Field temp0 = fields[i];
            temp0.setAccessible(true);
            Object valueObject = temp0.get(Bridge);
            //System.out.println(value);
            // System.out.println("Variable Name is : " + fld[i].getName());
            if(pIntToken == (int)valueObject) {
                //System.out.println(temp0.getName());
                out = temp0.getName();
            }
        }
        return out;
}

%}

%eofval{
{return new Symbol(sym.EOF,null);}
%eofval}

%eof{
writeOut(TokensOut);
TokensIntermedio.add("END"); 
generadorIntermedio.debugInterSack(TokensIntermedio);
%eof}

LETRA=[a-zA-Z]
DIGITO=[0-9]
ALPHA_NUMERIC={LETRA}|{DIGITO}

ID=("_"|{LETRA})({ALPHA_NUMERIC})*

NEW_LINE=(\n|\r|\r\n)
ESPACIO_EN_BLANCO=([\ |\t|\f])
 
FRASE=("_"|{ALPHA_NUMERIC})("_"|{ALPHA_NUMERIC})*

%%
[\n] {yychar=0;}

[ \t\f] {/* ignore white space. */ }

\' { /* ignora apostrofes. */ }

<YYINITIAL> {ESPACIO_EN_BLANCO}       {/*no hace nada, aumenta una columna,continua lectura*/yychar++; }
<YYINITIAL> {NEW_LINE}*               {yychar=0; yyline=0; 
                                      if(banderaNewLine == true) {
                                           //System.out.println("Salto linea");
                                           banderaNewLine = false;
                                           return  new Symbol(sym.NewLine,  yyline, yychar, yytext());
                                      }}

<YYINITIAL>"mover"|"MOVER"                     {echo(sym.Mover); banderaNewLine = true; return new Symbol(sym.Mover,          yyline, yychar, yytext());}
<YYINITIAL>"declarar"|"DECLARAR"               {echo(sym.Declarar); banderaNewLine = true; return new Symbol(sym.Declarar,          yyline, yychar, yytext());}
<YYINITIAL>"lindos"|"LINDOS"                   {echo(sym.Lindos); return new Symbol(sym.Lindos,         yyline, yychar, yytext());}
<YYINITIAL>"<"                                 {echo(sym.Menor); return new Symbol(sym.Menor,          yyline, yychar, yytext());}
<YYINITIAL>"haga"|"HAGA"                       {echo(sym.Haga); return new Symbol(sym.Haga,           yyline, yychar, yytext());}
<YYINITIAL>"adios"|"ADIOS"                     {echo(sym.Adios); return new Symbol(sym.Adios,          yyline, yychar, yytext());}
<YYINITIAL>"+"                                 {echo(sym.Suma); return new Symbol(sym.Suma,           yyline, yychar, yytext());}
<YYINITIAL>"-"                                 {echo(sym.Resta); return new Symbol(sym.Resta,          yyline, yychar, yytext());}
<YYINITIAL>"="                                 {echo(sym.Eq); return new Symbol(sym.Eq,          yyline, yychar, yytext());}
<YYINITIAL>"<="                                {echo(sym.MenorEq); return new Symbol(sym.MenorEq,        yyline, yychar, yytext());}
<YYINITIAL>"true"|"TRUE"                       {echo(sym.True); return new Symbol(sym.True,           yyline, yychar, yytext());}
<YYINITIAL>"*"                                 {echo(sym.Multi); return new Symbol(sym.Multi,          yyline, yychar, yytext());}
<YYINITIAL>"/"                                 {echo(sym.Divi); return new Symbol(sym.Divi,           yyline, yychar, yytext());}
<YYINITIAL>"mientras"|"MIENTRAS"               {echo(sym.Mientras); return new Symbol(sym.Mientras,       yyline, yychar, yytext());}
<YYINITIAL>"sino"|"SINO"                       {echo(sym.Sino); return new Symbol(sym.Sino,           yyline, yychar, yytext());}
<YYINITIAL>">="                                {echo(sym.MayorEq); return new Symbol(sym.MayorEq,        yyline, yychar, yytext());}
<YYINITIAL>"false"|"FALSE"                     {echo(sym.False); return new Symbol(sym.False,          yyline, yychar, yytext());}		
<YYINITIAL>"vida"|"VIDA"                       {echo(sym.Vida); return new Symbol(sym.Vida,           yyline, yychar, yytext());}
<YYINITIAL>"=="                                {echo(sym.EqEq); return new Symbol(sym.EqEq,           yyline, yychar, yytext());}
<YYINITIAL>">"                                 {echo(sym.Mayor); return new Symbol(sym.Mayor,          yyline, yychar, yytext());}
<YYINITIAL>"hola"|"HOLA"                       {echo(sym.Hola); return new Symbol(sym.Hola,           yyline, yychar, yytext());}
<YYINITIAL>"!="                                {echo(sym.Diferente); return new Symbol(sym.Diferente,      yyline, yychar, yytext());}
<YYINITIAL>"pura"|"PURA"                       {echo(sym.Pura); return new Symbol(sym.Pura,           yyline, yychar, yytext());}
<YYINITIAL>"entonces"|"ENTONCES"               {echo(sym.Entonces); return new Symbol(sym.Entonces,       yyline, yychar, yytext());}
<YYINITIAL>"decir"|"DECIR"                     {echo(sym.Decir); return new Symbol(sym.Decir,          yyline, yychar, yytext());}
<YYINITIAL>"si"|"SI"                           {echo(sym.Si); return new Symbol(sym.Si,             yyline, yychar, yytext());}

<YYINITIAL>"ojos"|"OJOS"                       {echo(sym.Ojos); return new Symbol(sym.Ojos,           yyline, yychar, yytext());}
<YYINITIAL>"boca"|"BOCA"                       {echo(sym.Boca); return new Symbol(sym.Boca,           yyline, yychar, yytext());}
<YYINITIAL>"cabeza"|"CABEZA"                   {echo(sym.Cabeza); return new Symbol(sym.Cabeza,           yyline, yychar, yytext());}

<YYINITIAL>"("                                 {echo(sym.OpParenth); return new Symbol(sym.OpParenth,             yyline, yychar, yytext());}
<YYINITIAL>")"                                 {echo(sym.CloseParenth); return new Symbol(sym.CloseParenth,             yyline, yychar, yytext());}
<YYINITIAL>"{"                                 {echo(sym.OpKey); return new Symbol(sym.OpKey,             yyline, yychar, yytext());}
<YYINITIAL>"}"                                 {echo(sym.CloseKey); return new Symbol(sym.CloseKey,             yyline, yychar, yytext());}

<YYINITIAL>"izquierda"|"IZQUIERDA"             {echo(sym.Izquierda); return new Symbol(sym.Izquierda,      yyline, yychar, yytext());}
<YYINITIAL>"derecha"|"DERECHA"                 {echo(sym.Derecha); return new Symbol(sym.Derecha,        yyline, yychar, yytext());}
<YYINITIAL>"arriba"|"ARRIBA"                   {echo(sym.Arriba); return new Symbol(sym.Arriba,         yyline, yychar, yytext());}
<YYINITIAL>"abajo"|"ABAJO"                     {echo(sym.Abajo); return new Symbol(sym.Abajo,          yyline, yychar, yytext());}

<YYINITIAL>{DIGITO}+ {ESPACIO_EN_BLANCO}*      {echo(sym.Num); return new Symbol(sym.Num, yyline, yychar, yytext()); }

<YYINITIAL>{ID}                                {echo(sym.ID); return new Symbol(sym.ID, yyline, yychar, yytext()); }

. {TokensOut.addElement("Caracter desconocido en la fila: " + yyline + ", columna: " + yychar + "el análisis continúa");
VentanaPrincipal.mostrarSalida("Caracter desconocido en la fila: " + yyline + ", columna: " + yychar + "el análisis continúa");
System.out.println("Caracter desconocido en la fila: " + yyline + ", columna: " + yychar + "el análisis continúa");}