package org.asdfjkl.jfxchess.gui;

import org.asdfjkl.jfxchess.lib.Board;
import org.asdfjkl.jfxchess.lib.InstructiveGame;
import org.asdfjkl.jfxchess.lib.OpenRepLinesHandler;
import org.asdfjkl.jfxchess.lib.RepertoireGamesController;
import org.asdfjkl.jfxchess.lib.RepertoireLine;
import org.asdfjkl.jfxchess.lib.Repertoire;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import org.asdfjkl.jfxchess.lib.Game;
import org.asdfjkl.jfxchess.lib.GameNode;

import java.util.ArrayList;

/**
 * Controller for repertoire 
 */
public class RepertoireController {
    //currently open repertoire
    private Repertoire openRepertoire;
    //list of all repertoires under this controler
    private ArrayList<Repertoire> repertoires;

    //game model that all reps will use
    public GameModel gameModel;

    //common game contraller
    public RepertoireGamesController gamesController;

    //set up a handler for opening lines from the choiceBox to main board
    private OpenRepLinesHandler repLinesHandler;



    //current repertoire stuff
    private RepertoireLine currentLine; //the currently displayed line in choiceBox
    private Tab repertoireTab;
    private Label repLinesLbl;
    private BorderPane repertoirePane; 


    //choice box for chosing line
    private ChoiceBox<RepertoireLine> repertoireLines; //list of the lines in this rep.

    //buttons for rep. Tab
    private Button btnSaveRep;
    private Button btnImportGame;

    //VBox for the buttons
    private VBox actionButtons;

    //VBox for the lines
    private VBox chessLinesBx;


    /**
     * create a new empty controller, with no repertoires
     */
    public RepertoireController(){

        //make a new empty list of repertoires
        this.repertoires = new ArrayList<Repertoire>();

        //make a game controler for this controler
        this.gamesController = new RepertoireGamesController(this);

        init(); //call stuff common to all initializers
    }

    /**
     * create a Repeptioire Controler with a list of Repertiores already made
     * @param repertoires list of pre-made reps
     */
    public RepertoireController(ArrayList<Repertoire> repertoires){

        //create a Repertoire controller with an existing list of repertoires
        this.repertoires = repertoires;
        
        //open the first repertoire of the list
        openRepertoire(repertoires.get(0));

        init(); //call stuff common to all initializers
    }

    /**
     * Open a repetoire
     * @param rep - repertoire to open
     */
    public void openRepertoire(Repertoire rep) {

        openRepertoire = rep;
        gameModel.triggerStateChange();
    }

    /**
     * set the instructive games for the selected line.
     * @param event
     */
    public void setInstructiveGames(ActionEvent event){
        
        //get the line from the choice box
        currentLine = repertoireLines.getValue();

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
    public Tab getRepertoireTab(){

        return repertoireTab;
    }

    /**
     * make a new repertoire with an existing set of lines, and open it
     * @param name
     * @param lines list of lines to make the repertoire already have
     * @return The made repertoire
     */
    public Repertoire newRepertoire(String name,ArrayList<RepertoireLine> lines){

        Repertoire rep = new Repertoire(name, lines);

		// open the new rep        
        openRepertoire(rep);
		// add the rep to the list of reps
		repertoires.add(rep);
		
        return rep;
    }



    /**
     * set up the Repertoire. Stuff that is common to all constructors
     */
    private void init(){

        // create buttons
        Button btnSaveRep = new Button("Save Repertoire");
        Button btnImportGame = new Button("Import Game");
        VBox actionButtons = new VBox(10); //container for action buttons
        
        btnSaveRep.setMinWidth(140);
        btnSaveRep.setGraphic(new ImageView(new Image("icons/document-save.png")));

        btnImportGame.setMinWidth(140);
        btnImportGame.setGraphic(new ImageView(new Image("icons/document-enter-position.png")));

        //add the buttons to a HBox for easy grouping
        actionButtons.getChildren().addAll(btnSaveRep, btnImportGame);
        
        // set up lines choicebox
        ChoiceBox<RepertoireLine> repertoireLines = new ChoiceBox<>();

        //link method setInstructiveGames to the choicebox
        repertoireLines.setOnAction(repLinesHandler::handle);

        ObservableList repertoireLineList;
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

        //create a tab for rep. information an populate it
        repertoireTab = new Tab("Repertoire", repertoirePane);

        //repertoirePane.setRight();
    }   
}
