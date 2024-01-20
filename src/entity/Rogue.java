package entity;

import game.Attack;

public class Rogue extends Player{
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
    public Rogue(String entityName, int worldX, int worldY, int dirX, int dirY, int speed, String facing,
            int spriteCntMax, int spriteSpeed) {
        super(entityName, worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);

        maxHealth = 175;
        health = maxHealth;

        maxMana = 12;
        mana = maxMana;

        agility = 20;
        strength = 22;
        defense = 12;
        initiative = 10;
        
        
        attacks[0] = new Attack("Étrangler", 15, 0, true,1);
        attacks[1] = new Attack("Koutal", 27, 10, true,2);
        attacks[2] = new Attack("Poizong", 40, 15, false,3);
        attacks[3] = new Attack("KhaZix Youmuu", 61, 38, false,5);
    }

    public void levelUp(){
        maxHealth += 15;
        maxMana += 8;
        agility += 5;
        strength += 6;
        defense += 3;
        initiative += 2;
        mana = maxMana;
    }
}
