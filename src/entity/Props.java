/**
 * @file Props.java
 * @brief This file contains the implementation of the Props class, representing in-game props with properties such as image, name, and position.
 */

package entity;

import game.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @class Props
 * @extends Entity
 * @brief Represents in-game props with properties such as image, name, and position.
 */
public abstract class Props extends Entity {
    public BufferedImage image; // Image representing the prop
    public String name; // Name of the prop

    public int hitboxDefaultX = 0;
    public int hitboxDefaultY = 0;
    public final int screenWidth = 800; // Screen width of the game window
    public final int screenHeight = 600; // Screen height of the game window

    protected void loadTextures(String name){
        try {
            // Load the image of the key from the specified file path
            //image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/object/key.png")));
            image = ImageIO.read(new FileInputStream("res/object/"+name+".png"));
        } catch (IOException e) {
            // Print the stack trace in case of an IOException during image loading
            e.printStackTrace();
        }
    }

    /**
     * @brief Draw the prop on the screen based on its position relative to the player's position.
     * @param g2 Graphics2D object for drawing.
     * @param world World object containing information about the game world.
     */
    public void draw(Graphics2D g2, World world) {
        // Calculate the screen position of the player
        int playerScreenX = (screenWidth - world.player.screenSize) / 2;
        int playerScreenY = (screenHeight - world.player.screenSize) / 2;

        // Calculate the screen position of the prop relative to the player's position
        int screenX = worldX - world.player.worldX + playerScreenX;
        int screenY = worldY - world.player.worldY + playerScreenY;

        // Check if the prop is within the visible screen region around the player
        if (worldX + _tileSize * _scale > world.player.worldX - playerScreenX
                && worldX - _tileSize * _scale < world.player.worldX + playerScreenX
                && worldY + _tileSize * _scale > world.player.worldY - playerScreenY
                && worldY - _tileSize * _scale < world.player.worldY + playerScreenY) {
            // Draw the prop on the screen
            g2.drawImage(image, screenX, screenY, world.player.screenSize, world.player.screenSize, null);
        }
    }
}
