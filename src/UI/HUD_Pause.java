package UI;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Const;
import game.Const.Selection;

public class HUD_Pause extends HUD{


    public HUD_Pause(){
        super();
        selection = Selection.CONTINUE;
        _nbButtons = 2;
        _buttons = new ChoiceButton[_nbButtons];

        _buttons[0] = new ChoiceButton(200, 50, "CONTINUER", "rainyhearts", Color.blue);
        _buttons[1] = new ChoiceButton(200, 50, "QUITTER", "rainyhearts", Color.blue);
        changeSelectionColor(0, Color.blue, Color.red);
    }


    public void update(){
        if(keyH.interactPressed){
            confirm = true;
            System.out.println("Je confirme ma selection");
        }
        if(keyH.upPressed || keyH.downPressed){
            switch(selection){
                case Const.Selection.CONTINUE:
                    selection = Const.Selection.QUIT;
                    changeSelectionColor(1, Color.blue, Color.red);
                    break;
                case Const.Selection.QUIT:
                    selection = Const.Selection.CONTINUE;
                    changeSelectionColor(0, Color.blue, Color.red);
                    break;

                default: break;    
            }
            keyH.upPressed = false;
            keyH.downPressed = false;
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
