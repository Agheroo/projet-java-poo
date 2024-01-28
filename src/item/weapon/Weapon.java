package item.weapon;

import entity.Player;
import item.Item;

public abstract class Weapon extends Item {

    public int buffStrenght;
    public Weapon(){};


    public void addItem(Player player){
        player.weapon=this;
        System.out.println("Nouvelle arme : "+this.name);
    }
}
