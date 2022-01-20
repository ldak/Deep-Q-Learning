package bots;

import utils.board.Board;

public interface Bot {

    public int play(Board board) throws Exception;

    void win() throws Exception;

    void lost() throws Exception;

    void draw() throws Exception;
}
