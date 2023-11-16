package tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
    //Display purpose variables
    private final int tileSize=16;
    private final int scale = 3;
    public final int screenSize = tileSize*scale;

    private int spriteCnt=0;        //Variable responsible for the incrementation of the different sprites
    private int spriteUpdater=0;    //Variable responsible for the incrementation of the speed of the sprites
    private int spriteSpeed;        //How fast are the sprites changing (higher spriteSpeed means slower time to change)
    //spriteSpeed of -1 means it has NO animation
    private int spriteCntMax;       //How many sprite does the entity have


    public BufferedImage[] image;
    public boolean collision=false;

    public Tile(int spriteCntMax, int spriteSpeed){
        this.spriteCntMax = spriteCntMax;
        this.spriteSpeed = spriteSpeed;
        image = new BufferedImage[spriteCntMax];
    }

    public void loadAnimatedTextures(String name, int i){
        try {
            for(int j=0; j<spriteCntMax ;j++){
                image[j] = ImageIO.read(new FileInputStream("res/tiles/animated/"+name+(i+1)+".png"));
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadTextures(String name, int i){
        try {
            image[0] = ImageIO.read(new FileInputStream("res/tiles/static/"+name+(i+1)+".png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, int screenX, int screenY){
        if(spriteSpeed > -1){
            spriteUpdater++;
            if (spriteUpdater>spriteSpeed){
                spriteCnt++;
                if(spriteCnt == spriteCntMax){
                    spriteCnt = 0;
                }
                spriteUpdater=0;
            }
        }
        
        g2.drawImage(image[spriteCnt], screenX, screenY,screenSize, screenSize, null);
    }
    
}
