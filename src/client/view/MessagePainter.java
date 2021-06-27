package src.client.view;
import src.client.model.GameInfo;
import src.client.viewconstant.Const;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Font;

public class MessagePainter implements Painter {
    @Override
    public void draw(GameInfo game, BufferedImage screenImg){
        Graphics g = screenImg.getGraphics();
        g.setFont(new Font("Consolas", Font.PLAIN, Const.MESSAGE_FONT_SIZE));
        if(game.isMyTurn())
            g.drawString("My Turn", Const.MESSAGE_X, Const.MESSAGE_Y);
        else
            g.drawString("Opponent's Turn", Const.MESSAGE_X, Const.MESSAGE_Y);
    }
}