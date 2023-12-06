package entity;

import game.Scene;
import game.World;
import tiles.Tile;
import tiles.TileManager;

public abstract class Character extends Entity{

    int health;
    int mana;
    int agility;
    int strength;
    int defense;
    int initiative;

    public Character(int x, int y, int dirX, int dirY, int speed, String facing, int _spriteCntMax, int spriteSpeed){
        super(x,y,dirX,dirY,speed,facing,_spriteCntMax,spriteSpeed);
    }

    public abstract void update(World world, double dt);

    public boolean checkCollision(TileManager tileManager){

        int x= hitbox.x + worldX;
        int y= hitbox.y + worldY;
        int height= hitbox.height;
        int width= hitbox.width;
        return tileManager.getTile(x,y).getCollision() || tileManager.getTile(x+width,y).getCollision() ||
                tileManager.getTile(x,y+height).getCollision() || tileManager.getTile(x+width,y+height).getCollision();
    };
}
