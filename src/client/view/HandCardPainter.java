package src.client.view;
import src.client.model.GameInfo;
import src.client.viewconstant.Const;
import src.model.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Font;

public class HandCardPainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        int opponentId = 1 - game.getPlayerId();
        Graphics g = screenImg.getGraphics();
        List<Card> handCards = game.getHandCards();
        Card card;
        int shiftedX = Const.HANDCARD_REGION[0] + (Const.HANDCARD_REGION[2] - (handCards.size()-1)*Const.HANDCARD_GAP - handCards.size()*Const.CARD_W)/2;
        for(int i = 0; i < handCards.size(); i++){
            card = handCards.get(i);
            // g.drawImage(View.loadImage(Const.CARD_IMG_DIR + card.getName() + ".png"), shiftedX + i*(Const.HANDCARD_GAP + Const.CARD_W), Const.HANDCARD_REGION[1], Const.CARD_W, Const.CARD_H, null);
            g.drawImage(View.loadImage(Const.CARD_IMG_DIR + "Goblin.png"), shiftedX + i*(Const.HANDCARD_GAP + Const.CARD_W), Const.HANDCARD_REGION[1], Const.CARD_W, Const.CARD_H, null);
            if(card instanceof Minion)
                g.drawImage(View.loadImage(Const.MINION_CARD_FRAME_PATH), shiftedX + i*(Const.HANDCARD_GAP + Const.CARD_W), Const.HANDCARD_REGION[1], Const.CARD_W, Const.CARD_H, null);
            else if(card instanceof Spell)
                g.drawImage(View.loadImage(Const.SPELL_CARD_FRAME_PATH), shiftedX + i*(Const.HANDCARD_GAP + Const.CARD_W), Const.HANDCARD_REGION[1], Const.CARD_W, Const.CARD_H, null);
            g.setFont(new Font("Consolas", Font.PLAIN, Const.HANDCARD_NAME_FONT_SIZE));
            g.drawString(card.getName(), shiftedX + i*(Const.HANDCARD_GAP + Const.CARD_W) + (int) (Const.CARD_NAME_X_RATIO*Const.CARD_W), Const.HANDCARD_REGION[1] + (int) (Const.CARD_NAME_Y_RATIO*Const.CARD_H));
        }
        int opponentHandSize = game.getHandSize(opponentId);
        shiftedX = Const.OPPONENT_CARD_REGION[0] + (Const.OPPONENT_CARD_REGION[2] - (opponentHandSize-1)*Const.OP_HANDCARD_GAP - opponentHandSize*Const.CARD_W)/2;
        for(int i = 0; i < game.getHandSize(opponentId); i++){
            g.drawImage(View.loadImage(Const.CARD_BACK_PATH), shiftedX + i*(Const.OP_HANDCARD_GAP + Const.CARD_W), Const.OP_HANDCARD_Y, Const.CARD_W, Const.CARD_H, null);
        }
    }
}