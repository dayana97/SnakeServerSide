package entities;

import game.Constants;

import java.awt.*;

public abstract class Entity {
    protected int X;
    protected int Y;

    public void setX(int x) {
        this.X = x;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    protected void tick() {

    }

    protected void render(Graphics g, Color c, int offX, int offY) {
        g.setColor(c);
        g.fillRect(offX+X, offY+Y, Constants.size, Constants.size);
    }
}
