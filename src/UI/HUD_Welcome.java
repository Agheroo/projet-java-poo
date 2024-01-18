package UI;

/**
 * @file HUD_Welcome.java
 * @brief Contains the definition of the HUD_Welcome class representing the welcome screen HUD in the game.
 */

import java.awt.Color;
import java.awt.Graphics2D;
import game.Const;
import game.FightScene;

/**
 * @class HUD_Welcome
 * @brief Represents the welcome screen HUD class, extending the abstract HUD class.
 */
public class HUD_Welcome extends HUD {

    /**
     * @brief Constructor for HUD_Welcome class.
     * Initializes the welcome screen HUD.
     */
    public HUD_Welcome(){
        super();
    }

    /**
     * @brief Updates the welcome screen HUD.
     * Currently, this method has no implementation.
     */
    public void update(){

    }

    /**
     * @brief Draws the welcome screen HUD on the screen.
     * @param g2 Graphics2D object for drawing.
     */
    public void draw(Graphics2D g2){

        // Check if the player has lost in the fight scene
        if(FightScene.state == Const.FightState.LOST){
            // Display a message for losing
            Textbox thanks = new Textbox("Vous avez perdu !", Const.fontName, 400, 90, new Color(0xCF1C3F));
            Textbox thanks2 = new Textbox("Merci d'avoir joué !", Const.fontName, 350, 90, new Color(0xDDDDDD));

            // Draw the losing messages on the screen
            thanks.draw(g2, 0, 150);
            thanks2.draw(g2, -20, 280);

            // Draw a separator line
            g2.setColor(new Color(0xDDDDDD));
            g2.fillRect(50, 400, Const.WDW_width - 100, 4);
        }
    }
}
