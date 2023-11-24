package CreationCombatScene.entity;

import entity.Player;

public class PlayerTest extends Player {


    public PlayerTest(){
        super(0,0,0,0,0," ",0,0);
    }

    public  void  update(double dt){

        System.out.println("Je suis modifié");

    }
}
