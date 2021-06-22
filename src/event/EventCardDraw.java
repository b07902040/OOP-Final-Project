package src.event;

public class EventCardDraw implements Event{
    private String name = "EventCardDraw";

    @Override
    public String getName(){
        return this.name;
    }
}