package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
public class Textbox extends JLabel{
    public int width, height;
    private Color _color;
    private String _text;
    private Font _font;
    private int _fontSizeToUse;


    /**
     * Class used for displaying texts, for buttons or dialogues
     * @param text The text diplayed on screen
     * @param fontName The font to use
     * @param w The total width the text has to cover
     * @param h The total height the text had to cover
     * @param color The colour of the text
     */
    public Textbox(String text, String fontName, int w, int h, Color color){  
        width = w; height = h;
        _text = text;  
        _color = color;
        
        //loadFont(fontName);
        _font = new Font(fontName, Font.PLAIN, 1);

        
        int stringWidth = this.getFontMetrics(_font).stringWidth(_text);
        int componentWidth = width;

        // Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(_font.getSize() * widthRatio);
        int componentHeight = height;

        // Pick a new font size so it will not be larger than the height of label.
        _fontSizeToUse = Math.min(newFontSize, componentHeight);

        // Set the label's font size to the newly determined size.
        _font = new Font(fontName, Font.PLAIN, _fontSizeToUse);
    }

    /**
     * @brief Setter func for a new font color of the current text
     * @param newCol The new Color of the text
     */
    public void setColor(Color newCol){
        _color = newCol;
    }

    public void setNewText(String newText){
        _text = newText;
    }

    public static void loadFont(String fontName){
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/hud/font/rainyhearts.ttf")));
        } 
        catch (FontFormatException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2,int x, int y){
        //TODO: find a way to center text on the button
        g2.setFont(_font);
        g2.setColor(_color);
        g2.drawString(_text, x + _fontSizeToUse, y + (height +_fontSizeToUse)/2);
    }

}
