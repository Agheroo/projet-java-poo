package UI;


import java.awt.Color;
import java.awt.Graphics2D;

import entity.Player;
import game.Const;
import game.Scene;
import game.Const.Selection;

public class HUD_Fight extends HUD {
    public int page;   //Which page of the HUD are we in :
    public int attackSelection;
    /* 0 : Main menu
     * 1 : Attack menu
     * 2 : Potion menu
     */

    public HUD_Fight(int nbButtons){
        super();
        selection = Selection.ATTACK;
        page = 0;
        _nbButtons = 3;
        _buttons = new ChoiceButton[_nbButtons];
        _texts = new Textbox[Player.potions.size()+Player.attacks.length];
        for(int i=0;i<Player.attacks.length;i++){
            _texts[i] = new Textbox(Player.attacks[i].name,Const.fontName,40,20,Color.black);
        }
        for(int i=Player.attacks.length;i<Player.attacks.length + Player.potions.size();i++){
            _texts[i] = new Textbox(Player.potions.get(i).type,Const.fontName,100,50,Color.black);
        }
        _buttons[0] = new ChoiceButton(220, 80, "ATTAQUER", Const.fontName, Color.blue);
        _buttons[1] = new ChoiceButton(220, 80, "POTION", Const.fontName, Color.blue);
        _buttons[2] = new ChoiceButton(200, 50, "RETOUR", Const.fontName, Color.blue);
        changeSelectionColor(0, Color.blue, Color.red);
    }


    public void update(){
        if(Scene.keyH.interactPressed){
            confirm = true;
            System.out.println("Je confirme ma selection");
            Scene.keyH.interactPressed = false;
        }
        if(Scene.keyH.rightPressed || Scene.keyH.leftPressed){
            switch(selection.toString()){
                case "ATTACK":
                    selection = Const.Selection.POTION;
                    changeSelectionColor(1, Color.blue, Color.red);
                    break;
                
                case "POTION":
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
        

        switch(page){
            case 0:
                for(int i=0;i<2;i++){
                    _buttons[i].draw(g2, 3*margin + i*(_buttons[0].width+2*margin), Const.WDW_height - Const.HUD_fightHeight - margin + (Const.HUD_fightHeight - _buttons[i].height)/2 );
                }
                break;
            case 1:
                for(int i=0;i<Player.attacks.length;i++){
                    if(Player.attacks[i].unlocked){
                        _texts[i].setColor(new Color(255,165,120));
                    }
                    else{
                        _texts[i].setColor(new Color(179,179,179));
                    }

                    _texts[i].draw(g2,50*i+margin,Const.WDW_height - margin - Const.HUD_fightHeight);
                }
                break;
            case 2:
                for(int i=Player.attacks.length;i<Player.attacks.length + Player.potions.size();i++){
                    _texts[i].draw(g2,50*i+margin,Const.WDW_height - margin - Const.HUD_fightHeight);
                }
                break;

            default: break;
        }

    }
}
