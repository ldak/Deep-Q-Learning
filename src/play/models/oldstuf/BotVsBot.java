//package play.models;
//
//import deep_qlearning.DeepQLearning;
//import utils.board.Board;
//import utils.board.Connect4Board;
//
//import java.io.*;
//
//public class BotVsBot{
//
//    //private static File saveFile=new File("connect4.deepQLearningV3-10Layers");
//    private static final File saveFirst=new File("connect4First.deepQLearningV5");
//    private static final File saveSecond=new File("connect4Second.deepQLearningV5");
//
//    private static DeepQLearning qBot1;
//    private static DeepQLearning qBot2;
//
//    private static final int GAMESTOPLAY=1_000_000_000;
//    private static final int logGames =100_000; //log and save after every "logGames" games
//    private static final int switchGames =100_000; //switch the bot which is learning after "switchGames" games
//
//    private static int brGames=0;
//
//    private static final Board board=new Connect4Board();
//
//    private static int win=0;
//    private static int lost=0;
//    private static int tie=0;
//    private static long prevTime=System.currentTimeMillis();
//
//    private static boolean secondTurn =false;
//
//    public static void main(String[] args) throws Exception {
//        qBot1=loadBot(saveFirst);
//        qBot1.setLearning(true);
//        qBot1.setPercentsRandom(10);
//        qBot1.log();
//
//        qBot2=loadBot(saveSecond);
//        qBot2.setLearning(false);
//        qBot2.setPercentsRandom(10);
//        qBot2.log();
//        initializeBoard();
//
//        while (brGames<=GAMESTOPLAY){
//            int i;
//            int j;
//
//            secondTurn =false;
//            if (!board.isEmpty())
//                qBot1.setError(board);
//            i=qBot1.chooseMove(board);
//            j=board.played(i,1);
//            if(board.isWin(i,j)){
//                gameWon();
//                continue;
//            }
//            if (board.isFull()){
//                tie();
//                continue;
//            }
//
//            secondTurn =true;
//            if (!board.isEmpty())
//                qBot2.setError(board);
//            i=qBot2.chooseMove(board);
//            j=board.played(i,-1);
//            if(board.isWin(i,j)){
//                gameWon();
//            }
//            if (board.isFull()){
//                tie();
//            }
//        }
//        log();
//        qBot1.log();
//        save(qBot1,saveFirst);
//        save(qBot2,saveSecond);
//    }
//
//    private static void tie() throws Exception {
//        qBot1.setReward(0.5);
//        qBot2.setReward(0.5);
//        tie++;
//        initializeBoard();
//    }
//
//    private static DeepQLearning loadBot(File file){
//        DeepQLearning qBot;
//        try{
//            ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
//            qBot = (DeepQLearning) in.readObject();
//            in.close();
//        }catch (IOException | ClassNotFoundException e) {
//            qBot =new DeepQLearning();
//        }
//        return qBot;
//    }
//
//
//
//    public static void save(DeepQLearning qBot,File saveFile) throws IOException {
//        if (!saveFile.exists()){
//            saveFile.createNewFile();
//        }
//        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(saveFile));
//        out.writeObject(qBot);
//        out.close();
//    }
//
//    private static void gameWon() throws Exception {
//        if (secondTurn){
//            qBot1.setReward(0);
//            qBot2.setReward(1);
//            lost++;
//        }else{
//            qBot1.setReward(1);
//            qBot2.setReward(0);
//            win++;
//        }
//        initializeBoard();
//    }
//
//    private static void initializeBoard() throws IOException {
//        brGames++;
//        board.reset();
//        if (brGames%(logGames)==0){
//            log();
//            save(qBot1,saveFirst);
//            save(qBot2,saveSecond);
//
//        }
//        if (brGames%(switchGames)==0){
//            qBot1.reverseLearning();
//            qBot2.reverseLearning();
//        }
//
//    }
//
//
//
//    private static void log() throws IOException {
//        qBot1.log();
//        System.out.println("______________");
//        System.out.println("Wins: "+win);
//        System.out.println("Losses:"+lost);
//        System.out.println("Tie: "+tie);
//        System.out.println("Win rate:"+(((double)win)/(double) (logGames))*100);
//        System.out.println("Tie rate:"+(((double)tie)/(double) (logGames))*100);
//        System.out.println("Time: "+(System.currentTimeMillis()-prevTime));
//        prevTime=System.currentTimeMillis();
//        win=0;
//        lost=0;
//        tie=0;
//
//    }
//}
