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
        int scale = 3;
        gp.obj[0].worldX = 13* tileSize*scale;
        gp.obj[0].worldY = 13* tileSize*scale;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 14* tileSize*scale;
        gp.obj[1].worldY = 13* tileSize*scale;

        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].worldX = 15* tileSize*scale;
        gp.obj[2].worldY = 13* tileSize*scale;
    }
}

