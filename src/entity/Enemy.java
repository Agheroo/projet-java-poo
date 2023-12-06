package entity;

import game.World;

public class Enemy extends Character{

    private int xpRate;

    public Enemy(int worldX,int worldY,int dirX,int dirY,int speed,String facing, int spriteCntMax, int spriteSpeed){
        super(worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);  //Calls the parent class as for every entity setup but need to specify scene.keyH for player

        loadTextures("enemy");
    }

    @Override
    public void update(World world, double dt) {

    }
}
