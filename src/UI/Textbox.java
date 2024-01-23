package UI;

/**
 * @file Textbox.java
 * @brief Contains the definition of the Textbox class used for displaying text on the screen.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JLabel;

/**
 * @class Textbox
 * @brief Represents a text box for displaying text on the screen, used for buttons or dialogues.
 */
public class Textbox extends JLabel {
    /**
     * @brief Width of the text box.
     */
    public int width;

    /**
     * @brief Height of the text box.
     */
    public int height;

    /**
     * @brief Color of the text in the text box.
     */
    private Color _color;

    /**
     * @brief Text content of the text box.
     */
    private String _text;

    /**
     * @brief Font of the text in the text box.
     */
    private Font _font;

    /**
     * @brief Font size to use for the text box.
     */
    private int _fontSizeToUse;

    /**
     * @brief Constructor for Textbox class.
     * @param text The text displayed on screen.
     * @param fontName The font to use.
     * @param w The total width the text has to cover.
     * @param h The total height the text has to cover.
     * @param color The color of the text.
     */
    public Textbox(String text, String fontName, int w, int h, Color color){
        width = w; height = h;
        _text = text;
        _color = color;

        // Load the font and set its size based on the width and height of the text box.
        loadFont(fontName);
        _font = new Font(fontName, Font.PLAIN, 1);

        int stringWidth = this.getFontMetrics(_font).stringWidth(_text);
        int componentWidth = width;

        // Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(_font.getSize() * widthRatio);
        int componentHeight = height;

        // Pick a new font size so it will not be larger than the height of the label.
        _fontSizeToUse = Math.min(newFontSize, componentHeight);

        // Set the label's font size to the newly determined size.
        _font = new Font(fontName, Font.PLAIN, _fontSizeToUse);
    }

    /**
     * @brief Setter function for a new font color of the current text.
     * @param newCol The new Color of the text.
     */
    public void setColor(Color newCol){
        _color = newCol;
    }

    /**
     * @brief Setter function for a new text content of the text box.
     * @param newText The new text content.
     */
    public void setNewText(String newText){
        _text = newText;
    }

    /**
     * @brief Static method to load a custom font.
     * @param fontName The name of the font file.
     */
    public static void loadFont(String fontName) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            InputStream fontInputStream = ClassLoader.getSystemResourceAsStream("hud/font/rainyhearts.ttf");

            if (fontInputStream != null) {
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontInputStream);
                ge.registerFont(customFont);
            }
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @brief Draws the text box on the screen.
     * @param g2 Graphics2D object for drawing.
     * @param x X-coordinate of the top-left corner of the text box.
     * @param y Y-coordinate of the top-left corner of the text box.
     */
    public void draw(Graphics2D g2, int x, int y){
        // Find a way to better center text on the button
        g2.setFont(_font);
        g2.setColor(_color);
        g2.drawString(_text, x + _fontSizeToUse, y + (height + _fontSizeToUse) / 2 - height/5);
    }
}
