package src.event;

public class EventCardDraw implements Event{
    private String name = "EventCardDraw";
    private boolean fatigue;
    private boolean full;
    private int playerId;
    
    public EventCardDraw(boolean fatigue, boolean full, int playerId){
        this.fatigue = fatigue;
        this.full = full;
        this.playerId = playerId;
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