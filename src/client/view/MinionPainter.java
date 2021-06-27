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
        int opponentId = 1 - game.getPlayerId();
        Graphics g = screenImg.getGraphics();
        List<Minion> ally = game.getAlly();
        List<Minion> enemy = game.getEnemy();
        // draw ally minions
        int shiftedX = Const.BOARD_REGION[0] + (Const.BOARD_REGION[2] - (ally.size()-2)*Const.MINION_GAP - (ally.size()-1)*Const.MINION_W)/2;
        for(int i = 1; i < ally.size(); i++){
            // draw minion image
            g.drawImage(View.loadImage(Const.CARD_IMG_DIR + "Goblin.png"), shiftedX + (i-1)*(Const.MINION_GAP + Const.MINION_W) + (int)(Const.MINION_IMG_X_RATIO*Const.MINION_W),
                        Const.BOARD_REGION[1] + (int)(Const.MINION_IMG_Y_RATIO*Const.MINION_H), Const.MINION_IMG_W, Const.MINION_IMG_H, null);
            // draw minion frame
            g.drawImage(View.loadImage(Const.MINION_FRAME_PATH), shiftedX + (i-1)*(Const.MINION_GAP + Const.MINION_W), Const.BOARD_REGION[1], Const.MINION_W, Const.MINION_H, null);
            // draw name for debugging
            View.drawCenteredString(g,((Card) ally.get(i)).getName(), shiftedX + (i-1)*(Const.MINION_GAP + Const.MINION_W) + Const.MINION_W/2, 
                Const.BOARD_REGION[1] + Const.MINION_H/2, new Font("Consolas", Font.PLAIN, 20));
            
            // draw attack
            View.drawCenteredString(g, Integer.toString(ally.get(i).getATK()), shiftedX + (i-1)*(Const.MINION_GAP + Const.MINION_W) + (int)(Const.MINION_ATTACK_X_RATIO*Const.MINION_W)
                                    , Const.BOARD_REGION[1] + (int)(Const.MINION_ATTACK_Y_RATIO*Const.MINION_H), new Font("Consolas", Font.BOLD, Const.MINION_SHOW_STATUS_FONT_SIZE));
            // draw health
            View.drawCenteredString(g, Integer.toString(ally.get(i).getHP()), shiftedX + (i-1)*(Const.MINION_GAP + Const.MINION_W) + (int)(Const.MINION_HEALTH_X_RATIO*Const.MINION_W)
                                    , Const.BOARD_REGION[1] + (int)(Const.MINION_HEALTH_Y_RATIO*Const.MINION_H), new Font("Consolas", Font.BOLD, Const.MINION_SHOW_STATUS_FONT_SIZE));
        }
        // draw enemy minions
        shiftedX = Const.OP_BOARD_REGION[0] + (Const.OP_BOARD_REGION[2] - (enemy.size()-2)*Const.MINION_GAP - (enemy.size()-1)*Const.MINION_W)/2;
        for(int i = 1; i < enemy.size(); i++){
            // draw minion image
            g.drawImage(View.loadImage(Const.CARD_IMG_DIR + "Goblin.png"), shiftedX + (enemy.size()-i-1)*(Const.MINION_GAP + Const.MINION_W) + (int)(Const.MINION_IMG_X_RATIO*Const.MINION_W),
                        Const.OP_BOARD_REGION[1] + (int)(Const.MINION_IMG_Y_RATIO*Const.MINION_H), Const.MINION_IMG_W, Const.MINION_IMG_H, null);
            // draw minion frame
            g.drawImage(View.loadImage(Const.MINION_FRAME_PATH), shiftedX + (enemy.size()-i-1)*(Const.MINION_GAP + Const.MINION_W), Const.OP_BOARD_REGION[1], Const.MINION_W, Const.MINION_H, null);
            // draw name for debugging
            View.drawCenteredString(g,((Card) enemy.get(i)).getName(), shiftedX + (enemy.size()-i-1)*(Const.MINION_GAP + Const.MINION_W) + Const.MINION_W/2, 
                Const.OP_BOARD_REGION[1] + Const.MINION_H/2, new Font("Consolas", Font.PLAIN, 20));
            
            // draw attack
            View.drawCenteredString(g, Integer.toString(enemy.get(i).getATK()), shiftedX + (enemy.size()-i-1)*(Const.MINION_GAP + Const.MINION_W) + (int)(Const.MINION_ATTACK_X_RATIO*Const.MINION_W)
                                    , Const.OP_BOARD_REGION[1] + (int)(Const.MINION_ATTACK_Y_RATIO*Const.MINION_H), new Font("Consolas", Font.BOLD, Const.MINION_SHOW_STATUS_FONT_SIZE));
            // draw health
            View.drawCenteredString(g, Integer.toString(enemy.get(i).getHP()), shiftedX + (enemy.size()-i-1)*(Const.MINION_GAP + Const.MINION_W) + (int)(Const.MINION_HEALTH_X_RATIO*Const.MINION_W)
                                    , Const.OP_BOARD_REGION[1] + (int)(Const.MINION_HEALTH_Y_RATIO*Const.MINION_H), new Font("Consolas", Font.BOLD, Const.MINION_SHOW_STATUS_FONT_SIZE));
        }
    }
}