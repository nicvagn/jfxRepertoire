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
import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


/**
 * Open the games associated with a given repertoire line
 */
public class LineGameController {

    private RepertoireController controller;
    Label repGamesLbl = new Label("Line example Games");

    private ArrayList<InstructiveGame> instructiveGames;
    private VBox instructiveGamesVBox;

    boolean hasGames = false; //are there games?



    /**
     * Construct a games controler allready containing InstructiveGames
     * @param instructiveGames
     * @param repController
     */
    public LineGameController(ArrayList<InstructiveGame> instructiveGames, RepertoireController controller) {
      
        this.controller = controller;
        this.instructiveGames = instructiveGames;
        instructiveGamesVBox = new VBox(repGamesLbl);
    }

    /**
     * Construct a LineGameControler with no games
     */
    public LineGameController(RepertoireController controller) {
    
        this.instructiveGames = new ArrayList<InstructiveGame>();

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

        InstructiveGame instructiveGame = new InstructiveGame(title);
        return instructiveGame;
    }

    /**
     * make a new Instructive game with a board setup at a given fen
     * @return Game - InstructiveGame with its board set to fen 
     */
    public InstructiveGame makeInstructiveGame(String title, String fen){
        
        return new InstructiveGame(title, fen);
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
     * @param ig InstructiveGame to add
     */
    private void  addGame(InstructiveGame ig) { 
   
       instructiveGames.add(ig);
    }
}
