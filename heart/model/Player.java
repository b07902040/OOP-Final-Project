package heart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import heart.constant.Const;
import heart.model.minion.AbstractMinion;
import heart.model.minion.Hero;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private int playerId;
    private String name;
    private int mana = 0;
    private int fullMana = Const.INIT_MANA;
    private Player opponent;
    private List<Card> deck;
    private List<Card> handCards;
    private int fatigueDamge = 1;
    private List<Minion> ally;
    private List<Minion> enemy;
    private int cardPlayed = 0;
    private Game game;
    private Minion hero;
    private boolean firstPlayer;

    public Player(Hero hero, Game game, boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
        this.playerId = firstPlayer ? 0 : 1;
        // this.name = name;
        this.game = game;
        this.handCards = new ArrayList<Card>();
        this.ally = new ArrayList<Minion>();
        this.hero = hero;
        this.hero.setMaster(this);
        this.ally.add(this.hero);
    }

    public Game getGame() {
        return this.game;
    }

    public boolean getFirstPlayer() {
        return this.firstPlayer;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    /*
     * public String getName(){ return this.name; }
     */

    public Minion getHero() {
        return this.hero;
    }

    public int getMana() {
        return this.mana;
    }

    public void setMana(int mana) {
        System.out.printf("%s %d mana.\n", this.name, mana - this.mana);
        this.mana = Math.min(mana, Const.MAX_MANA);
        this.game.manaChange(this.playerId, this.mana, this.fullMana);
    }

    public void fillMana() {
        this.mana = this.fullMana;
        this.game.manaChange(this.playerId, this.mana, this.fullMana);
    }

    public int getFullMana() {
        return this.fullMana;
    }

    public void setFullMana(int fullMana) {
        this.fullMana = fullMana;
        this.game.manaChange(this.playerId, this.mana, this.fullMana);
    }

    public int getCardPlayed() {
        return this.cardPlayed;
    }

    public void setCardPlayed(int cardPlayed) {
        this.cardPlayed = cardPlayed;
    }

    public Player getOpponent() {
        return this.opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
        this.enemy = opponent.ally;
    }

    public List<Card> getDeck() {
        return this.deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public List<Card> getHandCards() {
        return this.handCards;
    }

    public void setHandCards(List<Card> handCards) {
        this.handCards = handCards;
    }

    public void addHandCards(Card card) {
        if (this.handCards.size() >= Const.MAX_HAND_SIZE)
            return;
        this.handCards.add(card);
        this.game.handCardAdd(this.playerId, card);
    }

    public void throwCard(int index) {
        this.handCards.remove(index);
        this.game.handCardRemove(this.playerId, index);
        this.printPlayerStatus();
    }

    public List<Minion> getAlly() {
        return this.ally;
    }

    public void summonAlly(Minion minion, int index) {
        if (this.ally.size() >= Const.BOARD_SPACE + 1) {
            System.out.printf("Board is full, can't summon ally.\n");
            return;
        }
        minion.setMaster(this);
        minion.setPlayedOrder(this.game.getMinionSummoned());
        this.ally.add(index, minion);
        System.out.printf("%s summon %s to ally.\n", this.name, ((Card) minion).getName());
        this.game.boardAdd(this.playerId, index, minion);
    }

    public void removeAlly(Minion minion) {
        this.ally.remove(minion);
    }

    public List<Minion> getEnemy() {
        return this.enemy;
    }

    public void drawCards(int cardNum) {
        for (int i = 0; i < cardNum; i++) {
            if (this.deck.size() > 0) {
                Card newCard = this.deck.get(0);
                this.deck.remove(0);
                if (this.handCards.size() < Const.MAX_HAND_SIZE) {
                    System.out.printf("%s draws %s.\n", this.name, newCard.getName());
                    this.game.cardDrew(this.playerId, false, false);
                    this.addHandCards(newCard);
                } else {
                    System.out.printf("!!!!!!%s draws %s BOOM!!!!!!!\n", this.name, newCard.getName());
                    this.game.cardDrew(this.playerId, false, true);
                }
            } else {
                System.out.printf("!!!!!!%s draws fatigue!!!!!!\n", this.name);
                this.hero.setHP(this.hero.getHP() - this.fatigueDamge);
                this.fatigueDamge += 1;
                this.game.cardDrew(this.playerId, true, false);
            }
        }
    }

    public boolean checkValidCard(int index) {
        Card card = this.handCards.get(index);
        if (card.getCost() > this.mana)
            return false;
        if (card instanceof Minion) {
            if (this.ally.size() >= Const.BOARD_SPACE + 1)// ADD FOR HERO
                return false;
        }
        if (card instanceof Spell && card instanceof Targeting) {
            if (((Targeting) card).getCandidates(this.ally, this.enemy).size() == 0) {
                if (card instanceof BattleCry)
                    return true;
                else
                    return false;
            }
        }
        return true;
    }

    public void printPlayerStatus() {
        System.out.printf("-------------------------------------------------------\n");
        System.out.printf("%s's turn , Mana: %d , DeckNum: %d , Cardplayed: %d\n", this.name, this.mana,
                this.deck.size(), this.cardPlayed);
        this.printHandCards();
        this.printAlly();
        this.printEnemy();
        System.out.printf("--------------------------------------------------------\n");
    }

    public void printHandCards() {
        System.out.printf("handCard:(%d / %d): ", this.handCards.size(), Const.MAX_HAND_SIZE);
        for (Card card : this.handCards) {
            // System.out.printf("%s ",((AbstractMinion)card).getName());
            // System.out.println(card);
            System.out.printf("%s ", card.getName());
        }
        System.out.printf("\n");
    }

    public void printAlly() {
        System.out.printf("Ally(%d / %d): ", this.ally.size(), Const.BOARD_SPACE + 1);
        for (Minion minion : this.ally) {
            AbstractMinion m = ((AbstractMinion) minion);
            System.out.printf("%s (ATK:%d HP:%d) ", m.getName(), m.getATK(), m.getHP());
        }
        System.out.printf("\n");
    }

    public void printEnemy() {
        System.out.printf("Enemy(%d / %d): ", this.enemy.size(), Const.BOARD_SPACE + 1);
        for (Minion minion : this.enemy) {
            AbstractMinion m = ((AbstractMinion) minion);
            System.out.printf("%s (ATK:%d HP:%d) ", m.getName(), m.getATK(), m.getHP());
        }
        System.out.printf("\n");
    }
}