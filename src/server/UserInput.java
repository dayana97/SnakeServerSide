package server;

import entities.Moves;
import game.Player;

import java.io.IOException;

public class UserInput implements Runnable{
    Player player;

    public UserInput(Player p) {
        this.player = p;
    }

    @Override
    public void run() {
        while(true) {
            Moves m = Moves.right;
            try {
                m = (Moves) player.getObjectInputStream().readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.getSnake().setMove(m);
        }
    }
}
