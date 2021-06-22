package src.event;

public class EventMinionAttack implements Event{
    private String name = "EventMinionAttack";

    @Override
    public String getName(){
        return this.name;
    }
}