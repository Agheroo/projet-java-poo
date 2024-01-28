package item.weapon;

public class Staff extends Weapon{

    public Staff(){
        name = "Sceptre éternel";
        buffStrenght= (int) (Math.random() * 7);
    }

    public Staff(int _buffStrenght){
        name = "Sceptre";
        buffStrenght= _buffStrenght;
    }
}
