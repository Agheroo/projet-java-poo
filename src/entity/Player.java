/**
 * @file Player.java
 * @brief This file contains the implementation of the Player class, representing a player entity extending the Character class.
 */

package entity;

import entity.props.Props;
import game.Attack;
import game.Const;
import game.Scene;
import game.World;
import item.Item;

import java.awt.*;
import java.util.Vector;

/**
 * @class Player
 * @extends Character
 * @brief Represents the player entity in the game.
 */
public class Player extends Character {
    public int level;
    public int xp;  //xp of the player
    public int xpMax; //The amount of xp the player needs to level up
    public int hasKey = 0;
    public static Attack attacks[]; 
    public static Vector<Item> potions = new Vector<Item>();

    /*
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
        super(entityName, worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);  // Calls the parent class for entity setup, specifying Scene.keyH for player
        
        attacks = new Attack[4];
        level = 1;
        xpMax = 700;
        xp = 50;
    }

    /**
     * @brief Updates the player entity based on the current scene and time elapsed.
     * @param dt The time elapsed since the last update.
     */
    public void update(double dt) {
        super.update(dt);  // Calls the parent class update method
        // World updates
        if (Scene.state == Const.State.WORLD) {
            if (Scene.keyH.upPressed || Scene.keyH.downPressed || Scene.keyH.leftPressed || Scene.keyH.rightPressed) {
                dirX = 0;
                dirY = 0;
                if (Scene.keyH.leftPressed) {
                    dirX = -1;
                    facing = "left";
                }
                if (Scene.keyH.rightPressed) {
                    dirX = 1;
                    facing = "right";
                }
                if (Scene.keyH.upPressed) {
                    dirY = -1;
                    facing = "up";
                }
                if (Scene.keyH.downPressed) {
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
        if (Scene.state == Const.State.FIGHT) {
            // To be implemented
            

        }
    }

    /**
     * @brief Checks for collision with nearby objects using the player's hitbox.
     * @return The coordinates of the collided object or null if no collision.
     */
    public Point checkObject() {
        Point index = null;

        for (Props obj : World.objMap.values()) {
            if (obj != null) {
                if (hitbox.intersects(obj.hitbox)) {
                    if(obj.getCollision()){ //If object has "solid" collision
                        obj.block(this);    //Block the player
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

    /**
     * @brief Lets the player pick up the object near him if he presses the interract button (space)
     * @param index Point "coordinates" of the object
     */
    public void pickUpObject(Point index) {
        if (index != null) {
            Entity object = World.objMap.get(index);
            if(Scene.keyH.interactPressed){
                object.playerInterraction(this);
                Scene.keyH.interactPressed = false;
            }   
            
        }
    }

    /**
     * @brief Adds item to the virtual inventory of the player, if it's not a potion it will be directly converted to additionnal stats
     * @param i The item t add
     */
    public void addItem(Item i){
        System.out.println("J'ai rajoutée un item"); 
        if(i.type == "potion"){
            potions.add(i);
        }
        //Change stats directly if the item is not a potion
    }

    public void playerInterraction(Player player) {
        //
    }
}
