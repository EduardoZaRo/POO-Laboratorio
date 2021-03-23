//A lo que investigue, lo de cuenta solo es accesible por banco porque tiene visibilidad package, por lo que protected funciona
import java.util.ArrayList;
public class Banco{
	ArrayList<Cuenta> c = new ArrayList<Cuenta>();
	int num = 0;
	public void setCuenta(){
	  	Cuenta cuenta = new Cuenta();
		cuenta.nombre = CapturaEntrada.capturarString("Nombre");
		cuenta.apellido = CapturaEntrada.capturarString("Apellido");
		cuenta.id = CapturaEntrada.capturarEntero("ID");
		cuenta.dinero = CapturaEntrada.capturarFloat("Saldo");
	  	c.add(cuenta);
	  	System.out.println("Para un deposito tendra que ir a la opcion 2, ingresar ID y escoger la operacion");
	  	num++;
	} 	
	public void imprimirCuentas(){
		for(int i = 0; i < num; i++){
			System.out.println("Nombre:" + c.get(i).nombre + ", ID: " + c.get(i).id);
		}
	}
	public void buscarCuentaOperaciones(int id){
		int fallidos = 0, seguir = 0;
    	for(int i = 0; i < num; i++){
    		if(id == c.get(i).id){
    			System.out.println("La cuenta es de : " + c.get(i).nombre);
    		}
    		else{fallidos++;}
    		seguir = 1;
    	}		
    	if(fallidos == num){System.out.println("Ninguna cuenta posee ese ID"); }
    	else{


	    	if(seguir == 1){
	    		int opc = CapturaEntrada.capturarEntero("Deseas realizar un retiro o deposito? 1)Si 2)No 3)Salir");
	    		if(opc == 1){
	    			
					do{
						System.out.println("1)Retiro 2)Deposito");
					    opc = CapturaEntrada.capturarEntero("Opcion: ");
					    switch(opc){
					    	case 1:
						    	for(int i = 0; i < num; i++){
						    		if(id == c.get(i).id){
						    			float retiro = CapturaEntrada.capturarFloat("Cantidad a retirar");
						    			
						    			if((c.get(i).dinero - retiro) >= 0){
						    				c.get(i).dinero = c.get(i).dinero - retiro;
						    				System.out.println("El balance ahora es" + c.get(i).dinero);
						    			}
						    			else{System.out.println("No es posible hacer la transaccion :(");}
						    		}
						    		
						    	}
					    	
					    	break;
					    	case 2:
						    	for(int i = 0; i < num; i++){
						    		if(id == c.get(i).id){
						    			float deposito = CapturaEntrada.capturarFloat("Cantidad a depositar");
						    			c.get(i).dinero = c.get(i).dinero + deposito;
						    			System.out.println("El balance ahora es" + c.get(i).dinero);
						    		}
						    		
						    	}

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
	    }
	}
}
class Cuenta{
	protected String nombre, apellido;
	protected int id;
	protected float dinero;

	protected void setNombreCompleto(String nombre, String apellido){
		this.nombre = nombre;
		this.apellido = apellido;
	}
	protected void setID(int id){
		this.id = id;
	}
	protected void setDinero(int dinero){
		this.dinero = dinero;
	}
	protected String getNombre(){
		return nombre;
	}
	protected String getApellido(){
		return apellido;
	}
	protected int getID(){
		return id;
	}
	protected float getDinero(){
		return dinero;
	}
}