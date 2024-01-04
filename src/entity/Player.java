/**
 * @file Player.java
 * @brief This file contains the implementation of the Player class, representing a player entity extending the Character class.
 */

package entity;

import game.Scene;
import game.Scene.State;
import game.World;

import java.awt.*;
import java.util.Map;
import java.util.Objects;

/**
 * @class Player
 * @extends Character
 * @brief Represents the player entity in the game.
 */
public class Player extends Character {

    int hasKey = 0;
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

        loadTextures(entityName);
    }

    /**
     * @brief Updates the player entity based on the current scene and time elapsed.
     * @param scene The current game scene.
     * @param dt The time elapsed since the last update.
     */
    public void update(Scene scene, double dt) {
        super.update(scene, dt);  // Calls the parent class update method

        // World updates
        if (scene.state == State.WORLD) {
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
            Point objIndex = checkObject(this, World.getWorld(), true);
            pickUpObject(World.getWorld(), objIndex);
        }

        // Fightscene updates
        if (scene.state == State.FIGHT) {
            // To be implemented
            

        }
    }

    /**
     * @brief Checks for collision with nearby objects using the player's hitbox.
     * @param entity The entity to check collision for.
     * @param gp     The current game world.
     * @param player Indicates if the entity is a player.
     * @return The coordinates of the collided object or null if no collision.
     */
    public Point checkObject(Entity entity, World gp, boolean player) {
        Point index = null;

        for (Props obj : gp.objMap.values()) {
            if (obj != null) {
                // Get entity's hitbox position
                entity.hitbox.x = (entity.worldX + entity.hitbox.x) / 2;
                entity.hitbox.y = (entity.worldY + entity.hitbox.y) / 2;

                // Get the object's solid area position
                obj.hitbox.x = obj.worldX + obj.hitbox.x;
                obj.hitbox.y = obj.worldY + obj.hitbox.y;

                // Check collision based on coordinates
                if (entity.hitbox.intersects(obj.hitbox) && obj.collision) {

                    entity.collision = true;

                    if (player) {
                        index = new Point((int) obj.worldX, (int) obj.worldY);
                        break;
                    }
                }

                entity.hitbox.x = entity.hitboxDefaultX;
                entity.hitbox.y = entity.hitboxDefaultY;
                obj.hitbox.x = obj.hitboxDefaultX;
                obj.hitbox.y = obj.hitboxDefaultY;
            }
        }

        return index;
    }


    public void pickUpObject(World gp, Point index) {
        if (index != null) {
            Entity object = gp.objMap.get(index);
            object.interagitAvec(this);
        }
    }
}
