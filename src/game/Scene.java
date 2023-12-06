package game;

import main.KeyHandler;

import java.awt.*;

import UI.HUD;
import UI.HUD.MenuType;
import entity.Enemy;
import entity.Player;

public abstract class Scene {
    public enum State {WORLD,FIGHT,PAUSE,MENU}
    private State _lastState;


    public KeyHandler keyH = KeyHandler.getInstance();
    public double dt = 0;
    public State state;
    public HUD menu;
    

    public abstract void update();
    public abstract void draw(Graphics2D g2, int screenWidth, int screenHeight);

    public Scene worldScene(){
        return World.getWorld();
    }

    public Scene fightScene(Player player, Enemy enemy){
        return new FightScene(player,enemy);
    }

    public void checkSceneChange(){
        if(keyH.pausePressed){
            keyH.pausePressed = false;
            
            if(state != State.PAUSE){
                System.out.println("CHANGING SCENE TO : PAUSE");
                _lastState = state;
                state = State.PAUSE;
                menu = new HUD(MenuType.PAUSE);
            }
            else{
                System.out.println("CHANGING SCENE TO : WORLD");
                state = _lastState;
                menu = null;
            }
        }
    }
}
