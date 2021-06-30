package heart.view;

import heart.model.*;
import heart.constant.Const;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Font;

public class DeckPainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        Graphics g = screenImg.getGraphics();
        g.drawImage(View.loadImage(Const.CARD_BACK_PATH), Const.DECK[0], Const.DECK[1], Const.DECK[2], Const.DECK[3], null);
        View.drawCenteredString(g, Integer.toString(game.getDeckSize(game.getPlayerId())), (Const.SCREEN_W + Const.DECK[0])/2, Const.DECK[1] + Const.DECK[3]/2, new Font("Consolas", Font.BOLD, Const.DECKNUM_FONT_SIZE));
        g.drawImage(View.loadImage(Const.CARD_BACK_PATH), Const.OP_DECK[0], Const.OP_DECK[1], Const.OP_DECK[2], Const.OP_DECK[3], null);
        View.drawCenteredString(g, Integer.toString(game.getDeckSize(game.getOpponentId())), (Const.SCREEN_W + Const.OP_DECK[0])/2, Const.OP_DECK[1] + Const.OP_DECK[3]/2, new Font("Consolas", Font.BOLD, Const.DECKNUM_FONT_SIZE));
    }
}