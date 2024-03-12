package sk.stuba.fei.uim.oop.reversi.controller;

import lombok.Getter;
import sk.stuba.fei.uim.oop.reversi.board.Moves;
import sk.stuba.fei.uim.oop.reversi.Vykreslovanie;
import sk.stuba.fei.uim.oop.reversi.board.Board;

import javax.swing.*;
import java.awt.event.*;

public class GameController extends ControllerSupport{

    @Getter
    private Vykreslovanie vykreslovanie;
    private Board board;
    private Moves moves;
    @Getter
    private JLabel sizeLabel;
    @Getter
    private JLabel scoreLabel;
    @Getter
    private JLabel turnLabel;
    private int size;
    private int turn;
    private int nodeX, nodeY;
    private int fails;

    public GameController() {
        this.size=6;
        this.fails=0;
        this.board = new Board(this.size);
        this.vykreslovanie = new Vykreslovanie(this.board);
        this.vykreslovanie.addMouseListener(this);
        this.vykreslovanie.addMouseMotionListener(this);
        this.moves = new Moves(this.board);
        this.moves.setSize(size);
        this.sizeLabel = new JLabel((String) null,SwingConstants.CENTER);
        this.scoreLabel = new JLabel((String) null,SwingConstants.CENTER);
        this.turnLabel = new JLabel((String) null,SwingConstants.CENTER);
        updateLabels();
        game();
    }

    private void game() {
        int x;
        int y;

        if(moves.possibleMoves(currentTurn())){
            if(currentTurn() == 'w'){
                System.out.println("Enemy turn");
                System.out.println("White - On Position: " + moves.getPickedChipX() + "x" + moves.getPickedChipY());
                selectChip(moves.getPickedChipX(),moves.getPickedChipY());
            }
            else if(currentTurn() == 'b'){
                System.out.println("Your turn");

            }
            this.fails = 0;
        }
        else{
            System.out.println("CANT MOVE");
            turn++;
            this.fails++;
            game();
        }
        if(this.fails > 1){
            updateLabels();
        }
    }

    public char currentTurn(){
        if(this.turn % 2 == 0){
            return 'b';
        }
        else return 'w';
    }

    public void restart(){
        board.generateBoard(this.size);
        moves.setSize(size);
        this.turn = 0;
        updateLabels();
        game();
        repaint();
    }

    private void updateLabels(){
        this.sizeLabel.setText("Velkost: " + size + "x" + size);
        this.scoreLabel.setText("Black: " + board.getBlackChips() + "  White: " + board.getWhiteChips());
        if(this.fails > 1){
            if(board.getBlackChips() > board.getWhiteChips()){
                this.turnLabel.setText("VYHRAL CIERNY ");
            }
            if(board.getBlackChips() < board.getWhiteChips()){
                this.turnLabel.setText("VYHRAL BIELY ");
            }
            else {
                this.turnLabel.setText("REMIZA ! ");
            }
        }
        else if(currentTurn() == 'b'){
            this.turnLabel.setText("Na tahu je: Cierny");
        }
        else{
            this.turnLabel.setText("Na tahu je: Biely");
        }
    }

    public void repaint() {
        this.vykreslovanie.repaint();
    }

    public void selectChip(int x, int y){
        if(board.getPosition(x,y)=='h' || board.getPosition(x,y)=='p'){
            board.setPosition(x,y,currentTurn());
            moves.flippableChips(x,y);
            this.turn++;
            board.removePossibleMove();
            updateLabels();
            repaint();
            game();
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        int nodeX = e.getX()/board.getNodeSize();
        int nodeY = e.getY()/board.getNodeSize();

        selectChip(nodeX,nodeY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        int nodeMoveX = e.getX()/board.getNodeSize();
        int nodeMoveY = e.getY()/board.getNodeSize();

        if(board.getPosition(nodeMoveX,nodeMoveY)=='p'){
            board.removeHighlight();
            board.setPosition(nodeMoveX,nodeMoveY,'h');
            this.nodeX = nodeMoveX;
            this.nodeY = nodeMoveY;
        }
        if(this.nodeX != nodeMoveX || this.nodeY != nodeMoveY){
            board.removeHighlight();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if(e.getKeyCode() == KeyEvent.VK_R){
            this.restart();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.restart();
    }

    public void setSize(String s){
        switch (s){
            case "8x8":
                this.size = 8;
                break;
            case "10x10":
                this.size = 10;
                break;
            case "12x12":
                this.size = 12;
                break;
            default:
                this.size=6;
        }
    }
}