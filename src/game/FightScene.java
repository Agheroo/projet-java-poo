package game;

import entity.Ennemy;
import entity.Player;

import java.awt.*;

public class FightScene extends Scene{
    Player player;
    Ennemy ennemy;

    public FightScene(Player _player, Ennemy _ennemy){
        System.out.println("Je suis en combat");
        player=_player;
        ennemy=_ennemy;
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
