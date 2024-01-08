/**
 * @file Player.java
 * @brief This file contains the implementation of the Player class, representing a player entity extending the Character class.
 */

package entity;

import entity.props.Props;
import game.Scene;
import game.Scene.State;
import game.World;
import item.Item;

import java.awt.*;

/**
 * @class Player
 * @extends Character
 * @brief Represents the player entity in the game.
 */
public class Player extends Character {

    public int hasKey = 0;
    /**
     * @brief Constructor for the Player class.
     * @param worldX The X-coordinate of the player in the world.
     * @param worldY The Y-coordinate of the player in the world.
     * @param dirX The X-direction of the player.
     * @param dirY The Y-direction of the player.
     * @param speed The speed of the player's movement.
     * @param facing The direction the player is facing (up, down, left, right).
     * @param spriteCntMax The maximum number of sprites for animation.
     * @param spriteSpeed The speed of sprite animation.
     */
    public Player(String entityName, int worldX, int worldY, int dirX, int dirY, int speed, String facing, int spriteCntMax, int spriteSpeed) {
        super(entityName, worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);  // Calls the parent class for entity setup, specifying scene.keyH for player
    }

    /**
     * @brief Updates the player entity based on the current scene and time elapsed.
     * @param scene The current game scene.
     * @param dt The time elapsed since the last update.
     */
    public void update(Scene scene, double dt) {
        super.update(scene, dt);  // Calls the parent class update method
        // World updates
        if (Scene.state == State.WORLD) {
            if (scene.keyH.upPressed || scene.keyH.downPressed || scene.keyH.leftPressed || scene.keyH.rightPressed) {
                dirX = 0;
                dirY = 0;
                if (scene.keyH.leftPressed) {
                    dirX = -1;
                    facing = "left";
                }
                if (scene.keyH.rightPressed) {
                    dirX = 1;
                    facing = "right";
                }
                if (scene.keyH.upPressed) {
                    dirY = -1;
                    facing = "up";
                }
                if (scene.keyH.downPressed) {
                    dirY = 1;
                    facing = "down";
                }
                accelerate(30,20, dt);
            } else {
                if (speed > 0) {
                    decelerate(1,dt);
                }
            }

            // CHECK OBJECT COLLISION
            Point objIndex = checkObject();
            pickUpObject(objIndex);

            Point enemyInd = checkEnemy();
            fightEnemy(enemyInd);
        }

        // Fightscene updates
        if (Scene.state == State.FIGHT) {
            // To be implemented
            

        }
    }

    /**
     * @brief Checks for collision with nearby objects using the player's hitbox.
     * @param entity The entity to check collision for.
     * @param world     The current game world.
     * @return The coordinates of the collided object or null if no collision.
     */
    public Point checkObject() {
        Point index = null;

        for (Props obj : World.objMap.values()) {
            if (obj != null) {
                if (hitbox.intersects(obj.hitbox)) {
                    if (obj.getCollision()) {   //If object has "solid" collision
                        //prevent the player from moving in the hitbox
                        obj.block(this);
                    }
                    index = new Point((int) obj.worldX, (int) obj.worldY);
                    break;
                }
            }
        }

        return index;
    }

    public Point checkEnemy(){
        Point index = null;

        for (Enemy enemy : World.enemies.values()) {
            if (enemy != null) {
                if (hitbox.intersects(enemy.hitbox)) {
                    
                    index = new Point((int) enemy.worldX, (int) enemy.worldY);
                    break;
                }
            }
        }

        return index;
    }
    
    public void fightEnemy(Point ind){
        if(ind != null){
            System.out.println("JE TABASSE");
            Enemy enemy = World.enemies.get(ind);
            enemy.playerInterraction(this); 
        }
        
    }

    public void pickUpObject(Point index) {
        if (index != null) {
            Entity object = World.objMap.get(index);
            object.playerInterraction(this);
        }
    }

    public void addItem(Item i){
        System.out.println("J'ai rajoutée un item");   
    }

}
