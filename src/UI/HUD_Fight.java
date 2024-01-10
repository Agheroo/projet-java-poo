package UI;

import game.Const;

public class HUD_Fight extends HUD {

    public HUD_Fight(int nbButtons){
        super(nbButtons);
    }


    public void update(){
        if(keyH.interactPressed){
            System.out.println("Je confirme ma selection");
            System.out.println(selection);
            keyH.interactPressed = false;
            confirm = true;
        }
        if(keyH.rightPressed || keyH.leftPressed){
            if(selection == Const.Selection.ATTACK){
                selection = Const.Selection.POTION;      
            }
            else if(selection == Const.Selection.POTION){
                selection = Const.Selection.ATTACK;
            }
            keyH.rightPressed = false;
            keyH.leftPressed = false;
        }  
    }
}
