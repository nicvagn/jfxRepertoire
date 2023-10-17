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




        // create menu items
        MenuItem m1 = new MenuItem("Anderssen's Opening, Polish Gambit: 1...a5 2.b4");
        MenuItem m2 = new MenuItem("Anderssen's Opening, Bugayev Attack: 1...e5 2.b4");
        MenuItem m3 = new MenuItem("Anderssen's Opening, Creepy Crawly Formation: 1...e5 2.h3 d5");
        MenuItem m4 = new MenuItem("Anderssen's Opening, Andersspike: 1...g6 2.g4");
        MenuItem m5 = new MenuItem("Anderssen's Opening, Polish Gambit: 1...a5 2.b4");
        MenuItem m6 = new MenuItem("Anderssen's Opening, Bugayev Attack: 1...e5 2.b4");
        MenuItem m7 = new MenuItem("Anderssen's Opening, Creepy Crawly Formation: 1...e5 2.h3 d5");

    // create a menu button of Chess lines
    MenuButton chessLines = new MenuButton("Anderssen's Opening",null, m1,m2, m3, m4, m5, m6, m7);

    //create Array and pane for instructive games
    ArrayList<Text> instructiveGamesText = new ArrayList<>(15);
    TitledPane instructiveGamesTP = new TitledPane();


    //make a new board so the user can enter a position
    EnterPosBoard enterPosBoard;

    GameModel gameModel = new GameModel();

    MoveView moveView = new MoveView(gameModel); //for displaying the played moves

    Stage stage; //main stage


    public boolean show(Board board, BoardStyle style, double width, int colorTheme){

        setUpMoveView();

        stage = new Stage(); //create new stage (window)
        stage.initModality(Modality.APPLICATION_MODAL);

        enterPosBoard = new EnterPosBoard(board);

        //lines
        VBox lines = new VBox(chessLines);
        VBox.setVgrow(lines, Priority.ALWAYS);

        //board
        HBox mBoard = new HBox();
        mBoard.getChildren().addAll(enterPosBoard);
        HBox.setHgrow(enterPosBoard, Priority.ALWAYS);

        //instructive games
        instructiveGamesTP.setText("Instructive Games");
        instructiveGamesTP.setMinHeight(400);
        instructiveGamesTP.setPrefWidth(200);        //Instructive games

        TextFlow linesTextFlow = new TextFlow();
        ListIterator<Text> linesIter = instructiveGamesText.listIterator();
    
        while (linesIter.hasNext()) {
            linesTextFlow.getChildren().add(linesIter.next());
        }





        //tab pane for organization
        TabPane tabBottomPane = new TabPane();
        tabBottomPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        //tab for the moves
        Tab tabMoves = new Tab("Moves", moveView.getWebView());
        //tab for the notes
        TextArea notesTextArea = new TextArea();
        Tab tabNotes = new Tab("Notes", notesTextArea);
        tabBottomPane.getTabs().addAll(tabMoves, tabNotes);

        //container
        BorderPane compositePane = new BorderPane();

        compositePane.setLeft(lines);
        compositePane.setCenter(mBoard);
        compositePane.setRight(instructiveGamesTP);
        compositePane.setBottom(tabBottomPane);

        Scene scene = new Scene(compositePane, 1200, 800, Color.BISQUE);

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
        return true;//hack b/c I don't understand accepted
    }

    private void setUpMoveView(){
        gameModel.addListener(moveView);

        Text x = new Text("xxxxxxxxxxxxxxx");
        instructiveGamesText.add(x);
    }

}
