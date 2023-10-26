package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public BufferedImage[] up_idle = new BufferedImage[4];
    public BufferedImage[] down_idle = new BufferedImage[4];
    public BufferedImage[] left_idle = new BufferedImage[4];
    public BufferedImage[] right_idle = new BufferedImage[4];
    public BufferedImage[] right_walk = new BufferedImage[4];
    public BufferedImage[] up_walk = new BufferedImage[4];
    public BufferedImage[] down_walk = new BufferedImage[4];
    public BufferedImage[] left_walk = new BufferedImage[4];

    public  Player(GamePanel gp, KeyHandler keyH){

        this.gp=gp;
        this.keyH=keyH;

        screenX= gp.screenWidth/2 -(gp.tileSize/2);
        screenY= gp.screenHeight/2 -(gp.tileSize/2);

        solidArea =new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=32;
        solidArea.height=32;

        setDefaultValues();
        getPlayerImage();
    }

    //Initializing player (spawn & base stats)
    public  void  setDefaultValues(){
        worldX =gp.tileSize * 23;
        worldY =gp.tileSize * 21;
        dirX = dirY = 0;
        speed = 0;
        direction="down";
    }

    public  void getPlayerImage(){
        try {
            for(int i=0; i<4 ;i++){
                up_idle[i] = ImageIO.read(new FileInputStream("res/player/idle/up"+(i+1)+".png"));
                down_idle[i] = ImageIO.read(new FileInputStream("res/player/idle/down"+(i+1)+".png"));
                left_idle[i] = ImageIO.read(new FileInputStream("res/player/idle/left"+(i+1)+".png"));
                right_idle[i] = ImageIO.read(new FileInputStream("res/player/idle/right"+(i+1)+".png"));

                up_walk[i] = ImageIO.read(new FileInputStream("res/player/walk/up"+(i+1)+".png"));
                down_walk[i] = ImageIO.read(new FileInputStream("res/player/walk/down"+(i+1)+".png"));
                left_walk[i] = ImageIO.read(new FileInputStream("res/player/walk/left"+(i+1)+".png"));
                right_walk[i] = ImageIO.read(new FileInputStream("res/player/walk/right"+(i+1)+".png"));
            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public  void  update(double dt){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            dirX = dirY=0;
            if(speed < 20){
                speed += 20*dt;
            }
            if(keyH.leftPressed){
                worldX -= speed*dt;
                dirX = -1;
                direction = "left";
            }
            if(keyH.rightPressed){
                worldX += speed*dt;
                dirX = 1;
                direction = "right";
            }
            if(keyH.upPressed){
                worldY -= speed*dt;
                dirY = -1;
                direction = "up";
            }
            if(keyH.downPressed){
                worldY += speed*dt;
                dirY = 1;
                direction = "down";
            }

            collisionOn=false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

        }
        else{
            if(speed > 0){
                speed -= 10*dt;
            }
            else{speed = 0;}
            if(dirX == 1){
                worldX += speed*dt;
            }
            if(dirX == -1){
                worldX -= speed*dt;
            }
            if(dirY == 1){
                worldY += speed*dt;
            }
            if(dirY == -1){
                worldY -= speed*dt;
            }
                
        }

        //SPRITES & ANIMATIONS
        spriteCounter++;
        if (spriteCounter>20){

            spriteNum++;
            if(spriteNum == 4){
                spriteNum = 0;
            }
            spriteCounter=0;
        }
    }

    public void pickUpObject(int i) {
        if(i != 999) {
            String objectName = gp.obj[i].name;
            switch(objectName) {
            case "Key":
                hasKey++;
                gp.obj[i] = null;
                System.out.println("Key: "+hasKey);
                break;
            case "Door":
                if(hasKey > 0) {
                    gp.obj[i] = null;
                    hasKey--;
                }
                System.out.println("Key : "+hasKey);
                break;
            }
        }
    }

    public  void draw(Graphics2D g2){
        BufferedImage image=null;



        if(speed == 0){ //PLAYER IDLE ANIMATIONS
            for(int i=0;i<4;i++){
                switch (direction) {
                    case "up":
                        if (spriteNum == i)image=up_idle[i];
                        break;
                    case "down":
                        if (spriteNum == i)image=down_idle[i];
                        break;
                    case "left":
                        if (spriteNum == i)image=left_idle[i];
                        break;
                    case "right":
                        if (spriteNum == i)image=right_idle[i];
                        break;
                }
            }
            
        }
        if(speed > 0){  //PLAYER WALKING ANIMATIONS
            for(int i=0;i<4;i++){
                switch (direction) {
                    case "up":
                        if (spriteNum == i)image=up_walk[i];
                        break;
                    case "down":
                        if (spriteNum == i)image=down_walk[i];
                        break;
                    case "left":
                        if (spriteNum == i)image=left_walk[i];
                        break;
                    case "right":
                        if (spriteNum == i)image=right_walk[i];
                        break;
                }
            }
        }
        

        g2.drawImage(image, screenX, screenY,gp.tileSize, gp.tileSize, null);

    }
}
