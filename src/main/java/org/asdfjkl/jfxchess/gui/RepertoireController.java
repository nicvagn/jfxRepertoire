package org.asdfjkl.jfxchess.gui;

import org.asdfjkl.jfxchess.lib.Board;
import org.asdfjkl.jfxchess.lib.InstructiveGame;
import org.asdfjkl.jfxchess.lib.RepertoireGamesController;
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
        this.repertoireGamesController = new RepertoireGamesController(null,gameModel);
        this.gameModel = gameModel;
    }


    /**
     * get the asociated RepertoireGameControler
     * @return the RepertioreGameControler
     */
    public RepertoireGamesController getGamesController(){
        return this.repertoireGamesController;

    }

    //make a repertoire
    public void makeRep(double dialogHeight, BoardStyle style){
        Board board = gameModel.getGame().getCurrentNode().getBoard();
        DialogMakeRepertoire dlg = new DialogMakeRepertoire();
        double width = dialogHeight * 5;
        dlg.show(board, style, width, gameModel.THEME);

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
     * daoes this repertoire line have example games?
     * @return if it does
     */
    public boolean hasGames() {
        return this.repertoireGamesController.hasGames();
    }

}
