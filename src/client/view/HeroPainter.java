package src.client.view;
import src.client.model.GameInfo;
import src.client.viewconstant.Const;
import src.model.Minion;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Font;

public class HeroPainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        Graphics g = screenImg.getGraphics();
        
        // draw ally minions
        g.drawImage(View.loadImage(Const.MINION_FRAME_PATH), Const.HERO[0], Const.HERO[1], Const.HERO[2], Const.HERO[3], null);
        // draw enemy minions
        g.drawImage(View.loadImage(Const.MINION_FRAME_PATH), Const.OP_HERO[0], Const.OP_HERO[1], Const.OP_HERO[2], Const.OP_HERO[3], null);

    }
}