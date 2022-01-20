package bots;

import utils.board.Board;

public class PatternBot implements Bot{

    @Override
    public int play(Board board) throws Exception {
        return board.getPossibleMoves().get(0);
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
