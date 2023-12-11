/**
 * @file FightScene.java
 * @brief This file contains the implementation of the FightScene class, representing the scene during a fight between a player and an enemy.
 */

package game;

import entity.Enemy;
import entity.Player;
import game.Scene.State;

import java.awt.*;
import java.util.Vector;


/**
 * @class FightScene
 * @extends Scene
 * @brief Represents the scene during a fight between a player and an enemy.
 */
public class FightScene {
    public enum FightState {FIGHTING, WON , LOST};
    //private double dt = Scene.getdt();
    public Player player;
    public Enemy enemy;
    public FightState state;

    /**
     * @brief Constructor for the FightScene class.
     * @param player The player entity in the fight.
     * @param enemy The enemy entity in the fight.
     */
    public FightScene(Player player, Enemy enemy) {
        System.out.println("Entering combat");
        this.player = player;
        this.enemy = enemy;
        state = FightState.FIGHTING;
    }

    /**
     * @brief Updates the fight scene.
     */
    public void update(Scene scene) {

        // Additional logic for the fight scene update
        System.out.println("Le joueur est en combat avec "+ enemy.name);
        if(scene.keyH.interactPressed){
            player.worldX = 15*player.screenSize;
            player.worldY = 15*player.screenSize;
            state = FightState.WON;
            scene.state = State.WORLD;
            player.speed = 0;
            killEnemy(World.enemies);
            
            scene.keyH.interactPressed = false;
        }
    }

    public void killEnemy(Vector<Enemy> enemies){
        enemies.remove(enemy);
    }


    /**
     * @brief Draws the fight scene.
     * @param g2 The Graphics2D object for drawing.
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     */
    public void draw(Graphics2D g2, int screenWidth, int screenHeight) {
        g2.setColor( new Color(0xFF2265));
        g2.fillRect(100,200,50,150);
        player.drawInFight(g2, screenWidth / 2 - (player.screenSize / 2), screenHeight / 2 - (player.screenSize / 2));
    }
}
