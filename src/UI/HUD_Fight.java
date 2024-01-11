package UI;


import java.awt.Color;
import java.awt.Graphics2D;

import game.Const;
import game.Const.Selection;

public class HUD_Fight extends HUD {

    public HUD_Fight(int nbButtons){
        super();
        selection = Selection.ATTACK;
        _nbButtons = 3;
        _buttons = new ChoiceButton[_nbButtons];
    }


    public void update(){
        //System.out.println(selection);
        if(keyH.interactPressed){
            confirm = true;
            System.out.println("Je confirme ma selection");
        }
        if(keyH.rightPressed || keyH.leftPressed){
            if(selection == Const.Selection.ATTACK){
                selection = Const.Selection.POTION;
            }
            else if(selection == Const.Selection.POTION){
                selection = Const.Selection.ATTACK;
            }
            keyH.rightPressed = false;
            keyH.leftPressed = false;
        }
    }


    public void draw(Graphics2D g2){
        //Background of the HUD
        g2.setColor(Color.WHITE);
        g2.fillRect((Const.WDW_width - Const.HUD_fightWidth)/2, Const.WDW_height - Const.HUD_fightHeight - ((Const.WDW_width - Const.HUD_fightWidth)/2), Const.HUD_fightWidth, Const.HUD_fightHeight);
    }
}
