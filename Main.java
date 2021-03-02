import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("\tDatos de la primera persona: ");
		Persona p1 = new Persona(Captura.capturarString("Ingresa nombre"), Captura.capturarString("Ingresa apellido paterno"),
			Captura.capturarString("Ingresa apellido materno"), Captura.capturarString("Ingresa sexo (H/M)"),
			Captura.capturarString("Ingresa fecha de nacimiento AAMMDD"),
			Captura.capturarString("Ingresa las 2 siglas de la entidad federativa"));
		System.out.println("\tDatos de la segunda persona: ");
		Persona p2 = new Persona(Captura.capturarString("Ingresa nombre"), Captura.capturarString("Ingresa apellido paterno"),
			Captura.capturarString("Ingresa apellido materno"), Captura.capturarString("Ingresa sexo"),
			Captura.capturarString("Ingresa fecha de nacimiento AAMMDD"),
			Captura.capturarString("Ingresa las 2 siglas de la entidad federativa"));
		System.out.println("\tDatos de la tercera persona: ");
		Persona p3 = new Persona(Captura.capturarString("Ingresa nombre"), Captura.capturarString("Ingresa apellido paterno"),
			Captura.capturarString("Ingresa apellido materno"), Captura.capturarString("Ingresa sexo"),
			Captura.capturarString("Ingresa fecha de nacimiento AAMMDD"),
			Captura.capturarString("Ingresa las 2 siglas de la entidad federativa"));

		System.out.println("El CURP de la primera persona es: " + p1.crearCURP());
		System.out.println("El RFC de la sgunda persona es: " + p2.crearRFC());
		System.out.println("El CURP y RFC de la tercera persona es: " + p3.crearCURP() + "\n" + p3.crearRFC());

	}
}
