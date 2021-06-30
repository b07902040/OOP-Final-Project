package heart.event;

public class EventCardDrawed implements Event {
    private String name = "EventCardDrawed";
    
    @Override
    public String getName() {
        return this.name;
    }
}