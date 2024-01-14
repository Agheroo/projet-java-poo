/**
 * @file Character.java
 * @brief This file contains the implementation of the Character class, representing an abstract character entity with position, hitbox, stats, and animations.
 */

package entity;

import game.Const;
import game.Scene;
import game.World;
import tiles.TileManager;
import game.Scene.State;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @class Character
 * @extends Entity
 * @brief Represents an abstract character entity with position, hitbox, stats, and animations.
 */
public abstract class Character extends Entity {
    //Stats for fights
    public int health;  //health
    public int mana;    //mana for special attacks
    public int agility; //probability to dodge an attack
    public int strength;//damage to inflict
    public int defense; //damag to reduce
    public int initiative;  //who strikes first in a battle


    //World movement "stats"
    public int speed;
    public int dirX, dirY;
    

    // Which direction is the entity facing (if directions are available) for animation
    public String facing;

    // Character animations
    private BufferedImage[] _idle_up;
    private BufferedImage[] _idle_down;
    private BufferedImage[] _idle_right;
    private BufferedImage[] _idle_left;
    private BufferedImage[] _walk_up;
    private BufferedImage[] _walk_down;
    private BufferedImage[] _walk_right;
    private BufferedImage[] _walk_left;

    /**
     * @brief Constructor for the Character class.
     * @param x The initial X-coordinate of the character in the world.
     * @param y The initial Y-coordinate of the character in the world.
     * @param dirX The X-direction of the character.
     * @param dirY The Y-direction of the character.
     * @param speed The speed of the character's movement.
     * @param facing The direction the character is facing (up, down, left, right).
     * @param _spriteCntMax The maximum number of sprites for animation.
     * @param spriteSpeed The speed of sprite animation.
     */
    public Character(String entityName, int x, int y, int dirX, int dirY, int speed, String facing, int _spriteCntMax, int spriteSpeed) {
        super(entityName, x, y, _spriteCntMax, spriteSpeed,true);



        // Hitbox settings (size of the entity)
        this.hitbox.width = Const.WRLD_entityScreenSize / 2;
        this.hitbox.height = Const.WRLD_entityScreenSize / 2;
        this.dirX = dirX;
        this.dirY = dirY;
        this.speed = speed;
        this.facing = facing;

        _idle_up = new BufferedImage[_spriteCntMax];
        _idle_down = new BufferedImage[_spriteCntMax];
        _idle_right = new BufferedImage[_spriteCntMax];
        _idle_left = new BufferedImage[_spriteCntMax];
        _walk_up = new BufferedImage[_spriteCntMax];
        _walk_down = new BufferedImage[_spriteCntMax];
        _walk_right = new BufferedImage[_spriteCntMax];
        _walk_left = new BufferedImage[_spriteCntMax];
        loadTextures(entityName);
    }

    /**
     * @brief Updates the character entity based on the current scene and time elapsed.
     * @param scene The current game scene.
     * @param dt The time elapsed since the last update.
     */
    public void update(double dt) {
        if (Scene.state == State.WORLD) {
            World currWorld = World.getWorld();

            hitbox.x = worldX + hitbox.width / 2;
            hitbox.y = worldY + hitbox.height;

            // CHECK THE COLLISION
            move(World.getWorld(), speed, dt);
            checkTileCollision(currWorld.tileManager);
            
            

            updateFrames();
        }
    }

    /**
     * @brief Checks for collision with nearby tiles using the character's hitbox.
     * @param tileManager The TileManager containing information about tiles in the world.
     */
    private void checkTileCollision(TileManager tileManager) {
        // Checking tiles with hitbox
        
        
        if((tileManager.getTile(hitbox.x, hitbox.y - 5).getCollision() //Checks collision with tile on top of the character
        || tileManager.getTile(hitbox.x + hitbox.width , hitbox.y - 5).getCollision() )&&  dirY == -1) {
            worldY = tileManager.getTile(hitbox.x,hitbox.y).getPos()[1] - hitbox.height;    //Prevent moving if collidable terrain
        }
        if((tileManager.getTile(hitbox.x, hitbox.y + hitbox.height + 5).getCollision() //Checks collision with tile beneath of the character
        || tileManager.getTile(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 5).getCollision()) && dirY == 1){
            worldY = tileManager.getTile(hitbox.x,hitbox.y).getPos()[1] -1;        //Prevent moving if collidable terrain
        }
        if((tileManager.getTile(hitbox.x - 5, hitbox.y).getCollision() //Checks collision with tile on the left of the character
        || tileManager.getTile(hitbox.x - 5, hitbox.y + hitbox.height).getCollision()) && dirX == -1) {
            worldX = tileManager.getTile(hitbox.x,hitbox.y).getPos()[0] - hitbox.width/2 ;    //Prevent moving if collidable terrain
        }
        if((tileManager.getTile(hitbox.x + hitbox.width + 5, hitbox.y).getCollision() //Checks collision with tile on the right of the character
        || tileManager.getTile(hitbox.x + hitbox.width + 5, hitbox.y + hitbox.height).getCollision()) && dirX == 1){
            worldX = tileManager.getTile(hitbox.x,hitbox.y).getPos()[0] + hitbox.width/2 - 1;        //Prevent moving if collidable terrain
        }
    }



    /**
     * @brief Moves the character in the world based on its direction, speed, and the elapsed time.
     * @param world The current game world.
     * @param speed The speed of the character's movement.
     * @param dt The time elapsed since the last update.
     */
    protected void move(World world, int speed, double dt) {
        if ((dirX == 0 && dirY != 0) || (dirY == 0 && dirX != 0)) {
            if (dirX == 1) {
                worldX += speed * dt;
            }
            if (dirX == -1) {
                worldX -= speed * dt;
            }
            if (dirY == 1) {
                worldY += speed * dt;
            }
            if (dirY == -1) {
                worldY -= speed * dt;
            }
        }
        if (dirX != 0 && dirY != 0) {
            double normSum = Math.sqrt(dirX * dirX + dirY * dirY);      //Normalizing vector
            worldX += (dirX / normSum) * speed * dt;
            worldY += (dirY / normSum) * speed * dt;
        }
    }

    /**
     * @brief Accelerates the character's speed up to the specified maximum speed.
     * @param maxSpeed The maximum speed to accelerate to.
     * @param dt The time elapsed since the last update.
     */
    protected void accelerate(int maxSpeed, int factor, double dt) {
        if (speed < maxSpeed) {
            speed += factor*dt;
        }
        if (speed > maxSpeed) {
            speed = maxSpeed;
        }
    }

    /**
     * @brief Decelerates the character's speed.
     * @param dt The time elapsed since the last update.
     */
    protected void decelerate(int factor,double dt) {
        speed -= factor*dt;
    }

    /**
     * @brief Loads character textures based on the specified name.
     * @param name The name used to determine the textures to load.
     */
    protected void loadTextures(String name) {
        try {
            for (int i = 0; i < _spriteCntMax; i++) {
                _idle_up[i] = ImageIO.read(new FileInputStream("res/entity/character/idle/" + name + "/up" + (i + 1) + ".png"));
                _idle_down[i] = ImageIO.read(new FileInputStream("res/entity/character/idle/" + name + "/down" + (i + 1) + ".png"));
                _idle_left[i] = ImageIO.read(new FileInputStream("res/entity/character/idle/" + name + "/left" + (i + 1) + ".png"));
                _idle_right[i] = ImageIO.read(new FileInputStream("res/entity/character/idle/" + name + "/right" + (i + 1) + ".png"));

                _walk_up[i] = ImageIO.read(new FileInputStream("res/entity/character/walk/" + name + "/up" + (i + 1) + ".png"));
                _walk_down[i] = ImageIO.read(new FileInputStream("res/entity/character/walk/" + name + "/down" + (i + 1) + ".png"));
                _walk_left[i] = ImageIO.read(new FileInputStream("res/entity/character/walk/" + name + "/left" + (i + 1) + ".png"));
                _walk_right[i] = ImageIO.read(new FileInputStream("res/entity/character/walk/" + name + "/right" + (i + 1) + ".png"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @brief Draws the character in the world scene.
     * @param g2 The Graphics2D object for drawing.
     * @param screenX The X-coordinate on the screen.
     * @param screenY The Y-coordinate on the screen.
     */
    public void drawInWorld(Graphics2D g2, int screenX, int screenY) {
        BufferedImage image = null;
        if (speed == 0) { // IDLE ANIMATIONS
            for (int i = 0; i < _spriteCntMax; i++) {
                switch (facing) {
                    case "up":
                        if (_spriteCnt == i) image = _idle_up[i];
                        break;
                    case "down":
                        if (_spriteCnt == i) image = _idle_down[i];
                        break;
                    case "left":
                        if (_spriteCnt == i) image = _idle_left[i];
                        break;
                    case "right":
                        if (_spriteCnt == i) image = _idle_right[i];
                        break;
                }
            }
        }
        if (speed > 0) { // WALKING ANIMATIONS
            for (int i = 0; i < _spriteCntMax; i++) {
                switch (facing) {
                    case "up":
                        if (_spriteCnt == i) image = _walk_up[i];
                        break;
                    case "down":
                        if (_spriteCnt == i) image = _walk_down[i];
                        break;
                    case "left":
                        if (_spriteCnt == i) image = _walk_left[i];
                        break;
                    case "right":
                        if (_spriteCnt == i) image = _walk_right[i];
                        break;
                }
            }
        }
        

        int playerScreenX = (Const.WDW_width - Const.WRLD_entityScreenSize) / 2;
        int playerScreenY = (Const.WDW_height - Const.WRLD_entityScreenSize) / 2;

        //Checking if we need to draw enemy or not
        if (worldX + Const.WRLD_tileScreenSize > worldX - playerScreenX
        && worldX - Const.WRLD_tileScreenSize < worldX + playerScreenX
        && worldY + Const.WRLD_tileScreenSize > worldY - playerScreenY
        && worldY - Const.WRLD_tileScreenSize < worldY + playerScreenY) {
                        
            g2.drawImage(image, screenX, screenY, Const.WRLD_entityScreenSize, Const.WRLD_entityScreenSize, null);
            g2.drawRect(screenX + hitbox.width / 2, screenY + hitbox.height, hitbox.width, hitbox.height); // Center the hitbox to the entity
        }
    }


    /**
     * @brief Draws the character in the fight scene.
     * @param g2 The Graphics2D object for drawing.
     * @param screenX The X-coordinate on the screen.
     * @param screenY The Y-coordinate on the screen.
     */
    public void drawInFight(Graphics2D g2, int screenX, int screenY) {
        // Other function to draw in fight scene
    }
}
