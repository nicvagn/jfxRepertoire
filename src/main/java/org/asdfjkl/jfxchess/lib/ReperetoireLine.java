package org.asdfjkl.jfxchess.lib;

import javafx.scene.control.MenuItem;

/**
 * A line in the repertoire, with example games
 */
public class ReperetoireLine extends MenuItem {

    private InstructiveGame[] inst_Games;

    public ReperetoireLine(String name, InstructiveGame[] instructiveGames){
        super(name);
        this.inst_Games = instructiveGames;
        this.setText(name);
    }

    /**
     * get an array of this Rep. lines Instructive games
     * @return array of InstructiveGame
     */
    public InstructiveGame[] getIntructGames(){
        return this.inst_Games;
    }

    public String getlineName(){
        return this.getText();
    }

    /**
     * add an instructive game
     * @param ig instructive game
     */
    public void addInstructiveGame(InstructiveGame ig){
        inst_Games = addX(inst_Games, ig);
    }


    private InstructiveGame[] addX(InstructiveGame arr[], InstructiveGame ig) { 
       int n = arr.length; 
   
       // create a new array of size n+1 
       InstructiveGame newarr[] = new InstructiveGame[n + 1]; 
   
       // insert the elements from 
       // the old array into the new array 
       // insert all elements till n 
       // then insert x at n+1 
       for (int i = 0; i < n; i++) 
           newarr[i] = arr[i]; 
   
       newarr[n] = ig; 
   
       return newarr; 
    }

    public String toString(){
        return this.getText(); 
    }
}
