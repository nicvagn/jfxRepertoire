/* JerryFX - A Chess Graphical User Interface with rep. Builder
 * Copyright (C) 2023 Nicolas Vaagen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */


package org.asdfjkl.jfxchess.lib;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.asdfjkl.jfxchess.gui.GameModel;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Open the games associated with a given repertoire line
 */
public class RepertoireGamesController {

    private GameModel gameModel;
    private InstructiveGame[] instructiveGames;
    private VBox instructiveGamesVBox;
    boolean hasGames = false; //are there games?

    public RepertoireGamesController(InstructiveGame[] instructiveGames, GameModel gameModel) {
        this.instructiveGames = instructiveGames;
        this.gameModel = gameModel;
        Label repGamesLbl = new Label("Line example Games");
        instructiveGamesVBox = new VBox(repGamesLbl);

        if(instructiveGames != null){
            // go through list of instructive games and add them to vbox
            for (int i = 0; i < instructiveGames.length; i++) {
                instructiveGamesVBox.getChildren().add(instructiveGames[i]);
            }
        }
    }

    public RepertoireGamesController(GameModel gameModel) {
        this.gameModel = gameModel;
        this.instructiveGames = new InstructiveGame[0];

        Label repGamesLbl = new Label("Line example Games");
        instructiveGamesVBox = new VBox(repGamesLbl);
    }
    /**
     * add an instructive game
     * @param ig instructive game
     */
    public void addInstructiveGame(InstructiveGame ig){
        addGame(ig);
        instructiveGamesVBox.getChildren().add(ig);

        hasGames = true;
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
     * get the instructive games VBox associated with this repertoire
     * @return the vBox
     */
    public VBox getInstructiveGamesVBox(){
        return this.instructiveGamesVBox;
    }

     /**
     * make a new Instructive game
     * @return Game - Instructive Game
     */
    public InstructiveGame makeInstructiveGame(String title){
        Game tempGame = new Game();
        GameModel tempGameModel = new GameModel();

        InstructiveGame instructiveGame = new InstructiveGame(tempGame, title, tempGameModel);
        return instructiveGame;
    }

    /**
     * make a new Instructive game with a board setup at a given fen
     * @return Game - InstructiveGame with its board set to fen 
     */
    public InstructiveGame makeInstructiveGame(String title, String fen){
        Game tempGame = new Game();
        Board tempBoard = new Board(fen);
        GameModel tempGameModel = new GameModel();

        //set the board displaying fen
        tempGame.getRootNode().setBoard(tempBoard);

        tempGameModel.setGame(tempGame);

        InstructiveGame instructiveGame = new InstructiveGame(tempGame, title, tempGameModel);
        return instructiveGame;        
    }


    /**
     * set the main board of the gui 
     * @param board the board to set
     */
    public void setMainGame(Game game){

        gameModel.setGame(game);
        gameModel.triggerStateChange();

    }

    /**
     * does this Repertoire line have games
     * @return this.hasGames
     */
    public boolean hasGames(){
        return this.hasGames;
    }

    /**
     * add an istructive game
     * @param arr array of current instructive games
     * @param ig new instructive game to add
     * @return {arr + ig}
     */
    private void  addGame(InstructiveGame ig) { 
        int n = instructiveGames.length; 
   
        // create a new array of size n+1 
        InstructiveGame newInstructiveGame[] = new InstructiveGame[n + 1]; 
   
        // insert the elements from 
        // the old array into the new array 
        // insert all elements till n 
        // then insert x at n+1 
        for (int i = 0; i < n; i++){
            newInstructiveGame[i] = instructiveGames[i]; 
        }
        newInstructiveGame[n] = ig; 
   
       instructiveGames = newInstructiveGame; 
    }
}
