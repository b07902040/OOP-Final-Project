package src.client.view;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import src.event.*;
import src.model.*;
// import src.constant.*;
import src.client.viewconstant.*;
import src.client.controller.Controller;
import src.client.model.GameInfo;
import src.client.view.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;


public class View implements EventListener{
    private EventManager eventManager;
    private GameInfo model;
    private Controller controller;
    private JFrame screen;
    private Board board;
    private BufferedImage onScreenImage;
    private BufferedImage offScreenImage;
    private List<Painter> painters;
    private Animation attackAnimation;
    private boolean attacking;
    private List<Animation> animations;
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
        this.animations = new ArrayList<Animation>();
        this.attacking = false;

        painters.add(new BoardPainter());
        painters.add(new ManaPainter());
        painters.add(new HandCardPainter());
        painters.add(new MinionPainter());
        painters.add(new HeroPainter());
        painters.add(new ShowCardPainter());
        painters.add(new MessagePainter());

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
            for (int i = this.animations.size() - 1; i >= 0; i--){
                if(animations.get(i).isExpired()){
                    painters.remove((Painter) animations.get(i));
                    if(animations.get(i) == this.attackAnimation){
                        this.attacking = false;
                        this.eventManager.post(new EventMinionAttacked());
                    }
                    animations.remove(i);
                }
            }
            if(this.model.getState() == Const.STATE_ATTACKING && !this.attacking){
                this.attackAnimation = new AttackAnimation();
                this.painters.add((Painter) this.attackAnimation);
                this.animations.add(this.attackAnimation);
                this.attacking = true;
            }
            update();
        }
        else if(event instanceof EventEffecting){
            this.eventManager.post(new EventCardEffected());
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

    public static void drawCenteredString(Graphics g, String text, int centerX, int centerY, Font font){
        FontMetrics metrics = g.getFontMetrics(font);
        int x = centerX - metrics.stringWidth(text) / 2;
        int y = centerY;
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public static void drawCardImage(Graphics g, Card card, int x, int y, int w, int h){
        Path path = Paths.get(Const.CARD_IMG_DIR + card.getName() + ".png");
        // draw minion image
        if (Files.exists(path)){
            g.drawImage(View.loadImage(Const.CARD_IMG_DIR + card.getName() + ".png"), x, y, w, h, null);
        }
        else{
            g.drawImage(View.loadImage(Const.CARD_IMG_DIR + "default.png"), x, y, w, h, null);
        }
    }

    public static void drawMinion(Graphics g, Minion minion, int x, int y){
        View.drawCardImage(g, (Card) minion, x + (int)(Const.MINION_IMG_X_RATIO*Const.MINION_W),
                            y + (int)(Const.MINION_IMG_Y_RATIO*Const.MINION_H), Const.MINION_IMG_W, Const.MINION_IMG_H);
        // draw minion frame
        g.drawImage(View.loadImage(Const.MINION_FRAME_PATH), x, y, Const.MINION_W, Const.MINION_H, null);
        // draw name for debugging
        View.drawCenteredString(g,((Card) minion).getName(), x + Const.MINION_W/2, y + Const.MINION_H/2, new Font("Consolas", Font.PLAIN, 20));
        
        // draw attack
        View.drawCenteredString(g, Integer.toString(minion.getATK()), x + (int)(Const.MINION_ATTACK_X_RATIO*Const.MINION_W),
                                y + (int)(Const.MINION_ATTACK_Y_RATIO*Const.MINION_H), new Font("Consolas", Font.BOLD, Const.MINION_SHOW_STATUS_FONT_SIZE));
        // draw health
        View.drawCenteredString(g, Integer.toString(minion.getHP()), x + (int)(Const.MINION_HEALTH_X_RATIO*Const.MINION_W),
                                y + (int)(Const.MINION_HEALTH_Y_RATIO*Const.MINION_H), new Font("Consolas", Font.BOLD, Const.MINION_SHOW_STATUS_FONT_SIZE));
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
