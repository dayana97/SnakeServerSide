package entities;

import game.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Snack extends Entity{
    ImageIcon snackIcon;

    public Snack(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public Snack() {
        this.X = new Random().nextInt(40) * Constants.size;		//TODO: Replace magic numbers
        this.Y = new Random().nextInt(40) * Constants.size;
    }

    public void reset() {
        this.X = new Random().nextInt(40) * Constants.size;		//TODO: Replace magic numbers
        this.Y = new Random().nextInt(40) * Constants.size;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g, Color c, int offsetX, int offsetY) {
        g.setColor(c);
        snackIcon = new ImageIcon("snack.png");
        snackIcon.paintIcon(null, g, offsetX+X, offsetY+Y);
        // g.fillRect(offsetX+X, offsetY+Y, Constants.size, Constants.size);
    }
}
