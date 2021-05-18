import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        //Lectura del archivo csv
        CSV csv1 = new CSV();
        csv1.leerCSV();
        
        //Creacion de los alumnos y sus calificaciones
        Alumno a1 = new Alumno("Irvin",1270771);
        a1.setCalificaciones();
        a1.calcularPromedio();
        a1.mostrarDatos();
        Alumno a2 = new Alumno("Eduardo",1270779);
        a2.setCalificaciones();
        a2.calcularPromedio();
        a2.mostrarDatos();
        Alumno a3 = new Alumno("Zavala",6969568);
        a3.setCalificaciones();
        a3.calcularPromedio();
        a3.mostrarDatos(); 
        
        //Se escriben los datos mostrados en un archivo txt llamado practica9.txt
        a1.escribirAlumno(a1);
        a2.escribirAlumno(a2);
        a3.escribirAlumno(a3);
        System.out.println("Se han guardado los datos con exito!");
    }

}
//C:/Users/irvin/Desktop/Practica 9/src/practica9.csv
class Alumno implements Serializable{
    protected String nombre;
    protected int matricula;
    protected float promedio;
    protected Calificaciones calificaciones[];    
    public void calcularPromedio(){
        float suma = 0;
        for(int i = 0; i < 7; i++){
            suma += this.calificaciones[i].calificacion;
        }		
        promedio = (float) suma/7;   
    }
    public Alumno(String nombre, int matricula) {
        this.nombre = nombre;
        this.matricula = matricula;
    }  
    public void setCalificaciones(){
	calificaciones = new Calificaciones[7];
	for (int i = 0; i < 7; i++){
            calificaciones[i]= new Calificaciones();
            calificaciones[i].materia = Captura.capturarString("Nombre de materia");
            calificaciones[i].calificacion = Captura.capturarEntero("Calificacion");
            if(calificaciones[i].calificacion < 0 || calificaciones[i].calificacion > 100){
		System.out.println("Calificacion no valida :(");
                    do{
			calificaciones[i].calificacion = Captura.capturarEntero("Ingresa calificacion valida:");

                    }while(calificaciones[i].calificacion < 0 || calificaciones[i].calificacion > 100);
            }        
	} 
}
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }  
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    public String getNombre() {
        return nombre;
    }
    public int getMatricula() {
        return matricula;
    }
    public float getPromedio() {
        return promedio;
    }
    public void mostrarCalificaciones(){
        for(int i = 0; i < this.calificaciones.length;i++){
            System.out.println(this.calificaciones[i].materia+":"+this.calificaciones[i].calificacion+"\n");
        }
    }
    public void mostrarDatos(){
        System.out.println("El alumno se llama "+nombre+", su matricula es "+matricula+" y el promedio es "+getPromedio());
        mostrarCalificaciones();
    }
    public Calificaciones[] getCalificaciones(){
	return calificaciones;
    }
    
    public void escribirAlumno(Alumno a){
        try {
            Writer escribir = null;
            escribir = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("practica9.txt",true), "UTF-8"));
            escribir.write("Nombre:"+a.getNombre()+"\n");
            escribir.write("Matricula:"+String.valueOf(a.getMatricula())+"\n");

            
            for(int i = 0; i < 7; i++){
                escribir.write(String.valueOf(a.getCalificaciones()[i].calificacion)+"\t");
            }
            escribir.write("\n");
            escribir.write("Promedio:"+String.valueOf(a.getPromedio()));
            escribir.write("\n\n");
            escribir.close();
        }
        catch (Exception e) {
            System.out.println("Error al escribir");
        }        
    }
     
} 
class Calificaciones implements Serializable{
	String materia;
	int calificacion;
	float promedio;

	public void setMateria(String mat){
		materia = mat;
	}
	public void setCalificacion(int cal){
		cal = calificacion;
	}
	public String getMateria(){
		return materia;
	}
        
}
class CSV{
    public void leerCSV(){
        try{
           BufferedReader reader = new BufferedReader(new FileReader("practica9.csv"));
           String line = null;
           while((line = reader.readLine()) != null){
               String [] parts = line.split(",");
               int totalParts = parts.length;
               for(int i = 0; i < totalParts; i++){
                   System.out.println(parts[i]+"\n");
               }
           }
       }
       catch(IOException e){
           e.printStackTrace();
       }    
    }
}
class Captura{
	public static int capturarEntero(String msg){
            int j,a = 0;    
            do{
                try{
                    Scanner sc = new Scanner(System.in);
                    System.out.println("" + msg+":");
                    a = sc.nextInt();
                    j = 5;
                }
                catch(Exception e){
                    System.out.println("Pusiste otro tipo de dato!");
                    j = 3;
                }                   
            }while(j == 3);
            return (a);
	}
	public static String capturarString(String msg){
            Scanner sc = new Scanner(System.in);
            System.out.println("" + msg+":");
            return(sc.nextLine());	
	}
}

