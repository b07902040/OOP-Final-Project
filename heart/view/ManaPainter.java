package heart.view;
import java.awt.image.BufferedImage;

import heart.model.*;
import heart.constant.Const;

import java.awt.Graphics;
import java.awt.Font;

public class ManaPainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        int id = game.getPlayerId();
        int fullMana = game.getFullMana(id);
        int mana = game.getMana(id);
        Graphics g = screenImg.getGraphics();
        // draw mana
        for(int i = 0; i < fullMana; i++){
            if(i < mana)
                g.drawImage(View.loadImage(Const.FULL_MANA_PATH), Const.MANA_X + i*(Const.MANA_GAP + Const.MANA_SIZE), Const.MANA_Y, Const.MANA_SIZE, Const.MANA_SIZE, null);
            else
                g.drawImage(View.loadImage(Const.EMPTY_MANA_PATH), Const.MANA_X + i*(Const.MANA_GAP + Const.MANA_SIZE), Const.MANA_Y, Const.MANA_SIZE, Const.MANA_SIZE, null);
        }
        // draw my mana message
        g.setFont(new Font("Consolas", Font.PLAIN, Const.MANA_FONT_SIZE));
        View.drawCenteredString(g, Integer.toString(mana) + "/" + Integer.toString(fullMana), Const.MANA_MESSAGE_X, Const.MANA_MESSAGE_Y, new Font("Consolas", Font.PLAIN, Const.MANA_FONT_SIZE));
        // draw opponent mana message
        id = 1 - id;
        fullMana = game.getFullMana(id);
        mana = game.getMana(id);
        View.drawCenteredString(g, Integer.toString(mana) + "/" + Integer.toString(fullMana), Const.OP_MANA_BOX[0] + Const.OP_MANA_BOX[2]/2,
                                Const.OP_MANA_MESSAGE_Y, new Font("Consolas", Font.PLAIN, Const.MANA_FONT_SIZE));
    }
}