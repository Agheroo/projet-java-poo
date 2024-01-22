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
        Props key = new OBJ_Key(9 * Const.WRLD_entityScreenSize, 46 * Const.WRLD_entityScreenSize);
        world.addObject(new Point((int) key.worldX, (int) key.worldY), key);

        // Create and set up a Door object at a specific location in the world
        Props door = new OBJ_Door(29 * Const.WRLD_entityScreenSize, 39 * Const.WRLD_entityScreenSize);
        world.addObject(new Point((int) door.worldX, (int) door.worldY), door);

        // Create and set up a Chest object at a specific location in the world
        Props chest = new OBJ_Chest(50 * Const.WRLD_entityScreenSize, 42 * Const.WRLD_entityScreenSize);
        world.addObject(new Point((int) chest.worldX, (int) chest.worldY), chest);

        Props chest2 = new OBJ_Chest(9 * Const.WRLD_entityScreenSize, 40 * Const.WRLD_entityScreenSize);
        world.addObject(new Point((int) chest2.worldX, (int) chest2.worldY), chest);
    }

    public void setEnemies(){
        int nbEnemies = 11;
        Enemy enemies[] = new Enemy[nbEnemies];
        //Enemies on the left when you spawn
        enemies[0] = new Enemy("orc", 16*Const.WRLD_entityScreenSize, 50*Const.WRLD_entityScreenSize, 0, 0, 0, "down", 4, 20);
        enemies[1] = new Enemy("orc", 14*Const.WRLD_entityScreenSize, 47*Const.WRLD_entityScreenSize, 0, 0, 0, "down", 4, 20);
        enemies[2] = new Enemy("orc", 19*Const.WRLD_entityScreenSize, 45*Const.WRLD_entityScreenSize, 0, 0, 0, "up", 4, 20);
        enemies[3] = new Enemy("orc", 12*Const.WRLD_entityScreenSize, 44*Const.WRLD_entityScreenSize, 0, 0, 0, "right", 4, 20);
        enemies[4] = new Enemy("orc", 9*Const.WRLD_entityScreenSize, 41*Const.WRLD_entityScreenSize, 0, 0, 0, "up", 4, 20);
        
        
        //Enemies on the right when you spawn
        enemies[5] = new Enemy("orc", 41*Const.WRLD_entityScreenSize, 47*Const.WRLD_entityScreenSize, 0, 0, 0, "left", 4, 20);
        enemies[6] = new Enemy("orc", 46*Const.WRLD_entityScreenSize, 52*Const.WRLD_entityScreenSize, 0, 0, 0, "left", 4, 20);
        enemies[7] = new Enemy("orc", 44*Const.WRLD_entityScreenSize, 44*Const.WRLD_entityScreenSize, 0, 0, 0, "up", 4, 20);
        enemies[8] = new Enemy("orc", 51*Const.WRLD_entityScreenSize, 43*Const.WRLD_entityScreenSize, 0, 0, 0, "up", 4, 20);

        
        //Enemies past the door
        enemies[9] = new Enemy("orc", 17*Const.WRLD_entityScreenSize, 33*Const.WRLD_entityScreenSize, 0, 0, 0, "down", 4, 20);
        enemies[10] = new Enemy("orc", 15*Const.WRLD_entityScreenSize, 26*Const.WRLD_entityScreenSize, 0, 0, 0, "down", 4, 20);
        
        //Setting up enemies with increasing difficulty
        for(int i=0;i<nbEnemies;i++){
            enemies[i].setStats(37+3*(int)Math.sqrt(i*56), 200+(int)(i*65*Math.log(6*(i+1))), 0, 7+(int)Math.sqrt(i*6), (8+(int)Math.sqrt(i*2)), (3+i*5), 3+(int)Math.log((i+1)*3), new Attack("Tabasse", 7+i*(int)Math.log((i+1)*3),0,true,1));
            world.addEnemy(new Point((int)enemies[i].worldX, (int)enemies[i].worldY), enemies[i]);
        }        
    }
}
