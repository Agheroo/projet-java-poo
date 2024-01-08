package item;

import item.armor.*;
import item.potion.*;
import item.weapon.*;

public class Generator {

    public static Item generateItem(){
        int nbRandom= (int) (Math.random() * 3);
        return switch (nbRandom) {
            case 1 -> Generator.generateWeapon();
            case 2 -> Generator.generateArmor();
            default -> Generator.generatePotion();
        };
    }

    public static Weapon generateWeapon(){
        int nbRandom= (int) (Math.random() * 3);
        return switch (nbRandom) {
            case 1 -> new Bow();
            case 2 -> new Staff();
            default -> new Sword();
        };
    }

    public static Potion generatePotion(){
        int nbRandom= (int) (Math.random() * 3);
        return switch (nbRandom) {
            case 1 -> new HealthPotion();
            case 2 -> new ManaPotion();
            default -> new SpeedPotion();
        };
    }

    public static Armor generateArmor(){
        int nbRandom= (int) (Math.random() * 4);
        return switch (nbRandom) {
            case 1 -> new Body();
            case 2 -> new Head();
            case 3 -> new Legs();
            default -> new Foot();
        };
    }
}
