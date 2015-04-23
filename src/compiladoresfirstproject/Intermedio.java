/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoresfirstproject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Vector;

/**
 *
 * @author DANIEL
 */
public class Intermedio {

    private FileWriter fichero = null;
    private PrintWriter pw = null;

    private String TempString = "";
    private boolean oneLine = false;

    public Intermedio() {

    }

    public void createInterStack(Vector pVector, String pToken, String pLexema) {

        if (pToken.equals("ID")) {
            pVector.add(pLexema);
        } else {
            if (!pToken.equals("NewLine")) {
                pVector.add(pLexema.toUpperCase());
            }
        }
    }

//    public void createInterStack(Vector pVector, String pToken, String pLexema) {
//
//        if (pToken.equals("ID")) {
//            pVector.add(pLexema);
//        } else {
//            if (!pToken.equals("NewLine") & !pToken.equals("CloseParenth") & !pToken.equals("OpParenth")) {
//                pVector.add(pLexema.toUpperCase());
//            }
//        }
//    }
    // METODO PERMITE PONER LA EXPRSION EN UNA SOLA LINEA
//    public void createInterStack(Vector pVector, String pToken, String pLexema) {
//
//        if (pToken.equals("OpParenth")) {
//            oneLine = true;
//        }
//        if (pToken.equals("CloseParenth")) {
//            oneLine = false;
//            pVector.add(TempString);
//            TempString = "";
//        }
//        if (oneLine) {
//            if (!pToken.equals("NewLine") & !pToken.equals("CloseParenth") & !pToken.equals("OpParenth")) {
//                if (pToken.equals("ID")) {
//                    TempString = TempString + pLexema;
//                } else {
//                    TempString = TempString + pLexema.toUpperCase();
//                }
//            }
//        } else {
//            if (!pToken.equals("NewLine") & !pToken.equals("CloseParenth") & !pToken.equals("OpParenth") & !pToken.equals("ID")) {
//                pVector.add(pLexema.toUpperCase());
//            }
//            if (pToken.equals("ID")) {
//                pVector.add(pLexema);
//            }
//        }
//    }
    public void debugInterSack(Vector pVector) {

        if (AnalizadorMain.canGenerateCode) {
            FileWriter fichero = null;
            PrintWriter pw = null;

            Boolean putCloseKey = false;

            try {
                fichero = new FileWriter("src/outputs/OutputAnálisisSemántico.txt");
                pw = new PrintWriter(fichero);

                for (int i = 0; i < pVector.size(); i++) {
                    if (pVector.get(i).toString().equals("sino") | pVector.get(i).toString().equals("haga")
                            | pVector.get(i).toString().equals("entonces") | pVector.get(i).toString().equals("SINO")
                            | pVector.get(i).toString().equals("HAGA") | pVector.get(i).toString().equals("ENTONCES")) {

                        int tmp = i;
                        if (pVector.get(tmp + 1).equals("{")) {
                            pw.println(pVector.get(i).toString());
                        } else {
                            pw.println(pVector.get(i).toString());
                            pw.println("{");
                            putCloseKey = true;
                        }

                    } else {
                        if (pVector.get(i).toString().equals("izquierda") | pVector.get(i).toString().equals("IZQUIERDA")
                                | pVector.get(i).toString().equals("derecha") | pVector.get(i).toString().equals("DERECHA")
                                | pVector.get(i).toString().equals("arriba") | pVector.get(i).toString().equals("ARRIBA")
                                | pVector.get(i).toString().equals("abajo") | pVector.get(i).toString().equals("ABAJO")
                                | pVector.get(i).toString().equals("hola") | pVector.get(i).toString().equals("HOLA")
                                | pVector.get(i).toString().equals("lindos") | pVector.get(i).toString().equals("LINDOS")
                                | pVector.get(i).toString().equals("adios") | pVector.get(i).toString().equals("ADIOS")
                                | pVector.get(i).toString().equals("pura") | pVector.get(i).toString().equals("PURA")
                                | pVector.get(i).toString().equals("vida") | pVector.get(i).toString().equals("VIDA")) {
                            if (putCloseKey) {
                                pw.println(pVector.get(i).toString());
                                pw.println("}");
                                putCloseKey = false;
                            } else {
                                pw.println(pVector.get(i).toString());
                            }
                        } else {
                            pw.println(pVector.get(i).toString());
                        }
                    }
                }

            } catch (Exception e) {
            } finally {
                try {
                    // Nuevamente aprovechamos el finally para 
                    // asegurarnos que se cierra el fichero.
                    if (null != fichero) {
                        fichero.close();
                    }
                } catch (Exception e2) {
                }
            }
        } else {
            // Reportar que hay errores y no se puede generar el intermedio
        }
    }
}
