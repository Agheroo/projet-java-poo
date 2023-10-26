package main;

import entity.Player;
import object.SuperObject;
import tiles.TilesManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements  Runnable{
    //Tiles size
    final int originalTileSize =16;
    final int scale=3;
    public final int tileSize=originalTileSize*scale;

    //World size
    public final int maxWorldCol=64;
    public final int maxWorldRow=64;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;

    //Game screen
    public final int maxScreenCol=16;
    public final int maxScreenRow=12;
    public final int screenWidth=tileSize*maxScreenCol;
    public final int screenHeight=tileSize*maxScreenRow;


    //Game init
    final int FPS = 120;
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    double dt = 0;

    TilesManager tileM =new TilesManager(this);


    public AssetSetter aSetter = new AssetSetter(this);
    public Player player =new Player(this,keyH);
    public SuperObject obj[]=new SuperObject[10];

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame(){
        aSetter.setObject();
    }

    public  void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 10e9/FPS;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();

            dt += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(dt > 0.1){
                update();
                repaint();
                dt-=0.1;
            }
        }
    }

    public  void update(){
        player.update(dt);
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2= (Graphics2D)g;

        tileM.draw(g2);

        for (int i=0;i<obj.length;i++){
            if (obj[i]!=null) obj[i].draw(g2,this);
        }

        player.draw(g2);

        g2.dispose();
    }
}