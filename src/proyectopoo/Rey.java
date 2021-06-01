/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopoo;

/**
 *
 * @author irvin
 */
public class Rey extends Pieza{

    public Rey(int x, int y, boolean is_white, String file_path, Tablero board){
        super(x,y,is_white,file_path, board);
    }
    
    @Override
    public boolean canMove(int dx, int dy){

        return true;

    }
  
}
