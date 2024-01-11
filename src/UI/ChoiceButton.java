package UI;


/*import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;*/

import java.awt.Graphics2D;
import java.awt.Color;

public class ChoiceButton {
    public int width, height;
    private Textbox _textBox;
    
    public ChoiceButton(int w,int h, String title, String fontName, Color fontColor){
        this.width = w; this.height = h;

        _textBox = new Textbox(title,fontName,w,h,fontColor);

        //loadTexture();
    }

    /**
     * @brief Setter func to change a button's text color
     * @param newColor The new color to use for the text
     */
    public void setTextColor(Color newColor){
        _textBox.setColor(newColor);
    }

    /*private void loadTexture(){
        try{
            _bgTexture = ImageIO.read(new FileInputStream("res/hud/bg.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }*/

    /**
     * @brief Draws a button (rectangle) on the screen at x,y coords (top left)
     * @param g2 //Graphics2D
     * @param x //x coord
     * @param y //y coord
     */
    public void draw(Graphics2D g2, int x, int y){
        g2.setColor(new Color(0xA38168));
        g2.fillRect(x,y,width,height);

        _textBox.draw(g2,x,y);
    }
}
