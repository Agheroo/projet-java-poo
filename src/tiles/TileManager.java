/**
 * @file TileManager.java
 * @brief This file contains the implementation of the TileManager class, which manages tiles in the game world.
 */

package tiles;

import game.Const;
import game.World;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @class TileManager
 * @brief Manages tiles in the game world.
 */
public class TileManager {


    private Tile[] _floorMapTextures;
    private Tile[] _topMapTextures;

    private Tile[][] _floorMap;
    private Tile[][] _topMap;

    /**
     * Constructor for the TileManager class.
     *
     * @param world The world for which tiles are managed.
     */
    public TileManager(World world) {
        // Textures
        _floorMapTextures = new Tile[Const.nbFloorTextures];
        _topMapTextures = new Tile[Const.nbTopTextures];

        // Map itself
        _floorMap = new Tile[Const.WRLD_maxRow][Const.WRLD_maxCol];
        _topMap = new Tile[Const.WRLD_maxRow][Const.WRLD_maxCol];

        loadTextures();
        loadMap("res/maps/test_floor.csv", world, _floorMap, _floorMapTextures);
        loadMap("res/maps/test_top.csv", world, _topMap, _topMapTextures);
    }

    /**
     * Gets the tile of the top map with its x, y coordinates.
     *
     * @param x X-coordinate of the tile.
     * @param y Y-coordinate of the tile.
     * @return The corresponding tile of the top map.
     */
    public Tile getTile(int x, int y) {
        int param = Const.WRLD_tileScreenSize;
        int i = x / param;
        int j = y / param;

        return _topMap[j][i];
    }

    /**
     * Stores in a Tile array (from "start" to "start + size") the textures that are loaded for the game.
     *
     * @param name       Name of the texture.
     * @param tiles      Array in which to store the textures.
     * @param start      Starting point of loading textures for the array.
     * @param size       If there are multiple textures with the same name but with variations (ex : grass1, grass2...).
     * @param animated   Boolean if the textures are supposed to be animated (different folder if animated or not).
     * @param spriteCntMax Number of sprites if the texture is animated (if not, then put 1).
     * @param spriteSpeed   Sprite speed if the texture is animated (if not, then put 0).
     * @param isBlocking    Boolean to make the player collide with the tile or not.
     */
    private void storeTexture(String name, Tile[] tiles, int start, int size, boolean animated, int spriteCntMax,
                              int spriteSpeed, boolean isBlocking) {
        for (int i = start; i < size + start; i++) {
            Tile tile = new Tile(spriteCntMax, spriteSpeed, isBlocking, i);
            tiles[i] = tile;
            tiles[i].loadTextures(name, animated, i - start);
        }
    }

    /**
     * Loading all tiles' textures of the game for the map in an array.
     */
    private void loadTextures() {
        // Floor map textures
        storeTexture("void", _floorMapTextures, 0, 1, false, 1, 0, false);
        storeTexture("grass", _floorMapTextures, 1, 3, false, 1, 0, false);
        // Decoration textures (plants and tall grass)
        storeTexture("grass0", _floorMapTextures, 4, 1, true, 5, 20, false);
        storeTexture("grass1", _floorMapTextures, 5, 1, true, 5, 20, false);
        storeTexture("flower0", _floorMapTextures, 6, 1, true, 6, 20, false);

        // Top map textures (trees & forest)
        storeTexture("void", _topMapTextures, 0, 1, false, 1, 0, false);

        storeTexture("forest", _topMapTextures, 1, 9, false, 1, 0, true);
        storeTexture("tree", _topMapTextures, 10, 9, false, 1, 0, true);

        storeTexture("fire", _topMapTextures, 19, 1, true, 7, 15, true);
    }

    /**
     * Reads the txt map file to store the tileMap accordingly.
     *
     * @param filePath Path of the txt map.
     * @param world    World to get the size.
     * @param mapTile  The map in which we store the read tiles on the txt.
     * @param textures The textures tile array where we read the textures from.
     */
    private void loadMap(String filePath, World world, Tile[][] mapTile, Tile[] textures) {
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);

            for (int i = 0; i < Const.WRLD_maxRow; i++) {
                String line = br.readLine();

                for (int j = 0; j < Const.WRLD_maxCol; j++) {
                    String[] numbers = line.split(",");
                    int num = 1 + Integer.parseInt(numbers[j]); // Reading the file itself and stocking int read
                    Tile tileCurrent = new Tile(textures[num].spriteCntMax, textures[num].spriteSpeed,
                            textures[num].getCollision(), num); // Creating new tile to store with the according texture
                    tileCurrent.setPos(Const.WRLD_tileScreenSize * j, Const.WRLD_tileScreenSize * i);

                    mapTile[i][j] = tileCurrent; // Setting the tile to the actual map
                    mapTile[i][j].setTexture(textures[num].image); // Set the current tile texture to what corresponds
                    // in the textures array
                    mapTile[i][j].setCollision(textures[num].getCollision()); // Set the collision factor to the tile

                    // System.out.print(num + " ");
                }
                // System.out.println("");
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the tile frames based on the player's position.
     *
     * @param world        The world object.
     */
    public void update(World world) {
        int playerScreenX = (Const.WDW_width - Const.WRLD_entityScreenSize) / 2;
        int playerScreenY = (Const.WDW_height - Const.WRLD_entityScreenSize) / 2;

        for (int i = 0; i < Const.WRLD_maxRow; i++) {
            for (int j = 0; j < Const.WRLD_maxCol; j++) {
                int worldX = _floorMap[i][j].getPos()[0];
                int worldY = _floorMap[i][j].getPos()[1];

                // Checks if the player is close enough to the tile to render it to optimize memory and CPU usage
                if (worldX + Const.WRLD_tileScreenSize > World.player.worldX - playerScreenX
                        && worldX - Const.WRLD_tileScreenSize < World.player.worldX + playerScreenX
                        && worldY + Const.WRLD_tileScreenSize > World.player.worldY - playerScreenY
                        && worldY - Const.WRLD_tileScreenSize < World.player.worldY + playerScreenY) {
                    _floorMap[i][j].updateFrames();
                    _topMap[i][j].updateFrames();
                }
            }
        }
    }

    /**
     * Draws the tiles in the game world.
     *
     * @param g2           The graphics context.
     * @param world        The world object.
     */
    public void draw(Graphics2D g2, World world) {
        int playerScreenX = (Const.WDW_width - Const.WRLD_entityScreenSize) / 2;
        int playerScreenY = (Const.WDW_height - Const.WRLD_entityScreenSize) / 2;

        // Check for every tile of the map if it needs to be drawn
        for (int i = 0; i < Const.WRLD_maxRow; i++) {
            for (int j = 0; j < Const.WRLD_maxCol; j++) {
                int worldX = _floorMap[i][j].getPos()[0];
                int worldY = _floorMap[i][j].getPos()[1];

                // Checks if the player is close enough to the tile to render it to optimize memory and CPU usage
                if (worldX + Const.WRLD_tileScreenSize > World.player.worldX - playerScreenX
                        && worldX - Const.WRLD_tileScreenSize < World.player.worldX + playerScreenX
                        && worldY + Const.WRLD_tileScreenSize > World.player.worldY - playerScreenY
                        && worldY - Const.WRLD_tileScreenSize < World.player.worldY + playerScreenY) {
                    int screenX = worldX - World.player.worldX + playerScreenX;
                    int screenY = worldY - World.player.worldY + playerScreenY;
                    _floorMap[i][j].draw(g2, screenX, screenY);
                    _topMap[i][j].draw(g2, screenX, screenY);
                }
            }
        }
    }
}
