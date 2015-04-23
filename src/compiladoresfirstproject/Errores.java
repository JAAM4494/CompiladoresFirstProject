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
    
    public Errores(String pTipoAnalisis, ArrayList<String> pErr){

        try
        {
            fichero = new FileWriter("src/outputs/index.html");
            pw = new PrintWriter(fichero);
            initFile();
            pw.println("<div class=\"Errores\">"+pTipoAnalisis);
            pw.println("<div class=\"Err\">");
            for (int i = 0; i < pErr.size(); i++)
                pw.println("<p>"+pErr.get(i)+"</p>");
            endFile();
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }

    private void initFile() {
        pw.println("<!DOCTYPE html>");
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title> Errores </title>");
        pw.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
        pw.println("</head>");
        pw.println("<body>");   
    }

    private void endFile() {
        pw.println("</div>");
        pw.println("</div>");
        pw.println("</body>");
        pw.println("</html>");
    }   
}

