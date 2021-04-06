public class Leon extends Animal{
	public Leon(String nombre, int edad, boolean vacunado, boolean alimentado){
		super(nombre,edad,vacunado,alimentado);
	}
	@Override
	public void comer(){System.out.println("*el leon a comido carne jaja*"); alimentado = true; }
	public void hacerSonido(){System.out.println(nombre+" a rujido bien fuerte jaja que loco");}
}