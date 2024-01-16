/**
 * @file World.java
 * @brief This file contains the implementation of the World class, responsible for managing the game world.
 */

package game;

import tiles.TileManager;
import entity.Enemy;
import entity.EntitySetter;
import entity.Warrior;
import entity.props.Props;
import java.awt.*;
import java.util.HashMap;

import UI.HUD_Welcome;
import UI.HUD_World;
import UI.Textbox;


/**
 * @class World
 * @extends Scene
 * @brief Represents the game world and manages its entities.
 */
public class World extends Scene {

    private static World _instance;
    private HUD_World HUD_world;
    public static FightScene currfight;
    public TileManager tileManager = new TileManager(this);
    

    public EntitySetter entitySetter = new EntitySetter(this); // Instance of EntitySetter
    
    // Doc table de Hashage : https://www.geeksforgeeks.org/java-util-dictionary-class-java/
    public static HashMap<Point, Props> objMap = new HashMap<>(); // HashMap to store objects with coordinates
    public static HashMap<Point, Enemy> enemies = new HashMap<>();
    
  
    // Player settings
    public Warrior player;
    /**
     * @brief Gets the instance of the World.
     *
     * @return The World instance.
     */
    public static World getWorld() {
        Textbox.loadFont(Const.fontName);
        if (_instance == null) {
            _instance = new World();
            state = Const.State.WORLD;
        }
        return _instance;
    }


    /**
     * Set up the initial state of the game.
     */
    public void setupGame() {
        entitySetter.setObject();
        entitySetter.setEnemies();

        player = new Warrior("player", 8 * Const.WRLD_tileScreenSize,
            16 * Const.WRLD_tileScreenSize, 0, 0, 0, "down", 4, 20);
        
        menu = new HUD_Welcome();
        state = Const.State.WORLD;    //TODO : CHANGE THIS STATE TO WELCOME WHEN RELEASING THE GAME
        HUD_world = new HUD_World();
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
     * Adds an enemy to the HashMap with the specified coordinates.
     *
     * @param coordinates The coordinates of the object.
     * @param object      The object to be added.
     */
    public void addEnemy(Point coordinates, Enemy enemy){
        enemies.put(coordinates,enemy);
    }

    /**
     * Updates the game world based on the scene state.
     */
    public void update() {
        switch(state){
            case Const.State.WELCOME:
                menu.update();
                break;
            case Const.State.WORLD:
                checkPauseScene();
                HUD_world.update();
                player.update(dt);
                tileManager.update(this);


                //Update enemy if he's close enough otherwise useless to update (5 tile radius around the screen)
                for(Enemy enemy : enemies.values()){
                    int playerScreenX = (Const.WDW_width - Const.WRLD_entityScreenSize) / 2;
                    int playerScreenY = (Const.WDW_height - Const.WRLD_entityScreenSize) / 2;

                    if(enemy.worldX + 5*Const.WRLD_tileScreenSize > player.worldX - playerScreenX
                    && enemy.worldX - 5*Const.WRLD_tileScreenSize < player.worldX + playerScreenX
                    && enemy.worldY + 5*Const.WRLD_tileScreenSize > player.worldY - playerScreenY
                    && enemy.worldY - 5*Const.WRLD_tileScreenSize < player.worldY + playerScreenY) {  
                        enemy.update(dt);

                    }
                }
                break;
            
            case Const.State.PAUSE:
                menu.update(); 
                checkPauseScene();
                break;

            case Const.State.FIGHT:
                currfight.update(this); break;

            default:
                break;
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
        if(currfight != null){
            switch(FightScene.state){
                case Const.FightState.FIGHTING:
                    currfight.draw(g2); break;
                case Const.FightState.WON:
                    currfight = null; break;
                case Const.FightState.LOST:
                    menu.draw(g2); break;
                default: break;
            }
        }
        
        else{
            if(state == Const.State.WORLD){
                // TILE
                tileManager.draw(g2, this);
                // OBJECT
                for (Props props : objMap.values()) {
                    props.draw(g2, this);
                }
                // PLAYER
                player.drawInWorld(g2, (screenWidth-Const.WRLD_entityScreenSize)/2,
                                        (screenHeight-Const.WRLD_entityScreenSize)/2); // Player is always centered to screen

                

                //ENEMIES
                for(Enemy enemy : enemies.values()){
                    // Calculate the screen position of the character relative to the player's position
                    int screenX = enemy.worldX - player.worldX + (Const.WDW_width - Const.WRLD_entityScreenSize)/2;
                    int screenY = enemy.worldY - player.worldY + (Const.WDW_height - Const.WRLD_entityScreenSize)/2;

                    enemy.drawInWorld(g2, screenX, screenY);
                }
                HUD_world.draw(g2);
                
            }

            else if(state == Const.State.WELCOME){
                menu.draw(g2);
            }
        }  


        //Draw pause screen last but still draw the background game
        if(Scene.state == Const.State.PAUSE){
            menu.draw(g2);
        }
        
    }
}
