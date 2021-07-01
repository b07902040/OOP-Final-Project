package hearthclone.view;

import java.awt.image.BufferedImage;

import hearthclone.constant.Const;
import hearthclone.model.*;

import java.awt.Graphics;
import java.awt.Font;

public class DeckPainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        Graphics g = screenImg.getGraphics();
        // draw my deck
        g.drawImage(View.loadImage(Const.CARD_BACK_PATH), Const.DECK[0], Const.DECK[1], Const.DECK[2], Const.DECK[3], null);
        View.drawCenteredString(g, Integer.toString(game.getDeckSize(game.getPlayerId())), (Const.SCREEN_W + Const.DECK[0])/2, Const.DECK[1] + Const.DECK[3]/2, new Font("Consolas", Font.BOLD, Const.DECKNUM_FONT_SIZE));
        // draw opponent deck
        g.drawImage(View.loadImage(Const.CARD_BACK_PATH), Const.OP_DECK[0], Const.OP_DECK[1], Const.OP_DECK[2], Const.OP_DECK[3], null);
        View.drawCenteredString(g, Integer.toString(game.getDeckSize(game.getOpponentId())), (Const.SCREEN_W + Const.OP_DECK[0])/2, Const.OP_DECK[1] + Const.OP_DECK[3]/2, new Font("Consolas", Font.BOLD, Const.DECKNUM_FONT_SIZE));
    }
}