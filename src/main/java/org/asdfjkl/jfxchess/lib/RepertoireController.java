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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

import org.asdfjkl.jfxchess.gui.BoardStyle;
import org.asdfjkl.jfxchess.gui.DialogEnterPosition;
import org.asdfjkl.jfxchess.gui.GameModel;
import org.asdfjkl.jfxchess.gui.RepertoireTab;


/**
 * Controller for repertoire 
 */
public class RepertoireController {

    //currently open repertoire
    private Repertoire openRepertoire;

    //game model that all reps will use
    public GameModel gameModel;

    //currently open line
    public RepertoireLine currentLine; //the currently displayed line in choiceBox

    // observable list of all repertoires under this controler
    private ObservableList<Repertoire> repertoires;


    // GUI stuff ===================================================

    public RepertoireTab repertoireTab = new RepertoireTab(this);

    //label for rep lines (repertior lines)
    private final Label repLinesLbl = new Label("Repertoire Line:");

    //label for Repertiores
    private final Label repLabel = new Label("Repertoires:");

    //main pane for the rep. tab
    private BorderPane repertoirePane;
	
    //a choice box for chosing repertoire
    public ChoiceBox<Repertoire> repertoireChoiceBx =  new ChoiceBox<Repertoire>();
    
    //choice box for chosing line
    public ChoiceBox<RepertoireLine> repertoireLinesChoiceBx; 
    
    //buttons for rep. Tab
    public final Button btnSaveRep = new Button("Save Repertoire");
    public final Button btnImportGame = new Button("Import Game");
    public final Button btnOpenRep = new Button("Open Repertoire");

    public final Button btnMakeRep = new Button("New Repertoire");

    public final Button btnOpenRepTop = new Button("Open Repertoire");

    //VBox for the buttons
    private VBox actionButtons = new VBox();

    //VBox for the lines
    private VBox repeptioireLinesBx;

    //VBox for the Reps.
    private VBox repertoiresBx;

    //end GUI stuff ==================================================



    /**
     * create a new empty controller, with no repertoires
     */
    public RepertoireController(){

        //make a new empty list of repertoires
        this.repertoires = FXCollections.observableArrayList();

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


		//set the game model
		this.gameModel = openRepertoire.getGameModel();

		//set up rep.Tab with buttons and shit
		populateRepertoireTab();

    }

    /**
     * Open a repetoire
     * @param rep - repertoire to open
     */
    public void openRepertoire(Repertoire rep) {
	
        openRepertoire = rep;
		this.gameModel = rep.getGameModel();

        //add the open rep. lines to the rep. lines choice box
        repertoireLinesChoiceBx.getItems().addAll(openRepertoire.getLines());
        
        //make VBox for chess lines
    	repeptioireLinesBx = new VBox(repLinesLbl, repertoireLinesChoiceBx);


        this.gameModel.triggerStateChange();

    }

    /**
     * open a rep. when you choose one in the choice
     * @param event
     */
    private void openRepertoire(ActionEvent event){

        this.openRepertoire = repertoireChoiceBx.getValue();
        openRepertoire(this.openRepertoire);

        System.out.println("Reper open function called");

        //set the example games for the opened rep

    }

    /**
     * create a new window to open a rep. from a save
     */
    public void openRepertoire(){
        Board board = gameModel.getGame().getCurrentNode().getBoard();
        DialogEnterPosition dlg = new DialogEnterPosition();
        double width = 600;
        //double width = dialogHeight * 1.7;
        boolean accepted = dlg.show(board, new BoardStyle(), width, 600, gameModel.THEME);
        if(accepted) {
            Board newBoard = dlg.getCurrentBoard();
            if(newBoard.isConsistent()) {
                Game g = new Game();
                g.getRootNode().setBoard(newBoard);
                gameModel.setGame(g);
                gameModel.getGame().setTreeWasChanged(true);
                gameModel.getGame().setHeaderWasChanged(true);
                gameModel.triggerStateChange();
            }
        }
    }


    /**
     * get the open rep.
     * @return open rep.
     */
    public Repertoire getOpenRepertoire(){

        return this.openRepertoire;
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
     * get the rep. tab for display on the main gui
     * @return the tab
     */
    public RepertoireTab getRepertoireTab(){

        return repertoireTab;
    }

    /**
     * hadle line change. A line change is when a new line is chosen in the lines ChoiceBox
     * @param the action even trigered by the line change in the ChoiceBox
     */
    public void handleLineChange(ActionEvent event){


    }

    /**
     * set the main board of the gui 
     * @param game the Game to set
     */
    public void setMainGame(Game game){

        this.gameModel.setGame(game);
        this.gameModel.triggerStateChange();

    }

    /**
     * set the main board of the gui 
     * @param instructiveGame the InstructiveGame to set
     */
    public void setMainGame(InstructiveGame instructiveGame){

        Game game = instructiveGame.getGame();

        this.gameModel.setGame(game);
        this.gameModel.triggerStateChange();

    }



	/**
	 * populate the repertire Tab
	 */
	private void populateRepertoireTab(){

    	btnSaveRep.setMinWidth(200);
    	btnSaveRep.setGraphic(new ImageView(new Image("icons/document-save.png")));

		btnImportGame.setMinWidth(200);
    	btnImportGame.setGraphic(new ImageView(new Image("icons/document-enter-position.png")));

        btnOpenRep.setMinWidth(200);
        btnOpenRep.setGraphic(new ImageView(new Image("icons/database.png")));

        //top menu buttons
        btnOpenRepTop.setGraphic(new ImageView( new Image("icons/database.png")));
        btnOpenRepTop.setContentDisplay(ContentDisplay.TOP);

        btnMakeRep.setGraphic(new ImageView( new Image("icons/database.png")));
        btnMakeRep.setContentDisplay(ContentDisplay.TOP);

	    //add the buttons to a HBox for easy grouping
    	actionButtons.getChildren().addAll(btnOpenRep, btnSaveRep, btnImportGame);

        //make a choicebox to pick open rep
        repertoireChoiceBx = new ChoiceBox<>(repertoires);
        
        repertoireChoiceBx.setOnAction(this::openRepertoire);
    	repertoireChoiceBx.setMinWidth(300.0);

    	// set up lines choicebox
    	repertoireLinesChoiceBx = new ChoiceBox<RepertoireLine>();

    	//link method setInstructiveGames to the choicebox
    	repertoireLinesChoiceBx.setOnAction(this::handleLineChange);
    	repertoireLinesChoiceBx.setMinWidth(300.0);
		
    	//container
    	repertoirePane = new BorderPane();

    	//set the action buttons
    	repertoirePane.setBottom(actionButtons);
        //set the rep choicebx
        repertoirePane.setLeft(repertoiresBx);
        
		//set the tab content to the pane we constructed
		repertoireTab.setContent(repertoirePane);

	}
}
