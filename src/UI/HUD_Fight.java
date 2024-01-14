package UI;


import java.awt.Color;
import java.awt.Graphics2D;

import game.Const;
import game.Scene;
import game.Const.Selection;

public class HUD_Fight extends HUD {

    public HUD_Fight(int nbButtons){
        super();
        selection = Selection.ATTACK;
        _nbButtons = 3;
        _buttons = new ChoiceButton[_nbButtons];

        _buttons[0] = new ChoiceButton(220, 80, "ATTAQUER", "rainyhearts", Color.blue);
        _buttons[1] = new ChoiceButton(220, 80, "POTION", "rainyhearts", Color.blue);
        _buttons[2] = new ChoiceButton(200, 50, "RETOUR", "rainyhearts", Color.blue);
        changeSelectionColor(0, Color.blue, Color.red);
    }


    public void update(){
        //System.out.println(selection);
        if(Scene.keyH.interactPressed){
            confirm = true;
            System.out.println("Je confirme ma selection");
            Scene.keyH.interactPressed = false;
        }
        if(Scene.keyH.rightPressed || Scene.keyH.leftPressed){
            switch(selection){
                case Const.Selection.ATTACK:
                    selection = Const.Selection.POTION;
                    changeSelectionColor(1, Color.blue, Color.red);
                    break;
                
                case Const.Selection.POTION:
                    selection = Const.Selection.ATTACK;
                    changeSelectionColor(0, Color.blue, Color.red);
                    break;

                default: break;
            }
            Scene.keyH.rightPressed = false;
            Scene.keyH.leftPressed = false;
        }
    }


    public void draw(Graphics2D g2){
        //Background of the HUD
        int margin = (Const.WDW_width - Const.HUD_fightWidth)/2;
        g2.setColor(Color.WHITE);
        g2.fillRect(margin, Const.WDW_height - Const.HUD_fightHeight - margin, Const.HUD_fightWidth, Const.HUD_fightHeight);
        
        for(int i=0;i<2;i++){
            _buttons[i].draw(g2, 3*margin + i*(_buttons[0].width+2*margin), Const.WDW_height - Const.HUD_fightHeight - margin + (Const.HUD_fightHeight - _buttons[i].height)/2 );
        }        
    }
}
