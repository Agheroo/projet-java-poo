package game;

import entity.Player;
import entity.Enemy;

public class CreateScene {

    public static Scene creator(){

        return World.getWorld();

    }

    public static Scene creator(Player player,Enemy ennemy){

        return new FightScene(player,ennemy);
    }



}
