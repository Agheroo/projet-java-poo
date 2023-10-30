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

        Window gameWindow = new Window();
        windows.add(gameWindow);

        windows.pack();

        windows.setLocationRelativeTo(null);
        windows.setVisible(true);

        gameWindow.startGameThread();
    }
}
