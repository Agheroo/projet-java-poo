/**
 * @file OBJ_Chest.java
 * @brief This file contains the implementation of the OBJ_Chest class, which represents a chest object extending the Props class.
 */

package entity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

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
    public OBJ_Chest() {
        // Set the name of the chest object
        name = "Chest";

        try {
            // Load the image for the chest from the resources
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/chest.png")));
        } catch (IOException e) {
            // Print the stack trace in case of an exception during image loading
            e.printStackTrace();
        }
    }
}
