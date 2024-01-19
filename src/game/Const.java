package game;
import java.awt.Color;

public final class Const {
    //Enums
    public static enum State {WORLD, FIGHT, PAUSE, WELCOME, WON, LOST}
    public static enum FightState {FIGHTING, WON , LOST}
    public static enum Selection {NONE, PLAY, RESUME, CONTINUE, QUIT, ATTACK, POTION, BACK}


    //Window parameters
    public final static int WDW_width = 800;
    public final static int WDW_height = 600;
    public final static int WDW_FPS = 60;

    //World parameters
    public final static int WRLD_tileSize = 16;
    public final static int WRLD_scale = 3;
    public final static int WRLD_tileScreenSize = WRLD_tileSize*WRLD_scale;
    public final static int WRLD_entityScreenSize = WRLD_tileSize*WRLD_scale;
    public final static int WRLD_spawnX = 27*WRLD_tileScreenSize;
    public final static int WRLD_spawnY = 54*WRLD_tileScreenSize; 

    // World initialization settings
    public final static int WRLD_maxRow = 64, WRLD_maxCol = 64; //DONT FORGET TO MODIFY WHEN CHANGING THE MAP !!!

    //FightScene parameters
    public final static int FGHT_entityScreenSize = 100;

    //TileManager
    public final static int nbFloorTextures = 34;
    public final static int nbTopTextures = 27;

    //HUD
    public static String fontName = "rainyhearts";
    
    //Welcome
    public final static int nbOfCharacters = 3;
    public final static int nbSpriteCharacterAnim = 4;

    //Fighting
    public final static int HUD_fightWidth = 760;
    public final static int HUD_fightHeight = 160;

    //Colors
    public final static Color COLOR_WELCOME_red = new Color(0xCF1C3F);
    public final static Color COLOR_WELCOME_red_highlight = new Color(0xB21A4E);
    public final static Color COLOR_WELCOME_white = new Color(0xDDDDDD);
    public final static Color COLOR_WELCOME_blue = new Color(0x7D8DED);
    public final static Color COLOR_WELCOME_blue_highlight = new Color(0x4443EF);
    public final static Color COLOR_WELCOME_green = new Color(0x2AEA90);
    public final static Color COLOR_WON_green = new Color(0x27CD74);
    public final static Color COLOR_BUTTON_text = new Color(0x784F30);
    public final static Color COLOR_BUTTON_text_highlight = new Color(0x784F30);
    public final static Color COLOR_BUTTON_bg = new Color(0xA38168);
    public final static Color COLOR_BUTTON_bg_highlight = new Color(0x996348);
    public final static Color COLOR_WORLD_blue = new Color(10,0,176);
    public final static Color COLOR_WORLD_grey_transparent = new Color(180, 180, 180, 180);
    public final static Color COLOR_WORLD_XP_color = new Color(10, 150, 190);
    public final static Color COLOR_FIGHT_bg = new Color(0xC4A187);
    public final static Color COLOR_locked_ability = new Color(0x5A5C6C);
    public final static Color COLOR_locked_ability_highlight = new Color(0xCDCED5);
    public final static Color COLOR_unlocked_ability = new Color(0x4669A0);
    public final static Color COLOR_unlocked_ability_highlight = new Color(0x1B1E5B);
    public final static Color COLOR_unlockLvl = new Color(0x5A5C6C);
    public final static Color COLOR_FIGHT_underline = new Color(0x1B1E5B);
}
