package entity;

import game.Attack;

public class Mage extends Player{
    
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
    public Mage(String entityName, int worldX, int worldY, int dirX, int dirY, int speed, String facing,
            int spriteCntMax, int spriteSpeed) {
        super(entityName, worldX, worldY, dirX, dirY, speed, facing, spriteCntMax, spriteSpeed);

        maxHealth = 100;
        health = maxHealth;

        maxMana = 70;
        mana = maxMana;

        agility = 7;
        strength = 20;
        defense = 18;
        initiative = 13;

        
        attacks[0] = new Attack("Coup de sceptre", 10, 0, true,1);
        attacks[1] = new Attack("Boule de glace", 30, 10, true,1);
        attacks[2] = new Attack("Flammes de la haine", 50, 27, false,2);
        attacks[3] = new Attack("Die de gazo", 90, 60, false,4);
    }
}
