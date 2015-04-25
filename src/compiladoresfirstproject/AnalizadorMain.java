/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoresfirstproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JAAM
 */
public class AnalizadorMain {

    public static boolean canGenerateCode = true;

    // Metodo principal del compilador, se encarga del procesamiento de la entrada
    public void procesarEntrada(String pPathEntrada) {

        try {
            canGenerateCode = true;
            myLexer AnalizadorLexico = new myLexer(new FileReader(pPathEntrada));
            myParser AnalizadorSintactico = new myParser(AnalizadorLexico);
            VentanaPrincipal.mostrarSalida("***********  RESUMEN PROCESAMIENTO ***********");
            AnalizadorSintactico.parse();
            //Symbol currToken;
            //   do {
            // currToken = AnalizadorLexico.next_token();
            //} while (currToken.sym != sym.EOF);
            System.out.println("Fin de escaneo..!!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Metodo encargado de generar el código final .ino
    public void GenerarCodigo() {
        if (canGenerateCode == true) {
            Traductor newTrans = new Traductor();
        } else {
            //reportar revisar modulo errores
            Main.mainWindow.rotularError();
        }
    }
}
