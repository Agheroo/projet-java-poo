/**
 * @file OBJ_Door.java
 * @brief This file contains the implementation of the OBJ_Door class, representing a door object extending the Props class.
 */

package entity.props;


import entity.Player;

/**
 * @class OBJ_Door
 * @extends Props
 * @brief Represents a door object in the game.
 */
public class OBJ_Door extends Props {

    /**
     * @brief Constructor for the OBJ_Door class.
     * @details Initializes the name of the door and loads its image from a resource file.
     */
    public OBJ_Door(int worldX, int worldY) {
        super(worldX, worldY,"door",1,0, true);

        loadTextures("door");
        collision = true;
    }
    public void playerInterraction(Player p) {
        if(p.hasKey > 0 && collision == true) {
            
            p.hasKey--;

            // TODO : Change door collision; instead of destroying self
            destroySelf();
        }
    }

}
