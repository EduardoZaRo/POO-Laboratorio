import java.util.Random;

public class Pajaro{
	String vuela = "No", enojado = "si", resortera = "basica";
	public void lanzar(){
		System.out.println("Se ha lanzado el pajaro!");
	}

	public int acierto(){
		Random a = new Random();
		int tiro = (int)a.nextInt(2);
		if(tiro == 0){
			System.out.println("El tiro fallo :(");
		}
		else{
			System.out.println("El tiro acerto :)");
		}
		return tiro;
	}
}