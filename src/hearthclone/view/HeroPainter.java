package hearthclone.view;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import hearthclone.constant.Const;
import hearthclone.model.Card;
import hearthclone.model.GameInfo;
import hearthclone.model.Minion;
import hearthclone.model.Targeting;

public class HeroPainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        Graphics g = screenImg.getGraphics();
        
        // draw my Hero
        Minion hero = game.getHero();
        if ((game.isMyTurn() && hero.canTargeted() && game.getState() == Const.STATE_CARD_TARGETING && ((Targeting) game.getLastShowedCard()).getCandidates(game.getAlly(), game.getEnemy()).contains(hero)))
                View.drawValidMinionEffect(g, Const.HERO[0], Const.HERO[1], Const.HERO[2], Const.HERO[3]);
        // draw hero image
        View.drawCardImage(g, (Card) hero, Const.HERO[0] + (int)(Const.MINION_IMG_X_RATIO*Const.HERO[2]),
                        Const.HERO[1] + (int)(Const.MINION_IMG_Y_RATIO*Const.HERO[3]), Const.HERO_IMG_W, Const.HERO_IMG_H);
        // draw hero frame
        g.drawImage(View.loadImage(Const.MINION_FRAME_PATH), Const.HERO[0], Const.HERO[1], Const.HERO[2], Const.HERO[3], null);
        View.drawCenteredString(g, Integer.toString(hero.getHP()), Const.HERO[0] + (int)(Const.MINION_HEALTH_X_RATIO*Const.HERO[2]),
                                Const.HERO[1] + (int)(Const.MINION_HEALTH_Y_RATIO*Const.HERO[3]), new Font("Consolas", Font.BOLD, Const.HERO_SHOW_STATUS_FONT_SIZE));
        // draw opponent Hero
        hero = game.getOpponentHero();
        if ((game.isMyTurn() && game.canAttacked(game.getOpponentId(), 0) && game.getState() == Const.STATE_ATTACKER_TARGETING) ||
            (game.isMyTurn() && hero.canTargeted() && game.getState() == Const.STATE_CARD_TARGETING && ((Targeting) game.getLastShowedCard()).getCandidates(game.getAlly(), game.getEnemy()).contains(hero)))
                View.drawValidMinionEffect(g, Const.OP_HERO[0], Const.OP_HERO[1], Const.OP_HERO[2], Const.OP_HERO[3]);

        View.drawCardImage(g, (Card) hero, Const.OP_HERO[0] + (int)(Const.MINION_IMG_X_RATIO*Const.OP_HERO[2]),
                        Const.OP_HERO[1] + (int)(Const.MINION_IMG_Y_RATIO*Const.OP_HERO[3]), Const.HERO_IMG_W, Const.HERO_IMG_H);

        g.drawImage(View.loadImage(Const.MINION_FRAME_PATH), Const.OP_HERO[0], Const.OP_HERO[1], Const.OP_HERO[2], Const.OP_HERO[3], null);
        View.drawCenteredString(g, Integer.toString(hero.getHP()), Const.OP_HERO[0] + (int)(Const.MINION_HEALTH_X_RATIO*Const.OP_HERO[2]),
                                Const.OP_HERO[1] + (int)(Const.MINION_HEALTH_Y_RATIO*Const.OP_HERO[3]), new Font("Consolas", Font.BOLD, Const.HERO_SHOW_STATUS_FONT_SIZE));
    }
}