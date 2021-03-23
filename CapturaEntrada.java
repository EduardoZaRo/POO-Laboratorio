import java.util.*;

public class CapturaEntrada{
	public static int capturarEntero(String msg){
		Scanner sc = new Scanner(System.in);
		System.out.println("" + msg+":");
		return (sc.nextInt());
	}
	public static String capturarString(String msg){
		Scanner sc = new Scanner(System.in);
		System.out.println("" + msg+":");
		return (sc.nextLine());		
	}
	public static float capturarFloat(String msg){
		Scanner sc = new Scanner(System.in);
		System.out.println("" + msg+":");
		return (sc.nextFloat());		
	}
	public static double capturarDouble(String msg){
		Scanner sc = new Scanner(System.in);
		System.out.println("" + msg+ ":");
		return (sc.nextDouble());
	}
}