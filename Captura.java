import java.util.*;

public class Captura{
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

	public static boolean capturarBooleano(String msg){
		Scanner sc = new Scanner(System.in);
		System.out.println("" + msg+":");
		return (sc.nextBoolean());
	}
}