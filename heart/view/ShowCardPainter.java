package heart.view;

import heart.constant.Const;
import heart.model.*;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class ShowCardPainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        if(!game.isMyTurn()) return;
        Graphics g = screenImg.getGraphics();
        int state = game.getState();
        Card card;
        if(state == Const.STATE_VALID_CARD || state == Const.STATE_INVALID_CARD){
            int index = game.getShowedCardIndex();
            card = game.getHandCards().get(index);
            
        }
        else if(state == Const.STATE_VALID_ATTACKER || state == Const.STATE_VALID_ATTACKED || state == Const.STATE_VALID_TARGET || 
                state == Const.STATE_INVALID_ATTACKER || state == Const.STATE_INVALID_ATTACKED || state == Const.STATE_INVALID_TARGET){
            int playerId = game.getShowedMinionPlayerId();
            int index = game.getShowedMinionIndex();
            if(playerId == game.getPlayerId())
                card = (Card) game.getAlly().get(index);
            else 
                card = (Card) game.getEnemy().get(index);
        }
        else return;
        // draw card image
        View.drawCardImage(g, card, Const.CARD_SHOW[0] + (int)(Const.CARD_IMG_X_RATIO*Const.CARD_SHOW[2]),
                            Const.CARD_SHOW[1] + (int)(Const.CARD_IMG_Y_RATIO*Const.CARD_SHOW[3]), (int)(Const.CARD_IMG_W_RATIO*Const.CARD_SHOW[2]), (int)(Const.CARD_IMG_H_RATIO*Const.CARD_SHOW[3]));
        if(card instanceof Minion){
            // draw card frame
            g.drawImage(View.loadImage(Const.MINION_CARD_FRAME_PATH), Const.CARD_SHOW[0], Const.CARD_SHOW[1], Const.CARD_SHOW[2], Const.CARD_SHOW[3], null);
            // draw attack
            View.drawCenteredString(g, Integer.toString(((Minion)card).getATK()), Const.CARD_SHOW[0] + (int)(Const.CARD_ATTACK_X_RATIO*Const.CARD_SHOW[2])
                                    , Const.CARD_SHOW[1] + (int)(Const.CARD_ATTACK_Y_RATIO*Const.CARD_SHOW[3]), new Font("Consolas", Font.BOLD, Const.CARD_SHOW_STATUS_FONT_SIZE));
            // draw health
            View.drawCenteredString(g, Integer.toString(((Minion)card).getHP()), Const.CARD_SHOW[0] + (int)(Const.CARD_HEALTH_X_RATIO*Const.CARD_SHOW[2])
                                    , Const.CARD_SHOW[1] + (int)(Const.CARD_HEALTH_Y_RATIO*Const.CARD_SHOW[3]), new Font("Consolas", Font.BOLD, Const.CARD_SHOW_STATUS_FONT_SIZE));
        }
        else if(card instanceof Spell)
            g.drawImage(View.loadImage(Const.SPELL_CARD_FRAME_PATH), Const.CARD_SHOW[0], Const.CARD_SHOW[1], Const.CARD_SHOW[2], Const.CARD_SHOW[3], null);
        // draw mana cost
        g.setColor(new Color(25, 70, 130));
        View.drawCenteredString(g, Integer.toString(card.getCost()), Const.CARD_SHOW[0] + (int)(Const.CARD_MANA_X_RATIO*Const.CARD_SHOW[2]),
                                Const.CARD_SHOW[1] + (int)(Const.CARD_MANA_Y_RATIO*Const.CARD_SHOW[3]), new Font("Consolas", Font.BOLD, Const.CARD_SHOW_STATUS_FONT_SIZE));
        // draw card name
        g.setColor(Color.WHITE);
        View.drawCenteredString(g, card.getName(), Const.CARD_SHOW[0] + (int) (Const.CARD_NAME_X_RATIO*Const.CARD_SHOW[2]),
                                Const.CARD_SHOW[1] + (int) (Const.CARD_NAME_Y_RATIO*Const.CARD_SHOW[3]), new Font("Consolas", Font.PLAIN, Const.SHOWEDCARD_NAME_FONT_SIZE));
        // draw description
        //g.drawImage(View.loadImage(Const.CARD_IMG_DIR + "default.png"), Const.CARD_SHOW[0] + (int) (Const.CARD_DESCRIPTION_X_RATIO*Const.CARD_SHOW[2]),
                                //Const.CARD_SHOW[1] + (int) (Const.CARD_DESCRIPTION_Y_RATIO*Const.CARD_SHOW[3]), (int)(Const.CARD_DESCRIPTION_W_RATIO*Const.CARD_SHOW[2]), 100, null);
        View.drawRectString(g, card.getDescription(), Const.CARD_SHOW[0] + (int) (Const.CARD_DESCRIPTION_X_RATIO*Const.CARD_SHOW[2]),
                                Const.CARD_SHOW[1] + (int) (Const.CARD_DESCRIPTION_Y_RATIO*Const.CARD_SHOW[3]), (int)(Const.CARD_DESCRIPTION_W_RATIO*Const.CARD_SHOW[2]),
                                new Font("Consolas", Font.PLAIN, Const.DESCRIPTION_FONT_SIZE));
    }
}