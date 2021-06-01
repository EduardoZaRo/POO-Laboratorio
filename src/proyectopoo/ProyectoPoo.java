package proyectopoo;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.sound.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import static proyectopoo.PantallaPrincipal.u;


public class ProyectoPoo{
    public static void main(String[] args) throws IOException, InterruptedException {  
        PantallaPrincipal p = new PantallaPrincipal();
        p.main();
    }
}



