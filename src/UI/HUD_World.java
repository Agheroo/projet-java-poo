package UI;

/**
 * @file HUD_World.java
 * @brief Contains the definition of the HUD_World class representing the in-game HUD for player statistics and instructions.
 */

import java.awt.Color;
import java.awt.Graphics2D;
import game.Const;
import game.World;

/**
 * @class HUD_World
 * @brief Represents the in-game HUD for displaying player statistics and instructions.
 */
public class HUD_World {
    /**
     * @brief Reference to the World object to track the player's progress.
     */
    World world = World.getWorld(); // Get track of the player's progress in the current world

    /**
     * @brief Textbox representing the player's experience points.
     */
    private Textbox xp;

    /**
     * @brief Textbox representing the player's level.
     */
    private Textbox level;

    /**
     * @brief Textbox representing the number of keys the player has.
     */
    private Textbox key;

    /**
     * @brief Textbox displaying movement and interaction instructions.
     */
    private Textbox instructions;

    /**
     * @brief Constructor for HUD_World class.
     * Initializes Textbox objects for displaying player statistics and instructions.
     */
    public HUD_World(){
        xp = new Textbox(String.valueOf(world.player.xp) + "/" + String.valueOf(world.player.xpMax), Const.fontName, 100, 20, Color.black);
        level = new Textbox("Level "+String.valueOf(world.player.level), Const.fontName, 100, 30, Color.black);
        key = new Textbox("Keys:" +String.valueOf(world.player.hasKey), Const.fontName, 80, 20, Color.black);
        instructions = new Textbox("Movements : ZQSD  |  Interact/Confirm : SPACE  |  Pause : ESCAPE", Const.fontName, 400, 30, new Color(10,0,176));
    }

    /**
     * @brief Draws the HUD on the screen, displaying player statistics and instructions.
     * @param g2 Graphics2D object for drawing.
     */
    public void draw(Graphics2D g2) {
        // Small background to make instructions more readable
        instructions.draw(g2, -10, Const.WDW_height - instructions.height - 20);

        // Draw the key information
        key.draw(g2, Const.WDW_width - key.width, 10);

        // Draw the player's level information
        level.draw(g2, 0, 0);

        // Draw the XP bar
        g2.setColor(Color.black);
        g2.drawRect(9, 39, 151, 31);
        g2.setColor(new Color(180, 180, 180, 180));
        g2.fillRect(10, 40, 150, 30);
        g2.setColor(new Color(10, 150, 190));
        g2.fillRect(10, 40, world.player.xp * 150 / world.player.xpMax, 30);
        xp.draw(g2, 10, 40);
    }

    /**
     * @brief Updates the HUD by refreshing player statistics.
     */
    public void update(){
        xp.setNewText(String.valueOf(world.player.xp) + "/" + String.valueOf(world.player.xpMax));
        level.setNewText("Level "+String.valueOf(world.player.level));
        key.setNewText("Keys:" +String.valueOf(world.player.hasKey));
    }
}
