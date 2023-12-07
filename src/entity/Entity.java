package entity;

import game.Scene;

public abstract class Entity {
    //Display purpose variables
    private final int _tileSize=16;
    protected final int _scale = 3;
    public final int screenSize = _tileSize*_scale;


    //Position of entity in the world, directions and speed
    public int worldX, worldY;
    public int dirX,dirY;


    //Entity animations
    protected int _spriteCnt=0;        //Variable responsible for the incrementation of the different sprites
    protected int _spriteUpdater=0;    //Variable responsible for the incrementation of the speed of the sprites
    protected int _spriteSpeed;        //How fast are the sprites changing (higher spriteSpeed means slower time to change)
    protected int _spriteCntMax;       //How many sprite does the entity have


    
    public Entity(){
        //
    }

    public Entity(int x,int y, int _spriteCntMax, int spriteSpeed){
        this.worldX = x; this.worldY = y;
        this._spriteCntMax = _spriteCntMax; this._spriteSpeed = spriteSpeed;


    }


    public void update(Scene scene, double dt){
        //Updating entity position accurately (at any point in time either pressing keys or not)
    }



    //GRAPHICS
    private void loadTextures(String name){
        //TODO: Different texture loading from characters
    }

    protected void updateFrames(){
        _spriteUpdater++;
        if (_spriteUpdater>_spriteSpeed){
            _spriteCnt++;
            if(_spriteCnt == _spriteCntMax){
                _spriteCnt = 0;
            }
            _spriteUpdater=0;
        }
    }

    

   
}
