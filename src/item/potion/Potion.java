package item.potion;

import entity.Player;
import item.Item;
import item.Useable;

public abstract class Potion extends Item implements Useable {

    Potion(){}

    public abstract void usePotion(Player player);
    public void useItem(Player player){
        usePotion(player);
    }

    public void addItem(Player player){
        player.potions.add(this);
        System.out.println("Nouvelle potion : "+this.name);
    }
}
