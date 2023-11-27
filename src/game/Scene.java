package game;

import main.KeyHandler;

import java.awt.*;

import entity.Enemy;
import entity.Player;

public abstract class Scene {
    enum State {WORLD,FIGHT,PAUSE,MENU}
    public KeyHandler keyH = KeyHandler.getInstance();
    public double dt = 0;
    State state;
    

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
            if(state == State.WORLD || state == State.FIGHT){
                state = State.PAUSE;
            }
            if(state == State.PAUSE){
                state = State.WORLD;
            }
        }
    }
}
