package tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class TilesManager {
    GamePanel gp;

    //Tiles for textures
    public Tile[] floorTiles;
    public Tile[] topTiles;

    public int mapTileNum[][];


    public  TilesManager(GamePanel gp){
        this.gp = gp;
        floorTiles= new Tile[10];
        topTiles = new Tile[10];
        mapTileNum=new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("res/maps/top.txt");
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
            topTiles[9]=new Tile();
            topTiles[9].image= ImageIO.read(new FileInputStream("res/tiles/void.png"));
            topTiles[0]=new Tile();
            topTiles[0].image= ImageIO.read(new FileInputStream("res/tiles/forest1.png"));
            topTiles[1]=new Tile();
            topTiles[1].image= ImageIO.read(new FileInputStream("res/tiles/forest2.png"));
            topTiles[2]=new Tile();
            topTiles[2].image= ImageIO.read(new FileInputStream("res/tiles/forest3.png"));
            topTiles[3]=new Tile();
            topTiles[3].image= ImageIO.read(new FileInputStream("res/tiles/forest4.png"));
            topTiles[4]=new Tile();
            topTiles[4].image= ImageIO.read(new FileInputStream("res/tiles/forest5.png"));
            topTiles[5]=new Tile();
            topTiles[5].image= ImageIO.read(new FileInputStream("res/tiles/forest6.png"));
            topTiles[6]=new Tile();
            topTiles[6].image= ImageIO.read(new FileInputStream("res/tiles/forest7.png"));
            topTiles[7]=new Tile();
            topTiles[7].image= ImageIO.read(new FileInputStream("res/tiles/forest8.png"));
            topTiles[8]=new Tile();
            topTiles[8].image= ImageIO.read(new FileInputStream("res/tiles/forest9.png"));


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try {
            File file =  new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader br= new BufferedReader(fileReader);

            int col=0;
            int row=0;

            while (col< gp.maxWorldCol && row<gp.maxWorldRow){

                String line = br.readLine();

                while (col < gp.maxWorldCol){

                    String numbers[]=line.split(" ");

                    int num =Integer.parseInt(numbers[col]);

                    mapTileNum[col][row]=num;
                    col++;
                }
                if (col== gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void  draw(Graphics2D g2){

        int worldCol =0;
        int worldRow =0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum=mapTileNum[worldCol][worldRow];

            int worldX=worldCol*gp.tileSize;
            int worldY=worldRow*gp.tileSize;
            int screenX=worldX-gp.player.worldX+gp.player.screenX;
            int screenY=worldY-gp.player.worldY+gp.player.screenY;

            //Drawing tiles around the player to optimize the memory
            if (worldX+gp.tileSize>gp.player.worldX-gp.player.screenX && worldX-gp.tileSize<gp.player.worldX+gp.player.screenX 
            && worldY+ gp.tileSize>gp.player.worldY-gp.player.screenY && worldY- gp.tileSize<gp.player.worldY+gp.player.screenY) {
                //g2.drawImage(floorTiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);  //Drawing floor first
                g2.drawImage(topTiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null); //Drawing top layer (trees & props...)
            }
            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol =0;
                worldRow++;
            }
        }
    }
}
