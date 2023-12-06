package UI;

import java.awt.Color;
import java.awt.Graphics2D;

public class HUD {
    public enum MenuType {WELCOME, PAUSE, FIGHT, SHOP}

    private int _nbButtons;
    private ChoiceButton[] _buttons;
    private MenuType _type;
    //private KeyHandler keyH = KeyHandler.getInstance();
    //private Cursor _cursor;


    public final int screenSizeWidth = 125*3;
    public final int screenSizeHeight = 65*3;

    public HUD(MenuType type){
        _type = type;
        switch(_type){
            case WELCOME:
                _nbButtons = 2;
                break;
            case PAUSE:
                _nbButtons = 3;
                break;
            case FIGHT:
                _nbButtons = 6;
                break;
            case SHOP:
                _nbButtons = 4;
                break;
        }

        _buttons = new ChoiceButton[_nbButtons];
        for(int i =0; i<_nbButtons ; i++){
            _buttons[i] = new ChoiceButton(80*4,40*4, "TITRE "+i, "Arial",Color.black);
            
        }
    }


    public void draw(Graphics2D g2, int screenWidth, int screenHeight){
        //g2.drawImage(_image, (800 - screenSizeWidth)/2,(600 - screenSizeHeight)/2,screenSizeWidth, screenSizeHeight, null);

        for(int i=0;i < _nbButtons; i++){
            _buttons[i].draw(g2,(screenWidth - _buttons[i].width)/2, (screenHeight - _buttons[i].height)/2 );
        }
    }

    public void update(double dt){
        
    }
}
