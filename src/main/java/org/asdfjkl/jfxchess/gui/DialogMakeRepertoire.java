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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.*;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;
import org.asdfjkl.jfxchess.lib.Board;
import org.asdfjkl.jfxchess.lib.CONSTANTS;

public class DialogMakeRepertoire implements EnterPosBoardListener {

    ListView<String> chessLinesView = new ListView<String>();

    ObservableList<String> chessLinesNames = FXCollections.observableArrayList(
            "Anderssen's Opening, Polish Gambit: 1...a5 2.b4",
            "Anderssen's Opening, Bugayev Attack: 1...e5 2.b4",
            "Anderssen's Opening, Creepy Crawly Formation: 1...e5 2.h3 d5",
            "Anderssen's Opening, Andersspike: 1...g6 2.g4",
            "Anderssen's Opening, Spider Sense Gambit: 1...d5 2.d4 Nc6 3.Nf3 Bf5 4.Bf4 e6 5.Nc3 Bd6 6.e3 Nf6 7.g3 h6 8.Bg2 Rc8 9.0-0 0-0",
            "Anderssen's Opening, Sammarinese Gambit: 1...a6");

    Stage stage;

    public boolean show(Board board, BoardStyle style, double width, double height, int colorTheme) {
        return true;

    }

    public void boardChanged() {
        // needed to compile, I don't know what is for
    };

    /* 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(stage, 400, 400);
        stage.setScene(scene);
        stage.setTitle("ListViewSample");

        list.setItems(data);

        list.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                return new ColorRectCell();
            }
        });

        Text text = new Text(10, 40, "Hello World!");
        text.setFont(new Font(40));
        stage.setTitle("Welcome to JavaFX!");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
    */


    public boolean show(int currSecs, double currThreshold, int colorTheme) {

        //System.out.println(currSecs + " " + currThreshold);

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Button btnOk = new Button();
        btnOk.setText("OK");

        Button btnCancel = new Button();
        btnCancel.setText("Cancel");
        Region spacer = new Region();

        HBox hbButtons = new HBox();
        hbButtons.getChildren().addAll(spacer, btnOk, btnCancel);
        hbButtons.setHgrow(spacer, Priority.ALWAYS);
        hbButtons.setSpacing(10);

        ToggleGroup radioGroupColors = new ToggleGroup();
        rbBoth.setToggleGroup(radioGroupColors);
        rbWhite.setToggleGroup(radioGroupColors);
        rbBlack.setToggleGroup(radioGroupColors);

        rbBoth.setSelected(true);

        SpinnerValueFactory<Integer> valueFactorySecs =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 5);
        sSecs.setValueFactory(valueFactorySecs);
        sSecs.setEditable(true);
        sSecs.getValueFactory().setValue(currSecs);

        SpinnerValueFactory<Double> valueFactoryThres =
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0.1, 5.0, 0.5, 0.1);
        sPawnThreshold.setValueFactory(valueFactoryThres);
        sPawnThreshold.setEditable(true);
        sPawnThreshold.getValueFactory().setValue(currThreshold);

        HBox hbColors = new HBox(rbBoth, rbWhite, rbBlack);
        hbColors.setSpacing(10);
        hbColors.setPadding(new Insets(5, 20, 20, 0));

        GridPane grd = new GridPane();
        grd.add(lSecsPerMove, 0, 0);
        grd.add(sSecs, 1, 0);
        grd.add(lPawnThresh, 0, 1);
        grd.add(sPawnThreshold, 1, 1);
        grd.setHgap(10);
        grd.setVgap(10);
        //grd.setPadding(new Insets(5, 20, 20, 0));

        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                grd,
                lAnalysePlayers,
                hbColors,
                hbButtons);
        vbox.setSpacing(10);
        vbox.setPadding( new Insets(15, 25, 10, 25));

        vbox.getStyleClass().add(JMetroStyleClass.BACKGROUND);
        Scene scene = new Scene(vbox);

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

        return accepted;
    }
    boolean accepted = false;

    final Label lSecsPerMove = new Label("Sec(s) per Move");
    final Label lPawnThresh = new Label("Threshold (in pawns)");
    final Label lAnalysePlayers = new Label("Analyse Players:");

    final Spinner<Integer> sSecs = new Spinner<Integer>();
    final Spinner<Double> sPawnThreshold = new Spinner<Double>();

    final RadioButton rbBoth = new RadioButton("Both");
    final RadioButton rbWhite = new RadioButton("White");
    final RadioButton rbBlack = new RadioButton("Black");
}
