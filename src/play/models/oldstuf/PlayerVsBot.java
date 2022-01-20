//package play.models;
//
//import deep_qlearning.DeepQLearning;
//
//import javax.swing.*;
//import java.awt.*;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//
//public class PlayerVsBot extends JFrame{
//
//    public int width=800;
//    public int height=600;
//
//    public JPanel contentPanel;
//
//    //start program
//    public static void main(String[] args) {
//
//        PlayerVsBot frame=new PlayerVsBot();
//        frame.setVisible(true);
//    }
//
//    /*
//         Index of buttons:
//
//    */
//    private static final File saveFile=new File("connect4Second.deepQLearningV5");
//
//    public JButton[][] buttons=new JButton[7][6];
//    private final Board board=new Board();
//
//    private boolean won=false;
//    private DeepQLearning bot;
//
//
//    public PlayerVsBot(){
//        try{
//            ObjectInputStream in=new ObjectInputStream(new FileInputStream(saveFile));
//            bot = (DeepQLearning) in.readObject();
//            in.close();
//        }catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            bot =new DeepQLearning();
//        }
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(200,100,width,height);
//        contentPanel=new JPanel();
//        int startX=100,startY=100,plusX=width/10,plusY=height/10;
//        for (int i = 0; i <buttons.length ; i++) {
//            final int I=i;
//            for (int j = buttons[i].length-1; j >=0 ; j--) {
//                JButton button=new JButton(" ");
//                button.setFont(new Font("sheriff",Font.BOLD,30));
//                button.setBounds(startX+i*plusX,startY+j*plusY,plusX-10,plusY-10);
//                button.addActionListener(a->{
//                    if (!won){
//                        try {
//                            int J;
//                            J=board.played(I,1);
//                            if(board.isWin(I,J)){
//                                board.loadButtons(buttons);
//                                setTitle("You Win");
//                                won=true;
//                                return;
//                            }
//
//                            int move=bot.chooseMove(board);
//                            J=board.played(move,-1);
//                            if(board.isWin(move,J)){
//                                setTitle("Bot Win");
//                                board.loadButtons(buttons);
//                                won=true;
//                            }
//                            board.loadButtons(buttons);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                buttons[i][j]=button;
//                contentPanel.add(button);
//            }
//        }
//        contentPanel.setLayout(null);
//        setContentPane(contentPanel);
//    }
//
//
//}
