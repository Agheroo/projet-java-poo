package item.potion;

import entity.Player;

public class SpeedPotion extends Potion{

    public SpeedPotion(){
        name = "Potion de vitesse";
    }
    public void usePotion(Player player) {
        player.agility += 4;
        player.initiative += 5;
    }
}
