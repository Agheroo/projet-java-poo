package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Singleton design pattern for the KeyHandler class to handle keyboard input.
 */
public final class KeyHandler implements KeyListener {
    public static KeyHandler instance;
    public boolean upPressed, downPressed, leftPressed, rightPressed, interactPressed;

    // Private constructor to prevent external instantiation
    private KeyHandler() {}

    /**
     * Controls access to the singleton instance of KeyHandler.
     *
     * @return The singleton instance of KeyHandler.
     */
    public static KeyHandler getInstance() {
        if (instance == null) {
            instance = new KeyHandler();
        }
        return instance;
    }

    /**
     * Invoked when a key is typed.
     *
     * @param e The KeyEvent object.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this implementation
    }

    /**
     * Invoked when a key is pressed.
     *
     * @param e The KeyEvent object.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Check which key is pressed and set the corresponding boolean flag
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
    }

    /**
     * Invoked when a key is released.
     *
     * @param e The KeyEvent object.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // Check which key is released and set the corresponding boolean flag
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
    }
}
