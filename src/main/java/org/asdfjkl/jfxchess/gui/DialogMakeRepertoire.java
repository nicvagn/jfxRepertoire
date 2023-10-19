/* JerryFX - A Chess Graphical User Interface
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

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.util.ArrayList;
import java.util.ListIterator;

import org.asdfjkl.jfxchess.lib.Board;

public class DialogMakeRepertoire {

    //CONSTANTS for ease of adjusting
    final double SCENE_WIDTH = 1200;  //Width and height of main window
    final double SCENE_HEIGHT = 800;

    // create menu items for testing
    MenuItem m1 = new MenuItem("Anderssen's Opening, Polish Gambit: 1...a5 2.b4");
    MenuItem m2 = new MenuItem("Anderssen's Opening, Bugayev Attack: 1...e5 2.b4");
    MenuItem m3 = new MenuItem("Anderssen's Opening, Creepy Crawly Formation: 1...e5 2.h3 d5");
    MenuItem m4 = new MenuItem("Anderssen's Opening, Andersspike: 1...g6 2.g4");
    MenuItem m5 = new MenuItem("Anderssen's Opening, Polish Gambit: 1...a5 2.b4");
    MenuItem m6 = new MenuItem("Anderssen's Opening, Bugayev Attack: 1...e5 2.b4");
    MenuItem m7 = new MenuItem("Anderssen's Opening, Creepy Crawly Formation: 1...e5 2.h3 d5");

    // create a menu button of Chess lines
    MenuButton chessLines = new MenuButton("Anderssen's Opening", null, m1, m2, m3, m4, m5, m6, m7);

    // create Array and pane for instructive games
    ArrayList<Button> instructiveGamesText = new ArrayList<>(15);
    TitledPane instructiveGamesTP;

    //various action buttons
    Button btnFlipBoard = new Button("Flip Board");
    Button btnSaveRep = new Button("Save Repertoire");
    Button btnImportGame = new Button("Import Game");
    VBox actionButtons = new VBox(10); //container for action buttons


    //make a new board so the user can enter a position
    RepertoireBoard repertoireBoard;

    GameModel gameModel = new GameModel();

    MoveView moveView = new MoveView(gameModel); //for displaying the played moves

    Stage stage; //main stage

    public void show(Board board, BoardStyle style, double width, int colorTheme){

        setUp(board);  //made a separate function bc dum dum

        stage = new Stage(); //create new stage (window)
        stage.initModality(Modality.APPLICATION_MODAL);


        //lines
        VBox lines = new VBox(chessLines);
        lines.getChildren().add(actionButtons);
        VBox.setVgrow(lines, Priority.ALWAYS);

        //board
        HBox mBoard = new HBox();
        mBoard.getChildren().addAll(repertoireBoard);
        HBox.setHgrow(repertoireBoard, Priority.ALWAYS);

        //instructive games        
        TextFlow linesTextFlow = new TextFlow();
        ListIterator<Button> linesIter = instructiveGamesText.listIterator();
    
        while (linesIter.hasNext()) {
            linesTextFlow.getChildren().add(linesIter.next());
        }
        //config instructive games titled pane
        instructiveGamesTP = new TitledPane("Instructive Games", linesTextFlow);
        instructiveGamesTP.setExpanded(true);
        instructiveGamesTP.setCollapsible(false);
        instructiveGamesTP.setMinHeight(400);
        instructiveGamesTP.setPrefWidth(310);


        //bottom Pane
        //AnchorPane to organize Tap panes
        AnchorPane tabRootAnchorPane = new AnchorPane(); // to allow for displaying buttons next to tabs
        tabRootAnchorPane.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT / 4);

        //tab pane for organization
        TabPane tabBottomPane = new TabPane();
        tabBottomPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //configure the Anchor pane
        AnchorPane.setTopAnchor(tabBottomPane, 5.0);
        AnchorPane.setLeftAnchor(tabBottomPane, 5.0);
        AnchorPane.setRightAnchor(tabBottomPane, 5.0);

        //tab for the moves
        Tab tabMoves = new Tab("Moves", moveView.getWebView());


        //tab for the notes
        TextArea notesTextArea = new TextArea();
        Tab tabNotes = new Tab("Notes", notesTextArea);
        tabBottomPane.getTabs().addAll(tabMoves, tabNotes);

        //add all wanted items to the Anchor Pane
        tabRootAnchorPane.setPrefSize(500, 500);
        tabRootAnchorPane.getChildren().addAll(tabBottomPane);

        //container
        BorderPane compositePane = new BorderPane();

        compositePane.setLeft(lines);
        compositePane.setCenter(mBoard);
        compositePane.setRight(instructiveGamesTP);
        compositePane.setBottom(tabRootAnchorPane);

        //set up scene
        Scene scene = new Scene(compositePane, SCENE_WIDTH, SCENE_HEIGHT, Color.DARKBLUE);

        //set up consistent colour styling
        JMetro jMetro;
        if(colorTheme == GameModel.STYLE_LIGHT) {
            jMetro = new JMetro();
        } else {
            jMetro = new JMetro(Style.DARK);
        }
        jMetro.setScene(scene);

        stage.setScene(scene);
        stage.getIcons().add(new Image("icons/app_icon.png"));

        stage.showAndWait();

        //set flipBoard button to flip the board
        btnFlipBoard.setOnAction(e -> {
            repertoireBoard.flipBoard();
            repertoireBoard.updateCanvas();
        });
    }

    //get the current board
    public Board getCurrentBoard(){
        return this.repertoireBoard.makeBoardCopy();
    }

    private void setUp(Board board){
        //I don-t really understand this, Was trying to get played moves to show up
        gameModel.addListener(moveView);
        repertoireBoard = new RepertoireBoard(board);

        //setup action buttons
        btnFlipBoard.setMinWidth(140);
        btnFlipBoard.setGraphic(new ImageView( new Image("icons/view-refresh.png")));

        btnSaveRep.setMinWidth(140);
        btnSaveRep.setGraphic(new ImageView(new Image("icons/document-save.png")));

        btnImportGame.setMinWidth(140);
        btnImportGame.setGraphic(new ImageView(new Image("icons/document-enter-position.png")));

        //add the buttons to a HBox for easy grouping
        actionButtons.getChildren().addAll(btnFlipBoard, btnSaveRep, btnImportGame);

        //test data for display config
        Button G1 = new Button("Kasparov vs. Topalov, Wijk aan Zee 1999");
        Button G2 = new Button("Morphy vs. Allies, Paris Opera 1858");
        Button G3 = new Button("Aronian vs. Anand, Wijk aan Zee 2013");
        Button G4 = new Button("Karpov vs. Kasparov, World Championship 1985, game 16");
        Button G5 = new Button("Morphy vs. Allies, Paris Opera 1858");
        Button G6 = new Button("Aronian vs. Anand, Wijk aan Zee 2013");
        Button[] G = {G1,G2,G3,G4,G5,G6};

        for(int i = 0; i < 6; i++){
            instructiveGamesText.add(G[i]);
        }
    }

}
