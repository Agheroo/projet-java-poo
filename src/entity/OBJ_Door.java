/**
 * @file OBJ_Door.java
 * @brief This file contains the implementation of the OBJ_Door class, representing a door object extending the Props class.
 */

package entity;


import game.World; /**
 * @class OBJ_Door
 * @extends Props
 * @brief Represents a door object in the game.
 */
public class OBJ_Door extends Props {

    /**
     * @brief Constructor for the OBJ_Door class.
     * @details Initializes the name of the door and loads its image from a resource file.
     */
    public OBJ_Door() {
        // Set the name of the door
        name = "Door";

        loadTextures("door");
        collision = true;
    }
    public void interagitAvec(Player p) {
        if(p.hasKey > 0 ) {
            // TODO : enlever la porte de la map
            p.hasKey--;
        }
        System.out.println("Key:"+p.hasKey);

    }

}
