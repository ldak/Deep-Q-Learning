package bots;

import deep_qlearning.DeepQLearning;
import utils.board.Board;

import java.io.Serializable;

public class DeepQBot implements Bot, Serializable {

    private final DeepQLearning deepQLearning;

    public DeepQBot(DeepQLearning deepQLearning){
        this.deepQLearning=deepQLearning;
    }

    public DeepQBot(){
        this(new DeepQLearning());
    }

    @Override
    public int play(Board board) throws Exception {
        deepQLearning.setError(board);
        return deepQLearning.chooseMove(board);
    }

    @Override
    public void win() throws Exception {
        deepQLearning.setReward(1);
    }

    @Override
    public void lost() throws Exception {
        deepQLearning.setReward(0);
    }

    @Override
    public void draw() throws Exception {
        deepQLearning.setReward(0.5);
    }

    public void setPercentsRandom(int a){
        deepQLearning.setPercentsRandom(a);
    }

    public void setLearningRate(float a) {
        deepQLearning.setLearningRate(a);
    }
}
