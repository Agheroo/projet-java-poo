/**
 * @file OBJ_Chest.java
 * @brief This file contains the implementation of the OBJ_Chest class, which represents a chest object extending the Props class.
 */

package entity.props;


import entity.Player;
import game.Scene;
import item.Generator;
import item.Item;

/**
 * @class OBJ_Chest
 * @extends Props
 * @brief Represents a chest object.
 */
public class OBJ_Chest extends Props {
    /**
     * @brief Constructor for the OBJ_Chest class.
     * @details Initializes the name and loads the image for the chest.
     */
    public OBJ_Chest(int worldX, int worldY){
        super(worldX,worldY,"chest",2,0,true);
        open = false;
        loadTextures("chest");
    }
    public void playerInterraction(Player player) {
        if(open == false && Scene.keyH.interactPressed){
            open = true;
            Item item = Generator.generateItem();
            player.addItem(item);
        }
    }
}
