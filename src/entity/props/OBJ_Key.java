/**
 * @file OBJ_Key.java
 * @brief This file contains the implementation of the OBJ_Key class, representing a key object extending the Props class.
 */

package entity.props;


import entity.Player;

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
        super(worldX, worldY,"key",1,0,false);

        loadTextures("key");
    }
    public void playerInterraction(Player p) {

        p.hasKey++;
        System.out.println("Key:"+p.hasKey);

        destroySelf();
    }
}
