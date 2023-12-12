/**
 * @file Character.java
 * @brief This file contains the implementation of the Character class, representing an abstract character entity with position, hitbox, stats, and animations.
 */

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

/**
 * @class Character
 * @extends Entity
 * @brief Represents an abstract character entity with position, hitbox, stats, and animations.
 */
public abstract class Character extends Entity {
    int health;
    int mana;
    int agility;
    int strength;
    int defense;
    int initiative;
    public int speed;
    public int dirX, dirY;
    int hasKey = 0; //normalement dans player

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
    public Character(int x, int y, int dirX, int dirY, int speed, String facing, int _spriteCntMax, int spriteSpeed) {
        super(x, y, _spriteCntMax, spriteSpeed);

        hitbox = new Rectangle();
        // Hitbox settings to set up later
        hitbox.x = x;
        hitbox.y = y;
        hitbox.width = screenSize / 2;
        hitbox.height = screenSize / 2;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;
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
    }

    /**
     * @brief Updates the character entity based on the current scene and time elapsed.
     * @param scene The current game scene.
     * @param dt The time elapsed since the last update.
     */
    @Override
    public void update(Scene scene, double dt) {
        if (scene.state == State.WORLD) {
            World currWorld = World.getWorld();

            hitbox.x = worldX + hitbox.width / 2;
            hitbox.y = worldY + hitbox.height / 2;

            move(World.getWorld(), speed, dt);
            // CHECK THE COLLISION
            checkNearTiles(currWorld.tileManager);
            collisionOn = false;
            //CHECK OBJECT COLLISION
            int objIndex = checkObject(this, currWorld, true);
            pickUpObject(currWorld, objIndex);

            updateFrames();
        }
    }

    /**
     * @brief Checks for collision with nearby tiles using the character's hitbox.
     * @param tileManager The TileManager containing information about tiles in the world.
     */
    private void checkNearTiles(TileManager tileManager) {
        // Checking tiles with hitbox
        if (isBlocked(tileManager.getTile(worldX + hitbox.width, worldY + hitbox.height))) {
            worldX = hitbox.x - hitbox.width / 2;
            worldY = hitbox.y - hitbox.height / 2;

            // TODO: try to add some statements to make the player move diagonally if he tries instead of getting fully stucked
        }
    }
    public int checkObject (Entity entity, World gp, boolean player) {
        int index = 999;
        for(int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null) {
                // Get entity's hitbox position
                entity.hitbox.x = (entity.worldX + entity.hitbox.x)/2;
                entity.hitbox.y = (entity.worldY + entity.hitbox.y)/2;

                // Get the object's solid area position
                gp.obj[i].hitbox.x = gp.obj[i].worldX + gp.obj[i].hitbox.x;
                gp.obj[i].hitbox.y = gp.obj[i].worldY + gp.obj[i].hitbox.y;
                switch(facing) {
                case "up":
                    entity.hitbox.y -= entity._spriteSpeed;
                    if(entity.hitbox.intersects(gp.obj[i].hitbox)) {
                        if(gp.obj[i].collision == true) {
                            entity.collision = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                        //System.out.println("up collision!");
                    }
                    break;
                case "down":
                    entity.hitbox.y += entity._spriteSpeed;
                    if(entity.hitbox.intersects(gp.obj[i].hitbox)) {
                        //System.out.println("down collision!");
                        if(gp.obj[i].collision == true) {
                            entity.collision = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "left":
                    entity.hitbox.x -= entity._spriteSpeed;
                    if(entity.hitbox.intersects(gp.obj[i].hitbox)) {
                        //System.out.println("left collision!");
                        if(gp.obj[i].collision == true) {
                            entity.collision = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "right":
                    entity.hitbox.x += entity._spriteSpeed;
                    if(entity.hitbox.intersects(gp.obj[i].hitbox)) {
                        //System.out.println("right collision!");
                        if(gp.obj[i].collision == true) {
                            entity.collision = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    break;
                    }
                }
                entity.hitbox.x = entity.hitboxDefaultX;
                entity.hitbox.y = entity.hitboxDefaultY;
                gp.obj[i].hitbox.x = gp.obj[i].hitboxDefaultX;
                gp.obj[i].hitbox.y = gp.obj[i].hitboxDefaultY;
            }
        }
        return index;
    }

    public void pickUpObject(World gp, int i) {
        if(i != 999) {
            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Key:"+hasKey);
                    break;
                case "Door":
                    if(hasKey > 0 ) {
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    System.out.println("Key:"+hasKey);
                    break;
            }
        }
    }

    /**
     * @brief Checks if the given tile is blocking the character's movement.
     * @param tile The Tile to check.
     * @return True if the tile is blocking, false otherwise.
     */
    public boolean isBlocked(Tile tile) {
        return tile.getCollision();
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
            double normSum = Math.sqrt(dirX * dirX + dirY * dirY);
            worldX += (dirX / normSum) * speed * dt;
            worldY += (dirY / normSum) * speed * dt;
        }
    }

    /**
     * @brief Accelerates the character's speed up to the specified maximum speed.
     * @param maxSpeed The maximum speed to accelerate to.
     * @param dt The time elapsed since the last update.
     */
    protected void accelerate(int maxSpeed, double dt) {
        if (speed < maxSpeed) {
            speed += 20 * dt;
        }
        if (speed > maxSpeed) {
            speed = maxSpeed;
        }
    }

    /**
     * @brief Decelerates the character's speed.
     * @param dt The time elapsed since the last update.
     */
    protected void decelerate(double dt) {
        speed -= dt;
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
     * @brief Draws the character in the fight scene.
     * @param g2 The Graphics2D object for drawing.
     * @param screenX The X-coordinate on the screen.
     * @param screenY The Y-coordinate on the screen.
     */
    public void drawInFight(Graphics2D g2, int screenX, int screenY) {
        // Other function to draw in fight scene
    }

    /**
     * @brief Draws the character in the world scene.
     * @param g2 The Graphics2D object for drawing.
     * @param screenX The X-coordinate on the screen.
     * @param screenY The Y-coordinate on the screen.
     */
    public void drawInWorld(Graphics2D g2, int screenX, int screenY) {
        BufferedImage image = null;
        // System.out.println(worldX + " " + worldY);
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

        g2.drawImage(image, screenX, screenY, screenSize, screenSize, null);
        g2.drawRect(screenX + hitbox.width / 2, screenY + hitbox.height / 2, hitbox.width, hitbox.height); // Center the hitbox to the entity
    }
}
