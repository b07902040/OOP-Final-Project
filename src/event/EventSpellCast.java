package src.event;

public class EventSpellCast implements Event{
    private String name = "EventSpellCast";

    @Override
    public String getName(){
        return this.name;
    }
}