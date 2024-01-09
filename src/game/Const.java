package game;

public final class Const {

    //Window parameters
    public final static int WDW_width = 800;
    public final static int WDW_height = 600;
    public final static int WDW_FPS = 60;

    //World parameters
    public final static int WRLD_tileSize = 16;
    public final static int WRLD_scale = 3;
    public final static int WRLD_tileScreenSize = WRLD_tileSize*WRLD_scale;
    public final static int WRLD_entityScreenSize = WRLD_tileSize*WRLD_scale;
    // World initialization settings
    public final static int WRLD_maxRow = 64, WRLD_maxCol = 64; // DONT FORGET TO MODIFY WHEN CHANGING THE MAP !!!

    //FightScene parameters
    public final static int FGHT_entityScreenSize = 200;

    //TileManager
    public final static int nbFloorTextures = 6;
    public final static int nbTopTextures = 29;
}
