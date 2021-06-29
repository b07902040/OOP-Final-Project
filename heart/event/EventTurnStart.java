package heart.event;

public class EventTurnStart implements Event {
    private String name = "EventTurnStart";

    @Override
    public String getName() {
        return this.name;
    }
}