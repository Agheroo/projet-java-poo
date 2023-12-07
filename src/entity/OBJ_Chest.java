package entity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * The OBJ_Chest class represents a chest object that extends the Props class.
 */
public class OBJ_Chest extends Props {

    /**
     * Constructor for the OBJ_Chest class.
     * Initializes the name and loads the image for the chest.
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