package UI;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Const;
import game.Scene;
import game.Const.Selection;

public class HUD_Pause extends HUD{


    public HUD_Pause(){
        super();
        selection = Selection.CONTINUE;
        _nbButtons = 2;
        _buttons = new ChoiceButton[_nbButtons];

        _buttons[0] = new ChoiceButton(200, 50, "CONTINUER", Const.fontName, Color.blue);
        _buttons[1] = new ChoiceButton(200, 50, "QUITTER", Const.fontName, Color.blue);
        changeSelectionColor(0, new Color(0x784F30), new Color(0x594E3B),new Color(0xA38168), new Color(0xAB9672));
    }


    public void update(){
        if(Scene.keyH.interactPressed){
            confirm = true;
            Scene.keyH.interactPressed = false;
        }
        if(Scene.keyH.upPressed || Scene.keyH.downPressed){
            switch(selection){
                case Const.Selection.CONTINUE:
                    selection = Const.Selection.QUIT;
                    changeSelectionColor(1, new Color(0x784F30), new Color(0x594E3B),new Color(0xA38168), new Color(0x996348));
                    break;
                case Const.Selection.QUIT:
                    selection = Const.Selection.CONTINUE;
                    changeSelectionColor(0, new Color(0x784F30), new Color(0x594E3B),new Color(0xA38168), new Color(0x996348));
                    break;

                default: break;    
            }
            Scene.keyH.upPressed = false;
            Scene.keyH.downPressed = false;
        }
    }

    public void draw(Graphics2D g2){
        //Darkened background
        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRect(0, 0, Const.WDW_width, Const.WDW_height);

        //Actual buttons to draw
        _buttons[0].draw(g2,(Const.WDW_width - _buttons[0].width)/2 , 200);
        _buttons[1].draw(g2,(Const.WDW_width - _buttons[0].width)/2 , 400);
    }
}
