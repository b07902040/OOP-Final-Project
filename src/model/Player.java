package src.model;
import java.util.List;

import javax.swing.AbstractAction;

import src.model.minion.*;
//import src.model.spell.*;
import src.constant.Const;
import src.event.EventCardDraw;

import java.lang.annotation.Target;
import java.util.ArrayList;

public class Player{
    private String name;
    private int mana = 0;
    private int fullMana = 0;
    private Player opponent;
    private List<Card> deck;
    private List<Card> handCard;
    private int fatigueDamge = 0;
    private List<Minion> ally;
    private List<Minion> enemy; 
    private int cardPlayed = 0;
    private Game game;
    private Minion hero;
    public Player(String name, Game game){         
        this.name = name;
        this.game = game;
        this.handCard = new ArrayList<Card>();
        this.ally = new ArrayList<Minion>();
        this.hero = new Hero();
        this.hero.setMaster(this);
        this.ally.add(this.hero);
    }

    public String getName(){
        return this.name;
    } 

    public Minion  getHero(){
        return this.hero;
    }

    public int getMana(){
        return this.mana;
    }

    public void setMana(int mana){
        this.mana = Math.min(mana, this.fullMana);
    }

    public void fillMana(){
        this.mana = this.fullMana;
    }

    public int getFullMana(){
        return this.fullMana;
    }

    public void setFullMana(int fullMana){
        this.fullMana = fullMana;
    }

    public int getCardPlayed(){
        return this.cardPlayed;
    }

    public void setCardPlayed(int cardPlayed){
        this.cardPlayed =cardPlayed;
    }

    public Player getOpponent(){
        return this.opponent;
    }

    public void setOpponent(Player oppenent){
        this.opponent = oppenent;
        this.enemy = oppenent.ally;
    }

    public List<Card> getDeck(){
        return this.deck;
    }

    public void setDeck(ArrayList<Card> deck){
        this.deck = deck;
    }

    public List<Card> getHandCard(){
        return this.handCard;
    }

    public void setHandCard(List<Card> handCard){
        this.handCard = handCard;
    }

    public void addHandCard(Card card){
        this.handCard.add(card);
    }

    public List<Minion> getAlly(){
        return this.ally;
    }

    public void addAlly(Minion minion){
        minion.setMaster(this);
        minion.setPlayedOrder(this.game.getMinionSummoned());     
        this.ally.add(minion);     
        System.out.printf("%s add %s to ally.\n", this.name, ((AbstractMinion)minion).getName());  
        this.game.addMinionSummoned();
    }

    public void removeAlly(Minion minion){
        this.ally.remove(minion);
    }

    public List<Minion> getEnemy(){
        return this.enemy;
    }


    public void drawCards(int cardNum) {
        for(int i = 0; i < cardNum; i++){
            if(this.deck.size() > 0){        
                Card newCard = this.deck.get(0);
                this.deck.remove(0);
                if(this.handCard.size() < Const.MAX_HAND_SIZE){
                    this.addHandCard(newCard);
                    //TODO
                    this.game.cardDrew(false,false);
                }
                else
                    this.game.cardDrew(false,true);
            }
            else{
                this.game.cardDrew(true,false);
                //TODO:
                //take fatigue damge
                this.fatigueDamge += 1;                
            }
        }
    }

    public void throwCard(int index){
        System.out.printf("%s throw %d.\n", this.name,index);          
        this.handCard.remove(index);
        this.printPlayerStatus();
    }

    public boolean checkValidCard(int index){
        Card card = this.handCard.get(index);
        if(card.getCost() > this.mana)
            return false;
        if(card instanceof Minion){
            if(this.ally.size() >= Const.BOARD_SPACE + 1)//ADD FOR HERO 
                return false;      
        }
        if(card instanceof Spell && card instanceof Targeting){
            if(((Targeting)card).getCandidates(this) == null)
                return false;
        }
        return true;
    }

    public void printPlayerStatus(){
        System.out.printf("----------------------------------------\n");
        System.out.printf("%s's turn , Mana: %d , DeckNum: %d , Cardplayed: %d\n",
            this.name, this.mana, this.deck.size(),this.cardPlayed);
        this.printHandCard();
        this.printAlly();
        this.printEnemy();
        System.out.printf("----------------------------------------\n");
    }
    
    public void printHandCard(){
        System.out.printf("Handcards(%d): ",this.handCard.size());
        for(Card card : this.handCard){
            //System.out.printf("%s ",((AbstractMinion)card).getName());
            //System.out.println(card);
            System.out.printf("%s ",((AbstractMinion) card).getName());
        }
        System.out.printf("\n");
    }

    public void printAlly(){
        System.out.printf("Ally(%d): ",this.ally.size());
        for(Minion minion : this.ally){
            AbstractMinion m = ((AbstractMinion) minion);
            System.out.printf("%s (ATK:%d HP:%d) ", m.getName(), m.getATK(), m.getHP());
        }
        System.out.printf("\n");
    }

    public void printEnemy(){
        System.out.printf("Enemy(%d): ",this.enemy.size());
        for(Minion minion : this.enemy){
            AbstractMinion m = ((AbstractMinion) minion);
            System.out.printf("%s (ATK:%d HP:%d) ", m.getName(), m.getATK(), m.getHP());
        }
        System.out.printf("\n");
    }
}