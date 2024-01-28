package entity;
import game.Attack;
import item.weapon.Sword;

public class Warrior extends Player{

    /**
     * @brief Different player class for another gameplay experience, only stats change from Player class
     * @param entityName    //Same as player
     * @param worldX //Same as player
     * @param worldY //Same as player
     * @param dirX //Same as player
     * @param dirY //Same as player
     * @param speed //Same as player
     * @param facing //Same as player
     * @param spriteCntMax //Same as player
     * @param spriteSpeed //Same as player
     */
    public Warrior(String entityName, int worldX, int worldY, int dirX, int dirY, int speed, String facing,
            int spriteCntMax, int spriteSpeed) {
        super(entityName, worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);

        maxHealth = 300;
        health = maxHealth;

        maxMana = 20;
        mana = maxMana;

        agility = 3;
        strength = 17;
        defense = 30;
        initiative = 8;
        
        
        attacks[0] = new Attack("Coup d'épaule", 15, 0, true,1);
        attacks[1] = new Attack("Tranchage", 22, 8, true,1);
        attacks[2] = new Attack("Berserk", 40, 14, false,2);
        attacks[3] = new Attack("Shlaz tah'maïr", 57, 22, false,3);

        weapon=new Sword(0);
    }

    public void levelUp(){
        level++;
        xpMax = 55 + (int)1.3*xpMax;
        maxHealth += 20;
        maxMana += 11;
        agility += 5;
        strength += 5;
        defense += 10;
        initiative += 1;
        mana = maxMana;
    }
}
