package src.client.view;
import src.client.model.GameInfo;
import src.client.viewconstant.Const;
import src.model.Minion;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Font;

public class MinionPainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        int opponentId = 1 - game.getPlayerId();
        Graphics g = screenImg.getGraphics();
        List<Minion> ally = game.getAlly();
        List<Minion> enemy = game.getEnemy();
        // draw ally minions
        int shiftedX = Const.BOARD_REGION[0] + (Const.BOARD_REGION[2] - (ally.size()-2)*Const.MINION_GAP - (ally.size()-1)*Const.MINION_W)/2;
        for(int i = 1; i < ally.size(); i++){
            g.drawImage(View.loadImage(Const.MINION_FRAME_PATH), shiftedX + (i-1)*(Const.MINION_GAP + Const.MINION_W), Const.BOARD_REGION[1], Const.MINION_W, Const.MINION_H, null);
        }
        // draw enemy minions
        shiftedX = Const.OP_BOARD_REGION[0] + (Const.OP_BOARD_REGION[2] - (enemy.size()-2)*Const.MINION_GAP - (enemy.size()-1)*Const.MINION_W)/2;
        for(int i = 1; i < enemy.size(); i++){
            g.drawImage(View.loadImage(Const.MINION_FRAME_PATH), shiftedX + (enemy.size()-i-1)*(Const.MINION_GAP + Const.MINION_W), Const.OP_BOARD_REGION[1], Const.MINION_W, Const.MINION_H, null);
        }
    }
}