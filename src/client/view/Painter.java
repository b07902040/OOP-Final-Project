package src.client.view;
import src.client.model.GameInfo;
import java.awt.image.BufferedImage;

public interface Painter {
    void draw(GameInfo game, BufferedImage screenImg);
}