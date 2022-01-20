package bots;

import utils.board.Board;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBot implements Bot{

    private final Random random;

    public RandomBot(){
        this.random= ThreadLocalRandom.current();
    }

    @Override
    public int play(Board board) throws Exception {
        List<Integer> moves=board.getPossibleMoves();
        return moves.get(random.nextInt(moves.size()));
    }

    @Override
    public void win() throws Exception {

    }

    @Override
    public void lost() throws Exception {

    }

    @Override
    public void draw() {

    }
}
