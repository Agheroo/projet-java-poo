package entity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Door extends Props {
    public OBJ_Door() {
        name = "Door";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/door.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}

