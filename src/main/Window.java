package main;

import javax.swing.JPanel;
import java.awt.*;

/*
 * This class's purpose is to keep track of the displaying of the game, based on what is on the backend (World.java)
 */
public class Window extends JPanel implements Runnable{
    //Game world
    public World world = new World();

    //Game screen
    public final int screenWidth=800;
    public final int screenHeight=600;


    //Game init
    final int FPS = 120;
    Thread gameThread;



    public Window(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(world.keyH);
        this.setFocusable(true);

    }

    @Override
    public void run() {
        double drawInterval = 10e9/FPS;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();

            world.dt += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(world.dt > 0.1){
                update();           //update() method for window both updates the world and repaints the components of it
                world.dt-=0.1;
            }
        }
    }

    public  void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public  void update(){
        world.update();     //Updates the whole world's props & animfations 
        repaint();
    }

    
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2= (Graphics2D)g;

        world.tileManager.draw(g2,world);
        world.player.draw(g2,screenWidth/2 - (world.player.screenSize/2), screenHeight/2 - (world.player.screenSize/2));

        g2.dispose();
    }
}