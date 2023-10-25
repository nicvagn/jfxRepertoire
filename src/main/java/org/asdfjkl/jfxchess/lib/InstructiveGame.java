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



import javafx.scene.control.Button;

/**
 * Instructive game for a praticular line of repertoire. Extends button because it's primary use is in a gui
 */
public class InstructiveGame extends Button {

    private Game instructiveGame;
    
    /**
     * make a new instructive game with a given position
     * @param fen to use to make Instructive Game
     * @param title the tittle of the game
     */
    public InstructiveGame(String title, String fen){
        
        super(title); //set the tittal of the button
        
        Board tBoard = new Board(fen);

        Game game = new Game();
        GameNode gNode = new GameNode();  //you need the gNode to set the fen

        gNode.setBoard(tBoard);
        game.setCurrent(gNode);

        this.instructiveGame = game;
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
