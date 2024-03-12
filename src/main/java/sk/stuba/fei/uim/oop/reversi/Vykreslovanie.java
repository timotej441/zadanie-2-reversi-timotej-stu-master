package sk.stuba.fei.uim.oop.reversi;

import sk.stuba.fei.uim.oop.reversi.board.Board;

import javax.swing.*;
import java.awt.*;

public class Vykreslovanie extends JPanel {

    private Board board;

    public Vykreslovanie(Board board) {
        this.board = board;
        this.setBackground(Color.GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.board.vykreslit(g);
    }
}
