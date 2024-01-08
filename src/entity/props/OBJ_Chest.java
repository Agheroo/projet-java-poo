/**
 * @file OBJ_Chest.java
 * @brief This file contains the implementation of the OBJ_Chest class, which represents a chest object extending the Props class.
 */

package entity.props;


import entity.Player;
import entity.props.Props;
import game.World;
import item.Generator;
import item.Item;

import java.awt.*;

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
        super(worldX,worldY);
        // Set the name of the chest object
        name = "Chest";

        loadTextures("chest");
        collision = true;
    }
    public void interagitAvec(Player p) {
        Item item= Generator.generateItem();
        if(true){
            p.addItem(item);
        }
        System.out.println("Chest interaction");

        destructor();

    }
}
