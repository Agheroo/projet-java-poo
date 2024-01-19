package UI;

/**
 * @file HUD_Welcome.java
 * @brief Contains the definition of the HUD_Welcome class representing the welcome screen HUD in the game.
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Rogue;
import entity.Warrior;
import entity.Wizard;
import game.Const;
import game.FightScene;
import game.Scene;
import game.World;

/**
 * @class HUD_Welcome
 * @brief Represents the welcome screen HUD class, extending the abstract HUD class.
 */
public class HUD_Welcome extends HUD {
    private int choiceClass;
    private int page;
    private BufferedImage bg;
    
    private BufferedImage[][] charactersDisplayed;
    private int spriteUpdater =0;
    private int spriteCnt =0;
        

    /**
     * @brief Constructor for HUD_Welcome class.
     * Initializes the welcome screen HUD.
     */
    public HUD_Welcome(){
        super();
        _nbButtons = 3;
        _buttons = new ChoiceButton[_nbButtons];
        selection = Const.Selection.PLAY;
        page = 0;
        choiceClass = -1;

        charactersDisplayed = new BufferedImage[Const.nbOfCharacters][Const.nbSpriteCharacterAnim];
        loadImages();


        _buttons[0] = new ChoiceButton(400, 80, "JOUER", Const.fontName, Color.blue);
        _buttons[1] = new ChoiceButton(400, 80, "QUITTER", Const.fontName, Color.blue);
        _buttons[2] = new ChoiceButton(200, 60, "RETOUR", Const.fontName, Color.blue);
        changeSelectionColor(0, new Color(0x784F30), new Color(0x594E3B), new Color(0xA38168), new Color(0x996348));

        _texts = new Textbox[15];

        //Warrior class
        _texts[0] = new Textbox("Guerrier", Const.fontName, 100, 30, new Color(0x6676F0));
        _texts[1] = new Textbox("+ Attaque\n", Const.fontName, 80, 30, new Color(0xCF1C3F));
        _texts[2] = new Textbox("+ Défense",Const.fontName, 80, 30, new Color(0xCF1C3F));
        _texts[3] = new Textbox("- Vitesse",Const.fontName, 80, 30, new Color(0xCF1C3F));

        //Mage class
        _texts[4] = new Textbox("Sorcier", Const.fontName, 100, 30, new Color(0x6676F0));
        _texts[5] = new Textbox("+ Attaque", Const.fontName, 80, 30, new Color(0xCF1C3F));
        _texts[6] = new Textbox("- Défense", Const.fontName, 80, 30, new Color(0xCF1C3F));
        _texts[7] = new Textbox("+ Initiative", Const.fontName, 80, 30, new Color(0xCF1C3F));

        //Rogue class
        _texts[8] = new Textbox("Assassin", Const.fontName, 100, 30, new Color(0x6676F0));
        _texts[9] = new Textbox("++ Attaque", Const.fontName, 100, 30, new Color(0xCF1C3F));
        _texts[10] = new Textbox("-- Défense", Const.fontName, 100, 30, new Color(0xCF1C3F));
        _texts[11] = new Textbox("+ Agilité", Const.fontName, 100, 30, new Color(0xCF1C3F));


        //End game title screen
        _texts[12] = new Textbox("Vous avez perdu !", Const.fontName, 400, 90, new Color(0xCF1C3F)); //Game lost !
        _texts[13] = new Textbox("Vous avez gagné !", Const.fontName, 400, 90, new Color(0x27CD74)); //Game won !
        _texts[14] = new Textbox("Merci d'avoir joué !", Const.fontName, 350, 90, new Color(0xDDDDDD));//Thanks for playing
    }

    /**
     * @brief Updates the welcome screen HUD.
     * Currently, this method has no implementation.
     */
    public void update(){
        if(Scene.keyH.escPressed || (confirm && selection == Const.Selection.BACK)){
            if(FightScene.state == Const.FightState.LOST){
                System.exit(0);
            }else if(Scene.state == Const.State.WON){
                System.exit(0);
            }
            else{
                selection = Const.Selection.PLAY;
                if(page != 0){
                    page--; //Go back to the previous page
                    choiceClass = -1;
                    Scene.keyH.escPressed = false;
                    confirm = false;
                }
            }
        }
        if(Scene.keyH.downPressed || Scene.keyH.upPressed){
            if(page == 0){
                if(selection == Const.Selection.PLAY){
                    changeSelectionColor(1, new Color(0x784F30), new Color(0x594E3B), new Color(0xA38168), new Color(0x996348));
                    selection = Const.Selection.QUIT;
                }
                else if(selection == Const.Selection.QUIT){
                    changeSelectionColor(0, new Color(0x784F30), new Color(0x594E3B), new Color(0xA38168), new Color(0x996348));
                    selection = Const.Selection.PLAY;
                }
            }
            else{
                if(Scene.keyH.downPressed && selection == Const.Selection.NONE){
                    selection = Const.Selection.BACK;
                    choiceClass=-1;
                    changeSelectionColor(2, new Color(0x784F30), new Color(0x594E3B), new Color(0xA38168), new Color(0x996348));
                }
                else if(Scene.keyH.upPressed && selection == Const.Selection.BACK){
                    choiceClass = 1;
                    selection = Const.Selection.NONE;
                    changeSelectionColor(0, new Color(0x784F30), new Color(0x594E3B), new Color(0xA38168), new Color(0x996348));
                }
            }
            Scene.keyH.downPressed = false;
            Scene.keyH.upPressed = false;
        }
        if(Scene.keyH.rightPressed){        //Chose between different character classes
            choiceClass++;
            if(choiceClass >= Const.nbOfCharacters)
                choiceClass = 0;
            Scene.keyH.rightPressed = false;
        }
        if(Scene.keyH.leftPressed){         //Chose between different character classes
            choiceClass--;
            if(choiceClass < 0)
                choiceClass = Const.nbOfCharacters -1;
            Scene.keyH.leftPressed = false;
        }




        //Confirming
        if(Scene.keyH.interactPressed){
            confirm = true;
            Scene.keyH.interactPressed = false;
        }
        
        //Checking what is confirmed
        if(confirm){
            switch(selection.toString()){
                case "PLAY":
                    page=1;
                    choiceClass = 0;
                    selection = Const.Selection.NONE;
                    break;
                case "QUIT":
                    System.exit(0); break;
                case "BACK":
                    page = 0;
                    choiceClass = -1;
                    selection = Const.Selection.PLAY;
                    changeSelectionColor(0, new Color(0x784F30), new Color(0x594E3B), new Color(0xA38168), new Color(0x996348));
                    break;
                case "NONE":  //Chose the character class and create the game
                    selectClass();
                    Scene.state = Const.State.WORLD;
                default:break;
            }

            confirm = false;
        }

        spriteUpdater++;
        if (spriteUpdater > 20) {
            spriteCnt++;
            if (spriteCnt == Const.nbSpriteCharacterAnim) {
                spriteCnt = 0;
            }
            spriteUpdater = 0;
        }
    }

    /**
     * @brief Draws the welcome screen HUD on the screen.
     * @param g2 Graphics2D object for drawing.
     */
    public void draw(Graphics2D g2){
        //If new game, then display the welcome menu
        if(Scene.state == Const.State.WELCOME){
            //Draw background
            g2.drawImage(bg, 0,0, null);
            
            switch(page){
                case 0:     //Real welcome main menu
                    _buttons[0].draw(g2,200,160);
                    _buttons[1].draw(g2,200,320);
                    break;

                case 1:     //Choice between different classes

                    //Drawing differents characters
                    for(int i=0;i<Const.nbOfCharacters;i++){ 
                        if(choiceClass == i){   //If the caracter is selected, update his animations
                            
                            //Change text colors for more visibility
                            _texts[4*i].setColor(new Color(0x7D8DED));
                            for(int j=1;j<4;j++){
                                _texts[4*i+j].setColor(new Color(0x1FD670));
                            }
                            
                            
                            //Little design for selection
                            g2.setColor(new Color(0x000000));
                            g2.fillRect(125*(1+i)+i*Const.FGHT_entityScreenSize - 25 - 10,(Const.WDW_height-Const.FGHT_entityScreenSize)/2-80 +10,Const.FGHT_entityScreenSize + 50 +4, Const.FGHT_entityScreenSize + 100 +4);
                            
                            //Draw a little background for each character
                            g2.setColor(new Color(0x996348));
                            g2.drawRect(125*(1+i)+i*Const.FGHT_entityScreenSize - 25 - 2,(Const.WDW_height-Const.FGHT_entityScreenSize)/2-80 - 2,Const.FGHT_entityScreenSize + 50 +4, Const.FGHT_entityScreenSize + 100 +4);
                            g2.fillRect(125*(1+i)+i*Const.FGHT_entityScreenSize - 25,(Const.WDW_height-Const.FGHT_entityScreenSize)/2-80,Const.FGHT_entityScreenSize + 50, Const.FGHT_entityScreenSize + 100);
                            for(int j=0;j<Const.nbSpriteCharacterAnim;j++){
                                if(spriteCnt == j)
                                    //Draw character
                                    g2.drawImage(charactersDisplayed[choiceClass][j], 125*(1+i)+i*Const.FGHT_entityScreenSize,(Const.WDW_height-Const.FGHT_entityScreenSize)/2-50, Const.FGHT_entityScreenSize,Const.FGHT_entityScreenSize,null);
                            }
                        }
                        else{
                            //Change text colors for more visibility
                            _texts[4*i].setColor(new Color(0x4443EF));

                            for(int j=1;j<4;j++){
                                _texts[4*i+j].setColor(new Color(0xB21A4E));
                            }
                            //Draw a little background for each character
                            g2.setColor(new Color(0xA38168));
                            g2.drawRect(125*(1+i)+i*Const.FGHT_entityScreenSize - 25 -2,(Const.WDW_height-Const.FGHT_entityScreenSize)/2-80 -2,Const.FGHT_entityScreenSize + 50 +4, Const.FGHT_entityScreenSize + 100 +4);
                            g2.fillRect(125*(1+i)+i*Const.FGHT_entityScreenSize - 25,(Const.WDW_height-Const.FGHT_entityScreenSize)/2-80,Const.FGHT_entityScreenSize + 50, Const.FGHT_entityScreenSize + 100);
                            
                            //Draw character
                            g2.drawImage(charactersDisplayed[i][0], 125*(1+i)+i*Const.FGHT_entityScreenSize,(Const.WDW_height-Const.FGHT_entityScreenSize)/2-50, Const.FGHT_entityScreenSize,Const.FGHT_entityScreenSize,null);
                        }

                        //Name of the class
                        _texts[4*i].draw(g2,125*(1+i)+i*Const.FGHT_entityScreenSize - 25,(Const.WDW_height-Const.FGHT_entityScreenSize)/2-80);
                        //More stats informations about the character class:
                        _texts[4*i+1].draw(g2,125*(1+i)+i*Const.FGHT_entityScreenSize - 40,(Const.WDW_height-Const.FGHT_entityScreenSize)/2+50);
                        _texts[4*i+2].draw(g2,125*(1+i)+i*Const.FGHT_entityScreenSize - 40,(Const.WDW_height-Const.FGHT_entityScreenSize)/2+70);
                        _texts[4*i+3].draw(g2,125*(1+i)+i*Const.FGHT_entityScreenSize - 40 ,(Const.WDW_height-Const.FGHT_entityScreenSize)/2+90);
                    }

                    //Drawing the go back button
                _buttons[2].draw(g2, (Const.WDW_width - _buttons[2].width)/2,500);
                    
                    break;
                
                default: break;
            }
        }



        // Check if the player has lost in the fight scene
        if(FightScene.state == Const.FightState.LOST){           
            // Draw the losing messages on the screen
            _texts[12].draw(g2, 0, 150);
            _texts[14].draw(g2, -20, 280);

            // Draw a separator line
            g2.setColor(new Color(0xDDDDDD));
            g2.fillRect(50, 400, Const.WDW_width - 100, 4);
        }

        // Check if the player has lost in the fight scene
        if(Scene.state == Const.State.WON){           
            // Draw the losing messages on the screen
            _texts[13].draw(g2, 0, 150);
            _texts[14].draw(g2, -20, 280);

            // Draw a separator line
            g2.setColor(new Color(0xDDDDDD));
            g2.fillRect(50, 400, Const.WDW_width - 100, 4);
        }
    }

    /**
     * @brief Creates the relevant class according to the class selected
     */
    public void selectClass(){
        switch(choiceClass){
            case 0:
                World.player = new Warrior("warrior",Const.WRLD_spawnX,Const.WRLD_spawnY,0,0,0,"down",4,20); break;
            case 1:
                World.player = new Wizard("mage",Const.WRLD_spawnX,Const.WRLD_spawnY,0,0,0,"down",4,20); break;
            case 2:
                World.player = new Rogue("rogue",Const.WRLD_spawnX,Const.WRLD_spawnY,0,0,0,"down",4,20); break;
            default: 
                System.out.println("ERROR selecting the class");
        }

    }

    /**
     * @brief Function used to load the images of the different characters' classes
     */
    private void loadImages(){
        try{
            bg = ImageIO.read(new FileInputStream("res/hud/welcome.png"));
            for(int i=0;i<Const.nbSpriteCharacterAnim;i++){
                charactersDisplayed[0][i] = ImageIO.read(new FileInputStream("res/entity/character/idle/warrior/down"+(i+1)+".png"));
                charactersDisplayed[1][i] = ImageIO.read(new FileInputStream("res/entity/character/idle/mage/down"+(i+1)+".png"));
                charactersDisplayed[2][i] = ImageIO.read(new FileInputStream("res/entity/character/idle/rogue/down"+(i+1)+".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
