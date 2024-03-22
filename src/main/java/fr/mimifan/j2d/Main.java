package fr.mimifan.j2d;

import fr.mimifan.j2d.panels.GamePanel;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame gameFrame = new JFrame("My 2D Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);

        gameFrame.add(new GamePanel());
        gameFrame.pack();

        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

}
