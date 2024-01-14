/**
 * @file Window.java
 * @brief This file contains the implementation of the Window class, responsible for displaying the game based on the backend (World.java).
 */

package game;

import javax.swing.JPanel;

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
     * The game world.
     */
    World world = World.getWorld();

    /**
     * The game thread.
     */
    Thread gameThread;

    /**
     * @brief Constructor for the Window class.
     */
    public Window() {
        scene = World.getWorld();
        this.setPreferredSize(new Dimension(Const.WDW_width, Const.WDW_height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(Scene.keyH);
        this.setFocusable(true);
        world.setupGame(); // Creation of instance setter
    }

    /**
     * @brief Runs the game loop.
     */
    @Override
    public void run() {
        double drawInterval = 10e9 / Const.WDW_FPS;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            Scene.dt += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (Scene.dt > 0.1) {
                update(); // update() method for window both updates the world and repaints the components of it
                Scene.dt -= 0.05;
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

        scene.draw(g2, Const.WDW_width, Const.WDW_height);



        g2.dispose();
    }
}
