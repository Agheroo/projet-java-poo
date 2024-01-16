package UI;


import java.awt.Color;
import java.awt.Graphics2D;

import entity.Enemy;
import entity.Player;
import game.Const;
import game.Scene;
import game.Const.Selection;

public class HUD_Fight extends HUD {
    public int page;   //Which page of the HUD are we in :
    public int attackSelection;
    public Textbox[] playerStats;  //for displaying useful player stats in combat (HP & mana)
    private Player player;
    public int choice;
    /* 0 : Main menu
     * 1 : Attack menu
     * 2 : Potion menu
     */

    public HUD_Fight(Player player, Enemy enemy){
        super();
        this.player = player;

        selection = Selection.ATTACK;
        page = 0;
        choice = -1;
        _nbButtons = 3;
        _buttons = new ChoiceButton[_nbButtons];
        _texts = new Textbox[player.potions.size()+player.attacks.length];
        
        
        playerStats = new Textbox[2];
        playerStats[0] = new Textbox(String.valueOf(player.health) + "/" + String.valueOf(player.maxHealth),Const.fontName,100,30,new Color(0x0EC44C));
        playerStats[1] = new Textbox(String.valueOf(player.mana) + "/" + String.valueOf(player.maxMana),Const.fontName,100,30,new Color(0x1D81CE));

        for(int i=0;i<player.attacks.length;i++){
            _texts[i] = new Textbox(player.attacks[i].name,Const.fontName,200,30,Color.black);
        }
        for(int i=player.attacks.length;i<player.attacks.length + player.potions.size();i++){
            _texts[i] = new Textbox(player.potions.get(i-player.attacks.length).name,Const.fontName,100,50,Color.black);
        }
        _buttons[0] = new ChoiceButton(220, 80, "ATTAQUER", Const.fontName, Color.blue);
        _buttons[1] = new ChoiceButton(220, 80, "POTION", Const.fontName, Color.blue);
        _buttons[2] = new ChoiceButton(160, 50, "RETOUR", Const.fontName, Color.blue);
        changeSelectionColor(0, Color.blue, Color.red);
    }


    /**
     * @brief Update the HUD of the current fight the player is in
     */
    public void update(){
        if(Scene.keyH.interactPressed){
            confirm = true;
            Scene.keyH.interactPressed = false;
        }
        
        if(Scene.keyH.rightPressed || Scene.keyH.leftPressed){
            switch(selection){
                case Const.Selection.ATTACK:
                    selection = Const.Selection.POTION;
                    break;
                
                case Const.Selection.POTION:
                    selection = Const.Selection.ATTACK;
                    break;

                default: break;

                case Const.Selection.BACK:
                    if(Scene.keyH.leftPressed){
                        selection = Const.Selection.NONE;
                        choice = 1;
                        Scene.keyH.leftPressed = false;
                    }
                    break;
            }
            
        }
        if(page != 0){
            //List of 4 choices between attacks
            /**
             *  0 : top left
             *  1 : top right
             *  2 : bottom left
             *  3 : bottom right
             */
            switch(choice){
                case 0:
                    if(Scene.keyH.downPressed){
                        choice = 2; break;
                    }
                    if(Scene.keyH.rightPressed){
                        choice = 1; break;
                    }
                case 1:
                    if(Scene.keyH.downPressed){
                        choice = 3;
                    }
                    if(Scene.keyH.leftPressed){
                        choice = 0;
                    } 
                    if(Scene.keyH.rightPressed){
                        selection = Const.Selection.BACK;
                        changeSelectionColor(2, Color.blue, Color.red);
                        choice = -1;
                    }
                    break;
                case 2:
                    if(Scene.keyH.upPressed){
                        choice = 0;
                    }
                    if(Scene.keyH.rightPressed){
                        choice = 3;
                    }
                    break;
                case 3:
                    if(Scene.keyH.rightPressed){
                        selection = Const.Selection.BACK;
                        changeSelectionColor(2, Color.blue, Color.red);
                        choice = -1;
                    }  
                    if(Scene.keyH.upPressed){
                        choice = 1;
                    }
                    if(Scene.keyH.leftPressed){
                        choice = 2;
                    }
                    break;


                default: break;
            }
        }

        //Simply updating colors
        switch(selection){
            case Const.Selection.ATTACK:
                changeSelectionColor(0, Color.blue, Color.red); break;
                
            case Const.Selection.POTION: 
                changeSelectionColor(1, Color.blue, Color.red); break;
            
            case Const.Selection.BACK:
                changeSelectionColor(2, Color.blue, Color.red); break;

            default: 
                changeSelectionColor(0, Color.blue, Color.red); break;   
        }

        Scene.keyH.rightPressed = false; Scene.keyH.upPressed = false;
        Scene.keyH.leftPressed = false; Scene.keyH.downPressed = false;
    }


    public void draw(Graphics2D g2){
        //Background of the HUD
        int margin = (Const.WDW_width - Const.HUD_fightWidth)/2;
        g2.setColor(new Color(0x5A2D16));
        g2.fillRect(margin-2, Const.WDW_height - Const.HUD_fightHeight - margin-2, Const.HUD_fightWidth+4, Const.HUD_fightHeight+4);
        g2.setColor(new Color(0xC4A187));
        g2.fillRect(margin, Const.WDW_height - Const.HUD_fightHeight - margin, Const.HUD_fightWidth, Const.HUD_fightHeight);

        //Small seperation
        g2.setColor(Color.BLACK);
        g2.fillRect(_buttons[0].width+_buttons[1].width+ 7*margin,Const.WDW_height - Const.HUD_fightHeight - margin/2,2,Const.HUD_fightHeight - margin);

        //Displaying current player's health  and mana
        playerStats[0].draw(g2,_buttons[0].width+_buttons[1].width+ 8*margin,Const.WDW_height - Const.HUD_fightHeight - margin);
        playerStats[1].draw(g2,_buttons[0].width+_buttons[1].width+ 8*margin,Const.WDW_height - Const.HUD_fightHeight + margin);



        //Buttons and texts
        switch(page){
            case 0: //Drawing main page buttons (attack or potion)
                for(int i=0;i<2;i++){
                    _buttons[i].draw(g2, 3*margin + i*(_buttons[0].width+2*margin), Const.WDW_height - Const.HUD_fightHeight - margin + (Const.HUD_fightHeight - _buttons[i].height)/2 );
                }
                break;
            case 1: //Drawing all attacks available
                for(int i=0;i<player.attacks.length;i++){
                    if(player.attacks[i].unlocked){
                        _texts[i].setColor(new Color(0x4669A0));
                    }
                    else{
                        _texts[i].setColor(new Color(0xAAB2B6));
                    }
                }

                //Small rectangle to underline the selection
                if(choice != -1 && player.attacks[choice].unlocked){
                    _texts[choice].setColor(new Color(0x1B1E5B));
                }

                g2.setColor(new Color(0x1B1E5B));
                if(choice < 2 && choice != -1)
                    g2.fillRect(margin*(2+13*choice),Const.WDW_height - Const.HUD_fightHeight + margin/2 + _texts[choice].height,_texts[choice].width,3);
                else if(choice >= 2)
                    g2.fillRect(margin*(2+13*(choice-2)),Const.WDW_height - Const.HUD_fightHeight + 4*margin + _texts[choice].height,_texts[choice].width,3);
                
                _texts[0].draw(g2,margin,Const.WDW_height - Const.HUD_fightHeight);
                _texts[1].draw(g2,4*margin + _texts[0].width,Const.WDW_height -Const.HUD_fightHeight);
                _texts[2].draw(g2,margin,Const.WDW_height - Const.HUD_fightHeight + 3*margin);
                _texts[3].draw(g2,4*margin + _texts[2].width,Const.WDW_height - Const.HUD_fightHeight + 3*margin);

                //Draw the "go back button"
                _buttons[2].draw(g2,Const.WDW_width-_buttons[2].width-2*margin,Const.WDW_height-2*margin-_buttons[2].height);
                break;
            case 2: //Drawing all the potions of the inventory
                if(player.potions.size() > 0){
                    choice = 0;
                    for(int i=player.attacks.length;i<player.attacks.length + player.potions.size();i++){
                        _texts[i].draw(g2,50*i+margin,Const.WDW_height - margin - Const.HUD_fightHeight);
                    }
                }
                else{
                    Textbox alert = new Textbox("Vous n'avez pas de potion !", Const.fontName, Const.HUD_fightWidth-margin,40, new Color(0xCF1C3F));
                    alert.draw(g2,3*margin,Const.WDW_height - Const.HUD_fightHeight + (3/2)*margin);
                    choice = -1;
                    selection = Const.Selection.BACK;
                    changeSelectionColor(2, Color.blue, Color.red);
                }

                //Draw the "go back button"
                _buttons[2].draw(g2,Const.WDW_width-_buttons[2].width-2*margin,Const.WDW_height-2*margin-_buttons[2].height);
                break;

            default: break;
        }

    }
}
