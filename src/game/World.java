package game;

import entity.Player;
import tiles.TileManager;
import entity.EntitySetter;
import entity.Props;
import java.awt.*;

/*
 * This class's purpose is to set and to keep track of everything in the game despite its display on the screen 
 * 
 * 
 */
public class World extends Scene{

    private static World _instance;
    public TileManager tileManager = new TileManager(this);
    //World intialization settings
    
    public final int maxRow= 27, maxCol = 27; //DONT FORGET TO MODIFY WHEN CHANGING THE MAP !!!
    // public final int maxWidth = tileManager.tileSize*maxCol;
    // public final int maxHeight = tileManager.tileSize*maxRow;

    public EntitySetter aSetter = new EntitySetter(this); // Instance of EntitySetter
    //Player settings
    public Props[] obj = new Props[10]; // The array that lists all objects
    public Player player = new Player(15*tileManager.tileSize*tileManager.scale, 15*tileManager.tileSize*tileManager.scale,0,0,0,"down",4,20);
    
    //All world instances (ennemies NPC mon cul les coffres et tout)
    public static World getWorld(){
        if (_instance==null){
            _instance=new World();
            _instance.state = State.WORLD;
        }
        return _instance;
    }

    private World(){

    }
    /**
     * Set up the initial state of the game.
     */
    public void setupGame() {
        aSetter.setObject();
    }

    public void update(){
        checkSceneChange();
        if(state == State.WORLD){
            int playerScreenX = (800 - player.screenSize)/2;
            int playerScreenY = (600 - player.screenSize)/2;

            if(player.worldX + tileManager.tileScreenSize > player.worldX - playerScreenX       //Do all the world updates if they are actually visible on the screen (or near)
            && player.worldX - tileManager.tileScreenSize < player.worldX + playerScreenX 
            && player.worldY + tileManager.tileScreenSize > player.worldY - playerScreenY 
            && player.worldY - tileManager.tileScreenSize < player.worldY + playerScreenY){
                
                player.update(this, dt);
                tileManager.update(this, 800, 600);

                //otherentity.update(this,dt);
            }
            




            //Checks if player is touching the edges of the map
            //TODO: collision checker in entity class of Akim's branch
            if(player.worldX + player.hitbox.width/2 < 0){
                player.worldX = 0 - player.hitbox.width/2;
            }
            if(player.worldX - player.hitbox.width/2 >= (maxCol-1)*tileManager.tileSize*tileManager.scale){
                player.worldX = (maxCol-1)*tileManager.tileSize*tileManager.scale + player.hitbox.width/2;
            }
            if(player.worldY + player.hitbox.height/2< 0){
                player.worldY = 0 - player.hitbox.height/2;
            }
            if(player.worldY - player.hitbox.height/2 >= (maxRow-1)*tileManager.tileSize*tileManager.scale){
                player.worldY = (maxRow-1)*tileManager.tileSize*tileManager.scale + player.hitbox.height/2;
            }
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
        player.drawInWorld(g2,screenWidth/2 - (player.screenSize/2), screenHeight/2 - (player.screenSize/2)); //Player is always centered to screen
    }
}
