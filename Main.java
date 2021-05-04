import java.util.ArrayList;
import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		System.out.println("Bienvenido al torneo de los 5 grandes!");
		Torneo torneo  = new Torneo("5 grandes", "Mexico", 5, 5,0);
		torneo.agregarEquipos();
		torneo.datosTorneo();

	}
}
class Equipo{
	String nombreEquipo, division, nombreEntrenador;	
	int torneosParticipados, torneosGanados, torneosPerdidos;
	ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	public Equipo(String nombreEquipo, String division, String nombreEntrenador, int torneosParticipados,
		int torneosGanados, int torneosPerdidos){
		this.nombreEquipo = nombreEquipo;
		this.nombreEntrenador = nombreEntrenador;
		this.division = division;
		this.torneosParticipados = torneosParticipados;
		this.torneosPerdidos = torneosPerdidos;
		this.torneosGanados = torneosGanados;
	}	
	int cantJugadores = 10;
	public void agregarJugadores(){
		String nombreJugador,posicion;
		int dorsal, puntosAnotados;
		float estatura;
		int i = 1,terminar = 1;
		int aux = -1;
		while(i < 11){
			do{
				nombreJugador = CapturaEntrada.CapturarString("Ingresa nombre del jugador "+i);
				posicion = CapturaEntrada.CapturarString("Ingresa posicion del jugador");
				dorsal = CapturaEntrada.capturarEntero("Ingresa numero del jugador");
				puntosAnotados = CapturaEntrada.capturarEntero("Ingresa la cantidad de puntos anotados por ese jugador");
				estatura = CapturaEntrada.CapturarFloat("Ingresa estatura del jugador (en mts)");
				if((estatura < 0) || (estatura > 4)||(puntosAnotados < 0)||(dorsal < 0)) System.out.println("Algun valor no es valido");
				else terminar = 0;
				if(dorsal == aux){System.out.println("El numero de jugador se repite"); terminar = 1;} 
				else aux = dorsal;

				String auxiliarNombre = nombreJugador;
				for(Jugador j:jugadores){
					if(j.getNombre().equals(auxiliarNombre)){
						System.out.println("El jugador no se puede repetir o estar en diferentes equipos");
						terminar = 1;
					}
					else auxiliarNombre = j.getNombre();
				}

			}while(terminar != 0);
			terminar = 1;
			i++;
			Jugador nuevoJugador = new Jugador(dorsal,puntosAnotados,estatura,nombreJugador,posicion);		
			jugadores.add(nuevoJugador);	
		}
	}
	public int puntosTotales(){
		int puntosTotales = 0;
		for(int i = 0; i < jugadores.size();i++){
			puntosTotales += jugadores.get(i).getPuntos();
		}
		return puntosTotales;
	}
	public void mostrarJugadores(){
		System.out.println("NOMBRE ------------ NUMERO DE JUGADOR");
		for(int i = 0; i < jugadores.size(); i++){
			System.out.println(jugadores.get(i).getNombre()+":"+jugadores.get(i).getDorsal());
		}
	}
	public void datosEquipo(){
		System.out.println(nombreEquipo +","+division);
	}
	public String getnombreEquipo(){return nombreEquipo;}
	public String getnombreEntrenador(){return nombreEntrenador;}
	public String getDivision(){return division;}
	public int gettorneosParticipados(){return torneosParticipados;}
	public int gettorneosPerdidos(){return torneosPerdidos;}
	public int gettorneosGanados(){return torneosGanados;}
}
class Jugador{
	int dorsal, puntosAnotados;
	float estatura;
	String nombreJugador, posicion;
	public Jugador(int dorsal, int puntosAnotados, float estatura, String nombreJugador,
		String posicion){
		this.dorsal = dorsal;
		this.puntosAnotados = puntosAnotados;
		this.estatura = estatura;
		this.nombreJugador = nombreJugador;
		this.posicion = posicion;
	}
	public String getNombre(){return nombreJugador;}
	public int getDorsal(){return dorsal;}
	public float getEstatura(){return estatura;}
	public String getPosicion(){return posicion;}
	public int getPuntos(){return puntosAnotados;}

}
class Torneo{
	String nombreTorneo, region;
	int numEquipos, partidosJugados, partidosPendientes;
	public Torneo(String nombreTorneo, String region, int numEquipos, int partidosPendientes,
		int partidosJugados){
		this.nombreTorneo = nombreTorneo;
		this.region = region;
		this.numEquipos = numEquipos;
		this.partidosPendientes = partidosPendientes;
		this.partidosJugados = partidosJugados;
	}
	ArrayList<Equipo> equipos = new ArrayList<Equipo>();
	public void agregarEquipos(){
		int terminar = 1, numEquipos = 1;
		while(numEquipos < 6){
			String nombreEquipo = CapturaEntrada.CapturarString("Ingrese nombre del equipo");
			String division = CapturaEntrada.CapturarString("Ingrese nombre de la division");
			String nombreEntrenador = CapturaEntrada.CapturarString("Ingrese nombre del entrenador");
			int torneosPerdidos,torneosParticipados, torneosGanados;
			do{
				torneosParticipados = CapturaEntrada.capturarEntero("Ingrese cantidad de torneos jugados");
				torneosPerdidos = CapturaEntrada.capturarEntero("Ingrese cantidad de torneos perdidos");
				torneosGanados = CapturaEntrada.capturarEntero("Ingrese cantidad de torneos ganados");
				if(torneosParticipados != (torneosPerdidos + torneosGanados)) System.out.println("Los torneos no encajan");
				else terminar = 0;
			}while(terminar!= 0);
			numEquipos++;
			Equipo nuevoEquipo = new Equipo(nombreEquipo,division,nombreEntrenador,torneosParticipados, torneosPerdidos, torneosGanados);
			System.out.println("Se van a agregar los 10 jugadores");
			nuevoEquipo.agregarJugadores();	
			equipos.add(nuevoEquipo);		
		}
	}
	public void datosTorneo(){
		System.out.println("En el torneo "+nombreTorneo+" hay "+numEquipos+" equipos  de la region "+region);
		System.out.println("Se han jugado "+partidosJugados+" partidos y quedan "+partidosPendientes+" pendientes");
		for(int i = 0; i < equipos.size(); i++){
			System.out.println("Equipo "+ i+":"+equipos.get(i).getnombreEquipo()+":PUNTOS = "+equipos.get(i).puntosTotales()+":DIVISION = "+equipos.get(i).getDivision());
			equipos.get(i).mostrarJugadores();
		}
	}
}

class CapturaEntrada{
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
	public static String CapturarString(String msg){
		Scanner sc = new Scanner(System.in);
		System.out.println("" + msg+ ":");
		return (sc.nextLine());
	}
}
