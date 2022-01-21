//package play.models;
//
//import bots.Bot;
//import bots.DeepQBot;
//import bots.RandomBot;
//import deep_qlearning.DeepQLearning;
//import utils.BotSerializer;
//import utils.board.Board;
//import utils.board.Connect4Board;
//import utils.logger.Logger;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//public class BotVsRandom {
//
//    //private static File saveFile=new File("connect4.deepQLearningV3-10Layers");
//    private static final BotSerializer serializer=new BotSerializer("v-1");
//
//    private static final int GAMESTOPLAY=100_000_00;
//    private static final Logger log=new Logger(new String[]{"games","win","lost","draw"});
//
//    private static final Board board = new Connect4Board(7,6);
//    private static DeepQBot qBot;
//    private static final Bot randomBot=new RandomBot();
//
//    public static void main(String[] args) throws Exception {
//        qBot=serializer.getFirstBot();
//        qBot.setPercentsRandom(90);
//        qBot.log();
//        initializeBoard();
//
//        while (log.getFieldValue("games")<=GAMESTOPLAY){
//
//
//            played(randomPlay());
//
//            qBot.setError(board);
//          //  printBoard();
//            board.played(qBot.chooseMove(board));
//            // printBoard();
//        }
//        log();
//        qBot.log();
//        serializer.save(qBot);
//    }
//
//
//    public static void played(int i) throws Exception {
//        for (int j = board[i].length-1; j >=0 ; j--) {
//            if (board[i][j] == 0.0){
//                if (randomTurn){
//                    board[i][j]=-1;
//                }else{
//                    board[i][j]=1;
//                }
//                checkWin(i,j);
//                return;
//            }
//        }
//        if(randomTurn){
//            throw new Exception("Random played illegal move!");
//        }
//        shitDeads++;
//        qBot.setReward(-1);
//        initializeBoard();
//    }
//
//    private static void checkWin(int i, int j) throws Exception {
//
//        //check horizontal
//        int br=1;
//        try {
//            for (int k = i+1; k < board.length; k++) {
//                if (board[k][j]==board[i][j]){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        try {
//            for (int k = i-1; k >= 0; k--) {
//                if (board[k][j]==board[i][j]){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        if (br>=4){
//            gameWon();
//            return;
//        }
//        //check vertical
//        br=1;
//        try {
//            for (int k = j+1; k < board[i].length; k++) {
//                if (board[i][k]==board[i][j]){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        try {
//            for (int k = j-1; k >= 0; k--) {
//                if (board[i][k]==board[i][j]){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        if (br>=4){
//            gameWon();
//            return;
//        }
//        //check one diagonal
//        br=1;
//        try {
//            for (int k = 1; k < board.length; k++) {
//                if (board[i+k][j+k]==board[i][j]){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        try {
//            for (int k = -1; k >= -board.length; k--) {
//                if (board[i+k][j+k]==board[i][j]){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        if (br>=4){
//            gameWon();
//            return;
//        }
//        //check shit diagonal
//        br=1;
//        try {
//            for (int k = 1; k < board.length; k++) {
//                if (board[i-k][j+k]==board[i][j]){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        try {
//            for (int k = 1; k < board.length; k++) {
//                if (board[i+k][j-k]==board[i][j]){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        if (br>=4){
//            gameWon();
//            return;
//        }
//    }
//
//    private static void gameWon() throws Exception {
//        if (randomTurn){
//           qBot.setReward(0);
//           lost++;
//        }else{
//            qBot.setReward(1);
//            win++;
//        }
//       initializeBoard();
//    }
//
//    private static int randomPlay() {
//        List<Integer> moves=new ArrayList<>();
//        for (int i = 0; i <board.length ; i++) {
//            if (board[i][0]==0){
//                moves.add(i);
//            }
//        }
//        return moves.get(rnd.nextInt(moves.size()));
//    }
//
//    private static void initializeBoard() throws IOException {
//        brGames++;
//        for (double[] doubles : board) {
//            Arrays.fill(doubles, 0);
//        }
//        if (brGames%(100_000)==0){
//            log();
//            save();
//        }
//    }
//
//    public static double[] boardToArray(){
//        double[] data=new double[board.length*board[0].length*2];
//        int index=0;
//        for (double[] doubles : board) {
//            for (double aDouble : doubles) {
//                data[index++] = aDouble==1?1:0;
//            }
//        }
//        for (double[] doubles : board) {
//            for (double aDouble : doubles) {
//                data[index++] = aDouble==-1?1:0;
//            }
//        }
//                  return data;
//    }
//
//    private static void log() throws IOException {
//        System.out.println("______________");
//        System.out.println("Wins: "+win);
//        System.out.println("Losses:"+lost);
//        System.out.println("Win rate:"+(((double)win)/(double) (100_000))*100);
//        System.out.println("Illegal moves: "+shitDeads);
//        System.out.println("Percent lost due illegal move: "+((double)shitDeads)/100_000d*100);
//        if((((double)win)/(double) (100_000))*100>98){
//            save();
//            System.out.println("98% win rate!");
//            System.exit(0);
//        }
//        win=0;
//        lost=0;
//        shitDeads=0;
//    }
//}
