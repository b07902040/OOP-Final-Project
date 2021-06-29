package heart.event;

public class EventCardEffected implements Event{
    private String name = "EventCardEffected";

    @Override
    public String getName(){
        return this.name;
    }
}