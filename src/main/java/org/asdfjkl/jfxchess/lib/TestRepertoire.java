package org.asdfjkl.jfxchess.lib;

import java.util.ArrayList;

import org.asdfjkl.jfxchess.gui.*;

//test rep additions
public class TestRepertoire {

    Repertoire rep;
    RepertoireController repContr;
    RepertoireGamesController gameControler;
    ArrayList<RepertoireLine> lines = new ArrayList<>();  

    public void testMaking_a_rep(RepertoireController contr){
        
        // test making a rep
        repContr = contr;
        gameControler=repContr.getGamesController();


        InstructiveGame[] ida1 = {new InstructiveGame("Game 1","rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"), 
        new InstructiveGame("Game 2", "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2 null"),
        new InstructiveGame("Game 3", "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2")};
        
        RepertoireLine ln1 = new RepertoireLine("Line 1", ida1, repContr);

        RepertoireLine ln2 = new RepertoireLine("Line 2", ida1, repContr);

        RepertoireLine ln3 = new RepertoireLine("Line 3", ida1, repContr);

        lines.add(ln1);
        lines.add(ln2);
        lines.add(ln3);

        rep = repContr.newRepertoire("firstRep", lines);


        repContr.openRepertoire(rep);

    } 
    
    public void testMakingManyReps(RepertoireController controller){

        //make some games for the test
        InstructiveGame[] instructiveGames = {new InstructiveGame("Game 1","rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"), 
        new InstructiveGame("Game 2", "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2 null"),
        new InstructiveGame("Game 3", "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2")};

        //makesome lines for the test
        RepertoireLine line1 = new RepertoireLine("Line 1", instructiveGames, controller);
        RepertoireLine line2 = new RepertoireLine("Line 2", instructiveGames, controller);
        RepertoireLine line3 = new RepertoireLine("Line 3", instructiveGames, controller);

        //add lines to array list
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);

        //make some repertoires
        Repertoire rep1 = controller.newRepertoire("Reper 1", lines);

        gameControler = rep1.getGamesController();



        Repertoire rep2 = controller.newRepertoire("reper 2", lines);

        controller.openRepertoire(rep2);



        
    }
}
