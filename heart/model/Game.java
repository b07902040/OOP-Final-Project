package heart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import heart.constant.Const;
import heart.event.*;
import heart.model.minion.*;
import heart.model.spell.*;

public class Game implements EventListener, Serializable {
    private static final long serialVersionUID = 1L;
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
    // private float timer = 0;
    private int minionSummoned = 0;

    // play card
    private int clickedCardIndex = -1;
    private int clickedMinionIndex = -1;
    private int clickedPlayerId = -1;
    private Card selectedCard = null;
    private Minion selectedMinion = null;
    // minion attack
    private int clickedAttackerIndex = -1;
    private int clickedAttackedIndex = -1;
    private Minion selectedAttacker = null;
    private Minion selectedAttacked = null;

    private int attackedCount = 0;
    private int effectedCount = 0;

    public Game(EventManager eventManager) {
        this.eventManager = eventManager;
        this.eventManager.register(this);
    }

    public void run() {
        this.eventManager.post(new EventInitialize());
        this.running = true;
        while (this.running) {
            this.eventManager.post(new EventEveryTick());
            // sleep
            try {
                Thread.sleep(1000 / Const.FPS);
            } catch (Exception e) {
            }
            ;
        }
    }

    @Override
    public void notify(Event event) {
        // ADD FOR TEST
        if (event instanceof EventEveryTick)
            return;
        System.out.printf("Get event: %s.\n", event.getName());
        System.out.printf("Current state: %s.\n", this.getState());
        if (event instanceof EventInitialize) {
            this.initialize();
            this.eventManager.post(new EventGameStart());
        } else if (event instanceof EventGameStart) {
            this.gameStart();
            this.eventManager.post(new EventTurnStart());
        } else if (event instanceof EventTurnStart) {
            this.turnStart();
            this.stateChange(Const.STATE_PENDING);
            this.currentPlayer.drawCards(1);
        } else if (event instanceof EventCardDraw) {
            this.checkDeadStatus();
            this.currentPlayer.printPlayerStatus();
            if (winner > 0) {
                this.stateChange(Const.STATE_GAME_END);
                this.eventManager.post(new EventGameEnd(winner - 1));
            } else
                this.stateChange(Const.STATE_PENDING);
        } else if (event instanceof EventClickedEmpty) {
            if (this.isState(Const.STATE_VALID_CARD) || this.isState(Const.STATE_INVALID_CARD))
                this.stateChange(Const.STATE_PENDING);
            else if (this.isState(Const.STATE_VALID_TARGET) || this.isState(Const.STATE_INVALID_TARGET))
                this.stateChange(Const.STATE_CARD_TARGETING);
            else if (this.isState(Const.STATE_VALID_ATTACKER) || this.isState(Const.STATE_INVALID_ATTACKER))
                this.stateChange(Const.STATE_PENDING);
            else if (this.isState(Const.STATE_VALID_ATTACKED) || this.isState(Const.STATE_INVALID_ATTACKED))
                this.stateChange(Const.STATE_ATTACKER_TARGETING);
        } else if (event instanceof EventCardClicked) {
            if (this.isState(Const.STATE_PENDING) || this.isState(Const.STATE_VALID_CARD)
                    || this.isState(Const.STATE_INVALID_CARD)) {
                this.clickedCardIndex = ((EventCardClicked) event).getClickedIndex();
                boolean valid = this.currentPlayer.checkValidCard(this.clickedCardIndex);
                if (valid)
                    this.stateChange(Const.STATE_VALID_CARD);
                else
                    this.stateChange(Const.STATE_INVALID_CARD);
                this.eventManager.post(new EventCardShow(this.clickedCardIndex, valid));
            }
        } else if (event instanceof EventCardSelected) {
            if (this.isState(Const.STATE_VALID_CARD)) {
                this.selectedCard = this.currentPlayer.getHandCards().get(this.clickedCardIndex);
                if (this.selectedCard instanceof Targeting) {
                    List<Minion> candidates = ((Targeting) this.selectedCard).getCandidates(this.currentPlayer.getAlly(), this.currentPlayer.getEnemy());
                    if (this.selectedCard instanceof BattleCry && candidates.size() == 0) {
                        System.out.printf("BattleCry no target!\n");
                        this.currentPlayer.setMana(this.currentPlayer.getMana() - this.selectedCard.getCost());
                        ((Minion) this.selectedCard).setMaster(this.currentPlayer);
                        this.stateChange(Const.STATE_NULL);
                        this.playedMinion(this.selectedCard);          
                        this.stateChange(Const.STATE_EFFECTING);              
                        this.eventManager.post(new EventEffecting(this.selectedCard));
                        this.reset();
                    } else
                        this.stateChange(Const.STATE_CARD_TARGETING);
                } else {
                    this.currentPlayer.setMana(this.currentPlayer.getMana() - this.selectedCard.getCost());
                    if (this.selectedCard instanceof Minion) {
                        if (this.selectedCard instanceof BattleCry) {
                            ((Minion) this.selectedCard).setMaster(this.currentPlayer);
                            this.stateChange(Const.STATE_NULL);
                            this.playedMinion(this.selectedCard);
                            ((BattleCry) this.selectedCard).doBattleCryEffect(null);
                        } else{
                            this.stateChange(Const.STATE_NULL);
                            this.playedMinion(this.selectedCard);
                        }
                    } else if (this.selectedCard instanceof Spell) {
                        this.stateChange(Const.STATE_NULL);
                        this.playedSpell(this.selectedCard);
                        ((Spell) this.selectedCard).takeEffect(this.currentPlayer, null);
                    }
                    this.stateChange(Const.STATE_EFFECTING);
                    this.eventManager.post(new EventEffecting(this.selectedCard));
                    this.reset();
                }
            }
        } else if (event instanceof EventMinionClicked) {
            // play card choose target
            if (this.isState(Const.STATE_CARD_TARGETING)) {
                this.clickedMinionIndex = ((EventMinionClicked) event).getClickedIndex();
                this.clickedPlayerId = ((EventMinionClicked) event).getPlayerId();
                boolean valid = this.checkValidMinion();
                if (valid)
                    this.stateChange(Const.STATE_VALID_TARGET);
                else
                    this.stateChange(Const.STATE_INVALID_TARGET);
                this.eventManager.post(new EventMinionShow(this.clickedPlayerId, this.clickedMinionIndex, valid));
            }
            // choose attacker
            else if (this.isState(Const.STATE_PENDING)) {
                this.clickedAttackerIndex = ((EventMinionClicked) event).getClickedIndex();
                this.clickedPlayerId = ((EventMinionClicked) event).getPlayerId();
                boolean canAttack = false;
                if (((EventMinionClicked) event).getPlayerId() == this.currentPlayerid) {
                    Minion attacker = this.currentPlayer.getAlly().get(this.clickedAttackerIndex);
                    canAttack = attacker.canAttack();
                }
                if (canAttack)
                    this.stateChange(Const.STATE_VALID_ATTACKER);
                else
                    this.stateChange(Const.STATE_INVALID_ATTACKER);
                this.eventManager.post(new EventMinionShow(this.clickedPlayerId, this.clickedAttackerIndex, canAttack));
            }
            // choose attacked
            else if (this.isState(Const.STATE_ATTACKER_TARGETING)) {
                this.clickedAttackedIndex = ((EventMinionClicked) event).getClickedIndex();
                this.clickedPlayerId = ((EventMinionClicked) event).getPlayerId();
                boolean canAttacked = false;
                if (!(((EventMinionClicked) event).getPlayerId() == this.currentPlayerid)) {
                    Minion attacked = this.currentPlayer.getEnemy().get(this.clickedAttackedIndex);
                    canAttacked = attacked.canAttacked();
                }
                if (canAttacked)
                    this.stateChange(Const.STATE_VALID_ATTACKED);
                else
                    this.stateChange(Const.STATE_INVALID_ATTACKED);
                this.eventManager
                        .post(new EventMinionShow(this.clickedPlayerId, this.clickedAttackedIndex, canAttacked));
            }
        } else if (event instanceof EventMinionSelected) {
            if (this.isState(Const.STATE_VALID_TARGET)) {
                this.selectedMinion = this.players.get(this.clickedPlayerId).getAlly().get(this.clickedMinionIndex);
                this.currentPlayer.setMana(this.currentPlayer.getMana() - this.selectedCard.getCost());
                if (this.selectedCard instanceof BattleCry) {
                    ((Minion) this.selectedCard).setMaster(this.currentPlayer);
                    this.stateChange(Const.STATE_NULL);
                    this.playedMinion(this.selectedCard);
                    ((BattleCry) this.selectedCard).doBattleCryEffect(this.selectedMinion);
                } else if (this.selectedCard instanceof Spell) {
                    this.stateChange(Const.STATE_NULL);
                    this.playedSpell(this.selectedCard);
                    ((Spell) this.selectedCard).takeEffect(this.currentPlayer, this.selectedMinion);
                }
                this.stateChange(Const.STATE_EFFECTING);
                this.eventManager.post(new EventEffecting(this.selectedCard));
                this.reset();
            } else if (this.isState(Const.STATE_VALID_ATTACKER)) {
                this.selectedAttacker = this.currentPlayer.getAlly().get(this.clickedAttackerIndex);
                this.stateChange(Const.STATE_ATTACKER_TARGETING);
            } else if (this.isState(Const.STATE_VALID_ATTACKED)) {
                this.selectedAttacked = this.currentPlayer.getEnemy().get(this.clickedAttackedIndex);
                this.selectedAttacker.attack(this.selectedAttacked);
                this.stateChange(Const.STATE_ATTACKING);
                int attackerPlayerId = this.currentPlayerid;
                int attackedPlayerId = (this.currentPlayerid + 1) % 2;
                this.eventManager.post(new EventAttacking(attackerPlayerId, this.clickedAttackerIndex, attackedPlayerId,
                        this.clickedAttackedIndex));
                this.reset();
            }
        } else if (event instanceof EventCardEffected) {
            this.effectedCount++;
            if (this.isState(Const.STATE_EFFECTING) && this.effectedCount >= 2) {
                this.checkDeadStatus();
                this.currentPlayer.printPlayerStatus();
                if (winner > 0) {
                    this.stateChange(Const.STATE_GAME_END);
                    this.eventManager.post(new EventGameEnd(winner - 1));
                } else
                    this.stateChange(Const.STATE_PENDING);
                this.effectedCount = 0;
            }   
        } else if (event instanceof EventMinionAttacked) {
            this.attackedCount++;
            if (this.isState(Const.STATE_ATTACKING) && this.attackedCount >= 2) {
                this.checkDeadStatus();
                this.currentPlayer.printPlayerStatus();
                if (winner > 0) {
                    this.stateChange(Const.STATE_GAME_END);
                    this.eventManager.post(new EventGameEnd(winner - 1));
                } else
                    this.stateChange(Const.STATE_PENDING);
                this.attackedCount = 0;
            }
        } else if (event instanceof EventDeathRattleTriggered) {
            System.out.printf("DeathRattle!!!!!!!!\n");
            this.checkDeadStatus();
            this.currentPlayer.printPlayerStatus();
            if (winner > 0) {
                this.stateChange(Const.STATE_GAME_END);
                this.eventManager.post(new EventGameEnd(winner - 1));
            }
        } else if (event instanceof EventTurnEnd) {
            if (this.isState(Const.STATE_PENDING)) {
                this.turnEnd();
                this.eventManager.post(new EventTurnStart());
            }
        } else if (event instanceof EventGameEnd) {
            System.out.printf("Game End!! Player %d win.\n", winner);
        }

    }

    public boolean isState(int state) {
        return (this.state.peek() == state);
    }

    private void initialize() {
        this.deckLoader = new DeckLoader();
        this.players = new ArrayList<Player>();
        this.players.add(new Player(new Jaina(), this, true));
        this.players.add(new Player(new Guldan(), this, false));
        ArrayList<ArrayList<Card>> decks = this.deckLoader.loadDecks();
        for (int i = 0; i < 2; i++) {
            this.players.get(i).setOpponent(this.players.get((i + 1) % 2));
            this.players.get(i).setDeck(decks.get(i));
            this.eventManager.post(new EventBoardChange(i, 0, this.players.get(i).getHero()));
        }
        this.currentPlayerid = 0;
        this.currentPlayer = this.players.get(0);
    }

    private void gameStart() {
        for (int i = 0; i < 2; i++) {
            this.players.get(i).drawCards(Const.STARTING_HAND_SIZE + i);
        }
        this.players.get(1).addHandCards(new Coin());
    }

    private void turnStart() {
        if (firstPlayerTurn)
            this.turn += 1;
        int mana = this.currentPlayer.getFullMana();
        this.currentPlayer.setFullMana(Math.min(mana + 1, Const.MAX_MANA));
        this.currentPlayer.setCardPlayed(0);
        this.currentPlayer.fillMana();
        for (Minion minion : this.currentPlayer.getAlly()) {
            minion.addAliveTime();
            minion.resetAttackCount();
        }
        this.currentPlayer.printPlayerStatus();
        // this.timer = 0;
    }

    public int getTurn() {
        return this.turn;
    }

    public int getMinionSummoned() {
        return this.minionSummoned;
    }

    private boolean checkValidMinion() {
        Minion target = this.players.get(this.clickedPlayerId).getAlly().get(this.clickedMinionIndex);
        if (this.selectedCard instanceof Targeting) {
            List<Minion> candidates = ((Targeting) this.selectedCard).getCandidates(this.currentPlayer.getAlly(), this.currentPlayer.getEnemy());
            return candidates.contains(target);
        } else
            return false;
    }

    private void playedMinion(Card card) {
        this.currentPlayer.throwCard(this.clickedCardIndex);
        this.currentPlayer.summonAlly((Minion) card, this.currentPlayer.getAlly().size());
        this.currentPlayer.setCardPlayed(this.currentPlayer.getCardPlayed() + 1);
    }

    private void playedSpell(Card card) {
        this.currentPlayer.throwCard(this.clickedCardIndex);
        this.currentPlayer.setCardPlayed(this.currentPlayer.getCardPlayed() + 1);
    }

    private void checkDeadStatus() {
        List<Minion> deadMinions = new ArrayList<Minion>();
        for (Player player : players) {
            List<Minion> ally = player.getAlly();
            for (int i = ally.size() - 1; i >= 1; i--) {
                Minion minion = ally.get(i);
                if (!minion.isAlive()) {
                    deadMinions.add(minion);
                    player.removeAlly(minion);
                    this.boardRemove(player.getPlayerId(), i);
                }
            }
        }
        boolean p1Die = !(players.get(0).getHero().isAlive());
        boolean p2Die = !(players.get(1).getHero().isAlive());
        if (p1Die && p2Die) {
            this.winner = Const.PLAYER_DRAW;
            return;
        } else if (p1Die) {
            this.winner = Const.PLAYER2_WIN;
            return;
        } else if (p2Die) {
            this.winner = Const.PLAYER1_WIN;
            return;
        }
        Collections.sort(deadMinions, new CompareByPlayedOrder());
        boolean triggered = false;
        for (Minion minion : deadMinions) {
            if (minion instanceof DeathRattle) {
                triggered = true;
                ((DeathRattle) minion).doDeathRattleEffect();
            }
        }
        if (triggered)
            this.eventManager.post(new EventDeathRattleTriggered());
    }

    private void turnEnd() {
        List<Minion> turnEndMinions = new ArrayList<Minion>();
        for (Minion minion : this.currentPlayer.getAlly())
            turnEndMinions.add(minion);
        Collections.sort(turnEndMinions, new CompareByPlayedOrder());
        for (Minion minion : turnEndMinions) {
            if (minion.isAlive())
                minion.doTurnEnd();
            this.checkDeadStatus();
            if (winner > 0) {
                this.stateChange(Const.STATE_GAME_END);
                this.eventManager.post(new EventGameEnd(winner - 1));
                break;
            }
        }
        // end
        this.currentPlayer = this.currentPlayer.getOpponent();
        this.currentPlayerid = this.currentPlayer.getPlayerId();
        this.firstPlayerTurn = !this.firstPlayerTurn;
    }

    private void reset() {
        this.clickedCardIndex = -1;
        this.clickedMinionIndex = -1;
        this.clickedPlayerId = -1;
        this.selectedCard = null;
        this.selectedMinion = null;

        this.clickedAttackerIndex = -1;
        this.clickedAttackedIndex = -1;
        this.selectedAttacker = null;
        this.selectedAttacked = null;
    }

    public void stateChange(int state) {
        this.state.push(state);
        this.eventManager.post(new EventStateChange(state));
    }

    public void cardDrew(int playerId, boolean fatigue, boolean full) {
        this.eventManager.post(new EventCardDraw(playerId, fatigue, full));
    }

    public void handCardAdd(int playerId, Card card) {
        this.eventManager.post(new EventHandCardChange(playerId, card));
    }

    public void handCardRemove(int playerId, int index) {
        this.eventManager.post(new EventHandCardChange(playerId, index));
    }

    public void manaChange(int playerId, int mana, int fullMana) {
        this.eventManager.post(new EventManaChange(playerId, mana, fullMana));
    }

    public void boardRemove(int playerId, int index) {
        this.eventManager.post(new EventBoardChange(playerId, index));
    }

    public void boardAdd(int playerId, int index, Minion minion) {
        this.minionSummoned++;
        this.eventManager.post(new EventBoardChange(playerId, index, minion));
    }

    public void minionChange(int playerId, int id, Minion minion) {
        Minion newMinion = ((AbstractMinion) minion).clone();
        System.out.printf("%s %d\n",((Card)newMinion).getName(),minion.getHP());
        this.eventManager.post(new EventMinionChange(playerId, id, newMinion));
    }

    public static int getRandom(int range) {
        return ((int) (Math.random() * range + 1));
    }

    private String getState() {
        String state = "STATE_INITIALIZED";
        if (this.isState(Const.STATE_PENDING))
            state = "STATE_PENDING";
        else if (this.isState(Const.STATE_GAME_END))
            state = "STATE_GAME_END";
        else if (this.isState(Const.STATE_VALID_CARD))
            state = "STATE_VALID_CARD";
        else if (this.isState(Const.STATE_INVALID_CARD))
            state = "STATE_INVALID_CARD";
        else if (this.isState(Const.STATE_CARD_TARGETING))
            state = "STATE_CARD_TARGETING";
        else if (this.isState(Const.STATE_VALID_TARGET))
            state = "STATE_VALID_TARGET";
        else if (this.isState(Const.STATE_INVALID_TARGET))
            state = "STATE_INVALID_TARGET";
        else if (this.isState(Const.STATE_EFFECTING))
            state = "STATE_EFFECTING";
        else if (this.isState(Const.STATE_VALID_ATTACKER))
            state = "STATE_VALID_ATTACKER";
        else if (this.isState(Const.STATE_INVALID_ATTACKER))
            state = "STATE_INVALID_ATTACKER";
        else if (this.isState(Const.STATE_ATTACKER_TARGETING))
            state = "STATE_ATTACKER_TARGETING";
        else if (this.isState(Const.STATE_VALID_ATTACKED))
            state = "STATE_VALID_ATTACKED";
        else if (this.isState(Const.STATE_INVALID_ATTACKED))
            state = "STATE_INVALID_ATTACKED";
        else if (this.isState(Const.STATE_ATTACKING))
            state = "STATE_ATTACKING";
        return state;
    }
}

class CompareByPlayedOrder implements Comparator<Minion> {

    @Override
    public int compare(Minion minion1, Minion minion2) {
        return minion1.getPlayedOrder() - minion2.getPlayedOrder();
    }
}