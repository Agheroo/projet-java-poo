package entity;

import game.Scene;
import game.World;
import main.KeyHandler;

import java.awt.*;
/**
 * The Player class represents a player entity that extends the Entity class.
 * It includes additional functionality specific to the player, such as key handling and movement updates.
 */
public class Player extends Character {
    int hasKey = 0; // Indicates whether the player has a key

    /**
     * Constructor for the Player class.
     *
     * @param worldX        Initial X coordinate in the world
     * @param worldY        Initial Y coordinate in the world
     * @param dirX          Initial X direction
     * @param dirY          Initial Y direction
     * @param speed         Speed of the player
     * @param facing        Initial facing direction for animation
     * @param spriteCntMax  Maximum number of sprites for animations
     * @param spriteSpeed   Speed of sprite animations
     */
    public Player(int worldX, int worldY, int dirX, int dirY, int speed, String facing, int spriteCntMax, int spriteSpeed) {
        super(worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);  // Calls the parent class for entity setup

        hitbox= new Rectangle(16,16,32,32);
        loadTextures("player");
    }

    /**
     * Update method for the Player class.
     *
     * @param world The current game world
     * @param dt    Time elapsed since the last update
     */
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
        } else { // Deceleration system (use of dirX & dirY instead of facing in case of diagonal movements)
            if (speed > 0) {
                speed -= dt;
            } else {
                speed = 0;
            }
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
