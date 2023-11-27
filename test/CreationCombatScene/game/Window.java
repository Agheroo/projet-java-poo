package CreationCombatScene.game;

import CreationCombatScene.entity.PlayerTest;
import entity.Enemy;
import game.Scene;

import javax.swing.*;
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


    //Game init
    final int FPS = 120;
    Thread gameThread;



    public Window(){
        PlayerTest playerTest=new PlayerTest();
        Enemy ennemy=new Enemy();

        scene = scene.fightScene(playerTest,ennemy);
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(scene.keyH);
        this.setFocusable(true);


    }

    @Override
    public void run() {
        double drawInterval = 10e9/FPS;
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
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        scene.update();         //Updating abstract class scene which means either world or fightscene
        repaint();
    }

    
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2= (Graphics2D)g;

        scene.draw(g2,screenWidth,screenHeight);

        g2.dispose();
    }
}