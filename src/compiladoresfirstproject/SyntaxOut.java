/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoresfirstproject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JAAM
 */
public class SyntaxOut {

    String finalOut = "";
    Vector<colectObject> datosSalida = new Vector();

    class colectObject {

        String Token = "";
        String Lexema = "";
    }

    // Metodo encargado de escribir la lista de la salida del analisis sintactico
    public void writeSintaxStack(String pToken, String pLexema) {

        colectObject temp1 = new colectObject();

        temp1.Lexema = pLexema;
        temp1.Token = pToken;
        datosSalida.add(temp1);
    }

    // Metodo encargado de escribir la salida del analisis sintactico
    public void writeSintaxOut() {
        FileWriter fichero = null;
        PrintWriter pw = null;

        Boolean putCloseKey = false;
        Boolean writeExtra = false;

        try {
            fichero = new FileWriter("src/outputs/OutputAnalisisSintactico.txt");
            pw = new PrintWriter(fichero);
            pw.println("***********  RESUMEN ANÁLISIS SINTÁCTICO  ***********");

            for (int i = 0; i < datosSalida.size(); i++) {
                if (datosSalida.get(i).Token.equals("Entonces") | datosSalida.get(i).Token.equals("Haga")
                        | datosSalida.get(i).Token.equals("Sino")) {

                    int tmp = i;
                    if (datosSalida.get(tmp + 1).Token.equals("OpKey")) {
                        finalOut = finalOut + " " + datosSalida.get(i).Lexema;
                    } else {
                        finalOut = finalOut + " " + datosSalida.get(i).Lexema + " {";
                        writeExtra = true;
                    }

                } else {
                    if ((datosSalida.get(i).Token.equals("Mover") || datosSalida.get(i).Token.equals("Decir"))
                            && writeExtra) {
                        putCloseKey = true;
                        writeExtra = false;
                    }
                    if ((datosSalida.get(i)).Token.equals("NewLine")) {

                        if (putCloseKey) {
                            finalOut = finalOut + " }";
                            putCloseKey = false;
                        }
                        pw.println(finalOut);
                        finalOut = "";
                    } else {
                        finalOut = finalOut + " " + datosSalida.get(i).Lexema;
                    }

                }
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
}
