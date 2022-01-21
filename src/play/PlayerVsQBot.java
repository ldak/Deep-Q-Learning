package play;

import bots.DeepQBot;
import deep_qlearning.DeepQLearning;
import utils.Move;
import utils.QBotSerializer;
import utils.board.Board;
import utils.board.Connect4Board;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PlayerVsQBot extends JFrame{

    public final int width=800;
    public final int height=600;

    public JPanel contentPanel;

    //start program
    public static void main(String[] args) {
        PlayerVsQBot frame=new PlayerVsQBot();
        frame.setVisible(true);
    }

    /*
         Index of buttons:

    */
    private static final File saveFile=new File("connect4Second.deepQLearningV5");

    public JButton[][] buttons=new JButton[7][6];
    private final Board board=new Connect4Board(7,6);

    private boolean won=false;
    private DeepQBot bot;


    public PlayerVsQBot(){
        initBot();
        initFrame();
        initButtons();
        letTheFirstMoveToTheBot();

    }

    private void letTheFirstMoveToTheBot() {
        try{
            board.setFirstPlayer(true);
            int move=bot.play(board);
            board.played(move);
            board.loadButtons(buttons);

        }catch (Exception e){
         e.printStackTrace();
        }

    }

    private void initButtons() {
        int startX=100,startY=100,plusX=width/10,plusY=height/10;
        for (int i = 0; i <buttons.length ; i++) {
            final int I=i;
            for (int j = buttons[i].length-1; j >=0 ; j--) {
                JButton button=new JButton(" ");
                button.setFont(new Font("sheriff",Font.BOLD,30));
                button.setBounds(startX+i*plusX,startY+j*plusY,plusX-10,plusY-10);
                button.addActionListener(a->{
                    gameLogic(I);
                });
                buttons[i][j]=button;
                contentPanel.add(button);
            }
        }
    }

    private void gameLogic(int i){
        if (!won){
            try {
                board.setFirstPlayer(false);
                int j=board.played(i);
                if(board.isWon(new Move(i,j))){
                    board.loadButtons(buttons);
                    setTitle("You Win");
                    won=true;
                    return;
                }

                board.setFirstPlayer(true);
                int move=bot.play(board);
                j=board.played(move);
                if(board.isWon(new Move(move,j))){
                    setTitle("Bot Win");
                    board.loadButtons(buttons);
                    won=true;
                }
                board.loadButtons(buttons);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200,100,width,height);
        contentPanel=new JPanel();
        contentPanel.setLayout(null);
        setContentPane(contentPanel);
    }

    private void initBot() {
        QBotSerializer botSerializer=new QBotSerializer("v-3","",true);
        this.bot=botSerializer.getFirstBot();
        bot.setPercentsRandom(0);
    }


}
