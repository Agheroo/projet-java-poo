/**
 * @file Scene.java
 * @brief This file contains the implementation of the abstract Scene class, representing a scene in the game.
 */

package game;

import main.KeyHandler;

import java.awt.*;

import UI.HUD;
import UI.HUD.MenuType;

/**
 * @class Scene
 * @brief Represents an abstract scene in the game.
 */
public abstract class Scene {
    /**
     * @enum State
     * @brief Represents the possible states of a scene (WORLD, FIGHT, PAUSE, MENU).
     */
    public enum State {WORLD, FIGHT, PAUSE, MENU}

    private State _lastState;

    public KeyHandler keyH = KeyHandler.getInstance();
    protected static double dt = 0;
    public State state;
    public HUD menu;

    /**
     * @brief Updates the scene.
     */
    public abstract void update();

    /**
     * @brief Draws the scene.
     * @param g2 The Graphics2D object for drawing.
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     */
    public abstract void draw(Graphics2D g2, int screenWidth, int screenHeight);
    

    public static double getdt(){
        return dt;
    }
    
    /**
     * @brief Gets the World scene.
     * @return The World scene.
     */
    /*public Scene worldScene() {
        return World.getWorld();
    }*/

    /**
     * @brief Creates and returns a new FightScene.
     * @param player The player entity in the fight.
     * @param enemy The enemy entity in the fight.
     * @return The new FightScene.
     */
    /*public Scene fightScene(Player player, Enemy enemy) {
        return new FightScene(player, enemy);
    }*/


    /**
     * @brief Checks for a change in the scene state based on user input.
     */
    public void checkPauseScene() {
        if (keyH.pausePressed) {
            keyH.pausePressed = false;

            if (state != State.PAUSE) {
                System.out.println("CHANGING SCENE TO: PAUSE");
                _lastState = state;
                state = State.PAUSE;
                menu = new HUD(MenuType.PAUSE);
            } else {
                System.out.println("CHANGING SCENE TO: "+_lastState);
                state = _lastState;
                menu = null;
            }
        }
    }

    public void changeScene(State newState){
        System.out.println("CHANGING SCENE TO: " + newState);
        this.state = newState;
    }

}
