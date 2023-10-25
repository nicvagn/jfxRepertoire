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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

import org.asdfjkl.jfxchess.gui.GameModel;
import org.asdfjkl.jfxchess.gui.RepertoireTab;


/**
 * Controller for repertoire 
 */
public class RepertoireController {

    //currently open repertoire
    private Repertoire openRepertoire;
	
    // observable list of all repertoires under this controler
    private ObservableList<Repertoire> repertoires;

    //game model that all reps will use
    public GameModel gameModel;

    //common game contraller
    public RepertoireGamesController gamesController;

    //currently open line
    public RepertoireLine currentLine; //the currently displayed line in choiceBox


    public RepertoireTab repertoireTab = new RepertoireTab(this);
    //label for rep lines (repertior lines)
    private Label repLinesLbl;
    //main pane for the rep. tab
    private BorderPane repertoirePane;
	

    //a choice box for chosing repertoire
    public ChoiceBox<Repertoire> repertoireChoiceBx =  new ChoiceBox<Repertoire>();
    //choice box for chosing line
    public ChoiceBox<RepertoireLine> repertoireLinesChoiceBx; 
    
    //buttons for rep. Tab
    private Button btnSaveRep = new Button("Save Repertoire");
    private Button btnImportGame = new Button("Import Game");

    //VBox for the buttons
    private VBox actionButtons = new VBox();

    //VBox for the lines
    private VBox chessLinesBx = new VBox();



    /**
     * create a new empty controller, with no repertoires
     */
    public RepertoireController(){

        //make a new empty list of repertoires
        this.repertoires = FXCollections.observableArrayList();

        //make a game controler for this controler
        this.gamesController = new RepertoireGamesController(this);

		//set the game model
		this.gameModel = new GameModel();

		//set up rep.Tab with buttons and shit
		populateRepertoireTab();

    }

    /**
     * create a Repeptioire Controler with a list of Repertiores already made
     * @param repertoires list of pre-made reps
     */
    public RepertoireController(ObservableList<Repertoire> repertoires){

        //create a Repertoire controller with an existing list of repertoires
        this.repertoires = repertoires;

        //open the first repertoire of the list
        openRepertoire(repertoires.get(0));

		//make a game controler for this controler
        this.gamesController = new RepertoireGamesController(this);

		//set the game model
		this.gameModel = openRepertoire.getGameModel();

		//set up rep.Tab with buttons and shit
		populateRepertoireTab();

    }

	/**
     * get the asociated RepertoireGameControler
     * @return the RepertioreGameControler
     */
    public RepertoireGamesController getGamesController(){

        return this.gamesController;

    }

    /**
     * Open a repetoire
     * @param rep - repertoire to open
     */
    public void openRepertoire(Repertoire rep) {
	
        openRepertoire = rep;
		this.gameModel = rep.getGameModel();
		repertoireTab = rep.getRepertoireTab();

        //when you oppen a new rep. reset the lines bx
        repertoireLinesChoiceBx = new ChoiceBox<>();

        //add the open rep. lines to the rep. lines choice box
        repertoireLinesChoiceBx.getItems().addAll(openRepertoire.getLines());

        this.gameModel.triggerStateChange();

    }

    /**
     * open a rep. when you choose one in the choice
     * @param event
     */
    private void openRepertoire(ActionEvent event){

        this.openRepertoire = repertoireChoiceBx.getValue();
        openRepertoire(this.openRepertoire);
    }


    /**
     * set the instructive games for the selected line.
     * @param event
     */
    private void setInstructiveGames(ActionEvent event){
        
        //get the line from the choice box
        currentLine = repertoireLinesChoiceBx.getValue();

        gamesController.setInstructiveGames(currentLine.getIntructGames());

        //set the Instructive games
        repertoirePane.setCenter(gamesController.getInstructiveGamesVBox());
    }



    /**
     * get the open rep.
     * @return open rep.
     */
    public Repertoire getOpenRepertoire(){

        return this.openRepertoire;
    }


    /**
     * get the rep. tab for display on the main gui
     * @return the tab
     */
    public RepertoireTab getRepertoireTab(){

        return repertoireTab;
    }

	/**
	 * populate the repertire Tab
	 */
	private void populateRepertoireTab(){

    	btnSaveRep.setMinWidth(200);
    	btnSaveRep.setGraphic(new ImageView(new Image("icons/document-save.png")));

		btnImportGame.setMinWidth(200);
    	btnImportGame.setGraphic(new ImageView(new Image("icons/document-enter-position.png")));

	    //add the buttons to a HBox for easy grouping
    	actionButtons.getChildren().addAll(btnSaveRep, btnImportGame);

        //make a choicebox to pick open rep
        repertoireChoiceBx = new ChoiceBox<>(repertoires);
        
        repertoireChoiceBx.setOnAction(this::openRepertoire);
    	repertoireChoiceBx.setMinWidth(300.0);

    	// set up lines choicebox
    	repertoireLinesChoiceBx = new ChoiceBox<RepertoireLine>();

    	//link method setInstructiveGames to the choicebox
    	repertoireLinesChoiceBx.setOnAction(this::setInstructiveGames);
    	repertoireLinesChoiceBx.setMinWidth(300.0);
		
    	//container
    	repertoirePane = new BorderPane();

    	//make VBox for chess lines
    	repLinesLbl = new Label("Repertoire Line:");
    	chessLinesBx = new VBox(repLinesLbl, repertoireLinesChoiceBx);

	    //set the chess lines choice box
    	repertoirePane.setLeft(chessLinesBx);
    	//set the action buttons
    	repertoirePane.setBottom(actionButtons);
        //set the rep choicebx
        repertoirePane.setTop(repertoireChoiceBx);
		//set the tab content to the pane we constructed
		repertoireTab.setContent(repertoirePane);
        //set the 

	}

    /**
     * make a new repertoire with an existing set of lines, and open it
     * @param name
     * @param lines list of lines to make the repertoire already have
     * @return The made repertoire
     */
    public Repertoire newRepertoire(String name, ArrayList<RepertoireLine> lines){

        Repertoire rep = new Repertoire(name, this, lines);

		// open the new rep        
        openRepertoire(rep);
		// add the rep to the list of reps
		repertoires.add(rep);

        //add lines given to the Repertoire
        for(RepertoireLine line: lines){
            rep.addLine(line);
        }

        return rep;
    }

    /**
     * handle a change in the repertoireLinesChoiceBx
     * @param event
     */
    private void handleLineChange(ActionEvent event){
        //set the current line
        currentLine = this.repertoireLinesChoiceBx.getSelectionModel().getSelectedItem();
        
        gamesController.setInstructiveGames(currentLine.getIntructGames());
    }

}
