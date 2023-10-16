package main;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        JFrame windows = new JFrame();
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setResizable(false);
        windows.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        windows.add(gamePanel);

        windows.pack();

        windows.setLocationRelativeTo(null);
        windows.setVisible(true);

        gamePanel.startGameThread();
    }
}
