/**
 * @file EntitySetter.java
 * @brief This file contains the implementation of the EntitySetter class, responsible for initializing and setting up objects (entities) in the game world.
 */

package entity;

import entity.props.*;
import game.World;
import game.Const;
import java.awt.*;
import game.Attack;;

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
        Props key = new OBJ_Key(13 * Const.WRLD_entityScreenSize, 13 * Const.WRLD_entityScreenSize);
        world.addObject(new Point((int) key.worldX, (int) key.worldY), key);

        // Create and set up a Door object at a specific location in the world
        Props door = new OBJ_Door(14 * Const.WRLD_entityScreenSize, 13 * Const.WRLD_entityScreenSize);
        world.addObject(new Point((int) door.worldX, (int) door.worldY), door);

        Props door2 = new OBJ_Door( 10 * Const.WRLD_entityScreenSize, 19 * Const.WRLD_entityScreenSize);
        world.addObject(new Point((int) door2.worldX, (int) door2.worldY), door2);

        // Create and set up a Chest object at a specific location in the world
        Props chest = new OBJ_Chest(15 * Const.WRLD_entityScreenSize,13 * Const.WRLD_entityScreenSize);
        world.addObject(new Point((int) chest.worldX, (int) chest.worldY), chest);
    }

    public void setEnemies(){
        Enemy enemies[] = new Enemy[20];
        enemies[0] = new Enemy("orc", 8*Const.WRLD_entityScreenSize, 10*Const.WRLD_entityScreenSize, 0, 0, 0, "down", 4, 20);
        enemies[1] = new Enemy("orc", 16*Const.WRLD_entityScreenSize, 10*Const.WRLD_entityScreenSize, 0, 0, 0, "up", 4, 20);
        enemies[2] = new Enemy("orc", 10*Const.WRLD_entityScreenSize, 18*Const.WRLD_entityScreenSize, 0, 0, 0, "up", 4, 20); 
        //Setting up enemies with increasing difficulty
        for(int i=0;i<3;i++){
            enemies[i].setStats(42+i*26, 100+(i*37), 0, 7+i*4, 7+i*3, 5+i*3, 3+i*2, new Attack("Tabasse",20,0,true,1));
            world.addEnemy(new Point((int)enemies[i].worldX, (int)enemies[i].worldY), enemies[i]);
        }        
    }
}
