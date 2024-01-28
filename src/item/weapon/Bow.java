package item.weapon;

import entity.Player;

public class Bow extends Weapon{

    public Bow(){
        name = "Arc supérieur";
        buffStrenght= (int) (Math.random() * 7);
    }

    public Bow(int _buffStrenght){
        name = "Arc";
        buffStrenght= _buffStrenght;
    }
}
