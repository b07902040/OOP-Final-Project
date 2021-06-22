package src.model;
import java.util.List;
import java.util.ArrayList;
import src.event.*;
import src.constant.*;

public class Game implements EventListener{    
    private EventManager eventManager;
    private StateMachine state = new StateMachine();
    private boolean running = false;

    private List<Player> players;
    private Player currentPlayer;
    private int turn = 0;
    private boolean firstPlayerTurn = true;
    private float time = 0;    
    //play card
    private int clickedCardIndex = -1;
    private int clickedMinionIndex = -1;
    private boolean clickedMinionAlly = false;
    private Card selectedCard;
    private Minion selectedMinion;
    //minion attack
    private int clickedAttackerIndex = -1;
    private boolean clickedAttackerAlly = false;
    private int clickedAttackedIndex = -1;
    private boolean clickedAttackedAlly = false;
    private Minion selectedAttacker;
    private Minion selectedAttacked;

   

    public Game(EventManager eventManager){
        this.eventManager = eventManager;
        this.eventManager.register(this);
        
    }

    public void run(){
        this.eventManager.post(new EventInitialize());
        this.running = true;
        while(this.running){
            this.eventManager.post(new EventEveryTick());
            //TODO:
            //sleep
        }
    }

    @Override 
    public void notify(Event event){
        if(event instanceof EventInitialize){
           this.initialize();
           this.eventManager.post(new EventGameStart());
        }
        else if(event instanceof EventGameStart){
            this.gameStart();
            this.eventManager.post(new EventTurnStart());       
        }
        else if(event instanceof EventTurnStart){
            this.turnStart();
            this.state.push(Const.STATE_PENDING);
            this.currentPlayer.drawCards(1);                  
        }
        else if(event instanceof EventClickedEmpty){
            if(this.isState(Const.STATE_VALID_CARD) || this.isState(Const.STATE_INVALID_CARD))
                this.state.push(Const.STATE_PENDING);        
            else if(this.isState(Const.STATE_VALID_TARGET) || this.isState(Const.STATE_INVALID_TARGET))
                this.state.push(Const.STATE_CARD_TARGETING);       
            else if(this.isState(Const.STATE_VALID_ATTACKER) || this.isState(Const.STATE_INVALID_ATTACKER))
                this.state.push(Const.STATE_PENDING);  
            else if(this.isState(Const.STATE_VALID_ATTACKED) || this.isState(Const.STATE_INVALID_ATTACKED))
                this.state.push(Const.STATE_ATTACKER_TARGETING);   
        }
        else if(event instanceof EventCardClicked){
            if(this.isState(Const.STATE_PENDING)){
                this.clickedCardIndex = ((EventCardClicked) event).getClickedIndex();
                if(this.currentPlayer.checkValidCard(this.clickedCardIndex))
                    this.state.push(Const.STATE_VALID_CARD);                           
                else
                    this.state.push(Const.STATE_INVALID_CARD);    
            }        
        }        
        else if(event instanceof EventCardSelected){
            if(this.isState(Const.STATE_VALID_CARD)){
                this.selectedCard = this.currentPlayer.getHandCard().get(this.clickedCardIndex);
                if(this.selectedCard instanceof Targeting) 
                    this.state.push(Const.STATE_CARD_TARGETING);
                else{
                    this.selectedCard.playedEffect(this.currentPlayer,null);
                    this.state.push(Const.STATE_EFFECTING);                    
                }  
            }
        }   
        else if(event instanceof EventMinionClicked){
            //play card choose target
            if(this.isState(Const.STATE_CARD_TARGETING)){
                this.clickedMinionIndex = ((EventMinionClicked) event).getClickedIndex();
                this.clickedMinionAlly = ((EventMinionClicked) event).getIsAlly();
                if(this.checkValidMinion())
                    this.state.push(Const.STATE_VALID_TARGET);                           
                else
                    this.state.push(Const.STATE_INVALID_TARGET); 
            }
            //choose attacker
            else if(this.isState(Const.STATE_PENDING)){
                this.clickedAttackerIndex = ((EventMinionClicked) event).getClickedIndex();
                this.clickedAttackerAlly = ((EventMinionClicked) event).getIsAlly();
                boolean canAttack = false;
                if(this.clickedAttackerAlly){
                    Minion attacker = this.currentPlayer.getAlly().get(this.clickedAttackerIndex);
                    canAttack = attacker.canAttack();                
                }
                if(canAttack) this.state.push(Const.STATE_VALID_ATTACKER); 
                else this.state.push(Const.STATE_INVALID_ATTACKER); 
            }
            //choose attacked
            else if(this.isState(Const.STATE_ATTACKER_TARGETING)){
                this.clickedAttackedIndex = ((EventMinionClicked) event).getClickedIndex();
                this.clickedAttackedAlly = ((EventMinionClicked) event).getIsAlly();
                boolean canAttacked = false;
                if(!this.clickedAttackerAlly){
                    Minion attacked = this.currentPlayer.getEnemy().get(this.clickedAttackerIndex);
                    canAttacked = attacked.canAttacked();                    
                }
                if(canAttacked) this.state.push(Const.STATE_VALID_ATTACKED); 
                else this.state.push(Const.STATE_INVALID_ATTACKED); 
            }
        }      
        else if(event instanceof EventMinionSelected){
            if(this.isState(Const.STATE_VALID_TARGET)){
                this.selectedMinion = (this.clickedMinionAlly)? 
                    this.currentPlayer.getAlly().get(this.clickedMinionIndex) : 
                    this.currentPlayer.getEnemy().get(this.clickedMinionIndex);
                this.selectedCard.playedEffect(this.currentPlayer,this.selectedMinion);
                this.currentPlayer.throwCard(this.selectedCard);
                this.state.push(Const.STATE_EFFECTING);
            }
            else if(this.isState(Const.STATE_VALID_ATTACKER)){
                this.selectedAttacker = this.currentPlayer.getAlly().get(this.clickedAttackerIndex);                
                this.state.push(Const.STATE_ATTACKER_TARGETING);
            }
            else if(this.isState(Const.STATE_VALID_ATTACKED)){
                this.selectedAttacked = this.currentPlayer.getEnemy().get(this.clickedAttackedIndex);     
                this.selectedAttacker.attackTarget(this.selectedAttacked);           
                this.state.push(Const.STATE_ATTACKING);
            }
        } 
        else if(event instanceof EventCardEffected){
            if(this.isState(Const.STATE_EFFECTING))
                this.state.push(Const.STATE_PENDING);                
        }  
        else if(event instanceof EventMinionAttacked){
            if(this.isState(Const.STATE_ATTACKING))
                this.state.push(Const.STATE_PENDING);               
        }               
        else if(event instanceof EventTurnEnd){
            if(this.isState(Const.STATE_PENDING))
                this.turnEnd();      
        }
    }
    
    
    public boolean isState(int state){
        return (this.state.peek() == state);
    }

    private void initialize(){
        this.players.add(new Player("player0", this));
        this.players.add(new Player("player1", this));
        for(int i = 0; i < 2; i++)
            this.players.get(i).setOpponent(this.players.get((i + 1) % 2));        
        this.currentPlayer = this.players.get(0);
        //TODO:
        //add hero to minions
        //deal  
    } 
    
    private void gameStart(){
        for(int i = 0; i < 2; i++){
            this.players.get(i).drawCards(Const.STARTING_HAND_SIZE + i);
        }
    }

    private void turnStart(){
        if(firstPlayerTurn)
            this.turn += 1; 
        int mana = this.currentPlayer.getFullMana(); 
        this.currentPlayer.setFullMana(Math.max(mana + 1, Const.MAX_MANA));      
        this.currentPlayer.fillMana();
    }

    public void cardDrew(boolean fatigue, boolean full){
        this.eventManager.post(new EventCardDraw(fatigue, full));
    }

    private boolean checkValidMinion(){
        Minion target = (this.clickedMinionAlly)? 
            this.currentPlayer.getAlly().get(this.clickedMinionIndex) : 
            this.currentPlayer.getEnemy().get(this.clickedMinionIndex);
        if(this.selectedCard instanceof Targeting){
            List<Minion> candidates = ((Targeting) this.selectedCard).getCandidates(this.currentPlayer);
            return candidates.contains(target);
        }
        else return false;
    }

    private void turnEnd(){
        this.currentPlayer = this.currentPlayer.getOpponent();
        this.firstPlayerTurn = !this.firstPlayerTurn;
    }
   
}