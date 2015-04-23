/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoresfirstproject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author StevenJM
 */
public class TablaSim {
    
    private FileWriter fichero = null;
    private PrintWriter pw = null;
    
    public TablaSim(){

        try
        {
            fichero = new FileWriter("src/outputs/tabla.html");
            pw = new PrintWriter(fichero);
            initFile(); 
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
        pw.println("<title> Tabla Simbolos </title>");
        pw.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
        pw.println("</head>");
        pw.println("<body>"); 
        pw.println("<table style=\"width:100%\">");
        pw.println("<thead>");
        pw.println("<tr>");
        pw.println("<th>Simbolo</th>");
        pw.println("<th>Valor</th>");
        pw.println("</tr>");
        pw.println("</thead>"); 
        pw.println("<tbody>");  
        pw.close();
    }
    
    public void writeSymbol(String pSim, int pValor){
            FileWriter fichero = null;
             PrintWriter pw = null;
            try
        {
            fichero = new FileWriter("tabla.html",true);
            pw = new PrintWriter(fichero);
            pw.write("<tr>");
            pw.write("<th>"+pSim+"</th>");
            pw.write("<th>"+pValor+"</th>"); 
            pw.write("</tr>");
            pw.close();
            //System.out.println("Sim: "+pSim+", Valor: "+pValor);
 
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
    
}
