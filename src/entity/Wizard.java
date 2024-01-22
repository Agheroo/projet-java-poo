package entity;

import game.Attack;

public class Wizard extends Player{
    
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
    public Wizard(String entityName, int worldX, int worldY, int dirX, int dirY, int speed, String facing,
            int spriteCntMax, int spriteSpeed) {
        super(entityName, worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);

        maxHealth = 240;
        health = maxHealth;

        maxMana = 60;
        mana = maxMana;

        agility = 10;
        strength = 15;
        defense = 10;
        initiative = 14;

        
        attacks[0] = new Attack("Coup de sceptre", 7, 0, true,1);
        attacks[1] = new Attack("Katon sazuké", 30, 10, true,1);
        attacks[2] = new Attack("FireFlouche", 43, 23, false,2);
        attacks[3] = new Attack("Die de gazo", 68, 52, false,4);
    }

    public void levelUp(){
        level++;
        xpMax = 55 + (int)1.3*xpMax;
        maxHealth += 12;
        maxMana += 18;
        agility += 8;
        strength += 4;
        defense += 3;
        initiative += 2;
        mana = maxMana;
    }
}
