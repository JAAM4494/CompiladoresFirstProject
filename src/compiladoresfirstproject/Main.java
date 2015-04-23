package compiladoresfirstproject;

import java.io.File;

/**
 *
 * @author JAAM 
 */
public class Main {

    /*
     * @param args the command line arguments
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    public static void main(String[] args) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
            
            //generarScanner();
             new VentanaPrincipal().run();
             //generarParser();     
        
    }

    // Metodo que genera el analizador lexico, usando jFlex
    private static void generarScanner() {
        String path = "src/compiladoresfirstproject/scanner.flex";
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
        opciones[1] = "src/compiladoresfirstproject/";
        // habilita la opcion de nombre
        opciones[2] = "-parser";
        // nombre dela clase del parser
        opciones[3] = "myParser";
        // path donde se encuentra el archivo parser.cup
        opciones[4] = "src/compiladoresfirstproject/parser.cup";
        try {
            java_cup.Main.main(opciones);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
