package entity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * The OBJ_Door class represents a specific type of game object, a door, extending the Props class.
 */
public class OBJ_Door extends Props {

    /**
     * Constructor for the OBJ_Door class.
     * Initializes the name of the door and loads its image from a resource file.
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