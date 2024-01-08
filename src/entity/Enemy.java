/**
 * @file Enemy.java
 * @brief This file contains the implementation of the Enemy class, representing an enemy entity extending the Character class.
 */

package entity;

import game.Scene;

/**
 * @class Enemy
 * @extends Character
 * @brief Represents an enemy entity in the game.
 */
public class Enemy extends Character {

    private int _xpRate; // Experience points rate for defeating the enemy
    public String name;
    /**
     * @brief Constructor for the Enemy class.
     * @param worldX The X-coordinate of the enemy in the world.
     * @param worldY The Y-coordinate of the enemy in the world.
     * @param dirX The X-direction of the enemy.
     * @param dirY The Y-direction of the enemy.
     * @param speed The speed of the enemy's movement.
     * @param facing The direction the enemy is facing (up, down, left, right).
     * @param spriteCntMax The maximum number of sprites for animation.
     * @param spriteSpeed The speed of sprite animation.
     */
    public Enemy(String enemyName, int worldX, int worldY, int dirX, int dirY, int speed, String facing, int spriteCntMax, int spriteSpeed) {
        super(enemyName, worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);  // Calls the parent class for entity setup, specifying scene.keyH for player
    }

    /**
     * @brief Checks if the current enemy is in contact with the player
     * @param player The player entity
     */
    public boolean touchingPlayer(Player player){
        
        if((hitbox.x >= player.hitbox.x + player.hitbox.width)      // trop à droite
	    || (hitbox.x + hitbox.width <= player.hitbox.x) // trop à gauche
	    || (hitbox.y >= player.hitbox.y + player.hitbox.height) // trop en bas
	    || (hitbox.y + hitbox.height <= player.hitbox.y)){// trop en haut
            return false;
            
        }

        return true;
    }


    /**
     * @brief Updates the enemy entity based on the current scene and time elapsed.
     * @param scene The current game scene.
     * @param dt The time elapsed since the last update.
     */
    @Override
    public void update(Scene scene, double dt) {
        super.update(scene, dt); // Calls the parent class update method
        //TODO : find a method to make the enemy move in predictive patterns
    }
}
