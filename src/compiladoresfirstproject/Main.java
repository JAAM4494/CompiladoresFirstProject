/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoresfirstproject;

import java.io.File;

/**
 *
 * @author JAAM
 * Testing Commit
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Metodos para generar los .java de los analizadores
        // los comentan despues de generarlos (las 2 lineas de abajo)
        //generarScanner();
        //generarParser();
        //---------------------------------------------------
        
       // new Interfaz().setVisible(true);
    }

    // Metodo que genera el analizador lexico, usando jFlex
    private static void generarScanner() {
        String path = "C:/Users/JAAM/Documents/NetBeansProjects/CompiladoresFirstProject/src/compiladoresfirstproject/scanner.lex";
        File file = new File(path); // path -> donde se encuentra el archivo scanner.lex
        jflex.Main.generate(file);
    }

    //Metodo que genera el analizador sintáctico y la tabla de simbolos usando jCup
    private static void generarParser() {
        String opciones[] = new String[5];
        // habilita la opcion de guardar en directorio
        opciones[0] = "-destdir";
        // path donde se va guardar
        opciones[1] = "C:/Users/JAAM/Documents/NetBeansProjects/CompiladoresFirstProject/src/compiladoresfirstproject/";
        // habilita la opcion de nombre
        opciones[2] = "-parser";
        // nombre dela clase del parser
        opciones[3] = "Parser";
        // path donde se encuentra el archivo parser.cup
        opciones[4] = "C:/Users/JAAM/Documents/NetBeansProjects/CompiladoresFirstProject/src/compiladoresfirstproject/parser.cup";
        try {
            java_cup.Main.main(opciones);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
