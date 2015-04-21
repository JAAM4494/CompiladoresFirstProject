package serial;


import com.sun.jmx.snmp.Enumerated;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author aaroneg,vladimir,steven
 *
 */
public class ArduinoConnection{ 
    private FileWriter fichero = null;
    private PrintWriter pw = null;
    private Scanner sc = null;

    public ArduinoConnection() { 
        try
        {
            fichero = new FileWriter("robot.ino");
            pw = new PrintWriter(fichero);
            initFile(); 
            setupArduino();
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
        
        try {
            sc = new Scanner(new File("ej.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArduinoConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(sc.hasNext()){
            String line = sc.nextLine();  
            System.out.println(line);
            switch (line){
                case "DECLARAR":
                    declararVariable();
                    break;
                case "IF":
                    declararIf();
                    break;
                case "HAGA":
                    haga();
                    break;
                case "MIENTRAS":
                    mientras();
                    break;
                
            }
        }
    }
    
     private void initFile() {
        pw.println("#include <Servo.h>");
        pw.println("Servo ojoDerX; //8");
        pw.println("Servo ojoIzqX; //9");
        pw.println("Servo cabeza; //10");
        pw.println("Servo ojoDerY; //11");
        pw.println("Servo boca; //12");
        pw.println("Servo ojoIzqY; //4");  
    }
    
    public static void main(String[] args) {
        ArduinoConnection a= new ArduinoConnection();
    }

    private void setupArduino() {
        pw.println("void setup(){");
        pw.println("Serial.begin(9600);");
        pw.println("ojoDerX.attach(8);");
        pw.println("ojoDerX.write(90);");
        pw.println("ojoDerY.attach(11);");
        pw.println("ojoDerY.write(60);");
        pw.println("ojoIzqX.attach(9);");
        pw.println("ojoIzqX.write(90);"); 
        pw.println("ojoIzqY.attach(4);");
        pw.println("ojoIzqY.write(90);");
        pw.println("boca.write(110);");
        pw.println("cabeza.attach(10);");
        pw.println("cabeza.write(90);}");
        pw.println("void loop(){");
        
    }   
    
    private void declararVariable(){
        String var=sc.nextLine();
        FileWriter fichero = null;
        PrintWriter pw = null;
            try
        {
            fichero = new FileWriter("robot.ino",true);
            pw = new PrintWriter(fichero);
            pw.println("int "+var+";");
            pw.close();
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

    private void declararIf() {
        String con=sc.nextLine();
        FileWriter fichero = null;
        FileWriter fichero1 = null;
        PrintWriter pw = null;
            try
        {
            fichero = new FileWriter("robot.ino",true);
            pw = new PrintWriter(fichero);
            pw.println("if"+"("+con+"){");
            String line=sc.nextLine();
            line=sc.nextLine();
            fichero.close();
            if(line.equals("{")){
            while(!"}".equals(line)){   
                line=sc.nextLine();
                System.out.println(line);
            switch (line){
                case "MOVER":
                    mover();
                    break;
                case "DECIR":
                    decir();
                    break;                
           }
            }
            fichero = new FileWriter("robot.ino",true);
            PrintWriter pw1 = new PrintWriter(fichero);
            pw1.println("}");
            pw1.close();           
            pw.close();
            fichero.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

    }
    }

    private void mover() {
        String line=sc.nextLine();
        if(line.equals("CABEZA")){
            moverCabeza();
        }
        if (line.equals("BOCA")){
            moverBoca();
        }
        if(line.equals("OJOS")){
            moverOjos();
        }
    }
    
    private void haga() {
        FileWriter fichero = null;
        PrintWriter pw = null;
            try
        {
            fichero = new FileWriter("robot.ino",true);
            pw = new PrintWriter(fichero);
            pw.println("do");
            String line=sc.nextLine();
            if(line.equals("{")){
            pw.println("{");
            fichero.close();
            while(!"}".equals(line)){   
                line=sc.nextLine();
            switch (line){
                case "MOVER":
                    mover();
                    System.out.println("hola1");
                    break;
                case "DECIR":
                    decir();
                    System.out.println("hola2");
                    break;                
                    }
                }
           
            }
            fichero = new FileWriter("robot.ino",true);
            PrintWriter pw1 = new PrintWriter(fichero);
            pw1.println("}");           
            line=sc.nextLine();
            if(line.equals("MIENTRAS")){
                line=sc.nextLine();
                pw1.println("while"+"("+line+");");
            }
            pw1.close();
            fichero.close();
            pw.close();
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

    private void mientras() {
        String con=sc.nextLine();
        FileWriter fichero = null;
        PrintWriter pw = null;
            try
        {
            fichero = new FileWriter("robot.ino",true);
            pw = new PrintWriter(fichero);
            pw.println("while"+"("+con+")");
            fichero.close();
            String line=sc.nextLine();
            line=sc.nextLine();
            if(line.equals("{")){
                pw.println("{");
                while(!"}".equals(line)){   
                    line=sc.nextLine();
                    switch (line){
                        case "MOVER":
                            System.out.println("hola1");
                            mover();
                            break;
                        case "DECIR":
                            decir();
                            System.out.println("hola2");
                            break;                
                        }
                  }
           }
            fichero = new FileWriter("robot.ino",true);
            PrintWriter pw1 = new PrintWriter(fichero);
            pw1.println("}");
            pw1.close();           
            fichero.close();
            pw.close();
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
    
    private void decir() {
        String palabra=sc.nextLine();
        FileWriter fichero = null;
        PrintWriter pw = null;
            try
        {
            fichero = new FileWriter("robot.ino",true);
            pw = new PrintWriter(fichero);
            char msg=palabra.charAt(0);
            
            pw.println("Serial.println(\""+msg+"\");");
            pw.println("delay(20);");
            pw.close();
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

    private void moverOjos() {
        String dir=sc.nextLine();
        FileWriter fichero = null;
        PrintWriter pw = null;
            try
        {
            fichero = new FileWriter("robot.ino",true);
            pw = new PrintWriter(fichero);
            if(dir.equals("IZQUIERDA")){
                pw.println("ojoIzqX.write(175);");
                pw.println("ojoIzqY.write(90);");
                pw.println("ojoDerX.write(123);");
                pw.println("ojoDerY.write(60);");    
                pw.println("delay(20);");
            }
            if(dir.equals("DERECHA")){
                pw.println("ojoDerY.write(69);");
                pw.println("ojoDerX.write(20);");
                pw.println("ojoIzqY.write(90);");
                pw.println("ojoIzqX.write(45);");  
                pw.println("delay(20);");
            }
            if(dir.equals("ABAJO")){                
                pw.println("ojoDerY.write(10);");
                pw.println("ojoDerX.write(90);");
                pw.println("ojoIzqY.write(125);");
                pw.println("ojoIzqX.write(90);"); 
                pw.println("delay(20);");
            }
            if(dir.equals("ARRIBA")){
                pw.println("ojoDerY.write(110);");
                pw.println("ojoDerX.write(90);");
                pw.println("ojoIzqY.write(30);");
                pw.println("ojoIzqX.write(90);"); 
                pw.println("delay(20);");
            }
            pw.close();
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

    private void moverCabeza() {
        String dir=sc.nextLine();
        FileWriter fichero = null;
        PrintWriter pw = null;
            try
        {
            fichero = new FileWriter("robot.ino",true);
            pw = new PrintWriter(fichero);
            if(dir.equals("IZQUIERDA")){
                pw.println("cabeza.write(130);");   
                pw.println("delay(50);");
            }
            if(dir.equals("DERECHA")){
                pw.println("cabeza.write(40);"); 
                pw.println("delay(50);");
            }
            pw.close();
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
    
    private void moverBoca(){
        String dir=sc.nextLine();
        FileWriter fichero = null;
        PrintWriter pw = null;
            try
        {
            fichero = new FileWriter("robot.ino",true);
            pw = new PrintWriter(fichero);
            if(dir.equals("ARRIBA")){
                pw.println("boca.write(110);");   
                pw.println("delay(20);");
            }
            if(dir.equals("DERECHA")){
                pw.println("boca.write(140);"); 
                pw.println("delay(20);");
            }
            pw.close();
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
}