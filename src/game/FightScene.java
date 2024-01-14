/**
 * @file FightScene.java
 * @brief This file contains the implementation of the FightScene class, representing the scene during a fight between a player and an enemy.
 */

package game;

import game.Scene.State;

import java.awt.*;
import java.util.HashMap;


import UI.HUD_Fight;
import entity.Enemy;
import entity.Player;


/**
 * @class FightScene
 * @extends Scene
 * @brief Represents the scene during a fight between a player and an enemy.
 */
public class FightScene {
    
    //private double dt = Scene.getdt();
    public Player player;
    public Enemy enemy;
    public Const.FightState state;
    private HUD_Fight menu;

    /**
     * @brief Constructor for the FightScene class.
     * @param player The player entity in the fight.
     * @param enemy The enemy entity in the fight.
     */
    public FightScene(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        state = Const.FightState.FIGHTING;
        menu = new HUD_Fight(3);
        menu.selection = Const.Selection.ATTACK;
    }

    /**
     * @brief Updates the fight scene.
     */
    public void update(Scene scene) {
        // Additional logic for the fight scene update
        menu.update();

        if(menu.selection == Const.Selection.ATTACK && menu.confirm){
            //Implement combat game mechanics here
            //If the fight is won, theses lines are mendatory
            state = Const.FightState.WON;
            Scene.state = State.WORLD;
            player.speed = 0;
            World.enemies.remove(new Point(enemy.worldX,enemy.worldY), enemy);
        }
        if(menu.selection == Const.Selection.POTION && menu.confirm){
            System.out.println("Je cherche dans le HUD des popo ;)");
        }
        menu.confirm = false;
    }

    public void killEnemy(HashMap<Point, Enemy> enemies, Enemy enemy){
        enemies.remove(new Point(enemy.worldX,enemy.worldY), enemy);
    }


    /**
     * @brief Draws the fight scene.
     * @param g2 The Graphics2D object for drawing.
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     */
    public void draw(Graphics2D g2) {
        player.drawInFight(g2, 50, 200);
        enemy.drawInFight(g2, 600, 200);
        menu.draw(g2);
    }
}
