package src.event;
import src.model.Card;

public class EventCardDraw implements Event{
    private String name = "EventCardDraw";
    private int playerId;
    private boolean fatigue;
    private boolean full;
    private Card card;

    public EventCardDraw(int playerId, boolean fatigue, boolean full, Card card){
        this.playerId = playerId;
        this.fatigue = fatigue;
        this.full = full;
        this.card =card;
    }

    public boolean getFatigue(){
        return this.fatigue;
    }

    public boolean getFull(){
        return this.full;
    }

    public int getPlayerId(){
        return this.playerId;
    }

    public Card getCard(){
        return this.card;
    }


    @Override
    public String getName(){
        return this.name;
    }
   
}