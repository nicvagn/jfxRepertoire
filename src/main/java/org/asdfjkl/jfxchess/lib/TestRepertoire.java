package org.asdfjkl.jfxchess.lib;

import java.util.ArrayList;

import org.asdfjkl.jfxchess.gui.*;

//test rep additions
public class TestRepertoire {

    Repertoire rep;
    RepertoireController repContr;
    LineGameController gameControler;
    ArrayList<RepertoireLine> lines = new ArrayList<>();  


    public void testMakingManyReps(RepertoireController controller){

        //make some games for the test
        ArrayList<InstructiveGame> instructiveGames1 = new ArrayList<>();
        ArrayList<InstructiveGame> instructiveGames2 = new ArrayList<>();
        ArrayList<InstructiveGame> instructiveGames3 = new ArrayList<>();


        //line 1 inst games
        instructiveGames1.add(new InstructiveGame("Game 1","rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"));
        instructiveGames1.add(new InstructiveGame("Game 2", "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2 null"));
        instructiveGames1.add(new InstructiveGame("Game 3", "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"));

        //line 2 inst. games
        instructiveGames2.add(new InstructiveGame("G1","rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"));
        instructiveGames2.add(new InstructiveGame("G2", "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2 null"));
        instructiveGames2.add(new InstructiveGame("G3", "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"));
 
        //line 3 inst games
        instructiveGames3.add(new InstructiveGame("G5 1","rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"));
        instructiveGames3.add(new InstructiveGame("G66ame 2", "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2 null"));
        instructiveGames3.add(new InstructiveGame("G77 3", "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"));

        //make some lines for the test
        RepertoireLine line1 = new RepertoireLine("Line 1", controller, instructiveGames1);
        RepertoireLine line2 = new RepertoireLine("Line 2", controller, instructiveGames2);
        RepertoireLine line3 = new RepertoireLine("Line 3", controller, instructiveGames3);

        //add lines to array list
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);

        //make rep1
        Repertoire rep1 = controller.newRepertoire("Rep. 1: Qeens Uplift", lines);


        //rep2 stuff

        //make some games for the test
        ArrayList<InstructiveGame> ig1 = new ArrayList<>();
        ArrayList<InstructiveGame> ig2 = new ArrayList<>();
        ArrayList<InstructiveGame> ig3 = new ArrayList<>();


        //line 3 inst games
        ig3.add(new InstructiveGame("Game 1","rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"));
        ig3.add(new InstructiveGame("Game 2", "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2 null"));
        ig3.add(new InstructiveGame("Game 3", "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"));
        

        //line 1
        ig1.add(new InstructiveGame("G1","rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"));
        ig1.add(new InstructiveGame("G2", "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2 null"));
        ig1.add(new InstructiveGame("G3", "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"));
        
        ig2.add(new InstructiveGame("G5 1","rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"));
        ig2.add(new InstructiveGame("G66ame 2", "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2 null"));
        ig2.add(new InstructiveGame("G77 3", "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2"));

        //make some lines for the test
        RepertoireLine l1 = new RepertoireLine("2 rep: Line 1", controller, ig3);
        RepertoireLine l2 = new RepertoireLine("2: rep Line 2", controller, ig1);
        RepertoireLine l3 = new RepertoireLine("2 r:b Line 3", controller, ig2);

        lines = new ArrayList<>();  

        //add lines to array list
        lines.add(l1);
        lines.add(l2);
        lines.add(l3);

        Repertoire rep2 = controller.newRepertoire("Rep. 2: Qeens goes down", lines);

        controller.openRepertoire(rep1);


        
    }
}
