package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HUD {
    private BufferedImage image = null;
    public final int screenSizeWidth = 125*3; 
    public final int screenSizeHeight = 65*3;

    public HUD(){
        loadTextures();
    }

    public void loadTextures(){
        try{
            image = ImageIO.read(new FileInputStream("res/hud/pause.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, int screenX, int screenY){
        g2.drawImage(image, screenX, screenY,screenSizeWidth, screenSizeHeight, null);
    }
}
