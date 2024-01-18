package item.potion;

import entity.Player;

public class HealthPotion extends Potion{

    public HealthPotion(){
        type = "potion";
        name = "Potion de Soin";
    }

    public void usePotion(Player player) {
        player.health += 30;
        if(player.health > player.maxHealth)
            player.health = player.maxHealth;
    }
}
