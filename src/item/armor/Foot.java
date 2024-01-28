package item.armor;

import entity.Player;

public class Foot extends Armor{


    public Foot(){
        name = "Bottes";
        buffDefence=(int) (Math.random()*9);
    }

    public Foot(int _buffDefence){
        name = "Bottes";
        buffDefence=_buffDefence;
    }

    public void addItem(Player player){
        player.foot=this;
        System.out.println("Nouvelle protection : "+this.name);
    }
}
