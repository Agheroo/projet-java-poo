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
import game.FightScene.FightState;
import java.awt.*;
import java.util.HashMap;
import java.util.Vector;
import UI.Textbox;


/**
 * @class World
 * @extends Scene
 * @brief Represents the game world and manages its entities.
 */
public class World extends Scene {

    private static World _instance;
    private FightScene _currfight;
    public TileManager tileManager = new TileManager(this);

    

    public EntitySetter aSetter = new EntitySetter(this); // Instance of EntitySetter
    
    // Doc table de Hashage : https://www.geeksforgeeks.org/java-util-dictionary-class-java/
    public HashMap<Point, Props> objMap = new HashMap<>(); // HashMap to store objects with coordinates
    
  
    // Player settings
    public Player player = new Player("player",15 * Const.WRLD_tileScreenSize,
            15 * Const.WRLD_tileScreenSize, 0, 0, 0, "down", 4, 20);



    public static Vector<Enemy> enemies  = new Vector<Enemy>(5);
    //public Enemy[] enemies = new Enemy[10]; // The array of all enemies

    //public Player player;
    /**
     * Gets the instance of the World.
     *
     * @return The World instance.
     */
    public static World getWorld() {
        Textbox.loadFont("rainyhearts");
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

        player = new Player("player", 15 * Const.WRLD_tileScreenSize,
            15 * Const.WRLD_tileScreenSize, 0, 0, 0, "down", 4, 20);

        enemies.clear();
        for(int i=0;i<5;i++){
            enemies.add(new Enemy("orc", (6 + 2*i )* Const.WRLD_tileScreenSize,
                10 * Const.WRLD_tileScreenSize, 0, 0, 0, "down", 4, 20)); 
        }
        
    }

    

    /**
     * Adds an object to the HashMap with the specified coordinates.
     *
     * @param coordinates The coordinates of the object.
     * @param object      The object to be added.
     */
    public void addObject(Point coordinates, Props object) {
        objMap.put(coordinates, object);
    }

    /**
     * Updates the game world based on the scene state.
     */
    public void update() {
        checkPauseScene();
        if (state == State.WORLD) {
            int playerScreenX = (Const.WDW_width - Const.WRLD_entityScreenSize) / 2;
            int playerScreenY = (Const.WDW_height - Const.WRLD_entityScreenSize) / 2;

            player.update(this, dt);
            tileManager.update(this);


            
            // other entity.update(this,dt);
            for(int i=0;i<enemies.size();i++){
                //Update enemies only if he's visible otherwise it's useless
                if (enemies.get(i).worldX + Const.WRLD_tileScreenSize > player.worldX - playerScreenX
                && enemies.get(i).worldX - Const.WRLD_tileScreenSize < player.worldX + playerScreenX
                && enemies.get(i).worldY + Const.WRLD_tileScreenSize > player.worldY - playerScreenY
                && enemies.get(i).worldY - Const.WRLD_tileScreenSize < player.worldY + playerScreenY){
                    enemies.get(i).update(this,dt);
                    if(enemies.get(i).touchingPlayer(player)){
                        
                        System.out.println("Enemy is touching player at " +player.worldX+ ", "+ player.worldY);
                        changeScene(State.FIGHT);
                        _currfight = new FightScene(player,enemies.get(i));
                    }
                }
            }
        }
        if (state == State.FIGHT){
            _currfight.update(this);
        }
    }

    /**
     * Draws the game world and its entities.
     *
     * @param g2           The Graphics2D object for drawing.
     * @param screenWidth  The width of the screen.
     * @param screenHeight The height of the screen.
     */
    public void draw(Graphics2D g2, int screenWidth, int screenHeight) {
        
        //If there is a fight, draw the fight instead of the game world
        if(_currfight != null){
            if( _currfight.state == FightState.FIGHTING){
                _currfight.draw(g2);
            }
            if(_currfight.state == FightState.WON){
                _currfight = null;
            }
        }
        
        else{
            // TILE
            tileManager.draw(g2, this);
            // OBJECT
            for (Props props : objMap.values()) {
                props.draw(g2, this);
            }
            // PLAYER
            player.drawInWorld(g2, screenWidth / 2 - (Const.WRLD_entityScreenSize / 2),
                    screenHeight / 2 - (Const.WRLD_entityScreenSize / 2)); // Player is always centered to screen

            

            //ENEMIES
            for(int i=0; i<enemies.size();i++){
                int playerScreenX = (screenWidth - Const.WRLD_entityScreenSize) / 2;
                int playerScreenY = (screenHeight - Const.WRLD_entityScreenSize) / 2;

                if (enemies.get(i).worldX + Const.WRLD_tileScreenSize > player.worldX - playerScreenX
                    && enemies.get(i).worldX - Const.WRLD_tileScreenSize < player.worldX + playerScreenX
                    && enemies.get(i).worldY + Const.WRLD_tileScreenSize > player.worldY - playerScreenY
                    && enemies.get(i).worldY - Const.WRLD_tileScreenSize < player.worldY + playerScreenY) {
                        int screenX = enemies.get(i).worldX - player.worldX + playerScreenX;
                        int screenY = enemies.get(i).worldY - player.worldY + playerScreenY;
                        enemies.get(i).drawInWorld(g2, screenX, screenY);
                }
            }
        }
        
    }

}
