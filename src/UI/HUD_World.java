package UI;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Const;
import game.World;

public class HUD_World{
    World world = World.getWorld(); //Get track of the player's progress in the current world

    private Textbox level;
    private Textbox key;
    private Textbox xp;
    private Textbox instructions;


    public HUD_World(){
        xp = new Textbox(String.valueOf(world.player.xp) + "/" + String.valueOf(world.player.xpMax), Const.fontName, 100, 20, Color.black);
        level = new Textbox("Level "+String.valueOf(world.player.level), Const.fontName, 100, 30, Color.black);
        key = new Textbox("Keys:" +String.valueOf(world.player.hasKey), Const.fontName, 80, 20, Color.black);
        instructions = new Textbox("Movements : ZQSD  |  Interact/Confirm : SPACE  |  Pause : ESCAPE",Const.fontName,400,30, new Color (10,0,176));
    }

    public void draw(Graphics2D g2) {
        instructions.draw(g2,-10,Const.WDW_height - instructions.height-20);
        level.draw(g2,0,0);
        key.draw(g2,Const.WDW_width-key.width,10);
        
        //Xp bar drawing
        g2.setColor(Color.black);
        g2.drawRect(9,39,151,31);
        g2.setColor(new Color(180,180,180,180));
        g2.fillRect(10,40,150,30);
        g2.setColor(new Color(10,150,190));
        g2.fillRect(10,40,world.player.xp*150/world.player.xpMax,30);
        xp.draw(g2,10,40);
    }

    public void update(){
        xp.setNewText(String.valueOf(world.player.xp) + "/" + String.valueOf(world.player.xpMax));
        level.setNewText("Level "+String.valueOf(world.player.level));
        key.setNewText("Keys:" +String.valueOf(world.player.hasKey));
    }
}
