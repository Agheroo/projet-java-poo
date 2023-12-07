package entity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Chest extends Props {
    public OBJ_Chest() {
        name="Chest";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/chest.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
