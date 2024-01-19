package UI;

/**
 * @file HUD.java
 * @brief Contains the definition of the abstract HUD (Heads-Up Display) class for in-game user interfaces.
 */

import java.awt.Color;
import java.awt.Graphics2D;
import game.Scene;
import game.Const.Selection;

/**
 * @class HUD
 * @brief Represents the abstract base class for Heads-Up Display in the game's user interfaces.
 */
public abstract class HUD {
    /**
     * @brief Current selection in the HUD.
     */
    public Selection selection;

    /**
     * @brief Flag indicating confirmation status in the HUD.
     */
    public boolean confirm;

    /**
     * @brief Number of buttons in the HUD.
     */
    protected int _nbButtons;

    /**
     * @brief Array of ChoiceButton objects representing buttons in the HUD.
     */
    protected ChoiceButton[] _buttons;

    /**
     * @brief Array of Textbox objects representing text elements in the HUD.
     */
    protected Textbox[] _texts;

    /**
     * @brief Constructor for the HUD class.
     */
    public HUD(){
        // Initialize confirmation status and reset key press flags.
        confirm = false;
        Scene.keyH.interactPressed = false;
        Scene.keyH.downPressed = false;
        Scene.keyH.upPressed = false;
        Scene.keyH.leftPressed = false;
        Scene.keyH.rightPressed = false;
    }

    /**
     * @brief Changes the color of the selected button in the HUD.
     * @param selectionIndex Index of the selected button.
     * @param baseText Base color for the text of unselected buttons.
     * @param newText New color for the text of the selected button.
     * @param baseBg Base color for the background of unselected buttons.
     * @param newBg New color for the background of the selected button.
     */
    public void changeSelectionColor(int selectionIndex, Color baseText, Color newText, Color baseBg, Color newBg){
        for(int i = 0; i < _nbButtons; i++){
            _buttons[i].setTextColor(baseText);
            _buttons[i].setBgColor(baseBg);
            _buttons[i].highlighted = false;
        }
        _buttons[selectionIndex].setTextColor(newText);
        _buttons[selectionIndex].setBgColor(newBg);
        _buttons[selectionIndex].highlighted = true;
    }

    /**
     * @brief Abstract method to draw the HUD on the screen.
     * @param g2 Graphics2D object for drawing.
     */
    public abstract void draw(Graphics2D g2);

    /**
     * @brief Abstract method to update the HUD.
     */
    public abstract void update();

    public abstract void selectClass();
}
