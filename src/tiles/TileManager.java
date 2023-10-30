package tiles;

import main.World;
import javax.imageio.ImageIO;

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


    public TileManager(World world){
        floorTiles = new Tile[10];
        topTiles = new Tile[10];
        mapFloorNum=new int[world.maxCol][world.maxRow];
        mapTopNum=new int[world.maxCol][world.maxRow];
        getTileImage();
        loadMap("res/maps/top.txt", world, mapTopNum);
        loadMap("res/maps/tmpfloor.txt",world, mapFloorNum);
    }

    public  void getTileImage(){        //Tiles textures
        try {
            //////////////////////////
            //      FLOOR SKINS     //
            //////////////////////////
            //Grass skin 1
            floorTiles[0]=new Tile();
            floorTiles[0].image= ImageIO.read(new FileInputStream("res/tiles/grass0.png"));
            //Grass skin 2
            floorTiles[1]=new Tile();
            floorTiles[1].image= ImageIO.read(new FileInputStream("res/tiles/grass1.png"));
            //Grass skin 3
            floorTiles[2]=new Tile();
            floorTiles[2].image= ImageIO.read(new FileInputStream("res/tiles/grass2.png"));

            //////////////////////////////
            //      TOP LAYER SKINS     //
            //////////////////////////////
            
            topTiles[0]=new Tile();
            topTiles[0].image= ImageIO.read(new FileInputStream("res/tiles/void.png"));
            topTiles[1]=new Tile();
            topTiles[1].image= ImageIO.read(new FileInputStream("res/tiles/forest1.png"));
            topTiles[2]=new Tile();
            topTiles[2].image= ImageIO.read(new FileInputStream("res/tiles/forest2.png"));
            topTiles[3]=new Tile();
            topTiles[3].image= ImageIO.read(new FileInputStream("res/tiles/forest3.png"));
            topTiles[4]=new Tile();
            topTiles[4].image= ImageIO.read(new FileInputStream("res/tiles/forest4.png"));
            topTiles[5]=new Tile();
            topTiles[5].image= ImageIO.read(new FileInputStream("res/tiles/forest5.png"));
            topTiles[6]=new Tile();
            topTiles[6].image= ImageIO.read(new FileInputStream("res/tiles/forest6.png"));
            topTiles[7]=new Tile();
            topTiles[7].image= ImageIO.read(new FileInputStream("res/tiles/forest7.png"));
            topTiles[8]=new Tile();
            topTiles[8].image= ImageIO.read(new FileInputStream("res/tiles/forest8.png"));
            topTiles[9]=new Tile();
            topTiles[9].image= ImageIO.read(new FileInputStream("res/tiles/forest9.png"));


        }catch (IOException e){
            e.printStackTrace();
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
                    String numbers[]=line.split(" ");
                    int num =Integer.parseInt(numbers[j]);

                    mapTileNum[j][i]=num;
                }
            }

            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2,World world){
        int worldCol =0;
        int worldRow =0;

        while (worldCol < world.maxCol && worldRow < world.maxRow){

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
