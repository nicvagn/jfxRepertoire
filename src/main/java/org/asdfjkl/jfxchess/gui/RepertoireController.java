package org.asdfjkl.jfxchess.gui;

import org.asdfjkl.jfxchess.lib.Board;
import org.asdfjkl.jfxchess.lib.InstructiveGame;
import org.asdfjkl.jfxchess.lib.RepertoireGamesController;
import org.asdfjkl.jfxchess.lib.RepertoireLine;
import org.asdfjkl.jfxchess.lib.Repertoire;

import javafx.scene.layout.VBox;

import org.asdfjkl.jfxchess.lib.Game;
import org.asdfjkl.jfxchess.lib.GameNode;

import java.util.ArrayList;

/**
 * Controller for repertoire 
 */
public class RepertoireController {

    private GameModel gameModel;
    private RepertoireGamesController repertoireGamesController;


    /**
     * create a new  controller
     * @param gameModel - the game model, you need this to change the big board
     */
    public RepertoireController(GameModel gameModel){
        this.repertoireGamesController = new RepertoireGamesController(gameModel);
        this.gameModel = gameModel;
        this. gameModel = new GameModel();
    }

    /**
     * get the asociated RepertoireGameControler
     * @return the RepertioreGameControler
     */
    public RepertoireGamesController getGamesController(){
        return this.repertoireGamesController;

    }

    /**
     * Open a repetoire
     * @param rep - repertoire to open
     */
    public void openRep(Repertoire rep) {
        this.gameModel = rep.getGameModel();
        gameModel.triggerStateChange();
        this.repertoireGamesController = rep.getControler().getGamesController();
    }


    /**
     * does this repertoire line have example games?
     * @return if it does
     */
    public boolean hasGames() {
        return this.repertoireGamesController.hasGames();
    }
}
