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
import javafx.event.ActionEvent;
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

    private String repertoireName;
    private RepertoireController controller;


    //create an observable list for lines
    private ObservableList<RepertoireLine> repertoireLineList;

    /**
     * construct an empty repertoire
     * @param repertoireName
     * @param gameModel
     */
    public Repertoire(String repertoireName){
        this.repertoireName = repertoireName;
        repertoireLineList = FXCollections.observableArrayList();
    }

    /**
     * construct a repertoire with lines defined
     * @param repertoireName
     * @param gameModel
     * @param lines ArrayList<RepertoireLine> of pre defined lines
     */
    public Repertoire(String repertoireName, ArrayList<RepertoireLine> lines){
        this.repertoireName = repertoireName;


        //make a ObservableList of lines
        repertoireLineList = FXCollections.observableArrayList(lines);

    }



    /**
     * get the asociated RepertoireGameControler
     * @return the RepertioreGameControler
     */
    public RepertoireGamesController getGamesController(){
        return controller.gamesController;

    }


    public GameModel getGameModel(){
        return controller.gameModel;
    }

    public ObservableList<RepertoireLine> getLines(){
        return this.repertoireLineList;
    }
    

    /**
     * get the name of this Repertoire
     * @return the name
     */
    public String getName(){
        return this.repertoireName;
    }

    /**
     * add a line to the repertoire
     * @param line
     */
    public void addLine(RepertoireLine line){
        repertoireLineList.add(line);
    }
}
