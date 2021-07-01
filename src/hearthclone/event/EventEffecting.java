package hearthclone.event;

import hearthclone.model.Card;

public class EventEffecting implements Event {
    private String name = "EventEffecting";

    private int cardIndex;
    private int cardPlayerIndex;
    private int targetIndex;
    private int targetPlayerIndex;
    private Card card;

    public EventEffecting(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return this.card;
    }

    public int getCardIndex() {
        return this.cardIndex;
    }

    public int getCardPlayerIndex() {
        return this.cardPlayerIndex;
    }

    public int getTargetIndex() {
        return this.targetIndex;
    }

    public int getTargetPlayerIndex() {
        return this.targetPlayerIndex;
    }

    @Override
    public String getName() {
        return this.name;
    }
}