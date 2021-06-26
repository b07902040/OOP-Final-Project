package src.client.model;
import java.util.List;
import java.util.ArrayList;

import src.model.Minion;
import src.model.Card;
import src.model.minion.*;
//import src.model.spell.*;
import src.event.*;
import src.constant.*;
public class GameInfo implements EventListener{
    
    private int state; 
    private EventManager eventManager;
    private int playerId;
    private int[] mana ;
    private int[] fullMana;
    private boolean myTurn;
    private Minion hero;
    private Minion opponentHero;
    private List<Minion> ally = new ArrayList<Minion>();
    private List<Minion> enemy = new ArrayList<Minion>();   
    private List<Card> handCards = new ArrayList<Card>();
    private int[] handSize;
    private int[] deckSize;
    
    private int turn;
    private float timer;
    
    private int clickedCardIndex = -1;
    private int clickedMinionIndex = -1;
    private boolean clickedMinionAlly = false;
    private int clickedAttackerIndex = -1;
    private boolean clickedAttackerAlly = false;
    private int clickedAttackedIndex = -1;
    private boolean clickedAttackedAlly = false;   
    
    public GameInfo(EventManager eventManager){
        this.eventManager = eventManager;
        this.eventManager.register(this);
    }

    @Override
    public void notify(Event event) {
        if(event instanceof EventClientInitalize){            
            this.initialize(((EventClientInitalize) event).getClientId());
        }
        else if(event instanceof EventStateChange){
            this.state = ((EventStateChange) event).getState();
        }
        else if(event instanceof EventCardDraw){
            EventCardDraw e = (EventCardDraw) event;
            this.cardDraw(e.getPlayerId(), e.getFatigue(), e.getFull());
        }
        else if(event instanceof EventTurnStart){
            this.turnStart();
        }
        else if (event instanceof EventMinionChange){            
            EventMinionChange e = (EventMinionChange) event;
            //this.checkChange(e.getAlly(), e.getIndex(), e.getMinion());
        }     
    }
    private void initialize(int id){
        this.myTurn = (id == 0)? false : true;
        this.playerId = id;
        this.mana = new int[] {0, 0};
        this.fullMana = new int[] {0, 0};
        this.turn = 0;
        this.deckSize = new int[] {Const.DECK_SIZE, Const.DECK_SIZE};
        this.handSize = new int[] {0, 0};
        
    }

    private void turnStart(){
        this.myTurn = !this.myTurn;
        if(this.myTurn){
            this.fullMana[this.playerId] = Math.min(this.fullMana[this.playerId] + 1, Const.MAX_MANA);
            this.mana[this.playerId] = this.fullMana[this.playerId];
            if(this.playerId == 0) 
                this.turn++;
        }
    }
    
    private void cardDraw(int id, boolean fatigue, boolean full){
        if(fatigue) return;
        if(full)
            this.deckSize[id]--;        
        else{
            this.deckSize[id]--;
        }         
    }

    public int getState(){
        return this.state;
    }

    public int getPlayerId(){
        return this.playerId;
    }

    public int getFullMana(int id){
        return this.fullMana[id];
    }

    public int getMana(int id){
        return this.mana[id];
    }

    public List<Card> getHandCards(){
        return this.handCards;
    }

    public int getHandSize(int id){
        return this.handSize[id];
    }

    public int getDeckSize(int id){
        return this.deckSize[id];
    }
    
    public List<Minion> getAlly(){
        return this.ally;
    }

    public List<Minion> getEnemy(){
        return this.enemy;
    }

    public Minion getHero(){
        return this.hero;
    }

    public Minion getOpponentHero(){
        return this.opponentHero;
    }
    /*
    public void checkChange(boolean ally, int index, Minion minion){
        int playerId;
        if(this.myTurn) 
            playerId = (ally)? this.playerId : ((this.playerId + 1) % 2);
        else
            playerId = (ally)? ((this.playerId + 1) % 2) : this.playerId ; 
        List<Minion> minions; 
        if(this.myTurn)
            minions = (ally)? this.ally : this.enemy;
        else
            minions = (ally)? this.enemy : this.ally;  

        if(minions.get(index).getAlive() != minion.getAlive())
            this.eventManager.post(new EventMinionDestroy(playerId, index));
        else{
            int hpDiff = minion.getHP() - minions.get(index).getHP();
            if(hpDiff != 0)
                this.eventManager.post(new EventMinionChangeHP(playerId, index, hpDiff));
        }       
        this.minions.get(index) = minon;
    }
    */

}