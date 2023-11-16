package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entity {
    //Display purpose variables
    private final int tileSize=16;
    private final int scale = 3;
    public final int screenSize = tileSize*scale;


    //Position of entity in the world, directions and speed
    public int worldX, worldY;
    public int dirX,dirY;
    public int speed;
    
    //Hitbox of entity
    public Rectangle hitbox;    
    public boolean collisionOn = false;

    //Which direction is the entity facing (if directions are available) for animation
    public String facing;




    //Entity animations
    private int spriteCnt=0;        //Variable responsible for the incrementation of the different sprites
    private int spriteUpdater=0;    //Variable responsible for the incrementation of the speed of the sprites
    private int spriteSpeed;        //How fast are the sprites changing (higher spriteSpeed means slower time to change)
    private int spriteCntMax;       //How many sprite does the entity have
    public BufferedImage[] idle_up; public BufferedImage[] idle_down;
    public BufferedImage[] idle_right; public BufferedImage[] idle_left;
    public BufferedImage[] walk_up; public BufferedImage[] walk_down;
    public BufferedImage[] walk_right; public BufferedImage[] walk_left;

    


    public Entity(int x,int y,int dirX,int dirY,int speed,String facing,int spriteCntMax,int spriteSpeed){
        
        //Hitbox settings to set up later


        this.worldX = x; this.worldY = y;
        this.dirX = dirX; this.dirY = dirY;
        this.speed = speed;
        this.facing = facing;
        this.spriteCntMax = spriteCntMax; this.spriteSpeed = spriteSpeed;

        idle_up = new BufferedImage[spriteCntMax]; idle_down = new BufferedImage[spriteCntMax];
        idle_right = new BufferedImage[spriteCntMax]; idle_left = new BufferedImage[spriteCntMax];
        walk_up = new BufferedImage[spriteCntMax]; walk_down = new BufferedImage[spriteCntMax];
        walk_right = new BufferedImage[spriteCntMax]; walk_left = new BufferedImage[spriteCntMax];
    }

    public void loadTextures(String name){
        try {
            for(int i=0; i<spriteCntMax ;i++){
                idle_up[i] = ImageIO.read(new FileInputStream("res/entity/idle/"+name+"/up"+(i+1)+".png"));
                idle_down[i] = ImageIO.read(new FileInputStream("res/entity/idle/"+name+"/down"+(i+1)+".png"));
                idle_left[i] = ImageIO.read(new FileInputStream("res/entity/idle/"+name+"/left"+(i+1)+".png"));
                idle_right[i] = ImageIO.read(new FileInputStream("res/entity/idle/"+name+"/right"+(i+1)+".png"));

                walk_up[i] = ImageIO.read(new FileInputStream("res/entity/walk/"+name+"/up"+(i+1)+".png"));
                walk_down[i] = ImageIO.read(new FileInputStream("res/entity/walk/"+name+"/down"+(i+1)+".png"));
                walk_left[i] = ImageIO.read(new FileInputStream("res/entity/walk/"+name+"/left"+(i+1)+".png"));
                walk_right[i] = ImageIO.read(new FileInputStream("res/entity/walk/"+name+"/right"+(i+1)+".png"));
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, int screenX, int screenY){
        BufferedImage image=null;

        if(speed == 0){ //IDLE ANIMATIONS
            for(int i=0;i<spriteCntMax;i++){
                switch (facing) {
                    case "up":
                        if (spriteCnt == i)image=idle_up[i];
                        break;
                    case "down":
                        if (spriteCnt == i)image=idle_down[i];
                        break;
                    case "left":
                        if (spriteCnt == i)image=idle_left[i];
                        break;
                    case "right":
                        if (spriteCnt == i)image=idle_right[i];
                        break;
                }
            }
        }
        if(speed > 0){  //WALKING ANIMATIONS
            for(int i=0;i<spriteCntMax;i++){
                switch (facing) {
                    case "up":
                        if (spriteCnt == i)image=walk_up[i];
                        break;
                    case "down":
                        if (spriteCnt == i)image=walk_down[i];
                        break;
                    case "left":
                        if (spriteCnt == i)image=walk_left[i];
                        break;
                    case "right":
                        if (spriteCnt == i)image=walk_right[i];
                        break;
                }
            }
        }


        spriteUpdater++;
        if (spriteUpdater>spriteSpeed){
            spriteCnt++;
            if(spriteCnt == spriteCntMax){
                spriteCnt = 0;
            }
            spriteUpdater=0;
        }

        g2.drawImage(image, screenX, screenY,screenSize, screenSize, null);
    }
}
