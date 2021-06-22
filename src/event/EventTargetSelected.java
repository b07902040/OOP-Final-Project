package src.event;

public class EventTargetSelected implements Event{
    private String name = "EventTargetSelected";

    @Override
    public String getName(){
        return this.name;
    }
}