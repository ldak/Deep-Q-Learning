package utils.board;

import utils.Move;

import java.util.List;

public interface Board {

    double[] toArray();
    List<Integer> getPossibleMoves() throws Exception;

    int played(int chooseMove) throws Exception;

    void setFirstPlayer(boolean b);

    boolean isWon(Move move) throws Exception;

    void reset();

    boolean isTie();
}
