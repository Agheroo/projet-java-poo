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
    private Tile[] floorMapTextures;
    private Tile[] topMapTextures;

    private Tile[][] floorMap;
    private Tile[][] topMap;

    public TileManager(World world){
        floorMapTextures = new Tile[3];
        topMapTextures = new Tile[29];
        floorMap = new Tile[world.maxCol][world.maxRow];
        topMap = new Tile[world.maxCol][world.maxRow];
        
        
        loadTextures();
        loadMap("res/maps/debugfloor.txt", world, floorMap, floorMapTextures);
        loadMap("res/maps/debugtop.txt", world, topMap, topMapTextures);
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
        storeTexture("grass",floorMapTextures,0,3,false,1, 0);

        storeTexture("void",topMapTextures	,0,1,false,1, 0);

        storeTexture("forest", topMapTextures, 1,9,false,1, 0);
        storeTexture("tree", topMapTextures, 10,9,false,1, 0);

        storeTexture("forest_topleft", topMapTextures,19,3,false,1, 0);
        storeTexture("forest_topright", topMapTextures,22,2,false,1, 0);
        storeTexture("forest_bottomleft", topMapTextures,24,2,false,1, 0);
        storeTexture("forest_bottomright", topMapTextures,26,1,false,1, 0);
        storeTexture("fire", topMapTextures,27,1,true,7, 15);
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
            

            for(int i = 0; i<world.maxRow; i++){
                String line = br.readLine();
                
                for(int j =0; j<world.maxCol; j++){
                    String numbers[]=line.split("\\s+");
                    int num =Integer.parseInt(numbers[j]);      //Reading the file itself and stocking int read
                    

                    Tile tileCurrent = new Tile(textures[num].spriteCntMax, textures[num].spriteSpeed);   //Creating new tile to store with the according texture
                    tileCurrent.worldX = j*tileSize;
                    tileCurrent.worldY = i*tileSize;

                    mapTile[j][i] = tileCurrent;        //Setting the tile to the actual map
                    mapTile[j][i].setTexture(textures[num].image);  //Set the current tile texture to what corresponds in the textures array
                }
            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void draw(Graphics2D g2,World world, int screenWidth, int screenHeight){

        //Need to optimize memory with rendering that's only visible for the player instead of rendering all the map at once
        for(int i=0; i<world.maxRow; i++){
            for(int j=0; j<world.maxCol; j++){
                int worldX=j*tileSize;
                int worldY=i*tileSize;

                int screenX=(worldX*scale-world.player.worldX);
                int screenY=(worldY*scale-world.player.worldY);

                floorMap[j][i].draw(g2, screenX, screenY);
                topMap[j][i].draw(g2, screenX, screenY);
            }
        }
    }
}
