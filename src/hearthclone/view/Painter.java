package hearthclone.view;

import java.awt.image.BufferedImage;

import hearthclone.model.*;

public interface Painter {
    void draw(GameInfo game, BufferedImage screenImg);
}