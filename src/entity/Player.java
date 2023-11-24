package entity;

import game.Scene;

public class Player extends Entity{
    int hasKey = 0;

    public Player(int worldX,int worldY,int dirX,int dirY,int speed,String facing, int spriteCntMax, int spriteSpeed){
        super(worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);  //Calls the parent class as for every entity setup but need to specify scene.keyH for player


        loadTextures("player");
    }

    public  void  update(Scene scene, double dt){
        if(scene.keyH.upPressed || scene.keyH.downPressed || scene.keyH.leftPressed || scene.keyH.rightPressed){
            if(speed < 30){ //Acceleration
                speed += 20*dt;
            }
            if(speed>30){
                speed=30;
            }
            dirX = dirY=0;
            
            if(scene.keyH.leftPressed){
                dirX = -1;
                facing = "left";
            }
            if(scene.keyH.rightPressed){
                dirX = 1;
                facing = "right";
            }
            if(scene.keyH.upPressed){
                dirY = -1;
                facing = "up";
            }
            if(scene.keyH.downPressed){
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
