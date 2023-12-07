/**
 * @file Entity.java
 * @brief This file contains the implementation of the Entity class, representing an abstract entity with position, hitbox, and animations.
 */

package entity;

import java.awt.Rectangle;

import game.Scene;

/**
 * @class Entity
 * @brief Represents an abstract entity with position, hitbox, and animations.
 */
public abstract class Entity {
    // Display purpose variables
    protected final int _tileSize = 16;
    protected final int _scale = 3;
    public final int screenSize = _tileSize * _scale;

    // Position of the entity in the world, directions, and speed
    public int worldX, worldY;

    // Hitbox of the entity
    public Rectangle hitbox;

    // Entity animations
    protected int _spriteCnt = 0; // Variable responsible for the incrementation of the different sprites
    protected int _spriteUpdater = 0; // Variable responsible for the incrementation of the speed of the sprites
    protected int _spriteSpeed; // How fast the sprites are changing (higher spriteSpeed means slower time to change)
    protected int _spriteCntMax; // How many sprites does the entity have

    /**
     * @brief Default constructor for the Entity class.
     */
    public Entity() {
        // Default constructor
    }

    /**
     * @brief Constructor for the Entity class with specified initial position and animation parameters.
     * @param x The initial X-coordinate of the entity in the world.
     * @param y The initial Y-coordinate of the entity in the world.
     * @param _spriteCntMax The maximum number of sprites for animation.
     * @param spriteSpeed The speed of sprite animation.
     */
    public Entity(int x, int y, int _spriteCntMax, int spriteSpeed) {
        this.worldX = x;
        this.worldY = y;
        this._spriteCntMax = _spriteCntMax;
        this._spriteSpeed = spriteSpeed;
    }

    /**
     * @brief Updates the entity's position based on the current scene and time elapsed.
     * @param scene The current game scene.
     * @param dt The time elapsed since the last update.
     */
    public void update(Scene scene, double dt) {
        // Updating entity position accurately (at any point in time either pressing keys or not)
    }

    // GRAPHICS

    /**
     * @brief Loads textures for the entity based on its name.
     * @param name The name used to determine the textures to load.
     */
    private void loadTextures(String name) {
        // TODO: Different texture loading from characters
    }

    /**
     * @brief Updates the frames of the entity's animation.
     */
    protected void updateFrames() {
        _spriteUpdater++;
        if (_spriteUpdater > _spriteSpeed) {
            _spriteCnt++;
            if (_spriteCnt == _spriteCntMax) {
                _spriteCnt = 0;
            }
            _spriteUpdater = 0;
        }
    }
}
