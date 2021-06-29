package heart.view;
import java.awt.image.BufferedImage;

import heart.model.*;

public interface Painter {
    void draw(GameInfo game, BufferedImage screenImg);
}