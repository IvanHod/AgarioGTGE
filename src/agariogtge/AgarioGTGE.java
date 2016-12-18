/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agariogtge;


import com.golden.gamedev.GameLoader;
import game.GameView;

/**
 *
 * @author VIKA
 */
public class AgarioGTGE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new AgarIOGame(), GameView.GAME_WINDOW, false);
        game.start();
    }
    
}
