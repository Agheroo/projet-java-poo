package UI;


/*import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;*/

import java.awt.Graphics2D;
import java.awt.Color;

public class ChoiceButton {
    public int width, height;
    public boolean highlighted;
    private Textbox _textBox;
    private Color _bgColor;
    
    public ChoiceButton(int w,int h, String title, String fontName, Color fontColor){
        this.width = w; this.height = h;
        highlighted = false;

        _textBox = new Textbox(title,fontName,w,h,fontColor);
        _bgColor = new Color(0xA38168);
    }

    /**
     * @brief Setter func to change a button's text color
     * @param newColor The new color to use for the text
     */
    public void setTextColor(Color newColor){
        _textBox.setColor(newColor);
    }

    /**
     * @brief Setter func to change the button's background color
     * @param newColor  The new color to use for the background
     */
    public void setBgColor(Color newColor){
        _bgColor = newColor;
    }

    /**
     * @brief Draws a button (rectangle) on the screen at x,y coords (top left)
     * @param g2 //Graphics2D
     * @param x //x coord
     * @param y //y coord
     */
    public void draw(Graphics2D g2, int x, int y){
        if(highlighted){
            g2.setColor(Color.black);
            g2.fillRect(x-2, y-2, width + 4, height + 4);
        }
        

        g2.setColor(_bgColor);
        g2.fillRect(x,y,width,height);

        _textBox.draw(g2,x,y);
    }
}
