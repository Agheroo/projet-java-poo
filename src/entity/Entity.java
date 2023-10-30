package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    //Position of entity in the world, directions and speed
    public int worldX, worldY;
    public int dirX,dirY;
    public int speed;
    public final int tileSize=16;
    public final int scale = 3;
    public final int screenSize = tileSize*scale;
    
    //Which direction is the entity facing (if directions are available) for animation
    public String facing;

    //Entity animations
    public int spriteUpdater=0;
    public int spriteNum=4;
    public BufferedImage[] idle_up = new BufferedImage[spriteNum]; public BufferedImage[] idle_down = new BufferedImage[spriteNum];
    public BufferedImage[] idle_right = new BufferedImage[spriteNum]; public BufferedImage[] idle_left = new BufferedImage[spriteNum];
    public BufferedImage[] walk_up = new BufferedImage[spriteNum]; public BufferedImage[] walk_down = new BufferedImage[spriteNum];
    public BufferedImage[] walk_right = new BufferedImage[spriteNum]; public BufferedImage[] walk_left = new BufferedImage[spriteNum];


    //Hitbox of entity
    public Rectangle hitbox;
    public boolean collisionOn = false;

    public Entity(int x,int y,int dirX,int dirY,int speed,String facing,int spriteUpdater,int spriteNum){
        hitbox = new Rectangle();
        hitbox.x = x+8;  hitbox.y = y+8; //hitbox is starting from top left = worldX worldY coords of entity
        //hitbox.width = tileSize*scale;


        this.worldX = x; this.worldY = y;
        this.dirX = dirX; this.dirY = dirY;
        this.speed = speed;
        this.facing = facing;
        this.spriteUpdater = spriteUpdater; this.spriteNum = spriteNum;
    }
}
