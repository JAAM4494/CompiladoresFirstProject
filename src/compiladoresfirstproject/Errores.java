/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoresfirstproject;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author StevenJM
 */
public class Errores {
    private FileWriter fichero = null;
    private PrintWriter pw = null;
    
    public Errores(){    }

    private void initFile() {
        pw.println("<!DOCTYPE html>");
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title> Errores </title>");
        pw.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
        pw.println("</head>");
        pw.println("<body>");   
    }
    
    public void reportarErrores(ArrayList<String> pErrSintactico, ArrayList<String> pErrSemantico){
        
        try
        {
            fichero = new FileWriter("src/outputs/errores.html");
            pw = new PrintWriter(fichero);
            initFile();
            if(pErrSintactico.isEmpty()){
                pw.println("<div class=\"Errores\">Sintactico");
                pw.println("<div class=\"Err\">");
                pw.println("<p>NO HUBO ERRORES</p>");
                pw.println("</div>");
                pw.println("</div>");
            }
            else{
                pw.println("<div class=\"Errores\">Sintactico");
                pw.println("<div class=\"Err\">");
                for (int i = 0; i < pErrSintactico.size(); i++)
                    pw.println("<p>"+pErrSintactico.get(i)+"</p>");
                pw.println("</div>");
                pw.println("</div>");
            }
            if(pErrSemantico.isEmpty()){
                pw.println("<div class=\"Errores\">Semantico");
                pw.println("<div class=\"Err\">");
                pw.println("<p>NO HUBO ERRORES</p>");
                pw.println("</div>");
                pw.println("</div>");
            }
            else{
                pw.println("<div class=\"Errores\">Semantico");
                pw.println("<div class=\"Err\">");
                for (int i = 0; i < pErrSemantico.size(); i++)
                    pw.println("<p>"+pErrSemantico.get(i)+"</p>");
                pw.println("</div>");
                pw.println("</div>");
            }
            endFile();
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }

    private void endFile() {
        pw.println("</body>");
        pw.println("</html>");
    }   
}

