/**
 * @file EntitySetter.java
 * @brief This file contains the implementation of the EntitySetter class, responsible for initializing and setting up objects (entities) in the game world.
 */

package entity;

import game.World;

import java.awt.*;

/**
 * @class EntitySetter
 * @brief Responsible for initializing and setting up objects (entities) in the game world.
 */
public class EntitySetter {
    World gp;

    /**
     * @brief Constructor for the EntitySetter class.
     * @param gp The World object representing the game world.
     */
    public EntitySetter(World gp) {
        this.gp = gp;
    }

    /**
     * @brief Method to set up objects in the game world, such as keys, doors, and chests.
     */
    public void setObject() {
        // Create and set up a Key object at a specific location in the world
        Props key = new OBJ_Key();
        int tileSize = 16;
        int scale = 3;
        key.worldX = 13 * tileSize * scale;
        key.worldY = 13 * tileSize * scale;
        gp.addObject(new Point((int) key.worldX, (int) key.worldY), key);

        // Create and set up a Door object at a specific location in the world
        Props door = new OBJ_Door();
        door.worldX = 14 * tileSize * scale;
        door.worldY = 13 * tileSize * scale;
        gp.addObject(new Point((int) door.worldX, (int) door.worldY), door);

        // Create and set up a Chest object at a specific location in the world
        Props chest = new OBJ_Chest();
        chest.worldX = 15 * tileSize * scale;
        chest.worldY = 13 * tileSize * scale;
        gp.addObject(new Point((int) chest.worldX, (int) chest.worldY), chest);
    }
}
