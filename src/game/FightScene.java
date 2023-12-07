package game;

import entity.Ennemy;  // Corrected typo in class name
import entity.Player;

import java.awt.*;

/**
 * The FightScene class represents a scene for handling combat between a player and an enemy.
 */
public class FightScene extends Scene {
    Player player;
    Ennemy ennemy;  // Corrected typo in class name

    /**
     * Constructor for the FightScene class.
     *
     * @param _player The player object in the combat scene
     * @param _ennemy The enemy object in the combat scene
     */
    public FightScene(Player _player, Ennemy _ennemy) {
        System.out.println("Je suis en combat");  // French: "I am in combat"
        player = _player;
        ennemy = _ennemy;
    }

    /**
     * Update method for the FightScene.
     * Currently commented out to prevent unnecessary updates.
     */
    @Override
    public void update() {
        // player.update(dt);
    }

    /**
     * Draw method for the FightScene.
     *
     * @param g2           Graphics2D object for drawing
     * @param screenWidth  Screen width for positioning the player
     * @param screenHeight Screen height for positioning the player
     */
    @Override
    public void draw(Graphics2D g2, int screenWidth, int screenHeight) {
        player.draw(g2, screenWidth / 2 - (player.screenSize / 2), screenHeight / 2 - (player.screenSize / 2));
    }
}
