package entity;

import game.World;

/**
 * The Ennemy class represents an enemy entity in the game.
 */
public class Ennemy extends Character{
    private int xpRate;

    public Ennemy(int worldX,int worldY,int dirX,int dirY,int speed,String facing, int spriteCntMax, int spriteSpeed){
        super(worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);  //Calls the parent class as for every entity setup but need to specify scene.keyH for player

        loadTextures("enemy");
    }

    @Override
    public void update(World world, double dt) {

    }
}
