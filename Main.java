/*
1. Utilizar los conceptos obtenidos en esta práctica para crear un programa que simule operaciones bancarias.
2. Haga una clase banco que pueda registrar nuevas cuentas.
permanecer todos privados y solo ser accesibles para el Banco.
4. Una "Cuenta" se compone del "nombre" del titular de la cuenta, el "saldo" que tiene esta cuenta y un "PIN" que
se utiliza para acceder al saldo de la cuenta.
5. Crear un menú en el que el usuario pueda crear una cuenta o acceder a una ya existente.
6. Al crear una cuenta se ingresará la información necesaria por parte del usuario para realizar el primer
depósito.
7. Para una cuenta ya existente, primero debe pedirle al usuario su PIN y luego dejar que retire o deposite como
mejor le parezca.
8. Recuerda validar las transacciones para que tu banco no pierda ninguno de sus recursos.
*/

import java.util.Scanner;
public class Main{
    public static void main(String args[]){
    	Banco bancomex = new Banco();
    	int opc, numcuentas;
    	do{
	    	System.out.println("Menu de opciones");
	    	System.out.println("1)Crear cuenta");
	    	System.out.println("2)Acceder a cuenta");
	    	System.out.println("3)Salir");
    		opc = CapturaEntrada.capturarEntero("Opcion: ");
    		switch(opc){
    			case 1:
    				bancomex.setCuenta();
    			break;
    			case 2:
    				bancomex.buscarCuentaOperaciones(CapturaEntrada.capturarEntero("Ingrese ID de la cuenta a buscar:"));
    			break;
    			case 3:
    				System.out.println("Nos vemos!");
    			break;
    			default:
    				System.out.println("Opcion no valida!");
    			break;
    		}
    	}while(opc != 3);
    }
}