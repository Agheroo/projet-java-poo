/**
 * @file World.java
 * @brief This file contains the implementation of the World class, responsible for managing the game world.
 */

package game;

import entity.Player;
import tiles.TileManager;
import entity.Enemy;
import entity.EntitySetter;
import entity.Props;
import java.awt.*;

/**
 * @class World
 * @extends Scene
 * @brief Represents the game world and manages its entities.
 */
public class World extends Scene {

    private static World _instance;
    private FightScene _currfight;
    public TileManager tileManager = new TileManager(this);

    // World initialization settings
    public final int maxRow = 27, maxCol = 27; // DONT FORGET TO MODIFY WHEN CHANGING THE MAP !!!

    public EntitySetter aSetter = new EntitySetter(this); // Instance of EntitySetter
    // Player settings
    public Props[] obj = new Props[10]; // The array that lists all objects
    public Enemy[] enemies = new Enemy[10]; // The array of all enemies

    public Player player;
    /**
     * Gets the instance of the World.
     *
     * @return The World instance.
     */
    public static World getWorld() {
        if (_instance == null) {
            _instance = new World();
            _instance.state = State.WORLD;
        }
        return _instance;
    }


    /**
     * Set up the initial state of the game.
     */
    public void setupGame() {
        aSetter.setObject();

        player = new Player("player", 15 * tileManager.tileSize * tileManager.scale,
            15 * tileManager.tileSize * tileManager.scale, 0, 0, 0, "down", 4, 20);

        for(int i=0;i<5;i++){
            enemies[i] = new Enemy("orc", (6 + 2*i )* tileManager.tileSize * tileManager.scale,
            10 * tileManager.tileSize * tileManager.scale, 0, 0, 0, "down", 4, 20);
        }
        
    }

    /**
     * Updates the game world based on the scene state.
     */
    public void update() {
        checkPauseScene();
        if (state == State.WORLD) {
            int playerScreenX = (800 - player.screenSize) / 2;
            int playerScreenY = (600 - player.screenSize) / 2;

            player.update(this, dt);
            tileManager.update(this, 800, 600);


            
            // other entity.update(this,dt);
            for(int i=0;i<5;i++){
                //Update enemies only if he's visible otherwise it's useless
                if (enemies[i].worldX + tileManager.tileSize * tileManager.scale > player.worldX - playerScreenX
                && enemies[i].worldX - tileManager.tileSize * tileManager.scale < player.worldX + playerScreenX
                && enemies[i].worldY + tileManager.tileSize * tileManager.scale > player.worldY - playerScreenY
                && enemies[i].worldY - tileManager.tileSize * tileManager.scale < player.worldY + playerScreenY){
                    if(!enemies[i].touchingPlayer(player)){
                        enemies[i].update(this,dt);
                    }
                    else{
                        
                        System.out.println("Enemy is touching player at " +player.worldX+ ", "+ player.worldY);
                        changeScene(State.FIGHT);
                        _currfight = new FightScene(player,enemies[i]);
                    }
                }
            }
        }
        if (state == State.FIGHT){
            _currfight.update();
        }
            
            /*// Checks if player is touching the edges of the map
            if (player.worldX + player.hitbox.width / 2 < 0) {
                player.worldX = 0 - player.hitbox.width / 2;
            }
            if (player.worldX - player.hitbox.width / 2 >= (maxCol - 1) * tileManager.tileSize * tileManager.scale) {
                player.worldX = (maxCol - 1) * tileManager.tileSize * tileManager.scale + player.hitbox.width / 2;
            }
            if (player.worldY + player.hitbox.height / 2 < 0) {
                player.worldY = 0 - player.hitbox.height / 2;
            }
            if (player.worldY - player.hitbox.height / 2 >= (maxRow - 1) * tileManager.tileSize * tileManager.scale) {
                player.worldY = (maxRow - 1) * tileManager.tileSize * tileManager.scale + player.hitbox.height / 2;
            }*/
    }

    /**
     * Draws the game world and its entities.
     *
     * @param g2           The Graphics2D object for drawing.
     * @param screenWidth  The width of the screen.
     * @param screenHeight The height of the screen.
     */
    public void draw(Graphics2D g2, int screenWidth, int screenHeight) {
        // TILE
        tileManager.draw(g2, this, screenWidth, screenHeight);
        // OBJECT
        for (Props props : obj) {
            if (props != null) {
                props.draw(g2, this);
            }
        }
        // PLAYER
        player.drawInWorld(g2, screenWidth / 2 - (player.screenSize / 2),
                screenHeight / 2 - (player.screenSize / 2)); // Player is always centered to screen

        

        //ENEMIES
        for(int i=0;i<5;i++){
            int playerScreenX = (screenWidth - player.screenSize) / 2;
            int playerScreenY = (screenHeight - player.screenSize) / 2;

            if (enemies[i].worldX + tileManager.tileSize * tileManager.scale > player.worldX - playerScreenX
                && enemies[i].worldX - tileManager.tileSize * tileManager.scale < player.worldX + playerScreenX
                && enemies[i].worldY + tileManager.tileSize * tileManager.scale > player.worldY - playerScreenY
                && enemies[i].worldY - tileManager.tileSize * tileManager.scale < player.worldY + playerScreenY) {
                    int screenX = enemies[i].worldX - player.worldX + playerScreenX;
                    int screenY = enemies[i].worldY - player.worldY + playerScreenY;
                    enemies[i].drawInWorld(g2, screenX, screenY);
            }
        }
    }
}
