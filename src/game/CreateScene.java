package game;

import entity.Player;
import entity.Ennemy;

/**
 * The CreateScene class provides methods to create different scenes in the game.
 */
public class CreateScene {

    /**
     * Creates a world scene.
     *
     * @return The created world scene.
     */
    public static Scene creator() {
        return World.getWorld();

    }

    /**
     * Creates a fight scene with a player and an enemy.
     *
     * @param player The player for the fight scene.
     * @param ennemy  The enemy for the fight scene.
     * @return The created fight scene.
     */
    public static Scene creator(Player player, Ennemy ennemy) {
        return new FightScene(player, ennemy);
    }



}
