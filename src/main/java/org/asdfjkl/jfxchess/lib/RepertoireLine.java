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

import org.asdfjkl.jfxchess.gui.GameModel;
import org.asdfjkl.jfxchess.gui.RepertoireController;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

/**
 * A line in the repertoire, with example games
 */
public class RepertoireLine extends MenuItem {

    private InstructiveGame[] instructiveGames;
    private RepertoireController repertoireController;
    /**
     * construct a rep. line with instructive games
     * @param name
     * @param instructiveGames
     * @param repertoireController
     */
    public RepertoireLine(String name, RepertoireController repertoireController){
        super(name); //super constructer for Button()
        this.instructiveGames = new InstructiveGame[0]; //construct a new line w/o inst games
        this.repertoireController = repertoireController;
    }

    /**
     * construct a rep. line with instructive games
     * @param name
     * @param instructiveGames
     * @param repertoireController
     */
    public RepertoireLine(String name, InstructiveGame[] instructiveGames, RepertoireController repertoireController){
        super(name); //super constructer for Button()
        this.instructiveGames = instructiveGames;
        this.repertoireController = repertoireController;
    }

    /**
     * get an array of this Rep. lines instructiveGamesuctive games
     * @return array of instructiveGame
     */
    public InstructiveGame[] getIntructGames(){
        return this.instructiveGames;
    }

    /**
     * add an instructiveGamesuctive Game to this line
     */
    public void addinstructiveGame(InstructiveGame game){
        //if instructive games has 1 or more elements
        if(instructiveGames.length > 0){
            InstructiveGame[] newinstructiveGames = new InstructiveGame[this.instructiveGames.length];

            int i;
            for(i = 0; i < this.instructiveGames.length; i++){
                newinstructiveGames[i] = instructiveGames[i];
            }

            newinstructiveGames[i] = game;
            this.instructiveGames = newinstructiveGames;
        }else{
            instructiveGames = new InstructiveGame[]{game};
        }

    }

    /**
     * get the line's name
     * @return the name
     */
    public String getlineName(){
        return this.getText();
    }

    /**
     * @return the tittle of the line
     */
    public String toString(){
        return this.getText(); 
    }
}
