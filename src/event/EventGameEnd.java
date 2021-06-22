package src.event;

public class EventGameEnd implements Event{
    private String name = "EventGameEnd";

    @Override
    public String getName(){
        return this.name;
    }
}