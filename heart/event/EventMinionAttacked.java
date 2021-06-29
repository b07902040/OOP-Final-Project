package heart.event;

public class EventMinionAttacked implements Event {
    private String name = "EventMinionAttacked";

    @Override
    public String getName() {
        return this.name;
    }
}