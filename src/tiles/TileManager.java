package tiles;

import game.World;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * The TileManager class manages tiles for the game, including loading textures and rendering the map.
 */
public class TileManager {
    public final int tileSize = 16;
    public final int scale = 3;
    public final int screenSize = tileSize * scale;

    // Tiles for textures
    private final int nbFloorTextures = 6;
    private final int nbTopTextures = 29;

    private Tile[] _floorMapTextures;
    private Tile[] _topMapTextures;

    private Tile[][] _floorMap;
    private Tile[][] _topMap;

    /**
     * Constructor for TileManager, initializes textures and loads the map.
     *
     * @param world The World object for obtaining the map size.
     */
    public TileManager(World world) {
        _floorMapTextures = new Tile[nbFloorTextures];
        _topMapTextures = new Tile[nbTopTextures];

        //Map itself
        _floorMap = new Tile[world.maxRow][world.maxCol];
        _topMap = new Tile[world.maxRow][world.maxCol];

        loadTextures();
        loadMap("res/maps/debugfloor.txt", world, _floorMap, _floorMapTextures);
        loadMap("res/maps/debugtop.txt", world, _topMap, _topMapTextures);
    }

    /**
     * Stores textures in a Tile array.
     *
     * @param name         Name of the texture.
     * @param tiles        Array to store the textures.
     * @param start        Starting point for loading textures for the array.
     * @param size         Number of textures to load.
     * @param animated     Boolean indicating if the textures are animated.
     * @param spriteCntMax Number of sprites if the texture is animated.
     * @param spriteSpeed  Sprite speed if the texture is animated.
     */
    private void storeTexture(String name, Tile[] tiles, int start, int size, boolean animated, int spriteCntMax, int spriteSpeed,boolean collision) {
        for (int i = start; i < size + start; i++) {
            Tile tile = new Tile(spriteCntMax, spriteSpeed);
            tiles[i] = tile;
            tiles[i].setCollision(collision);
            tiles[i].loadTextures(name, animated, i-start);
        }
    }

    /**
     * Loads all tiles' textures for the map in an array.
     */
    private void loadTextures(){
        //Floor map textures
        storeTexture("grass",_floorMapTextures,0,3,false,1, 0,false);
        //Decoration textures (plants and tall grass)
        storeTexture("grass0",_floorMapTextures,3,1,true,5, 20,false);
        storeTexture("grass1",_floorMapTextures,4,1,true,5, 20,false);
        storeTexture("flower0",_floorMapTextures,5,1,true,6, 20,false);

        //Top map textures (trees & forest)
        storeTexture("void",_topMapTextures	,0,1,false,1, 0,false);

        storeTexture("forest", _topMapTextures, 1,9,false,1, 0,true);
        storeTexture("tree", _topMapTextures, 10,9,false,1, 0,true);

        storeTexture("forest_topleft", _topMapTextures,19,3,false,1, 0,true);
        storeTexture("forest_topright", _topMapTextures,22,2,false,1, 0,true);
        storeTexture("forest_bottomleft", _topMapTextures,24,2,false,1, 0,true);
        storeTexture("forest_bottomright", _topMapTextures,26,1,false,1, 0,true);
        storeTexture("fire", _topMapTextures,27,1,true,7, 15,true);


        

    }

    /**
     * Reads the txt map file to store the tileMap accordingly.
     *
     * @param filePath Path of the txt map.
     * @param world    World to get the size.
     * @param mapTile  The map in which to store the read tiles on the txt.
     * @param textures The textures tile array where we read the textures from.
     */
    private void loadMap(String filePath, World world, Tile[][] mapTile, Tile[] textures) {
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);

            for (int i = 0; i < world.maxRow; i++) {
                String line = br.readLine();

                for (int j = 0; j < world.maxCol; j++) {
                    String numbers[] = line.split("\\s+");
                    int num = Integer.parseInt(numbers[j]); // Reading the file itself and stocking int read

                    Tile tileCurrent = new Tile(textures[num].spriteCntMax, textures[num].spriteSpeed);   //Creating new tile to store with the according texture
                    tileCurrent.setPos(tileSize*j*scale, tileSize*i*scale);
                    mapTile[i][j] = tileCurrent;        //Setting the tile to the actual map
                    mapTile[i][j].setCollision(textures[num].getCollision());
                    mapTile[i][j].setTexture(textures[num].image);  //Set the current tile texture to what corresponds in the textures array

                    //System.out.print(num + " ");
                }
                //System.out.println("");
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws tiles on the screen based on the player's position.
     *
     * @param g2            Graphics2D object for drawing.
     * @param world         World object to get player information.
     * @param screenWidth   Screen width.
     * @param screenHeight  Screen height.
     */
    public void draw(Graphics2D g2, World world, int screenWidth, int screenHeight) {
        int playerScreenX = (screenWidth - world.player.screenSize) / 2;
        int playerScreenY = (screenHeight - world.player.screenSize) / 2;

        // Check for every tile of the map if it needs to be drawn
        for (int i = 0; i < world.maxRow; i++) {
            for (int j = 0; j < world.maxCol; j++) {
                int worldX = _floorMap[i][j].getPos()[0];
                int worldY = _floorMap[i][j].getPos()[1];

                // Checks if the player is close enough to the tile to render it for optimization
                if (worldX + tileSize * scale > world.player.worldX - playerScreenX
                        && worldX - tileSize * scale < world.player.worldX + playerScreenX
                        && worldY + tileSize * scale > world.player.worldY - playerScreenY
                        && worldY - tileSize * scale < world.player.worldY + playerScreenY) {
                    int screenX = worldX - world.player.worldX + playerScreenX;
                    int screenY = worldY - world.player.worldY + playerScreenY;
                    _floorMap[i][j].draw(g2, screenX, screenY);
                    _topMap[i][j].draw(g2, screenX, screenY);
                }
            }
        }

    }

    public Tile getTile(int x,int y){
        int param=tileSize*scale;
        int i=x/param;
        int j=y/param;

        return _topMap[j][i];
    }
}
