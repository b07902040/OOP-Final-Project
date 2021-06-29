package heart.view;
import heart.constant.Const;
import heart.model.*;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Font;

public class HeroPainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        Graphics g = screenImg.getGraphics();
        
        // draw my Hero
        // draw hero image
        View.drawCardImage(g, (Card) game.getHero(), Const.HERO[0] + (int)(Const.MINION_IMG_X_RATIO*Const.HERO[2]),
                        Const.HERO[1] + (int)(Const.MINION_IMG_Y_RATIO*Const.HERO[3]), Const.HERO_IMG_W, Const.HERO_IMG_H);
        // draw hero frame
        g.drawImage(View.loadImage(Const.MINION_FRAME_PATH), Const.HERO[0], Const.HERO[1], Const.HERO[2], Const.HERO[3], null);
        View.drawCenteredString(g, Integer.toString(game.getHero().getHP()), Const.HERO[0] + (int)(Const.MINION_HEALTH_X_RATIO*Const.HERO[2]),
                                Const.HERO[1] + (int)(Const.MINION_HEALTH_Y_RATIO*Const.HERO[3]), new Font("Consolas", Font.BOLD, Const.HERO_SHOW_STATUS_FONT_SIZE));
        // draw opponent Hero
        //g.drawImage(View.loadImage(Const.CARD_IMG_DIR + "Goblin.png"), Const.OP_HERO[0] + (int)(Const.MINION_IMG_X_RATIO*Const.OP_HERO[2]),
                        //Const.OP_HERO[1] + (int)(Const.MINION_IMG_Y_RATIO*Const.OP_HERO[3]), Const.HERO_IMG_W, Const.HERO_IMG_H, null);
        
        if (game.isMyTurn() && game.getOpponentHero().canAttacked() && game.getState() == Const.STATE_ATTACKER_TARGETING)
                        g.drawImage(View.loadImage(Const.CAN_ATTACK_EFFECT_PATH),
                                Const.OP_HERO[0] + (int) (Const.CAN_ATTACK_EFFECT_X_RATIO * Const.OP_HERO[2]),
                                Const.OP_HERO[1] + (int) (Const.CAN_ATTACK_EFFECT_Y_RATIO * Const.OP_HERO[3]),
                                (int) (Const.CAN_ATTACK_EFFECT_W_RATIO * Const.OP_HERO[2]),
                                (int) (Const.CAN_ATTACK_EFFECT_H_RATIO * Const.OP_HERO[3]), null);

        View.drawCardImage(g, (Card) game.getOpponentHero(), Const.OP_HERO[0] + (int)(Const.MINION_IMG_X_RATIO*Const.OP_HERO[2]),
                        Const.OP_HERO[1] + (int)(Const.MINION_IMG_Y_RATIO*Const.OP_HERO[3]), Const.HERO_IMG_W, Const.HERO_IMG_H);

        g.drawImage(View.loadImage(Const.MINION_FRAME_PATH), Const.OP_HERO[0], Const.OP_HERO[1], Const.OP_HERO[2], Const.OP_HERO[3], null);
        View.drawCenteredString(g, Integer.toString(game.getOpponentHero().getHP()), Const.OP_HERO[0] + (int)(Const.MINION_HEALTH_X_RATIO*Const.OP_HERO[2]),
                                Const.OP_HERO[1] + (int)(Const.MINION_HEALTH_Y_RATIO*Const.OP_HERO[3]), new Font("Consolas", Font.BOLD, Const.HERO_SHOW_STATUS_FONT_SIZE));
    }
}