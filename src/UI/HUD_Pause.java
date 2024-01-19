package UI;

/**
 * @file HUD_Pause.java
 * @brief Contains the definition of the HUD_Pause class representing the pause menu in the game.
 */

import java.awt.Color;
import java.awt.Graphics2D;
import game.Const;
import game.Scene;
import game.Const.Selection;

/**
 * @class HUD_Pause
 * @brief Represents the pause menu HUD class, extending the abstract HUD class.
 */
public class HUD_Pause extends HUD {

    /**
     * @brief Constructor for HUD_Pause class.
     * Initializes the pause menu with two buttons: CONTINUER and QUITTER.
     */
    public HUD_Pause(){
        super();
        selection = Selection.CONTINUE;
        _nbButtons = 2;
        _buttons = new ChoiceButton[_nbButtons];

        _buttons[0] = new ChoiceButton(200, 50, "CONTINUER", Const.fontName, Color.blue);
        _buttons[1] = new ChoiceButton(200, 50, "QUITTER", Const.fontName, Color.blue);
        changeSelectionColor(0, Const.COLOR_BUTTON_text, Const.COLOR_BUTTON_text_highlight, Const.COLOR_BUTTON_bg, Const.COLOR_BUTTON_bg_highlight);
    }

    /**
     * @brief Updates the pause menu based on user input.
     */
    public void update(){
        if(Scene.keyH.interactPressed){
            confirm = true;
            Scene.keyH.interactPressed = false;
        }
        if(Scene.keyH.upPressed || Scene.keyH.downPressed){
            switch(selection.toString()){
                case "CONTINUE":
                    selection = Const.Selection.QUIT;
                    changeSelectionColor(1, Const.COLOR_BUTTON_text, Const.COLOR_BUTTON_text_highlight, Const.COLOR_BUTTON_bg, Const.COLOR_BUTTON_bg_highlight);
                    break;
                case "QUIT":
                    selection = Const.Selection.CONTINUE;
                    changeSelectionColor(0, Const.COLOR_BUTTON_text, Const.COLOR_BUTTON_text_highlight, Const.COLOR_BUTTON_bg, Const.COLOR_BUTTON_bg_highlight);
                    break;
                default: break;
            }
            Scene.keyH.upPressed = false;
            Scene.keyH.downPressed = false;
        }
    }

    /**
     * @brief Draws the pause menu on the screen.
     * @param g2 Graphics2D object for drawing.
     */
    public void draw(Graphics2D g2){
        // Darkened background
        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRect(0, 0, Const.WDW_width, Const.WDW_height);

        // Actual buttons to draw
        _buttons[0].draw(g2, (Const.WDW_width - _buttons[0].width) / 2, 200);
        _buttons[1].draw(g2, (Const.WDW_width - _buttons[0].width) / 2, 400);
    }

    public void selectClass(){}
}
