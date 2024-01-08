/**
 * @file Props.java
 * @brief This file contains the implementation of the Props class, representing in-game props with properties such as image, name, and position.
 */

package entity;

import game.Const;
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
    private BufferedImage image; // Image representing the prop
    protected boolean collision = false;

    //public int hitboxDefaultX = 0;
    //public int hitboxDefaultY = 0;

    Props(int x, int y, String name, int spriteCntMax, int spriteSpeed){
        super(name,x,y,spriteCntMax,spriteSpeed);
        hitbox.width = 3*Const.WRLD_tileScreenSize/4;  //Slightly smaller than a tile
        hitbox.height = 3*Const.WRLD_tileScreenSize/4;
        hitbox.x = worldX + hitbox.width/4;
        hitbox.y = worldY + hitbox.height/4;
    }

    boolean getCollision(){
        return collision;
    }

    protected void loadTextures(String name){
        try {
            // Load the image of the key from the specified file path
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
        int playerScreenX = (Const.WDW_width - Const.WRLD_entityScreenSize) / 2;
        int playerScreenY = (Const.WDW_height - Const.WRLD_entityScreenSize) / 2;

        // Calculate the screen position of the prop relative to the player's position
        int screenX = worldX - world.player.worldX + playerScreenX;
        int screenY = worldY - world.player.worldY + playerScreenY;

        // Check if the prop is within the visible screen region around the player
        if (worldX + Const.WRLD_tileScreenSize > world.player.worldX - playerScreenX
                && worldX - Const.WRLD_tileScreenSize < world.player.worldX + playerScreenX
                && worldY + Const.WRLD_tileScreenSize > world.player.worldY - playerScreenY
                && worldY - Const.WRLD_tileScreenSize < world.player.worldY + playerScreenY) {
            // Draw the prop on the screen
            g2.drawImage(image, screenX, screenY, Const.WRLD_entityScreenSize, Const.WRLD_entityScreenSize, null);
            g2.drawRect(screenX + Const.WRLD_entityScreenSize/8, screenY + Const.WRLD_entityScreenSize/8, this.hitbox.width, this.hitbox.height);
        }
    }
}
