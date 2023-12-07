package game;

import main.KeyHandler;

import java.awt.*;

/**
 * The abstract Scene class serves as a template for different scenes in the game.
 * It includes key handling and methods for updating and drawing scenes.
 */
public abstract class Scene {
    // Key handler instance for handling user input
    public KeyHandler keyH = KeyHandler.getInstance();

    // Delta time for time-based updates (initialized to 0)
    public double dt = 0;

    /**
     * Abstract method to be implemented by subclasses.
     * Used for updating the scene's logic and state.
     */
    public abstract void update();

    /**
     * Abstract method to be implemented by subclasses.
     * Used for rendering/drawing the scene on the screen.
     *
     * @param g2            Graphics2D object for drawing
     * @param screenWidth   Width of the screen
     * @param screenHeight  Height of the screen
     */
    public abstract void draw(Graphics2D g2, int screenWidth, int screenHeight);
}
