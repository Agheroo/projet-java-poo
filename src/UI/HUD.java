package UI;


import java.awt.Color;
import java.awt.Graphics2D;

import game.Const.Selection;
import main.KeyHandler;

public abstract class HUD {
    public Selection selection;
    public boolean confirm;

    protected int _nbButtons;
    protected ChoiceButton[] _buttons;
    protected Textbox[] _texts;
    protected KeyHandler keyH;
    


    public HUD(){
        keyH = KeyHandler.getInstance();
        confirm = false;

        keyH.interactPressed = false;
        keyH.downPressed = false;
        keyH.upPressed = false;
        keyH.leftPressed = false;
        keyH.rightPressed = false;
    }


    protected void changeSelectionColor(int selectionIndex, Color baseColor, Color selectColor){
        for(int i =0; i< _nbButtons; i++){
            _buttons[i].setTextColor(baseColor);
            _buttons[i].highlighted = false;
        }
        _buttons[selectionIndex].setTextColor(selectColor);
        _buttons[selectionIndex].highlighted = true;
    }

    public abstract void draw(Graphics2D g2);
    public abstract void update();
}
