package src.event;

public class EventGameStart implements Event{
    private String name = "EventGameStart";

    @Override
    public String getName(){
        return this.name;
    }
}