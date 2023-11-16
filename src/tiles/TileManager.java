package tiles;


import game.World;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TileManager {
    //World world = new World();
    public final int tileSize = 16;
    public final int scale = 3;
    public final int screenSize = tileSize*scale;

    //Tiles for textures
    private Tile[] floorTiles;
    private Tile[] topTiles;

    private int mapTopNum[][];
    private int mapFloorNum[][];

    public TileManager(World world){
        floorTiles = new Tile[3];
        topTiles = new Tile[29];
        mapFloorNum=new int[world.maxCol][world.maxRow];
        mapTopNum=new int[world.maxCol][world.maxRow];
        
        
        loadTextures();
        loadMap("res/maps/debugfloor.txt", world, mapFloorNum);
        loadMap("res/maps/debugtop.txt",world, mapTopNum);
    }

    public void loadTextures(){ //Loading all textures for the map
        //////////////////////////
        //      FLOOR SKINS     //
        //////////////////////////
        //GRASS TEXTURE
        getTileImage(0,3,"grass",floorTiles, 1, -1);

        //////////////////////////////
        //      TOP LAYER SKINS     //
        //////////////////////////////
        //VOID TEXTURE (just 16x16 invisible tile)
        getTileImage(0,1,"void",topTiles, 1, -1);

        //FOREST TEXTURE
        //Principal forest
        getTileImage(1,9,"forest",topTiles, 1, -1);

        //TREE TEXTURE
        getTileImage(10,9,"tree",topTiles, 1, -1);

        //topleft forest
        getTileImage(19,3,"forest_topleft",topTiles, 1, -1);
        //topright forest
        getTileImage(22,2,"forest_topright",topTiles, 1, -1);
        //bottomleft forest
        getTileImage(24,2,"forest_bottomleft",topTiles, 1, -1);
        //bottomright forest
        getTileImage(26,1,"forest_bottomright",topTiles, 1, -1);

        getTileImage(27,1,"fire",topTiles, 7, 20);
    }

    /**
     * 
     * @param start //Starting point of the array    
     * @param size  //How many tiles are there to load
     * @param name  //Name of the png file ex:  grass
     * @param layer //What layer are we writing in
     * @param spriteCntMax //Number of sprites the tile has (if more than 1)
     * @param spriteSpeed //Indicates the speed of animation (set to -1 if no animation)
     */
    private void getTileImage(int start, int size, String name, Tile[] layer, int spriteCntMax, int spriteSpeed){    //Loading specific tiles texture
        for(int i = start;i<start + size; i++){
            layer[i] = new Tile(spriteCntMax, spriteSpeed);
            if(spriteSpeed > -1){       //If the tile is animated, look for the animated folder
                layer[i].loadAnimatedTextures(name);
            }
            else{                       //If tile is not animated, look for the static folder
                layer[i].loadTextures(name,i-start);
            }  
        }
    }

    

    public void loadMap(String filePath, World world,int mapTileNum[][]){
        try {
            File file =  new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader br= new BufferedReader(fileReader);
            for(int i = 0; i<world.maxRow; i++){
                String line = br.readLine();
                for(int j =0; j<world.maxCol; j++){
                    String numbers[]=line.split("\\s+");
                    int num =Integer.parseInt(numbers[j]);

                    mapTileNum[j][i]=num;
                }
            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void draw(Graphics2D g2,World world, int screenWidth, int screenHeight){
        int worldCol =0;
        int worldRow =0;

        while(worldCol < world.maxCol && worldRow < world.maxRow){
            int topNum=mapTopNum[worldCol][worldRow];       //Mapping the floor tiles
            int floorNum=mapFloorNum[worldCol][worldRow];   //Mapping the top layer tiles

            int worldX=worldCol*tileSize;
            int worldY=worldRow*tileSize;

            int screenX=(worldX*scale-world.player.worldX);
            int screenY=(worldY*scale-world.player.worldY);

            //Drawing tiles around the player to optimize the memory

            floorTiles[floorNum].draw(g2,screenX,screenY);
            topTiles[topNum].draw(g2,screenX,screenY);


            worldCol++;
            if(worldCol == world.maxCol){
                worldCol =0;
                worldRow++;
            }
        }
    }
}
