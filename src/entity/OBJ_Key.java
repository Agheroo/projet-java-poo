/**
 * @file OBJ_Key.java
 * @brief This file contains the implementation of the OBJ_Key class, representing a key object extending the Props class.
 */

package entity;


/**
 * @class OBJ_Key
 * @extends Props
 * @brief Represents a key object in the game.
 */
public class OBJ_Key extends Props {

    /**
     * @brief Constructor for the OBJ_Key class.
     * @details Initializes the name of the key and loads its image from a file.
     */
    public OBJ_Key(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
        // Set the name of the key
        name = "Key";

        loadTextures("key");
    }
    public void interagitAvec(Player p) {

        p.hasKey++;
        // TODO : enlever la clé de la map
        System.out.println("Key:"+p.hasKey);
    }
}
