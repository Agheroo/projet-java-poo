package tiles;

import javax.imageio.ImageIO;

import game.World;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class TileManager {
    //World world = new World();
    public final int tileSize = 16;
    public final int scale = 3;
    public final int screenSize = tileSize*scale;

    //Tiles for textures
    public Tile[] floorTiles;
    public Tile[] topTiles;

    public int mapTopNum[][];
    public int mapFloorNum[][];

    public void loadTextures(){ //Loading all textures for the map
        //////////////////////////
        //      FLOOR SKINS     //
        //////////////////////////
        //GRASS TEXTURE
        getTileImage(0,3,"grass",floorTiles);


        //////////////////////////////
        //      TOP LAYER SKINS     //
        //////////////////////////////
        //VOID TEXTURE (just 16x16 invisible tile)
        getTileImage(0,1,"void",topTiles);

        //FOREST TEXTURE
        //Principal forest
        getTileImage(1,9,"forest",topTiles);

        //TREE TEXTURE
        getTileImage(10,9,"tree",topTiles);

        //topleft forest
        getTileImage(19,3,"forest_topleft",topTiles);
        //topright forest
        getTileImage(22,2,"forest_topright",topTiles);
        //bottomleft forest
        getTileImage(24,2,"forest_bottomleft",topTiles);
        //bottomright forest
        getTileImage(26,1,"forest_bottomright",topTiles);


        



    }

    /**
     * 
     * @param start //Starting point of the array    
     * @param size  //How many tiles are there
     * @param name  //Name of the png file ex:  grass
     * @param layer //What layer are we writing in
     */
    private void getTileImage(int start, int size, String name, Tile[] layer){    //Loading specific tiles texture
        try{
            for(int i = start;i<start + size; i++){
                layer[i] = new Tile();
                layer[i].image = ImageIO.read(new FileInputStream("res/tiles/"+name+(i-start+1)+".png"));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public TileManager(World world){
        floorTiles = new Tile[3];
        topTiles = new Tile[28];
        mapFloorNum=new int[world.maxCol][world.maxRow];
        mapTopNum=new int[world.maxCol][world.maxRow];
        loadTextures();
        loadMap("res/maps/debugfloor.txt", world, mapFloorNum);
        loadMap("res/maps/debugtop.txt",world, mapTopNum);
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
            int topNum=mapTopNum[worldCol][worldRow];
            int floorNum=mapFloorNum[worldCol][worldRow];

            int worldX=worldCol*tileSize;
            int worldY=worldRow*tileSize;

            int screenX=(worldX*scale-world.player.worldX);
            int screenY=(worldY*scale-world.player.worldY);

            //Drawing tiles around the player to optimize the memory

            g2.drawImage(floorTiles[floorNum].image, screenX, screenY, screenSize, screenSize, null); //Drawing top layer (trees & props...)
            g2.drawImage(topTiles[topNum].image, screenX, screenY, screenSize, screenSize, null); //Drawing top layer (trees & props...)
            worldCol++;

            if(worldCol == world.maxCol){
                worldCol =0;
                worldRow++;
            }
        }
    }
}
