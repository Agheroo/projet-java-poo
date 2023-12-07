/**
 * @file OBJ_Door.java
 * @brief This file contains the implementation of the OBJ_Door class, representing a door object extending the Props class.
 */

package entity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

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
    public OBJ_Door() {
        // Set the name of the door
        name = "Door";

        try {
            // Load the image of the door from the resource file
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/door.png")));
        } catch (IOException e) {
            // Print stack trace in case of an IOException during image loading
            e.printStackTrace();
        }
    }
}
