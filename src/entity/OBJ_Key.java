package entity;


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

        loadTextures("key");
    }
}