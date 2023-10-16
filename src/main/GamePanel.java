package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements  Runnable{


    final int originalTileSize =16;
    final int scale=3;

    final int tileSize=originalTileSize*scale;
    final int maxScreenCol=16;
    final int maxScreenRow=12;
    final int screenWidth=tileSize*maxScreenCol;
    final int screenHeight=tileSize*maxScreenRow;
    KeyHandler keyH=new KeyHandler();


    int playerX =100;
    int playerY =100;
    int playerSpeed =4;

    Thread gameThread;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public  void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        while (gameThread!= null){

            long currentTime=System.nanoTime();

            update();

            repaint();

        }
    }

    public  void update(){
        if(keyH.upPressed){
            playerY-=playerSpeed;
        }
        else if(keyH.downPressed){
            playerY+=playerSpeed;
        }
        else if(keyH.leftPressed){
            playerX-=playerSpeed;
        }
        else if(keyH.rightPressed){
            playerX+=playerSpeed;
        }
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2= (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize ,tileSize);

        g2.dispose();
    }
}