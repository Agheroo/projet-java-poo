/**
 * @file World.java
 * @brief This file contains the implementation of the World class, responsible for managing the game world.
 */

package game;

import entity.Player;
import tiles.TileManager;
import entity.Enemy;
import entity.EntitySetter;
import entity.props.Props;
import game.FightScene.FightState;
import java.awt.*;
import java.util.HashMap;
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

    

    public EntitySetter entitySetter = new EntitySetter(this); // Instance of EntitySetter
    
    // Doc table de Hashage : https://www.geeksforgeeks.org/java-util-dictionary-class-java/
    public HashMap<Point, Props> objMap = new HashMap<>(); // HashMap to store objects with coordinates
    public static HashMap<Point, Enemy> enemies = new HashMap<>();
    
  
    // Player settings
    public Player player = new Player("player",15 * Const.WRLD_tileScreenSize,
            15 * Const.WRLD_tileScreenSize, 0, 0, 0, "down", 4, 20);


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
        entitySetter.setObject();
        entitySetter.setEnemies();

        player = new Player("player", 15 * Const.WRLD_tileScreenSize,
            15 * Const.WRLD_tileScreenSize, 0, 0, 0, "down", 4, 20);
    
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

    public void addEnemy(Point coordinates, Enemy enemy){
        enemies.put(coordinates,enemy);
    }

    /**
     * Updates the game world based on the scene state.
     */
    public void update() {
        checkPauseScene();
        if (state == State.WORLD) {
            Point ptn = new Point((int)13 * Const.WRLD_entityScreenSize, (int) 13 * Const.WRLD_entityScreenSize);
            //System.out.println("("+objMap.get(ptn).worldX +"," + objMap.get(ptn).worldY + ")  ("+player.worldX +","+player.worldY+")");
            //int playerScreenX = (Const.WDW_width - Const.WRLD_entityScreenSize) / 2;
            //int playerScreenY = (Const.WDW_height - Const.WRLD_entityScreenSize) / 2;

            player.update(this, dt);
            tileManager.update(this);


            for(Enemy enemy : enemies.values()){
                int playerScreenX = (Const.WDW_width - Const.WRLD_entityScreenSize) / 2;
                int playerScreenY = (Const.WDW_height - Const.WRLD_entityScreenSize) / 2;

                if (enemy.worldX + 5*Const.WRLD_tileScreenSize > player.worldX - playerScreenX
                && enemy.worldX - 5*Const.WRLD_tileScreenSize < player.worldX + playerScreenX
                && enemy.worldY + 5*Const.WRLD_tileScreenSize > player.worldY - playerScreenY
                && enemy.worldY - 5*Const.WRLD_tileScreenSize < player.worldY + playerScreenY) {  //Update enemy if he's close enough otherwise useless to update
                    enemy.update(this, dt);

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
            for(Enemy enemy : enemies.values()){
                // Calculate the screen position of the player
                int playerScreenX = (Const.WDW_width - Const.WRLD_entityScreenSize) / 2;
                int playerScreenY = (Const.WDW_height - Const.WRLD_entityScreenSize) / 2;


                //Checking if we need to draw enemy or not
                if (enemy.worldX + Const.WRLD_tileScreenSize > player.worldX - playerScreenX
                && enemy.worldX - Const.WRLD_tileScreenSize < player.worldX + playerScreenX
                && enemy.worldY + Const.WRLD_tileScreenSize > player.worldY - playerScreenY
                && enemy.worldY - Const.WRLD_tileScreenSize < player.worldY + playerScreenY) {
                                
                        // Calculate the screen position of the character relative to the player's position
                        int screenX = enemy.worldX - player.worldX + playerScreenX;
                        int screenY = enemy.worldY - player.worldY + playerScreenY;
                        enemy.drawInWorld(g2, screenX, screenY);
                }
            }
        }
        
    }

}
