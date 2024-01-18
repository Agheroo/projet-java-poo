package item.potion;

import entity.Player;
import item.Item;

public abstract class Potion extends Item {

    Potion(){
        type = "potion";
    }

    public abstract void usePotion(Player player);
    public void useItem(Player player){
        usePotion(player);
    }
}
