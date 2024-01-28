package item.armor;

import entity.Player;

public class Legs extends Armor{


    public Legs(){
        name = "Cotes de maille";
        buffDefence=(int) (Math.random()*9);
    }

    public Legs(int _buffDefence){
        name = "Cotes de maille";
        buffDefence=_buffDefence;
    }

    public void addItem(Player player){
        player.legs=this;
        System.out.println("Nouvelle protection : "+this.name);
    }
}
