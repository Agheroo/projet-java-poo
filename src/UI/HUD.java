package UI;

import java.awt.Color;
import java.awt.Graphics2D;

public class HUD {
    public enum MenuType {WELCOME, PAUSE, FIGHT, SHOP}

    private int _nbButtons;
    private ChoiceButton[] _buttons;
    private MenuType _type;
    private int _titleWidth, _titleHeight;
    //private KeyHandler keyH = KeyHandler.getInstance();
    //private Cursor _cursor;


    public final int screenSizeWidth = 600;
    public final int screenSizeHeight = 500;

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

        //To replace with the current names that we want depending on the MenuType
        for(int i =0; i<_nbButtons ; i++){
            _buttons[i] = new ChoiceButton(80*3,20*3, "BUTTON " +i, "Arial",Color.black);
        }
    }


    public void draw(Graphics2D g2, int screenWidth, int screenHeight){
        //g2.drawImage(_bgTexture, (800 - screenSizeWidth)/2,(600 - screenSizeHeight)/2,screenSizeWidth, screenSizeHeight, null);
        g2.setColor(new Color(0,0,0,20));
        g2.fillRect((screenWidth - screenSizeWidth)/2, (screenHeight - screenSizeHeight)/2,screenSizeWidth,screenSizeHeight);

        int gap = (screenSizeHeight - _nbButtons*_buttons[0].height)/(_nbButtons+1)+_titleHeight;    //Small gap to add to make the buttons centered on the screen and spaced
        for(int i=0;i < _nbButtons; i++){
            _buttons[i].draw(g2,(screenWidth - _buttons[i].width)/2, (gap*(i+1) + _buttons[i].height*i + (screenHeight - screenSizeHeight)/2));
        }
    }

    public void update(double dt){
        
    }
}
