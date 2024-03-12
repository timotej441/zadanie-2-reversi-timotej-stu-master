package sk.stuba.fei.uim.oop.reversi.board;

import lombok.Setter;

import java.util.ArrayList;

public class Moves{

    private Board board;
    private Direction dir;
    private int x,y;
    @Setter
    private int size;
    @Setter
    private boolean flip;
    private int mostCaptured, currentlyCaptured;
    private int pickedChipX, pickedChipY;
    ArrayList<Integer> xChips;
    ArrayList<Integer> yChips;

    public Moves(Board board){
        this.board = board;
        dir = new Direction();
        this.flip = false;
        this.mostCaptured = 0;
        this.currentlyCaptured = 0;
    }

    public boolean possibleMoves(char currentTurn){
        boolean hasPossibleMoves = false;
        this.currentlyCaptured = 0;
        this.mostCaptured = 0;
        for(int i = 0;i < size; i++){
            for (int j = 0;j < size; j++){
                if(board.getPosition(j,i)=='e'){
                    this.x = j;
                    this.y = i;
                    if(diretions(currentTurn)){
                        if(currentlyCaptured > mostCaptured){
                            this.pickedChipX = this.x;
                            this.pickedChipY = this.y;
                            mostCaptured = currentlyCaptured;
                        }
                        board.setPosition(this.x,this.y,'p');
                        hasPossibleMoves = true;
                    }
                }
            }
        }
        return hasPossibleMoves;
    }

    public void flippableChips(int x, int y){
        char currentTurn = board.getPosition(x,y);
        this.x = x;
        this.y = y;
        this.flip = true;
        diretions(currentTurn);
        this.flip = false;
    }

    public void flipChips(char currentTurn){
        char nepriatel;

        if(currentTurn == 'b'){
            nepriatel = 'w';
        }
        else {
            nepriatel = 'b';
        }

        for(int i = 0; i < this.xChips.size(); i++){
            if(board.getPosition(this.xChips.get(i),this.yChips.get(i)) == nepriatel){
                board.setPosition(this.xChips.get(i),this.yChips.get(i), currentTurn);
            }
        }
    }

    private boolean diretions(char currentTurn) {
        char nepriatel;
        int j = 0;
        boolean direction = false;

        if(currentTurn == 'b'){
            nepriatel = 'w';
        }
        else {
            nepriatel = 'b';
        }

        for(int i = 0; i < 8; i++){
            boolean passedThrough = false;
            this.xChips = new ArrayList<Integer>();
            this.yChips = new ArrayList<Integer>();
            int xNode = this.x + dir.getX(i);
            int yNode = this.y + dir.getY(i);

            if(!(xNode >= size || yNode >= size || xNode < 0 || yNode < 0)){
                while(xNode >= 0 && yNode >= 0 && board.getPosition(xNode,yNode) == nepriatel){
                    passedThrough = true;
                    this.xChips.add(xNode);
                    this.yChips.add(yNode);
                    xNode += dir.getX(i);
                    yNode += dir.getY(i);
                }
                if(xNode >= 0 && yNode >= 0 && board.getPosition(xNode,yNode) == currentTurn && passedThrough){
                    if(++j > currentlyCaptured){
                        currentlyCaptured = j;
                    }
                    if(this.flip){
                        flipChips(currentTurn);
                    }
                    direction = true;
                }
            }
        }
            return direction;
    }

    public int getPickedChipX(){
        return this.pickedChipX;
    }

    public int getPickedChipY(){
        return this.pickedChipY;
    }

    public int getMostCaptured() {
        return this.mostCaptured;
    }
}
