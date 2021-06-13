package connect4;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private double[][] data=new double[7][6];

    public void reset(){
        for (double[] doubles : data) {
            Arrays.fill(doubles, 0);
        }
    }

    public double[] toArray(){
        double[] board=new double[data.length*data[0].length*2];
        int index=0;
        for (double[] doubles : data) {
            for (double aDouble : doubles) {
                board[index++] = aDouble==1?1:0;
            }
        }
        for (double[] doubles : data) {
            for (double aDouble : doubles) {
                board[index++] = aDouble==-1?1:0;
            }
        }
        return board;
    }
    public void printBoard() {
        System.out.println("----------------");
        for (double[] arr:data) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public int played(int i,int a) throws Exception {
        for (int j = data[i].length-1; j >=0 ; j--) {
            if (data[i][j] == 0.0){
                data[i][j]=a;
                return j;
            }
        }
        throw new Exception("WTF");
    }

    public boolean isWin(int i, int j) throws Exception {
        if(data[i][j]==0){
            throw new Exception("Who made this illegal move?");
        }
        //check horizontal
        int br=1;
        try {
            for (int k = 1; k < data.length; k++) {
                if (data[i+k][j]==data[i][j]){
                    br++;

                }else{
                    break;
                }
            }
        }catch (IndexOutOfBoundsException ignored){}
        try {
            for (int k = 1; k <=data[0].length; k++) {
                if (data[i-k][j]==data[i][j]){
                    br++;

                }else{
                    break;
                }
            }
        }catch (IndexOutOfBoundsException ignored){}
        if (br>=4){
            //System.out.println("horizontal");
            return true;
        }
        //check vertical
        br=1;
        try {
            for (int k = j+1; k < data[i].length; k++) {
                if (data[i][k]==data[i][j]){
                    br++;
                }else{
                    break;
                }
            }
        }catch (IndexOutOfBoundsException ignored){}
        try {
            for (int k = j-1; k >= 0; k--) {
                if (data[i][k]==data[i][j]){
                    br++;
                }else{
                    break;
                }
            }
        }catch (IndexOutOfBoundsException ignored){}
        if (br>=4){
            //System.out.println("vertical");
            return true;
        }
        //check one diagonal
        br=1;
        try {
            for (int k = 1; k < data.length; k++) {
                if (data[i+k][j+k]==data[i][j]){
                    br++;
                }else{
                    break;
                }
            }
        }catch (IndexOutOfBoundsException ignored){}
        try {
            for (int k = 1; k < data.length; k++) {
                if (data[i-k][j-k]==data[i][j]){
                    br++;
                }else{
                    break;
                }
            }
        }catch (IndexOutOfBoundsException ignored){}
        if (br>=4){
            //System.out.println("diagonal");
            return true;
        }
        //check shit diagonal
        br=1;
        try {
            for (int k = 1; k < data.length; k++) {
                if (data[i-k][j+k]==data[i][j]){
                    br++;
                }else{
                    break;
                }
            }
        }catch (IndexOutOfBoundsException ignored){}
        try {
            for (int k = 1; k < data.length; k++) {
                if (data[i+k][j-k]==data[i][j]){
                    br++;
                }else{
                    break;
                }
            }
        }catch (IndexOutOfBoundsException ignored){}
        if (br>=4){
            //System.out.println("shit diagonal");
            return true;
        }
        return false;
    }

    public boolean isEmpty(){
        for (double[] doubles : data) {
            for (double d : doubles) {
                if (d!=0){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isFull(){
        for (double[] doubles : data) {
            for (double d : doubles) {
                if (d==0){
                    return false;
                }
            }
        }
        return true;
    }

    public List<Integer> getPossibleMoves() throws Exception {
        List<Integer> moves=new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            if (data[i][0]==0){
                moves.add(i);
            }
        }
        if (moves.size()==0){
            printBoard();
            throw new Exception("No possible moves! How?? omg");
        }
        return moves;
    }

    public void loadButtons(JButton[][] buttons){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j <data[0].length ; j++) {
                if (data[i][j]==1){
                    buttons[i][j].setForeground(Color.RED);
                    buttons[i][j].setText("O");
                }else  if (data[i][j]==-1){
                    buttons[i][j].setForeground(Color.BLUE);
                    buttons[i][j].setText("O");
                }else {
                    buttons[i][j].setText("");
                }
            }
        }
    }

}
