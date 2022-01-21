package deep_qlearning;

import neural_network.NeuralNetwork;
import utils.board.Board;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class DeepQLearning implements Serializable {

    private NeuralNetwork targetNeuralNetwork;
    private NeuralNetwork predictionNeuralNetwork;

    private float percentsRandom =50;
    private final Random rnd=new Random();

    private int brIterations=0;
    private final int transferIterations=1000;
    private long updates=0;

    private int lastMove=0;

    public boolean learning=true;

    private boolean trained=true;

    public DeepQLearning(){
        targetNeuralNetwork =new NeuralNetwork(84,60,7,3);
        predictionNeuralNetwork =new NeuralNetwork(targetNeuralNetwork);
    }

    public DeepQLearning(DeepQLearning deepQLearning){
        this.targetNeuralNetwork=new NeuralNetwork(deepQLearning.targetNeuralNetwork);
        this.predictionNeuralNetwork=new NeuralNetwork(deepQLearning.predictionNeuralNetwork);
        this.updates=deepQLearning.updates;
    }

    public void log(){
        System.out.println("Deep Q-Learning:");
        System.out.println("Updates: "+updates);
        System.out.println("PercentRandom: "+ percentsRandom);
    }
    public void transfer(){
        targetNeuralNetwork=new NeuralNetwork(predictionNeuralNetwork);
    }

    public void reducePercentsRandom(float a){
        if(percentsRandom -a>=1) {
            percentsRandom -= a;
        }
    }

    public void setPercentsRandom(float percentsRandom) {
        this.percentsRandom = percentsRandom;
    }

    public int chooseMove(Board board) throws Exception {
        trained=false;
        List<Double> output=predictionNeuralNetwork.forwardProp(board.toArray());
        List<Integer> moves=board.getPossibleMoves();
        int random=rnd.nextInt(100);
        if (random< percentsRandom){
            lastMove=moves.get(rnd.nextInt(moves.size()));
        }else {

            double max=output.get(moves.get(0));
            int i=moves.get(0);
            if(max!=max){
                throw new Exception("Neural network return NAN");
            }
            for (Integer move: moves) {
                if (max<output.get(move)){
                    i=move;
                    max=output.get(move);
                }
            }
            lastMove=i;
        }
        return lastMove;
    }

    public void setError(Board newBoard) throws Exception {
        if (!learning)return;
        if (trained) return;
        List<Double> output=targetNeuralNetwork.forwardProp(newBoard.toArray());
        //System.out.println(output);
        double max=output.get(0);
        for (int j = 1; j < output.size(); j++) {
            if (max<output.get(j)){
                max=output.get(j);
            }
        }
        predictionNeuralNetwork.backProp(lastMove,max);
        brIterations++;
        checkForTransfer();
    }

    private void checkForTransfer() {
        if (brIterations>=transferIterations){
            transfer();
            updates++;
            reducePercentsRandom(0.0001f);
            brIterations=0;
        }
    }

    public void setReward(double reward) throws Exception {
        if (!learning)return;
        trained=true;
        predictionNeuralNetwork.backProp(lastMove,reward);
        brIterations++;
        checkForTransfer();
    }

    public void reverseLearning(){
        learning=!learning;
    }

    public void setLearning(boolean learning) {
        this.learning = learning;
    }

    public void setLearningRate(float a) {
        predictionNeuralNetwork.setLearningRate(a);
    }

    public float getPercentsRandom() {
        return percentsRandom;
    }
}
