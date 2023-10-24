package org.asdfjkl.jfxchess.lib;


import java.util.ArrayList;

import org.asdfjkl.jfxchess.gui.RepertoireController;

import javafx.scene.control.Button;

/**
 * Instructive game for a praticular line of repertoire. Extends button because it's primary use is in a gui
 */
public class InstructiveGame extends Button {

    private Game instructiveGame;
    
    /**
     * make a new instructive game given a game
     * @param instructiveGame Game to be used to make instructive game
     * @param title the tittle of the game
     */
    public InstructiveGame(Game instructiveGame, String title){
        super(title);
        this.instructiveGame = instructiveGame;
        this.setText(title);
    }
    
    /**
     * construct a empty Instructive game containing a newGamee
     * @param title
     */
    public InstructiveGame(String title){
        super(title);
        this.instructiveGame = new Game();

    }
    
    /**
     * Get the instructive game as a normal game 
     * @return Game related to the InstructiveGame
     */
    public Game getGame(){
        return this.instructiveGame;
    }

}
