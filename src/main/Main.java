/**
 * @file Main.java
 * @brief This file contains the implementation of the Main class, which contains the main method to start the 2D Adventure game.
 */

package main;

import javax.swing.*;

import game.Window;

/**
 * @class Main
 * @brief Contains the main method to start the 2D Adventure game.
 */
public class Main {
    /**
     * The main method that initializes the game window and starts the game thread.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Create a JFrame (window) for the game
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

        // Create an instance of the Window class (game window)
        Window gameWindow = new Window();
        window.add(gameWindow);

        // Pack the components of the window
        window.pack();

        // Set the window to appear at the center of the screen
        window.setLocationRelativeTo(null);

        // Make the window visible
        window.setVisible(true);

        // Start the game thread to handle game logic and rendering
        gameWindow.startGameThread();
    }
}
