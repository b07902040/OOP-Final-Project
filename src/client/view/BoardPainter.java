package src.client.view;
import src.client.model.GameInfo;
import src.client.viewconstant.Const;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class BoardPainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        Graphics g = screenImg.getGraphics();
        g.drawImage(View.loadImage(Const.BACKGROUND_IMG_PATH), 0, 0, Const.SCREEN_W, Const.SCREEN_H, null);
        g.drawImage(View.loadImage(Const.BAR_IMG_PATH), Const.MIDDLE_BAR[0], Const.MIDDLE_BAR[1], Const.MIDDLE_BAR[2], Const.MIDDLE_BAR[3], null);
        g.drawImage(View.loadImage(Const.BAR_IMG_PATH), Const.BOTTOM_BAR[0], Const.BOTTOM_BAR[1], Const.BOTTOM_BAR[2], Const.BOTTOM_BAR[3], null);
        g.drawImage(View.loadImage(Const.OPPONENT_CARD_REGION_PATH), Const.OPPONENT_CARD_REGION[0], Const.OPPONENT_CARD_REGION[1], Const.OPPONENT_CARD_REGION[2], Const.OPPONENT_CARD_REGION[3], null);
        g.drawImage(View.loadImage(Const.SELECT_BUTTON_PATH), Const.SELECT_BUTTON[0], Const.SELECT_BUTTON[1], Const.SELECT_BUTTON[2], Const.SELECT_BUTTON[3], null);
    }
}