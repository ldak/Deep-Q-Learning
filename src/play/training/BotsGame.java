package play.training;

import bots.Bot;
import utils.Move;
import utils.board.Board;
import utils.board.Connect4Board;
import utils.logger.Logger;

public class BotsGame {
    private static final Logger log=new Logger(new String[]{"games","win","lost","draw"});

    private final Board board;

    private Bot firstBot;
    private Bot secondBot;

    public BotsGame( Bot firstBot, Bot secondBot, Board board) {
        this.firstBot = firstBot;
        this.secondBot = secondBot;
        this.board = board;
    }

    public void printGame() throws Exception{
        int a=log.getFieldValue("games");
        while (a==log.getFieldValue("games")){
            board.setFirstPlayer(true);
            Move move=play(firstBot);
            if (board.isWon(move)){
                firstBotWon();
                board.reset();
                continue;
            }
            if (board.isTie()){
                tie();
                board.reset();
                continue;
            }
            System.out.println("FirstBot");
            System.out.println(board);

            board.setFirstPlayer(false);
            move=play(secondBot);
            if (board.isWon(move)){
                secondBotWon();
                board.reset();
            }
            if (board.isTie()){
                tie();
                board.reset();
            }
            System.out.println("SecondBot");
            System.out.println(board);
        }
    }

    public void play(int gamesToPlay) throws Exception {
        log.reset();
        long prevTime=System.currentTimeMillis();

        while (log.getFieldValue("games")<gamesToPlay){
            board.setFirstPlayer(true);
            Move move=play(firstBot);
            if (board.isWon(move)){
                firstBotWon();
                board.reset();
                continue;
            }
            if (board.isTie()){
               tie();
               board.reset();
               continue;
            }
//            System.out.println("QLearning");
//            System.out.println(board);

            board.setFirstPlayer(false);
            move=play(secondBot);
            if (board.isWon(move)){
                secondBotWon();
                board.reset();
            }
            if (board.isTie()){
                tie();
                board.reset();
            }
//            System.out.println("Pattern");
//            System.out.println(board);

        }
        log.log();
        System.out.println("Winning rate: "+((double)log.getFieldValue("win")/log.getFieldValue("games"))*100+"%");
        System.out.println("Time needed for the games: "+(System.currentTimeMillis()-prevTime));
    }

    private void tie() throws Exception {
        firstBot.draw();
        secondBot.draw();
        log.increaseField("draw");
        log.increaseField("games");
    }

    private void secondBotWon() throws Exception {
        secondBot.win();
        firstBot.lost();
        log.increaseField("lost");
        log.increaseField("games");
    }

    private void firstBotWon() throws Exception {
        firstBot.win();
        secondBot.lost();
        log.increaseField("win");
        log.increaseField("games");
    }


    private Move play(Bot player) throws Exception {
        int i=player.play(board);
        int j=board.played(i);
        return new Move(i,j);
    }




}
