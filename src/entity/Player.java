package entity;

import game.Scene;
import game.World;
import main.KeyHandler;

import java.awt.*;

public class Player extends Character{
    int hasKey = 0;

    public Player(int worldX,int worldY,int dirX,int dirY,int speed,String facing, int spriteCntMax, int spriteSpeed){
        super(worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);  //Calls the parent class as for every entity setup but need to specify scene.keyH for player

        hitbox= new Rectangle(16,16,32,32);
        loadTextures("player");
    }

    public  void  update(World world, double dt){
        int tmp;
        KeyHandler keyH=KeyHandler.getInstance();
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if(speed < 30){ //Acceleration
                speed += 20*dt;
            }
            if(speed>30){
                speed=30;
            }
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
            tmp=worldX;
            worldX -= speed*dt;
            if (checkCollision(world.getMap()))worldX = tmp;

        }
        if(dirX == 1){
            tmp=worldX;
            worldX += speed*dt;
            if (checkCollision(world.getMap()))worldX = tmp;
        }
        if(dirY == -1){
            tmp=worldY;
            worldY -= speed*dt;
            if (checkCollision(world.getMap()))worldY = tmp;
        }
        if(dirY == 1){
            tmp=worldY;
            worldY += speed*dt;
            if (checkCollision(world.getMap()))worldY = tmp;
        }
    }
}
