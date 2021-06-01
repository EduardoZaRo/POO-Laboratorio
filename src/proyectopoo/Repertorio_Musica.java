/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopoo;

import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author irvin
 */
public class Repertorio_Musica extends Thread{
    JFXPanel fxPanel = new JFXPanel();
    boolean isMusic;
    String seleccion = "";
    public void setCancion(String seleccion){
        this.seleccion = seleccion;
    }
    String path_1 = "sounds/db_1.mp3";
    Media media_1 = new Media(new File(path_1).toURI().toString());
    MediaPlayer mp_1 = new MediaPlayer(media_1);
    
    String path_2 = "sounds/chopin.mp3";
    Media media_2 = new Media(new File(path_2).toURI().toString());
    MediaPlayer mp_2 = new MediaPlayer(media_2);
    
    String path_3 = "sounds/suavemente.mp3";
    Media media_3 = new Media(new File(path_3).toURI().toString());
    MediaPlayer mp_3 = new MediaPlayer(media_3);
    
    String path_4 = "sounds/doom.mp3";
    Media media_4 = new Media(new File(path_4).toURI().toString());
    MediaPlayer mp_4 = new MediaPlayer(media_4);
    
    String path_5 = "sounds/single_kill.mp3";
    Media media_5 = new Media(new File(path_5).toURI().toString());
    MediaPlayer singlekill = new MediaPlayer(media_5); 
    
    String path_6 = "sounds/double_kill.mp3";
    Media media_6 = new Media(new File(path_6).toURI().toString());
    MediaPlayer doublekill = new MediaPlayer(media_6);  
    
    String path_7 = "sounds/triple_kill.mp3";
    Media media_7 = new Media(new File(path_7).toURI().toString());
    MediaPlayer triplekill = new MediaPlayer(media_7); 
    
    String path_8 = "sounds/cuadra_kill.mp3";
    Media media_8 = new Media(new File(path_8).toURI().toString());
    MediaPlayer cuadrakill = new MediaPlayer(media_8);  
    
    String path_9 = "sounds/ace.mp3";
    Media media_9 = new Media(new File(path_9).toURI().toString());
    MediaPlayer ace = new MediaPlayer(media_9);    
    String path_10 = "sounds/wrong.mp3";
    Media media_10 = new Media(new File(path_10).toURI().toString());
    MediaPlayer wrong = new MediaPlayer(media_10);      
    
    
    
    
}
