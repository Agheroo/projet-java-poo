package game;

import entity.Player;
import main.KeyHandler;
import tiles.TileManager;

/*
 * This class's purpose is to set and to keep track of everything in the game despite its display on the screen 
 * 
 * 
 */
public class World {
    TileManager tileManager = new TileManager(this);
    //World intialization settings
    public double dt = 0;
    public final int maxCol= 27, maxRow = 27; //DONT FORGET TO MODIFY WHEN CHANGING THE MAP !!!
    public final int maxWidth = tileManager.tileSize*maxCol;
    public final int maxHeight = tileManager.tileSize*maxRow;
    
    
    //Player settings
    public KeyHandler keyH = new KeyHandler();
    public Player player = new Player(keyH,15*tileManager.tileSize, 26*tileManager.tileSize,0,0,0,"down",0,1);
    
    //All world instances (ennemies NPC mon cul les coffres et tout)


    public World(){
        
    }

    public void update(){
        player.update(dt);
        //All updates of entities here
    }
}
