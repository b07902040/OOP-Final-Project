package src.client.view;
import src.client.model.GameInfo;
import src.client.viewconstant.Const;
import src.model.Card;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Font;

public class ShowCardPainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        int opponentId = 1 - game.getPlayerId();
        Graphics g = screenImg.getGraphics();
        List<Card> handCards = game.getHandCards();
        int shiftedX = Const.HANDCARD_REGION[0] + (Const.HANDCARD_REGION[2] - (handCards.size()-1)*Const.HANDCARD_GAP - handCards.size()*Const.CARD_W)/2;
        for(int i = 0; i < handCards.size(); i++){
            g.drawImage(View.loadImage(Const.CARD_IMG_DIR + handCards.get(i).getName() + ".png"), shiftedX + i*(Const.HANDCARD_GAP + Const.CARD_W), Const.HANDCARD_REGION[1], Const.CARD_W, Const.CARD_H, null);
            g.drawImage(View.loadImage(Const.CARD_FRAME_PATH), shiftedX + i*(Const.HANDCARD_GAP + Const.CARD_W), Const.HANDCARD_REGION[1], Const.CARD_W, Const.CARD_H, null);
        }
        int opponentHandSize = game.getHandSize(opponentId);
        shiftedX = Const.OPPONENT_CARD_REGION[0] + (Const.OPPONENT_CARD_REGION[2] - (opponentHandSize-1)*Const.OP_HANDCARD_GAP - opponentHandSize*Const.CARD_W)/2;
        for(int i = 0; i < game.getHandSize(opponentId); i++){
            g.drawImage(View.loadImage(Const.CARD_BACK_PATH), shiftedX + i*(Const.OP_HANDCARD_GAP + Const.CARD_W), Const.OP_HANDCARD_Y, Const.CARD_W, Const.CARD_H, null);
        }
    }
}