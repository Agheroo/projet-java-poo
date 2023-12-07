package main;

import javax.swing.*;

import game.Window;

/**
 * The Main class contains the main method to start the 2D Adventure game.
 */
public class Main {
    /**
     * The main method that initializes the game window and starts the game thread.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Create a JFrame (window) for the game
        JFrame windows = new JFrame();
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setResizable(false);
        windows.setTitle("2D Adventure");

        // Create an instance of the Window class (game window)
        Window gameWindow = new Window();
        windows.add(gameWindow);

        // Pack the components of the window
        windows.pack();

        // Set the window to appear at the center of the screen
        windows.setLocationRelativeTo(null);

        // Make the window visible
        windows.setVisible(true);

        // Start the game thread to handle game logic and rendering
        gameWindow.startGameThread();
    }
}
