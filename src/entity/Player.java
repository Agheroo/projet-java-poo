package entity;

import main.KeyHandler;

public class Player extends Entity{
    private KeyHandler keyH;

    int hasKey = 0;

    public Player(KeyHandler keyH, int worldX,int worldY,int dirX,int dirY,int speed,String facing, int spriteCntMax, int spriteSpeed){
        super(worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);  //Calls the parent class as for every entity setup but need to specify keyH for player
        this.keyH=keyH;

        loadTextures("player");
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
}
