package entity;

import game.Scene;

/**
 * The Player class represents a player entity that extends the Entity class.
 * It includes additional functionality specific to the player, such as key handling and movement updates.
 */
public class Player extends Entity {
    int hasKey = 0; // Indicates whether the player has a key

    /**
     * Constructor for the Player class.
     *
     * @param worldX        Initial X coordinate in the world
     * @param worldY        Initial Y coordinate in the world
     * @param dirX          Initial X direction
     * @param dirY          Initial Y direction
     * @param speed         Speed of the player
     * @param facing        Initial facing direction for animation
     * @param spriteCntMax  Maximum number of sprites for animations
     * @param spriteSpeed   Speed of sprite animations
     */
    public Player(int worldX, int worldY, int dirX, int dirY, int speed, String facing, int spriteCntMax, int spriteSpeed) {
        super(worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);  // Calls the parent class for entity setup

        loadTextures("player"); // Load player-specific textures
    }

    /**
     * Update method for the Player class.
     *
     * @param scene The current game scene
     * @param dt    Time elapsed since the last update
     */
    public void update(Scene scene, double dt) {
        // Check if movement keys are pressed
        if (scene.keyH.upPressed || scene.keyH.downPressed || scene.keyH.leftPressed || scene.keyH.rightPressed) {
            if (speed < 30) { // Acceleration
                speed += 20 * dt;
            }
            if (speed > 30) {
                speed = 30;
            }
            dirX = dirY = 0;

            // Handle key presses and update facing direction
            if (scene.keyH.leftPressed) {
                dirX = -1;
                facing = "left";
            }
            if (scene.keyH.rightPressed) {
                dirX = 1;
                facing = "right";
            }
            if (scene.keyH.upPressed) {
                dirY = -1;
                facing = "up";
            }
            if (scene.keyH.downPressed) {
                dirY = 1;
                facing = "down";
            }
        } else { // Deceleration system (use of dirX & dirY instead of facing in case of diagonal movements)
            if (speed > 0) {
                speed -= dt;
            } else {
                speed = 0;
            }
        }

        // Updating player position accurately (at any point in time either pressing keys or not)
        if (dirX == -1) {
            worldX -= speed * dt;
        }
        if (dirX == 1) {
            worldX += speed * dt;
        }
        if (dirY == -1) {
            worldY -= speed * dt;
        }
        if (dirY == 1) {
            worldY += speed * dt;
        }
    }
}
