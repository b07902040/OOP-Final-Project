package hearthclone.event;

public class EventTurnEnd implements Event {
    private String name = "EventTurnEnd";

    @Override
    public String getName() {
        return this.name;
    }
}