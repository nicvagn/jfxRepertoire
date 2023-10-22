package org.asdfjkl.jfxchess.lib;

import org.asdfjkl.jfxchess.gui.GameModel;

import javafx.scene.control.Button;

/**
 * Instructive game for a praticular line of repertoire. Extends button because it's primary use is in a gui
 */
public class InstructiveGame extends Button {

    private Game instructiveGame;
    private GameModel gameModel;

    public InstructiveGame(Game instructiveGame, String title, GameModel gameModel){
        super(title);
        this.instructiveGame = instructiveGame;
        this.setText(title);
        this.gameModel = gameModel;
    }
    
    public Game getGame(){
        return this.instructiveGame;
    }

}
