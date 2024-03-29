/**
 * @file FightScene.java
 * @brief This file contains the implementation of the FightScene class, representing the scene during a fight between a player and an enemy.
 */

package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import UI.HUD_Fight;
import UI.Textbox;
import entity.Enemy;
import entity.Player;
import game.Const.Selection;


/**
 * @class FightScene
 * @extends Scene
 * @brief Represents the scene during a fight between a player and an enemy.
 */
public class FightScene {
    public Player player;
    public Enemy enemy;
    public static Const.FightState state;
    private HUD_Fight menu;
    private BufferedImage bg;

    /**
     * @brief Constructor for the FightScene class.
     * @param player The player entity in the fight.
     * @param enemy The enemy entity in the fight.
     */
    public FightScene(Player player, Enemy enemy) {
        try {
            bg = ImageIO.read(new FileInputStream("res/hud/fightscene.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.player = player;
        this.enemy = enemy;

        state = Const.FightState.FIGHTING;
        menu = new HUD_Fight(player, enemy);
        menu.selection = Const.Selection.ATTACK;
    }


    /**
     * @brief Updates the fight scene.
     */
    public void update(Scene scene){
        // Additional logic for the fight scene update
        menu.update();
        player.updateFrames();
        enemy.updateFrames();

        switch(menu.selection.toString()){
            case "ATTACK":
                if(menu.confirm){
                    //Implement combat game mechanics here
                    menu.page = 1;
                    menu.choice = 0;
                    menu.selection = Selection.NONE;
                }
                break;
            
            case "POTION":
                if(menu.confirm){
                    menu.page = 2;
                    menu.choice = 0;
                    menu.selection = Selection.NONE;
                }
                break;
            
            case "BACK":
                if(menu.confirm){
                    menu.selection = Const.Selection.ATTACK;
                    //menu.changeSelectionColor(0, Color.blue, Color.red);
                    menu.page = 0;
                    menu.choice = -1;
                }
                break;
            
            case "NONE":
                if(menu.confirm){
                    if(menu.choice != -1){
                        switch(menu.page){
                            case 1: //Attack page
                                if(player.attacks[menu.choice].unlocked == true){
                                    if(player.initiative>enemy.initiative){
                                        player.attacks[menu.choice].applyAttack(player, enemy);
                                        if(enemy.health <= 0){
                                            state = Const.FightState.WON;
                                            break;
                                        }
                                        enemy.attack.applyAttack(enemy, player);
                                        if(player.health <= 0){
                                            state = Const.FightState.LOST;
                                            Scene.state = Const.State.GAMELOST;
                                            break;
                                        }
                                    }
                                    else{
                                        enemy.attack.applyAttack(enemy, player);
                                        if(player.health <= 0){
                                            state = Const.FightState.LOST;
                                            Scene.state = Const.State.GAMELOST;
                                            break;
                                        }
                                        player.attacks[menu.choice].applyAttack(player, enemy);
                                        if(enemy.health <= 0){
                                            state = Const.FightState.WON;
                                            break;
                                        }
                                    }
                                }
                                else{
                                    System.out.println("Compétence non débloquée !");
                                }
                                
                                break;
                            case 2: //Potion page
                                player.potions.get(menu.choice).useItem(player);
                                enemy.attack.applyAttack(enemy, player);
                                if(player.health <= 0){
                                    state = Const.FightState.LOST;
                                    break;
                                }
    
                                break;
                            default: break;
                        }
                        menu.playerStats[0] = new Textbox(String.valueOf(player.health) + "/" + String.valueOf(player.maxHealth),Const.fontName,100,30,Const.COLOR_WELCOME_green);
                        menu.playerStats[1] = new Textbox(String.valueOf(player.mana) + "/" + String.valueOf(player.maxMana),Const.fontName,100,30,Const.COLOR_WELCOME_blue_highlight);
                    }
                    
                    menu.confirm = false;
                    menu.page = 0;
                    menu.selection = Const.Selection.ATTACK;
                    menu.choice = -1;
                }
                
            default: break;
        }
        if(Scene.keyH.escPressed && (menu.selection == Const.Selection.NONE || menu.selection == Const.Selection.BACK)){  //If you want to go back to choice between potion & attacks
            menu.selection = Const.Selection.ATTACK;
            menu.page = 0;
            Scene.keyH.escPressed = false;
        }

        //Check if the fight is won to add xp and update the rest
        if(state == Const.FightState.WON){
            //If the player levels up
            //Update player's XP and eventually their stats
            if(player.xp + enemy.xpRate >= player.xpMax){
                while(player.xp + enemy.xpRate >= player.xpMax){
                    //Level up as much as needed
                    player.xp += enemy.xpRate;
                    player.xp -= player.xpMax;
                    player.levelUp();
                    
                    
                    
                    //Check if any new attack is unlocked
                    for(int i=0;i<player.attacks.length;i++){
                        if(player.level >= player.attacks[i].unlockLvl){
                            player.attacks[i].unlocked = true;
                        }
                    }
                    player.health = player.maxHealth;
                }
            }
            //If the player hasn't leveled up
            else{
                player.xp += enemy.xpRate;
                player.mana += (int)(0.2*player.maxMana);      //Regen a bit of the mana too
                if(player.mana > player.maxMana)
                    player.mana = player.maxMana;

                
                player.health += (int)(0.1*player.maxHealth);//Regen a bit of the health
                if(player.health > player.maxHealth)
                    player.health = player.maxHealth;
            }
            
            

            //If the fight is won, theses lines are mendatory
            Scene.state = Const.State.WORLD;
            player.speed = 0;
            World.enemies.remove(new Point(enemy.worldX,enemy.worldY), enemy);

            if(World.enemies.size() == 0){  //Checking at the end of a fight if the player has killed everyone
                Scene.state = Const.State.GAMEWON;
            }
        }

        menu.confirm = false;
    }

    /**
     * @brief Draws the fight scene.
     * @param g2 The Graphics2D object for drawing.
     */
    public void draw(Graphics2D g2) {
        //Draw background
        g2.drawImage(bg,0,0,800,600,null);

        //Draw player HP bar
        g2.setColor(Color.black);
        g2.drawRect(49,Const.WDW_height-Const.HUD_fightHeight - 251,201,31);
        //Background of HP bar (red = missing hp)
        g2.setColor(new Color(180,12,23));
        g2.fillRect(50,Const.WDW_height-Const.HUD_fightHeight - 250,200,30);
        //Actual HP (in green)
        g2.setColor(new Color(10,190,50));
        g2.fillRect(50, Const.WDW_height-Const.HUD_fightHeight - 250, player.health*200/player.maxHealth,30);

        player.drawInFight(g2, 100, Const.WDW_height-Const.HUD_fightHeight - 200);

        
        //Draw enemy HP bar
        g2.setColor(Color.black);
        g2.drawRect(Const.WDW_width - Const.FGHT_entityScreenSize - 151 ,Const.WDW_height-Const.HUD_fightHeight - 251,201,31);
        //Background of HP bar (red = missing hp)
        g2.setColor(new Color(180,12,23));
        g2.fillRect(Const.WDW_width - Const.FGHT_entityScreenSize - 150,Const.WDW_height-Const.HUD_fightHeight - 250,200,30);
        //Actual HP (in green)
        g2.setColor(new Color(10,190,50));
        g2.fillRect(Const.WDW_width - Const.FGHT_entityScreenSize - 150, Const.WDW_height-Const.HUD_fightHeight - 250, enemy.health*200/enemy.maxHealth,30);

        enemy.drawInFight(g2, Const.WDW_width - Const.FGHT_entityScreenSize - 100, Const.WDW_height-Const.HUD_fightHeight - 200);


        //Drawing menu
        menu.draw(g2);
    }
}
