public class Aguila extends Animal{
	public Aguila(String nombre, int edad, boolean vacunado, boolean alimentado){
		super(nombre,edad,vacunado, alimentado);
	}
	public void volar(){System.out.println("El aguila a volado!");}
	@Override
	public void comer(){System.out.println("La serpiente se trago una serpiente!"); alimentado = true;}
	public void hacerSonido(){System.out.println(nombre+" a hecho sssssssss");}
}