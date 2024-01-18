package UI;


import java.awt.Color;
import java.awt.Graphics2D;

import game.Scene;
import game.Const.Selection;

public abstract class HUD {
    public Selection selection;
    public boolean confirm;

    protected int _nbButtons;
    protected ChoiceButton[] _buttons;
    protected Textbox[] _texts;
    


    public HUD(){
        confirm = false;

        Scene.keyH.interactPressed = false;
        Scene.keyH.downPressed = false;
        Scene.keyH.upPressed = false;
        Scene.keyH.leftPressed = false;
        Scene.keyH.rightPressed = false;
    }


    public void changeSelectionColor(int selectionIndex, Color baseText, Color newText, Color baseBg, Color newBg){
        for(int i =0; i< _nbButtons; i++){
            _buttons[i].setTextColor(baseText);
            _buttons[i].setBgColor(baseBg);
            _buttons[i].highlighted = false;
        }
        _buttons[selectionIndex].setTextColor(newText);
        _buttons[selectionIndex].setBgColor(newBg);
        _buttons[selectionIndex].highlighted = true;
    }

    public abstract void draw(Graphics2D g2);
    public abstract void update();
}
