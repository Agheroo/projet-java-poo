/**
 * @file EntitySetter.java
 * @brief This file contains the implementation of the EntitySetter class, responsible for initializing and setting up objects (entities) in the game world.
 */

package entity;

import game.World;

/**
 * @class EntitySetter
 * @brief Responsible for initializing and setting up objects (entities) in the game world.
 */
public class EntitySetter {
    World world;

    /**
     * @brief Constructor for the EntitySetter class.
     * @param gp The World object representing the game world.
     */
    public EntitySetter(World world) {
        this.world = world;
    }

    /**
     * @brief Method to set up objects in the game world, such as keys, doors, and chests.
     */
    public void setObject() {
        // Create and set up a Key object at a specific location in the world
        world.obj[0] = new OBJ_Key();
        int tileSize = 16;
        int scale = 3;
        world.obj[0].worldX = 13 * tileSize * scale;
        world.obj[0].worldY = 13 * tileSize * scale;

        // Create and set up a Door object at a specific location in the world
        world.obj[1] = new OBJ_Door();
        world.obj[1].worldX = 14 * tileSize * scale;
        world.obj[1].worldY = 13 * tileSize * scale;

        // Create and set up a Chest object at a specific location in the world
        world.obj[2] = new OBJ_Chest();
        world.obj[2].worldX = 15 * tileSize * scale;
        world.obj[2].worldY = 13 * tileSize * scale;
    }
}
