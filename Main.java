/*¡Te has vuelto muy famoso en los últimos años al crear robots complejos capaces de hacer cosas que pensábamos que
solo los humanos podían hacer! Hoy, has decidido hacer el primer Robot Chef completamente funcional que existe. Para
eso, tendrá que darle la capacidad de cortar, picar verduras y carne, cocinar un buen guiso con los ingredientes y servirlo
en un tazón. Sin embargo, en el proceso de hacer tu Magnum Opus, has decidido crear también algunos pequeños
asistentes robóticos muy capaces de una sola tarea para ayudar a su Robot Chef. Creaste un mini robot con 2 cuchillos
para las manos, muy eficiente para preparar la carne y las verduras, pero completamente inútil para cocinarlo o servirlo y
un mini robot con manos calientes, increíble para cocinar a la temperatura adecuada pero terrible con cuchillos o para
servir. Después de mucho tiempo, finalmente lo lograste. Creó el primer robot de cocina que existió y algunos asistentes
para ayudarlo. Todos tienen una GUI muy moderna que le indica el nivel de batería restante, su nombre, identificación y la
última acción que hicieron antes de que se sirva el plato. Esta noche es la noche, ¡tienes que ir y mostrarles a los críticos
quién manda! ¡Instruye a tus robots con gracia para cocinar todos los platos que pidan!

1. Haz un menú en el que puedas seleccionar un robot para controlar y, después de eso, la acción que quieres que
realice. Todos ellos prepararán el mismo plato y, como tal, deberás coordinarlos con cuidado. No se puede
cocinar sin los ingredientes preparados y no se puede servir un plato que no esté cocido.
2. Si sigue el procedimiento correctamente (preparar ingredientes -> cocinar -> servir), imprimirá un mensaje que
dice "¡Hurra! El plato estaba delicioso".
3. Si se perdió uno de los primeros 2 pasos o si un robot hizo algo que no es capaz de imprimir un mensaje de error
personalizado
--Usar interfaces, clases abstractas y herencia--
*/
import java.util.Scanner;
public class Main{
	public static void main(String[] args){
		RobotPicador rp1 = new RobotPicador();
		RobotCocinador rc1 = new RobotCocinador();
		RobotServidor rs1 = new RobotServidor();
		Verduras v1 = new Verduras();
		Carne c1 = new Carne();
		Platillo p1 = new Platillo();
		int salir = 1;
		System.out.println("Si quiere salir ingrese 0");
		do{
			System.out.println("1)Seleccionar robot 2)Preparar plato 3)GUI robots");
			int opc = CapturaEntrada.capturarEntero("Ingresa opcion");
			switch(opc){
				case 1:
				System.out.println("1)Robot picador 2)Robot cocinador");
					int robot = CapturaEntrada.capturarEntero("Ingresa robot");
					switch(robot){
						case 1:
							System.out.println("Que deseas picar? 1)Carne 2)Verdura");
							int accion_picar = CapturaEntrada.capturarEntero("Opcion");
							if(accion_picar == 1){
								rp1.picarCarne(c1);
							}else{
								if(accion_picar == 2){
									rp1.picarVerdura(v1);
								}
								else{System.out.println("Opcion no valida");}							
							}

						break;
						case 2:
							System.out.println("Que deseas cocinar? 1)Carne 2)Verdura");
							int accion_cocinar = CapturaEntrada.capturarEntero("Opcion");
							if(accion_cocinar == 1){
								rc1.cocinarCarne(c1);
							}else{
								if(accion_cocinar == 2){
									rc1.cocinarVerdura(v1);
								}
								else{System.out.println("Opcion no valida");}							
							}	
						break;
						default:
							System.out.println("Opcion no valida!");
						break;
					}
				break;
				case 2:
					p1.armarPlato(v1,c1);
				break;
				case 3:
					rc1.GUI();
					rp1.GUI();
;				break;
				default:
					System.out.println("Opcion no valida!");
				break;
			}
			
			salir = CapturaEntrada.capturarEntero("Salir?");
		}while(salir != 0);

	}
}

abstract class Robot{
	private String nombre;
	protected int bateria = 100;
	private String identificacion;
	protected String ultimaAccion = "El robot no a hecho nada";
	public String getNombre(){return nombre;}
	public abstract void GUI();
	public abstract void ultimaAccion();


}
class RobotPicador extends Robot{
	private String nombre = "Robot Picador 9000";
	private String identificacion = "ROBOTOPICADOR01";

	public void picarVerdura(Verduras v1){
		v1.picar();
		System.out.println("Verdura picada!");
		bateria -= 10;
		ultimaAccion = "Lo ultimo que hizo el robot picador fue picar verdura";
	}
	public void picarCarne(Carne c1){
		c1.picar();
		System.out.println("Carne picada!");
		bateria -= 10;
		ultimaAccion = "Lo ultimo que hizo el robot picador fue picar carne";
	}
	@Override
	public void ultimaAccion(){
		System.out.println(ultimaAccion);
	}
	public void GUI(){
		System.out.println("El nombre del robot es "+nombre+" y su identificacion es "+identificacion);
		System.out.println("La bateria restante es "+bateria);
		ultimaAccion();
		System.out.println("------------------");
	}

}
class RobotCocinador extends Robot{
	private String nombre = "Robot Cocinador 8000";
	private String identificacion = "ROBOTOCOCINADOR07";
	public void cocinarVerdura(Verduras v1){
		if(v1.picado() == true){
			v1.cocinar(); 
			System.out.println("Verdura cocinada!");
			bateria -= 10;
			ultimaAccion = "Lo ultimo que hizo el robot cocinador fue cocinar verduras";
		}
		else{System.out.println("No se puede cocinar la verdura pq no ta picada");}

		
	}
	public void cocinarCarne(Carne c1){
		if(c1.picado() == true){
			c1.cocinar();
			System.out.println("Carne cocinada!");
			ultimaAccion = "Lo ultimo que hizo el robot cocinador fue cocinar carne";
			bateria -= 10;
		}
		else{System.out.println("No se puede cocinar la carne pq no ta picada");}
		
		
	}
	@Override
	public void ultimaAccion(){
		System.out.println(ultimaAccion);
	}
	public void GUI(){
		System.out.println("El nombre del robot es "+nombre+" y su identificacion es "+identificacion);
		System.out.println("La bateria restante es "+bateria);
		ultimaAccion();
		System.out.println("------------------");
	}
}


class Platillo{
	protected boolean armado = false;
	public void armarPlato(Verduras v1, Carne c1){
		if(v1.cocinado() == true && c1.cocinado() == true){
			System.out.println("Los ingredientes han sido emplatados y esta listo para servirse!");
			System.out.println("¡Hurra! El plato estaba delicioso");
			armado = true;
		}
		else{
			System.out.println("Los ingredientes no estan listos para ser unidos");
		}
	}
	public boolean estadoPlato(){
		if(armado == true){System.out.println("El platillo esta listo!");}
		else{System.out.println("El platillo no esta listo :(");}
		return armado;

	}
}
abstract class Ingredientes{
	boolean picado = false, cocinado = false;
	public abstract void picar();
	public abstract boolean picado();
	public abstract void cocinar();
	public abstract boolean cocinado();	
}


class Verduras extends Ingredientes{
	@Override
	public void picar(){picado = true;}
	public boolean picado(){return picado;}
	public void cocinar(){cocinado = true;}
	public boolean cocinado(){return cocinado;}	
}

class Carne extends Ingredientes{
	private String termino;
	@Override
	public void picar(){picado = true;}
	public boolean picado(){return picado;}
	public void cocinar(){
		System.out.println("Terminos disponibles: 1)blue 2)Medio 3)Bien cocido");
		int opc = CapturaEntrada.capturarEntero("En que termino quieres la carne?");
		do{
			switch(opc){
				case 1: 
					termino = "blue";
				break;
				case 2:
					termino = "medio";
				break;
				case 3:
					termino = "bien cocido";
				break;
				default:
					System.out.println("Opcion no valida");
				break;
			}
		}while(opc >3 || opc < 1);
		if(termino == "blue"||termino == "medio"||termino == "bien cocido"){
			cocinado = true;
		}

	}
	public boolean cocinado(){return cocinado;}	
}

class CapturaEntrada{
	public static int capturarEntero(String msg){
		Scanner sc = new Scanner(System.in);
		System.out.println("" + msg+":");
		return (sc.nextInt());
	}
	public static float CapturarFloat(String msg){
		Scanner sc = new Scanner(System.in);
		System.out.println("" + msg+":");
		return (sc.nextFloat());		
	}
	public static String CapturarString(String msg){
		Scanner sc = new Scanner(System.in);
		System.out.println("" + msg+ ":");
		return (sc.nextLine());
	}
}