package src.event;

public class EventCardDraw implements Event{
    private String name = "EventCardDraw";
    private boolean fatigue;
    private boolean full;

    public EventCardDraw(boolean fatigue, boolean full){
        this.fatigue = fatigue;
        this.full = full;
    }

    @Override
    public String getName(){
        return this.name;
    }
}