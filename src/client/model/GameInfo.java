package src.client.model;
import java.util.List;
import java.util.ArrayList;

import src.model.Minion;
import src.model.Card;
import src.model.minion.*;
//import src.model.spell.*;
import src.event.*;
import src.client.viewconstant.Const;
public class GameInfo implements EventListener{
    
    private int state; 
    private EventManager eventManager;
    private int playerId;
    private int winnerId;
    private int[] mana ;
    private int[] fullMana;
    private boolean myTurn;
    private List<Minion> ally = new ArrayList<Minion>();
    private List<Minion> enemy = new ArrayList<Minion>();   
    private List<Card> handCards = new ArrayList<Card>();
    private int[] handSize;
    private int[] deckSize;
    
    private int turn;
    private float timer;
    
    private int showedCardIndex;
    private boolean showedCardValid;
    private int showedMinionIndex;
    private int showedMinionPlayerId;
    private boolean showedMinionValid;

    private int attackerIndex;
    private int attackerPlayerIndex;
    private int attackedIndex;
    private int attackedPlayerIndex;

    
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
        else if(event instanceof EventTurnStart){
            this.turnStart();
        }
        else if(event instanceof EventCardDraw){
            EventCardDraw e = (EventCardDraw) event;
            this.cardDraw(e.getPlayerId(), e.getFatigue(), e.getFull());
        }        
        else if (event instanceof EventManaChange){ 
            EventManaChange e = (EventManaChange) event; 
            this.manaChange(e.getPlayerId(), e.getMana(), e.getFullMana());
        }
        else if (event instanceof EventHandCardChange){ 
            EventHandCardChange e = (EventHandCardChange) event;  
            this.handCardChange(e.getPlayerId(), e.getIndex(), e.getCard());
        }
        else if (event instanceof EventBoardChange){ 
            EventBoardChange e = (EventBoardChange) event; 
            this.boardChange(e.getPlayerId(), e.getIndex(), e.getMinion());
        }
        else if (event instanceof EventMinionChange){            
            EventMinionChange e = (EventMinionChange) event;
            this.minionChange(e.getPlayerId(), e.getIndex(), e.getMinion());
        }    
        else if (event instanceof EventAttacking){            
            EventAttacking e = (EventAttacking) event;
            this.attackerIndex = e.getAttackerIndex();
            this.attackerPlayerIndex = e.getAttackerPlayerIndex();
            this.attackedIndex = e.getAttackedIndex();
            this.attackedPlayerIndex = e.getAttackedPlayerIndex();
        }   
        else if (event instanceof EventCardShow){            
            EventCardShow e = (EventCardShow) event;
            this.showedCardIndex = e.getShowIndex();
            this.showedCardValid = e.getValid();
        }
        else if (event instanceof EventMinionShow){            
            EventMinionShow e = (EventMinionShow) event;
            this.showedMinionPlayerId = e.getShowPlayerIndex();
            this.showedMinionIndex = e.getShowIndex();
            this.showedMinionValid = e.getValid();
        }
        else if (event instanceof EventMinionChange){            
            EventMinionChange e = (EventMinionChange) event;
            this.minionChange(e.getPlayerId(), e.getIndex(), e.getMinion());
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
        if(this.myTurn)
            if(this.playerId == 0) 
                this.turn++;        
    }
    
    private void cardDraw(int id, boolean fatigue, boolean full){
        if(fatigue) return;
        if(full)
            this.deckSize[id]--;        
        else
            this.deckSize[id]--;                
    }

    public boolean isMyTurn(){
        return this.myTurn;
    }

    public int getState(){
        return this.state;
    }

    public int getPlayerId(){
        return this.playerId;
    }

    public int getOpponentId(){
        return 1 - this.playerId;
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
        if(this.ally.size() == 0)
            return null;
        return this.ally.get(0);
    }

    public Minion getOpponentHero(){
        if(this.enemy.size() == 0)
            return null;
        return this.enemy.get(0);
    }

    public int getShowedCardIndex(){
        return this.showedCardIndex;
    }

    public boolean getShowedCardValid(){
        return this.showedCardValid;
    }

    public int getShowedMinionIndex(){
        return this.showedMinionIndex;
    }

    public int getShowedMinionPlayerId(){
        return this.showedMinionPlayerId;
    }

    public boolean getShowedMinionValid(){
        return this.showedMinionValid;
    }

    public int getAttackerIndex(){
        return this.attackerIndex;
    }

    public int getAttackerPlayerIndex(){
        return this.attackerPlayerIndex;
    }

    public int getAttackedIndex(){
        return this.attackedIndex;
    }

    public int getAttackedPlayerIndex(){
        return this.attackedPlayerIndex;
    }

    public int[] getMinionPosition(int playerId, int index){
        int x, y;

        if(index == 0){
            if(playerId == this.playerId){
                x = Const.HERO[0] + (Const.HERO[2] - Const.MINION_W)/2;
                y = Const.HERO[1] + (Const.HERO[3] - Const.MINION_H)/2;
            }
            else{
                x = Const.OP_HERO[0] + (Const.OP_HERO[2] - Const.MINION_W)/2;
                y = Const.OP_HERO[1] + (Const.OP_HERO[3] - Const.MINION_H)/2;
            }
        }
        else if(playerId == this.playerId){
            x = Const.BOARD_REGION[0] + (Const.BOARD_REGION[2] - (ally.size()-2)*Const.MINION_GAP - (ally.size()-1)*Const.MINION_W)/2 + (index-1)*(Const.MINION_GAP + Const.MINION_W);
            y = Const.BOARD_REGION[1];
        }
        else{
            x = Const.OP_BOARD_REGION[0] + (Const.OP_BOARD_REGION[2] - (enemy.size()-2)*Const.MINION_GAP - (enemy.size()-1)*Const.MINION_W)/2 + (enemy.size()-index-1)*(Const.MINION_GAP + Const.MINION_W);
            y = Const.OP_BOARD_REGION[1];
        }
        return new int[] {x,y};
    }

    public int[] getAttackerPosition(){
        return getMinionPosition(this.attackerPlayerIndex, this.attackerIndex);
    }

    public int[] getAttackedPosition(){
        return getMinionPosition(this.attackedPlayerIndex, this.attackedIndex);
    }

    public Minion getAttacker(){
        if(this.attackerPlayerIndex == this.playerId)
            return this.ally.get(this.attackerIndex);
        else
            return this.enemy.get(this.attackerIndex);
    }

    public void manaChange(int playerId, int mana, int fullMana){
        this.mana[playerId] = mana;
        this.fullMana[playerId] = fullMana;
    }

    public void handCardChange(int playerId, int index, Card card){
        if(playerId == this.playerId){            
            if(card == null)
                this.handCards.remove(index);                
            else
                this.handCards.add(card);
            this.handSize[playerId] = this.handCards.size();
        }
        else{
            if(card == null)
                this.handSize[playerId]--;                
            else
                this.handSize[playerId]++;
        }        
    }
    
    public void minionChange(int playerId, int index, Minion minion){
        List<Minion> minions; 
        minions = (playerId == this.playerId)? this.ally : this.enemy;
        if(minions.get(index).getAlive() != minion.getAlive())
            this.eventManager.post(new EventMinionDestroy(playerId, index));
        else{
            int hpDiff = minion.getHP() - minions.get(index).getHP();
            if(hpDiff != 0)
                this.eventManager.post(new EventMinionChangeHP(playerId, index, hpDiff));
        }       
        minions.set(index, minion);
    }
    
    public void boardChange(int playerId, int index, Minion minion){
        List<Minion> minions; 
        minions = (playerId == this.playerId)? this.ally : this.enemy;
        if(minion == null)
            minions.remove(index);
        else
            minions.add(index, minion);        
    }

}