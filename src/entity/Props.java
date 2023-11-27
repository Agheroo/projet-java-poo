package entity;

import game.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Props {
    public BufferedImage image;
    public String name;

    public int worldX, worldY;
    public final int screenWidth=800;
    public final int screenHeight=600;


    public void draw(Graphics2D g2, World world) {
        int playerScreenX = (screenWidth - world.player.screenSize)/2;
        int playerScreenY = (screenHeight - world.player.screenSize)/2;
        int screenX = worldX - world.player.worldX + playerScreenX;
        int screenY = worldY - world.player.worldY + playerScreenY;
        int tileSize = 16;
        int scale = 6;
        if(worldX + tileSize * scale > world.player.worldX - playerScreenX
                && worldX - tileSize * scale < world.player.worldX + playerScreenX
                && worldY + tileSize * scale > world.player.worldY - playerScreenY
                && worldY - tileSize * scale < world.player.worldY + playerScreenY){
            g2.drawImage(image, screenX, screenY,world.player.screenSize, world.player.screenSize, null);

        }


    }
}
