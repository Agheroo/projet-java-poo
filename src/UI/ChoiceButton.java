package UI;

/**
 * @file ChoiceButton.java
 * @brief Contains the definition of the ChoiceButton class used for creating interactive buttons in the UI.
 */

import java.awt.Graphics2D;
import java.awt.Color;

/**
 * @class ChoiceButton
 * @brief Represents a clickable button with associated text and appearance settings.
 */
public class ChoiceButton {
    /**
     * @brief Width of the button.
     */
    public int width;

    /**
     * @brief Height of the button.
     */
    public int height;

    /**
     * @brief Flag indicating whether the button is highlighted.
     */
    public boolean highlighted;

    /**
     * @brief Textbox associated with the button.
     */
    private Textbox _textBox;

    /**
     * @brief Background color of the button.
     */
    private Color _bgColor;

    /**
     * @brief Constructor for ChoiceButton class.
     * @param w Width of the button.
     * @param h Height of the button.
     * @param title Text content of the button.
     * @param fontName Font name for the text.
     * @param fontColor Font color for the text.
     */
    public ChoiceButton(int w, int h, String title, String fontName, Color fontColor){
        this.width = w;
        this.height = h;
        highlighted = false;

        // Initialize the associated Textbox with specified parameters.
        _textBox = new Textbox(title, fontName, w, h, fontColor);

        // Set default background color for the button.
        _bgColor = new Color(0xA38168);
    }

    /**
     * @brief Setter function to change the button's text color.
     * @param newColor The new color to use for the text.
     */
    public void setTextColor(Color newColor){
        _textBox.setColor(newColor);
    }

    /**
     * @brief Setter function to change the button's background color.
     * @param newColor The new color to use for the background.
     */
    public void setBgColor(Color newColor){
        _bgColor = newColor;
    }

    /**
     * @brief Draws a button (rectangle) on the screen at specified coordinates (top left).
     * @param g2 Graphics2D object for drawing.
     * @param x X-coordinate of the top-left corner of the button.
     * @param y Y-coordinate of the top-left corner of the button.
     */
    public void draw(Graphics2D g2, int x, int y){
        // Draw a highlighted border if the button is highlighted.
        if(highlighted){
            g2.setColor(Color.black);
            g2.fillRect(x-2, y-2, width + 4, height + 4);
        }

        // Draw the button's background.
        g2.setColor(_bgColor);
        g2.fillRect(x, y, width, height);

        // Draw the associated Textbox on the button.
        _textBox.draw(g2, x, y);
    }
}
