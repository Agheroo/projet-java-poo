package item.armor;

import entity.Player;

public class Body extends Armor{

    public Body(){
        name = "Plastron";
        buffDefence=(int) (Math.random()*9);
    }

    public Body(int _buffDefence){
        name = "Plastron";
        buffDefence=_buffDefence;
    }

    public void addItem(Player player){
        player.body=this;
        System.out.println("Nouvelle protection : "+this.name);
    }
}
