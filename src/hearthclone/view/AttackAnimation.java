package hearthclone.view;

import java.awt.image.BufferedImage;

import hearthclone.constant.Const;
import hearthclone.model.*;

import java.awt.Graphics;

public class AttackAnimation implements Animation {
    private int timer = 0;
    private static final double attackScale = 1.1;
    private static final int scalingTicks = Const.FPS / 10;
    private static final int floatingTicks = Const.FPS / 6;
    private static final int forwadTicks = Const.FPS / 10;
    private static final int retrieveTicks = Const.FPS / 10;
    private static final int endTime = scalingTicks + floatingTicks + forwadTicks + retrieveTicks;

    @Override
    public void draw(GameInfo game, BufferedImage screenImg) {
        int targetX = game.getAttackedPosition()[0];
        int targetY = game.getAttackedPosition()[1];
        int attackerX = game.getAttackerPosition()[0];
        int attackerY = game.getAttackerPosition()[1];
        int x, y;
        double scale;
        if (timer < scalingTicks) {
            x = attackerX;
            y = attackerY;
            scale = 1 + (attackScale - 1) * (timer + 1) / scalingTicks;
        } else if (timer < scalingTicks + floatingTicks) {
            x = attackerX;
            y = attackerY;
            scale = attackScale;
        } else if (timer < scalingTicks + floatingTicks + forwadTicks) {
            x = attackerX + (targetX - attackerX) * (timer + 1 - (scalingTicks + floatingTicks)) / forwadTicks;
            y = attackerY + (targetY - attackerY) * (timer + 1 - (scalingTicks + floatingTicks)) / forwadTicks;
            scale = attackScale;
        } else if (timer < endTime) {
            x = targetX + (attackerX - targetX) * (timer + 1 - (scalingTicks + floatingTicks + forwadTicks))
                    / retrieveTicks;
            y = targetY + (attackerY - targetY) * (timer + 1 - (scalingTicks + floatingTicks + forwadTicks))
                    / retrieveTicks;
            scale = attackScale
                    + (1 - attackScale) * (timer + 1 - (scalingTicks + floatingTicks + forwadTicks)) / retrieveTicks;
        } else {
            return;
        }
        Graphics g = screenImg.getGraphics();
        View.drawMinion(g, game.getAttacker(), x, y, scale);
        update();
    }

    @Override
    public boolean isExpired() {
        return (this.timer >= endTime);
    }

    @Override
    public void stop() {
        this.timer = endTime;
    }

    @Override
    public void update() {
        this.timer++;
    }
}