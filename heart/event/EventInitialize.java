package heart.event;

public class EventInitialize implements Event{
    private String name = "EventInitialize";

    @Override
    public String getName(){
        return this.name;
    }
}