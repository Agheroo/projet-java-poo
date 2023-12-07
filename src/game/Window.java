package game;

import tiles.TileManager;

import javax.swing.JPanel;

import java.awt.*;

/**
 * The Window class manages the display of the game, based on the backend (World.java).
 */
public class Window extends JPanel implements Runnable {
    // Game world
    public Scene scene;

    // Game screen dimensions
    public final int screenWidth = 800;
    public final int screenHeight = 600;
    World world = World.getWorld();

    // Game initialization
    private final int _FPS = 120;
    Thread gameThread;

    /**
     * Constructor for the Window class.
     */
    public Window() {
        scene = CreateScene.creator();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(scene.keyH);
        this.setFocusable(true);
    }

    /**
     * Implementation of the run method for the Runnable interface.
     * Manages the game loop and updates the scene at a specific frame rate.
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
                update();           // update() method for the window both updates the world and repaints its components
                scene.dt -= 0.1;
            }
        }
    }

    /**
     * Starts the game thread.
     */
    public void startGameThread() {
        world.setupGame(); // Création de l'instance du setter
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Updates the scene, including the world's properties and animations.
     */
    public void update() {
        scene.update();     // Updates the whole world's props & animations
        repaint();
    }

    /**
     * Overrides the paintComponent method to draw the scene on the JPanel.
     *
     * @param g Graphics object for painting
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        scene.draw(g2, screenWidth, screenHeight);

        g2.dispose();
    }
}
