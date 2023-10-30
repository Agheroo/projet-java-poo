package main;

import entity.Player;
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
    public final int maxCol= 64, maxRow = 64;
    public final int maxWidth = tileManager.tileSize*maxCol;
    public final int maxHeight = tileManager.tileSize*maxRow;
    
    
    //Player settings
    public KeyHandler keyH = new KeyHandler();
    public Player player = new Player(keyH,5*tileManager.tileSize, 5*tileManager.tileSize,0,0,0,"down",0,1);
    
    public World(){
        
    }

    public void update(){
        player.update(dt);
    }
}
