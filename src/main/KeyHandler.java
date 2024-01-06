/**
 * @file KeyHandler.java
 * @brief This file contains the implementation of the KeyHandler class, following the singleton pattern.
 */

package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @class KeyHandler
 * @implements KeyListener
 * @brief Handles keyboard input using the singleton pattern.
 */
public final class KeyHandler implements KeyListener {
    public static KeyHandler instance;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean interactPressed;
    public boolean escPressed;

    // Private constructor to prevent instantiation from outside the class
    private KeyHandler() {}

    /**
     * Gets the instance of the KeyHandler using the singleton pattern.
     *
     * @return The KeyHandler instance.
     */
    public static KeyHandler getInstance() {
        if (instance == null) {
            instance = new KeyHandler();
        }
        return instance;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_Z) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_Q) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            interactPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_Z) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_Q) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            interactPressed = false;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = false;
        }
    }
}
