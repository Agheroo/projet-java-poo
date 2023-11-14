package entity;

import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity{
    KeyHandler keyH;

    int hasKey = 0;

    public Player(KeyHandler keyH, int worldX,int worldY,int dirX,int dirY,int speed,String facing,int spriteCnt,int spriteNum){
        super(worldX, worldY, dirX, dirY, speed, facing, spriteCnt,spriteNum);  //Calls the parent class as for every entity setup but need to specify keyH for player
        this.keyH=keyH;

        loadImage();
    }

    public  void loadImage(){
        try {
            for(int i=0; i<4 ;i++){
                idle_up[i] = ImageIO.read(new FileInputStream("res/player/idle/up"+(i+1)+".png"));
                idle_down[i] = ImageIO.read(new FileInputStream("res/player/idle/down"+(i+1)+".png"));
                idle_left[i] = ImageIO.read(new FileInputStream("res/player/idle/left"+(i+1)+".png"));
                idle_right[i] = ImageIO.read(new FileInputStream("res/player/idle/right"+(i+1)+".png"));

                walk_up[i] = ImageIO.read(new FileInputStream("res/player/walk/up"+(i+1)+".png"));
                walk_down[i] = ImageIO.read(new FileInputStream("res/player/walk/down"+(i+1)+".png"));
                walk_left[i] = ImageIO.read(new FileInputStream("res/player/walk/left"+(i+1)+".png"));
                walk_right[i] = ImageIO.read(new FileInputStream("res/player/walk/right"+(i+1)+".png"));
            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public  void  update(double dt){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if(speed < 30){ //Acceleration
                speed += 15*dt;
            }
            else{ speed = 30;}
            dirX = dirY=0;
            
            if(keyH.leftPressed){
                dirX = -1;
                facing = "left";
            }
            if(keyH.rightPressed){
                dirX = 1;
                facing = "right";
            }
            if(keyH.upPressed){
                dirY = -1;
                facing = "up";
            }
            if(keyH.downPressed){
                dirY = 1;
                facing = "down";
            }
        }
        else{       //Deceleration system (use of dirX & dirY instead of facing in case of diagonal movements)
            if(speed > 0){
                speed -= dt;
            }
            else{speed = 0;}
        }
        

        //Updating player position accurately (at any point in time either pressing keys or not)
        if(dirX == -1){
            worldX -= speed*dt;
        }
        if(dirX == 1){
            worldX += speed*dt;
        }
        if(dirY == -1){
            worldY -= speed*dt;
        }
        if(dirY == 1){
            worldY += speed*dt;
        }
        

    }



    /***    Drawing the player
     * 
     * @param g2
     */
    public  void draw(Graphics2D g2, int screenX, int screenY){
        BufferedImage image=null;


        if(speed == 0){ //PLAYER IDLE ANIMATIONS
            for(int i=0;i<4;i++){
                switch (facing) {
                    case "up":
                        if (spriteNum == i)image=idle_up[i];
                        break;
                    case "down":
                        if (spriteNum == i)image=idle_down[i];
                        break;
                    case "left":
                        if (spriteNum == i)image=idle_left[i];
                        break;
                    case "right":
                        if (spriteNum == i)image=idle_right[i];
                        break;
                }
            }
        }
        if(speed > 0){  //PLAYER WALKING ANIMATIONS
            for(int i=0;i<4;i++){
                switch (facing) {
                    case "up":
                        if (spriteNum == i)image=walk_up[i];
                        break;
                    case "down":
                        if (spriteNum == i)image=walk_down[i];
                        break;
                    case "left":
                        if (spriteNum == i)image=walk_left[i];
                        break;
                    case "right":
                        if (spriteNum == i)image=walk_right[i];
                        break;
                }
            }
        }
        //SPRITES & ANIMATIONS
        spriteUpdater++;
        if (spriteUpdater>20){

            spriteNum++;
            if(spriteNum == 4){
                spriteNum = 0;
            }
            spriteUpdater=0;
        }
        
        //Drawing player on screen
        g2.drawImage(image, screenX, screenY,screenSize, screenSize, null);
    }
}
