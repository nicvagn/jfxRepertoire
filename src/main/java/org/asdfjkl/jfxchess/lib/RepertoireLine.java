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

import java.awt.event.ActionListener;

import org.asdfjkl.jfxchess.gui.GameModel;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

/**
 * A line in the repertoire, with example games
 */
public class RepertoireLine extends MenuItem {

    private InstructiveGame[] inst_Games;
    private GameModel gameModel;
    private RepertoireGamesController controller;

    public RepertoireLine(String name, InstructiveGame[] instructiveGames, GameModel gameMode){
        super(name);
        this.inst_Games = instructiveGames;
        this.gameModel = gameMode;
        this.controller = new RepertoireGamesController(instructiveGames, gameModel);
        this.setText(name);

        //instructive games
        Label instructiveGamesLbl = new Label("Instructive Games:");
    }

    /**
     * get an array of this Rep. lines Instructive games
     * @return array of InstructiveGame
     */
    public InstructiveGame[] getIntructGames(){
        return this.inst_Games;
    }

    /**
     * get the line's name
     * @return the name
     */
    public String getlineName(){
        return this.getText();
    }

    public String toString(){
        return this.getText(); 
    }
}
