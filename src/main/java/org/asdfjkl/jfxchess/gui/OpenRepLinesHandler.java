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

import org.asdfjkl.jfxchess.lib.RepertoireController;
import org.asdfjkl.jfxchess.lib.Game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Handle opening repertoire lines in gui 
 */
public class OpenRepLinesHandler implements EventHandler<ActionEvent>{

    GameModel gameModel;
    
    @Override
    public void handle(ActionEvent event){
        RepertoireController repertoireController = (RepertoireController) event.getSource();

    }

    public void handleSaveCurrentRepertoire() {

        DialogSave dlg = new DialogSave();
        int result = dlg.show(gameModel.THEME,
                gameModel.currentPgnDatabaseIdx >= 0,
                gameModel.getPgnDatabase().filename);
        System.out.println("result of dlg: "+result);
        switch(result) {
            case DialogSave.DLG_SAVE_NEW:
                gameModel.getPgnDatabase().saveAsNewPGN(gameModel);
                break;
            case DialogSave.DLG_SAVE_APPEND_CURRENT:
                gameModel.getPgnDatabase().appendToCurrentPGN(gameModel.game);
                break;
            case DialogSave.DLG_SAVE_APPEND_OTHER:
                gameModel.getPgnDatabase().appendToOtherPGN(gameModel);
                break;
            case DialogSave.DLG_SAVE_REPLACE:
                gameModel.getPgnDatabase().replaceCurrentGame(gameModel.game, gameModel.currentPgnDatabaseIdx);
                break;
            default:
        }
    }


    public void handleImportGame() {

        DialogSave dlg = new DialogSave();
        int result = dlg.show(gameModel.THEME,
                gameModel.currentPgnDatabaseIdx >= 0,
                gameModel.getPgnDatabase().filename);
        System.out.println("result of dlg: "+result);
        switch(result) {
            case DialogSave.DLG_SAVE_NEW:
                gameModel.getPgnDatabase().saveAsNewPGN(gameModel);
                break;
            case DialogSave.DLG_SAVE_APPEND_CURRENT:
                gameModel.getPgnDatabase().appendToCurrentPGN(gameModel.game);
                break;
            case DialogSave.DLG_SAVE_APPEND_OTHER:
                gameModel.getPgnDatabase().appendToOtherPGN(gameModel);
                break;
            case DialogSave.DLG_SAVE_REPLACE:
                gameModel.getPgnDatabase().replaceCurrentGame(gameModel.game, gameModel.currentPgnDatabaseIdx);
                break;
            default:
        }
    }

}

