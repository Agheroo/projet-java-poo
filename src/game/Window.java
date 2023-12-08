/**
 * @file Window.java
 * @brief This file contains the implementation of the Window class, responsible for displaying the game based on the backend (World.java).
 */

package game;

import javax.swing.JPanel;

import game.Scene.State;

import java.awt.*;

/**
 * @class Window
 * @extends JPanel
 * @implements Runnable
 * @brief Represents the window that displays the game.
 */
public class Window extends JPanel implements Runnable {
    /**
     * The game scene.
     */
    public Scene scene;

    /**
     * The width of the game screen.
     */
    public final int screenWidth = 800;

    /**
     * The height of the game screen.
     */
    public final int screenHeight = 600;

    /**
     * The game world.
     */
    World world = World.getWorld();

    /**
     * The frames per second for the game.
     */
    private final int _FPS = 120;

    /**
     * The game thread.
     */
    Thread gameThread;

    /**
     * @brief Constructor for the Window class.
     */
    public Window() {
        scene = World.getWorld();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(scene.keyH);
        this.setFocusable(true);
        world.setupGame(); // Création de l'instance du setter
    }

    /**
     * @brief Runs the game loop.
     */
    @Override
    public void run() {
        double drawInterval = 10e9 / _FPS;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            scene.dt += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (scene.dt > 0.1) {
                update(); // update() method for window both updates the world and repaints the components of it
                scene.dt -= 0.1;
            }
        }
    }

    /**
     * @brief Starts the game thread.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * @brief Updates the game scene.
     */
    public void update() {
        scene.update(); // Updates the whole world's props & animations
        repaint();
    }

    /**
     * @brief Paints the components of the game window.
     * @param g The Graphics object for drawing.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        scene.draw(g2, screenWidth, screenHeight);

        // Darkens the scene on the background to let the menu on top
        if (scene.state == State.PAUSE) {
            g2.setColor(new Color(0, 0, 0, 180));
            g2.fillRect(0, 0, screenWidth, screenHeight);
            scene.menu.draw(g2, screenWidth, screenHeight);
        }

        g2.dispose();
    }
}
