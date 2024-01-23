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
    private Props doors[];
    private Props keys[];
    private Props chests[];
    private Props allProps[];
    private int nbKeys;
    private int nbChests;
    private int nbDoors;

    /**
     * @brief Constructor for the EntitySetter class.
     * @param world The World object representing the game world.
     */
    public EntitySetter(World world) {
        this.world = world;
        nbKeys = 2;
        nbChests = 2;
        nbDoors = 2;
        doors = new Props[nbDoors];
        keys = new Props[nbKeys];
        chests = new Props[nbChests];
        allProps = new Props[nbDoors + nbKeys + nbChests];


        keys[0] = new OBJ_Key(9 * Const.WRLD_entityScreenSize, 46 * Const.WRLD_entityScreenSize);
        keys[1] = new OBJ_Key(38 * Const.WRLD_entityScreenSize, 24 * Const.WRLD_entityScreenSize);

        doors[0] = new OBJ_Door(29 * Const.WRLD_entityScreenSize, 39 * Const.WRLD_entityScreenSize);
        doors[1] = new OBJ_Door(27 * Const.WRLD_entityScreenSize, 23 * Const.WRLD_entityScreenSize);

        chests[0] = new OBJ_Chest(100 * Const.WRLD_entityScreenSize, 100 * Const.WRLD_entityScreenSize);
        chests[1] = new OBJ_Chest(101 * Const.WRLD_entityScreenSize, 101 * Const.WRLD_entityScreenSize);

        for(int i = 0;i<nbKeys;i++){
            allProps[i] = keys[i];
        }
        for(int i=nbKeys;i<nbDoors+nbKeys;i++){
            allProps[i] = doors[i-nbKeys];
        }
        for(int i = nbDoors + nbKeys; i<nbChests + nbKeys + nbDoors;i++){
            allProps[i] = chests[i-(nbDoors + nbKeys)];
        }
    }

    /**
     * @brief Method to set up objects in the game world, such as keys, doors, and chests.
     */
    public void setObject() {
        for(int i=0; i < nbChests + nbChests + nbDoors ;i++){
            world.addObject(new Point((int) allProps[i].worldX, (int) allProps[i].worldY), allProps[i]);
        }

    }

    public void setEnemies(){
        int nbEnemies = 26;
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

        
        //Enemies past the 1st door
        enemies[9] = new Enemy("demon", 29*Const.WRLD_entityScreenSize, 38*Const.WRLD_entityScreenSize, 0, 0, 0, "down", 4, 20);
        enemies[10] = new Enemy("demon", 22*Const.WRLD_entityScreenSize, 37*Const.WRLD_entityScreenSize, 0, 0, 0, "right", 4, 20);
        enemies[11] = new Enemy("demon", 35*Const.WRLD_entityScreenSize, 33*Const.WRLD_entityScreenSize, 0, 0, 0, "left", 4, 20);
        enemies[12] = new Enemy("demon", 48*Const.WRLD_entityScreenSize, 30*Const.WRLD_entityScreenSize, 0, 0, 0, "left", 4, 20);
        enemies[13] = new Enemy("demon", 37*Const.WRLD_entityScreenSize, 25*Const.WRLD_entityScreenSize, 0, 0, 0, "down", 4, 20);
        enemies[14] = new Enemy("demon", 38*Const.WRLD_entityScreenSize, 25*Const.WRLD_entityScreenSize, 0, 0, 0, "down", 4, 20);
        enemies[15] = new Enemy("demon", 14*Const.WRLD_entityScreenSize, 25*Const.WRLD_entityScreenSize, 0, 0, 0, "right", 4, 20);


        //Enemies past 2nd door
        enemies[16] =  new Enemy("dragon", 27*Const.WRLD_entityScreenSize, 22*Const.WRLD_entityScreenSize, 0, 0, 0, "down", 4, 20);
        enemies[17] =  new Enemy("dragon", 22*Const.WRLD_entityScreenSize, 16*Const.WRLD_entityScreenSize, 0, 0, 0, "up", 4, 20);
        enemies[18] =  new Enemy("dragon", 15*Const.WRLD_entityScreenSize, 11*Const.WRLD_entityScreenSize, 0, 0, 0, "right", 4, 20);
        enemies[19] =  new Enemy("dragon", 13*Const.WRLD_entityScreenSize, 8*Const.WRLD_entityScreenSize, 0, 0, 0, "right", 4, 20);
        enemies[20] =  new Enemy("dragon", 17*Const.WRLD_entityScreenSize, 6*Const.WRLD_entityScreenSize, 0, 0, 0, "down", 4, 20);        
        enemies[21] =  new Enemy("dragon", 25*Const.WRLD_entityScreenSize, 9*Const.WRLD_entityScreenSize, 0, 0, 0, "down", 4, 20);
        enemies[22] =  new Enemy("dragon", 37*Const.WRLD_entityScreenSize, 5*Const.WRLD_entityScreenSize, 0, 0, 0, "down", 4, 20);
        enemies[23] =  new Enemy("dragon", 43*Const.WRLD_entityScreenSize, 15*Const.WRLD_entityScreenSize, 0, 0, 0, "left", 4, 20);
        enemies[24] =  new Enemy("dragon", 54*Const.WRLD_entityScreenSize, 14*Const.WRLD_entityScreenSize, 0, 0, 0, "right", 4, 20);
        enemies[25] =  new Enemy("dragon", 52*Const.WRLD_entityScreenSize, 18*Const.WRLD_entityScreenSize, 0, 0, 0, "right", 4, 20);

        //Setting up enemies with increasing difficulty
        for(int i=0;i<nbEnemies;i++){
            enemies[i].setStats(37+3*(int)Math.sqrt(i*56), 200+(int)(i*65*Math.log(6*(i+1))), 0, 7+(int)Math.sqrt(i*6), (8+(int)Math.sqrt(i*2)), (3+i), 3+(int)Math.log((i+1)*3), new Attack("Tabasse", 7+i*(int)Math.log((i+1)*3),0,true,1));
            world.addEnemy(new Point((int)enemies[i].worldX, (int)enemies[i].worldY), enemies[i]);
        }



    }
}
