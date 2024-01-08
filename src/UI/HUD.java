package UI;

import java.awt.Color;
import java.awt.Graphics2D;

public class HUD {
    public enum MenuType {WELCOME, PAUSE, FIGHT, SHOP}

    private int _nbButtons;
    private ChoiceButton[] _buttons;
    private ChoiceButton _title;
    private MenuType _type;
    private int _titleWidth, _titleHeight;
    //private KeyHandler keyH = KeyHandler.getInstance();


    public final int HUDWidth = 600;
    public final int HUDHeight = 550;

    public HUD(MenuType type){
        _type = type;
        switch(_type){
            case WELCOME:
                _nbButtons = 2;
                break;
            case PAUSE:
                _nbButtons = 3;
                _titleWidth = 300;
                _titleHeight = 80;
                _title = new ChoiceButton(_titleWidth,_titleHeight,"PAUSE","rainyhearts",new Color(0x834317));
                break;
            case FIGHT:
                _titleWidth = 300;
                _titleHeight = 60;
                _nbButtons = 4;
                _title = new ChoiceButton(_titleWidth,_titleHeight,"FIGHT","rainyhearts",new Color(0xF10516));
                break;
            case SHOP:
                _nbButtons = 4;
                break;
        }

        _buttons = new ChoiceButton[_nbButtons];

        //To replace with the current names that we want depending on the MenuType
        
        for(int i =0; i<_nbButtons ; i++){
            _buttons[i] = new ChoiceButton(80*3,20*3, "BUTTON " +i, "rainyhearts",Color.black);
        }
    }


    public void draw(Graphics2D g2, int screenWidth, int screenHeight){
        //g2.drawImage(_bgTexture, (800 - HUDWidth)/2,(600 - HUDHeight)/2,HUDWidth, HUDHeight, null);
        g2.setColor(new Color(0,0,0,20));
        g2.fillRect((screenWidth - HUDWidth)/2, (screenHeight - HUDHeight)/2,HUDWidth,HUDHeight);   //"Drawing" HUD with soft background color




        int gap = (HUDHeight - _nbButtons*_buttons[0].height)/(_nbButtons+1);    //Small gap to space the buttons and keep them centered on screen
        _title.draw(g2,(screenWidth - _titleWidth)/2, screenHeight - HUDHeight);
        for(int i=0;i < _nbButtons; i++){
            _buttons[i].draw(g2,(screenWidth - _buttons[i].width)/2, (gap+gap*(i+1)/2 + _buttons[i].height*i + (screenHeight - HUDHeight)/2));
        }
    }

    public void update(double dt){
        
    }
}
