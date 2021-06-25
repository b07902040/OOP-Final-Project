package src.client.model;
import java.util.List;
import java.util.ArrayList;

import src.model.Minion;
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

    public GameInfo(EventManager eventManager){

        this.eventManager = eventManager;
        this.eventManager.register(this);
    }

    @Override
    public void notify(Event event) {
        if(event instanceof EventClientInitalize){            
            this.initialize(((EventClientInitalize) event).getClientId);
        }
        else if(event instanceof EventStateChange){
            this.state = ((EventStateChange) event).getState();
        }
        else if(event instanceof EventCardDraw){
            EventCardDraw e = (EventCardDraw) event
            this.cardDraw(e.getPlayerId(), e.getFatigue(), e.getFull(), e.getCard());
        }
        else if(event instanceof EventTurnStart){
            this.turnStart();
        }
        else if (event instanceof EventMinionChange){            
            EventMinionChange e = (EventMinionChange) event;
            this.checkChange(e.getAlly(), e.getIndex(), e.getMinion());
        }     
    }
    private void initialize(int id){
        this.myTurn = (id == 0)? false : true;
        this.playerId = id;
        this.mana = {0, 0};
        this.fullMana = {0, 0};
        this.turn = 0;
        this.deckSize = {Const.DECK_SIZE, Const.DECK_SIZE};
        this.handSize = {0, 0};
        
    }

    private void turnStart(){
        this.myturn = !this.myTurn;
        if(this.myturn){
            this.fullMana[this.playerId] = Math.min(this.fullMana[this.playerId] + 1, Const.MAX_MANA);
            this.mana[this.playerId] = this.fullMana[this.playerId];
            if(this.playerId == 0) 
                this.turn++;
        }
        
    }
    
    private void cardDraw(int id, boolean fatigue, boolean full,Card card){
        if(fatigue) return;
        if(full)
            this.deckSize[id]--;        
        else{
            this.deckSize[id]--;
            this.handSize[id]++;
            if(id == this.playerId)
                this.handCards.add(card);
        }
            
    }    

    public void checkChange(boolean ally, int index, Minion minion){
        Minion oldMinion;
        if(this.myTurn)
            oldMinion = (ally)? this.ally.get(index) : this.enemy.get(index);
        else
            oldMinion = (ally)? this.enemy.get(index) : this.ally.get(index);
        int playerId;
        if(this.myturn) 
            playerId = (ally)? this.playerId : ((this.playerId + 1) % 2);
        else
            playerId = (ally)? ((this.playerId + 1) % 2) : this.playerId ;    
        if(!minion.getAlive())
            this.eventManager.post(new EventMinionDestroy(playerId, index));
        else{
            int hpDiff = minion.getHP() - oldMinion.getHP();
            this.eventManager.post(new EventMinionChangeHP(playerId, index, hpDiff));
        }        
    }


}