package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Patron de conception singleton pour la classe KeyHandler
public final class KeyHandler implements KeyListener {
    public static KeyHandler instance;
    public boolean upPressed,downPressed,leftPressed,rightPressed, interactPressed;
    public boolean changeScenePressed;
    // Constructeur privée
    private KeyHandler() {}

    // getInstance() contrôle l'accès à l'instance du singleton
    public static KeyHandler getInstance() {
        if(instance == null) {
            instance = new KeyHandler();
        }
        return instance;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code=e.getKeyCode();

        if(code == KeyEvent.VK_Z){
            upPressed=true;
        }
        if(code == KeyEvent.VK_Q){
            leftPressed=true;
        }
        if(code == KeyEvent.VK_S){
            downPressed=true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed=true;
        }
        if(code == KeyEvent.VK_SPACE){
            interactPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            changeScenePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();

        if(code == KeyEvent.VK_Z){
            upPressed=false;
        }
        if(code == KeyEvent.VK_Q){
            leftPressed=false;
        }
        if(code == KeyEvent.VK_S){
            downPressed=false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed=false;
        }
        if(code == KeyEvent.VK_SPACE){
            interactPressed = false;
        }
        if(code == KeyEvent.VK_ESCAPE){
            changeScenePressed = false;
        }
    }
}
