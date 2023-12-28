/**
 * @file World.java
 * @brief This file contains the implementation of the World class, responsible for managing the game world.
 */

package game;

import entity.Player;
import tiles.TileManager;
import entity.EntitySetter;
import entity.Props;
import java.awt.*;
import java.util.HashMap;

/**
 * @class World
 * @extends Scene
 * @brief Represents the game world and manages its entities.
 */
public class World extends Scene {

    private static World _instance;
    public TileManager tileManager = new TileManager(this);

    // World initialization settings
    public final int maxRow = 27, maxCol = 27; // DONT FORGET TO MODIFY WHEN CHANGING THE MAP !!!

    public EntitySetter aSetter = new EntitySetter(this); // Instance of EntitySetter
    // Player settings
    public HashMap<Point, Props> objMap = new HashMap<>(); // HashMap to store objects with coordinates
    // TODO : https://www.geeksforgeeks.org/java-util-dictionary-class-java/
    public Player player = new Player(15 * tileManager.tileSize * tileManager.scale,
            15 * tileManager.tileSize * tileManager.scale, 0, 0, 0, "down", 4, 20);

    /**
     * Gets the instance of the World.
     *
     * @return The World instance.
     */
    public static World getWorld() {
        if (_instance == null) {
            _instance = new World();
            _instance.state = State.WORLD;
        }
        return _instance;
    }

    private World() {
    }

    /**
     * Set up the initial state of the game.
     */
    public void setupGame() {
        aSetter.setObject();
    }

    /**
     * Adds an object to the HashMap with the specified coordinates.
     *
     * @param coordinates The coordinates of the object.
     * @param object      The object to be added.
     */
    public void addObject(Point coordinates, Props object) {
        objMap.put(coordinates, object);
    }

    /**
     * Updates the game world based on the scene state.
     */
    public void update() {
        checkSceneChange();
        if (state == State.WORLD) {
            int playerScreenX = (800 - player.screenSize) / 2;
            int playerScreenY = (600 - player.screenSize) / 2;

            if (player.worldX + tileManager.tileScreenSize > player.worldX - playerScreenX // Do all the world updates if they are actually visible on the screen (or near)
                    && player.worldX - tileManager.tileScreenSize < player.worldX + playerScreenX
                    && player.worldY + tileManager.tileScreenSize > player.worldY - playerScreenY
                    && player.worldY - tileManager.tileScreenSize < player.worldY + playerScreenY) {

                player.update(this, dt);
                tileManager.update(this, 800, 600);

                // otherentity.update(this,dt);
            }

            // Checks if player is touching the edges of the map
            // TODO: collision checker in entity class of Akim's branch
            if (player.worldX + player.hitbox.width / 2 < 0) {
                player.worldX = 0 - player.hitbox.width / 2;
            }
            if (player.worldX - player.hitbox.width / 2 >= (maxCol - 1) * tileManager.tileSize * tileManager.scale) {
                player.worldX = (maxCol - 1) * tileManager.tileSize * tileManager.scale + player.hitbox.width / 2;
            }
            if (player.worldY + player.hitbox.height / 2 < 0) {
                player.worldY = 0 - player.hitbox.height / 2;
            }
            if (player.worldY - player.hitbox.height / 2 >= (maxRow - 1) * tileManager.tileSize * tileManager.scale) {
                player.worldY = (maxRow - 1) * tileManager.tileSize * tileManager.scale + player.hitbox.height / 2;
            }
        }
    }

    /**
     * Draws the game world and its entities.
     *
     * @param g2           The Graphics2D object for drawing.
     * @param screenWidth  The width of the screen.
     * @param screenHeight The height of the screen.
     */
    public void draw(Graphics2D g2, int screenWidth, int screenHeight) {
        // TILE
        tileManager.draw(g2, this, screenWidth, screenHeight);

        // OBJECT
        for (Props props : objMap.values()) {
            props.draw(g2, this);
        }

        // PLAYER
        player.drawInWorld(g2, screenWidth / 2 - (player.screenSize / 2),
                screenHeight / 2 - (player.screenSize / 2)); // Player is always centered to screen
    }

}
