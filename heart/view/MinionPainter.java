package heart.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import heart.constant.Const;
import heart.model.GameInfo;
import heart.model.Minion;
import heart.model.Targeting;

public class MinionPainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg) {
        int id = game.getPlayerId();
        int opponentId = game.getOpponentId();
        Graphics g = screenImg.getGraphics();
        List<Minion> ally = game.getAlly();
        List<Minion> enemy = game.getEnemy();
        int x, y;
        Minion minion;
        // draw ally minions
        for (int i = 1; i < ally.size(); i++) {
            if (game.getState() == Const.STATE_ATTACKING && game.getAttackerIndex() == i
                    && game.getAttackerPlayerIndex() == id)
                continue;
            minion = ally.get(i);
            x = game.getMinionPosition(id, i)[0];
            y = game.getMinionPosition(id, i)[1];
            if ((game.isMyTurn() && minion.canAttack() && game.getState() == Const.STATE_PENDING) ||
                (game.isMyTurn() && minion.canTargeted() && game.getState() == Const.STATE_CARD_TARGETING && ((Targeting) game.getLastShowedCard()).getCandidates(game.getHero().getMaster()).contains(minion)))
                View.drawValidMinionEffect(g, x, y, Const.MINION_W, Const.MINION_H);
            View.drawMinion(g, minion, x, y, 1);

        }
        // draw enemy minions
        for (int i = 1; i < enemy.size(); i++) {
            if (game.getState() == Const.STATE_ATTACKING && game.getAttackerIndex() == i
                    && game.getAttackerPlayerIndex() == opponentId)
                continue;
            minion = enemy.get(i);
            x = game.getMinionPosition(opponentId, i)[0];
            y = game.getMinionPosition(opponentId, i)[1];
            if ((game.isMyTurn() && minion.canAttacked() && game.getState() == Const.STATE_ATTACKER_TARGETING) ||
                (game.isMyTurn() && minion.canTargeted() && game.getState() == Const.STATE_CARD_TARGETING && ((Targeting) game.getLastShowedCard()).getCandidates(game.getHero().getMaster()).contains(minion)))
                View.drawValidMinionEffect(g, x, y, Const.MINION_W, Const.MINION_H);
            View.drawMinion(g, minion, x, y, 1);
        }
    }
}