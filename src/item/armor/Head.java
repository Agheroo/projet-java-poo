package item.armor;

import entity.Player;

public class Head extends Armor{

    public Head(){
        name = "Casque";
        buffDefence=(int) (Math.random()*9);
    }

    public Head(int _buffDefence){
        name = "Casque";
        buffDefence=_buffDefence;
    }

    public void addItem(Player player){
        player.head=this;
        System.out.println("Nouvelle protection : "+this.name);
    }
}
