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
    public Tile[] tile;
    public int mapTileNum[][];

    public  TilesManager(GamePanel gp){

        this.gp = gp;

        tile= new Tile[10];
        mapTileNum=new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("res/maps/world01.txt");
    }

    public  void getTileImage(){        //Tiles textures
        try {
            //Grass skin 1
            tile[0]=new Tile();
            tile[0].image= ImageIO.read(new FileInputStream("res/tiles/grass0.png"));

            //Grass skin 2
            tile[1]=new Tile();
            tile[1].image= ImageIO.read(new FileInputStream("res/tiles/grass1.png"));

            //Grass skin 3
            tile[2]=new Tile();
            tile[2].image= ImageIO.read(new FileInputStream("res/tiles/grass2.png"));

            //Sand skin 1
            tile[3] = new Tile();
            tile[3].image= ImageIO.read(new FileInputStream("res/tiles/sign.png"));

            //Forest tree
            tile[4]=new Tile();
            tile[4].image= ImageIO.read(new FileInputStream("res/tiles/forest.png"));

            //Sand skin 2
            tile[5] = new Tile();
            tile[5].image= ImageIO.read(new FileInputStream("res/tiles/sand.png"));

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

            if (worldX+gp.tileSize>gp.player.worldX-gp.player.screenX && worldX-gp.tileSize<gp.player.worldX+gp.player.screenX && worldY+ gp.tileSize>gp.player.worldY-gp.player.screenY && worldY- gp.tileSize<gp.player.worldY+gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol =0;
                worldRow++;
            }
        }
    }
}
