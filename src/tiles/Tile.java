/**
 * @file Tile.java
 * @brief This file contains the implementation of the Tile class, which represents a tile in the game world.
 */

package tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @class Tile
 * @brief Represents a tile in the game world.
 */
public class Tile {
    private boolean _isBlocking = false;
    private int _worldX, _worldY;

    // Display purpose variables
    private final int tileSize = 16;
    private final int scale = 3;
    public final int screenSize = tileSize * scale;

    private int _spriteCnt = 0; // Variable responsible for the incrementation of the different sprites
    private int _spriteUpdater = 0; // Variable responsible for the incrementation of the speed of the sprites
    public int spriteSpeed; // How fast are the sprites changing (higher spriteSpeed means slower time to change)
    // spriteSpeed of 0 means it has NO animation
    public int spriteCntMax; // How many sprite does the entity have

    public BufferedImage[] image;

    /**
     * Constructor for the Tile class.
     *
     * @param spriteCntMax The maximum number of sprites for the tile.
     * @param spriteSpeed  The speed of sprite animation.
     * @param isBlocking   Indicates whether the tile is blocking.
     * @param ind          The index name of the tile.
     */
    public Tile(int spriteCntMax, int spriteSpeed, boolean isBlocking, int ind) {
        this.spriteCntMax = spriteCntMax;
        this.spriteSpeed = spriteSpeed;
        this._isBlocking = isBlocking;
        image = new BufferedImage[spriteCntMax];
    }

    /**
     * Set whether the tile is blocking.
     *
     * @param collision Indicates whether the tile is blocking.
     */
    public void setCollision(boolean collision) {
        _isBlocking = collision;
    }

    /**
     * Set the texture for the tile.
     *
     * @param newTexture The new texture for the tile.
     */
    public void setTexture(BufferedImage[] newTexture) {
        this.image = newTexture;
    }

    /**
     * Set the position of the tile.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     */
    public void setPos(int x, int y) {
        this._worldX = x;
        this._worldY = y;
    }

    /**
     * Get the position of the tile.
     *
     * @return An array containing the x and y coordinates of the tile.
     */
    public int[] getPos() {
        int tmp[] = new int[2];
        tmp[0] = _worldX;
        tmp[1] = _worldY;
        return tmp;
    }

    /**
     * Get whether the tile is blocking.
     *
     * @return True if the tile is blocking, false otherwise.
     */
    public boolean getCollision() {
        return _isBlocking;
    }

    /**
     * Set the sprite count and speed for the tile.
     *
     * @param newSpriteCntMax The new maximum number of sprites for the tile.
     * @param newSpriteSpeed  The new speed of sprite animation for the tile.
     */
    public void setSpriteCountAndSpeed(int newSpriteCntMax, int newSpriteSpeed) {
        this.spriteCntMax = newSpriteCntMax;
        this.spriteSpeed = newSpriteSpeed;
    }

    /**
     * Load textures for the tile.
     *
     * @param name      The name of the tile.
     * @param animated  Indicates whether the tile has animated textures.
     * @param i         Index variable for static textures.
     */
    public void loadTextures(String name, boolean animated, int i) {
        try {
            if (animated) {
                for (int j = 0; j < spriteCntMax; j++) {
                    image[j] = ImageIO.read(new FileInputStream("res/tiles/animated/" + name + "/" + name + (j + 1) + ".png"));
                }
            } else {
                image[0] = ImageIO.read(new FileInputStream("res/tiles/static/" + name + "/" + name + (i + 1) + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Update the sprite frames for the tile.
     */
    public void updateFrames() {
        if (spriteSpeed > 0) {
            _spriteUpdater++;
            if (_spriteUpdater > spriteSpeed) {
                _spriteCnt++;
                if (_spriteCnt == spriteCntMax) {
                    _spriteCnt = 0;
                }
                _spriteUpdater = 1;
            }
        }
    }

    /**
     * Draw the tile on the screen.
     *
     * @param g2      Graphics2D object for drawing.
     * @param screenX The x-coordinate on the screen.
     * @param screenY The y-coordinate on the screen.
     */
    public void draw(Graphics2D g2, int screenX, int screenY) {
        BufferedImage render = null;

        for (int i = 0; i < spriteCntMax; i++) {
            if (_spriteCnt == i) {
                render = image[i];
            }
        }

        g2.drawImage(render, screenX, screenY, screenSize, screenSize, null);
        //g2.drawRect(screenX,screenY,Const.WRLD_tileScreenSize,Const.WRLD_tileScreenSize);   //Debugging purposes
    }

}
