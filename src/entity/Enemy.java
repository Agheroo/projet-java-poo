/**
 * @file Enemy.java
 * @brief This file contains the implementation of the Enemy class, representing an enemy entity extending the Character class.
 */

package entity;

import game.Const;
import game.FightScene;
import game.World;

/**
 * @class Enemy
 * @extends Character
 * @brief Represents an enemy entity in the game.
 */
public class Enemy extends Character {

    public int xpRate; // Experience points rate for defeating the enemy
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
        if(hitbox.intersects(player.hitbox)){
            return true;
        }
        return false;

    }

    /**
     * Setter for enemy creation to initialize thei stats
     * @param xpRate    The xp that will give the enemy to the player once beaten
     * @param health    The health of the enemy
     * @param mana      The mana of the enemy (if he has spe atk)
     * @param strength  The "damage" of the enemy
     * @param defense   The reducing of damage of the enemy
     * @param agility   The probability of the enemy to dodge the attack
     * @param initiative    Who attacks first
     */
    public void setStats(int xpRate, int health, int mana, int strength, int defense,  int agility, int initiative){
        this.xpRate = xpRate;
        this.health = health;
        this.mana = mana;
        this.strength = strength;
        this.defense = defense;
        this.agility = agility;
        this.initiative = initiative;
    }


    /**
     * @brief Updates the enemy entity based on the current scene and time elapsed.
     * @param scene The current game scene.
     * @param dt The time elapsed since the last update.
     */
    public void update(double dt) {
        super.update(dt); // Calls the parent class update method
        //TODO : find a method to make the enemy move in predictive patterns
    }

    public void playerInterraction(Player player){
        System.out.println("Attention : je pratique le tabassing.");
        World.currfight = new FightScene(player,this);
        World.state = Const.State.FIGHT;
    }
}
