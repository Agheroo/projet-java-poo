package item.armor;

import entity.Player;
import item.Item;

public abstract class Armor extends Item {
    Armor(){
        type = "armor";
    }

    public void useItem(Player player){
        //
    }
}
