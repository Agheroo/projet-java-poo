package game;

import main.KeyHandler;

import java.awt.*;

public abstract class Scene {
    public KeyHandler keyH = KeyHandler.getInstance();
    public double dt = 0;

    public abstract void update();

    public abstract void draw(Graphics2D g2, int screenWidth, int screenHeight);
}
