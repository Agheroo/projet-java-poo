/**
 * @file OBJ_Key.java
 * @brief This file contains the implementation of the OBJ_Key class, representing a key object extending the Props class.
 */

package entity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

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
    public OBJ_Key() {
        // Set the name of the key
        name = "Key";

        try {
            // Load the image of the key from the specified file path
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/key.png")));
        } catch (IOException e) {
            // Print the stack trace in case of an IOException during image loading
            e.printStackTrace();
        }
    }
}
