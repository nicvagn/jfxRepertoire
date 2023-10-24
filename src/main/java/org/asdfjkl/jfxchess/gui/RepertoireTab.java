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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import org.asdfjkl.jfxchess.lib.OpenRepLinesHandler;

/**
 * A tab containing all the stuff for nrv's rep builder
 */
public class RepertoireTab extends Tab {

	// a way to chose what line to examine
	ChoiceBox<RepertoireLine> repertoireLines;

	// list of lines
	ObservableList<RepertoireLine> repertoireLineList;

	Button btnSaveRep = new Button("Save Repertoire");
	Button btnImportGame = new Button("Import Game");
	VBox actionButtons = new VBox(10); // container for action buttons

	//box and label for the lines
	Label repLinesLbl;
	VBox chessLinesBx;

	OpenRepLinesHandler repLinesHandler = new OpenRepLinesHandler();

	//main tab used to organize the tab
    BorderPane repertoirePane; 


	/**
    * init a tab with a repertire
    */
	public RepertoireTab(){
		super("Repertoire");
    	btnSaveRep.setMinWidth(140);
    	btnSaveRep.setGraphic(new ImageView(new Image("icons/document-save.png")));

	    btnImportGame.setMinWidth(140);
    	btnImportGame.setGraphic(new ImageView(new Image("icons/document-enter-position.png")));

	    //add the buttons to a HBox for easy grouping
    	actionButtons.getChildren().addAll(btnSaveRep, btnImportGame);
		
    	// set up lines choicebox
    	repertoireLines = new ChoiceBox<>();
    	//link method setInstructiveGames to the choicebox
    	repertoireLines.setOnAction(repLinesHandler::handle);
    	repertoireLines.setMinWidth(300.0);
		
    	//container
    	repertoirePane = new BorderPane();
    	//make VBox for chess lines
    	repLinesLbl = new Label("Repertoire Line:");
    	chessLinesBx = new VBox(repLinesLbl, repertoireLines);

	    //set the chess lines choice box
    	repertoirePane.setLeft(repertoireLines);
    	//set te action buttons
    	repertoirePane.setBottom(actionButtons);

		//set the pane content to the constructed pane
		this.setContent(repertoirePane);
    }

}
