import java.util.ArrayList;
public class Main{
	public static void main(String args[]){
		ArrayList<Animal> zoologico = new ArrayList<Animal>();
		Animal leon = new Leon("Leonilo",5,true,false);
		Animal elefante = new Elefante("Elefantino",5,true,false);
		Animal serpiente = new Serpiente("Serpientogro",5,false,false);
		Animal aguila = new Aguila("Aguilardo",5,true,false);
		Veterinario v = new Veterinario("Irvin");
		zoologico.add(leon);
		zoologico.add(elefante);
		zoologico.add(serpiente);
		zoologico.add(aguila);

		mostrarAnimales(zoologico);

		int opc;
		int num_animal;
		do{
			System.out.println("Opciones: 1)Agregar animal 2)Eliminar animal 3)Mostrar animales 4)Vacunar animal");
			System.out.println("5)Obtener salario del veterinario 6)Dar de comer a los animales 7)Salir");
			opc = Captura.capturarEntero("Ingresa opcion");
			switch(opc){
				case 1:
					agregar(zoologico);
				break;
				case 2:
					eliminar(zoologico);
				break;
				case 3:
					mostrarAnimales(zoologico);
				break;
				case 4:
					do{
						mostrarAnimales(zoologico);
						num_animal = Captura.capturarEntero("Ingresa el numero del animal a vacunar");
					}while(num_animal <= 0 || num_animal >= zoologico.size());
					v.vacunar(zoologico.get(num_animal));

				break;
				case 5:
					System.out.println("El salario de " + v.getNombre() + " es de " + v.getSalario());
				break;
				case 6:
					do{
						mostrarAnimales(zoologico);
						num_animal = Captura.capturarEntero("Ingresa el numero del animal a alimentar");
					}while(num_animal <= 0 || num_animal >= zoologico.size());
					v.alimentarAnimal(zoologico.get(num_animal));		
				break;
				case 7:
					System.out.println("Saliendo...");
				break;
				default:
					System.out.println("Opcion no valida:(");
				break;
			}
		}while(opc != 7);
	}
	public static void eliminar(ArrayList<Animal> a){
		mostrarAnimales(a);
		int indice = Captura.capturarEntero("Cual es el indice del animal que quieres eliminar?");
		if(indice >= 0 && indice <= a.size()){
			a.remove(indice);
		}
		else{
			System.out.println("Indice no valido");
		}
		mostrarAnimales(a);
	}
	public static void agregar(ArrayList<Animal> a){
		String nombre = Captura.capturarString("Ingresa el nombre del nuevo animal");
		int opc, edad = Captura.capturarEntero("Ingresa la edad del nuevo animal");
		boolean vacunado = Captura.capturarBooleano("Ingresa si esta vacunado el nuevo animal(true o false)");
		do{
			opc = Captura.capturarEntero("Que tipo de animal es? 1)Leon 2)Elefante 3)Serpiente 4)Aguila 5)Salir");
			switch(opc){
				case 1:
					Animal l = new Leon(nombre,edad,vacunado,false);
					a.add(l);
				break;
				case 2:
					Animal e = new Elefante(nombre,edad,vacunado,false);
					a.add(e);
				break;
				case 3:
					Animal s = new Serpiente(nombre,edad,vacunado,false);
					a.add(s);
				break;
				case 4:
					Animal aguila = new Aguila(nombre,edad,vacunado,false);
					a.add(aguila);
				break;
				case 5:
					System.out.println("Saliendo de agregar animal...");
				break;
				default:
					System.out.println("Opcion no valida:(");
				break;
			}
		}while(opc < 1 || opc >4);
		
	}
	public static void mostrarAnimales(ArrayList<Animal> a){
		System.out.println("Los animales del zoologico son:");
		for(int i = 0;i < a.size();i++){
			System.out.println(i+":"+a.get(i).getNombre());
		}
	}
}