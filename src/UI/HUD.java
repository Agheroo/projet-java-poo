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
    


    public HUD(int nbButtons){
        _nbButtons = nbButtons;
        _buttons = new ChoiceButton[_nbButtons];
        keyH = KeyHandler.getInstance();
        confirm = false;

        //To replace with the current names that we want depending on the MenuType
        
        for(int i =0; i<_nbButtons ; i++){
            _buttons[i] = new ChoiceButton(80*3,20*3, "BUTTON " +i, "rainyhearts",Color.black);
        }
    }


    public void draw(Graphics2D g2, int screenWidth, int screenHeight){

    }

    public abstract void update();
}
