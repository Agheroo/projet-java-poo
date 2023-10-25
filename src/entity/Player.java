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

    public  void  setDefaultValues(){
        worldX =gp.tileSize * 23;
        worldY =gp.tileSize * 21;
        speed = 0;
        direction="down";
    }

    public  void getPlayerImage(){
        try {
            up1 = ImageIO.read(new FileInputStream("res/player/boy_up_1.png"));
            up2= ImageIO.read(new FileInputStream("res/player/boy_up_2.png"));
            down1= ImageIO.read(new FileInputStream("res/player/boy_down_1.png"));
            down2= ImageIO.read(new FileInputStream("res/player/boy_down_2.png"));
            left1= ImageIO.read(new FileInputStream("res/player/boy_left_1.png"));
            left2= ImageIO.read(new FileInputStream("res/player/boy_left_2.png"));
            right1= ImageIO.read(new FileInputStream("res/player/boy_right_1.png"));
            right2= ImageIO.read(new FileInputStream("res/player/boy_right_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public  void  update(double dt){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if(speed < 30){
                speed += 20*dt;
            }
            if(keyH.leftPressed){
                worldX -= speed*dt;
                dirX = -1;
                dirY = 0;
            }
            if(keyH.rightPressed){
                worldX += speed*dt;
                dirX = 1;
                dirY = 0;
            }
            if(keyH.upPressed){
                worldY -= speed*dt;
                dirY = -1;
                dirX = 0;
            }
            if(keyH.downPressed){
                worldY += speed*dt;
                dirY = 1;
                dirX = 0;
            }

            collisionOn=false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
           
            spriteCounter++;
            if (spriteCounter>10){
                if (spriteNum==1) spriteNum=2;
                else if (spriteNum==2) spriteNum=1;
                spriteCounter=0;
            }
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

        // g2.setColor(Color.white);
        // g2.fillRect(worldX, worldY, gp.tileSize,gp.tileSize);

        BufferedImage image=null;

        switch (direction) {
            case "up":
                if (spriteNum==1)image=up1;
                if (spriteNum==2)image=up2;
                break;
            case "down":
                if (spriteNum==1)image=down1;
                if (spriteNum==2)image=down2;
                break;
            case "left":
                if (spriteNum==1)image=left1;
                if (spriteNum==2)image=left2;
                break;
            case "right":
                if (spriteNum==1)image=right1;
                if (spriteNum==2)image=right2;
                break;
        }

        g2.drawImage(image, screenX, screenY,gp.tileSize, gp.tileSize, null);

    }
}
