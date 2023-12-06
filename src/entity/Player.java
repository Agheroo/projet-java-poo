package entity;


import game.Scene;
import game.Scene.State;

public class Player extends Entity{
    int hasKey = 0;

    public Player(int worldX,int worldY,int dirX,int dirY,int speed,String facing, int spriteCntMax, int spriteSpeed){
        super(worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);  //Calls the parent class as for every entity setup but need to specify scene.keyH for player


        loadTextures("player");
    }

    public void update(Scene scene, double dt){
        super.update(scene,dt);
        //World updates
        if(scene.keyH.upPressed || scene.keyH.downPressed || scene.keyH.leftPressed || scene.keyH.rightPressed){
            dirX = 0;
            dirY = 0;
            if(scene.keyH.leftPressed){
                dirX = -1;
                facing = "left";
            }
            if(scene.keyH.rightPressed){
                dirX = 1;   
                facing = "right";
            }
            if(scene.keyH.upPressed){
                dirY = -1;
                facing = "up";
            }
            if(scene.keyH.downPressed){
                dirY = 1;
                facing = "down";
            }
            accelerate(30,dt);
        }
        else{
            if(speed >0){
                decelerate(dt);
            }
            
        }
        move(speed,dt);



        //Fightscene updates
        if(scene.state == State.FIGHT){

        }
    
        


    }
}
