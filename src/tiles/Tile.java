package tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The Tile class represents a game tile with properties such as collision, position, and animations.
 */
public class Tile {
    public boolean collision = false;
    private int _worldX, _worldY;

    // Display purpose variables
    private final int tileSize = 16;
    private final int scale = 3;
    private final int screenSize = tileSize * scale;

    private int _spriteCnt = 0;        // Variable responsible for the incrementation of the different sprites
    private int _spriteUpdater = 0;    // Variable responsible for the incrementation of the speed of the sprites
    public int spriteSpeed;            // How fast the sprites change (higher spriteSpeed means slower time to change)  // spriteSpeed of 0 means it has NO animation
    public int spriteCntMax;           // How many sprites does the tile have

    public BufferedImage[] image;

    /**
     * Constructor for the Tile class.
     *
     * @param spriteCntMax Maximum number of sprites for animations
     * @param spriteSpeed  Speed of sprite animations
     */
    public Tile(int spriteCntMax, int spriteSpeed) {
        this.spriteCntMax = spriteCntMax;
        this.spriteSpeed = spriteSpeed;
        image = new BufferedImage[spriteCntMax];
    }

    /**
     * Set the texture for the tile.
     *
     * @param newTexture Array of BufferedImages representing the tile's texture
     */
    public void setTexture(BufferedImage[] newTexture) {
        this.image = newTexture;
    }

    /**
     * Set the position of the tile.
     *
     * @param x X coordinate in the world
     * @param y Y coordinate in the world
     */
    public void setPos(int x, int y) {
        this._worldX = x;
        this._worldY = y;
    }

    /**
     * Get the position of the tile.
     *
     * @return An array containing the X and Y coordinates of the tile
     */
    public int[] getPos() {
        int tmp[] = new int[2];
        tmp[0] = _worldX;
        tmp[1] = _worldY;

        return tmp;
    }

    /**
     * Set the sprite count and speed for animations.
     *
     * @param newSpriteCntMax New maximum number of sprites for animations
     * @param newSpriteSpeed  New speed of sprite animations
     */
    public void setSpriteCountAndSpeed(int newSpriteCntMax, int newSpriteSpeed) {
        this.spriteCntMax = newSpriteCntMax;
        this.spriteSpeed = newSpriteSpeed;
    }

    /**
     * Load textures for animations or static display.
     *
     * @param name     Name of the tile for locating textures
     * @param animated True if the tile has animated textures, false for static textures
     * @param i        Index for static textures (ignored for animated textures)
     */
    public void loadTextures(String name, boolean animated, int i) {
        try {
            if (animated) {
                for (int j = 0; j < spriteCntMax; j++) {
                    image[j] = ImageIO.read(new FileInputStream("res/tiles/animated/" + name + (j + 1) + ".png"));
                }
            } else {
                image[0] = ImageIO.read(new FileInputStream("res/tiles/static/" + name + (i + 1) + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draw the tile on the screen with animations.
     *
     * @param g2      Graphics2D object for drawing
     * @param screenX Screen X coordinate for drawing
     * @param screenY Screen Y coordinate for drawing
     */
    public void draw(Graphics2D g2, int screenX, int screenY) {
        BufferedImage render = null;

        for (int i = 0; i < spriteCntMax; i++) {
            if (_spriteCnt == i) {
                render = image[i];
            }
        }

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

        g2.drawImage(render, screenX, screenY, screenSize, screenSize, null);
    }
}
