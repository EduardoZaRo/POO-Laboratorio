public class Veterinario{
	private String nombre;
	private double salario = 0;
	public Veterinario(String nombre){
		this.nombre = nombre;
	}
	public void vacunar(Animal animal){
		if(animal.getVacunado() == true){
			System.out.println(animal.getNombre() + " ya esta vacunado!");
		}
		else{
			animal.setVacunado(true);
			System.out.println(animal.getNombre() + " fue vacunado, " + nombre + " gano 100$!");
			salario += 100;
		}
	}
	public void alimentarAnimal(Animal animal){
		if(animal.getAlimentado() == true){
			System.out.println(animal.getNombre() + " ya esta alimentado!");
		}
		else{
			animal.comer();
			System.out.println(animal.getNombre() + " fue alimentado, " + nombre + " gano 50$!");
			salario += 50;
		}
	}
	public void setNombre(String nombre){this.nombre = nombre;}
	public String getNombre(){return nombre;}
	public double getSalario(){return salario;}
}