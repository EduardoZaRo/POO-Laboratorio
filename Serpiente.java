public class Serpiente extends Animal{
	public Serpiente(String nombre, int edad, boolean vacunado, boolean alimentado){
		super(nombre,edad,vacunado,alimentado);
	}
	@Override
	public void comer(){System.out.println("La serpiente se trago un raton omaiga"); alimentado = true;}
	public void hacerSonido(){System.out.println(nombre+" a hecho sssssssss");}
}