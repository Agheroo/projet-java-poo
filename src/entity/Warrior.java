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

        maxHealth = 130;
        health = maxHealth;

        maxMana = 35;
        mana = maxMana;

        agility = 6;
        strength = 26;
        defense = 25;
        initiative = 10;
        
        
        attacks[0] = new Attack("Coup d'épaule", 10, 0, true,1);
        attacks[1] = new Attack("Tranchage", 27, 10, true,1);
        attacks[2] = new Attack("Berserk", 40, 15, false,2);
        attacks[3] = new Attack("Shlass tha'Mair", 67, 20, false,3);
    }

}
