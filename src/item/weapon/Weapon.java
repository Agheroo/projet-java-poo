package item.weapon;

import entity.Player;
import item.Item;

public abstract class Weapon extends Item {
    
    public Weapon(){
        this.type = "weapon";
    }

    public void useItem(Player player){
        //
    }
}
