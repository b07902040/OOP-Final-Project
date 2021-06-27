package src.model;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;


import src.model.minion.*;
import src.model.spell.*;
import src.event.*;
import src.constant.*;

public class Game implements EventListener{    
    private EventManager eventManager;
    private StateMachine state = new StateMachine();
    private boolean running = false;
    private int winner;
    
    private DeckLoader deckLoader;
    private List<Player> players;
    private Player currentPlayer;
    private int currentPlayerid;
    private int turn = 0;
    private boolean firstPlayerTurn = true;
    private float timer = 0; 
    private int minionSummoned = 0;
       
    //play card
    private int clickedCardIndex = -1;
    private int clickedMinionIndex = -1;
    private boolean clickedMinionAlly = false;
    private Card selectedCard = null;
    private Minion selectedMinion = null;
    //minion attack
    private int clickedAttackerIndex = -1;
    private boolean clickedAttackerAlly = false;
    private int clickedAttackedIndex = -1;
    private boolean clickedAttackedAlly = false;
    private Minion selectedAttacker = null;
    private Minion selectedAttacked = null;

   

    public Game(EventManager eventManager){
        this.eventManager = eventManager;
        this.eventManager.register(this);        
    }
    
    public void run(){
        Event e = new EventInitialize();
        this.eventManager.post(new EventInitialize());
        this.running = true;
       /* while(this.running){
            //this.eventManager.post(new EventEveryTick());
            //sleep
        }     */
    }

    @Override 
    public void notify(Event event){
        //ADD FOR TEST
        if(event instanceof EventEveryTick) return;
        System.out.printf("Get event: %s.\n",event.getName());
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
            this.stateChange(Const.STATE_PENDING);
            this.currentPlayer.drawCards(1);                  
        }
        else if(event instanceof EventCardDraw){                
            this.checkDeadStatus();
            this.currentPlayer.printPlayerStatus();
            if(winner > 0){
                this.eventManager.post(new EventGameEnd(winner -1));
                this.stateChange(Const.STATE_GAME_END);  
            }
            else 
                this.stateChange(Const.STATE_PENDING);
        }
        else if(event instanceof EventClickedEmpty){
            if(this.isState(Const.STATE_VALID_CARD) || this.isState(Const.STATE_INVALID_CARD))
                this.stateChange(Const.STATE_PENDING);        
            else if(this.isState(Const.STATE_VALID_TARGET) || this.isState(Const.STATE_INVALID_TARGET))
                this.stateChange(Const.STATE_CARD_TARGETING);       
            else if(this.isState(Const.STATE_VALID_ATTACKER) || this.isState(Const.STATE_INVALID_ATTACKER))
                this.stateChange(Const.STATE_PENDING);  
            else if(this.isState(Const.STATE_VALID_ATTACKED) || this.isState(Const.STATE_INVALID_ATTACKED))
                this.stateChange(Const.STATE_ATTACKER_TARGETING);   
        }
        else if(event instanceof EventCardClicked){
            if(this.isState(Const.STATE_PENDING)){
                this.clickedCardIndex = ((EventCardClicked) event).getClickedIndex();
                boolean valid = this.currentPlayer.checkValidCard(this.clickedCardIndex);
                if(valid)
                    this.stateChange(Const.STATE_VALID_CARD);                           
                else
                    this.stateChange(Const.STATE_INVALID_CARD);  
                this.eventManager.post(new EventCardShow(this.clickedCardIndex, valid));  
            }        
        }        
        else if(event instanceof EventCardSelected){
            if(this.isState(Const.STATE_VALID_CARD)){
                this.selectedCard = this.currentPlayer.getHandCards().get(this.clickedCardIndex);
                if(this.selectedCard instanceof Targeting){
                    List<Minion> candidates = ((Targeting) this.selectedCard).getCandidates(this.currentPlayer);
                    if(this.selectedCard instanceof BattleCry &&  candidates.size() == 0){                        
                        System.out.printf("BattleCry no target!\n");
                        this.currentPlayer.setMana(this.currentPlayer.getMana() - this.selectedCard.getCost());
                        ((Minion) this.selectedCard).setMaster(this.currentPlayer);
                        this.playedMinion(this.selectedCard);
                        this.stateChange(Const.STATE_EFFECTING);
                        this.reset();                        
                    }
                    else
                        this.stateChange(Const.STATE_CARD_TARGETING);                                       
                }
                else{
                    this.currentPlayer.setMana(this.currentPlayer.getMana() - this.selectedCard.getCost());
                    if(this.selectedCard instanceof Minion){
                        if(this.selectedCard instanceof BattleCry){
                            ((Minion) this.selectedCard).setMaster(this.currentPlayer);
                            this.playedMinion(this.selectedCard);
                            ((BattleCry) this.selectedCard).doBattleCryEffect(null); 
                        }                                              
                        else 
                            this.playedMinion(this.selectedCard);      
                    }
                    else if(this.selectedCard instanceof Spell){  
                        this.playedSpell(this.selectedCard);                      
                        ((Spell) this.selectedCard).takeEffect(this.currentPlayer, null);
                        
                    }
                    this.stateChange(Const.STATE_EFFECTING);
                    int cardPlayerId = this.currentPlayerid;
                    int targetPlayerId = (this.currentPlayerid + 1) % 2;
                    this.eventManager.post(new EventEffecting(cardPlayerId, this.clickedCardIndex, targetPlayerId,this.clickedMinionIndex));
                    this.reset();                    
                }  
            }
        }   
        else if(event instanceof EventMinionClicked){
            //play card choose target
            if(this.isState(Const.STATE_CARD_TARGETING)){
                this.clickedMinionIndex = ((EventMinionClicked) event).getClickedIndex();
                this.clickedMinionAlly = ((EventMinionClicked) event).getIsAlly();
                boolean valid = this.checkValidMinion();
                if(valid)
                    this.stateChange(Const.STATE_VALID_TARGET);                           
                else
                    this.stateChange(Const.STATE_INVALID_TARGET); 
                int clickedPlayerId = (this.clickedMinionAlly)? this.currentPlayerid : (this.currentPlayerid + 1) % 2;
                this.eventManager.post(new EventMinionShow(clickedPlayerId, this.clickedMinionIndex, valid));
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
                if(canAttack) this.stateChange(Const.STATE_VALID_ATTACKER); 
                else this.stateChange(Const.STATE_INVALID_ATTACKER); 
            }
            //choose attacked
            else if(this.isState(Const.STATE_ATTACKER_TARGETING)){
                this.clickedAttackedIndex = ((EventMinionClicked) event).getClickedIndex();
                this.clickedAttackedAlly = ((EventMinionClicked) event).getIsAlly();
                boolean canAttacked = false;
                if(!this.clickedAttackedAlly){
                    Minion attacked = this.currentPlayer.getEnemy().get(this.clickedAttackedIndex);
                    canAttacked = attacked.canAttacked();                    
                }
                if(canAttacked) this.stateChange(Const.STATE_VALID_ATTACKED); 
                else this.stateChange(Const.STATE_INVALID_ATTACKED); 
            }
        }      
        else if(event instanceof EventMinionSelected){
            if(this.isState(Const.STATE_VALID_TARGET)){
                this.selectedMinion = (this.clickedMinionAlly)? 
                    this.currentPlayer.getAlly().get(this.clickedMinionIndex) : 
                    this.currentPlayer.getEnemy().get(this.clickedMinionIndex);
                this.currentPlayer.setMana(this.currentPlayer.getMana() - this.selectedCard.getCost());
                if(this.selectedCard instanceof BattleCry){
                    ((Minion) this.selectedCard).setMaster(this.currentPlayer);
                    this.playedMinion(this.selectedCard);
                    ((BattleCry) this.selectedCard).doBattleCryEffect(this.selectedMinion);                    
                }
                else if(this.selectedCard instanceof Spell){
                    this.playedSpell(this.selectedCard);                    
                    ((Spell) this.selectedCard).takeEffect(this.currentPlayer,this.selectedMinion);                    
                }
                this.stateChange(Const.STATE_EFFECTING);                
                this.reset();
            }
            else if(this.isState(Const.STATE_VALID_ATTACKER)){
                this.selectedAttacker = this.currentPlayer.getAlly().get(this.clickedAttackerIndex);                
                this.stateChange(Const.STATE_ATTACKER_TARGETING);
            }
            else if(this.isState(Const.STATE_VALID_ATTACKED)){
                this.selectedAttacked = this.currentPlayer.getEnemy().get(this.clickedAttackedIndex);     
                this.selectedAttacker.attack(this.selectedAttacked);  
                this.stateChange(Const.STATE_ATTACKING);
                int attackerPlayerId = this.currentPlayerid;
                int attackedPlayerId = (this.currentPlayerid + 1) % 2;
                this.eventManager.post(new EventAttacking(attackerPlayerId, this.clickedAttackerIndex, attackedPlayerId,this.clickedAttackedIndex));
                this.reset();
            }
        } 
        else if(event instanceof EventCardEffected){
            if(this.isState(Const.STATE_EFFECTING)){                
                this.checkDeadStatus();
                this.currentPlayer.printPlayerStatus();
                if(winner > 0){
                    this.eventManager.post(new EventGameEnd(winner - 1));
                    this.stateChange(Const.STATE_GAME_END);  
                }
                else 
                    this.stateChange(Const.STATE_PENDING);
            }                
        }  
        else if(event instanceof EventMinionAttacked){
            if(this.isState(Const.STATE_ATTACKING)){
                this.checkDeadStatus();
                this.currentPlayer.printPlayerStatus();
                if(winner > 0){
                    this.eventManager.post(new EventGameEnd(winner - 1));
                    this.stateChange(Const.STATE_GAME_END);  
                }
                else
                    this.stateChange(Const.STATE_PENDING);             
            }
        }   
        else if(event instanceof EventDeathRattleTriggered){
            this.checkDeadStatus();
            this.currentPlayer.printPlayerStatus();
            if(winner > 0){
                this.eventManager.post(new EventGameEnd(winner - 1));
                this.stateChange(Const.STATE_GAME_END);  
            }            
        }              
        else if(event instanceof EventTurnEnd){
            if(this.isState(Const.STATE_PENDING))
                this.turnEnd();   
            this.eventManager.post(new EventTurnStart());
        }
        else if(event instanceof EventGameEnd){
            System.out.printf("Game End!! Player %d win.\n",winner);       
        }
    }
    
    public boolean isState(int state){
        return (this.state.peek() == state);
    }

    private void initialize(){      
        this.deckLoader = new DeckLoader();
        this.players = new ArrayList<Player>();
        this.players.add(new Player("Jaina", this, true));
        this.players.add(new Player("Guldan", this, false));
        for(int i = 0; i < 2; i++){
            this.players.get(i).setOpponent(this.players.get((i + 1) % 2));  
            ArrayList<Card> deck = this.deckLoader.loadDeck0();
            //Collections.shuffle(deck,new Random());
            this.players.get(i).setDeck(deck);
            this.eventManager.post(new EventBoardChange(i, 0, this.players.get(i).getHero()));
        }   
        this.currentPlayerid = 0;   
        this.currentPlayer = this.players.get(0);
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
        this.currentPlayer.setFullMana(Math.min(mana + 1, Const.MAX_MANA));
        this.currentPlayer.setCardPlayed(0);      
        this.currentPlayer.fillMana();
        for(Minion minion : this.currentPlayer.getAlly()){
            minion.addAliveTime();
            minion.resetAttackCount();
        }
        this.currentPlayer.printPlayerStatus();
        this.timer = 0;
    }

    public int getMinionSummoned(){
        return this.minionSummoned;
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

    private void playedMinion(Card card){ 
        this.currentPlayer.throwCard(this.clickedCardIndex);             
        this.currentPlayer.summonAlly((Minion) card, this.currentPlayer.getAlly().size());        
        this.currentPlayer.setCardPlayed(this.currentPlayer.getCardPlayed() + 1);
    }

    private void playedSpell(Card card){        
        this.currentPlayer.throwCard(this.clickedCardIndex);
        this.currentPlayer.setCardPlayed(this.currentPlayer.getCardPlayed() + 1);
    }

    private void checkDeadStatus(){
        List<Minion> deadMinions = new ArrayList<Minion>();
        for(Player player : players){
            List<Minion> ally = player.getAlly();
            for(int i = ally.size() - 1; i >= 0; i--){
                Minion minion = ally.get(i);
                if(!minion.isAlive()){
                    deadMinions.add(minion);
                    player.removeAlly(minion);
                    this.boardRemove(player.getPlayerId(), i);
                }
            }
        }
        boolean p1Die = !(players.get(0).getHero().isAlive());
        boolean p2Die = !(players.get(1).getHero().isAlive());
        if(p1Die && p2Die){
            this.winner = Const.PLAYER_DRAW; 
            return;
        }
        else if(p1Die){
            this.winner = Const.PLAYER2_WIN; 
            return;
        }
        else if(p2Die){
            this.winner = Const.PLAYER1_WIN; 
            return;
        }
        Collections.sort(deadMinions, new CompareByPlayedOrder());
        boolean triggered = false;
        for(Minion minion : deadMinions){
            if (minion instanceof DeathRattle){
                triggered = true;
                ((DeathRattle)minion).doDeathRattleEffect();
            }
        }
        if(triggered)
            this.eventManager.post(new EventDeathRattleTriggered());
    }

    private void turnEnd(){
        List<Minion> turnEndMinions = new ArrayList<Minion>();
        for(Minion minion : this.currentPlayer.getAlly())
            turnEndMinions.add(minion);
        Collections.sort(turnEndMinions, new CompareByPlayedOrder());
        for(Minion minion : turnEndMinions){
            if(minion.isAlive())
                minion.doTurnEnd();
            this.checkDeadStatus();
            if(winner > 0){
                this.eventManager.post(new EventGameEnd(winner -1));
                this.stateChange(Const.STATE_GAME_END); 
                break; 
            }
        }
        //end
        this.currentPlayer = this.currentPlayer.getOpponent();
        this.currentPlayerid = this.currentPlayer.getPlayerId(); 
        this.firstPlayerTurn = !this.firstPlayerTurn;
    }
   
    private void reset(){
        this.clickedCardIndex = -1;
        this.clickedMinionIndex = -1;
        this.clickedMinionAlly = false;
        this.selectedCard = null;
        this.selectedMinion = null;

        this.clickedAttackerIndex = -1;
        this.clickedAttackerAlly = false;
        this.clickedAttackedIndex = -1;
        this.clickedAttackedAlly = false;
        this.selectedAttacker = null;
        this.selectedAttacked = null;
    } 

    public void stateChange(int state){
        this.state.push(state); 
        this.eventManager.post(new EventStateChange(state));
    }
    public void cardDrew(int playerId, boolean fatigue, boolean full){
        this.eventManager.post(new EventCardDraw(playerId, fatigue, full));
    }

    public void handCardAdd(int playerId, Card card){
        this.eventManager.post(new EventHandCardChange(playerId, card));
    }

    public void handCardRemove(int playerId, int index){
        this.eventManager.post(new EventHandCardChange(playerId, index));
    }

    public void manaChange(int playerId, int mana, int fullMana){
        this.eventManager.post(new EventManaChange(playerId, mana, fullMana));
    }

    public void boardRemove(int playerId, int index){
        this.eventManager.post(new EventBoardChange(playerId, index));
    }   

    public void boardAdd(int playerId, int index, Minion minion){
        this.minionSummoned++;
        this.eventManager.post(new EventBoardChange(playerId, index, minion));
    }
    
    public void minionChange(int playerId, int id, Minion minion){
        this.eventManager.post(new EventMinionChange(playerId, id, minion));
    }
    
    public static int getRandom(int range){
        return ((int) (Math.random() * range + 1));
    }
}   

class CompareByPlayedOrder implements Comparator<Minion>{
    
    @Override
    public int compare(Minion minion1, Minion minion2) {
        return minion1.getPlayedOrder() - minion2.getPlayedOrder();
    }
}