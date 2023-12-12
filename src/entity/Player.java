/**
 * @file Player.java
 * @brief This file contains the implementation of the Player class, representing a player entity extending the Character class.
 */

package entity;

import game.Scene;
import game.Scene.State;
import game.World;

/**
 * @class Player
 * @extends Character
 * @brief Represents the player entity in the game.
 */
public class Player extends Character {

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
    public Player(int worldX, int worldY, int dirX, int dirY, int speed, String facing, int spriteCntMax, int spriteSpeed) {
        super(worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);  // Calls the parent class for entity setup, specifying scene.keyH for player

        loadTextures("player");
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
                accelerate(30, dt);
            } else {
                if (speed > 0) {
                    decelerate(dt);
                }
            }

            // CHECK OBJECT COLLISION
            int objIndex = checkObject(this, World.getWorld(), true);
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
     * @param gp The current game world.
     * @param player Indicates if the entity is a player.
     * @return The index of the collided object or 999 if no collision.
     */
    public int checkObject(Entity entity, World gp, boolean player) {
        int index = 999;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                // Get entity's hitbox position
                entity.hitbox.x = (entity.worldX + entity.hitbox.x) / 2;
                entity.hitbox.y = (entity.worldY + entity.hitbox.y) / 2;

                // Get the object's solid area position
                gp.obj[i].hitbox.x = gp.obj[i].worldX + gp.obj[i].hitbox.x;
                gp.obj[i].hitbox.y = gp.obj[i].worldY + gp.obj[i].hitbox.y;
                switch (facing) {
                    case "up":
                        entity.hitbox.y -= entity._spriteSpeed;
                        if (entity.hitbox.intersects(gp.obj[i].hitbox)) {
                            if (gp.obj[i].collision == true) {
                                entity.collision = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.hitbox.y += entity._spriteSpeed;
                        if (entity.hitbox.intersects(gp.obj[i].hitbox)) {
                            if (gp.obj[i].collision == true) {
                                entity.collision = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.hitbox.x -= entity._spriteSpeed;
                        if (entity.hitbox.intersects(gp.obj[i].hitbox)) {
                            if (gp.obj[i].collision == true) {
                                entity.collision = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.hitbox.x += entity._spriteSpeed;
                        if (entity.hitbox.intersects(gp.obj[i].hitbox)) {
                            if (gp.obj[i].collision == true) {
                                entity.collision = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                            break;
                        }
                }
                entity.hitbox.x = entity.hitboxDefaultX;
                entity.hitbox.y = entity.hitboxDefaultY;
                gp.obj[i].hitbox.x = gp.obj[i].hitboxDefaultX;
                gp.obj[i].hitbox.y = gp.obj[i].hitboxDefaultY;
            }
        }
        return index;
    }
    public void interagitAvec(Entity b) {
        b.interagitAvec(this);
    }

    public void pickUpObject(World gp, int i) {
        if(i != 999) {
            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Key":
                    interagitAvec(entity);
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Key:"+hasKey);
                    break;
                case "Door":
                    if(hasKey > 0 ) {
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    System.out.println("Key:"+hasKey);
                    break;
            }
        }
    }
}
