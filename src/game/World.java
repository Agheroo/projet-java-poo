package game;

import entity.Player;
import tiles.TileManager;

import java.awt.*;

/*
 * This class's purpose is to set and to keep track of everything in the game despite its display on the screen 
 * 
 * 
 */
public class World extends Scene{

    private static World instance;
    TileManager tileManager = new TileManager(this);
    //World intialization settings
    
    public final int maxCol= 27, maxRow = 27; //DONT FORGET TO MODIFY WHEN CHANGING THE MAP !!!
    public final int maxWidth = tileManager.tileSize*maxCol;
    public final int maxHeight = tileManager.tileSize*maxRow;
    
    
    //Player settings
    public Player player = new Player(keyH,15*tileManager.tileSize, 26*tileManager.tileSize,0,0,0,"down",4,20);
    
    //All world instances (ennemies NPC mon cul les coffres et tout)
    public static World getWorld(){
        if (instance==null){
            instance=new World();
        }
        return instance;
    }

    private World(){

    }

    public void update(){
        player.update(dt);
        //All updates of entities here
    }
    
    public  void draw(Graphics2D g2,int screenWidth,int screenHeight){
        tileManager.draw(g2, this, screenWidth, screenHeight);
        player.draw(g2,screenWidth/2 - (player.screenSize/2), screenHeight/2 - (player.screenSize/2)); //Player is always centered to screen
    }
}
