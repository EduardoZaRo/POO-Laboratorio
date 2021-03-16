/*Practica herencia:  Angry Birds
Se creara una clase padre llamada "Pajaro" con caracteristicas generales
de un personaje de angry birds
Crear subclases para 3 pajaros con sus caracteristicas especiales
Los metodos de las subclases pueden solo imprimir mensajes dependiendo de las
acciones
Crear un arreglo de 3 objetos
Probar los resultados en el programa
*/
import java.util.Scanner;
public class Main{
    public static void main(String args[]){
    	Pajaro[] pajaros = new Pajaro[]{new Rojo(), new Amarillo(), new BombBird()};
    	System.out.println("Bienvenido al nivel de piedra!");
    	System.out.println("Todos los pajaros " + pajaros[0].enojado + " estan enojados y usan la resortera " + pajaros[2].resortera);
    	System.out.println("Aves disponibles:");
    	System.out.println("1) Rojo 2) Amarillo 3) BombBird 4)Salir XD");
    	Scanner input = new Scanner(System.in);
    	int opc, acierto;
    	do{
    		opc = CapturaEntrada.capturarEntero("Selecciona tu ave!");
    		switch(opc){
    			case 1:
    				pajaros[0].lanzar();
    				acierto = pajaros[0].acierto();
    				if(acierto == 1){
    					Rojo.ataque();
    					System.out.println("Y no fue efectivo :(");
    					
    				}
    				
    			break;
    			case 2:
    				pajaros[1].lanzar();
    				acierto = pajaros[1].acierto();
    				if(acierto == 1){
    					Amarillo.ataque();
    					System.out.println("Y no fue efectivo :(");
    					
    				}
    				
    			break;
    			case 3:
    				pajaros[2].lanzar();
    				acierto = pajaros[2].acierto();
    				if(acierto == 1){
    					BombBird.ataque();
    					System.out.println("Y fue muy efectivo :D");
    					
    				}
    				
    			break;
    			case 4:
    				System.out.println("Nos vemos!");
    			break;
                default: 
                    System.out.println("Opcion no valida :(");
                break;
    		}
    	}while(opc != 4);
    }
}