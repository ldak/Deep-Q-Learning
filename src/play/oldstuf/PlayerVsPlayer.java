//package play.models;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class PlayerVsPlayer extends JFrame {
//
//    public int width=800;
//    public int height=600;
//
//    public JPanel contentPanel;
//
//    //start program
//    public static void main(String[] args) {
//
//        PlayerVsPlayer frame=new PlayerVsPlayer();
//        frame.setVisible(true);
//    }
//
//    /*
//         Index of buttons:
//
//    */
//    public JButton[][] buttons=new JButton[7][6];
//    private boolean redTurn=false;
//    private boolean won=false;
//
//    public PlayerVsPlayer(){
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
//                    if (!won)
//                        played(I);
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
//
//    public void played(int i){
//        for (int j = buttons[i].length-1; j >=0 ; j--) {
//            if (buttons[i][j].getText().equals(" ")){
//                if (redTurn){
//                  buttons[i][j].setForeground(Color.RED);
//                }else{
//                    buttons[i][j].setForeground(Color.BLUE);
//                }
//                buttons[i][j].setText("O");
//                checkWin(i,j);
//                redTurn=!redTurn;
//                break;
//            }
//        }
//    }
//
//    private void checkWin(int i, int j) {
//        //check horizontal
//        int br=1;
//        try {
//            for (int k = i+1; k < buttons.length; k++) {
//                if (buttons[k][j].getForeground().equals(buttons[i][j].getForeground())){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        try {
//            for (int k = i-1; k >= 0; k--) {
//                if (buttons[k][j].getForeground().equals(buttons[i][j].getForeground())){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        if (br>=4){
//            gameWon();
//        }
//        //check vertical
//         br=1;
//        try {
//            for (int k = j+1; k < buttons[i].length; k++) {
//                if (buttons[i][k].getForeground().equals(buttons[i][j].getForeground())){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        try {
//            for (int k = j-1; k >= 0; k--) {
//                if (buttons[i][k].getForeground().equals(buttons[i][j].getForeground())){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        if (br>=4){
//            gameWon();
//        }
//        //check one diagonal
//        br=1;
//        try {
//            for (int k = 1; k < buttons.length; k++) {
//                if (buttons[i+k][j+k].getForeground().equals(buttons[i][j].getForeground())){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        try {
//            for (int k = -1; k >= -buttons.length; k--) {
//                if (buttons[i-k][j-k].getForeground().equals(buttons[i][j].getForeground())){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        if (br>=4){
//            gameWon();
//        }
//        //check shit diagonal
//        br=1;
//        try {
//            for (int k = 1; k < buttons.length; k++) {
//                if (buttons[i-k][j+k].getForeground().equals(buttons[i][j].getForeground())){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        try {
//            for (int k = 1; k < buttons.length; k++) {
//                if (buttons[i+k][j-k].getForeground().equals(buttons[i][j].getForeground())){
//                    br++;
//                }else{
//                    break;
//                }
//            }
//        }catch (IndexOutOfBoundsException ignored){}
//        if (br>=4){
//            gameWon();
//        }
//    }
//
//    private void gameWon() {
//        if (redTurn){
//            setTitle("Red Won");
//        }else{
//            setTitle("Blue won");
//        }
//        won=true;
//    }
//}
