package entity;

import game.World;

public class EntitySetter {
    World gp;

    public EntitySetter(World  gp) {
        this.gp = gp;
    }
    public void setObject() {
        gp.obj[0] = new OBJ_Key();
        int tileSize = 16;
        gp.obj[0].worldX = 32* tileSize;
        gp.obj[0].worldY = 30* tileSize;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 36* tileSize;
        gp.obj[1].worldY = 30* tileSize;

        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].worldX = 40* tileSize;
        gp.obj[2].worldY = 30* tileSize;
    }
}

