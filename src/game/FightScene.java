/**
 * @file FightScene.java
 * @brief This file contains the implementation of the FightScene class, representing the scene during a fight between a player and an enemy.
 */

package game;

import java.awt.*;
import java.util.HashMap;


import UI.HUD_Fight;
import entity.Enemy;
import entity.Player;
import game.Const.Selection;


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


        switch(menu.selection.toString()){
            case "ATTACK":
                if(menu.confirm){
                    //Implement combat game mechanics here
                    menu.page = 1;
                    menu.selection = Selection.NONE;





                }
                break;
            
            case "POTION":
                if(menu.confirm){
                    menu.page = 2;
                    menu.selection = Selection.NONE;
                    System.out.println("Je cherche dans le HUD des popo ;)");
                }
                break;
            
            case "BACK":
                if(menu.confirm){
                    menu.selection = Const.Selection.ATTACK;
                    menu.page = 0;
                }
                break;
            
            default: break;
        }
        if(Scene.keyH.escPressed && (menu.selection == Const.Selection.NONE || menu.selection == Const.Selection.BACK)){  //If you want to go back to choice between potion & attacks
            menu.selection = Const.Selection.ATTACK;
            menu.changeSelectionColor(0, Color.blue, Color.red);
            menu.page = 0;
            Scene.keyH.escPressed = false;
        }

        //Check if the fight is won to add xp and update the rest
        if(state == Const.FightState.WON){
            //If the fight is won, theses lines are mendatory

            Scene.state = Const.State.WORLD;
            player.speed = 0;
            World.enemies.remove(new Point(enemy.worldX,enemy.worldY), enemy);
        }

        menu.confirm = false;
    }

    public void killEnemy(HashMap<Point, Enemy> enemies, Enemy enemy){
        enemies.remove(new Point(enemy.worldX,enemy.worldY), enemy);
    }


    /**
     * @brief Draws the fight scene.
     * @param g2 The Graphics2D object for drawing.
     */
    public void draw(Graphics2D g2) {
        player.drawInFight(g2, 50, 200);
        enemy.drawInFight(g2, 600, 200);
        menu.draw(g2);
    }
}
