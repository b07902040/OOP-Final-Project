package hearthclone.event;

public class EventMinionSelected implements Event {
    private String name = "EventMinionSelected";

    @Override
    public String getName() {
        return this.name;
    }
}