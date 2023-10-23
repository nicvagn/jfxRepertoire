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
    private String repertoireName;
    private Tab repertoireTab;
    private TabPane repertoireTabPane;


    //create an observable list for lines
    private ObservableList<RepertoireLine> reperetoireLineList;


    //buttons for rep. Tab
    private Button btnSaveRep;
    private Button btnImportGame;

    //VBox for the buttons
    private VBox actionButtons;

    private Chessboard repChessboard;


    /**
     * construct an empty repertoire
     * @param repertoireName
     * @param gameModel
     */
    public Repertoire(String repertoireName, GameModel gameModel){
        this.repertoireName = repertoireName;
        this.gameModel = gameModel;
        repertoireController = new RepertoireController(gameModel);
        reperetoireLineList = FXCollections.observableArrayList();

        init();
    }

    /**
     * construct a repertoire with lines defined
     * @param repertoireName
     * @param gameModel
     * @param lines ArrayList<RepertoireLine> of pre defined lines
     */
    public Repertoire(String repertoireName, GameModel gameModel, ArrayList<RepertoireLine> lines){
        this.repertoireName = repertoireName;
        this.gameModel = gameModel;
        repertoireController = new RepertoireController(gameModel);

        //make a ObservableList of lines
        reperetoireLineList = FXCollections.observableArrayList(lines);

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
        return this.reperetoireLineList;
    }

    public void addLine(RepertoireLine line){
        reperetoireLineList.add(line);
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
        ChoiceBox<RepertoireLine> chessLines = new ChoiceBox<>();
        chessLines.getItems().addAll(reperetoireLineList);
        chessLines.setMinWidth(300.0);

       
        //container
        BorderPane repertoirePane = new BorderPane();
        //tab pane
        repertoireTabPane = new TabPane();

        //make label for chess lines
        Label chessLinesLbl = new Label("Repertoire Line:");
        VBox chessLinesBx = new VBox(chessLinesLbl, chessLines);


        repertoirePane.setLeft(chessLinesBx);
        if(repertoireController.hasGames()){
            repertoirePane.setCenter(repertoireController.getGamesController().getInstructiveGamesVBox());
        }
        repertoirePane.setBottom(actionButtons);

        repertoireTab = new Tab("Reperitoire", repertoirePane);

        repertoireTabPane.getTabs().addAll(repertoireTab);

        repertoirePane.setCenter(repertoireTabPane);

        //repertoirePane.setRight();

    }   
}
