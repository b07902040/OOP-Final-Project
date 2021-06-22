package src.model;
import java.util.List;

import src.constant.Const;
import src.event.EventCardDraw;

import java.lang.annotation.Target;
import java.util.ArrayList;

public class Player(){
    private String name;
    private int mana = 0;
    private int fullMana = 0;
    private Player opponent;
    private List<Card> deck;
    private List<Card> handCard;
    private int fatigueDamge = 0;
    private List<Minion> ally;
    private List<Minion> enemy; 
    private Game game;
    public Player(String name, Game game){
        this.name = name;
        this.game = game;
        this.handCard = new ArrayList<Card>();
        this.ally = new ArrayList<Minion>();
    }

    public String getName(){
        return this.name;
    } 

    public int getMana(){
        return this.mana;
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

    public List<Card> setDeck(List<Card> deck){
        this.deck = deck;
    }

    public List<Card> getHandCard(){
        return this.handCard;
    }

    public List<Card> setHandCard(List<Card> handCard){
        this.handCard = handCard;
    }

    public List<Card> addHandCard(Card card){
        this.handCard.add(card);
    }

    public List<Minion> getAlly(){
        return this.ally;
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
                    this.handCard.add(newCard);
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
    public void throwCard(Card throwedCard){
        for(int i = 0; i < this.handCard.size(); i++){
            Card c = this.handCard.get(i);
            if(c.equals(throwedCard)){
                this.handCard.remove(i);
                break;
            }
        }
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
}