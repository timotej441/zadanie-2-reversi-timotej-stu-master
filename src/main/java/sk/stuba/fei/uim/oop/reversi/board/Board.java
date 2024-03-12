package sk.stuba.fei.uim.oop.reversi.board;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Board {

    private char[][] board;
    private int size;
    @Getter
    private int nodeSize;
    private int white, black;

    public Board(int size){
        this.black = 2;
        this.white = 2;
        generateBoard(size);
    }

    public void generateBoard(int size) {
        this.size = size;
        nodeSize = 700/size;
        board = new char[12][12];
        this.black = 2;
        this.white = 2;
//        this.node = new Node[size][size];
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                if((x==size/2 && y==x) || (x==(size/2)-1 && y==x)){
                    board[x][y] = 'w';
                }
                else if((x==size/2 && y==(size/2)-1) || (x==(size/2)-1 && y==size/2)){
                    board[x][y] = 'b';
                }
                else {
                    board[x][y] = 'e';
                }
            }
        }
    }

    public void vykreslit(Graphics g){
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++)  {

                if(x%2==0 && y%2!=0 || x%2!=0 && y%2==0){
                    g.setColor(Color.GREEN);
                }
                else {
                    g.setColor(Color.GREEN.darker());
                }
                g.fillRect(x*nodeSize, y*nodeSize ,nodeSize,nodeSize);

                switch (board[x][y]){
                    case 'w':
                        g.setColor(Color.WHITE);
                        g.fillOval(x*nodeSize+5, y*nodeSize+5, nodeSize-10, nodeSize-10);
                        break;

                    case 'b':
                        g.setColor(Color.BLACK);
                        g.fillOval(x*nodeSize+5, y*nodeSize+5, nodeSize-10, nodeSize-10);
                        break;

                    case 'h':
                        g.setColor(Color.YELLOW);
                        g.fillOval(x*nodeSize+10, y*nodeSize+10, nodeSize-20, nodeSize-20);

                    case 'p':
                        g.setColor(Color.BLACK);
                        g.drawOval(x*nodeSize+10, y*nodeSize+10, nodeSize-20, nodeSize-20);
                        break;

                    default:
                }
            }
        }
    }

    public void removePossibleMove(){
        this.black = 0;
        this.white = 0;
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                if(board[x][y]=='p'){
                    board[x][y]='e';
                }
                else if(board[x][y]=='b'){
                    this.black++;
                }
                else if(board[x][y]=='w'){
                    this.white++;
                }
            }
        }
    }

    public void removeHighlight() {
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                if(board[x][y]=='h'){
                    board[x][y]='p';
                }
            }
        }
    }

    public void setPosition(int x, int y, char c){
        this.board[x][y] = c;
    }

    public char getPosition(int x, int y) {
        return this.board[x][y];
    }


    public int getBlackChips() {
        return this.black;
    }

    public int getWhiteChips() {
        return this.white;
    }
}
