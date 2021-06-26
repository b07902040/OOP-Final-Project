package src.client.view;
import src.client.model.GameInfo;
import src.client.viewconstant.Const;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Font;

public class ManaPainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        int id = game.getPlayerId();
        int fullMana = game.getFullMana(id);
        int mana = game.getMana(id);
        Graphics g = screenImg.getGraphics();
        for(int i = 0; i < fullMana; i++){
            if(i < mana)
                g.drawImage(View.loadImage(Const.FULL_MANA_PATH), Const.MANA_X + i*(Const.MANA_GAP + Const.MANA_SIZE), Const.MANA_Y, Const.MANA_SIZE, Const.MANA_SIZE, null);
            else
                g.drawImage(View.loadImage(Const.EMPTY_MANA_PATH), Const.MANA_X + i*(Const.MANA_GAP + Const.MANA_SIZE), Const.MANA_Y, Const.MANA_SIZE, Const.MANA_SIZE, null);
        }
        g.setFont(new Font("Consolas", Font.PLAIN, Const.MANA_FONT_SIZE));
        g.drawString(Integer.toString(mana) + "/" + Integer.toString(fullMana), Const.MANA_X + Const.MAX_MANA*(Const.MANA_GAP + Const.MANA_SIZE), Const.MANA_FONT_Y);
    }
}