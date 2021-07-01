package hearthclone.view;
import java.awt.image.BufferedImage;

import hearthclone.constant.Const;
import hearthclone.model.*;

import java.awt.Graphics;

public class BoardPainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        int state = game.getState();
        Graphics g = screenImg.getGraphics();
        // draw back ground
        g.drawImage(View.loadImage(Const.BACKGROUND_IMG_PATH), 0, 0, Const.SCREEN_W, Const.SCREEN_H, null);
        // draw middle bar
        g.drawImage(View.loadImage(Const.BAR_IMG_PATH), Const.MIDDLE_BAR[0], Const.MIDDLE_BAR[1], Const.MIDDLE_BAR[2], Const.MIDDLE_BAR[3], null);
        // draw bottom bar
        g.drawImage(View.loadImage(Const.BAR_IMG_PATH), Const.BOTTOM_BAR[0], Const.BOTTOM_BAR[1], Const.BOTTOM_BAR[2], Const.BOTTOM_BAR[3], null);
        // draw opponent card region
        g.drawImage(View.loadImage(Const.OPPONENT_CARD_REGION_PATH), Const.OPPONENT_CARD_REGION[0], Const.OPPONENT_CARD_REGION[1], Const.OPPONENT_CARD_REGION[2], Const.OPPONENT_CARD_REGION[3], null);
        // draw select button
        if(game.isMyTurn() && (state == Const.STATE_VALID_CARD || state == Const.STATE_VALID_ATTACKER || state == Const.STATE_VALID_ATTACKED || state == Const.STATE_VALID_TARGET))
            g.drawImage(View.loadImage(Const.SELECT_BUTTON_PATH), Const.SELECT_BUTTON[0], Const.SELECT_BUTTON[1], Const.SELECT_BUTTON[2], Const.SELECT_BUTTON[3], null);
        else g.drawImage(View.loadImage(Const.DIM_SELECT_BUTTON_PATH), Const.SELECT_BUTTON[0], Const.SELECT_BUTTON[1], Const.SELECT_BUTTON[2], Const.SELECT_BUTTON[3], null);
        // draw end turn button
        if(game.isMyTurn() && state == Const.STATE_PENDING)
            g.drawImage(View.loadImage(Const.ENDTURN_BUTTON_PATH), Const.ENDTURN_BUTTON[0], Const.ENDTURN_BUTTON[1], Const.ENDTURN_BUTTON[2], Const.ENDTURN_BUTTON[3], null);
        else g.drawImage(View.loadImage(Const.DIM_ENDTURN_BUTTON_PATH), Const.ENDTURN_BUTTON[0], Const.ENDTURN_BUTTON[1], Const.ENDTURN_BUTTON[2], Const.ENDTURN_BUTTON[3], null);
        // draw my mana box
        g.drawImage(View.loadImage(Const.MANA_BOX_IMG_PATH), Const.MANA_BOX[0], Const.MANA_BOX[1], Const.MANA_BOX[2], Const.MANA_BOX[3], null);
        // draw opponent mana box
        g.drawImage(View.loadImage(Const.OPPONENT_MANA_BOX_IMG_PATH), Const.OP_MANA_BOX[0], Const.OP_MANA_BOX[1], Const.OP_MANA_BOX[2], Const.OP_MANA_BOX[3], null);
        // draw message box
        g.drawImage(View.loadImage(Const.MESSAGE_BOX_IMG_PATH), Const.MESSAGE_BOX[0], Const.MESSAGE_BOX[1], Const.MESSAGE_BOX[2], Const.MESSAGE_BOX[3], null);
    }
}