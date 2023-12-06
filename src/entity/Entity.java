package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The Entity class represents a game entity with position, direction, speed, hitbox, and animations.
 */
public class Entity {
    // Display purpose variables
    private final int _tileSize = 16;
    private final int _scale = 3;
    public final int screenSize = _tileSize * _scale;

    // Position of the entity in the world, directions, and speed
    public int worldX, worldY;
    public int dirX, dirY;
    public int speed;

    // Hitbox of the entity
    public Rectangle hitbox;
    public boolean collisionOn = false;

    // Which direction the entity is facing (for animation)
    public String facing;

    // Entity animations
    private int _spriteCnt = 0;        // Variable responsible for the incrementation of the different sprites
    private int _spriteUpdater = 0;    // Variable responsible for the incrementation of the speed of the sprites
    private int _spriteSpeed;          // How fast the sprites change (higher spriteSpeed means slower time to change)
    private int _spriteCntMax;         // How many sprites the entity has
    private BufferedImage[] idle_up;
    public BufferedImage[] idle_down;
    private BufferedImage[] idle_right;
    public BufferedImage[] idle_left;
    private BufferedImage[] walk_up;
    public BufferedImage[] walk_down;
    private BufferedImage[] walk_right;
    public BufferedImage[] walk_left;

    /**
     * Constructor for the Entity class.
     *
     * @param x            Initial X coordinate in the world
     * @param y            Initial Y coordinate in the world
     * @param dirX         Initial X direction
     * @param dirY         Initial Y direction
     * @param speed        Speed of the entity
     * @param facing       Initial facing direction for animation
     * @param _spriteCntMax Maximum number of sprites for animations
     * @param spriteSpeed  Speed of sprite animations
     */
    public Entity(int x, int y, int dirX, int dirY, int speed, String facing, int _spriteCntMax, int spriteSpeed) {
        // Hitbox settings to set up later

        this.worldX = x;
        this.worldY = y;
        this.dirX = dirX;
        this.dirY = dirY;
        this.speed = speed;
        this.facing = facing;
        this._spriteCntMax = _spriteCntMax;
        this._spriteSpeed = spriteSpeed;

        idle_up = new BufferedImage[_spriteCntMax];
        idle_down = new BufferedImage[_spriteCntMax];
        idle_right = new BufferedImage[_spriteCntMax];
        idle_left = new BufferedImage[_spriteCntMax];
        walk_up = new BufferedImage[_spriteCntMax];
        walk_down = new BufferedImage[_spriteCntMax];
        walk_right = new BufferedImage[_spriteCntMax];
        walk_left = new BufferedImage[_spriteCntMax];
    }

    /**
     * Load textures for animations.
     *
     * @param name Name of the entity for locating textures
     */
    public void loadTextures(String name) {
        try {
            for (int i = 0; i < _spriteCntMax; i++) {
                idle_up[i] = ImageIO.read(new FileInputStream("res/entity/idle/" + name + "/up" + (i + 1) + ".png"));
                idle_down[i] = ImageIO.read(new FileInputStream("res/entity/idle/" + name + "/down" + (i + 1) + ".png"));
                idle_left[i] = ImageIO.read(new FileInputStream("res/entity/idle/" + name + "/left" + (i + 1) + ".png"));
                idle_right[i] = ImageIO.read(new FileInputStream("res/entity/idle/" + name + "/right" + (i + 1) + ".png"));

                walk_up[i] = ImageIO.read(new FileInputStream("res/entity/walk/" + name + "/up" + (i + 1) + ".png"));
                walk_down[i] = ImageIO.read(new FileInputStream("res/entity/walk/" + name + "/down" + (i + 1) + ".png"));
                walk_left[i] = ImageIO.read(new FileInputStream("res/entity/walk/" + name + "/left" + (i + 1) + ".png"));
                walk_right[i] = ImageIO.read(new FileInputStream("res/entity/walk/" + name + "/right" + (i + 1) + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draw the entity on the screen with animations.
     *
     * @param g2      Graphics2D object for drawing
     * @param screenX Screen X coordinate for drawing
     * @param screenY Screen Y coordinate for drawing
     */
    public void draw(Graphics2D g2, int screenX, int screenY) {
        BufferedImage image = null;

        if (speed == 0) { // IDLE ANIMATIONS
            for (int i = 0; i < _spriteCntMax; i++) {
                switch (facing) {
                    case "up":
                        if (_spriteCnt == i) image = idle_up[i];
                        break;
                    case "down":
                        if (_spriteCnt == i) image = idle_down[i];
                        break;
                    case "left":
                        if (_spriteCnt == i) image = idle_left[i];
                        break;
                    case "right":
                        if (_spriteCnt == i) image = idle_right[i];
                        break;
                }
            }
        }
        if (speed > 0) {  // WALKING ANIMATIONS
            for (int i = 0; i < _spriteCntMax; i++) {
                switch (facing) {
                    case "up":
                        if (_spriteCnt == i) image = walk_up[i];
                        break;
                    case "down":
                        if (_spriteCnt == i) image = walk_down[i];
                        break;
                    case "left":
                        if (_spriteCnt == i) image = walk_left[i];
                        break;
                    case "right":
                        if (_spriteCnt == i) image = walk_right[i];
                        break;
                }
            }
        }

        _spriteUpdater++;
        if (_spriteUpdater > _spriteSpeed) {
            _spriteCnt++;
            if (_spriteCnt == _spriteCntMax) {
                _spriteCnt = 0;
            }
            _spriteUpdater = 0;
        }

        g2.drawImage(image, screenX, screenY, screenSize, screenSize, null);
    }
}
