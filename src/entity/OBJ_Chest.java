package entity;


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

        loadTextures("chest");
    }
}