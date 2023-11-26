package tiles;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
    public boolean isBlocking=false;
    private int _worldX, _worldY;
    public Rectangle hitbox;

    //Display purpose variables
    private final int tileSize=16;
    private final int scale = 3;
    private final int screenSize = tileSize*scale;

    private int _spriteCnt=0;        //Variable responsible for the incrementation of the different sprites
    private int _spriteUpdater=0;    //Variable responsible for the incrementation of the speed of the sprites
    public int spriteSpeed;        //How fast are the sprites changing (higher spriteSpeed means slower time to change)  //spriteSpeed of 0 means it has NO animation
    public int spriteCntMax;       //How many sprite does the entity have

    public BufferedImage[] image;


    public Tile(int spriteCntMax, int spriteSpeed, boolean isBlocking){
        this.spriteCntMax = spriteCntMax;
        this.spriteSpeed = spriteSpeed;
        this.isBlocking = isBlocking;
        image = new BufferedImage[spriteCntMax];
    }


    public void setTexture(BufferedImage[] newTexture){
        this.image = newTexture;
    }

    public void setPos(int x, int y){
        this._worldX = x;
        this._worldY = y;
    }

    public int[] getPos(){
        int tmp[] = new int[2];
        tmp[0] = _worldX;
        tmp[1] = _worldY;

        return tmp;
    }

    public void setSpriteCountAndSpeed(int newSpriteCntMax, int newSpriteSpeed){
        this.spriteCntMax = newSpriteCntMax;
        this.spriteSpeed = newSpriteSpeed;
    }


    public void loadTextures(String name, boolean animated, int i){
        try {
            if(animated){
                for(int j=0; j<spriteCntMax ;j++){
                    image[j] = ImageIO.read(new FileInputStream("res/tiles/animated/"+name+(j+1)+".png"));
                }
            }
            else{
                image[0] = ImageIO.read(new FileInputStream("res/tiles/static/"+name+(i+1)+".png"));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, int screenX, int screenY){
        BufferedImage render = null;

        for(int i=0; i<spriteCntMax; i++){
            if(_spriteCnt == i){
                render = image[i];
            }
        }

        if(spriteSpeed > 0){
            _spriteUpdater++;
            if (_spriteUpdater>spriteSpeed){
                _spriteCnt++;
                if(_spriteCnt == spriteCntMax){
                    _spriteCnt = 0;
                }
                _spriteUpdater=1;
            }
        }
 
        g2.drawImage(render, screenX, screenY,screenSize, screenSize, null);
    }
    
}
