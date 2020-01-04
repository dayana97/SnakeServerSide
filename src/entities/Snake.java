package entities;

import game.Constants;
import game.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Snake extends Entity {

    // Snake properties
    private Moves moves;

    // Declare arrayList with block locations
    public ArrayList<Point> location;

    private int originX;
    private int originY;
    private Moves defaultDirection;
    boolean right = false;
    boolean left = false;
    boolean up = false;
    boolean down = false;
    ImageIcon rightMouth;
    ImageIcon leftMouth;
    ImageIcon upMouth;
    ImageIcon downMouth;
    ImageIcon snakeImage;
    boolean snake1 = false;
    private String nickname;

    public Snake(){
        snake1 = true;
        location = new ArrayList<Point>();
        moves = Moves.right;
        location.add(new Point(0, 0));
        originX = 0;
        originY = 0;
        defaultDirection = Moves.right;
    }

    public Snake(int x, int y, Moves d) {
        location = new ArrayList<Point>();
        moves = d;
        location.add(new Point(x, y));
        defaultDirection = d;
        originX = x;
        originY = y;
    }

    public void setLocation(ArrayList<Point> loc) {
        this.location = loc;
    }

    public boolean checkCollisionWith(Entity e) {
        if(e instanceof Snack) {
            if(location.get(0).getX() == e.getX() && location.get(0).getY() == e.getY()) {
                increaseLength();
                return true;
            }
        }

        if(e instanceof Snake) {

            Snake other = (Snake) e;

            for(int i = 0; i < other.location.size(); i++) {
                if(location.get(0).equals(other.location.get(i))) {

                    if(i == 0) {
                        other.reset();
                    }

                    reset();
                }
            }
        }


        return false;
    }

    public void reset() {
        int size = location.size();

        for(int i = size - 1; i > 0; i--) {
            location.remove(i);
        }

        location.get(0).setPoint(originX, originY);
        setMoves(defaultDirection);
    }

    public void increaseLength() {
        location.add(new Point(location.get(location.size()-1)));
    }

    public void setMoves(Moves d) {
        this.moves = d;
    }

    public Moves getMoves() {
        return moves;
    }

    @Override
    public void tick() {

        for(int i = (location.size() - 1); i > 0; i--) {
            location.get(i).setPoint(location.get(i-1));
        }

        switch(moves) {
            case up:
                up = true;
                location.get(0).setY(location.get(0).getY() - Constants.size);
                down = false;
                right = false;
                left = false;
                break;
            case right:
                right = true;
                location.get(0).setX(location.get(0).getX() + Constants.size);
                down = false;
                left = false;
                up = false;
                break;
            case down:
                down = true;
                location.get(0).setY(location.get(0).getY() + Constants.size);
                left = false;
                right = false;
                up = false;
                break;
            case left:
                left = true;
                location.get(0).setX(location.get(0).getX() - Constants.size);
                right = false;
                down = false;
                up = false;
                break;
        }

        // Check for self collision.
        for(int i = 1; i < location.size(); i++) {
            if(location.get(0).equals(location.get(i)))
                reset();
        }

        // Check for out of bounds.
        if(location.get(0).getX() >= 800|| location.get(0).getX() < 0 || location.get(0).getY() >= 800 || location.get(0).getY() < 0) {
            reset();
        }



    }

    @Override
    public void render(Graphics g, Color c, int offX, int offY) {

        g.setColor(c);

        for(int i = 0; i < location.size(); i++) {
            // g.fillRect(location.get(i).getX() + offX, location.get(i).getY() + offY, Constants.size, Constants.size);
            if(i == 0 & right){
                rightMouth = new ImageIcon("rightmouth.png");
                rightMouth.paintIcon(null, g,location.get(i).getX() + offX, location.get(i).getY() + offY);
            }
            if(i == 0 & left){
                leftMouth = new ImageIcon("leftmouth.png");
                leftMouth.paintIcon(null, g,location.get(i).getX() + offX, location.get(i).getY() + offY);
            }
            if(i == 0 & up){
                upMouth = new ImageIcon("upmouth.png");
                upMouth.paintIcon(null, g,location.get(i).getX() + offX, location.get(i).getY() + offY);
            }
            if(i == 0 & down){
                downMouth = new ImageIcon("downmouth.png");
                downMouth.paintIcon(null, g,location.get(i).getX() + offX, location.get(i).getY() + offY);
            }
            if(i!=0){
                snakeImage = new ImageIcon("snakeImage.png");
                snakeImage.paintIcon(null, g,location.get(i).getX() + offX, location.get(i).getY() + offY);
            }
            g.setColor(Color.WHITE);

            g.drawString("Scores: " + String.valueOf(location.size()), 780, 15);
        }
    }

    public ArrayList<Point> getLocation(){
        return location;
    }

    public void setMove(Moves m) {
        this.moves = m;
    }

    // g.drawString(String.valueOf(location.size()), location.get(0).getX() + offX, location.get(0).getY() + offY);
}
