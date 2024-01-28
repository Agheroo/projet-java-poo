package item.weapon;

public class Sword extends Weapon{

    public Sword(){
        name = "Épée à double tranchant";
        buffStrenght= (int) (Math.random() * 7);
    }

    public Sword(int _buffStrenght){
        name = "Épée";
        buffStrenght= _buffStrenght;
    }
}
