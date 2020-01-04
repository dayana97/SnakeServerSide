package game;

import entities.Moves;
import entities.Snake;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Player {
    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    boolean connected = false;
    boolean assigned = false;

    Snake snake;

    int playerNumber;

    public Player(int num) {
        playerNumber = num;
        connected = false;

        if(num == 1)
            snake = new Snake();
        else if(num == 2)
            snake = new Snake(780, 780, Moves.left);
    }

    public void sendStatus(boolean stat) {
        try {
            oos.writeBoolean(stat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAssigned(boolean stat) {
        assigned = stat;
    }

    public ArrayList<Point> getLocation() {
        return snake.getLocation();
    }

    public void reset(){
        snake.reset();
    }

    public void tick() {
        snake.tick();
    }

    public void setSocket(Socket s) {
        this.socket = s;
    }

    public void setObjectInputStream() {
        try {
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setObjectOutputStream() {
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectInputStream getObjectInputStream() {
        return ois;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return oos;
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public Snake getSnake() {
        return snake;
    }

    public void sendData(Player other) {
        try {
            oos.writeBoolean(true);
            oos.writeObject(snake.getLocation());
            oos.writeObject(other.snake.getLocation());
            oos.reset();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
