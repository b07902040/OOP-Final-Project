package src.event;

public class EventTargetClicked implements Event{
    private String name = "EventTargetClicked";

    @Override
    public String getName(){
        return this.name;
    }
}