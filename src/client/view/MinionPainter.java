package src.client.view;
import src.client.model.GameInfo;
import src.client.viewconstant.Const;
import src.model.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Font;

public class MinionPainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        int id = game.getPlayerId();
        int opponentId = game.getOpponentId();
        Graphics g = screenImg.getGraphics();
        List<Minion> ally = game.getAlly();
        List<Minion> enemy = game.getEnemy();
        int x, y;
        // draw ally minions
        for(int i = 1; i < ally.size(); i++){
            x = game.getMinionPosition(id, i)[0];
            y = game.getMinionPosition(id, i)[1];
            View.drawMinion(g, ally.get(i), x, y);
        }
        // draw enemy minions
        for(int i = 1; i < enemy.size(); i++){
            x = game.getMinionPosition(opponentId, i)[0];
            y = game.getMinionPosition(opponentId, i)[1];
            View.drawMinion(g, enemy.get(i), x, y);
        }
    }
}