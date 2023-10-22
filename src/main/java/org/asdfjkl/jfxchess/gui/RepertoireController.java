package org.asdfjkl.jfxchess.gui;

import org.asdfjkl.jfxchess.lib.Board;
import org.asdfjkl.jfxchess.lib.InstructiveGame;
import org.asdfjkl.jfxchess.lib.Game;

import java.util.ArrayList;

/**
 * Controller for repertoire 
 */
public class RepertoireController {

    GameModel gameModel;

    /**
     * create a new  controller
     * @param gameModel - the game model, you need this to change the big board
     */
    public RepertoireController(GameModel gameModel){
        this.gameModel = gameModel;
    }

    /**
     * a helper function to set the initial instructive games
     * @param instructiveGames an array of instructive games
     * @return
     */
    public ArrayList<InstructiveGame> setInstructiveGames(InstructiveGame[] instructiveGames, GameModel gameModel){
        ArrayList<InstructiveGame> instructiveGamesBtns = new ArrayList<>(instructiveGames.length);

        int i = 0;
        while (i < instructiveGames.length){
            instructiveGamesBtns.add(instructiveGames[i]);
            i++;
        }
        return instructiveGamesBtns;
    }

    /**
     * set the main board of the gui 
     * @param board the board to set
     */
    public void setMainGame(Game game){

        gameModel.setGame(game);
        gameModel.triggerStateChange();

    }

    //open repertoire
    public void  openRep(Game game){
        //open a repertoire
        gameModel.setGame(game);
        gameModel.triggerStateChange();
        
    }

    //make a repertoire
    public void makeRep(double dialogHeight, BoardStyle style){
        Board board = gameModel.getGame().getCurrentNode().getBoard();
        DialogMakeRepertoire dlg = new DialogMakeRepertoire();
        double width = dialogHeight * 5;
        dlg.show(board, style, width, gameModel.THEME);

    }

    public void handleOpenRepertoire() {
        
    }
}
