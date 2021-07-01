package hearthclone.event;

public class EventCardSelected implements Event {
    private String name = "EventCardSelected";

    @Override
    public String getName() {
        return this.name;
    }
}