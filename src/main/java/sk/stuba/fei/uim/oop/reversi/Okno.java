package sk.stuba.fei.uim.oop.reversi;

import sk.stuba.fei.uim.oop.reversi.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Okno {

    public Okno() {
        String[] sizes = {"6x6", "8x8", "10x10", "12x12"};
        GameController controller = new GameController();
        JFrame frame = new JFrame("Reversi");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(712,787);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.addKeyListener(controller);
        frame.add(controller.getVykreslovanie());

        JComboBox comboBox = new JComboBox(sizes);
        comboBox.addActionListener(e -> {
            controller.setSize((String) Objects.requireNonNull(comboBox.getSelectedItem()));
            controller.restart();
        });

        comboBox.setFocusable(false);
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(controller);
        restartButton.setFocusable(false);

        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(2,3));
        menu.add(controller.getSizeLabel());
        menu.add(controller.getScoreLabel());
        menu.add(comboBox);
        menu.add(new JLabel());
        menu.add(controller.getTurnLabel());
        menu.add(restartButton);

        frame.add(menu, BorderLayout.PAGE_START);
        frame.setVisible(true);
    }
}
