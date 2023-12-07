package entity;


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

        loadTextures("door");
    }
}