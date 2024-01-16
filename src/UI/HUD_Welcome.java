package UI;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Const;
import game.FightScene;

public class HUD_Welcome extends HUD{
    public HUD_Welcome(){
        super();
    }



    public void update(){
        
    }

    public void draw(Graphics2D g2){

        if(FightScene.state == Const.FightState.LOST){
            Textbox thanks = new Textbox("Vous avez perdu !",Const.fontName,400,90,new Color(0xCF1C3F));
            Textbox thanks2 = new Textbox("Merci d'avoir joué !",Const.fontName,350,90,new Color(0xDDDDDD));
            thanks.draw(g2,0,150);
            thanks2.draw(g2,-20,280);
            g2.setColor(new Color(0xDDDDDD));
            g2.fillRect(50,400,Const.WDW_width-100,4);
        }
        
    }
}
