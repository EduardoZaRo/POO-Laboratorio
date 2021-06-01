/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopoo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static proyectopoo.PantallaPrincipal.u;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author irvin
 */


public class Tacticas{
    TacticaBasica1_Grafico tb1 = new TacticaBasica1_Grafico();
    TacticaBasica2_Grafico tb2 = new TacticaBasica2_Grafico();
    TacticaBasica3_Grafico tb3 = new TacticaBasica3_Grafico();
    TacticaBasica4_Grafico tb4 = new TacticaBasica4_Grafico();
    TacticaBasica5_Grafico tb5 = new TacticaBasica5_Grafico();
    TacticaMedia1_Grafico tm1 = new TacticaMedia1_Grafico();
    TacticaMedia2_Grafico tm2 = new TacticaMedia2_Grafico();
    TacticaMedia3_Grafico tm3 = new TacticaMedia3_Grafico();
    TacticaMedia4_Grafico tm4 = new TacticaMedia4_Grafico();
    TacticaMedia5_Grafico tm5 = new TacticaMedia5_Grafico();
    TacticaAvanzada1_Grafico ta1 = new TacticaAvanzada1_Grafico();
    TacticaAvanzada2_Grafico ta2 = new TacticaAvanzada2_Grafico();
    TacticaAvanzada3_Grafico ta3 = new TacticaAvanzada3_Grafico();
    TacticaAvanzada4_Grafico ta4 = new TacticaAvanzada4_Grafico();
}
/*
Descripcion del funcionamiento de los tableros de ajedrez
Se cuentan con todas las piezas de ajedrez, heredando de pieza. 
El tablero en un JComponent, el cual heredara a todas las posiciones a resolver
La posicion se define en estas subclases 

*/
class TacticaBasica1_Grafico extends JFrame {
    TacticaBasica1 t = new TacticaBasica1();
    JPanel p = new JPanel();
    JLabel l = new JLabel();
    JButton b = new JButton();
    JLabel pista = new JLabel();
    boolean presionado = false;
    public TacticaBasica1_Grafico(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Tactica Basica 1");
        this.setResizable(true);
        l.setText("<html> Esta tactica es un mate en 1,<br>"
                + "bastante fácil de analizar<br>"
                + "<br>"
                + "No hay lista de jugadas<br>"
                + "disponible para esta posición");
        p.add(l);
        b.setText("?");
        
        b.addActionListener(new ActionListener () {
          public void actionPerformed(ActionEvent e){
              if(!presionado){
                l.setText(l.getText()+"<br>"
                        + "<b>Prueba moviendo el<br>"
                        + "peon ;)</b>");
                presionado = true;
              }

            
          }
        });
        
        p.add(b);
        this.add(p,BorderLayout.EAST );        
        this.add(t, BorderLayout.CENTER);
        
        this.setLocation(200, 50);
        this.pack();

    }
    public void run(){
        setVisible(true);
    }
}
class TacticaBasica1 extends Tablero{
    int  estrellas;
    static int intentos;
    boolean victoria = false;
    @Override
    public void initGrid(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                BoardGrid[i][j] = 0;
            }
        }
        piezas_blancas.add(new Rey(1,7,true,"ReyBlanco.png",this));
        piezas_blancas.add(new Torre(0,1,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Peon(4,1,true,"PeonBlanco.png",this));
        piezas_negras.add(new Rey(7,0,false,"ReyNegro.png",this));
    }
    @Override
    public void setColor(String color){
        if(color.equals("Azul")) board_file_path = "images/blue_board.png";
        if(color.equals("Rosa")) board_file_path = "images/pink_board.png";
        if(color.equals("Morado")) board_file_path = "images/magenta_board.png";
        if(color.equals("Verde")) board_file_path = "images/green_board.png";
    }
    MouseAdapter xd = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
                
        }
        @Override
        public void mousePressed(MouseEvent e) {
            
            int dX = e.getX();
            int dY = e.getY();  
            int fila_click = dY / casilla_width;
            int columna_click = dX / casilla_width;
            Pieza piezaClickeada;

            
            piezaClickeada = getPieza(columna_click, fila_click);

            
            if (pieza_activa == null && piezaClickeada != null){
                pieza_activa = piezaClickeada;    
            }
            if(pieza_activa != null && piezaClickeada == null){
                    if(pieza_activa.getClass().getSimpleName().equals("Peon")&&columna_click == 4&&fila_click == 0){

                        terminarTiempo();
                        tiempoTotal();
                        JLabel l = new JLabel("<html>Bien hecho!<br>"
                                + "Tardaste "+dif+" segundos");
                        JFrame j = new JFrame();
                        j.add(l);
                        j.setBounds(10,10,200,200);
                        j.setLocationRelativeTo(null);
                        j.setVisible(true);
                        r.ace.stop();
                        r.ace.play();
                        pieza_activa.setX(columna_click); pieza_activa.setY(fila_click);
                        intentos++;
                        
                        victoria = true;
                        if(intentos == 1 && victoria) {
                            estrellas = 3;
                        }
                        if(intentos == 2 && victoria ){
                            estrellas = 2;
                        }
                        if(intentos > 3 && victoria){
                            estrellas = 1;
                        }
                        if(!victoria) estrellas = 0;
                        
                        drawBoard();
                        sumarEloBasico();


                    }
                    else{

                        errorMensaje();
                        restarEloBasico();
                    }
            }
            
            piezas_blancas.clear();
            piezas_negras.clear();
            initGrid();
            pieza_activa = getPieza(e.getX()/64, e.getY()/64);

            System.out.println("Tactica Basica 1:"+pieza_activa.getClass().getSimpleName());
            drawBoard();    
            


            
            

            

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) { 


                
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	  
    }; 
    public TacticaBasica1(){
        BoardGrid = new Integer[filas][columnas];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        piezas_blancas = new ArrayList();
        piezas_negras = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));
        this.addMouseMotionListener(xd);
        this.addMouseListener(xd);



        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }     
}
class TacticaBasica2_Grafico extends JFrame {
    TacticaBasica2 t = new TacticaBasica2();
    JPanel p = new JPanel();
    JLabel l = new JLabel();
    JButton b = new JButton();
    JLabel pista = new JLabel();
    public TacticaBasica2_Grafico(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Tactica Basica 2");
        this.setResizable(true);
        l.setText("<html> Esta tactica es un mate en 1,<br>"
                + "bastante fácil de analizar<br>"
                + "<br>"
                + "No hay lista de jugadas<br>"
                + "disponible para esta posición");
        p.add(l);
        b.setText("?");
        b.addActionListener(new ActionListener () {
          public void actionPerformed(ActionEvent e){
            
              l.setText(l.getText()+"<br>"
                      + "<b>Prueba capturar algo con el<br>"
                      + "caballo ;)</b>");
            
          }
        });
        p.add(b);
        this.add(p,BorderLayout.EAST );        
        this.add(t, BorderLayout.CENTER);
        
        this.setLocation(200, 50);
        this.pack();

    }
    public void run(){
        setVisible(true);
    }

}
class TacticaBasica2 extends Tablero{
    int estrellas = 0;
    int intentos = 0;
    boolean victoria = false;
    
    @Override
    public void initGrid(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                BoardGrid[i][j] = 0;
            }
        }
        piezas_blancas.add(new Rey(2,0,true,"ReyBlanco.png",this));
        piezas_blancas.add(new Caballo(3,3,true,"CaballoBlanco.png",this));


        piezas_negras.add(new Rey(0,0,false,"ReyNegro.png",this));
        piezas_negras.add(new Peon(0,1,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(1,1,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(2,1,false,"PeonNegro.png",this));

    }
    
    @Override
    public void setColor(String color){
        if(color.equals("Azul")) board_file_path = "images/blue_board.png";
        if(color.equals("Rosa")) board_file_path = "images/pink_board.png";
        if(color.equals("Morado")) board_file_path = "images/magenta_board.png";
        if(color.equals("Verde")) board_file_path = "images/green_board.png";
    }
    
    MouseAdapter xd = new MouseAdapter() {
        
        @Override
        public void mouseClicked(MouseEvent e) {
                
        }
        int firstx, firsty;
        @Override
        public void mousePressed(MouseEvent e) {
            
            int firstx = e.getX()/64; int firsty = e.getY()/64;

            int dX = e.getX();
            int dY = e.getY();  
            int fila_click = dY / casilla_width;
            int columna_click = dX / casilla_width;
            boolean turno_blancas = true;
            Pieza piezaClickeada;

            
            piezaClickeada = getPieza(columna_click, fila_click);

            
            if (pieza_activa == null && piezaClickeada != null){
                pieza_activa = piezaClickeada;
                
            }
            if(pieza_activa != null && piezaClickeada == null){
                errorMensaje();
                        
            }
            
            if (pieza_activa != null && pieza_activa.getX() == columna_click && pieza_activa.getY() == fila_click){
                pieza_activa = null;     
            
            }
            else if (pieza_activa != null && pieza_activa.canMove(columna_click, fila_click)){
                
                    if(pieza_activa.getClass().getSimpleName().equals("Caballo")&&piezaClickeada.getClass().getSimpleName().equals("Peon")&&columna_click == 2&&fila_click == 1){
                        terminarTiempo();
                        tiempoTotal();
                        piezas_negras.remove(piezaClickeada);
                        JLabel l = new JLabel("<html>Bien hecho!<br>"
                                + "Tardaste "+dif+" segundos!");

                        JFrame j = new JFrame();
                        j.add(l);
                        j.setBounds(10,10,500,500);
                        j.setLocationRelativeTo(null);
                        j.setVisible(true);r.ace.stop();
                        r.ace.play();
                        pieza_activa.setX(columna_click); pieza_activa.setY(fila_click);
                        drawBoard();
                        piezas_blancas.clear();
                        piezas_negras.clear();
                        initGrid();
                         victoria = true;intentos ++;
                        if(intentos == 1 && victoria) {
                            estrellas = 3;
                        }
                        if(intentos == 2 && victoria ){
                            estrellas = 2;
                        }
                        if(intentos > 3 && victoria){
                            estrellas = 1;
                        }
                        if(!victoria) estrellas = 0;
                        sumarEloBasico();
                        
                    }
                    else{
                        errorMensaje();
                        restarEloBasico();

                    }


                
                pieza_activa.setX(columna_click);
                pieza_activa.setY(fila_click);       
                pieza_activa = null;
                turnCounter++;
            }
            piezas_blancas.clear();
            piezas_negras.clear();
            initGrid();
            
            pieza_activa = getPieza(e.getX()/64, e.getY()/64);

            System.out.println("Tactica Basica 2:"+pieza_activa.getClass().getSimpleName());

            

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) { 


                
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	  
    }; 
    public TacticaBasica2() {

        BoardGrid = new Integer[filas][columnas];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        piezas_blancas = new ArrayList();
        piezas_negras = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));
        this.addMouseMotionListener(xd);
        this.addMouseListener(xd);



        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }  
}
class TacticaBasica3_Grafico extends JFrame {
    
    TacticaBasica3 t = new TacticaBasica3();
    JPanel p = new JPanel();
    JLabel l = new JLabel();
    JButton b = new JButton();
    JLabel pista = new JLabel();
    boolean presionado = false;
    public TacticaBasica3_Grafico(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Tactica Basica 3");
        this.setResizable(true);
        l.setText("<html> En esta tactica consigues ventaja,<br>"
                + "decisiva en 1 jugada<br>"
                + "<br>"
                + "1. d4 d6 2. Nf3 Nf6 <br>"
                + "3. Bf4 g6 4. e3 Bg7 <br>"
                + "5. Be2 O-O  6. h3 c5<br>"
                + "7. O-O cxd4 8. exd4 Nc6 <br>"
                + "9. c3 Re8 10. Nbd2 Nh5 <br>"
                + "11. Bh2 e5 12. dxe5 dxe5 <br>"
                + "13. Nc4 Nf4 14. Bxf4 exf4<br>"
                + "15. Re1 Be6 16. Nd6 Re7<br>"
                + "17. Ne4 f6  18. Qxd8+ Rxd8 <br>"
                + "19. Nc5 Bf7  20. Rad1 Rde8 <br>"
                + "21.Bf1 Ne5 22. Nxe5 fxe5 <br>"
                + "23. Ne4 Bxa2 24. Ra1 Bd5 <br>"
                + "25. Rxa7 Bxe4 26. Rxe4 Kf7 <br>"
                + "27.Bc4+ Kf6 28. Bd5 Rb8 <br>"
                + "29. Rb4 Rd7 30. Bxb7 Rd1+ <br>"
                + "31. Kh2 Bf8 32. Rb6+ Kf5 <br>"
                + "33. Raa6 Bc5 34. Rf6+ Kg5 <br> "
                + " 35. Bf3 Bxf2 <br>"
                + "Eduardo Zavala 1 - 0 MN Mike Sailer<br>"
                + "2003\t\t2112");
        
        p.add(l);
        b.setText("?");
        b.addActionListener(new ActionListener () {
          public void actionPerformed(ActionEvent e){
              if(!presionado){
                             l.setText(l.getText()+"<br>"
                      + "Prueba capturar algo con el<br>"
                      + "alfil ;)");
                             presionado = true;
              }
  

            
          }
        });
        p.add(b);
        this.add(p,BorderLayout.EAST );        
        this.add(t, BorderLayout.CENTER);
        
        this.setLocation(200, 50);
        this.pack();

    }
    public void run(){
        setVisible(true);
        
    }

}
class TacticaBasica3 extends Tablero{
    static int intentos = 0;
    boolean victoria = false;
    int estrellas = 0;

    @Override
    public void initGrid(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                BoardGrid[i][j] = 0;
            }
        }
        piezas_blancas.add(new Rey(7,6,true,"ReyBlanco.png",this));
        piezas_blancas.add(new Alfil(5,5,true,"AlfilBlanco.png",this));
        piezas_blancas.add(new Torre(0,2,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Torre(5,2,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Peon(1,6,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(2,5,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(6,6,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(7,5,true,"PeonBlanco.png",this));
        
        piezas_negras.add(new Rey(6,3,false,"ReyNegro.png",this));
        piezas_negras.add(new Torre(1,0,false,"TorreNegra.png",this));
        piezas_negras.add(new Torre(3,7,false,"TorreNegra.png",this));
        piezas_negras.add(new Alfil(5,6,false,"AlfilNegro.png",this));
        piezas_negras.add(new Peon(7,1,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(6,2,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(4,3,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(5,4,false,"PeonNegro.png",this));

    }
    
    @Override
    public void setColor(String color){
        if(color.equals("Azul")) board_file_path = "images/blue_board.png";
        if(color.equals("Rosa")) board_file_path = "images/pink_board.png";
        if(color.equals("Morado")) board_file_path = "images/magenta_board.png";
        if(color.equals("Verde")) board_file_path = "images/green_board.png";
    }
    
    MouseAdapter xd = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
                
        }
        @Override
        public void mousePressed(MouseEvent e) {
           

            int dX = e.getX();
            int dY = e.getY();  
            int fila_click = dY / casilla_width;
            int columna_click = dX / casilla_width;

            Pieza piezaClickeada;

            
            piezaClickeada = getPieza(columna_click, fila_click);

            
            if (pieza_activa == null && piezaClickeada != null){
                pieza_activa = piezaClickeada;
                
            }
            if(pieza_activa != null && piezaClickeada == null){
                errorMensaje();
                        
            }
            
            if (pieza_activa != null && pieza_activa.getX() == columna_click && pieza_activa.getY() == fila_click){
                pieza_activa = null;     
            
            }
            else if (pieza_activa != null && pieza_activa.canMove(columna_click, fila_click)){
                
                    if(pieza_activa.getClass().getSimpleName().equals("Alfil")&&piezaClickeada.getClass().getSimpleName().equals("Torre")&&columna_click == 3&&fila_click == 7){
                        piezas_negras.remove(piezaClickeada);                        terminarTiempo();
                        tiempoTotal();
                        JLabel l = new JLabel("<html>Bien hecho!<br>"
                                + "Tardaste "+dif+" segundos!");

                        JFrame j = new JFrame();
                        j.add(l);
                        j.setBounds(10,10,500,500);
                        j.setLocationRelativeTo(null);
                        j.setVisible(true);r.ace.stop();
                        r.ace.play();
                        pieza_activa.setX(columna_click); pieza_activa.setY(fila_click);
                        drawBoard();
                        piezas_blancas.clear();
                        piezas_negras.clear();
                        initGrid();
                         victoria = true;intentos ++;
                        if(intentos == 1 && victoria) {
                            estrellas = 3;
                        }
                        if(intentos == 2 && victoria ){
                            estrellas = 2;
                        }
                        if(intentos > 3 && victoria){
                            estrellas = 1;
                        }
                        if(!victoria) estrellas = 0;
                        sumarEloBasico();
                        
                    }
                    else{

                        errorMensaje();
                        restarEloBasico();

                    }


                
                pieza_activa.setX(columna_click);
                pieza_activa.setY(fila_click);       
                pieza_activa = null;
                turnCounter++;
            }
            piezas_blancas.clear();
            piezas_negras.clear();
            initGrid();
            
            pieza_activa = getPieza(e.getX()/64, e.getY()/64);

            System.out.println("Tactica Basica 2:"+pieza_activa.getClass().getSimpleName());

            

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) { 


                
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	  
    }; 
    public TacticaBasica3() {

        BoardGrid = new Integer[filas][columnas];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        piezas_blancas = new ArrayList();
        piezas_negras = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));
        this.addMouseMotionListener(xd);
        this.addMouseListener(xd);



        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }  
}

class TacticaBasica4_Grafico extends JFrame {
    
    TacticaBasica4 t = new TacticaBasica4();
    JPanel p = new JPanel();
    JLabel l = new JLabel();
    JButton b = new JButton();
    JLabel pista = new JLabel();
    boolean presionado = false;
    public TacticaBasica4_Grafico(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Tactica Basica 4");
        this.setResizable(true);
        l.setText("<html> En esta tactica das mate en 1<br>"
                + "No hay una partida especifica para esta<br>"
                + "posicion ya que es una tactica tipica ;)");
        
        p.add(l);
        b.setText("?");
        b.addActionListener(new ActionListener () {
          public void actionPerformed(ActionEvent e){
              if(!presionado){
                             l.setText(l.getText()+"<br>"
                      + "<b>Prueba mover la dama<br>"
                      + " ;)</b>");
                        presionado = true;
              }
  

            
          }
        });
        p.add(b);
        this.add(p,BorderLayout.EAST );        
        this.add(t, BorderLayout.CENTER);
        
        this.setLocation(200, 50);
        this.pack();

    }
    public void run(){
        setVisible(true);
        
    }

}
class TacticaBasica4 extends Tablero{
    int intentos = 0, estrellas = 0;
    boolean victoria = false;

    @Override
    public void initGrid(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                BoardGrid[i][j] = 0;
            }
        }
        piezas_blancas.add(new Rey(7,7,true,"ReyBlanco.png",this));
        piezas_blancas.add(new Alfil(6,6,true,"AlfilBlanco.png",this));
        piezas_blancas.add(new Dama(7,1,true,"ReinaBlanca.png",this));

        piezas_negras.add(new Rey(1,0,false,"ReyNegro.png",this));


    }
    
    @Override
    public void setColor(String color){
        if(color.equals("Azul")) board_file_path = "images/blue_board.png";
        if(color.equals("Rosa")) board_file_path = "images/pink_board.png";
        if(color.equals("Morado")) board_file_path = "images/magenta_board.png";
        if(color.equals("Verde")) board_file_path = "images/green_board.png";
    }
    
    MouseAdapter xd = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
                
        }

        @Override
        public void mousePressed(MouseEvent e) {
            


            int dX = e.getX();
            int dY = e.getY();  
            int fila_click = dY / casilla_width;
            int columna_click = dX / casilla_width;
            Pieza piezaClickeada;

            
            piezaClickeada = getPieza(columna_click, fila_click);

            
            if (pieza_activa == null && piezaClickeada != null){
                pieza_activa = piezaClickeada;    
            }
            if(pieza_activa != null && piezaClickeada == null){
                if(columna_click == 1&& fila_click == 1&&pieza_activa.getClass().getSimpleName().equals("Dama")){
                        terminarTiempo();
                        tiempoTotal();
                    JLabel l = new JLabel("<html>Bien hecho!<br>"
                            + "En esta posición se combinan<br>"
                            + "la poderosa dama con un filoso<br>"
                            + "alfil! Un ataque que sin duda debe conocerse<br>"
                            + "Tardaste "+dif+" segundos!");

                        JFrame j = new JFrame();
                        j.add(l);
                        j.setBounds(10,10,500,500);
             
                    j.setLocationRelativeTo(null);
                    j.setVisible(true);
                    r.ace.stop();
                    r.ace.play();
                    pieza_activa.setX(columna_click); pieza_activa.setY(fila_click);
                    drawBoard();
                    pieza_activa = null;

                         victoria = true;intentos ++;
                        if(intentos == 1 && victoria) {
                            estrellas = 3;
                        }
                        if(intentos == 2 && victoria ){
                            estrellas = 2;
                        }
                        if(intentos > 3 && victoria){
                            estrellas = 1;
                        }
                        if(!victoria) estrellas = 0;
                        sumarEloBasico();
                }
                else{
                    errorMensaje();
                    restarEloBasico();
                }

            }


            
            piezas_blancas.clear();
            piezas_negras.clear();
            initGrid();
 
            pieza_activa = getPieza(e.getX()/64, e.getY()/64);

            System.out.println("Tactica Basica 4:"+pieza_activa.getClass().getSimpleName());
       
            

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) { 


                
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	  
    }; 
    public TacticaBasica4() {

        BoardGrid = new Integer[filas][columnas];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        piezas_blancas = new ArrayList();
        piezas_negras = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));
        this.addMouseMotionListener(xd);
        this.addMouseListener(xd);



        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }  
}
class TacticaBasica5_Grafico extends JFrame {
    
    TacticaBasica5 t = new TacticaBasica5();
    JPanel p = new JPanel();
    JLabel l = new JLabel();
    JButton b = new JButton();
    JLabel pista = new JLabel();
    boolean presionado = false;
    public TacticaBasica5_Grafico(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Tactica Basica 5");
        this.setResizable(true);
        l.setText("<html> En esta tactica obtienes ventaja <br>"
                + "decisiva en una jugada!<br>"
                + "posicion ya que es una tactica tipica ;)");
        
        p.add(l);
        b.setText("?");
        b.addActionListener(new ActionListener () {
          public void actionPerformed(ActionEvent e){
              if(!presionado){
                             l.setText(l.getText()+"<br>"
                      + "<b>Prueba mover la dama<br>"
                      + " ;)</b>");
                        presionado = true;
              }
  

            
          }
        });
        p.add(b);
        this.add(p,BorderLayout.EAST );        
        this.add(t, BorderLayout.CENTER);
        
        this.setLocation(500, 50);
        this.pack();

    }

    public void run(){
        setVisible(true);
        
    }

}
class TacticaBasica5 extends Tablero{
    int intentos = 0, estrellas = 0;
    boolean victoria = false;

    @Override
    public void initGrid(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                BoardGrid[i][j] = 0;
            }
        }
        piezas_blancas.add(new Rey(7,7,true,"ReyBlanco.png",this));
        piezas_blancas.add(new Caballo(3,2,true,"CaballoBlanco.png",this));
        piezas_blancas.add(new Dama(7,1,true,"ReinaBlanca.png",this));

        piezas_negras.add(new Rey(1,0,false,"ReyNegro.png",this));


    }
    
    @Override
    public void setColor(String color){
        if(color.equals("Azul")) board_file_path = "images/blue_board.png";
        if(color.equals("Rosa")) board_file_path = "images/pink_board.png";
        if(color.equals("Morado")) board_file_path = "images/magenta_board.png";
        if(color.equals("Verde")) board_file_path = "images/green_board.png";
    }
    
    MouseAdapter xd = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
                
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
  

            int dX = e.getX();
            int dY = e.getY();  
            int fila_click = dY / casilla_width;
            int columna_click = dX / casilla_width;
            Pieza piezaClickeada;

            
            piezaClickeada = getPieza(columna_click, fila_click);

            
            if (pieza_activa == null && piezaClickeada != null){
                pieza_activa = piezaClickeada;    
            }
            if(pieza_activa != null && piezaClickeada == null){
                if(columna_click == 1&& fila_click == 1&&pieza_activa.getClass().getSimpleName().equals("Dama")){
                        terminarTiempo();
                        tiempoTotal();
                    JLabel l = new JLabel("<html>Bien hecho!<br>"
                            + "A diferencia de la tactica 4, esta vez<br>"
                            + "se combina un travieso caballo y una dama<br>"
                            + "amenazando de manera feroz! Esta combinacion<br>"
                            + "da todo su potencial en posiciones cerradas<br>"
                            + "donde un alfil quedaria bloqueado!"
                                + "Tardaste "+dif+" segundos!");

                        JFrame j = new JFrame();
                        j.add(l);
                        j.setBounds(10,10,500,500);
                    j.setTitle("Correcto!");
          
                    j.setLocationRelativeTo(null);
                    j.setVisible(true);
                    r.ace.stop();
                    r.ace.play();
                    
                    
                    pieza_activa.setX(columna_click); pieza_activa.setY(fila_click);
                    drawBoard();
                    pieza_activa = null;
                         victoria = true;intentos ++;
                        if(intentos == 1 && victoria) {
                            estrellas = 3;
                        }
                        if(intentos == 2 && victoria ){
                            estrellas = 2;
                        }
                        if(intentos > 3 && victoria){
                            estrellas = 1;
                        }
                        if(!victoria) estrellas = 0;
                        sumarEloBasico();
                }
                else{
                    errorMensaje();
                    restarEloBasico();
                }
            }


            

            piezas_blancas.clear();
            piezas_negras.clear();
            initGrid();
 
            pieza_activa = getPieza(e.getX()/64, e.getY()/64);

            System.out.println("Tactica Basica 5:"+pieza_activa.getClass().getSimpleName());

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) { 


                
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	  
    }; 
    public TacticaBasica5() {

        BoardGrid = new Integer[filas][columnas];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        piezas_blancas = new ArrayList();
        piezas_negras = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));
        this.addMouseMotionListener(xd);
        this.addMouseListener(xd);



        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }  
}
class TacticaMedia1_Grafico extends JFrame {
    
    TacticaMedia1 t = new TacticaMedia1();
    JPanel p = new JPanel();
    JLabel l = new JLabel();
    JButton b = new JButton();
    JLabel pista = new JLabel();
    boolean presionado = false;
    public TacticaMedia1_Grafico(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Tactica Media 1");
        this.setResizable(true);
        l.setText("<html> En esta tactica obtienes ventaja<br>"
                + "decisiva en 1 jugada<br>"
                + "<br>"
                + "1. e4 d6 2. Bc4 Nf6 <br>"
                + "3. d3 g6 4. Bg5 Bg7<br>"
                + "5. Bb3 c5 6. Qd2 h6 <br>"
                + "7. Bf4 Nh5 8. Be3 Nf6 <br>"
                + "9. Nc3 Ng4 10. Nf3 Nxe3<br>"
                + "11. fxe3 Nc6 12. d4 cxd4<br>"
                + "13. exd4 Bg4 14. Ne2 Qb6 <br>"
                + "15. c3 O-O 16. h3 Bxf3 <br>"
                + "17. gxf3 Na5 18. Bc2 Nc4 <br>"
                + "19. Qd3 Nxb2 20. Qe3 Nc4 <br>"
                + "21. Qf4 e5 22. Qg4 <br>"
                + "<i>2249 Tatverdacht 1 - 0 Eduardo Zavala 2081</i><br>"
                + "\n\nEn esta partida tuve una brtutal derrota<br>"
                + "por alguien mucho mejor que yo! En una jugada <br>"
                + "mis planes y esperanzas cayeron :(");
        p.add(l);
        b.setText("?");
        b.addActionListener(new ActionListener () {
          public void actionPerformed(ActionEvent e){
              if(!presionado){
                             l.setText(l.getText()+"<br>"
                      + "<b>Prueba mover el caballo</b><br>"
                      + "<b> ;)</b>");
                        presionado = true;
              }
          }
        });
        p.add(b);
        this.add(p,BorderLayout.EAST );        
        this.add(t, BorderLayout.CENTER);
        
        this.setLocation(500,200);
        this.pack();

    }
    public void run(){
        setVisible(true);
        
    }

}

class TacticaMedia1 extends Tablero{
    static int intentos = 0, estrellas = 0;
    boolean victoria = false;
    @Override
    public void initGrid(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                BoardGrid[i][j] = 0;
            }
        }
        piezas_blancas.add(new Rey(4,0,true,"ReyBlanco.png",this));
        piezas_blancas.add(new Dama(1,3,true,"ReinaBlanca.png",this));
        piezas_blancas.add(new Alfil(5,1,true,"AlfilBlanco.png",this));
        piezas_blancas.add(new Caballo(3,1,true,"CaballoBlanco.png",this));
        piezas_blancas.add(new Torre(7,0,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Torre(0,0,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Peon(0,2,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(2,2,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(3,3,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(4,3,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(5,2,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(7,1,true,"PeonBlanco.png",this));

        piezas_negras.add(new Rey(1,7,false,"ReyNegro.png",this));
        piezas_negras.add(new Dama(6,5,false,"ReinaNegra.png",this));
        piezas_negras.add(new Alfil(1,6,false,"AlfilNegro.png",this));
        piezas_negras.add(new Caballo(5,3,false,"CaballoNegro.png",this));
        piezas_negras.add(new Torre(2,7,false,"TorreNegra.png",this));
        piezas_negras.add(new Torre(7,7,false,"TorreNegra.png",this));
        piezas_negras.add(new Peon(0,5,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(1,5,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(2,6,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(3,4,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(4,5,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(6,6,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(7,6,false,"PeonNegro.png",this));


    }
    
    @Override
    public void setColor(String color){
        if(color.equals("Azul")) board_file_path = "images/blue_board.png";
        if(color.equals("Rosa")) board_file_path = "images/pink_board.png";
        if(color.equals("Morado")) board_file_path = "images/magenta_board.png";
        if(color.equals("Verde")) board_file_path = "images/green_board.png";
    }
    MouseAdapter xd = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
                
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
            

            int dX = e.getX();
            int dY = e.getY();  
            int fila_click = dY / casilla_width;
            int columna_click = dX / casilla_width;
            Pieza piezaClickeada;

            
            piezaClickeada = getPieza(columna_click, fila_click);

            
            if (pieza_activa == null && piezaClickeada != null){
                pieza_activa = piezaClickeada;    
            }
            if(pieza_activa != null && piezaClickeada == null){
                if(columna_click == 3&& fila_click == 2&&pieza_activa.getClass().getSimpleName().equals("Caballo")){
                        terminarTiempo();
                        tiempoTotal();
                    JLabel l = new JLabel("<html>Bien hecho!<br>"
                            + "Bienvenido a las tacticas medias!<br>"
                            + "Como vez la complejidad aumenta pero no es imposible!<br>"
                                + "Tardaste "+dif+" segundos!");

                        JFrame j = new JFrame();
                        j.add(l);
                        j.setBounds(10,10,500,500);
                    j.setTitle("Correcto!");
       
                    j.setLocationRelativeTo(null);
                    j.setVisible(true);
                    r.ace.stop();
                    r.ace.play();
                    pieza_activa.setX(columna_click); pieza_activa.setY(fila_click);
                    drawBoard();
                    pieza_activa = null;
            
                    victoria = true;intentos ++;
                   if(intentos == 1 && victoria) {
                       estrellas = 3;
                   }
                   if(intentos == 2 && victoria ){
                       estrellas = 2;
                   }
                   if(intentos > 3 && victoria){
                       estrellas = 1;
                   }
                   if(!victoria) estrellas = 0;
                   sumarEloBasico();
                }
                else{
                    errorMensaje();
                }
            }


            
            piezas_blancas.clear();
            piezas_negras.clear();
            initGrid();
 
            pieza_activa = getPieza(e.getX()/64, e.getY()/64);

            System.out.println("Tactica Media 1:"+pieza_activa.getClass().getSimpleName());
            

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) { 


                
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	  
    }; 
    public TacticaMedia1() {

        BoardGrid = new Integer[filas][columnas];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        piezas_blancas = new ArrayList();
        piezas_negras = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));
        this.addMouseMotionListener(xd);
        this.addMouseListener(xd);



        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }  
}

class TacticaMedia2_Grafico extends JFrame {
    TacticaMedia2 t = new TacticaMedia2();
    JPanel p = new JPanel();
    JLabel l = new JLabel();
    JButton b = new JButton();
    JLabel pista = new JLabel();
    boolean presionado = false;
    public TacticaMedia2_Grafico(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Tactica Media 2");
        this.setResizable(true);
        l.setText("<html>No hay movimientos para este partido!<br>"
                + "Ataque formidable con una jugada!");
        p.add(l);
        b.setText("?");
        b.addActionListener(new ActionListener () {
          public void actionPerformed(ActionEvent e){
              if(!presionado){
                             l.setText(l.getText()+"<br>"
                      + "<b>Prueba mover el caballo</b><br>"
                      + "<b> ;)</b>");
                        presionado = true;
              }
          }
        });
        p.add(b);
        this.add(p,BorderLayout.EAST );        
        this.add(t, BorderLayout.CENTER);
        
        this.setLocation(500,200);
        this.pack();

    }
    public void run(){
        setVisible(true);
        
    }

}
class TacticaMedia2 extends Tablero{
    static int intentos = 0, estrellas = 0;
    boolean victoria = false;

    @Override
    public void initGrid(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                BoardGrid[i][j] = 0;
            }
        }
        piezas_blancas.add(new Rey(4,6,true,"ReyBlanco.png",this));
        piezas_blancas.add(new Caballo(7,2,true,"CaballoBlanco.png",this));
        piezas_blancas.add(new Torre(7,7,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Torre(0,7,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Peon(2,5,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(2,6,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(4,5,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(6,4,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(7,6,true,"PeonBlanco.png",this));


        piezas_negras.add(new Rey(6,1,false,"ReyNegro.png",this));
        piezas_negras.add(new Alfil(1,1,false,"AlfilNegro.png",this));
        piezas_negras.add(new Torre(4,0,false,"TorreNegra.png",this));
        piezas_negras.add(new Torre(3,2,false,"TorreNegra.png",this));
        piezas_negras.add(new Peon(0,3,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(2,4,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(4,4,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(5,1,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(7,1,false,"PeonNegro.png",this));



    }
    
    @Override
    public void setColor(String color){
        if(color.equals("Azul")) board_file_path = "images/blue_board.png";
        if(color.equals("Rosa")) board_file_path = "images/pink_board.png";
        if(color.equals("Morado")) board_file_path = "images/magenta_board.png";
        if(color.equals("Verde")) board_file_path = "images/green_board.png";
    }
    MouseAdapter xd = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
                
        }
 
        @Override
        public void mousePressed(MouseEvent e) {
            
            

            int dX = e.getX();
            int dY = e.getY();  
            int fila_click = dY / casilla_width;
            int columna_click = dX / casilla_width;
            Pieza piezaClickeada;

            
            piezaClickeada = getPieza(columna_click, fila_click);

            
            if (pieza_activa == null && piezaClickeada != null){
                pieza_activa = piezaClickeada;    
            }
            if(pieza_activa != null && piezaClickeada == null){
                if(columna_click == 5&& fila_click == 3&&pieza_activa.getClass().getSimpleName().equals("Caballo")){
                        terminarTiempo();
                        tiempoTotal();
                    JLabel l = new JLabel("<html>Bien hecho!<br>"
                            + "Un doblete con caballo!<br>"
                            + "Un ataque con esta singular movimiento ;)<br>"
                                + "Tardaste "+dif+" segundos!");

                        JFrame j = new JFrame();
                        j.add(l);
                        j.setBounds(10,10,500,500);
                    j.setTitle("Correcto!");
      
                    j.setLocationRelativeTo(null);
                    j.setVisible(true);
                    r.ace.stop();
                    r.ace.play();
                    pieza_activa.setX(columna_click); pieza_activa.setY(fila_click);
                    drawBoard();
                    pieza_activa = null;
            
                    victoria = true;intentos ++;
                   if(intentos == 1 && victoria) {
                       estrellas = 3;
                   }
                   if(intentos == 2 && victoria ){
                       estrellas = 2;
                   }
                   if(intentos > 3 && victoria){
                       estrellas = 1;
                   }
                   if(!victoria) estrellas = 0;
                   sumarEloMedio();
                }
                else{
                    errorMensaje();
                    restarEloMedio();
                }

            }


            
            piezas_blancas.clear();
            piezas_negras.clear();
            initGrid();
            pieza_activa = getPieza(e.getX()/64, e.getY()/64);

            System.out.println("Tactica Media 2:"+pieza_activa.getClass().getSimpleName());
            drawBoard();  
            

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) { 


                
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	  
    }; 

    public TacticaMedia2() {

        BoardGrid = new Integer[filas][columnas];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        piezas_blancas = new ArrayList();
        piezas_negras = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));
        this.addMouseMotionListener(xd);
        this.addMouseListener(xd);



        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }  
}
class TacticaMedia3_Grafico extends JFrame {
    TacticaMedia3 t = new TacticaMedia3();
    JPanel p = new JPanel();
    JLabel l = new JLabel();
    JButton b = new JButton();
    JLabel pista = new JLabel();
    boolean presionado = false;
    public TacticaMedia3_Grafico(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Tactica Media 3");
        this.setResizable(true);
        l.setText("<html>Ganas ventaja decisiva en una jugada!<br>"
                + "No hay lista de movimientos :(<br>"
                + "<i>masterbrein 2157 0 - 1 EduardoZaRom 2062</i><br>"
                + "Una de mis partidas en el torneo inicio de semestre<br>"
                + "UABC!");
        p.add(l);
        b.setText("?");
        b.addActionListener(new ActionListener () {
          public void actionPerformed(ActionEvent e){
              if(!presionado){
                             l.setText(l.getText()+"<br>"
                      + "<b>Prueba mover el alfil</b><br>"
                      + "<b> ;)</b>");
                        presionado = true;
              }
          }
        });
        p.add(b);
        this.add(p,BorderLayout.EAST );        
        this.add(t, BorderLayout.CENTER);
        
        this.setLocation(500,200);
        this.pack();

    }
    public void run(){
        setVisible(true);
        
    }

}
class TacticaMedia3 extends Tablero{
    static int intentos = 0, estrellas = 0;
    boolean victoria = false;

    @Override
    public void initGrid(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                BoardGrid[i][j] = 0;
            }
        }
        piezas_blancas.add(new Rey(0,1,true,"ReyBlanco.png",this));
        piezas_blancas.add(new Alfil(1,3,true,"AlfilBlanco.png",this));
        piezas_blancas.add(new Torre(4,5,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Peon(0,2,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(2,4,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(3,3,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(4,4,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(6,1,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(7,1,true,"PeonBlanco.png",this));

        piezas_negras.add(new Rey(1,7,false,"ReyNegro.png",this));
        piezas_negras.add(new Alfil(3,2,false,"AlfilNegro.png",this));
        piezas_negras.add(new Alfil(4,2,false,"AlfilNegro.png",this));
        piezas_negras.add(new Torre(2,6,false,"TorreNegra.png",this));
        piezas_negras.add(new Peon(0,6,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(7,4,false,"PeonNegro.png",this));




    }
    
    @Override
    public void setColor(String color){
        if(color.equals("Azul")) board_file_path = "images/blue_board.png";
        if(color.equals("Rosa")) board_file_path = "images/pink_board.png";
        if(color.equals("Morado")) board_file_path = "images/magenta_board.png";
        if(color.equals("Verde")) board_file_path = "images/green_board.png";
    }
    MouseAdapter xd = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
                
        }
     
        @Override
        public void mousePressed(MouseEvent e) {
            
            

            int dX = e.getX();
            int dY = e.getY();  
            int fila_click = dY / casilla_width;
            int columna_click = dX / casilla_width;
           ;
            Pieza piezaClickeada;

            
            piezaClickeada = getPieza(columna_click, fila_click);

            
            if (pieza_activa == null && piezaClickeada != null){
                pieza_activa = piezaClickeada;    
            }
            if(pieza_activa != null && piezaClickeada == null){
                if(columna_click == 2&& fila_click == 3&&pieza_activa.getClass().getSimpleName().equals("Alfil")&&pieza_activa.getX() == 3){
                        terminarTiempo();
                        tiempoTotal();
                    JLabel l = new JLabel("<html>Bien hecho!<br>"
                            + "Un ataque doble con alfil!<br>"
                            + "No olvides que los ataques en diagonal con<br>"
                            + "alfil es fulminante!"
                                + "Tardaste "+dif+" segundos!");

                        JFrame j = new JFrame();
                        j.add(l);
                        j.setBounds(10,10,500,500);
                    j.setTitle("Correcto!");
              
                    j.setLocationRelativeTo(null);
                    j.setVisible(true);
                    r.ace.stop();
                    r.ace.play();
                    pieza_activa.setX(columna_click); pieza_activa.setY(fila_click);
                    drawBoard();
                    pieza_activa = null;
            
                    victoria = true;intentos ++;
                   if(intentos == 1 && victoria) {
                       estrellas = 3;
                   }
                   if(intentos == 2 && victoria ){
                       estrellas = 2;
                   }
                   if(intentos > 3 && victoria){
                       estrellas = 1;
                   }
                   if(!victoria) estrellas = 0;
                   sumarEloMedio();
                   drawBoard(); 
                }
                else{
                    errorMensaje();
                    restarEloMedio();
                }
            }


            
            piezas_blancas.clear();
            piezas_negras.clear();
            initGrid();
            pieza_activa = getPieza(e.getX()/64, e.getY()/64);

            System.out.println("Tactica Media 3:"+pieza_activa.getClass().getSimpleName());
            drawBoard();  
            

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) { 


                
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	  
    }; 

    public TacticaMedia3() {

        BoardGrid = new Integer[filas][columnas];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        piezas_blancas = new ArrayList();
        piezas_negras = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));
        this.addMouseMotionListener(xd);
        this.addMouseListener(xd);



        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }  
}
class TacticaMedia4_Grafico extends JFrame {
    TacticaMedia4 t = new TacticaMedia4();
    JPanel p = new JPanel();
    JLabel l = new JLabel();
    JButton b = new JButton();
    JLabel pista = new JLabel();
    boolean presionado = false;
    public TacticaMedia4_Grafico(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Tactica Media 4");
        this.setResizable(true);
        l.setText("<html>Ganas ventaja decisiva en una jugada!<br>"
                + "");
        p.add(l);
        b.setText("?");
        b.addActionListener(new ActionListener () {
          public void actionPerformed(ActionEvent e){
              if(!presionado){
                             l.setText(l.getText()+"<br>"
                      + "<b>Prueba mover el alfil</b><br>"
                      + "<b> ;)</b>");
                        presionado = true;
              }
          }
        });
        p.add(b);
        this.add(p,BorderLayout.EAST );        
        this.add(t, BorderLayout.CENTER);
        
        this.setLocation(500,200);
        this.pack();

    }
    public void run(){
        setVisible(true);
        
    }

}
class TacticaMedia4 extends Tablero{
    static int intentos = 0, estrellas = 0;
    boolean victoria = false;
    @Override
    public void initGrid(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                BoardGrid[i][j] = 0;
            }
        }
        piezas_blancas.add(new Rey(6,7,true,"ReyBlanco.png",this));
        piezas_blancas.add(new Alfil(3,5,true,"AlfilBlanco.png",this));
        piezas_blancas.add(new Alfil(4,3,true,"AlfilBlanco.png",this));
        piezas_blancas.add(new Torre(3,7,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Torre(4,6,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Peon(1,6,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(3,4,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(5,6,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(6,6,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(7,6,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Dama(5,5,true,"ReinaBlanca.png",this));

        piezas_negras.add(new Rey(6,0,false,"ReyNegro.png",this));
        piezas_negras.add(new Alfil(4,1,false,"AlfilNegro.png",this));
        piezas_negras.add(new Alfil(5,2,false,"CaballoNegro.png",this));
        piezas_negras.add(new Torre(3,0,false,"TorreNegra.png",this));
        piezas_negras.add(new Torre(5,0,false,"TorreNegra.png",this));
        piezas_negras.add(new Peon(0,1,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(1,1,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(4,2,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(5,1,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(6,1,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(7,1,false,"PeonNegro.png",this));
        piezas_negras.add(new Dama(1,5,false,"ReinaNegra.png",this));
    }
    
    @Override
    public void setColor(String color){
        if(color.equals("Azul")) board_file_path = "images/blue_board.png";
        if(color.equals("Rosa")) board_file_path = "images/pink_board.png";
        if(color.equals("Morado")) board_file_path = "images/magenta_board.png";
        if(color.equals("Verde")) board_file_path = "images/green_board.png";
    }
    MouseAdapter xd = new MouseAdapter() {
        
        @Override
        public void mouseClicked(MouseEvent e) {
                
        }

        @Override
        public void mousePressed(MouseEvent e) {
            


            int dX = e.getX();
            int dY = e.getY();  
            int fila_click = dY / casilla_width;
            int columna_click = dX / casilla_width;
            
            Pieza piezaClickeada;

            
            piezaClickeada = getPieza(columna_click, fila_click);

            
            if (pieza_activa == null && piezaClickeada != null){
                pieza_activa = piezaClickeada;
                
            }
            if(pieza_activa != null && piezaClickeada == null){
                    errorMensaje();
                    restarEloMedio();
                        
            }
            
            if (pieza_activa != null && pieza_activa.getX() == columna_click && pieza_activa.getY() == fila_click){
                pieza_activa = null;     
            
            }
            else if (pieza_activa != null && pieza_activa.canMove(columna_click, fila_click)){
                
                    if(pieza_activa.getClass().getSimpleName().equals("Alfil")&&piezaClickeada.getClass().getSimpleName().equals("Peon")&&columna_click ==7&&fila_click == 1){
                        piezas_negras.remove(piezaClickeada);
                        terminarTiempo();
                        tiempoTotal();
                        JLabel l = new JLabel("<html>Bien hecho!<br>"
                                + "Tardaste "+dif+" segundos!");

                        JFrame j = new JFrame();
                        j.add(l);
                        j.setBounds(10,10,500,500);
                        j.setLocationRelativeTo(null);
                        j.setVisible(true);r.ace.stop();
                        r.ace.play();
                        pieza_activa.setX(columna_click); pieza_activa.setY(fila_click);
                        drawBoard();
                        piezas_blancas.clear();
                        piezas_negras.clear();
                        initGrid();
                         victoria = true;intentos ++;
                        if(intentos == 1 && victoria) {
                            estrellas = 3;
                        }
                        if(intentos == 2 && victoria ){
                            estrellas = 2;
                        }
                        if(intentos > 3 && victoria){
                            estrellas = 1;
                        }
                        if(!victoria) estrellas = 0;
                        sumarEloMedio();
                        
                    }
                    else{
                    errorMensaje();
                    restarEloMedio();

                    }


                
                pieza_activa.setX(columna_click);
                pieza_activa.setY(fila_click);       
                pieza_activa = null;
                turnCounter++;
            }
            piezas_blancas.clear();
            piezas_negras.clear();
            initGrid();
            
            pieza_activa = getPieza(e.getX()/64, e.getY()/64);

            System.out.println("Tactica Media 4:"+pieza_activa.getClass().getSimpleName());

            

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) { 


                
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	  
    }; 

    public TacticaMedia4() {

        BoardGrid = new Integer[filas][columnas];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        piezas_blancas = new ArrayList();
        piezas_negras = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));
        this.addMouseMotionListener(xd);
        this.addMouseListener(xd);



        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }  
}
class TacticaMedia5_Grafico extends JFrame {
    TacticaMedia5 t = new TacticaMedia5();
    JPanel p = new JPanel();
    JLabel l = new JLabel();
    JButton b = new JButton();
    JLabel pista = new JLabel();
    boolean presionado = false;
    public TacticaMedia5_Grafico(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Tactica Media 5");
        this.setResizable(true);
        l.setText("<html>Ganas ventaja decisiva en una jugada!<br>"
                + "No hay serie de jugadas porque la posicion es inventada!");
        p.add(l);
        b.setText("?");
        b.addActionListener(new ActionListener () {
          public void actionPerformed(ActionEvent e){
              if(!presionado){
                             l.setText(l.getText()+"<br>"
                      + "<b>Prueba mover la torre</b><br>"
                      + "<b> ;)</b>");
                        presionado = true;
              }
          }
        });
        p.add(b);
        this.add(p,BorderLayout.EAST );        
        this.add(t, BorderLayout.CENTER);
        
        this.setLocation(500,200);
        this.pack();

    }
    public void run(){
        setVisible(true);
        
    }

}
class TacticaMedia5 extends Tablero{
    static int intentos = 0, estrellas = 0;
    boolean victoria = false;
    @Override
    public void initGrid(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                BoardGrid[i][j] = 0;
            }
        }
        piezas_blancas.add(new Rey(7,1,true,"ReyBlanco.png",this));
        piezas_blancas.add(new Alfil(0,2,true,"AlfilBlanco.png",this));
        piezas_blancas.add(new Torre(2,7,true,"TorreBlanca.png",this));

        piezas_negras.add(new Rey(1,0,false,"ReyNegro.png",this));
        piezas_negras.add(new Peon(0,1,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(1,2,false,"PeonNegro.png",this));
        piezas_negras.add(new Dama(3,2,false,"ReinaNegra.png",this));
        piezas_negras.add(new Dama(5,2,false,"ReinaNegra.png",this));
    }
    
    @Override
    public void setColor(String color){
        if(color.equals("Azul")) board_file_path = "images/blue_board.png";
        if(color.equals("Rosa")) board_file_path = "images/pink_board.png";
        if(color.equals("Morado")) board_file_path = "images/magenta_board.png";
        if(color.equals("Verde")) board_file_path = "images/green_board.png";
    }
    MouseAdapter xd = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
                
        }
       
        @Override
        public void mousePressed(MouseEvent e) {
            
      

            int dX = e.getX();
            int dY = e.getY();  
            int fila_click = dY / casilla_width;
            int columna_click = dX / casilla_width;
          
            Pieza piezaClickeada;

            
            piezaClickeada = getPieza(columna_click, fila_click);

            
            if (pieza_activa == null && piezaClickeada != null){
                pieza_activa = piezaClickeada;    
            }
            if(pieza_activa != null && piezaClickeada == null){
                if(columna_click == 2&& fila_click == 0&&pieza_activa.getClass().getSimpleName().equals("Torre")){
                        terminarTiempo();
                        tiempoTotal();
   
                                
                    JLabel l = new JLabel("<html>Bien hecho!<br>"
                            + "A pesar de la abrumadora ventaja material del negro<br>"
                            + "tenemos una oportunidad esperanzadora!<br>"
                            + "Tardaste "+dif+" segundos!");
                    JFrame j = new JFrame();
                    j.add(l);
                    j.setTitle("Correcto!");
                    j.setBounds(10,10,300,150);
                    j.setLocationRelativeTo(null);
                    j.setVisible(true);
                    r.ace.stop();
                    r.ace.play();
                    
                    
                    pieza_activa.setX(columna_click); pieza_activa.setY(fila_click);
                    drawBoard();
                    pieza_activa = null;
                         victoria = true;intentos ++;
                        if(intentos == 1 && victoria) {
                            estrellas = 3;
                        }
                        if(intentos == 2 && victoria ){
                            estrellas = 2;
                        }
                        if(intentos > 3 && victoria){
                            estrellas = 1;
                        }
                        if(!victoria) estrellas = 0;
                        sumarEloMedio();
                }
                else{
                    errorMensaje();
                    restarEloMedio();
                }
            }


            

            piezas_blancas.clear();
            piezas_negras.clear();
            initGrid();
 
            pieza_activa = getPieza(e.getX()/64, e.getY()/64);

            System.out.println("Tactica Basica 5:"+pieza_activa.getClass().getSimpleName());

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) { 


                
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	  
    }; 

    public TacticaMedia5() {

        BoardGrid = new Integer[filas][columnas];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        piezas_blancas = new ArrayList();
        piezas_negras = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));
        this.addMouseMotionListener(xd);
        this.addMouseListener(xd);



        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }  
}
class TacticaAvanzada1_Grafico extends JFrame {
    TacticaAvanzada1 t = new TacticaAvanzada1();
    JPanel p = new JPanel();
    JLabel l = new JLabel();
    JButton b = new JButton();
    JLabel pista = new JLabel();
    boolean presionado = false;
    public TacticaAvanzada1_Grafico(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Tactica Media 5");
        this.setResizable(true);
        l.setText("<html>Ganas ventaja decisiva en una jugada!<br>"
                + "<i>Karpov 1 - 0 Mickiewicks</i>");
        p.add(l);
        b.setText("?");
        b.addActionListener(new ActionListener () {
          public void actionPerformed(ActionEvent e){
              if(!presionado){
                             l.setText(l.getText()+"<br>"
                      + "<b>Prueba mover la torre</b><br>"
                      + "<b> ;)</b>");
                        presionado = true;
              }
          }
        });
        p.add(b);
        this.add(p,BorderLayout.EAST );        
        this.add(t, BorderLayout.CENTER);
        
        this.setLocation(500,200);
        this.pack();

    }
    public void run(){
        setVisible(true);
        
    }

}
class TacticaAvanzada1 extends Tablero{
    static int intentos = 0, estrellas = 0;
    boolean victoria = false;
    @Override
    public void initGrid(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                BoardGrid[i][j] = 0;
            }
        }
        piezas_blancas.add(new Rey(6,6,true,"ReyBlanco.png",this));
        piezas_blancas.add(new Alfil(3,6,true,"AlfilBlanco.png",this));
        piezas_blancas.add(new Torre(4,4,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Torre(3,7,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Peon(0,6,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(2,3,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(5,2,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(6,5,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(7,5,true,"PeonBlanco.png",this));
        
        
        piezas_negras.add(new Rey(7,3,false,"ReyNegro.png",this));
        piezas_negras.add(new Peon(0,2,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(1,1,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(2,2,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(5,2,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(6,3,false,"PeonNegro.png",this));
        piezas_negras.add(new Torre(5,0,false,"TorreNegra.png",this));
        piezas_negras.add(new Torre(7,0,false,"TorreNegra.png",this));
    }
    
    @Override
    public void setColor(String color){
        if(color.equals("Azul")) board_file_path = "images/blue_board.png";
        if(color.equals("Rosa")) board_file_path = "images/pink_board.png";
        if(color.equals("Morado")) board_file_path = "images/magenta_board.png";
        if(color.equals("Verde")) board_file_path = "images/green_board.png";
    }
    MouseAdapter xd = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
                
        }
  
        @Override
        public void mousePressed(MouseEvent e) {
            

            int dX = e.getX();
            int dY = e.getY();  
            int fila_click = dY / casilla_width;
            int columna_click = dX / casilla_width;
     
            Pieza piezaClickeada;

            
            piezaClickeada = getPieza(columna_click, fila_click);

            
            if (pieza_activa == null && piezaClickeada != null){
                pieza_activa = piezaClickeada;    
            }
            if(pieza_activa != null && piezaClickeada == null){
                if(columna_click == 7&& fila_click == 4&&pieza_activa.getClass().getSimpleName().equals("Torre")&&pieza_activa.getX() == 4){
                        terminarTiempo();
                        tiempoTotal();

                            
                    JLabel l = new JLabel("<html>Me sorprendes!<br>"
                            + "Viste la dificil de ver secuencia forzada<br>"
                            + "con un sacrificio de torre!<br>"
                            + "Esta jugada realizada en 1997 sigue<br>"
                            + "siendo brutal en 2021!<br>"
                            + "Tardaste "+dif+" segundos!");
                            
                    JFrame j = new JFrame();
                    j.add(l);
                    j.setTitle("Correcto!");
                    j.setBounds(10,10,300,150);
                    j.setLocationRelativeTo(null);
                    j.setVisible(true);
                    r.ace.stop();
                    r.ace.play();
                    
                    
                    pieza_activa.setX(columna_click); pieza_activa.setY(fila_click);
                    drawBoard();
                    pieza_activa = null;
                         victoria = true;intentos ++;
                        if(intentos == 1 && victoria) {
                            estrellas = 3;
                        }
                        if(intentos == 2 && victoria ){
                            estrellas = 2;
                        }
                        if(intentos > 3 && victoria){
                            estrellas = 1;
                        }
                        if(!victoria) estrellas = 0;
                        sumarEloAvanzado();
                }
                else{
                    errorMensaje();
                    restarEloAvanzado();
                }
            }


            

            piezas_blancas.clear();
            piezas_negras.clear();
            initGrid();
 
            pieza_activa = getPieza(e.getX()/64, e.getY()/64);

            System.out.println("Tactica Avanzada 1:"+pieza_activa.getClass().getSimpleName());

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) { 


                
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	  
    }; 

    public TacticaAvanzada1() {

        BoardGrid = new Integer[filas][columnas];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        piezas_blancas = new ArrayList();
        piezas_negras = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));
        this.addMouseMotionListener(xd);
        this.addMouseListener(xd);



        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }  
}
class TacticaAvanzada2_Grafico extends JFrame {
    TacticaAvanzada2 t = new TacticaAvanzada2();
    JPanel p = new JPanel();
    JLabel l = new JLabel();
    JButton b = new JButton();
    JLabel pista = new JLabel();
    boolean presionado = false;
    public TacticaAvanzada2_Grafico(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Tactica Avanzada 2");
        this.setResizable(true);
        l.setText("<html>Ganas ventaja decisiva en una jugada!<br>"
                + "<i>Idani 0 - 1 Gholami</i>");
        p.add(l);
        b.setText("?");
        b.addActionListener(new ActionListener () {
          public void actionPerformed(ActionEvent e){
              if(!presionado){
                             l.setText(l.getText()+"<br>"
                      + "<b>Prueba mover la dama!</b><br>"
                      + "<b> ;)</b>");
                        presionado = true;
              }
          }
        });
        p.add(b);
        this.add(p,BorderLayout.EAST );        
        this.add(t, BorderLayout.CENTER);
        
        this.setLocation(500,200);
        this.pack();

    }
    public void run(){
        setVisible(true);
        
    }

}
class TacticaAvanzada2 extends Tablero{
    static int intentos = 0, estrellas = 0;
    boolean victoria = false;
    @Override
    public void initGrid(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                BoardGrid[i][j] = 0;
            }
        }
        piezas_blancas.add(new Rey(7,7,true,"ReyBlanco.png",this));
        piezas_blancas.add(new Alfil(5,7,true,"AlfilBlanco.png",this));
        piezas_blancas.add(new Dama(4,4,true,"ReinaBlanca.png",this));
        piezas_blancas.add(new Peon(0,1,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(0,3,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(1,4,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(7,6,true,"PeonBlanco.png",this));
        
        
        piezas_negras.add(new Rey(7,1,false,"ReyNegro.png",this));
        piezas_negras.add(new Peon(5,5,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(6,2,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(7,2,false,"PeonNegro.png",this));
        piezas_negras.add(new Torre(0,6,false,"TorreNegra.png",this));
        piezas_negras.add(new Dama(6,3,false,"ReinaNegra.png",this));
    }
    
    @Override
    public void setColor(String color){
        if(color.equals("Azul")) board_file_path = "images/blue_board.png";
        if(color.equals("Rosa")) board_file_path = "images/pink_board.png";
        if(color.equals("Morado")) board_file_path = "images/magenta_board.png";
        if(color.equals("Verde")) board_file_path = "images/green_board.png";
    }
    MouseAdapter xd = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
                
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            


            int dX = e.getX();
            int dY = e.getY();  
            int fila_click = dY / casilla_width;
            int columna_click = dX / casilla_width;
       
            Pieza piezaClickeada;

            
            piezaClickeada = getPieza(columna_click, fila_click);

            
            if (pieza_activa == null && piezaClickeada != null){
                pieza_activa = piezaClickeada;    
            }
            if(pieza_activa != null && piezaClickeada == null){
                if(columna_click == 6&& fila_click == 6&&pieza_activa.getClass().getSimpleName().equals("Dama")&&pieza_activa.getX() == 6){
                        terminarTiempo();
                        tiempoTotal();

                            
                    JLabel l = new JLabel("<html>Alucinante!<br>"
                            + "Viste el sacrificio de dama?!?!<br>"
                            + "Seguramente no eres humano!<br>"
                            + "Tardaste "+dif+" segundos!");
                    JFrame j = new JFrame();
                    j.add(l);
                    j.setTitle("Correcto!");
                    j.setBounds(10,10,300,150);
                    j.setLocationRelativeTo(null);
                    j.setVisible(true);
                    r.ace.stop();
                    r.ace.play();
                    
                    
                    pieza_activa.setX(columna_click); pieza_activa.setY(fila_click);
                    drawBoard();
                    pieza_activa = null;
                         victoria = true;intentos ++;
                        if(intentos == 1 && victoria) {
                            estrellas = 3;
                        }
                        if(intentos == 2 && victoria ){
                            estrellas = 2;
                        }
                        if(intentos > 3 && victoria){
                            estrellas = 1;
                        }
                        if(!victoria) estrellas = 0;
                        sumarEloAvanzado();
                }
                else{
                    errorMensaje();
                    restarEloAvanzado();
                }
            }


            

            piezas_blancas.clear();
            piezas_negras.clear();
            initGrid();
 
            pieza_activa = getPieza(e.getX()/64, e.getY()/64);

            System.out.println("Tactica Avanzada 2:"+pieza_activa.getClass().getSimpleName());

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) { 


                
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	  
    }; 

    public TacticaAvanzada2() {

        BoardGrid = new Integer[filas][columnas];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        piezas_blancas = new ArrayList();
        piezas_negras = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));
        this.addMouseMotionListener(xd);
        this.addMouseListener(xd);



        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }  
}
class TacticaAvanzada3_Grafico extends JFrame {
    TacticaAvanzada3 t = new TacticaAvanzada3();
    JPanel p = new JPanel();
    JLabel l = new JLabel();
    JButton b = new JButton();
    JLabel pista = new JLabel();
    boolean presionado = false;
    public TacticaAvanzada3_Grafico(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Tactica Avanzada 3");
        this.setResizable(true);
        l.setText("<html>Ganas ventaja decisiva en una jugada!<br>"
                    + "<i>Driknsna 0 - 1 Strautins    1967</i>");
        p.add(l);
        b.setText("?");
        b.addActionListener(new ActionListener () {
          public void actionPerformed(ActionEvent e){
              if(!presionado){
                             l.setText(l.getText()+"<br>"
                      + "<b>Prueba mover la dama!</b><br>"
                      + "<b> ;)</b>");
                        presionado = true;
              }
          }
        });
        p.add(b);
        this.add(p,BorderLayout.EAST );        
        this.add(t, BorderLayout.CENTER);
        
        this.setLocation(500,200);
        this.pack();

    }
    public void run(){
        setVisible(true);
        
    }

}
class TacticaAvanzada3 extends Tablero{
    static int intentos = 0, estrellas = 0;
    boolean victoria = false;
    @Override
    public void initGrid(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                BoardGrid[i][j] = 0;
            }
        }
        piezas_blancas.add(new Rey(5,0,true,"ReyBlanco.png",this));
        piezas_blancas.add(new Alfil(5,3,true,"AlfilBlanco.png",this));
        piezas_blancas.add(new Dama(6,5,true,"ReinaBlanca.png",this));
        piezas_blancas.add(new Torre(4,0,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Torre(4,1,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Peon(0,1,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(1,1,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(2,3,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(5,2,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(6,1,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(7,1,true,"PeonBlanco.png",this));
        
        piezas_negras.add(new Rey(1,6,false,"ReyNegro.png",this));
        piezas_negras.add(new Peon(0,5,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(1,5,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(2,6,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(5,4,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(6,4,false,"PeonNegro.png",this));
        piezas_negras.add(new Torre(4,6,false,"TorreNegra.png",this));
        piezas_negras.add(new Dama(2,4,false,"ReinaNegra.png",this));
        piezas_negras.add(new Caballo(4,3,false,"CaballoNegro.png",this));
        piezas_negras.add(new Alfil(1,3,false,"AlfilNegro.png",this));
    }
    
    @Override
    public void setColor(String color){
        if(color.equals("Azul")) board_file_path = "images/blue_board.png";
        if(color.equals("Rosa")) board_file_path = "images/pink_board.png";
        if(color.equals("Morado")) board_file_path = "images/magenta_board.png";
        if(color.equals("Verde")) board_file_path = "images/green_board.png";
    }
    MouseAdapter xd = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
                
        }
   
        @Override
        public void mousePressed(MouseEvent e) {
            
  

            int dX = e.getX();
            int dY = e.getY();  
            int fila_click = dY / casilla_width;
            int columna_click = dX / casilla_width;
       
            Pieza piezaClickeada;

            
            piezaClickeada = getPieza(columna_click, fila_click);

            
            if (pieza_activa == null && piezaClickeada != null){
                pieza_activa = piezaClickeada;    
            }
            if(pieza_activa != null && piezaClickeada == null){
                if(columna_click == 5&& fila_click == 1&&pieza_activa.getClass().getSimpleName().equals("Dama")&&pieza_activa.getX() == 2){
                        terminarTiempo();
                        tiempoTotal();

 
                            
                    JLabel l = new JLabel("<html>Es en serio?!?!?!?!<br>"
                            + "Viste el sacrificio de dama?!?!<br>"
                            + "Tardaste "+dif+" segundos!");
                    JFrame j = new JFrame();
                    j.add(l);
                    j.setTitle("Correcto!");
                    j.setBounds(10,10,300,150);
                    j.setLocationRelativeTo(null);
                    j.setVisible(true);
                    r.ace.stop();
                    r.ace.play();
                    
                    
                    pieza_activa.setX(columna_click); pieza_activa.setY(fila_click);
                    drawBoard();
                    pieza_activa = null;
                         victoria = true;intentos ++;
                        if(intentos == 1 && victoria) {
                            estrellas = 3;
                        }
                        if(intentos == 2 && victoria ){
                            estrellas = 2;
                        }
                        if(intentos > 3 && victoria){
                            estrellas = 1;
                        }
                        if(!victoria) estrellas = 0;
                        sumarEloAvanzado();
                }
                else{
                    errorMensaje();
                    restarEloAvanzado();
                }
            }


            

            piezas_blancas.clear();
            piezas_negras.clear();
            initGrid();
 
            pieza_activa = getPieza(e.getX()/64, e.getY()/64);

            System.out.println("Tactica Avanzada 2:"+pieza_activa.getClass().getSimpleName());

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) { 


                
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	  
    }; 

    public TacticaAvanzada3() {

        BoardGrid = new Integer[filas][columnas];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        piezas_blancas = new ArrayList();
        piezas_negras = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));
        this.addMouseMotionListener(xd);
        this.addMouseListener(xd);



        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }  
}
class TacticaAvanzada4_Grafico extends JFrame {
    TacticaAvanzada4 t = new TacticaAvanzada4();
    JPanel p = new JPanel();
    JLabel l = new JLabel();
    JButton b = new JButton();
    JLabel pista = new JLabel();
    boolean presionado = false;
    public TacticaAvanzada4_Grafico(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Tactica Avanzada 4");
        this.setResizable(true);
        l.setText("<html>Ganas ventaja decisiva en una jugada!<br>"
                    + "<i>Marangunic 0 - 1 Ljubojevic    1970</i>");
        p.add(l);
        b.setText("?");
        b.addActionListener(new ActionListener () {
          public void actionPerformed(ActionEvent e){
              if(!presionado){
                             l.setText(l.getText()+"<br>"
                      + "<b>Prueba mover la torre!</b><br>"
                      + "<b> ;)</b>");
                        presionado = true;
              }
          }
        });
        p.add(b);
        this.add(p,BorderLayout.EAST );        
        this.add(t, BorderLayout.CENTER);
        
        this.setLocation(500,200);
        this.pack();

    }
    public void run(){
        setVisible(true);
        
    }

}
class TacticaAvanzada4 extends Tablero{
    static int intentos = 0, estrellas = 0;
    boolean victoria = false;
    @Override
    public void initGrid(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                BoardGrid[i][j] = 0;
            }
        }
        piezas_blancas.add(new Rey(6,7,true,"ReyBlanco.png",this));
        piezas_blancas.add(new Caballo(3,2,true,"CaballoBlanco.png",this));
        piezas_blancas.add(new Dama(3,7,true,"ReinaBlanca.png",this));
        piezas_blancas.add(new Torre(5,7,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Peon(0,2,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(2,4,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(3,5,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(5,6,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(6,5,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(7,6,true,"PeonBlanco.png",this));
        
        piezas_negras.add(new Rey(6,0,false,"ReyNegro.png",this));
        piezas_negras.add(new Peon(0,1,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(2,1,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(4,3,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(5,4,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(7,1,false,"PeonNegro.png",this));
        piezas_negras.add(new Torre(1,0,false,"TorreNegra.png",this));
        piezas_negras.add(new Dama(7,5,false,"ReinaNegra.png",this));
        piezas_negras.add(new Alfil(6,1,false,"AlfilNegro.png",this));
    }
    
    @Override
    public void setColor(String color){
        if(color.equals("Azul")) board_file_path = "images/blue_board.png";
        if(color.equals("Rosa")) board_file_path = "images/pink_board.png";
        if(color.equals("Morado")) board_file_path = "images/magenta_board.png";
        if(color.equals("Verde")) board_file_path = "images/green_board.png";
    }
    MouseAdapter xd = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
                
        }
       
        @Override
        public void mousePressed(MouseEvent e) {
            


            int dX = e.getX();
            int dY = e.getY();  
            int fila_click = dY / casilla_width;
            int columna_click = dX / casilla_width;
   
            Pieza piezaClickeada;

            
            piezaClickeada = getPieza(columna_click, fila_click);

            
            if (pieza_activa == null && piezaClickeada != null){
                pieza_activa = piezaClickeada;    
            }
            if(pieza_activa != null && piezaClickeada == null){
                if(columna_click == 1&& fila_click == 7&&pieza_activa.getClass().getSimpleName().equals("Torre")&&pieza_activa.getX() == 1){
                        terminarTiempo();
                        tiempoTotal();

    
                    JLabel l = new JLabel("<html>Es en serio?!?!?!?!<br>"
                            + "Haz completado el ultimo nivel!"
                            + "Las tacticas te han servido?...<br>"
                            + "Tardaste "+dif+" segundos!");  
                    JFrame j = new JFrame();
                    j.add(l);
                    j.setTitle("Correcto!");
                    j.setBounds(10,10,300,150);
                    j.setLocationRelativeTo(null);
                    j.setVisible(true);
                    r.ace.stop();
                    r.ace.play();
                    intentos++;
                    
                    pieza_activa.setX(columna_click); pieza_activa.setY(fila_click);
                    drawBoard();
                    pieza_activa = null;
                         victoria = true;
                        if(intentos == 1 && victoria) {
                            estrellas = 3;
                        }
                        if(intentos == 2 && victoria ){
                            estrellas = 2;
                        }
                        if(intentos > 3 && victoria){
                            estrellas = 1;
                        }
                        if(!victoria) estrellas = 0;
                        sumarEloAvanzado();
                }
                else{
                    errorMensaje();
                    restarEloAvanzado();
                }
            }


            

            piezas_blancas.clear();
            piezas_negras.clear();
            initGrid();
 
            pieza_activa = getPieza(e.getX()/64, e.getY()/64);

            System.out.println("Tactica Avanzada 2:"+pieza_activa.getClass().getSimpleName());

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) { 


                
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	  
    }; 

    public TacticaAvanzada4() {

        BoardGrid = new Integer[filas][columnas];
        Static_Shapes = new ArrayList();
        Piece_Graphics = new ArrayList();
        piezas_blancas = new ArrayList();
        piezas_negras = new ArrayList();

        initGrid();

        this.setBackground(new Color(37,13,84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));
        this.addMouseMotionListener(xd);
        this.addMouseListener(xd);



        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }  
}
class Tablero extends JComponent {

    long t1, t2, dif;
    public void iniciarTiempo(){
        t1 = System.currentTimeMillis();
    }
    public void terminarTiempo(){
        t2 = System.currentTimeMillis();
    }
    public void tiempoTotal(){
        dif = (t2-t1)/1000;
    } 
    
    Repertorio_Musica r = new Repertorio_Musica();   int intentos = 0; 
    public int turnCounter = 0;
    private static Image NULL_IMAGE = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
    protected final int casilla_width = 65;
    public ArrayList<Pieza> piezas_blancas;
    public ArrayList<Pieza> piezas_negras;
    public ArrayList<DrawingShape> Static_Shapes;
    public ArrayList<DrawingShape> Piece_Graphics;
    public static Pieza pieza_activa = null;
    protected final int filas = 8;
    protected final int columnas = 8;
    protected Integer[][] BoardGrid;
    protected String board_file_path = "images/green_board.png";
    protected String active_square_file_path = "images/active_square.png";
    public void setColor(String color){
        if(color.equals("Azul")) board_file_path = "images/blue_board";
        if(color.equals("Rosa")) board_file_path = "images/pink_board";
        if(color.equals("Morado")) board_file_path = "images/magenta_board";
        if(color.equals("Verde")) board_file_path = "images/green_board";
    }
    public void initGrid(){
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                BoardGrid[i][j] = 0;
            }
        }
        //Esto seria equivalente a la posicion inicial
        piezas_blancas.add(new Rey(3,7,true,"ReyBlanco.png",this));
        piezas_blancas.add(new Dama(4,0,true,"ReinaBlanca.png",this));
        piezas_blancas.add(new Alfil(2,0,true,"AlfilBlanco.png",this));
        piezas_blancas.add(new Alfil(5,0,true,"AlfilBlanco.png",this));
        piezas_blancas.add(new Caballo(1,0,true,"CaballoBlanco.png",this));
        piezas_blancas.add(new Caballo(6,0,true,"CaballoBlanco.png",this));
        piezas_blancas.add(new Torre(0,0,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Torre(7,0,true,"TorreBlanca.png",this));
        piezas_blancas.add(new Peon(0,1,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(1,1,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(2,1,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(3,1,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(4,1,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(5,1,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(6,1,true,"PeonBlanco.png",this));
        piezas_blancas.add(new Peon(7,1,true,"PeonBlanco.png",this));

        piezas_negras.add(new Rey(3,0,false,"ReyNegro.png",this));
        piezas_negras.add(new Dama(4,7,false,"ReinaNegra.png",this));
        piezas_negras.add(new Alfil(2,7,false,"AlfilNegro.png",this));
        piezas_negras.add(new Alfil(5,7,false,"AlfilNegro.png",this));
        piezas_negras.add(new Caballo(1,7,false,"CaballoNegro.png",this));
        piezas_negras.add(new Caballo(6,7,false,"CaballoNegro.png",this));
        piezas_negras.add(new Torre(0,7,false,"TorreNegra.png",this));
        piezas_negras.add(new Torre(7,7,false,"TorreNegra.png",this));
        piezas_negras.add(new Peon(0,6,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(1,6,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(2,6,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(3,6,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(4,6,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(5,6,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(6,6,false,"PeonNegro.png",this));
        piezas_negras.add(new Peon(7,6,false,"PeonNegro.png",this));

    }
    protected void drawBoard(){
        Piece_Graphics.clear();
        Static_Shapes.clear();

        
        Image board = loadImage(board_file_path);
        Static_Shapes.add(new DrawingImage(board, new Rectangle2D.Double(0, 0, board.getWidth(null), board.getHeight(null))));
        if (pieza_activa != null){
            Image active_square = loadImage("images/active_square.png");
            Static_Shapes.add(new DrawingImage(active_square, new Rectangle2D.Double(casilla_width*pieza_activa.getX(),casilla_width*pieza_activa.getY(), active_square.getWidth(null), active_square.getHeight(null))));
        }
        
        
        for (int i = 0; i < piezas_blancas.size(); i++){
            int COL = piezas_blancas.get(i).getX();
            int FIL = piezas_blancas.get(i).getY();
            Image piece = loadImage("images/white_pieces/" + piezas_blancas.get(i).getFilePath());  
            Piece_Graphics.add(new DrawingImage(piece, new Rectangle2D.Double(casilla_width*COL,casilla_width*FIL, 65, 65)));
        }
        for (int i = 0; i < piezas_negras.size(); i++){
            int COL = piezas_negras.get(i).getX();
            int FIL = piezas_negras.get(i).getY();
            Image piece = loadImage("images/black_pieces/" + piezas_negras.get(i).getFilePath());  
            Piece_Graphics.add(new DrawingImage(piece, new Rectangle2D.Double(casilla_width*COL,casilla_width*FIL, 65, 65)));
        }
        this.repaint();
    }
    public Pieza getPieza(int x, int y) {
        for (Pieza p : piezas_blancas)
        {
            if (p.getX() == x && p.getY() == y)
            {
                return p;
            }
        }
        for (Pieza p : piezas_negras)
        {
            if (p.getX() == x && p.getY() == y)
            {
                return p;
            }
        }
        return null;
    }
    public void errorMensaje(){

        JLabel l = new JLabel("Incorrecto!"); intentos ++;
        JFrame j = new JFrame();
        j.add(l);
        j.setBounds(10,10,100,100);
        j.setLocationRelativeTo(null);
        j.setVisible(true);
        r.wrong.stop();
        r.wrong.play();
        pieza_activa = null;
    }
    public void sumarEloBasico(){
        if(u.elo <= 800) u.elo = 800;
        if(u.elo >= 800 && u.elo <= 1600) u.elo += 100;
        if(u.elo > 1600 && u.elo <= 2000) u.elo += 50;
        if(u.elo > 2000) u.elo += 30;  
    }
    public void restarEloBasico(){
        if(u.elo <= 800) u.elo = 800;
        if(u.elo >= 800 && u.elo <= 1600) u.elo -= 30;
        if(u.elo > 1600 && u.elo <= 2000) u.elo -= 50;
        if(u.elo > 2000) u.elo -= 100;  
    }
    public void sumarEloMedio(){
        if(u.elo <= 800) u.elo = 800;
        if(u.elo >= 800 && u.elo <= 1600) u.elo += 200;
        if(u.elo > 1600 && u.elo <= 2000) u.elo += 100;
        if(u.elo > 2000) u.elo += 60;  
    }
    public void restarEloMedio(){
        if(u.elo <= 800) u.elo = 800;
        if(u.elo >= 800 && u.elo <= 1600) u.elo -= 20;
        if(u.elo > 1600 && u.elo <= 2000) u.elo -= 40;
        if(u.elo > 2000) u.elo -= 80;  
    }
    public void sumarEloAvanzado(){
        if(u.elo <= 800) u.elo = 800;
        if(u.elo >= 800 && u.elo <= 1600) u.elo += 200;
        if(u.elo > 1600 && u.elo <= 2000) u.elo += 100;
        if(u.elo > 2000) u.elo += 60;  
    }
    public void restarEloAvanzado(){
        if(u.elo <= 800) u.elo = 800;
        if(u.elo >= 800 && u.elo <= 1600) u.elo -= 10;
        if(u.elo > 1600 && u.elo <= 2000) u.elo -= 20;
        if(u.elo > 2000) u.elo -= 60;  
    } 
    MouseAdapter mouseAdapter = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
                
        }
        int firstx, firsty;
        @Override
        public void mousePressed(MouseEvent e) {
            


            int dX = e.getX();
            int dY = e.getY();  
            int fila_click = dY / casilla_width;
            int columna_click = dX / casilla_width;
            boolean turno_blancas = true;
            Pieza piezaClickeada;
            if (turnCounter%2 == 1){
                turno_blancas = false;
            }
            
            piezaClickeada = getPieza(columna_click, fila_click);
            
            if (pieza_activa == null && piezaClickeada != null && 
                    ((turno_blancas && piezaClickeada.isWhite()) || (!turno_blancas && piezaClickeada.isBlack()))){
                pieza_activa = piezaClickeada;
            }
            else if (pieza_activa != null && pieza_activa.getX() == columna_click && pieza_activa.getY() == fila_click){
                pieza_activa = null;
            }
            else if (pieza_activa != null && pieza_activa.canMove(columna_click, fila_click) 
                    && ((turno_blancas && pieza_activa.isWhite()) || (!turno_blancas && pieza_activa.isBlack()))){
                if (piezaClickeada != null){
                    if (piezaClickeada.isWhite()) piezas_blancas.remove(piezaClickeada);
                    
                    else piezas_negras.remove(piezaClickeada);
                }
                pieza_activa.setX(columna_click);
                pieza_activa.setY(fila_click);       
                pieza_activa = null;
                turnCounter++;
            }

            firstx = e.getX()/64;firsty = e.getY()/64;
            pieza_activa = getPieza(e.getX()/64, e.getY()/64);
            
            System.out.println("Estas en tablero:"+pieza_activa.getClass().getSimpleName());
            drawBoard();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) { 


                
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	  
    };
    private void adjustShapePositions(double dx, double dy) {

        Static_Shapes.get(0).adjustPosition(dx, dy);
        this.repaint();

    } 
    private Image loadImage(String imageFile) {
        try {
                return ImageIO.read(new File(imageFile));
        }
        catch (IOException e) {
                return NULL_IMAGE;
        }
    }
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        drawBackground(g2);
        drawShapes(g2);
    }
    private void drawBackground(Graphics2D g2) {
        g2.setColor(getBackground());
        g2.fillRect(0,  0, getWidth(), getHeight());
    }
    private void drawShapes(Graphics2D g2) {
        for (DrawingShape shape : Static_Shapes) {
            shape.draw(g2);
        }	
        for (DrawingShape shape : Piece_Graphics) {
            shape.draw(g2);
        }
    }
    private ComponentAdapter componentAdapter = new ComponentAdapter() {

        @Override
        public void componentHidden(ComponentEvent e) {

        }

        @Override
        public void componentMoved(ComponentEvent e) {

        }

        @Override
        public void componentResized(ComponentEvent e) {

        }

        @Override
        public void componentShown(ComponentEvent e) {

        }	
    };
    private KeyAdapter keyAdapter = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }	
    };
}

interface DrawingShape {
    boolean contains(Graphics2D g2, double x, double y);
    void adjustPosition(double dx, double dy);
    void draw(Graphics2D g2);
}


class DrawingImage implements DrawingShape {
    public Image image;
    public Rectangle2D rect;
    public DrawingImage(Image image, Rectangle2D rect) {
            this.image = image;
            this.rect = rect;
    }
    @Override
    public boolean contains(Graphics2D g2, double x, double y) {
            return rect.contains(x, y);
    }
    @Override
    public void adjustPosition(double dx, double dy) {
            rect.setRect(rect.getX() + dx, rect.getY() + dy, rect.getWidth(), rect.getHeight());	
    }
    @Override
    public void draw(Graphics2D g2) {
        Rectangle2D bounds = rect.getBounds2D();
        g2.drawImage(image, (int)bounds.getMinX(), (int)bounds.getMinY(), (int)bounds.getMaxX(), (int)bounds.getMaxY(), 0, 0, image.getWidth(null), image.getHeight(null), null);
    }	
}


