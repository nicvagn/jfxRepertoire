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

package org.asdfjkl.jfxchess.gui;

import org.asdfjkl.jfxchess.lib.RepertoireLine;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

import org.asdfjkl.jfxchess.lib.OpenRepLinesHandler;
import org.asdfjkl.jfxchess.lib.Repertoire;
import org.asdfjkl.jfxchess.lib.RepertoireController;

/**
 * A tab containing all the stuff for nrv's rep builder
 */
public class RepertoireTab extends Tab {

	RepertoireController controller;

	//the reps you can open
	ChoiceBox<Repertoire> repertoireList;

	// a way to chose what line to examine lines of open rep
	ChoiceBox<RepertoireLine> repertoireLines;

	// list of lines
	ObservableList<RepertoireLine> repertoireLineList;

	//handle opening lines on main board
	OpenRepLinesHandler repLinesHandler = new OpenRepLinesHandler();

	//main tab used to organize the tab
    BorderPane repertoirePane;


	/**
 	 * init a tab with a repertire
	 */
	public RepertoireTab(RepertoireController controller){
		
		super("Repertoire");
		this.controller = controller;
    }
}
