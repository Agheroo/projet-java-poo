/**
 * @file Scene.java
 * @brief This file contains the implementation of the abstract Scene class, representing a scene in the game.
 */

package game;

import main.KeyHandler;

import java.awt.*;

import UI.HUD;
import UI.HUD_Pause;
import game.Const.State;

/**
 * @class Scene
 * @brief Represents an abstract scene in the game.
 */
public abstract class Scene {
    public static State lastState;

    public static KeyHandler keyH = KeyHandler.getInstance();
    protected static double dt = 0;
    public static State state;
    public HUD menu;

    /**
     * @brief Updates the scene.
     */
    public void update(){
        if(menu != null){
            menu.update();
        }
    }

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
     * @brief Checks for a change in the scene state based on user input.
     */
    public void checkPauseScene() {
        //If the game is not already paused, pause it and show pause HUD
        if(state != State.PAUSE){
            if(keyH.escPressed){
                System.out.println("CHANGING SCENE TO: PAUSE");
                lastState = state;
                state = State.PAUSE;
                menu = new HUD_Pause();
                keyH.escPressed = false;
            }
        }
        else{ //If the game is paused
            if(keyH.escPressed || (menu.confirm && menu.selection == Const.Selection.CONTINUE)){    //Change back to current scene if the continue is selected
                System.out.println("CHANGING SCENE TO: "+lastState);
                state = lastState;
                keyH.escPressed = false;
                menu.confirm = false;
                menu = null;
            }
            else if(menu.confirm && menu.selection == Const.Selection.QUIT){        //Destroy window and close game if quit is selected
                System.out.println("Détruire la fenêtre");
                menu.confirm = false;
            }
            keyH.interactPressed = false;
        }        
    }
}
