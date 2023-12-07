package entity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * The OBJ_Key class represents a key object that extends the Props class.
 * It initializes the key's name and loads its image from a file.
 */
public class OBJ_Key extends Props {

    /**
     * Constructor for the OBJ_Key class.
     * Initializes the name of the key and loads its image from a file.
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