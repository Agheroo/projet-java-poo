package entity;

import game.Scene;
import game.World;
import tiles.Tile;
import tiles.TileManager;
import game.Scene.State;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Character extends Entity{
    int health;
    int mana;
    int agility;
    int strength;
    int defense;
    int initiative;

    public int speed;
    
    //Hitbox of entity
    public Rectangle hitbox;

    //Which direction is the entity facing (if directions are available) for animation
    public String facing;


    //Character animations
    private BufferedImage[] _idle_up; public BufferedImage[] _idle_down;
    private BufferedImage[] _idle_right; public BufferedImage[] _idle_left;
    private BufferedImage[] _walk_up; public BufferedImage[] _walk_down;
    private BufferedImage[] _walk_right; public BufferedImage[] _walk_left;


    public Character(int x, int y, int dirX, int dirY, int speed, String facing, int _spriteCntMax, int spriteSpeed){
        super(x,y,_spriteCntMax,spriteSpeed);

        hitbox = new Rectangle();
        //Hitbox settings to set up later
        hitbox.x = x; hitbox.y = y;
        hitbox.width = screenSize/2; hitbox.height = screenSize/2;

        
        this.dirX = dirX; this.dirY = dirY;
        this.speed = speed;
        this.facing = facing;

        _idle_up = new BufferedImage[_spriteCntMax]; _idle_down = new BufferedImage[_spriteCntMax];
        _idle_right = new BufferedImage[_spriteCntMax]; _idle_left = new BufferedImage[_spriteCntMax];
        _walk_up = new BufferedImage[_spriteCntMax]; _walk_down = new BufferedImage[_spriteCntMax];
        _walk_right = new BufferedImage[_spriteCntMax]; _walk_left = new BufferedImage[_spriteCntMax];
    }

    public void update(Scene scene, double dt){
        if(scene.state == State.WORLD){
            World currWorld = World.getWorld();
            
            checkNearTiles(currWorld.tileManager);
            hitbox.x = worldX + hitbox.width/2;
            hitbox.y = worldY + hitbox.height/2;
            updateFrames();
        }
    }

    private void checkNearTiles(TileManager tileManager){
        if(isBlocked(tileManager.getTile(worldX - hitbox.width + tileManager.tileScreenSize, worldY))){     //Tile on the right
            worldX = tileManager.getTile(worldX - hitbox.width + tileManager.tileScreenSize, worldY).getPos()[0] - hitbox.width;
        }
        if(isBlocked(tileManager.getTile(worldX+hitbox.width, worldY))){                                    //Tile on the left
            worldX = tileManager.getTile(worldX+hitbox.width, worldY).getPos()[0] + hitbox.width;
        }
        if(isBlocked(tileManager.getTile(worldX, worldY - hitbox.height  + tileManager.tileScreenSize))){   //Tile on the bottom
            System.out.println(tileManager.getTile(worldX, worldY - hitbox.height + tileManager.tileScreenSize).indexName);
        }
        if(isBlocked(tileManager.getTile(worldX, worldY + hitbox.height - tileManager.tileScreenSize))){    //Tile on the top

        }
    }

    public boolean isBlocked(Tile tile){
        return tile.getCollision();
    };

    protected void move(World world, int speed, double dt){
        if((dirX == 0 && dirY != 0) || (dirY == 0 && dirX != 0)){
            if(dirX == 1){
                worldX += speed*dt;
            }
            if(dirX == -1){
                worldX -= speed*dt;
            }
            if(dirY == 1){
                worldY += speed*dt;
            }
            if(dirY == -1){
                worldY -= speed*dt;
            }
        }
        if(dirX != 0 && dirY != 0){
            double normSum = Math.sqrt(dirX*dirX + dirY*dirY);
            worldX += (dirX/normSum)*speed*dt;
            worldY += (dirY/normSum)*speed*dt;
        }
    }
    
    protected void accelerate(int maxSpeed, double dt){
        if(speed < maxSpeed){
            speed += 20*dt;
        }
        if(speed>maxSpeed){
            speed=maxSpeed;
        }
    }

    protected void decelerate(double dt){
        speed -= dt;
    }

    protected void loadTextures(String name){
        try {
            for(int i=0; i<_spriteCntMax ;i++){
                _idle_up[i] = ImageIO.read(new FileInputStream("res/entity/character/idle/"+name+"/up"+(i+1)+".png"));
                _idle_down[i] = ImageIO.read(new FileInputStream("res/entity/character/idle/"+name+"/down"+(i+1)+".png"));
                _idle_left[i] = ImageIO.read(new FileInputStream("res/entity/character/idle/"+name+"/left"+(i+1)+".png"));
                _idle_right[i] = ImageIO.read(new FileInputStream("res/entity/character/idle/"+name+"/right"+(i+1)+".png"));

                _walk_up[i] = ImageIO.read(new FileInputStream("res/entity/character/walk/"+name+"/up"+(i+1)+".png"));
                _walk_down[i] = ImageIO.read(new FileInputStream("res/entity/character/walk/"+name+"/down"+(i+1)+".png"));
                _walk_left[i] = ImageIO.read(new FileInputStream("res/entity/character/walk/"+name+"/left"+(i+1)+".png"));
                _walk_right[i] = ImageIO.read(new FileInputStream("res/entity/character/walk/"+name+"/right"+(i+1)+".png"));
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void drawInFight(Graphics2D g2, int screenX, int screenY){
        // Other function to draw in fightscene
    }
    public void drawInWorld(Graphics2D g2, int screenX, int screenY){
        BufferedImage image=null;
        //System.out.println(worldX + " " + worldY);
        if(speed == 0){ //IDLE ANIMATIONS
            for(int i=0;i<_spriteCntMax;i++){
                switch (facing) {
                    case "up":
                        if (_spriteCnt == i)image=_idle_up[i];
                        break;
                    case "down":
                        if (_spriteCnt == i)image=_idle_down[i];
                        break;
                    case "left":
                        if (_spriteCnt == i)image=_idle_left[i];
                        break;
                    case "right":
                        if (_spriteCnt == i)image=_idle_right[i];
                        break;
                }
            }
        }
        if(speed > 0){  //WALKING ANIMATIONS
            for(int i=0;i<_spriteCntMax;i++){
                switch (facing) {
                    case "up":
                        if (_spriteCnt == i)image=_walk_up[i];
                        break;
                    case "down":
                        if (_spriteCnt == i)image=_walk_down[i];
                        break;
                    case "left":
                        if (_spriteCnt == i)image=_walk_left[i];
                        break;
                    case "right":
                        if (_spriteCnt == i)image=_walk_right[i];
                        break;
                }
            }
        }


        g2.drawImage(image, screenX, screenY,screenSize, screenSize, null);
        g2.drawRect(screenX + hitbox.width/2, screenY + hitbox.height/2, hitbox.width, hitbox.height); //Center the hitbox to the entity
    }
}