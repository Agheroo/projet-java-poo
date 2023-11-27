package game;

import main.KeyHandler;

import java.awt.*;

import entity.Enemy;
import entity.Player;

public abstract class Scene {
    public enum State {WORLD,FIGHT,PAUSE,MENU}
    public KeyHandler keyH = KeyHandler.getInstance();
    public double dt = 0;
    public State state;
    

    public abstract void update();
    public abstract void draw(Graphics2D g2, int screenWidth, int screenHeight);

    public Scene worldScene(){
        return World.getWorld();
    }

    public Scene fightScene(Player player, Enemy enemy){
        return new FightScene(player,enemy);
    }

    public void checkSceneChange(){
        if(keyH.changeScenePressed){
            keyH.changeScenePressed = false;
            
            if(state == State.WORLD || state == State.FIGHT){
                System.out.println("CHANGING SCENE TO : PAUSE");
                state = State.PAUSE;
            }
            else{
                System.out.println("CHANGING SCENE TO : WORLD");
                state = State.WORLD;
            }
        }
    }
}
