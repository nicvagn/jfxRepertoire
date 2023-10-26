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

import java.util.ArrayList;

import javafx.scene.control.MenuItem;

/**
 * A line in the repertoire, with example games
 */
public class RepertoireLine extends MenuItem {

    private LineGameController lineGameController;
    private RepertoireController controller;
    
    /**
     * construct a rep. line with no instructive games
     * @param name passed to the button constructor
     */
    public RepertoireLine(String name, RepertoireController controller){

        super(name); //super constructer for Button()
        this.lineGameController = new LineGameController(controller); //construct a new line w/o inst games
    }

    /**
     * construct a rep. line with instructive games
     * @param name passed to the button constructor
     * @param instructiveGames ArrayList of the instructive games for this line
     */
    public RepertoireLine(String name, RepertoireController controller, ArrayList<InstructiveGame> instructiveGames){

        super(name); //super constructer for Button()

        this.controller = controller;
        //make a game controller for this line
        lineGameController = new LineGameController(instructiveGames, controller);
    }

    /**
     * get an ArrayList of this Rep. lines instructiveGames
     * @return arrayList of instructiveGame
     *
    public ArrayList<InstructiveGame> getIntructGames(){

        return this.lineGameController.g;
    }

    */


    /**
     * get the line's name
     * @return the name
     */
    public String getlineName(){

        return this.getText();
    }

    /**
     * get the line's game controler
     * @return this lines game controler
     */
    public LineGameController getlineGameController(){

        return this.lineGameController;
    }

    /**
     * @return the name of the line
     */
    public String toString(){
        
        return this.getText(); 
    }
}
