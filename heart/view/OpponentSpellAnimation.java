package heart.view;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import heart.constant.Const;
import heart.model.Card;
import heart.model.GameInfo;
import heart.model.Spell;

public class OpponentSpellAnimation implements Animation {
    private int timer = 0;
    private static final int endTime = Const.FPS;

    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        Graphics g = screenImg.getGraphics();
        Card card = game.getEffectingCard();
        if(!(card instanceof Spell)){
            this.stop();
            return;
        }
        if(this.timer < endTime){
            // draw card image
            View.drawCardImage(g, card, Const.SPELL_SHOW[0] + (int)(Const.CARD_IMG_X_RATIO*Const.SPELL_SHOW[2]),
                                Const.SPELL_SHOW[1] + (int)(Const.CARD_IMG_Y_RATIO*Const.SPELL_SHOW[3]), (int)(Const.CARD_IMG_W_RATIO*Const.SPELL_SHOW[2]), (int)(Const.CARD_IMG_H_RATIO*Const.SPELL_SHOW[3]));
            g.drawImage(View.loadImage(Const.SPELL_CARD_FRAME_PATH), Const.SPELL_SHOW[0], Const.SPELL_SHOW[1], Const.SPELL_SHOW[2], Const.SPELL_SHOW[3], null);
            // draw mana cost
            g.setColor(new Color(25, 70, 130));
            View.drawCenteredString(g, Integer.toString(card.getCost()), Const.SPELL_SHOW[0] + (int)(Const.CARD_MANA_X_RATIO*Const.SPELL_SHOW[2]),
                                    Const.SPELL_SHOW[1] + (int)(Const.CARD_MANA_Y_RATIO*Const.SPELL_SHOW[3]), new Font("Consolas", Font.BOLD, Const.CARD_SHOW_STATUS_FONT_SIZE));
            // draw card name
            g.setColor(Color.WHITE);
            View.drawCenteredString(g, card.getName(), Const.SPELL_SHOW[0] + (int) (Const.CARD_NAME_X_RATIO*Const.SPELL_SHOW[2]),
                                    Const.SPELL_SHOW[1] + (int) (Const.CARD_NAME_Y_RATIO*Const.SPELL_SHOW[3]), new Font("Consolas", Font.PLAIN, Const.SHOWEDCARD_NAME_FONT_SIZE));
            // draw description
            View.drawRectString(g, card.getDescription(), Const.SPELL_SHOW[0] + (int) (Const.CARD_DESCRIPTION_X_RATIO*Const.SPELL_SHOW[2]),
                                    Const.SPELL_SHOW[1] + (int) (Const.CARD_DESCRIPTION_Y_RATIO*Const.SPELL_SHOW[3]), (int)(Const.CARD_DESCRIPTION_W_RATIO*Const.SPELL_SHOW[2]),
                                    new Font("Consolas", Font.PLAIN, Const.DESCRIPTION_FONT_SIZE));
        }
        update();
    }

    @Override
    public boolean isExpired(){
        return (this.timer >= endTime);
    }

    @Override
    public void stop(){
        this.timer = endTime;
    }

    @Override
    public void update(){
        this.timer ++;
    }
}