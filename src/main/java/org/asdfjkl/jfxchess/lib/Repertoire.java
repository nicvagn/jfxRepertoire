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
import org.asdfjkl.jfxchess.gui.RepertoireController;
import org.asdfjkl.jfxchess.gui.Chessboard;
import org.asdfjkl.jfxchess.gui.GameModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


/**
 * a repertoire with example games ilistrating lines
 */
public class Repertoire {

    private GameModel gameModel;
    private RepertoireController repertoireController;
    private RepertoireGamesController gamesController;
    private String repertoireName;
    private Tab repertoireTab;
    private Label repLinesLbl;
    private BorderPane repertoirePane; 

    //create an observable list for lines
    private ObservableList<RepertoireLine> repertoireLineList;

    //choice box for chosing line
    private ChoiceBox<RepertoireLine> repertoireLines;

    //buttons for rep. Tab
    private Button btnSaveRep;
    private Button btnImportGame;

    //VBox for the buttons
    private VBox actionButtons;

    //VBox for the lines
    private VBox chessLinesBx;


    /**
     * construct an empty repertoire
     * @param repertoireName
     * @param gameModel
     */
    public Repertoire(String repertoireName, GameModel gameModel, RepertoireController repertoireController){
        this.repertoireName = repertoireName;
        this.gameModel = gameModel;
        this.repertoireController = repertoireController;
        gamesController = repertoireController.getGamesController();

        repertoireLineList = FXCollections.observableArrayList();

        init();
    }

    /**
     * construct a repertoire with lines defined
     * @param repertoireName
     * @param gameModel
     * @param lines ArrayList<RepertoireLine> of pre defined lines
     */
    public Repertoire(String repertoireName, GameModel gameModel, RepertoireController repertoireController, ArrayList<RepertoireLine> lines){
        this.repertoireName = repertoireName;
        this.gameModel = gameModel;
        this.repertoireController = repertoireController;
        gamesController = repertoireController.getGamesController();


        //make a ObservableList of lines
        repertoireLineList = FXCollections.observableArrayList(lines);

        init();
    }

    public Tab getTab(){
        return repertoireTab;
    }


    public RepertoireController getControler(){
        return this.repertoireController;
    }

    public GameModel getGameModel(){
        return this.gameModel;
    }

    public ObservableList<RepertoireLine> getLines(){
        return this.repertoireLineList;
    }
    
    /**
     * add a line to the repertoire
     * @param line
     */
    public void addLine(RepertoireLine line){
        repertoireLineList.add(line);
        chessLinesBx = new VBox(repLinesLbl, repertoireLines);
        repertoirePane.setLeft(chessLinesBx);

    }

    public String getName(){
        return this.repertoireName;
    }


    /**
     * set up the Repertoire. Stuff that is common to all constructors
     */
    private void init(){
        // create buttons
        btnSaveRep = new Button("Save Repertoire");
        btnImportGame = new Button("Import Game");
        actionButtons = new VBox(10); //container for action buttons
        
        btnSaveRep.setMinWidth(140);
        btnSaveRep.setGraphic(new ImageView(new Image("icons/document-save.png")));

        btnImportGame.setMinWidth(140);
        btnImportGame.setGraphic(new ImageView(new Image("icons/document-enter-position.png")));

        //add the buttons to a HBox for easy grouping
        actionButtons.getChildren().addAll(btnSaveRep, btnImportGame);
        
        // display lines
        repertoireLines = new ChoiceBox<>();
        repertoireLines.setItems(repertoireLineList);
        repertoireLines.setMinWidth(300.0);

       
        //container
        repertoirePane = new BorderPane();

        //make VBox for chess lines
        repLinesLbl = new Label("Repertoire Line:");
        chessLinesBx = new VBox(repLinesLbl, repertoireLines);
 
        //set the chess lines choice box
        repertoirePane.setLeft(repertoireLines);

        //set the Instructive games
        repertoirePane.setCenter(gamesController.getInstructiveGamesVBox());

        //set te action buttons
        repertoirePane.setBottom(actionButtons);


        //create a tab for rep. information an populate it
        repertoireTab = new Tab("Repertoire", repertoirePane);

        //repertoirePane.setRight();
    }   
}
