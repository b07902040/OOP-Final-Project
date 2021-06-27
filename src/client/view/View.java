package src.client.view;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import src.event.*;
// import src.model.*;
// import src.constant.*;
import src.client.viewconstant.*;
import src.client.controller.Controller;
import src.client.model.GameInfo;
import src.client.view.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Dimension;


public class View implements EventListener{
    private EventManager eventManager;
    private GameInfo model;
    private Controller controller;
    private JFrame screen;
    private Board board;
    private BufferedImage onScreenImage;
    private BufferedImage offScreenImage;
    private List<Painter> painters;
    private static HashMap<String, BufferedImage> imgLib = new HashMap<String, BufferedImage>(128);
    
    public View(EventManager eventManager, GameInfo model, Controller controller) {
        this.eventManager = eventManager;
        this.eventManager.register(this);
        this.model = model;
        this.controller = controller;
    }

    public void initialize(){
        this.screen = new JFrame();
        this.board = new Board(this);
        this.onScreenImage = new BufferedImage(Const.SCREEN_W, Const.SCREEN_H, BufferedImage.TYPE_INT_ARGB);
        this.offScreenImage = new BufferedImage(Const.SCREEN_W, Const.SCREEN_H, BufferedImage.TYPE_INT_ARGB);
        this.painters = new ArrayList<Painter>();

        painters.add(new BoardPainter());
        painters.add(new ManaPainter());
        painters.add(new HandCardPainter());
        painters.add(new MinionPainter());
        painters.add(new ShowCardPainter());

        this.board.addMouseListener(this.controller);
        this.screen.add(this.board);
        this.screen.pack();
        update();
        this.screen.setVisible(true);
        return;
    };

    @Override
    public void notify(Event event){
        if(event instanceof EventInitialize){
            this.initialize();
        }
        else if(event instanceof EventEveryTick){
            update();
            return;
        }
        else if(event instanceof EventTurnStart){
            update();
        }
    }

    private void update(){
        for(Painter painter : this.painters){
            painter.draw(this.model, this.offScreenImage);
        }
        flip();
        this.board.repaint();
        this.screen.revalidate();
    }

    private void flip(){
        BufferedImage tmp = this.onScreenImage;
        this.onScreenImage = this.offScreenImage;
        this.offScreenImage = tmp;
    }
    
    public BufferedImage getOnScreen(){
        return this.onScreenImage;
    }

    public static BufferedImage loadImage(String path){
        if(imgLib.containsKey(path))
            return imgLib.get(path);
        BufferedImage image;
        try{
            File imageFile = new File(path);
            image = ImageIO.read(imageFile);
            imgLib.put(path, image);
            return image;
        } catch (IOException e){
            System.out.printf("Error: image %s cannot be loaded.\n", path);
            return null;
        }
    }

    
}

class Board extends JPanel {
    private View view;
    public Board(View view){
        this.view = view;
        this.setPreferredSize(new Dimension(Const.SCREEN_W, Const.SCREEN_H));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(this.view.getOnScreen(), 0, 0, Const.SCREEN_W, Const.SCREEN_H, this);
    }
}
