import java.util.Scanner;
import java.util.Random;
public class Persona{
	String CURP = "", RFC = "";
	String nombre, apellido_p, apellido_m, sexo, entidad_federativa, fecha;
	public Persona(){
	}
	public Persona(String nombre, String apellido_p, String apellido_m, String sexo, String fecha, String entidad_federativa){
		this.nombre = nombre;
		this.apellido_p = apellido_p;
		this.apellido_m = apellido_m;
		

		if(sexo.length() != 1){
			System.out.println("Sexo no valido :(");
			do{
				sexo = Captura.capturarString("Ingresa sexo valido:");

			}while(sexo.length() != 6);				
		}
		if(fecha.length() != 6){
			System.out.println("Fecha no valida :(");
			do{
				fecha = Captura.capturarString("Ingresa fecha valida:");

			}while(fecha.length() != 6);				
		}
		if(entidad_federativa.length() != 2){
			System.out.println("Entidad federativa no valida :(");
			do{
				fecha = Captura.capturarString("Ingresa entidad federativa valida:");

			}while(entidad_federativa.length() != 6);				
		}
		this.sexo = sexo;
		this.fecha = fecha;
		this.entidad_federativa = entidad_federativa;

	}

	//INICIALES-FECHA-SEXO-ENTIDAD-CONSONANTES-2 ALEATORIOS
	public String crearCURP(){
		CURP = String.valueOf(apellido_p.charAt(0)) + String.valueOf(apellido_p.charAt(1)) + String.valueOf(apellido_m.charAt(0)) +
		       String.valueOf(nombre.charAt(0)) + String.valueOf(nombre.charAt(0)) + fecha + sexo + entidad_federativa;

		CURP += primeraConsonante(apellido_p) + primeraConsonante(apellido_m) + primeraConsonante(nombre);

		Random gen = new Random();
		CURP += String.valueOf(gen.nextInt(10))+String.valueOf(gen.nextInt(10));
		return CURP.toUpperCase();

	} 
	public String primeraConsonante(String palabra){
		String devolver = "";
		for(int i = 1; i < palabra.length(); i++){
			if((String.valueOf(palabra.charAt(i)).toUpperCase().equals("A") ||String.valueOf(palabra.charAt(i)).toUpperCase().equals("E") 
				||String.valueOf(palabra.charAt(i)).toUpperCase().equals("I")||String.valueOf(palabra.charAt(i)).toUpperCase().equals("O")||
				String.valueOf(palabra.charAt(i)).toUpperCase().equals("U"))){
				continue;
			}
			else{
				devolver = String.valueOf(palabra.charAt(i)); 
				break;
			}
		}		
		return devolver;
	}
	//PRIMERAS 2 LETRAS DE PATERNO- PRIMERAS 2 LETRAS MATERNO - PRIMERA LETRA NOMBRE- AAMMDD - 3 RANDOM
	public String crearRFC(){
		RFC = String.valueOf(apellido_p.charAt(0)) + String.valueOf(apellido_p.charAt(1)) + String.valueOf(apellido_m.charAt(0)) +
		       String.valueOf(apellido_m.charAt(1)) + String.valueOf(nombre.charAt(0)) + fecha;
		Random gen = new Random();
		RFC += String.valueOf(gen.nextInt(10)) + String.valueOf(gen.nextInt(10))  + String.valueOf(gen.nextInt(10)) ;
		return RFC.toUpperCase();
	} 	
}