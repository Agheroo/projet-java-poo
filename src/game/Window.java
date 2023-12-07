package game;

import tiles.TileManager;

import javax.swing.JPanel;

import game.Scene.State;

import java.awt.*;

/*
 * This class's purpose is to keep track of the displaying of the game, based on what is on the backend (World.java)
 */
public class Window extends JPanel implements Runnable{
    //Game world
    public Scene scene;

    //Game screen
    public final int screenWidth=800;
    public final int screenHeight=600;
    World world = World.getWorld();

    //Game init
    private final int _FPS = 120;
    Thread gameThread;



    public Window(){
        //scene=CreateScene.creator();
        scene = World.getWorld();
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(scene.keyH);
        this.setFocusable(true);
    }

    @Override
    public void run() {
        double drawInterval = 10e9/_FPS;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();

            scene.dt += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(scene.dt > 0.1){
                update();           //update() method for window both updates the world and repaints the components of it
                scene.dt-= 0.1;
            }
        }
    }

    public void startGameThread(){
        world.setupGame(); // Création de l'instance du setter
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        scene.update();     //Updates the whole world's props & animations
        repaint();
    }

    
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2= (Graphics2D)g;
        

        scene.draw(g2,screenWidth,screenHeight);

        //Darkens the scene on the background to let the menu on top
        if(scene.state == State.PAUSE){
            g2.setColor(new Color(0,0,0,180));
            g2.fillRect(0, 0, screenWidth, screenHeight);
            scene.menu.draw(g2,screenWidth,screenHeight);
        }

        g2.dispose();
    }
}