package game;

import entity.Player;
import entity.Ennemy;

public class CreateScene {

    public static Scene creator(){

        return World.getWorld();

    }

    public static Scene creator(Player player,Ennemy ennemy){

        return new FightScene(player,ennemy);
    }



}
