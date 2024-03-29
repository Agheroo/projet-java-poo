/**
 * @file Entity.java
 * @brief This file contains the implementation of the Entity class, representing an abstract entity with position, hitbox, and animations.
 */

package entity;

import java.awt.Rectangle;


/**
 * @class Entity
 * @brief Represents an abstract entity with position, hitbox, and animations.
 */
public abstract class Entity {
    // Position of the entity in the world, directions, and speed
    public int worldX, worldY;
    protected boolean collision;
    // Hitbox of the entity
    public Rectangle hitbox = new Rectangle();

    // Entity animations
    protected int _spriteCnt = 0; // Variable responsible for the incrementation of the different sprites
    protected int _spriteUpdater = 0; // Variable responsible for the incrementation of the speed of the sprites
    protected int _spriteSpeed; // How fast the sprites are changing (higher spriteSpeed means slower time to change)
    protected int _spriteCntMax; // How many sprites does the entity have

    public String name; //Name of the current entity

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
    public Entity(String entityName, int x, int y, int _spriteCntMax, int spriteSpeed, boolean collision) {
        this.collision = collision;
        this.worldX = x;
        this.worldY = y;
        this.hitbox.x = worldX;
        this.hitbox.y = worldY;
        this._spriteCntMax = _spriteCntMax;
        this._spriteSpeed = spriteSpeed;
        this.name = entityName;
    }

    // GRAPHICS
    /**
     * @brief Updates the frames of the entity's animation.
     */
    public void updateFrames() {
        _spriteUpdater++;
        if (_spriteUpdater > _spriteSpeed) {
            _spriteCnt++;
            if (_spriteCnt == _spriteCntMax) {
                _spriteCnt = 0;
            }
            _spriteUpdater = 0;
        }
    }

    public abstract void playerInterraction(Player player);
}
