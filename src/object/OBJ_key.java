package object;

import javax.imageio.ImageIO;

import java.io.FileInputStream;
import java.io.IOException;

public class OBJ_key extends SuperObject{
    public OBJ_key(){
        name="Key";
        try {
            image = ImageIO.read(new FileInputStream("res/object/key.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
