package src.event;

public class EventCardPlayed implements Event{
    private String name = "EventCardPlayed";

    @Override
    public String getName(){
        return this.name;
    }
}