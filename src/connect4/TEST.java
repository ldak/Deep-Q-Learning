package connect4;

import deep_qlearning.DeepQLearning;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TEST {



        //private static File saveFile=new File("connect4.deepQLearningV3-10Layers");
        private static File saveFile=new File("connect4.deepQLearningV16");

        private static double[][] board=new double[7][6];
        private static DeepQLearning qBot;
        private static Random rnd=new Random();

        private static final int GAMESTOPLAY=1000000;
        private static int brGames=0;

        private static int win=0;
        private static int lost=0;
        private static int shitDeads=0;

        private static boolean randomTurn=false;
        public static void main(String[] args) throws Exception {
            try{
                ObjectInputStream in=new ObjectInputStream(new FileInputStream(saveFile));
                qBot = (DeepQLearning) in.readObject();
                in.close();
            }catch (IOException | ClassNotFoundException e) {
                qBot =new DeepQLearning();
            }
            qBot.setPercentsRandom(0);
            qBot.log();
            initializeBoard();

            while (brGames<=GAMESTOPLAY){
                randomTurn=true;

                played(randomPlay());

                if (randomTurn==true) {
                    randomTurn = false;
                   // qBot.setError(boardToArray());
                    //printBoard();
                    //played(qBot.chooseMove(boardToArray()));

                }
                //if (brGames-1==GAMESTOPLAY)
                checkIsBoardGood();
            }
            qBot.log();
            save();
        }

    private static void checkIsBoardGood() throws Exception {
        int brQ=0;
        int brR=0;
        for (double[] doubles:
                board) {
            for (double d:
                    doubles) {
                if (d==1){
                    brQ++;
                }else if (d==-1){
                    brR++;
                }
            }
        }
        if (brQ!=brR){
            throw new Exception("faking game random have: "+brR+" and Qbot have: "+brQ+" moves.");
        }
    }

        private static void printBoard() {
            System.out.println("----------------");
            for (double[] arr:board) {
                System.out.println(Arrays.toString(arr));
            }
        }

        public static void save() throws IOException {
            if (!saveFile.exists()){
                saveFile.createNewFile();
            }
            ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(saveFile));
            out.writeObject(qBot);
            out.close();
        }

        public static void played(int i) throws Exception {
            for (int j = board[i].length-1; j >=0 ; j--) {
                if (board[i][j] == 0.0){
                    if (randomTurn){
                        board[i][j]=-1;
                    }else{
                        board[i][j]=1;
                    }
                    checkWin(i,j);
                    return;
                }
            }
            if(randomTurn){
                throw new Exception("Random played illegal move!");
            }
            shitDeads++;
            qBot.setReward(-1);
            initializeBoard();
        }

        private static void checkWin(int i, int j) throws Exception {
            if(board[i][j]==0.0){
                throw new Exception("WTF");
            }
            //check horizontal
            int br=1;
            try {
                for (int k = i+1; k < board.length; k++) {
                    if (board[k][j]==board[i][j]){
                        br++;
                    }else{
                        break;
                    }
                }
            }catch (IndexOutOfBoundsException ignored){}
            try {
                for (int k = i-1; k >= 0; k--) {
                    if (board[k][j]==board[i][j]){
                        br++;
                    }else{
                        break;
                    }
                }
            }catch (IndexOutOfBoundsException ignored){}
            if (br>=4){
                gameWon();
                return;
            }
            //check vertical
            br=1;
            try {
                for (int k = j+1; k < board[i].length; k++) {
                    if (board[i][k]==board[i][j]){
                        br++;
                    }else{
                        break;
                    }
                }
            }catch (IndexOutOfBoundsException ignored){}
            try {
                for (int k = j-1; k >= 0; k--) {
                    if (board[i][k]==board[i][j]){
                        br++;
                    }else{
                        break;
                    }
                }
            }catch (IndexOutOfBoundsException ignored){}
            if (br>=4){
                gameWon();
                return;
            }
            //check one diagonal
            br=1;
            try {
                for (int k = 1; k < board.length; k++) {
                    if (board[i+k][j+k]==board[i][j]){
                        br++;
                    }else{
                        break;
                    }
                }
            }catch (IndexOutOfBoundsException ignored){}
            try {
                for (int k = -1; k >= -board.length; k--) {
                    if (board[i+k][j+k]==board[i][j]){
                        br++;
                    }else{
                        break;
                    }
                }
            }catch (IndexOutOfBoundsException ignored){}
            if (br>=4){
                gameWon();
                return;
            }
            //check shit diagonal
            br=1;
            try {
                for (int k = 1; k < board.length; k++) {
                    if (board[i-k][j+k]==board[i][j]){
                        br++;
                    }else{
                        break;
                    }
                }
            }catch (IndexOutOfBoundsException ignored){}
            try {
                for (int k = 1; k < board.length; k++) {
                    if (board[i+k][j-k]==board[i][j]){
                        br++;
                    }else{
                        break;
                    }
                }
            }catch (IndexOutOfBoundsException ignored){}
            if (br>=4){
                gameWon();
                return;
            }
        }

        private static void gameWon() throws Exception {
            if (randomTurn){
                qBot.setReward(0);
                lost++;
            }else{
                qBot.setReward(1);
               // printBoard();
                //System.out.println("Wins: "+win);
                win++;
               // if (win==20)System.exit(0);
                //System.out.println("Wins: "+win);
                //throw new Exception();

            }
            initializeBoard();
        }

        private static int randomPlay() {
            List<Integer> moves=new ArrayList<>();
            for (int i = 0; i <board.length ; i++) {
                if (board[i][0]==0){
                    moves.add(i);
                }
            }
            return moves.get(0);
        }

        private static void initializeBoard() throws IOException {
            brGames++;
            randomTurn=false;
            for (double[] doubles : board) {
                Arrays.fill(doubles, 0);
            }
            if (brGames%(100_000)==0){
                log();
                save();
            }
        }

        public static double[] boardToArray(){
            double[] data=new double[board.length*board[0].length*2];
            int index=0;
            for (double[] doubles : board) {
                for (double aDouble : doubles) {
                    data[index++] = aDouble==1?1:0;
                }
            }
            for (double[] doubles : board) {
                for (double aDouble : doubles) {
                    data[index++] = aDouble==-1?1:0;
                }
            }
            return data;
        }

        private static void log() throws IOException {
            System.out.println("______________");
            System.out.println("Wins: "+win);
            System.out.println("Losses:"+lost);
            System.out.println("Win rate:"+(((double)win)/(double) (100_000))*100);
            System.out.println("Illegal moves: "+shitDeads);
            System.out.println("Percent lost due illegal move: "+((double)shitDeads)/100_000d*100);
            if((((double)win)/(double) (100_000))*100>98){
                save();
                System.out.println("98% win rate!");
                System.exit(0);
            }
            win=0;
            lost=0;
            shitDeads=0;
        }


}
