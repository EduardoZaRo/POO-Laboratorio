public class Elefante extends Animal{
	public Elefante(String nombre, int edad, boolean vacunado, boolean alimentado){
		super(nombre,edad,vacunado, alimentado);
	}
	@Override
	public void comer(){System.out.println("El elefante a comido cosas de elefantes! (Para mas info preguntarle al elefante)"); alimentado = true;}
	public void hacerSonido(){System.out.println(nombre+" a barritado(Sonido de elefante si no saben chino)");}
}