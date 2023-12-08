/**
 * @file FightScene.java
 * @brief This file contains the implementation of the FightScene class, representing the scene during a fight between a player and an enemy.
 */

package game;

import entity.Enemy;
import entity.Player;

import java.awt.*;

/**
 * @class FightScene
 * @extends Scene
 * @brief Represents the scene during a fight between a player and an enemy.
 */
public class FightScene extends Scene {
    public Player player;
    public Enemy enemy;

    /**
     * @brief Constructor for the FightScene class.
     * @param player The player entity in the fight.
     * @param enemy The enemy entity in the fight.
     */
    public FightScene(Player player, Enemy enemy) {
        System.out.println("Entering combat");
        this.player = player;
        this.enemy = enemy;
    }

    /**
     * @brief Updates the fight scene.
     */
    @Override
    public void update() {
        checkSceneChange();
        if (state == State.FIGHT) {
            // Additional logic for the fight scene update
        }
        // player.update(dt);
    }

    /**
     * @brief Draws the fight scene.
     * @param g2 The Graphics2D object for drawing.
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     */
    @Override
    public void draw(Graphics2D g2, int screenWidth, int screenHeight) {
        player.drawInFight(g2, screenWidth / 2 - (player.screenSize / 2), screenHeight / 2 - (player.screenSize / 2));
    }
}
