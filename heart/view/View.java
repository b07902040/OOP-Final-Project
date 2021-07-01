package heart.view;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import heart.constant.Const;
import heart.controller.Controller;
import heart.event.*;
import heart.model.*;


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
    private Animation effectAnimation;
    private Animation cardDrawAnimation;
    private boolean attacking;
    private boolean effecting;
    private boolean drawingCard;
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
        this.effecting = false;
        this.drawingCard = false;

        painters.add(new BoardPainter());
        painters.add(new ManaPainter());
        painters.add(new HandCardPainter());
        painters.add(new MinionPainter());
        painters.add(new HeroPainter());
        painters.add(new ShowCardPainter());
        painters.add(new MessagePainter());
        painters.add(new DeckPainter());
        

        this.board.addMouseListener(this.controller);
        this.screen.add(this.board);
        this.screen.pack();
        update();
        Thread t = new Thread(new AudioPlayer());
		t.start();
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
                        this.model.pauseState();
                        this.eventManager.post(new EventMinionAttacked());
                    }
                    else if(animations.get(i) == this.effectAnimation){
                        System.out.println("effect ends");
                        this.effecting = false;
                        this.model.pauseState();
                        this.eventManager.post(new EventCardEffected());
                    }
                    else if(animations.get(i) == this.cardDrawAnimation){
                        this.drawingCard = false;
                        this.model.pauseState();
                        System.out.printf("END DRAWING!\n");
                        this.eventManager.post(new EventCardDrawed());
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
            if(this.model.getState() == Const.STATE_EFFECTING && !this.effecting){
                this.effectAnimation = new OpponentSpellAnimation();
                this.painters.add((Painter) this.effectAnimation);
                this.animations.add(this.effectAnimation);
                this.effecting = true;
            }
            if(this.model.getState() == Const.STATE_DRAWING && !this.drawingCard){
                System.out.printf("START DRAWING!\n");
                this.cardDrawAnimation = new DrawCardAnimation();
                this.painters.add((Painter) this.cardDrawAnimation);
                this.animations.add(this.cardDrawAnimation);
                this.drawingCard = true;
            }
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

    public static void drawRectString(Graphics g, String text, int x, int y, int w, Font font){
        FontMetrics metrics = g.getFontMetrics(font);
        g.setFont(font);
        String[] splited = text.split(" ");
        String line = "";
        for(String word : splited){
            if(metrics.stringWidth(line + " " + word) > w){
                y += metrics.getHeight();
                drawCenteredString(g, line, x + w/2, y, font);
                line = word;
            }
            else
                line += " " + word;
        }
        y += metrics.getHeight();
        drawCenteredString(g, line, x + w/2, y, font);
    }

    public static void drawMinion(Graphics g, Minion minion, int x, int y, double scale){
        x -= (int)((scale - 1)*Const.MINION_W/2);
        y -= (int)((scale - 1)*Const.MINION_H/2);
        int w = (int)(Const.MINION_W*scale);
        int h = (int)(Const.MINION_H*scale);
        // draw taunt
        if(minion instanceof Taunt)
                g.drawImage(View.loadImage(Const.TAUNT_IMG_PATH), x + (int)(Const.CARD_TAUNT_X_RATIO*w), y + (int)(Const.CARD_TAUNT_Y_RATIO*h),
                            (int)(Const.CARD_TAUNT_W_RATIO*w), (int)(Const.CARD_TAUNT_H_RATIO*h), null);
        // draw minion image
        View.drawCardImage(g, (Card) minion, x + (int)(Const.MINION_IMG_X_RATIO*w),
                            y + (int)(Const.MINION_IMG_Y_RATIO*h), (int)(Const.MINION_IMG_W_RATIO*w), (int)(Const.MINION_IMG_H_RATIO*h));
        // draw minion frame
        g.drawImage(View.loadImage(Const.MINION_FRAME_PATH), x, y, w, h, null);
        // draw name for debugging
        //View.drawCenteredString(g,((Card) minion).getName(), x + w/2, y + h/2, new Font("Consolas", Font.PLAIN, 20));
        // draw attack
        View.drawCenteredString(g, Integer.toString(minion.getATK()), x + (int)(Const.MINION_ATTACK_X_RATIO*w),
                                y + (int)(Const.MINION_ATTACK_Y_RATIO*h), new Font("Consolas", Font.BOLD, Const.MINION_SHOW_STATUS_FONT_SIZE));
        // draw health
        View.drawCenteredString(g, Integer.toString(minion.getHP()), x + (int)(Const.MINION_HEALTH_X_RATIO*w),
                                y + (int)(Const.MINION_HEALTH_Y_RATIO*h), new Font("Consolas", Font.BOLD, Const.MINION_SHOW_STATUS_FONT_SIZE));
        // draw death rattle icon
        if(minion instanceof DeathRattle)
            g.drawImage(View.loadImage(Const.DEATHRATTLE_IMG_PATH), x + (int)(Const.CARD_DEATHRATTLE_X_RATIO*w),
                        y + (int)(Const.CARD_DEATHRATTLE_Y_RATIO*h), (int)(Const.CARD_DEATHRATTLE_W_RATIO*w), (int)(Const.CARD_DEATHRATTLE_H_RATIO*h), null);
        // draw divine shied
        if(minion instanceof DivineShield && ((DivineShield) minion).hasDivineShield())
            g.drawImage(View.loadImage(Const.DIVINE_SHIELD_IMG_PATH), x, y, w, h, null);
    }

    public static void drawValidMinionEffect(Graphics g, int x, int y, int w, int h){
        g.drawImage(View.loadImage(Const.VALID_MINION_EFFECT_PATH),
                                x + (int) (Const.VALID_MINION_EFFECT_X_RATIO * w),
                                y + (int) (Const.VALID_MINION_EFFECT_Y_RATIO * h),
                                (int) (Const.VALID_MINION_EFFECT_W_RATIO * w),
                                (int) (Const.VALID_MINION_EFFECT_H_RATIO * h), null);
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
