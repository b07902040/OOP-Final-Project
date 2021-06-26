package src.event;
import src.model.Card;

public class EventCardDraw implements Event{

    //to deck
    private String name = "EventCardDraw";
    private int playerId;
    private boolean fatigue;
    private boolean full;

    public EventCardDraw(int playerId, boolean fatigue, boolean full){
        this.playerId = playerId;
        this.fatigue = fatigue;
        this.full = full;
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

    @Override
    public String getName(){
        return this.name;
    }
   
}