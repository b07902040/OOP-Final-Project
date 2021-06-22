package src.model;
import java.util.List;
import java.util.ArrayList;
import src.event.*;
import src.constant.*;

public class Game implements EventListener{    
    private EventManager eventManager;
    private List<Player> players;
    private Player currentPlayer;
    private int turn = 0;
    private boolean firstPlayerTurn = true;
    private float time = 0;
    private StateMachine state = new StateMachine();
    private int markedIndex = -1;
    private Card selectedCard;
    private boolean running = false;

    public Game(EventManager eventManager){
        this.eventManager = eventManager;
        this.eventManager.register(this);
        
    }

    public void run(){
        this.post(EventInitialize());
        this.running = true;
        while(this.running){
            self.eventManager.post(EventEveryTick());
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
            state.push(Const.STATE_PENDING);
            this.gameStart();
            this.eventManager.post(new EventTurnStart());       
        }
        else if(event instanceof EventTurnStart){
            this.turnStart();
            this.eventManager.post(new EventCardDraw());       
        }
        else if(event instanceof EventCardDraw){
            this.cardDraw();            
        }
        else if(event instanceof EventCardClicked){
            int clickedIndex = ((EventCardClicked) event).getClickedIndex();
            if(this.cardClicked(clickedIndex)){ 
                this.markedIndex = clickedIndex;
                this.eventManager.post(new EventCardMarked(clickedIndex)); 
            }  
        }
        else if(event instanceof EventCardSelected){
            if(this.cardSelected()) this.eventManager.post(new EventChooseTarget()); 
            else //playcard
        }        
        else if(event instanceof EventTurnEnd){
            this.turnEnd();
        }
    }
    
    private void initialize(){
        players.add(new Player("player0"));
        players.add(new Player("player1"));
        for(int i = 0; i < 2; i++)
            players.get(i).setOpponent(players.get((i + 1) % 2);
        this.currentPlayer = players.get(0);
        //TODO:
        //add hero to minions
        //deal  
    } 
    
    private void gameStart(){
        for(int i = 0; i < 2; i++){
            players.get(i).drawCards(Const.STARTING_HAND_SIZE + i);
        }
    }

    private void turnStart(){
        if(firstPlayerTurn)
            this.turn += 1; 
        int mana = currentPlayer.getfullMana(); 
        currentPlayer.setFullMana(Math.max(mana + 1, Const.MAX_MANA));      
        currentPlayer.fillMana();
    }

    private void cardDraw(){
        currentPlayer.drawCards(1);
    }

    private boolean cardClicked(int index){
        return currentPlayer.checkValid(index);
    }

    private boolean cardSelected(){
        this.selectedCard = currentPlayer.getHandCard().get(markedIndex);
        if(this.selectedCard instanceof Targeting) 
            return true;
        return false;
    }





    private void turnEnd(){
        this.currentPlayer = this.currentPlayer.getOpponent();
        this.firstPlayerTurn = !this.firstPlayerTurn;
    }
   
}