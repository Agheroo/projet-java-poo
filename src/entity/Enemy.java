/**
 * @file Enemy.java
 * @brief This file contains the implementation of the Enemy class, representing an enemy entity extending the Character class.
 */

package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.Attack;
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
    public Attack attack;
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
     * @param attack The attack that the enemy will use to fight
     */
    public void setStats(int xpRate, int health, int mana, int strength, int defense,  int agility, int initiative, Attack attack){
        this.xpRate = xpRate;
        this.health = health;
        this.maxHealth = health;
        this.mana = mana;
        this.maxMana = mana;
        this.strength = strength;
        this.defense = defense;
        this.agility = agility;
        this.initiative = initiative;
        this.attack = attack;
    }


    /**
     * @brief Updates the enemy entity based on the current scene and time elapsed.
     * @param scene The current game scene.
     * @param dt The time elapsed since the last update.
     */
    public void update(double dt) {
        super.update(dt); // Calls the parent class update method
        // find a method to make the enemy move in predictive patterns
    }
    

    public void playerInterraction(Player player){
        World.currfight = new FightScene(player,this);
        World.state = Const.State.FIGHT;
    }

    /**
     * @brief Draws the character in the fight scene.
     * @param g2 The Graphics2D object for drawing.
     * @param screenX The X-coordinate on the screen.
     * @param screenY The Y-coordinate on the screen.
     */
    public void drawInFight(Graphics2D g2, int screenX, int screenY){
        BufferedImage image = null;
        for (int i = 0; i < _spriteCntMax; i++) {
            if(_spriteCnt == i){
                image = _idle_left[i];
            }
        }
        g2.drawImage(image,screenX,screenY,Const.FGHT_entityScreenSize,Const.FGHT_entityScreenSize,null);
    }
}
