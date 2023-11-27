package game;

import entity.Player;
import entity.Props;
import entity.EntitySetter;
import tiles.TileManager;

import java.awt.*;

/*
 * This class's purpose is to set and to keep track of everything in the game despite its display on the screen 
 * 
 * 
 */
public class World extends Scene{

    private static World _instance;
    TileManager tileManager = new TileManager(this);
    //World intialization settings
    
    public final int maxRow= 27, maxCol = 27; //DONT FORGET TO MODIFY WHEN CHANGING THE MAP !!!
    // public final int maxWidth = tileManager.tileSize*maxCol;
    // public final int maxHeight = tileManager.tileSize*maxRow;
    
    
    //Player settings
    public EntitySetter aSetter = new EntitySetter(this); // Instance de l'EntitySetter
    public Player player = new Player(15*tileManager.tileSize*tileManager.scale, 15*tileManager.tileSize*tileManager.scale,0,0,0,"down",4,20);
    public Props[] obj = new Props[10]; // Le tableau qui ressence tous les objets
    //All world instances (ennemies NPC mon cul les coffres et tout)
    public static World getWorld(){
        if (_instance==null){
            _instance=new World();
        }
        return _instance;
    }

    private World(){

    }
    public void setupGame() {
        aSetter.setObject();
    }
    public void update(){
        player.update(_instance, dt);
        //All updates of entities here




        //Checks if player is touching the edges of the map
        if(player.worldX < 0){
            player.worldX=0;
        }
        if(player.worldX > (maxCol-1)*tileManager.tileSize*tileManager.scale){
            player.worldX = (maxCol-1)*tileManager.tileSize*tileManager.scale;
        }
        if(player.worldY < 0){
            player.worldY = 0;
        }
        if(player.worldY >= (maxRow-1)*tileManager.tileSize*tileManager.scale){
            player.worldY = (maxRow-1)*tileManager.tileSize*tileManager.scale;
        }
    }
    
    public  void draw(Graphics2D g2,int screenWidth,int screenHeight){
        // TILE
        tileManager.draw(g2, this, screenWidth, screenHeight);
        // OBJECT
        for (Props props : obj) {
            if (props != null) {
                props.draw(g2, this);
            }
        }
        // PLAYER
        player.draw(g2,screenWidth/2 - (player.screenSize/2), screenHeight/2 - (player.screenSize/2)); //Player is always centered to screen
    }
}
