package entity;

import java.awt.*;

public class Entity {

    public int worldX, worldY;
    public int dirX,dirY;
    public int speed;

    
    public String direction;

    public int spriteCounter=0;
    public int spriteNum=1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public  boolean collisionOn = false;
}
