package game;
import entity.Character;

public class Attack {
    public final String name;     //The name of the attack
    public final int damage;      //The damage of the attack dealt
    public final int cost;        //The cost in mana if any
    public final int unlockLvl; //The level at which the attack gets unlocked if not unlocked
    public boolean unlocked;    //If the attack is unlocked or not yet

    public Attack(String name, int damage, int cost, boolean unlocked, int unlockLvl){
        this.unlockLvl = unlockLvl;
        this.name = name;
        this.damage = damage;
        this.cost = cost;
        this.unlocked = unlocked;
    }

    /**
     * @brief Applies the attack of a character on another one
     * @param emitter   The character that sends the attack
     * @param reciever  The character that "recieves" the attack
     */
    public void applyAttack(Character emitter, Character reciever){
        //Find a nice equation to balance a bit the game and add a dodge chance with agility
        reciever.health -= (emitter.strength*damage - 0.7*reciever.defense);
        emitter.mana -= cost;
    }
}
