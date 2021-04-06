public class Animal{
	protected String nombre;
	protected int edad;
	protected boolean vacunado, alimentado = false;
	public void comer(){System.out.println("*come*"); alimentado = true;}
	public void dormir(){System.out.println(nombre+" se a puesto a dormir!");}
	public void hacerSonido(){System.out.println(nombre+" a hecho un sonido de animal omg");}
	public Animal(String nombre, int edad, boolean vacunado, boolean alimentado){
		this.nombre = nombre;
		this.edad = edad;
		this.vacunado = vacunado;
		alimentado = false;
	}
	public void caracteristicas(){
		String v;
		if(vacunado == true){ v = "esta vacunado"; }
		else{ v = "no esta vacunado";}
		System.out.println("El animal se llama "+nombre+", tiene "+edad+" years y " + v);
	}
	public void setNombre(String nombre){this.nombre = nombre;}
	public void setEdad(int edad){this.edad = edad;}
	public void setVacunado(boolean vacunado){this.vacunado = vacunado;}	
	public void setAlimentado(boolean alimentado){this.alimentado = alimentado;}
	public String getNombre(){return nombre;}
	public int getEdad(){return edad;}
	public boolean getVacunado(){return vacunado;}
	public boolean getAlimentado(){return alimentado;}
}