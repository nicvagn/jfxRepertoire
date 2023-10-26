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

import org.asdfjkl.jfxchess.gui.RepertoireTab;
import org.asdfjkl.jfxchess.gui.GameModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * a repertoire with example games ilistrating lines
 */
public class Repertoire {

    private final String repertoireName;
    private final RepertoireController controller;


    //create an observable list for lines
    private ObservableList<RepertoireLine> repertoireLineList;


    /**
     * construct an empty repertoire
     * @param repertoireName
     * @param controler the RepertireController that made this rep
     */
    public Repertoire(String repertoireName, RepertoireController controller){

        this.controller = controller;
        this.repertoireName = repertoireName;
        repertoireLineList = FXCollections.observableArrayList();
    }

    /**
     * construct a repertoire with lines defined
     * @param repertoireName
     * @param controler the RepertireController that made this rep
     * @param lines ArrayList<RepertoireLine> of pre defined lines
     */
    public Repertoire(String repertoireName, RepertoireController controller, ArrayList<RepertoireLine> lines){

        this.repertoireName = repertoireName;
        this.controller = controller;

        //make a ObservableList of lines
        repertoireLineList = FXCollections.observableArrayList(lines);
    }

    /**
     * add a line to the repertoire
     * @param line
     */
    public void addLine(RepertoireLine line){

        repertoireLineList.add(line);
    }

	/**
	 * get the game controler used by this rep
	 * @return the game model
	 */
    public GameModel getGameModel(){

        return controller.gameModel;
    }

    /**
	 * get the observable list or lines asociated with this rep
	 * @return rep. line list
	 */
	public ObservableList<RepertoireLine> getLines(){

        return this.repertoireLineList;
    }
    

    /**
     * get the name of this Repertoire
     * @return the name
     */
    public String getName(){

        return this.repertoireName;
    }

    /**
     * reps string representation is just it's name
     */
    public String toString(){

        return this.repertoireName;
    }
}
