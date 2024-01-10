package UI;

import game.Const;

public class HUD_Fight extends HUD {

    public HUD_Fight(int nbButtons){
        super(nbButtons);
    }


    public void update(){
        //System.out.println(selection);
        if(keyH.interactPressed){
            confirm = true;
            System.out.println("Je confirme ma selection");
            
            keyH.interactPressed = false;
            keyH.downPressed = false;
            keyH.upPressed = false;
            keyH.leftPressed = false;
            keyH.rightPressed = false;
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
