package entity;

import game.Attack;
import item.weapon.Bow;

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

        maxMana = 16;
        mana = maxMana;

        agility = 30;
        strength = 27;
        defense = 12;
        initiative = 10;
        
        
        attacks[0] = new Attack("Étrangler", 15, 0, true,1);
        attacks[1] = new Attack("Koutal", 24, 8, true,2);
        attacks[2] = new Attack("Poizong", 36, 15, false,3);
        attacks[3] = new Attack("KhaZix Youmuu", 61, 38, false,5);

        weapon=new Bow(0);
    }

    public void levelUp(){
        level++;
        xpMax = 55 + (int)1.3*xpMax;
        maxHealth += 15;
        maxMana += 6;
        agility += 22;
        strength += 12;
        defense += 3;
        initiative += 1;
        mana = maxMana;
    }
}
