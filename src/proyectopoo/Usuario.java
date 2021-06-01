/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopoo;


import java.io.*;
import static java.lang.Math.abs;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author irvin
 */
public class Usuario implements Serializable{
    protected String nombre, titulo;
    protected int elo , tacticasResueltas, tacticasFalladas, eloinicial;
    public void setTitulo(){
        if(elo < 2200) titulo = "Amateur";
        if(elo >=  2200 && elo < 2300) titulo = "Candidato a Maestro";
        if(elo >=  2300 && elo < 2400) titulo = "Maestro Fide";
        if(elo >= 2400 && elo < 2500) titulo = "Maestro Internacional";
        if(elo >= 2500 && elo < 2800) titulo = "Gran Maestro";
        if(elo >= 2800) titulo = "Campeon Mundial";
        System.out.println(titulo);
    }

    public String getNombre() {
        return nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getElo() {
        return elo;
    }

    public int getTacticasResueltas() {
        return tacticasResueltas;
    }

    public int getTacticasFalladas() {
        return tacticasFalladas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setElo(int elo) {
        this.elo = elo;
        eloinicial = elo;
    }

    public void setTacticasResueltas(int tacticasResueltas) {
        this.tacticasResueltas = tacticasResueltas;
    }

    public void setTacticasFalladas(int tacticasFalladas) {
        this.tacticasFalladas = tacticasFalladas;
    }
    public void escribirUsuario(Usuario u){
        System.out.println(u.getNombre());
        try {
            Writer escribir = null;
            escribir = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Usuarios.txt",true), "UTF-8"));
            escribir.write("Nombre:"+u.getNombre()+"\n");
            escribir.write("Elo:"+String.valueOf(u.getElo())+"\n");
            escribir.write("Titulo:"+String.valueOf(u.getTitulo())+"\n");
            escribir.write("-");
            escribir.write("\n\n");
            escribir.close();
        }
        catch (Exception e) {
            System.out.println("Error al escribir");
        }        
    }
    public void leerUsuarios() throws FileNotFoundException, IOException{
        String cadena;
        FileReader f = new FileReader("Usuarios.txt");
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
     
                System.out.println(cadena);
            
        }
        b.close();  
    }
    public void progresoUsuario(){
        int elofinal = elo - eloinicial;
        double porcentaje = ((float)Math.abs(elofinal) / eloinicial) * 100;
        JFrame f = new JFrame(); JLabel l = new JLabel();
        if(elofinal < 0) l.setText("Haz empeorado un "+porcentaje+" % :(");
        if(elofinal > 0) l.setText("Haz mejorado un "+porcentaje+"% :D");
        if(elofinal == 0) l.setText("No haz tenido un progreso, sigue jugando!");
        f.add(l);
        f.setBounds(10,10,300,150);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
