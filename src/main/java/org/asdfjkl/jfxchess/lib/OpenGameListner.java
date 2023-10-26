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


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Open the games associated with a given repertoire line
 */
public class OpenGameListner implements ActionListener {

    private RepertoireController controller;

    /**
     * construct Listner with a ref. to the main repertior Controller
     * @param controller
     */
    public OpenGameListner(RepertoireController controller){
        this.controller = controller;
    }

    public void handleGameChange(ActionEvent event){
        System.out.println("GameChange");

        controller.setMainGame((InstructiveGame) event.getSource());

    }

    /**
     * Discover what event was preformed, and take appropriate action
     * @param event the event that triggered this
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() instanceof InstructiveGame){
            controller.setMainGame((InstructiveGame) event.getSource());
        } 
    }
}
