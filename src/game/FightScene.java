package game;

import entity.Enemy;
import entity.Player;

import java.awt.*;

public class FightScene extends Scene{
    public Player player;
    public Enemy enemy;

    public FightScene(Player player, Enemy enemy){
        System.out.println("Je suis en combat");
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void update(){
        //player.update(dt);
    }

    @Override
    public void draw(Graphics2D g2, int screenWidth, int screenHeight){
        player.draw(g2,screenWidth/2 - (player.screenSize/2), screenHeight/2 - (player.screenSize/2));
    }
}
