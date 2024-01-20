package entity;
import game.Attack;

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
        strength = 16;
        defense = 30;
        initiative = 8;
        
        
        attacks[0] = new Attack("Coup d'épaule", 10, 0, true,1);
        attacks[1] = new Attack("Tranchage", 17, 10, true,1);
        attacks[2] = new Attack("Berserk", 25, 15, false,2);
        attacks[3] = new Attack("Shlaz tah'maïr", 43, 22, false,3);
    }

    public void levelUp(){
        maxHealth += 20;
        maxMana += 8;
        agility += 2;
        strength += 4;
        defense += 10;
        initiative += 1;
        mana = maxMana;
    }
}
