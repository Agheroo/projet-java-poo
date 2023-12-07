package entity;


import game.World;

/**
 * The EntitySetter class is responsible for initializing and setting up objects (entities) in the game world.
 */
public class EntitySetter {
    World gp;

    /**
     * Constructor for the EntitySetter class.
     *
     * @param gp The World object representing the game world.
     */
    public EntitySetter(World gp) {
        this.gp = gp;
    }

    /**
     * Method to set up objects in the game world, such as keys, doors, and chests.
     */
    public void setObject() {
        // Create and set up a Key object at a specific location in the world
        gp.obj[0] = new OBJ_Key();
        int tileSize = 16;
        int scale = 3;
        gp.obj[0].worldX = 13 * tileSize * scale;
        gp.obj[0].worldY = 13 * tileSize * scale;

        // Create and set up a Door object at a specific location in the world
        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 14 * tileSize * scale;
        gp.obj[1].worldY = 13 * tileSize * scale;

        // Create and set up a Chest object at a specific location in the world
        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].worldX = 15 * tileSize * scale;
        gp.obj[2].worldY = 13 * tileSize * scale;
    }
}