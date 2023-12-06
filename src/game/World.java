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
    private static World _instance;
    TileManager tileManager = new TileManager(this);
    //World intialization settings
    
    public final int maxRow= 27, maxCol = 27; //DONT FORGET TO MODIFY WHEN CHANGING THE MAP !!!
    
    
    //Player settings

    public Player player = new Player(15*tileManager.tileSize*tileManager.scale, 15*tileManager.tileSize*tileManager.scale,0,0,0,"down",4,20);
    //All world instances (ennemies NPC mon cul les coffres et tout)



    public static World getWorld(){
        if (_instance==null){
            _instance=new World();
            _instance.state = State.WORLD;
        }
        return _instance;
    }

    public void update(){
        checkSceneChange();
        if(state == State.WORLD){
            player.update(this, dt);
            tileManager.update(this, 800, 600);
            //All updates of entities here




            //Checks if player is touching the edges of the map
            if(player.worldX + player.hitbox.width < 0){
                player.worldX = -player.hitbox.width;
            }
            if(player.worldX > (maxCol-1)*tileManager.tileSize*tileManager.scale + player.hitbox.width ){
                player.worldX = (maxCol-1)*tileManager.tileSize*tileManager.scale + player.hitbox.width;
            }
            if(player.worldY + player.hitbox.height < 0){
                player.worldY = -player.hitbox.height;
            }
            if(player.worldY >= (maxRow-1)*tileManager.tileSize*tileManager.scale + player.hitbox.height){
                player.worldY = (maxRow-1)*tileManager.tileSize*tileManager.scale + player.hitbox.height;
            }
        }
        
    }
    
    public  void draw(Graphics2D g2,int screenWidth,int screenHeight){
        if(state == State.WORLD){
            tileManager.draw(g2, this, screenWidth, screenHeight);
            player.drawInWorld(g2,screenWidth/2 - (player.screenSize/2), screenHeight/2 - (player.screenSize/2)); //Player is always centered to screen
        }

        if(state == State.PAUSE){
            tileManager.draw(g2, this, screenWidth, screenHeight);
            player.drawInWorld(g2,screenWidth/2 - (player.screenSize/2), screenHeight/2 - (player.screenSize/2)); //Player is always centered to screen
            
            //System.out.println("GAME PAUSED");
        }
    }
}
