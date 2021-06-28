package src.client.view;
import src.client.model.GameInfo;
import src.client.viewconstant.Const;
import src.model.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Font;

public class AttackAnimation implements Animation {
    private int timer = 0;
    private static final int forwadTicks = Const.FPS/4;
    private static final int hitTicks = Const.FPS/20;
    private static final int retrieveTicks = Const.FPS/4;
    private static final int endTime = forwadTicks + hitTicks + retrieveTicks;

    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        int targetX = game.getAttackedPosition()[0];
        int targetY = game.getAttackedPosition()[1];
        int attackerX = game.getAttackerPosition()[0];
        int attackerY = game.getAttackerPosition()[1];
        int x, y;
        if(timer < forwadTicks){
            System.out.println("first stage");
            x = attackerX + (targetX - attackerX)*(timer + 1)/forwadTicks;
            y = attackerY + (targetY - attackerY)*(timer + 1)/forwadTicks;
        }
        else if(timer < forwadTicks + hitTicks){
            System.out.println("second stage");
            x = targetX;
            y = targetY;
        }
        else if(timer < endTime){
            System.out.println("last stage");
            x = targetX + (attackerX - targetX)*(timer + 1 - forwadTicks - hitTicks)/retrieveTicks;
            y = targetY + (attackerY - targetY)*(timer + 1 - forwadTicks - hitTicks)/retrieveTicks;
        }
        else{
            return;
        }
        Graphics g = screenImg.getGraphics();
        View.drawMinion(g, game.getAttacker(), x, y);
        System.out.printf("animation drawing %d %d\n", x, y);
        System.out.printf("attacker index: %d, target index: %d\n", game.getAttackerIndex(), game.getAttackedIndex());
        System.out.printf("attacker %d %d\n", attackerX, attackerY);
        System.out.printf("target %d %d\n", targetX, targetY);
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