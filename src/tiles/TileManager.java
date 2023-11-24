package tiles;


import game.World;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TileManager {
    public final int tileSize = 16;
    public final int scale = 3;
    public final int screenSize = tileSize*scale;

    //Tiles for textures
    private Tile[] _floorMapTextures;
    private Tile[] _topMapTextures;

    private Tile[][] floorMap;
    private Tile[][] topMap;

    public TileManager(World world){
        _floorMapTextures = new Tile[3];
        _topMapTextures = new Tile[29];
        floorMap = new Tile[world.maxCol][world.maxRow];
        topMap = new Tile[world.maxCol][world.maxRow];
        
        
        loadTextures();
        loadMap("res/maps/debugfloor.txt", world, floorMap, _floorMapTextures);
        loadMap("res/maps/debugtop.txt", world, topMap, _topMapTextures);
    }

    /**
     * Stores in a Tile array (from "start" to "start + size") the textures that are loaded for the game
     * @param name      Name of the texture
     * @param tiles     Array in which to store the textures
     * @param start     Starting point of loading textures for the array
     * @param size      If there are multiple textures with the same name but with variations (ex : grass1, grass2...)
     * @param animated  Boolean if the textures are supposed to be animated (different folder if animated or not)
     * @param spriteCntMax  Number of sprites if the texture is animated (if not then put 1)
     * @param spriteSpeed   Sprite speed if the texture is animated (if not then put 0)
     */
    private void storeTexture(String name, Tile[] tiles, int start, int size, boolean animated, int spriteCntMax, int spriteSpeed){
        for(int i=start; i<size+start; i++){
            Tile tile = new Tile(spriteCntMax, spriteSpeed);
            tiles[i] = tile;
            tiles[i].loadTextures(name, animated, i-start);
        }
    }


    /**
     * Loading all tiles' textures of the game for the map in an array
     */
    private void loadTextures(){ 
        storeTexture("grass",_floorMapTextures,0,3,false,1, 0);

        storeTexture("void",_topMapTextures	,0,1,false,1, 0);

        storeTexture("forest", _topMapTextures, 1,9,false,1, 0);
        storeTexture("tree", _topMapTextures, 10,9,false,1, 0);

        storeTexture("forest_topleft", _topMapTextures,19,3,false,1, 0);
        storeTexture("forest_topright", _topMapTextures,22,2,false,1, 0);
        storeTexture("forest_bottomleft", _topMapTextures,24,2,false,1, 0);
        storeTexture("forest_bottomright", _topMapTextures,26,1,false,1, 0);
        storeTexture("fire", _topMapTextures,27,1,true,7, 15);
    }

    
    /**
     * Reads the txt map file to store the tileMap accordingly 
     * @param filePath  Path of the txt map
     * @param world     World to get the size
     * @param mapTile   The map in which we store the read tiles on the txt
     * @param textures  The textures tile array where we read the textures from
     */
    private void loadMap(String filePath, World world, Tile[][] mapTile, Tile[] textures){
        try {
            File file =  new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader br= new BufferedReader(fileReader);
            

            for(int j = 0; j<world.maxRow; j++){
                String line = br.readLine();
                
                for(int i =0; i<world.maxCol; i++){
                    String numbers[]=line.split("\\s+");
                    int num =Integer.parseInt(numbers[i]);      //Reading the file itself and stocking int read
                    

                    Tile tileCurrent = new Tile(textures[num].spriteCntMax, textures[num].spriteSpeed);   //Creating new tile to store with the according texture
                    tileCurrent.setPos(tileSize*i*scale, tileSize*j*scale);
                    mapTile[i][j] = tileCurrent;        //Setting the tile to the actual map
                    mapTile[i][j].setTexture(textures[num].image);  //Set the current tile texture to what corresponds in the textures array
                    System.out.print(mapTile[i][j].getPos()[0] + " ");
                }
                System.out.println("");
            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void draw(Graphics2D g2,World world, int screenWidth, int screenHeight){
        System.out.println(world.player.worldX + " " + world.player.worldY);
        int playerScreenX = (screenWidth - world.player.screenSize)/2;
        int playerScreenY = (screenHeight - world.player.screenSize)/2;

        //Check for everytile of the map if it needs to be drawn
        for(int j=0;j<world.maxCol;j++){
            for(int i=0;i<world.maxRow;i++){
                int worldX = floorMap[i][j].getPos()[0];
                int worldY = floorMap[i][j].getPos()[1];

                //Checks if the player is close enough to the tile to render it to optimize memory and CPU usage
                if(worldX + tileSize*scale > world.player.worldX - playerScreenX 
                && worldX - tileSize*scale < world.player.worldX + playerScreenX 
                && worldY + tileSize*scale > world.player.worldY - playerScreenY 
                && worldY - tileSize*scale < world.player.worldY + playerScreenY){
                    int screenX = worldX - world.player.worldX + playerScreenX;
                    int screenY = worldY - world.player.worldY + playerScreenY;
                    floorMap[i][j].draw(g2,screenX,screenY);
                    topMap[i][j].draw(g2,screenX,screenY);
                }
            }
        }
    
    }
}
