/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoresfirstproject;

import java.io.File;
import java.lang.reflect.Field;
//import java.io.StringReader;

/**
 *
 * @author JAAM Testing Commit
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    public static void main(String[] args) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        // Metodos para generar los .java de los analizadores
        // los comentan despues de generarlos (las 2 lineas de abajo)

       // generarScanner();
        new VentanaPrincipal().run();
        // generarParser();

    }

    // Metodo que genera el analizador lexico, usando jFlex
    private static void generarScanner() {
        String path = "C:/Users/JAAM/Documents/NetBeansProjects/CompiladoresFirstProject/src/compiladoresfirstproject/scanner.flex";
        File file = new File(path); // path -> donde se encuentra el archivo scanner.lex
        jflex.Main.generate(file);
    }

    //Metodo que genera el analizador sintáctico y la tabla de simbolos usando jCup
    private static void generarParser() {
        String opciones[] = new String[5];
        // habilita la opcion de guardar en directorio
        opciones[0] = "-destdir";
        // path donde se va guardar
        //C:/Users/JAAM/Documents/NetBeansProjects/CompiladoresFirstProject/src/compiladoresfirstproject
        //C:/Users/Francisco/Documents/NetBeansProjects/CompiladoresFirstProject/src/compiladoresfirstproject/
        opciones[1] = "C:/Users/JAAM/Documents/NetBeansProjects/CompiladoresFirstProject/src/compiladoresfirstproject/";
        // habilita la opcion de nombre
        opciones[2] = "-parser";
        // nombre dela clase del parser
        opciones[3] = "myParser";
        // path donde se encuentra el archivo parser.cup
        opciones[4] = "C:/Users/JAAM/Documents/NetBeansProjects/CompiladoresFirstProject/src/compiladoresfirstproject/parser.cup";
        try {
            java_cup.Main.main(opciones);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static String returnTokenName(int pIntToken) throws IllegalArgumentException, IllegalAccessException {
        String out = null;
        sym Bridge = null;
        Field fields[] = sym.class.getDeclaredFields();

        for (int i = 0; i < fields.length - 1; i++) {
            Field temp0 = fields[i];
            temp0.setAccessible(true);
            Object valueObject = temp0.get(Bridge);
            //System.out.println(value);
            // System.out.println("Variable Name is : " + fld[i].getName());
            if (pIntToken == (int) valueObject) {
                //System.out.println(temp0.getName());
                out = temp0.getName();
            }
        }
        return out;
    }

}
