import java.util.*;

public class CapturaEntrada{
	//sc son objetos
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
	public static double CapturarDouble(String msg){
		Scanner sc = new Scanner(System.in);
		System.out.println("" + msg+ ":");
		return (sc.nextDouble());
	}
}