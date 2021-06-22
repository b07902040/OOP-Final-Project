package src.event;

public class EventTargetMarked implements Event{
    private String name = "EventTargetMarked";

    @Override
    public String getName(){
        return this.name;
    }
}