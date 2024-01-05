/**
 * @file EntitySetter.java
 * @brief This file contains the implementation of the EntitySetter class, responsible for initializing and setting up objects (entities) in the game world.
 */

package entity;

import game.World;
import game.Const;
import java.awt.*;

/**
 * @class EntitySetter
 * @brief Responsible for initializing and setting up objects (entities) in the game world.
 */
public class EntitySetter {
    World world;

    /**
     * @brief Constructor for the EntitySetter class.
     * @param world The World object representing the game world.
     */
    public EntitySetter(World world) {
        this.world = world;
    }

    /**
     * @brief Method to set up objects in the game world, such as keys, doors, and chests.
     */
    public void setObject() {
        // Create and set up a Key object at a specific location in the world
        Props key = new OBJ_Key();
        key.worldX = 13 * Const.WRLD_entityScreenSize;
        key.worldY = 13 * Const.WRLD_entityScreenSize;
        world.addObject(new Point((int) key.worldX, (int) key.worldY), key);

        // Create and set up a Door object at a specific location in the world
        Props door = new OBJ_Door();
        door.worldX = 14 * Const.WRLD_entityScreenSize;
        door.worldY = 13 * Const.WRLD_entityScreenSize;
        world.addObject(new Point((int) door.worldX, (int) door.worldY), door);

        // Create and set up a Chest object at a specific location in the world
        Props chest = new OBJ_Chest();
        chest.worldX = 15 * Const.WRLD_entityScreenSize;
        chest.worldY = 13 * Const.WRLD_entityScreenSize;
        world.addObject(new Point((int) chest.worldX, (int) chest.worldY), chest);
    }
}
