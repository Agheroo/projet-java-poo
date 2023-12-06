package game;

import entity.Enemy;
import entity.Player;

import java.awt.*;

public class FightScene extends Scene{
    Player player;
    Enemy enemy;

    public FightScene(Player _player, Enemy _enemy){
        System.out.println("Je suis en combat");
        player=_player;
        enemy=_enemy;
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
