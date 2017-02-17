import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.PrintWriter;
import java.io.File;

/**
 *
 * @author miguelcuellar
 */
//&p-Totales
class Partes{
  public String Tipo;
  public String Nombre;
  public int T = 0;
  public int I = 0;
  public int B = 0;
  public int D = 0;
  public int M = 0;
  public int A = 0;
//&i
  public Partes(){
    Tipo = "";
    Nombre = "";
    I = 0;
    T = 0;
    B = 0;
    D = 0;
    M = 0;
    A = 0;
 }
 //&i
 public Partes(String tipo,String n, int i, int t, int b,int d,int m,int a){
   Tipo = tipo;
   Nombre = n;
   I = i;
   T = t;
   B = b;
   D = d;
   M = m;
   A = a;
}

//&i
 public void Inicializar(){
   Nombre = "";
   I = 0;
   T = 0;
   B = 0;
   D = 0;
   M = 0;
   A = 0;
 }
//&i
public String getNombre(){
  return Nombre;
}
//&i
 public void setNombreParte(String n){
   this.Nombre = n;
 }
//&i
 public int getT(){
   return T;
 }
//&i
 public void masT(){
   this.T++;
 }
//&i
 public int getI(){
   return I;
 }
//&i
 public void masI(){
   this.I++;
 }
//&i
 public void setB(int b){
   B = b;
 }
//&i
 public int getB(){
   return B;
 }
//&i
 public int getD(){
   return D;
 }
//&i
 public int getM(){
   return M;
 }
//&i
public int getA(){
  return A;
}
//&i
 public void CalcularA(){
   A = T - B + D;
 }
}
//&p-Totales
 class Totales{
   //&b=20
   public int iCantArchivos = 0;
   public int iCantLineasBlancas = 0;
   public int iCantLineasContenido = 0;
//&i
  public Totales(){
     iCantArchivos = 0;
     iCantLineasBlancas = 0;
     iCantLineasContenido = 0;
   }
//&i
   public void agregarBlancas(int b){
     iCantLineasBlancas += b;
   }
//&i
   public void agregarContenido(int c){
     iCantLineasContenido += c;
   }
//&i
   public void agregarArchivo(){
     iCantArchivos++;
   }
//&i
   public int getNumArchivos(){
     return iCantArchivos;
   }
//&i
   public int getLineasBlancas(){
     return iCantLineasBlancas;
   }
//&i
   public int getLineasContenido(){
     return iCantLineasContenido;
   }
 }
//&p-DatosArchivo
 class DatosArchivo{
   //&b=33
   public String sNombre = " ";
   public int iLineasBlanco = 0;
   public int iLineasContenido = 0;
   public  ArrayList<Partes> parte = new ArrayList<Partes>();;
//&i
   public DatosArchivo(){
     sNombre = " ";
     iLineasBlanco = 0;
     iLineasContenido = 0;
   }
//&i
   public DatosArchivo(String n){
     sNombre = n;
     iLineasBlanco = 0;
     iLineasContenido = 0;
   }
//&i
   public void setNombre(String n){
     sNombre = n;
   }
//&i
   public String getNombre(){
     return sNombre;
   }
//&i
   public int getLineasBlanco(){
     return iLineasBlanco;
   }
//&i
   public int getLineasContenido(){
     return iLineasContenido;
   }
//&i
  public boolean Comentario(String linea){
    if(linea.trim().contains("//")){
      if(linea.trim().charAt(0)=='/' && linea.trim().charAt(1) == '/'){
      return true;
      }else{
      return false;
      }
    }
    return false;
  }
//&i
  public void ImprimirPartesBase(PrintWriter w){
    for (int iy = 0; iy<parte.size(); iy++) {
      if(parte.get(iy).Tipo=="Base"){
        w.println(parte.get(iy).Nombre+ " : " +"T= "+parte.get(iy).T+ ", I= "+parte.get(iy).I+", B= "+parte.get(iy).B+", D= "+parte.get(iy).D+ ", M= "+parte.get(iy).M+", A= "+parte.get(iy).A);
      System.out.println(parte.get(iy).Nombre+ " : " +"T= "+parte.get(iy).T+ ", I= "+parte.get(iy).I+", B= "+parte.get(iy).B+", D= "+parte.get(iy).D+ ", M= "+parte.get(iy).M+", A= "+parte.get(iy).A);
      }
    }
  }
  //&i
  public void ImprimirPartesNuevas(PrintWriter w){
    for (int iy = 0; iy<parte.size(); iy++) {
      if(parte.get(iy).Tipo=="Nueva"){
        w.println(parte.get(iy).Nombre+ " : " +"T= "+parte.get(iy).T+ ", I= "+parte.get(iy).I);
        System.out.println(parte.get(iy).Nombre+ " : " +"T= "+parte.get(iy).T+ ", I= "+parte.get(iy).I);
      }
  }
  }
//&i
  public void ImprimirPartesReusadas(PrintWriter w){
    for (int iy = 0; iy<parte.size(); iy++) {
      if(parte.get(iy).Tipo=="Reusada"){
        w.println(parte.get(iy).Nombre+ " : " +"T= "+parte.get(iy).T+ ", I= "+parte.get(iy).I+", B= "+parte.get(iy).B);
      System.out.println(parte.get(iy).Nombre+ " : " +"T= "+parte.get(iy).T+ ", I= "+parte.get(iy).I+", B= "+parte.get(iy).B);
      }
    }
  }
//&i
   public boolean Contar(){
     boolean Comentario = false;
     boolean variableString = false;
     boolean parteEncontrada = false;
     Partes parteNueva = new Partes();
     int i = 0;
     int b = 0;
     int t = 0;
     int d = 0;
     int m = 0;
     int a = 0;
     String n = "";
     String tipo = "";
     int iContPartes = 0;
     try {
          BufferedReader in = new BufferedReader(new FileReader(sNombre));
          String linea;
          while((linea = in.readLine()) != null) {
            if(linea.trim().contains("/*")){
              Comentario = true;
            }
            if(linea.trim().contains("/*") && linea.trim().contains("*/")){
              Comentario = true;
            }
            if(linea.trim().contains("//&p") && !Comentario){
              if(parteEncontrada){
                a = t - b + d ;
                if(b>0 && (m>0 || d>0 || a>0)){
                  tipo = "Base";
                }else if(b==0 && m==0 && d==0 && a>0){
                  tipo = "Nueva";
                }else if(b>0 && m==0 && d==0 && a==0){
                  tipo = "Reusada";
                }else{
                  tipo = "Error";
                }
                Partes nuevaParte = new Partes(tipo,n,i,t,b,d,m,a);
                parte.add(nuevaParte);
                n = "";
                i = t = b = d = m = 0;
              }
              n = (linea.trim().substring(5));
              parteEncontrada = true;
            }
            if(linea.trim().contains("//&i") && !Comentario){
              i++;
            }
            if(linea.trim().contains("//&d") && !Comentario){
              d += (Integer.parseInt(linea.trim().substring(5)));
            }
            if(linea.trim().contains("//&b") && !Comentario){
              b += (Integer.parseInt(linea.trim().substring(5)));
            }
            if(linea.trim().contains("//&m") && !Comentario){
              Pattern mateCompu = Pattern.compile("\"([^\"]*)\"");
              Matcher match = mateCompu.matcher(linea);
              while (match.find()) {
                if(match.group(1).contains("//&m")){
                  m--;
                }
              }
              m++;
            }

            if(linea.trim().equals("") || linea.trim().equals("\t") || linea.trim().equals(" ") || linea.trim().equals("{") || linea.trim().equals("}") || Comentario || Comentario(linea)) {
              iLineasBlanco++;
            }
            else {
              if (parteEncontrada) {
                t++;
              }
              iLineasContenido++;
            }
            if(linea.trim().contains("*/")){
              Comentario = false;
            }
          }
          a = t - b + d ;
          if(b>0 && (m>0 || d>0 || a>0)){
            tipo = "Base";
          }else if(b==0 && m==0 && d==0 && a>0){
            tipo = "Nueva";
          }else if(b>0 && m==0 && d==0 && a==0){
            tipo = "Reusada";
          }else{
            tipo = "Error";
          }
          Partes nuevaParte = new Partes(tipo,n,i,t,b,d,m,a);
          parte.add(nuevaParte);
          in.close();
          return true;
        }
        catch(IOException e){
          return false;
        }
   }
 }
 //&p-Programa2Calidad
public class Programa2Calidad {
//&i
    public static void main(String[] args) {
//&b=26
        ArrayList<DatosArchivo> Archivos = new ArrayList<DatosArchivo>();
        Totales total = new Totales();
        System.out.println("Ingrese los nombres de los archivos separados por espacios");
        Scanner nomArchivos = new Scanner(System.in);
        String  inputNombres = nomArchivos.nextLine();
        String[] nombres = inputNombres.split("\\s+"); //&m

        for (int ix = 0; ix<nombres.length; ix++) {
          DatosArchivo arch = new DatosArchivo();
          arch.setNombre(nombres[ix]);
          Archivos.add(arch);
        }
        for (int iy = 0; iy<Archivos.size(); iy++) {
          if (Archivos.get(iy).Contar()) {
            total.agregarBlancas(Archivos.get(iy).getLineasBlanco());
            total.agregarContenido(Archivos.get(iy).getLineasContenido());
            total.agregarArchivo();
            //&d-3


          }else{
            System.out.println("El archivo con nombre: "+Archivos.get(iy).getNombre()+"no existe o esta vacío.");
            Archivos.remove(iy);
          }

        }
      try{
          PrintWriter writer = new PrintWriter("ConteoLDC.txt", "UTF-8");

      writer.println("---------------------------");
      System.out.println("---------------------------");
      writer.println("Partes Base:");
      System.out.println("Partes Base:"); //&m
      for (int iy = 0; iy<Archivos.size(); iy++) {
        Archivos.get(iy).ImprimirPartesBase(writer);
      }
      writer.println("---------------------------");
      System.out.println("---------------------------");
      writer.println("Partes Nuevas:");
      System.out.println("Partes Nuevas:"); //&m
      for (int iy = 0; iy<Archivos.size(); iy++) {
        Archivos.get(iy).ImprimirPartesNuevas(writer);
      }
      writer.println("---------------------------");
      System.out.println("---------------------------");
      writer.println("Partes Reusadas:"); //&m
      System.out.println("Partes Reusadas:");
      for (int iy = 0; iy<Archivos.size(); iy++) {
        Archivos.get(iy).ImprimirPartesReusadas(writer);
      }

      /*System.out.println("Número de archivos analizados: " + total.getNumArchivos());
      System.out.println("Lineas en blanco: " + total.getLineasBlancas());*/
      writer.println("---------------------------");
      System.out.println("---------------------------");
      writer.println("Total LDC: " + total.getLineasContenido());
      System.out.println("Total LDC: " + total.getLineasContenido()); //&m
      writer.close();
      } catch (IOException e) {
      System.out.println("Error al escribir archivo.");
      }
    }

}
