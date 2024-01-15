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


        health = 100 + level*27;
        mana = 20 + level*17;
        agility = 4 + level*2;
        strength = 20 + level*6;
        defense = 19 + level*6;
        initiative = 7 + level*3;

        
        attacks[0] = new Attack("Repousse", 20, 0, true,1);
        attacks[1] = new Attack("Tranchage", 27, 5, true,1);
        attacks[2] = new Attack("Baléyétt", 40, 0, false,2);
        attacks[3] = new Attack("Shlass supreme", 67, 20, false,3);
    }

}
