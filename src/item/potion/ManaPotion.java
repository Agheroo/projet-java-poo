package item.potion;

import entity.Player;

public class ManaPotion extends Potion{

    public ManaPotion(){
        type = "potion";
        name = "Potion de Mana";
    }
    
    public void usePotion(Player player) {
        player.mana += 15;
        if(player.mana > player.maxMana)
            player.mana = player.maxMana;
    }
}
